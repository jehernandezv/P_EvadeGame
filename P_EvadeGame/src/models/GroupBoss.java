package models;

import java.util.ArrayList;
import java.util.Random;

public class GroupBoss {
	ArrayList<Boss> bosses;

	public GroupBoss(int cant) {
		bosses = new ArrayList<Boss>();
		generateBoss(cant);
	}
	
	public GroupBoss(){
		bosses = new ArrayList<Boss>();
	}
	
	public void generateBoss(int cant){
		Random random = new Random();
		for (int i = 0; i < cant; i++) {
			if(bosses.size() == 0){
				bosses.add(new Boss(random.nextInt(800), random.nextInt(800), 50));
			}else {
				Boss boss = new Boss(random.nextInt(800), random.nextInt(800), 50);
				if(!validatePositionBoss(boss)){
				    bosses.add(boss);
				}
		    }
		}
	}
	
	public boolean validatePositionBoss(Boss boss){
		boolean flag = false;
		for (int i = 0; i < this.getBosses().size(); i++) {
			if(getBosses().get(i).contains(boss.x, boss.y)){
				flag = true;
			}
		}
		return flag;
	}

	public ArrayList<Boss> getBosses() {
		return bosses;
	}

	public void setBosses(ArrayList<Boss> bosses) {
		this.bosses = bosses;
	}
}
