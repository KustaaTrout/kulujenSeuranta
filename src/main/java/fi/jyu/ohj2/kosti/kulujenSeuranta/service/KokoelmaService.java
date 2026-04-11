package fi.jyu.ohj2.kosti.kulujenSeuranta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kategoria;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kokoelma;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tyyppi;

import java.io.File;
import java.io.IOException;

public class KokoelmaService {

    private static final String TIEDOSTO = "data.json";

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule()) // LocalDate tuki
            .enable(SerializationFeature.INDENT_OUTPUT);

    // tallennetaan data tiedostoon.
    public static void tallenna(Kokoelma kokoelma) {
        try {
            mapper.writeValue(new File(TIEDOSTO), kokoelma);
        } catch (IOException e) {
            throw new RuntimeException("Tallennus ei onnistunut!", e);
        }
    }

    // ladataan data tiedostosta, jos tiedostoa ei ole, luodaan default-kategoriat.
    public static Kokoelma lataa() {
        try {
            File file = new File(TIEDOSTO);
            if (!file.exists() || file.length() == 0) {
                Kokoelma kokoelma = new Kokoelma();
                kokoelma.setKategoriat(java.util.List.of(
                        new Kategoria("Palkka", Tyyppi.TULO),
                        new Kategoria("Asumistuki", Tyyppi.TULO),
                        new Kategoria("Opintotuki", Tyyppi.TULO),
                        new Kategoria("Opintolaina", Tyyppi.TULO),
                        new Kategoria("Ruoka", Tyyppi.MENO),
                        new Kategoria("Vuokra", Tyyppi.MENO),
                        new Kategoria("Asuminen", Tyyppi.MENO)
                ));
                return kokoelma;
            }
            return mapper.readValue(file, Kokoelma.class);
        } catch (IOException e) {
            throw new RuntimeException("Lataus ei onnistunut!", e);
        }
    }
}


