package ClientProgram;

/**
 * @author Koyoungil
 *	������ ��: Email -> email�� �ٲ�(�׳� �ƹ��������� �ٲ㺽)
 * 			toString �����ؼ� ClientManager���� �ҷ������Ҷ� ���Ϸ��� �߰���.
 */
public class ClientInfo{

	private String name;
	private String gender;
	private String email;
	private int birth;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getBirth() {
		return birth;
	}
	public void setBirth(int birth) {
		this.birth = birth;
	}
	
	@Override
	public String toString() {
		return name+","+gender+","+email+","+birth+"\n";
	}
}
