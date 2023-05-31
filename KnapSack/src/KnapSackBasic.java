/**
 * Given the weights and profits of 'N' items, we are asked to put these items in a knapsack that has a capacity 'C'.
 * The goal is to get the maximum profit from the items in the knapsack.
 * Each item can only be selected once, as we don't have multiple quantities of any item.
 *
 * Example:
 * Items: { Apple, Orange, Banana, Melon }
 * Weights: { 2, 3, 1, 4 }
 * Profits: { 4, 5, 3, 7 }
 * Knapsack capacity: 5
 *
 * Output: 10
 * Explanation: Banana + Melon (total weight 5) => 10 profit
 */
public class KnapSackBasic {

  public static void main(String args[]) {
    //input data
    int[] profits = {1, 6, 10, 16};
    int[] weights = {1, 2, 3, 5};

    //Recursive Approach
    int maxProfit = solveKnapsackRecursive(profits, weights, 7, 0);
    System.out.println("Total knapsack profit Recursive---> " + maxProfit);
    maxProfit = solveKnapsackRecursive(profits, weights, 6, 0);
    System.out.println("Total knapsack profit Recursive---> " + maxProfit);
    System.out.println();

    //DP Top-Down Approach
    int capacity = 7;
    int[][] dp = new int[profits.length][capacity+1];

    maxProfit = solveKnapsackTopDown(dp ,profits, weights, capacity, 0);
    System.out.println("Total knapsack profit Top-Down---> " + maxProfit);

    dp = new int[profits.length][capacity+1];

    capacity = 6;
    maxProfit = solveKnapsackTopDown(dp, profits, weights, capacity, 0);
    System.out.println("Total knapsack profit Top-Down---> " + maxProfit);
    System.out.println();

    //DP Bottom-Up Memoization Approach
    capacity = 7;

    initializeDPArray(profits, weights, capacity);

    capacity = 6;
    initializeDPArray(profits, weights, capacity);
  }

  private static void initializeDPArray(int[] profits, int[] weights,
      int capacity) {
    int maxProfit;

    int[][] dp = new int[2][capacity+1];

    for(int i = 0 ; i< capacity;i++) {
      if(weights[0] <= i){
        dp[0][i] = profits[0];
        dp[1][i] = profits[0];
      }
    }

    maxProfit = solveKnapsackBottomUp(dp, profits, weights, capacity);
    System.out.println("Total knapsack profit Bottom-Up---> " + maxProfit);
  }

  /**
   * The basic approach to solve this problem is that we have 2 options:
   * 1. Create a new set which INCLUDES item 'i' if the total weight does not exceed the capacity, and
   *    recursively process the remaining capacity and items
   * 2. Create a new set WITHOUT item 'i', and recursively process the remaining items
   *
   * Take the max of above to cases
   *
   * @param profits represents the array of profit of each item
   * @param weights represents the array of weights of each item
   * @param capacity represents the capacity of the sack
   * @param currentIndex keeps track of the current index being processed
   * @return max profit
   */
  private static int solveKnapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
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

  /**
   * The top-down approach is similar to the recursive approach except in this case we save the output of each step so that,
   * we don't have to process the same step again. If the output of a step already exist then we return it, else we process.
   * Here we use the dp array to store the result
   * @param dp represents the memoization array
   * @param profits represents the array of profit of each item
   * @param weights represents the array of weights of each item
   * @param capacity represents the capacity of the sack
   * @param currentIndex keeps track of the current index being processed
   * @return max profit
   */
  private static int solveKnapsackTopDown(int[][] dp, int[] profits, int[] weights, int capacity, int currentIndex) {

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

  /**
   * The approach for bottom up dp solution is that, for each item at index 'i' (0 <= i < items.length)
   * and capacity 'c' (0 <= c <= capacity), we have two options:
   *  1. Exclude the item at index 'i'. In this case, we will take whatever profit we get from the sub-array excluding this item => dp[i-1][c]
   *  2. Include the item at index 'i' if its weight is not more than the capacity. In this case, we include its profit plus whatever profit we get from the remaining capacity and from remaining items => profits[i] + dp[i-1][c-weights[i]]
   *
   * Finally, our optimal solution will be maximum of the above two values:
   *    dp[i][c] = max (dp[i-1][c], profits[i] + dp[i-1][c-weights[i]])
   *
   * @param dp represents the 2-D array to save the intermediate results
   * @param profits represents the array of profit of each item
   * @param weights represents the array of weights of each item
   * @param capacity represents the capacity of the sack
   * @return max profit
   */
  private static int solveKnapsackBottomUp(int[][] dp, int[] profits, int[] weights, int capacity) {

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
