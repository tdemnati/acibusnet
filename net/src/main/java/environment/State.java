package environment;

import java.util.Scanner;

public class State {
/*State is a class allowing to receive a user statement.*/

private String istate;
private int a;

public State(int a) {
	this.istate = istate;
	
	System.out.println ("Listening to you : ");
	Scanner readkeys = new Scanner(System.in);
	setInput(readkeys.nextLine());
	readkeys.close();
}
	
	public String getInput() {
		return istate;
	}
	
	public void setInput(String istate) {
		this.istate = istate;
	}

}