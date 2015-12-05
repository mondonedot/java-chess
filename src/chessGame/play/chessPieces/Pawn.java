package chessGame.play.chessPieces;

import chessGame.play.Piece;
import chessGame.play.Player;
import chessGame.play.Tile;
//import chessGame.play.Game;

public class Pawn extends Piece {
	
	/* Used to determine which way the pawn can move */
	/* If top is true, the pawn will move in the positive y direction */
	public boolean top;
	
	/* Used to determine if the pawn can still move two spaces*/
	public boolean isFirstMove;
	
	/**
	 * Primary constructor to create the piece at the specified location
	 * @param owner the player who the piece belongs to
	 * @param isFirstMove set to true if the pawn will be placed on the starting line
	 * @param x, y the coordinates the piece will be initialized to
	 */
	public Pawn(Player owner, boolean top, boolean isFirstMove, int x, int y) {
		this.owner = owner;
		this.isFirstMove = isFirstMove;
		this.top = top;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Check if a move to the given coordinates is valid for this specific piece
	 * Does not check if there is a piece on the destination tile as that should be checked prior
	 * to calling this function in the Game classes isValidMove function.
	 * @param destX, destY destination coordinates for the piece
	 * @return true if the move is valid
	 */
	public boolean isValidMove(Tile[][] board, int destX, int destY) {
		
		if(top) {
			if(destX == this.x && destY == this.y + 1) {
				if(!board[destX][destY].isEmpty())
					return false;
				else 
					return true;
			}
			if(isFirstMove) {
				if(destX == this.x && destY == this.y + 2) {
					//make sure the destination is empty and the path is clear
					if(!board[destX][destY].isEmpty() || !board[destX][this.y + 1].isEmpty())
						return false;
					else 
						return true;
				}
			}
			if (destX == this.x + 1 && destY == this.y + 1) {
				if(!board[destX][destY].isEmpty() && 
						!board[destX][destY].getPiece().getOwner().equals(this.owner))
					return true;
				else 
					return false;
			}
			if (destX == this.x - 1 && destY == this.y + 1) {
				if(!board[destX][destY].isEmpty() && 
						!board[destX][destY].getPiece().getOwner().equals(this.owner))
					return true;
				else 
					return false;
			}
		} else {
			if(destX == this.x && destY == this.y - 1) {
				if(!board[destX][destY].isEmpty())
					return false;
				else 
					return true;
			}
			if(isFirstMove) {
				//make sure the destination is empty and the path is clear
				if(destX == this.x && destY == this.y - 2) {
					if(!board[destX][destY].isEmpty() || !board[destX][this.y - 1].isEmpty())
						return false;
					else 
						return true;
				}
			}
			if (destX == this.x + 1 && destY == this.y - 1) {
				if(!board[destX][destY].isEmpty() && 
						!board[destX][destY].getPiece().getOwner().equals(this.owner))
					return true;
				else 
					return false;
			}
			if (destX == this.x - 1 && destY == this.y - 1) {
				if(!board[destX][destY].isEmpty() && 
						!board[destX][destY].getPiece().getOwner().equals(this.owner))
					return true;
				else 
					return false;
			}
		}
		
		return false;
	}
}
