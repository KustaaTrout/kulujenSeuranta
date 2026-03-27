package fi.jyu.ohj2.kosti.kulujenSeuranta.model;

import java.util.ArrayList;
import java.util.List;

public class Kokoelma {
    private List<Kategoria> kategoriat = new ArrayList<>();
    private List<Tapahtuma> tapahtumat = new ArrayList<>();

    public List<Kategoria> getKategoriat() {
        return kategoriat;
    }
    public List<Tapahtuma> getTapahtumat() {
        return tapahtumat;
    }
    public void setKategoriat(List<Kategoria> kategoriat) {
        this.kategoriat = kategoriat;
    }
    public void setTapahtumat(List<Tapahtuma> tapahtumat) {
        this.tapahtumat = tapahtumat;
    }
}

