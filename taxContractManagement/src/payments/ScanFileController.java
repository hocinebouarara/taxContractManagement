package payments;

import Outils.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import models.Payment;

public class ScanFileController implements Initializable {

    @FXML
    private TableView<?> Table;

    @FXML
    private JFXButton Parcourir;

    @FXML
    private JFXButton Scanner;

    @FXML
    private TextField search;

    @FXML
    private ImageView imageView;

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
    private ScrollPane scrollp1;

    @FXML
    private JFXTextField imgh,imgw;

    @FXML
    private AnchorPane anchore;


    private Group scrollContent=new Group();
    public static String path;
    private final Rectangle zone = new Rectangle();
    @FXML
    private final Light.Point anchor = new Light.Point();
    static int getx, gety, getwidth, getheight;
    private Group group=new Group();
    FileChooser Brows=new FileChooser();
    File file;

    List<Rectangle> list=new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Parcourir.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
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
                FileChooser.ExtensionFilter images=new FileChooser.ExtensionFilter("image","*PNG","*JPG","*GIF");
                Brows.setInitialDirectory(file.getParentFile());
                FileInputStream stream=new FileInputStream(file.getAbsolutePath());
                path=file.getAbsolutePath();
                Brows.getExtensionFilters().add(images);
                Image image=new Image(stream);
                imageView.setFitWidth(image.getWidth());
                imageView.setFitHeight(image.getHeight());
                imageView.setImage(image);
                imgh.setText("Height :"+image.getHeight());
                imgw.setText("Width :"+image.getWidth());

                for (String s: foldersnameList.list) {
                    Rectangle rectangle=LinearRegression.posinnement(s,imageView);
                    rectangle.setFill(Color.rgb(0,0,0,0));
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
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("fileNotFoundException");
                alert.setContentText(fileNotFoundException.getLocalizedMessage());
                alert.show();
            }

        });
        Scanner.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
            try {
                Payment payment=new Payment();
            //     List<String> Champlist=new ArrayList<>();

                int i=0;
                for (Rectangle R:list) {
                    System.out.println(Outils.Recognition.recognition((int)Math.round(list.get(i).getLayoutX()),(int)Math.round(list.get(i).getLayoutY()),(int)Math.round(list.get(i).getWidth()),(int)Math.round(list.get(i).getHeight()),path));
                    i++;
                }

                //Champlist.add(Outils.Recognition.recognition((int)list.get(4).getX(),(int)list.get(4).getY(),(int)list.get(4).getWidth(),(int)list.get(4).getHeight(),path));
                payment.setDeriction   (Outils.Recognition.recognition((int)Math.round(list.get(0).getLayoutX()),(int)Math.round(list.get(0).getLayoutY()),(int)Math.round(list.get(0).getWidth()),(int)Math.round(list.get(0).getHeight()),path));
                payment.setWilaya      (Outils.Recognition.recognition((int)Math.round(list.get(1).getLayoutX()),(int)Math.round(list.get(1).getLayoutY()),(int)Math.round(list.get(1).getWidth()),(int)Math.round(list.get(1).getHeight()),path));
                payment.setInspectoin  (Outils.Recognition.recognition((int)Math.round(list.get(2).getLayoutX()),(int)Math.round(list.get(2).getLayoutY()),(int)Math.round(list.get(2).getWidth()),(int)Math.round(list.get(2).getHeight()),path));
                payment.setRecette     (Outils.Recognition.recognition((int)Math.round(list.get(3).getLayoutX()),(int)Math.round(list.get(3).getLayoutY()),(int)Math.round(list.get(3).getWidth()),(int)Math.round(list.get(3).getHeight()),path));
                payment.setLessorName  (Outils.Recognition.recognition((int)Math.round(list.get(4).getLayoutX()),(int)Math.round(list.get(4).getLayoutY()),(int)Math.round(list.get(4).getWidth()),(int)Math.round(list.get(4).getHeight()),path));
                payment.setLessorAdress(Outils.Recognition.recognition((int)Math.round(list.get(5).getLayoutX()),(int)Math.round(list.get(5).getLayoutY()),(int)Math.round(list.get(5).getWidth()),(int)Math.round(list.get(5).getHeight()),path));
                payment.setNif         (Outils.Recognition.recognition((int)Math.round(list.get(6).getLayoutX()),(int)Math.round(list.get(6).getLayoutY()),(int)Math.round(list.get(6).getWidth()),(int)Math.round(list.get(6).getHeight()),path));
                payment.setNbrAtricle  (Outils.Recognition.recognition((int)Math.round(list.get(7).getLayoutX()),(int)Math.round(list.get(7).getLayoutY()),(int)Math.round(list.get(7).getWidth()),(int)Math.round(list.get(7).getHeight()),path));
                payment.setTakerAdress (Outils.Recognition.recognition((int)Math.round(list.get(8).getLayoutX()),(int)Math.round(list.get(8).getLayoutY()),(int)Math.round(list.get(8).getWidth()),(int)Math.round(list.get(8).getHeight()),path));
                payment.setAmount      (Float.valueOf(Outils.Recognition.recognition((int)Math.round(list.get(9).getLayoutX()),(int)Math.round(list.get(9).getLayoutY()),(int)Math.round(list.get(9).getWidth()),(int)Math.round(list.get(9).getHeight()),path)));
                payment.setTakerName   (Outils.Recognition.recognition((int)Math.round(list.get(10).getLayoutX()),(int)Math.round(list.get(10).getLayoutY()),(int)Math.round(list.get(10).getWidth()),(int)Math.round(list.get(10).getHeight()),path));
                payment.setOccupationTaker(Outils.Recognition.recognition((int)Math.round(list.get(11).getLayoutX()),(int)Math.round(list.get(11).getLayoutY()),(int)Math.round(list.get(11).getWidth()),(int)Math.round(list.get(11).getHeight()),path));

                System.err.println("");
                list.clear();

                AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("/Paiements/Correction.fxml"));
                Scene scene=new Scene(anchorPane);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.setUserData(payment);
                stage.show();

            }catch (IOException | TesseractException ex){

                System.out.println(ex.getLocalizedMessage());

            }
        });
    }
}