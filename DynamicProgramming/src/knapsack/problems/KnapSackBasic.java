package knapsack.problems;

public class KnapSackBasic {

  public static void main(String args[]) {
    KnapSackBasic ks = new KnapSackBasic();
    int[] profits = {1, 6, 10, 16};
    int[] weights = {1, 2, 3, 5};
    //Recursive Approach
    int maxProfit = ks.solveKnapsackRecursive(profits, weights, 7, 0);
    System.out.println("Total knapsack profit Recursive---> " + maxProfit);
    maxProfit = ks.solveKnapsackRecursive(profits, weights, 6, 0);
    System.out.println("Total knapsack profit Recursive---> " + maxProfit);

    //DP Top-Down Approach
    int capacity = 7;
    int[][] dp = new int[profits.length][capacity+1];

    maxProfit = ks.solveKnapsackTopDown(dp ,profits, weights, capacity, 0);
    System.out.println("Total knapsack profit Top-Down---> " + maxProfit);

    dp = new int[profits.length][capacity+1];

    capacity = 6;
    maxProfit = ks.solveKnapsackTopDown(dp, profits, weights, capacity, 0);
    System.out.println("Total knapsack profit Top-Down---> " + maxProfit);

    //DP Bottom-Up Memoization Approach
    capacity = 7;

    initializeDPArray(ks, profits, weights, capacity);


    capacity = 6;
    initializeDPArray(ks, profits, weights, capacity);

  }

  private static void initializeDPArray(KnapSackBasic ks, int[] profits, int[] weights,
      int capacity) {
    int maxProfit;

    int[][] dp = new int[2][capacity+1];

    for(int i = 0 ; i< capacity;i++) {
      if(weights[0] <= i){
        dp[0][i] = profits[0];
        dp[1][i] = profits[0];
      }
    }

    maxProfit = ks.solveKnapsackBottomUp(dp, profits, weights, capacity);
    System.out.println("Total knapsack profit Bottom-Up---> " + maxProfit);
  }

  private int solveKnapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
    if (capacity <= 0 || currentIndex >= profits.length) {
      return 0;
    }

    int profit1 = 0;
    if (weights[currentIndex] <= capacity) {
      profit1 =
          profits[currentIndex]
              + solveKnapsackRecursive(profits, weights, capacity - weights[currentIndex], currentIndex + 1);
    }

    int profit2 = solveKnapsackRecursive(profits, weights, capacity, currentIndex + 1);

    return Math.max(profit1, profit2);
  }

  private int solveKnapsackTopDown(int[][] dp, int[] profits, int[] weights, int capacity, int currentIndex) {

    if (capacity <= 0 || currentIndex >= profits.length) {
      return 0;
    }

    int profit1 = 0;

    if(dp[currentIndex][capacity] == 0) {
      if (weights[currentIndex] <= capacity) {
        profit1 =
            profits[currentIndex]
                + solveKnapsackRecursive(profits, weights, capacity - weights[currentIndex], currentIndex + 1);
      }

      int profit2 = solveKnapsackRecursive(profits, weights, capacity, currentIndex + 1);

      dp[currentIndex][capacity] =  Math.max(profit1, profit2);
    }

    return  dp[currentIndex][capacity];
  }

  private int solveKnapsackBottomUp(int[][] dp, int[] profits, int[] weights, int capacity) {

    if(capacity <= 0 || profits.length != weights.length || profits.length == 0) {
      return 0;
    }

    for(int currentIndex = 1; currentIndex < profits.length; currentIndex++) {
      for(int cap = 1; cap <= capacity; cap++) {
        if (cap >= weights[currentIndex]) {
          dp[currentIndex%2][cap] =
              Math.max(
                  dp[(currentIndex - 1) % 2][cap], profits[currentIndex] + dp[(currentIndex - 1)%2][cap - weights[currentIndex]]);
        } else {
          dp[currentIndex%2][cap] = dp[(currentIndex - 1)%2][cap];
        }
      }
    }

    return dp[(profits.length-1)%2][capacity];
  }



}
