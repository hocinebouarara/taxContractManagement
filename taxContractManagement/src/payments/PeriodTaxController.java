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
    private TableColumn<Period, String> checkCol;
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
    Controle controle = null;
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
    private Text nbrPeriodPay;
    @FXML
    private Text montantPay;
    @FXML
    private Text nbrPeriodNoPay;
    @FXML
    private Text montantNoPay;
    @FXML
    private Text proprPhone;

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

    }

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
                    + "    fiche_de_control.id = \"13\"\n"
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setTextFields() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void getData(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Controle c = (Controle) stage.getUserData();

        proprName.setText(c.getProprName());
        proprAdresse.setText(c.getProprAdress());
        proprBith.setText(c.getProprbirth().toString());
        proprCommBirth.setText(c.getProprCommuneBirth());
        proprMere.setText(c.getProprNationalite());
        proprPere.setText(c.getProprPere());
        proprPhone.setText(c.getProprPhone());
        proprWilayaBirth.setText(c.getProprWilayaBirth());

        benefAdresse.setText(c.getBenefAdress());
        benefBith.setText(c.getBenefBirth().toString());
        benefCommBirth.setText(c.getBenefCommuneBirth());
        benefMere.setText(c.getBenefMere());
        benefName.setText(c.getBenefName());
        benefNatio.setText(c.getBenefNationalite());
        benefPere.setText(c.getBenefPere());
        benefWilayaBirth.setText(c.getBenefWilayaBirth());

        contractType.setText(c.getContractType());
        startDate.setText(c.getStartDate().toString());
        endDate.setText(c.getEndDate().toString());
        montantContrat.setText(c.getMontant().toString());
        periodeImpot.setText(c.getPeriodImpot());
        numContrat.setText(c.getNumAcie());
        

    }

}
