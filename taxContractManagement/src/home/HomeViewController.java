/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import com.jfoenix.controls.JFXButton;
import helpres.Links;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class HomeViewController implements Initializable {

    private Button btn1;
    @FXML
    private JFXButton homeBtn;
    @FXML
    private Button propaBtn;
    @FXML
    private Button proprBtn;
    @FXML
    private Button contratBtn;
    @FXML
    private Button benefiBtn;
    @FXML
    private Button paymentBtn;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button documentsBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO    
    }

    private void btnClick(MouseEvent event) {
        btn1.setStyle("-fx-background-color:#212121;-fx-font-size:36px;");
    }

    @FXML
    private void getHomeView(MouseEvent event) {
        propaBtn.setStyle("-fx-background-color:transparent;");
        proprBtn.setStyle("-fx-background-color:transparent;");
        benefiBtn.setStyle("-fx-background-color:transparent;");
        contratBtn.setStyle("-fx-background-color:transparent;");
        paymentBtn.setStyle("-fx-background-color:transparent;");
        documentsBtn.setStyle("-fx-background-color:transparent;");

        homeBtn.setStyle("-fx-text-fill:white;-fx-background-color:#FFA000;-fx-border-color:#FFA000;-fx-border-width:0 10 0 10;-fx-padding:0 0 0 16;-fx-border-radius:18px;");
    }

    @FXML
    private void getPropaView(MouseEvent event) {
        proprBtn.setStyle("-fx-background-color:transparent;");
        homeBtn.setStyle("-fx-background-color:transparent;");
        benefiBtn.setStyle("-fx-background-color:transparent;");
        contratBtn.setStyle("-fx-background-color:transparent;");
        paymentBtn.setStyle("-fx-background-color:transparent;");
        documentsBtn.setStyle("-fx-background-color:transparent;");

        propaBtn.setStyle("-fx-text-fill:white;-fx-background-color:#FFA000;-fx-border-color:#FFA000;-fx-border-width:0 10 0 10;-fx-padding:0 0 0 24;-fx-border-radius:18px;");

        loadViews(Links.PROPERTYVIEW);
    }

    @FXML
    private void getProprView(MouseEvent event) {
        propaBtn.setStyle("-fx-background-color:transparent;");
        homeBtn.setStyle("-fx-background-color:transparent;");
        contratBtn.setStyle("-fx-background-color:transparent;");
        benefiBtn.setStyle("-fx-background-color:transparent;");
        paymentBtn.setStyle("-fx-background-color:transparent;");
        documentsBtn.setStyle("-fx-background-color:transparent;");

        proprBtn.setStyle("-fx-text-fill:white;-fx-background-color:#FFA000;-fx-border-color:#FFA000;-fx-border-width:0 10 0 10;-fx-padding:0 0 0 18;-fx-border-radius:18px;");

        loadViews(Links.PROPIETORVIEW);
    }

    @FXML
    private void getContratsView(MouseEvent event) {
        propaBtn.setStyle("-fx-background-color:transparent;");
        homeBtn.setStyle("-fx-background-color:transparent;");
        proprBtn.setStyle("-fx-background-color:transparent;");
        benefiBtn.setStyle("-fx-background-color:transparent;");
        paymentBtn.setStyle("-fx-background-color:transparent;");
        documentsBtn.setStyle("-fx-background-color:transparent;");

        contratBtn.setStyle("-fx-text-fill:white;-fx-background-color:#FFA000;-fx-border-color:#FFA000;-fx-border-width:0 10 0 10;-fx-padding:0 0 0 25;-fx-border-radius:18px;");

        loadViews(Links.CONTRACTVIEW);
    }

    @FXML
    private void getBenefisView(MouseEvent event) {
        propaBtn.setStyle("-fx-background-color:transparent;");
        homeBtn.setStyle("-fx-background-color:transparent;");
        proprBtn.setStyle("-fx-background-color:transparent;");
        contratBtn.setStyle("-fx-background-color:transparent;");
        paymentBtn.setStyle("-fx-background-color:transparent;");
        documentsBtn.setStyle("-fx-background-color:transparent;");

        benefiBtn.setStyle("-fx-text-fill:white;-fx-background-color:#FFA000;-fx-border-color:#FFA000;-fx-border-width:0 10 0 10;-fx-padding:0 0 0 18;-fx-border-radius:18px;");

        loadViews(Links.BENEFICIAIREVIEW);

    }

    @FXML
    private void getpaymentView(MouseEvent event) {
        propaBtn.setStyle("-fx-background-color:transparent;");
        homeBtn.setStyle("-fx-background-color:transparent;");
        proprBtn.setStyle("-fx-background-color:transparent;");
        contratBtn.setStyle("-fx-background-color:transparent;");
        benefiBtn.setStyle("-fx-background-color:transparent;");
        documentsBtn.setStyle("-fx-background-color:transparent;");

        paymentBtn.setStyle("-fx-text-fill:white;-fx-background-color:#FFA000;-fx-border-color:#FFA000;-fx-border-width:0 10 0 10;-fx-padding:0 0 0 10;-fx-border-radius:18px;");

        loadViews(Links.PAYMENTSVIEW);
    }

    @FXML
    private void getDocumentsView(MouseEvent event) {
        propaBtn.setStyle("-fx-background-color:transparent;");
        homeBtn.setStyle("-fx-background-color:transparent;");
        proprBtn.setStyle("-fx-background-color:transparent;");
        contratBtn.setStyle("-fx-background-color:transparent;");
        benefiBtn.setStyle("-fx-background-color:transparent;");
        paymentBtn.setStyle("-fx-background-color:transparent;");

        documentsBtn.setStyle("-fx-text-fill:white;-fx-background-color:#FFA000;-fx-border-color:#FFA000;-fx-border-width:0 10 0 10;-fx-padding:0 0 0 10;-fx-border-radius:18px;");

        loadViews(Links.DEMATERIALIZATIONVIEW);

    }

    private void loadViews(String viewName) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(viewName));
            anchor.getChildren().setAll(pane);

        } catch (IOException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
