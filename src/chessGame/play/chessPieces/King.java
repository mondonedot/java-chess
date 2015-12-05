package chessGame.play.chessPieces;

import chessGame.play.Piece;
import chessGame.play.Player;
import chessGame.play.Tile;

public class King extends Piece {
	
	/* Opponent to keep track of dangers to the king, used to see if the king is in check*/
	public Player opponent;
	
	/**
	 * Primary constructor to create the piece at the specified location
	 * @param owner TODO
	 * @param x, y the coordinates the piece will be initialized to
	 * @param owner the player who the piece belongs to
	 */
	public King(Player owner, Player opponent, int x, int y) {
		this.owner = owner;
		this.opponent = opponent;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Check if a move to the given coordinates is valid for this specific piece
	 * Has to check if the move is within the board and if it is a proper move this 
	 * piece can make.
	 * @param destX, destY destination coordinates for the piece
	 * @param board the current board used to be able to check other pieces locations
	 * @return true if the move is valid
	 */
	public boolean isValidMove(Tile [][] board, int destX, int destY) {
				
		/* check if the move is one of the 8 moves the king can make
		 * in addition check if the king goes into check */
		if  (destX == this.x && destY == this.y - 1) {	
			return true;
		}
		if  (destX == this.x + 1 && destY == this.y - 1) {
			return true;
		}
		if  (destX == this.x + 1 && destY == this.y) {		
			return true;
		}
		if  (destX == this.x + 1 && destY == this.y + 1) {
			return true;
		}
		if  (destX == this.x && destY == this.y + 1) {
			return true;
		}
		if  (destX == this.x - 1 && destY == this.y + 1) {
			return true;
		}
		if  (destX == this.x - 1 && destY == this.y) {
			return true;
		}
		if  (destX == this.x - 1 && destY == this.y - 1) {
			return true;
		}
				
			return false;
	}
}
