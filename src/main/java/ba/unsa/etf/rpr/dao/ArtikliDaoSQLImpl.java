package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artikli;

import java.sql.*;
import java.util.Date;
import java.util.List;
public class ArtikliDaoSQLImpl implements ArtikliDao {

    private Connection connection;

    public ArtikliDaoSQLImpl() {
        try{
            this.connection = DriverManager.getConnection("sql.freedb.tech:3306/freedb_RPRProject", "freedb_hmusinbego1", "*bcuN9B@#52h&qn" );
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public List<Artikli> traziPoIstekuRoka(Date rok) {
        return null;
    }

    @Override
    public List<Artikli> traziPoCijeni(int cijena) {
        return null;
    }

    @Override
    public Artikli getById(int id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM categories WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = ((PreparedStatement) stmt).executeQuery();
            if (((ResultSet) rs).next()){
                Artikli artikal = new Artikli();
                artikal.setId(rs.getInt("id"));
                //Artikli.setNaziv(ArtikliDaoSQLImpl(getById(rs.getString("artikal")))); //ovo samo kad se doda implementacija i toga ce raditi....
                rs.close();
                return artikal;
            }else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT MAX(id)+1 FROM artikli");
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
    public Artikli add(Artikli item) {
        int id = getMaxId();
        try {
            PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO artikli VALUES (id, item.getArtikal().getId(), item.getGenerated())");
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
    public Artikli update(Artikli item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Artikli> getAll() {
        return null;
    }
}
