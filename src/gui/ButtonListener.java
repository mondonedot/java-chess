package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener class for buttons used to store the x, y coordinates of the button on the board
 */
public class ButtonListener implements ActionListener{
	
	private int x;
	private int y;
	
	//the view and controller associated with this button
	public GameView view;
	public GameController controller;
	
	/**
	 * Constructor used to initialize the listener for a button at the specifed coordinates
	 * @param x the x coordinate of this button
	 * @param y the y coordinate of this button
	 */
	public ButtonListener(GameView view, GameController controller, int x, int y) {
		this.view = view;
		this.controller = controller;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Implementation of the action to be performed when a button is pressed.
	 * Sets the coordinates for the piece to be moved and its destination depending on 
	 * where the user has clicked.
	 */
	public void actionPerformed(ActionEvent e) {		
		//check whether this is the first or second click in the player's move
		if(GameController.buttonPressCount == 0) {
			GameController.sourceX = this.x;
			GameController.sourceY = this.y;
			GameController.buttonPressCount++;
			view.boardTiles[this.x][this.y].setBackground(Color.orange);
		} else if(GameController.buttonPressCount == 1) {
			GameController.destX = this.x;
			GameController.destY = this.y;
			GameController.buttonPressCount++;
			//reset the color of where the piece was
			if ((GameController.sourceX % 2 == 0 && GameController.sourceY % 2 == 0) || 
				(GameController.sourceX % 2 == 1 && GameController.sourceY % 2 == 1)) {
    			view.boardTiles[GameController.sourceX][GameController.sourceY].setBackground(new Color(210, 190, 155));
    		} else {
    			view.boardTiles[GameController.sourceX][GameController.sourceY].setBackground(new Color(110, 75, 15));
    		}
		}
	}
}