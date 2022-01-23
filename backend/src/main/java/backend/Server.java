package backend;

import java.io.*;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        // Simple Echo server
        try {
            ServerSocket server = new ServerSocket(5000);
            while (true){
                var client = server.accept();
                System.out.printf("New connection from %s%n", client.getRemoteSocketAddress());
                BufferedReader reader = new BufferedReader(new InputStreamReader((client.getInputStream())));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                Thread clientHandler = new Thread(()->{
                    while (true){
                        try {
                            String line;
                            if ((line = reader.readLine()) == null) break;
                            System.out.printf("Received from %s: %s%n", client.getRemoteSocketAddress(), line);
                            out.println(line);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
                clientHandler.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
