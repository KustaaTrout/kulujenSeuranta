package fi.jyu.ohj2.kosti.kulujenSeuranta.controller;
import fi.jyu.ohj2.kosti.kulujenSeuranta.App;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kategoria;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tapahtuma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tapahtumatTable.setItems(tapahtumat);
        pvmCol.setCellValueFactory(new PropertyValueFactory<>("pvm"));
        summaCol.setCellValueFactory(new PropertyValueFactory<>("summa"));
        aiheCol.setCellValueFactory(new PropertyValueFactory<>("aihe"));
        kategoriaCol.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
    }

    private final ObservableList<Tapahtuma> tapahtumat = FXCollections.observableArrayList();

    @FXML
    private DatePicker alkuPvmValitsin;
    @FXML
    private DatePicker loppuPvmValitsin;
    @FXML
    private ComboBox<Kategoria> kategoriaBox;
    @FXML
    private Button kategoriaButton;
    @FXML
    private CheckBox vainPakollisetCheck;
    // lisää tableviewiin tyypitys!
    @FXML
    private TableView<Tapahtuma> tapahtumatTable;
    @FXML
    private TextField menotYhteensaField;
    @FXML
    private TextField tulotYhteensaField;
    @FXML
    private Button lisaaTuloButton;
    @FXML
    private Button lisaaMenoButton;
    @FXML
    private TableColumn<Tapahtuma, LocalDate> pvmCol;
    @FXML
    private TableColumn<Tapahtuma, Double> summaCol;
    @FXML
    private TableColumn<Tapahtuma, String> aiheCol;
    @FXML
    private TableColumn<Tapahtuma, Kategoria> kategoriaCol;




    public void lisaaTapahtuma(Tapahtuma tapahtuma) {
        tapahtumat.add(tapahtuma);
        //tapahtumatTable.refresh();

    }



    //  Tapahtumankäsittelijät
    @FXML
    private void handleLisaaTulo() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("lisaaTulo.fxml"));
            Parent root = loader.load();

            LisaaTuloController controller = loader.getController();
            controller.setMainController(this);

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

            LisaaMenoController controller = loader.getController();
            controller.setMainController(this);

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
