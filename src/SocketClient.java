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

                InputStream is = s.getInputStream();
                InputStreamReader ir = new InputStreamReader(is);
                BufferedReader inr = new BufferedReader(ir);
                OutputStream os = s.getOutputStream();
                OutputStreamWriter or = new OutputStreamWriter(os);
                BufferedWriter outr = new BufferedWriter(or);

                System.out.print("Inserisci il messaggio da inviare: ");
                Scanner scanner = new Scanner(System.in);
                String msg = scanner.nextLine();

                outr.write(msg, 0, msg.length());
                outr.newLine();
                outr.flush();

                if (msg.equals("bye bye")) {
                    s.close();
                    break;
                }

                String line = inr.readLine();
                System.out.println("Server: " + line);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
