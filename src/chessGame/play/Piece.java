package chessGame.play;

import chessGame.play.chessPieces.*;

public abstract class Piece {
	
	/* Current coordinates of the given piece */
	protected int x;
	protected int y;
	
	/* The player who the piece belongs to */
	protected Player owner;
		
	/**
	 * This will update the x and y coordinates of the piece
	 * @param newX, newY the new coordinates for the piece to be set to
	 */
	public void updateCoordinates(int newX, int newY) {
		this.x = newX;
		this.y = newY;
	}
	
	/**
	 * X coordinate getter function
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Y coordinate getter function
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Returns the owner of the piece
	 */
	public Player getOwner() {
		return this.owner;
	}
	
	/**
	 * Makes a copy of the given piece
	 * @param owner the owner that this piece will belong to
	 * @return a deep copy of the passed in piece
	 */
	public static Piece copyPiece(Piece p, Player owner) {
		Piece pieceCopy = null;
		int x = p.getX();
		int y = p.getY();
		
		//figure out which kind of piece it is and make a copy of it
		if (p instanceof Pawn) {
			pieceCopy = new Pawn(owner, ((Pawn)(p)).top, ((Pawn)(p)).isFirstMove, x, y);
		} else if (p instanceof Rook) {
			pieceCopy = new Rook(owner, x, y);
		} else if (p instanceof Knight) {
			pieceCopy = new Knight(owner, x, y);
		} else if (p instanceof Bishop) {
			pieceCopy = new Bishop(owner, x, y);
		} else if (p instanceof Queen) {
			pieceCopy = new Queen(owner, x, y);
		} else if (p instanceof King) {
			pieceCopy = new King(owner, ((King)(p)).opponent, x, y);
		}
		
		return pieceCopy;
	}
	
	/**
	 * Check if a move to the given coordinates is valid for this specific piece
	 * Implemented specific to each kind of piece
	 * @param board TODO
	 * @param destX, destY destination coordinates for the piece
	 * @return true if the move is valid
	 */
	public abstract boolean isValidMove(Tile[][] board, int destX, int destY);
}
