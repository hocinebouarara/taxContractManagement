/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import helpres.DbConnect;
import com.mysql.jdbc.PreparedStatement;
import helpres.Links;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class LoginViewController implements Initializable {

    @FXML
    private JFXTextField userNameFld;
    @FXML
    private JFXPasswordField passwordFld;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private boolean checkUser = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean checkUsername(String username) {

        query = "SELECT * FROM `users` WHERE `USEM` =?";

        try {

            connection = DbConnect.getConnect();
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                checkUser = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkUser;
    }

    @FXML
    private void Login(ActionEvent event) {

        String userName = userNameFld.getText();
        String password = passwordFld.getText();
        query = "SELECT * FROM `users` WHERE `userName` =? and password = ?";
        connection = DbConnect.getConnect();
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println(resultSet.getString(7));
                if (resultSet.getString(7).equals("admin")) {
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource(Links.HOMEVIEW));
                        Scene scene = new Scene(parent);
                        scene.setFill(Color.TRANSPARENT);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.show();

                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();

                    } catch (IOException ex) {
                        Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource(Links.PAYINGVIEW));
                        Scene scene = new Scene(parent);
                        scene.setFill(Color.TRANSPARENT);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.show();

                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();

                    } catch (IOException ex) {
                        Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {

                JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

}
