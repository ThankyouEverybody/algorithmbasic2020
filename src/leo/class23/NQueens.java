package leo.class23;

/**
 * @author Leo
 * @ClassName NQueens
 * @DATE 2021/1/20 4:48 下午
 * @Description
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 */
public class NQueens {

    public static class Recursion {
        public static int nQueens(int n) {
            if (n <= 0) {
                return 0;
            }
            int[] info = new int[n];
            return process(0, n, info);
        }

        private static int process(int i, int n, int[] info) {
            if (i == n) {
                return 1;
            }
            int res = 0;
            for (int j = 0; j < n; j++) {
                if (verify(info, j, i)) {
                    info[i] = j;
                    res += process(i + 1, n, info);
                }
            }
            return res;
        }

        private static boolean verify(int[] info, int j, int i) {
            for (int k = 0; k < j; k++) {
                if (info[k] == i || Math.abs(info[k] - i) == Math.abs(k - j)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int n = 10;

        long start = System.currentTimeMillis();
        System.out.println(Recursion.nQueens(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(Recursion.nQueens(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
