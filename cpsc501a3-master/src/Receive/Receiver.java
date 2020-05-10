package Receive;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class Receiver {

	static int portNum;
	
		public static void main(String args[]) throws Exception {
			portValidator();
			//System.out.println(portNum);
			Socket socket = new Socket("localhost", portNum);
			DataInputStream input = new DataInputStream(socket.getInputStream());
			//SAXBuilder builder = new SAXBuilder();
			//Document doc = (Document) builder.build(input);
			Document doc = convertStringToXMLDocument(input.readUTF());
			//ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			SAXBuilder builder = new SAXBuilder();
			//Document object = (Document) input.readObject();
			//Document doc = (Document) builder.build((InputSource) input.readObject());
			Deserializer deserializer = new Deserializer();
			Object obj = deserializer.deserialize(doc);
			
			Inspector inspector = new Inspector();
			inspector.inspect(obj, true);
			
			socket.close();
	}
		
		public static void portValidator() {
			System.out.println("Insert a port number to connect to: ");
			Scanner scanner = new Scanner(System.in);
			portNum = scanner.nextInt();
		}
		
		 private static Document convertStringToXMLDocument(String xmlString) 
		    {
		        //Parser that produces DOM object trees from XML content
		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		         
		        //API to obtain DOM Document instance
		        DocumentBuilder builder = null;
		        try
		        {
		            //Create DocumentBuilder with default configuration
		            builder = factory.newDocumentBuilder();
		             
		            //Parse the content to Document object
		            Document doc = (Document) builder.parse(new InputSource(new StringReader(xmlString)));
		            return doc;
		        } 
		        catch (Exception e) 
		        {
		            e.printStackTrace();
		        }
		        return null;
		    }
}
