package largestsum;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		while (input.hasNext()) {
			int rowNum = input.nextInt();
			int[][] a = new int[rowNum][rowNum];

			for (int i = 0; i < rowNum; i++) {
				for (int j = 0; j <= i; j++) {
					a[i][j] = input.nextInt();
				}
			}

			LargestSum maxsum = new LargestSum(a, rowNum);
			System.out.println("Max is " + maxsum.maxSum());
			System.out.println(maxsum.path());
		}
		
		input.close();
	}
	

}
