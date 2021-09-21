/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beneficiaries;

import com.jfoenix.controls.JFXDatePicker;
import helpres.DbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Beneficiaire;
import proprietors.AddProprietorController;

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

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Beneficiaire beneficiaire = null;
    private boolean update = false;
    int beneficiaireId;

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

        String name = nameFld.getText();
        String birth = String.valueOf(dateFld.getValue());
        String commune = communeFld.getText();
        String wilaya = wilayaFld.getText();
        String father = nameFatherFld.getText();
        String mother = nameMotherFld.getText();
        String nation = nationFld.getText();
        String adress = AdressFld.getText();
        String actPrinc = actPrincFld.getText();
        String adressActPrinc = adressActPricFld.getText();
        String actSecon = actSecnFld.getText();
        String adressActSec = adressActSecnFld.getText();
        String numregister = numRegisFld.getText();
        String registerDate = String.valueOf(regisDateFld.getValue());
        String numCarteAr = carteArtFld.getText();
        String carteDate = String.valueOf(cartDateFld.getValue());
        String agrement = agrementFld.getText();
        String agrementDate = String.valueOf(agrementDateFld.getValue());
        String autres = AutresFld.getText();
        String date = String.valueOf(dateFld.getValue());

        if (name.isEmpty() || birth.isEmpty() || wilaya.isEmpty() || commune.isEmpty() || father.isEmpty()
                || mother.isEmpty() || nation.isEmpty() || adress.isEmpty() || actPrinc.isEmpty()
                || adressActPrinc.isEmpty() || actSecon.isEmpty() || adressActSec.isEmpty()
                || numregister.isEmpty() || registerDate.isEmpty() || numCarteAr.isEmpty() || carteDate.isEmpty()
                || agrement.isEmpty() || agrementDate.isEmpty() || autres.isEmpty() || date.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir toutes les données");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Ajouté avec succès");
            alert.showAndWait();

        }
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

    public void setTextFields(int id, String name, Date birth, String commune, String wilaya, String fatherName,
            String motherName, String nation, String adress, String actPrinc, String adressActP, String actSecon,
            String adressActS, String numRegister, Date registerDate, String numCarte, Date carteDate,
            String numAgrement, Date agrementDate, String autres, Date date) {

        beneficiaireId = id;
        nameFld.setText(name);
        birthDateFld.setValue(birth.toLocalDate());
        communeFld.setText(commune);
        wilayaFld.setText(wilaya);
        nameFatherFld.setText(fatherName);
        nameMotherFld.setText(motherName);
        nationFld.setText(nation);
        AdressFld.setText(adress);
        actPrincFld.setText(actPrinc);
        adressActPricFld.setText(adressActP);
        actSecnFld.setText(actSecon);
        adressActSecnFld.setText(adressActS);
        numRegisFld.setText(numRegister);
        regisDateFld.setValue(registerDate.toLocalDate());
        carteArtFld.setText(numCarte);
        cartDateFld.setValue(carteDate.toLocalDate());
        agrementFld.setText(numAgrement);
        agrementDateFld.setValue(agrementDate.toLocalDate());
        dateFld.setValue(date.toLocalDate());
        AutresFld.setText(autres);

    }

    public void setUpdate(boolean b) {
        this.update = b;

    }

    private void getQuery() {
        if (update == false) {

            query = "INSERT INTO `beneficiaire`(`nom_prenom_or_RS`, `date_nss`, `commune`,"
                    + " `wilaya`, `prenom_pere`, `nom_mere`, `nationalite`, `adresse_domicile`,`activite_prcpl`,"
                    + " `adresse_act_prcpl`, `activite_sec`, `adresse_act_sec`,`n_register_comrc`, `date_registre_c`,"
                    + " `n_cart_artisan`, `date_carte_ar`,`n_agrement`, `date_agrement`, `autres`, `date`) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Ajouté avec succès");
            alert.showAndWait();

        } else {
            query = "UPDATE `beneficiaire` SET "
                    + "`nom_prenom_or_RS`=?,"
                    + "`date_nss`=?,"
                    + "`commune`=?,"
                    + "`wilaya`=?,"
                    + "`prenom_pere`=?,"
                    + "`nom_mere`=?,"
                    + "`nationalite`=?,"
                    + "`adresse_domicile`=?,"
                    + "`activite_prcpl`=?,"
                    + "`adresse_act_prcpl`=?,"
                    + "`activite_sec`=?,"
                    + "`adresse_act_sec`=?,"
                    + "`n_register_comrc`=?,"
                    + "`date_registre_c`=?,"
                    + "`n_cart_artisan`=?,"
                    + "`date_carte_ar`=?,"
                    + "`n_agrement`=?,"
                    + "`date_agrement`=?,"
                    + "`autres`=?,"
                    + "`date`=? WHERE id = '" + beneficiaireId + "'";
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("modifier avec succès");
                alert.showAndWait();

        }
    }

    private void insert() {
        try {
            connection = DbConnect.getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameFld.getText());
            preparedStatement.setString(2, String.valueOf(birthDateFld.getValue()));
            preparedStatement.setString(3, communeFld.getText());
            preparedStatement.setString(4, wilayaFld.getText());
            preparedStatement.setString(5, nameFatherFld.getText());
            preparedStatement.setString(6, nameMotherFld.getText());
            preparedStatement.setString(7, nationFld.getText());
            preparedStatement.setString(8, AdressFld.getText());
            preparedStatement.setString(9, actPrincFld.getText());
            preparedStatement.setString(10, adressActPricFld.getText());
            preparedStatement.setString(11, actSecnFld.getText());
            preparedStatement.setString(12, adressActSecnFld.getText());
            preparedStatement.setString(13, numRegisFld.getText());
            preparedStatement.setString(14, String.valueOf(regisDateFld.getValue()));
            preparedStatement.setString(15, carteArtFld.getText());
            preparedStatement.setString(16, String.valueOf(cartDateFld.getValue()));
            preparedStatement.setString(17, agrementFld.getText());
            preparedStatement.setString(18, String.valueOf(agrementDateFld.getValue()));
            preparedStatement.setString(19, AutresFld.getText());
            preparedStatement.setString(20, String.valueOf(dateFld.getValue()));

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddProprietorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
