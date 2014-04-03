package ContextEngine;

import java.util.LinkedList;

public class MyContext {

	private LinkedList<Entity> entityList;
	private LinkedList<MyRelationship> relationshipList;
	private Boolean hasEntityList = false;
	private Boolean hasRelationshipList = false;
	
	MyContext()
	{
		this.entityList = new LinkedList();
		this.relationshipList = new LinkedList();
	}
	
	public boolean hasEntityList()
	{
		return hasEntityList;
	}
	
	public boolean hasRelationshipList()
	{
		return hasRelationshipList;
	}
	
	public void addEntity(Entity entity)
	{
		//if false, we have yet to add anything.  Set to true for further evals
		if(hasEntityList == false) hasEntityList = true;
		
		this.entityList.add(entity);
	}
	
	public void addRelationship(MyRelationship relationship)
	{
		//if false, we have yet to add anything.  Set to true for further evals
		if(hasRelationshipList == false) hasRelationshipList = true;
			
		this.relationshipList.add(relationship);
	}
	
	public LinkedList<Entity> getEntityList()
	{
		return this.entityList;
	}
	
	public LinkedList<MyRelationship> getRelationshipList()
	{
		return this.relationshipList;
	}
}
