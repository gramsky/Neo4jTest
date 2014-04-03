package ContextEngine;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.helpers.collection.IteratorUtil;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.sql.*;


/*
 * Class is responsible for placing entries directly into the Neo4J database.  It requires either a DB handle
 *     or an object that has direct access to the database (for example, a Node)
 */
public class NeoProcessor {

	private GraphDatabaseService handle;
	
	public NeoProcessor(GraphDatabaseService handle)
	{
		this.handle = handle;
	}
	
	public void processContextData(MyContext context)
	{
		LinkedList<Entity> entities = context.getEntityList();
		LinkedList<MyRelationship> relationships = context.getRelationshipList();
		
		// add all entities in context
		for(Entity c : entities)
		{
		    Node node = addNode(c);
		    c.setNode(node);		    
		}
		
		//Now add relationships from relationship list
		for(MyRelationship r : relationships)
		{
			addRelationship(r);
		}
	}
	
	public void myQuery() 
	{
		/*
		Connection conn = DriverManager.getConnection("jdbc:neo4j://localhost:7474/");
		try{
		ResultSet rs = conn.createStatement().executeQuery("start n=node(*) where n.Name =~ '.*79.*' return n, n.Name");
		
		    while(rs.next()) {
		        System.out.println(rs.getLong("n.name"));
		    }
		}
		
		finally{
			return;
		}*/
		//return;
        String nodeResult = "";
        String rows = "";
        String resultString;
        String columnsString;

        System.out.println("In query");
        // START SNIPPET: execute
        GraphDatabaseService graphDb = this.handle;

        ExecutionEngine engine = new ExecutionEngine( this.handle );

        ExecutionResult result;
        try ( Transaction ignored = graphDb.beginTx() )
        {
            result = engine.execute( "start n=node(*) where n.Name =~ '.*79.*' return n, n.Name" );
            // END SNIPPET: execute
            // START SNIPPET: items
            Iterator<Node> n_column = result.columnAs( "n" );
            for ( Node node : IteratorUtil.asIterable( n_column ) )
            {
                // note: we're grabbing the name property from the node,
                // not from the n.name in this case.
                nodeResult = node + ": " + node.getProperty( "Name" );
                System.out.println("In for loop");
                System.out.println(nodeResult);

            }
            // END SNIPPET: items
            
            
            
            // START SNIPPET: columns
            List<String> columns = result.columns();
            // END SNIPPET: columns

            // the result is now empty, get a new one
            result = engine.execute( "start n=node(*) where n.Name =~ '.*79.*' return n, n.Name" );
            // START SNIPPET: rows
            for ( Map<String, Object> row : result )
            {
                for ( Entry<String, Object> column : row.entrySet() )
                {
                    rows += column.getKey() + ": " + column.getValue() + "; ";
                    System.out.println("nested");
                }
                rows += "\n";
            }
            // END SNIPPET: rows
            resultString = engine.execute( "start n=node(*) where n.Name =~ '.*79.*' return n.Name"  ).dumpToString();
            columnsString = columns.toString();
            System.out.println(rows);
            System.out.println(resultString);
            System.out.println(columnsString);

            
            //ignored.success();
            System.out.println("leaving");

        }
	}
	
	public void addRelationship(MyRelationship relationship)
	{
		//TODO - wrap in transaction
        GraphDatabaseService graphDb = this.handle;

		Relationship myRelationship;   // Neo4J relationship object, creates relationship in database
		Entity entityFrom = relationship.getEntityFrom();
		Entity entityTo = relationship.getEntityTo();
		Node nodeFrom = entityFrom.getNode();
		Node nodeTo = entityTo.getNode();
		
		
		RelationshipType relType = relationship.getRelationshipType();
		LinkedList<Attribute> attributes = relationship.getAttributes();
		
		try ( Transaction tx = graphDb.beginTx() )
        {
		    // create relationship
            myRelationship = nodeFrom.createRelationshipTo(nodeTo, relType);
        
            // Now set attriutes by iterating through linked list
            for(Attribute a : attributes)
            {
        	    String attribute = a.getAttribute();
                String value = a.getValue();	
                myRelationship.setProperty(attribute, value);
                System.out.println("Setting attribute " + attribute + "now ");
            }
            tx.success();
        }
	}
	
	public Node addNode(Entity myNode)
	{   
		//TODO - wrap in transaction
        GraphDatabaseService graphDb = this.handle;
        
        Node node;
        
       
        try ( Transaction tx = graphDb.beginTx() )
        {
            node = graphDb.createNode();
            if(myNode.hasName()) 
            	{
            	System.out.println("setting name");
            	node.setProperty("Name", myNode.getName());
            	System.out.println(node.getProperty("Name"));
            	}
            /*LinkedList<Attribute> attributes = myNode.getAttributes();
        
            // Now set attriutes by iterating through linked list
            if(!attributes.isEmpty()) for(Attribute a : attributes)
            {
        	    String attribute = a.getAttribute();
                String value = a.getValue();	
                node.setProperty(attribute, value);
            } // for loop for attributes*/
            tx.success();
        }//try transaction	
        
        return node;
	}
}
