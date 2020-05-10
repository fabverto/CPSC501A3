package Send;

public class Object3 {
	
int[] array = new int[5];
	
	public Object3() {
		for(int i=0; i<= 4; i++)
			array[i] = 0;
	}

	public void setArray(int[] a) {
		for(int i=0; i<= 4; i++)
			array[i] = a[i];
	}
}
