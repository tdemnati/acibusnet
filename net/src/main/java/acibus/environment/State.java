/*
/*
 * Copyright (c) 2016-2017 "Acibus Technology,"
 * -- [http://www.a-cibus.com]
 *
 * This file is part of AcibusNet.
 * State: aims at gathering a given state. 
 */

package acibus.environment;

import java.util.Scanner;

public class State {
/*State is a class allowing to receive a user statement.*/

private String istate;

public State(int a) {
	
	System.out.println ("Listening to you : ");
	Scanner readkeys = new Scanner(System.in);
	setInput(readkeys.nextLine());
	readkeys.close();
}
	
	public String getInput() {
		return istate;
	}
	
	public void setInput(String istate) {
		this.istate = istate+" .";
	}

}
