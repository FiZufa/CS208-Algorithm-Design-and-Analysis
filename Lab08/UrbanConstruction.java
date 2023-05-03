import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class UrbanConstruction {

    static int N ;
    static node[] array;
    static int[] numb ;

    static class node{
        int value;
        int idx;
        int rank;

        node(int value, int idx, int rank){
            this.value = value;
            this.idx = idx ;
            this.rank = rank ;
        }

    }

    public static void main(String[] args){

        QReader in = new QReader();
        QWriter out = new QWriter();

        N = in.nextInt();
        array = new node[N] ;
        numb = new int[N] ;

        for(int i=0 ; i<N ; i++){

            int value = in.nextInt();
            int idx = i;

            array[i] = new node(value,idx,0);

        }

        Arrays.sort(array, new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return o1.value - o2.value;
            }
        });

        int r=1;
        array[0].rank = 1 ;

        for(int i=1 ; i<N ; i++){

            if(array[i-1].value != array[i].value){
                r++ ;
            }
            array[i].rank = r ;

        }

        for(int i=0 ; i<N ; i++){

            numb[array[i].idx] = array[i].rank;
        }

        /*
        for(int i=0 ; i<N ; i++){
            System.out.print(array[i].rank + " ");
        }
        System.out.println();

         */



        // do operation here
        merge(1,r,0,N-1);

        /*

        for(int i=0 ; i<N ; i++){
            System.out.print(array[i].value + " ");
        }

        System.out.println();

        for(int i=0 ; i<N ; i++){
            System.out.print(numb[i] + " ");
        }

        System.out.println();

         */
        out.println(-1 + " " + -1);


        out.close();

    }

    // what is max and min for?

    static int divide(int leftIdx, int rightIdx, int bd){

        if(leftIdx < rightIdx){

            int mid = (leftIdx + rightIdx)/2 ;
            int lbd = divide(leftIdx, mid, bd);
            int rbd = divide(mid+1, rightIdx, bd) ;
            int newBound = lbd + rbd ;
            if(rbd !=0 && (mid-leftIdx+1-lbd) != 0){
                reverse(leftIdx+lbd, mid+rbd);
                out.println((leftIdx+lbd+1) + " " + (mid+rbd+1));
            }

            return newBound;
        }
        else  if(numb[leftIdx] > bd){
            return 0;
        }
        else {
            return 1;
        }

    }

    static void merge(int min, int max, int left, int right) {
        int bd = (min+max) / 2 ;
        if(min == max){
            return ;
        }

        int mid = divide(left, right, bd);
        mid += left ;
        merge(min,bd,left,mid-1);
        merge(bd+1, max,mid,right);
    }

    static void reverse(int left, int right){

        while(left < right){
            // swap numb[left] and numb[right]
            int tmp = numb[left] ;
            numb[left] = numb[right];
            numb[right] = tmp;
            left++;
            right--;

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
