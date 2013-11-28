package googleplaces.types;

import googleplaces.*;
import java.util.ArrayList;
import java.util.List;

public class Food extends Entity {
	private List<String> localType = new ArrayList<>();
	
	public Food(){
		localType.add("Food");
		this.types = localType;
	}
	
	public List<String> getLocalType() {
		return localType;
	}

	public void setLocalType(List<String> localType) {
		this.localType = localType;
	}
	
}
