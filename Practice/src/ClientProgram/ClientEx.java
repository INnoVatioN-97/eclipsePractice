package ClientProgram;

import java.util.Scanner;

/**
 * 
 * @author Koyoungil
 *	수정: switch-case에서  q, Q말고도 한글로 ㅂ라고 쳐도 되도록 바꿈 (대소문자, 영어한글 다 되도록)
 */
public class ClientEx extends ClientManager {

	static Scanner scan = new Scanner(System.in);
	static int index = -1;

	public static void main(String[] args) {
		loadSavedList();
		
		System.out.println("고객관리 프로그램");
		System.out.println("사용하실 기능에 따라 올바르게 입력해주세요.");

		while (true) {
			System.out.println("저장(I) | 조회(P/N) | 수정(U) | 삭제(D) | 종료(Q)");
			System.out.println("지정된 알파벳으로 입력해주세요(대소문자 상관X) : ");
			String menu = scan.nextLine().trim();
			menu = menu.toUpperCase();

			switch (menu) {
			case "ㅑ":
			case "I":
				System.out.println("새로운 고객정보를 입력합니다.");
				insertClientInfo();
				break;
				
			case "ㅔ":
			case "ㅜ":
			case "P":
			case "N":
				index = searchClientNameInfo();
				if (index >= 0) {
					printClientInfo(index);

				} else {
					System.out.println("등록된 정보가 없습니다. 다시 확인해주세요.");
				}
				break;

			case "ㅕ":
			case "U":
				index = searchClientNameInfo();
				if (index >= 0) {
					updateClientInfo(index);
				} else {
					System.out.println("등록된 정보가 없습니다. 다시 확인해주세요.");
				}
				break;
				
			case "ㅇ":
			case "D":
				index = searchClientNameInfo();
				if (index >= 0) {
					deleteClientInfo(index);
				} else {
					System.out.println("등록된 정보가 없습니다. 다시 확인해주세요.");
				}
				break;
				
			case "ㅂ":
			case "Q":
				System.out.println("고객관리 프로그램을 종료합니다.");
				scan.close();
				System.exit(0);
				
			default:
				System.out.println("잘못된 입력입니다.");
				break;

			}
			System.out.println("-------------------------------------");
		}

	}

}
