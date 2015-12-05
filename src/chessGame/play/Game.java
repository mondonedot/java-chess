package chessGame.play;

import chessGame.play.chessPieces.*;

public class Game {
	
	/**
	 * Class that will be used to contain the previous command received from the view
	 */
	public class Command {
		public int prevSourceX;
		public int prevSourceY;
		public int prevDestX;
		public int prevDestY;
		public Piece movedPiece;
		public Piece removedPiece;
	}

	public Tile board [][];
	public Player white;
	public Player black;
	public Command lastCommand;
	
	/**
	 * Constructor to create classic game of chess with white and black players
	 */
	public Game() {
		this.board = new Tile[8][8];
		//create a new tile for each coordinate
		for (int x = 0; x < this.board.length; x++) {
			for (int y = 0; y < this.board[0].length; y++) {
				this.board[x][y] = new Tile();
			}
		}
		
		this.white = new Player();
		this.black = new Player();
		lastCommand = new Command();
		
		// Add the pieces to lists of each player and then to the board
		// Instantiate pieces for the white player
		for(int i = 0; i < 8; i++) {  //add all eight pawns
			Piece pawn = new Pawn(white, true, true, i, 1);
			white.addPiece(pawn);
			board[i][1].addPiece(pawn);
		}
		Piece rook1 = new Rook(white, 0, 0); 
		white.addPiece(rook1); board[0][0].addPiece(rook1);
		Piece rook2 = new Rook(white, 7, 0); 
		white.addPiece(rook2); board[7][0].addPiece(rook2);
		Piece knight1 = new Knight(white, 1, 0); 
		white.addPiece(knight1); board[1][0].addPiece(knight1);
		Piece knight2 = new Knight(white, 6, 0);
		white.addPiece(knight2); board[6][0].addPiece(knight2);
		Piece bishop1 = new Bishop(white, 2, 0);
		white.addPiece(bishop1); board[2][0].addPiece(bishop1);
		Piece bishop2 = new Bishop(white, 5, 0);
		white.addPiece(bishop2); board[5][0].addPiece(bishop2);
		Piece queen = new Queen(white, 4, 0);
		white.addPiece(queen); board[4][0].addPiece(queen);
		Piece king = new King(white, black, 3, 0);
		white.addPiece(king); board[3][0].addPiece(king);
		
		/* repeat for the black player */
		for(int i = 0; i < 8; i++) {
			Piece pawn = new Pawn(black, false, true, i, 6);
			black.addPiece(pawn);
			board[i][6].addPiece(pawn);
		}
		rook1 = new Rook(black, 0, 7); 
		black.addPiece(rook1); board[0][7].addPiece(rook1);
		rook2 = new Rook(black, 7, 7); 
		black.addPiece(rook2); board[7][7].addPiece(rook2);
		knight1 = new Knight(black, 1, 7); 
		black.addPiece(knight1); board[1][7].addPiece(knight1);
		knight2 = new Knight(black, 6, 7);
		black.addPiece(knight2); board[6][7].addPiece(knight2);
		bishop1 = new Bishop(black, 2, 7);
		black.addPiece(bishop1); board[2][7].addPiece(bishop1);
		bishop2 = new Bishop(black, 5, 7);
		black.addPiece(bishop2); board[5][7].addPiece(bishop2);
		queen = new Queen(black, 4, 7);
		black.addPiece(queen); board[4][7].addPiece(queen);
		king = new King(black, white, 3, 7);
		black.addPiece(king); board[3][7].addPiece(king);
	}
	
	/**
	 * Game copy constructor
	 * @param board the game board that will be duplicated
	 * @param white the white player to duplicate
	 * @param black the black player to duplicate
	 */
	public Game(Game game) {
		
		this.board = new Tile[8][8];
		this.white = new Player();
		this.black = new Player();
		this.lastCommand = new Command();
		
		//copy each tile for each coordinate
		for (int x = 0; x < this.board.length; x++) {
			for (int y = 0; y < this.board[0].length; y++) {
			
				Player owner = null;
				if (!game.board[x][y].isEmpty()) {
					//add the piece to the correct owner
					if (game.getPiece(x, y).owner.equals(game.white)) {
						owner = this.white;
					} else {
						owner = this.black;
					}
				}
				
				//make a deep copy of the piece and place it on the new test board
				this.board[x][y] = Tile.copyTile(game.board[x][y], owner);
			
				//if there is a piece on the tile then add it to the player list
				if (!game.board[x][y].isEmpty())
					owner.addPiece(this.getPiece(x, y));
			}
		}
	}
	
