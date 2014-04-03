package ContextEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.neo4j.graphdb.GraphDatabaseService;

public class Tester {

	private Streamer myStream;
	private String inputType;
	
	public Tester(String inputType, GraphDatabaseService graphDb)
	{
		this.inputType = inputType;
		if(inputType.toLowerCase().contains("Stream".toLowerCase()))
		{
			System.out.println("Using Stream");
		    // instantiate streamer
		    myStream = new Streamer(graphDb);
		}
	}
	
	public void fileTester(String fileName) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		
		while(in.ready())
		{
			String s = in.readLine();
			myStream.streamInput(s);
		}
		in.close();
		myStream.runQuery();
	}
	
	
}
