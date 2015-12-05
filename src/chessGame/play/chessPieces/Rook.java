package chessGame.play.chessPieces;

import chessGame.play.Piece;
import chessGame.play.Player;
import chessGame.play.Tile;

public class Rook extends Piece {

	/**
	 * Primary constructor to create the piece at the specified location
	 * @param owner TODO
	 * @param x, y the coordinates the piece will be initialized to
	 * @param owner the player who the piece belongs to
	 */
	public Rook(Player owner, int x, int y) {
		this.owner = owner;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Check if a move to the given coordinates is valid for this specific piece
	 * Has to check if the move is within the board, if it is a proper move this 
	 * piece can make and if the path to the destination is not blocked by other pieces. 
	 * Does not check if there is a piece on the destination tile as that should be checked prior
	 * to calling this function in the Game classes isValidMove function.
	 * @param destX, destY destination coordinates for the piece
	 * @param board the current board used to be able to check other pieces locations
	 * @return true if the move is valid
	 */
	public boolean isValidMove(Tile[][] board, int destX, int destY) {
		
		//holds the differences in distances as a positive value
		int xDistance = Math.abs(destX - this.x);
		int yDistance = Math.abs(destY - this.y);
				
		if (this.x < destX && this.y == destY) { //right
			/* Loop through the path and check if there are any pieces in the way */
			for (int i = 1; i < xDistance; i++) {
				if (!board[this.x + i][this.y].isEmpty())
					return false;
			}
			return true;
		} else if (this.x > destX && this.y == destY) { //left
			for (int i = 1; i < xDistance; i++) {
				if (!board[this.x - i][this.y].isEmpty())
					return false;
			}
			return true;
		} else if (this.x == destX && this.y < destY) { //down
			for (int i = 1; i < yDistance; i++) {
				if (!board[this.x][this.y + i].isEmpty())
					return false;
			}
			return true;
		} else if (this.x == destX && this.y > destY) { //up
			for (int i = 1; i < yDistance; i++) {
				if (!board[this.x][this.y - i].isEmpty())
					return false;
			}
			return true;
		} else
			return false;
	}
}
