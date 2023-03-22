import java.io.*;
import java.util.*;

import static java.lang.System.out;

public class ProblemB {
    public static int[] level ;
    //public static int count=0 ;
    public static int lv ;

    static class Node{
        boolean isVisited = false ;
        ArrayList<Node> child  = new ArrayList<>() ;
    }

    static void bfs(Node node){
        Queue<Node> queue = new LinkedList<>() ;
        int count = 0 ;
        node.isVisited = true ;
        queue.add(node) ;
        int layer = 0 ;
        while (!queue.isEmpty()){
            count = queue.size() ;
            level[layer] = count ;
            lv=layer ;
            layer++ ;
            for(int i=0 ; i<count ; i++){
                Node head = queue.remove() ;
                head.isVisited = true ;

                for(int j=0 ; j<head.child.size() ; j++){
                    if(!head.child.get(j).isVisited){
                        queue.add(head.child.get(j)) ;
                    }
                }
            }

        }
    }


    public static void main(String[] args){

        //Scanner in = new Scanner(System.in) ;
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt() ; // villages
        int m = in.nextInt() ; //roads
        int p = in.nextInt() ; // village start
        int q = in.nextInt() ; //q th day

        int[] days = new int[q] ; // questions
        level = new int[n] ;
        //level[0] = 1 ; //day 0 stay
        int[] result = new int[n] ;
        Node[] graph = new Node[n] ;

        for(int i=0 ; i<n ; i++){
            graph[i] = new Node();
        }

        for(int i=0 ; i<m ; i++){
            int u = in.nextInt()-1 ;
            int v = in.nextInt()-1 ;
            //bidirectional graph start from index 0
            graph[u].child.add(graph[v]) ;
            graph[v].child.add(graph[u]) ;
        }

        //questions
        for(int i=0; i<q ; i++){
            days[i] = in.nextInt() ;
        }

        //bfs start from village p
        bfs(graph[p-1]); //error

        for(int i=lv+1 ; i<n ; i++){
            level[i] = level[lv];
        }

        // in day 0, there is one option that is stay in the position
        result[0] = 1 ;
        for(int i=1 ; i<n ; i++){
            if(i<=lv){
                result[i] = result[i-1] + level[i];
            } else {
                result[i] = result[lv];
            }


        }

        for(int i=0; i<q ; i++){
            if(days[i]<n){
                out.print(result[days[i]] + " ");
            } else {
                out.print(result[n-1] + " "); //result[n-1]
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
