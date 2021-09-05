/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import models.Contract;
import models.Property;
import proprietors.AddProprietorController;
import proprietors.ProprietorsViewController;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class AddFicheControleController implements Initializable {

    @FXML
    private TableView<Contract> contractTable;
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

    @FXML
    private Button contractBtn;
    @FXML
    private AnchorPane ficheControllerAnchor;
    @FXML
    private TextField inspectionFld;
    @FXML
    private TextField recetteFld;
    @FXML
    private TextField anneeFld;
    @FXML
    private TextField designationFld;
    @FXML
    private TextField nisFld;
    @FXML
    private TextField nifFld;
    @FXML
    private TextField activityFld;
    @FXML
    private TextField codeActivityFld;
    @FXML
    private TextField FormJurdFld;
    @FXML
    private TextField articleImpotFld;
    @FXML
    private TextField wilayaFld;
    @FXML
    private TextField adressFld;
    @FXML
    private AnchorPane ContractAnchor;
    @FXML
    private TextField ProprietaireFld;
    @FXML
    private TextField nomBenefiFld;
    @FXML
    private JFXDatePicker startDateFld;
    @FXML
    private JFXDatePicker finDateFld;
    @FXML
    private TextField montantFld;
    @FXML
    private TextField numAcieFld;
    @FXML
    private Button ficheControleBtn;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Contract contract = null;
    boolean update = false;

    ObservableList<Contract> contractsList = FXCollections.observableArrayList();
    @FXML
    private TextField contractTypeFld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getContractView();
        loadData();
    }

    @FXML
    private void getContractView() {
        contractBtn.setStyle("-fx-background-color:white;-fx-border-width:1.6px;-fx-border-color:#123456;-fx-border-radius:5px;");
        ficheControleBtn.setStyle("-fx-background-color:#DDD;");
        ContractAnchor.setVisible(true);
        ficheControllerAnchor.setVisible(false);
    }

    @FXML
    private void getFicheControllerView() {
        ficheControleBtn.setStyle("-fx-background-color:white;-fx-border-width:1.6px;-fx-border-color:#123456;-fx-border-radius:5px;");
        contractBtn.setStyle("-fx-background-color:#DDD;");
        ContractAnchor.setVisible(false);
        ficheControllerAnchor.setVisible(true);
    }

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO `fiche_de_control`(`inscpection`, `Recette`, `Annee`,"
                    + " `Designation`, `NiS`, `NIF`, `Wilaya`, `Activity`,"
                    + " `Code_d_activity`, `Forme_Juridique`, `Adress`,"
                    + " `Article_imposition`, `id_fiscal`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        } else {

        }

    }

    private void insert() {
        try {

            connection = DbConnect.getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, inspectionFld.getText());
            preparedStatement.setString(2, recetteFld.getText());
            preparedStatement.setInt(3, Integer.valueOf(anneeFld.getText()));
            preparedStatement.setString(4, designationFld.getText());
            preparedStatement.setInt(5, Integer.valueOf(nifFld.getText()));
            preparedStatement.setInt(6, Integer.valueOf(nisFld.getText()));
            preparedStatement.setString(7, wilayaFld.getText());
            preparedStatement.setString(8, activityFld.getText());
            preparedStatement.setInt(9, Integer.valueOf(codeActivityFld.getText()));
            preparedStatement.setString(10, FormJurdFld.getText());
            preparedStatement.setString(11, adressFld.getText());
            preparedStatement.setInt(12, Integer.valueOf(articleImpotFld.getText()));
            preparedStatement.setInt(13, contract.getId());

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddProprietorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void save(MouseEvent event) {

        if (contract == null || inspectionFld.getText() == null || recetteFld.getText() == null
                || anneeFld.getText() == null || designationFld.getText() == null
                || nisFld.getText() == null || nifFld.getText() == null || nisFld.getText() == null || wilayaFld.getText() == null
                || activityFld.getText() == null || codeActivityFld.getText() == null || FormJurdFld.getText() == null
                || adressFld.getText() == null || articleImpotFld.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Data");
            alert.showAndWait();

        } else {
            getQuery();
            insert();

        }
    }

    @FXML
    private void clean(MouseEvent event) {
    }

    @FXML
    private void selectContract(MouseEvent event) {
        contract = (Contract) contractTable.getSelectionModel().getSelectedItem();
        if (contract == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une ligne");
            alert.showAndWait();
        } else {
            ProprietaireFld.setText(contract.getProprietorName());
            nomBenefiFld.setText(contract.getBeneficiaryName());
            contractTypeFld.setText(contract.getContractType());
            startDateFld.setValue(contract.getDate().toLocalDate());
            finDateFld.setValue(contract.getEndDate().toLocalDate());
            montantFld.setText(String.valueOf(contract.getAmount()));
            numAcieFld.setText(contract.getSteelNumber());

        }
    }

    @FXML
    private void refreshContractTable() {
        try {
            contractsList.clear();

            query = "SELECT\n"
                    + "    contrat.id,\n"
                    + "    fiche_habitation.id_propr,\n"
                    + "    proprietaire.nom_prenom_or_RS,\n"
                    + "    beneficiaire.id,\n"
                    + "    fiche_habitation.id,\n"
                    + "    beneficiaire.nom_prenom_or_RS,\n"
                    + "    contrat.type_contr,\n"
                    + "    contrat.date,\n"
                    + "    contrat.date_fin,\n"
                    + "    contrat.montant,\n"
                    + "    contrat.n_acie\n"
                    + "FROM\n"
                    + "    contrat\n"
                    + "\n"
                    + "INNER JOIN beneficiaire ON beneficiaire.id = contrat.id_benef\n"
                    + "INNER JOIN fiche_habitation ON fiche_habitation.id = contrat.id_fiche_hab\n"
                    + "INNER JOIN proprietaire ON fiche_habitation.id_propr = proprietaire.id\n"
                    + "WHERE contrat.type_contr = \"Bail\"\n"
                    + "ORDER BY\n"
                    + "    contrat.id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                contractsList.add(new Contract(
                        resultSet.getInt("contrat.id"),
                        resultSet.getInt("fiche_habitation.id_propr"),
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
                contractTable.setItems(contractsList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadData() {

        connection = DbConnect.getConnect();
        refreshContractTable();

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
                                + "-glyph-size:18px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:18px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                contract = contractTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `contrat` WHERE id  =" + contract.getId();
                                connection = DbConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshContractTable();

                            } catch (SQLException ex) {
                                Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

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
        contractTable.setItems(contractsList);

    }

    @FXML
    private void addContactView(MouseEvent event) {
    }

}
