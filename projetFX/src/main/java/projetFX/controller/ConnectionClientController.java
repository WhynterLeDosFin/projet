package projetFX.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

public class ConnectionClientController {
        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;
        private String userId;

        public ConnectionClientController(Socket socket) throws IOException {
            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            userId = String.valueOf(UUID.randomUUID());
        }

        /********************************/

        public String readLine() throws IOException {
            var line = this.reader.readLine();
            if (line == null) throw new IOException("Serveur disconnected");
            return line;
        }

        /********************************/

        public void println(String message) {
            this.writer.println(message);
        }

        /********************************/

        public void uuid() {
            String message = "UUID:"+this.userId;
            println(message);
        }

        public void connexion(String username, String password) {
            String message = "LOGIN:" + username + ":" + password;
            System.out.println(message);
            println(message);
        }

        public void inscription(String username, String password) {
            String message = "CREATEACCOUNT:" + username + ":" + password;
            println(message);
        }

        public void getListGame() {
            String message = "GETCURRENTLISTGAME";
            println(message);
        }

        public void createGame(String gameName) {
            String message = "CREATEGAME:"+gameName;
            println(message);
        }
    }

