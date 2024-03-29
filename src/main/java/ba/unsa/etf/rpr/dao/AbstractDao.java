package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.ArtikliException;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Abstract class that implements DAO CRUD methods for every entity
 *
 * @author Hanka Musinbegovic
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private  static Connection connection = null;

    //private static Connection connection;
    private String tableName;
    public AbstractDao(String tableName) {
       /* try{
            //if(connection == null) createConnection();
            this.tableName = tableName;
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = p.getProperty("db.connection_string");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_RPRProject", "freedb_hmusinbego1", "****");
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }*/
        this.tableName = tableName;
        createConnection();
    }

    private static void createConnection(){
        if(AbstractDao.connection==null){
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("application.properties").openStream());
                String url = p.getProperty("db.connection_string");
                String username = p.getProperty("db.username");
                String password = p.getProperty("db.password");
                System.out.println("Create connection");
                System.out.println(url);
                System.out.println(username);
                System.out.println(password);
                AbstractDao.connection=DriverManager.getConnection(url,username,password);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Runtime.getRuntime().addShutdownHook(new Thread(){
                    @Override
                    public void run(){
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
    public Connection getConnection(){

        return AbstractDao.connection;
    }

    /**
     * Method for mapping ResultSet into Object
     * @param rs - result set from database
     * @return a Bean object for specific table

     */
    public abstract T row2object(ResultSet rs) throws ArtikliException;
    /**
     * Method for mapping Object into Map
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    public abstract Map<String, Object> object2row(T object);
    public T getById(int id) throws ArtikliException {
       /* try{
            return executeQueryUnique("SELECT * FROM "+this.tableName+" WHERE id = ?", new Object[]{id});
        }catch (ArtikliException e){
            throw new ArtikliException("Problem sa getbyId u ArtikliDaoSQLImpl");
        }*/
        return executeQueryUnique("SELECT * FROM " + this.tableName + " WHERE id = ?", new Object[]{id});

    }

    public List<T> getAll() throws ArtikliException {
        String query = "SELECT * FROM " + tableName;
        System.out.println(query);
        return executeQuery(query, null);
    }


    public void delete(int id) throws ArtikliException {
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new ArtikliException(e.getMessage(), e);
        }
    }



    /**
     * Utility method for executing any kind of query
     * @param query - SQL query
     * @param params - params for query
     * @return List of objects from database
     */
    public List<T> executeQuery(String query, Object[] params) throws ArtikliException{
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ArtikliException("Problemi sa executequery metodom");
        }
    }


    /**
     * Utility for query execution that always return single record
     * @param query - query that returns single record
     * @param params - list of params for sql query
     * @return Object
     */
    public T executeQueryUnique(String query, Object[] params) throws ArtikliException{
        List<T> result = executeQuery(query, params);
        System.out.println(result);
        if (result != null && result.size() == 1){
            return result.get(0);
        }else{
            throw new ArtikliException("Problemi sa dovrsavanjem radnje");
        }
    }



    /**
     * Accepts KV storage of column names and return CSV of columns and question marks for insert statement
     * Example: (id, name, date) ?,?,?
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        List<String> columnNames = row.keySet()
                .stream()
                .filter(column -> !column.equals("id"))
                .collect(Collectors.toList());
        String columns = String.join(",", columnNames);
        String questions = columnNames.stream().map(column -> "?").collect(Collectors.joining(","));
        return new AbstractMap.SimpleEntry<>(columns, questions);
    }
    /**
     * Prepare columns for update statement id=?, name=?, ...
     * @param row - row to be converted intro string
     * @return String for update statement
     */
    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip update of id due where clause
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }

    public T add(T item) throws ArtikliException{
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            String query = builder.toString();
            System.out.println(query);
            PreparedStatement stmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */

            return item;
        }catch (SQLException e){
            e.printStackTrace();
            throw new ArtikliException("Problem je add metoda");
        }
    }



    public T update(T item) throws ArtikliException{
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new ArtikliException("Problem s update metodom", e);
        }
    }

}
