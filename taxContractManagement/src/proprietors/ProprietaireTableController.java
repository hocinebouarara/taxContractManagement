/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proprietors;

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
import models.Beneficiaire;
import models.Proprietor;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class ProprietaireTableController implements Initializable {

    @FXML
    private AnchorPane proprAnchor;
    @FXML
    private TableColumn<Proprietor, String> idCol;
    @FXML
    private TableColumn<Proprietor, String> nameCol;
    @FXML
    private TableColumn<Proprietor, String> operationCol;
    @FXML
    private TableColumn<Proprietor, String> checkCol;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Proprietor proprietor = null;
    Stage stage = null;

    ObservableList<Proprietor> proprietorsList = FXCollections.observableArrayList();
    @FXML
    private TableView<Proprietor> proprietaireTable;
    @FXML
    private TableColumn<Proprietor, String> birthDateCol;
    @FXML
    private TableColumn<Proprietor, String> communeCol;
    @FXML
    private TableColumn<Proprietor, String> wilayaCol;
    @FXML
    private TableColumn<Proprietor, String> prenom_pereCol;
    @FXML
    private TableColumn<Proprietor, String> nom_mereCol;
    @FXML
    private TableColumn<Proprietor, String> natCol;
    @FXML
    private TableColumn<Proprietor, String> adresseCol;
    @FXML
    private TableColumn<Proprietor, String> telephoneCol;

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

            proprietorsList.clear();

            query = "SELECT * FROM `proprietaire`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                proprietorsList.add(new Proprietor(
                        resultSet.getInt("id"),
                        resultSet.getString("nom_prenom_or_RS"),
                        resultSet.getDate("date_nss"),
                        resultSet.getString("commune"),
                        resultSet.getString("wilaya"),
                        resultSet.getString("pere"),
                        resultSet.getString("mere"),
                        resultSet.getString("nationalite"),
                        resultSet.getString("adress"),
                        resultSet.getString("telephone")
                ));
                proprietaireTable.setItems(proprietorsList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDate() {

        connection = DbConnect.getConnect();
        refreshTable();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        communeCol.setCellValueFactory(new PropertyValueFactory<>("commune"));
        wilayaCol.setCellValueFactory(new PropertyValueFactory<>("wilaya"));
        prenom_pereCol.setCellValueFactory(new PropertyValueFactory<>("prenom_pere"));
        nom_mereCol.setCellValueFactory(new PropertyValueFactory<>("nom_mere"));
        natCol.setCellValueFactory(new PropertyValueFactory<>("nationnalite"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse_domicile"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        //add cell of button edit 
        Callback<TableColumn<Proprietor, String>, TableCell<Proprietor, String>> cellFoctory = (TableColumn<Proprietor, String> param) -> {
            // make cell containing buttons
            final TableCell<Proprietor, String> cell = new TableCell<Proprietor, String>() {
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
                                        proprietor = proprietaireTable.getSelectionModel().getSelectedItem();
                                        query = "DELETE FROM `proprietaire` WHERE id  =" + proprietor.getId();
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

                            proprietor = proprietaireTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource(Links.ADDPROPIETORVIEW));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AddProprietorController addProprietorController = loader.getController();
                            addProprietorController.setUpdate(true);

                            addProprietorController.setTextField(proprietor.getId(), proprietor.getName(),
                                    proprietor.getBirthDate(), proprietor.getCommune(),
                                    proprietor.getWilaya(), proprietor.getPrenom_pere(), proprietor.getNom_mere(),
                                    proprietor.getNationnalite(), proprietor.getAdresse_domicile(), proprietor.getPhone());
                            
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
        proprietaireTable.setItems(proprietorsList);

    }

}
