package ContextEngine;

import java.util.HashMap;
import java.util.Set;



public class AttributeMap extends HashMap{

	public AttributeMap()
	{
		
	}
	
	public Set getAttributes()
	{
		return this.keySet();
	}
}
