package knowledge;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import acibus.knowledge.cooking.singlegatenet;

public class shouldforwardmultiply {

	protected singlegatenet mySingleGateNet;

	@Before
	public void prepareState() {
		// " ." Is used for test purposes only
		mySingleGateNet = new singlegatenet(-2,3);
	}
	
	@Test
	public void shouldforwardmultiplyTest() {
		System.out.println("\n --- RUNNING forward multiply 1 --- ");
		assertTrue("Error, multiplication result is wrong", mySingleGateNet.force(0.01) == -5.8706);
	    
	}

}
