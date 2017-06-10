
public class StrategyTester {

	public static void main(String[] args) {
		
		//creating a initialising players
		Player ani = new Avatar("ani");
		Player luke = new Avatar("luke");
		
		//creating a 10 by 10 grid/'board'
		Grid grid = new Grid(10);
		//adding 'avatars' to the 'board' with they're initial positions
		grid.add(ani, new Pos(0,1));
		grid.add(luke, new Pos(1,1));
		//creating new Strategy
		Strategy avatStrategy = new SimpleStrategy();
		//adding behaviours to the Strategy hashmap
		avatStrategy.addBehaviour("fly", new Fly());
		avatStrategy.addBehaviour("run", new Run());
		
		//for loop to loop through finding a new place for each avatar using
		//avatStrategy
		for(int i = 0; i<10; i++){
			//try catch block
			try {
				//using the strategy to get a new position for the player
				//similar to how chess dynamics and piece moving, where the strategy decides how a piece can move
				avatStrategy.findAPlace(grid, ani);
				avatStrategy.findAPlace(grid, luke);
			} catch (NoSuchCommandException e) {
				System.out.println(e.getMessage());
		}
			//formatting
		System.out.println("\n");
		}
		//creating a initialising players
		Player avi = new Avatar("avi");
		Player dabi = new Avatar("dabi");
		//adding them to the 'board'
		grid.add(avi, new Pos(1,0));
		grid.add(dabi, new Pos(1,2));
		//creating new Strategy
		Strategy secondaryStrategy = new SecondaryStrategy();
		//adding new Behaviours
		secondaryStrategy.addBehaviour("sneak", new Sneak());
		secondaryStrategy.addBehaviour("walk", new Walk());
		secondaryStrategy.addBehaviour("jump", new Jump());
		secondaryStrategy.addBehaviour("dig", new Dig());
		
		//for loop to loop through finding a new place for each avatar
		for(int i = 0; i<10; i++){
			//try catch block
			try {
				secondaryStrategy.findAPlace(grid, avi);
				secondaryStrategy.findAPlace(grid, dabi);
				secondaryStrategy.findAPlace(grid, ani);
			} catch (NoSuchCommandException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("\n");
			}
	}
}
