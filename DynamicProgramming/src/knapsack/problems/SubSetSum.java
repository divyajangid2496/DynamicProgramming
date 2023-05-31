package knapsack.problems;

public class SubSetSum {

  public static void main(String args[]) {
    SubSetSum ss = new SubSetSum();
    int[] num = {1, 2, 3, 7};
    System.out.println(ss.canPartition(num, 6));
    num = new int[] {1, 2, 7, 1, 5};
    System.out.println(ss.canPartition(num, 10));
    num = new int[] {1, 3, 4, 8};
    System.out.println(ss.canPartition(num, 6));
  }

  private boolean canPartition(int[] num, int sum) {
    boolean[][] dp = new boolean[num.length][sum + 1];

    for (int i = 0; i < num.length; i++) {
      dp[i][0] = true;
    }

    for (int i = 0; i <= sum; i++) {
      if (num[0] == i) {
        dp[0][i] = true;
      } else {
        dp[0][i] = false;
      }
    }

    for (int set = 1; set < num.length; set++) {
      for (int s = 1; s <= sum; s++) {
        if (dp[set - 1][s]) {
          dp[set][s] = dp[set-1][s];
        } else if (s >= num[set]) {
          dp[set][s] = dp[set - 1][s - num[set]];
        }
      }
    }

    return dp[num.length - 1][sum];
  }
}
