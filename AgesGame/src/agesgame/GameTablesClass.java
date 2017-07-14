/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agesgame;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GameTablesClass {

    private final int BLD = 30;
    public String buildingName[] = new String[BLD];
    public int buildingL[] = new int[BLD];
    public int buildingW[] = new int[BLD];
    public String buildingCost[] = new String[BLD];
    List<String> buildingMenu = new ArrayList<String>();
    public String buildingTask[] = new String[BLD];
    public String buidlingResStrore[] = new String[BLD];
    public BufferedImage buildingImage[] = new BufferedImage[BLD];
    public int buildingValue[] = new int[BLD];
    public String buildingType[] = new String[BLD];
    public int buildingUpgrade[] = new int[BLD];
    public int bldRequiredTech[] = new int[BLD];
    public String buildingProduction[] = new String[BLD];
    

    private final int TEC = 20;
    public String technologyName[] = new String[TEC];
    public int technologyCost[] = new int[TEC];
    public int technologyStore[] = new int[TEC];
    public int techPrerequ[] = new int[TEC];
    public int technologyValue[] = new int[TEC];
    public String techDescript[] = new String[TEC];
    
    private final int ITEM = 20;
    public String itemName[] = new String[ITEM];
    public String itemType[] = new String[ITEM];
    public int itemValue[] = new int[ITEM];
    public int itemTime[] = new int[ITEM];
    
    private final int ANIMAL = 5;
    public String animalName[] = new String[ANIMAL];
    
    public BufferedImage unbuiltImage[] = new BufferedImage[10];

    public BufferedImage tileImage[] = new BufferedImage[25];

    public BufferedImage foodIcon[] = new BufferedImage[5];

    public String resource[] = new String[20];
    public BufferedImage resourceIcon[] = new BufferedImage[10];
    public BufferedImage researchIcon[] = new BufferedImage[5];

    public BufferedImage person[] = new BufferedImage[20];
    public BufferedImage personCarry;
    public BufferedImage personDeliver;
    public BufferedImage happy[] = new BufferedImage[11];
    
    public BufferedImage map0;
    
    public BufferedImage researchPoints;
    public BufferedImage popIcon;
    public BufferedImage upIcon;
    public BufferedImage downIcon;
    public BufferedImage goodsIcon;
    public BufferedImage eatIcon;
    
    
    private BufferedImage genericBuilding;
    private BufferedImage genericItem;
    

    public Color color_grey1;
    public Color color_grey2;
    public Color color_grey3;
    public Color color_red;

    public void MockLoad() {

    	System.out.println(System.getProperty("user.dir"));

    	String path = System.getProperty("user.dir") + "/Graphics/";

        resource[0] = "Grain";
        resource[1] = "Produce";
        resource[2] = "Meat";
        resource[3] = "Fish";
        resource[4] = "Meal";

        resource[7] = "Wood";
        resource[8] = "Stone";
        resource[9] = "Clay";
        resource[10] = "Cloth";
        resource[11] = "Planks";
        resource[12] = "Ore";

        buildingName[0] = "Small Camp";
        buildingL[0] = 11;
        buildingW[0] = 11;
        buildingCost[0] = null;
        buildingTask[0] = null;
        buildingType[0] = "CENTER";
        buidlingResStrore[0] = "0,1,2,3,4,7,8,9";
        buildingUpgrade[0] = 4;
        buildingProduction[0] = "0,2";

        buildingName[1] = "Simple Woodcutting";
        buildingL[1] = 4;
        buildingW[1] = 4;
        buildingCost[1] = "7x4";
        buildingTask[1] = "WOODCUT";
        buildingType[1] = "WOODCUT";
        buildingValue[1] = 1;
        buidlingResStrore[1] = "7";
        buildingUpgrade[1] = 9;

        buildingName[2] = "Tent";
        buildingL[2] = 3;
        buildingW[2] = 3;
        buildingCost[2] = "7x6";
        buidlingResStrore[2] = null;
        buildingType[2] = "HOUSE";
        buildingValue[2] = 4;

        buildingName[3] = "Growing Site";
        buildingL[3] = 5;
        buildingW[3] = 5;
        buildingCost[3] = "7x10";
        buildingTask[3] = "FARM";
        buildingType[3] = "FARM";
        buildingValue[3] = 1;
        buildingUpgrade[3] = 10;

        buildingName[4] = "Camp";
        buildingL[4] = 11;
        buildingW[4] = 11;
        buildingCost[4] = "7x10,8x4";
        buidlingResStrore[4] = "0,1,2,3,4,7,8,9";
        buildingUpgrade[4] = 7;
        buildingProduction[4] = "0,2";
        buildingType[4] = "CENTER";

        buildingName[5] = "Stone Collector";
        buildingL[5] = 4;
        buildingW[5] = 4;
        buildingCost[5] = "7x4";
        buildingTask[5] = "DIGSTONE";
        buildingType[5] = "DIGSTONE";
        buidlingResStrore[5] = "8";
        buildingValue[5] = 1;
        buildingUpgrade[5] = 18;

        buildingName[6] = "Stone Circle";
        buildingL[6] = 5;
        buildingW[6] = 5;
        buildingCost[6] = "8x10";
        buildingTask[6] = "THINK";
        buildingType[6] = "THINK";

        buildingName[7] = "Basic Settlement";
        buildingL[7] = 11;
        buildingW[7] = 11;
        buildingCost[7] = "7x12,8x4,9x4";
        bldRequiredTech[7] = 5;  //TBD
        buidlingResStrore[7] = "0,1,2,3,4,7,8,9";
        buildingUpgrade[7] = -1;
        buildingProduction[7] = "0,2";
        buildingType[7] = "CENTER";

        buildingName[8] = "Cooking Fire";
        buildingL[8] = 4;
        buildingW[8] = 4;
        buildingCost[8] = "7x6,8x6";
        buildingTask[8] = "COOK";
        buidlingResStrore[8] = "0,1,2,3,4";
        bldRequiredTech[8] = 1;
        buildingUpgrade[8] = 21;
        buildingType[8] = "COOK";

        buildingName[9] = "Woodcutting Camp";
        buildingL[9] = 4;
        buildingW[9] = 4;
        buildingCost[9] = "7x12";
        buildingTask[9] = "WOODCUT";
        buildingType[9] = "WOODCUT";
        buildingValue[9] = 2;
        buidlingResStrore[9] = "7";
        buildingUpgrade[9] = 20;
        bldRequiredTech[9] = 2;

        buildingName[10] = "Primitive Garden";
        buildingL[10] = 5;
        buildingW[10] = 5;
        buildingCost[10] = "7x10,8x4";
        buildingTask[10] = "FARM";
        buildingType[10] = "FARM";
        buildingValue[10] = 3;
        bldRequiredTech[10] = 6;

        buildingName[11] = "Simple Wooden Hut";
        buildingL[11] = 4;
        buildingW[11] = 4;
        buildingCost[11] = "7x10";
        buidlingResStrore[11] = null;
        buildingType[11] = "HOUSE";
        buildingValue[11] = 8;
        bldRequiredTech[11] = 5;
        buildingUpgrade[11] = 12;

        buildingName[12] = "Wooden Hut";
        buildingL[12] = 4;
        buildingW[12] = 4;
        buildingCost[12] = "7x6,8x2";
        buidlingResStrore[12] = null;
        buildingType[12] = "HOUSE";
        buildingValue[12] = 10;

        buildingName[13] = "Simple Clay Hut";
        buildingL[13] = 4;
        buildingW[13] = 4;
        buildingCost[13] = "7x2,9x8";
        buidlingResStrore[13] = null;
        buildingType[13] = "HOUSE";
        buildingValue[13] = 8;
        bldRequiredTech[13] = 5;
        buildingUpgrade[13] = 14;
        
        buildingName[14] = "Clay Hut";
        buildingL[14] = 4;
        buildingW[14] = 4;
        buildingCost[14] = "7x2,8x2,9x4";
        buidlingResStrore[14] = null;
        buildingType[14] = "HOUSE";
        buildingValue[14] = 8;
        bldRequiredTech[14] = 5;
        
        buildingName[15] = "Grainary";
        buildingL[15] = 4;
        buildingW[15] = 4;
        buildingType[15] = "HEALTH";
        buildingValue[15] = 1;
        buildingCost[15] = "7x2,8x8";
        buidlingResStrore[15] = "0,1,2,3,4";
        bldRequiredTech[15] = 3;

        buildingName[16] = "Mud Pit";
        buildingL[16] = 4;
        buildingW[16] = 4;
        buildingCost[16] = "7x4";
        buildingTask[16] = "DIGCLAY";
        buildingType[16] = "DIGCLAY";
        buidlingResStrore[16] = "9";
        buildingUpgrade[16] = -1;
        
        buildingName[17] = "Fire Pit";//Early Pottery
        buildingL[17] = 3;
        buildingW[17] = 3;
        buildingCost[17] = "7x4,8x2";
        buildingType[17] = "CRAFT";
        bldRequiredTech[17] = 3;
        buildingValue[17] = 1;
        buildingUpgrade[17] = 23;
        buildingProduction[17] = "1";
        
        buildingName[18] = "Simple Quary";
        buildingL[18] = 4;
        buildingW[18] = 4;
        buildingCost[18] = "7x8,8x2";
        buildingTask[18] = "DIGSTONE";
        buildingType[18] = "DIGSTONE";
        buidlingResStrore[18] = "8";
        bldRequiredTech[18] = 8;
        buildingValue[18] = 2;
        buildingUpgrade[18] = 19;
        
        buildingName[19] = "Quary";
        buildingL[19] = 4;
        buildingW[19] = 4;
        buildingCost[19] = "7x10,8x4";
        buildingTask[19] = "DIGSTONE";
        buildingType[19] = "DIGSTONE";
        buidlingResStrore[19] = "8";
        bldRequiredTech[19] = 8;
        buildingValue[19] = 5;
        buildingUpgrade[19] = -1;
        
        buildingName[20] = "Logging Camp";
        buildingL[20] = 4;
        buildingW[20] = 4;
        buildingCost[20] = "7x12,8x6";
        buildingTask[20] = "WOODCUT";
        buildingType[20] = "WOODCUT";
        buildingValue[20] = 4;
        buidlingResStrore[20] = "7";
        buildingUpgrade[20] = -1;
        bldRequiredTech[20] = 9;
        
        buildingName[21] = "Cooking Site";
        buildingL[21] = 4;
        buildingW[21] = 4;
        buildingCost[21] = "7x4,8x8,9x4";
        buildingTask[21] = "COOK";
        buidlingResStrore[21] = "0,1,2,3,4";
        bldRequiredTech[21] = 1;
        buildingValue[21] = 3;
        buildingType[21] = "COOK";
        
        buildingName[22] = "Training Ground";
        buildingL[22] = 5;
        buildingW[22] = 5;
        buildingCost[22] = "7x6";
        buildingTask[22] = ""; //Training?
        buildingType[22] = "TRAIN";
        buildingValue[22] = 4;
        buildingUpgrade[22] = -1;
        bldRequiredTech[22] = 5;
        
        buildingName[23] = "Potter";
        buildingL[23] = 3;
        buildingW[23] = 3;
        buildingCost[23] = "7x6,8x4,9x4";
        buildingType[23] = "CRAFT";
        bldRequiredTech[23] = 3;
        buildingValue[23] = 5;
        buildingProduction[23] = "1";
        
        buildingMenu.add("2");
        buildingMenu.add("1");
        buildingMenu.add("5");
        buildingMenu.add("16");
        buildingMenu.add("3");
        buildingMenu.add("15");
        buildingMenu.add("8");
        buildingMenu.add("6");
        buildingMenu.add("11");
        buildingMenu.add("13");
        buildingMenu.add("17");
        buildingMenu.add("22");

        technologyName[0] = "Symbols";
        technologyCost[0] = 1;
        technologyStore[0] = 0;
        techDescript[0] = "+10 Tech Storage **ALPHA: 100pts";

        technologyName[1] = "Cooking";
        technologyCost[1] = 3;
        technologyStore[1] = 1;
        techDescript[1] = "Unlocks Cooking Fire";

        technologyName[2] = "Woodworking";
        technologyCost[2] = 3;
        technologyStore[2] = 2;
        techDescript[2] = "Unlocks Advanced Woodcutting";

        technologyName[3] = "Pottery";
        technologyCost[3] = 3;
        technologyStore[3] = 2;
        techDescript[3] = "Unlock Clay Buildings and Items"; //TBD

        technologyName[4] = "Numbers";
        technologyCost[4] = 5;
        technologyStore[4] = 1;
        techPrerequ[4] = 0;
        technologyValue[4] = 5;
        techDescript[4] = "Thinking Speed +10%";

        technologyName[5] = "Construction";
        technologyCost[5] = 5;
        technologyStore[5] = 3;
        techPrerequ[5] = 2;
        techDescript[5] = "Unlocks New Buildings";

        technologyName[6] = "Farming";
        technologyCost[6] = 5;
        technologyStore[6] = 3;
        techDescript[6] = "Unlocks Advanced Gardens";
        
        technologyName[7] = "Hunting Strategy";
        technologyCost[7] = 5;
        technologyStore[7] = 4;
        techDescript[7] = "Unlocks Training Ground";
        
        technologyName[8] = "Stoneworking";
        technologyCost[8] = 4;
        technologyStore[8] = 4;
        techDescript[8] = "Unlocks Advanced Stone Gathering";
        
        technologyName[9] = "Carpentry";
        technologyCost[9] = 6;
        technologyStore[9] = 4;
        techDescript[9] = "Unlocks New Buildings";
        
        technologyName[10] = "Burrial";
        technologyCost[10] = 5;
        technologyStore[10] = 1;
        techPrerequ[10] = 0;
        techDescript[10] = "-NOT ACTIVE- Unlocks Religion";
        
        technologyName[11] = "Geography";
        technologyCost[11] = 6;
        technologyStore[11] = 3;
        techPrerequ[11] = 7;
        techDescript[11] = "-NOT ACTIVE- Unlocks Map";
        
        technologyName[12] = "Arrowhead";
        technologyCost[12] = 5;
        technologyStore[12] = 3;
        techPrerequ[12] = 7;
        techDescript[12] = "-NOT ACTIVE- Unlocks New Weapons";
        
        technologyName[12] = "Animal Husbandry";
        technologyCost[12] = 7;
        technologyStore[12] = 5;
        techPrerequ[12] = 6;
        techDescript[12] = "-NOT ACTIVE- Unlocks Ranches";

        technologyName[13] = "Arithmetic";
        technologyCost[13] = 10;
        technologyStore[13] = 3;
        techPrerequ[13] = 4;
        techDescript[13] = "-NOT ACTIVE- TBD";
        
        technologyName[14] = "Mystisism";
        technologyCost[14] = 9;
        technologyStore[14] = 5;
        techPrerequ[14] = 10;
        techDescript[14] = "-NOT ACTIVE- TBD";
        
        //Wheels
        
        itemName[0] = "Basket";
        itemType[0] = "GOODS";
        itemValue[0] = 1;
        itemTime[0] = 20;
        
        itemName[1] = "Pottery";
        itemType[1] = "GOODS";
        itemValue[1] = 2;
        itemTime[1] = 15;
        
        itemName[2] = "Wooden Spear";
        itemType[2] = "WEAPON";
        itemValue[2] = 1;
        itemTime[2] = 10;
        
        itemName[3] = "Tablet"; //Clay
        itemType[3] = "TECH_STORE";
        itemValue[3] = 1;
        itemTime[3] = 20;
        
        animalName[0] = "Deer";
        animalName[1] = "Rabbit";
        
        try {
            buildingImage[0] = ImageIO.read(new File(path + "BuildingImgs/SmallCamp.png"));
            buildingImage[1] = ImageIO.read(new File(path + "BuildingImgs/Woodcut.png"));
            buildingImage[2] = ImageIO.read(new File(path + "BuildingImgs/Tent.png"));
            buildingImage[3] = ImageIO.read(new File(path + "BuildingImgs/GrowSite.png"));
            buildingImage[4] = ImageIO.read(new File(path + "BuildingImgs/Camp.png"));
            buildingImage[5] = ImageIO.read(new File(path + "BuildingImgs/Digging.png"));
            buildingImage[6] = ImageIO.read(new File(path + "BuildingImgs/StoneCircle.png"));
            buildingImage[7] = ImageIO.read(new File(path + "BuildingImgs/BasicSettlement.png"));
            
            buildingImage[8] = ImageIO.read(new File(path + "BuildingImgs/CookingFire.png"));
            
            buildingImage[9] = ImageIO.read(new File(path + "BuildingImgs/Woodcut.png"));//To be changed
            
            buildingImage[10] = ImageIO.read(new File(path + "BuildingImgs/Garden.png"));
            
            buildingImage[11] = ImageIO.read(new File(path + "BuildingImgs/SimWoodHut.png"));
            buildingImage[12] = ImageIO.read(new File(path + "BuildingImgs/WoodHut.png"));
            
            buildingImage[13] = ImageIO.read(new File(path + "BuildingImgs/Generic.png"));
            buildingImage[14] = ImageIO.read(new File(path + "BuildingImgs/Generic.png"));

            unbuiltImage[0] = ImageIO.read(new File(path + "BuildingImgs/30x30.png"));
            unbuiltImage[1] = ImageIO.read(new File(path + "BuildingImgs/40x40.png"));
            unbuiltImage[2] = ImageIO.read(new File(path + "BuildingImgs/50x50.png"));
            unbuiltImage[3] = ImageIO.read(new File(path + "BuildingImgs/110x110.png"));
            
            genericBuilding = ImageIO.read(new File(path + "BuildingImgs/Generic.png"));
        } catch (IOException e) {
            System.out.println("-LoadBuildingImages- Err: " + e.getMessage());
        }
        
        try {
            tileImage[0] = ImageIO.read(new File(path + "TerrainTiles/Grass0.png"));
            tileImage[1] = ImageIO.read(new File(path + "TerrainTiles/Grass1.png"));
            tileImage[2] = ImageIO.read(new File(path + "TerrainTiles/Grass2.png"));
            //3 , 4

            tileImage[5] = ImageIO.read(new File(path + "TerrainTiles/Dirt0.png"));
            tileImage[6] = ImageIO.read(new File(path + "TerrainTiles/Dirt1.png"));
            //7 , 8 , 9 
            
            
            
            tileImage[10] = ImageIO.read(new File(path + "TerrainTiles/Brush_Tile.png"));
            tileImage[11] = ImageIO.read(new File(path + "TerrainTiles/Brush1.png"));
            //12 , 13
            
            tileImage[14] = ImageIO.read(new File(path + "TerrainTiles/Plains0.png"));
            tileImage[15] = ImageIO.read(new File(path + "TerrainTiles/Plains1.png"));
            //16

            tileImage[17] = ImageIO.read(new File(path + "TerrainTiles/Forest_Tile.png"));
            //18 19

            tileImage[20] = ImageIO.read(new File(path + "TerrainTiles/Water_Tile.png"));
            tileImage[21] = ImageIO.read(new File(path + "TerrainTiles/Marsh_Tile.png"));

            tileImage[24] = ImageIO.read(new File(path + "TerrainTiles/Hill0.png"));
            

        } catch (IOException e) {
            System.out.println("-LoadTerrainTiles- Err: " + e.getMessage());
        }

        try {
            foodIcon[0] = ImageIO.read(new File(path + "FoodIcon0.png"));
            foodIcon[1] = ImageIO.read(new File(path + "FoodIcon1.png"));
            foodIcon[2] = ImageIO.read(new File(path + "FoodIcon2.png"));
            foodIcon[3] = ImageIO.read(new File(path + "FoodIcon3.png"));
        } catch (IOException e) {
            System.out.println("-LoadIcons- Err: " + e.getMessage());
        }

        try {
            resourceIcon[0] = ImageIO.read(new File(path + "ResourceIcons/Grain.png"));
            resourceIcon[1] = ImageIO.read(new File(path + "ResourceIcons/Produce.png"));
            resourceIcon[2] = ImageIO.read(new File(path + "ResourceIcons/Meat.png"));
            resourceIcon[4] = ImageIO.read(new File(path + "ResourceIcons/Meal.png"));
            resourceIcon[7] = ImageIO.read(new File(path + "ResourceIcons/Logs.png"));
            resourceIcon[8] = ImageIO.read(new File(path + "ResourceIcons/Stone.png"));
            resourceIcon[9] = ImageIO.read(new File(path + "ResourceIcons/Clay.png"));
            
            resourceIcon[3] = ImageIO.read(new File(path + "ResourceIcons/TBD.png"));
            resourceIcon[5] = ImageIO.read(new File(path + "ResourceIcons/TBD.png"));
            resourceIcon[6] = ImageIO.read(new File(path + "ResourceIcons/TBD.png"));
            
        } catch (IOException e) {
            System.out.println("-Resources- Err: " + e.getMessage());
        }

        try {
            researchIcon[0] = ImageIO.read(new File(path + "ResourceIcons/Thinking.png"));

            researchPoints = ImageIO.read(new File(path + "Research.png"));
        } catch (IOException e) {
            System.out.println("-Research- Err: " + e.getMessage());
        }
        
        try {
            person[0] = ImageIO.read(new File(path + "People/Ancient0.png"));
            person[1] = ImageIO.read(new File(path + "People/Ancient1.png"));
            person[2] = ImageIO.read(new File(path + "People/Ancient2.png"));

            
            person[10] = ImageIO.read(new File(path + "People/Bronze0.png"));
            person[11] = ImageIO.read(new File(path + "People/Bronze1.png"));
            person[12] = ImageIO.read(new File(path + "People/Bronze2.png"));
            
            
            personCarry = ImageIO.read(new File(path + "People/CarryFood.png"));
            personDeliver = ImageIO.read(new File(path + "People/CarryItem.png"));
        } catch (IOException e) {
            System.out.println("-Population- Err: " + e.getMessage());
        }
        
        try {
        	happy[0] =  ImageIO.read(new File(path + "People/Happy0.png"));
        	happy[1] =  ImageIO.read(new File(path + "People/Happy1.png"));
        	happy[2] =  ImageIO.read(new File(path + "People/Happy2.png"));
        	happy[3] =  ImageIO.read(new File(path + "People/Happy3.png"));
        	happy[4] =  ImageIO.read(new File(path + "People/Happy4.png"));
        	happy[5] =  ImageIO.read(new File(path + "People/Happy5.png"));
        	happy[6] =  ImageIO.read(new File(path + "People/Happy6.png"));
        	happy[7] =  ImageIO.read(new File(path + "People/Happy7.png"));
        	happy[8] =  ImageIO.read(new File(path + "People/Happy8.png"));
        	happy[9] =  ImageIO.read(new File(path + "People/Happy9.png"));
        	happy[10] =  ImageIO.read(new File(path + "People/Happy10.png"));
            
        } catch (IOException e) {
            System.out.println("-Happy- Err: " + e.getMessage());
        }
        

        try {
            popIcon = ImageIO.read(new File(path + "PopIcon.png"));
            upIcon = ImageIO.read(new File(path + "UpIcon.png"));
            downIcon = ImageIO.read(new File(path + "DownIcon.png"));
            goodsIcon = ImageIO.read(new File(path + "Goods.png"));
            eatIcon = ImageIO.read(new File(path + "Eating.png"));
            
            genericItem = ImageIO.read(new File(path + "ResourceIcons/TBD.png"));
        } catch (IOException e) {
            System.out.println("-LoadIcons- Err: " + e.getMessage());
        }
        
        try{       
        	map0 = ImageIO.read(new File(path + "/Map0.png"));
        }catch (IOException e) {System.out.println("-LoadMap- Err: " + e.getMessage());}
        
    }

    public int getTaskTime(String task) {
        if (task.contains("BUILD")) {
            return 3;
        }
        if (task.contains("WOODCUT")) {
            return 16;
        }
        if (task.contains("DIGSTONE")) {
            return 20;
        }
        if (task.contains("FORAGE")) {
            return 15;
        }
        if (task.contains("THINK")) {
            return 50;
        }
        if (task.contains("FARM")) {
            return 22;
        }
        if (task.contains("COOK")) {
            return 12;
        }
        if (task.contains("DIGCLAY")) {
            return 17;
        }
        if (task.contains("CRAFT")) {
        	System.out.println("Get Craft Time   TASK: " + task);
        	int id = Integer.parseInt(task.split("-")[2]);
            return itemTime[id];
        }

        return 10;
    }

    public BufferedImage getTileImage(int i) {
        return tileImage[i];
    }

    public String getBuildingName(int i) {
        return buildingName[i];
    }

    public int getBuildingLength(int i) {
        return buildingL[i];
    }

    public int getBuildingWidth(int i) {
        return buildingW[i];
    }

    public String getBuildCost(int i) {
        return buildingCost[i];
    }
    
    public String getBuildingCostText(int i){
    	String Cost = "";
        if (getBuildCost(i).contains(",")){
            for (int s = 0; s < getBuildCost(i).split(",").length; s++) {
            	
            	Cost += getResourceName(Integer.parseInt(getBuildCost(i).split(",")[s].split("x")[0]));
            	Cost += " x" + getBuildCost(i).split(",")[s].split("x")[1] + " ";
            	
            }
        }else{
        	Cost = getResourceName(Integer.parseInt(getBuildCost(i).split("x")[0]));
        	Cost += " x" + getBuildCost(i).split("x")[1] + " ";
        }
        return Cost;
    }
    

    public int getBuildTotal(int i) {
        if (buildingCost[i] == null) {
            return 0;
        }
        //System.out.println("Check build cost " + buildingCost[i]);
        int total = 0;
        for (int x = 0; x < buildingCost[i].split(",").length; x++) {
            total += Integer.parseInt(buildingCost[i].split(",")[x].split("x")[1]);
        }
        return total;
    }

    public String getBuildingTask(int i) {
        return buildingTask[i];
    }

    public String getBuildingType(int i) {
        return buildingType[i];
    }

    public List<String> getBuildMenuList() {
        return buildingMenu;
    }

    public int getBuildingValue(int i) {
        return buildingValue[i];
    }

    public String getBuildingResourceStore(int i) {
        if (buidlingResStrore[i] != null) {
            return buidlingResStrore[i];
        } else {
            return "";
        }

    }
    public String getBuildingProduction(int i) {
        if (buildingProduction[i] != null) {
            return buildingProduction[i];
        } else {
            return "";
        }

    }  

    public BufferedImage getBuildingImage(int i) {
    	if (buildingImage[i] != null){
    		return buildingImage[i];
    	}
    	return genericBuilding;
    	
    }

    public BufferedImage getUnbuiltImage(int i) {
        return unbuiltImage[i];
    }

    public BufferedImage getPopIcon() {
        return popIcon;
    }
    public BufferedImage getGoodsIcon() {
        return goodsIcon;
    }
    public BufferedImage getUpIcon() {
        return upIcon;
    }

    public BufferedImage getDownIcon() {
        return downIcon;
    }

    public BufferedImage getResearchPointIcon() {
        return researchPoints;
    }

    public BufferedImage getFoodIcon(int i) {
        return foodIcon[i];
    }

    public int getBuidlingUpgrade(int i) {
        return buildingUpgrade[i];
    }

    public int getBuildingTech(int i) {
        return bldRequiredTech[i];
    }

    public BufferedImage getResourceIcon(int i) {
    	if (resourceIcon[i] != null){
    		return resourceIcon[i];
    	}
    	return genericItem;
    }

    public BufferedImage getResearchIcon(int i) {
    	if (researchIcon[i] != null){
    		return researchIcon[i];
    	}
    	return genericItem;
        
    }

    public String getTechnologyName(int i) {
        return technologyName[i];
    }

    public int getTechnologyCost(int i) {
        return technologyCost[i];
    }

    public int getTechnologyStorage(int i) {
        return technologyStore[i];
    }

    public int getTechValue(int i) {
        return technologyValue[i];
    }

    public String getTechDescript(int i){
    	return techDescript[i];
    }
    
    public String getResourceName(int i) {
        return resource[i];
    }
    
    public BufferedImage getPersonIcon(int i){
        return person[i];
    }
    
    public BufferedImage getCarryIcon() {
        return personCarry;
    }
    public BufferedImage getDeliverIcon() {
        return personDeliver;
    }
    public BufferedImage getPersonCarryIcon(int i){
        BufferedImage out = person[i];
        out.getGraphics().drawImage(personCarry.getScaledInstance(20, 20, 0), 0, 20, null);
    	return out;
    }
    public BufferedImage getHappyIcon(int i) {
        return happy[i];
    }
    
    public String getItemName(int i){
    	return itemName[i];
    }
    public int getItemCost(int i){
    	return 0;
    //	return itemCost[i];
    }
    public int getItemValue(int i){
    	return itemValue[i];
    }
    public String getItemType(int i){
    	return itemType[i];
    }
    
    public BufferedImage getItemIcon(int i) {
    	//TEMP
    	return genericItem;
        //return resourceIcon[i];
    }
    public BufferedImage getEatingIcon() {
    	return eatIcon;
    }
    public BufferedImage getMap(int i){
    	return map0;
    }
            
}
