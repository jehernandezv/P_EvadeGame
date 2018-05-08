package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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
	private ImageIcon hero = new ImageIcon(getClass().getResource("/hero.jpg"));
	private ImageIcon bossIcon = new ImageIcon(getClass().getResource("/boss.png"));
	private ImageIcon bullet = new ImageIcon(getClass().getResource("/bullet.jpg"));
	
	public JPGameZone(ArrayList<Enemy> boss,Hero player,ArrayList<Bullet> listBullet,Controller controller,Boss bossMaster) {
		this.addMouseMotionListener(controller);
		this.addMouseListener(controller);
		this.addKeyListener(controller);
		this.setFocusable(true);
		this.player = player;
		this.listEnemys = boss;
		this.listBullets = listBullet;
		this.boss = bossMaster;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon imageNew;
		this.setBackground(Color.decode("#5D92FF"));
		Image imageAux;
		imageAux = hero.getImage();
		imageNew = new ImageIcon(imageAux.getScaledInstance(player.height, player.width,Image.SCALE_REPLICATE));
		g.drawImage(imageNew.getImage(), player.x, player.y, null);
		g.setColor(Color.RED);
		for (int i = 0; i < listEnemys.size(); i++) {
			g.fillRect(listEnemys.get(i).x , listEnemys.get(i).y , listEnemys.get(i).width, listEnemys.get(i).height);
		}
		
		imageAux = bullet.getImage();
		for (int i = 0; i < listBullets.size(); i++) {
			imageNew = new ImageIcon(imageAux.getScaledInstance(listBullets.get(i).height, listBullets.get(i).width,Image.SCALE_REPLICATE));
			g.drawImage(imageNew.getImage(),listBullets.get(i).x, listBullets.get(i).y, null);
		}
		if(listEnemys.size() == 0){
		imageAux = bossIcon.getImage();	
		imageNew = new ImageIcon(imageAux.getScaledInstance(boss.height, boss.width,Image.SCALE_REPLICATE));
		g.drawImage(imageNew.getImage(), boss.x, boss.y, null);
		}
	}

	public Hero getPlayer() {
		return player;
	}

	public void setPlayer(Hero player) {
		this.player = player;
	}
	
	
}
