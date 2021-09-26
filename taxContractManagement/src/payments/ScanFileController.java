package payments;

import Outils.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.mysql.jdbc.ResultSetImpl;
import helpres.DbConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import net.sourceforge.tess4j.TesseractException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Tooltip;
import models.Controle;
import models.Payment;
import proprietors.AddProprietorController;

public class ScanFileController implements Initializable {

    @FXML
    private JFXButton Parcourir;

    @FXML
    private JFXButton Scanner;

    @FXML
    private ImageView imageView;

    @FXML
    private ScrollPane scrollp1;

    private Group scrollContent = new Group();
    public static String path;
    private final Rectangle zone = new Rectangle();
    static int getx, gety, getwidth, getheight;
    private Group group = new Group();
    FileChooser Brows = new FileChooser();
    File file;
    int idContrat = -1;
    int proprietorId = -1;
    int locataireId = -1;
    int propertyId = -1;

    Controle c;

    List<Rectangle> list = new ArrayList<>();
    @FXML
    private AnchorPane anchor1;
    @FXML
    private JFXButton edit;
    @FXML
    private JFXButton save;
    @FXML
    private TextField directionFld;
    @FXML
    private TextField wilayaFld;
    @FXML
    private TextField inspectionFld;
    @FXML
    private TextField recetteFld;
    @FXML
    private TextField ProprietaireFld;
    @FXML
    private TextField proprAdressFld;
    @FXML
    private TextField nifFld;
    @FXML
    private TextField articleFld;
    @FXML
    private TextField adresseLoueFld;
    @FXML
    private TextField montantFld;
    @FXML
    private TextField preneurFld;
    @FXML
    private JFXComboBox<String> occupationCombo;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Payment payment = null;

    int controleID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        occupationCombo.getItems().addAll("Etudaint", "Autres");

        Scanner.setTooltip(new Tooltip("Extraction de données"));
        save.setTooltip(new Tooltip("La sauvegarde des données"));

        Parcourir.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            /*for (Node node:scrollContent.getChildren()) {
                if (node.getClass().getName().equals("Rectangle"))
                    anchore.getChildren().remove(node);
            }*/

            scrollContent.getChildren().clear();

            group = new Group(imageView);
            scrollContent = new Group(group);
            scrollp1.setContent(scrollContent);

