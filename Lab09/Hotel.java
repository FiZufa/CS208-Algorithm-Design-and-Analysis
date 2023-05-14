import java.io.*;
import java.util.*;

import static java.lang.System.out;


public class Hotel {
    static List<Integer> store ;
    static int[] hotels ;

    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();

        int N = in.nextInt(); // numb or rooms
        int M = in.nextInt(); // numb of request

        hotels = new int[N] ;
        store = new ArrayList<>() ;

        for(int i=0 ; i<M ; i++){
            int input = in.nextInt() ;
            if(input == 2){
                int l = in.nextInt();
                int r = in.nextInt();

                for(int j=l-1 ; j<r ; j++){
                    hotels[j] = 0 ;
                }

            } else if (input == 1){
                int checkin = in.nextInt() ;

                int left = -1 ;
                for(int j=0 ; j<=N-checkin ; j++){
                    boolean isFound = true ;
                    for(int k=j ; k<j+checkin ; k++){
                        if(hotels[k] == 1){
                            isFound = false ;
                            break;
                        }
                    }
                    if(isFound){
                        left = j ;
                        break;
                    }
                }
                if(left == -1){
                    store.add(0) ;
                } else {
                    for(int j=left ; j<left+checkin ; j++){
                        hotels[j] = 1 ;
                    }
                    store.add(left+1);
                }
            }
        }

        for(int i=0 ; i<store.size() ; i++){
            out.println(store.get(i));
        }


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
