package chessGame.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessGame.play.Game;
import chessGame.play.Piece;
import chessGame.play.chessPieces.*;

public class GameTests {

	/**
	 * Tests the creation of a classic game of chess seeing if pieces are placed 
	 * in their proper locations and if each player is properly initialized.
	 */
	@Test
	public void createGameTest() {
		System.out.println("CREATE GAME TESTS\n");

		Game testGame = new Game();
		testGame.printBoard();	
		assertEquals(testGame.white.pieces.size(), 16);
		assertEquals(testGame.black.pieces.size(), 16);
		
		testGame.clearGame();
		testGame.printBoard();	
		assertEquals(testGame.white.pieces.size(), 0);
		assertEquals(testGame.black.pieces.size(), 0);
	}
	
	/**
	 * Copy constructor test
	 */
	@Test
	public void copyTest() {
		System.out.println("COPY TESTS");
		
		Game game1 = new Game();		
		Game game2 = new Game(game1);
		
		assertFalse(game1.equals(game2));
		assertFalse(game1.board[0][0].equals(game2.board[0][0]));
		assertFalse(game1.white.equals(game2.white));

		game1.printBoard();
		game2.printBoard();
	}
	
	@Test
	public void addRemoveTest() {
		System.out.println("ADD AND REMOVE TESTS\n");
		
		Game testGame = new Game();
		testGame.printBoard();
		
		Piece pawn = new Pawn(testGame.white, true, true, 3, 3);
		testGame.addPiece(pawn, testGame.white, 3, 3);
		Piece knight = new Knight(testGame.white, 4, 3);
		testGame.addPiece(knight, testGame.black, 4, 3);
		
		testGame.printBoard();
		assertEquals(testGame.board[3][3].getPiece(), pawn);
		assertEquals(testGame.board[4][3].getPiece(), knight);
		assertEquals(testGame.white.pieces.size(), 17);
		assertEquals(testGame.black.pieces.size(), 17);
		
		testGame.removePiece(1, 1);
		testGame.removePiece(5, 0);
		testGame.removePiece(6, 6);
		testGame.removePiece(2, 7);
		testGame.printBoard();
	}
	
	@Test
	public void isInBoardTest() {
		Game testGame = new Game();
		assertTrue(testGame.isInBoard(0, 0));
		assertTrue(testGame.isInBoard(5, 5));
		assertTrue(testGame.isInBoard(7, 3));
		assertTrue(testGame.isInBoard(4, 6));
		assertFalse(testGame.isInBoard(-1, 5));
		assertFalse(testGame.isInBoard(8, 0));
		assertFalse(testGame.isInBoard(5, 9));
		assertFalse(testGame.isInBoard(0, 8));
	}
	
	@Test
	public void undoTest() {
		System.out.println("UNDO TESTS\n");

		Game testGame = new Game();
		testGame.printBoard();
		
		Piece p = testGame.board[3][1].getPiece();
		testGame.makeMove(1, p, 3, 3);
		testGame.printBoard();
		
		p = testGame.board[3][6].getPiece();
		testGame.makeMove(-1, p, 3, 4);
		testGame.printBoard();
		
		p = testGame.board[2][0].getPiece();
		testGame.makeMove(1, p, 5, 3);
		testGame.printBoard();
		
		testGame.undo();
		testGame.printBoard();
		assertTrue(testGame.board[5][3].isEmpty());
	}
	
	@Test
	public void moveTest() {
		System.out.println("MOVE TESTS\n");

		Game testGame = new Game();
		testGame.printBoard();
		
		//simulate one of the quickest ways to get check mate
		Piece p = testGame.board[3][1].getPiece();
		testGame.makeMove(1, p, 3, 3);
		testGame.printBoard();
		
		p = testGame.board[3][6].getPiece();
		testGame.makeMove(-1, p, 3, 4);
		testGame.printBoard();
		
		p = testGame.board[2][0].getPiece();
		testGame.makeMove(1, p, 5, 3);
		testGame.printBoard();
		
		p = testGame.board[6][7].getPiece();
		assertFalse(testGame.makeMove(-1, p, 6, 5));
		testGame.printBoard();
		
		p = testGame.board[6][7].getPiece();
		testGame.makeMove(-1, p, 7, 5);
		testGame.printBoard();
		
		p = testGame.board[4][0].getPiece();
		testGame.makeMove(1, p, 2, 2);
		testGame.printBoard();
		
		p = testGame.board[7][5].getPiece();
		testGame.makeMove(-1, p, 6, 3);
		testGame.printBoard();
		
		p = testGame.board[2][2].getPiece();
		testGame.makeMove(1, p, 2, 6);
		testGame.printBoard();
		assertTrue(Game.isCheck(testGame, -1));
	}
	
