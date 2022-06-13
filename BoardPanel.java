package Othello;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	int turn = 1;
	final int BOARD_HEIGHT = 720;
	final int BOARD_WIDTH = 720;
	final int WHITE = 2;
	final int BLACK = 1;
	final int BLANK = 0;
	int[][] board = new int[8][8];
	
	public BoardPanel() {
		super();
		board[3][4] = BLACK;//the default starting position in Othello.
		board[4][3] = BLACK;
		board[3][3] = WHITE;
		board[4][4] = WHITE;
	}
	
	public void paintComponent(Graphics g) {//does the painting of the dots and of the background of the grid.
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		g.setColor(Color.WHITE);
		for (int i = 90; i < BOARD_WIDTH; i += 90) {
			g.drawLine(i, 0, i ,BOARD_HEIGHT);
		}
		for(int i = 90; i < BOARD_HEIGHT; i+=90) {
			g.drawLine(0, i, BOARD_WIDTH, i);
		}
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == BLACK) {
					g.setColor(Color.BLACK);
					g.fillOval(i*90+15, j*90+15, 60, 60);
				}
				if(board[i][j] == WHITE) {
					g.setColor(Color.WHITE);
					g.fillOval(i*90+15, j*90+15, 60, 60);
				}
			}
		}
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public void resetBoard() {
		turn = 1;//sets the turn to black because the game starts with black turn
		for(int i = 0; i<board.length; i++) {
		for (int j = 0; j < board.length; j++) {//goes through the bboard
		board[i][j] = 0;//sets everything to blank(removing all the black and white dots)
		}
		}
		board[3][3] = BLACK;//sets the board to the default/starting position
		board[4][4] = BLACK;
		board[3][4] = WHITE;
		board[4][3] = WHITE;
		}

	public void restartMatch() {
		turn = 1;//sets the turn to black 
		for(int i = 0; i<board.length; i++) {
		for (int j = 0; j < board.length; j++) {//goes through the board
		board[i][j] = 0;//sets everything to blank(removing all the black and white dots)
		}
		}
		board[3][3] = BLACK;//sets the board to the default/starting position
		board[4][4] = BLACK;
		board[3][4] = WHITE;
		board[4][3] = WHITE;

		}


	
	public boolean isValid(int player, int xIndex, int yIndex) {
		//creates an int which stores who the opponent is
		int oppositePlayer;
		boolean valid = false;
		if(player == BLACK) {
			oppositePlayer = WHITE;
		}
		else {
			oppositePlayer = BLACK;
		}
		
		//the for loops check the indexes around the given index
		for(int i = -1; i <=1; i++) {
			for(int j = -1; j <=1; j++) {
				// if the index isn't the original index
				if(i!= 0 || j!= 0) {
					//make a multiplier variable 1
					int multiplier = 1;
					//if the index is valid, and keep on going in the direction that the for loop originally went, and check to see if a piece in that direction is the player
					while(xIndex+i*multiplier < 8 && yIndex+j*multiplier<8 && xIndex+i*multiplier >=0 && yIndex+j*multiplier>=0 && board[xIndex+i*multiplier][yIndex+j*multiplier] == oppositePlayer) {
						multiplier++;
						if(board[xIndex+i*multiplier][yIndex+j*multiplier] == player) {
							//if the piece is the player, then say that the move is valid
							valid = true;
						}
					
					}
				}
			}
		}
		return valid;
	}
	
	public boolean doMove(int player, int xIndex, int yIndex) {//checks if the move is vaild through isValid method and if it is it does the move 
		boolean valid = false;
		int oppositePlayer;
		if(player == BLACK) {
			oppositePlayer = WHITE;
		}
		else {
			oppositePlayer = BLACK;
		}
		for(int i = -1; i <= 1; i++) {//isValid: to see if the path the computer is taking is valid.
			for(int j = -1; j <= 1; j++) {
				boolean thisValid = false;
				int multiplier = 1;
				while(board[xIndex+i*multiplier][yIndex+j*multiplier] == oppositePlayer) {//finds a valid path that changes the pieces on the path.
					multiplier++;
					if(board[xIndex+i*multiplier][yIndex+j*multiplier] == player) {
						thisValid = true;
						valid = true;
					}
				}
				multiplier = 1;
				while(thisValid == true && board[xIndex+i*multiplier][yIndex+j*multiplier] == oppositePlayer) {
					board[xIndex+i*multiplier][yIndex+j*multiplier] = player;
					multiplier++;//increments the multiplier
				}
			}
		}
		return valid;
	}
}