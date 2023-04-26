import java.io.*;
//import java.util.Scanner;
import java.util.StringTokenizer;

//import static java.lang.System.clearProperty;
import static java.lang.System.out;


public class Binary {
    static long N ;
    static long l ;
    static long r ;
    //static long bits ;
    //static int length ;
    //static int S=0 ;

    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();

        N = in.nextLong() ;
        l = in.nextLong() ;
        r = in.nextLong() ;

        long bits = (long) ((Math.log(N))/Math.log(2)) + 1; // how many bits

        long length = (long) Math.pow(2,bits) - 1 ; // length

        //out.println(bits);
        //out.println(length);

        long result = sum(r, N, length) - sum(l-1,N,length);

        out.println(result);



        out.close();
    }

    static long sum(long right, long N, long length){
        // 4 bits, length=15
        // right 7
        // n = 9

        if(length==1 || length==right){
            return N ;
        }

        long mid = (length+1)/2  ;
        if(right > mid){
            return N - sum(length-right, (N/2), mid-1);

        } else if(right < mid){
            return sum(right, (N/2), mid-1);

        } else {
            return (N/2) + (N%2);
        }

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
