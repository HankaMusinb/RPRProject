package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Kategorije;
import ba.unsa.etf.rpr.exceptions.ArtikliException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * //MySQL implementation of the DAO
 */
public class KategorijeDaoSQLImpl extends AbstractDao<Kategorije> implements KategorijeDao {
    private Connection connection;

    public KategorijeDaoSQLImpl() {
        super("Kategorije");
    }

    @Override
    public Kategorije row2object(ResultSet rs) throws ArtikliException {

        try {
            Kategorije kategorije = new Kategorije();
            kategorije.setId(rs.getInt("id"));
            kategorije.setKategorija(rs.getString("Kategorija"));
            return kategorije;
        }catch (SQLException e){
            throw new ArtikliException(e.getMessage(),e);
        }

    }

    @Override
    public Map<String, Object> object2row(Kategorije object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("Kategorija", object.getKategorija());
        return row;
    }



}
