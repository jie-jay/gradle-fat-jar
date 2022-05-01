package au.edu.unimelb.ds.week3;


import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.io.IOException;

public class UDPServer {

    public static void main(String args[]) {

        try (DatagramSocket serverSocket = new DatagramSocket(9884);) {
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            //Listen for incoming connections for ever
            while (true) {
                System.out.println("This is  UDP server- Waiting for data to recieve");

                // Create a receive Datagram packet and receive through socket
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String receivesentence = new String(receivePacket.getData());
                String sendSentence = "This is Server, I recieved from client- ";
                sendSentence += receivesentence;
                System.out.println(" Server Data: " + sendSentence);

                //Get client attributes from the received data 
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                String capitalizedSentence = sendSentence.toUpperCase();
                sendData = capitalizedSentence.getBytes();

                //Create a send Datagram packet and send through socket
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Socket: " + e.getMessage());
        }
    }
}
