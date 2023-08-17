package ba.unsa.etf.rpr.exceptions;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artikli;

import java.util.ArrayList;
import java.util.List;

public class ArtikliException extends Exception {
    public ArtikliException(String message, Exception reason){
        super(message, reason);
    }
    public ArtikliException(String message){
        super(message);
    }
    //password: *bcuN9B@#52h&qn

    /**
     * Hello world!
     *
     */
    public static class App
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
}
