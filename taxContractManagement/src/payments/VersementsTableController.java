/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpres.DbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.Payment;
import models.Proprietor;
import proprietors.AddProprietorController;
import proprietors.ProprietorsViewController;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class VersementsTableController implements Initializable {

    @FXML
    private TableView<Payment> paymentTable;
    @FXML
    private TableColumn<Payment, String> checkCol;
    @FXML
    private TableColumn<Payment, String> idCol;
    @FXML
    private TableColumn<Payment, String> idContratCol;
    @FXML
    private TableColumn<Payment, String> lessorNameCol;
    @FXML
    private TableColumn<Payment, String> lessorAdressCol;
    @FXML
    private TableColumn<Payment, String> nifCol;
    @FXML
    private TableColumn<Payment, String> nbrArticleCol;
    @FXML
    private TableColumn<Payment, String> amountCol;
    @FXML
    private TableColumn<Payment, String> takerNameCol;
    @FXML
    private TableColumn<Payment, String> occupationCol;
    @FXML
    private TableColumn<Payment, String> actionCol;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Payment payment = null;

    ObservableList<Payment> paymentsList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Payment, String> takerAdressCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }

    private void refreshTable() {
        try {
            paymentsList.clear();

            query = "SELECT\n"
                    + "    avis_versement.id,\n"
                    + "    contrat.id,\n"
                    + "    avis_versement.nom_bailleur,\n"
                    + "    avis_versement.adresse_bailleur,\n"
                    + "    avis_versement.n_id_fiscal,\n"
                    + "    avis_versement.n_articlage,\n"
                    + "    avis_versement.adresse_du_bien,\n"
                    + "    avis_versement.montant,\n"
                    + "    avis_versement.nom_preneur,\n"
                    + "    avis_versement.occupation_preneur\n"
                    + "FROM\n"
                    + "    avis_versement\n"
                    + "INNER JOIN contrat ON avis_versement.id = contrat.id\n"
                    + "ORDER BY\n"
                    + "    avis_versement.id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                paymentsList.add(new Payment(
                        resultSet.getInt("avis_versement.id"),
                        resultSet.getInt("contrat.id"),
                        resultSet.getString("avis_versement.nom_bailleur"),
                        resultSet.getString("avis_versement.adresse_bailleur"),
                        resultSet.getString("avis_versement.n_id_fiscal"),
                        resultSet.getString("avis_versement.n_articlage"),
                        resultSet.getString("avis_versement.adresse_du_bien"),
                        resultSet.getFloat("avis_versement.montant"),
                        resultSet.getString("avis_versement.nom_preneur"),
                        resultSet.getString("avis_versement.occupation_preneur")));
                paymentTable.setItems(paymentsList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDate() {

        connection = DbConnect.getConnect();
        refreshTable();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idContratCol.setCellValueFactory(new PropertyValueFactory<>("idContract"));
        lessorNameCol.setCellValueFactory(new PropertyValueFactory<>("lessorName"));
        lessorAdressCol.setCellValueFactory(new PropertyValueFactory<>("lessorAdress"));
        nifCol.setCellValueFactory(new PropertyValueFactory<>("nif"));
        nbrArticleCol.setCellValueFactory(new PropertyValueFactory<>("nbrAtricle"));
        takerAdressCol.setCellValueFactory(new PropertyValueFactory<>("takerAdress"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        takerNameCol.setCellValueFactory(new PropertyValueFactory<>("takerName"));
        occupationCol.setCellValueFactory(new PropertyValueFactory<>("occupationTaker"));

        //add cell of button edit 
        Callback<TableColumn<Payment, String>, TableCell<Payment, String>> cellFoctory = (TableColumn<Payment, String> param) -> {
            // make cell containing buttons
            final TableCell<Payment, String> cell = new TableCell<Payment, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        actionCol.setCellFactory(cellFoctory);
        paymentTable.setItems(paymentsList);

    }
}
