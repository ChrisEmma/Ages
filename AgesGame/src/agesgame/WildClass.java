package agesgame;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

public class WildClass extends CreatureClass {
    private GameDataClass game;
    
    private int graphic;
    private int food;
    private int id;
    private boolean fightActive;
    
    public WildClass(GameDataClass game, boolean barb, int type, int x, int y) {
    	this.game = game;
    	this.x = (float)x;
        this.y = (float)y;
        target = new Point(x,y);
        fightActive = false;
        
        hp = 10; //Load from tables
        if (barb){
        	id = 10;
        	speed = .2;
        }else{
        	id = type;
        	food = 1000;
        	speed = .1;
        }
    }
    
    public BufferedImage getImage(){//TBD
    	if (id == 10){
    		return game.tables.getBarbIcon(0);
    	}
    	return null;
    }
    
    public void timeTick(){
    	food -= .1;
        
        think();
        walk();
        
        if (atLocation() && task != null){
            work();
        }
      
    }
    public void think() {
    	if (food < 1 && id < 10){
    		task = new TasksClass("GRAZE");
    		target = game.locateMapTile(1,Math.round(x),Math.round(y));
    	}
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
    public void setHunt(int index){
    	fightActive = true;
    	game.tasks.add(new TasksClass("HUNT",0,index));
    }
    public void work(){
        if (task.getString().contains("WANDER")){
        	task = null;
        }else if (task.getString().contains("GRAZE")){
        	task = null;
        	food = 1500;
        }
    }
    public int getAttackDammage(){
    	int attack = 0;
    	
    	if (id > 9){ //Barbarian
    		//TBD
    		//attack += game.tables.getItemValue(weapon);
    		attack = 2;
    	}else{
    		attack += 
    	}
    	
    	
    	return attack;
    }
    
    public void hpChange(int value){
		hp += value;
		react();
	}
    public int getHP(){
    	return hp;
    }
    public String getName(){
    	return game.tables.getAnimalName(id);
    }
    public boolean getHunted(){
    	return fightActive;
    }
    
}
