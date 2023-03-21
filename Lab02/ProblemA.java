import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ProblemA {
    //static int N = 10000;
    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int n, m;
            n = in.nextInt();
            m = in.nextInt();

            ArrayList<Integer>[] arr = new ArrayList[n*2];
            for (int k = 0; k < n*2; k++) {
                arr[k] = new ArrayList<Integer>();
            }

            for (int k = 0; k < m; k++) {
                int a = in.nextInt();
                int b = in.nextInt();
                a--;
                b--;
                b += n;
                arr[a].add(b);
                arr[b].add(a);
            }
            n *= 2;
            out.println(shortest_cycle(n, arr));
        }
        out.close();
    }
    static int shortest_cycle(int n, ArrayList<Integer>[] arr) {
        int ans = 5000;

        for (int i = 0; i < n; i++) {

            ArrayList<Integer> dist = new ArrayList<Integer>(Collections.nCopies(n, (int) (5000)));

            ArrayList<Integer> par = new ArrayList<Integer>(Collections.nCopies(n, (int) (-1)));

            dist.set(i, 0);
            Queue<Integer> q = new LinkedList<Integer>();

            q.add(i);

            while (!q.isEmpty()) {

                int x = q.peek();
                q.remove();

                for (int child : arr[x]) {

                    if (dist.get(child) == (int) (5000)) {

                        dist.set(child, 1 + dist.get(x));

                        par.set(child, x);

                        q.add(child);
                    }

                    else if (par.get(x) != child && par.get(child) != x) {
                        int xx = Math.min(ans, dist.get(x) + dist.get(child) + 1);
                        if(xx == 4){
                            return 4;
                        }
                        if (xx > 3 && (xx % 2) == 0) {
                            ans = xx;
                        }
                    }
                }
            }
        }

        if (ans == 5000)
            return -1;

        else
            return ans;
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
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

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
