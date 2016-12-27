package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import acibus.environment.VectorOfState;

public class shouldreturnvectorstateTest {

	protected VectorOfState myVectorState1;
	protected VectorOfState myVectorState2;
	
	@Before
	public void prepareState() {
		// " ." Is used for test purposes only
		myVectorState1 = new VectorOfState("Teresa.is$guapa." +" .");
		myVectorState2 = new VectorOfState("Teresa" +" .");
	}
	
	@Test
	public void shouldReturnVectorState1Test() {
		System.out.println("\n --- RUNNING VectorOfStateTest 1 of 2 --- ");
	    //Check number of words in mystate
		assertTrue("Error, Vector size is not OK", myVectorState1.getVector().size() == 6);
	    
		//Check strings are matching
		String mystr1 = myVectorState1.getVector().get(0).toString();
	    assertEquals("Teresa",mystr1);
	    
		String mystr2 = myVectorState1.getVector().get(1).toString();
	    assertEquals(".",mystr2);
	    
		String mystr3 = myVectorState1.getVector().get(2).toString();
	    assertEquals("is",mystr3);
	    
		String mystr4 = myVectorState1.getVector().get(3).toString();
	    assertEquals("$",mystr4);
	    
		String mystr5 = myVectorState1.getVector().get(4).toString();
	    assertEquals("guapa",mystr5);
	    
		String mystr6 = myVectorState1.getVector().get(5).toString();
	    assertEquals(".",mystr6);
	    
	    myVectorState1.printVector();
	}
	@Test
	public void shouldReturnVectorState2Test() {
		System.out.println("\n --- RUNNING VectorOfStateTest 2 of 2---");
	    //Check number of words in mystate
		assertTrue("Error, Vector size is not OK", myVectorState2.getVector().size() == 1);
	    
		//Check strings are matching
		String mystr1 = myVectorState2.getVector().get(0).toString();
	    assertEquals("Teresa",mystr1);

	    myVectorState2.printVector();
	}

}
