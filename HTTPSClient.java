
import java.io.*;
import javax.net.ssl.*;

public class HTTPSClient
{

    public static void main(String[] args)
    {
        
        int port = 443;   // Der Standardport für HTTPS
        String host = "itg.bayern";   
        String seite = "/startseite.html";
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        SSLSocket socket = null;
        try {
            // Stellt eine Verbindung zu host auf der port her:
            socket = (SSLSocket) factory.createSocket(host, port);
        
            // Erstellen des GET-Request!
            Writer out = new OutputStreamWriter (socket.getOutputStream(), "UTF-8");
            
            out.write("GET https://" + host + seite +" HTTP/1.1 \r\n");
            out.write("Host: " + host +  "\r\n");
            out.write("\r\n");
            out.flush();   // GET-Request wird abgeschickt

            // Response lesen
            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));

            // Liest den Header aus und gibt ihn aus
            System.out.println("----------------------------");
            System.out.println("Der Header");
            System.out.println("----------------------------");
            String s;
            while (!(s = in.readLine()).equals("")) {
                System.out.println(s);
            }
            System.out.println();

            // Liest die Daten 
            System.out.println("----------------------------");
            System.out.println("Die Daten");
            System.out.println("----------------------------");

            // Gibt die ersten 1000 Zeichen der Daten aus
            int c;
            int i= 0;
            while ((c = in.read()) != -1 && (i++ < 1000)) {
                System.out.write(c);
            }

            System.out.println();
            System.out.println("----------------------------");
            System.out.println("Ende der ersten 1000 Zeichen");
            System.out.println("----------------------------");
        }
        catch (IOException ex) {
            System.err.println(ex);  //Fehlerbehandlung
        }
        finally {
            try {
                if (socket != null) socket.close();  // Schließen der Verbindung
            }
            catch(IOException e) {}
        }
    }

}
