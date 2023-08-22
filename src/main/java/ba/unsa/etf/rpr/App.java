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
           // Kategorije voce = new Kategorije();
            //DaoFactory.kategorijeDao().add(voce);
           List<Artikli> listaKategorija = new ArrayList<Artikli>(DaoFactory.artikliDao().getAll());
           System.out.println(listaKategorija);

        } catch (ArtikliException e) {
            System.out.println("Nesto nije u redu sa getAll metodom!");
            throw new RuntimeException(e);

        }
       // System.out.println( "Hello World!" );

    }
}
