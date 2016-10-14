import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Bonfante - Ferretto on 30/09/2016.
 */
public class SocketServer extends Thread{

    Socket client;

    public SocketServer(Socket socket) {
        this.client = socket;
    }

    public void run(){

        try {
            while (true) {
                //inizializza input
                InputStreamReader in = new InputStreamReader(client.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(in);

                //inizializza output
                OutputStreamWriter out = new OutputStreamWriter(client.getOutputStream());
                BufferedWriter bufferedWriter = new BufferedWriter(out);

                PrintWriter printWriter = new PrintWriter(bufferedWriter, true);

                //legge input
                String stringa = bufferedReader.readLine();
                System.out.println("Client " + client.getInetAddress() + " : " + stringa);


                if (stringa.equals("bye bye")) {
                    client.close();
                } else if (stringa.equals("what my ip and port are?")) {
                    printWriter.println("Your ip is: " + client.getInetAddress() + ", your port is: " + client.getPort());
                } else {
                    System.out.print("Inserisci il messaggio da inviare: ");
                    Scanner scanner = new Scanner(System.in);
                    String msg = scanner.nextLine();

                    printWriter.println(msg);
                }
            }

            } catch(Exception e){
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8000);

            while(true){

                Socket client = server.accept();
                new SocketServer(client).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}