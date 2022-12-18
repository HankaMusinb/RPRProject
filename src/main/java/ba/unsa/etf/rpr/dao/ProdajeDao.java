package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artikli;

public interface ProdajeDao extends Dao<Artikli> {

    int profit();
}
