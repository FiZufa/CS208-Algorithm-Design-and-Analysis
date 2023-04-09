import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class Shopping {
    static Queue<Integer> prioQue = new PriorityQueue<>() ;

    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();

        int N = in.nextInt() ;
        int money = in.nextInt() ;

        int[] price = new int[N] ;
        int[] effic = new int[N] ;

        for(int i=0 ; i<N ; i++){
            price[i] = in.nextInt() ;
        }
        for (int i=0 ; i<N ; i++){
            effic[i] = in.nextInt() ;
        }


        int coin = money ;
        long total = 0 ;

        int curPrice = 0 ;


        for(int i=0 ; i<N ; i++){

            curPrice = price[i] % 100 ;


            if(curPrice==0){
                continue;
            }
            prioQue.add((100-curPrice)*effic[i]) ;
            if(coin >= curPrice){
                coin = coin - curPrice ;

            } else {
                coin = coin + 100 ;
                coin = coin - curPrice ;


                total += (long)prioQue.poll()  ;

            }

        }

        out.println(total);

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
