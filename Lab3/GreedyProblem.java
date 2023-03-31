import java.io.*;
import java.lang.reflect.Array;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class GreedyProblem {
    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();

        int a = in.nextInt() ;
        int b = in.nextInt() ;

        int[] apples = new int[a] ;
        int[] baskets = new int[b] ;

        long totalBasket = 0 ;
        for(int i=0 ; i<b ; i++){
            baskets[i] = in.nextInt() ;
            totalBasket = totalBasket + baskets[i] ;

        }
        for(int i=0 ; i<a ; i++){
            apples[i] = in.nextInt() ;
        }

        //sort the basket
        mergeSort(baskets);

        long distance = 0 ;
        int curBas = 0 ;
        if(a >= totalBasket){ //small basket take the nearest first

            for(int i=0; i<b ; i++){

                curBas = curBas + baskets[i];
                distance = distance + (2L * apples[curBas-1]) ;

            }


        }
        else { // biggest basket take the farthest

            int range = a-1 ;
            for(int i=b-1; i>=0 ; i--){
                if(range-curBas < 0){
                    break;
                }
                distance = distance + (2L * apples[range-curBas]) ;
                curBas += baskets[i];



            }

        }

        out.println(distance) ;

        out.close();
    }

    static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] aux = new int[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    static void sort(int[] arr, int[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    static void merge(int[] arr, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > hi) {
                arr[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
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
