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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Pilote chargé");
        } catch (java.lang.ClassNotFoundException e) {
            System.err.println("Erreur : " + e.getMessage());
        }

        try {
            String url = "jdbc:oracle:thin:@licinfo.univ-jfc.fr:1521:pedago";
            String user = "ISIS3_29";
            String passwd = "isis";
            conn = DriverManager.getConnection(url, user, passwd);
            if (conn != null) {
                System.out.println("connecté");
            } else {
                System.out.println("erreur");
            }
        } catch (SQLException ex) {
            System.err.println("erreur");
        }
    }
}
