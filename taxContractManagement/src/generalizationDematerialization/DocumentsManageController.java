/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generalizationDematerialization;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class DocumentsManageController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private HBox addPaymentBtn;
    @FXML
    private JFXButton ficheContBtn;
    @FXML
    private JFXButton versementBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void getControllerView() {
        loadViews(Links.LEARNDOCUMENTS);
        versementBtn.setStyle("-fx-background-radius: 10px 10px 0px 0px;-fx-border-width: 0 0 12 0;-fx-border-color:transparent;");
        ficheContBtn.setStyle("-fx-background-radius: 10px 10px 0px 0px;-fx-border-width: 0 0 12 0;-fx-border-color:#FFA000;");
       }

    @FXML
    private void getVersementView(MouseEvent event) {
        loadViews(Links.ADDNEWDOCUMENT);
        ficheContBtn.setStyle("-fx-background-radius: 10px 10px 0px 0px;-fx-border-width: 0 0 12 0;-fx-border-color:transparent;");
        versementBtn.setStyle("-fx-background-radius: 10px 10px 0px 0px;-fx-border-width: 0 0 12 0;-fx-border-color:#FFA000;");

    }
    
    private void loadViews(String viewName) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(viewName));
            anchorPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
