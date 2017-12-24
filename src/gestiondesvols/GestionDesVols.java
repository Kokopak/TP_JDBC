/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondesvols;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ipradel
 */


public class GestionDesVols {

    private static Connection conn = null;

    public static void main(String[] args) {
  
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Pilote chargé");
        } catch (java.lang.ClassNotFoundException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        
        try {
            String url = "jdbc:postgresql:gestion_vol";
            String user = "postgres";
            String password = "postgres";
            
            conn = DriverManager.getConnection(url, user, password);
            
            afficherTablePilote(conn);
            //majLocalisation(conn);
            afficherTableAvion(conn);
            
            //insertionAvion(11, "A300", 300, conn);
            //afficherTableAvion(conn);
            
            String time = "9:00";
            DateFormat timeFormat = new SimpleDateFormat("hh:mm");
            
            
            try {
				rechercheVol(conn, new Time(timeFormat.parse(time).getTime()), "Nantes");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
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
        System.out.println("Table pilote : \n");
        
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
                System.out.println("Moyenne des salaires : " + moyenneSal / totalPilote);
                System.out.println("\n");
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
        
        System.out.println("Table avion : \n");
        
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
                System.out.println("Somme des capacités : " + sommeCapacité);
                System.out.println("\n");
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
    
    public static void insertionAvion(int noAv, String nom, int capacite, Connection conn) {
        String requete = "insert into avion (AVNUM, AVNOM, CAPACITE) values (?, ?, ?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            
            ps.setInt(1, noAv);
            ps.setString(2, nom);
            ps.setInt(3, capacite);
            int nb = ps.executeUpdate();
            if (nb == 0) {
                throw new SQLException("Pas de update effectués !");
            }
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void rechercheVol(Connection conn, Time heureDepart, String lieuDepart) {
    	String requete = "select * from VOL where heuredep=? and villedep=?";
    	System.out.println("Résultat(s) recherche du vol : \n");
    	
    	try {
            PreparedStatement ps = conn.prepareStatement(requete);
            
            ps.setTime(1, heureDepart);
            ps.setString(2, lieuDepart);
            
            ResultSet rset  = ps.executeQuery();
            
            if (rset != null) {
                while (rset.next()) {
                	System.out.println("\t Numéro : " + rset.getInt("VOLNUM") + "\t");
                	System.out.println("\t Ville départ : " + rset.getString("VILLEDEP") + "\t");
                	System.out.println("\t Ville arrivé : " + rset.getString("VILLEARR") + "\t");
                	System.out.println("\t Heure départ : " + rset.getTime("HEUREDEP") + "\t");
                	System.out.println("\t Heure arrivé : " + rset.getTime("HEUREARR") + "\t");
                    
                    System.out.println("\n----------------------------------------------");
                    
                }
                System.out.println();
            } else {
              throw new SQLException("Echec lors de l'éxécution de la query !");  
            }
            
            
            
            ps.close();
            
        } catch (SQLException e) {
        	System.out.println(e);
        }
    }
}
