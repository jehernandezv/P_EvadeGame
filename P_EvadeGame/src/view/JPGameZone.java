package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.Controller;
import models.Boss;
import models.Enemy;
import models.Bullet;
import models.Hero;

public class JPGameZone extends JPanel{
	private static final long serialVersionUID = 1L;
	private Hero player;
	private ArrayList<Enemy> listEnemys;
	private ArrayList<Bullet> listBullets;
	private Boss boss;
	
	public JPGameZone(ArrayList<Enemy> boss,Hero player,ArrayList<Bullet> listBullet,Controller controller,Boss bossMaster) {
		this.addMouseMotionListener(controller);
		this.addMouseListener(controller);
		this.setFocusable(true);
		this.player = player;
		this.listEnemys = boss;
		this.listBullets = listBullet;
		this.boss = bossMaster;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.decode("#91DC5A"));
		g.fillRect(player.x, player.y, player.width, player.height);
		
		g.setColor(Color.RED);
		for (int i = 0; i < listEnemys.size(); i++) {
			g.fillRect(listEnemys.get(i).x , listEnemys.get(i).y , listEnemys.get(i).width, listEnemys.get(i).height);
		}
		
		g.setColor(Color.BLACK);
		for (int i = 0; i < listBullets.size(); i++) {
			g.fillRect(listBullets.get(i).x, listBullets.get(i).y, listBullets.get(i).width, listBullets.get(i).height);
		}
		if(listEnemys.size() == 0){
		g.setColor(Color.BLACK);
		g.fillRect(boss.x, boss.y, boss.width, boss.height);
		}
	}

	public Hero getPlayer() {
		return player;
	}

	public void setPlayer(Hero player) {
		this.player = player;
	}
	
	
}