	@Test
	public void movePieceTest() {
		System.out.println("MOVE PIECE TESTS\n");

		Game testGame = new Game();
		testGame.printBoard();
		
		//move a piece to some other location, it does not have to be a valid move
		Piece p = testGame.board[3][7].getPiece();
		testGame.movePiece(p, 4, 4);
		assertTrue(testGame.board[3][7].isEmpty());
		assertFalse(testGame.board[4][4].isEmpty());
		assertEquals(p.getX(), 4);
		assertEquals(p.getY(), 4);
		
		//random move that can be checked by looking at the printed board
		testGame.movePiece(testGame.board[0][0].getPiece(), 1, 6);

		testGame.printBoard();
	}

	@Test
	public void isValidMoveTest() {
		System.out.println("ISVALIDMOVE TESTS\n");
		Game testGame = new Game();

		Piece p = testGame.board[1][7].getPiece();
		assertTrue(testGame.isValidMove(-1, p, 0, 5));
		assertTrue(testGame.isValidMove(-1, p, 2, 5));
		assertFalse(testGame.isValidMove(-1, p, 3, 6));
		assertFalse(testGame.isValidMove(-1, p, 1, 7));

		p = new Queen(testGame.white, 3, 3);
		testGame.addPiece(p, testGame.white, 3, 3);
		assertTrue(testGame.isValidMove(1, p, 3, 5));
		assertTrue(testGame.isValidMove(1, p, 3, 6));
		assertTrue(testGame.isValidMove(1, p, 6, 6));
		assertTrue(testGame.isValidMove(1, p, 0, 3));
		assertFalse(testGame.isValidMove(1, p, 3, 1));
		assertFalse(testGame.isValidMove(1, p, 6, 0));
		assertFalse(testGame.isValidMove(1, p, 7, 4));
		
		p = testGame.board[1][1].getPiece();
		assertTrue(testGame.isValidMove(1, p, 1, 2));
		assertTrue(testGame.isValidMove(1, p, 1, 3));
		assertFalse(testGame.isValidMove(1, p, 2, 2));
		assertFalse(testGame.isValidMove(1, p, 1, 4));
	}
	
	@Test
	public void checkTest() {
		System.out.println("CHECK TESTS\n");

		Game testGame = new Game();

		//the players should not be check at the beginning of the game
		assertFalse(Game.isCheck(testGame, 1));
		assertFalse(Game.isCheck(testGame, -1));

		//simple scenario when a rook places the king in check
		testGame.clearGame();
		Piece king = new King(testGame.white, testGame.black, 4, 4);
		testGame.addPiece(king, testGame.white, 4, 4);
		Piece rook = new Rook(testGame.black, 7, 4);
		testGame.addPiece(rook, testGame.black, 7, 4);
		assertTrue(Game.isCheck(testGame, 1));
		testGame.printBoard();
		
		//now place something in between the rook and king
		Piece knight = new Knight(testGame.black, 6, 4);
		testGame.addPiece(knight, testGame.black, 6, 4);
		assertFalse(Game.isCheck(testGame, 1));
		testGame.printBoard();
		
		//check you can't place your own king in check
		Piece bishop = new Bishop(testGame.white, 0, 0);
		testGame.addPiece(bishop, testGame.white, 0, 0);
		assertFalse(Game.isCheck(testGame, 1));
		testGame.printBoard();
	}	
	
	@Test
	public void checkmateTest() {
		Game testGame = new Game();
		testGame.printBoard();
		
		//simulate one of the quickest ways to get check mate
		Piece p = testGame.board[3][1].getPiece();
		testGame.makeMove(1, p, 3, 3);
		testGame.printBoard();
		
		p = testGame.board[3][6].getPiece();
		testGame.makeMove(-1, p, 3, 4);
		testGame.printBoard();
		
		p = testGame.board[2][0].getPiece();
		testGame.makeMove(1, p, 5, 3);
		testGame.printBoard();
		
		p = testGame.board[6][7].getPiece();
		assertFalse(testGame.makeMove(-1, p, 6, 5));
		testGame.printBoard();
		
		p = testGame.board[6][7].getPiece();
		testGame.makeMove(-1, p, 7, 5);
		testGame.printBoard();
		
		p = testGame.board[4][0].getPiece();
		testGame.makeMove(1, p, 2, 2);
		testGame.printBoard();
		
		p = testGame.board[7][5].getPiece();
		testGame.makeMove(-1, p, 6, 3);
		testGame.printBoard();
		
		p = testGame.board[2][2].getPiece();
		testGame.makeMove(1, p, 2, 6);
		testGame.printBoard();	
	
		assertTrue(testGame.isCheckmate(-1));
	}
}
