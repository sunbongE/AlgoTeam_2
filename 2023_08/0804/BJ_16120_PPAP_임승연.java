import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str=br.readLine();
        Deque<Character> stack=new ArrayDeque<>();
        char a,b;
        int i=0;
        char curr;

        while(true){
            if(i>=str.length()){
                break;
            }
            curr=str.charAt(i);
            if(curr=='A'){ // A 라면
                // 바로 뒤에 오는 문자가 P 인지 먼저 확인
                // 뒤에 오는 문자가 P 라면 ? -> 2개 꺼내보기
                if(i+1<str.length() && str.charAt(i+1)=='P'){
                    if(stack.size()>=2){ // 꺼낼 문자가 2개 이상 존재할때
                        a=stack.removeLast();
                        b=stack.removeLast();
                        if(a=='P' && b=='P'){
                            curr='P'; // P 다시 추가
                            i+=1;
                        } // ppap 문자열
                        else{
                            stack.addLast(a);
                            stack.addLast(b);
                        } // ppap 아님 -> 뺐던 문자들 다시 넣기
                    }
                } // ppap 인지 확인
            }
            stack.addLast(curr);
            i+=1;
        } // while 

        if(stack.size()==1 && stack.removeLast()=='P'){
            System.out.println("PPAP");
        }
        else{
            System.out.println("NP");
        }
    }
}
