import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Othello {
	JFrame frame = new JFrame();
	BoardPanel panel = new BoardPanel();
	
	public Othello() {
		frame.setSize(1200,1200);
		frame.setLayout(new BorderLayout());
		frame.add(panel,BorderLayout.CENTER);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		System.out.println("width: "+panel.getWidth());
		System.out.println("height: "+panel.getHeight());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Othello();
	}

}
