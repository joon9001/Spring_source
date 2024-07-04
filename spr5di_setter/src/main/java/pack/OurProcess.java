package pack;

public class OurProcess {
	private String name;
	private int su;
	private GuguDan guguDan;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setSu(int su) {
		this.su = su;
	}
	public void setGuguDan(GuguDan guguDan) {
		this.guguDan = guguDan;
	}
	
	@Override
	public String toString() { //최상위 클래스 object의 함수 toString
		int[] results = guguDan.numberCalc(su);
		String str = "";
		
		for (int i = 0; i < results.length; i++) {
			str += su + "*" + (i + 1) + "=" + results[i] + "\n";
		}
		return "작성자 : " + name + "\n" + su + "단 결과: \n" + str;
	}
}
