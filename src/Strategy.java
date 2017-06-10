//creating Strategy interface
public interface Strategy {
	
	//neccessary methods
	public void addBehaviour(String command, Behaviour behaviour);
	
	public void findAPlace(Grid grid, Player player) throws NoSuchCommandException;

}
