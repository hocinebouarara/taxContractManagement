/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXToggleButton;
import com.mysql.jdbc.ResultSetImpl;
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
import java.time.LocalDate;
import java.time.Period;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.Contract;
import models.Controle;
import proprietors.AddProprietorController;
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
    private TextField recetteFld;
    @FXML
    private TextField anneeFld;
    @FXML
    private TextField nisFld;
    @FXML
    private TextField nifFld;
    @FXML
    private TextField articleImpotFld;
    @FXML
    private TextField adressFld;
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
    Contract contract = null;
    boolean update = false;
    ObservableList<Controle> controlesList = FXCollections.observableArrayList();
    ObservableList<Contract> contractsList = FXCollections.observableArrayList();

    @FXML
    private TextField numIdenfFld;
    @FXML
    private TextField adresseLoueFld;
    @FXML
    private TextField montantMesFld;
    @FXML
    private JFXComboBox<String> occupationCombo;
    @FXML
    private JFXComboBox<String> periodeImpotCombo;
    @FXML
    private TextField montantBrutFld;
    @FXML
    private TextField montantImpotFld;
    @FXML
    private AnchorPane periodeImpotAnchor;
    @FXML
    private HBox addAnotherBtn;

    @FXML
    private VBox vBoxBail;
    @FXML
    private TextField searchFicheControle;
    @FXML
    private VBox bailFields;
    @FXML
    private JFXComboBox<String> usageCombo;

    int years = 0, months = 0, days = 0;

    float tax = (float) 0.15;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
        getFicheControllerView();
        periodeImpotCombo.getItems().addAll("Payer en une fois", "Annuel", "trimestrielle", "Mensuel");
        occupationCombo.getItems().addAll("Etudaint", "Autres");
        usageCombo.getItems().addAll("Usage commercial taux (15%)", "Usage d'habitation taux (10%)", "Etudiant taux (07%)");
        addAnotherBtn.setVisible(false);

        getPeriodContract();

    }

    @FXML
    private void refreshFicheContTable() {
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
        refreshFicheContTable();

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
    private void getFicheControllerView() {
        ficheControleBtn.setStyle("-fx-background-color:white;-fx-border-width:1.6px;-fx-border-color:#123456;-fx-border-radius:5px;");
        paymentBtn.setStyle("-fx-background-color:#DDD;");
        ScanBtn.setStyle("-fx-background-color:#DDD;");
        periodeImpotAnchor.setVisible(false);
        ficheControllerAnchor.setVisible(true);
    }

    @FXML
    private void getpaymentView(MouseEvent event) {
        paymentBtn.setStyle("-fx-background-color:white;-fx-border-width:1.6px;-fx-border-color:#123456;-fx-border-radius:5px;");
        ficheControleBtn.setStyle("-fx-background-color:#DDD;");
        ScanBtn.setStyle("-fx-background-color:#DDD;");
        periodeImpotAnchor.setVisible(true);
        ficheControllerAnchor.setVisible(false);
    }

    @FXML
    private void getScanView(MouseEvent event) {
    }

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO `avis_versement`(`nom_bailleur`, `adresse_bailleur`, `n_id_fiscal`, `n_articlage`, `adresse_du_bien`, `montant`, `nom_preneur`, `occupation_preneur`, `id_fiche_contr`) VALUES (?,?,?,?,?,?,?,?,?)";

        } else {

        }

    }

    private void insert() {
        try {

            connection = DbConnect.getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, controle.getProprName());
            preparedStatement.setString(2, controle.getAdress());
            preparedStatement.setInt(3, controle.getNif());
            preparedStatement.setInt(4, controle.getArticleImpots());
            preparedStatement.setString(5, controle.getAdress());
            preparedStatement.setFloat(6, Float.valueOf(montantMesFld.getText()));
            preparedStatement.setString(7, controle.getBenefName());
            preparedStatement.setString(8, occupationCombo.getValue());
            preparedStatement.setInt(9, controle.getId());

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddProprietorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void save(MouseEvent event) {

        if (controle == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Data");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            insertPeriod();
            addAnotherBtn.setVisible(true);

        }
    }

    @FXML
    private void clean(MouseEvent event) {
    }

    @FXML
    private void selectFicheController(MouseEvent event) {
        controle = (Controle) controleTable.getSelectionModel().getSelectedItem();
        if (controle == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une ligne");
            alert.showAndWait();
        } else {
            ProprietaireFld.setText(controle.getProprName());
            nomBenefiFld.setText(controle.getBenefName());
            anneeFld.setText(controle.getAnnee());
            recetteFld.setText(controle.getRecette());
            nisFld.setText(String.valueOf(controle.getNis()));
            nifFld.setText(String.valueOf(controle.getNif()));
            articleFld.setText(String.valueOf(controle.getArticleImpots()));
            activiteFld.setText(controle.getActivite());
            adressFld.setText(controle.getAdress());
            numIdenfFld.setText(String.valueOf(controle.getNis()));
            articleImpotFld.setText(String.valueOf(controle.getNis()));

        }
    }

    @FXML
    private void addFicheControllerView(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Links.ADDFICHEDECONTROLE));
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
    private void addAnother(MouseEvent event) {

        if (periodeImpotCombo.getValue() == null || montantBrutFld.getText() == null
                || usageCombo.getValue() == null
                || montantImpotFld.getText() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Data");
            alert.showAndWait();

        } else {
            insertPeriod();

        }
    }

    public static int getLastIdPayment() {
        int i = -1;
        try {
            String Sql = "SELECT id FROM `avis_versement` ORDER BY id DESC LIMIT 1";
            Connection connection = (Connection) DbConnect.getConnect();
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(Sql);
            ResultSetImpl resultSet = (ResultSetImpl) preparedStatement.executeQuery();
            resultSet.next();
            i = resultSet.getInt(1);

        } catch (SQLException e) {

        }
        return i;
    }

    public void insertPeriod() {

        if (update == false) {

            query = "INSERT INTO `peroide_impots`(`periode_impot`, `Montant_brut_des_loyers`, `Commercial_taux`, `Montant_impots`, `id_versement`) VALUES (?,?,?,?,?)";

            try {

                connection = DbConnect.getConnect();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, periodeImpotCombo.getValue());
                preparedStatement.setFloat(2, Float.valueOf(montantBrutFld.getText()));
                preparedStatement.setFloat(3, getTax(usageCombo.getValue()));
                preparedStatement.setFloat(4, Float.valueOf(montantImpotFld.getText()));
                preparedStatement.setInt(5, getLastIdPayment());

                preparedStatement.execute();

            } catch (SQLException ex) {
                Logger.getLogger(AddProprietorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

        }

    }

    public void getPeriodContract() {

        try {
            LocalDate startDate = null, endDate = null;
            String Sql = "SELECT contrat.date,contrat.date_fin FROM `contrat` WHERE contrat.id=" + controle.getId() + ";";
            Connection connection = (Connection) DbConnect.getConnect();
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(Sql);
            ResultSetImpl resultSet = (ResultSetImpl) preparedStatement.executeQuery();
            resultSet.next();
            startDate = resultSet.getDate(1).toLocalDate();
            endDate = resultSet.getDate(2).toLocalDate();

            findDifference(startDate, endDate);

            System.out.println("payments.AddPaymentController.getPeriodContract()" + startDate + "  " + endDate);

        } catch (SQLException ex) {
            Logger.getLogger(AddPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void findDifference(LocalDate start_date, LocalDate end_date) {

        // find the period between
        // the start and end date
        Period period = Period.between(start_date, end_date);

        years = period.getYears();
        months = period.getMonths();
        days = period.getDays();

        // Print the date difference
        // in years, months, and days
        System.out.print(
                "Difference "
                + "between two dates is: ");

        // Print the result
        System.out.printf(
                "%d years, %d months"
                + " and %d days ",
                period.getYears(),
                period.getMonths(),
                period.getDays());
    }

    public float getTax(String value) {
        switch (value) {

            case "Usage commercial taux (15%)":
                tax = (float) 0.15;
                break;

            case "Usage d'habitation taux (10%)":
                tax = (float) 0.10;
                break;

            case "Etudiant taux (07%)":
                tax = (float) 0.07;
                break;
        }

        return tax;
    }

}
