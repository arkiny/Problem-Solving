package larvae;

import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int numberCase = input.nextInt();
		Honeycomb a = new Honeycomb();
		
		for (int i = 0; i < numberCase; i++) {
			System.out.println(a.steps(input.nextInt()));
		}
	}

	
}
