package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artikli;
import ba.unsa.etf.rpr.domain.Kategorije;
import ba.unsa.etf.rpr.domain.Prodaje;
import ba.unsa.etf.rpr.exceptions.ArtikliException;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 * @author Hanka Musinbegovic
 */
public class App {

    /**
     * Defining final variables to describe all code having options
     */

    private static final org.apache.commons.cli.Option addArtikal = new Option("artikal", "dodaj-artikal", false, "Adding new article");

    private static final Option addCategory = new Option("c", "add-category", false, "Adding new category");

    private static final Option getArtikle = new Option("getA", "get-articles", false, "Printing all articles");

    private static final Option getCategories = new Option("getC", "get-categories", false, "Printing all categories");

    private static final Option categoryDefinition = new Option(null, "category", false, "Defining category for next added article");

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
        options.addOption(categoryDefinition);
        return options;
    }

    public static Kategorije searchThroughCategories(List<Kategorije> listOfCategories, String categoryName) {
        Kategorije category = null;
        category = listOfCategories.stream().filter(cat -> cat.getKategorija().toLowerCase().equals(categoryName.toLowerCase())).findAny().get();
        return category;
    }


    public static void main(String[] args) throws ArtikliException, ParseException {
        // testKategorije();
        //testArtikli();
//
//        Options options = addOptions();
//
//        CommandLineParser commandLineParser = new DefaultParser(); //<- This may be the problem
//
//        CommandLine cl = commandLineParser.parse(options, args);
//
//        if ((cl.hasOption(addArtikal.getOpt()) || cl.hasOption(addArtikal.getLongOpt())) && cl.hasOption((categoryDefinition.getLongOpt()))) {
//            ArtikliManager artikliManager = new ArtikliManager();
//            KategorijeManager kategorijeManager = new KategorijeManager();
//            Kategorije kategorije = null;
//            try {
//                kategorije = searchThroughCategories(kategorijeManager.getAll(), cl.getArgList().get(1));
//
//            } catch (Exception e) {
//                System.out.println("There is no category in the list! Try again.");
//                System.exit(1);
//            }
//            Artikli artikal = new Artikli();
//            artikal.setKategorija(kategorije);
//            artikal.setNaziv(cl.getArgList().get(0));
//            artikal.setIstekRoka(Date.valueOf(LocalDate.now()));
//            artikal.setCijena(5);
//            Prodaje prodaja = new Prodaje();
//            prodaja.setZarada(0);
//            artikal.setProdaje(prodaja);
//            artikliManager.add(artikal);
//            System.out.println("You successfully added artikal to database!");
//        } else if (cl.hasOption(getArtikle.getOpt()) || cl.hasOption(getArtikle.getLongOpt())) {
//            ArtikliManager artikliManager = new ArtikliManager();
//            try {
//                artikliManager.getAll().forEach(q -> System.out.println(q.getNaziv()));
//            } catch (ArtikliException e) {
//                throw new RuntimeException(e);
//            }
//            //                break;
//        } else if (cl.hasOption(addCategory.getOpt()) || cl.hasOption(addCategory.getLongOpt())) {
//            try {
//                KategorijeManager kategorijeManager = new KategorijeManager();
//                Kategorije cat = new Kategorije();
//                cat.setKategorija(cl.getArgList().get(0));
//                kategorijeManager.add(cat);
//                System.out.println("Category has been added successfully");
//                //                    break;
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                System.out.println("There is already category with same name in database! Try again");
//                System.exit(1);
//                //                   break;
//            }
//
//        } else if (cl.hasOption(getCategories.getOpt()) || cl.hasOption(getCategories.getLongOpt())) {
//            KategorijeManager kategorijeManager = new KategorijeManager();
//            try {
//                kategorijeManager.getAll().forEach(c -> System.out.println(c.getKategorija()));
//            } catch (ArtikliException e) {
//                throw new RuntimeException(e);
//            }
//            //                break;
//        } else {
//
//            printFormattedOptions(options);
//            System.exit(-1);
//            //                break;
//        }
//        }

         //    List<Kategorije> listaKategorija = new ArrayList<>(DaoFactory.kategorijeDao().getAll());
         // DaoFactory.kategorijeDao().delete(listaKategorija.stream().filter(k -> k.getKategorija().equals("povrce")).findAny().get().getId());
         //      DaoFactory.kategorijeDao().delete(listaKategorija.stream().filter(k -> k.getKategorija().equals("sirevi")).findAny().get().getId());
         // System.out.println(listaKategorija);
         // List<Artikli> listaArtikala = new ArrayList<>(DaoFactory.artikliDao().getAll());
         //System.out.println(listaArtikala);

    }

    static void testKategorije() throws ArtikliException {
        List<Kategorije> listaKategorija = new ArrayList<>(DaoFactory.kategorijeDao().getAll());
        System.out.println(listaKategorija);
        if (listaKategorija.stream().noneMatch(k -> k.getKategorija().equals("Kozmetika"))) {
            System.out.println("Nema Kozmetike u bazi");
            Kategorije voce = new Kategorije();
            voce.setKategorija("Kozmetika");
            DaoFactory.kategorijeDao().add(voce);
            listaKategorija = new ArrayList<>(DaoFactory.kategorijeDao().getAll());
            System.out.println(listaKategorija);
            if (listaKategorija.stream().noneMatch(k -> k.getKategorija().equals("Kozmetika"))) {
                System.out.println("Nije dodato Kozmetika");
            } else {
                System.out.println("Uspjesno dodato Kozmetika");
                // DaoFactory.kategorijeDao().delete(listaKategorija.stream().filter(k -> k.getKategorija().equals("Voce")).findAny().get().getId());
            }
        } else {
            System.out.println("Ima Kozmetika u bazi, brisemo ga");
            DaoFactory.kategorijeDao().delete(listaKategorija.stream().filter(k -> k.getKategorija().equals("MSlatki program")).findAny().get().getId());
        }

    }

    static void testArtikli() throws ArtikliException {
        List<Artikli> listaArtikala = new ArrayList<>(DaoFactory.artikliDao().getAll());
        System.out.println(listaArtikala);
        if (listaArtikala.stream().noneMatch(a -> a.getNaziv().equals("Lubenica"))) {
            ArrayList<Kategorije> listaKategorija = new ArrayList<>(DaoFactory.kategorijeDao().getAll());
            Kategorije voce;
            if (listaKategorija.stream().noneMatch(k -> k.getKategorija().equals("Voce"))) {
                voce = new Kategorije("Voce");
                DaoFactory.kategorijeDao().add(voce);
            } else {
                voce = listaKategorija.stream().filter(k -> k.getKategorija().equals("Voce")).findAny().get();
            }

            Prodaje prodaja_kurkume = new Prodaje();
            prodaja_kurkume.setZarada(6);
            DaoFactory.prodajeDao().add(prodaja_kurkume);

            System.out.println("Nema lubenice u bazi");
            Artikli jabuka = new Artikli();
            jabuka.setNaziv("Lubenica");
            jabuka.setCijena(2);
            jabuka.setKategorija(voce);
            jabuka.setProdaje(prodaja_kurkume);
            jabuka.setIstekRoka(Date.valueOf(LocalDate.now()));

            DaoFactory.artikliDao().add(jabuka);
            listaArtikala = new ArrayList<>(DaoFactory.artikliDao().getAll());
            System.out.println(listaArtikala);
            if (listaArtikala.stream().noneMatch(a -> a.getNaziv().equals("Lubenica"))) {
                System.out.println("Nije dodata lubenica");
            } else {
                System.out.println("Uspjesno dodata lubenica, brisemo je");
                //  DaoFactory.artikliDao().delete(listaArtikala.stream().filter(a -> a.getNaziv().equals("Jabuka")).findAny().get().getId());
            }
        } else {
            System.out.println("Ima jabuka u bazi, brisemo je");
            //    DaoFactory.artikliDao().delete(listaArtikala.stream().filter(a -> a.getNaziv().equals("Jabuka")).findAny().get().getId());
        }

    }

}