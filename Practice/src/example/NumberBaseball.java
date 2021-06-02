package example;

import java.util.*;
import java.util.stream.Stream;
public class NumberBaseball {
	
		public static void main(String[] args) {
			int inputArray[]= new int[4];
			int result[] = new int[4];
			int strike = 0, ball = 0, myTurn = 0;;
			Scanner sc = new Scanner(System.in);
			
			for(int i=0; i<4; i++) {
				result[i] = (int)(Math.random() * 9)  +1;
				for(int j=0; j<i; j++)
					if(i>0 && result[i] == result[j] || result[i] == 0) { i--; System.out.println("중복발생, i감소");}
			}
			for(int i=0; i<4; i++)
				System.out.format("result[%d] : %d\t", i, result[i]);
			
			while(true) {
				
				System.out.println();
				
				System.out.print("숫자를 입력 하라. ex) 1462 >>");
				int input = sc.nextInt();
				if(input<1000 || input>9999) {
					System.out.println("숫자는 4자리만 입력하라.");
					input = sc.nextInt();
				}
				inputArray = Stream.of(String.valueOf(input).split("")).mapToInt(Integer::parseInt).toArray();
				boolean stFlag=false;
				for(int i=0; i<4; i++) {
					stFlag = false;
					if(inputArray[i] == result[i]) {strike++; stFlag = true;}
					for(int j=0; j<4; j++) {
						if(!stFlag && inputArray[i] == result[j]) {
							ball++;
							myTurn++;
						}
						
					}
				}
				if(ball == 0 && strike == 0) {
					myTurn++;
					if(myTurn == 10) {
						System.out.println("기회 소진. 게임 종료.");
						break;
					}else
						System.out.format("%d 턴 남음.", 10-myTurn);
					strike = ball = 0;
				}
				else 
				{
					System.out.format("%d스트라이크 %d 볼", strike, ball);
					strike=ball=0;
				}
				
				System.out.println();
				
			}
			
		}
		

}
