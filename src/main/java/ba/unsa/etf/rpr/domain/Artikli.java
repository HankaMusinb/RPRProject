package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;


public class Artikli implements Idable {

    // Atributes
    private int id;
    private String naziv;
    private int cijena;
    private Kategorije kategorija;
    private Date istekRoka;

    private Prodaje prodaja;

    //Setters and getters
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

    public int getCijena() {
        return cijena;
    }

    public void setCijena(int cijena) {
        this.cijena = cijena;
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
    //Hash, equals, toString methods

    public Prodaje getProdaja() {
        return prodaja;
    }

    public void setProdaja(Prodaje prodaja) {
        this.prodaja = prodaja;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, cijena, kategorija, istekRoka);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artikli artikli = (Artikli) o;
        return id == artikli.id;
    }


    @Override
    public String toString() {
        return "Artikli{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", cijena=" + cijena +
                ", kategorija=" + kategorija +
                ", istekRoka=" + istekRoka +
                '}';
    }





}
