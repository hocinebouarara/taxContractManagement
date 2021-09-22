/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proprietors;

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
public class ProprietorsViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane editPane;
    @FXML
    private AnchorPane proprAnchor;
    @FXML
    private JFXButton proprBtn;
    @FXML
    private JFXButton getAddProprietor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadViews(Links.PROPRIETORTABLEVIEW);

        proprBtn.setTooltip(new Tooltip("La liste des proprietaires"));
        getAddProprietor.setTooltip(new Tooltip("Ajouter des nouveaux proprietaires"));

    }

    private void loadViews(String viewName) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(viewName));
            proprAnchor.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void getAddProprView(MouseEvent event) {
        loadViews(Links.ADDPROPIETORVIEW);
    }

    @FXML
    private void proprietaireView() {

        loadViews(Links.PROPRIETORTABLEVIEW);

    }

}
