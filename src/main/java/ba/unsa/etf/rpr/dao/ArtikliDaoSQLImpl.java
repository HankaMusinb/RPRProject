package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artikli;
import ba.unsa.etf.rpr.exceptions.ArtikliException;


import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.*;
public class ArtikliDaoSQLImpl extends AbstractDao<Artikli>  implements ArtikliDao {

    public ArtikliDaoSQLImpl(){
        super("Artikli");
    }
    private Connection connection;


    @Override
    public List<Artikli> traziPoIstekuRoka(Date rok) throws ArtikliException {
        return executeQuery("SELECT * FROM Artikli WHERE istekRoka = ?", new Object[]{rok});
    }

    @Override
    public List<Artikli> traziPoCijeni(int cijena) throws ArtikliException {

            return executeQuery("SELECT * FROM Artikli WHERE cijena = ?", new Object[]{cijena});

    }

    @Override
    public Artikli row2object(ResultSet rs) throws ArtikliException {
        try{
            Artikli a = new Artikli();
            a.setId(rs.getInt("idArtikli"));
            a.setNaziv(rs.getString("naziv"));
            a.setCijena(rs.getInt("cijena"));
            a.setIstekRoka(rs.getDate("istekRoka"));
            a.setKategorija(DaoFactory.kategorijeDao().getById(rs.getInt("idKategorije")));
            //a.setProdaja(DaoFactory.kategorijeDao().getById(rs.getInt("idKategorije")));
            return a;
        } catch (Exception e) {
            throw new ArtikliException(e.getMessage(),e);
        }
    }
    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Artikli object) {
        Map<String,Object> artikal = new TreeMap<>();
        artikal.put("idArtikli", object.getId());
        artikal.put("naziv",object.getNaziv());
        artikal.put("cijena",object.getCijena());
        artikal.put("istekRoka",object.getIstekRoka());
        artikal.put("idKategorije", object.getKategorija().getId());
        return artikal;
    }


}
