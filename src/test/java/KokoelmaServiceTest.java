import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kategoria;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kokoelma;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tapahtuma;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tyyppi;
import fi.jyu.ohj2.kosti.kulujenSeuranta.service.KokoelmaService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KokoelmaServiceTest {

    @Test
    void LatausJaTallennusToimii(){
        Kokoelma kokoelma = new Kokoelma();
        Kategoria kategoria = new Kategoria("testiKategoria", Tyyppi.MENO);
        Tapahtuma tapahtuma = new Tapahtuma(LocalDate.of(2026, 4, 9), "testiTapahtuma", kategoria, 100, true, Tyyppi.MENO);
        List<Kategoria> kategoriat = new ArrayList<>();
        List<Tapahtuma> tapahtumat = new ArrayList<>();
        kategoriat.add(kategoria);
        tapahtumat.add(tapahtuma);
        kokoelma.setKategoriat(kategoriat);
        kokoelma.setTapahtumat(tapahtumat);

        KokoelmaService.tallenna(kokoelma);
        Kokoelma ladattu = KokoelmaService.lataa();

        assertNotNull(ladattu);

        assertEquals(1, ladattu.getKategoriat().size());
        assertEquals(1, ladattu.getTapahtumat().size());

        assertEquals("testiKategoria", ladattu.getKategoriat().getFirst().getNimi());
        assertEquals("testiTapahtuma", ladattu.getTapahtumat().getFirst().getAihe());

        assertEquals(Tyyppi.MENO, ladattu.getTapahtumat().getFirst().getTyyppi());
        assertEquals(Tyyppi.MENO, ladattu.getKategoriat().getFirst().getTyyppi());

        assertEquals(100, ladattu.getTapahtumat().getFirst().getSumma());
        assertEquals(LocalDate.of(2026, 4, 9), ladattu.getTapahtumat().getFirst().getPvm());
        assertTrue(ladattu.getTapahtumat().getFirst().isPakollinen());
    }


    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void latausPalauttaaOletusKategoriat(){
        new File("data.json").delete();
        Kokoelma ladattu = KokoelmaService.lataa();

        assertNotNull(ladattu);
        assertEquals("Palkka", ladattu.getKategoriat().get(0).getNimi());
        assertEquals("Asumistuki", ladattu.getKategoriat().get(1).getNimi());
        assertEquals(7, ladattu.getKategoriat().size());
    }
}
