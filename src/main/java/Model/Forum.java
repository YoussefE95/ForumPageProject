package Model;

import java.util.ArrayList;

public class Forum
{
	private String name;
	private ArrayList<Topic> topics;
	
	public Forum( String name, ArrayList<Topic> topics )
	{
		this.name = name;
		this.topics = topics;
	}

	public String getName()
	{
		return name;
	}
	
	public ArrayList<Topic> getTopics()
	{
		return topics;
	}
	
	public int getNumberTopics()
	{
		return topics.size();
	}
}