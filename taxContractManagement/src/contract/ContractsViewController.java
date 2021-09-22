/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contract;

import com.jfoenix.controls.JFXButton;
import helpres.Links;
import home.HomeViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class ContractsViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane editPane;
    @FXML
    private AnchorPane contractsAnchor;
    @FXML
    private JFXButton contratListBtn;
    @FXML
    private JFXButton addContractBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadViews(Links.CONTRACTTABLEVIEW);
        
        addContractBtn.setTooltip(new Tooltip("Ajouter des nouveaux locataires"));
        contratListBtn.setTooltip(new Tooltip("La liste des contrats de location "));
    }    

    @FXML
    private void getContractsTable(MouseEvent event) {
        loadViews(Links.CONTRACTTABLEVIEW);
    }


    @FXML
    private void getAddContractsView(MouseEvent event) {
        loadViews(Links.ADDCONTRACTEVIEW);
    }
    
    private void loadViews(String viewName) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(viewName));
            contractsAnchor.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
