
public class BallotMain {

    public static void main (String[] args) throws InterruptedException{
	String[] cName = {"Candidate 1", "Candidate 2", "Candidate 3"}; 
	String[] cInfo = {"Candidate 1 Info", "Candidate 2 Info", "Candidate 3 Info"}; 
	int id = 1; 
	LocalBallot chapelHillBallot = (LocalBallot)BallotFactory.makeBallot(cName, cInfo, id); 
	chapelHillBallot.castVote();
	LocalBallot durhamBallot = (LocalBallot)BallotFactory.makeBallot(cName, cInfo, id+1); 
	// test state and national ballots - wait a minute for votes to be cast
	Thread.sleep(60000); 
	chapelHillBallot.getResults();
	StateBallot NCBallot = (StateBallot)BallotFactory.makeBallot("state", id+2); 
	NCBallot.add(chapelHillBallot);
	NCBallot.add(durhamBallot);
	NCBallot.getResults(); 
	LocalBallot charlestonBallot = (LocalBallot)BallotFactory.makeBallot(cName, cInfo, id+3); 
	StateBallot SCBallot = (StateBallot)BallotFactory.makeBallot("state", id+4); 
	SCBallot.add(charlestonBallot);
	NationalBallot nationalBallot = (NationalBallot)BallotFactory.makeBallot("national", id+5);
	nationalBallot.add(NCBallot); 
	nationalBallot.add(SCBallot);
	nationalBallot.getResults(); 
    }

}