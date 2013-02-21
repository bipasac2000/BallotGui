
public class BallotFactory {

	public static BallotGui makeBallot(String cName, String type){
		if (type.equals("national")){
			NationalBallotGui nb = new NationalBallotGui(cName); 
			return nb; 
		}
		else if (type.equals("state")){
			StateBallotGui sb = new StateBallotGui(cName);
			return sb; 
		}
		else if (type.equals("local")){
				LocalBallotGui lb = new LocalBallotGui(cName);
				return lb; 
		}
	}
}
