package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Kategorije;

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
    public Kategorije row2object(ResultSet rs) throws SQLException {

        try {
            Kategorije kategorije = new Kategorije();
            kategorije.setId(rs.getInt("idKategorije"));
            kategorije.setKategorija(rs.getString("Kategorija"));
            return kategorije;
        }catch (SQLException e){
            //
        }
        return null;
    }

    @Override
    public Map<String, Object> object2row(Kategorije object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("kategorija", object.getKategorija());
        return row;
    }



}
