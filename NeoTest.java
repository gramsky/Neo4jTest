package ContextEngine;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import java.util.Iterator;
import org.neo4j.helpers.collection.IteratorUtil;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;





import java.io.*;
import java.util.LinkedList;

import ContextEngine.Tester;

public class NeoTest {

	private static final String DB_PATH = "/usr/local/Cellar/neo4j/2.0.1/libexec/data/new_graph.db";

    // START SNIPPET: vars
    static GraphDatabaseService graphDb;
    // END SNIPPET: vars


    public static void main( final String[] args ) throws IOException
    {

        NeoTest test = new NeoTest();
        
        test.createDb();
        
    	//Our tester to test the database
	    Tester myTest = new Tester("Stream", graphDb);

        
    	try{
        	myTest.fileTester("/Users/me/UMD/PHD Thesis/data/calllog");
            test.shutDown();
        	
    	}
    	catch (IOException ex)
    	{
    		System.out.println("Cannot open file fool");
    	}
    }

    
    void createDb()
    {
        clearDb();
        // START SNIPPET: startDb
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
        registerShutdownHook( graphDb );
  
    }

    private void clearDb()
    {
        try
        {
            FileUtils.deleteRecursively( new File( DB_PATH ) );
        }
        catch ( IOException e )
        {
            throw new RuntimeException( e );
        }
    }

    void shutDown()
    {
        System.out.println();
        System.out.println( "Shutting down database ..." );
        // START SNIPPET: shutdownServer
        graphDb.shutdown();
        // END SNIPPET: shutdownServer
    }

    // START SNIPPET: shutdownHook
    private static void registerShutdownHook( final GraphDatabaseService graphDb )
    {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                graphDb.shutdown();
            }
        } );
    }
}
