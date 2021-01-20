package leo.class23;

/**
 * @author Leo
 * @ClassName SplitSumClosedSizeHalf
 * @DATE 2021/1/20 9:29 上午
 * @Description
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 */
public class SplitSumClosedSizeHalf {

    static class Recursion {
        public static int minSumAndSizeHalf(int[] arr) {
            if (arr == null || arr.length < 2) {
                return 0;
            }
            int sum = 0;
            for (int num : arr) {
                sum += num;
            }
            if ((arr.length & 1) == 0) {
                return process(arr, 0, arr.length / 2, sum / 2);
            }else{
                int p1 = process(arr, 0, arr.length / 2 + 1, sum / 2);
                int p2 = process(arr, 0, arr.length / 2, sum / 2);
                return Math.max(p1, p2);
            }
        }

        private static int process(int[] arr, int i, int count, int rest) {
            if (i == arr.length) {
                return count == 0 ? 0 : -1;
            }
            int p1 = process(arr, i + 1, count, rest);
            int next = -1;
            int p2 = -1;
            if (arr[i] <= rest) {
                next = process(arr, i + 1, count - 1, rest - arr[i]);
            }
            if (next != -1) {
                p2 = arr[i] + next;
            }
            return Math.max(p1, p2);

        }
    }

    static class Dp {
        public static int minSumAndSizeHalf(int[] arr) {
            if (arr == null || arr.length < 2) {
                return 0;
            }
            int sum = 0;
            for (int i : arr) {
                sum += i;
            }
            int n = arr.length;
            int m = (n + 1) / 2;
            sum /= 2;
            int[][][] dp = new int[n + 1][m + 1][sum + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    for (int k = 0; k <= sum; k++) {
                        dp[i][j][k] = -1;
                    }
                }
            }
            for (int i = 0; i <= sum; i++) {
                dp[n][0][i] = 0;
            }
            for (int i = n - 1; i >= 0; i--) {
                for (int count = 0; count <= m; count++) {
                    for (int rest = 0; rest <= sum; rest++) {
                        int p1 = dp[i + 1][count][rest];
                        int next = -1;
                        int p2 = -1;
                        if (count-1>=0&&arr[i] <= rest) {
                            next = dp[i + 1][count - 1][rest - arr[i]];
                        }
                        if (next != -1) {
                            p2 = arr[i] + next;
                        }
                        dp[i][count][rest] = Math.max(p1, p2);
                    }
                }
            }

            if ((n & 1) == 0) {
                return dp[0][n / 2][sum];
            }
            return Math.max(dp[0][m - 1][sum], dp[0][m][sum]);
        }
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = Recursion.minSumAndSizeHalf(arr);
            int ans2 = Dp.minSumAndSizeHalf(arr);
            if (ans1 != ans2 ) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