            file = Brows.showOpenDialog(null);
            try {
                FileChooser.ExtensionFilter images = new FileChooser.ExtensionFilter("image", "*PNG", "*JPG", "*GIF");
                Brows.setInitialDirectory(file.getParentFile());
                FileInputStream stream = new FileInputStream(file.getAbsolutePath());
                path = file.getAbsolutePath();
                Brows.getExtensionFilters().add(images);
                Image image = new Image(stream);
                imageView.setFitWidth(image.getWidth());
                imageView.setFitHeight(image.getHeight());
                imageView.setImage(image);
                //imgh.setText("Height :"+image.getHeight());
                //imgw.setText("Width :"+image.getWidth());

                for (String s : foldersnameList.list) {
                    Rectangle rectangle = LinearRegression.posinnement(s, imageView);
                    rectangle.setFill(Color.rgb(0, 0, 0, 0));
                    rectangle.setStroke(Color.valueOf("#0746A6"));
                    list.add(rectangle);
                    scrollContent.getChildren().add(rectangle);
                    scrollp1.setContent(scrollContent);
                    //anchore.getChildren().add(rectangle);
                }

                /*for (String s:list) {
                    File theDir = new File("C:\\Users\\Abdelhamid\\Documents\\NetBeansProjects\\Deriction_des_impots\\src\\Dataset\\G51\\Dataset\\"+s);
                    if (!theDir.exists()){
                        theDir.mkdirs();
                    }
                }*/
            } catch (IOException fileNotFoundException) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("fileNotFoundException");
                alert.setContentText(fileNotFoundException.getLocalizedMessage());
                alert.show();
            }

        });
        Scanner.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                payment = new Payment();
                //     List<String> Champlist=new ArrayList<>();

                int i = 0;
                for (Rectangle R : list) {
                    System.out.println(Outils.Recognition.recognition((int) Math.round(list.get(i).getLayoutX()), (int) Math.round(list.get(i).getLayoutY()), (int) Math.round(list.get(i).getWidth()), (int) Math.round(list.get(i).getHeight()), path));
                    i++;
                }

                //Champlist.add(Outils.Recognition.recognition((int)list.get(4).getX(),(int)list.get(4).getY(),(int)list.get(4).getWidth(),(int)list.get(4).getHeight(),path));
                payment.setDirection(Outils.Recognition.recognition((int) Math.round(list.get(0).getLayoutX()), (int) Math.round(list.get(0).getLayoutY()), (int) Math.round(list.get(0).getWidth()), (int) Math.round(list.get(0).getHeight()), path));
                payment.setWilaya(Outils.Recognition.recognition((int) Math.round(list.get(1).getLayoutX()), (int) Math.round(list.get(1).getLayoutY()), (int) Math.round(list.get(1).getWidth()), (int) Math.round(list.get(1).getHeight()), path));
                payment.setInspection(Outils.Recognition.recognition((int) Math.round(list.get(2).getLayoutX()), (int) Math.round(list.get(2).getLayoutY()), (int) Math.round(list.get(2).getWidth()), (int) Math.round(list.get(2).getHeight()), path));
                payment.setRecette(Outils.Recognition.recognition((int) Math.round(list.get(3).getLayoutX()), (int) Math.round(list.get(3).getLayoutY()), (int) Math.round(list.get(3).getWidth()), (int) Math.round(list.get(3).getHeight()), path));
                payment.setLessorName(Outils.Recognition.recognition((int) Math.round(list.get(4).getLayoutX()), (int) Math.round(list.get(4).getLayoutY()), (int) Math.round(list.get(4).getWidth()), (int) Math.round(list.get(4).getHeight()), path));
                payment.setLessorAdress(Outils.Recognition.recognition((int) Math.round(list.get(5).getLayoutX()), (int) Math.round(list.get(5).getLayoutY()), (int) Math.round(list.get(5).getWidth()), (int) Math.round(list.get(5).getHeight()), path));
                payment.setNif(Outils.Recognition.recognition((int) Math.round(list.get(6).getLayoutX()), (int) Math.round(list.get(6).getLayoutY()), (int) Math.round(list.get(6).getWidth()), (int) Math.round(list.get(6).getHeight()), path));
                payment.setNbrAtricle(Outils.Recognition.recognition((int) Math.round(list.get(7).getLayoutX()), (int) Math.round(list.get(7).getLayoutY()), (int) Math.round(list.get(7).getWidth()), (int) Math.round(list.get(7).getHeight()), path));
                payment.setTakerAdress(Outils.Recognition.recognition((int) Math.round(list.get(8).getLayoutX()), (int) Math.round(list.get(8).getLayoutY()), (int) Math.round(list.get(8).getWidth()), (int) Math.round(list.get(8).getHeight()), path));
                payment.setAmount(Float.valueOf(Outils.Recognition.recognition((int) Math.round(list.get(9).getLayoutX()), (int) Math.round(list.get(9).getLayoutY()), (int) Math.round(list.get(9).getWidth()), (int) Math.round(list.get(9).getHeight()), path)));
                payment.setTakerName(Outils.Recognition.recognition((int) Math.round(list.get(10).getLayoutX()), (int) Math.round(list.get(10).getLayoutY()), (int) Math.round(list.get(10).getWidth()), (int) Math.round(list.get(10).getHeight()), path));
                payment.setOccupationTaker(Outils.Recognition.recognition((int) Math.round(list.get(11).getLayoutX()), (int) Math.round(list.get(11).getLayoutY()), (int) Math.round(list.get(11).getWidth()), (int) Math.round(list.get(11).getHeight()), path));

                System.err.println("");
                list.clear();

                directionFld.setText(payment.getDirection());
                wilayaFld.setText(payment.getWilaya());
                inspectionFld.setText(payment.getInspection());
                recetteFld.setText(payment.getRecette());
                ProprietaireFld.setText(payment.getLessorName());
                proprAdressFld.setText(payment.getLessorAdress());
                nifFld.setText(payment.getNif());
                articleFld.setText(payment.getNbrAtricle());
                adresseLoueFld.setText(payment.getTakerAdress());
                montantFld.setText(String.valueOf(payment.getAmount()));
                preneurFld.setText(payment.getTakerName());

                if (payment.getOccupationTaker() != null) {

                    occupationCombo.setValue("Etudaint");
                } else {
                    occupationCombo.setValue("Autres");

                }

                /*  AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/Paiements/Correction.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setUserData(payment);
                stage.show();*/
            } catch (TesseractException ex) {

                System.out.println(ex.getLocalizedMessage());

            }

        });

        save.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            String direction = directionFld.getText();
            String wilaya = wilayaFld.getText();
            String inspection = inspectionFld.getText();
            String recette = recetteFld.getText();
            String propr = ProprietaireFld.getText();
            String proprAdress = proprAdressFld.getText();
            String nif = nifFld.getText();
            String article = articleFld.getText();
            String adressLoue = adresseLoueFld.getText();
            String montant = montantFld.getText();
            String preneur = preneurFld.getText();
            String occupation = occupationCombo.getValue();

            if (direction.isEmpty() || wilaya.isEmpty() || inspection.isEmpty()
                    || recette.isEmpty() || propr.isEmpty() || proprAdress.isEmpty()
                    || nif.isEmpty() || article.isEmpty() || adressLoue.isEmpty()
                    || montant.isEmpty() || preneur.isEmpty() || occupation.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir toutes les données");
                alert.showAndWait();

            } else {
                insert();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Ajouté avec succès");
                alert.showAndWait();

            }

        });

        edit.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            String direction = directionFld.getText();
            String wilaya = wilayaFld.getText();
            String inspection = inspectionFld.getText();
            String recette = recetteFld.getText();
            String propr = ProprietaireFld.getText();
            String proprAdress = proprAdressFld.getText();
            String nif = nifFld.getText();
            String article = articleFld.getText();
            String adressLoue = adresseLoueFld.getText();
            String montant = montantFld.getText();
            String preneur = preneurFld.getText();
            String occupation = occupationCombo.getValue();

            if (direction.isEmpty() || wilaya.isEmpty() || inspection.isEmpty()
                    || recette.isEmpty() || propr.isEmpty() || proprAdress.isEmpty()
                    || nif.isEmpty() || article.isEmpty() || adressLoue.isEmpty()
                    || montant.isEmpty() || preneur.isEmpty() || occupation.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Selectionner un document avant de la verification ");
                alert.showAndWait();

            } else {

                getIdControle(articleFld.getText());
                getIds(idContrat);
                getProprietorId();
                getLocataireData();
                getProprietorData();

            }

        });
    }

    private void insert() {
        try {

            query = "INSERT INTO `avis_versement`(`nom_bailleur`, `adresse_bailleur`, `n_id_fiscal`, `n_articlage`, `adresse_du_bien`, `montant`, `nom_preneur`, `occupation_preneur`, `id_fiche_contr`) VALUES (?,?,?,?,?,?,?,?,?)";

            connection = DbConnect.getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ProprietaireFld.getText());
            preparedStatement.setString(2, proprAdressFld.getText());
            preparedStatement.setString(3, nifFld.getText());
            preparedStatement.setString(4, articleFld.getText());
            preparedStatement.setString(5, adresseLoueFld.getText());
            preparedStatement.setFloat(6, Float.valueOf(montantFld.getText()));
            preparedStatement.setString(7, preneurFld.getText());
            preparedStatement.setString(8, occupationCombo.getValue());
            preparedStatement.setInt(9, getIdControle(articleFld.getText()));

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddProprietorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getIdControle(String article) {
        int i = -1;

        try {

            String Sql = "SELECT * FROM `fiche_de_control` WHERE `Article_imposition`=" + article;
            Connection connection2 = (Connection) DbConnect.getConnect();
            PreparedStatement preparedStatement2 = (PreparedStatement) connection2.prepareStatement(Sql);
            ResultSet resultSet2 = (ResultSet) preparedStatement2.executeQuery();
            resultSet2.next();
            i = resultSet2.getInt(1);
            idContrat = resultSet2.getInt(15);

            String direction = resultSet2.getString(2);
            String inspection = resultSet2.getString(3);
            String recette = resultSet2.getString(4);
            String wilaya = resultSet2.getString(9);
            String nif = resultSet2.getString(8);

            directionFld.setText(direction);
            inspectionFld.setText(inspection);
            recetteFld.setText(recette);
            wilayaFld.setText(wilaya);
            nifFld.setText(nif);

            return i;
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Cette propriete n'existe pas vérifier le numéro d'article");
            alert.showAndWait();
        }
        return i;
    }

    public void getIds(int id) {

        try {
            String Sql4 = "SELECT `id`, `id_benef`, `id_fiche_hab`, `type_contr`, `date`, `date_fin`, `montant`, `n_acie`, `periodes_imposition` FROM `contrat` WHERE  `id`=" + id;
            Connection connection4 = (Connection) DbConnect.getConnect();
            PreparedStatement preparedStatement4 = (PreparedStatement) connection4.prepareStatement(Sql4);
            ResultSet resultSet4 = (ResultSet) preparedStatement4.executeQuery();
            resultSet4.next();
            propertyId = resultSet4.getInt("id_fiche_hab");
            locataireId = resultSet4.getInt("id_benef");
            String period = resultSet4.getString("periodes_imposition");

        } catch (SQLException ex) {
            Logger.getLogger(ScanFileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getLocataireData() {
        try {
            String Sql5 = "SELECT * FROM `beneficiaire` WHERE `id`=" + locataireId;
            Connection connection5 = (Connection) DbConnect.getConnect();
            PreparedStatement preparedStatement5 = (PreparedStatement) connection5.prepareStatement(Sql5);
            ResultSet resultSet5 = (ResultSet) preparedStatement5.executeQuery();
            resultSet5.next();
            String preneur = resultSet5.getString(2);
            preneurFld.setText(preneur);
        } catch (SQLException ex) {
            Logger.getLogger(ScanFileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getProprietorId() {
        try {
            String Sql6 = "SELECT * FROM `fiche_habitation` WHERE `id` =" + propertyId;
            Connection connection6 = (Connection) DbConnect.getConnect();
            PreparedStatement preparedStatement6 = (PreparedStatement) connection6.prepareStatement(Sql6);
            ResultSet resultSet6 = (ResultSet) preparedStatement6.executeQuery();
            resultSet6.next();

            proprietorId = resultSet6.getInt(22);
            String adress = resultSet6.getString(21);

            adresseLoueFld.setText(adress);

        } catch (SQLException ex) {
            Logger.getLogger(ScanFileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getProprietorData() {
        try {
            String Sql7 = "SELECT * FROM `proprietaire` WHERE `id`=" + proprietorId;
            Connection connection7 = (Connection) DbConnect.getConnect();
            PreparedStatement preparedStatement7 = (PreparedStatement) connection7.prepareStatement(Sql7);
            ResultSet resultSet7 = (ResultSet) preparedStatement7.executeQuery();
            resultSet7.next();

            String proprietorName = resultSet7.getString(2);
            String proprietorAdress = resultSet7.getString(4);
            ProprietaireFld.setText(proprietorName);
            proprAdressFld.setText(proprietorAdress);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("verification avec succès");
            alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(ScanFileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
