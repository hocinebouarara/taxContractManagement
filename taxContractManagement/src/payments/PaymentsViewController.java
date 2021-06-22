/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import home.HomeViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class PaymentsViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane editPane;
    @FXML
    private Pane editPane1;
    @FXML
    private Pane editPane2;
    @FXML
    private AnchorPane paymentsAnchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void loadViews(String viewName) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(viewName));
            paymentsAnchor.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void getContractsTable(MouseEvent event) {
    }

    @FXML
    private void statView(MouseEvent event) {
    }

    @FXML
    private void getDematerView(MouseEvent event) {
    }

    @FXML
    private void getAddContractsView(MouseEvent event) {
    }
    
}
