package fi.jyu.ohj2.kosti.kulujenSeuranta.controller;

import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kategoria;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tyyppi;
import fi.jyu.ohj2.kosti.kulujenSeuranta.service.KategoriaService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class KategoriaMuokkausController implements Initializable {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private ListView<Kategoria> menoKategoriaList;
    @FXML
    private ListView<Kategoria> tuloKategoriaList;
    @FXML
    private TextField lisaaUusiText;
    @FXML
    private ComboBox<Tyyppi> menoVaiTuloBox;
    @FXML
    private Button suljeButton;
    @FXML
    private Label kategoriaVirheLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tuloKategoriaList.setItems(KategoriaService.getKategoriat().filtered(k -> k.getTyyppi() == Tyyppi.TULO)
        );
        menoKategoriaList.setItems(KategoriaService.getKategoriat().filtered(k -> k.getTyyppi() == Tyyppi.MENO)
        );
        menoVaiTuloBox.getItems().addAll(Tyyppi.values());
        menoKategoriaList.getSelectionModel().selectedItemProperty().addListener((_, _, valittu) -> {
            if (valittu != null) {
                lisaaUusiText.setText(valittu.getNimi());
            }
        });
        tuloKategoriaList.getSelectionModel().selectedItemProperty().addListener((_, _, valittu) -> {
            if (valittu != null) {
                lisaaUusiText.setText(valittu.getNimi());
            }
        });
    }

    //  Kategorian poisto, tarkistetaan onko kyseessä tulo vai meno ja varmistetaan käyttäjältä poisto.
    @FXML
    private void handlePoista() {
        Kategoria valittuTulo = tuloKategoriaList.getSelectionModel().getSelectedItem();
        Kategoria valittuMeno = menoKategoriaList.getSelectionModel().getSelectedItem();

        Kategoria valittu = null;
        if (valittuTulo != null) {
            valittu = valittuTulo;
        } else if (valittuMeno != null) {
            valittu = valittuMeno;
        }

        if (valittu == null) return;

        Alert varmistus = new Alert(Alert.AlertType.CONFIRMATION);
        varmistus.setTitle("Haluatko varmasti poistaa kategorian?");
        varmistus.setHeaderText("Poistetaanko?");
        varmistus.setContentText(valittu.getNimi());

        Optional<ButtonType> tulos = varmistus.showAndWait();
        if (tulos.isPresent() && tulos.get() == ButtonType.OK) {
            KategoriaService.poistaKategoria(valittu, mainController.getTapahtumat());
            mainController.paivitaTaulukko();
            mainController.paivitaKategoriat();
            mainController.tallennaData();
        }

    }

    //  Uuden kategorian lisäys
    @FXML
    private void handleLisaaUusi() {
        kategoriaVirheLabel.setText("");
        String nimi = lisaaUusiText.getText().trim();
        Tyyppi tyyppi = menoVaiTuloBox.getValue();

        if (nimi.isEmpty() || tyyppi == null) return;

        for (Kategoria k : KategoriaService.getKategoriat()) {
            if (k.getNimi().equals(nimi) && k.getTyyppi() == tyyppi) {
                kategoriaVirheLabel.setText("Kategoria on jo olemassa!");
                return;
            }
        }
        Kategoria kategoria = new Kategoria(nimi, tyyppi);
        KategoriaService.lisaaKategoria(kategoria);
        mainController.paivitaKategoriat();
        mainController.tallennaData();
        lisaaUusiText.clear();
        menoVaiTuloBox.setValue(null);
    }

    @FXML
    private void handleMuokkaa() {
        Kategoria valittuTulo = tuloKategoriaList.getSelectionModel().getSelectedItem();
        Kategoria valittuMeno = menoKategoriaList.getSelectionModel().getSelectedItem();

        Kategoria valittu = null;
        if (valittuTulo != null) {
            valittu = valittuTulo;
        } else if (valittuMeno != null) {
            valittu = valittuMeno;
        }
        if (valittu == null) return;

        String uusiNimi = lisaaUusiText.getText().trim();
        if (uusiNimi.isEmpty()) {
            return;
        }
        valittu.setNimi(uusiNimi);
        mainController.paivitaKategoriat();
        mainController.paivitaTaulukko();
        tuloKategoriaList.refresh();
        menoKategoriaList.refresh();
        lisaaUusiText.clear();
        mainController.tallennaData();

    }

    @FXML
    private void handleLisaaUusiText() {
        handleLisaaUusi();
    }

    @FXML
    private void handleSulje() {
        suljeIkkuna();
    }

    private void suljeIkkuna() {
        Stage stage = (Stage) suljeButton.getScene().getWindow();
        stage.close();
    }

}
