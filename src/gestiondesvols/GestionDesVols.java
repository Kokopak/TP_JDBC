/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondesvols;
import java.sql.*;

/**
 *
 * @author ipradel
 */


public class GestionDesVols {

    private static Connection conn = null;

    public static void main(String[] args) {
  
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Pilote chargé");
        } catch (java.lang.ClassNotFoundException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        
        try {
            String url = "jdbc:oracle:thin:@licinfo.univ-jfc.fr:1521:pedago";
            String user = "ISIS3_20";
            String password = "isis";
            
            conn = DriverManager.getConnection(url, user, password);
            
            if(conn != null) {
                System.out.println("Connexion établie !");
            }
            else {
                System.out.println("Echec de la connexion");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}
