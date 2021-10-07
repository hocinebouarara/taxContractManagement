/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generalizationDematerialization;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import helpres.DbConnect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class LearnController implements Initializable {

    @FXML
    private ScrollPane scrollp1;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXTextField x;
    @FXML
    private JFXTextField y;
    @FXML
    private JFXTextField width_field;
    @FXML
    private JFXTextField heith_field;
    @FXML
    private JFXTextField imgw;
    @FXML
    private JFXTextField imgh;
    @FXML
    private JFXTextArea Ocr_TextArea;
    @FXML
    private JFXButton Parcourir;
    @FXML
    private JFXButton learn;
    @FXML
    private ComboBox<String> documentsCombo;
    @FXML
    private ComboBox<String> champsCombo;

    String query = null, query1 = null;
    Connection connection = null, connection1 = null;
    PreparedStatement preparedStatement = null, preparedStatement1 = null;
    ResultSet resultSet = null, resultSet1 = null;
    String document;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getDocument();

        documentsCombo.setOnAction((event) -> {
            try {
                query1 = "SELECT `document_name`, `field_name` FROM `fieldsname` WHERE document_name = "+"'"+document  +"';";
                connection = DbConnect.getConnect();
                preparedStatement = connection.prepareStatement(query1);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    champsCombo.getItems().add(resultSet.getString(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(LearnController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        /* documentsCombo.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });*/
    }

    public String getDocument() {
        try {
            query = "SELECT * FROM `documentname`";
            connection = DbConnect.getConnect();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                document = resultSet.getString(1);
                documentsCombo.getItems().add(document);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LearnController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return document;
    }

    public void getFields(String documentName) {

        try {
            query = "SELECT `document_name`, `field_name` FROM `fieldsname` WHERE document_name =" + documentName;
            connection = DbConnect.getConnect();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            champsCombo.getItems().add(resultSet.getString(2));
        } catch (SQLException ex) {
            Logger.getLogger(LearnController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
