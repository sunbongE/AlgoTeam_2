import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author 임승연
 *
 * left, right, data 정보를 갖는 Node 클래스 생성
 * data 값을 key로 하고 노드를 value 로 갖는 해시맵 생성
 * 전위, 중위, 후위 순회 메서드는 재귀함수로 구현
 *
 */


public class Main {

    static Map<String,Node> tree; // tree 정보 저장

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        String[] temp;
        String left,right; // 왼쪽, 오른쪽 자식 정보

        tree=new HashMap<>(); // 해시맵 생성

        for(int i=0;i<n;i++){ // 트리 정보를 입력받고 그에 맞는 노드를 생성하여 해시맵에 저장
            temp=br.readLine().split(" ");
            left=temp[1];
            right=temp[2];

            if(left.equals(".") || right.equals(".")){ // 두 자식 노드 중 어느 하나라도 null 인 경우
                if(left.equals(".") && right.equals(".")) tree.put(temp[0],new Node(temp[0],null,null)); // 둘 다 null 인 경우
                else if(!left.equals(".")) tree.put(temp[0],new Node(temp[0],temp[1],null)); // 오른쪽만 null
                else tree.put(temp[0],new Node(temp[0],null,temp[2])); // 왼쪽만 null
            }
            else{
                tree.put(temp[0],new Node(temp[0],temp[1],temp[2])); // 둘 다 not null
            }
        } // 트리 생성


        preOrder(tree.get("A")); // root 에서 시작하는 전위순회
        System.out.println();
        inOrder(tree.get("A")); // root 에서 시작하는 중위순회
        System.out.println();
        postOrder(tree.get("A")); // root 에서 시작하는 후위순회


    }


    static void preOrder(Node curr){ // 전위순회 , 현재 탐색하는 Node curr 을 매개변수로

        if(curr==null) return; // leaf 노드 만나면 리턴

        System.out.print(curr.data); // 나의 데이터 먼저 출력
        preOrder(tree.get(curr.left)); // 왼쪽으로
        preOrder(tree.get(curr.right)); // 오른쪽으로

    }

    static void inOrder(Node curr){ // 중위순회

        if(curr==null) return; // leaf 노드 만나면 리턴

        inOrder(tree.get(curr.left)); // 왼쪽으로 먼저
        System.out.print(curr.data); // 내 데이터 출력
        inOrder(tree.get(curr.right)); // 오른쪽으로

    }

    static void postOrder(Node curr){ // 후위순회

        if(curr==null) return; // leaf 노드 만나면 리턴

        postOrder(tree.get(curr.left)); // 왼쪽으로 먼저
        postOrder(tree.get(curr.right)); // 오른쪽
        System.out.print(curr.data); // 내 데이터 출력

    }

    static class Node{ // data, left, right 정보를 갖는 Node class
        String data;
        String left;
        String right;

        Node(String data,String left,String right){// 생성자
            this.data=data;
            this.left=left;
            this.right=right;
        }
    }
}
