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
    //Tapahtumakäsittelijät
    @FXML
    private void handleMenoKategoria(){
        System.out.println("Meno-kategoria valitsin");
    }
    @FXML
    private void handleMenoTeksti(){
        System.out.println("Meno-teksti valitsin");
    }
    @FXML
    private void handleMenoCheckbox(){
        System.out.println("Meno-checkbox valitsin");
    }
    @FXML
    private void handleMSumma(){
        System.out.println("Meno-summa");
    }
    @FXML
    private void handleMTallenna(){
        String aihe = menoTeksti.getText();
        Kategoria kategoria = menoKategoria.getValue();
        double summa = Double.parseDouble(mSumma.getText());
        LocalDate pvm = menoPvmValitsin.getValue();
        boolean pakollinen = menoCheckbox.isSelected();

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

    private void suljeIkkuna() {
        Stage stage = (Stage) mPeruuta.getScene().getWindow();
        stage.close();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
