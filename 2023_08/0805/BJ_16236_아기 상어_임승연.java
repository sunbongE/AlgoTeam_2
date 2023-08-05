import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] graph;
    static List<int[]> arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] temp;
        arr=new ArrayList<>();
        n=Integer.parseInt(br.readLine());
        graph=new int[n][n];
        int[] nextPos;
        int x = 0,y = 0; // 상어 위치 저장
        int size=2,fish=0,time=0;

        for(int i=0;i<n;i++){
            temp=br.readLine().split(" ");
            for(int j=0;j<n;j++){
                graph[i][j]=Integer.parseInt(temp[j]);
                if(graph[i][j]==9){
                    x=i;
                    y=j;
                }// 상어 위치 저장
            }
        } // 그래프 생성

        while(true){

            bfs(x,y,size); // 먹을 수 있는 상어 위치 담아옴 (ArrayList 에 저장)

            if(arr.size()==0){ // 먹을 수 있는 고기가 없다면
                break;
            }
            arr.sort(Comparator.comparingInt(o->o[1])); // y 좌표 기준 정렬 (좌우)
            arr.sort(Comparator.comparingInt(o->o[0])); // x 좌표 기준 정렬 (상하)
            arr.sort(Comparator.comparingInt(o->o[2])); // 거리 기준 정렬

            nextPos=arr.get(0); // 맨 앞 요소 빼기
            x=nextPos[0];
            y=nextPos[1];
            time+=nextPos[2]; // 시간 추가

            fish+=1;
            if(fish==size){
                size+=1;
                fish=0;
            }
            arr.clear(); // 먹을 수 있는 고기 초기화

        } // while
        System.out.println(time);

    }

    public static void bfs(int x,int y,int size){

        int[][] visited=new int[graph.length][graph.length]; // 이미 담은 좌표를 다시 담지 않기 위해
        for(int i=0;i<graph.length;i++){
            Arrays.fill(visited[i],-1);
        }
        Deque<int[]> fish=new ArrayDeque<>();
        fish.addFirst(new int[]{x,y});
        visited[x][y]=0;
        graph[x][y]=0;

        int[] dx={0,-1,0,1}; // 왼 위 오 아
        int[] dy={-1,0,1,0};

        int currX,currY;
        int xx,yy;
        int[] temp;

        while(fish.size()!=0){
            temp=fish.removeFirst();
            currX=temp[0];
            currY=temp[1]; // 탐색할 위치

            for(int i=0;i<4;i++){
                xx=dx[i]+currX;
                yy=dy[i]+currY;

                if(xx<0 || yy<0 || xx>=graph.length || yy>=graph.length){ // out
                    continue;
                }

                if(graph[xx][yy]>size || visited[xx][yy]>0) continue; // 나보다 커서 못먹음 & 이미 확인했던 위치

                fish.addLast(new int[]{xx,yy}); // 지나갈 수 있고 아직 방문 전이므로 탐색 목록에 추가
                visited[xx][yy]=visited[currX][currY]+1;

                if(graph[xx][yy]<size && graph[xx][yy]>0){
                    arr.add(new int[]{xx,yy,visited[xx][yy]}); // 먹을 수 있는 고기 리스트에도 추가
                }
            }
        }
    }
}
