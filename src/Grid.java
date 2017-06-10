import java.util.*;

public class Grid {
	
	//creating and initialising private variables
	private Player[][] board;
	private List<Player> gridlist = new ArrayList<Player>();
	private Pos p;
	private int max;
	
	//constructor to create a 'board' 
	public Grid(int i){
		
		this.board = new Player[i][i];
		//setting max for boundary
		max = i-1;
	}
	
	//method to add new players to the board
	public void add(Player ani, Pos pos) {
		
		//gives the position to the player
		ani.add(pos); 
		//setting the player to the position on the 'board'
		board[pos.getXpos()][pos.getYpos()] = ani;
		gridlist.add(ani);	
	}

	//method to create a random position to move to
	public Pos getRandXY() {
		
		//creating new random
		Random rand = new Random();
		//boundaries to operate in
		int min = 0;
		
		//getting values for x and y axis
		int Xaxis = rand.nextInt(max - min + 1) + min;
		int Yaxis = rand.nextInt(max - min + 1) + min;
		
		//creating new position of these values
		p = new Pos(Xaxis, Yaxis);
		
		//returning this position
		return p;
	}
	//method to return item on certain position on board
	public Player getItemOnBoard(int i, int j) {

		return this.board[i][j];
		
	}
	//setting new position for player
	public void setNewPosOnBoard(Player ani, Pos randompos) {
		
		//removing the player from their old position on the 'board'
		board[ani.getXPosition()][ani.getYPosition()] = null;
		
		//adding the new position to the player
		ani.add(randompos);
		
		//setting the player to the position on the 'board'
		board[randompos.getXpos()][randompos.getYpos()] = ani;
	}
	//method to calculate distance between two positions
	public int calculateDistance(Player ani, Pos randompos) {
		
		//getting old position values of player
		double x = ani.getXPosition();
		double y = ani.getYPosition();
		//getting new position values
		double k = randompos.getXpos();
		double j = randompos.getYpos();
		
		//method to calculate the distance and round the returned double and cast to an integer
		int distance = (int) Math.round((Math.sqrt(((x - k)*(x - k)) + ((y - j)*(y - j)))));
		
		//output to console
		System.out.println("Distance: " + distance);
		return distance;
		
	}
}
