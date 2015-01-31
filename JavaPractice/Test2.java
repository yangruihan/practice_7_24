public class Test2 {

	public static void main(String[] args) {
		MyClass myClass = new MyClass();
		myClass.output();
	}
}

interface MyInterface {

	public void output();
}

interface MyInterface2 {

	public void output();
}

class MyClass implements MyInterface, MyInterface2 {

	public void output() {
		System.out.println("hello world");		
	}
}
