package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node { // 노드 정의 해버리기.
	char data;
	Node left;
	Node right;
	
	Node(char data){
		this.data = data;
	}
	
}

class tree{
	Node root; // 루트 노드 처음엔 null상태.
	
	public void createNode(char data, char leftData, char rightData) {
		if(root==null) {
			root = new Node(data);
			// 좌우 값이 있는 경우에만 노드 생성
			if(leftData !='.') {
				root.left = new Node(leftData);
			}
			if(rightData !='.') {
				root.right = new Node(rightData);
			}
		}else {
			searchNode(root, data, leftData, rightData);
		}
	}

	private void searchNode(Node root, char data, char leftData, char rightData) {
		if(root==null) {// 
			return;
		}else if(root.data==data) {
			if(leftData !='.') {
				root.left = new Node(leftData);
			}
			
			if(rightData !='.') {
				root.right = new Node(rightData);
			}
		}else {
			searchNode(root.left, data, leftData, rightData);
			searchNode(root.right, data, leftData, rightData);
		}
	}
	
	//전위 순회 : 루트 -> 좌 -> 우
	public void preorder(Node root) {
		System.out.print(root.data);
		// 재귀 호출로 왼쪽 먼저 쭉 호출하고 왼쪽 노드가 비어있는 경우 출력.
		if(root.left!=null)preorder(root.left);
		if(root.right!=null)preorder(root.right);
	}
	// 중위 순회 : 좌 -> 루트 -> 우
	public void inorder(Node root) {
		if(root.left!=null)inorder(root.left);
		System.out.print(root.data);
		if(root.right!=null)inorder(root.right);
	}
	// 후위 순회 : 좌 -> 우 -> 루트
	public void postorder(Node root) {
		if(root.left!=null)postorder(root.left);
		if(root.right!=null)postorder(root.right);
		System.out.print(root.data);
	}
	
}
class BJ_1991_트리순회_박태호 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		tree tree = new tree();
		for (int i = 0; i < N; i++) {
			char[] data;
			data = br.readLine().replaceAll(" ", "").toCharArray();
			tree.createNode(data[0], data[1], data[2]);
		}
		
		// 전위 순회
		tree.preorder(tree.root);
		System.out.println();
		// 중위 순회
		tree.inorder(tree.root);
		System.out.println();
		// 후위 순회
		tree.postorder(tree.root);
		System.out.println();
		br.close();
	}

}
