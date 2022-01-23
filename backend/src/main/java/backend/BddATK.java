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

    public boolean queryInscription(String username, String password) {
        try {
            PreparedStatement inscriptionUser = this.connection.prepareStatement("INSERT INTO users VALUE (?,?)");

            inscriptionUser.setString(1, username);
            inscriptionUser.setString(2, password);

            inscriptionUser.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean queryCreateConsole(String gameName, String constructor, String year, String image) {
        try {
            PreparedStatement creationConsole = this.connection.prepareStatement("INSERT INTO consoles VALUES (?,?,?,?)");

            creationConsole.setString(1, gameName);
            if (!image.isEmpty()) {
                creationConsole.setString(2, image);
            }
            if (!year.isEmpty()) {
                creationConsole.setInt(3, Integer.parseInt(year));
            }
            creationConsole.setString(4, constructor);

            creationConsole.executeUpdate();
            System.out.println("creationConsole = " + creationConsole.executeUpdate());

        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
