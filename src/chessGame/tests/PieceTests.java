package chessGame.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessGame.play.Game;
import chessGame.play.Piece;
import chessGame.play.Player;
import chessGame.play.chessPieces.*;

/**
 * Suite of tests that test the functionality of the function is the abstract 
 * Piece class and all of its subclasses
 * NOTE for all of the isValidMove tests in each of the subclasses,
 * it is not tested if there is a piece on the destination tile
 * because that is checked in the Game.java isValidMove function
 */
public class PieceTests {

	@Test
	public void getterTest() {
		Player Tim = new Player("Tim");
		Piece p = new Knight(Tim, 0, 5);
		assertEquals(0, p.getX());
		assertEquals(5, p.getY());
		assertEquals(Tim, p.getOwner());
	}
	
	@Test
	public void updateCoordinatesTest() {
		Piece p = new Knight(null, 0, 5);
		p.updateCoordinates(3, 4);
		
		assertEquals(3, p.getX());
		assertEquals(4, p.getY());
	}
	
	@Test
	public void copyPieceTest() {
		Player white = new Player();
		//Player black = new Player();
		
		Piece p1 = new Pawn(white, true, true, 0, 1);
		Piece p2 = Piece.copyPiece(p1, null);
		
		//make sure they are not the same reference
		assertFalse(p1.equals(p2));
		assertEquals(p1.getX(), p2.getX());
		assertEquals(p1.getY(), p2.getY());
	}
	
	@Test
	public void PawnTest() {
		System.out.println("Pawn Tests\n");
		
		Game testGame = new Game();
		
		Piece testPiece = testGame.board[1][1].getPiece();
		//test the two moves a pawn can make at the beginning
		assertTrue(testPiece.isValidMove(testGame.board, 1, 2));
		assertTrue(testPiece.isValidMove(testGame.board, 1, 3));
		
		assertFalse(testPiece.isValidMove(testGame.board, 1, 4));
		//test the diagonal moves when there are no pieces there
		assertFalse(testPiece.isValidMove(testGame.board, 2, 2));
		assertFalse(testPiece.isValidMove(testGame.board, 0, 2));
		//test other random locations to make sure the pawn cannot move there
		for(int i = 0; i < 8; i++) {
			assertFalse(testPiece.isValidMove(testGame.board,  i, 4));
		}

		//test pawn that can capture enemy pieces
		Piece p = new Pawn(testGame.white, true, false, 1, 5);
		testGame.addPiece(p, testGame.white, 1, 5);
		assertTrue(p.isValidMove(testGame.board, 0, 6));
		assertTrue(p.isValidMove(testGame.board, 2, 6));
		//the pawn cannot move forward if there is a piece in front of it
		assertFalse(p.isValidMove(testGame.board, 1, 6));

		
		testGame.printBoard();
	}
	
	@Test
	public void RookTest() {
		System.out.println("Rook Tests\n");
		
		Game testGame = new Game();
		
		Piece testPiece = testGame.board[0][0].getPiece();
		//make sure the rook cannot move anywhere in the start of a game
		assertFalse(testPiece.isValidMove(testGame.board, 0, 7));
		assertFalse(testPiece.isValidMove(testGame.board, 2, 0));
		assertFalse(testPiece.isValidMove(testGame.board, 0, 2));
		assertFalse(testPiece.isValidMove(testGame.board, 1, 4));
		assertFalse(testPiece.isValidMove(testGame.board, 5, 5));
		
		//tests random moves the rook can make if placed in the center of the board
		testPiece = new Rook(testGame.white, 3, 3);
		testGame.addPiece(testPiece, testGame.white, 3, 3);
		assertTrue(testPiece.isValidMove(testGame.board, 7, 3));
		assertTrue(testPiece.isValidMove(testGame.board, 4, 3));
		assertTrue(testPiece.isValidMove(testGame.board, 0, 3));
		assertTrue(testPiece.isValidMove(testGame.board, 3, 2));
		assertTrue(testPiece.isValidMove(testGame.board, 3, 4));
		assertFalse(testPiece.isValidMove(testGame.board, 2, 2));
		assertFalse(testPiece.isValidMove(testGame.board, 3, 7));
		assertFalse(testPiece.isValidMove(testGame.board, 7, 5));

		testGame.printBoard();
	}
	
	@Test
	public void KnightTest() {
		System.out.println("Knight Tests\n");
		
		Game testGame = new Game();
		
		//test moves from a knight in its starting position
		Piece testPiece = testGame.board[1][0].getPiece();
		assertTrue(testPiece.isValidMove(testGame.board, 0, 2));
		assertTrue(testPiece.isValidMove(testGame.board, 2, 2));

		//test random moves if a knight is in the center of the board
		testPiece = new Knight(testGame.white, 3, 3);
		testGame.addPiece(testPiece, testGame.white, 3, 3);
		assertTrue(testPiece.isValidMove(testGame.board, 5, 2));
		assertTrue(testPiece.isValidMove(testGame.board, 5, 4));
		assertTrue(testPiece.isValidMove(testGame.board, 2, 5));
		assertTrue(testPiece.isValidMove(testGame.board, 4, 5));
		assertFalse(testPiece.isValidMove(testGame.board, 3, 5));
		assertFalse(testPiece.isValidMove(testGame.board, 3, 2));
		assertFalse(testPiece.isValidMove(testGame.board, 6, 5));

		testGame.printBoard();
	}
	
