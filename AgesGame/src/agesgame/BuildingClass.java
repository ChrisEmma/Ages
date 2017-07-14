/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agesgame;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author cpemm_000
 */

public class BuildingClass {
	private int index;
	
    public GameDataClass game;
    private int XPos;
    private int YPos;
    private boolean active;
    private int ID;
    private int buildCount;
    private boolean constructed;
    private boolean upgrading;
       
    public BuildingClass(GameDataClass game, int buildID, int x, int y, int i, boolean isUpgrading){
        this.game = game;
        this.upgrading = isUpgrading;
        ID = buildID;
        index = i;
        XPos = x;
        YPos = y;
        active = true;
        constructed = false;
        
        System.out.println("New Buidling: " + ID + "  (" + x + "," + y + ")");
        buildCount = 0;
    }
    public String getName(){
        return game.tables.getBuildingName(ID);
    }
    public int getID(){
        return ID;
    }
    public int getX(){
        return XPos;
    }
    public int getY(){
        return YPos;
    }
    public int getL(){
        return game.tables.getBuildingLength(ID);
    }
    public int getW(){
        return game.tables.getBuildingWidth(ID);
    }
    public void checkBuilt(){
        if (buildCount >= game.tables.getBuildTotal(ID)){
            constructed = true;
            upgrading = false;
            //check building tasks
            if (game.tables.getBuildingTask(ID) != null){
                game.tasks.add(new TasksClass(game.tables.getBuildingTask(ID),index));
                System.out.println("Adding Task: " + game.tables.getBuildingTask(ID) + "-" + index);
                
            }
        }
        
    }
    public String showBuildPercent(){
        float fract = 100* buildCount/game.tables.getBuildTotal(ID);
        return Math.round(fract) + "%";
    }
    public int getBuildCount(){
        return buildCount;
    }
    public boolean getConstructed(){
        return constructed;
    }
    public void buildTick(){
        buildCount ++;
        checkBuilt();
    }
    public String getResource(){
        return game.tables.getBuildingResourceStore(ID);
    }
    public String getType(){
        return game.tables.getBuildingType(ID);
    }
    public int getValue(){
        return game.tables.getBuildingValue(ID);
    }
    public void setConstructed(boolean cons){
    	constructed = cons;
    }
    public void setUpgrading(){
        constructed = false;
        upgrading = true;
    }
    public boolean isUpgrading(){
        return upgrading;
    }
    public BufferedImage getImage(){
        if (constructed){
            return game.tables.getBuildingImage(ID);
        }else if (upgrading){
            //TBD
            
            return game.tables.getBuildingImage(ID);
            
        }else{
            if (buildCount == 0){
                
            }else{
                
            }
            
            if (getL() == 30)
                return game.tables.getUnbuiltImage(0);
            if (getL() == 40)
                return game.tables.getUnbuiltImage(1);
            if (getL() == 50)
                return game.tables.getUnbuiltImage(2);
            if (getL() == 110)
                return game.tables.getUnbuiltImage(3);
            
            return game.tables.getUnbuiltImage(2);
        }
    }
    public boolean canUpgrade(){
        if (getUpgradeID() < 1)
        	return false;
        if (!constructed)
            return false;
        if (game.tables.getBuildingTech(getUpgradeID()) > 0 && !game.hasResearched(game.tables.getBuildingTech(getUpgradeID())))
        	return false;
        return true;
    }
    public int getUpgradeID(){
    	return game.tables.getBuidlingUpgrade(ID);
    }
    public String getProduction(){
    	return game.tables.getBuildingProduction(ID);
    }
    public Point center(){
    	Point out = new Point(0,0);
    	out.x = XPos + (getL()/2);
    	out.y = YPos + (getW()/2);
    	return out;
    }
}
