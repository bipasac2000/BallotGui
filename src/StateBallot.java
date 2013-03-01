import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


public class StateBallot implements BallotInterface{

	ArrayList <LocalBallot> localBallots; 
	int id; 
	
	public StateBallot(int inputId){
		id = inputId; 
		localBallots = new ArrayList<LocalBallot>(); 
	}

	public void addLocalBallot (LocalBallot lb){
		localBallots.add(lb); 
	}
	
	public Hashtable <String, Integer> tallyVotes() {
		
		Hashtable <String, Integer> stateResults = new Hashtable<String, Integer>(); 
		
		for (int i =0; i<localBallots.size(); i++){
			Hashtable <String, Integer> localResults = localBallots.get(i).tallyVotes(); 
			Enumeration<String> enumKey = localResults.keys();
			while(enumKey.hasMoreElements()) {
			    String key = enumKey.nextElement();
			    if (stateResults.containsKey(key)){
			    	Integer num = new Integer (stateResults.get(key).intValue()+localResults.get(key).intValue()); 
			    	stateResults.put(key, num); 
					}
					else {
						stateResults.put(key, localResults.get(key));   
					}
			}
		}
		return stateResults;
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
		System.out.println("Result of State ballot"); 
		System.out.println(winner + " is the winner. Has " + max + " votes."); 
		
	}

}
