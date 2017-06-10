import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StrategyTest {
	
	//declaring variables
	Avatar ani;
	Avatar luke;
	Avatar avi;
	Avatar dabi;
	Grid grid;
	Strategy aniStrategy;
	

	@Before
	public void setUp() throws Exception {
		
		//initialising varables
		ani = new Avatar("ani");
		luke = new Avatar("luke");
		avi = new Avatar("avi");
		dabi = new Avatar("dabi");
		//creating a 10 by 10 grid/'board'
		grid = new Grid(10);
		aniStrategy = new SimpleStrategy();
		
		//adding 'avatars' to the 'board' with they're initial positions
		grid.add(ani, new Pos(1,2));
		grid.add(luke, new Pos(1,1));
		grid.add(dabi, new Pos(1,0));
		
	}

	
	@Test
	public void calculatedistancetest() {
		
		//as original position is (1,2) & new position will be (3,4)
		//the distance calculated should be 3 (rounded up from 2.828....sqrt(8))
		int expectedDistance = 3;
		//creating a new position
		Pos position = new Pos(3,4);
		
		//setting the actual distance between the two points
		int actualDistance = (grid.calculateDistance(ani, position));
		
		//asserting that the expectedDistance should equal the actual Distance
		assertEquals(expectedDistance, actualDistance);
		
	}
	
	@Test
	public void addPlayerTest(){
		
		//setting the expected Player
		Player expectedPlayer = avi;
		
		//adding the 'avatar' to a specific place on the 'board'
		grid.add(avi, new Pos(0,0));
		
		//setting the actual player equal to the item at the specified position
		Player actualPlayer = grid.getItemOnBoard(0, 0);
		
		//asserting that the expectedPlayer equals the player added at that position
		assertEquals(expectedPlayer, actualPlayer);
	}
	
	@Test
	public void getItemOnBoardTest(){
		
		//creating a new position
		Pos position = new Pos(5,5);
		//setting player to new position on board
		grid.setNewPosOnBoard(dabi, position);
		
		//setting the expected Player
		Player expectedplayer = dabi;
		//setting the actual player
		Player actualplayer = grid.getItemOnBoard(position.getXpos(), position.getYpos());
		
		//asserting that the player gotten is the player set on that position
		assertEquals(expectedplayer, actualplayer);
	}
	
	@Test
	public void setNewPositionOnBoardTest(){
		
		//creating new position
		Pos position = new Pos(5,2);
		
		//setting player to new position on board
		grid.setNewPosOnBoard(ani, position);
		
		//asserting that this position is not null
		assertNotNull(grid.getItemOnBoard(position.getXpos(), position.getYpos()));
		
		
		//Could also use method commented below to check if the actual player is in this
		//position rather than just not null - both pass testing.
		//Player expectedPlayer = ani;
		//Player actualPlayer = grid.getItemOnBoard(position.getXpos(), position.getYpos());
		//assertEquals(expectedPlayer, actualPlayer);
	}
	
	@Test
	public void CheckPreviousPositionsSetToNullTest(){
		
		//ani's original postion = (1,2)
		Pos position = new Pos(5,2);
		
		//setting player to new position on board
		grid.setNewPosOnBoard(ani, position);
		
		//asserting that the previous position held by the Avatar is now null
		assertNull(grid.getItemOnBoard(1, 2));
	}
	
	@Test
	//to test if an exception is thrown if appropriate behaviour has not being added
	public void exceptionTest(){
		boolean exceptionThrown = false;
		
		//try catch block
		try {
			//using the strategy to get a new position for the player
			//similar to how chess dynamics and piece moving, where the strategy decides how a piece can move
			aniStrategy.findAPlace(grid, ani);
		} catch (NoSuchCommandException e) {
			System.out.println(e.getMessage());
			//setting boolean to true if exception is thrown
			exceptionThrown = true;
		}
		//asserting if the boolean is true
		assertTrue(exceptionThrown);
		
	}
}
