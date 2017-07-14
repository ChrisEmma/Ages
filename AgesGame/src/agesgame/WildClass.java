package agesgame;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

public class WildClass extends CreatureClass {
    private GameDataClass game;
    
    private int graphic;

    public WildClass(GameDataClass game, boolean barb, int type, int x, int y) {
    	this.game = game;
    	this.x = (float)x;
        this.y = (float)y;
        target = new Point(x,y);
        
        hp = 10; //Load from tables
        if (barb){
        	speed = .2;
        }else{
        	speed = .1;
        }
    }
    
    public BufferedImage getImage(){//TBD
    	return null;
    }
    
    public void timeTick(){
       // food -= .1;
        
        think();
        walk();
        
        if (atLocation() && task != null){
            work();
        }
      
    }
    public void think() {
    	if (task == null) {
    		task = new TasksClass("WANDER");
    		
    		Random rand = new Random();
            int newX = rand.nextInt(10) - 5;
            int newY = rand.nextInt(10) - 5;
            
    		target.x = Math.round(Math.round(getX() + newX));
    		if (target.x < 0)
    			target.x = 0;
    		target.y = Math.round(Math.round(getY() + newY));
    		if (target.y < 0)
    			target.y = 0;
    	}
    }
    public void react(){
    	//Flee
    	
    }
    public void work(){
        if (task.getString().contains("WANDER")){
        	task = null;
        }
    }
    public void hpChange(int value){
		hp += value;
		react();
	}
    
}
