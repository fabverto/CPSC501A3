package Receive;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Vector;

public class Inspector {

    public void inspect(Object obj, boolean recursive) throws IllegalArgumentException, IllegalAccessException {
    	initialPrint();
    	
        Class c = obj.getClass();
        System.out.println("Declaring Class: " + c.getName());
        System.out.println();
        
        arrayHandler(obj, c);
        System.out.println();
        
        System.out.println("Class Fields");
        inspectFields(obj, c, recursive);
        
    }
    
   public void initialPrint() {
		System.out.println();
	   	System.out.println("-----------------------------Starting Inspection-----------------------------");
	   	System.out.println();
   }

    public void arrayHandler(Object obj, Class c) {
            if (c.isArray()) {
               printArrayInitialInfo(obj, c);
                for (int i = 0; i < Array.getLength(obj); i++)
                    System.out.println("Element at index " + i + ": " + Array.get(obj, i));
            }
    }
    
    public void printArrayInitialInfo(Object obj, Class c) {
    	System.out.println("Array of Type: " + c.getComponentType());
        System.out.println("Array of length: " + Array.getLength(obj));
    }

    
    public void inspectFields(Object obj, Class objClass, boolean recursive) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = objClass.getDeclaredFields();

            for (Field f : fields){
            	printInitialFieldInfo(f);
            	printFieldValue(obj, f);
            }

            if (recursive) {
                System.out.println("-------------  Recursive Inspection -------------");
                Vector<Field> objectsToInspect = new Vector();

                for (Field f : fields)
                    nonPrimitiveCheck(f, objectsToInspect);

                for (Field field : objectsToInspect) 
                    inspect(field.get(obj), recursive);
            }
    }
    
    public void nonPrimitiveCheck(Field f, Vector objectsToInspect) {
    	   if(!f.getType().isPrimitive() ) 
               objectsToInspect.addElement(f);
    }
    
    public void printFieldValue(Object obj, Field f) throws IllegalArgumentException, IllegalAccessException {
    	 f.setAccessible(true);
         System.out.println("Field Value: " + f.get(obj));
         System.out.println();
    }
    
    public void printInitialFieldInfo(Field f) {
    	System.out.println("Field: " + f.getName());
        System.out.println("Type: " + f.getType().getName());
    }
}