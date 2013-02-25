
public class BallotMain {
	
	public static void main (String[] args){
		String[] cName = {"C1", "C2", "C3"}; 
		String[] cInfo = {"I1", "I2", "I3"}; 
		BallotGui b = new BallotGui("Ballot", cName, cInfo);
		b.create(); 
		
	}

}
