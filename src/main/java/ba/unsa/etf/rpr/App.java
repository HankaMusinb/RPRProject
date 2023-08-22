package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artikli;
import ba.unsa.etf.rpr.exceptions.ArtikliException;
import org.apache.commons.cli.Option;

import java.util.ArrayList;
import java.util.List;
/**
 * Hello world!
 *
 */
public class App 
{

    /**
     * Defining final variables to describe all code having options
     */

    private static final org.apache.commons.cli.Option addQuote = new Option("artikal","dodaj-artikal",false, "Adding new article");


    private static final Option addCategory = new Option("c","add-category",false, "Adding new category");


    private static final Option getArtikal = new Option("getA", "get-articles",false, "Printing all articles");

    private static final Option getCategories = new Option("getC", "get-categories",false, "Printing all categories");
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
