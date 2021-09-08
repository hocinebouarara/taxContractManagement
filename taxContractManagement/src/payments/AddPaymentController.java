/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

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
import models.Controle;
import proprietors.ProprietorsViewController;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class AddPaymentController implements Initializable {

    @FXML
    private Button ficheControleBtn;
    @FXML
    private Button paymentBtn;
    @FXML
    private Button ScanBtn;
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
    private TableView<Controle> controleTable;
    @FXML
    private TableColumn<Controle, String> idCol;
    @FXML
    private TableColumn<Controle, String> idContractCol;
    @FXML
    private TableColumn<Controle, String> idHabitCol;
    @FXML
    private TableColumn<Controle, String> idProprCol;
    @FXML
    private TableColumn<Controle, String> proprNameCol;
    @FXML
    private TableColumn<Controle, String> idBenefCol;
    @FXML
    private TableColumn<Controle, String> beneNameCol;
    @FXML
    private TableColumn<Controle, String> inspectionCol;
    @FXML
    private TableColumn<Controle, String> recetteCol;
    @FXML
    private TableColumn<Controle, String> anneeCol;
    @FXML
    private TableColumn<Controle, String> designationCol;
    @FXML
    private TableColumn<Controle, String> nisCol;
    @FXML
    private TableColumn<Controle, String> nifCol;
    @FXML
    private TableColumn<Controle, String> wilayaCol;
    @FXML
    private TableColumn<Controle, String> activiteCol;
    @FXML
    private TableColumn<Controle, String> codeActCol;
    @FXML
    private TableColumn<Controle, String> formJuridiqueCol;
    @FXML
    private TableColumn<Controle, String> adresseCol;
    @FXML
    private TableColumn<Controle, String> articleImpotCol;
    @FXML
    private TableColumn<Controle, String> actionsCol;
    @FXML
    private TextField ProprietaireFld;
    @FXML
    private TextField nomBenefiFld;
    @FXML
    private TextField activiteFld;
    @FXML
    private TextField articleFld;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Controle controle = null;

    ObservableList<Controle> controlesList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
    }

    private void refreshTable() {
        try {
            controlesList.clear();

            query = "SELECT\n"
                    + "    fiche_de_control.id,\n"
                    + "    contrat.id,\n"
                    + "    contrat.id_fiche_hab,\n"
                    + "    proprietaire.id,\n"
                    + "    proprietaire.nom_prenom_or_RS,\n"
                    + "    contrat.id_benef,\n"
                    + "    beneficiaire.nom_prenom_or_RS,\n"
                    + "    fiche_de_control.inscpection,\n"
                    + "    fiche_de_control.Recette,\n"
                    + "    fiche_de_control.Annee,\n"
                    + "    fiche_de_control.Designation,\n"
                    + "    fiche_de_control.NiS,\n"
                    + "    fiche_de_control.NIF,\n"
                    + "    fiche_de_control.Wilaya,\n"
                    + "    fiche_de_control.Activity,\n"
                    + "    fiche_de_control.Code_d_activity,\n"
                    + "    fiche_de_control.Forme_Juridique,\n"
                    + "    fiche_de_control.Adress,\n"
                    + "    fiche_de_control.Article_imposition\n"
                    + "FROM\n"
                    + "    fiche_de_control\n"
                    + "INNER JOIN contrat ON contrat.id = fiche_de_control.id_fiscal\n"
                    + "INNER JOIN beneficiaire ON beneficiaire.id = contrat.id_benef\n"
                    + "INNER JOIN fiche_habitation ON fiche_habitation.id = contrat.id_fiche_hab\n"
                    + "INNER JOIN proprietaire ON fiche_habitation.id_propr = proprietaire.id\n"
                    + "ORDER by \n"
                    + "fiche_de_control.id;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                controlesList.add(new Controle(
                        resultSet.getInt("fiche_de_control.id"),
                        resultSet.getInt("contrat.id"),
                        resultSet.getInt("contrat.id_fiche_hab"),
                        resultSet.getInt("proprietaire.id"),
                        resultSet.getString("proprietaire.nom_prenom_or_RS"),
                        resultSet.getInt("proprietaire.id"),
                        resultSet.getString("beneficiaire.nom_prenom_or_RS"),
                        resultSet.getString("fiche_de_control.inscpection"),
                        resultSet.getString("fiche_de_control.recette"),
                        resultSet.getString("fiche_de_control.annee"),
                        resultSet.getString("fiche_de_control.designation"),
                        resultSet.getInt("fiche_de_control.NiS"), resultSet.getInt("fiche_de_control.NIF"),
                        resultSet.getString("fiche_de_control.wilaya"),
                        resultSet.getString("fiche_de_control.activity"),
                        resultSet.getInt("fiche_de_control.Code_d_activity"),
                        resultSet.getString("fiche_de_control.Forme_Juridique"),
                        resultSet.getString("fiche_de_control.adress"),
                        resultSet.getInt("fiche_de_control.Article_imposition")
                ));
                controleTable.setItems(controlesList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadData() {

        connection = DbConnect.getConnect();
        refreshTable();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idContractCol.setCellValueFactory(new PropertyValueFactory<>("contractId"));
        idHabitCol.setCellValueFactory(new PropertyValueFactory<>("ficheHabId"));
        idProprCol.setCellValueFactory(new PropertyValueFactory<>("proprId"));
        proprNameCol.setCellValueFactory(new PropertyValueFactory<>("proprName"));
        idBenefCol.setCellValueFactory(new PropertyValueFactory<>("benefID"));
        beneNameCol.setCellValueFactory(new PropertyValueFactory<>("benefName"));
        inspectionCol.setCellValueFactory(new PropertyValueFactory<>("inspection"));
        recetteCol.setCellValueFactory(new PropertyValueFactory<>("recette"));
        anneeCol.setCellValueFactory(new PropertyValueFactory<>("annee"));
        designationCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        nisCol.setCellValueFactory(new PropertyValueFactory<>("nis"));
        nifCol.setCellValueFactory(new PropertyValueFactory<>("nif"));
        wilayaCol.setCellValueFactory(new PropertyValueFactory<>("wilaya"));
        activiteCol.setCellValueFactory(new PropertyValueFactory<>("activite"));
        codeActCol.setCellValueFactory(new PropertyValueFactory<>("codeActivite"));
        formJuridiqueCol.setCellValueFactory(new PropertyValueFactory<>("formJuridique"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("Adress"));
        articleImpotCol.setCellValueFactory(new PropertyValueFactory<>("articleImpots"));

        //add cell of button edit 
        Callback<TableColumn<Controle, String>, TableCell<Controle, String>> cellFoctory = (TableColumn<Controle, String> param) -> {
            // make cell containing buttons
            final TableCell<Controle, String> cell = new TableCell<Controle, String>() {
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
        actionsCol.setCellFactory(cellFoctory);
        controleTable.setItems(controlesList);

    }

    @FXML
    private void getFicheControllerView(MouseEvent event) {
    }

    @FXML
    private void getpaymentView(MouseEvent event) {
    }

    @FXML
    private void getScanView(MouseEvent event) {
    }

    @FXML
    private void save(MouseEvent event) {
    }

    @FXML
    private void clean(MouseEvent event) {
    }

    @FXML
    private void selectFicheController(MouseEvent event) {
    }

    @FXML
    private void refreshFicheContTable(MouseEvent event) {
    }

    @FXML
    private void addFicheControllerView(MouseEvent event) {
    }

}
