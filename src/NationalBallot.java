
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


public class NationalBallot implements BallotInterface{

	ArrayList <StateBallot> stateBallots; 
	int id; 
	
	public NationalBallot(int inputId){
		id = inputId; 
		stateBallots = new ArrayList<StateBallot>(); 
	}
	
	public void addStateBallot (StateBallot sb){
		stateBallots.add(sb); 
	}

	public Hashtable <String, Integer> tallyVotes() {
		
		Hashtable <String, Integer> NationalResults = new Hashtable<String, Integer>(); 
		
		for (int i =0; i<stateBallots.size(); i++){
			Hashtable <String, Integer> stateResults = stateBallots.get(i).tallyVotes(); 
			Enumeration<String> enumKey = stateResults.keys();
			while(enumKey.hasMoreElements()) {
			    String key = enumKey.nextElement();
			    if (NationalResults.containsKey(key)){
			    	Integer num = new Integer (NationalResults.get(key).intValue()+stateResults.get(key).intValue()); 
			    	NationalResults.put(key, num); 
					}
					else {
						NationalResults.put(key, stateResults.get(key));   
					}
			}
		}
		return NationalResults;
	}


	public void castVote() {
	}

	public void getResults() {
		Hashtable <String, Integer> results = tallyVotes(); 
		int max = 0; 
		String winner = ""; 
		
		Enumeration<String> enumKey = results.keys();
		while(enumKey.hasMoreElements()) {
		    String key = enumKey.nextElement();
		    Integer val = results.get(key);
		    if (val.intValue() > max){
		    	max = val.intValue(); 
		      winner = key;
		    }
		}
		System.out.println("Result of National ballot"); 
		System.out.println(winner + " is the winner. Has " + max + " votes."); 
		
	}

}

