/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contract;

import beneficiaries.AddBeneficiaryController;
import beneficiaries.BeneficiairesTableController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpres.DbConnect;
import helpres.Links;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.Beneficiaire;
import models.Property;
import models.Proprietor;
import properties.AddPropertyController;
import properties.PropertiesViewController;
import proprietors.AddProprietorController;
import proprietors.ProprietorsViewController;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class AddContractController implements Initializable {

    @FXML
    private Button beneficiaryBtn;
    @FXML
    private Button proprietyBtn;
    @FXML
    private Button contractBtn;
    @FXML
    private AnchorPane beneficiaryAnchor;
    @FXML
    private TableView<Beneficiaire> beneficiairesTable;
    @FXML
    private TableColumn<Beneficiaire, String> idCol1;
    @FXML
    private TableColumn<Beneficiaire, String> nameCol1;
    @FXML
    private TableColumn<Beneficiaire, String> communeCol;
    @FXML
    private TableColumn<Beneficiaire, String> wilayaCol;
    @FXML
    private TableColumn<Beneficiaire, String> prenom_pereCol;
    @FXML
    private TableColumn<Beneficiaire, String> nom_mereCol;
    @FXML
    private TableColumn<Beneficiaire, String> natCol;
    @FXML
    private TableColumn<Beneficiaire, String> adresseCol;
    @FXML
    private TableColumn<Beneficiaire, String> dateCol1;
    @FXML
    private TableColumn<Beneficiaire, String> activitePrincCol;
    @FXML
    private TableColumn<Beneficiaire, String> adresseActPrincCol;
    @FXML
    private TableColumn<Beneficiaire, String> activiteSecnCol;
    @FXML
    private TableColumn<Beneficiaire, String> adresseActiviteSecnCol;
    @FXML
    private TableColumn<Beneficiaire, String> n_registreCol;
    @FXML
    private TableColumn<Beneficiaire, String> date_registreCol;
    @FXML
    private TableColumn<Beneficiaire, String> n_carteArtisanCol;
    @FXML
    private TableColumn<Beneficiaire, String> date_carteArCol;
    @FXML
    private TableColumn<Beneficiaire, String> n_argementCol;
    @FXML
    private TableColumn<Beneficiaire, String> agrementDateCol;
    @FXML
    private TableColumn<Beneficiaire, String> AutresCol;
    @FXML
    private TableColumn<Beneficiaire, String> optionCol;

    @FXML
    private TextField nomBenefiFld;
    @FXML
    private JFXDatePicker BenefbirthDateFld;
    @FXML
    private TextField birthPlace;
    @FXML
    private TextField nomParentsFld;
    @FXML
    private TextField activitePrincFld;
    @FXML
    private TextField adressBeneficiaryFld;

    @FXML
    private TableColumn<Beneficiaire, String> birthDate_Col;
    @FXML
    private AnchorPane proprietyAnchor;
    @FXML
    private TableView<Property> propertiesTable;
    @FXML
    private TableColumn<Property, String> idCol2;
    @FXML
    private TableColumn<Property, String> idProprCol;
    @FXML
    private TableColumn<Property, String> nomProprCol;
    @FXML
    private TableColumn<Property, String> birthdateCol;
    @FXML
    private TableColumn<Property, String> articleCol;
    @FXML
    private TableColumn<Property, String> inspectionCol;
    @FXML
    private TableColumn<Property, String> titreCol;
    @FXML
    private TableColumn<Property, String> reuCol;
    @FXML
    private TableColumn<Property, String> originProprietyCol;
    @FXML
    private TableColumn<Property, String> N_terrainCol;
    @FXML
    private TableColumn<Property, String> N_immeubleCol;
    @FXML
    private TableColumn<Property, String> N_etageCol;
    @FXML
    private TableColumn<Property, String> N_appartementCol;
    @FXML
    private TableColumn<Property, String> rezChausseeCol;
    @FXML
    private TableColumn<Property, String> NbrEtagesCol;
    @FXML
    private TableColumn<Property, String> NbrAppartementCol;
    @FXML
    private TableColumn<Property, String> typeImmbeubleCol;
    @FXML
    private TableColumn<Property, String> superficieTotalCol;
    @FXML
    private TableColumn<Property, String> superficieBatieCol;
    @FXML
    private TableColumn<Property, String> superficieNonBatieCol;
    @FXML
    private TableColumn<Property, String> dateAchevCol;
    @FXML
    private TableColumn<Property, String> usageCol;
    @FXML
    private TableColumn<Property, String> adressPrincipaleCol;
    @FXML
    private TableColumn<Property, String> dateCol2;
    @FXML
    private TableColumn<Property, String> acieCol;
    @FXML
    private TableColumn<Property, String> operationCol1;
    @FXML
    private TextField proprietorNameFld;
    @FXML
    private JFXDatePicker ProprietorbirthDateFld;
    @FXML
    private TextField articleFld;
    @FXML
    private TextField titreFld;
    @FXML
    private TextField adressFld;
    @FXML
    private TextField acieFld;
    @FXML
    private JFXDatePicker acieDateFld;
    @FXML
    private AnchorPane contarctDetailsAnchor;
    @FXML
    private JFXComboBox<String> contractTypeCombo;
    @FXML
    private JFXDatePicker startDateFld;
    @FXML
    private JFXDatePicker endDateFld;
    @FXML
    private TextField montantContrFld;
    @FXML
    private TextField nbrAcieFld;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Beneficiaire beneficiaire = null;
    private boolean update = false;
    int propertyId, beneficiaireId;
    String type = null;

    boolean ground_floor = false;

    ObservableList<Property> propertyList = FXCollections.observableArrayList();
    Property property = null;
    ObservableList<Beneficiaire> beneficiairesList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Property, String> communeCol1;
    @FXML
    private TableColumn<Property, String> wilayaCol1;
    @FXML
    private JFXComboBox<String> periodeTypeCombo;
    @FXML
    private Button saveBtn;
    @FXML
    private Button viderBtn;
    @FXML
    private Button continerBtn2;
    @FXML
    private Button viderBtn3;
    @FXML
    private FontAwesomeIconView selectBtn;
    @FXML
    private FontAwesomeIconView refreshBtn;
    @FXML
    private FontAwesomeIconView ajouterBtn;
    @FXML
    private Button continuer3;
    @FXML
    private Button viderBtn5;
    @FXML
    private Label proprNameText;
    @FXML
    private Label articleTxt;
    @FXML
    private Label titreTxt;
    @FXML
    private Label adressTxt;
    @FXML
    private Label numAcieTxt;
    @FXML
    private Label dateAcieTxt;
    @FXML
    private Label typeContratTxt;
    @FXML
    private Label typePeriodeTxt;
    @FXML
    private Label dateDebutTxt;
    @FXML
    private Label dateFinTxt;
    @FXML
    private Label montantTxt;
    @FXML
    private Label nunAcieContratTxt;
    @FXML
    private JFXButton RevenirBtn;
    @FXML
    private JFXButton Valider;
    @FXML
    private Label dateNssProprTxt;
    @FXML
    private Label dateNssLocataireTxt;
    @FXML
    private Label lieuNssTxt;
    @FXML
    private Label locatairePereTxt;
    @FXML
    private Label locataireAdressTxt;
    @FXML
    private Label activityTxt;
    @FXML
    private Label locataireNameText;
    @FXML
    private AnchorPane validAnchor;
    @FXML
    private FontAwesomeIconView propSelectIcon;
    @FXML
    private FontAwesomeIconView propRefreshIcon;
    @FXML
    private FontAwesomeIconView propAddIcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        validAnchor.setVisible(false);
        Valider.setDisable(true);
        RevenirBtn.setDisable(true);

        getProprietyView();
        loadBeneficiaryData();
        loadPropertyDate();
        contractTypeCombo.getItems().addAll("Vente", "Location", "Donation", "échange");
        periodeTypeCombo.getItems().addAll("Payer en une fois", "Annuel", "trimestrielle", "Mensuel");

        property = null;
        beneficiaire = null;
        update = false;
        continerBtn2.setTooltip(new Tooltip("Passer à l'étape suivante"));
        continuer3.setTooltip(new Tooltip("Passer à l'étape suivante"));
        viderBtn.setTooltip(new Tooltip("Vider les champs "));
        viderBtn3.setTooltip(new Tooltip("Vider les champs "));
        viderBtn5.setTooltip(new Tooltip("Vider les champs "));
        saveBtn.setTooltip(new Tooltip("La sauvegarde des données"));
        proprietyBtn.setTooltip(new Tooltip("Informations sur la propriété "));
        beneficiaryBtn.setTooltip(new Tooltip("Informations sur la locataire "));
        contractBtn.setTooltip(new Tooltip("Informations sur le contrat "));

        Tooltip tooltip1 = new Tooltip();
        tooltip1.setGraphic(new FontAwesomeIconView());
        Tooltip.install(selectBtn, new Tooltip("Selectionner un locataire"));

        Tooltip tooltip2 = new Tooltip();
        tooltip2.setGraphic(new FontAwesomeIconView());
        Tooltip.install(ajouterBtn, new Tooltip("ajouter des nouveaux locataires "));

        Tooltip tooltip3 = new Tooltip();
        tooltip3.setGraphic(new FontAwesomeIconView());
        Tooltip.install(refreshBtn, new Tooltip("Recharger la liste des locataires"));

        Tooltip tooltip11 = new Tooltip();
        tooltip11.setGraphic(new FontAwesomeIconView());
        Tooltip.install(propSelectIcon, new Tooltip("Selectionner une propriétés"));

        Tooltip tooltip22 = new Tooltip();
        tooltip22.setGraphic(new FontAwesomeIconView());
        Tooltip.install(propAddIcon, new Tooltip("ajouter des nouveaux propriétés "));

        Tooltip tooltip33 = new Tooltip();
        tooltip33.setGraphic(new FontAwesomeIconView());
        Tooltip.install(propRefreshIcon, new Tooltip("Recharger la liste des propriétés"));

        RevenirBtn.setTooltip(new Tooltip("Revenir à l'étape précédente"));
        RevenirBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            validAnchor.setVisible(false);
            Valider.setDisable(true);
            RevenirBtn.setDisable(true);
        });

        Valider.setTooltip(new Tooltip("Confirmer le processus de sauvegarde "));
        Valider.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            connection = DbConnect.getConnect();

            if (property == null || beneficiaire == null || contractTypeCombo.getValue() == null
                    || startDateFld.getValue() == null || endDateFld.getValue() == null
                    || startDateFld.getValue() == null || montantContrFld.getText() == null
                    || nbrAcieFld.getText() == null || periodeTypeCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir toutes les données");
                alert.showAndWait();

                getProprietyView();

            } else {
                getQuery();
                insert();
                Alert alerte = new Alert(Alert.AlertType.INFORMATION);
                alerte.setHeaderText(null);
                alerte.setContentText("Ajouté avec succès");
                alerte.showAndWait();
                getProprietyView();

                Valider.setDisable(true);
                RevenirBtn.setDisable(true);
                validAnchor.setVisible(false);

            }
        });

    }

    @FXML
    private void getBeneficiaryView(MouseEvent event) {
        beneficiaryBtn.setStyle("-fx-background-color:white;-fx-border-width:1.6px;-fx-border-color:#123456;-fx-border-radius:5px;");
        proprietyBtn.setStyle("-fx-background-color:#DDD;");
        contractBtn.setStyle("-fx-background-color:#DDD;");
        proprietyAnchor.setVisible(false);
        beneficiaryAnchor.setVisible(true);
        contarctDetailsAnchor.setVisible(false);

    }

    @FXML
    private void getProprietyView() {
        proprietyBtn.setStyle("-fx-background-color:white;-fx-border-width:1.6px;-fx-border-color:#123456;-fx-border-radius:5px;");
        beneficiaryBtn.setStyle("-fx-background-color:#DDD;");
        contractBtn.setStyle("-fx-background-color:#DDD;");
        proprietyAnchor.setVisible(true);
        beneficiaryAnchor.setVisible(false);
        contarctDetailsAnchor.setVisible(false);

    }

    @FXML
    private void getContractDetailView(MouseEvent event) {
        contractBtn.setStyle("-fx-background-color:white;-fx-border-width:1.6px;-fx-border-color:#123456;-fx-border-radius:5px;");
        beneficiaryBtn.setStyle("-fx-background-color:#DDD;");
        proprietyBtn.setStyle("-fx-background-color:#DDD;");
        contarctDetailsAnchor.setVisible(true);
        beneficiaryAnchor.setVisible(false);
        proprietyAnchor.setVisible(false);
    }

    @FXML
    private void refreshProprietyTable() {
        try {
            propertyList.clear();

            query = "SELECT\n"
                    + "    fiche_habitation.id,\n"
                    + "    fiche_habitation.nbr_article,\n"
                    + "    fiche_habitation.inspection,\n"
                    + "    fiche_habitation.wilaya,\n"
                    + "    fiche_habitation.commune,\n"
                    + "    fiche_habitation.reu,\n"
                    + "    fiche_habitation.origin_propriete,\n"
                    + "    fiche_habitation.n_terrain,\n"
                    + "    fiche_habitation.n_immeuble,\n"
                    + "    fiche_habitation.n_etage,\n"
                    + "    fiche_habitation.n_appartement,\n"
                    + "    fiche_habitation.rez_chaussee,\n"
                    + "    fiche_habitation.nbr_etage,\n"
                    + "    fiche_habitation.nbr_apparemment,\n"
                    + "    fiche_habitation.type_immbeuble,\n"
                    + "    fiche_habitation.superficie_tot,\n"
                    + "    fiche_habitation.superficie_batie,\n"
                    + "    fiche_habitation.superficie_non_batie,\n"
                    + "    fiche_habitation.date_achev,\n"
                    + "    fiche_habitation.usage,\n"
                    + "    fiche_habitation.adresse_prcpl,\n"
                    + "    proprietaire.id,\n"
                    + "    proprietaire.nom_prenom_or_RS,\n"
                    + "    proprietaire.date_nss,\n"
                    + "    fiche_habitation.titre_propriete,\n"
                    + "    fiche_habitation.n_acie,\n"
                    + "    fiche_habitation.date\n"
                    + "FROM\n"
                    + "    fiche_habitation\n"
                    + "INNER JOIN proprietaire ON proprietaire.id = fiche_habitation.id_propr\n"
                    + "ORDER BY  fiche_habitation.id;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                propertyList.add(new Property(
                        resultSet.getInt("fiche_habitation.id"),
                        resultSet.getInt("proprietaire.id"),
                        resultSet.getString("proprietaire.nom_prenom_or_RS"),
                        resultSet.getDate("proprietaire.date_nss"),
                        resultSet.getString("fiche_habitation.nbr_article"),
                        resultSet.getString("fiche_habitation.titre_propriete"),
                        resultSet.getString("fiche_habitation.commune"),
                        resultSet.getString("fiche_habitation.inspection"),
                        resultSet.getString("fiche_habitation.wilaya"),
                        resultSet.getString("fiche_habitation.reu"),
                        resultSet.getString("fiche_habitation.origin_propriete"),
                        resultSet.getString("fiche_habitation.n_terrain"),
                        resultSet.getString("fiche_habitation.n_immeuble"),
                        resultSet.getString("fiche_habitation.n_etage"),
                        resultSet.getString("fiche_habitation.n_appartement"),
                        resultSet.getString("fiche_habitation.rez_chaussee"),
                        resultSet.getString("fiche_habitation.nbr_etage"),
                        resultSet.getString("fiche_habitation.nbr_apparemment"),
                        resultSet.getString("fiche_habitation.type_immbeuble"),
                        resultSet.getFloat("fiche_habitation.superficie_tot"),
                        resultSet.getFloat("fiche_habitation.superficie_batie"),
                        resultSet.getFloat("fiche_habitation.superficie_non_batie"),
                        resultSet.getDate("fiche_habitation.date_achev"),
                        resultSet.getString("fiche_habitation.usage"),
                        resultSet.getString("fiche_habitation.adresse_prcpl"),
                        resultSet.getString("fiche_habitation.n_acie"),
                        resultSet.getDate("fiche_habitation.date")));
                propertiesTable.setItems(propertyList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadPropertyDate() {

        connection = DbConnect.getConnect();
        refreshProprietyTable();
        idCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        idProprCol.setCellValueFactory(new PropertyValueFactory<>("id_propr"));
        nomProprCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        birthdateCol.setCellValueFactory(new PropertyValueFactory<>("proprBirth"));
        articleCol.setCellValueFactory(new PropertyValueFactory<>("article"));
        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        inspectionCol.setCellValueFactory(new PropertyValueFactory<>("inspection"));
        wilayaCol1.setCellValueFactory(new PropertyValueFactory<>("wilaya"));
        communeCol1.setCellValueFactory(new PropertyValueFactory<>("commune"));
        reuCol.setCellValueFactory(new PropertyValueFactory<>("reu"));
        originProprietyCol.setCellValueFactory(new PropertyValueFactory<>("origin_propriete"));
        N_terrainCol.setCellValueFactory(new PropertyValueFactory<>("n_terrain"));
        N_immeubleCol.setCellValueFactory(new PropertyValueFactory<>("n_immeuble"));
        N_etageCol.setCellValueFactory(new PropertyValueFactory<>("n_etage"));
        N_appartementCol.setCellValueFactory(new PropertyValueFactory<>("n_appartement"));
        rezChausseeCol.setCellValueFactory(new PropertyValueFactory<>("rez_chaussee"));
        NbrEtagesCol.setCellValueFactory(new PropertyValueFactory<>("nbr_etage"));
        NbrAppartementCol.setCellValueFactory(new PropertyValueFactory<>("nbr_apparemment"));
        typeImmbeubleCol.setCellValueFactory(new PropertyValueFactory<>("type_immbeuble"));
        superficieTotalCol.setCellValueFactory(new PropertyValueFactory<>("superficie_tot"));
        superficieBatieCol.setCellValueFactory(new PropertyValueFactory<>("superficie_batie"));
        superficieNonBatieCol.setCellValueFactory(new PropertyValueFactory<>("superficie_non_batie"));
        dateAchevCol.setCellValueFactory(new PropertyValueFactory<>("date_achev"));
        usageCol.setCellValueFactory(new PropertyValueFactory<>("usage"));
        adressPrincipaleCol.setCellValueFactory(new PropertyValueFactory<>("adresse_prcpl"));
        acieCol.setCellValueFactory(new PropertyValueFactory<>("acie"));
        dateCol2.setCellValueFactory(new PropertyValueFactory<>("date"));

        //add cell of button edit 
        Callback<TableColumn<Property, String>, TableCell<Property, String>> cellFoctory = (TableColumn<Property, String> param) -> {
            // make cell containing buttons
            final TableCell<Property, String> cell = new TableCell<Property, String>() {
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
                                + "-glyph-size:14px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:14px;"
                                + "-fx-fill:#00E676;"
                        );
                        Tooltip tooltip = new Tooltip();
                        tooltip.setGraphic(new FontAwesomeIconView());
                        Tooltip.install(deleteIcon, new Tooltip("Supprimer cet élément"));
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            ButtonType ok = new ButtonType("D'accord");
                            ButtonType cancel = new ButtonType("Annuler");
                            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Promote pawn to:", ok, cancel);
                            a.setResizable(true);
                            a.setContentText("Voulez-vous supprimer cet élément");
                            a.showAndWait().ifPresent(response -> {
                                if (response == ok) {
                                    try {
                                        // promote to queen...
                                        property = propertiesTable.getSelectionModel().getSelectedItem();
                                        query = "DELETE FROM `fiche_habitation` WHERE id = '" + property.getId() + "'"
                                                + " and id_propr = '" + property.getId_propr() + "'";
                                        connection = DbConnect.getConnect();
                                        preparedStatement = connection.prepareStatement(query);
                                        preparedStatement.execute();
                                        refreshProprietyTable();
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setHeaderText(null);
                                        alert.setContentText("Supprimé avec succès");
                                        alert.showAndWait();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(BeneficiairesTableController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else if (response == cancel) {
                                    // promote to rook...
                                }
                            });
                        });
                        Tooltip tooltip1 = new Tooltip();
                        tooltip1.setGraphic(new FontAwesomeIconView());
                        Tooltip.install(editIcon, new Tooltip("Modifier cet élément"));
                        editIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                property = propertiesTable.getSelectionModel().getSelectedItem();

                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource(Links.ADDPROPERTYVIEW));
                                try {
                                    loader.load();
                                } catch (IOException ex) {
                                    Logger.getLogger(PropertiesViewController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                AddPropertyController addPropertyController = loader.getController();
                                addPropertyController.setUpdate(update);
                                addPropertyController.setTextFields(property.getId(), property.getId_propr(), property.getNom(), property.getProprBirth(),
                                        property.getArticle(), property.getTitre(), property.getCommune(), property.getInspection(),
                                        property.getWilaya(), property.getReu(), property.getOrigin_propriete(), property.getN_terrain(),
                                        property.getN_immeuble(), property.getN_etage(), property.getN_appartement(), property.getRez_chaussee(),
                                        property.getNbr_etage(), property.getNbr_apparemment(), property.getType_immbeuble(), property.getSuperficie_tot(),
                                        property.getSuperficie_batie(), property.getSuperficie_non_batie(), property.getDate_achev(), property.getUsage(),
                                        property.getAdresse_prcpl(), property.getAcie(), property.getDate()
                                );
                                Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();
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
        operationCol1.setCellFactory(cellFoctory);
        propertiesTable.setItems(propertyList);

    }

    @FXML
    private void refreshBeneficiaryTable() {
        try {
            beneficiairesList.clear();

            query = "SELECT * FROM `beneficiaire`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                beneficiairesList.add(new Beneficiaire(
                        resultSet.getInt("id"),
                        resultSet.getString("nom_prenom_or_RS"),
                        resultSet.getDate("date_nss"),
                        resultSet.getString("commune"),
                        resultSet.getString("wilaya"),
                        resultSet.getString("prenom_pere"),
                        resultSet.getString("nom_mere"),
                        resultSet.getString("nationalite"),
                        resultSet.getString("adresse_domicile"),
                        resultSet.getString("activite_prcpl"),
                        resultSet.getString("adresse_act_prcpl"),
                        resultSet.getString("activite_sec"),
                        resultSet.getString("adresse_act_sec"),
                        resultSet.getString("n_register_comrc"),
                        resultSet.getDate("date_registre_c"),
                        resultSet.getString("n_cart_artisan"),
                        resultSet.getDate("date_carte_ar"),
                        resultSet.getString("n_agrement"),
                        resultSet.getDate("date_agrement"),
                        resultSet.getString("autres"),
                        resultSet.getDate("date")
                ));
                beneficiairesTable.setItems(beneficiairesList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadBeneficiaryData() {

        connection = DbConnect.getConnect();
        refreshBeneficiaryTable();
        idCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthDate_Col.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        communeCol.setCellValueFactory(new PropertyValueFactory<>("commune"));
        wilayaCol.setCellValueFactory(new PropertyValueFactory<>("wilaya"));
        prenom_pereCol.setCellValueFactory(new PropertyValueFactory<>("prenom_pere"));
        nom_mereCol.setCellValueFactory(new PropertyValueFactory<>("nom_mere"));
        natCol.setCellValueFactory(new PropertyValueFactory<>("nationnalite"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse_domicile"));
        activitePrincCol.setCellValueFactory(new PropertyValueFactory<>("activite_prcpl"));
        adresseActPrincCol.setCellValueFactory(new PropertyValueFactory<>("adresse_act_prcpl"));
        activiteSecnCol.setCellValueFactory(new PropertyValueFactory<>("activite_sec"));
        adresseActiviteSecnCol.setCellValueFactory(new PropertyValueFactory<>("adresse_act_sec"));
        n_registreCol.setCellValueFactory(new PropertyValueFactory<>("n_register_comrc"));
        date_registreCol.setCellValueFactory(new PropertyValueFactory<>("date_registre_c"));
        n_carteArtisanCol.setCellValueFactory(new PropertyValueFactory<>("n_cart_artisan"));
        date_carteArCol.setCellValueFactory(new PropertyValueFactory<>("date_carte_ar"));
        n_argementCol.setCellValueFactory(new PropertyValueFactory<>("n_agrement"));
        agrementDateCol.setCellValueFactory(new PropertyValueFactory<>("date_agrement"));
        AutresCol.setCellValueFactory(new PropertyValueFactory<>("autres"));
        dateCol1.setCellValueFactory(new PropertyValueFactory<>("date"));

        //add cell of button edit 
        Callback<TableColumn<Beneficiaire, String>, TableCell<Beneficiaire, String>> cellFoctory = (TableColumn<Beneficiaire, String> param) -> {
            // make cell containing buttons
            final TableCell<Beneficiaire, String> cell = new TableCell<Beneficiaire, String>() {
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
                                + "-glyph-size:14px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:14px;"
                                + "-fx-fill:#00E676;"
                        );
                        Tooltip tooltip = new Tooltip();
                        tooltip.setGraphic(new FontAwesomeIconView());
                        Tooltip.install(deleteIcon, new Tooltip("Supprimer cet élément"));
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            ButtonType ok = new ButtonType("D'accord");
                            ButtonType cancel = new ButtonType("Annuler");
                            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Promote pawn to:", ok, cancel);
                            a.setResizable(true);
                            a.setContentText("Voulez-vous supprimer cet élément");
                            a.showAndWait().ifPresent(response -> {
                                if (response == ok) {
                                    try {
                                        // promote to queen...
                                        beneficiaire = beneficiairesTable.getSelectionModel().getSelectedItem();
                                        query = "DELETE FROM `beneficiaire` WHERE id  =" + beneficiaire.getId();
                                        connection = DbConnect.getConnect();
                                        preparedStatement = connection.prepareStatement(query);
                                        preparedStatement.execute();
                                        refreshBeneficiaryTable();
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setHeaderText(null);
                                        alert.setContentText("Supprimé avec succès");
                                        alert.showAndWait();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(BeneficiairesTableController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else if (response == cancel) {
                                    // promote to rook...
                                }
                            });

                        });

                        Tooltip tooltip1 = new Tooltip();
                        tooltip1.setGraphic(new FontAwesomeIconView());
                        Tooltip.install(editIcon, new Tooltip("Modifier cet élément"));
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            beneficiaire = beneficiairesTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource(Links.ADDBENEFICIAIREVIEW));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AddBeneficiaryController addBeneficiaryController = loader.getController();
                            addBeneficiaryController.setUpdate(true);
                            addBeneficiaryController.setTextFields(beneficiaire.getId(), beneficiaire.getName(),
                                    beneficiaire.getDate(), beneficiaire.getCommune(),
                                    beneficiaire.getWilaya(), beneficiaire.getPrenom_pere(), beneficiaire.getNom_mere(),
                                    beneficiaire.getNationnalite(), beneficiaire.getAdresse_domicile(), beneficiaire.getActivite_prcpl(),
                                    beneficiaire.getAdresse_act_prcpl(), beneficiaire.getActivite_sec(),
                                    beneficiaire.getAdresse_act_sec(), beneficiaire.getN_register_comrc(),
                                    beneficiaire.getDate_registre_c(), beneficiaire.getN_cart_artisan(),
                                    beneficiaire.getDate_carte_ar(), beneficiaire.getN_agrement(), beneficiaire.getDate_agrement(),
                                    beneficiaire.getAutres(), beneficiaire.getDate()
                            );
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

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
        optionCol.setCellFactory(cellFoctory);
        beneficiairesTable.setItems(beneficiairesList);

    }

    @FXML
    private void clean(MouseEvent event) {
    }

    @FXML
    private void selectBeneficiary(MouseEvent event) {
        beneficiaire = (Beneficiaire) beneficiairesTable.getSelectionModel().getSelectedItem();
        if (beneficiaire == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une ligne");
            alert.showAndWait();
        } else {
            nomBenefiFld.setText(beneficiaire.getName());
            BenefbirthDateFld.setValue(beneficiaire.getDate().toLocalDate());
            birthPlace.setText(beneficiaire.getCommune() + " wilaya de " + beneficiaire.getWilaya());
            nomParentsFld.setText(beneficiaire.getPrenom_pere() + " et " + beneficiaire.getNom_mere());
            adressBeneficiaryFld.setText(beneficiaire.getAdresse_domicile());
            activitePrincFld.setText(beneficiaire.getActivite_prcpl());
            beneficiaireId = beneficiaire.getId();

        }
    }

    @FXML
    private void addBeneficiary(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Links.ADDBENEFICIAIREVIEW));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void selectPropriety(MouseEvent event) {
        property = (Property) propertiesTable.getSelectionModel().getSelectedItem();
        if (property == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une ligne");
            alert.showAndWait();
        } else {
            proprietorNameFld.setText(property.getNom());
            ProprietorbirthDateFld.setValue(property.getProprBirth().toLocalDate());
            articleFld.setText(property.getArticle());
            titreFld.setText(property.getTitre());
            adressFld.setText(property.getAdresse_prcpl());
            acieFld.setText(property.getAcie());
            acieDateFld.setValue(property.getDate().toLocalDate());
            propertyId = property.getId();

        }
    }

    @FXML
    private void addProprietyView(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Links.ADDPROPERTYVIEW));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO `contrat`(`id_benef`, `id_fiche_hab`, `type_contr`, `date`, `date_fin`, `montant`, `n_acie`, `periodes_imposition`) VALUES (?,?,?,?,?,?,?,?)";

        } else {

        }

    }

    private void insert() {
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, beneficiaire.getId());
            preparedStatement.setInt(2, property.getId());
            preparedStatement.setString(3, contractTypeCombo.getValue());
            preparedStatement.setString(4, String.valueOf(startDateFld.getValue()));
            preparedStatement.setString(5, String.valueOf(endDateFld.getValue()));
            preparedStatement.setFloat(6, Float.valueOf(montantContrFld.getText()));
            preparedStatement.setString(7, nbrAcieFld.getText());
            preparedStatement.setString(8, periodeTypeCombo.getValue());

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddProprietorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void save(MouseEvent event) {

        connection = DbConnect.getConnect();

        if (property == null || beneficiaire == null || contractTypeCombo.getValue() == null
                || startDateFld.getValue() == null || endDateFld.getValue() == null
                || startDateFld.getValue() == null || montantContrFld.getText() == null
                || nbrAcieFld.getText() == null || periodeTypeCombo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir toutes les données");
            alert.showAndWait();

            getProprietyView();

        } else {
            getProprietyView();
            Valider.setDisable(false);
            RevenirBtn.setDisable(false);
            validAnchor.setVisible(true);

            proprNameText.setText(property.getNom());
            dateNssProprTxt.setText(property.getProprBirth().toString());
            articleTxt.setText(property.getArticle());
            titreTxt.setText(property.getTitre());
            adressTxt.setText(property.getAdresse_prcpl());
            numAcieTxt.setText(property.getAcie());
            dateAcieTxt.setText(property.getDate().toString());

            locataireNameText.setText(beneficiaire.getName());
            dateNssLocataireTxt.setText(beneficiaire.getBirthDate().toString());
            lieuNssTxt.setText(beneficiaire.getCommune());
            locatairePereTxt.setText(beneficiaire.getPrenom_pere());
            locataireAdressTxt.setText(beneficiaire.getAdresse_domicile());
            activityTxt.setText(beneficiaire.getActivite_prcpl());

            typeContratTxt.setText(contractTypeCombo.getValue());
            typePeriodeTxt.setText(periodeTypeCombo.getValue());
            dateDebutTxt.setText(startDateFld.getValue().toString());
            dateFinTxt.setText(endDateFld.getValue().toString());
            montantTxt.setText(montantContrFld.getText());
            nunAcieContratTxt.setText(acieFld.getText());

        }
    }

}
