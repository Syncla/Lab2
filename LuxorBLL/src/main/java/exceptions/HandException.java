package exceptions;
import base.Hand;

public class HandException extends Exception {
	private Hand hand;
	public HandException(Hand hand){
		super();
		this.hand=hand;
	}
}
