package pack.bank;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("sin")
@Scope("prototype")  //"기본은 singleton이지만 객체 공유를 하지 않고 매번 다른 객체 인스턴스를 만들고 싶을 경우 prototype을 쓴다.
public class SinhanBank implements Bank{
	private int money = 5000;
	
	@Override
	public void inputMoney(int money) {
		this.money += money;
		
	}
	@Override
	public void outputMoney(int money) {
		this.money -= money;
		
	}
	@Override
	public int getMoney() {
		return money;
		
	}
}
