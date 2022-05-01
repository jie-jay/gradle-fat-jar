package au.edu.unimelb.ds.week9;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonObject;
import javax.net.ServerSocketFactory;

public class Server {
	
	// Declare the port number
	private static int port = 3003;

	private final static String SERVER_PATH = "/Users/jay/server_file/";
	
	// Identifies the user number connected
	private static int counter = 0;

	public static void main(String[] args) {
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
	
	private static void serveClient(Socket client){
		try(Socket clientSocket = client;
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			// Output Stream
		    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())){

			System.out.println("CLIENT: "+input.readUTF());
		   
		    output.writeUTF("Server: Hi Client "+counter+" !!!");
		    
		    // Receive more data..
		    while(true){
		    	if(input.available() > 0){
					String received = input.readUTF();
		    		// Attempt to convert read data to JSON
		    		JsonObject command = Json.createReader(new StringReader(received)).readObject();
		    		System.out.println("COMMAND RECEIVED: "+command.toString());
		    		parseCommand(command,output);
		    	}
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void parseCommand(JsonObject command, DataOutputStream output) {
		// This section deals with the file handler
		if(command.getString("command_name").equals("GET_FILE")){
			System.out.println("Received get_file command.");

			String fileName = command.getString("file_name");
			// Check if file exists
			File f = new File(SERVER_PATH+fileName);
			System.out.println("Sending file: "+ f.getAbsolutePath());
			if(f.exists()){
				// Send this back to client so that they know what the file is.
				JsonObject trigger = Json.createObjectBuilder().add("command_name", "SENDING_FILE").add("file_name","cat.png")
								.add("file_size",f.length()).build();
				try {
					// Send trigger to client
					output.writeUTF(trigger.toString());
					
					// Start sending file
					RandomAccessFile byteFile = new RandomAccessFile(f,"r");
					byte[] sendingBuffer = new byte[1024*1024];
					int num;
					// While there are still bytes to send.
					while((num = byteFile.read(sendingBuffer)) > 0){
						System.out.println("value:" + num);
						
						output.write(Arrays.copyOf(sendingBuffer, num));
					}
					byteFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				// Throw an error here..
			}
		}
	}

}
