/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import beneficiaries.AddBeneficiaryController;
import beneficiaries.BeneficiairesTableController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpres.DbConnect;
import helpres.Links;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.Property;
import proprietors.ProprietorsViewController;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class PropertiesTableController implements Initializable {

    @FXML
    private TableView<Property> propertiesTable;
    @FXML
    private TableColumn<Property, String> idCol;
    @FXML
    private TableColumn<Property, String> idProprCol;
    @FXML
    private TableColumn<Property, String> nomProprCol;
    @FXML
    private TableColumn<Property, String> articleCol;
    @FXML
    private TableColumn<Property, String> titreCol;
    @FXML
    private TableColumn<Property, String> communeCol;
    @FXML
    private TableColumn<Property, String> reuCol;
    @FXML
    private TableColumn<Property, String> acieCol;
    @FXML
    private TableColumn<Property, String> dateCol;
    @FXML
    private TableColumn<Property, String> operationCol;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Property property = null, proprty = null;
    public static Stage stage = null;

    ObservableList<Property> propertyList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Property, String> checkCol;
    @FXML
    private TableColumn<Property, String> birthdateCol;
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
    private TableColumn<Property, String> wilayaCol;
    @FXML
    private TableColumn<Property, String> originProprietyCol;
    @FXML
    private TableColumn<Property, String> adressPrincipaleCol;
    @FXML
    private TableColumn<Property, String> inspectionCol;
    @FXML
    private TableColumn<Property, String> usageCol;

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

    private void loadDate() {

        connection = DbConnect.getConnect();
        refreshTable();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idProprCol.setCellValueFactory(new PropertyValueFactory<>("id_propr"));
        nomProprCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        birthdateCol.setCellValueFactory(new PropertyValueFactory<>("proprBirth"));
        articleCol.setCellValueFactory(new PropertyValueFactory<>("article"));
        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        inspectionCol.setCellValueFactory(new PropertyValueFactory<>("inspection"));
        wilayaCol.setCellValueFactory(new PropertyValueFactory<>("wilaya"));
        communeCol.setCellValueFactory(new PropertyValueFactory<>("commune"));
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
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

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
                                + "-glyph-size:20px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
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
                                        refreshTable();
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
                            property = propertiesTable.getSelectionModel().getSelectedItem();

                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource(Links.ADDPROPERTYVIEW));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(PropertiesViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AddPropertyController addPropertyController = loader.getController();
                            addPropertyController.setUpdate(true);

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
        operationCol.setCellFactory(cellFoctory);

        propertiesTable.setItems(propertyList);

    }

}
