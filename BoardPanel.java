import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	final int BOARD_HEIGHT = 800;
	final int BOARD_WIDTH = 800;
	
	
	public BoardPanel() {
		super();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		g.setColor(Color.WHITE);
		for (int i = 100; i < BOARD_WIDTH; i += 100) {
			g.drawLine(i, 0, i ,BOARD_HEIGHT);
		}
		for(int i = 100; i < BOARD_HEIGHT; i+=100) {
			g.drawLine(0, i, BOARD_WIDTH, i);
		}
	}
	
}
