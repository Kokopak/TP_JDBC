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
            
            afficherTablePilote(conn);
            afficherTableAvion(conn);
            majLocalisation(conn);
            
            
            if(conn != null) {
                try {
                    conn.close();
                    System.out.println("Déconnexion");    
                } catch (Exception e) {
                    System.out.println(e);
                }
                
            }
            else {
                System.out.println("Echec de la connexion");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void afficherTablePilote(Connection con) throws SQLException  {
        String requete = "select * from PILOTE";
        
        float moyenneSal = 0;
        int totalPilote = 0;
        
        try {
            Statement stmt = null;
            stmt = con.createStatement();
        
            ResultSet rset = stmt.executeQuery(requete);
            
            if (rset != null) {
                while (rset.next()) {
                    System.out.println("\t Numéro : " + rset.getInt("PLNUM") + "\t");
                    System.out.println("\t Nom : " + rset.getString("PLNOM"));
                    System.out.println("\t Date de naissance : " + rset.getTimestamp("DATENAISS"));
                    System.out.println("\t Salaire : " + rset.getFloat("SALAIRE"));
                    
                    moyenneSal += rset.getFloat("SALAIRE");
                    totalPilote += 1;
                    
                    System.out.println("\n----------------------------------------------");
                }
                System.out.println();
                System.out.println("Moyenne des salaires : " + moyenneSal / totalPilote);
            } else {
              throw new SQLException("Echec lors de l'éxécution de la query !");  
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
        } 
    }
    
    public static void afficherTableAvion(Connection con) throws SQLException  {
        String requete = "select * from AVION";
        
        int sommeCapacité = 0;
        
        try {
            Statement stmt = null;
            stmt = con.createStatement();
        
            ResultSet rset = stmt.executeQuery(requete);
            
            if (rset != null) {
                while (rset.next()) {
                    System.out.println("\t Numéro : " + rset.getInt("AVNUM") + "\t");
                    System.out.println("\t Nom : " + rset.getString("AVNOM"));
                    System.out.println("\t Capacité : " + rset.getInt("CAPACITE"));
                    System.out.println("\t Localisation : " + rset.getString("LOCALISATION"));
                    
                    System.out.println("\n----------------------------------------------");
                    
                    sommeCapacité += rset.getInt("CAPACITE");
                }
                System.out.println();
                System.out.println("Somme des capacités : " + sommeCapacité);
            } else {
              throw new SQLException("Echec lors de l'éxécution de la query !");  
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
        } 
    }
    
    public static void majLocalisation(Connection con) {    
        String requete = "update avion set LOCALISATION='Toulouse' where AVNOM='A300'";
        
        int sommeCapacité = 0;
        
        try {
            Statement stmt = null;
            stmt = con.createStatement();
        
            int nb = stmt.executeUpdate(requete);
            
            System.out.println("Nombre de update effectués : " + nb);
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
        } 
        
    }
}
