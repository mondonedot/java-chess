package chessGame.play.chessPieces;

import chessGame.play.Piece;
import chessGame.play.Player;
import chessGame.play.Tile;

public class Knight extends Piece {
	
	/**
	 * Primary constructor to create the piece at the specified location
	 * @param owner TODO
	 * @param x, y the coordinates the piece will be initialized to
	 * @param owner the player who the piece belongs to
	 */
	public Knight(Player owner, int x, int y) {
		this.owner = owner;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Check if a move to the given coordinates is valid for this specific piece
	 * Has to check if the move is within the board and if it is a proper move this 
	 * piece can make.	 
	 * Does not check if there is a piece on the destination tile as that should be checked prior
	 * to calling this function in the Game classes isValidMove function.
	 * @param destX, destY destination coordinates for the piece
	 * @param board the current board used to be able to check other pieces locations
	 * @return true if the move is valid
	 */
	public boolean isValidMove(Tile [][] board, int destX, int destY) {
				
		//check if the move is one of the 8 moves the knight can make
		if ( (destX == this.x + 1 && destY == this.y + 2) || 
			(destX == this.x + 2 && destY == this.y + 1) || 
			(destX == this.x + 2 && destY == this.y - 1) || 
			(destX == this.x + 1 && destY == this.y - 2) ||
			(destX == this.x - 1 && destY == this.y - 2) ||
			(destX == this.x - 2 && destY == this.y - 1) ||
			(destX == this.x - 2 && destY == this.y + 1) ||
			(destX == this.x - 1 && destY == this.y + 2) ) {
			return true;
		}
		return false;
	}
}
