package googleplaces.types;

import googleplaces.*;
import java.util.ArrayList;
import java.util.List;

public class Lodging extends Entity{
		public Lodging(){
			this.types = new ArrayList<>();
			types.add("lodging");
		}
}
