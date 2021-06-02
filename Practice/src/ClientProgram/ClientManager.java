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
 *         추가된 것 : loadSavedList() - 기존에 저장된 리스트 파일이 있으면 그 내용을 가져와 clientList를
 *         최신화한다. 수정된 것 : insertClientInfo - 리스트에 저장하는거까지는 그대로이고 추가로 그 리스트를 가지고
 *         실제 파일 경로에 저장하도록 수정. (debug용으로 저장된 경로까지 띄워둠. searchClientNameInfo()
 *         (144번줄) - 이름 검색 대소문자 구분없이 그냥 검색되도록 수정
 */
public class ClientManager {

	static ArrayList<ClientInfo> clientList = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);

	public static void insertClientInfo() {
		ClientInfo cl = new ClientInfo();

		System.out.print("이름 : ");
		cl.setName(sc.nextLine().trim());

		while (true) {
			System.out.print("성별(M/F) :");
			String gender = sc.nextLine().trim();
			gender = gender.toUpperCase();
			if (gender.equals("M") || gender.equals("F")) {
				cl.setGender(gender);
				break;
			} else {
				System.out.println("잘못된 성별입니다. 다시 입력해주세요.");

			}
		}
		System.out.print("이메일 :");
		cl.setEmail(sc.nextLine().trim());

		System.out.print("출생년도 :");
		cl.setBirth(sc.nextInt());

		sc.nextLine();

		clientList.add(cl);

		OutputStream out = null;
		File dirent = new File(ClientManager.class.getResource("").getPath() + "textFiles");
		try {
			// 저장할 디렉토리가 존재하지 않으면 생성
			if (!dirent.exists()) {
				dirent.mkdir();
			}

			File outFile = new File(dirent.getAbsolutePath(), "clientList.txt");

			// 파일이 존재한다면 삭제한다.
			if (outFile.exists()) {
				outFile.delete();
			}

			// 파일에 String값을 입력할 BufferedOutputStream 생성
			out = new BufferedOutputStream(new FileOutputStream(outFile));
			for (int idx = 0; idx < clientList.size(); idx++) {
				String writeStr = clientList.get(idx).toString();

				// 저장한 string값을 Byte 배열로 변경
				byte[] b = writeStr.getBytes();

				// 파일에 해당 내용을 쓴다.
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
		System.out.println("입력하신 정보가" + dirent.getAbsolutePath() + "에 저장되었습니다.");
	}

	public static void printClientInfo(int index) {
		ClientInfo cl = clientList.get(index);

		System.out.println("이름 : " + cl.getName());
		System.out.println("성별 : " + cl.getGender());
		System.out.println("이메일 : " + cl.getEmail());
		System.out.println("출생년도 : " + cl.getBirth());

	}

	public static void updateClientInfo(int index) {
		ClientInfo cl = clientList.get(index);

		System.out.printf("이름(%s) -> ", cl.getName());
		cl.setName(sc.nextLine().trim());

		System.out.printf("성별(%s) -> ", cl.getGender());
		String gender = sc.nextLine().trim();
		gender = gender.toUpperCase();
		while (true) {
			if (gender.equals("M") || gender.equals("F")) {
				cl.setGender(gender);
				break;
			} else {
				System.out.println("잘못된 성별입니다. 다시 입력해주세요.");

			}
		}

		System.out.printf("이메일(%s) -> ", cl.getEmail());
		cl.setEmail(sc.nextLine().trim());

		System.out.printf("출생년도(%s) -> ", cl.getBirth());
		cl.setBirth(sc.nextInt());

		sc.nextLine();

		System.out.println("수정되었습니다.");

	}

	public static void deleteClientInfo(int index) {
		clientList.remove(index);
		System.out.println("삭제되었습니다.");

	}

	public static int searchClientNameInfo() {
		System.out.print("고객님의 이름을 입력해주세요.");
		String name = sc.nextLine().trim();
		for (int i = 0; i < clientList.size(); i++) {
			if (clientList.get(i).getName().equalsIgnoreCase(name)) { // 대소문자 무시(jacob, JACOB, JaCoB도 다 가능)
				return i;
			}
		}
		return -1;
	}

	// 파일로 저장된 txt 파일을 불러와 clientList에 저장하기
	public static void loadSavedList() {
		File dirent = new File(ClientManager.class.getResource("").getPath() + "textFiles");
		// 저장할 디렉토리가 존재하지 않으면 생성
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
					clientInfo.setBirth(Integer.parseInt(info[3])); // 생일은 숫자이므로 Wrapper Class를 활용해 문자열을 정수로 감싸기
					tmpList.add(clientInfo);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			clientList = tmpList;
		}
	}
}
