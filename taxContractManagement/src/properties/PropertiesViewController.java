/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class PropertiesViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane editPane;
    @FXML
    private AnchorPane PropertiesAnchor;
    @FXML
    private JFXButton propertyBtn1;
    @FXML
    private JFXButton addPropertyfBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getPropertiesTable();
        addPropertyfBtn.setTooltip(new Tooltip("Ajouter des nouveaux propriétés"));
        propertyBtn1.setTooltip(new Tooltip("La liste des propriétés"));
    }

    @FXML
    private void getPropertiesTable() {
        loadViews(Links.PROPERTYTABLEVIEW);
    }


    @FXML
    private void getAddPropertiesView(MouseEvent event) {
        loadViews(Links.ADDPROPERTYVIEW);
    }

    private void loadViews(String viewName) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(viewName));
            PropertiesAnchor.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
