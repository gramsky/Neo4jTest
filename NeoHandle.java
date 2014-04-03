package ContextEngine;


import org.neo4j.graphdb.GraphDatabaseService;


public class NeoHandle {

    private static GraphDatabaseService graphDb;
	
	NeoHandle(GraphDatabaseService graphDb)
	{
		this.graphDb = graphDb;
	}


	public GraphDatabaseService getNeoHandle()
	{
		return graphDb;
	}
	
}
