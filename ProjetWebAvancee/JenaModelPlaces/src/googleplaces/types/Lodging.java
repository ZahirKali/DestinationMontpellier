package googleplaces.types;

import googleplaces.*;
import java.util.ArrayList;
import java.util.List;

public class Lodging extends Entity{

		private List<String> localType = new ArrayList<>();
		public Lodging(){
			localType.add("Lodging");
			this.types = localType;
		}
		
		public List<String> getLocalType() {
			return localType;
		}

		public void setLocalType(List<String> localType) {
			this.localType = localType;
		}

}
