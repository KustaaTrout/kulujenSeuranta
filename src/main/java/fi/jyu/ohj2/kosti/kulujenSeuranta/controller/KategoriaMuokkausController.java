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
    private Button poistaValintaButton;
    @FXML
    private TextField lisaaUusiText;
    @FXML
    private ComboBox<Tyyppi> menoVaiTuloBox;
    @FXML
    private Button lisaaUusiButton;
    @FXML
    private Button suljeButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tuloKategoriaList.setItems(KategoriaService.getKategoria().filtered(k -> k.getTyyppi() == Tyyppi.TULO)
        );
        menoKategoriaList.setItems(KategoriaService.getKategoria().filtered(k -> k.getTyyppi() == Tyyppi.MENO)
        );
        menoVaiTuloBox.getItems().addAll(Tyyppi.values());
    }

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
        }
    }

    @FXML
    private void handleLisaaUusi(){
        String nimi = lisaaUusiText.getText();
        Tyyppi tyyppi = menoVaiTuloBox.getValue();

        if (nimi.isEmpty() || tyyppi == null) return;

        Kategoria kategoria = new Kategoria(nimi, tyyppi);
        KategoriaService.lisaaKategoria(kategoria);

        lisaaUusiText.clear();
        menoVaiTuloBox.setValue(null);
    }
    @FXML
    private void handleLisaaUusiText() {
        handleLisaaUusi();
    }

    @FXML
    private void handleSulje(){
        suljeIkkuna();
    }
    private void suljeIkkuna() {
        Stage stage = (Stage) suljeButton.getScene().getWindow();
        stage.close();
    }


}
