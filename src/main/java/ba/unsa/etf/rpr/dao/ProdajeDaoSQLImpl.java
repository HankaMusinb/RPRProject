package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Prodaje;
import ba.unsa.etf.rpr.exceptions.ArtikliException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProdajeDaoSQLImpl extends AbstractDao<Prodaje> implements ProdajeDao {
    private Connection connection;

    public ProdajeDaoSQLImpl() {
        super("Prodaje");
    }



    /**
     * Method for mapping ResultSet into Object
     *
     * @param rs - result set from database
     * @return a Bean object for specific table
     */
    @Override
    public Prodaje row2object(ResultSet rs) throws ArtikliException {
        try {
            Prodaje prodaje = new Prodaje();
            prodaje.setId(rs.getInt("id"));
            prodaje.setZarada(rs.getInt("zarada"));
            return prodaje;
        }catch (SQLException e){
            throw new ArtikliException(e.getMessage(), e);
        }
    }

    /**
     * Method for mapping Object into Map
     *
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    @Override
    public Map<String, Object> object2row(Prodaje object) {
        Map<String,Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("zarada", object.getZarada());
        return item;
    }
    /**
     * @return sum of profit that the store has made
     */
    @Override
    public List<Prodaje> vratiProfit(int id ) throws  ArtikliException {
        return executeQuery("SELECT * FROM Prodaje WHERE idProdaje EQUALS = ?", new Object[]{id});
    }

}
