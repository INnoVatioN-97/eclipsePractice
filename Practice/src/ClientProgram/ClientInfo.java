package ClientProgram;

/**
 * @author Koyoungil
 *	수정된 것: Email -> email로 바꿈(그냥 아무이유없이 바꿔봄)
 * 			toString 수정해서 ClientManager에서 불러오기할때 편하려고 추가함.
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
