package larvae;

public class Honeycomb {

	private final int MAX = 17;

	private int direct[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 },
			{ 1, -1 }, { -1, 1 } };
	private int dp[][][] = new int[MAX][MAX][MAX];

	public Honeycomb() {
		dp[0][8][8] = 1;
		for (int k = 1; k <= 14; ++k) {
			for (int i = 1; i < MAX-1; ++i) {
				for (int j = 1; j < MAX-1; ++j) {
					for (int t = 0; t < 6; ++t) {
						dp[k][i][j] += dp[k - 1][i - direct[t][0]][j - direct[t][1]];
					}
				}
			}
		}
	}
	
	public int steps(int n){
		return dp[n][8][8];
	}

}
