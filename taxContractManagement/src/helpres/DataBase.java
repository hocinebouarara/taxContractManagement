/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpres;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abdullah
 */
public class DataBase {

    public static PreparedStatement preparedStatement;
    public static Connection connection = DbConnect.getConnect();
    public static ResultSet resultSet = null;
    public static ResultSet resultSet1 = null;

    /*Navicat for mysql 6 =>  jdbc:mysql://127.0.0.1:3309/
                        username = "root" 
                        password = "root"
       wamp for   mysql 8  => jdbc:mysql://127.0.0.1:3308/
                        username = "root" 
                        password = ""
     */
    public static String insertMasqueData(String doc_name, String field_name, int X, int Y, int W, int H,
            String table_champ, int ligne) {

        if (field_name == null) {
            //Alert_class.Alert_fuction("ERROR", "null !", "ERROR Dialog");
            return "Exception";
        } else {
            try {
                String st;
                st = "INSERT INTO `fields`(`document_name`, `field_name`, `x`, `y`, `w`, `h`, `field_table`, `lines_nbr`)"
                        + "VALUES (?,?,?,?,?,?,?,?)";
                preparedStatement = (PreparedStatement) connection.prepareStatement(st);
                preparedStatement.setString(1, doc_name);
                preparedStatement.setString(2, field_name);
                preparedStatement.setInt(3, X);
                preparedStatement.setInt(4, Y);
                preparedStatement.setInt(5, W);
                preparedStatement.setInt(6, H);
                preparedStatement.setString(7, table_champ);
                preparedStatement.setInt(8, ligne);
                preparedStatement.executeUpdate();
                // Alert_class.Alert_fuction("CONFIRMATION", "masque cree", "CONFIMATION Dialog");
                return "Success";
            } catch (SQLException ex) {
                //Alert_class.Alert_fuction("ERROR", ex.getMessage(), "ERROR Dialog");
                return "Exception";
            }
        }

    }

    public static String insertFactureData(String NumFact, String id_fact, String paiement,
            String Date_f, int Nbr_ligne, int Qte_globale, double Total_PPA, double Total_TVA,
            double M_HT, double M_TTC, String Net_a_paye, String Date_Demat, String id_four) {
        try {
            String sql = " INSERT INTO Facture (NumFact,Id_facture,paiement,Date_f,Nbr_ligne,"
                    + "Qte_globale,Total_PPA,Total_TVA,M_HT,M_TTC,Net_a_paye,Date_Demat,Id_four) VALUES "
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            String sql1 = " INSERT INTO Facture (NumFact,Date_f) VALUES (?)";

            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, NumFact);
            preparedStatement.setString(2, id_fact);
            preparedStatement.setString(3, paiement);
            preparedStatement.setDate(4, Date.valueOf(Date_f));
            preparedStatement.setInt(5, Nbr_ligne);
            preparedStatement.setInt(6, Qte_globale);
            preparedStatement.setDouble(7, Total_PPA);
            preparedStatement.setDouble(8, Total_TVA);
            preparedStatement.setDouble(9, M_HT);
            preparedStatement.setDouble(10, M_TTC);
            preparedStatement.setString(11, Net_a_paye);
            preparedStatement.setDate(12, Date.valueOf(Date_Demat));
            preparedStatement.setString(13, id_four);
            System.out.println("ready to insert in facture ... ");
            preparedStatement.executeUpdate();
            //Alert_class.Alert_fuction("CONFIRMATION", "Table Facture Insérer !", "Confirmation Dialog");
            return "Success";
        } catch (SQLException ex) {
            //5Alert_class.Alert_fuction("ERROR", ex.getMessage(), "Error Dialog");
            return "Exception";
        }
    }
    //make sure you add the lib
}
