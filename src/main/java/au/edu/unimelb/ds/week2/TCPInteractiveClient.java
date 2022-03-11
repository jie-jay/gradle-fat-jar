package au.edu.unimelb.ds.week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class TCPInteractiveClient {


    public static void main(String[] args) {

        // Create a stream socket bounded to any port and connect it to the
        // socket bound to localhost on port 4444
        try (Socket socket = new Socket("localhost", 4444);
             Scanner scanner = new Scanner(System.in);
        ) {

            System.out.println("Connection established");


            // Get the input/output streams for reading/writing data from/to the socket
            try(
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            ){
            String inputStr;

            //While the user input differs from "exit"
            while (!(inputStr = scanner.nextLine()).equals("exit")) {

                // Send the input string to the server by writing to the socket output stream
                out.write(inputStr + "\n");
                out.flush();
                System.out.println("Message sent");

                // Receive the reply from the server by reading from the socket input stream
                String received = in.readLine(); // This method blocks until there  is something to read from the
                // input stream
                System.out.println("Message received: " + received);
            }}
            catch(IOException e){
                System.out.println("IOException: "+e.getMessage());
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
