import java.util.HashMap;
//SecoondaryStrategy implements Strategy interface
public class SecondaryStrategy implements Strategy {
	//creating private hashmap behaviours 
	private HashMap<String, Behaviour> behaviours = new HashMap<String, Behaviour>();
	
	@Override
	public void addBehaviour(String command, Behaviour behaviour) {
		//adding behaviours to the hashmap with key
		behaviours.put(command.toLowerCase(), behaviour);
	}

	@Override
	//findAPlace method to find random position within the 'board' for player
	public void findAPlace(Grid x, Player b) throws NoSuchCommandException { //throws OccupiedPlaceException{
		//Creating a random position with getRandXY method
		Pos randompos = x.getRandXY();
		//using this randompos to check if there is no avatar already at this position
		if(x.getItemOnBoard(randompos.getXpos(), randompos.getYpos()) == null){
			//if not calculate the distance from original position to the new position
			int distance = x.calculateDistance(b, randompos);
			
			//decision making
			if((distance) <= 1){
				///checking if the hasmap contains key sneak
				if (behaviours.containsKey("sneak")){
					//print to console for formatting
					System.out.print(b.getClass().getSimpleName() + " " + b.getName() + ": ");
					//calling appropriate method if it does
					behaviours.get("sneak").doIt();
				}
				else {
					throw new NoSuchCommandException("sneak is not a valid command for " + b.getClass().getSimpleName() +" " + b.getName());
				}
			}
			else if((distance) <= 3){
				//checking if the hasmap contains key walk
				if (behaviours.containsKey("walk")){
					//print to console for formatting
					System.out.print(b.getClass().getSimpleName() + " " + b.getName() + ": ");
					//calling appropriate method if it does
					behaviours.get("walk").doIt();
				}
				else {
					//else throw exception
					throw new NoSuchCommandException("walk is not a valid command for " + b.getClass().getSimpleName() + " " + b.getName());
				}
				
			}
			else if((distance) <= 6){
				//checking if the hasmap contains key jump
				if (behaviours.containsKey("jump")){
					//print to console for formatting
					System.out.print(b.getClass().getSimpleName() + " " + b.getName() + ": ");
					//calling appropriate method if it does
					behaviours.get("jump").doIt();
				}
				else {
					//else throw exception
					throw new NoSuchCommandException("jump is not a valid command for " + b.getClass().getSimpleName() + " " + b.getName());
				}
				
			}
			else if((distance) >= 7){
				//checking if the hasmap contains key dig
				if (behaviours.containsKey("dig")){
					//print to console for formatting
					System.out.print(b.getClass().getSimpleName() + " " + b.getName() + ": ");
					//calling appropriate method if it does
					behaviours.get("dig").doIt();
				}
				else {
					//else throw exception
					throw new NoSuchCommandException("dig is not a valid command for " + b.getClass().getSimpleName() + " " + b.getName());
				}
				
			}
			//output to console
			System.out.println("I found a new Place");
			//setting to new position generated
			x.setNewPosOnBoard(b, randompos);
		}
		else{
			System.out.println("This position is taken");
			//handling the exception that the position is taken by doing a recursive call
			//to the method, and therefore allowing the 'game' to continue
			findAPlace(x, b); 				
		
		}
	}
}