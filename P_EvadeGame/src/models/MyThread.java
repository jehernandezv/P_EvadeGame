package models;

import java.util.ArrayList;
import java.util.Iterator;


public class MyThread implements Runnable{

	private Thread thread;
	private int sleep;
	private boolean stop;
	private boolean pause;
	private ArrayList<Enemy> listEnemys;
	private Boss boss;
	private Hero hero;
	
	public MyThread(int sleep,ArrayList<Enemy> listBoss,Hero hero,Boss boss) {
		this.hero = hero;
		this.listEnemys = listBoss;
		this.sleep = sleep;
		this.boss = boss;
		thread = new Thread(this);
	}
	
	@Override
	public void run() {
		while (!stop) {
			try {
				for (Iterator<?> it = listEnemys.iterator(); it.hasNext();) {
					Enemy enemy = (Enemy) it.next();
					enemy.chase(hero.x, hero.y);
					if(enemy.intersects(this.hero)){
						it.remove();
						hero.decreaseHealth();
					}
				}
				if(listEnemys.size() == 0){
					if(hero.intersects(boss.x, boss.y, boss.height, boss.width)){
						this.stop = true;
					}else{
						boss.chase(hero.x, hero.y);
						}
				}
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized (this) {
				if (stop) {
					break;
				}
				while (pause) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void start() {
		thread.start();
	}
	
	public synchronized void stop() {
		stop = true;
		notify();
	}
	
	public synchronized void pause() {
		pause = true;
		notify();
	}
	
	public synchronized void resume() {
		pause = false;
		notify();
	}
	
	public Thread getThread() {
		return thread;
	}

	public ArrayList<Enemy> getlistEnemy() {
		return listEnemys;
	}

	public void setListBoss(ArrayList<Enemy> listBoss) {
		this.listEnemys = listBoss;
	}

	public boolean isStop() {
		return stop;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

//	public static void main(String[] args) {
//		MyThread t1 = new MyThread("Hola", 3000);
//		MyThread t2 = new MyThread("Mundo", 1000);
//		
//		t1.start();
//		try {
//			t1.getThread().join();
//			t2.getThread().join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		t2.start();
//	}
}