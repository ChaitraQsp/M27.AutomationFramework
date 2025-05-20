package practice;

public class GenericMethodPractice {
	
	//test script
	public static void main(String[] args) {//Caller function
		
		int sum = add(10,20);
		System.out.println(sum);
		System.out.println(add(sum,40));
		System.out.println(add(30,50));
	}

	public static int add(int a, int b)//called function - generic method
	{
		int c = a+b;//logic
		return c;
	}
	
	public static int add(int a, int b, int s)//called function - generic method
	{
		int c = a+b+s;//logic
		return c;
	}
	
	
}
