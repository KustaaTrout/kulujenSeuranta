package fi.jyu.ohj2.kosti.kulujenSeuranta.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LisaaTuloController implements Initializable {
    @FXML
    private ComboBox<String> tuloKategoria;
    @FXML
    private TextField tuloText;
    @FXML
    private TextField tSumma;
    @FXML
    private Button tTallenna;
    @FXML
    private Button tPeruuta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        System.out.println("Tallenna tulo");
    }
    @FXML
    private void handleTPeruuta() {
        suljeIkkuna();
    }

    private void suljeIkkuna() {
        Stage stage = (Stage) tPeruuta.getScene().getWindow();
        stage.close();
    }
}
