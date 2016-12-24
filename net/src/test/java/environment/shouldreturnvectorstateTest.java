package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class shouldreturnvectorstateTest {

	protected VectorOfState myVector;
	
	@Before
	public void prepareState() {
		myVector = new VectorOfState("Teresa.is. guapa.*");
	}
	
	@Test
	public void shouldReturnVectorStateTest() {
	    //Check number of words in mystate
		assertTrue("Error, Vector size is not OK", myVector.getVector().size() == 3);
	    
		//Check strings are matching
		String mystr1 = myVector.getVector().get(0).toString();
	    assertEquals("Teresa",mystr1);
	    
		String mystr2 = myVector.getVector().get(1).toString();
	    assertEquals("is",mystr2);
	    
		String mystr3 = myVector.getVector().get(2).toString();
	    assertEquals("guapa",mystr3);

	}

}
