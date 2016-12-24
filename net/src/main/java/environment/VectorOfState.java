package environment;

import java.util.Vector;
import java.util.regex.Pattern;

public class VectorOfState {

	private Vector<String> myvector;
	
	public VectorOfState (String mystate){
		
		Vector<String> aVector = new Vector<String>();
			
		//Convert the string to a char array and then just add each char to the vector
		char[] sChars = mystate.toCharArray();
		String word = "";
		//TODO use the following instead of "not alpha num": Pattern p = Pattern.compile("\\p{Punct}");
		//TODO use the following for spaces: Pattern s = Pattern.compile("\\p{Space}");
		
		System.out.print("My state has " + mystate.length() + " characters"+ "\n");
		System.out.print("My state is: " + "\""+ mystate + "\""+ "\n");
		
		for(int i = 0; i < mystate.length(); ++i) 
		{
			//if alpha numeric
			if (Character.toString(sChars[i]).matches("[A-Za-z0-9_]")) 
			{
				word = word.concat(Character.toString(sChars[i]));
			}

			//if not alpha numeric
			else if (!Character.toString(sChars[i]).matches("[A-Za-z0-9_]")) 
			{
				//count = count + 1;
				
				if (i>0 && !Character.toString(sChars[i-1]).matches("[A-Za-z0-9_]"))
				{
					System.out.print("Char " + "\"" + Character.toString(sChars[i]) + "\"" + " has been discarded\n");
				}
				else 
				{
					aVector.add(word);
					word = "";
				}
			}	
		}
		
		System.out.print("The vector size is: " + aVector.size() + "\n");
		
		//print vector
		for (int j=1;j<aVector.size()+1;j++)
		{
			//TODO To clean up the usage of System.out.print to better understand the code
			System.out.print("My word number " + j + " is: " + aVector.get(j-1).toString() + "\n");
		}
		setVector(aVector);
		}
		
	public Vector<String> getVector(){
		return myvector;
	}
		
	public void setVector(Vector<String> myvector)
	{
		this.myvector = myvector;
	}
	
}
