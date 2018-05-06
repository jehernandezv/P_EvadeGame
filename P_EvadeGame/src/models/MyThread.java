package models;

import java.util.ArrayList;


public class MyThread implements Runnable{

	private Thread thread;
	private int sleep;
	private boolean stop;
	private boolean pause;
	private ArrayList<Boss> listBoss;
	private Hero hero;
	
	public MyThread(int sleep,ArrayList<Boss> listBoss,Hero hero) {
		this.hero = hero;
		this.listBoss = listBoss;
		this.sleep = sleep;
		thread = new Thread(this);
	}
	
	@Override
	public void run() {
		while (!stop) {
			try {
				for (int index = 0; index < getListBoss().size(); index++) {
					getListBoss().get(index).chase(hero.x, hero.y);
					if(getListBoss().get(index).isColision(hero.x, hero.y)){
						this.stop();
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

	public ArrayList<Boss> getListBoss() {
		return listBoss;
	}

	public void setListBoss(ArrayList<Boss> listBoss) {
		this.listBoss = listBoss;
	}

	public boolean isStop() {
		return stop;
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