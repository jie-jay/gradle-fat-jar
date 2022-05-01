package au.edu.unimelb.ds.week9;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

public class Client {
	
	// IP and port
	private static String ip = "localhost";

	private final static String CLIENT_PATH = "/Users/jay/client_file/";
	private static int port = 3003;
	
	public static void main(String[] args) {
		 // System.out.println(new java.io.File("").getAbsolutePath());
		try(Socket socket = new Socket(ip, port);){
			// Output and Input Stream
			DataInputStream input = new DataInputStream(socket.getInputStream());
		    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		    
	    	output.writeUTF("I want to connect!");
	    	output.flush();

			JsonObject newCommand = Json.createObjectBuilder().add("command_name", "GET_FILE")
					.add("file_name","cat.png").build();


    		System.out.println(newCommand.toString());
    		
    		// Read hello from server..
    		String message = input.readUTF();
    		System.out.println(message);
    		
    		
    		output.writeUTF(newCommand.toString());
    		output.flush();
    		
    		// Print out results received from server..
    		while(true){
    			if(input.available() > 0){

    	    		String result = input.readUTF();
    	    		System.out.println("Received from server: "+result);
    	    		JsonObject command = Json.createReader(new StringReader(result)).readObject();


    	    		// Check the command name
    	    		if(command.containsKey("command_name")){
    	    			
    	    			if(command.getString("command_name").equals("SENDING_FILE")){
    	    				
    	    				// The file location
    						String fileName = CLIENT_PATH+command.getString("file_name");
    						
    						// Create a RandomAccessFile to read and write the output file.
    						RandomAccessFile downloadingFile = new RandomAccessFile(fileName, "rw");

    						// Find out how much size is remaining to get from the server.
    						long fileSizeRemaining = command.getJsonNumber("file_size").longValue();
    						
    						int chunkSize = setChunkSize(fileSizeRemaining);
    						
    						// Represents the receiving buffer
    						byte[] receiveBuffer = new byte[chunkSize];
    						
    						// Variable used to read if there are remaining size left to read.
    						int num;
    						
    						System.out.println("Downloading "+fileName+" of size "+fileSizeRemaining);
    						while((num=input.read(receiveBuffer))>0){
    							// Write the received bytes into the RandomAccessFile
    							downloadingFile.write(Arrays.copyOf(receiveBuffer, num));
    							
    							// Reduce the file size left to read..
    							fileSizeRemaining-=num;
    							
    							// Set the chunkSize again
    							chunkSize = setChunkSize(fileSizeRemaining);
    							receiveBuffer = new byte[chunkSize];
    							
    							// If you're done then break
    							if(fileSizeRemaining==0){
    								break;
    							}
    						}
    						System.out.println("File received!");
    						downloadingFile.close();
							break;		//jump out
    	    			}
    	    		}
    			}
    		}
		    
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}

	}
	
	public static int setChunkSize(long fileSizeRemaining){
		// Determine the chunkSize
		int chunkSize=1024*1024;
		// If the file size remaining is less than the chunk size
		// then set the chunk size to be equal to the file size.
		return fileSizeRemaining < chunkSize? (int)fileSizeRemaining :chunkSize;
	}

}
