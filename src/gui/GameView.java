package gui;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import chessGame.play.Game;
import chessGame.play.Piece;
import chessGame.play.Play;
import chessGame.play.Player;
import chessGame.play.chessPieces.*;

public class GameView {
 
	//GameController associated with this view used to communicate to the model
	GameController controller;
	
	//The game associated with the view
	Game game;
	
	//Holds a button for each tile on the chess board
	public JButton[][] boardTiles = new JButton[8][8];
	
	//Unicode encodings for every single piece		
	static String king = "\u265A";
	static String queen = "\u265B";
	static String rook = "\u265C";
	static String bishop = "\u265D";
	static String knight = "\u265E";
	static String pawn = "\u265F";
	
	//used to change the current message displayed
	JLabel message;

	/**
	 * Creates the GUI with a blank chess board
	 * @param controller the GameController that will communicate to the model
	 */
    public GameView(GameController controller, Game game){
    	this.controller = controller;
    	this.game = game;
    	
        JFrame window = new JFrame("Chess");
        window.setSize(500, 650);
        
        JPanel board = initializeBoardPanel();
        JPanel info = initializeInfoPanel();
        setUpMenu(window);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(info);
        mainPanel.add(board);
        
        window.setContentPane(mainPanel);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Sets up panel with score and game information
     */
    private JPanel initializeInfoPanel() {
    	JPanel info = new JPanel();
    	info.setBackground(Color.lightGray);
    	info.setMinimumSize(new Dimension(500, 100));
    	info.setMaximumSize(new Dimension(500, 100));
    	
    	message = new JLabel();
    	
    	message.setText("<html>White : "+ Play.whitePlayerWins +" wins &nbsp | &nbsp Black : "+ Play.blackPlayerWins + " wins<br>"
    			+ "<p align=\"center\"></p></html>");
    	message.setFont(new Font("Verdana", 0, 17));
    	message.setMinimumSize(new Dimension(500, 100));
    	message.setMaximumSize(new Dimension(500, 100));
    	message.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	info.add(message);
    	
    	return info;
    }
    
    /**
     * Sets the message to be the given player's turn
     * @param currPlayer 1 if it is the white player, -1 if it is the black player
     */
    public void setPlayerTurnMessage(int currPlayer) {
    	String msg = "";
    	if(currPlayer == 1) {
    		msg = "White Player's turn";
    	} else {
    		msg = "Black Player's turn";
    	}
    	
    	message.setText("<html>White : "+Play.whitePlayerWins+" wins &nbsp | &nbsp Black : "+Play.blackPlayerWins+" wins<br>"
    			+ "<p align=\"center\">" + msg + "</p></html>");
    }
    
    /**
     * Sets the message to say invalid move
     */
    public void setMessageInvalidMove() {
    	message.setText(message.getText().substring(0, message.getText().length()-7) + 
    			"<p align=\"center\">Invalid Move</p></html>");    	
    }
    
    /**
     * Sets the message that the player is in check
     * @param currPlayer 1 if the white player is in check -1 for the black player
     */
    public void setCheckMessage(int currPlayer) {
    	if(currPlayer == 1) {
    		message.setText(message.getText().substring(0, message.getText().length()-7) + 
        			"<p align=\"center\" color=\"red\">White Player is in Check!</p></html>");
    	} else {
    		message.setText(message.getText().substring(0, message.getText().length()-7) + 
        			"<p align=\"center\" color=\"red\">Black Player is in Check!</p></html>");
    	}
    }
    
    /**
     * Sets the message that the player is in checkmate and the game is over
     * @param currPlayer 1 if the white player is in checkmate -1 for the black player
     */
    public void setCheckmateMessage(int currPlayer) {
    	if(currPlayer == 1) {
    		message.setText("<html><br><p color=\"red\">White Player is in Checkmate!</p><br>"
    				+ "<p align=\"center\">Black Player Wins!</p></html>");
    	} else {
    		message.setText("<html><br><p color=\"red\">Black Player is in Checkmate!</p><br>"
    				+ "<p align=\"center\">White Player Wins!</p></html>");
    	}
    }
  
    /**
     * Function that will set up the menu bar for the GUI
     * @param window the JFrame that will contain the menu bar
     */
    private void setUpMenu(JFrame window) {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem resign = new JMenuItem("Resign");
        JMenuItem undo = new JMenuItem("Undo Last Move");

        newGame.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		controller.newGame = true;
        	}
        });
        file.add(newGame);
        
        resign.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		controller.resign = true;
        	}
        });
        file.add(resign);
        
        undo.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		controller.undo = true;
        	}
        });
        file.add(undo);
        
        menubar.add(file);
        window.setJMenuBar(menubar);
    }
    
    /**
     * Initializes the main JPanel in the JFrame that has the chess board
     * @return the JPanel that has been fully initialized with the blank chess board
     */
    private JPanel initializeBoardPanel() {
        JPanel board = new JPanel();
        board.setMinimumSize(new Dimension(500, 500));
        board.setMaximumSize(new Dimension(500, 500));

        board.setLayout(new GridLayout(8, 8, 0, 0));
        board.setBorder(BorderFactory.createLineBorder(Color.lightGray, 15));

        //setup each button that represents a tile on the board
        for (int y = 0; y < 8; y++) {
        	for (int x = 0; x < 8; x++) {
        		boardTiles[x][y] = new JButton();
        		boardTiles[x][y].setFont(new Font("", 0, 40));
        		boardTiles[x][y].setOpaque(true);
        		boardTiles[x][y].setBorderPainted(false);
        		boardTiles[x][y].setMargin(new Insets(-30, -30, -30, -30));
        		
        		//determine if the tile should be black or white
        		if ((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1)) {
        			boardTiles[x][y].setBackground(new Color(210, 190, 155));
        		} else {
        			boardTiles[x][y].setBackground(new Color(110, 75, 15));
        		}
        		board.add(boardTiles[x][y]);
        		
        		//add the listener for this button
        		boardTiles[x][y].addActionListener(new ButtonListener(this, this.controller, x, y));
        	}
        }
        return board;
    }
    
    /**
     * Updates the chess board according to the current state of the game
     * @param currGame the state that the game is to be updated in the view
     */
    public void updateView(Game currGame) {
    	for (int y = 0; y < 8; y++) {
    		for (int x = 0; x < 8; x++) {
    			Piece p = currGame.getPiece(x, y);
    			//if there is a piece on this tile then we need to draw it
    			if (!currGame.board[x][y].isEmpty()) {
    				if (p instanceof King)
    					boardTiles[x][y].setText(king);
    				else if (p instanceof Queen)
    					boardTiles[x][y].setText(queen);
    				else if (p instanceof Rook)
    					boardTiles[x][y].setText(rook);
    				else if (p instanceof Knight)
    					boardTiles[x][y].setText(knight);
    				else if (p instanceof Bishop)  
    					boardTiles[x][y].setText(bishop);
    				else if (p instanceof Pawn) 
    					boardTiles[x][y].setText(pawn);
    				
    				//if the piece is white then set the piece to be white
    				Player owner = p.getOwner();
        			if (owner.equals(currGame.white))
    					boardTiles[x][y].setForeground(Color.white);
        			else
    					boardTiles[x][y].setForeground(Color.black);
    			} else {
    				boardTiles[x][y].setText(null);
					boardTiles[x][y].setForeground(Color.black);
    			}
    		}
    	}
    }
}