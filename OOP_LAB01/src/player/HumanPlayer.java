package player;

public class HumanPlayer extends Player{

	public HumanPlayer(String repColor) {
		this.repColor = repColor;
		setSymbol();
		identity = "human";
	}
	public boolean isAbleToPlay() {
		// TODO Auto-generated method stub
		return false;
	}
}
