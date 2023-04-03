import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class TVBreakerProblem {
    static ArrayList<node> store = new ArrayList<>() ;

    static class node{
        int start ;
        int last ;

        node(int start, int last){
            this.start = start ;
            this.last = last ;
        }

    }

    static node[] KMP(String text, String[] pat){

        for (String s : pat) {
            int i = 0 ;
            while (i <= text.length()- s.length()) {
                int j=0 ;
                while(j < s.length() && text.charAt(i+j) == s.charAt(j)){
                    j++ ;
                }

                if (j == s.length()){
                    int start = i ;
                    int end = start + s.length() - 1 ;
                    store.add(new node(start, end)) ;
                    i++ ;
                } else if(j == 0){
                    i++ ;
                } else {
                    i += j ;
                }

            }
        }

        node[] storePoint = new node[store.size()] ;
        if(!store.isEmpty()){
            for (int i = 0; i < store.size(); i++) {
                storePoint[i] = store.get(i);
            }
        }

        return storePoint ;

    }

    static void mergeSort(node[] arr){
        if (arr == null || arr.length <= 1) {
            return;
        }
        if(!store.isEmpty()){
            node[] aux = new node[arr.length];
            sort(arr, aux, 0, arr.length - 1);
        }

    }

    static void sort(node[] arr, node[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    static void merge(node[] arr, node[] aux, int lo, int mid, int hi) {
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
            } else if (aux[i].last < aux[j].last) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
        }
    }

    static int count(node[] arr){
        int cnt = 1 ;
        node head = arr[0] ;
        for(int i=1 ; i< arr.length ; i++){

            if(arr[i].start > head.last){
                cnt++ ;
                head = arr[i] ;
            }
        }

        return cnt ;
    }



    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        String text = in.next();
        int n = in.nextInt() ;
        String[] pat = new String[n] ;

        for(int i=0 ; i<n ; i++){
            pat[i] = in.next() ;
        }

        node[] kmp = KMP(text, pat) ;
        if(!store.isEmpty()){
            mergeSort(kmp);
            int count = count(kmp);
            out.print(count);
        } else {
            out.print("0");
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