	@Test
	public void BishopTest() {
		System.out.println("Bishop Tests\n");

		Game testGame = new Game();
		
		Piece testPiece = testGame.board[2][0].getPiece();
		assertFalse(testPiece.isValidMove(testGame.board, 4, 2));
		assertFalse(testPiece.isValidMove(testGame.board, 2, 3));

		testPiece = new Bishop(testGame.white, 4, 4);
		testGame.addPiece(testPiece, testGame.white, 4, 4);
		assertTrue(testPiece.isValidMove(testGame.board, 5, 3));
		assertTrue(testPiece.isValidMove(testGame.board, 2, 2));
		assertTrue(testPiece.isValidMove(testGame.board, 3, 5));
		assertTrue(testPiece.isValidMove(testGame.board, 5, 5));
		assertFalse(testPiece.isValidMove(testGame.board, 4, 5));
		assertFalse(testPiece.isValidMove(testGame.board, 0, 0));
		assertFalse(testPiece.isValidMove(testGame.board, 6, 3));
		
		testGame.printBoard();
	}
	
	@Test
	public void QueenTest() {
		System.out.println("Queen Tests\n");

		Game testGame = new Game();
		
		//test moves from a queen in its starting position
		Piece testPiece = testGame.board[4][0].getPiece();
		assertFalse(testPiece.isValidMove(testGame.board, 4, 2));
		assertFalse(testPiece.isValidMove(testGame.board, 1, 3));

		testPiece = new Queen(testGame.white, 4, 4);
		testGame.addPiece(testPiece, testGame.white, 4, 4);
		assertTrue(testPiece.isValidMove(testGame.board, 4, 2));
		assertTrue(testPiece.isValidMove(testGame.board, 4, 5));
		assertTrue(testPiece.isValidMove(testGame.board, 0, 4));
		assertTrue(testPiece.isValidMove(testGame.board, 5, 4));
		assertTrue(testPiece.isValidMove(testGame.board, 2, 2));
		assertTrue(testPiece.isValidMove(testGame.board, 5, 5));
		assertTrue(testPiece.isValidMove(testGame.board, 5, 3));
		assertTrue(testPiece.isValidMove(testGame.board, 3, 5));
		assertFalse(testPiece.isValidMove(testGame.board, 6, 3));
		assertFalse(testPiece.isValidMove(testGame.board, 0, 5));

		testGame.printBoard();
	}
	
	/**
	 * This tests if the king can only make the 8 moves it can make
	 * It does not test if moving puts it into check.
	 */
	@Test
	public void KingTest() {
		System.out.println("King Tests\n");

		Game testGame = new Game();
		testGame.clearGame();
		
		Piece testPiece = new King(testGame.white, testGame.black, 4, 4);
		testGame.addPiece(testPiece, testGame.white, 4, 4);
		assertTrue(testPiece.isValidMove(testGame.board, 4, 3));
		assertTrue(testPiece.isValidMove(testGame.board, 4, 5));
		assertTrue(testPiece.isValidMove(testGame.board, 5, 4));
		assertTrue(testPiece.isValidMove(testGame.board, 3, 4));
		assertTrue(testPiece.isValidMove(testGame.board, 5, 3));
		assertTrue(testPiece.isValidMove(testGame.board, 5, 5));
		assertTrue(testPiece.isValidMove(testGame.board, 3, 3));
		assertTrue(testPiece.isValidMove(testGame.board, 3, 5));

		assertFalse(testPiece.isValidMove(testGame.board, 4, 2));
		assertFalse(testPiece.isValidMove(testGame.board, 7, 4));
		assertFalse(testPiece.isValidMove(testGame.board, 2, 6));
		assertFalse(testPiece.isValidMove(testGame.board, 3, 1));

		testGame.printBoard();
	}
	
	@Test 
	public void customPiecesTest() {
		System.out.println("Custom Piece Tests\n");

		Game testGame = new Game();
	
		//replace the rook in the top right corner with a cheap rook
		Piece knook = new Knook(testGame.white, 1, 0);
		testGame.removePiece(1, 0);
		testGame.addPiece(knook, testGame.white, 1, 0);
		assertTrue(knook.isValidMove(testGame.board, 0, 2));
		assertFalse(knook.isValidMove(testGame.board, 1, 4));

		//replace the rook in the top right corner with a cheap rook
		Piece cheapRook = new CheapRook(testGame.white, 7, 0);
		testGame.removePiece(7, 0);
		testGame.addPiece(cheapRook, testGame.white, 7, 0);
		assertTrue(cheapRook.isValidMove(testGame.board, 7, 5));
		assertTrue(cheapRook.isValidMove(testGame.board, 7, 2));
		assertFalse(cheapRook.isValidMove(testGame.board, 0, 2));
		assertFalse(cheapRook.isValidMove(testGame.board, 6, 2));
	}
}
