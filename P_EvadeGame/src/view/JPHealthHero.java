package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import models.Hero;

public class JPHealthHero extends JPanel{
	private static final long serialVersionUID = 1L;
	private Hero hero;
	private Font styleHp = new Font("Arial", Font.BOLD, 20);

	public JPHealthHero(Hero hero) {
		this.setLayout(new FlowLayout());
		this.hero = hero;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(hero.getHealth() != 100){
			g.setColor(Color.RED);
		
			g.fillRect(this.getWidth() * hero.getHealth() /100, 0,  this.getWidth() * hero.getHealth(), this.getHeight());
		}
		g.setColor(Color.WHITE);
		g.setFont(styleHp);
		g.drawString(String.valueOf(hero.getHealth()) + " % ", (this.getWidth()/2) - 10, (this.getHeight()/2) + 10);
	}
	

	
	
}
