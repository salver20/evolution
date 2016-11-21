package evolution;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		
		Thread t1 = new Thread(map);
		t1.start();
		
		map.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int i = (e.getX()) / 6;
				int j = (e.getY()) / 6;
				map.setNextMap(i, j, 1);
				map.refresh1();
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

}
