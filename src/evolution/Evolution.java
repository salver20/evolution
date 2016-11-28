package evolution;

import javax.swing.JFrame;

public class Evolution extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Evolution(int size) {
		this.setLocation(0, 0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(620, 640);
		setTitle("Game of Life");
		setVisible(true);
		setResizable(false);
		Map map = new Map(size);
		this.add(map);
		map.requestFocus();

		Thread t1 = new Thread(map);
		t1.start();
	}
}