	/**
	 * Clears the board to have no pieces on it and resets the players. 
	 */
	public void clearGame() {
		this.board = new Tile[8][8];
		//create a new tile for each coordinate
		for (int x = 0; x < this.board.length; x++) {
			for (int y = 0; y < this.board[0].length; y++) {
				this.board[x][y] = new Tile();
			}
		}
		
		this.white = new Player();
		this.black = new Player();
	}
	
	/**
	 * Returns the piece at the specified coordinates
	 * @param x the x coordinate of the desired piece
	 * @param y the y coordinate of the desired piece
	 * @return the piece at the requested coordinates
	 */
	public Piece getPiece(int x, int y) {
		if(!isInBoard(x, y))
			return null;
		else
			return this.board[x][y].getPiece();
	}
	
	/**
	 * Adds a piece to the board at the given position
	 * If there is a piece on the location then remove it.
	 * @param p The piece to add to the board
	 * @param owner The player that this piece will belong to
	 * @param x the x coordinate to add the new piece
	 * @param y the y coordinate to add the new piece
	 */
	public void addPiece(Piece p, Player owner, int x, int y) {
		//return if this coordinates are not within the board
		if (!isInBoard(x, y)) {
			return;
		}
		
		//remove any piece that may be on the tile
		removePiece(x, y);
		
		//add the new piece to the tile and update the player piece list
		owner.addPiece(p);
		this.board[x][y].addPiece(p);
	}
	
	/**
	 * Removes the piece from the board at the given position
	 * @param x the x coordinate to remove the new piece
	 * @param y the y coordinate to remove the new piece
	 * @return the piece that is removed
	 */
	public Piece removePiece(int x, int y) {
		//if there is a piece on the board then we need to remove it
		if (!this.board[x][y].isEmpty()) {
					
			//get the owner of the piece and remove it from their list
			Player owner = this.getPiece(x, y).getOwner();
			owner.removePiece(this.getPiece(x, y));
					
			return this.board[x][y].removePiece();		
		} else
			return null;
	}
	
