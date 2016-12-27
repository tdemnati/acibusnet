/*
 * Copyright (c) 2016-2017 "Acibus Technology,"
 * -- [http://www.a-cibus.com]
 *
 * This file is part of AcibusNet.
 * VectorOfState: aims at defining a vector of a given environment state. 
 */

package acibus.environment;

import java.util.Vector;

public class VectorOfState {

	private Vector<String> myvector;
	
	public VectorOfState (String mystate){
		
		Vector<String> aVectorOfState = new Vector<String>();
			
		//Convert the string to a char array
		char[] sChars = mystate.toCharArray();
		//"word" is one element of my vector
		String word = "";

		int n = mystate.length() - 1;
		
		for(int i = 0; i < n; ++i) 
		{
			//if alpha numeric concatenate the chars into a word
			if (Character.toString(sChars[i]).matches("[A-Za-z0-9_]")) 
			{
				word = word.concat(Character.toString(sChars[i]));
			}
			//if a whitespace append word to the vector and stop concatenating chars into word and 
			else if (Character.isWhitespace(sChars[i]))
			{
				if (word!="") {aVectorOfState.add(word);}
				word = "";
			}
			//if not alpha numeric and not a whitespace 
			else if (!Character.toString(sChars[i]).matches("[A-Za-z0-9_]")&&!Character.isWhitespace(sChars[i])) 
			{
				//
				if (word!="") {aVectorOfState.add(word);}
				word = "";
				word = word.concat(Character.toString(sChars[i]));
				if (word!="") {aVectorOfState.add(word);}
				word = "";
			}

		}
		
		
		setVector(aVectorOfState);
		
	}
	public Vector<String> getVector(){
		return myvector;
	}
		
	public void setVector(Vector<String> myvector)
	{
		this.myvector = myvector;
	}
	
	public void printVector()
	{
		// print vector size
		System.out.print("The vector size is: " + myvector.size() + "\n");
		//print vector
		System.out.print("My vector is: [" + myvector.get(0).toString());
		if (myvector.size()>1) {
		for (int j=2;j<myvector.size()+1;j++)
		{
			//TODO To clean up the usage of System.out.print to better understand the code
			System.out.print("," + myvector.get(j-1).toString());
		}
		}
		System.out.print("]\n");
	}
	
}
