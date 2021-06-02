package ClientProgram;

import java.util.Scanner;

/**
 * 
 * @author Koyoungil
 *	����: switch-case����  q, Q���� �ѱ۷� ����� �ĵ� �ǵ��� �ٲ� (��ҹ���, �����ѱ� �� �ǵ���)
 */
public class ClientEx extends ClientManager {

	static Scanner scan = new Scanner(System.in);
	static int index = -1;

	public static void main(String[] args) {
		loadSavedList();
		
		System.out.println("������ ���α׷�");
		System.out.println("����Ͻ� ��ɿ� ���� �ùٸ��� �Է����ּ���.");

		while (true) {
			System.out.println("����(I) | ��ȸ(P/N) | ����(U) | ����(D) | ����(Q)");
			System.out.println("������ ���ĺ����� �Է����ּ���(��ҹ��� ���X) : ");
			String menu = scan.nextLine().trim();
			menu = menu.toUpperCase();

			switch (menu) {
			case "��":
			case "I":
				System.out.println("���ο� �������� �Է��մϴ�.");
				insertClientInfo();
				break;
				
			case "��":
			case "��":
			case "P":
			case "N":
				index = searchClientNameInfo();
				if (index >= 0) {
					printClientInfo(index);

				} else {
					System.out.println("��ϵ� ������ �����ϴ�. �ٽ� Ȯ�����ּ���.");
				}
				break;

			case "��":
			case "U":
				index = searchClientNameInfo();
				if (index >= 0) {
					updateClientInfo(index);
				} else {
					System.out.println("��ϵ� ������ �����ϴ�. �ٽ� Ȯ�����ּ���.");
				}
				break;
				
			case "��":
			case "D":
				index = searchClientNameInfo();
				if (index >= 0) {
					deleteClientInfo(index);
				} else {
					System.out.println("��ϵ� ������ �����ϴ�. �ٽ� Ȯ�����ּ���.");
				}
				break;
				
			case "��":
			case "Q":
				System.out.println("������ ���α׷��� �����մϴ�.");
				scan.close();
				System.exit(0);
				
			default:
				System.out.println("�߸��� �Է��Դϴ�.");
				break;

			}
			System.out.println("-------------------------------------");
		}

	}

}
