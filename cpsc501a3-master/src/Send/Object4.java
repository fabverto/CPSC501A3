package Send;
public class Object4 {
	
	Object1 [] obj1Array;
	
	public Object4() {
		obj1Array = new Object1[3];
		obj1Array[0] = new Object1();
		obj1Array[1] = new Object1();
		obj1Array[2] = new Object1();
	}
	
	public void setObj4Int1(int i) {
		obj1Array[0].setInt(i);
	}
	
	public void setObj4Doub1(double d) {
		obj1Array[0].setDoub(d);
	}

	public void setObj4Int2(int i) {
		obj1Array[1].setInt(i);
	}

	public void setObj4Doub2(double d) {
		obj1Array[1].setDoub(d);
	}

	public void setObj4Int3(int i) {
		obj1Array[2].setInt(i);
	}

	public void setObj4Doub3(double d) {
		obj1Array[2].setDoub(d);
	}
}
