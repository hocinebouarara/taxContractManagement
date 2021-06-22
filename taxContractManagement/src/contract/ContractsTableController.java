/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contract;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpres.DbConnect;
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
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import models.Contract;
import models.Property;
import proprietors.ProprietorsViewController;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class ContractsTableController implements Initializable {

    @FXML
    private TableView<Contract> propertiesTable;
    @FXML
    private TableColumn<Contract, String> checkCol;
    @FXML
    private TableColumn<Contract, String> idCol;
    @FXML
    private TableColumn<Contract, String> idProprietorCol;
    @FXML
    private TableColumn<Contract, String> idBenefiCol;
    @FXML
    private TableColumn<Contract, String> idProprietyCol;
    @FXML
    private TableColumn<Contract, String> ProprietorNameCol;
    @FXML
    private TableColumn<Contract, String> beneficiaryNameCol;
    @FXML
    private TableColumn<Contract, String> typeCol;
    @FXML
    private TableColumn<Contract, String> dateCol;
    @FXML
    private TableColumn<Contract, String> endDateCol;
    @FXML
    private TableColumn<Contract, String> amountCol;
    @FXML
    private TableColumn<Contract, String> SteelNumberCol;
    @FXML
    private TableColumn<Contract, String> actionCol;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Property property = null;

    ObservableList<Contract> contractsList = FXCollections.observableArrayList();

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
            contractsList.clear();

            query = "SELECT\n"
                    + "    contrat.id,\n"
                    + "    proprietaire.id,\n"
                    + "    beneficiaire.id,\n"
                    + "    fiche_habitation.id,\n"
                    + "    proprietaire.nom_prenom_or_RS,\n"
                    + "    beneficiaire.nom_prenom_or_RS,\n"
                    + "    contrat.type_contr,\n"
                    + "    contrat.date,\n"
                    + "    contrat.date_fin,\n"
                    + "    contrat.montant,\n"
                    + "    contrat.n_acie\n"
                    + "FROM\n"
                    + "    contrat\n"
                    + "INNER JOIN proprietaire ON proprietaire.id = contrat.id_propr\n"
                    + "INNER JOIN beneficiaire ON beneficiaire.id = contrat.id_benef\n"
                    + "INNER JOIN fiche_habitation ON fiche_habitation.id = contrat.id_fiche_hab\n"
                    + "ORDER BY\n"
                    + "    contrat.id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                contractsList.add(new Contract(
                        resultSet.getInt("contrat.id"),
                        resultSet.getInt("proprietaire.id"),
                        resultSet.getInt("beneficiaire.id"),
                        resultSet.getInt("fiche_habitation.id"),
                        resultSet.getString("proprietaire.nom_prenom_or_RS"),
                        resultSet.getString("beneficiaire.nom_prenom_or_RS"),
                        resultSet.getString("contrat.type_contr"),
                        resultSet.getDate("contrat.date"),
                        resultSet.getDate("contrat.date_fin"),
                        resultSet.getFloat("contrat.montant"),
                        resultSet.getString("contrat.n_acie")
                ));
                propertiesTable.setItems(contractsList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadDate() {

        connection = DbConnect.getConnect();
        refreshTable();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idProprietorCol.setCellValueFactory(new PropertyValueFactory<>("id_proprietor"));
        idBenefiCol.setCellValueFactory(new PropertyValueFactory<>("id_beneficiary"));
        idProprietyCol.setCellValueFactory(new PropertyValueFactory<>("id_habitation"));
        ProprietorNameCol.setCellValueFactory(new PropertyValueFactory<>("proprietorName"));
        beneficiaryNameCol.setCellValueFactory(new PropertyValueFactory<>("beneficiaryName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("contractType"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        SteelNumberCol.setCellValueFactory(new PropertyValueFactory<>("SteelNumber"));

        //add cell of button edit 
        Callback<TableColumn<Contract, String>, TableCell<Contract, String>> cellFoctory = (TableColumn<Contract, String> param) -> {
            // make cell containing buttons
            final TableCell<Contract, String> cell = new TableCell<Contract, String>() {
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
        propertiesTable.setItems(contractsList);

    }

}
