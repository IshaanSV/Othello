package Othello;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	final int BOARD_HEIGHT = 720;
	final int BOARD_WIDTH = 720;
	
	
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
	}
	
}