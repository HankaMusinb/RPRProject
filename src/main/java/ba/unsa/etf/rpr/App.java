package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Kategorije;
import ba.unsa.etf.rpr.exceptions.ArtikliException;
import org.apache.commons.cli.Option;

import java.util.ArrayList;
import java.util.List;
/**
 * Hello world!
 *
 * @author Hanka Musinbegovic
 */
public class App 
{

    /**
     * Defining final variables to describe all code having options
     */

    private static final org.apache.commons.cli.Option addArtikal = new Option("artikal","dodaj-artikal",false, "Adding new article");


    private static final Option addCategory = new Option("c","add-category",false, "Adding new category");


    private static final Option getArtikle = new Option("getA", "get-articles",false, "Printing all articles");


    private static final Option getCategories = new Option("getC", "get-categories",false, "Printing all categories");

    public static void main( String[] args ) throws ArtikliException {
//        KategorijeDao kategorijeDao = DaoFactory.kategorijeDao();
//         // ArtikliDao artikliDao = DaoFactory.artikliDao();
//        System.out.println(kategorijeDao.getById(2));
        try {
            List<Kategorije> listaKategorija = new ArrayList<Kategorije>(DaoFactory.kategorijeDao().getAll());
            System.out.println(listaKategorija);

        } catch (ArtikliException e) {
            System.out.println("Nesto nije u redu sa getAll metodom!");
            throw new RuntimeException(e);

        }
       // System.out.println( "Hello World!" );

    }
}
