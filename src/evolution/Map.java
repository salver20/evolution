package evolution;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Map extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int mapsize;
	int[][] map;
	int[][] nextmap;
	int move[][] = { { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 },
			{ 0, 1 }, { -1, 1 }, { -1, 0 } };

	public Map(int size) {
		this.mapsize = size;
		map = new int[mapsize + 2][mapsize + 2];
		nextmap = new int[mapsize + 2][mapsize + 2];
	}

	public boolean die(int x, int y) {
		int i, s;
		s = 0;
		for (i = 0; i < 8; i++) {
			// map[i][j]==1 means there is a life alive
			// map[i][j]==0 means the life is dead
			if (map[x + move[i][0]][y + move[i][1]] == 1) {
				s++;
			}
		}
		if (s >= 4) {
			return true;
		}
		if (s < 2) {
			return true;
		} else {
			return false;
		}
	}

	public boolean born(int x, int y) {
		int i, s;
		s = 0;
		for (i = 0; i < 8; i++) {
			if (map[x + move[i][0]][y + move[i][1]] == 1) {
				s++;
			}
		}
		if (s == 3) {
			return true;
		} else {
			return false;
		}
	}

	public void refresh1() {
		int i, j;
		for (i = 1; i <= mapsize; i++)
			for (j = 1; j <= mapsize; j++)
				map[i][j] = nextmap[i][j];
	}

	public void refresh2() {
		int i, j;
		for (i = 1; i <= mapsize; i++)
			for (j = 1; j <= mapsize; j++)
				nextmap[i][j] = map[i][j];
	}

	public boolean motionless() {
		int i, j;
		for (i = 1; i <= mapsize; i++)
			for (j = 1; j <= mapsize; j++)
				if (nextmap[i][j] != map[i][j])
					return false;
		return true;
	}

	@Override
	public void run() {
		while (true) {
			repaint();
			refresh2();
			for (int i = 1; i <= mapsize; i++) {
				for (int j = 1; j <= mapsize; j++) {
					if (map[i][j] == 0) {
						if (born(i, j)) {
							setNextMap(i, j, 1);
						}
					}
					if (map[i][j] == 1) {
						if (die(i, j)) {
							setNextMap(i, j, 0);
						}
					}
				}
			}
			if (motionless())
				continue;
			refresh1();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("The map is stable");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 1; i <= mapsize; i++) {
			for (int j = 1; j <= mapsize; j++) {
				if (map[i][j] == 1) {
					g.setColor(Color.BLACK);
					g.fillRect(i * 6, j * 6, 6, 6);
				} else {
					g.drawRect(i * 6, j * 6, 6, 6);
				}
			}
		}
	}

	public synchronized void setNextMap(int i, int j, int status) {
		nextmap[i][j] = status;
	}
}
