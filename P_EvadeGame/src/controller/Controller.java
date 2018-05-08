package controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import persistence.SaveGameXML;
import view.JFMainWindow;
import models.Boss;
import models.Bullet;
import models.Game;
import models.GroupBoss;
import models.Hero;

public class Controller implements KeyListener, MouseListener,MouseMotionListener,ActionListener{
	private Game game;
	private JFMainWindow jfMainWindow;
	private Timer timerRefresh,timerAutoSave;
	
	public Controller() {
		initGame();
	}
	
	public void showGameOver(){
		JOptionPane.showMessageDialog(null, "Ha Perdido, Su Tiempo de juego fue de " + game.getCronometer());
		timerRefresh.stop();
		game.stop();
	}
	
	public void showWinGame(){
		JOptionPane.showMessageDialog(null, "Ha Ganado, Su Tiempo de juego fue de " + game.getCronometer());
		timerRefresh.stop();
		timerAutoSave.stop();
		game.stop();
	}
	
	public void initGame(){
		File file = new File("data/SaveGame.xml");
		game = new Game(new GroupBoss(2),(new Hero(40, 60,50)),new Rectangle(600,600),new Boss(500,500, 40));
		jfMainWindow = new JFMainWindow(game.getListBoss(),game.getHero(),this,game.getHero().getGroupBullet().getListBullets(),game.getAreaGame(),game.getBoss());
		if(file.exists()){
			Object [] opciones ={"Aceptar","Cancelar"};
			int eleccion = JOptionPane.showOptionDialog(jfMainWindow,"Se encontro un juego anterior, Desea cargarlo? ","Load?",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
			if (eleccion == JOptionPane.YES_OPTION){
				game = SaveGameXML.readGameSaved();
				jfMainWindow = new JFMainWindow(game.getListBoss(),game.getHero(),this,game.getHero().getGroupBullet().getListBullets(),game.getAreaGame(),game.getBoss());
			}
		}
			game.initGame();
			this.autoSave(game);
			this.refreshJFMainWindow();
			
		
	}
	
	public void autoSave(Game game){
		timerAutoSave = new Timer(15000, new ActionListener() {
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
					showGameOver();
				}else if(!game.getTimerCronometer().isRunning()){
					showWinGame();
				}
				jfMainWindow.repaint();
			}
		});
		timerRefresh.start();
	}
	
	public void mouseClicked(MouseEvent e) {
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
		//System.out.println("X: " + (e.getX()- game.getHero().x) + " Y: " +  (e.getY() - game.getHero().y));
	}

	
	
	public void actionPerformed(ActionEvent e) {
		switch (EAction.valueOf(e.getActionCommand())) {
		case EXITGAME:
			SaveGameXML.SaveXmlGroup(game);
			break;
		}
	}

}
