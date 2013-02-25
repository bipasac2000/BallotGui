
public class BallotFactory {

	public static BallotInterface makeBallot(String[] cName, String[] cInfo, int id){

				LocalBallot lb = new LocalBallot(cName, cInfo, id);
				return lb; 
		}
	
	public static BallotInterface makeBallot(String type, int id){
		
		if (type.equals("national")){
			NationalBallot nb = new NationalBallot(id); 
			return nb; 
		}
		else if (type.equals("state")){
			StateBallot sb = new StateBallot(id);
			return sb; 
		}
	
		return null; 
	}
}
