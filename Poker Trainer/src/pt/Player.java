package pt;

public class Player {
	private int stack;
	
	public int getStack() {
		return stack;
	}
	
	public void setStack(int ammount) {
		stack += ammount;
	}
	
	public Player(int initialStack) {
		stack = initialStack;
	}

}
