package ba.unsa.etf.rpr.domain;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.KategorijeDao;
import ba.unsa.etf.rpr.exceptions.ArtikliException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ArtikliException {
        KategorijeDao kategorijeDao = DaoFactory.kategorijeDao();
         // ArtikliDao artikliDao = DaoFactory.artikliDao();
        System.out.println(kategorijeDao.getById(2));
    }
}
