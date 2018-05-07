package models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.Timer;

public class Game{
	private GroupBoss groupBoss;
	private Hero hero;
	private MyThread moveFigures;
	private Timer timerCronometer;
	private Timer bullet;
	private String cronometer;
	private LocalDateTime cronometerGame;
	
	public Game(GroupBoss groupBoss, Hero hero) {
		this.groupBoss = groupBoss;
		this.hero = hero;
		moveFigures = new MyThread(30,groupBoss.getBosses(),hero);
	}
	
	public void stop(){
		timerCronometer.stop();
		moveFigures.stop();
	}
	
	public void initGame(){
		moveFigures.start();
		cromometer();
		initBullet();
	}
	
	
	
	public void cromometer(){
		timerCronometer = new Timer(1000, new ActionListener() {
			byte second = (byte) ((cronometerGame != null)? cronometerGame.getSecond():0);
			byte minute = (byte) ((cronometerGame != null)? cronometerGame.getMinute():0);
			byte hour = (byte) ((cronometerGame != null)? cronometerGame.getHour():0);
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
		this.bullet = new Timer(10, new ActionListener() {
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

	public LocalDateTime getCronometerGame() {
		return cronometerGame;
	}

	public void setCronometerGame(LocalDateTime cronometerGame) {
		this.cronometerGame = cronometerGame;
	}
	
}
