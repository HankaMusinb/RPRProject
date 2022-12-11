package ba.unsa.etf.rpr;
import java.util.Date;
import java.util.Objects;

public class Artikli {
    private int id;
    private String naziv;
    private Kategorije kategorija;
    private Date istekRoka;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Kategorije getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorije kategorija) {
        this.kategorija = kategorija;
    }

    public Date getIstekRoka() {
        return istekRoka;
    }

    public void setIstekRoka(Date istekRoka) {
        this.istekRoka = istekRoka;
    }

    @Override
    public String toString() {
        return "Artikli{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", kategorija=" + kategorija +
                ", istekRoka=" + istekRoka +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artikli artikli = (Artikli) o;
        return id == artikli.id && Objects.equals(naziv, artikli.naziv) && Objects.equals(kategorija, artikli.kategorija) && Objects.equals(istekRoka, artikli.istekRoka);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, kategorija, istekRoka);
    }
}
