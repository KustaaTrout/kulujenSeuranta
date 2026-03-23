package fi.jyu.ohj2.kosti.kulujenSeuranta.model;

public class Kategoria {
    private String nimi;
    private Tyyppi tyyppi;

    public Kategoria(String nimi, Tyyppi tyyppi) {
        this.nimi = nimi;
        this.tyyppi = tyyppi;
    }

    public String getNimi() {
        return nimi;
    }
    public Tyyppi getTyyppi() {
        return tyyppi;
    }

    @Override
    public String toString() {
        return nimi;
    }
}
