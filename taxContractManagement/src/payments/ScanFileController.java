package payments;

import Outils.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.ResultSetImpl;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import helpres.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sourceforge.tess4j.TesseractException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.event.EventHandler;
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
    private TextField ProprietaireFld;
    @FXML
    private TextField montantFld;
    @FXML
    private TextField directionFld;
    @FXML
    private TextField wilayaFld;
    @FXML
    private JFXComboBox<String> occupationCombo;
    @FXML
    private TextField inspectionFld;
    @FXML
    private TextField recetteFld;
    @FXML
    private TextField proprAdressFld;
    @FXML
    private TextField nifFld;
    @FXML
    private TextField articleFld;
    @FXML
    private TextField adresseLoueFld;
    @FXML
    private TextField preneurFld;
    @FXML
    private JFXButton save;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Payment payment = null;

    int controleID;
    @FXML
    private JFXButton edit;
    @FXML
    private FontAwesomeIconView parcourIcon;
    @FXML
    private MaterialDesignIconView scanIcon;
    @FXML
    private FontAwesomeIconView editIcon;
    @FXML
    private FontAwesomeIconView saveIcon;
    @FXML
    private FontAwesomeIconView fileIcon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        occupationCombo.getItems().addAll("Etudaint", "Autres");

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
        Parcourir.setTooltip(new Tooltip("sélectionner un document"));
        Scanner.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    payment = new Payment();
                    //     List<String> Champlist=new ArrayList<>();

                    /* int i = 0;
                    for (Rectangle R : list) {
                        System.out.println(Outils.Recognition.recognition((int) Math.round(list.get(i).getLayoutX()), (int) Math.round(list.get(i).getLayoutY()), (int) Math.round(list.get(i).getWidth()), (int) Math.round(list.get(i).getHeight()), path));
                        i++;
                    }*/
                    //Champlist.add(Outils.Recognition.recognition((int)list.get(4).getX(),(int)list.get(4).getY(),(int)list.get(4).getWidth(),(int)list.get(4).getHeight(),path));
                    payment.setDirection(Outils.Recognition.recognition((int) Math.round(list.get(0).getLayoutX()), (int) Math.round(list.get(0).getLayoutY()), (int) Math.round(list.get(0).getWidth()), (int) Math.round(list.get(0).getHeight()), path));
                    payment.setWilaya(Outils.Recognition.recognition((int) Math.round(list.get(1).getLayoutX()), (int) Math.round(list.get(1).getLayoutY()), (int) Math.round(list.get(1).getWidth()), (int) Math.round(list.get(1).getHeight()), path));
                    payment.setInspection(Outils.Recognition.recognition((int) Math.round(list.get(2).getLayoutX()), (int) Math.round(list.get(2).getLayoutY()), (int) Math.round(list.get(2).getWidth()), (int) Math.round(list.get(2).getHeight()), path));
                    payment.setRecette(Outils.Recognition.recognition((int) Math.round(list.get(3).getLayoutX()), (int) Math.round(list.get(3).getLayoutY()), (int) Math.round(list.get(3).getWidth()), (int) Math.round(list.get(3).getHeight()), path));
                    payment.setLessorName(Outils.Recognition.recognition((int) Math.round(list.get(4).getLayoutX()), (int) Math.round(list.get(4).getLayoutY()), (int) Math.round(list.get(4).getWidth()) / 2, (int) Math.round(list.get(4).getHeight()), path));
                    payment.setLessorAdress(Outils.Recognition.recognition((int) Math.round(list.get(5).getLayoutX()), (int) Math.round(list.get(5).getLayoutY()), (int) Math.round(list.get(5).getWidth()) / 2, (int) Math.round(list.get(5).getHeight()), path));
                    payment.setNif(Outils.Recognition.recognition((int) Math.round(list.get(6).getLayoutX()), (int) Math.round(list.get(6).getLayoutY()), (int) Math.round(list.get(6).getWidth()) / 2, (int) Math.round(list.get(6).getHeight()), path));
                    payment.setNbrAtricle(Outils.Recognition.recognition((int) Math.round(list.get(7).getLayoutX()), (int) Math.round(list.get(7).getLayoutY()), (int) Math.round(list.get(7).getWidth()) / 2, (int) Math.round(list.get(7).getHeight()), path));
                    payment.setTakerAdress(Outils.Recognition.recognition((int) Math.round(list.get(8).getLayoutX()), (int) Math.round(list.get(8).getLayoutY()), (int) Math.round(list.get(8).getWidth()) / 2, (int) Math.round(list.get(8).getHeight()), path));
                    payment.setAmount(Float.valueOf(Outils.Recognition.recognition((int) Math.round(list.get(9).getLayoutX()), (int) Math.round(list.get(9).getLayoutY()), (int) Math.round(list.get(9).getWidth()) / 2, (int) Math.round(list.get(9).getHeight()), path)));
                    payment.setTakerName(Outils.Recognition.recognition((int) Math.round(list.get(10).getLayoutX()), (int) Math.round(list.get(10).getLayoutY()), (int) Math.round(list.get(10).getWidth()) / 2, (int) Math.round(list.get(10).getHeight()), path));
                    payment.setOccupationTaker(Outils.Recognition.recognition((int) Math.round(list.get(11).getLayoutX()), (int) Math.round(list.get(11).getLayoutY()), (int) Math.round(list.get(11).getWidth()), (int) Math.round(list.get(11).getHeight()), path));

                    directionFld.setText(payment.getDirection());
                    wilayaFld.setText(payment.getWilaya());
                    inspectionFld.setText(payment.getInspection());
                    recetteFld.setText(payment.getRecette());
                    ProprietaireFld.setText(payment.getLessorName());
                    proprAdressFld.setText(payment.getLessorAdress());
                    nifFld.setText(payment.getNif().toString());
                    articleFld.setText(payment.getNbrAtricle().toString());
                    adresseLoueFld.setText(payment.getTakerAdress());
                    montantFld.setText(String.valueOf(payment.getAmount()));
                    preneurFld.setText(payment.getTakerName());
                    System.out.println("occupation" + payment.getOccupationTaker());
                    if (payment.getOccupationTaker() != null) {
                        occupationCombo.setValue("Etudaint");
                    } else {
                        occupationCombo.setValue("Autres");
                    }

                    list.clear();

                    /* AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("/Paiements/Correction.fxml"));
                    Scene scene=new Scene(anchorPane);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.setUserData(payment);
                    stage.show();*/
                } catch (TesseractException ex) {

                    System.out.println(ex.getLocalizedMessage());

                }
            }
        });
        Scanner.setTooltip(new Tooltip("Extraction de données"));

        edit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Controle controle = new Controle();
                
            }

        });
        edit.setTooltip(new Tooltip("Rectifiez les fautes"));

        save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (payment != null) {
                    insert();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Ajouté avec succès");
                    alert.showAndWait();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir toutes les données");
                    alert.showAndWait();
                }
            }
        });
        save.setTooltip(new Tooltip("La sauvegarde des données"));
        

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
    
    
    public  Controle getDataControle(String article) {
        
        
        try {

                    query = "SELECT\n"
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
                            + "ORDER BY\n"
                            + "    fiche_de_control.id;";
                    preparedStatement = connection.prepareStatement(query);
                    resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        c.setId(resultSet.getInt("fiche_de_control.id"));
                        c.setContractId(resultSet.getInt("contrat.id"));
                        c.setContractType(resultSet.getString("contrat.type_contr"));
                        c.setStartDate(resultSet.getDate("contrat.date"));
                        c.setEndDate(resultSet.getDate("contrat.date_fin"));
                        c.setMontant(resultSet.getFloat("contrat.montant"));
                        c.setNumAcie(resultSet.getString("contrat.n_acie"));
                        c.setPeriodImpot(resultSet.getString("contrat.periodes_imposition"));
                        c.setFicheHabId(resultSet.getInt("contrat.id_fiche_hab"));

                        c.setProprId(resultSet.getInt("proprietaire.id"));
                        c.setProprName(resultSet.getString("proprietaire.nom_prenom_or_RS"));
                        c.setProprbirth(resultSet.getDate("proprietaire.date_nss"));
                        c.setProprCommuneBirth(resultSet.getString("proprietaire.commune"));
                        c.setProprWilayaBirth(resultSet.getString("proprietaire.wilaya"));
                        c.setProprPere(resultSet.getString("proprietaire.pere"));
                        c.setProprMere(resultSet.getString("proprietaire.mere"));
                        c.setProprNationalite(resultSet.getString("proprietaire.nationalite"));
                        c.setAdress(resultSet.getString("proprietaire.adress"));
                        c.setProprPhone(resultSet.getString("proprietaire.telephone"));

                        c.setBenefID(resultSet.getInt("beneficiaire.id"));
                        c.setBenefName(resultSet.getString("beneficiaire.nom_prenom_or_RS"));
                        c.setBenefBirth(resultSet.getDate("beneficiaire.date_nss"));
                        c.setBenefCommuneBirth(resultSet.getString("beneficiaire.commune"));
                        c.setBenefWilayaBirth(resultSet.getString("beneficiaire.wilaya"));
                        c.setBenefPere(resultSet.getString("beneficiaire.prenom_pere"));
                        c.setBenefMere(resultSet.getString("beneficiaire.nom_mere"));
                        c.setBenefNationalite(resultSet.getString("beneficiaire.nationalite"));
                        c.setBenefAdress(resultSet.getString("beneficiaire.adresse_domicile"));

                        c.setInspection(resultSet.getString("fiche_de_control.inscpection"));
                        c.setRecette(resultSet.getString("fiche_de_control.Recette"));
                        c.setAnnee(resultSet.getString("fiche_de_control.Annee"));
                        c.setDesignation(resultSet.getString("fiche_de_control.Designation"));
                        c.setNis(resultSet.getString("fiche_de_control.NiS"));
                        c.setNif(resultSet.getString("fiche_de_control.NIF"));
                        c.setWilaya(resultSet.getString("fiche_de_control.Wilaya"));
                        c.setActivite(resultSet.getString("fiche_de_control.Activity"));
                        c.setCodeActivite(resultSet.getInt("fiche_de_control.Code_d_activity"));
                        c.setFormJuridique(resultSet.getString("fiche_de_control.Forme_Juridique"));
                        c.setAdress(resultSet.getString("fiche_de_control.Adress"));
                        c.setArticleImpots(resultSet.getString("fiche_de_control.Article_imposition"));

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(FileControllerController.class.getName()).log(Level.SEVERE, null, ex);
                }
        return c;
        
       
    }
}
