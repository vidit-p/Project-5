import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(4243);
                Socket socket = serverSocket.accept();
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());

                File f = new File("database.txt");
                FileReader fr = new FileReader(f);
                BufferedReader bfr = new BufferedReader(fr);
                String text = bfr.readLine();
                ArrayList<String> databaseText = new ArrayList<String>();

                while (text != null) {
                    databaseText.add(text);
                    text = bfr.readLine();
                }
                 while (true) {
                     try{
                         

                     } catch (SocketException e) {
                         continue;
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }
            } catch (SocketException e) {
                continue;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
