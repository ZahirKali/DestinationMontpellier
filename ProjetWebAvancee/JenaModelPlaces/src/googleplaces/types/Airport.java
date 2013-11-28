package googleplaces.types;

import googleplaces.Entity;

import java.util.ArrayList;
import java.util.List;
public class Airport extends Entity{
	private List<String> localType = new ArrayList<>();
	
	public Airport(){
		localType.add("Aireport");
		this.types = localType;
	}

	public List<String> getLocalType() {
		return localType;
	}

	public void setLocalType(List<String> localType) {
		this.localType = localType;
	}
}
