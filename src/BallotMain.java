
public class BallotMain {
	
	public static void main (String[] args){
		String[] cName = {"C1", "C2", "C3"}; 
		String[] cInfo = {"I1", "I2", "I3"}; 
		int id = 1; 
		LocalBallot myBallot = (LocalBallot)BallotFactory.makeBallot(cName, cInfo, id); 
		myBallot.castVote(); 
		
	}

}
