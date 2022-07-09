package algo.dynamic.knapsack.topdown;

class Knapsack {

	public int solveKnapsack(int[] profits, int[] weights, int capacity) {

		Integer[][] dp = new Integer[profits.length][capacity + 1];
		return knapsackRecursive(dp, profits, weights, capacity, 0);

	}

	private int knapsackRecursive(Integer[][] dp, int[] profits, int[] weights, int capacity, int currentIndex) {

		// base check
		if (capacity <= 0 || currentIndex >= profits.length) {
			return 0;
		}

		//check if the calculated value is present in the memory 
		//then return from the memory 
		if (dp[currentIndex][capacity] != null) {
			return dp[currentIndex][capacity];
		}

		int profitIncluded = 0;

		//recursive call to include the item
		if (weights[currentIndex] < capacity) {
			profitIncluded = profits[currentIndex]
					+ knapsackRecursive(dp, profits, weights, capacity - weights[currentIndex], currentIndex + 1);
		}

		//recursive call to skip the item
		int profitNotIncluded = knapsackRecursive(dp, profits, weights, capacity, currentIndex + 1);

		dp[currentIndex][capacity] = Math.max(profitIncluded, profitNotIncluded);

		return dp[currentIndex][capacity];

	}

	public static void main(String[] args) {
		Knapsack ks = new Knapsack();
		int[] profits = { 1, 6, 10, 16 };
		int[] weights = { 1, 2, 3, 5 };
		int maxProfit = ks.solveKnapsack(profits, weights, 7);
		System.out.println("Total knapsack profit ---> " + maxProfit);
		maxProfit = ks.solveKnapsack(profits, weights, 6);
		System.out.println("Total knapsack profit ---> " + maxProfit);
	}

}