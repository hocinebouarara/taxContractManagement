/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Contract;

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
    private Button scanBtn;
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
    private TextField adressBeneficiaryFld;
    @FXML
    private JFXDatePicker startDateFld;
    @FXML
    private JFXDatePicker finDateFld;
    @FXML
    private TextField montantFld;
    @FXML
    private TextField numAcieFld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void getContractView(MouseEvent event) {
    }

    @FXML
    private void getFicheControllerView(MouseEvent event) {
    }

    @FXML
    private void save(MouseEvent event) {
    }

    @FXML
    private void clean(MouseEvent event) {
    }

    @FXML
    private void selectContract(MouseEvent event) {
    }

    @FXML
    private void refreshContractTable(MouseEvent event) {
    }

    @FXML
    private void addContactView(MouseEvent event) {
    }

}
