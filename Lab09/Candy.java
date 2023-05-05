import java.io.*;
import java.util.*;

import static java.lang.System.out;


public class Candy {

    static long[] array;
    static int[][] input;
    static node[] tree ;

    static class node{
        int l;
        int r;
        long sum;

        node(int l, int r, long sum){
            this.l = l;
            this.r = r ;
            this.sum = sum ;
        }
    }

    static void buildTree(int node, int start, int end){
        if(start==end){
            tree[node] = new node(start, end, array[start]);
        }
        else {
            int mid = (start+end)/2;
            int leftChild = 2*node + 1 ;
            int rightChild = 2*node + 2 ;

            buildTree(leftChild, start, mid);
            buildTree(rightChild, mid+1, end);

            tree[node] = new node(start,end,tree[leftChild].sum+tree[rightChild].sum);
        }
    }

    static long sum(int idx, int left, int right, int findLeft, int findRight){
        if(findRight < left || findLeft > right){
            return 0;

        } else if(findLeft <= left && findRight >= right){
            return tree[idx].sum ;

        } else {
            int mid = (left+right)/2 ;
            long leftSum = sum(2*idx+1, left,mid, findLeft, findRight);
            long rightSum = sum(2*idx+2, mid+1, right, findLeft, findRight);

            return leftSum + rightSum ;

        }
    }

    static void update(int idx, int left, int right, int where, int numb){
        if(left == right){
            tree[idx].sum = numb ;
        } else {
            int mid = (left+right)/2 ;
            if(where <= mid){
                update(2*idx+1, left, mid, where, numb);
            } else {
                update(2*idx+2, mid+1, right, where, numb);
            }
            tree[idx].sum = tree[2*idx + 1].sum + tree[2*idx+2].sum;
        }
    }

    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();

        int N = in.nextInt();
        int M = in.nextInt();

        /*
        int height = (int) Math.ceil(Math.log(N)/Math.log(2)) ;
        int length = (int) Math.pow(2,height+1) - 1 ;

         */


        array = new long[N] ;
        input = new int[M][3] ;
        tree = new node[4*N] ;

        for(int i=0 ; i<N ; i++){
            array[i] = in.nextLong();
        }

        for(int i=0 ; i<M ; i++){
            for(int j=0 ; j<3 ; j++){
                input[i][j] = in.nextInt();
            }
        }

        buildTree(0,0,N-1);

        // 0 𝑥 𝑣: Change the number of candies in pile a𝑥 to �
        // 1 𝑙 𝑟: print the sum of the number of candies in the given interval [𝑙, 𝑟]
        for(int i=0 ; i<M ; i++) {
            if (input[i][0] == 0) {
                //System.out.println("hai");
                update(0,0,N-1,input[i][1]-1,input[i][2]);

            } else {
                out.println(sum(0,0,N-1, input[i][1]-1, input[i][2]-1));
            }

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
