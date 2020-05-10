package Receive;
import org.jdom2.Document;
import org.jdom2.Element;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deserializer {
	
	public static Object deserialize(Document doc) throws Exception {
		List objList = doc.getRootElement().getChildren();
		Map map = new HashMap();
		createInstances(map, objList);
		assignFieldValues(map, objList);
		return map.get("0");
	}

	private static void createInstances(Map map, List objList) throws Exception {
		for (int i=0; i<objList.size(); i++) {
			Element elem = (Element) objList.get(i);
			Class cls = Class.forName(elem.getAttributeValue("class"));
			Object instance = null;
			if (!cls.isArray()) {
				Constructor c = cls.getDeclaredConstructor(null);
				if (!Modifier.isPublic(c.getModifiers()))
					c.setAccessible(true);
				instance = c.newInstance(null);
			} else
				instance = Array.newInstance(cls.getComponentType(), Integer.parseInt(elem.getAttributeValue("length")));
			map.put(elem.getAttributeValue("id"), instance);
		}
	}
	
	private static void assignFieldValues(Map table, List objList) throws Exception {
		for (int i=0; i < objList.size(); i++) {
			Element oElt = (Element) objList.get(i);
			Object instance = table.get(oElt.getAttributeValue("id"));
			List fElts = oElt.getChildren();
			if (!instance.getClass().isArray()) {
				for (int j=0; j<fElts.size(); j++) {
					Element fElt = (Element) fElts.get(j);
					String className = fElt.getAttributeValue("declaringclass");
					Class fieldDC = Class.forName(className);
					String fieldName = fElt.getAttributeValue("name");
					Field f = fieldDC.getDeclaredField(fieldName);
					if (!Modifier.isPublic(f.getModifiers()))
						f.setAccessible(true);
					
					Element vElt = (Element) fElt.getChildren().get(0);
					f.set(instance, deserializeValue(vElt, f.getType(), table));
				}
			} else {
				Class comptype = instance.getClass().getComponentType();
				for (int j=0; j<fElts.size(); j++)
					Array.set(instance, j, deserializeValue((Element)fElts.get(j), comptype, table));
			}
		}
	}
	
	private static Object deserializeValue(Element vElt, Class fieldType, Map table) {
		String valtype = vElt.getName();
		if (valtype.equals("null"))
			return null;
		else if (valtype.equals("reference"))
			return table.get(vElt.getText());
		else {
			if (fieldType.equals(int.class))
				return Integer.valueOf(vElt.getText());
			else if (fieldType.equals(double.class))
				return Double.valueOf(vElt.getText());
			else
				return vElt.getText();
		}
	}
}