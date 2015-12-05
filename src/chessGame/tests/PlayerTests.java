package chessGame.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessGame.play.Piece;
import chessGame.play.Player;
import chessGame.play.chessPieces.*;

public class PlayerTests {

	/**
	 * Constructor test to see if an empty list is created and name is properly assigned
	 */
	@Test
	public void constructor() {
		Player Tim = new Player("Tim");
		assertEquals("Tim", Tim.name);
		assertEquals(0, Tim.pieces.size());
	}
	
	/**
	 * Tests if add and remove functionality work for the list of pieces
	 */
	@Test
	public void addPieces() {
		Player Tim = new Player("Tim");
		
		Piece p1 = new Knight(null,0, 0);
		Piece p2 = new Knight(null,0, 1);
		Piece p3 = new Knight(null,0, 2);
		Tim.addPiece(p1);
		Tim.addPiece(p2);
		Tim.addPiece(p3);
		
		assertEquals(3, Tim.pieces.size());
		assertNotEquals(-1, Tim.pieces.indexOf(p1));
		assertNotEquals(-1, Tim.pieces.indexOf(p2));
		
		Tim.removePiece(p1);
		
		assertEquals(2, Tim.pieces.size());
		assertEquals(-1, Tim.pieces.indexOf(p1));
	}

	/**
	 * Tests function that checks if a player owns a certain piece
	 */
	@Test
	public void isPieceOwner() {
		Player Tim = new Player("Tim");
		Player Bob = new Player("Bob");
		
		Piece p1 = new Knight(null,0, 0);
		Piece p2 = new Knight(null,0, 1);
		Piece p3 = new Knight(null,0, 2);
		Tim.addPiece(p1);
		Bob.addPiece(p2);
		Tim.addPiece(p3);
		
		assertTrue(Tim.isPieceOwner(p1));
		assertFalse(Tim.isPieceOwner(p2));
		assertTrue(Bob.isPieceOwner(p2));
		assertFalse(Bob.isPieceOwner(p3));
	}
	
	/**
	 * Tests the getKing function
	 */
	@Test
	public void findKing() {
Player Tim = new Player("Tim");
		
		Piece p1 = new Knight(null,0, 0);
		Piece p2 = new King(null, null, 0, 1);
		Piece p3 = new Knight(null,0, 2);
		Tim.addPiece(p1);
		Tim.addPiece(p2);
		Tim.addPiece(p3);
		
		assertEquals(p2, Tim.getKing());
	}
}
