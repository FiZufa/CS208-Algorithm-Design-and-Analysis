import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.clearProperty;
import static java.lang.System.out;


public class Friends {
    static int[][] ST ;
    static int result ;
    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();

        int N = in.nextInt() ;
        int[] friends = new int[N] ;

        for(int i=0 ; i<N ; i++){
            friends[i] = in.nextInt() ;
        }

        int log = (int) (Math.log(N-1) / Math.log(2)) ;

        //System.out.println(y);

        ST = new int[log+1][] ;
        ST[0] = new int[N-1] ;

        int p, k ;

        for(int i=0 ; i<N-1 ; i++){
            ST[0][i] = Math.abs(friends[i+1] - friends[i]) ;
        }

        for(int i=1 ; i<=log ; i++){
            p = (int) Math.pow(2,i) ;
            k = p/2 ;

            ST[i] = new int[N-p] ;
            for(int j=0 ; j<N-p ; j++){
                ST[i][j] = GCD(ST[i-1][j], ST[i-1][j+k]) ;
            }


        }

        BSA(1,N-1) ;
        out.println(result);

        out.close();
    }

    static int GCD(int a, int b){
        if(b==0){
            return a ;
        } else {
            return GCD(b, a%b) ;
        }

    }

    static int query(int left, int right){
        int S = (int) Math.floor(Math.log(right-left+1) / Math.log(2));
        int l = ST[S][left-1] ;
        int r = ST[S][right-(1<<S)] ;

        return GCD(l,r) ;
    }

    static int BSA(int left, int right){
        for(int i=1 ; i<right ; i++){
            int l = i ;
            int r = right ;
            while(l<=r){
                int mid = (l+r)/2;
                if(query(i,mid) >= 2){
                    l = mid + 1 ;
                    result = Math.max(result, mid-i+2) ;
                } else {
                    r = mid - 1 ;
                }
            }
        }

        return 0 ;
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
