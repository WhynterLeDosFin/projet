package backend;

import java.sql.*;

public class BddATK {

    private Connection connection;

    String url = "jdbc:mysql://54.37.227.110:3306/java_projet";
    String username = "remote_user";
    String password = "rklj234§!@";

    public BddATK() throws BddExceptionATK {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion " + e);
            System.exit(0);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void closeBdd() {
        try {
            this.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean queryConnexion(String username, String password) {
        ResultSet resultSet = null;

        try {
            PreparedStatement recherchePersonne = this.connection.prepareStatement("SELECT * FROM users WHERE username = ? and password = ?");

            recherchePersonne.setString(1, username);
            recherchePersonne.setString(2, password);

            resultSet = recherchePersonne.executeQuery();

            if (resultSet.isBeforeFirst())
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean queryInscription(String nom, String prenom, String email, String username, String password) {
        try {
            PreparedStatement inscriptionUser = this.connection.prepareStatement("INSERT INTO users (nom,prenom,email,username,password) VALUES (?,?,?,?,?)");

            inscriptionUser.setString(1, nom);
            inscriptionUser.setString(2, prenom);
            inscriptionUser.setString(3, email);
            inscriptionUser.setString(4, username);
            inscriptionUser.setString(5, password);

            //System.out.println(inscriptionUser);
            //System.out.println(inscriptionUser.executeUpdate());
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
