package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import persistence.SaveGameXML;
import utility.Utility;
import view.JFMainWindow;
import models.Bullet;
import models.Game;
import models.GroupBoss;
import models.Hero;

public class Controller implements KeyListener, MouseListener,MouseMotionListener,ActionListener{
	private Game game;
	private JFMainWindow jfMainWindow;
	private Timer timerRefresh,timerAutoSave;
	
	public Controller() {
		game = new Game(new GroupBoss(2),(new Hero(40, 60,50)));
		jfMainWindow = new JFMainWindow(game.getListBoss(),game.getHero(),this,game.getHero().getGroupBullet().getListBullets());
		game.initGame();
		this.autoSave(game);
		this.refreshJFMainWindow();
	}
	
	public void showStop(){
		JOptionPane.showMessageDialog(null, "Ha Perdido, Su Tiempo de juego fue de " + game.getCronometer());
		timerRefresh.stop();
		game.stop();
	}
	
	public void autoSave(Game game){
		timerAutoSave = new Timer(5000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveGameXML.SaveXmlGroup(game);
				System.out.println("guardo");
			}
		});
		timerAutoSave.start();
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

	public void keyReleased(KeyEvent e) {
		
	}
	
	public void refreshJFMainWindow(){
		timerRefresh = new Timer(5, new ActionListener(){
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
		System.out.println("Grados: " + ( 360 - Utility.atPolar(e.getX(), e.getY())));
		game.getHero().getGroupBullet().getListBullets().add(new Bullet(0, (short) 10, game.getHero().x,game.getHero().y));
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		System.out.println("X: " + (e.getX()- game.getHero().x) + " Y: " +  (e.getY() - game.getHero().y));
	}

	
	
	public void actionPerformed(ActionEvent e) {
		switch (EAction.valueOf(e.getActionCommand())) {
		case EXITGAME:
			SaveGameXML.SaveXmlGroup(game);
			break;
		}
	}

}
