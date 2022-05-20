package au.edu.unimelb.ds.week12;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket("localhost", 9999);

            Scanner input = new Scanner(System.in);

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                while (input.hasNextLine()) {
                    writer.write(input.nextLine() + "\n");
                    writer.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
