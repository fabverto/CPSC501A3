package Send;
public class Object2 {
	Object1 obj1;
	int intField;
	
	public Object2() {
		obj1 = new Object1();
		intField = 1;
	}
 
	public int getInt() {
		return intField;
	}
	
	public void setInt(int i) {
		intField = i;
	}
	
	public void setObj1Int(int i) {
		obj1.setInt(i);
	}
	
	public void setObj1Doub(double i) {
		obj1.setDoub(i);
	}
	
	public int getObj1Int() {
		return obj1.getInt();
	}
	
	public double getObj1Doub() {
		return obj1.getDoub();
	}
}
