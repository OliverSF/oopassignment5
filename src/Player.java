//creating abstract Player class
//in order to obey open, closed principle
//therefore other player types apart from Avatar may be added later
public abstract class Player {
	
	//creating protected variables to be accessed only within the package
	protected String name;
	protected Pos pos = new Pos();
	
	//method to add position to the player
	public void add(Pos pos) {
		this.pos = pos;
		
	}
	//method to get X axis of player
	public int getXPosition() {
		
		return pos.getXpos();
		
	}
	//method to get Y axis of player
	public int getYPosition() {
		
		return pos.getYpos();
		
	}
	//method to call players name
	public String getName() {

		return name;
	}

}
