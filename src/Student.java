
public class Student {
	private int sid;
	private String username;
	private String address;

	public Student(int sid, String username, String address) {
		super();
		this.sid = sid;
		this.username = username;
		this.address = address;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getSid() {
		return this.sid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [학번=" + sid + ", 이름=" + username + ", 주소=" + address + "]";
	}

}