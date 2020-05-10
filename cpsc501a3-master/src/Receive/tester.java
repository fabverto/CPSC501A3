package Receive;

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

import Send.Object1;
import Send.Object2;
import Send.Object3;
import Send.Object4;
import Send.Object5;
import Send.Serializer;

public class tester {
	public static void main(String[] args) throws Exception {
	//	Object1 obj = new Object1();
	//	Object2 obj = new Object2();
	//	Object3 obj = new Object3();
		Object4 obj = new Object4();
	//	Object5 obj = new Object5();
	//	Inspector inspector = new Inspector();
	//	inspector.inspect(obj, true);
		
		Serializer ser = new Serializer();
		Document doc = ser.serialize(obj);
		
		XMLOutputter outputter = new XMLOutputter();
		System.out.println(new XMLOutputter().outputString(doc));
		
		Deserializer deserializer = new Deserializer();
		Object object = deserializer.deserialize(doc);
		
		Inspector inspector = new Inspector();
		inspector.inspect(object, true);
		
	}

}
