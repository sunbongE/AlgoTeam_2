package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 태꽁이
 * 
 *         <pre>
 *입력이 100만이라 그냥 순회로는 시간 초과가 예상되어 슬라이드윈도우라는 스킬을 사용하기로 했다.
 *처음 0~m까지의 합을 어느 변수(sum)에 저장하고
 *다음 부터는 for문 1번으로 슬라이딩 윈도우 작업을 수행하는데 
 *for(int i=0;i<n-m;i++) 개수를 계산하고
 *범위를 정하는데 범위는 arr[i] 이거는 sum에서 빼주고 arr[i+m]은 sum에 더해주는 작업을 한다면
 *이게 바로 슬라이드 윈도우 아닌가 라는 생각으로 일단 도전
 *         </pre>
 */
public class BJ_12847_꿀아르바이트_박태호 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		st = new StringTokenizer(bf.readLine());
		int cnt = 1;
		long sum = 0; // 수가 커서 long 해야했다. ㅠ
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (cnt++ <= m) {// 입력받으면서 m번만 더해주기.
				sum += arr[i];
			}
		}
		long benefit = sum;// 참조형이 아니라 값이 변하는 일은 없을것같음,.
		// 슬라이딩 윈도우,
		for (int i = 0; i < n - m; i++) {
			sum -= arr[i];
			sum += arr[i + m ];
			if (sum > benefit) {
				benefit = sum;
			}
		}
		System.out.println(benefit);
	}
}
