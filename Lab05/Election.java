import java.io.*;
import java.util.*;

import static java.lang.System.out;

public class Election {

    //static Queue<node> prioQue = new PriorityQueue<>(new MoneyComparator()) ;
    static int N;
    static node[] array ;

    static class node{
        int stu ;
        int pay ;

        node(int stu, int pay){
            this.stu = stu ;
            this.pay = pay ;
        }
    }

    static class MoneyComparator implements Comparator<node> {
        public int compare(node n1, node n2) {
            return Integer.compare(n1.pay, n2.pay);
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int test = in.nextInt() ;

        for(int h=0 ; h<test ; h++){
            N = in.nextInt() ;
            array = new node[N] ;

            for(int i=0 ; i<N ; i++){
                int stu = in.nextInt() ;
                int pay = in.nextInt() ;

                array[i] = new node(stu, pay) ;

            }
            Arrays.sort(array, new Comparator<node>() {
                @Override
                public int compare(node o1, node o2) {
                    return o2.stu- o1.stu;
                }
            });

            Queue<node> prioQue = new PriorityQueue<>(new MoneyComparator()) ;
            //out.println();
            /*
            for (int i=0 ; i<N ; i++){
                out.println(array[i].stu + " " + array[i].pay);
            }

             */



            int temp = 0 ;
            long total = 0 ;
            for(int i=0 ; i<N ; i++){
                prioQue.add(array[i]) ;

                temp = array[i].stu ;
                if(i+1<N && temp == array[i+1].stu){
                    continue;
                }

                int m = N-prioQue.size() ;
                //out.println("m = " + m + " temp: " + temp);
                if(m < temp){

                    for(int j=0 ; j<temp-m ; j++){
                        total += prioQue.poll().pay ;
                        //out.println(total);
                    }
                }

            }
            out.println(total);
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
