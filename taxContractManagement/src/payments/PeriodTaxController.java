/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpres.DbConnect;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Controle;
import models.Period;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import proprietors.ProprietorsViewController;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class PeriodTaxController implements Initializable {

    @FXML
    private TableView<Period> periodsTable;
    @FXML
    private TableColumn<Period, String> idCol;
    @FXML
    private TableColumn<Period, String> idVersementCol;
    @FXML
    private TableColumn<Period, String> periodTypeCol;
    @FXML
    private TableColumn<Period, String> montantBrutCol;
    @FXML
    private TableColumn<Period, String> usageTauxCol;
    @FXML
    private TableColumn<Period, String> montantImpotCol;
    @FXML
    private TableColumn<Period, String> actionCol;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Period period = null;
    private int controleId;
    Controle controle = null;

    int years = 0, months = 0, days = 0;
    float onlyMonths = (float) 0.0;

    LocalDate endDate1 = null, startDate1 = null;
    String typePeriod = null;
    int nbrPeriod;
    int nbrItems;

    @FXML
    private Text proprName;
    @FXML
    private Text proprBith;
    @FXML
    private Text proprCommBirth;
    @FXML
    private Text proprWilayaBirth;
    @FXML
    private Text proprPere;
    @FXML
    private Text proprMere;
    @FXML
    private Text proprNatio;
    @FXML
    private Text proprAdresse;
    @FXML
    private Text contractType;
    @FXML
    private Text startDate;
    @FXML
    private Text endDate;
    @FXML
    private Text montantContrat;
    @FXML
    private Text periodeImpot;
    @FXML
    private Text numContrat;
    @FXML
    private Text benefName;
    @FXML
    private Text benefBith;
    @FXML
    private Text benefCommBirth;
    @FXML
    private Text benefWilayaBirth;
    @FXML
    private Text benefPere;
    @FXML
    private Text benefMere;
    @FXML
    private Text benefNatio;
    @FXML
    private Text benefAdresse;
    @FXML
    private Text nif;
    @FXML
    private Text article;
    @FXML
    private Text proprPhone;

    double totalAmount = 0.0;
    double cash = 0.0;
    double balance = 0.0;
    double bHeight = 0.0;
    @FXML
    private Text nis;

    public Controle getControle() {
        return controle;
    }

    public void setControle(Controle controle) {
        this.controle = controle;
    }

    ObservableList<Period> periodsList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();

        setControleId(controleId);

    }

    @FXML
    private void refreshTable() {
        try {
            periodsList.clear();

            query = "SELECT\n"
                    + "    fiche_de_control.id,\n"
                    + "    peroide_impots.id,\n"
                    + "    peroide_impots.id_versement,\n"
                    + "    peroide_impots.periode_impot,\n"
                    + "    peroide_impots.Montant_brut_des_loyers,\n"
                    + "    peroide_impots.usage_taux,\n"
                    + "    peroide_impots.Montant_impots\n"
                    + "FROM\n"
                    + "    peroide_impots\n"
                    + "INNER JOIN avis_versement ON avis_versement.id = peroide_impots.id_versement\n"
                    + "INNER JOIN fiche_de_control ON avis_versement.id_fiche_contr = fiche_de_control.id\n"
                    + "WHERE\n"
                    + "    fiche_de_control.id = '" + controleId + "'\n"
                    + "ORDER BY\n"
                    + "    fiche_de_control.id;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                periodsList.add(new Period(
                        resultSet.getInt("fiche_de_control.id"),
                        resultSet.getString("peroide_impots.periode_impot"),
                        resultSet.getFloat("peroide_impots.Montant_brut_des_loyers"),
                        resultSet.getFloat("peroide_impots.usage_taux"),
                        resultSet.getFloat("peroide_impots.Montant_impots"),
                        resultSet.getInt("peroide_impots.id_versement")
                ));
                periodsTable.setItems(periodsList);
                nbrItems = periodsTable.getItems().size();
                System.out.println(" size                       is :  " + nbrItems);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProprietorsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadData() {

        connection = DbConnect.getConnect();
        refreshTable();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        periodTypeCol.setCellValueFactory(new PropertyValueFactory<>("periodType"));
        montantBrutCol.setCellValueFactory(new PropertyValueFactory<>("montantBrut"));
        usageTauxCol.setCellValueFactory(new PropertyValueFactory<>("usageTaux"));
        montantImpotCol.setCellValueFactory(new PropertyValueFactory<>("montantImpot"));
        idVersementCol.setCellValueFactory(new PropertyValueFactory<>("idVersement;"));

        //add cell of button edit 
        Callback<TableColumn<Period, String>, TableCell<Period, String>> cellFoctory = (TableColumn<Period, String> param) -> {
            // make cell containing buttons
            final TableCell<Period, String> cell = new TableCell<Period, String>() {
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
                                period = periodsTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `peroide_impots` WHERE id =" + period.getId();
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
        actionCol.setCellFactory(cellFoctory);
        periodsTable.setItems(periodsList);

    }

    void setUpdate(boolean b) {
    }

    public void setTextFields(int id, String proprName1, String proprAdress, Date proprBirth, String proprComm, String propMere,
            String proprPere1, String proprPhone1, String proprWilaya, String proprNatio,
            String benefName1, String benefAdress, Date benefBirth, String benefComm, String benefMere1, String benefPere1,
            String benefNatio1, String benefWilaya,
            String contractType1, Date startDate1, Date endDate1, float montant, String periodImpot1, String numAcie, String nis1, String nif1, String article1) {

        controleId = id;
        nif.setText(nif1);
        nis.setText(nis1);
        article.setText(article1);
        proprName.setText(proprName1);
        proprAdresse.setText(proprAdress);
        proprBith.setText(proprBirth.toString());
        proprCommBirth.setText(proprComm);
        proprMere.setText(propMere);
        proprPere.setText(proprPere1);
        proprPhone.setText(proprPhone1);
        proprWilayaBirth.setText(proprWilaya);
        this.proprNatio.setText(proprNatio);

        benefAdresse.setText(benefAdress);
        benefBith.setText(benefBirth.toString());
        benefCommBirth.setText(benefComm);
        benefMere.setText(benefMere1);
        benefName.setText(benefName1);
        benefNatio.setText(benefNatio1);
        benefPere.setText(benefPere1);
        benefWilayaBirth.setText(benefWilaya);

        contractType.setText(contractType1);
        startDate.setText(startDate1.toString());
        endDate.setText(endDate1.toString());
        montantContrat.setText(String.valueOf(montant));
        periodeImpot.setText(periodImpot1);
        numContrat.setText(numAcie);

        this.endDate1 = endDate1.toLocalDate();
        this.startDate1 = startDate1.toLocalDate();
        typePeriod = periodImpot1;

    }

    private void getData(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Controle c = (Controle) stage.getUserData();

    }

    public int getControleId() {
        return controleId;
    }

    public void setControleId(int controleId) {
        this.controleId = controleId;
    }

    @FXML
    private void print(MouseEvent event) {

        try {
            LocalDate toDayDate = LocalDate.now();
            toDayDate.format(DateTimeFormatter.ISO_ORDINAL_DATE);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dtf.format(toDayDate);

            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\Deeplight\\OneDrive\\Documents\\NetBeansProjects\\taxContractManagement-main\\taxContractManagement\\src\\payments\\report.jrxml");
            JRDataSource dataSource = new JREmptyDataSource();
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("proprietorName", proprName.getText());
            parameters.put("nis", nis.getText());
            parameters.put("nif", nif.getText());
            parameters.put("direction", "Direction d'impots de lardjem");
            parameters.put("toDay", dtf.format(toDayDate));

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Deeplight\\OneDrive\\Bureau\\reports\\report.pdf");
        } catch (JRException ex) {
            Logger.getLogger(PeriodTaxController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*public float findDifference(LocalDate start_date, LocalDate end_date) {

        // find the period between
        // the start and end date
        java.time.Period periode = java.time.Period.between(start_date, end_date);

        years = periode.getYears();
        months = periode.getMonths();
        days = periode.getDays();

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
                periode.getYears(),
                periode.getMonths(),
                periode.getDays());

        return onlyMonths;
    }

    public void getNbrPeriods() {
        findDifference(startDate1, endDate1);
        switch (typePeriod) {
            case "payer en une fois":

                nbrItems = periodsTable.getItems().size();
                nbrPeriodPay.setText(String.valueOf(nbrItems));
                nbrPeriodNoPay.setText(String.valueOf(1 - nbrItems));

                break;
            case "annuel":

                nbrPeriod = (int) (onlyMonths / 12);
                nbrItems = periodsTable.getItems().size();
                nbrPeriodPay.setText(String.valueOf(nbrItems));
                nbrPeriodNoPay.setText(String.valueOf(nbrPeriod - nbrItems));

                break;
            case "trimestrielle":

                nbrPeriod = (int) (onlyMonths / 3);
                nbrItems = periodsTable.getItems().size();
                nbrPeriodPay.setText(String.valueOf(nbrItems));
                nbrPeriodNoPay.setText(String.valueOf(nbrPeriod - nbrItems));

                break;
            case "mensuel":

                nbrPeriod = (int) onlyMonths;
                nbrItems = periodsTable.getItems().size();
                nbrPeriodPay.setText(String.valueOf(nbrItems));
                nbrPeriodNoPay.setText(String.valueOf(nbrPeriod - nbrItems));

                break;
        }

    }*/
}
