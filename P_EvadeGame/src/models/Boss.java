package models;

import java.awt.Rectangle;


public class Boss extends Rectangle{
	private static final long serialVersionUID = 1L;
	private int step = 5;
	
	public Boss(int x,int y,int size) {
		super(x,y,size,size);
	}

	public void chase(int xHero, int yHero){
		if(x >= xHero){
			this.moveLeft();
		}
		if(x <= xHero){
			this.moveRigth();
		}
		if(y > yHero){
			this.moveUP();
		}
		if(y < yHero){
			this.moveDown();
		}
	}
	
	public boolean isColision(int x,int y){
		return this.contains(x, y);
	}
	
	public void moveLeft(){
		x -=step ;
	}
	
	public void moveRigth(){
		x += step ;
	}
	
	public void moveUP(){
		y -= step;
	}
	
	public void moveDown(){
		y += step ;
	}

}
