package largestsum;

import java.util.Stack;

public class LargestSum {

	private int maxSum;
	private Stack<Integer> path;
	private int sums[][];

	public LargestSum(int[][] input, int numRow) {

		sums = copyTri(input, numRow);
		maxSum = Integer.MIN_VALUE;
		path = new Stack<Integer>();
		int[] maxCord = {-1, -1};
		
		for (int x = 0; x < numRow; x++) {
			for (int y = 0; y <= x; y++) {
				if (x == 0) {
					// do nothing
				} else if (y == 0) {
					sums[x][y] = sums[x][y] + sums[x - 1][y];
				} else if (x == y) {
					sums[x][y] = sums[x][y] + sums[x - 1][y - 1];
				} else {
					sums[x][y] = sums[x][y]
							+ Math.max(sums[x - 1][y], sums[x - 1][y - 1]);
				}

				if (maxSum < sums[x][y]) {
					maxSum = sums[x][y];
					maxCord[0] = x;
					maxCord[1] = y;
				}
			}
		}
		
		path.push(input[maxCord[0]][maxCord[1]]);
		dppath(input, maxCord);
		
	}

	private void dppath(int[][] input, int[] cord){
		
		if (cord[0] == 0 && cord[1] == 0){
			return;
		}
		else if (cord[1]== 0){
			int[] retcor = {cord[0]-1, cord[1]};
			path.push(input[cord[0]-1][cord[1]]);
			dppath(input, retcor);
		}
		else if (sums[cord[0]-1][cord[1]-1] > sums[cord[0]-1][cord[1]]){
			int[] retcor = {cord[0]-1, cord[1]-1};
			path.push(input[cord[0]-1][cord[1]-1]);
			dppath(input, retcor);
		}
		else if (sums[cord[0]-1][cord[1]] > sums[cord[0]-1][cord[1]-1]){
			int[] retcor = {cord[0]-1, cord[1]};
			path.push(input[cord[0]-1][cord[1]]);
			dppath(input, retcor);
		}
		
	}
	
	private int[][] copyTri(int[][] input, int numRow) {
		int sums[][] = new int[numRow][numRow];
		for (int x = 0; x < numRow; x++) {
			for (int y = 0; y <= x; y++) {
				sums[x][y] = input[x][y];
			}
		}
		return sums;
	}

	public int maxSum() {
		return maxSum;
	}

	public String path() {
		String ret = ""+path.pop();
		while(!path.empty()){
			ret = ret + "-->"+path.pop();
		}
		return ret;
	}
}
