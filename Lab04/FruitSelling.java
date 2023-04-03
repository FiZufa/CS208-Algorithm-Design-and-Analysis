import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class FruitSelling {

    static HashMap<Integer, Integer> hashMap = new HashMap<>() ;
    static int[] S ;
    static node[] P ;

    static class node{
        int left ;
        int right ;
        int price ;

        node(int left, int right, int price){
            this.left = left ;
            this.right = right ;
            this.price = price ;
        }
    }

    static boolean linearMatch(node fruit, int x){
        if(x > fruit.right){
            return false ;
        }
        // if x is idle
        if(P[hashMap.get(x)].left == 0){
            P[hashMap.get(x)] = fruit ;
            return true ;
        }
        // ai = getFruitofX(x)
        node frt = P[hashMap.get(x)] ;
        if(fruit.right > frt.right){
            return linearMatch(fruit, S[hashMap.get(x)+1]) ;

        } else {

            if(linearMatch(frt, S[hashMap.get(x)+1])) {
                P[hashMap.get(x)] = fruit ;
                return true ;
            }
            return false ;
        }

    }

    //static long profit()
    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();

        int N = in.nextInt() ;

        node[] store = new node[N] ;

        for(int i=0 ; i<N ; i++){
            int left = in.nextInt() ;
            int right = in.nextInt() ;
            int price = in.nextInt() ;

            store[i] = new node(left, right, price) ;
        }

        mergesortLeft(store); // ascendingly according to the left point
        //Arrays.sort(store.)
        /*
        for(int i=0 ; i<N ; i++){
            System.out.println(store[i].left);
        }

         */

        // generate active time slot
        S = new int[N] ;
        int x = 0 ;
        for(int i=0 ; i<N ; i++){
            x = Math.max(x+1,store[i].left) ;
            S[i] = x;
            hashMap.put(x, i) ;

        }

        /*
        System.out.println();

        for(int i=0 ; i<N ; i++){
            System.out.println(S[i]);
        }

         */
        mergesortPrice(store); // sort descendingly by price
        /*
        System.out.println();
        for(int i=0 ; i<N ; i++){
            System.out.println(store[i].price);
        }
         */
        P = new node[N] ;
        for(int i=0 ; i<N ; i++){
            P[i] = new node(0,0,0) ;
        }

        // call
        ArrayList<node> list = new ArrayList<>() ;
        for (int i=0 ; i<N ; i++){
            if(linearMatch(store[i], store[i].left)){
                list.add(store[i]) ;
            }
        }

        long profit = 0 ;
        for(int i=0 ; i<list.size() ; i++){
            profit = profit + list.get(i).price ;
        }

        out.print(profit);

        /*
        node[] timeSlot = new node[N] ;
        timeSlot[store[0].left] = store[0] ;
        for(int i=1 ; i<N ; i++){
            int l = store[i].left ;
            node cur = store[i] ;

            if(timeSlot[l] == null || !isoccupied[l]){ // timeslot empty
                timeSlot[l] = cur ;
                isoccupied[i] = true ;

            } else { // timeslot is occupied

                for(int j = l ; j<N ; j++){

                    if(timeSlot[j].right > cur.right){
                        //then move origin time slot
                        cur = timeSlot[j] ;
                        timeSlot[j] = cur ;
                    } else {
                        timeSlot[j] = cur ;
                        isoccupied[j] = false ;
                    }
                }
            }


        }

        int result = 0 ;
        for(int i=0 ; i<N ; i++){
            if(timeSlot[i] != null || !isoccupied[i]){
                result = result + timeSlot[i].price ;
            }
        }

        System.out.println(result);

         */


        out.close();
    }

    // merge sort descendingly
    static void mergesortLeft(node[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        node[] aux = new node[arr.length];
        sortLeft(arr, aux, 0, arr.length - 1);
    }

    static void sortLeft(node[] arr, node[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sortLeft(arr, aux, lo, mid);
        sortLeft(arr, aux, mid + 1, hi);
        mergeLeft(arr, aux, lo, mid, hi);
    }

    static void mergeLeft(node[] arr, node[] aux, int lo, int mid, int hi) {
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
            } else if (aux[i].left < aux[j].left) { // changed comparison operator
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
        }
    }

    // merge sort descendingly
    static void mergesortPrice(node[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        node[] aux = new node[arr.length];
        sortPrice(arr, aux, 0, arr.length - 1);
    }

    static void sortPrice(node[] arr, node[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sortPrice(arr, aux, lo, mid);
        sortPrice(arr, aux, mid + 1, hi);
        mergePrice(arr, aux, lo, mid, hi);
    }

    static void mergePrice(node[] arr, node[] aux, int lo, int mid, int hi) {
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
            } else if (aux[i].price > aux[j].price) { // changed comparison operator
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

