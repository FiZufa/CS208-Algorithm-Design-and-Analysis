import java.util.HashMap ;
import java.util.Scanner;

public class StableMatching {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in) ;
        HashMap<String, Integer> boyHM = new HashMap<>() ;
        HashMap<String, Integer> girlHM = new HashMap<>() ;
        int N = in.nextInt() ;

        for(int i=0 ; i<N ; i++){
            String boyName = in.next() ;
            boyHM.put(boyName, i) ;
        }
        for(int i=0 ; i<N ; i++){
            String girlName = in.next() ;
            girlHM.put(girlName, i) ;
        }

        int[][] boyPre = new int[N][N] ;
        int[][] girlPre = new int[N][N] ;

        for(int i=0 ; i<N ; i++){
            for(int j=0 ; j<N ; j++){
                String girlName = in.next() ;
                boyPre[i][j] = girlHM.get(girlName) ;
            }
        }
        for(int i=0 ; i<N ; i++){
            for(int j=0 ; j<N ; j++){
                String boyName = in.next() ;
                girlPre[i][j] = boyHM.get(boyName) ;
            }
        }


        for(int i=0 ; i<N ; i++){
            for(int j=0 ; j<N ; j++){
                System.out.print(boyPre[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        for(int i=0 ; i<N ; i++){
            for(int j=0 ; j<N ; j++){
                System.out.print(girlPre[i][j] + " ");
            }
            System.out.println();
        }



    }
}
