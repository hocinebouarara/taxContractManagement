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
                    || montant.isEmpty() || preneur.isEmpty() || occupation.isEmpty() ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Selectionner un document avant de la verification ");
                alert.showAndWait();

            } else {

                getDataControle(article);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("verification avec succès");
                alert.showAndWait();

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

    public static int getIdControle(String article) {
        int i = -1;
        try {
            String Sql = "SELECT `id` FROM `fiche_de_control` WHERE `Article_imposition`=" + article;
            Connection connection = (Connection) DbConnect.getConnect();
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(Sql);
            ResultSetImpl resultSet = (ResultSetImpl) preparedStatement.executeQuery();
            resultSet.next();
            i = resultSet.getInt(1);
            System.out.println("payments.ScanFileController.getLastIdPayment()" + i);

        } catch (SQLException e) {

        }
        return i;
    }

    public void getDataControle(String article) {
        try {

            String query1 = "SELECT\n"
                    + "    fiche_de_control.id,\n"
                    + "    contrat.id,\n"
                    + "    contrat.type_contr,\n"
                    + "    contrat.date,\n"
                    + "    contrat.date_fin,\n"
                    + "    contrat.montant,\n"
                    + "    contrat.n_acie,\n"
                    + "    contrat.periodes_imposition,\n"
                    + "    contrat.id_fiche_hab,\n"
                    + "    proprietaire.id,\n"
                    + "    proprietaire.nom_prenom_or_RS,\n"
                    + "    proprietaire.date_nss,\n"
                    + "    proprietaire.commune,\n"
                    + "    proprietaire.wilaya,\n"
                    + "    proprietaire.pere,\n"
                    + "    proprietaire.mere,\n"
                    + "    proprietaire.nationalite,\n"
                    + "    proprietaire.telephone,\n"
                    + "    proprietaire.adress,\n"
                    + "    beneficiaire.id,\n"
                    + "    beneficiaire.nom_prenom_or_RS,\n"
                    + "    beneficiaire.date_nss,\n"
                    + "    beneficiaire.commune,\n"
                    + "    beneficiaire.wilaya,\n"
                    + "    beneficiaire.prenom_pere,\n"
                    + "    beneficiaire.nom_mere,\n"
                    + "    beneficiaire.nationalite,\n"
                    + "    beneficiaire.adresse_domicile,\n"
                    + "    fiche_de_control.direction,\n"
                    + "    fiche_de_control.inscpection,\n"
                    + "    fiche_de_control.Recette,\n"
                    + "    fiche_de_control.Annee,\n"
                    + "    fiche_de_control.Designation,\n"
                    + "    fiche_de_control.NiS,\n"
                    + "    fiche_de_control.NIF,\n"
                    + "    fiche_de_control.Wilaya,\n"
                    + "    fiche_de_control.Activity,\n"
                    + "    fiche_de_control.Code_d_activity,\n"
                    + "    fiche_de_control.Forme_Juridique,\n"
                    + "    fiche_de_control.Adress,\n" + "    fiche_de_control.Article_imposition\n"
                    + "FROM\n"
                    + "    fiche_de_control\n"
                    + "INNER JOIN contrat ON contrat.id = fiche_de_control.id_fiscal\n"
                    + "INNER JOIN beneficiaire ON beneficiaire.id = contrat.id_benef\n"
                    + "INNER JOIN fiche_habitation ON fiche_habitation.id = contrat.id_fiche_hab\n"
                    + "INNER JOIN proprietaire ON fiche_habitation.id_propr = proprietaire.id\n"
                    + "  WHERE  `Article_imposition`=" + article+";";
            
            
            
            
            PreparedStatement preparedStatement1= connection.prepareStatement(query1);
             ResultSet resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();

            directionFld.setText(resultSet1.getString("fiche_de_control.direction"));
            wilayaFld.setText(resultSet1.getString("fiche_de_control.Wilaya"));
            inspectionFld.setText(resultSet1.getString("fiche_de_control.inscpection"));
            recetteFld.setText(resultSet1.getString("fiche_de_control.Recette"));
            ProprietaireFld.setText(resultSet1.getString("proprietaire.nom_prenom_or_RS"));
            proprAdressFld.setText(resultSet1.getString("proprietaire.adress"));
            nifFld.setText(resultSet1.getString("fiche_de_control.NIF"));
            articleFld.setText(resultSet1.getString("fiche_de_control.Article_imposition"));
            adresseLoueFld.setText(resultSet1.getString("fiche_de_control.Adress"));
            preneurFld.setText(resultSet1.getString("beneficiaire.nom_prenom_or_RS"));

            /* c.setId(resultSet.getInt("fiche_de_control.id"));
            c.setContractId(resultSet.getInt("contrat.id"));
            c.setContractType(resultSet.getString("contrat.type_contr"));
            c.setStartDate(resultSet.getDate("contrat.date"));
            c.setEndDate(resultSet.getDate("contrat.date_fin"));
            c.setMontant(resultSet.getFloat("contrat.montant"));
            c.setNumAcie(resultSet.getString("contrat.n_acie"));
            c.setPeriodImpot(resultSet.getString("contrat.periodes_imposition"));
            c.setFicheHabId(resultSet.getInt("contrat.id_fiche_hab"));*/
        } catch (SQLException ex) {
            Logger.getLogger(FileControllerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
