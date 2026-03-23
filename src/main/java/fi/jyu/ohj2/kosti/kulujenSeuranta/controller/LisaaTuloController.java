package fi.jyu.ohj2.kosti.kulujenSeuranta.controller;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kategoria;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tapahtuma;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tyyppi;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LisaaTuloController implements Initializable {

    private MainController mainController;

    @FXML
    private ComboBox<Kategoria> tuloKategoria;
    @FXML
    private TextField tuloText;
    @FXML
    private TextField tSumma;
    @FXML
    private Button tTallenna;
    @FXML
    private Button tPeruuta;
    @FXML
    private DatePicker tuloPvmValitsin;
    @FXML
    private Label kategoriaVirheLabel;
    @FXML
    private Label aiheVirheLabel;
    @FXML
    private Label summaVirheLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tuloKategoria.getItems().addAll(
                new Kategoria("Palkka", Tyyppi.TULO),
                new Kategoria("Tuet", Tyyppi.TULO)
        );
        tuloPvmValitsin.setValue(LocalDate.now());


    }

    @FXML
    private void handleTuloKategoria() {
        System.out.println("Tulo-kategoria valitsin");
    }
    @FXML
    private void handleTuloTeksti() {
        System.out.println("Tulo-teksti");
    }
    @FXML
    private void handleTSumma() {
        System.out.println("Tulo-summa");
    }
    @FXML
    private void handleTTallenna() {
        String aihe = tuloText.getText();
        Kategoria kategoria = tuloKategoria.getValue();
        double summa = Double.parseDouble(tSumma.getText());
        LocalDate pvm = tuloPvmValitsin.getValue();
        boolean pakollinen = false;

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

    private void suljeIkkuna() {
        Stage stage = (Stage) tPeruuta.getScene().getWindow();
        stage.close();
    }



    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
