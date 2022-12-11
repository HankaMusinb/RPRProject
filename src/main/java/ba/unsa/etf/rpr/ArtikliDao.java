package ba.unsa.etf.rpr;
import java.util.Date;
import java.util.List;
public interface ArtikliDao extends Dao<Artikli> {
    /**
     * vraca sve artikle sa zadanim istekom roka
     *
     * @param rok za trazenje artikala s isteknutim rokom
     * @return listu trazenih artikala
     */
    List<Artikli> traziPoIstekuRoka(Date rok);

    /**
     * vraca sve artikle sa zadanom cijenom
     * @param cijena za trazenje artikala
     * @return listu zadanih artikala
     */
    List<Artikli> traziPoCijeni(int cijena);
}