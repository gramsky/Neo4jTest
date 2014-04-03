package ContextEngine;

import org.neo4j.graphdb.RelationshipType;
import java.util.LinkedList;


public class MyRelationship {

	private RelationshipType relType;                // the type of relationship.  Dictates rule set
	private Entity nodeFrom;                         // The node from which the relationship originates
	private Entity nodeTo;                           // The node where the relationship terminates
	private LinkedList<Attribute> attributes;       // Set of attributes, key/value stored
	
	public MyRelationship(Entity nodeFrom, Entity nodeTo, RelationshipType relType, LinkedList<Attribute> attributes)
	{
		this.nodeFrom = nodeFrom;
		this.nodeTo = nodeTo;
		this.relType = relType;
		this.attributes = attributes;
	}
	
	public void setRelationshipType(RelationshipType type)
	{
		this.relType = type;
	}
	
	public Entity getEntityFrom()
	{
		return this.nodeFrom;
	}
	
	public Entity getEntityTo()
	{
		return this.nodeTo;
	}
	
	public RelationshipType getRelationshipType()
	{
		return this.relType;
	}
	
	public LinkedList<Attribute> getAttributes()
	{
		return this.attributes;
	}

	public void addEntityTo(Entity entity)
	{
		this.nodeTo = entity;
	}
	
	public void addEntityFrom(Entity entity)
	{
		this.nodeFrom = entity;
	}
	
	public void addAttributes(LinkedList<Attribute> attributes)
	{
		this.attributes = attributes;
	}
	
}
