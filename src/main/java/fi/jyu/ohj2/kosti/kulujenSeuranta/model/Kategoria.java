package fi.jyu.ohj2.kosti.kulujenSeuranta.model;

public class Kategoria {
    private String nimi;
    private Tyyppi tyyppi;

    public Kategoria(String nimi, Tyyppi tyyppi) {
        this.nimi = nimi;
        this.tyyppi = tyyppi;
    }

    public Kategoria() {
    }

    public String getNimi() {return nimi;}
    public Tyyppi getTyyppi() {return tyyppi;}

    public void setNimi(String nimi) {this.nimi = nimi;}
    public void setTyyppi(Tyyppi tyyppi) {this.tyyppi = tyyppi;}

    @Override
    public String toString() {
        return nimi;
    }
}
