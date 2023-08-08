package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
* 소요 시간을 갱신해주는 부분을 잘 생각해야함!
*
*/

public class 게임개발 {

    public static void main(String[] args) throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] temp;

        List<Integer> conn=new ArrayList<>(); // 연결 요소 저장
        Map<Integer,List<Integer>> nodes= new HashMap<Integer, List<Integer>>();
        List<Integer> values;

        int n=Integer.parseInt(br.readLine()); // 건물의 종류 수
        int[] time=new int[n+1]; // 소요 시간 저장 (초기값 저장)
        int[] result=new int[n+1]; // 최종 소요 시간 저장 
        int[] in_degrees=new int[n+1]; // 진입차수 저장

        for(int i=1;i<=n;i++){
            nodes.put(i,new ArrayList<>());
        }

        for(int i=1;i<=n;i++){

            temp=br.readLine().split(" ");
            time[i]=Integer.parseInt(temp[0]); // 소요되는 시간 저장

            for(int j=1;j<temp.length-1;j++){ 
                values=nodes.get(Integer.parseInt(temp[j]));
                values.add(i);
                nodes.replace(Integer.parseInt(temp[j]),values);
                in_degrees[i]+=1; // 진입차수 +1
            }

        } // 입력 정보 저장 {"나": 나에서 출발하는 노드 리스트}
        

        // ===================================================== //
        
        Deque<Integer> queue=new ArrayDeque<>();
        for(int i=1;i<=n;i++){
            if(in_degrees[i]==0){
                queue.addLast(i);
                result[i]=time[i];
            } // 진입차수가 0인 노드들 큐에 저장 
        }

        int pop;
        List<Integer> pops;

        while(true){
            if(queue.size()==0) break; // 큐가 빌 때까지 반복 

            pop=queue.removeFirst(); // 진입차수가 0인 지금 처리하는 노드
            pops=nodes.get(pop); // 그 노드를 시작점으로 하는 연결노드들 리스트

            for(int i=0;i<pops.size();i++){
                in_degrees[pops.get(i)]-=1; // 연결 노드들 진입차수 - 1 
                if(in_degrees[pops.get(i)]==0){ // 진입차수가 0이 된 노드들
                    queue.addLast(pops.get(i)); // 큐에 add 
                }
                result[pops.get(i)]=Math.max(result[pop]+time[pops.get(i)],result[pops.get(i)]);
                // ( 현재 내가 가진 소요 시간 , 나보다 앞서 지어져야하는 노드의 소요 시간 + 나를 짓는데 걸리는 시간 ) 의 최댓값으로 지정
            }
        }

        for(int i=1;i<=n;i++){
            System.out.println(result[i]);
        }
    }
}
