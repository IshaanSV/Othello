package othello;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
final int BOARD_HEIGHT = 720;
final int BOARD_WIDTH = 720;
int [][] board = new int[8][8];
int turn = 1;
final int BLANK = 0;
final int BLACK = 1;
final int WHITE = 2;
String blackStr = "Black Wins: ";
String whiteStr = "White Wins: ";
int blackWinCount = 0;
int whiteWinCount = 0;
JLabel blackNm = new JLabel("Black Name: ");
JLabel whiteNm = new JLabel("White Name: ");

public BoardPanel() {
super();
}

public void paintComponent(Graphics g) {
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

public int[][] getBoard(){
return board;
}

public void resetBoard() {
turn = 1;
for(int i = 0; i<board.length; i++) {
for (int j = 0; j < board.length; j++) {
board[i][j] = 0;
}
}
board[3][3] = BLACK;
board[4][4] = BLACK;
board[3][4] = WHITE;
board[4][3] = WHITE;
}

public void restartMatch() {
turn = 1;
for(int i = 0; i<board.length; i++) {
for (int j = 0; j < board.length; j++) {
board[i][j] = 0;
}
}
board[3][3] = BLACK;
board[4][4] = BLACK;
board[3][4] = WHITE;
board[4][3] = WHITE;
blackWinCount = 0;
whiteWinCount = 0;
blackNm.setText(blackStr+ blackWinCount);
whiteNm.setText(whiteStr+whiteWinCount);
}

public boolean isValid(int player, int xIndex, int yIndex) {
	int oppositePlayer;
	boolean valid = false;
	if(player == BLACK) {
		oppositePlayer = WHITE;
	}
	else {
		oppositePlayer = BLACK;
	}
	for(int i = -1; i <=1; i++) {
		for(int j = -1; j <=1; j++) {
			if(xIndex + i >=0 && yIndex + j >= 0) {
				if(i!= 0 || j!= 0) {
					int multiplier = 1;
					while(board[xIndex+i*multiplier][yIndex+j*multiplier] == oppositePlayer) {
						multiplier++;
						if(board[xIndex+i*multiplier][yIndex+j*multiplier] == player) {
							valid = true;
						}
					}
				}
			}
		}
	}
	return false;
}

public boolean doMove(int player, int xIndex, int yIndex) {
	boolean valid = false;
	int oppositePlayer;
	if(player == BLACK) {
		oppositePlayer = WHITE;
	}
	else {
		oppositePlayer = BLACK;
	}
	for(int i = -1; i <= 1; i++) {
		for(int j = -1; j <= 1; j++) {
			boolean thisValid = false;
			int multiplier = 1;
			while(board[xIndex+i*multiplier][yIndex+j*multiplier] == oppositePlayer) {
				multiplier++;
				if(board[xIndex+i*multiplier][yIndex+j*multiplier] == player) {
					thisValid = true;
					valid = true;
				}
			}
			multiplier = 1;
			while(thisValid == true && board[xIndex+i*multiplier][yIndex+j*multiplier] == oppositePlayer) {
				board[xIndex+i*multiplier][yIndex+j*multiplier] = player;
			}
		}
	}
	return valid;
}

}