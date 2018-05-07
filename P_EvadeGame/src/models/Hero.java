package models;

import java.awt.Rectangle;


public class Hero extends Rectangle{
	private static final long serialVersionUID = 1L;
	private int step = 5;
	private GroupBullet groupBullet;


	public Hero(int x, int y ,int size) {
		super(x,y,size,size);
		groupBullet = new GroupBullet();
	}

	public void moveLeft(){
		x -= step ;
	}
	
	public void moveRigth(){
		x +=  step ;
	}
	
	public void moveUP(){
		y -= step;
	}
	
	public void moveDown(){
		y += step ;
	}

	public GroupBullet getGroupBullet() {
		return groupBullet;
	}

	public void setGroupBullet(GroupBullet groupBullet) {
		this.groupBullet = groupBullet;
	}	
	
	

}
