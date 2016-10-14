import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Bonfante - Ferretto on 30/09/2016.
 */
public class SocketServer{

        public static void main(String[] args) {
            try {
                ServerSocket server = new ServerSocket(8000);

                while(true){

                    Socket client = server.accept();
                    ClientHandler clientHandler = new ClientHandler(client);
                    clientHandler.start();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}