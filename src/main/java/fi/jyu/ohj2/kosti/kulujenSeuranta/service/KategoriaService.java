package fi.jyu.ohj2.kosti.kulujenSeuranta.service;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kategoria;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tapahtuma;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tyyppi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KategoriaService {

    private static final ObservableList<Kategoria> kategoriat = FXCollections.observableArrayList();

    static {
        kategoriat.addAll(
                new Kategoria("Palkka", Tyyppi.TULO),
                new Kategoria("Asumistuki", Tyyppi.TULO),
                new Kategoria("Opintotuki", Tyyppi.TULO),
                new Kategoria("Opintolaina", Tyyppi.TULO),
                new Kategoria("Ruoka", Tyyppi.MENO),
                new Kategoria("Vuokra", Tyyppi.MENO),
                new Kategoria("Asuminen", Tyyppi.MENO)
        );
    }

    public static ObservableList<Kategoria> getKategoria() {
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