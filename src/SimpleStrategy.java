import java.util.HashMap;

//simple strategy implements Strategy interface
public class SimpleStrategy implements Strategy {
	
	//creating private hashmap behaviours 
	private HashMap<String, Behaviour> behaviours = new HashMap<String, Behaviour>();
	
	@Override
	public void addBehaviour(String command, Behaviour behaviour) {
		//adding behaviours to the hashmap with key
		behaviours.put(command.toLowerCase(), behaviour);
		
	}

	@Override
	//findAPlace method to find random position within the 'board' for player
	public void findAPlace(Grid x, Player b) throws NoSuchCommandException {
		
		//Creating a random position with getRandXY method
		Pos randompos = x.getRandXY();
		
		//using this randompos to check if there is no avatar already at this position
		if(x.getItemOnBoard(randompos.getXpos(), randompos.getYpos()) == null){
			//if not calculate the distance from original position to the new position
			int distance = x.calculateDistance(b, randompos);
			
			//decision making
			if((distance) < 5){
				//checking if the hasmap contains key run
				if (behaviours.containsKey("run")){
					//print to console for formatting
					System.out.print(b.getClass().getSimpleName() + " " + b.getName() + ": ");
					//calling appropriate method if it does
					behaviours.get("run").doIt();
				}
				else {
					//else throw exception
					throw new NoSuchCommandException("run is not a valid command for " + b.getClass().getSimpleName() +" " + b.getName());
				}
			}
			//for distances greater than or equal to 5
			else if((distance) >= 5){
				//checking if the hasmap contains key fly
				if (behaviours.containsKey("fly")){
					//print to console for formatting
					System.out.print(b.getClass().getSimpleName() + " " + b.getName() + ": ");
					//calling appropriate method if it does
					behaviours.get("fly").doIt();
				}
				else {					
					//else throw exception
					throw new NoSuchCommandException("fly is not a valid command for " + b.getClass().getSimpleName() + " " + b.getName());
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
