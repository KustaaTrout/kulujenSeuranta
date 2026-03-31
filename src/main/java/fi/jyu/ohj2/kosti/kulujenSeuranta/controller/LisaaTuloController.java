package fi.jyu.ohj2.kosti.kulujenSeuranta.controller;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kategoria;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tapahtuma;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tyyppi;
import fi.jyu.ohj2.kosti.kulujenSeuranta.service.KategoriaService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LisaaTuloController implements Initializable {

    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML private ComboBox<Kategoria> tuloKategoria;
    @FXML private TextField tuloText;
    @FXML private TextField tSumma;
    @FXML private Button tTallenna;
    @FXML private Button tPeruuta;
    @FXML private DatePicker tuloPvmValitsin;
    @FXML private Label kategoriaVirheLabel;
    @FXML private Label aiheVirheLabel;
    @FXML private Label summaVirheLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tuloKategoria.setItems(
                KategoriaService.getKategoriat().filtered(k -> k.getTyyppi() == Tyyppi.TULO)
        );
        tuloPvmValitsin.setValue(LocalDate.now());
    }

    @FXML
    private void handleTTallenna() {
        tyhjennaVirhe();
        String aihe = tuloText.getText().trim();
        Kategoria kategoria = tuloKategoria.getValue();
        String summaText = tSumma.getText().trim();
        LocalDate pvm = tuloPvmValitsin.getValue();
        boolean pakollinen = false;

        boolean virhe = false;

        if (kategoria == null) {
            kategoriaVirheLabel.setText("Etkö halua lisätä kategoriaa?");
        }
        if (aihe.isEmpty()) {
            aiheVirheLabel.setText("Anna aihe!");
            virhe = true;
        }

        if (pvm == null) {
            summaVirheLabel.setText("Valitse päivämäärä!");
            virhe = true;
        }

        if(summaText.isEmpty()){
            summaVirheLabel.setText("Anna summa!");
            virhe = true;
        }
        double summa = 0;
        if(!summaText.isEmpty()){
            try {
                summa = Double.parseDouble(summaText.replace(",", "."));
                if(summa < 0){
                    summaVirheLabel.setText("Summa ei voi olla negatiivinen!");
                    virhe = true;
                }
            } catch (NumberFormatException e) {
                summaVirheLabel.setText("Anna kelvollinen summa!");
                virhe = true;
            }
        }
        if(virhe) return;

        Tapahtuma tapahtuma = new Tapahtuma(
                pvm,
                aihe,
                kategoria,
                summa,
                pakollinen,
                Tyyppi.TULO
        );
        mainController.lisaaTapahtuma(tapahtuma);
        suljeIkkuna();
    }

    @FXML
    private void handleTPeruuta() {
        suljeIkkuna();
    }

    private void tyhjennaVirhe() {
        aiheVirheLabel.setText("");
        kategoriaVirheLabel.setText("");
        summaVirheLabel.setText("");
    }

    private void suljeIkkuna() {
        Stage stage = (Stage) tPeruuta.getScene().getWindow();
        stage.close();
    }
}
