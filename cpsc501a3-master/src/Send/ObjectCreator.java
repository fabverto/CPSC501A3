package Send;
import java.util.Scanner;

public class ObjectCreator{
	
	Scanner scanner = new Scanner(System.in);
	
	public Object createObject() {
		//printMainMenu();
		//Scanner sc = new Scanner(System.in);
		int objChoice = getInput();
		System.out.println(objChoice);
		switch (objChoice) {
		case 1: return createObject1();
		case 2: return createObject2();
		case 3: return createObject3();
		case 4: return createObject4();
		case 5: return createObject5();
		case 6: System.exit(0);
	}
		return null;
}
	
	public int getInput() {
		printMainMenu();
		int objChoice = scanner.nextInt();
		return objChoice;
	}
	
	public static void printMainMenu() {
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("OBJECT CREATOR MENU");
		System.out.println("Choose one of the below objects you'd like to create");
		System.out.println();
		System.out.println("1 - A simple object with only primitives for instance variables");
		System.out.println("2 - An object that contains references to other objects");
		System.out.println("3 - An object that contains an array of primitives");
		System.out.println("4 - An object that contains an array of object references");
		System.out.println("5 - An object that uses an instance of one of Java’s collection classes to refer to several other objects");
		System.out.println("6 - Quit Program");
		System.out.println("----------------------------------------------------------------------------------");
	}
	
	public Object1 createObject1() {
		Object1 obj1 = new Object1();
		object1Menu();
		int choice = scanner.nextInt();
		
		while(choice != 3) {
			
			if(choice == 1)
			{
				System.out.println("Enter an Integer: ");
				int choiceField = scanner.nextInt();
				obj1.setInt(choiceField);
			}
			
			if(choice == 2)
			{
				System.out.println("Enter a Double: ");
				double choiceField = scanner.nextDouble();
				obj1.setDoub(choiceField);
			}
			
			if(choice == 4) {
				System.out.println("Adieu!");
				System.exit(0);
			}
			
			object1Menu();
			choice = scanner.nextInt();
		}
		
		System.out.println("Object 1 Created!");
		return obj1;
}
		
	
	
	public void object1Menu() {
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("Object 1 Menu");
		System.out.println("Set Fields");
		System.out.println("Pick which field you would like to change");
		System.out.println("1 - Integer Value");
		System.out.println("2 - Double Value");
		System.out.println("3 - Done Selecting, Create Object");
		System.out.println("4 - Exit Program");
		System.out.println("----------------------------------------------------------------------------------");
		
	}
	
	
	public Object2 createObject2() {
		Object2 obj2 = new Object2();
		object2Menu();
		int choice = scanner.nextInt();
		while(choice != 4) {
			
			if(choice == 1)
			{
				System.out.println("Enter an Integer for current object: ");
				int choiceField = scanner.nextInt();
				obj2.setInt(choiceField);
			}
			
			if(choice == 2)
			{
				System.out.println("Enter an Integer for referenced Object1: ");
				int choiceField = scanner.nextInt();
				obj2.setObj1Int(choiceField);
			}
			
			if (choice == 3)
			{
				System.out.println("Enter a Double for referenced Object1: ");
				double choiceField = scanner.nextDouble();
				obj2.setObj1Doub(choiceField);
			}
			
			if(choice == 5) {
				System.out.println("Adieu!");
				System.exit(0);
			}
			
			object2Menu();
			choice = scanner.nextInt();
		}
		
		System.out.println("Object 2 Created!");
		return obj2;
	}
	
	public void object2Menu() {
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("Object 2 Menu");
		System.out.println("Set Fields");
		System.out.println("Pick which field you would like to change");
		System.out.println("1 - Integer Value of Object 2");
		System.out.println("2 - Integer Value of Object 1 being referenced");
		System.out.println("3 - Double Value of Object 1 being referenced");
		System.out.println("4 - Done Selecting, Create Object");
		System.out.println("5 - Exit Program");
		System.out.println("----------------------------------------------------------------------------------");
	}

