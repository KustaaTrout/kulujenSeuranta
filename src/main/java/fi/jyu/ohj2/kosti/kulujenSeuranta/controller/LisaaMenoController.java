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

public class LisaaMenoController implements Initializable {

    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML private ComboBox<Kategoria> menoKategoria;
    @FXML private TextField menoTeksti;
    @FXML private CheckBox menoCheckbox;
    @FXML private TextField mSumma;
    @FXML private Button mTallenna;
    @FXML private Button mPeruuta;
    @FXML private DatePicker menoPvmValitsin;
    @FXML private Label mKategoriaVirheLabel;
    @FXML private Label mAiheVirheLabel;
    @FXML private Label mSummaVirheLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menoKategoria.setItems(
                KategoriaService.getKategoriat().filtered(k -> k.getTyyppi() == Tyyppi.MENO)
        );
        menoPvmValitsin.setValue(LocalDate.now());
    }

    @FXML
    private void handleMTallenna(){
        tyhjennaVirhe();
        String aihe = menoTeksti.getText().trim();
        Kategoria kategoria = menoKategoria.getValue();
        String summaText = mSumma.getText().trim();
        LocalDate pvm = menoPvmValitsin.getValue();
        boolean pakollinen = menoCheckbox.isSelected();

        boolean virhe = false;

        if (kategoria == null) {
            mKategoriaVirheLabel.setText("Etkö halua lisätä kategoriaa?");
        }
        if (aihe.isEmpty()) {
            mAiheVirheLabel.setText("Anna aihe!");
            virhe = true;
        }

        if (pvm == null) {
            mSummaVirheLabel.setText("Valitse päivämäärä!");
            virhe = true;
        }

        if(summaText.isEmpty()){
            mSummaVirheLabel.setText("Anna summa!");
            virhe = true;
        }
        double summa = 0;
        if(!summaText.isEmpty()){
            try {
                summa = Double.parseDouble(summaText.replace(",", "."));
                if(summa < 0){
                    mSummaVirheLabel.setText("Summa ei voi olla negatiivinen!");
                    virhe = true;
                }
            } catch (NumberFormatException e) {
                mSummaVirheLabel.setText("Anna kelvollinen summa!");
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
                Tyyppi.MENO
        );
        mainController.lisaaTapahtuma(tapahtuma);
        suljeIkkuna();

    }

    @FXML
    private void handleMPeruuta(){
        suljeIkkuna();
    }
    private void tyhjennaVirhe() {
        mAiheVirheLabel.setText("");
        mKategoriaVirheLabel.setText("");
        mSummaVirheLabel.setText("");
    }
    private void suljeIkkuna() {
        Stage stage = (Stage) mPeruuta.getScene().getWindow();
        stage.close();
    }
}
