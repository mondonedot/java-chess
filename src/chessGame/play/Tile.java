package chessGame.play;

public class Tile {
	
	/* If there is a piece on this tile then it will not be null*/
	private Piece p;
	
	/**
	 * Constructs a tile with no piece
	 */
	public Tile() {
		p = null;
	}
	
	/**
	 * Constructs a tile with a given piece
	 * @param p piece that will be placed on this tile
	 */
	public Tile(Piece p) {
		this.p = p;
	}
	
	/**
	 * @param tile the tile to copy
	 * @param owner the owner of the piece that is being copied
	 * @return Returns a new copy of the given tile
	 */
	public static Tile copyTile(Tile tile, Player owner) {
		Tile newTile = new Tile();
		//if the tile is empty just make a null piece
		if(tile.isEmpty())
			newTile.p = null;
		else
			newTile.p = Piece.copyPiece(tile.getPiece(), owner);
		return newTile;
	}
	
	/**
	 * This method checks whether the tile has a piece on it or not
	 * @return true if there is no piece on the tile, @return false otherwise
	 */
	public boolean isEmpty() {
		if (this.p == null)
			return true;
		else
			return false;
	}
	
	/**
	 * This function will remove the piece on the tile.
	 * If the tile is already empty, nothing will happen.
	 */
	public Piece removePiece() {
		Piece temp = this.p;
		this.p = null;
		return temp;
	}
	
	/**
	 * This function will add the given piece to the tile
	 * @param p piece that is being added to the tile
	 */
	public void addPiece(Piece p) {
		this.p = p;
	}
	
	/**
	 * Getter method for the piece on the tile
	 */
	public Piece getPiece() {
		return this.p;
	}
}
