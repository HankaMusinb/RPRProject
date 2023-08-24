package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artikli;
import ba.unsa.etf.rpr.domain.Kategorije;
import ba.unsa.etf.rpr.exceptions.ArtikliException;
import org.apache.commons.cli.Option;

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





    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar RPRProject-0.1.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addArtikal);
        options.addOption(addCategory);
        options.addOption(getArtikle);
        options.addOption(getCategories);
        return options;
    }

    public static Kategorije searchThroughCategories(List<Kategorije> listOfCategories, String categoryName) {

        Kategorije category = null;
        category = listOfCategories.stream().filter(cat -> cat.getName().toLowerCase().equals(categoryName.toLowerCase())).findAny().get();
        return category;

    }


    public static void main( String[] args ) throws ArtikliException {
//        KategorijeDao kategorijeDao = DaoFactory.kategorijeDao();
//         // ArtikliDao artikliDao = DaoFactory.artikliDao();
//        System.out.println(kategorijeDao.getById(2));
        try {
         //   Prodaje zaradamesa = new Prodaje();
           // zaradamesa.setId(1);
            //zaradamesa.setZarada(50);
           // DaoFactory.prodajeDao().add(zaradamesa);
       //    List<Prodaje> listaKategorija = new ArrayList<Prodaje>(DaoFactory.prodajeDao().getAll());
         //  System.out.println(listaKategorija);
            /*Radi ispis kategorija
            List<Kategorije> listaKategorija = new ArrayList<Kategorije>(DaoFactory.kategorijeDao().getAll());
             System.out.println(listaKategorija);*/
            Kategorije voce = DaoFactory.kategorijeDao().getById(2);
            Artikli cokolada = new Artikli();

            cokolada.setKategorija(voce);
            DaoFactory.artikliDao().add(cokolada);

        } catch (ArtikliException e) {
            System.out.println("Nesto nije u redu sa add metodom!");
            throw new RuntimeException(e);

        }
       // System.out.println( "Hello World!" );

    }
}
