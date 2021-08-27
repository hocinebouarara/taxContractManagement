/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dematerialization;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import static dematerialization.DematerializationViewController.facturePath;
import helpres.DataBase;
import helpres.DbConnect;
import helpres.Recongition;
import java.sql.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Light;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Abdullah
 */
public class ImageRecognitionController implements Initializable {

    @FXML
    private Label lbl;
    @FXML
    private ImageView img;
    @FXML
    private Label lv1;
    @FXML
    private Label lbl1;
    @FXML
    private JFXTextField x;
    @FXML
    private JFXTextField y;
    @FXML
    private JFXTextArea Ocr_TextArea;

    @FXML
    private JFXButton open;
    @FXML
    private JFXButton scn;
    @FXML
    private Button demat;
    @FXML
    private JFXTextField width_field;
    @FXML
    private JFXTextField heith_field;
    @FXML
    private ScrollPane scrollp1;
    @FXML
    private JFXTextField four;
    @FXML
    private JFXButton Next;
    @FXML
    private JFXTextField zone_id;
    @FXML
    private JFXTextField nbr_Zone;
    @FXML
    private HBox hbox1;
    @FXML
    private JFXButton reset;
    @FXML
    private JFXButton full;
    private JFXButton do_ocr;
    @FXML
    private JFXTextField nbr_ligne;
    @FXML
    private JFXButton deviser;

    public static String path;
    //String path2="D:\\Factures\\1 001.jpg";
    private Image image;
    private FileInputStream inputstream;
    static int getx, gety, getwidth, getheight;
    static int lineNbr;
    static PreparedStatement preparedStatement;
    static Connection connection;
    static ResultSet resultSet = null;
    private final Rectangle zone = new Rectangle();
    private final Light.Point anchor = new Light.Point();
    public static String documentName;
    public static String fieldName;
    public String Table_champ;
    static String AutoID_Zone;
    static String AutoID_fourlocal;
    static String queryID_ZOne = "SELECT Id_Zone FROM zone_champ order BY "
            + "LENGTH(Id_Zone) DESC , Id_Zone DESC LIMIT 1";
    public static String query_Four = "SELECT id_four FROM fournisseur ORDER BY "
            + "LENGTH(id_four) DESC,id_four DESC LIMIT 1";
    public static int nbrZone;
    private String NBR;
    private Group group;
    private Group scrollContent;
    @FXML
    private JFXTextField ligne_id;
    @FXML
    private JFXButton Ajouter_four;
    @FXML
    private JFXRadioButton contractBtn;
    @FXML
    private JFXRadioButton avisVersementBtn;
    @FXML
    private JFXComboBox<String> paymentFlds;
    @FXML
    private JFXComboBox<String> contractsFlds;

