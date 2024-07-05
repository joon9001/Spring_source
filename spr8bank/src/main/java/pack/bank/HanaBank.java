package pack.bank;

import org.springframework.stereotype.Component;

@Component
public class HanaBank implements Bank{
	private int money = 1000;
	
	@Override
	public void inputMoney(int money) {
		this.money = this.money + money;
		
	}
	
	
	@Override
	public void outputMoney(int money) {
		int imsi = money;
		this.money -= imsi;
		
	}
	@Override
	public int getMoney() {
		return money;
		
	}
}
