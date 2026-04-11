import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kategoria;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tapahtuma;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tyyppi;
import fi.jyu.ohj2.kosti.kulujenSeuranta.service.KategoriaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class KategoriaServiceTest {


    /// Tyhjennetään lista ennen testausta.
    @BeforeEach
    void tyhjenna() {
        KategoriaService.getKategoriat().clear();
    }

    ///  Testataan kategorian lisääminen.
    @Test
    void kategorianLisaysToimii() {
        KategoriaService.lisaaKategoria(new Kategoria("testi", Tyyppi.MENO));
        KategoriaService.lisaaKategoria(new Kategoria("testi2", Tyyppi.TULO));

        assertEquals(2, KategoriaService.getKategoriat().size());
        assertEquals("testi", KategoriaService.getKategoriat().get(0).getNimi());
        assertEquals("testi2", KategoriaService.getKategoriat().get(1).getNimi());
        assertEquals(Tyyppi.MENO, KategoriaService.getKategoriat().get(0).getTyyppi());
        assertEquals(Tyyppi.TULO, KategoriaService.getKategoriat().get(1).getTyyppi());
    }

    ///  Testataan kategorian poistoa.
    @Test
    void kategorianPoistoToimii() {
        Kategoria testi1 = new Kategoria("testi1", Tyyppi.MENO);
        Kategoria testi2 = new Kategoria("testi2", Tyyppi.TULO);

        KategoriaService.lisaaKategoria(testi1);
        KategoriaService.lisaaKategoria(testi2);

        ObservableList<Tapahtuma> tapahtuma = FXCollections.observableArrayList();

        KategoriaService.poistaKategoria(testi1, tapahtuma);

        assertEquals(1, KategoriaService.getKategoriat().size());
        assertEquals("testi2", KategoriaService.getKategoriat().getFirst().getNimi());

        KategoriaService.poistaKategoria(testi2, tapahtuma);

        assertEquals(0, KategoriaService.getKategoriat().size());
        assertFalse(KategoriaService.getKategoriat().contains(testi1));
        assertFalse(KategoriaService.getKategoriat().contains(testi2));
    }

    ///  Testataan saman nimisten kategorioiden poistoa, joissa eri tyyppi.
    @Test
    void kategorianPoistoTyypinMukaanToimii() {
        Kategoria testi1 = new Kategoria("testi", Tyyppi.MENO);
        Kategoria testi2 = new Kategoria("testi", Tyyppi.TULO);

        KategoriaService.lisaaKategoria(testi1);
        KategoriaService.lisaaKategoria(testi2);

        ObservableList<Tapahtuma> tapahtuma = FXCollections.observableArrayList();

        KategoriaService.poistaKategoria(testi1, tapahtuma);

        assertEquals(1, KategoriaService.getKategoriat().size());
        assertEquals("testi", KategoriaService.getKategoriat().getFirst().getNimi());
        assertEquals(Tyyppi.TULO, KategoriaService.getKategoriat().getFirst().getTyyppi());
    }
}
