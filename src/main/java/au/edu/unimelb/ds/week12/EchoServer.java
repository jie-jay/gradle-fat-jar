package au.edu.unimelb.ds.week12;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EchoServer {
    public static void main(String[] args) {
        try {
            SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket serverSock = (SSLServerSocket) factory.createServerSocket(9999);

            while (true) {
                SSLSocket sock = (SSLSocket) serverSock.accept();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {
                    String input;
                    while ((input = reader.readLine()) != null) {
                        System.out.println(input);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
