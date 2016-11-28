package evolution;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Test {
	

	public static void main(String[] args) {
		int[][] test = new int[5][5];
	    test[2][2] = 1;
	    test[1][2] = 2;
	    for(int i=0;i<5;i++){
	    	for(int j=0;j<5;j++){
	    		System.out.print(test[i][j]);
	    	}
	    	System.out.println();
	    }
	    
	  
	    
		
		/*Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				int i =2;
				// TODO Auto-generated method stub
				while(true){
					i++;
				}
			}
			
		});*/
		//t1.start();
		//if(t1.isAlive()){
		//	System.out.println("running");
		//}else{
		//	System.out.println("not running");
		//}
		
		// TODO Auto-generated method stub
		/*JFrame f = new JFrame(); 
		f.setVisible(true);
		f.setSize(200, 200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_UP){
					System.out.println("asss");
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}});*/
	}
}
