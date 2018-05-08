package models;

import java.util.ArrayList;
import java.util.Random;

public class GroupBoss {
	ArrayList<Enemy> bosses;

	public GroupBoss(int cant) {
		bosses = new ArrayList<Enemy>();
		generateBoss(cant);
	}
	
	public GroupBoss(){
		bosses = new ArrayList<Enemy>();
	}
	
	public void generateBoss(int cant){
		Random random = new Random();
		for (int i = 0; i < cant; i++) {
			if(bosses.size() == 0){
				bosses.add(new Enemy(random.nextInt(800), random.nextInt(800), 50));
			}else {
				Enemy boss = new Enemy(random.nextInt(800), random.nextInt(800), 50);
				if(!validatePositionBoss(boss)){
				    bosses.add(boss);
				}
		    }
		}
	}
	
	public boolean validatePositionBoss(Enemy boss){
		boolean flag = false;
		for (int i = 0; i < this.getBosses().size(); i++) {
			if(getBosses().get(i).contains(boss.x, boss.y)){
				flag = true;
			}
		}
		return flag;
	}

	public ArrayList<Enemy> getBosses() {
		return bosses;
	}

	public void setBosses(ArrayList<Enemy> bosses) {
		this.bosses = bosses;
	}
}
