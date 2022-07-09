package algo.dynamic.knapsack.bottomUp;

public class Knapsack {
	
	public int solveKnapsack(int[] weights , int[] profits , int capacity) {
		
		if(capacity < 0 || profits.length < 0 || profits.length != weights.length) {
			return 0;
		}
		
	    //intialize tabular array
		int[][] dp = new int[profits.length][capacity+1];
		
		//if capacity is zero then we cannot select any item 
		//and hence profit will be zero
		for(int i = 0 ; i < profits.length ; i++) {
			dp[i][0] = 0;
		}
		
		//if we have only one item we will take it if its weight is less than capacity
		for(int c =0 ; c <= capacity ; c++) {
			if(weights[0] <= c) {
				dp[0][c] = profits[0];
			}
		}
		
		//process all sub-arrays for all the capacities
		for(int i = 1 ; i < profits.length ; i++) {
			for(int c = 1 ; c <= capacity ; c++) {
				int profitIncluded = 0;
				int profitExcluded = 0;
				if(weights[i] <= c) {
					profitIncluded  = profits[i] + dp[i-1][c-weights[i]];
				}
				profitExcluded = dp[i-1][c];
				
				dp[i][c] = Math.max(profitIncluded, profitExcluded);
			}
		}
		
		return dp[profits.length-1][capacity];
		
	}
	
	public static void main(String[] args) {
	    Knapsack ks = new Knapsack();
	    int[] profits = {1, 6, 10, 16};
	    int[] weights = {1, 2, 3, 5};
	    int maxProfit = ks.solveKnapsack(profits, weights, 7);
	    System.out.println("Total knapsack profit ---> " + maxProfit);
	    maxProfit = ks.solveKnapsack(profits, weights, 6);
	    System.out.println("Total knapsack profit ---> " + maxProfit);
	  }

}
