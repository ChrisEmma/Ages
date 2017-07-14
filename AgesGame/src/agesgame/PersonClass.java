/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agesgame;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Random;

public class PersonClass extends CreatureClass{

	public int GOODS_TOTAL = 1000;
	
    private GameDataClass game;
    //public double x;
    //public double y;
    public float food;
    public float happy;
    private int goods;
    private int goodsLevel;
    public float workCount;
    private int graphic;
    
    public int weapon;
    
    private int idleCount; 
    
    public String jobsAllowd;
    

    
    private BufferedImage image;
    private BufferedImage imageCarry;
    //private BufferedImage imageDeliver;

    public PersonClass(GameDataClass game, int x, int y, String job, int g) {
        this.game = game;
        jobsAllowd = job;
        workCount = 0;
        happy = 5;
        this.x = (float)x;
        this.y = (float)y;
        speed = .3;
        target = new Point(x,y);
        food = 150;
        goods = 1000;
        goodsLevel = 1;
        
        if (g < 0){
            Random rand = new Random();
            int n = rand.nextInt(3);
            graphic = n;
        }

        //imageCarry = game.tables.getPersonIcon(graphic);
        //imageCarry.getGraphics().drawImage(game.tables.getCarryIcon().getScaledInstance(20, 20, 0), 2 , 8,null);
        //imageCarry.getGraphics().dispose();
                
        //image= game.tables.getPersonIcon(graphic);        
    }
    public BufferedImage getImage(){
        /*
    	//game.tables.getPersonIcon(graphic).flush();
    	//game.tables.getPersonIcon(graphic).getGraphics().dispose();
        //BufferedImage icon = game.tables.getPersonIcon(graphic);
        //icon.flush();
        
        //icon.getGraphics().dispose();
        if (task != null){
            if (task.contains("CARRY")){
            	return game.tables.getPersonCarryIcon(graphic);
                //icon.getGraphics().drawImage(game.tables.getCarryIcon().getScaledInstance(20, 20, 0), 2 , 8,null);
                //icon.getGraphics().finalize();
            }/*else
            if(task.contains("DELIVER")){
                icon.getGraphics().drawImage(game.tables.getDeliverIcon().getScaledInstance(20, 20, 0), 2 , 8,null);
            }else{
            
            	//icon.getGraphics().drawImage(game.tables.getPersonIcon(graphic), 0, 0, null);
            }
        }else{
        	
        }
        //icon.re*/
        //return icon;
             
       return game.tables.getPersonIcon(graphic + game.getCivEra()*10);
        
    }
    public void timeTick(){
        food -= .1;
        goods -= 1;
        
        if (happy != getHappyLevel()){
        	if (happy > getHappyLevel()){
        		happy -= .001;
        	}else{
        		happy += .001;
        	}
        }
        think();
        walk();
        
        if (atLocation() && task != null){
            work();
        }
        
        
    }

