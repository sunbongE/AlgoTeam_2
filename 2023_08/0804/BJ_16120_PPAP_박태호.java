package BJ;

import java.util.Scanner;
import java.util.Stack;

public class BJ_16120_PPAP_박태호 {
	static int aCnt=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] inp = sc.next().toCharArray();
		int len = inp.length;
		Stack<Character> stack = new Stack<>();
		if (inp.length == 1 && inp[0] == 'P') { // 입력으로 P하나오면 바로 답처리
			System.out.println("PPAP");
		} else if(len>=4){//입력이 4개 이상.
			for (Character val : inp) { // 입력으로 받은 글자 하나씩 스텍에 넣음.
				stack.add(val);
				if (stack.size() >= 4) { // 4개 이상들어오면
					if (stack.get(stack.size() - 4) == 'P' && stack.get(stack.size() - 3) == 'P'
							&& stack.get(stack.size() - 2) == 'A' && stack.get(stack.size() - 1) == 'P') {
						aCnt++;
						for (int i = 0; i < 3; i++) { // PPAP를 P로 변경.
							stack.pop();
						}
					}
				}
			}
			if (aCnt>0&&isPPAP(stack)) {
				System.out.println("PPAP");
			} else {
				System.out.println("NP");
			}
		}else {
			System.out.println("NP");
		}

	}

	private static boolean isPPAP(Stack<Character> stack) {
		int len=stack.size();
		for(int i=0;i<len;i++) {
			if(stack.pop()!='P') {
				return false;
			}
		}
		return true;
	}
}
