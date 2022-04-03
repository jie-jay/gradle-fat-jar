package au.edu.unimelb.ds.week5;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;
import javax.net.ServerSocketFactory;


public class Server {
	
	// Declare the port number
	private static int port = 3002;
	
	// Identifies the user number connected
	private static int counter = 0;

	public static void main(String[] args)
	{
		ServerSocketFactory factory = ServerSocketFactory.getDefault();
		try(ServerSocket server = factory.createServerSocket(port)){
			System.out.println("Waiting for client connection..");
			
			// Wait for connections.
			while(true){
				Socket client = server.accept();
				counter++;
				System.out.println("Client "+counter+": Applying for connection!");
				
				
				// Start a new thread for a connection
				Thread t = new Thread(() -> serveClient(client));
				t.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	private static void serveClient(Socket client)
	{
		try(Socket clientSocket = client;
			DataInputStream input = new DataInputStream(clientSocket.getInputStream()); 			// Input stream
			DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());				// Output Stream

		)
		{

		    System.out.println("CLIENT: "+input.readUTF());	//read msg 1
		    
		    output.writeUTF("Server: Hi Client "+counter+" !!!");
		    // Receive more data..
			String command = input.readUTF();
			JsonObject jsonObject = Json.createReader(new StringReader(command)).readObject();
		    // Attempt to convert read data to JSON
			System.out.println("COMMAND RECEIVED: "+command.toString());
		    Integer result = parseCommand(jsonObject);
		    JsonObject results = Json.createObjectBuilder().add("result", result).build();
		    output.writeUTF(results.toString());
			//reader.close();
		} catch (IOException e ) {
			e.printStackTrace();
		}
	}

	private static Integer parseCommand(JsonObject command) {
		
		int result = 0;

		if(command.containsKey("command_name")){
			System.out.println("IT HAS A COMMAND NAME");
		}

		String cmd = command.getString("command_name");
		if("Math".equals(cmd))
		{
			System.out.println("Performing "+cmd+" operation!");
			Math math = new Math();
			Integer firstInt = Integer.parseInt(command.get("first_integer").toString());
			Integer secondInt = Integer.parseInt(command.get("second_integer").toString());
			
			switch(command.getString("method_name"))
			{
				case "add":
					result = math.add(firstInt,secondInt);
					break;
				case "multiply":
					result = math.multiply(firstInt,secondInt);
					break;
				case "subtract":
					result = math.subtract(firstInt,secondInt);
					break;
				default:
					result = -1;
			}
		}
		// TODO Auto-generated method stub
		return result;
	}

}
