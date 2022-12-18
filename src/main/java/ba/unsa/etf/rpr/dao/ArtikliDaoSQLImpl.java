package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artikli;


import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.*;
public class ArtikliDaoSQLImpl  implements ArtikliDao {

    private Connection connection;

    public ArtikliDaoSQLImpl() {
        super();
        try{
            this.connection = DriverManager.getConnection("sql.freedb.tech:3306/freedb_RPRProject", "freedb_hmusinbego1", "*bcuN9B@#52h&qn" );
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public List<Artikli> traziPoIstekuRoka(Date rok) {
        List<Artikli> rokovi = new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Articles WHERE generated IstekRoka");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Artikli artikal = new Artikli();
                artikal.setId(rs.getInt("id"));
                //artikal.setNaziv(ArtikliDaoSQLImpl(getById(rs.getString("quote")))); //ovo samo kad se doda implementacija i toga ce raditi....
                artikal.setNaziv(String.valueOf(new ArtikliDaoSQLImpl().getById(rs.getInt("naziv"))));
                rokovi.add(artikal);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return rokovi;
    }

    @Override
    public List<Artikli> traziPoCijeni(int cijena) {
        List<Artikli> cijene = new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Articles WHERE generated cijena");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Artikli artikal = new Artikli();
                artikal.setId(rs.getInt("id"));
                //artikal.setNaziv(ArtikliDaoSQLImpl(getById(rs.getString("quote")))); //ovo samo kad se doda implementacija i toga ce raditi....
                artikal.setNaziv(String.valueOf(new ArtikliDaoSQLImpl().getById(rs.getInt("cijena"))));
                cijene.add(artikal);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return cijene;
    }

    @Override
    public Artikli getById(int id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Categories WHERE id = ?");
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
            PreparedStatement statement = this.connection.prepareStatement("SELECT MAX(id)+1 FROM Articles");
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
            PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO Articles VALUES (id, item.getArtikal().getId(), item.getNaziv())");
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
        try{
            PreparedStatement stmt = this.connection.prepareStatement("UPDATE Articles SET artikal=?, generated=? WHERE id=?");
            stmt.setInt(1, item.getId());
            stmt.setString(2,item.getNaziv());
            stmt.setInt(3, item.getCijena());
            stmt.setDate(5, (java.sql.Date) item.getIstekRoka());
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
            PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM Articles WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Artikli> getAll() {
        List<Artikli> artikli = new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Articles");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Artikli artikl = new Artikli();
                artikl.setId(rs.getInt("id"));

                //artikl.setNaziv(new ArtikliDaoSQLImpl(getById(Integer.parseInt(rs.getString("artikal"))))); //ovo samo kad se doda implementacija i toga ce raditi....
                artikli.add(artikl);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return artikli;
    }
}
