package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Prodaje;

import java.sql.SQLException;
import java.util.List;

public interface ProdajeDao extends Dao<Prodaje> {


    List<Prodaje> vratiProfit(int id) throws SQLException;

}
