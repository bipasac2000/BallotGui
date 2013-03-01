import java.util.Enumeration;
import java.util.Hashtable;


public class LocalBallot implements BallotInterface{
	
	String[] cName; 
	String[] cInfo;
  int ballotId; 
  Hashtable<String, String> localVotes;  
	
	LocalBallot(String []names, String[] info, int id){
		cName = new String[names.length]; 
		cInfo = new String[info.length]; 
		for (int i=0; i<names.length; i++){
			cName[i] = names[i]; 
			cInfo[i] = info[i]; 
		}
		ballotId = id; 
		localVotes = new Hashtable<String, String>();
	}
	
	public void updateTable(String voterId, String cName){
    	localVotes.put(voterId, cName); 
	}
	
	public Hashtable<String, Integer> tallyVotes(){
		
		Hashtable <String, Integer> candidateVotes = new Hashtable<String, Integer>(); 
		
		Enumeration<String> enumKey = localVotes.keys();
		while(enumKey.hasMoreElements()) {
		    String key = enumKey.nextElement();
		    String val = localVotes.get(key);
				if (candidateVotes.containsKey(val)){
		    	Integer num = new Integer (candidateVotes.get(val)+1); 
		    	candidateVotes.put(val, num); 
				}
				else {
					candidateVotes.put(val, new Integer(1));   
				}
		}
		
	return candidateVotes; 
	}

	public void castVote() {
		BallotGui b = new BallotGui("Ballot", cName, cInfo);
		b.create(this); 
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
		System.out.println("Result of Local ballot"); 
		System.out.println(winner + " is the winner. Has " + max + " votes."); 
	}
	
	

}
