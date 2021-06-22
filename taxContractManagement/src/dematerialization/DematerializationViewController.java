/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dematerialization;

import com.jfoenix.controls.JFXListView;
import helpres.Links;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class DematerializationViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXListView<String> listView;
    @FXML
    private Label label;

    public static String path = null;
    @FXML
    private Button next;
    @FXML
    private Button brows;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listView.getItems().clear();
        label.setText("");
        path = null;

    }

    @FXML
    private void getDirectoryChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\Deeplight\\OneDrive\\Documents\\G51_images"));
        File selectedFile = fc.showOpenDialog(null);

        listView.getItems().clear();
        path = null;

        if (selectedFile != null) {
            listView.getItems().add(selectedFile.getAbsolutePath());
            path = selectedFile.getAbsolutePath();
            label.setText("");
            System.out.println("path : " + path);
        } else {
            label.setText("Aucun Fichier Sélectionné !");
        }
    }

    @FXML
    private void NextBtn(ActionEvent event) throws IOException {
        if (path != null && event.getSource() == next) {

            //stage.setMaximized(true);
            Stage stg = new Stage();
            //stage.setMaximized(true);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(Links.IMAGERECODNITION)));
            stg.setTitle("Bordereau Ocr");
            stg.setScene(scene);
            stg.show();

        } else {
            System.out.println("none file chosen !!");
            label.setText("Aucun Fichier Sélectionné !");
        }
    }

}
