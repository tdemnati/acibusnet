package knowledge;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 * An example of unit testing with Neo4j.
 * http://www.vogella.com/tutorials/JUnit/article.html for ASSERT tests etc.
 */
public class shouldcreateasimplenetTest
{
    protected GraphDatabaseService graphDb;

    /**
     * Create temporary database for each unit test.
     */
    // START SNIPPET: beforeTest
    @Before
    public void prepareTestData()
    {
        //This method is executed before each test. It is used to prepare the test environment
    }
    // END SNIPPET: beforeTest

    /**
     * Shutdown the database.
     */
    // START SNIPPET: afterTest
    @After
    public void destroyTestData()
    {
        //This method is executed after each test. It is used to cleanup the test environment (e.g., delete temporary data, restore defaults).
    }
    // END SNIPPET: afterTest

    @Test
    public void shouldCreateNet()
    {
        // START SNIPPET: unitTest

        // END SNIPPET: unitTest
    }
}