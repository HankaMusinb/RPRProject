package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Kategorije;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KategorijeDaoSQLImpl implements KategorijeDao {
    private Connection connection;

    public KategorijeDaoSQLImpl() {
        try{
            this.connection = DriverManager.getConnection("sql.freedb.tech:3306/freedb_RPRProject", "freedb_hmusinbego1", "*bcuN9B@#52h&qn" );
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    @Override
    public Kategorije getById(int id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM categories WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                Kategorije kategorija = new Kategorije();
                kategorija.setId(rs.getInt("id"));
                //kategorija.setKategorija(KategorijeDaoSQLImpl(getById(rs.getRowId()("kategorija")))); //ovo samo kad se doda implementacija i toga ce raditi....
             //   kategorija.setKategorija(new KategorijeDaoSQLImpl().getById(rs.getInt("quote"))); //ovo samo kad se doda implementacija i toga ce raditi....
                rs.close();
                return kategorija;
            }else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }
    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT MAX(id)+1 FROM Categories");
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
                rs.close();
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return id;
    }
    @Override
    public Kategorije add(Kategorije item) {
        int id = getMaxId();
        try {
            PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO Categories VALUES (id, item.get.Naziv().getId())");
            stmt.executeUpdate();
            item.setId(id);
            return item;
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Kategorije update(Kategorije item) {

        try{
            PreparedStatement stmt = this.connection.prepareStatement("UPDATE Categories SET id=?, kateogorija=? WHERE id=?");
            //stmt.setInt(1, item.getKategorija().getInt());

            stmt.setInt(3, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try{
            PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM Categories WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Kategorije> getAll() {

        List<Kategorije> kategorije = new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Categories");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Kategorije kategorija = new Kategorije();
                kategorija.setId(rs.getInt("id"));
                //kategorija.setKategorija(new KategorijeDaoSQLImpl(kategorija.getId())); //ovo samo kad se doda implementacija i toga ce raditi....
                kategorija.setKategorija(String.valueOf(new KategorijeDaoSQLImpl().getById(rs.getInt("kategorija"))));
                kategorije.add(kategorija);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return kategorije;
    }
}
