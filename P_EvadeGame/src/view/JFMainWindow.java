package view;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.Controller;
import models.Boss;
import models.Enemy;
import models.Bullet;
import models.Hero;

public class JFMainWindow extends JFrame{
	private static final String NAME_APP = "RASTREADOR";
	private static final long serialVersionUID = 1L;
	private JPStats jpStats;
	private JPGameZone jpGameZone;
	private ImageIcon icon = new ImageIcon(getClass().getResource("/icon.jpg"));
	
	public JFMainWindow(ArrayList<Enemy> listEnemys,Hero player,Controller controller,ArrayList<Bullet> listBullets,Rectangle areaGame,Boss boss) {
		this.setTitle(NAME_APP);
		this.setResizable(false);
		this.setSize(areaGame.height, areaGame.width);
		this.setIconImage(icon.getImage());
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jpStats = new JPStats(player);
		jpGameZone = new JPGameZone(listEnemys,player,listBullets,controller,boss);
		this.setVisible(true);
		init();
	}

	private void init() {
		this.add(jpStats,BorderLayout.SOUTH);
		this.add(jpGameZone, BorderLayout.CENTER);
	}
	
	public void setTimeJLabel(String string){
		jpStats.setTimeJLabel(string);
	}
	
	public String getTimeJLabel(){
		return jpStats.getTimeJLabel();
	}
	
}
