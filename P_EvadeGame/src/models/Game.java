package models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Game{
	private GroupBoss groupBoss;
	private Hero hero;
	private MyThread moveFigures;
	private Timer timerCronometer;
	private Timer bullet;
	private String cronometer;
	
	public Game(GroupBoss groupBoss, Hero hero) {
		this.groupBoss = groupBoss;
		this.hero = hero;
		moveFigures = new MyThread(50,groupBoss.getBosses(),hero);
		moveFigures.start();
		cromometer();
		initBullet();
	}
	
	public void stop(){
		timerCronometer.stop();
		moveFigures.stop();
	}
	
	public void cromometer(){
		timerCronometer = new Timer(1000, new ActionListener() {
			byte second = 0;
			byte minute = 0;
			byte hour = 0;
			public void actionPerformed(ActionEvent e) {
				second ++;
				if(second == 60){
					minute ++;
					second = 0;
				}
				if(minute == 60){
					hour++;
					minute = 0;
				}
				cronometer = "Hour : " + hour + " Minutes : " + minute + " Second : " + second;
			}
		});
		timerCronometer.start();
	}
	
	public void initBullet(){
		this.bullet = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hero.getGroupBullet().getListBullets().size() != 0){
					for (int i = 0; i < hero.getGroupBullet().getListBullets().size(); i++) {
						try {
							hero.getGroupBullet().getListBullets().get(i).move(5);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}						
					}
				}
			}
		});
		bullet.start();
	}

	public ArrayList<Boss> getListBoss(){
		return this.groupBoss.getBosses();
	}
	
	public Hero getHero() {
		return hero;
	}
	
	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public String getCronometer() {
		return cronometer;
	}


	public Timer getTimerCronometer() {
		return timerCronometer;
	}


	public MyThread getMoveFigures() {
		return moveFigures;
	}
	
}
