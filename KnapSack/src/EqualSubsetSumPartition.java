package knapsack.problems;

public class EqualSubsetSumPartition {

  public static void main(String args[]) {

    // Recursive Approach
    EqualSubsetSumPartition ps = new EqualSubsetSumPartition();
    // ps.recursiveApproach(ps);

    // DP Top-Down Approach
    // ps.topDownApproach(ps);

    // DP Bottom-Up Approach
    ps.bottomUpApproach(ps);
  }

  private void bottomUpApproach(EqualSubsetSumPartition ps) {
    int[] num = {1, 2, 3, 4};
    int totalSum = ps.getTotalSum(num);
    boolean[][] dp = new boolean[num.length][totalSum / 2 + 1];

    if (totalSum % 2 == 0) {
      System.out.println("Case 1: " + ps.canPartitionBottomUp(dp, num, totalSum / 2, 0));
    } else {
      System.out.println("Case 1: false");
    }

    num = new int[] {1, 1, 3, 4, 7};
    totalSum = ps.getTotalSum(num);
    dp = new boolean[num.length][totalSum / 2 + 1];

    if (totalSum % 2 == 0) {
      System.out.println("Case 2: " + ps.canPartitionBottomUp(dp, num, totalSum / 2, 0));
    } else {
      System.out.println("Case 2: false");
    }

    num = new int[] {2, 3, 4, 6};
    totalSum = ps.getTotalSum(num);
    dp = new boolean[num.length][totalSum / 2 + 1];

    if (totalSum % 2 == 0) {
      System.out.println("Case 3: " + ps.canPartitionBottomUp(dp, num, totalSum / 2, 0));
    } else {
      System.out.println("Case 3: false");
    }
  }

  private boolean canPartitionBottomUp(boolean[][] dp, int[] num, int sum, int currentIndex) {

    for (int i = 0; i < num.length; i++) {
      dp[i][0] = true;
    }

    for (int s = 0; s <= sum; s++) {
      if(num[0] == s) {
        dp[0][s] = true;
      } else {
        dp[0][s] = false;
      }
    }

    for (int set = 1; set < num.length; set++) {
      for (int s = 1; s <= sum; s++) {
        if (dp[set - 1][s]) {
          dp[set][s] = dp[set - 1][s];
        } else if (s >= num[set]) { // else we can find a subset to get the remaining sum
          dp[set][s] = dp[set - 1][s - num[set]];
        }
      }
    }

    return dp[num.length - 1][sum];
  }

  private void topDownApproach(EqualSubsetSumPartition ps) {
    int[] num = {1, 2, 3, 4};
    int totalSum = ps.getTotalSum(num);
    boolean[][] dp = new boolean[num.length][totalSum / 2 + 1];

    if (totalSum % 2 == 0) {
      System.out.println("Case 1: " + ps.canPartitionTopDown(dp, num, totalSum / 2, 0));
    } else {
      System.out.println("Case 1: false");
    }

    num = new int[] {1, 1, 3, 4, 7};
    totalSum = ps.getTotalSum(num);
    dp = new boolean[num.length][totalSum / 2 + 1];
    if (totalSum % 2 == 0) {
      System.out.println("Case 2: " + ps.canPartitionTopDown(dp, num, totalSum / 2, 0));
    } else {
      System.out.println("Case 2: false");
    }

    num = new int[] {2, 3, 4, 6};
    totalSum = ps.getTotalSum(num);
    dp = new boolean[num.length][totalSum / 2 + 1];
    if (totalSum % 2 == 0) {
      System.out.println("Case 3: " + ps.canPartitionTopDown(dp, num, totalSum / 2, 0));
    } else {
      System.out.println("Case 3: false");
    }
  }

  private boolean canPartitionTopDown(boolean[][] dp, int[] num, int sum, int currentIndex) {
    if (sum == 0) {
      return true;
    }

    if (num.length == 0 || currentIndex >= num.length) {
      return false;
    }

    if (dp[currentIndex][sum] == false) {
      if (num[currentIndex] <= sum) {
        if (canPartitionTopDown(dp, num, sum - num[currentIndex], currentIndex + 1)) {
          dp[currentIndex][sum] = true;
          return true;
        }
      }
      dp[currentIndex][sum] = canPartitionTopDown(dp, num, sum, currentIndex + 1);
    }

    return dp[currentIndex][sum];
  }

  private void recursiveApproach(EqualSubsetSumPartition ps) {
    int[] num = {1, 2, 3, 4};
    int totalSum = ps.getTotalSum(num);
    if (totalSum % 2 == 0) {
      System.out.println("Case 1: " + ps.canPartition(num, totalSum / 2, 0));
    } else {
      System.out.println("Case 1: false");
    }

    num = new int[] {1, 1, 3, 4, 7};
    totalSum = ps.getTotalSum(num);
    if (totalSum % 2 == 0) {
      System.out.println("Case 2: " + ps.canPartition(num, totalSum / 2, 0));
    } else {
      System.out.println("Case 2: false");
    }

    num = new int[] {2, 3, 4, 6};
    totalSum = ps.getTotalSum(num);
    if (totalSum % 2 == 0) {
      System.out.println("Case 3: " + ps.canPartition(num, totalSum / 2, 0));
    } else {
      System.out.println("Case 3: false");
    }
  }

  private int getTotalSum(int[] nums) {
    int totalSum = 0;

    for (int num : nums) {
      totalSum += num;
    }
    return totalSum;
  }

  private boolean canPartition(int[] nums, int sum, int currentIndex) {
    if (sum == 0) {
      return true;
    }

    if (nums.length == 0 || currentIndex >= nums.length) {
      return false;
    }

    if (nums[currentIndex] <= sum) {
      if (canPartition(nums, sum - nums[currentIndex], currentIndex + 1)) {
        return true;
      }
    }

    return canPartition(nums, sum, currentIndex + 1);
  }
}
