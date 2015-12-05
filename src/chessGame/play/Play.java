package chessGame.play;

import java.util.concurrent.TimeUnit;

import gui.GameController;
import gui.GameView;

/**
 * Model of the chess game.
 * Contains the main game loop and gets information from the GUI through the controller class
 */
public class Play {
	
	public static int whitePlayerWins = 0;
	public static int blackPlayerWins = 0;
	
	public static void main (String[] args) throws InterruptedException {
		
		Game game = new Game();
		//white starts the game
		int currentPlayer = 1;
		
		//create the game controller
		GameController controller = new GameController();
		
		//create the GUI
		GameView view = new GameView(controller, game);
		view.updateView(game);
		
		boolean canUndo = false;

		//game loop start
		while (true) {
		
			view.setPlayerTurnMessage(currentPlayer);
			
			//check for end game conditions
			if (Game.isCheck(game, currentPlayer) || controller.resign) {
			
				//check if the player is in check mate and end the game if so
				if (game.isCheckmate(currentPlayer) || controller.resign) {
					//the winner is the player who was not in check mate
					if(currentPlayer == 1) {
						blackPlayerWins++;
						view.setCheckmateMessage(currentPlayer);
					} else {
						whitePlayerWins++;
						view.setCheckmateMessage(currentPlayer);
					}
					
					//game is over so wait until the player's restart the game
					while(!controller.newGame) {TimeUnit.MILLISECONDS.sleep(50);}
					game = new Game();
					view.updateView(game);
					controller.newGame = false;
					controller.resign = false;
					canUndo = false;
					continue;
				}
				
				view.setCheckMessage(currentPlayer);
			}
	
			Piece currPiece = null;
			int destX = -1;
			int destY = -1;
			
			//booleans used to set the invalid message at the appropriate time
			boolean isInvalidMoveMessageSet = false;
			boolean isNotFirstTry = false;
			
			//loop until the player makes a valid move
			while (true) {				
				
				//wait until the controller is ready and has received two values or the player has made a menu action
				while (!controller.isReady() && !controller.resign && !controller.newGame && !controller.undo) {
					TimeUnit.MILLISECONDS.sleep(50);
				}
				
				//Harvest the information from the controller and attempt to make that move
				currPiece = game.getPiece(controller.getSourceX(), controller.getSourceY());
				destX = controller.getDestX();
				destY = controller.getDestY();
				controller.resetController();
				isNotFirstTry = true;
				
				
				if(game.makeMove(currentPlayer, currPiece, destX, destY)) {
					canUndo = true;
					break;
				} else if (!isInvalidMoveMessageSet && isNotFirstTry) {
					view.setMessageInvalidMove();
					isInvalidMoveMessageSet = true;
				}
				
				//check if the player is resigning and restart the game if they are and update the score
				if (controller.resign)
					break;
				
				//check if the players want a new game from scratch with the scores reset
				if (controller.newGame) {
					game = new Game();
					whitePlayerWins = 0;
					blackPlayerWins = 0;
					canUndo = false;
					controller.newGame = false;
					break;
				}
				
				//check if the player wants to undo their move
				if (controller.undo && canUndo) {
					canUndo = false;
					controller.undo = false;
					game.undo();
					//view.updateView(game);
					currentPlayer *= 1; //change the player turn back to the other player
					break;
				}
			}
			
			//only continue updating the game if the player has not resigned
			if(!controller.resign) {
				//update the GUI view
				view.updateView(game);
			
				//reset the controller so that we don't try and do the same move again
				controller.resetController();

				currentPlayer *= -1;
			}
		} //end game loop
	}
}
