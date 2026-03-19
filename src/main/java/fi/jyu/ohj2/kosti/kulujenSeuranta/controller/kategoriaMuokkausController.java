package fi.jyu.ohj2.kosti.kulujenSeuranta.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class kategoriaMuokkausController implements Initializable {

    @FXML
    private ListView kategoriatList;
    @FXML
    private Button poistaValintaButton;
    @FXML
    private TextField lisaaUusiText;
    @FXML
    private CheckBox menoVaiTuloBox;
    @FXML
    private Button lisaaUusiButton;
    @FXML
    private Button suljeButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    @FXML
    private void handlePoista(){
        System.out.println("poista");
    }
    @FXML
    private void handleLisaaUusi(){
        System.out.println("Lisää uusi");
    }
    @FXML
    private void handleLisaaUusiText(){
    }

    @FXML
    private void handleSulje(){
        suljeIkkuna();
    }
    private void suljeIkkuna() {
        Stage stage = (Stage) suljeButton.getScene().getWindow();
        stage.close();
    }


}
