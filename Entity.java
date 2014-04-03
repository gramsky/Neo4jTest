package ContextEngine;

import java.util.LinkedList;
import org.neo4j.graphdb.Node;


public class Entity {

	private LinkedList<Attribute> attributes;
	private String Name;
	private Boolean hasName = false;
	private Node neoNode;  // this is the reference to the node that the entity became

	public Entity()
	{
		
	}

	public Entity(LinkedList<Attribute> attributes)
	{
		this.attributes = attributes;
	}
	
	public void setName(String Name)
	{
		this.Name = Name;
		this.hasName = true;
	}
	
	public Boolean hasName()
	{
		return this.hasName;
	}
	
	public String getName()
	{
		return this.Name;
	}
	
	public Entity(LinkedList<Attribute> attributes, String name)
	{
		this.Name = name;
		this.attributes = attributes;
	}
	
	public LinkedList<Attribute> getAttributes()
	{
		return this.attributes;
	}
	
	public Node getNode()
	{
		return this.neoNode;
	}
	
	public void setNode(Node node)
	{
		this.neoNode = node;
	}

}



