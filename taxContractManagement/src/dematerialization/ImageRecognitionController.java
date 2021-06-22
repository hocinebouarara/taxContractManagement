/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dematerialization;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class ImageRecognitionController implements Initializable {

    @FXML
    private HBox hbox1;
    @FXML
    private ScrollPane scrollp1;
    @FXML
    private ImageView img;
    @FXML
    private JFXButton reset;
    @FXML
    private JFXButton full;
    @FXML
    private JFXTextField width_field;
    @FXML
    private JFXTextField heith_field;
    @FXML
    private JFXTextField x;
    @FXML
    private JFXTextField y;
    @FXML
    private JFXTextArea Ocr_TextArea;
    @FXML
    private JFXTextField four;
    @FXML
    private JFXTextField zone_id;
    @FXML
    private JFXTextField nbr_Zone;
    @FXML
    private JFXComboBox<?> Fournisseur;
    @FXML
    private JFXComboBox<?> Facture;
    @FXML
    private JFXComboBox<?> Produit;
    @FXML
    private JFXButton Next;
    @FXML
    private JFXTextField nbr_ligne;
    @FXML
    private JFXButton deviser;
    @FXML
    private JFXButton Ajouter_four;
    @FXML
    private JFXButton scn;
    @FXML
    private JFXButton demat;
    @FXML
    private JFXButton open;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl;
    @FXML
    private Label lv1;
    @FXML
    private JFXTextField ligne_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Devise_zone(ActionEvent event) {
    }

    @FXML
    private void Ajouter_four_Action(ActionEvent event) {
    }

    @FXML
    private void HandleCorrectionButoon(ActionEvent event) {
    }

    @FXML
    private void Parcourir(ActionEvent event) {
    }
    
}
