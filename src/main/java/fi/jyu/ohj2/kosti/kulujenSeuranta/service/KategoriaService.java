package fi.jyu.ohj2.kosti.kulujenSeuranta.service;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kategoria;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tapahtuma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KategoriaService {

    private static final ObservableList<Kategoria> kategoriat = FXCollections.observableArrayList();

    public static ObservableList<Kategoria> getKategoriat() {
        return kategoriat;
    }

    public static void lisaaKategoria(Kategoria kategoria) {
        kategoriat.add(kategoria);
    }
    public static void poistaKategoria(Kategoria poistettava, ObservableList<Tapahtuma> tapahtumat) {
        for (Tapahtuma t : tapahtumat) {
            if (t.getKategoria() != null && t.getKategoria().equals(poistettava)) {
                t.setKategoria(null);
            }
        }
        kategoriat.remove(poistettava);
    }
}