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
            String url = "jdbc:oracle:thin:@licinfo.univ-jfc.fr:1521:pedago [ISIS3_21 sur ISIS3_21]";
            String user = "ISIS3_21";
            String passwd = "isis";
            conn = DriverManager.getConnection (url, user, passwd);
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("FERMER1");
                } catch (Exception e) {
                    System.err.println("FERMER2");
                }
            } else {
                System.out.println("FERMER3");
            }
        } catch (SQLException ex) {
            System.err.println("FERMER4");
        }
    }
    public static void AfficheTablePilote(Connection con) throws SQLException {
        String requete = "";
        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(requete);
            if (rset != null) {
                while (xxxxxx) {
                    System.out.println("\t Numéro : " + rset.getInt(xxx) + "\t");
                    System.out.println("\t Nom : " + rset.getString(xxx) + "\t");
                    System.out.println("\t Capacité : " + rset.getInt(xxx) + "\t");
                    System.out.println("\t Localisation : " + rset.getString(xxx) +
                   "\t");
                    System.out.println("\n---------------------------------");
                }
                System.out.println();
            } else {
                throw new SQLException("xxxxxx");
            }

            xxxxxx // fermeture de l'instance ResultSet
            xxxxxx // fermeture de l'instance Statement
        } catch (SQLException e) {
            System.err.println("xxxxxx!");
        }
    } 
}
