/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proprietors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import helpres.DbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Proprietor;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class AddProprietorController implements Initializable {

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Proprietor proprietor = null;
    private boolean update = false;
    int proprietorId = -1;
    @FXML
    private Button personnelsBtn;
    @FXML
    private AnchorPane personnelsInfoAnchor;
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
    private TextField phoneFld;
    @FXML
    private TextField AdressFld;
    @FXML
    private JFXButton getDataBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

       getDataBtn.setTooltip(new Tooltip("Coller les donnees"));

    }

    public void setUpdate(boolean b) {
        this.update = b;

    }

    public void setTextField(int id, String name, LocalDate toLocalDate, String adress, String email) {

        proprietorId = id;

    }

    @FXML
    private void save(MouseEvent event) {
        connection = DbConnect.getConnect();
        String name = nameFld.getText();
        String birth = String.valueOf(birthDateFld.getValue());
        String adress = AdressFld.getText();
        String commune = communeFld.getText();
        String wilaya = wilayaFld.getText();
        String father = nameFatherFld.getText();
        String mother = nameMotherFld.getText();
        String nationalite = nationFld.getText();
        String phone = phoneFld.getText();

        if (name.isEmpty() || birth.isEmpty() || adress.isEmpty() || phone.isEmpty()
                || commune.isEmpty()|| wilaya.isEmpty()|| father.isEmpty()
                || nationalite.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir toutes les données");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();

        }

    }

    @FXML
    private void clean() {
        nameFatherFld.setText(null);
        birthDateFld.setValue(null);
        AdressFld.setText(null);
        nationFld.setText(null);
        wilayaFld.setText(null);
        communeFld.setText(null);
        nameMotherFld.setText(null);
        nameFld.setText(null);
        phoneFld.setText(null);
    }

    private void getQuery() {

        if (proprietorId == -1) {

            query = "INSERT INTO `proprietaire`(`nom_prenom_or_RS`, `date_nss`, `adress`, `commune`, `wilaya`, `pere`, `mere`, `nationalite`, `telephone`) VALUES (?,?,?,?,?,?,?,?,?)";
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Ajouté avec succes");
            alert.showAndWait();

        } else {
            query = "UPDATE `proprietaire` SET "
                    + "`nom_prenom_or_RS`=?,"
                    + "`date_nss`=?,"
                    + "`adress`=?,"
                    + "`commune`=?,"
                    + "`wilaya`=?,"
                    + "`pere`=?,"
                    + "`mere`=?,"
                    + "`nationalite`=?,"
                    + "`telephone`=? WHERE id = '" + proprietorId + "'";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Modifier avec succes");
            alert.showAndWait();

        }
    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameFld.getText());
            preparedStatement.setString(2, String.valueOf(birthDateFld.getValue()));
            preparedStatement.setString(3, AdressFld.getText());
            preparedStatement.setString(4, communeFld.getText());
            preparedStatement.setString(5, wilayaFld.getText());
            preparedStatement.setString(6, nameFatherFld.getText());
            preparedStatement.setString(7, nameFatherFld.getText());
            preparedStatement.setString(8, nationFld.getText());

            preparedStatement.setString(9, phoneFld.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddProprietorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void getPersonnelsView(MouseEvent event) {
    }

    @FXML
    private void getData(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Proprietor proprietor1 = (Proprietor) stage.getUserData();
        proprietorId = proprietor1.getId();
        nameFld.setText(proprietor1.getName());
        birthDateFld.setValue(proprietor1.getBirthDate().toLocalDate());
        communeFld.setText(proprietor1.getCommune());
        wilayaFld.setText(proprietor1.getWilaya());
        nameFatherFld.setText(proprietor1.getPrenom_pere());
        nameMotherFld.setText(proprietor1.getNom_mere());
        nationFld.setText(proprietor1.getNationnalite());
        phoneFld.setText(proprietor1.getPhone());
        AdressFld.setText(proprietor1.getAdresse_domicile());
    }

}
