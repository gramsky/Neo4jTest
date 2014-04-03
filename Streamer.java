package ContextEngine;

import ContextEngine.NeoHandle;
import java.util.LinkedList;
import org.neo4j.graphdb.GraphDatabaseService;



/*
 * Class to handle streaming data from any coded source
 */
public class Streamer {

	private GraphDatabaseService myHandle;
	
	private String contextType;
	
	Streamer(GraphDatabaseService graphDb)
	{
		myHandle = graphDb;
	}
	
	
	public void streamInput(String dataLine)
	{
		MyContext context = new MyContext();
		
		System.out.println(dataLine);
		
		//apply rules to data (make ContextRules do this, send type and string of data)
		ContextRules contextRules = new ContextRules();
		context = contextRules.processContextRules("Calls", dataLine);
		
		//write data (using linked list from contextRules)
		NeoProcessor processor = new NeoProcessor(myHandle);
		processor.processContextData(context);
	}
	
	public void runQuery()
	{
		NeoProcessor processor = new NeoProcessor(myHandle);
		try{
		    processor.myQuery();
		}
		catch(Exception e){}
	}
	

}
