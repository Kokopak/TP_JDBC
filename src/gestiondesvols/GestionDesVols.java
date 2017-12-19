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
            AfficheTablePilote(conn);
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Fermeture du programme");
                } catch (Exception e){
                    System.err.println(e);
                }

            }
        } catch (SQLException e) {
            System.out.println(e);
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
                    System.out.println("\t Numéro pilote : " + rset.getInt("PLNUM") + "\t");
                    System.out.println("\t Nom pilote : " + rset.getString("PLNOM") + "\t");
                    System.out.println("\t Prénom pilote : " + rset.getString("PLPRENOM") + "\t");
                    System.out.println("\t Ville : " + rset.getString("VILLE") + "\t");
                    System.out.println("\t Date de naissance : " + rset.getTimestamp("DATENAISS") + "\t");
                    System.out.println("\t Salaire : " + rset.getInt("SALAIRE") + "\t");
                    
                    System.out.println("\n---------------------------------");
                }
                System.out.println("");
            }  else {
                throw new SQLException("Erreur");
            }
            
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
