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
            //salMoyenPilote(conn);
            //capaAvion(conn);
            //majLocalisation(conn);

            //insertionAvion(541, "Avion Ilo", 50, conn);

            rechercheVol(12-02-2017, "Lyon", conn);
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
                System.out.println("La moyenne des salaires des pilotes est de : " + sal);
            } else {
                throw new SQLException("Erreur");
            }
            rset.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void capaAvion(Connection con) throws SQLException {
        String requete = "Select sum(capacite) from Avion";
        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(requete);

            int capa = 0;

            if (rset != null) {
                while (rset.next()) {
                    capa = rset.getInt("sum(capacite)");
                }
                System.out.println("La somme des capacités des avions est : " + capa);
            } else {
                throw new SQLException("Erreur");
            }
            rset.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void majLocalisation(Connection con) throws SQLException {
        String requete = "update Avion set Localisation='Toulouse'";
        try {
            Statement stmt = null;
            stmt = con.createStatement();
            int upd = stmt.executeUpdate(requete);
            System.out.println("Nombre de modifications effectuées : " + upd);
            AfficheTableAvion(con);
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void insertionAvion(int no_av, String nom, int capa, Connection con) throws SQLException {
        String requete = "insert into avion(avnum, avnom, capacite) values(?, ?, ?)";

        try {
            PreparedStatement pstmt = con.prepareStatement(requete);
            pstmt.setInt(1, no_av);
            pstmt.setString(2, nom);
            pstmt.setInt(3, capa);
            int count = pstmt.executeUpdate();
            AfficheTableAvion(con);
            if (count == 0) {
                throw new SQLException("Erreur");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void rechercheVol(new Timestamp(2017), String villedep, Connection con) {
        String requete = "Select * from Vol where heuredep=? and villedep=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(requete);
            pstmt.setTimestamp(1, heuredep);
            pstmt.setString(2, villedep);
            ResultSet rset = pstmt.executeQuery();
            if (rset == null) {
                System.out.println("\t Numéro de vol : " + rset.getInt("volnum") + "\t");
                System.out.println("\t Nom du pilote : " + rset.getInt("plnum") + "\t");
                System.out.println("\t Numéro de l'avion : " + rset.getInt("avnum") + "\t");
                System.out.println("\t Ville de départ : " + rset.getString("villedep") + "\t");
                System.out.println("\t Ville d'arrivée : " + rset.getString("villearr") + "\t");
                System.out.println("\t Heure de départ : " + rset.getTimestamp("heuredep") + "\t");
                System.out.println("\t Heure d'arrivée : " + rset.getTimestamp("heurearr") + "\t");
            } else {
                throw new SQLException("Erreur");
            }
            rset.close();
            pstmt.close();

        } catch (SQLException e) {
            System.err.println(e);
        }

    }
}
