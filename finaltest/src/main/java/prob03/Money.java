package prob03;

import java.util.Objects;

public class Money {
	private int amount;
	private int result;
	// private Money money;
	
	/* 코드 작성 */
	public Money(int amount) {
		this.amount = amount;
		// this.money = money;
	}
	
	public Money add(Money money) {
		result = this.amount + money.amount;
		Money m = new Money(result);
		return m;
		
	}
	
	public Money minus(Money money) {
		result = this.amount - money.amount;
		Money m = new Money(result);
		return m;
	}
	
	public Money multiply(Money money) {
		result = this.amount * money.amount;
		Money m = new Money(result);
		return m;
	}
	
	public Money devide(Money money) {
		result = this.amount / money.amount;
		Money m = new Money(result);
		return m;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, result);
	}

	@Override
	public boolean equals(Object obj) {
		/// System.out.println(this.amount);
		// System.out.println(((Money)obj).amount);
		// System.out.println(obj.getClass().getSimpleName());
		if(obj instanceof Money && this.amount == ((Money)obj).amount) {
			return true;	
		}
		return false;
	}
	
	
}
