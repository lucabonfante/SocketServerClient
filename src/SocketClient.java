import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Bonfante - Ferretto on 30/09/2016.
 */
public class SocketClient {

    public static void main(String[] args) {

        try {
            Socket s = new Socket("127.0.0.1", 8000);

            while(true) {

                BufferedReader inr = new BufferedReader(new InputStreamReader(s.getInputStream()));
                BufferedWriter outr = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

                System.out.print("Inserisci il messaggio da inviare: ");
                Scanner scanner = new Scanner(System.in);
                String msg = scanner.nextLine();

                if (msg.equals("bye bye")) {
                    inr.close();
                    outr.close();
                    s.close();
                    break;
                }

                outr.write(msg, 0, msg.length());
                outr.newLine();
                outr.flush();

                String line = inr.readLine();
                System.out.println("Server: " + line);

            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: stampa server chiuso
        }

    }
}
