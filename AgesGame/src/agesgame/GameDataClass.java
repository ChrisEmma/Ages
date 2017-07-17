/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agesgame;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class GameDataClass {

	public int TILE_SIZE = 10;
	
	private boolean pause;
	//private boolean testing;
	private String civName;
    public double clock;
    public GameTablesClass tables;
    private MapGenerator map;
    private BuildingClass buildings[] = new BuildingClass[70];
    private int buildingCount;
    public int TerrainTiles[][] = new int[200][150];
    public int TerrainResourceCount[][] = new int[200][150];
    public int TerrainResourceType[][] = new int[200][150];
    
    List<TasksClass> tasks = new ArrayList<TasksClass>();
    private int popCount;
    private PersonClass population[] = new PersonClass[30];
    private WildClass animals[] = new WildClass[10];
    
    ArrayList<Integer>[] military = (ArrayList<Integer>[])new ArrayList[4];
    
    List<String> popupList = new ArrayList<String>();
    
    
    private int year;
    private float popGrowth;
    private boolean civFounded;
    
    private int resource[] = new int[20];
    private boolean technology[] = new boolean[20];
    private int items[] = new int[10];
    private int goods;
    private int goodsConsumptionLevel;
    
    private int research;
    
    public TimerClass timer;
            
    public GameDataClass(GameTablesClass tables) {
        this.tables = tables;   
        
        
        military[0] = new ArrayList<Integer>();
        System.out.println("Game Initilized");
    }
    public void setupNewGame(boolean testing){
    	popGrowth = 0;
        
        year = -3000;
        research = 0;
        
        map = new MapGenerator(tables);
        buildings[0] = new BuildingClass(this,0,60,15,0,false);
        buildings[1] = new BuildingClass(this,1,48,16,0,false);
        buildings[1].setConstructed(true);
        buildingCount = 2;
        population[0] = new PersonClass(this,50,15,"BUILD,PICKUP",-1);
        population[1] = new PersonClass(this,60,17,"FORAGE,PICKUP",-1);
        population[2] = new PersonClass(this,55,17,"WOODCUT,PICKUP",-1);
        population[3] = new PersonClass(this,60,20,"FORAGE,PICKUP",-1);
        population[4] = new PersonClass(this,42,16,"FORAGE,PICKUP",-1);
        popCount = 5;
        resource[0] = 10;
        resource[1] = 0;
        resource[7] = 10;
        resource[8] = 4;
        
        tasks.add(new TasksClass("WOODCUT",1));
        tasks.add(new TasksClass("WOODCUT",1));
        
        tasks.add(new TasksClass("FORAGE"));
        tasks.add(new TasksClass("FORAGE"));
        tasks.add(new TasksClass("FORAGE"));
        tasks.add(new TasksClass("FORAGE"));
        
        setFounded(false);
        goods = 10;
        goodsConsumptionLevel = 0;
        
        population[0].food = 40;
        civName = "Nomad Tribe";
        timer = new TimerClass(this,100);
        pause = false;
        
        animals[0] = new WildClass(this,false,0, 15,35);		//ADD tasks from CreatureInfo - Hunt button
        tasks.add(new TasksClass("HUNT",0,0));
        animals[1] = new WildClass(this,false,0, 17,34);
        tasks.add(new TasksClass("HUNT",0,1));
        animals[2] = new WildClass(this,false,1, 16,34);
        tasks.add(new TasksClass("HUNT",0,2));
        
        if (testing){
        	research = 15;
        	resource[7] = 60;
        	resource[8] = 30;
            resource[9] = 10;
        }
    }
    public void loadGame(){
    	//Load Map
    	//Load Civ Data
    	//Load Buildings
    	//Load People
    
    }
    public int getCivEra(){
    	if (buildings[0].getID() >= 7 && buildings[0].getConstructed()) //Need perminance: Will return false if building is further upgraded
    		return 1;
    	return 0;
    }
    public boolean hasResource(int id){
    	int count = getResource(id);
    	
    	for (int i = 0; i < popCount; i++){
    		if (population[i].task != null){
    			if (population[i].task.getString().contains("PICKUP")){
    				if (population[i].task.getObject() == id)
    					count --;
    			}
    				
    		}
    	}
    	
    	if (count > 0){
    		return true;
    	}else{
    		return false;
    	}
    }
    public void setCivName(String newName){
    	civName = newName;
    }

    public int getTerrainTile(int x, int y){
        if (x < 200 && y < 150){
            return TerrainTiles[x][y];
        }
        System.out.println("-getTerrainTile Err: x=" + x  + " y=" + y);
        return 10;
    }
    
    public void setupBuilding(int id, int x, int y, int i){
        int l = tables.getBuildingLength(id)/2;
        int w = tables.getBuildingWidth(id)/2;
        int center_x = x + l;
        int center_y = y + w;
        if (i < 0){
            i = buildingCount++;
            buildings[i] = new BuildingClass(this,id,x,y,i,false);
        }else{
            buildings[i] = new BuildingClass(this,id,x,y,i,true);
        }
        
        System.out.println("Tasking Build - " + tables.buildingCost[i]);
        for (int s = 0; s < tables.buildingCost[id].split(",").length; s++) {

            for (int t = 0; t < Integer.parseInt(tables.buildingCost[id].split(",")[s].split("x")[1]); t++) {
            	tasks.add(new TasksClass("PICKUP",i, Integer.parseInt(tables.buildingCost[id].split(",")[s].split("x")[0])));
                
                System.out.println(tasks.get(tasks.size()-1) + " added");
            }
        }
    }
    public void upgradeBuilding(int i){
        setupBuilding(buildings[i].getUpgradeID(),buildings[i].getX(),buildings[i].getY(), i);
        buildings[i].setUpgrading();
    }
    public void deleteBuilding(int bld){
    	System.out.println("Delete Building " + bld);
    	int i = 0;
    	while (i < tasks.size()){
    		if (tasks.get(i).getBuilding() == bld){

    			tasks.remove(i);
    		}else{
    		 i++;
    		}
    	}
    	
    	for (int p = 0; p < popCount; p++){
    		if (population[p].task.getBuilding() == bld){
    			population[p].task = null;
    			
    		}
    	}
    	
    	if (bld == buildingCount-1){
    		buildings[bld] = null;
    	}else{
    		for (int n = bld; n < buildingCount-1; n++){
    			buildings[n] = buildings[n+1];
    			
    			while (i < tasks.size()){
    	    		if (tasks.get(i).getBuilding() == (n+1)){
    	    			tasks.get(i).setBuilding(n);
    	    		}else{
    	    		 i++;
    	    		}
    	    	}
    			
    			
    		}
    	}
    	buildingCount--;
    }
    
    public BufferedImage getMap(int x, int y){
        return map.getTerrainMap().getSubimage(x, y, 1600, 730);
    }
    
    public void timerTick() {
    	if (pause)
    		return;
        clock += .1;
            
        for (int i = 0; i < popCount; i++){
            population[i].timeTick();
        }
        for (int i = 0; i < animalCount(); i ++){
        	animals[i].timeTick();
        }
        
        for (int i = 0; i < buildingCount; i++){
            if (!buildings[i].getConstructed())
                buildings[i].checkBuilt();
        }
        
        if (canGrow()){
	        popGrowth += (getGrowthRate()/(1000 + 100 * popCount));
                //System.out.println("PopGowth: " + popGrowth + "   fcn:" + getPopGrowth());
	        while(popGrowth >= 1){
	        	popGrowth -= 1;
	        	int i = popCount ++;
	        	System.out.println("New Pop(" + i + ")  Count - " + popCount);
	            population[i] = new PersonClass(this,100,100,"PICKUP",-1);
	        }
        }
        
        if (clock >= 100){
        	year += 1;
        	clock -= 100;
        }
    }
    public void addCraftTask(int build, int i){
    	int craft;
    	if (tables.getBuildingProduction(buildings[build].getID()).contains(",")){
    		craft = Integer.parseInt(tables.getBuildingProduction(buildings[build].getID()).split(",")[i]);
    	}else{
    		
    		craft = Integer.parseInt(tables.getBuildingProduction(buildings[build].getID()));
    	}
    	
    	tasks.add(new TasksClass("CRAFT-" + build + "-" + craft ));
    	System.out.println("Task Added: " + "CRAFT-" + build + "-" + craft );
    }
    public void addResearchPoints(int t){
    	research += t;
    }
    public void harvest(float x, float y, int res){
    	int Tile_X = Math.round( x );
    	int Tile_Y = Math.round( y );
    	map.harvest(Tile_X,Tile_Y, res);
    }
    public void addResource(int i, int amount){
        resource[i] += amount;
    }
    public int getResource(int i){
        return resource[i];
    }
    public void addItem(int i, int amount){
    	items[i] += amount;
    }
    public boolean canGrow(){
    	 if (getHousing() > popCount){
    		 return true;
    	 }else{
    		 return false;    		 
    	 }
    }
    public int getJobsAvilable(String job){
    	int count = 0;
    	for (int i = 0; i < tasks.size(); i ++){
    		if (tasks.get(i).getString().contains(job))
    			count ++;
    	}
    	for (int i = 0; i < popCount; i ++){
    		if (population[i].task != null){
	    		if (population[i].task.getString().contains(job))
	    			count ++;
    		}
    	}
    	return count;
    }
    public int getHousing(){
        int house = 0;
        for (int i = 0; i < buildingCount; i++){
            if (buildings[i].getType() != null){
                if (buildings[i].getType().contains("HOUSE") && buildings[i].getConstructed()){
                    house += buildings[i].getValue();
                }
            }
        }
        return house;
    }
    public int getWorkTime(String job, int building){
        int time = tables.getTaskTime(job);
        if (building > -1 && !job.contains("BUILD")){
            time -= buildings[building].getValue();
        }
        if (job.contains("THINK")){
        	if (technology[4])
        		time -= tables.getTechValue(4);
        }
        	
        if (time < 1)
            time = 1;
        //System.out.println("tables.getTaskTime() =  " + tables.getTaskTime(job) + "  getWorkTime(" + job  + ") = " + time);
        return time;
    }
    public int getFoodHealth(){
        int health = 0;
        //Expand for prepared meals
        if (getResource(0) > 0)
            health ++;
        if (getResource(1) > 0)
            health ++;
        if (getResource(2) > 0)
            health ++;
        if (getResource(3) > 0)
            health ++;
        
        if (health == 4){
            health = 5;//All types bonus
        }
        
        if (getResource(4) > 0)  //Cooked Food Bonus
            health += 2;
        
        if (health == 0)
            health = -2;//No food penalty
        
        
        return health;
    }
    public int getBuildingHealth(){
    	int health = 0;
    	for (int i = 0; i < getBuildingCount(); i++ ){
    		
    		if (buildings[i].getType().contains("HEALTH") && buildings[i].getConstructed()){
    			health += buildings[i].getValue();
    			break;		//TBD  Only one health building exists
    		}
    	}
    	return health;
    }
    public int getHealth(){
        int health = 1;//Base Value
        if (!hasFood())
            return 0;
        health += getFoodHealth();
        health += getBuildingHealth();
        return health;
    }
    public boolean hasFood(){
        for (int i = 0; i < 5; i++){
            if (getResource(i) > 0)
                return true;
        }
        return false;
    }
    public BuildingClass getBuilding(int i){
        return buildings[i];
    }
    public int getBuildingCount(){
        return buildingCount;
    }
    public PersonClass getPopulation(int i){
        return population[i];
    }
    public WildClass getAnimal(int i){
    	return animals[i];
    }
    public int animalCount(){
    	return 3;
    }
    public int getPopCount(){
        return popCount;
    }
    public int getYear(){
    	return year;
    }
    public float getGrowthRate(){	//Pop / 100 s
    	if (getHealth() > 0){
            return getHealth();
        }else{
            return 0;
        }
    }
    public float getPopGrowth(){
        return popGrowth;
    }
    public int getResearch(){
        return research;
    }
    public int getTechStorge(){
    	int store = 0;
    	if (technology[0]){//Symbols
    		store += 100;//TEMP
    	}
    	return store;
    }
    public int getTechUseage(){
    	int use = 0;
    	for (int i = 0; i < 20; i++){
    		if (technology[i])
    			use += tables.getTechnologyStorage(i);
    	}
    	return use;
    }
    public boolean canResearch(int id){
    	//Check prerequiste Techs
    	
    	if (tables.getTechnologyCost(id) > getResearch())
    		return false;
    	if (tables.getTechnologyStorage(id) > (getTechStorge() - getTechUseage()))
    		return false;
    	return true;
    		
    }
    
    public Point locateMapTile(int type, float FromX, float FromY){
    	FromX = FromX;
    	FromY = FromY;
        int minDist = 99999999;
        Point tile = new Point(0,0);
        for (int x = 0;x < 200;x++){
            for (int y = 0;y < 150;y++){
                if (map.TerrainResourceType[x][y] == type && map.TerrainResourceCount[x][y] > 0){
                    float d = Math.round(Math.pow(x - FromX, 2.0) + Math.pow(y - FromY,2));
                    if (d < minDist){
                        tile.x = x;
                        tile.y = y;
                        minDist = Math.round(d);
                        //System.out.println("Fount Map Resource " + type + " at " + tile + " d:" + d);
                    }
                }
            }
        }
        return tile;
    }
    public void useAllGoods(){
    	for (int i = 0; i < 5; i ++){
    		if (tables.getItemType(i) == "GOODS"){
    			goods += (items[i] * tables.getItemValue(i));
    			items[i] = 0; 
    		}
    	}
    }
    public void useGoods(int id){
		goods += (items[id] * tables.getItemValue(id));
		items[id] = 0; 
    }
    public boolean goodsConsumption(){
    	if (getCivEra() < 1)
    		return false;
    	//Testing / easy mode?
    	
    	return true;
    }
    public int getCraftingCount (int id){
    	int craft = 0;
    	for (int i = 0; i < tasks.size(); i ++){
    		if (tasks.get(i).getString().contains("CRAFT")){
    			if (tasks.get(i).getObject() == id){
    				craft ++;
    			}
    		}
    	}
    	return craft;
    }
    public void setGoods(int value){
    	goods += value;
    }
    public boolean hasResearched(int i){
    	return technology[i];
    }
    public void setResearchTech(int tech){
    	technology[tech] = true;
    	research -= tables.getTechnologyCost(tech);
    	System.out.println("Tech " + tables.getTechnologyName(tech) + ": " + technology[tech]);
    }
    public boolean redrawMap(){
        return map.resetTerrain;
    }
    public void clearRedraw(){
        map.setRedraw(false);
    }
    public boolean canBuild(int x, int y){
    	return map.canBuild(x,y);
    }
    public int getItemCount(int i){
    	return items[i];
    }
    public int getGoodsCount(){
    	return goods;
    }
    public String getCivName(){
    	return civName;
    }
    public void setGoodsConsumptionLevel(int level){
    	goodsConsumptionLevel = level;
    }
    public int getGoodsConsumptionLevel(){
    	return goodsConsumptionLevel;
    }

	public boolean isFounded() {
		return civFounded;
	}

	public void setFounded(boolean civFounded) {
		this.civFounded = civFounded;
	}

	public boolean isPaused() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
	public void killAnimal(int i){		//TBD
		animals[i] = new WildClass(this,false,0, 5,30);
	}
}

//TerrainMap.getGraphics().drawImage(tables.getTileImage(TerrainTiles[x][y]), (x * 10) , (y * 10) ,null);