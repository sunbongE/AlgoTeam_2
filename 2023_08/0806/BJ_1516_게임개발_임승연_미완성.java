import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 자바 자료구조 어렵다
 * 소요 시간 구하는 부분이 잘못된듯
 */

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] temp;

        Map<Integer,List<Integer>> nodes= new HashMap<Integer, List<Integer>>();
        List<Integer> values;

        int n=Integer.parseInt(br.readLine()); // 건물의 종류 수
        int[] time=new int[n+1]; // 소요 시간 저장
        int[] in_degrees=new int[n+1]; // 진입차수 저장

        for(int i=1;i<=n;i++){
            nodes.put(i,new ArrayList<>());
        }

        for(int i=1;i<=n;i++){

            temp=br.readLine().split(" ");
            time[i]=Integer.parseInt(temp[0]); // 소요되는 시간 저장

            for(int j=1;j<temp.length-1;j++){ // 맨 뒤는 -1 이니까
                values=nodes.get(Integer.parseInt(temp[j]));
                values.add(i);
                nodes.replace(Integer.parseInt(temp[j]),values);
                in_degrees[i]+=1; // 진입차수 +1
            }

        } // 입력 정보 저장 완료

        // ===================================================== //
        Deque<Integer> queue=new ArrayDeque<>();
        for(int i=1;i<=n;i++){
            if(in_degrees[i]==0){
                queue.addLast(i);
            } // 진입차수가 0인 노드들 모두 큐에 저장
        }

        int pop;
        List<Integer> pops;

        while(true){
            if(queue.size()==0) break;

            pop=queue.removeFirst(); // 진입차수가 0인 지금 처리해야하는 노드
            pops=nodes.get(pop); // 연결노드들 get

            for(int i=0;i<pops.size();i++){
                in_degrees[pops.get(i)]-=1;
                if(in_degrees[pops.get(i)]==0){
                    queue.addLast(pops.get(i));
                    time[pops.get(i)]+=time[pop];
                }
            }
        }

        for(int i=1;i<=n;i++){
            System.out.println(time[i]);
        }
    }
}
