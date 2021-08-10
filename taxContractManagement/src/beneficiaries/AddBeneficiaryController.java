/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beneficiaries;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class AddBeneficiaryController implements Initializable {

    @FXML
    private Button personnelsBtn;
    @FXML
    private Button metierBtn;
    @FXML
    private AnchorPane personnelsInfoAnchor;
    @FXML
    private AnchorPane metierInfoAnchor;
    @FXML
    private JFXDatePicker dateFld;
    @FXML
    private TextField actPrincFld;
    @FXML
    private TextField adressActPricFld;
    @FXML
    private TextField actSecnFld;
    @FXML
    private TextField adressActSecnFld;
    @FXML
    private TextField numRegisFld;
    @FXML
    private JFXDatePicker regisDateFld;
    @FXML
    private TextField carteArtFld;
    @FXML
    private JFXDatePicker cartDateFld;
    @FXML
    private TextField agrementFld;
    @FXML
    private JFXDatePicker agrementDateFld;
    @FXML
    private TextField AutresFld;
    @FXML
    private TextField nameFld;
    @FXML
    private JFXDatePicker birthDateFld;
    @FXML
    private TextField communeFld;
    @FXML
    private TextField wilayaFld;
    @FXML
    private TextField nameFatherFld;
    @FXML
    private TextField nameMotherFld;
    @FXML
    private TextField nationFld;
    @FXML
    private TextField AdressFld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getPersonnelsView();

        /* adressField.setOnMouseClicked((event) -> {
            adressField.setStyle("-fx-border-color:#123456");
        });*/
    }

    @FXML
    private void getPersonnelsView() {
        personnelsBtn.setStyle("-fx-background-color:white;-fx-border-width:1.6px;-fx-border-color:#123456;-fx-border-radius:5px;");
        metierBtn.setStyle("-fx-background-color:#DDD;");
        metierInfoAnchor.setVisible(false);
        personnelsInfoAnchor.setVisible(true);
    }

    @FXML
    private void getMetierView(MouseEvent event) {
        metierBtn.setStyle("-fx-background-color:white;-fx-border-width:1.6px;-fx-border-color:#123456;-fx-border-radius:5px;");
        personnelsBtn.setStyle("-fx-background-color:#DDD;");
        metierInfoAnchor.setVisible(true);
        personnelsInfoAnchor.setVisible(false);
    }

    @FXML
    private void save(MouseEvent event) {
    }

    @FXML
    private void clean() {
        nameFld.setText(null);
        birthDateFld.setValue(null);
        communeFld.setText(null);
        wilayaFld.setText(null);
        nameFatherFld.setText(null);
        nameMotherFld.setText(null);
        nationFld.setText(null);
        AdressFld.setText(null);
        actPrincFld.setText(null);
        adressActPricFld.setText(null);
        actSecnFld.setText(null);
        adressActSecnFld.setText(null);
        numRegisFld.setText(null);
        regisDateFld.setValue(null);
        carteArtFld.setText(null);
        cartDateFld.setValue(null);
        agrementFld.setText(null);
        agrementDateFld.setValue(null);
        dateFld.setValue(null);
        AutresFld.setText(null);

    }

    @FXML
    private void next(MouseEvent event) {
    }

}
