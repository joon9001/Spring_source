package pack;

public class MyProcess { //setter injection 사용
	private int nai;
	private String name;
	private ShowData showData; 
	// Showdata 클래스 타입의 showdata 변수 선언, 클래스의 포함관계 (유지보수 편의성을 위해 상속보다는 포함관계를 사용)
	
	public MyProcess() {
		// TODO Auto-generated constructor stub
	}
	
	public void setNai(int nai) {
		this.nai = nai;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setShowData(ShowData showData) {
		this.showData = showData;
	}
	
	public void displayData() {
		System.out.println("이름은 " + name +
				", 나이는 " + nai + 
				", 별명은 " + showData.processNickName() +
				", 취미는 " + showData.processHobby()
				);
	}
}
