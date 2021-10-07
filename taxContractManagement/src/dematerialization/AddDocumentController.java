/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dematerialization;

import Outils.CreateDataset;
import Outils.Recognition;
import Outils.foldersnameList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import net.sourceforge.tess4j.TesseractException;

/**
 * FXML Controller class
 *
 * @author Deeplight
 */
public class AddDocumentController implements Initializable {

    @FXML
    private JFXButton Parcourir;
    @FXML
    private JFXButton learn;
    @FXML
    private JFXTextField width_field;
    @FXML
    private JFXTextField heith_field;
    @FXML
    private JFXTextField x;
    @FXML
    private JFXTextField y;
    @FXML
    private JFXTextField imgw;
    @FXML
    private JFXTextField imgh;
    @FXML
    private JFXTextArea Ocr_TextArea;
    @FXML
    private ComboBox Combobox;
    @FXML
    private ScrollPane scrollp1;
    @FXML
    private ImageView imageView;
    
    private Group scrollContent;
    public static String path;
    private final Rectangle zone = new Rectangle();
    private final Light.Point anchor = new Light.Point();
    static int getx, gety, getwidth, getheight;
    private Group group=new Group();
    FileChooser Brows=new FileChooser();
    File file;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        group = new Group(imageView);
        scrollContent = new Group(group);
        scrollp1.setContent(scrollContent);

        Parcourir.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{

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

                Combobox.setItems(foldersnameList.list);

            } catch (FileNotFoundException fileNotFoundException) {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("fileNotFoundException");
                alert.setContentText(fileNotFoundException.getLocalizedMessage());
                alert.show();
            }

        });

        imageView.setOnMouseMoved((e) -> {
            anchor.setX(e.getX());
            anchor.setY(e.getY());
            int agetx = (int) Math.round(anchor.getX());
            int agety = (int) Math.round(anchor.getY());

        });
        imageView.setOnMousePressed(event -> {
            //whene you clicked at imageview delete the rectongle zone
            group.getChildren().remove(zone);
            //initialiser les X,Y,Height,Width de
            zone.setWidth(0);
            zone.setHeight(0);
            anchor.setX(event.getX());
            anchor.setY(event.getY());
            zone.setX(event.getX());
            zone.setY(event.getY());
            zone.setFill(Color.rgb(0, 0, 0, 0)); // transparent color
            zone.setStroke(Color.valueOf("#0746A6")); // border
            zone.getStrokeDashArray();
            group.getChildren().add(zone);
        });
        imageView.setOnMouseDragged(event -> {
            zone.setWidth(Math.abs(event.getX() - anchor.getX()));
            zone.setHeight(Math.abs(event.getY() - anchor.getY()));
            zone.setX(Math.min(anchor.getX(), event.getX()));
            zone.setY(Math.min(anchor.getY(), event.getY()));
        });
        imageView.setOnMouseReleased(event -> {
            // Do what you want with selection's properties here
            System.out.printf("X: %.2f, Y: %.2f, Width: %.2f, Height: %.2f%n",zone.getX(), zone.getY(), zone.getWidth(), zone.getHeight());
            getx = (int) Math.round(zone.getX());
            gety = (int) Math.round(zone.getY());
            getwidth = (int) Math.round(zone.getWidth());
            getheight = (int) Math.round(zone.getHeight());
            /* values from double to int */
            width_field.setText(String.valueOf(getwidth));
            heith_field.setText(String.valueOf(getheight));
            x.setText(String.valueOf(getx));
            y.setText(String.valueOf(gety));
            //Do ocr to the rectangle zone
            if (getwidth != 0 && getheight != 0) {
                String Result_zone_ocr = null;
                try {
                    System.out.println("width :"+getwidth+"height :"+getheight);
                    Result_zone_ocr = Recognition.recognition(getx, gety, getwidth, getheight, path);
                    System.out.println(Result_zone_ocr);
                    Ocr_TextArea.setStyle("-fx-text-fill: darkblue ;" + "-fx-font-weight: 700;");
                    Ocr_TextArea.setText(Result_zone_ocr);
                } catch (TesseractException e) { e.printStackTrace();}
            } else {
                Ocr_TextArea.setStyle("-fx-text-fill: red ;" + "-fx-font-weight: 700;");
                Ocr_TextArea.setText(" Null !! ");
                System.out.println(" null !!! ");
            }
        });

        learn.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{

            String fieldname =Combobox.getSelectionModel().getSelectedItem().toString();

            CreateDataset.addtoCsv((int) imageView.getFitWidth(), (int) zone.getWidth(),"C:\\Users\\Deeplight\\OneDrive\\Documents\\NetBeansProjects\\taxContractManagement-main\\taxContractManagement\\src\\Dataset\\G51\\Dataset\\"+fieldname+"\\Width.csv");
            CreateDataset.addtoCsv((int) imageView.getFitWidth(), (int) zone.getX(),"C:\\Users\\Deeplight\\OneDrive\\Documents\\NetBeansProjects\\taxContractManagement-main\\taxContractManagement\\src\\Dataset\\G51\\Dataset\\"+fieldname+"\\Xoffset.csv");
            CreateDataset.addtoCsv((int) imageView.getFitHeight(), (int) zone.getHeight(),"C:\\Users\\Deeplight\\OneDrive\\Documents\\NetBeansProjects\\taxContractManagement-main\\taxContractManagement\\src\\Dataset\\G51\\Dataset"+fieldname+"\\Height.csv");
            CreateDataset.addtoCsv((int) imageView.getFitHeight(), (int) zone.getY(),"C:\\Users\\Deeplight\\OneDrive\\Documents\\NetBeansProjects\\taxContractManagement-main\\taxContractManagement\\src\\Dataset\\G51\\Dataset"+fieldname+"\\Yoffset.csv");

        });
    }
    }    
    

