package au.edu.unimelb.ds.week6;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	
	// IP and port
	private static String ip = "localhost";
	private static int port = 3002;
	
	public static void main(String[] args) {
		try(Socket socket = new Socket(ip, port);
			DataInputStream input = new DataInputStream(socket.getInputStream());
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		){
			// Output and Input Stream

		    
	    	output.writeUTF("I want to connect!");	//msg 1
	    	output.flush();

			JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

    		jsonBuilder.add("command_name", "Math");
			jsonBuilder.add("method_name","add");
			jsonBuilder.add("first_integer",1);
			jsonBuilder.add("second_integer",2);

			String command = jsonBuilder.build().toString();

    		System.out.println(command);
    		
    		// Read hello from server.
    		String message = input.readUTF();	// read response
    		System.out.println(message);
    		
    		// Send message to Server
    		output.writeUTF(command);	//msg 2
    		output.flush();
    		
    		// Print out results received from server..
    		String result = input.readUTF();	// read response
    		System.out.println("Received from server: "+result);
		    
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
		}

	}

}
