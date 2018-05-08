package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Hero;

public class JPStats extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel jLabelTimeSimulation;
	private JPHealthHero jpHealthHero;
	private JLabel jLabelTitleHealth;
	
	public JPStats(Hero hero) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBackground(Color.decode("#91DC5A"));
		jLabelTimeSimulation = new JLabel();
		jpHealthHero = new JPHealthHero(hero);
		jLabelTitleHealth = new JLabel("   Health Hero:   ");
		init();
		
	}

	private void init() {
		jLabelTimeSimulation.setForeground(Color.WHITE);
		jLabelTitleHealth.setForeground(Color.WHITE);
		jLabelTimeSimulation.setFont(new Font("Arial", Font.BOLD, 20));
		jLabelTitleHealth.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(jLabelTimeSimulation);
		this.add(jLabelTitleHealth);
		this.add(jpHealthHero);
		
	}
	
	public void setTimeJLabel(String string){
		this.jLabelTimeSimulation.setText(string);
	}
	
	public String getTimeJLabel(){
		return jLabelTimeSimulation.getText();
	}

}
