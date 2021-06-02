package ClientProgram;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author koyoungil
 * 
 *         �߰��� �� : loadSavedList() - ������ ����� ����Ʈ ������ ������ �� ������ ������ clientList��
 *         �ֽ�ȭ�Ѵ�. ������ �� : insertClientInfo - ����Ʈ�� �����ϴ°ű����� �״���̰� �߰��� �� ����Ʈ�� ������
 *         ���� ���� ��ο� �����ϵ��� ����. (debug������ ����� ��α��� �����. searchClientNameInfo()
 *         (144����) - �̸� �˻� ��ҹ��� ���о��� �׳� �˻��ǵ��� ����
 */
public class ClientManager {

	static ArrayList<ClientInfo> clientList = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);

	public static void insertClientInfo() {
		ClientInfo cl = new ClientInfo();

		System.out.print("�̸� : ");
		cl.setName(sc.nextLine().trim());

		while (true) {
			System.out.print("����(M/F) :");
			String gender = sc.nextLine().trim();
			gender = gender.toUpperCase();
			if (gender.equals("M") || gender.equals("F")) {
				cl.setGender(gender);
				break;
			} else {
				System.out.println("�߸��� �����Դϴ�. �ٽ� �Է����ּ���.");

			}
		}
		System.out.print("�̸��� :");
		cl.setEmail(sc.nextLine().trim());

		System.out.print("����⵵ :");
		cl.setBirth(sc.nextInt());

		sc.nextLine();

		clientList.add(cl);

		OutputStream out = null;
		File dirent = new File(ClientManager.class.getResource("").getPath() + "textFiles");
		try {
			// ������ ���丮�� �������� ������ ����
			if (!dirent.exists()) {
				dirent.mkdir();
			}

			File outFile = new File(dirent.getAbsolutePath(), "clientList.txt");

			// ������ �����Ѵٸ� �����Ѵ�.
			if (outFile.exists()) {
				outFile.delete();
			}

			// ���Ͽ� String���� �Է��� BufferedOutputStream ����
			out = new BufferedOutputStream(new FileOutputStream(outFile));
			for (int idx = 0; idx < clientList.size(); idx++) {
				String writeStr = clientList.get(idx).toString();

				// ������ string���� Byte �迭�� ����
				byte[] b = writeStr.getBytes();

				// ���Ͽ� �ش� ������ ����.
				out.write(b);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception e) {
			}
		}
		System.out.println("�Է��Ͻ� ������" + dirent.getAbsolutePath() + "�� ����Ǿ����ϴ�.");
	}

	public static void printClientInfo(int index) {
		ClientInfo cl = clientList.get(index);

		System.out.println("�̸� : " + cl.getName());
		System.out.println("���� : " + cl.getGender());
		System.out.println("�̸��� : " + cl.getEmail());
		System.out.println("����⵵ : " + cl.getBirth());

	}

	public static void updateClientInfo(int index) {
		ClientInfo cl = clientList.get(index);

		System.out.printf("�̸�(%s) -> ", cl.getName());
		cl.setName(sc.nextLine().trim());

		System.out.printf("����(%s) -> ", cl.getGender());
		String gender = sc.nextLine().trim();
		gender = gender.toUpperCase();
		while (true) {
			if (gender.equals("M") || gender.equals("F")) {
				cl.setGender(gender);
				break;
			} else {
				System.out.println("�߸��� �����Դϴ�. �ٽ� �Է����ּ���.");

			}
		}

		System.out.printf("�̸���(%s) -> ", cl.getEmail());
		cl.setEmail(sc.nextLine().trim());

		System.out.printf("����⵵(%s) -> ", cl.getBirth());
		cl.setBirth(sc.nextInt());

		sc.nextLine();

		System.out.println("�����Ǿ����ϴ�.");

	}

	public static void deleteClientInfo(int index) {
		clientList.remove(index);
		System.out.println("�����Ǿ����ϴ�.");

	}

	public static int searchClientNameInfo() {
		System.out.print("������ �̸��� �Է����ּ���.");
		String name = sc.nextLine().trim();
		for (int i = 0; i < clientList.size(); i++) {
			if (clientList.get(i).getName().equalsIgnoreCase(name)) { // ��ҹ��� ����(jacob, JACOB, JaCoB�� �� ����)
				return i;
			}
		}
		return -1;
	}

	// ���Ϸ� ����� txt ������ �ҷ��� clientList�� �����ϱ�
	public static void loadSavedList() {
		File dirent = new File(ClientManager.class.getResource("").getPath() + "textFiles");
		// ������ ���丮�� �������� ������ ����
		if (!dirent.exists()) {
			dirent.mkdir();
		}

		ArrayList<ClientInfo> tmpList = new ArrayList<>();
		File file = new File(dirent.getAbsoluteFile() + "/clientList.txt");
		if (file.exists()) {
			try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
				String line = null;
				while ((line = br.readLine()) != null) {
					String[] info = line.split(",");
					ClientInfo clientInfo = new ClientInfo();
					clientInfo.setName(info[0]);
					clientInfo.setGender(info[1]);
					clientInfo.setEmail(info[2]);
					clientInfo.setBirth(Integer.parseInt(info[3])); // ������ �����̹Ƿ� Wrapper Class�� Ȱ���� ���ڿ��� ������ ���α�
					tmpList.add(clientInfo);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			clientList = tmpList;
		}
	}
}
