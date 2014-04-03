package ContextEngine;

import java.util.LinkedList;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.DynamicRelationshipType;



public class ContextRules {

	private String myData;
	private String myType;
	
	public void ContextRules()
	{
		
	}
	
	/*
	 * Function takes input of known type and creates a list of objects to create
	 */
	public MyContext processContextRules(String type, String data)
	{
		// We'll store our data in a linked list
		MyContext context = new MyContext();
		LinkedList contextualData = new LinkedList();
		LinkedList<Attribute> attributes = new LinkedList();
		myData = data;
		myType = type;
		RelationshipType relType = DynamicRelationshipType.withName(type);
		
		//define entities as needed
		//define relationship as needed
		// add all objects to the LinkedList
		
		//for now let's do this by hand for call log sample
		String[] parts;  // holds the individual parts of the string
		parts = myData.split(",");
		
		//Now take it apart
		if(parts.length != 6)
		{
			System.out.println("Data format invalid");
			return null;
		}
		
		//define entities but do not assign
		Entity entityTo = new Entity();
		Entity entityFrom = new Entity();
		MyRelationship relationship;
		
		//figure out who called who
		if(parts[3].contains("outgoing")) 
        {
			entityTo.setName(parts[0]);
			entityFrom.setName(parts[1]);
		}
		else
		{
			entityTo.setName(parts[1]);
			entityFrom.setName(parts[0]);
		}
		
		// relationship
		relationship = new MyRelationship(entityFrom, entityTo, relType, attributes);
		
		// set attributes
		Attribute time = new Attribute("Time", parts[2]);
		Attribute direction = new Attribute("Direction", parts[3]);
		Attribute duration = new Attribute("Duration", parts[4]);
		Attribute numberHash = new Attribute("NumberHash", parts[5]);
		
		// add to linked list of attributes
		attributes.add(time);
		attributes.add(direction);
		attributes.add(duration);
		attributes.add(numberHash);
		
		// add attributes to relationship
		relationship.addAttributes(attributes);
		
		// create contextList
		context.addEntity(entityTo);
		context.addEntity(entityFrom);
		context.addRelationship(relationship);
		
		return context;
	}
	

	private LinkedList<String> mapDataToRules()
	{
		LinkedList<String> mapping = new LinkedList<String>();
		mapping.add("Entity1");
		mapping.add("Entity2");
		mapping.add("RelAtt");
		mapping.add("Eval");
		mapping.add("RelAtt");
		mapping.add("RelAtt");		
		
		return mapping;
	}
	
	
}
