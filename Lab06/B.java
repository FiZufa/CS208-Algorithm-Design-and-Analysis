import java.io.*;
import java.util.*;

import static java.lang.System.out;


public class B {
    static final int M = (int) 2e6 + 10;
    static final int N = (int) 1e6 + 10;

    static int idx = 1;
    static int n, k, result;

    static int[] h = new int[N];
    static int[] to = new int[M];
    static int[] exp = new int[M];
    static int[] dis = new int[N];

    static void insFunct(int a, int b) {
        exp[idx] = h[a];
        h[a] = idx;
        to[idx] = b;
        idx++;
        dis[a]++;
    }

    static int DFS(int x, int fa) {
        if (dis[x] == 1)
            return 1;

        int na = 0;
        for (int u = h[x]; u != 0; u = exp[u]) {
            if (to[u] != fa) {
                int depth = DFS(to[u], x);
                if (depth + na > k) {
                    result++;
                    na = Math.min(na, depth);
                } else if (depth > na) {
                    na = depth;
                }
            }
        }

        return na != 0 ? na + 1 : 0;
    }


    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();

        n = in.nextInt();
        k = in.nextInt();

        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            insFunct(a, b);
            insFunct(b, a);
        }

        for (int i = 1; i <= n; i++) {
            if (dis[i] > 1) {
                int temp = DFS(i, 0);
                System.out.println(result + (temp != 0 ? 1 : 0));
                System.exit(0);
            }
        }

        out.println(result + 1);

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
