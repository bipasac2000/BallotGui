import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


public class NationalBallot implements BallotInterface{

    ArrayList <BallotInterface> ballots; 
    int id; 

    public NationalBallot(int inputId){
	id = inputId; 
	ballots = new ArrayList<BallotInterface>(); 
    }

    public void add (BallotInterface b) {
	ballots.add(b);
    }

    public void remove (BallotInterface b) {
	ballots.remove(b);
    }
    
    public Hashtable <String, Integer> tallyVotes() {

	Hashtable <String, Integer> NationalResults = new Hashtable<String, Integer>(); 

	for (int i =0; i<ballots.size(); i++){
	    Hashtable <String, Integer> stateResults = ballots.get(i).tallyVotes(); 
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
	//void - you cannot cast votes at the national level
    }

    public void getResults() {
	//print the winner of the national election
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
	if (max == 1) {
	    System.out.println(winner + " is the winner with " + max + " vote."); 
	} else {
	    System.out.println(winner + " is the winner with " + max + " votes."); 
	}

    }

}