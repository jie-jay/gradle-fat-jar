package au.edu.unimelb.ds.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class UDPClient {

    public static void main(String args[]) {

        // Create a UDP socket object

        try (DatagramSocket clientSocket = new DatagramSocket();
			 BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		) {

            System.out.println("This is UDP Client- Enter some text to send to the UDP server");

            //IP and port for socket
            InetAddress IPAddress = InetAddress.getByName("localhost");
            int port = 9884;

            // As UDP Datagrams are bounded by fixed message boundaries, define the length
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            String sentence = inFromUser.readLine();
            sendData = sentence.getBytes();

            // Create a send Datagram packet and send through socket
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            clientSocket.send(sendPacket);

            // Create a receive Datagram packet and receive through socket
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("This is client,  SERVER SENT: " + modifiedSentence);

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Socket: " + e.getMessage());
        }

    }


}
