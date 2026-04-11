import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kategoria;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tapahtuma;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tyyppi;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TapahtumaTest {

    ///  Testataan gettereiden toiminta
    @Test
    void getNimiToimii() {
        Tapahtuma tapahtuma = new Tapahtuma();
        tapahtuma.setAihe("kissa");
        assertEquals("kissa", tapahtuma.getAihe());
    }

    @Test
    void getSummaToimii() {
        Tapahtuma tapahtuma = new Tapahtuma();
        tapahtuma.setSumma(100);
        assertEquals(100, tapahtuma.getSumma());
    }

    @Test
    void getPvmToimii() {
        Tapahtuma tapahtuma = new Tapahtuma();
        tapahtuma.setPvm(LocalDate.of(2026, 4, 9));
        assertEquals(LocalDate.of(2026, 4, 9), tapahtuma.getPvm());
    }


    /// Testataan Tapahtuma-olion konstruktorin toiminta. Testissä tehdään myös yksi uusi kategoria,
    /// jotta testi voidaan tehdä.
    @Test
    void konstruktoriToimii() {
        LocalDate pvm = LocalDate.of(2026, 4, 9);
        Kategoria kategoria = new Kategoria("testi", Tyyppi.MENO);
        Tapahtuma tapahtuma = new Tapahtuma(pvm, "testi", kategoria, 100, true, Tyyppi.MENO);

        assertEquals(pvm, tapahtuma.getPvm());
        assertEquals("testi", tapahtuma.getAihe());
        assertEquals(kategoria, tapahtuma.getKategoria());
        assertEquals(100, tapahtuma.getSumma());
        assertTrue(tapahtuma.isPakollinen());
        assertEquals(Tyyppi.MENO, tapahtuma.getTyyppi());

    }


}
