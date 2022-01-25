package backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BddConnectionQuery {

    private Connection connection;

    public String usernameConnected;


    String url = "jdbc:mysql://54.37.227.110:3306/java_projet";
    String username = "remote_user";
    String password = "rklj234§!@";

    public BddConnectionQuery() throws BddException {
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

    public Integer getUserIdConnected() {
        ResultSet resultSetId = null;
        Integer userId = null;
        try {

            PreparedStatement rechercheIdUserConnected = this.connection.prepareStatement("SELECT id FROM users WHERE username = ? ORDER BY id ASC LIMIT 1");
            rechercheIdUserConnected.setString(1, usernameConnected);

            resultSetId = rechercheIdUserConnected.executeQuery();
            resultSetId.next();
            userId = resultSetId.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return userId;
    }

    public boolean queryConnexion(String username, String password) {
        ResultSet resultSet = null;

        try {
            PreparedStatement recherchePersonne = this.connection.prepareStatement("SELECT * FROM users WHERE username = ? and password = ?");

            recherchePersonne.setString(1, username);
            recherchePersonne.setString(2, password);

            resultSet = recherchePersonne.executeQuery();

            getUserIdConnected();

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

            System.out.println(inscriptionUser);
            System.out.println(inscriptionUser.executeUpdate());
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean queryCreateConsole(String consoleName, String constructor, String year, String image) {
        try {

            PreparedStatement creationConsole = this.connection.prepareStatement("INSERT INTO consoles (nom,image,annee,fabricant) VALUES (?,?,?,?)");

            creationConsole.setString(1, consoleName);
            if (!image.isEmpty()) {
                creationConsole.setString(2, image);
            }
            if (!year.isEmpty()) {
                creationConsole.setInt(3, Integer.parseInt(year));
            }
            creationConsole.setString(4, constructor);
            creationConsole.executeUpdate();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean queryCreateGame(String gameName, String image, String grade, String year, String nbPlayer, String isOnline, String isFinished, String buyDate, String consoleId, String editorId) {
        try {

            PreparedStatement creationGame = this.connection.prepareStatement("INSERT INTO jeux (" +
                    "nom,image,note,annee,nbJoueur,enLigne,fini,dateAchat,nomConsoles,nomEditeurs,username)" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            creationGame.setString(1, gameName);
            if (!image.isEmpty()) {
                creationGame.setString(2, image);
            }
            creationGame.setInt(3, Integer.parseInt(grade));
            creationGame.setInt(4, Integer.parseInt(year));
            creationGame.setInt(5, Integer.parseInt(nbPlayer));

            creationGame.setString(6, isOnline);
            creationGame.setString(7, isFinished);
            creationGame.setString(8, buyDate);
            creationGame.setString(9, consoleId);
            creationGame.setString(10, editorId);
            creationGame.setString(11, usernameConnected);
            creationGame.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public String queryGetEditor() {
        ResultSet resultEditor = null;
        try {
            PreparedStatement rechercheEditor = this.connection.prepareStatement("SELECT nom FROM editeurs ORDER BY nom ASC");
            List<String> names = new ArrayList<>();
            resultEditor = rechercheEditor.executeQuery();
            while(resultEditor.next())
                names.add(resultEditor.getString("nom"));
            return String.join(",", names);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String queryGetConsole() {
        ResultSet resultConsole = null;
        try {
            PreparedStatement rechercheConsole = this.connection.prepareStatement("SELECT nom FROM consoles ORDER BY nom ASC");
            List<String> names = new ArrayList<>();
            resultConsole = rechercheConsole.executeQuery();
            while(resultConsole.next())
                names.add(resultConsole.getString("nom"));
            return String.join(",", names);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
