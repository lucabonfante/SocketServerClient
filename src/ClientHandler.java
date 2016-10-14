import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 5ai on 14/10/2016.
 */
public class ClientHandler extends Thread{

    Socket client;

    public ClientHandler(Socket client){
        this.client = client;
    }

    public void run(){

        try {
            while (true) {
                //inizializza input
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

                //inizializza output
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

                //legge input
                String stringa = bufferedReader.readLine();
                System.out.println("Client " + client.getInetAddress() + " : " + stringa);

                if (stringa.equalsIgnoreCase("bye bye")) {
                    bufferedReader.close();
                    printWriter.close();
                    client.close();
                } else if (stringa.equalsIgnoreCase("what my ip and port are?")) {
                    printWriter.println("Your ip is: " + client.getInetAddress() + ", your port is: " + client.getPort());
                } else {
                    System.out.print("Inserisci il messaggio da inviare: ");
                    Scanner scanner = new Scanner(System.in);
                    String msg = scanner.nextLine();
                    printWriter.println(msg);
                }
            }

        } catch(Exception e){
            System.out.println("Connessione chiusa");
        }
    }
}
