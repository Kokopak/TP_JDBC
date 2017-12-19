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
            String user = "ISIS3_21";
            String passwd = "isis";
            conn = DriverManager.getConnection (url, user, passwd);
            
            AfficheTablePilote(conn);
            AfficheTableAvion(conn);
            
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
            System.err.println(ex);
        }
    }
    public static void AfficheTablePilote(Connection con) throws SQLException {
        String requete = "Select * from PILOTE";
        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(requete);
            if (rset != null) {
                while (rset.next()) {
                    System.out.println("\t Numéro : " + rset.getInt("PLNUM") + "\t");
                    System.out.println("\t Nom : " + rset.getString("PLNOM") + "\t");
                    System.out.println("\t Prénom : " + rset.getString("PLPRENOM") + "\t");
                    System.out.println("\t Ville : " + rset.getString("VILLE") + "\t");
                    System.out.println("\t Date de naissance : " + rset.getTimestamp("DATENAISS") + "\t");
                    System.out.println("\t Salaire : " + rset.getInt("SALAIRE") + "\t");
                    System.out.println("\n---------------------------------");
                }
                System.out.println("");
            } else {
                throw new SQLException("ERREUR1");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static void AfficheTableAvion(Connection con) throws SQLException {
        String requete = "Select * from AVION";
        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(requete);
            if (rset != null) {
                while (rset.next()) {
                    System.out.println("\t Numéro : " + rset.getInt("AVNUM") + "\t");
                    System.out.println("\t Nom : " + rset.getString("AVNOM") + "\t");
                    System.out.println("\t Capacité : " + rset.getString("CAPACITE") + "\t");
                    System.out.println("\t Localisation : " + rset.getString("LOCALISATION") + "\t");
                    System.out.println("\n---------------------------------");
                }
                System.out.println("");
            } else {
                throw new SQLException("ERREUR2");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    } 
}
