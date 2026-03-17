package fi.jyu.ohj2.kosti.kulujenSeuranta.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Write initialization code here
    }

    @FXML
    private DatePicker alkuPvmValitsin;
    @FXML
    private DatePicker loppuPvmValitsin;
    @FXML
    private ComboBox<String> kategoriaBox;
    @FXML
    private Button kategoriaButton;
    @FXML
    private CheckBox vainPakollisetCheck;
    // lisää tableviewiin tyypitys!
    @FXML
    private TableView tapahtumatTable;
    @FXML
    private TextField menotYhteensaField;
    @FXML
    private TextField tulotYhteensaField;
    @FXML
    private Button lisaaTuloButton;
    @FXML
    private Button lisaaMenoButton;
    @FXML
    private TableColumn pvmCol;
    @FXML
    private TableColumn summaCol;
    @FXML
    private TableColumn aiheCol;
    @FXML
    private TableColumn kategoriaCol;



    //  Tapahtumankäsittelijät
    @FXML
    private void handleLisaaTulo() {
        System.out.println("Lisää tulo");
    }

    @FXML
    private void handleLisaaMeno() {
        System.out.println("Lisää meno");
    }

    @FXML
    private void handleKategoriaBox (){
        System.out.println("Kategoria valitsin");
    }

    @FXML
    private void handleKategoriaButton (){
        System.out.println("Kategorioiden muokkaus");
    }

    @FXML
    private void handleVainPakolliset(){
        System.out.println("Listaa vain pakolliset");
    }

    @FXML
    private void handleAlkuPvmValitsin(){
        System.out.println("Alku pvm valitsin");
    }

    @FXML
    private void handleLoppuPvmValitsin(){
        System.out.println("Loppu pvm valitsin");
    }

}
