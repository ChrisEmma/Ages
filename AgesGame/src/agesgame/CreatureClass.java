package agesgame;

import java.awt.Point;

public class CreatureClass {
	public double x;
	public double y;
	
	public double speed;

	public Point target;
	public TasksClass task;
	public int hp;
	public boolean dammageFlag;

	public void walk() {
		
		// speed = person speed + terrain speed
		if (!atLocation()) {

			double yCalc = target.y - y;
			double xCalc = target.x - x;
			double theta = Math.atan((yCalc) / (xCalc));
			if (xCalc < 0)
				theta += Math.PI;

			x += speed * Math.cos(theta);
			y += speed * Math.sin(theta);
		}
	}

	public boolean atLocation() {
		if (target != null) {
			if ((Math.pow(x - target.x, 2.0) + Math.pow(y - target.y, 2)) <= .2)
				return true;
		}
		return false;

	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	public Point getLocation(){
		Point loc = new Point(Math.round(Math.round(getX())),Math.round(Math.round(getY())));
		return loc;
	}
	public void hpChange(int value){
		hp += value;
		dammageFlag = true;
		//flash red
	}
	public int getHP(){
		return hp;
	}
}
