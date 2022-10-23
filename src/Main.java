import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        String city = "???";
        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {System.out.println("New connection accepted");
                    out.println(city);
                    String newCity = in.readLine();
                    if(city.equals("???") || newCity.startsWith(city.substring(city.length() - 1))) {
                        out.println("OK");
                        city = newCity;
                    } else {
                        out.println("NOT OK");
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}