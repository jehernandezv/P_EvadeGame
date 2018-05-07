package models;

import java.util.ArrayList;
import java.util.Iterator;

public class GroupBullet {
	private ArrayList<Bullet> listBullets;

	public GroupBullet() {
		this.listBullets = new ArrayList<Bullet>();
	}

	public ArrayList<Bullet> getListBullets() {
		return listBullets;
	}

	public void setListBullets(ArrayList<Bullet> listBullets) {
		this.listBullets = listBullets;
	}
	
	
	public void removeBulletValidate(int x,int y){
		for (Iterator<?> it = listBullets.iterator(); it.hasNext();) {
			Bullet bullet = (Bullet) it.next();
			if(bullet.x > x || bullet.y > y){
				it.remove();
			}
	}
  }
}
