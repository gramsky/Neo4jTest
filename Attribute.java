package ContextEngine;

public class Attribute {

	private String attribute;
	private String value;
	
	public Attribute()
	{
		
	}
	
	public Attribute(String attribute, String value)
	{
		this.attribute = attribute;
		this.value = value;
	}
	
	public String getAttribute()
	{
		return this.attribute;
	}
	
	public String getValue()
	{
		return this.value;
	}
}
