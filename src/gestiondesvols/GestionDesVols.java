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
            //AfficheTablePilote(conn);
            //AfficheTableAvion(conn);
            salMoyenPilote(conn);
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Fermeture du programme");
                } catch (Exception e) {
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
            } else {
                throw new SQLException("Erreur");
            }
            rset.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void AfficheTableAvion(Connection con) throws SQLException {
        String requete = "Select * from Avion";
        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(requete);

            if (rset != null) {
                while (rset.next()) {
                    System.out.println("\t Numéro de l'avion : " + rset.getInt("AVNUM") + "\t");
                    System.out.println("\t Nom de l'avion : " + rset.getString("AVNOM") + "\t");
                    System.out.println("\t Capacité de l'avion : " + rset.getInt("CAPACITE") + "\t");
                    System.out.println("\t Localisation : " + rset.getString("LOCALISATION") + "\t");
                    System.out.println("");

                    System.out.println("\n---------------------------------");
                }
                System.out.println("");
            } else {
                throw new SQLException("Erreur");
            }
            rset.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void salMoyenPilote(Connection con) throws SQLException {
        String requete = "Select avg(salaire) from Pilote";
        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(requete);
            float sal = 0;
            if (rset != null) {
                while (rset.next()) {
                    sal = rset.getFloat("avg(salaire)");
                }
                System.out.println(sal);
            } else {
                throw new SQLException("Erreur");
            }
            rset.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    
}