    public void work(){
        workCount -= .1;
        if (workCount <= 0){
            if (task.getString().contains("BUILD")){
                game.getBuilding(task.getBuilding()).buildTick();
                //System.out.println("Building-" + buildID + "  " +game.getBuilding(buildID).getBuildCount() + "  "+ game.getBuilding(buildID).showBuildPercent()); 
                task = null;
            }else if (task.getString().contains("WOODCUT")){
                game.addResource(7,1);
                game.harvest(Math.round(x),Math.round(y),7);
                game.popupList.add("RES7" + "-" + Math.round(x) + "," + Math.round(y));
                game.tasks.add(task);   
                task = null;
            }else if (task.getString().contains("DIGSTONE")){
                game.addResource(8,1);
                game.popupList.add("RES8" + "-" + Math.round(x) + "," + Math.round(y));
                game.tasks.add(task);   
                task = null;
            }else if (task.getString().contains("DIGCLAY")){
                game.addResource(9,1);
                game.popupList.add("RES9" + "-" + Math.round(x) + "," + Math.round(y));
                game.tasks.add(task);   
                task = null;
            }else if (task.getString().contains("FARM")){
                game.addResource(0,1);//Make Dynamic OR Split Farm Tasks- Grain/Veg
                game.popupList.add("RES0" + "-" + Math.round(x) + "," + Math.round(y));
                game.tasks.add(task);   
                task = null;
            }else if (task.getString().contains("FORAGE")){
                int res = 1;//TBD randomize yeilds
                game.tasks.add(task);   
                game.harvest(Math.round(x),Math.round(y),res);
                task = new TasksClass("CARRY",0,res,1);
                findResource(res);
            }else if (task.getString().contains("PICKUP")){     //PICKUP -BUILDING ID- RESOURCE ID
                game.addResource(task.getObject(),-1);
                task = new TasksClass("DELIVER", task.getBuilding(), task.getObject());
                workCount = 1;                          //DELIVER - BUILDING ID- RESOURCE ID 
                target.setLocation(game.getBuilding(task.getBuilding()).center());
                System.out.println("Task Set: " + task.getString() +  target); 
            }else if (task.getString().contains("DELIVER")){    //Used for building materials
                game.tasks.add(new TasksClass("BUILD",task.getBuilding()));  
                task = null;
            }else if (task.getString().contains("CARRY")){    //CARRY-RES-QTY    Used for harvesting food
                game.addResource(task.getObject(),task.getCount());
                System.out.println("Adding " + task.getCount() + " to Res(" + task.getObject() + ")");
                game.popupList.add("RES" + task.getObject() + "-" +  Math.round(x) + "," + Math.round(y));
                task = null;
            }else if (task.getString().contains("EAT")){
                game.addResource(task.getObject(),-1);
                if (task.getObject() < 4){
                    food += 80;
                    if (food > 150)
                        food = 150;
                }else{
                    food = 150;
                }
                game.popupList.add("EAT" + "-" +  Math.round(x) + "," + Math.round(y));
                task = null;
            }else if (task.getString().contains("THINK")){
                game.addResearchPoints(1);
                game.popupList.add("TEC0" + "-" + Math.round(x) + "," + Math.round(y));
                game.tasks.add(task);   
                task = null;
            }else if (task.getString().contains("COOK")){
            	int meals = 0;
            	for (int f = 0; f < 4; f ++){
	            	if (game.getResource(f) > 0){
	            		game.addResource(f,-1);
	            		meals += 1;
	            	}
            	}
            	game.addResource(4,meals);
            	game.popupList.add("RES4" + "-" +  Math.round(x) + "," + Math.round(y));
                game.tasks.add(task);   
                task = null;
            }else if (task.getString().contains("CRAFT")){  //CRAFT - BUILDING ID- ITEM ID 
            	
            	//tables. itemCost
            	//game.addResource(res,-1);
            	
            	game.addItem(task.getObject(),1);
            	//game.popupList.add("RES4" + "-" +  Math.round(x) + "," + Math.round(y));
                task = null;
                
            }else if (task.getString().contains("GOODS")){ 
            	consumeGoods();
                task = null;
                game.popupList.add("GOODS" + "-" +  Math.round(x) + "," + Math.round(y));
            }else if (task.getString().contains("EQUIPT")){
            	int wep = task.getObject();
            	task = null;
            	if (game.getItemCount(wep) > 0){
            		game.addItem(wep, -1);
            		weapon = wep;
            		
            	}else{
            		checkWeapon();
            	}
            }else if (task.getString().contains("HUNT")){
            	int animal =  task.getObject();
            	game.getAnimal(animal).hpChange(-game.tables.getItemValue(weapon));
            	System.out.println("Animal(" + animal + ") Dammage Taken: " + game.tables.getItemValue(weapon) + "   HP:" + game.getAnimal(animal).getHP());
            	//Battle Popup	 game.popupList.add("GOODS" + "-" +  Math.round(x) + "," + Math.round(y));
            	// OR	hurt object - flash red 
            	
            	if (game.getAnimal(animal).getHP() < 1){
            		int foodcount = 8; //TBD load from game tables
            		task = new TasksClass("CARRY",0,2,foodcount);
                    findResource(2);
            		
                    game.killAnimal(animal);
            		jobsAllowd = "MILITARY";
            	}
            }else if (task.getString().contains("WANDER")){
            	task = null;
            }
            
        }
    }
    public void think() {
    	if (goods < 100 && game.getGoodsCount() > 0 && game.goodsConsumption()){
    		if (task == null || task.getString().contains("EQUIPT")){
    			//TBD Locate goods location --- TEMP - Default to building(0)
    			task = new TasksClass("GOODS");
    			target.setLocation(game.getBuilding(0).center());
    			workCount = 1;
    		}else if (!task.getString().contains("GOODS") && !task.getString().contains("EAT")){
    			if (task.getString().contains("DELIVER")) {
					task = new TasksClass("PICKUP", task.getBuilding(), task.getObject());
					game.addResource(task.getObject(), 1);
				}
    			game.tasks.add(0, task);
    			task = new TasksClass("GOODS");
    			target.setLocation(game.getBuilding(0).center());
    			workCount = 1;
    		}
    	}
    	if (goods < 1)
    		goodsLevel = 0;
		if (food < 20 && game.hasFood()) {
			if (task == null || task.getString().contains("EQUIPT")) {
				task = new TasksClass("EAT",0,getBestFood());
				findResource(getBestFood());
				workCount = 1;
			} else if (!task.getString().contains("EAT")) {
				if (task.getString().contains("DELIVER")) {
					task = new TasksClass("PICKUP", task.getBuilding(), task.getObject());
					game.addResource(task.getObject(), 1);
				}
				game.tasks.add(0, task);
				task = new TasksClass("EAT",0,getBestFood());
				findResource(getBestFood());
				workCount = 1;
			}
		}

        if (task == null) {
            for (int i = 0; i < game.tasks.size(); i++) {
                String currentTask = game.tasks.get(i).getString();

                if (jobsAllowd.contains(currentTask)) {
                    if (currentTask.contains("PICKUP")) {
                        if (game.hasResource(game.tasks.get(i).getObject())) {
                            findResource(game.tasks.get(i).getObject());
                            task = game.tasks.remove(i);
                            idleCount = 0;
                            workCount = 1;
                            System.out.println("Task Set: " + task.getString() + "  Resource at " + target); 
                            break;
                        }
                    }else if (currentTask.contains("CARRY")){
                        task = game.tasks.remove(i);
                        idleCount = 0;
                        workCount = 1;
                        findResource(game.tasks.get(i).getObject());
                        System.out.println("Task Set: " + task.getString() + " to " + target); 
                        break;
                    }else if (currentTask.contains("FORAGE")){
                        task = game.tasks.remove(i);
                        idleCount = 0;
                        workCount = game.getWorkTime(task.getString(),-1);;
                        target = game.locateMapTile(1,Math.round(x),Math.round(y));

                        System.out.println("Task Set: " + task.getString() + " to " + target + "  TIME: " + workCount); 
                        break;
                    }else if (currentTask.contains("HUNT")){
                        task = game.tasks.remove(i);
                        idleCount = 0;
                        workCount = 1;
                        System.out.println("Task Set: " + task.getString() + " to " + target + "  TIME: " + workCount); 
                        break;
                    }else{
                        task = game.tasks.remove(i);
                        idleCount = 0;
                        //System.out.println("---TASK: " + task);
                        target.setLocation(game.getBuilding(task.getBuilding()).center());
                        
                        if (task.getString().contains("WOODCUT")){
                            target = game.locateMapTile(7,Math.round(x),Math.round(y));
                        }else if (task.getString().contains("DIGSTONE")){
                            target = game.locateMapTile(8,Math.round(x),Math.round(y));
                        }else if (task.getString().contains("DIGCLAY")){
                            target = game.locateMapTile(9,Math.round(x),Math.round(y));
                        }
                        workCount = game.getWorkTime(task.getString(),task.getBuilding());
                        System.out.println("Pop " + " Assigning Task: " + task.getString() + " at " + target + "  TIME: " + workCount);
                        break;
                    }

                }
            }
        }
        if (task != null){		//Follow Creature
        	if (task.getString().contains("HUNT")){
	        	target = game.getAnimal(task.getObject()).getLocation();
	        }
        }
        
        if (task == null){
	        	idleCount ++;
	        if (idleCount > 90){
	        	
	        	task = new TasksClass("WANDER");
	        	idleCount = 0;
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

    }
    public void consumeGoods(){
    	int rate = game.getGoodsConsumptionLevel();
    	
    	if (game.getGoodsCount() < rate){
    		if (game.getGoodsCount() > 0){
    			game.setGoods(-game.getGoodsCount());
            	goodsLevel = game.getGoodsCount();
            	goods += GOODS_TOTAL;
    		}else{
    			if (goods < 1)		
    				goodsLevel = 0;
    		}
    	}else{
    		game.setGoods(-rate);
        	goodsLevel = rate;
        	if (goods < 0)
        		goods = 0;
        	goods += GOODS_TOTAL;
    	}
    	
    }
    public void findResource(int res){
        int minDist = 9999999;
        
        float d;
            for (int i = 0; i < game.getBuildingCount(); i++){
                if (game.getBuilding(i).getResource().contains(res+"") && (game.getBuilding(i).getConstructed() || game.getBuilding(i).isUpgrading())){
                    //System.out.println("Found Resource-" + res + " at Building:" + i);
                    if (y < game.getBuilding(i).getY() || y > (game.getBuilding(i).getY() + game.getBuilding(i).getW())){
                        if (x < game.getBuilding(i).getX() || x > (game.getBuilding(i).getX() + game.getBuilding(i).getL())){
                            d = Math.round(Math.pow(x - game.getBuilding(i).getX(), 2.0) + Math.pow(y - game.getBuilding(i).getY(),2));
                            d = Math.min(Math.round(Math.pow(x - game.getBuilding(i).getX(), 2.0) + Math.pow(y - game.getBuilding(i).getY() - game.getBuilding(i).getW(),2)), d);
                            d = Math.min(Math.round(Math.pow(x - game.getBuilding(i).getX() - game.getBuilding(i).getL(), 2.0) + Math.pow(y - game.getBuilding(i).getY(),2)), d);
                            d = Math.min(Math.round(Math.pow(x - game.getBuilding(i).getX() - game.getBuilding(i).getL(), 2.0) + Math.pow(y - game.getBuilding(i).getY() - game.getBuilding(i).getW(),2)), d);
                            if (d < minDist){
                                minDist = Math.round(d);
                                target = new Point(game.getBuilding(i).getX() + (game.getBuilding(i).getL())/2,game.getBuilding(i).getY() + (game.getBuilding(i).getW())/2);
                            }
                        }else{
                            d = Math.min(Math.round(game.getBuilding(i).getY() - y),Math.round(y - game.getBuilding(i).getY()- game.getBuilding(i).getW()));
                            if (d < minDist){
                                minDist = Math.round(d);
                                target = new Point(game.getBuilding(i).getX() + (game.getBuilding(i).getL())/2,game.getBuilding(i).getY() + (game.getBuilding(i).getW())/2);
                            }
                        }
                    }else{
                        if (x < game.getBuilding(i).getX() || x > (game.getBuilding(i).getX() + game.getBuilding(i).getL())){
                            d = Math.min(Math.round(game.getBuilding(i).getX() - x),Math.round(x - game.getBuilding(i).getX()- game.getBuilding(i).getL()));
                            if (d < minDist){
                                minDist = Math.round(d);
                                target = new Point(game.getBuilding(i).getX() + (game.getBuilding(i).getL())/2,game.getBuilding(i).getY() + (game.getBuilding(i).getW())/2);
                            }
                        }else{
                            //Already in building
                            minDist = 0;
                            target = new Point(Math.round(Math.round(x)),Math.round(Math.round(y)));
                        }
                    }
                }
            }
    
        
    }
    public int getHappyness(){
    	return Math.round(happy);
    }
    public int getHappyLevel(){
    	if (food < 1)
    		return 0;
    	
    	int h = 5;
    	if (goodsLevel < 1 && game.goodsConsumption()){
    		h -= 3;
    	}else{
    		h += goodsLevel;
    	}
    	
    	return h;
    }
        
    public int getBestFood(){
        //Look for complete meals first
        if (game.hasResource(5))
            return 5;
        if (game.hasResource(0))
            return 0;
        if (game.hasResource(1))
            return 1;
        if (game.hasResource(2))
            return 2;
        if (game.hasResource(3))
            return 3;
        
        return 0;
    }
    public int getGoodsValue(){
    	return Math.round(goods);
    }
    public int getGoodsLevel(){
    	if (!game.goodsConsumption())
    		return 1;
    	return goodsLevel;
    }
            
    public int getWeapon(){
    	return weapon;
    }

    public void checkWeapon(){
    	for (int i = 19; i > 0; i --){
    		if (game.tables.getItemType(i) == "WEAPON" && game.getItemCount(i) > 0 && i > getWeapon()){
    			if (task == null){
    				task = new TasksClass("EQUIPT",0,i);
    			}else{
    				if (task.getString().contains("DELIVER")) {
    					task = new TasksClass("PICKUP", task.getBuilding(), task.getObject());
    					game.addResource(task.getObject(), 1);
    				}
        			game.tasks.add(0, task);
        			task = new TasksClass("EQUIPT",0,i);
    			}
    			System.out.println("Task Set:" + task.getString());
    			//Equipt weapons at Center OR Military Building
    			target.setLocation(game.getBuilding(0).center());
    			
    			break;
    		}
    	}
    }
}