	/**
	 * Helper function to determine if a move is within the board
	 * @param x, y coordinates to check if they are within board
	 */
	public boolean isInBoard(int x, int y) {
		if(x < 0 || x >= this.board.length) {
			return false;
		}
		if(y < 0 || y >= this.board[0].length) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Undoes the last move using the information stored in lastCommand
	 * Can only be called once in a row
	 */
	public void undo() {
		this.board[lastCommand.prevSourceX][lastCommand.prevSourceY].addPiece(lastCommand.movedPiece);
		lastCommand.movedPiece.updateCoordinates(lastCommand.prevSourceX, lastCommand.prevSourceY);
		if(lastCommand.movedPiece instanceof Pawn)
			((Pawn)(lastCommand.movedPiece)).isFirstMove = true;
			
		this.board[lastCommand.prevDestX][lastCommand.prevDestY].removePiece();
		if(lastCommand.removedPiece != null) {
			this.board[lastCommand.prevDestX][lastCommand.prevDestY].addPiece(lastCommand.removedPiece);
			lastCommand.removedPiece.owner.addPiece(lastCommand.removedPiece);
		}
	}
	
	/**
	 * This it the primary method to move a piece from
	 * original x, y coordinates to the new given location
	 * If there is a piece on the new location it will be removed from the game.
	 * Updates the piece's new location.
	 * Calls the pieces isValidMove() function and only makes the move if this returns true
	 * Then checks if the move will place the player in check and returns false if it does
	 * 
	 * @param currentPlayer 1 = the white player is moving, -1 the black player is moving
	 * @param p Piece that is being moved
	 * @param destX	the destination x coordinate to move to
	 * @param destY the destination y coordinate to move to
	 * @return true if the move is successful
	 */
	public boolean makeMove(int currentPlayer, Piece p, int destX, int destY) {
		
		if (p == null) return false;
		
		//check if the player is trying to move their own piece
		if((currentPlayer == 1 && p.getOwner() == this.black) ||
			(currentPlayer == -1 && p.getOwner() == this.white)) {
			return false;
		}
		
		if(!isValidMove(currentPlayer, p, destX, destY)) {
			return false;
		}
	
		/* If the move causes the player to be in check then it is invalid 
		 * Simulate the move and pass that game into the isCheck function */
		Game gameCopy = simulateMove(p, destX, destY);
		if (isCheck(gameCopy, currentPlayer))
			return false;
		
		//move the piece and update data structures
		movePiece(p, destX, destY);
		
		return true;
	}

	/**
	 * Method that will actually move the given piece to the new location.
	 * All data structures will be updated.
	 * Does not check if the move is valid.
	 * @param p the piece being moved
	 * @param destX the new x coordinate of the piece
	 * @param destY the new y coordinate of the piece
	 */
	public void movePiece(Piece p, int destX, int destY) {
		/* Check if there is a piece on the destination tile and if so remove it. 
		 * In addition remove that piece from its owner's pieces list. */
		Piece removedPiece = null;
		if(!this.board[destX][destY].isEmpty()) {
			Player tempOwner = this.getPiece(destX, destY).getOwner();
			tempOwner.removePiece(this.getPiece(destX, destY));
			removedPiece = this.board[destX][destY].removePiece();
		}
		
		/* Update the piece with its new coordinates and record its old coordinates for later */
		int origX = p.getX();
		int origY = p.getY();
		p.updateCoordinates(destX, destY);
		
		/* Place the piece in its new location on the board and remove it from the old location*/
		this.board[origX][origY].removePiece();
		this.board[destX][destY].addPiece(p);
		
		/* If p is a pawn then set its first move to false */
		if (p instanceof Pawn)
			((Pawn)(p)).isFirstMove = false;
		
		//update lastCommandInfo
		lastCommand.prevSourceX = origX;
		lastCommand.prevSourceY = origY;
		lastCommand.prevDestX = destX;
		lastCommand.prevDestY = destY;
		lastCommand.movedPiece = p;
		lastCommand.removedPiece = removedPiece;
	}
	
	/**
	 * Returns a different game with the given move simulated
	 * @param p piece to be moved
	 * @param destX location of the new x coordinate
	 * @param destY location of the new y coordinate
	 * @return a new game with the move having been simulated
	 */
	public Game simulateMove(Piece p, int destX, int destY) {
		
		/*
		 *  First make a duplicate board to simulate if the move actually occurred
		 *  We know that everything about the move is valid except for if the 
		 *  move puts the player into check
		 */
		Game gameCopy = new Game(this);
					
		/* Find the corresponding piece in the game copy to the one that is being moved. */
		Piece currPiece = gameCopy.getPiece(p.getX(), p.getY());
		
		/* Simulate the move in the copy of the game*/
		gameCopy.movePiece(currPiece, destX, destY);
		
		return gameCopy;
	}
	
	/**
	 * Helper function to determine if a move by the given piece to the 
	 * given location is valid. Does not check if the move puts the player into check.
	 * Calls the piece's isValidMove function to validate moves specific for to that piece 
	 * @param currPlayer 1 = white player is moving, -1 = black player is moving
	 * @param p Piece that is being moved
	 * @param destX	the destination x coordinate to move to
	 * @param destY the destination y coordinate to move to
	 * @return true if the move is valid
	 */
	public boolean isValidMove(int currPlayer, Piece p, int destX, int destY) {
		
		/* First check if the coordinates are within the board*/
		if(!isInBoard(destX, destY))
			return false;
		
		/* Make sure the piece actually tries to move to a different place */
		if (p.getX() == destX && p.getY() == destY)
			return false;
		
		/* Check if the destination square is occupied by the player's own piece*/
		Player movingPlayer;
		if (currPlayer == 1)
			movingPlayer = this.white;
		else 
			movingPlayer = this.black;
		if (!this.board[destX][destY].isEmpty() && this.getPiece(destX, destY).getOwner().equals(movingPlayer))
			return false;
		
		/* Next call the piece specific isValidMove function to determine whether that 
		 * piece can actually move there. */
		if (!p.isValidMove(this.board, destX, destY))
			return false;
		
		//otherwise the move must be valid
		return true;
	}	
	
	/**
	 * Function checks if the current player's king is in check after they made a move
	 * Takes in a game to check if that game is in check
	 * @param testGame the current state of the game to be checked if the player is in check
	 * @param currPlayer 1 = white player is moving, -1 = black player is moving
	 * @return true if the player is in check
	 */
	public static boolean isCheck(Game testGame, int currentPlayer) {
				
		int opponentPlayer;
		//the player that just made the move who were are checking if they are in check
		Player movingPlayer; 
		Player opponent;
		
		//determine who the moving player is and who is the opponent
		if (currentPlayer == 1) {
			movingPlayer = testGame.white;
			opponent = testGame.black;
			opponentPlayer = -1;
		} else {
			movingPlayer = testGame.black;
			opponent = testGame.white;
			opponentPlayer = 1;
		}
		
		/* Find the king within the list of pieces that may be in danger*/
		Piece king = movingPlayer.getKing();
		int kingX = king.getX();
		int kingY = king.getY();
		
		/* Iterate through the list of opponent pieces checking if any piece 
		 * has a valid move to where the king is. Note it does not matter if 
		 * this places the opponent in check because this move would win the game for them */
		for(int i = 0; i < opponent.pieces.size(); i++) {
			Piece opponentPiece = opponent.pieces.get(i);
			if (testGame.isValidMove(opponentPlayer, opponentPiece, kingX, kingY))
				return true;		
		}
		
		//otherwise none of the opponents players can reach the king
		return false;
	}
	
	/**
	 * Function checks if the current player is in check mate
	 * Will try every possible move the player can make and if there 
	 * is no valid move then they are in check mate
	 * Will only be called if the player is in check
	 * @param currentPlayer the player who is in check that has to move
	 * @return true if it is check mate
	 */
	public boolean isCheckmate(int currentPlayer) {
	
		//determine which player is the one who has the move
		Player movingPlayer;
		if (currentPlayer == 1) 
			movingPlayer = this.white;
		else
			movingPlayer = this.black;
			
		//loop through all possible moves and check if any are valid
		for (int i = 0; i < movingPlayer.pieces.size(); i++) {
			Piece currPiece = movingPlayer.pieces.get(i);
			for (int x = 0; x < this.board.length; x++) {
				for (int y = 0; y < this.board[0].length; y++) {
					//check if the move is valid
					if (this.isValidMove(currentPlayer, currPiece, x, y)) {
						//the move is valid so now check if the player is still in check
						Game gameCopy = this.simulateMove(currPiece, x, y);
						if (!isCheck(gameCopy, currentPlayer))
							return false;						
					}
				}
			}
		}
		//otherwise we haven't found any valid moves 
		return true;
	}
	
	/**
	 * Prints the current chess board out to the console
	 */
	public void printBoard() {
		System.out.print("   0  1  2  3  4  5  6  7\n");
		for (int y = 0; y < this.board[0].length; y++) {
			System.out.print(y + "  ");
			for (int x = 0; x < board.length; x++) {
				if (this.board[x][y].isEmpty()) {
					System.out.print("   ");
				} else {
					Piece p = this.getPiece(x, y);
					if (p instanceof Pawn)
						System.out.print("P  ");
					else if (p instanceof Rook)
						System.out.print("R  ");
					else if (p instanceof Knight)
						System.out.print("Kn ");
					else if (p instanceof Bishop)
						System.out.print("B  ");
					else if (p instanceof Queen)
						System.out.print("Q  ");
					else if (p instanceof King)
						System.out.print("K  ");
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}

