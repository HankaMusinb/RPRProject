package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * sums profit every time an item has been selected or searched for
 * @author Hanka Musinbegovic
 */
public class Prodaje implements Idable {

    //Atributes
    private int id;
    private Artikli artikal;
    private int zarada;

    //Setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artikli getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikli artikal) {
        this.artikal = artikal;
    }

    public int getZarada() {
        return zarada;
    }

    public void setZarada(int zarada) {
        this.zarada = zarada;
    }

    @Override
    public String toString() {
        return "Prodaje{" +
                "id=" + id +
                ", artikal=" + artikal +
                ", zarada=" + zarada +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodaje prodaje = (Prodaje) o;
        return id == prodaje.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artikal, zarada);
    }
}
