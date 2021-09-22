/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.Beneficiaire;
import models.Property;
import models.Proprietor;
import proprietors.AddProprietorController;
import proprietors.ProprietorsViewController;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class AddPropertyController implements Initializable {

    @FXML
    private TextField articleFld;
    @FXML
    private TextField wilayaFld;
    @FXML
    private TextField communeFld;
    @FXML
    private TextField reuFld;
    @FXML
    private TextField inspectionFld;
    @FXML
    private TextField nTerrainFld;
    @FXML
    private TextField nImmeubleFld;
    @FXML
    private JFXRadioButton rezChaFld;
    @FXML
    private TextField nEtageFld;
    @FXML
    private TextField nomProprFld;
    @FXML
    private TextField adresseProprFld;
    @FXML
    private JFXComboBox<String> orignPropCombo;
    @FXML
    private TextField titreFld;
    @FXML
    private TextField acieFld;
    @FXML
    private JFXDatePicker dateAcieFld;
    @FXML
    private JFXRadioButton collectifBtn;
    @FXML
    private JFXRadioButton induvidlBtn;
    @FXML
    private TextField superTotFld;
    @FXML
    private TextField SuperBatieFld;
    @FXML
    private TextField superNonBatieFLd;
    @FXML
    private TextField nbrPiecesFld;
    @FXML
    private TextField nbrEtageFld;
    @FXML
    private JFXDatePicker dateAchevFld;
    @FXML
    private JFXComboBox<String> usageCombo;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Beneficiaire beneficiaire = null;
    private boolean update;
    int propertyId, proprietorId = 1;
    String type = null;
    boolean ground_floor = false;
    int position = 0;

    Proprietor proprietor = null;

    Property property = null, p = null;

    ObservableList<Proprietor> proprietorsList = FXCollections.observableArrayList();
    @FXML
    private Button habitationBtn;
    @FXML
    private Button proprietaireBtn;
    @FXML
    private Button designationBtn;
    @FXML
    private Button affectationBtn;
    @FXML
    private AnchorPane habitationAnchor;
    @FXML
    private TextField nAppertFld;
    @FXML
    private AnchorPane proprietorAnchor;
    @FXML
    private AnchorPane designationAnchor;
    @FXML
    private AnchorPane affectationAnchor;
    @FXML
    private TextField adresseResdcPrincpFld;
    @FXML
    private TableView<Proprietor> proprietaireTable;
    @FXML
    private TableColumn<Proprietor, String> idCol1;
    @FXML
    private TableColumn<Proprietor, String> nameCol1;
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
    @FXML
    private TableColumn<Proprietor, String> operationCol1;
    @FXML
    private FontAwesomeIconView selectProprBtn;
    @FXML
    private FontAwesomeIconView refrechBtn;
    @FXML
    private FontAwesomeIconView ajouterBtn;
    @FXML
    private Button continuerBtn1;
    @FXML
    private Button viderBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button viderBtn2;
    @FXML
    private Button continuerbtn3;
    @FXML
    private Button viderBtn3;
    @FXML
    private Button continuerBtn;
    @FXML
    private Button viderBtn5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        orignPropCombo.getItems().addAll("Héritage", "Donation", "Auto construction", "Acquisition");
        usageCombo.getItems().addAll("Usage professionnel", "Usage Mixte ( Habitation & Professionnel )",
                "Habitation", "Habitation principale", "Résidence secondaire");

        ToggleGroup group = new ToggleGroup();
        induvidlBtn.setToggleGroup(group);
        collectifBtn.setToggleGroup(group);

        //getDataBtn.setTooltip(new Tooltip("Coller les donnees "));
        getHabitationView();
        loadProprietorsData();

        continuerBtn.setTooltip(new Tooltip("Passer à l'étape suivante"));
        continuerBtn1.setTooltip(new Tooltip("Passer à l'étape suivante"));
        continuerbtn3.setTooltip(new Tooltip("Passer à l'étape suivante"));
        viderBtn.setTooltip(new Tooltip("Vider les champs "));
        viderBtn2.setTooltip(new Tooltip("Vider les champs "));
        viderBtn3.setTooltip(new Tooltip("Vider les champs "));
        viderBtn5.setTooltip(new Tooltip("Vider les champs "));
        saveBtn.setTooltip(new Tooltip("La sauvegarde des données"));
        habitationBtn.setTooltip(new Tooltip("Informations sur l'habitation "));
        proprietaireBtn.setTooltip(new Tooltip("Informations sur le propriétaire "));
        designationBtn.setTooltip(new Tooltip("Informations sur la désignation "));
        affectationBtn.setTooltip(new Tooltip("Informations sur l'affectation "));

        Tooltip tooltip1 = new Tooltip();
        tooltip1.setGraphic(new FontAwesomeIconView());
        Tooltip.install(selectProprBtn, new Tooltip("Selectionner un propriétaire"));

        Tooltip tooltip2 = new Tooltip();
        tooltip1.setGraphic(new FontAwesomeIconView());
        Tooltip.install(ajouterBtn, new Tooltip("ajouter des nouveaux propriétés "));

        Tooltip tooltip3 = new Tooltip();
        tooltip1.setGraphic(new FontAwesomeIconView());
        Tooltip.install(refrechBtn, new Tooltip("Recharger la liste des propriétaires"));

    }

    String getTypeImmbeuble() {

        return type;
    }

    public void setUpdate(boolean b) {
        this.update = b;
    }

     public void setTextFields(int id, int id_propr, String nom, Date proprBirth, String article, String titre,
            String commune, String inspection, String wilaya, String reu, String origin_propriete,
            String n_terrain, String n_immeuble, String n_etage, String n_appartement, String rez_chaussee,
            String nbr_etage, String nbr_apparemment, String type_immbeuble, Float superficie_tot,
            Float superficie_batie, Float superficie_non_batie, Date date_achev, String usage,
            String adresse_prcpl, String acie, Date date) {

        propertyId = id;
        proprietorId = id_propr;
        articleFld.setText(article);
        wilayaFld.setText(wilaya);
        communeFld.setText(commune);
        reuFld.setText(reu);
        inspectionFld.setText(inspection);
        nTerrainFld.setText(n_terrain);
        nImmeubleFld.setText(n_immeuble);
        if (Integer.valueOf(rez_chaussee) == 1) {
            rezChaFld.setSelected(true);
        } else {
            rezChaFld.setSelected(true);
        }
        nEtageFld.setText(n_etage);
        nomProprFld.setText(nom);
        adresseProprFld.setText(adresse_prcpl);
        orignPropCombo.setValue(origin_propriete);
        titreFld.setText(titre);
        acieFld.setText(acie);
        dateAcieFld.setValue(date.toLocalDate());

        if ("Cellectif".equals(type_immbeuble)) {

            collectifBtn.setSelected(true);
        } else {
            induvidlBtn.setSelected(true);
        }

        superTotFld.setText(String.valueOf(superficie_tot));
        SuperBatieFld.setText(String.valueOf(superficie_batie));
        superNonBatieFLd.setText(String.valueOf(superficie_non_batie));
        nbrPiecesFld.setText(nbr_apparemment);
        nbrEtageFld.setText(nbr_etage);
        dateAchevFld.setValue(date_achev.toLocalDate());
        usageCombo.setValue(usage);
        adresseResdcPrincpFld.setText(adresse_prcpl);
    }

    @FXML
    private void save(MouseEvent event) {
        connection = DbConnect.getConnect();

        if (articleFld.getText().isEmpty() || wilayaFld.getText().isEmpty() || communeFld.getText().isEmpty()
                || reuFld.getText().isEmpty()
                || nomProprFld.getText().isEmpty() || adresseProprFld.getText().isEmpty()
                || orignPropCombo.getValue() == null || titreFld.getText().isEmpty() || acieFld.getText().isEmpty()
                || dateAcieFld.getValue() == null
                || superTotFld.getText().isEmpty() || SuperBatieFld.getText().isEmpty() || superNonBatieFLd.getText().isEmpty()
                || nbrPiecesFld.getText().isEmpty() || nbrEtageFld.getText().isEmpty() || dateAchevFld.getValue() == null) {
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

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO `fiche_habitation`(`nbr_article`, `inspection`, `wilaya`, `commune`,"
                    + " `reu`, `origin_propriete`, `n_terrain`, `n_immeuble`, `n_etage`, `n_appartement`,"
                    + " `rez_chaussee`, `nbr_etage`, `nbr_apparemment`, `type_immbeuble`, `superficie_tot`,"
                    + " `superficie_batie`, `superficie_non_batie`, `date_achev`,`usage`, `adresse_prcpl`, `id_propr`, `titre_propriete`,"
                    + " `n_acie`, `date`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Ajouté avec succés");
            alert.showAndWait();

        } else {
            query = "UPDATE `fiche_habitation` SET "
                    + "`id`=?,"
                    + "`nbr_article`=?,"
                    + "`inspection`=?,"
                    + "`wilaya`=?,"
                    + "`commune`=?,"
                    + "`reu`=?,"
                    + "`origin_propriete`=?,"
                    + "`n_terrain`=?,"
                    + "`n_immeuble`=?,"
                    + "`n_etage`=?,"
                    + "`n_appartement`=?,"
                    + "`rez_chaussee`=?,"
                    + "`nbr_etage`=?,"
                    + "`nbr_apparemment`=?,"
                    + "`type_immbeuble`=?,"
                    + "`superficie_tot`=?,"
                    + "`superficie_batie`=?,"
                    + "`superficie_non_batie`=?,"
                    + "`date_achev`=?,"
                    + "`usage`=?,"
                    + "`adresse_prcpl`=?,"
                    + "`id_propr`=?,"
                    + "`titre_propriete`=?,"
                    + "`n_acie`=?,"
                    + "`date`=?  ";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("modifier avec succés");
            alert.showAndWait();

        }

    }

    private void insert() {
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, articleFld.getText());
            preparedStatement.setString(2, inspectionFld.getText());
            preparedStatement.setString(3, wilayaFld.getText());
            preparedStatement.setString(4, communeFld.getText());
            preparedStatement.setString(5, reuFld.getText());
            preparedStatement.setString(6, orignPropCombo.getValue());
            preparedStatement.setString(7, nTerrainFld.getText());
            preparedStatement.setString(8, nImmeubleFld.getText());
            preparedStatement.setString(9, nEtageFld.getText());
            preparedStatement.setString(10, nAppertFld.getText());
            preparedStatement.setBoolean(11, ground_floor);
            preparedStatement.setInt(12, Integer.valueOf(nbrEtageFld.getText()));
            preparedStatement.setInt(13, Integer.valueOf(nbrPiecesFld.getText()));
            preparedStatement.setString(14, getTypeImmbeuble());
            preparedStatement.setFloat(15, Float.valueOf(superTotFld.getText()));
            preparedStatement.setFloat(16, Float.valueOf(SuperBatieFld.getText()));
            preparedStatement.setFloat(17, Float.valueOf(superNonBatieFLd.getText()));
            preparedStatement.setString(18, String.valueOf(dateAchevFld.getValue()));
            preparedStatement.setString(19, usageCombo.getValue());
            preparedStatement.setString(20, adresseResdcPrincpFld.getText());
            preparedStatement.setInt(21, proprietor.getId());
            preparedStatement.setString(22, titreFld.getText());
            preparedStatement.setString(23, acieFld.getText());
            preparedStatement.setString(24, String.valueOf(dateAcieFld.getValue()));

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddProprietorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void CellectifType(MouseEvent event) {
        type = "Cellectif";
    }

    @FXML
    private void IndividuelType(MouseEvent event) {
        type = "individuel";
    }

    @FXML
    private void groundFloor(MouseEvent event) {
        ground_floor = true;
    }

    @FXML
    private void addProprietor(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Links.ADDPROPIETORVIEW));
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

    private void refreshProprietorTable() {
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

    private void loadProprietorsData() {

        connection = DbConnect.getConnect();
        refreshProprietorTable();

        idCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
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
                                + "-glyph-size:14px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:14px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                proprietor = proprietaireTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `proprietaire` WHERE id  =" + proprietor.getId();
                                connection = DbConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshProprietorTable();
                            } catch (SQLException ex) {
                                Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
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
                                    proprietor.getBirthDate(), proprietor.getCommune(), proprietor.getWilaya(),
                                    proprietor.getPrenom_pere(), proprietor.getNom_mere(), proprietor.getNationnalite(),
                                    proprietor.getAdresse_domicile(), proprietor.getPhone());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 3, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        operationCol1.setCellFactory(cellFoctory);
        proprietaireTable.setItems(proprietorsList);

    }

    @FXML
    private void selectProprietor(MouseEvent event) {

        proprietor = (Proprietor) proprietaireTable.getSelectionModel().getSelectedItem();
        if (proprietor == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une ligne");
            alert.showAndWait();
        } else {
            nomProprFld.setText(proprietor.getName());
            adresseProprFld.setText(proprietor.getAdresse_domicile());

        }
    }

    @FXML
    private void getHabitationView() {
        habitationBtn.setStyle("-fx-background-color:white;-fx-border-width:1.6px;-fx-border-color:#123456;-fx-border-radius:5px;");
        proprietaireBtn.setStyle("-fx-background-color:#DDD;");
        designationBtn.setStyle("-fx-background-color:#DDD;");
        affectationBtn.setStyle("-fx-background-color:#DDD;");
        habitationAnchor.setVisible(true);
        proprietorAnchor.setVisible(false);
        designationAnchor.setVisible(false);
        affectationAnchor.setVisible(false);
    }

    @FXML
    private void getProprietaireView() {
        proprietaireBtn.setStyle("-fx-background-color:white;-fx-border-width:1.6px;-fx-border-color:#123456;-fx-border-radius:5px;");
        habitationBtn.setStyle("-fx-background-color:#DDD;");
        designationBtn.setStyle("-fx-background-color:#DDD;");
        affectationBtn.setStyle("-fx-background-color:#DDD;");
        habitationAnchor.setVisible(false);
        proprietorAnchor.setVisible(true);
        designationAnchor.setVisible(false);
        affectationAnchor.setVisible(false);
    }

    @FXML
    private void getDesignationView() {
        designationBtn.setStyle("-fx-background-color:white;-fx-border-width:1.6px;-fx-border-color:#123456;-fx-border-radius:5px;");
        proprietaireBtn.setStyle("-fx-background-color:#DDD;");
        habitationBtn.setStyle("-fx-background-color:#DDD;");
        affectationBtn.setStyle("-fx-background-color:#DDD;");
        habitationAnchor.setVisible(false);
        proprietorAnchor.setVisible(false);
        designationAnchor.setVisible(true);
        affectationAnchor.setVisible(false);
    }

    @FXML
    private void getAffectationView() {
        affectationBtn.setStyle("-fx-background-color:white;-fx-border-width:1.6px;-fx-border-color:#123456;-fx-border-radius:5px;");
        proprietaireBtn.setStyle("-fx-background-color:#DDD;");
        designationBtn.setStyle("-fx-background-color:#DDD;");
        habitationBtn.setStyle("-fx-background-color:#DDD;");
        habitationAnchor.setVisible(false);
        proprietorAnchor.setVisible(false);
        designationAnchor.setVisible(false);
        affectationAnchor.setVisible(true);
    }

    void getData(ActionEvent event) {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Property property1 = (Property) stage.getUserData();
        articleFld.setText(property1.getArticle());
        wilayaFld.setText(property1.getWilaya());
        communeFld.setText(property1.getCommune());
        reuFld.setText(property1.getReu());
        inspectionFld.setText(property1.getInspection());
        nTerrainFld.setText(property1.getN_terrain());
        nImmeubleFld.setText(property1.getN_immeuble());
        rezChaFld.setText(property1.getRez_chaussee());
        nEtageFld.setText(property1.getNbr_etage());
        nomProprFld.setText(property1.getNom());
        adresseProprFld.setText(property1.getAdresse_prcpl());
        orignPropCombo.setValue(property1.getOrigin_propriete());
        titreFld.setText(property1.getTitre());
        acieFld.setText(property1.getAcie());
        dateAcieFld.setValue(property1.getDate().toLocalDate());

        if ("Cellectif".equals(property1.getType_immbeuble())) {

            collectifBtn.isPressed();
        } else {
            induvidlBtn.isPressed();
        }

        superTotFld.setText(String.valueOf(property1.getSuperficie_tot()));
        SuperBatieFld.setText(String.valueOf(property1.getSuperficie_batie()));
        superNonBatieFLd.setText(String.valueOf(property1.getSuperficie_non_batie()));
        nbrPiecesFld.setText(property1.getNbr_apparemment());
        nbrEtageFld.setText(property1.getNbr_etage());
        dateAchevFld.setValue(property1.getDate_achev().toLocalDate());
        usageCombo.setValue(property1.getUsage());

    }

    @FXML
    private void refreshTable(MouseEvent event) {
    }

}
