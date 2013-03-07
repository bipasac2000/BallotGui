import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


public class StateBallot implements BallotInterface{

    ArrayList <BallotInterface> ballots; 
    int id; 

    public StateBallot(int inputId){
	id = inputId; 
	ballots = new ArrayList<BallotInterface>(); 
    }

    public void add (BallotInterface b){
	ballots.add(b); 
    }

    public Hashtable <String, Integer> tallyVotes() {
	//tally the votes of all sub-elections together and put them into a 
	//hash table with candidates as keys and number of votes as values
	Hashtable <String, Integer> stateResults = new Hashtable<String, Integer>(); 

	for (int i =0; i<ballots.size(); i++){
	    Hashtable <String, Integer> localResults = ballots.get(i).tallyVotes(); 
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
	//void - you cannot cast votes at the state level
    }

    public void getResults() {
	//print the winner of the state election
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
	if (max == 1) {
	    System.out.println(winner + " is the winner with " + max + " vote."); 
	} else {
	    System.out.println(winner + " is the winner with " + max + " votes."); 
	}

    }

}