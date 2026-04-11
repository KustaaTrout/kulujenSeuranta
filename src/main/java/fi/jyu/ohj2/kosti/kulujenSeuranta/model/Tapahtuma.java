package fi.jyu.ohj2.kosti.kulujenSeuranta.model;

import java.time.LocalDate;

public class Tapahtuma {
    private LocalDate pvm;
    private String aihe;
    private Kategoria kategoria;
    private double summa;
    private boolean pakollinen;
    private Tyyppi tyyppi;

    public Tapahtuma(LocalDate pvm, String aihe, Kategoria kategoria, double summa, boolean pakollinen, Tyyppi tyyppi) {
        this.pvm = pvm;
        this.aihe = aihe;
        this.kategoria = kategoria;
        this.summa = summa;
        this.pakollinen = pakollinen;
        this.tyyppi = tyyppi;
    }

    public Tapahtuma() {
    }

    public LocalDate getPvm() {
        return pvm;
    }

    public void setPvm(LocalDate pvm) {
        this.pvm = pvm;
    }

    public String getAihe() {
        return aihe;
    }

    public void setAihe(String aihe) {
        this.aihe = aihe;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    public Tyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(Tyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public boolean isPakollinen() {
        return pakollinen;
    }

    public void setPakollinen(boolean pakollinen) {
        this.pakollinen = pakollinen;
    }

}
