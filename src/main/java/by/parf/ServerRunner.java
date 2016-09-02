package by.parf;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Kiryl_Parfiankou on 9/2/2016.
 */
public class ServerRunner {

    public static void main(String[] args){

        int portNumber = Integer.parseInt(args[0]);

        System.out.println("Server started on " + portNumber);

        try (
                ServerSocket server = new ServerSocket(portNumber);
                Socket client = server.accept();
                PrintStream out = new PrintStream(client.getOutputStream(), true, "UTF-8");
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
        ) {

            String inputLine;

            while ((inputLine = in.readLine()) != null) {

                if ("exit".equals(inputLine)) {
                    break;
                }
                System.out.println(inputLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server was stopped.");
    }
}