	public Object3 createObject3() {
		int[] arr = new int[5];
		Object3 obj3 = new Object3();
		object3Menu();
		int choice = scanner.nextInt();
		while(choice != 2) {
			
			if(choice == 1)
			{
				System.out.println("Enter the 4 integers you would like to set for the array: ");
				for(int i=0;i<=4;i++){
		            arr[i]=scanner.nextInt();
		        }
				obj3.setArray(arr);
			}
			
			if(choice == 3) {
				System.out.println("Adieu!");
				System.exit(0);
			}
			
			object3Menu();
			choice = scanner.nextInt();
		}
		
		System.out.println("Object 3 Created!");
		return obj3;
	}
	
	public void object3Menu() {
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("Object 3 Menu");
		System.out.println("Array Set Fields");
		System.out.println("Pick what you would like to do");
		System.out.println("1 - Set array values");
		System.out.println("2 - Done Selecting, Create Object");
		System.out.println("3 - Exit Program");
		System.out.println("----------------------------------------------------------------------------------");
	}

	public Object4 createObject4() {
		Object4 obj4 = new Object4();
		object4Menu();
		int choice = scanner.nextInt();
		while(choice != 2) {
			
			if(choice == 1){
				System.out.println("Pick which object1 of the 3 you would like to set the fields of");
				System.out.println("1 - Object1 index 0");
				System.out.println("2 - Object1 index 1");
				System.out.println("3 - Object1 index 2");
				System.out.println("4 - Done");
				int choiceArray = scanner.nextInt();
						if(choiceArray==1) {
							System.out.println("Enter an Integer for referenced Object1 index 0: ");
							int choice1 = scanner.nextInt();
							obj4.setObj4Int1(choice1);
							System.out.println("Enter a Double for referenced Object1 index 0: ");
							double choice2 = scanner.nextDouble();
							obj4.setObj4Doub1(choice2);
						}
						
						if(choiceArray==2) {
							System.out.println("Enter an Integer for referenced Object1 index 1: ");
							int choice1 = scanner.nextInt();
							obj4.setObj4Int2(choice1);
							System.out.println("Enter a Double for referenced Object1 index 1: ");
							double choice2 = scanner.nextDouble();
							obj4.setObj4Doub2(choice2);
						}
						
						if(choiceArray==3) {
							System.out.println("Enter an Integer for referenced Object1 index 2: ");
							int choice1 = scanner.nextInt();
							obj4.setObj4Int3(choice1);
							System.out.println("Enter a Double for referenced Object1 index 2: ");
							double choice2 = scanner.nextDouble();
							obj4.setObj4Doub3(choice2);
						}
			}
			if(choice == 3) {
				System.out.println("Adieu!");
				System.exit(0);
			}

			object4Menu();
			choice = scanner.nextInt();
			}
		System.out.println("Object 4 Created!");
		return obj4;
		}
	
	public void object4Menu() {
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("Object 4 Menu");
		System.out.println("Array of Object1 Set Fields");
		System.out.println("Pick what you would like to do");
		System.out.println("1 - Set Fields for Array of Object1 ");
		System.out.println("2 - Done Selecting, Create Object");
		System.out.println("3 - Exit Program");
		System.out.println("----------------------------------------------------------------------------------");
	}

	public Object5 createObject5() {
		Object5 obj5 = new Object5();
		object5Menu();
		int choice = scanner.nextInt();
		
		while(choice != 1) {
			if(choice == 2) {
				System.out.println("Adieu!");
				System.exit(0);
			}
			
			object5Menu();
			choice = scanner.nextInt();
		}
		
		System.out.println("Object 5 Created!");
		return obj5;
	}
	
	public void object5Menu() {
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("Object 5 Menu");
		System.out.println("Adding 4 Object1 to Object5 List");
		System.out.println("Pick what you would like to do");
		System.out.println("1 - Done Selecting, Create Object");
		System.out.println("2 - Exit Program");
		System.out.println("----------------------------------------------------------------------------------");
	}

	
}