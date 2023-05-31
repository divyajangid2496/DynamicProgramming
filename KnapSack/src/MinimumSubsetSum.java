package knapsack.problems;

public class MinimumSubsetSum {

  public static void main(String args[]) {
    MinimumSubsetSum ps = new MinimumSubsetSum();

    // Recursive Approach
    int[] num = {1, 2, 3, 9};
    System.out.println(ps.canPartitionRecursive(num, 0, 0, 0));
    num = new int[] {1, 2, 7, 1, 5};
    System.out.println(ps.canPartitionRecursive(num, 0, 0, 0));
    num = new int[] {1, 3, 100, 4};
    System.out.println(ps.canPartitionRecursive(num, 0, 0, 0));

    // DP Top-Down Approach
    num = new int[] {1, 2, 3, 9};

    int[][] dp = new int[num.length][ps.getTotalSum(num) + 1];
    System.out.println(ps.canPartitionTopDown(dp, num, 0, 0, 0));
    num = new int[] {1, 2, 7, 1, 5};
    dp = new int[num.length][ps.getTotalSum(num) + 1];
    System.out.println(ps.canPartitionTopDown(dp, num, 0, 0, 0));
    num = new int[] {1, 3, 100, 4};
    dp = new int[num.length][ps.getTotalSum(num) + 1];
    System.out.println(ps.canPartitionTopDown(dp, num, 0, 0, 0));

    // DP Botton-Up Approach
    num = new int[] {1, 2, 3, 9};
    System.out.println(ps.canPartitionBottomUp(num));

    num = new int[] {1, 2, 7, 1, 5};
    System.out.println(ps.canPartitionBottomUp(num));

    num = new int[] {1, 3, 100, 4};
    System.out.println(ps.canPartitionBottomUp(num));
  }

  private int canPartitionBottomUp(int[] num) {
    int sum = getTotalSum(num) / 2 + 1;
    boolean[][] dp = new boolean[num.length][sum];

    for (int i = 0; i < num.length; i++) {
      dp[i][0] = true;
    }

    for (int s = 0; s < sum; s++) {
      if (num[0] == s) {
        dp[0][s] = true;
      } else {
        dp[0][s] = false;
      }
    }

    for (int set = 1; set < num.length; set++) {
      for (int s = 1; s < sum; s++) {
        if (dp[set - 1][s]) {
          dp[set][s] = true;
        } else if (s >= num[set]) {
          dp[set][s] = dp[set - 1][s - num[set]];
        }
      }
    }

    boolean isTrue = false;
    int sum1 = sum - 1;
    while (!isTrue) {
      isTrue = dp[num.length - 1][sum1];
      if(isTrue) break;
      sum1--;
    }

    int sum2 = getTotalSum(num) - sum1;

    return Math.abs(sum2 - sum1);
  }

  private int canPartitionTopDown(int[][] dp, int[] num, int sum1, int sum2, int currentIndex) {

    if (currentIndex == num.length) {
      return Math.abs(sum2 - sum1);
    }

    if (dp[currentIndex][sum1] == 0) {
      int diff1 = canPartitionTopDown(dp, num, sum1 + num[currentIndex], sum2, currentIndex + 1);

      int diff2 = canPartitionTopDown(dp, num, sum1, sum2 + num[currentIndex], currentIndex + 1);

      dp[currentIndex][sum1] = Math.min(diff1, diff2);
    }

    return dp[currentIndex][sum1];
  }

  private int getTotalSum(int[] nums) {
    int totalSum = 0;

    for (int num : nums) {
      totalSum += num;
    }
    return totalSum;
  }

  private int canPartitionRecursive(int[] num, int sum1, int sum2, int currentIndex) {
    if (currentIndex == num.length) {
      return Math.abs(sum2 - sum1);
    }

    int diff1 = canPartitionRecursive(num, sum1 + num[currentIndex], sum2, currentIndex + 1);

    int diff2 = canPartitionRecursive(num, sum1, sum2 + num[currentIndex], currentIndex + 1);

    return Math.min(diff2, diff1);
  }
}
