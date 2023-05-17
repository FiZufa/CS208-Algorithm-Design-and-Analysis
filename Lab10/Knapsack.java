import java.io.*;
import java.util.*;

import static java.lang.System.out;


public class Knapsack {
    //static int N ;
    //static int[][] store ;
    // alice = 2
    // Bob = 1
    // draw = 0
    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt(); // Number of items
        int m = in.nextInt(); // Number of coins

        int[] weights = new int[n]; // Array to store weights of items
        int[] costs = new int[n]; // Array to store costs of items

        // Read weights of items
        for (int i = 0; i < n; i++) {
            costs[i] = in.nextInt();
        }

        // Read costs of items
        for (int i = 0; i < n; i++) {
            weights[i] = in.nextInt();
        }

        int[][] dp = new int[n + 1][m + 1];

        // Dynamic programming approach
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + costs[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        out.println(dp[n][m]); // Print the maximum sum of beauty


        out.close();
    }

}




class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}
