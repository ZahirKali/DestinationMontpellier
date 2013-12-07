
package googleplaces;

import java.util.List;

import JenaUtils.DumpString;

public class ResultSearchInCity{
   	private String next_page_token;
   	private List<Entity> results;
   	private String status;

 	public String getNext_page_token(){
		return this.next_page_token;
	}
	public void setNext_page_token(String next_page_token){
		this.next_page_token = next_page_token;
	}
 	public List<Entity> getResults(){
		return this.results;
	}
	public void setResults(List<Entity> results){
		this.results = results;
	}
 	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	
	public void Append(ResultSearchInCity other){
		for (Entity ent  : other.getResults()) {
			results.add(ent);
		}
		
		next_page_token= other.getNext_page_token();
	}
	
	
	@Override
	public String toString() {
		return DumpString.dumpString(this);
	}
}
