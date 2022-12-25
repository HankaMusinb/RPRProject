package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Hanka Musinbegovic
 */
public class DaoFactory {
    private static final KategorijeDao kategorijeDao = new KategorijeDaoSQLImpl();
    private static final ArtikliDao artikliDao = new ArtikliDaoSQLImpl();
    private static final ProdajeDao prodajeDao = new ProdajeDaoSQLImpl();

    private DaoFactory(){
    }
    public static KategorijeDao kategorijeDao(){
        return kategorijeDao;
    }
    public static ArtikliDao artikliDao(){
        return artikliDao;
    }
    public static ProdajeDao prodajeDao(){
        return prodajeDao;
    }
}
