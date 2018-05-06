package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.Boss;
import models.Bullet;
import models.Hero;

public class JPGameZone extends JPanel{
	private static final long serialVersionUID = 1L;
	private Hero player;
	private ArrayList<Boss> bosses;
	private ArrayList<Bullet> listBullets;
	
	
	public JPGameZone(ArrayList<Boss> boss,Hero player,ArrayList<Bullet> listBullet) {
		this.player = player;
		this.bosses = boss;
		this.listBullets = listBullet;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.decode("#91DC5A"));
		g.fillRect(player.x, player.y, player.width, player.height);
		
		g.setColor(Color.RED);
		for (int i = 0; i < bosses.size(); i++) {
			g.fillRect(bosses.get(i).x , bosses.get(i).y , bosses.get(i).width, bosses.get(i).height);
		}
		
		g.setColor(Color.BLACK);
		for (int i = 0; i < listBullets.size(); i++) {
			g.fillRect(listBullets.get(i).x, listBullets.get(i).y, listBullets.get(i).width, listBullets.get(i).height);
		}
	}

	public Hero getPlayer() {
		return player;
	}

	public void setPlayer(Hero player) {
		this.player = player;
	}
}
