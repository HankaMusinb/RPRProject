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
         Prodaje zaradamesa = new Prodaje();

            zaradamesa.setZarada(50);
           DaoFactory.prodajeDao().add(zaradamesa);
       //    List<Prodaje> listaKategorija = new ArrayList<Prodaje>(DaoFactory.prodajeDao().getAll());
         //  System.out.println(listaKategorija);
           // Radi ispis kategorija
           // List<Kategorije> listaKategorija = new ArrayList<Kategorije>(DaoFactory.kategorijeDao().getAll());
          //   System.out.println(listaKategorija);
         //   Kategorije voce = DaoFactory.kategorijeDao().getById(2);
         //   Artikli cokolada = new Artikli();

          //  cokolada.setKategorija(voce);
          //  DaoFactory.artikliDao().add(cokolada);

        } catch (ArtikliException e) {
            System.out.println("Nesto nije u redu sa add metodom!");
            throw new RuntimeException(e);

        }
       // System.out.println( "Hello World!" );

        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);

        if((cl.hasOption(addArtikal.getOpt()) || cl.hasOption(addArtikal.getLongOpt())) && cl.getArgList().get(1));
            ArtikliManager artikliManager = new ArtikliManager();
            KategorijeManager kategorijeManager = new KategorijeManager();
            Kategorije kategorije = null;
            try{
                kategorije = searchThroughCategories(kategorijeManager.getAll(), cl.getArgList().get(1));

            }catch (Exception e){
                System.out.println("There is no category in the list! Try again.");
                System.exit(1);
            }
        Artikal artikal = new Artikal();
        artikal.setKategorija(kategorije);
        artikal.setNaziv(cl.getArgList().get(0));
        artikal.setIstekRoka(Date.valueOf(LocalDate.now()));
        artikal.setCijena(5);
        Prodaje prodaja = new Prodaje();
        prodaja.setZarada(0);
        artikal.setProdaje(prodaja);
        artikliManager.add(artikal);
        System.out.println("You successfully added artikal to database!");
    } else if(cl.hasOption(getArtikle.getOpt()) || cl.hasOption(getArtikle.getLongOpt())){
    ArtikliManager artikliManager = new ArtikliManager();
   artikliManager.getAll().forEach(q -> System.out.println(q.getArtikle()));
//                break;
} else if(cl.hasOption(addCategory.getOpt()) || cl.hasOption(addCategory.getLongOpt())){
    try {
        KategorijeManager kategorijeManager = new KategorijeManager();
        Kategorije cat = new Kategorije();
        cat.setKategorija(cl.getArgList().get(0));
        kategorijeManager.add(cat);
        System.out.println("Category has been added successfully");
//                    break;
    }catch(Exception e) {
        System.out.println("There is already category with same name in database! Try again");
        System.exit(1);
//                   break;
    }

} else if(cl.hasOption(getCategories.getOpt()) || cl.hasOption(getCategories.getLongOpt())){
    KategorijeManager kategorijeManager = new KategorijeManager();
    kategorijeManager.getAll().forEach(c -> System.out.println(c.getKategorija()));
//                break;
} else {
    printFormattedOptions(options);
    System.exit(-1);
//                break;
}
//        }

    }
}
