package models;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;

public class Game{
	private GroupBoss groupBoss;
	private Hero hero;
	private MyThread moveFigures;
	private Timer timerCronometer;
	private Timer bullet;
	private String cronometer;
	private LocalDateTime cronometerGame;
	private Rectangle areaGame;
	private Boss boss;
	
	public Game(GroupBoss groupBoss, Hero hero,Rectangle areaGame,Boss boss) {
		this.groupBoss = groupBoss;
		this.hero = hero;
		this.areaGame = areaGame;
		this.boss = boss;
		moveFigures = new MyThread(30,groupBoss.getBosses(),hero,boss);
	}
	
	public void stop(){
		timerCronometer.stop();
		moveFigures.stop();
	}
	
	public void initGame(){
		moveFigures.start();
		cromometer();
		bulletUpdate();
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
	
	public void bulletUpdate(){
		this.bullet = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Recorre el grupo de enemigos
					for (Iterator<?> it = groupBoss.getBosses().iterator(); it.hasNext();) {
						Enemy enemy = (Enemy) it.next();
						//Recorre el grupo de disparos del heroe
					for (Iterator<?> it2 = hero.getGroupBullet().getListBullets().iterator(); it2.hasNext();) {
						Bullet bullet = (Bullet) it2.next();
							if(enemy.intersects(bullet.x, bullet.y, bullet.height, bullet.width)){
								it2.remove();
								if(enemy.getHealth() - bullet.getDamage() == 0){
									it.remove();
								}else{
									enemy.decreaseHealth(bullet.getDamage());
								}
							}
							
							
						}
					}
					for (Iterator<?> it3 = hero.getGroupBullet().getListBullets().iterator(); it3.hasNext();) {
						Bullet bullet = (Bullet) it3.next();
							if(bullet.x + 5 > areaGame.height || bullet.y + 5 > areaGame.width){
								it3.remove();
							}else{
								//Evaluar daño al boss master
								if(boss.intersects(bullet.x, bullet.y, bullet.height, bullet.width)){
									it3.remove();
									if(boss.getHealth() - bullet.getDamage() == 0){
										timerCronometer.stop();;
									}else{
										boss.decreaseHealth(bullet.getDamage());
									}
								}
									
								//Mover disparos
								try {
									bullet.move(5);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
						}
					
					}
				});
				bullet.start();
	}

	public ArrayList<Enemy> getListBoss(){
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

	public Rectangle getAreaGame() {
		return areaGame;
	}

	public void setAreaGame(Rectangle areaGame) {
		this.areaGame = areaGame;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}
	
	

}
