package Send;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;


public class Sender extends Thread {

	static int portNum;
	static ServerSocket server;
	static Socket socket;
	
	public Sender(int port) throws IOException {
		server = new ServerSocket(port);
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, IOException, InterruptedException {
		System.out.println("CPSC 501 - Assignment 3");
		System.out.println();
		portValidator();
		
		Thread thread = new Sender(portNum);
		thread.start();
	}
	
	public void run() {
			try {
			Socket socket = server.accept();

			System.out.println("Server connected to: " + socket.getRemoteSocketAddress());

			//DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			Document document = prepareSerialization();
			
			//XMLOutputter outputter = new XMLOutputter();
			//outputter.output(document, out);
			out.writeObject(new XMLOutputter().outputString(document));
			out.flush();
			System.out.println(new XMLOutputter().outputString(document));
	
			server.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}

	
	
	public Document prepareSerialization() throws Exception {
		ObjectCreator creator = new ObjectCreator();
		Serializer ser = new Serializer();
		Object objCreated = creator.createObject();
		Object obj = objCreated;
		Document document = ser.serialize(obj);
		return document;
	}

	public static void portValidator() {
		Scanner scanner = new Scanner(System.in);
		
		boolean portIsValid = false;
		while(!portIsValid) {
			try {
				System.out.println("Please input a port number: ");
				portNum = scanner.nextInt();
				
				if (portNum < 0 || portNum > 9999)
					throw new NumberFormatException();
			
				else 
					portIsValid = true;

			} catch (NumberFormatException e) {
				System.out.println("The input provided is an invalid port number, try again");
			}
		}
		//scanner.close();
	}
}
