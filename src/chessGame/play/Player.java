package chessGame.play;

import java.util.ArrayList;
import java.util.List;

import chessGame.play.chessPieces.King;

public class Player {
	
	public String name;
	
	/* Holds all the pieces that are still alive for the player*/
	public List<Piece> pieces;
	
	/**
	 * Default constructor that does not set the name
	 */
	public Player() {
		pieces = new ArrayList<Piece>();
	}
	
	/**
	 * Constructor to make an empty list and initialize the name of the player
	 * @param name name of the player
	 */
	public Player(String name) {
		this.name = name;
		pieces = new ArrayList<Piece>();
	}
	
	/**
	 * Adds a piece to a player's set
	 * @param p the piece that is being added to the player's list
	 */
	public void addPiece(Piece p) {
		pieces.add(p);
	}
	
	/**
	 * Removes a piece from a player's set
	 * @param p the piece that is to be removed from the list
	 */
	public void removePiece(Piece p) {
		this.pieces.remove(pieces.indexOf(p));
	}
	
	/**
	 * Determines if a given piece belongs to this player.
	 */
	public boolean isPieceOwner(Piece p) {
		if (this.pieces.indexOf(p) == -1)
			return false;
		else
			return true;
	}
	
	/**
	 * Finds the king in the array list and returns it.
	 */
	public Piece getKing() {
		for(int i = 0; i < this.pieces.size(); i++) {
			Piece p = this.pieces.get(i);
			if (p instanceof King)
				return p;
		}
		return null;
	}
}
