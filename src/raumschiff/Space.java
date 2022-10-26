package raumschiff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Space extends KeyAdapter{

	private JFrame frame;
	JPanel space;
	private Raumschiff orion = new Raumschiff();
	private Kacka kack = new Kacka();
	private Set<Integer> pressed = Collections.synchronizedSet(new HashSet<Integer>());
	private Timer timer;
	private Bullet b = new Bullet(orion);


	
	public Space() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setLocation(200, 100);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
	
		
		space = new JPanel();
		space.setBackground(new Color(0x000000));
		
		space.setLayout(null);
		
		//Sterne
		for(int i = 0; i < 100; i++) {
			JPanel stern = new JPanel();
			stern.setSize(1, 1);
			stern.setBackground(new Color(0xFFFFFF));
			Random r = new Random();
			stern.setLocation(r.nextInt(600), r.nextInt(600));
			space.add(stern);
		}
		space.add(kack);
		space.add(orion);
		space.setComponentZOrder(orion, 0);

		kack.setVisible(true);
		
		frame.add(space, BorderLayout.CENTER);
		frame.addKeyListener(this);
		
		ActionListener aL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pressed.contains(KeyEvent.VK_SPACE)) {
					space.add(b);
					b.setX(orion.getX()+80);
					b.setY(orion.getY()+20);
					if(b.getX()>600) space.remove(b);

				}
				if (pressed.contains(KeyEvent.VK_W)) {
					orion.setySpeed(orion.getySpeed()-1);
				}
				if (pressed.contains(KeyEvent.VK_S)) {
					orion.setySpeed(orion.getySpeed()+1);
				}
				if (pressed.contains(KeyEvent.VK_A)) {
					orion.setxSpeed(orion.getxSpeed()-1);
				}
				if (pressed.contains(KeyEvent.VK_D)) {
					orion.setxSpeed(orion.getxSpeed()+1);
				}
				b.shoot();
				b.setLocation(b.getX(), b.getY());
			}
		};
		timer = new Timer(20, aL);
		timer.start();
		
		createThread();
		frame.setVisible(true);
		
		JLabel lblScore = new JLabel("Scooore");
		lblScore.setBounds(250, 550, 100, 15);
		frame.add(lblScore);
	}
	
	private void createThread() {
		//Hier wird der Thread erzeugt, der sich um die BEwegung des Raumschiffs kümmert
		Thread moveT = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(100);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					orion.move();
					orion.setLocation(orion.getX(), orion.getY());
					kack.move();
					kack.setLocation(kack.getX(), kack.getY());

					collisionRK();
					collisionBK();
				}
			}
			
		});
		moveT.start();
	}
	
	public void collisionRK() {
		Random r = new Random();
		if((kack.getX() < orion.getX()+130 && kack.getX() > orion.getX()) &&
                (kack.getY() > orion.getY()-30 && kack.getY() < orion.getY()) ){
            System.out.println("Collison Schiff");
            kack.setY(r.nextInt(600));
            kack.setX(600);
        }
        if((orion.getX() < kack.getX()+50 && orion.getX() > kack.getX()) &&
                (orion.getY() > kack.getY()-50 && orion.getY() < kack.getY()) ){
            System.out.println("Collison Splat");
            kack.setY(r.nextInt(600));
            kack.setX(600);
        }
        if(kack.getX() <= 0){
            System.out.println("ENde");
            kack.setX(600);
            kack.setY(r.nextInt(580));
        }
					
	}
	
	public void collisionBK() {
		Random r = new Random();
		if((kack.getX() < b.getX()+20 && kack.getX() > b.getX()) &&
                (kack.getY() > b.getY()-2 && kack.getY() < b.getY()) ){
            System.out.println("Collison Bullet");
            kack.setY(r.nextInt(580));
            kack.setX(600);
        }
//        if((b.getX() < kack.getX()+50 && b.getX() > kack.getX()) &&
//                (b.getY() > kack.getY()-50 && b.getY() < kack.getY()) ){
//            System.out.println("Collison");
//            kack.setY(r.nextInt(600));
//            kack.setX(600);
//        }
	}

	public static void main(String[] args) {
		Space space = new Space();
	}


	@Override
	public void keyPressed(KeyEvent e) {
		pressed.add(e.getKeyCode());		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressed.remove(e.getKeyCode());
	}

}
