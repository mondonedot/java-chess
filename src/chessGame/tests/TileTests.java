package chessGame.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessGame.play.Piece;
import chessGame.play.Player;
import chessGame.play.Tile;
import chessGame.play.chessPieces.*;

public class TileTests {
	
	/**
	 * This test checks if the constructors initialize the Tile correctly
	 */
	@Test
	public void constructTile() {
		Tile t = new Tile();
		assertNull(t.getPiece());
		
		Piece p = new King(null, null, 0, 0);
		t = new Tile(p);
		assertNotNull(t.getPiece());
	}
	
	/**
	 * Tests if the copyTile functionality
	 */
	@Test
	public void copyTileTest() {
		
		Player white = new Player();
		//Player black = new Player();
		
		Piece p1 = new Pawn(white, true, true, 0, 1);
		
		//make two tiles with one being the copy of the other
		Tile t1 = new Tile(p1);
		Tile t2 = Tile.copyTile(t1, t1.getPiece().getOwner());
		
		p1 = t1.getPiece();
		Piece p2 = t2.getPiece();

		//make sure they are not the same reference
		assertFalse(p1.equals(p2));
		assertEquals(p1.getX(), p2.getX());
		assertEquals(p1.getY(), p2.getY());
	}
	
	/**
	 * Tests if the correct piece is returned from the tile
	 */
	@Test
	public void getPiece() {
		Piece p = new King(null, null, 0, 0);
		Tile t = new Tile(p);
		assertSame(p, t.getPiece());
	}
	
	
	/**
	 * Tests if a tile returns true when it has no piece on it
	 */
	@Test
	public void isEmpty() {
		Tile t = new Tile();
		assertTrue(t.isEmpty());
		
		t.addPiece(new King(null, null, 0, 0));
		assertFalse(t.isEmpty());
	}

	/**
	 * Tests adding or removing a piece
	 */
	@Test
	public void addRemovePiece() {
		Tile t = new Tile();
		Piece p = new King(null, null, 0, 0);
		
		t.addPiece(p);
		assertFalse(t.isEmpty());
		assertSame(p, t.getPiece());
		
		t.removePiece();
		assertTrue(t.isEmpty());
	}
}
