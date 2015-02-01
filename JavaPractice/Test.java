import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int j = scan.nextInt();
		int judge = (j & (1 << 3)) >> 3;
		System.out.println(judge);
	}
}

