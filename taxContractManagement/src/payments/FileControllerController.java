/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import beneficiaries.AddBeneficiaryController;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import models.Controle;
import org.controlsfx.control.Notifications;
import proprietors.ProprietorsViewController;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class FileControllerController implements Initializable {

    @FXML
    private TableView<Controle> controleTable;
    @FXML
    private TableColumn<Controle, String> checkCol;
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

    String query = null, query1 = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Controle controle = null;

    int years = 0, months = 0, days = 0;
    float onlyMonths = (float) 0.0;

    LocalDate endDate = null, startDate = null;
    String typePeriod = null;

    String proprName1, proprAdress;
    Date proprBirth;
    String proprComm, propMere, nis,
            proprPere1, proprPhone1, proprWilaya,
            benefName1, benefAdress, proprNatio;
    Date benefBirth;
    String benefComm, benefMere1, benefPere1,
            benefNatio1, benefWilaya,
            contractType1;
    Date startDate1, endDate1;
    float montant;
    String periodImpot, numAcie, nif, article;

    int controleId;

    ObservableList<Controle> controlesList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Controle, String> directionCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
        controleTable.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                controle = controleTable.getSelectionModel().getSelectedItem();
                Controle c = new Controle();
                try {

                    query = "SELECT\n"
                            + "    fiche_de_control.id,\n"
                            + "    contrat.id,\n"
                            + "    contrat.type_contr,\n"
                            + "    contrat.date,\n"
                            + "    contrat.date_fin,\n"
                            + "    contrat.montant,\n"
                            + "    contrat.n_acie,\n"
                            + "    contrat.periodes_imposition,\n"
                            + "    contrat.id_fiche_hab,\n"
                            + "    proprietaire.id,\n"
                            + "    proprietaire.nom_prenom_or_RS,\n"
                            + "    proprietaire.date_nss,\n"
                            + "    proprietaire.commune,\n"
                            + "    proprietaire.wilaya,\n"
                            + "    proprietaire.pere,\n"
                            + "    proprietaire.mere,\n"
                            + "    proprietaire.nationalite,\n"
                            + "    proprietaire.telephone,\n"
                            + "    proprietaire.adress,\n"
                            + "    beneficiaire.id,\n"
                            + "    beneficiaire.nom_prenom_or_RS,\n"
                            + "    beneficiaire.date_nss,\n"
                            + "    beneficiaire.commune,\n"
                            + "    beneficiaire.wilaya,\n"
                            + "    beneficiaire.prenom_pere,\n"
                            + "    beneficiaire.nom_mere,\n"
                            + "    beneficiaire.nationalite,\n"
                            + "    beneficiaire.adresse_domicile,\n"
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
                            + "    fiche_de_control.Adress,\n" + "    fiche_de_control.Article_imposition\n"
                            + "FROM\n"
                            + "    fiche_de_control\n"
                            + "INNER JOIN contrat ON contrat.id = fiche_de_control.id_fiscal\n"
                            + "INNER JOIN beneficiaire ON beneficiaire.id = contrat.id_benef\n"
                            + "INNER JOIN fiche_habitation ON fiche_habitation.id = contrat.id_fiche_hab\n"
                            + "INNER JOIN proprietaire ON fiche_habitation.id_propr = proprietaire.id\n"
                            + "ORDER BY\n"
                            + "    fiche_de_control.id;";
                    preparedStatement = connection.prepareStatement(query);
                    resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        c.setId(resultSet.getInt("fiche_de_control.id"));
                        c.setContractId(resultSet.getInt("contrat.id"));
                        c.setContractType(resultSet.getString("contrat.type_contr"));
                        c.setStartDate(resultSet.getDate("contrat.date"));
                        c.setEndDate(resultSet.getDate("contrat.date_fin"));
                        c.setMontant(resultSet.getFloat("contrat.montant"));
                        c.setNumAcie(resultSet.getString("contrat.n_acie"));
                        c.setPeriodImpot(resultSet.getString("contrat.periodes_imposition"));
                        c.setFicheHabId(resultSet.getInt("contrat.id_fiche_hab"));

                        c.setProprId(resultSet.getInt("proprietaire.id"));
                        c.setProprName(resultSet.getString("proprietaire.nom_prenom_or_RS"));
                        c.setProprbirth(resultSet.getDate("proprietaire.date_nss"));
                        c.setProprCommuneBirth(resultSet.getString("proprietaire.commune"));
                        c.setProprWilayaBirth(resultSet.getString("proprietaire.wilaya"));
                        c.setProprPere(resultSet.getString("proprietaire.pere"));
                        c.setProprMere(resultSet.getString("proprietaire.mere"));
                        c.setProprNationalite(resultSet.getString("proprietaire.nationalite"));
                        c.setAdress(resultSet.getString("proprietaire.adress"));
                        c.setProprPhone(resultSet.getString("proprietaire.telephone"));

                        c.setBenefID(resultSet.getInt("beneficiaire.id"));
                        c.setBenefName(resultSet.getString("beneficiaire.nom_prenom_or_RS"));
                        c.setBenefBirth(resultSet.getDate("beneficiaire.date_nss"));
                        c.setBenefCommuneBirth(resultSet.getString("beneficiaire.commune"));
                        c.setBenefWilayaBirth(resultSet.getString("beneficiaire.wilaya"));
                        c.setBenefPere(resultSet.getString("beneficiaire.prenom_pere"));
                        c.setBenefMere(resultSet.getString("beneficiaire.nom_mere"));
                        c.setBenefNationalite(resultSet.getString("beneficiaire.nationalite"));
                        c.setBenefAdress(resultSet.getString("beneficiaire.adresse_domicile"));

                        c.setInspection(resultSet.getString("fiche_de_control.inscpection"));
                        c.setRecette(resultSet.getString("fiche_de_control.Recette"));
                        c.setAnnee(resultSet.getString("fiche_de_control.Annee"));
                        c.setDesignation(resultSet.getString("fiche_de_control.Designation"));
                        c.setNis(resultSet.getString("fiche_de_control.NiS"));
                        c.setNif(resultSet.getString("fiche_de_control.NIF"));
                        c.setWilaya(resultSet.getString("fiche_de_control.Wilaya"));
                        c.setActivite(resultSet.getString("fiche_de_control.Activity"));
                        c.setCodeActivite(resultSet.getInt("fiche_de_control.Code_d_activity"));
                        c.setFormJuridique(resultSet.getString("fiche_de_control.Forme_Juridique"));
                        c.setAdress(resultSet.getString("fiche_de_control.Adress"));
                        c.setArticleImpots(resultSet.getString("fiche_de_control.Article_imposition"));

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(FileControllerController.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (controle != null) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource(Links.PERIODSVIEW));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    PeriodTaxController periodTaxController = loader.getController();
                    periodTaxController.setControle(c);
                    Parent parent = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.initStyle(StageStyle.UTILITY);
                    stage.setUserData(c);
                    stage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez sélectionner une ligne");
                    alert.showAndWait();
                }

            }
        });
    }

    private void refreshTable() {
        try {
            controlesList.clear();
            query = "SELECT\n"
                    + "    fiche_de_control.id,\n"
                    + "    contrat.id,\n"
                    + "    contrat.type_contr,\n"
                    + "    contrat.date,\n"
                    + "    contrat.date_fin,\n"
                    + "    contrat.montant,\n"
                    + "    contrat.n_acie,\n"
                    + "    contrat.periodes_imposition,\n"
                    + "    contrat.id_fiche_hab,\n"
                    + "    proprietaire.id,\n"
                    + "    proprietaire.nom_prenom_or_RS,\n"
                    + "    proprietaire.date_nss,\n"
                    + "    proprietaire.commune,\n"
                    + "    proprietaire.wilaya,\n"
                    + "    proprietaire.pere,\n"
                    + "    proprietaire.mere,\n"
                    + "    proprietaire.nationalite,\n"
                    + "    proprietaire.telephone,\n"
                    + "    proprietaire.adress,\n"
                    + "    beneficiaire.id,\n"
                    + "    beneficiaire.nom_prenom_or_RS,\n"
                    + "    beneficiaire.date_nss,\n"
                    + "    beneficiaire.commune,\n"
                    + "    beneficiaire.wilaya,\n"
                    + "    beneficiaire.prenom_pere,\n"
                    + "    beneficiaire.nom_mere,\n"
                    + "    beneficiaire.nationalite,\n"
                    + "    beneficiaire.adresse_domicile,\n"
                    + "    fiche_de_control.direction,\n"
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
                    + "    fiche_de_control.Adress,\n" + "    "
                    + "    fiche_de_control.Article_imposition\n"
                    + "FROM\n"
                    + "    fiche_de_control\n"
                    + "INNER JOIN contrat ON contrat.id = fiche_de_control.id_fiscal\n"
                    + "INNER JOIN beneficiaire ON beneficiaire.id = contrat.id_benef\n"
                    + "INNER JOIN fiche_habitation ON fiche_habitation.id = contrat.id_fiche_hab\n"
                    + "INNER JOIN proprietaire ON fiche_habitation.id_propr = proprietaire.id\n"
                    + "ORDER BY\n"
                    + "    fiche_de_control.id;";
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
                        resultSet.getString("fiche_de_control.direction"),
                        resultSet.getString("fiche_de_control.inscpection"),
                        resultSet.getString("fiche_de_control.recette"),
                        resultSet.getString("fiche_de_control.annee"),
                        resultSet.getString("fiche_de_control.designation"),
                        resultSet.getString("fiche_de_control.NiS"),
                        resultSet.getString("fiche_de_control.NIF"),
                        resultSet.getString("fiche_de_control.wilaya"),
                        resultSet.getString("fiche_de_control.activity"),
                        resultSet.getInt("fiche_de_control.Code_d_activity"),
                        resultSet.getString("fiche_de_control.Forme_Juridique"),
                        resultSet.getString("fiche_de_control.adress"),
                        resultSet.getString("fiche_de_control.Article_imposition")
                ));
                controleTable.setItems(controlesList);

                proprName1 = resultSet.getString(11);
                proprAdress = resultSet.getString(19);
                proprBirth = resultSet.getDate(12);
                proprComm = resultSet.getString(13);
                propMere = resultSet.getString(16);
                proprPere1 = resultSet.getString(15);
                proprNatio = resultSet.getString(17);
                proprPhone1 = resultSet.getString(18);
                proprWilaya = resultSet.getString(14);
                benefName1 = resultSet.getString(21);
                benefAdress = resultSet.getString(28);
                benefBirth = resultSet.getDate(22);
                benefComm = resultSet.getString(23);
                benefMere1 = resultSet.getString(26);
                benefPere1 = resultSet.getString(25);
                benefNatio1 = resultSet.getString(27);
                benefWilaya = resultSet.getString(24);
                contractType1 = resultSet.getString(3);
                montant = resultSet.getFloat(6);
                numAcie = resultSet.getString(7);
                controleId = resultSet.getInt(1);
                startDate1 = resultSet.getDate(4);
                endDate1 = resultSet.getDate(5);
                article = resultSet.getString(41);
                nif = resultSet.getString(35);
                nis = resultSet.getString(34);

                controleTable.setItems(controlesList);
                startDate = resultSet.getDate(4).toLocalDate();
                endDate = resultSet.getDate(5).toLocalDate();
                typePeriod = resultSet.getString(8);

                findDifference(startDate, endDate);
                System.out.println(" the period is  : " + typePeriod);

                switch (typePeriod) {
                    case "payer en une fois":
                        LocalDate toDayDate = LocalDate.now();

                        findDifference(toDayDate, endDate);

                        if (days < 4) {

                            // createNotification();
                            System.out.println("the contract is down");
                        }

                        break;
                    case "annuel":
                        findDifference(startDate, endDate);
                        System.out.println("annuel   :  " + findDifference(startDate, endDate));

                        if (onlyMonths >= 12) {

                            LocalDate toDayDate2 = LocalDate.now();
                            LocalDate afterYear = startDate;

                            while (onlyMonths >= 12 && findDifference(afterYear, endDate) > 0) {

                                afterYear = afterYear.plusYears(1);
                                findDifference(toDayDate2, afterYear);
                                if (days < 3) {
                                    // createNotification();

                                }

                            }

                        }

                        break;
                    case "trimestrielle":
                        findDifference(startDate, endDate);
                        System.out.println("trimestrielle   :  " + findDifference(startDate, endDate));

                        LocalDate toDayDate3 = LocalDate.now();
                        LocalDate afterTrimestre = startDate;

                        System.out.println("Tri diff after date is : " + findDifference(afterTrimestre, endDate));
                        while (onlyMonths >= 3) {

                            System.out.println(" Tri diff after date is : " + findDifference(afterTrimestre, endDate));

                            afterTrimestre = afterTrimestre.plusMonths(3);
                            System.out.println("after trimester is     :" + afterTrimestre);
                            findDifference(toDayDate3, afterTrimestre);
                            System.out.println(" Rest Days : " + days);
                            if (days <= 3) {
                                //createNotification();
                                System.out.println("once trimestre from contract ");

                            }

                        }

                        break;
                    case "mensuel":
                        findDifference(startDate, endDate);
                        System.out.println("Month   :  " + findDifference(startDate, endDate));

                        LocalDate toDayDate4 = LocalDate.now();
                        LocalDate afterMonth = startDate;

                        System.out.println("Tri diff after date is : " + findDifference(afterMonth, endDate));
                        while (onlyMonths >= 1) {

                            System.out.println(" Tri diff after date is : " + findDifference(afterMonth, endDate));

                            afterMonth = afterMonth.plusMonths(1);
                            System.out.println("after month is     :" + afterMonth);
                            findDifference(toDayDate4, afterMonth);
                            System.out.println(" Rest Days         : " + days);
                            if (days <= 3) {
                                createNotification();
                                System.out.println("once month from contract ");

                            }

                        }
                        break;
                }

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
        directionCol.setCellValueFactory(new PropertyValueFactory<>("direction"));

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
                                + "-glyph-size:25px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:25px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                controle = controleTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `fiche_de_control` WHERE id  =" + controle.getId();
                                connection = DbConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                            } catch (SQLException ex) {
                                Logger.getLogger(FileControllerController.class.getName()).log(Level.SEVERE, null, ex);
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
        actionsCol.setCellFactory(cellFoctory);
        controleTable.setItems(controlesList);

    }

    public void sendNotification() {

        String typePeriod = null;
        LocalDate startDate = null, endDate = null;

        try {
            Connection conn = DbConnect.getConnect();
            PreparedStatement preparedStatement1 = conn.prepareStatement(query1);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            startDate = resultSet1.getDate(4).toLocalDate();
            endDate = resultSet1.getDate(5).toLocalDate();
            typePeriod = resultSet1.getString(8);

        } catch (SQLException ex) {
            Logger.getLogger(FileControllerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public float findDifference(LocalDate start_date, LocalDate end_date) {

        // find the period between
        // the start and end date
        Period period = Period.between(start_date, end_date);

        years = period.getYears();
        months = period.getMonths();
        days = period.getDays();

        onlyMonths = 12 * years + months + days / 30;

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

        return onlyMonths;
    }

    private void createNotification() {

        //Image notifIcon = new Image("");
        Notifications notification = Notifications.create()
                .title("Notification de retard")
                .text("")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction((event) -> {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource(Links.PERIODSVIEW));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    PeriodTaxController periodTaxController = loader.getController();
                    periodTaxController.setControleId(controleId);
                    periodTaxController.setTextFields(controleId, proprName1, proprAdress, proprBirth, proprComm, propMere,
                            proprPere1,
                            proprPhone1, proprWilaya, proprNatio,
                            benefName1, benefAdress, benefBirth, benefComm, benefMere1, benefPere1,
                            benefNatio1, benefWilaya,
                            contractType1, startDate1, endDate1, montant, typePeriod, numAcie,nis, nif, article
                    );
                    System.out.println("the id is   :   " + controleId);

                    Parent parent = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();

                });
        notification.darkStyle();
        notification.showConfirm();
    }

}
