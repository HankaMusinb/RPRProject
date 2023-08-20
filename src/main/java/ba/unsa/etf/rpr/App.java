package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artikli;
import ba.unsa.etf.rpr.exceptions.ArtikliException;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ArtikliException {
//        KategorijeDao kategorijeDao = DaoFactory.kategorijeDao();
//         // ArtikliDao artikliDao = DaoFactory.artikliDao();
//        System.out.println(kategorijeDao.getById(2));
        try {
            List<Artikli> listaArtikala = new ArrayList<Artikli>(DaoFactory.artikliDao().getAll());
            System.out.println(listaArtikala);
        } catch (ArtikliException e) {
            System.out.println("Nesto nije u redu sa getId() metodom!");
            throw new RuntimeException(e);

        }
        System.out.println( "Hello World!" );

    }
}
