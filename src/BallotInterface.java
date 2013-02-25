import java.util.Hashtable;


public interface BallotInterface {
	
	Hashtable <String, Integer> tallyVotes(); 
	void castVote(); 
	void getResults(); 
	
}
