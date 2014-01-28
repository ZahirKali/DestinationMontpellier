package models;

import java.util.ArrayList;
import java.util.List;

public class LesTypes {

	private String city;
	private String  entity;
	private String location;
	private String  transport;
	private String  food;
	private String lodging;
	private String  health;
	private String  money;
	private String  religion;
	private String  loisirs;
	private String  study;

	List<String> t;
	
	public List<String> getType()
	{
		t=new ArrayList<String>();
		t.add(city);
		t.add(entity);
		t.add(location);
		t.add(food);
		t.add(religion);
		t.add(lodging);
		t.add(study);
		t.add(health);
		t.add(money);
		t.add(loisirs);
		t.add(transport);
		return t;
		
	}


	
}
