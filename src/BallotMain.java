
public class BallotMain {
	
	public static void main (String[] args) throws InterruptedException{
		String[] cName = {"C1", "C2", "C3"}; 
		String[] cInfo = {"I1", "I2", "I3"}; 
		int id = 1; 
		LocalBallot localBallot = (LocalBallot)BallotFactory.makeBallot(cName, cInfo, id); 
		localBallot.castVote();
		// wait a minute for votes to be cast
		Thread.sleep(60000); 
		localBallot.getResults();
		StateBallot stateBallot = (StateBallot)BallotFactory.makeBallot("state", id+1); 
		stateBallot.addLocalBallot(localBallot);
		stateBallot.getResults(); 
		NationalBallot nationalBallot = (NationalBallot)BallotFactory.makeBallot("national", id+2); 
		nationalBallot.addStateBallot(stateBallot); 
		nationalBallot.getResults(); 
	}

}