    public ImageRecognitionController() {
        connection = (Connection) DbConnect.getConnect();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hbox1.setHgrow(scrollp1, Priority.ALWAYS);
        //initialiser les générateurAuto ID
        //AutoID_Zone = gen_id(4, "Zone1", queryID_ZOne, "Id_zone", AutoID_Zone);
        //AutoID_fourlocal = gen_id(4, "four1", query_Four, "id_four", AutoID_fourlocal);
        nbrZone = 0;
        NBR = (String.valueOf(nbrZone));
        four.setText(AutoID_fourlocal);
        zone_id.setText(AutoID_Zone);
        nbr_Zone.setText(NBR);
        lineNbr = 0;
        NBR = (String.valueOf(lineNbr));
        ligne_id.setText(NBR);
        //path initialized
        path = facturePath;
        lbl.setTextFill(Color.RED);
        lv1.setText(path);

        ToggleGroup groupbtn = new ToggleGroup();
        avisVersementBtn.setToggleGroup(groupbtn);
        avisVersementBtn.setSelected(true);
        contractBtn.setToggleGroup(groupbtn);
        paymentFlds.setVisible(true);
        contractsFlds.setVisible(false);

        avisVersementBtn.setOnMouseClicked((event) -> {
            paymentFlds.setVisible(true);
            contractsFlds.setVisible(false);
        });

        contractBtn.setOnMouseClicked((event) -> {
            paymentFlds.setVisible(false);
            contractsFlds.setVisible(true);
        });

        paymentFlds.getItems().addAll("Nom de Document","Nom et Prénom bailleur", "Adresse du bailleur", "Numéeo d'identification fiscale",
                "Numéro d'article foncier", "Adresse du bien loue", "Montant mensuel des loyers", "Nom et Prénom du preneur",
                "Occupation du preneur");

        paymentFlds.valueProperty().addListener((observable) -> {
            if (paymentFlds.getValue() != null) {
                fieldName = paymentFlds.getValue().toString();
            }
            System.out.println("the field selected is : " + fieldName);
        });

        //Loading image from URL 
        try {
            //inputstream = new FileInputStream(path);
            //image = new Image(inputstream);
            inputstream = new FileInputStream(path);
            image = new Image(inputstream);
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        //Image image = new Image(new FileInputStream("url for the image));
        System.out.println(path);
        img.setImage(image);
        img.setPreserveRatio(true);
        //Creer un group contient imageview dans un Scroller
        group = new Group(img);
        scrollContent = new Group(group);
        scrollp1.setContent(scrollContent);

        System.out.println("table champ selected is : " + Table_champ);

        //Button next to change champ de la table
        Next.setOnMouseClicked((MouseEvent event) -> {

        });

        //MouseActions On ImageView
        img.setOnMouseMoved((e) -> {
            anchor.setX(e.getX());
            anchor.setY(e.getY());
            int agetx = (int) Math.round(anchor.getX());
            int agety = (int) Math.round(anchor.getY());
            lbl1.setText("Mouse moved: X= " + agetx + ",  Y= " + agety);
            lbl1.setTextFill(Color.color(Math.random(), Math.random(), Math.random()));
        });
        img.setOnMousePressed(event -> {
            //whene you clicked at imageview delete the rectongle zone
            group.getChildren().remove(zone);
            //initialiser les X,Y,Height,Width de 
            zone.setWidth(0);
            zone.setHeight(0);
            anchor.setX(event.getX());
            anchor.setY(event.getY());
            zone.setX(event.getX());
            zone.setY(event.getY());
            zone.setFill(Color.rgb(255, 0, 0, 0.1)); // transparent color
            zone.setStroke(Color.CRIMSON); // border
            zone.getStrokeDashArray();
            group.getChildren().add(zone);
        });
        img.setOnMouseDragged(event -> {
            zone.setWidth(Math.abs(event.getX() - anchor.getX()));
            zone.setHeight(Math.abs(event.getY() - anchor.getY()));
            zone.setX(Math.min(anchor.getX(), event.getX()));
            zone.setY(Math.min(anchor.getY(), event.getY()));
        });
        img.setOnMouseReleased(event -> {
            // Do what you want with selection's properties here
            System.out.printf("X: %.2f, Y: %.2f, Width: %.2f, Height: %.2f%n",
                    zone.getX(), zone.getY(), zone.getWidth(), zone.getHeight());
            getx = (int) Math.round(zone.getX());
            gety = (int) Math.round(zone.getY());
            getwidth = (int) Math.round(zone.getWidth());
            getheight = (int) Math.round(zone.getHeight());
            /* values from double to int */
            width_field.setText(String.valueOf(getwidth));
            heith_field.setText(String.valueOf(getheight));
            x.setText(String.valueOf(getx));
            y.setText(String.valueOf(gety));
            try {//Do ocr to the rectangle zone
                if (getwidth != 0 && getheight != 0) {
                    String Result_zone_ocr = Recongition.recognition(getx, gety, getwidth, getheight, path);
                    System.out.println(Result_zone_ocr);
                    Ocr_TextArea.setStyle("-fx-text-fill: darkblue ;" + "-fx-font-weight: 700;");
                    Ocr_TextArea.setText(Result_zone_ocr);
                } else {
                    Ocr_TextArea.setStyle("-fx-text-fill: red ;" + "-fx-font-weight: 700;");
                    Ocr_TextArea.setText(" Null !! ");
                    System.out.println(" null !!! ");
                }
            } catch (TesseractException e1) {
                System.out.println(e1.getMessage());
            }
        });
        //Zoom
        scrollp1.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable,
                    Bounds oldValue, Bounds newValue) {
                scrollp1.setMinSize(newValue.getWidth(), newValue.getHeight());
            }
        });
        final double SCALE_DELTA = 1.1;
        group.setOnScroll((ScrollEvent event) -> {
            event.consume();
            if (event.getDeltaY() == 0) {
                return;
            }
            double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA
                    : 1 / SCALE_DELTA;
            // amount of scrolling in each direction in scrollContent coordinate
            // units
            Point2D scrollOffset = zoom.figureScrollOffset(scrollContent, scrollp1);
            group.setScaleX(group.getScaleX() * scaleFactor);
            group.setScaleY(group.getScaleY() * scaleFactor);
            // move viewport so that old center remains in the center after the
            // scaling
            zoom.repositionScroller(scrollContent, scrollp1, scaleFactor, scrollOffset);
        });
        // insert datamasque button
        scn.setOnMouseClicked((event) -> {
            //if (getwidth == null && ){

            //}
            String result = DataBase.insertMasqueData(documentName, fieldName,
                    getx, gety, getwidth, getheight, Table_champ, lineNbr);

            if (result.equals("Success")) {
                //AutoID_Zone = gen_id(4, "Zone1", queryID_ZOne, "Id_zone", AutoID_Zone);
                zone_id.setText(AutoID_Zone);
                nbrZone++;
                NBR = (String.valueOf(nbrZone));
                nbr_Zone.setText(NBR);
                ClearField();
            }
        });
    }

    public void ClearField() {
        width_field.clear();
        heith_field.clear();
        x.clear();
        y.clear();
        Ocr_TextArea.clear();
    }

    private void makeSameZone(int nbr_ligne) {
        int n = 0;
        while (n < nbr_ligne) {
            n++;
            group.getChildren().remove(zone);
            zone.setY(zone.getY() + zone.getHeight() + 1);
            gety = (int) Math.round(zone.getY() + zone.getHeight() + 1);
            y.setText(String.valueOf(gety));
            group.getChildren().add(zone);
            try {
                String Result_zone_ocr = Recongition.recognition(getx, gety, getwidth, getheight, path);
                System.out.println(Result_zone_ocr);
                Ocr_TextArea.setStyle("-fx-text-fill: darkblue ;" + "-fx-font-weight: 700;");
                Ocr_TextArea.setText(Result_zone_ocr);
                String result = DataBase.insertMasqueData(documentName, fieldName,
                        getx, gety, getwidth, getheight, Table_champ, lineNbr
                );

                if (result.equals("Success")) {
                    //AutoID_Zone = gen_id(4, "Zone1", queryID_ZOne, "Id_zone", AutoID_Zone);
                    zone_id.setText(AutoID_Zone);
                    nbrZone++;
                    NBR = (String.valueOf(nbrZone));
                    nbr_Zone.setText(NBR);
                    ClearField();
                }
            } catch (TesseractException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }

    public void devise_zone(int nbr_ligne) {
        lineNbr++;
        NBR = (String.valueOf(lineNbr));
        ligne_id.setText(NBR);
        group.getChildren().remove(zone);
        zone.setHeight(zone.getHeight() / nbr_ligne);
        getheight = (int) Math.round(zone.getHeight());
        heith_field.setText(String.valueOf(getheight));
        group.getChildren().add(zone);
        System.out.printf("X: %.2f, Y: %.2f, Width: %.2f, Height: %.2f%n",
                zone.getX(), zone.getY(), zone.getWidth(), zone.getHeight());
        try {
            String Result_zone_ocr = Recongition.recognition(getx, gety, getwidth, getheight, path);
            System.out.println(Result_zone_ocr);
            Ocr_TextArea.setStyle("-fx-text-fill: darkblue ;" + "-fx-font-weight: 700;");
            Ocr_TextArea.setText(Result_zone_ocr);

            String result = DataBase.insertMasqueData(documentName, fieldName,
                    getx, gety, getwidth, getheight, Table_champ, lineNbr);
            if (result.equals("Success")) {
                //AutoID_Zone = gen_id(4, "Zone1", queryID_ZOne, "Id_zone", AutoID_Zone);
                zone_id.setText(AutoID_Zone);
                nbrZone++;
                NBR = (String.valueOf(nbrZone));
                nbr_Zone.setText(NBR);
                ClearField();
                lineNbr++;
                NBR = (String.valueOf(lineNbr));
                ligne_id.setText(NBR);
            }
        } catch (TesseractException e1) {
            System.out.println(e1.getMessage());
            //Alert_class.Alert_fuction("ERROR", e1.getMessage(), "ERROR dialog");
        }

        int n = 1;
        while (n < nbr_ligne) {
            n++;
            group.getChildren().remove(zone);
            zone.setY(zone.getY() + zone.getHeight());
            gety = (int) Math.round(zone.getY());
            y.setText(String.valueOf(gety));
            group.getChildren().add(zone);
            try {
                String Result_zone_ocr = Recongition.recognition(getx, gety, getwidth, getheight, path);
                System.out.println(Result_zone_ocr);
                Ocr_TextArea.setStyle("-fx-text-fill: darkblue ;" + "-fx-font-weight: 700;");
                Ocr_TextArea.setText(Result_zone_ocr);
                String result = DataBase.insertMasqueData(documentName, fieldName,
                        getx, gety, getwidth, getheight, Table_champ, lineNbr);
                if (result.equals("Success")) {
                    //AutoID_Zone = gen_id(4, "Zone1", queryID_ZOne, "Id_zone", AutoID_Zone);
                    zone_id.setText(AutoID_Zone);
                    nbrZone++;
                    NBR = (String.valueOf(nbrZone));
                    nbr_Zone.setText(NBR);
                    ClearField();
                    lineNbr++;
                    NBR = (String.valueOf(lineNbr));
                    ligne_id.setText(NBR);
                }
            } catch (TesseractException e1) {
                System.out.println(e1.getMessage());
                //Alert_class.Alert_fuction("ERROR", e1.getMessage(), "ERROR dialog");
            }
        }
        lineNbr = 0;
        NBR = (String.valueOf(lineNbr));
        ligne_id.setText(NBR);
    }

    @FXML
    private void HandleCorrectionButoon(ActionEvent event) throws IOException {
        if (event.getSource() == demat) {
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml_Dematerialization/correction.fxml")));
            stage.setTitle("Correction OCR");
            stage.setScene(scene);
            stage.show();
        }
    }

    //Open_Facure button hundle
    @FXML
    public void Parcourir(ActionEvent event) {
        if (event.getSource() == open) {
            File f2 = DematerializationViewController.ch1.showOpenDialog(null);
            if (f2 != null) {
                DematerializationViewController.ch1.setInitialDirectory(f2.getParentFile());
            }
            lv1.setTextFill(Color.GREEN);
            lv1.setText(null);
            if (f2 != null) {
                lbl.setTextFill(Color.RED);
                lbl.setText(null);
                facturePath = f2.getAbsolutePath();
                lv1.setText(facturePath);
                //System.out.println(facturePath);
            } else {
                if (facturePath != null) {
                    lbl.setText("None File Selected !");
                    lv1.setText(facturePath);
                }
                lbl.setText("None File Selected !");
            }
            if (f2 != null) {
                path = f2.getAbsolutePath();
                System.out.println(path);
                try {
                    inputstream = new FileInputStream(path);
                } catch (FileNotFoundException e) {
                    e.getMessage();
                }
                Image image_l = new Image(inputstream);
                img.setImage(image_l);
                group.getChildren().clear();
                group.getChildren().add(img);
                group.getChildren().add(zone);
                nbrZone = 0;
                NBR = (String.valueOf(nbrZone));
                nbr_Zone.setText(NBR);
            }
        }
    }

    private void ocr_ligne_Action(ActionEvent event) {
        if (event.getSource().equals(do_ocr)) {
            makeSameZone(Integer.valueOf(nbr_ligne.getText()));
        }
    }

    @FXML
    private void Devise_zone(ActionEvent event) {

        if (event.getSource().equals(deviser)) {
            if (nbr_ligne.getText().isEmpty()) {
                nbr_ligne.setText("1");
                devise_zone(Integer.valueOf(nbr_ligne.getText()));
            } else {
                devise_zone(Integer.valueOf(nbr_ligne.getText()));
            }
        }
    }

    @FXML
    private void Ajouter_four_Action(ActionEvent event) {

    }
}
