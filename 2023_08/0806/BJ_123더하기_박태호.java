package BJ;

import java.util.Scanner;

public class BJ_123더하기_박태호 { //백트로 풀이.
	static int cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc = 0; tc<T;tc++	) {
			cnt = 0;
			sol(sc.nextInt(),0);
			System.out.println(cnt);
			
		}
	}
	public static void sol(int n, int tmp){
		if(tmp>n) {
			return;
		}
		if(tmp==n) {
			cnt++;
			return;
		}
		for(int num=1;num<4;num++) {
			sol(n,tmp+num);
		}
		
	}

}
