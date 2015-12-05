package gui;

/**
 * Controller class that communicates between the view and the model
 * Allows the model to 
 */
public class GameController {
	
	//coordinates associated with the current move
	public static int sourceX;
	public static int sourceY;
	public static int destX;
	public static int destY;
	
	//used to determine once the player has clicked on two buttons corresponding to the piece to be moved and the destination
	public static int buttonPressCount = 0;
	
	public boolean newGame = false;
	public boolean resign = false;
	public boolean undo = false;
	
	/**
	 * Default constructor initializes everything to invalid coordinates
	 */
	public GameController() {
		sourceX = -1;
		sourceY = -1;
		destX = -1;
		destY = -1;
	}
	
	/**
	 * Determines if the controller has received move values from the view
	 * @return true if the controller has two move values from the view
	 */
	public boolean isReady() {
		if(buttonPressCount == 2)
			return true;
		else
			return false;
	}
	
	/**
	 * Resets the controller to wait for input from the view
	 */
	public void resetController() {
		sourceX = -1;
		sourceY = -1;
		destX = -1;
		destY = -1;
		buttonPressCount = 0;
	}
	
	/**
	 * sourceX getter method
	 * @return sourceX
	 */
	public int getSourceX() {
		return sourceX;
	}
	
	/**
	 * sourceY getter method
	 * @return sourceY
	 */
	public int getSourceY() {
		return sourceY;
	}
	
	/**
	 * destX getter method
	 * @return destX
	 */
	public int getDestX() {
		return destX;
	}
	
	/**
	 * destY getter method
	 * @return destY
	 */
	public int getDestY() {
		return destY;
	}
}
