package fi.jyu.ohj2.kosti.kulujenSeuranta.controller;
import fi.jyu.ohj2.kosti.kulujenSeuranta.App;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kategoria;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Kokoelma;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tapahtuma;
import fi.jyu.ohj2.kosti.kulujenSeuranta.model.Tyyppi;
import fi.jyu.ohj2.kosti.kulujenSeuranta.service.KategoriaService;
import fi.jyu.ohj2.kosti.kulujenSeuranta.service.KokoelmaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final ObservableList<Tapahtuma> tapahtumat = FXCollections.observableArrayList();
    private FilteredList<Tapahtuma> suodatettuLista;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tapahtumatTable.setItems(tapahtumat);
        suodatettuLista = new FilteredList<>(tapahtumat, p -> true);
        tapahtumatTable.setItems(suodatettuLista);
        pvmCol.setCellValueFactory(new PropertyValueFactory<>("pvm"));
        summaCol.setCellValueFactory(new PropertyValueFactory<>("summa"));
        aiheCol.setCellValueFactory(new PropertyValueFactory<>("aihe"));
        kategoriaCol.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        lataaData();
        tallennaData();
    }

    @FXML private DatePicker alkuPvmValitsin;
    @FXML private DatePicker loppuPvmValitsin;
    @FXML private ComboBox<Kategoria> kategoriaBox;
    @FXML private Button kategoriaButton;
    @FXML private CheckBox vainPakollisetCheck;
    @FXML private TableView<Tapahtuma> tapahtumatTable;
    @FXML private TextField menotYhteensaField;
    @FXML private TextField tulotYhteensaField;
    @FXML private Button lisaaTuloButton;
    @FXML private Button lisaaMenoButton;
    @FXML private Button PoistaValittuButton;
    @FXML private TableColumn<Tapahtuma, LocalDate> pvmCol;
    @FXML private TableColumn<Tapahtuma, Double> summaCol;
    @FXML private TableColumn<Tapahtuma, String> aiheCol;
    @FXML private TableColumn<Tapahtuma, Kategoria> kategoriaCol;


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
    private void handlePoistaValittuButton() {
        Tapahtuma valittu = tapahtumatTable.getSelectionModel().getSelectedItem();
        if (valittu == null) return;

        Alert varmistus = new Alert(Alert.AlertType.CONFIRMATION);
        varmistus.setTitle("Haluatko varmasti poistaa tapahtuman?");
        varmistus.setHeaderText("Poistetaanko?");
        varmistus.setContentText(valittu.getAihe());

        Optional<ButtonType> tulos = varmistus.showAndWait();
        if (tulos.isPresent() && tulos.get() == ButtonType.OK) {
            poistaTapahtuma(valittu);
        }
    }

    @FXML
    private void handleKategoriaButton(){
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("kategoriaMuokkaus.fxml"));
            Parent root = loader.load();
            KategoriaMuokkausController controller = loader.getController();
            controller.setMainController(this);

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
        if(vainPakollisetCheck.isSelected()) {
            suodatettuLista.setPredicate(p -> p.isPakollinen());
        } else {
            suodatettuLista.setPredicate(p -> true);
        }
    }

    @FXML
    private void handleAlkuPvmValitsin(){
        System.out.println("Alku pvm valitsin");
    }

    @FXML
    private void handleLoppuPvmValitsin(){
        System.out.println("Loppu pvm valitsin");
    }

    public ObservableList<Tapahtuma> getTapahtumat() {
        return tapahtumat;
    }

    public void lisaaTapahtuma(Tapahtuma tapahtuma) {
        tapahtumat.add(tapahtuma);
        paivitaTaulukko();
        PaivitaSummaKentat();
        tallennaData();
    }

    public void poistaTapahtuma(Tapahtuma poistettava) {
        if (poistettava == null) return;
        tapahtumat.remove(poistettava);
        paivitaTaulukko();
        PaivitaSummaKentat();
        tallennaData();
    }

    public void paivitaTaulukko() {
        tapahtumatTable.refresh();
    }

    public void lataaData(){
        Kokoelma data = KokoelmaService.lataa();
        tapahtumat.clear();
        tapahtumat.addAll(data.getTapahtumat());

        if(!data.getKategoriat().isEmpty()){
            KategoriaService.getKategoriat().clear();
            KategoriaService.getKategoriat().addAll(data.getKategoriat());
        }
    }

    public void tallennaData(){
        Kokoelma data = new Kokoelma();
        data.setTapahtumat(new ArrayList<>(tapahtumat));
        data.setKategoriat(new ArrayList<>(KategoriaService.getKategoriat()));
        KokoelmaService.tallenna(data);
    }

    public void PaivitaSummaKentat() {
        double tulot = suodatettuLista.stream()
                .filter(t -> t.getTyyppi() == Tyyppi.TULO)
                .mapToDouble(Tapahtuma::getSumma)
                .sum();

        double menot = suodatettuLista.stream()
                .filter(t -> t.getTyyppi() == Tyyppi.MENO)
                .mapToDouble(Tapahtuma::getSumma)
                .sum();

        tulotYhteensaField.setText(String.format("%.2f", tulot));
        menotYhteensaField.setText(String.format("%.2f", menot));
    }
}
