package by.parf;

import by.parf.bean.Registration;
import by.parf.protocol.Command;
import by.parf.protocol.Request;
import by.parf.protocol.Response;
import by.parf.register.RegisterService;
import by.parf.register.RegisterServiceImpl;
import by.parf.server.ChatServer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Kiryl_Parfiankou on 9/2/2016.
 */
public class ServerRunner {

    private static ObjectMapper mapper = new ObjectMapper();
    private static RegisterService registerService = new RegisterServiceImpl();

    public static void main(String[] args){

        int portNumber = Integer.parseInt(args[0]);

        System.out.println("Server started on " + portNumber);

        try {
            (new Thread(new ChatServer(InetAddress.getLocalHost(), portNumber))).start();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /* try (
                ServerSocket server = new ServerSocket(portNumber);
                Socket client = server.accept();
                PrintStream out = new PrintStream(client.getOutputStream(), true, "UTF-8");
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
        ) {

            String inputLine;

            inputLine = in.readLine();

            Request request = mapper.readValue(inputLine, Request.class);
            if (Command.REGISTER.equals(request.getHeader().getCommand())) {
                Registration registration = registerService.register(null);
                System.out.println("Is register..." + registration.toString());
                Response response = registerService.createRegisterResponse(registration);
                System.out.println("Id: " + response.getBody());
                out.println(mapper.writeValueAsString(response));
            } else {
                System.out.println(request.getHeader().getCommand().toString() + request.getBody());
            }

            System.out.println(inputLine);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}