package fi.jyu.ohj2.kosti.kulujenSeuranta.controller;
import fi.jyu.ohj2.kosti.kulujenSeuranta.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("lisaaTulo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage dialogi = new Stage();
            dialogi.setScene(scene);

            dialogi.setTitle("Lisää uusi tulo");
            dialogi.setMinWidth(250);
            dialogi.setMinHeight(300);
            dialogi.initModality(Modality.APPLICATION_MODAL);

            dialogi.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleLisaaMeno() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("lisaaMeno.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage dialogi = new Stage();
            dialogi.setScene(scene);

            dialogi.setTitle("Lisää uusi meno");
            dialogi.setMinWidth(250);
            dialogi.setMinHeight(300);
            dialogi.initModality(Modality.APPLICATION_MODAL);

            dialogi.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleKategoriaButton(){
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("kategoriaMuokkaus.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage dialogi = new Stage();
            dialogi.setScene(scene);

            dialogi.setTitle("Muokkaa kategorioita");
            dialogi.setMinWidth(250);
            dialogi.setMinHeight(300);
            dialogi.initModality(Modality.APPLICATION_MODAL);

            dialogi.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void handleKategoriaBox (){
        System.out.println("Kategoria valitsin");
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
