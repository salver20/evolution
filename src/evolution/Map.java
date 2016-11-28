package evolution;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Map extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int mapsize;
	int[][] map;
	int[][] nextmap;
	int life = 1;
	int flag = 0;

	int move[][] = { { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 } };
	Life life1;
	JButton addBlock = new JButton("添加山脉");
	JButton pause = new JButton("暂停");
	JButton start = new JButton("开始");
	
	JPanel buttons = new JPanel();
	

	public Map(int size) {

		this.mapsize = size;
		map = new int[mapsize + 2][mapsize + 2];
		nextmap = new int[mapsize + 2][mapsize + 2];
		this.addMouseListener(new Mouse());
		this.addKeyListener(new Key());
		life1 = new Life(mapsize / 2, mapsize / 2);
		map[life1.getX()][life1.getY()] = -1;
		addBlock.addActionListener(new MountainButton());
		pause.addActionListener(new PauseButton());
		start.addActionListener(new StartButton());
		buttons.setLayout(new FlowLayout());
		buttons.add(addBlock);
		buttons.add(pause);
		buttons.add(start);
		this.add(buttons,BorderLayout.SOUTH);
		//contentpane.add(buttons, BorderLayout.SOUTH);
		// addBlock.
	}

	public boolean die(int x, int y) {
		int i, s;
		s = 0;
		for (i = 0; i < 8; i++) {
			// map[i][j]==1 means there is a life alive
			// map[i][j]==0 means the life is dead
			if (map[x + move[i][0]][y + move[i][1]] == 1 || map[x + move[i][0]][y + move[i][1]] == -1) {
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
			if (map[x + move[i][0]][y + move[i][1]] == 1 || map[x + move[i][0]][y + move[i][1]] == -1) {
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
			
			if(flag ==1){
				
			}else{
				repaint();
				refresh2();
				for (int i = 1; i <= mapsize; i++) {
					for (int j = 1; j <= mapsize; j++) {
						if (map[i][j] == 0 || map[i][j] == -1) {
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
			
		}
		// System.out.println("The map is stable");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		for (int i = 1; i <= mapsize + 1; i++) {
			g.drawLine(10 + 10, 10 * i+50, 10 * mapsize + 20, 10 * i+50);
			g.drawLine(10 * i + 10, 10+50, 10 * i + 10, 10 * mapsize + 10+50);
		}

		for (int i = 1; i <= mapsize; i++) {
			for (int j = 1; j <= mapsize; j++) {
				if (map[i][j] == 1) {
					g.setColor(Color.BLACK);
					// g.fillArc(arg0, arg1, arg2, arg3, arg4, arg5);
					g.fillRect(j * 10 + 10, i * 10+50, 10, 10);
				} else if (map[i][j] == -1) {
					g.setColor(Color.orange);
					g.fillRect(j * 10 + 10, i * 10+50, 10, 10);
				} else if (map[i][j] == 2) {
					g.setColor(Color.RED);
					g.fillRect(j * 10 + 10, i * 10+50, 10, 10);
				} else {// g.setColor(Color.BLACK);
						// g.drawRect(i * 6, j * 6, 6, 6);}
				}
			}
		}
	}

	public synchronized void setNextMap(int i, int j, int status) {
		nextmap[i][j] = status;
	}

	class Mouse extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int j = (e.getX()) / 10 - 1;
			int i = (e.getY()) / 10 -5;
			if (map[i][j] == 2)
				return;
			setNextMap(i, j, life);
			refresh1();
			repaint();
		}

	}

	class MountainButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (life == 1)
				life = 2;
			else if (life == 2)
				life = 1;
		}
	}

	class PauseButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			flag = 1;
		}
		
	}
	class StartButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			flag = 0;
		}
		
	}
	class Key extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				int x = life1.getX();
				int y = life1.getY();
				life1.setX(x - 1);
				life1.setY(y);
				setNextMap(x - 1, y, -1);
				setNextMap(x, y, 0);
				refresh1();
				repaint();
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				int x = life1.getX();
				int y = life1.getY();
				life1.setX(x);
				life1.setY(y - 1);
				setNextMap(x, y - 1, -1);
				setNextMap(x, y, 0);
				refresh1();
				repaint();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				int x = life1.getX();
				int y = life1.getY();
				life1.setX(x);
				life1.setY(y + 1);
				setNextMap(x, y + 1, -1);
				setNextMap(x, y, 0);
				refresh1();
				repaint();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				int x = life1.getX();
				int y = life1.getY();
				life1.setX(x + 1);
				life1.setY(y);
				setNextMap(x + 1, y, -1);
				setNextMap(x, y, 0);
				refresh1();
				repaint();
			}
		}
	}
}
