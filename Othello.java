import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class Othello implements ActionListener, MouseListener {
	JFrame frame = new JFrame();
	String blackStr = "Black Wins: ";
	String whiteStr = "White Wins: ";
	JButton whiteNameChange = new JButton("Change White's Name:");
	JButton blackNameChange = new JButton("Change Black's Name:");
	BoardPanel panel = new BoardPanel();
	int blackWinCount = 0;
	int whiteWinCount = 0;
	JLabel blackWins = new JLabel(blackStr + blackWinCount);
	JLabel whiteWins = new JLabel(whiteStr + whiteWinCount);
	JTextField blackNameTX = new JTextField();
	JTextField whiteNameTX = new JTextField();
	JButton resetBoard = new JButton("Reset Board");
	JButton restartMatch = new JButton("Restart Match");
	JLabel turnLbl = new JLabel("Turn: ");
	JLabel turn2 = new JLabel("Black");
	JButton endTurn = new JButton("End Turn/Game: ");
	boolean move = true;
	JPanel north = new JPanel();
	JPanel west = new JPanel();
	JPanel south = new JPanel();
	JPanel east = new JPanel();
	Font font1 = new Font("Verdana", Font.PLAIN,30);
	Font font2 = new Font("Verdana", Font.PLAIN, 20);

	final int BLANK = 0;
	final int BLACK = 1;
	final int WHITE = 2;
	int turn = 1;
	
	
	public Othello() {
		panel.setPreferredSize(new Dimension(800,800));
		frame.setSize(1200,1200);
		frame.setLayout(new BorderLayout());
		frame.add(panel,BorderLayout.CENTER);
		north.setLayout(new GridLayout(1,2));
		blackWins.setFont(font1);
		whiteWins.setFont(font1);
		north.add(blackWins);
		north.add(whiteWins);
		north.setPreferredSize(new Dimension(1200,100));
		west.setLayout(new GridLayout(2,1));
		west.setPreferredSize(new Dimension(200,720));
		west.add(resetBoard);
		resetBoard.addActionListener(this);
		west.add(restartMatch);
		restartMatch.addActionListener(this);
		
		south.setLayout(new GridLayout(3,3));
		south.add(blackNameChange);
		blackNameChange.addActionListener(this);
		south.add(whiteNameChange);
		whiteNameChange.addActionListener(this);
		south.add(blackNameTX);
		south.add(whiteNameTX);
		south.setPreferredSize(new Dimension(800,180));
		east.setLayout(new GridLayout(3,1));
		east.setPreferredSize(new Dimension(200,720));
		turnLbl.setFont(font1);
		turn2.setFont(font1);
		east.add(turnLbl);
		east.add(turn2);
		east.add(endTurn);
		endTurn.addActionListener(this);
		frame.add(north,BorderLayout.NORTH);
		frame.add(west,BorderLayout.WEST);
		frame.add(south,BorderLayout.SOUTH);
		frame.add(east,BorderLayout.EAST);
		panel.addMouseListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Othello();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//panel coords: 205 x, 130 y, 925 x, 850 y
		System.out.println("working1");
		int x = e.getX();
		int y = e.getY();
			System.out.println("working2");
			int xIndex = x/90;
			int yIndex = y/90;
			if(panel.board[xIndex][yIndex] == BLANK) {
				System.out.println("Working3");
				if(turn %2 == 1) {
					System.out.println("Working4");
					System.out.println("isValid: " + panel.isValid(BLACK,xIndex,yIndex));
					if(panel.isValid(BLACK,xIndex,yIndex)) {
						System.out.println("Working5");

						panel.board[xIndex][yIndex] = BLACK;
						panel.doMove(BLACK, xIndex, yIndex);
						turn++;
						panel.turn++;
						if(turn%2 == 0) {
							String[] whiteName2 = whiteStr.split(" ");
							String whiteName3 = whiteName2[0];
							turn2.setText(whiteName3);
						}
						else {
							String[] blackName2 = blackStr.split(" ");
							String blackName3 = blackName2[0];
							turn2.setText(blackName3);
						}
					}
				}
				else {
					System.out.println("Working4");
					if(panel.isValid(WHITE,xIndex,yIndex)) {
						System.out.println("Working5");
						panel.board[xIndex][yIndex] = WHITE;
						panel.doMove(WHITE, xIndex, yIndex);
						turn++;
						panel.turn++;
						if(turn%2 == 0) {
							String[] whiteName2 = whiteStr.split(" ");
							String whiteName3 = whiteName2[0];
							turn2.setText(whiteName3);
						}
						else {
							String[] blackName2 = blackStr.split(" ");
							String blackName3 = blackName2[0];
							turn2.setText(blackName3);
						}
					}
				}

				frame.repaint();
			}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(resetBoard)) {
			//reset board, turn =1, make middle black: (3,3)(4,4) white:(3,4)(4,3),
			panel.resetBoard();
			turn = 1;
			panel.turn = 1;
			if(turn%2 == 0) {
				String[] whiteName2 = whiteStr.split(" ");
				String whiteName3 = whiteName2[0];
				turn2.setText(whiteName3);
			}
			else {
				String[] blackName2 = blackStr.split(" ");
				String blackName3 = blackName2[0];
				turn2.setText(blackName3);
			}
			frame.repaint();
		}
		if(e.getSource().equals(restartMatch)) {
			panel.restartMatch();
			blackWinCount = 0;
			whiteWinCount = 0;
			turn = 1;
			panel.turn = 1;
			if(turn%2 == 0) {
				String[] whiteName2 = whiteStr.split(" ");
				String whiteName3 = whiteName2[0];
				turn2.setText(whiteName3);
			}
			else {
				String[] blackName2 = blackStr.split(" ");
				String blackName3 = blackName2[0];
				turn2.setText(blackName3);
			}
			frame.repaint();
		}
		if(e.getSource().equals(blackNameChange)) {
			String newBlackName = blackNameTX.getText();
			blackNameTX.setText("");
			blackStr = (newBlackName + " Wins: ");
			blackWins.setText(blackStr+blackWinCount);
	
		}
		if(e.getSource().equals(whiteNameChange)) {
			String newWhiteName = whiteNameTX.getText();
			whiteNameTX.setText("");
			whiteStr = (newWhiteName + " Wins: ");
			whiteWins.setText(whiteStr+whiteWinCount);
		
		}
		if(e.getSource().equals(endTurn)) {
			endTurn();
			frame.repaint();
		}
	}
	public void endTurn(){
		int player = turn%2;
		int oppositePlayer;
		if(player == 0){
			player = 2;
		}
		if(player == 2) {
			oppositePlayer = 1;
		}
		else {
			oppositePlayer= 2;
		}
		for (int i = 0; i < panel.board.length; i++) {
			for (int j = 0; j < panel.board.length; j++) {
				
				System.out.println("i: " + i + "\t j: "+ j + "\t player: " + player);
				System.out.println(panel.isValid(player,i,j));
				if(panel.isValid(player,i,j)) {
					JOptionPane.showMessageDialog(frame, "There is a vaild move");
					return;
				}
				
			}
		}
		for (int i = 0; i < panel.board.length; i++) {
			for (int j = 0; j < panel.board.length; j++) {
				if(panel.isValid(oppositePlayer,i,j)) {
					turn++;
					panel.turn++;
					if(turn%2 == 1) {
						turn2.setText("Black");
					}
					else {
						turn2.setText("White");
					}
					return;
			}
		}
		}
		int blacks = 0;
		int whites = 0;
		for (int i = 0; i < panel.board.length-1; i++) {
			for (int j = 0; j < panel.board.length; j++) {
				if(panel.board[i][j] == BLACK) {
					blacks++;
				}
				else if(panel.board[i][j] == WHITE) {
					whites++;
				}
				if(whites>blacks) {
					panel.resetBoard();
					JOptionPane.showMessageDialog(frame, "White is the winner!");
					whiteWinCount++;
					whiteWins.setText(whiteStr+whiteWinCount);
					return;
				}else {
					panel.resetBoard();
					JOptionPane.showMessageDialog(frame, "Black is the winner!");
					blackWinCount++;
					blackWins.setText(blackStr+blackWinCount);
					return;

				}
			}
		}
	System.out.println("");
	}
}
