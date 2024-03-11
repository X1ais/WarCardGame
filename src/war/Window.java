package war;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window{
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;

	
	
	public Window() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel() {
		Image deckBack;
		String cardBackFile = "/cards/back.png";
			
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
				try {
					deckBack = ImageIO.read(getClass().getResource(cardBackFile));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(53,101,77));
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
