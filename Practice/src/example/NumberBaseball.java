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
					if(i>0 && result[i] == result[j] || result[i] == 0) { i--; System.out.println("�ߺ��߻�, i����");}
			}
			for(int i=0; i<4; i++)
				System.out.format("result[%d] : %d\t", i, result[i]);
			
			while(true) {
				
				System.out.println();
				
				System.out.print("���ڸ� �Է� �϶�. ex) 1462 >>");
				int input = sc.nextInt();
				if(input<1000 || input>9999) {
					System.out.println("���ڴ� 4�ڸ��� �Է��϶�.");
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
						System.out.println("��ȸ ����. ���� ����.");
						break;
					}else
						System.out.format("%d �� ����.", 10-myTurn);
					strike = ball = 0;
				}
				else 
				{
					System.out.format("%d��Ʈ����ũ %d ��", strike, ball);
					strike=ball=0;
				}
				
				System.out.println();
				
			}
			
		}
		

}
