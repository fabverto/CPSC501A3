package Send;
import java.lang.reflect.*;
import java.util.IdentityHashMap;
import java.util.Map;
import org.jdom2.Document;
import org.jdom2.Element;

public class Serializer {

	public static Document serialize(Object obj) throws Exception {
		IdentityHashMap map = new IdentityHashMap();
		Document doc = new Document(new Element("serialization"));
		
		return serializeHelper(obj, doc, map);
	}

	public static void setupElement(Class c, Document doc, Element element, String id) {
		element.setAttribute("class", c.getName());
		element.setAttribute("id", id);
		doc.getRootElement().addContent(element);
	}
	
	private static Document serializeHelper(Object obj, Document doc, Map map) throws Exception {
		String id = Integer.toString(map.size());
		map.put(obj, id);
		Class c = obj.getClass();

		Element element = new Element("object");
		setupElement(c, doc, element, id);
		
		if (!c.isArray()) {
			Field[] fields = c.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				serializeNonArray(obj, element, doc, map, fields, i);
			}
		}
		
		else 
			serializeArray(obj, c, element, doc, map);
		
		return doc;
	}

	public static void serializeArray(Object obj, Class c, Element element, Document doc, Map map) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, Exception {
		Class componentType = c.getComponentType();

		int length = Array.getLength(obj);
		element.setAttribute("length", Integer.toString(length));
		for (int i = 0; i < length; i++)
			element.addContent(serializeVariable(componentType, Array.get(obj, i), doc, map));
	}
	
	public static void serializeNonArray(Object obj, Element element, Document doc, Map map, Field[] fields, int i) throws Exception {
		Element elem = new Element("field");
		elem.setAttribute("name", fields[i].getName());
		elem.setAttribute("declaringclass", fields[i].getDeclaringClass().getName());
		elem.addContent(serializeVariable(fields[i].getType(), fields[i].get(obj), doc,map));
		element.addContent(elem);
	}
	
	
	public static Element serializeVariable(Class c, Object obj, Document doc, Map map) throws Exception {
		if (!c.isPrimitive()) {
			Element ref = new Element("reference");
			nonPrimitiveHelper(map, ref, doc, obj);
			return ref;
		} 
		else {
			Element value = new Element("value");
			return value.setText(obj.toString());
		}
	}
	
	public static void nonPrimitiveHelper(Map map, Element ref, Document doc, Object child) throws Exception {
		if (map.containsKey(child))
			ref.setText(map.get(child).toString());
		else {
			ref.setText(Integer.toString(map.size()));
			serializeHelper(child, doc, map);
		}
	}
}