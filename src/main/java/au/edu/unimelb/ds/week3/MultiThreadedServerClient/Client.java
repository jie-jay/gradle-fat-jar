package au.edu.unimelb.ds.week3.MultiThreadedServerClient;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	
	// IP and port
	private static String ip = "localhost";
	private static int port = 3005;
	
	public static void main(String[] args) 
	{
	
		try(Socket socket = new Socket(ip, port);
			DataInputStream input = new DataInputStream(socket.getInputStream());
			DataOutputStream output = new DataOutputStream(socket.getOutputStream())
		)
		{
			// Output and Input Stream

		    String sendData ="I want to connect";
		    
	    	output.writeUTF(sendData);
	    	System.out.println("Data sent to Server--> " + sendData);
	    	output.flush();
	    	
		    while(input.available() > 0)
		    {
	    		String message = input.readUTF();
	    		System.out.println(message);
		    }
		    
		} 
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}

}
