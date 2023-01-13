import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void exemple() {
        String url = "jdbc:mysql://localhost:3306/binomotron";
        String user = "root";
        String passwd = "root";
        String driver = "com.mysql.jdbc.Driver";


        try (Connection con = DriverManager.getConnection(url, user, passwd); // Conexion a la base de donnée
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT prenom FROM apprenant")) { // Requete SQL

            List<String> apprenants = new ArrayList<>(); // Creer liste qui recoit la colonne "prenom"
            Scanner sc = new Scanner(System.in); // Imput pour demander taille du groupe
            int tailleGroupe; // Variable qui va devenir la donnée rentrer
            System.out.println("Taille de groupe : ");
            tailleGroupe = sc.nextInt(); // Variable devient donnée rentrer

            while (rs.next()) {
                apprenants.add(rs.getString("prenom"));  // Boucle qui permet de recuperer touts les prenoms
            }

            if (tailleGroupe > apprenants.size()) {
                System.out.println("La taille du groupe est supérieure au nombre d'apprenants.");
                 return; // Condition pour ne pas rentrer une valeur supérieur a prenom.size
            }

            System.out.println("Liste avant le mélange : " + apprenants);
            Collections.shuffle(apprenants);
            System.out.println("Liste après le mélange : " + apprenants); // Melanger apprenants

            List<List<String>> listBinomes = new ArrayList<>(); // Liste qui va recevoir tous les groupes
            for (int spanGroupe = 0; spanGroupe < apprenants.size(); spanGroupe += tailleGroupe) {
                // Premiere boucle qui va permettre de diviser taille groupe par taille apprenants
                List<String> binome = new ArrayList<>();
                for (int i = spanGroupe; i < spanGroupe + tailleGroupe && i < apprenants.size(); i++) {
                    // Boucle qui creer une variable et qui va permettre d'aller chercher les prenoms par rapport a
                    // taille du groupe, et qui utilise && pour pouvoir creer un groupe different pour le dernier
                    binome.add(apprenants.get(i));
                }
                listBinomes.add(binome);
            }
            System.out.println("Voici les groupes : " + listBinomes);

        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
    }

    public static void mailCall() {
        String url = "jdbc:mysql://localhost:3306/binomotron";
        String user = "root";
        String passwd = "root";
        String driver = "com.mysql.jdbc.Driver";

        try (Connection con = DriverManager.getConnection(url, user, passwd);
             Statement st = con.createStatement();
             ResultSet ps = st.executeQuery("SELECT prenom FROM apprenant")) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez le prénom de l'apprenant : ");
            String prenom = sc.nextLine();
            String sql = "SELECT mail FROM apprenant WHERE prenom = '" + prenom + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {


                System.out.println("Adresse mail de l'apprenant : " + rs.getString("mail"));
            } else {


                System.out.println("Aucun apprenant avec ce prénom n'a été trouvé.");
            }
        } catch (
                SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());

        }

    }

    public static void projectCall() {
        String url = "jdbc:mysql://localhost:3306/binomotron";
        String user = "root";
        String passwd = "root";
        String driver = "com.mysql.jdbc.Driver";



        try (Connection con = DriverManager.getConnection(url, user, passwd);
             Statement st = con.createStatement();
             ResultSet ps = st.executeQuery("SELECT nom_projet FROM projet")) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez le prénom de l'apprenant : ");
            String prenom = sc.nextLine();
            String sql = "SELECT mail FROM apprenant WHERE prenom = '" + prenom + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {


                System.out.println("Adresse mail de l'apprenant : " + rs.getString("mail"));
            } else {


                System.out.println("Aucun apprenant avec ce prénom n'a été trouvé.");
            }
        } catch (
                SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());

        }

    }


    public static void main(String[] args) {
        exemple();
        mailCall();
        projectCall();
    }
}