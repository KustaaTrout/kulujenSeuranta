package fi.jyu.ohj2.kosti.kulujenSeuranta.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class LisaaMenoController implements Initializable {

    @FXML
    private ComboBox<String> menoKategoria;
    @FXML
    private TextField menoTeksti;
    @FXML
    private CheckBox menoCheckbox;
    @FXML
    private TextField mSumma;
    @FXML
    private Button mTallenna;
    @FXML
    private Button mPeruuta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
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
        System.out.println("Tallenna meno");
    }
    @FXML
    private void handleMPeruuta(){
        System.out.println("Peruuta");
    }



}
