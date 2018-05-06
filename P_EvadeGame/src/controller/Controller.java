package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import utility.Utility;
import view.JFMainWindow;
import models.Bullet;
import models.Game;
import models.GroupBoss;
import models.Hero;

public class Controller implements KeyListener, MouseListener{
	private Game game;
	private JFMainWindow jfMainWindow;
	private Timer timerRefresh;
	
	public Controller() {
		game = new Game(new GroupBoss(3),(new Hero(40, 60,50)));
		jfMainWindow = new JFMainWindow(game.getListBoss(),game.getHero(),this,game.getHero().getGroupBullet().getListBullets());
		this.refreshJFMainWindow();
	}
	
	public void showStop(){
		JOptionPane.showMessageDialog(null, "Ha Perdido, Su Tiempo de juego fue de " + game.getCronometer());
		timerRefresh.stop();
		game.stop();
	}
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			game.getHero().moveUP();
			break;
		case KeyEvent.VK_LEFT:
			game.getHero().moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			game.getHero().moveRigth();
			break;
		case KeyEvent.VK_DOWN:
			game.getHero().moveDown();
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void refreshJFMainWindow(){
		timerRefresh = new Timer(50, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				jfMainWindow.setTimeJLabel(game.getCronometer());
				if(game.getMoveFigures().isStop()){
					showStop();
				}
				jfMainWindow.repaint();
			}
		});
		timerRefresh.start();
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("x " + e.getX() + "y " + e.getY());
		game.getHero().getGroupBullet().getListBullets().add(new Bullet(Utility.atPolares(e.getX(), e.getY()), (short) 10, game.getHero().x,game.getHero().y));
		System.out.println(game.getHero().getGroupBullet().getListBullets().size());
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

}
