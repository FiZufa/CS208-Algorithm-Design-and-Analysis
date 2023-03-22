import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class StableMatching02 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        HashMap<String, Integer> boyHM = new HashMap<>();
        HashMap<String, Integer> girlHM = new HashMap<>();
        Queue<Integer> freemanQue = new LinkedList<>();

        int N = in.nextInt();
        String[] name_girl = new String[N];
        String[] name_boy = new String[N];

        for (int i = 0; i < N; i++) {
            name_boy[i] = in.next();
            boyHM.put(name_boy[i], i);
            freemanQue.add(i);
        }
        for (int i = 0; i < N; i++) {
            name_girl[i] = in.next();
            girlHM.put(name_girl[i], i);
        }
        int[][] boyPre = new int[N][N];
        int[][] girlPre = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                String girlName = in.next();
                boyPre[i][j] = girlHM.get(girlName);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                String boyName = in.next();
                girlPre[i][j] = boyHM.get(boyName);
            }
        }
        //INVERSE
        int[][] girlInvPre = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cur = girlPre[i][j];
                girlInvPre[i][cur] = j;
            }
        }
        int[] girlState = new int[N];
        int[] boyState = new int[N];
        int[] boyPreList = new int[N];

//        Queue<Integer> boyQue = new Queue<>();
        for (int i = 0; i < N; i++) {
            girlState[i] = -1;
            boyState[i] = -1;
        }

        while (!freemanQue.isEmpty()) {
            int boy = freemanQue.poll(); // pop it out!
            // you need to start at already state, not from the beginning.
            // So you also need a list.
            int i = boyPreList[boy];
            for (; i < N; i++) {
                int girl = boyPre[boy][i];
                if (girlState[girl] == -1) {
                    girlState[girl] = boy;
                    boyState[boy] = girl;
                    boyPreList[boy] = i + 1;
//                    freemanQue.dequeue();
                    break;
                } else {
                    // not >, because that the smaller the number is, the higher priority.
                    if (girlInvPre[girl][boy] < girlInvPre[girl][girlState[girl]]) {
                        boyState[girlState[girl]] = 0;
                        freemanQue.add(girlState[girl]);
                        boyPreList[boy] = i + 1;
//                        freemanQue.enqueue(girlState[girl]);
                        girlState[girl] = boy;
                        boyState[boy] = girl;
//                        freemanQue.dequeue();
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            String boyResult = name_boy[i];
            String girlResult = name_girl[boyState[i]];
            System.out.println(boyResult + " " + girlResult);
        }


    }
