package acibus.net;

import org.neo4j.driver.v1.AccessMode;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.Values;
import environment.State;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic( "neo4j", "tietie666" ) );

    	for (int i=1; i<2; i++) {

    		State mystate = new State(i);
    		
        	//DB session transaction
    		String bookmark;

    		try ( Session session = driver.session( AccessMode.WRITE ) )
    		{
    		    try ( Transaction tx = session.beginTransaction())
    		    {
    		        tx.run( "CREATE (object:Object {name: {name}, label: {label}})",
    		                Values.parameters( "name", mystate.getInput(), "label", "king" ) );
    		        tx.success();
    		        tx.close();
    		    }
    		    finally
    		    {
    		        bookmark = session.lastBookmark();
    		    }
    		}
    		try ( Session session = driver.session( AccessMode.READ ) )
    		{
    		    StatementResult result;
    		    try ( Transaction tx = session.beginTransaction( bookmark ) )
    		    {
    		        result = tx.run( "MATCH (object:Object {name: {name}}) RETURN object.label",
    		                Values.parameters( "name", mystate.getInput() ) );
    		        tx.success();
    		        tx.close();
    		        while ( result.hasNext() )
    		        {
    		            Record record = result.next();
    		            System.out.println( mystate.getInput() + "'s label is " + record.get( "object.label" ) );
    		        }
    		    }
    		}
    		try ( Session session = driver.session( AccessMode.WRITE ) )
    		{
    		    try ( Transaction tx = session.beginTransaction() )
    		    {
    		        tx.run( "MATCH (n) DETACH DELETE n");
    		        tx.success();
    		        tx.close();
    		    }
    		}
        	//session.close();
        	//End of DB session transaction
    	}
    	



    	driver.close();
    	
    }
}
