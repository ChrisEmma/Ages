package agesgame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

public class MapGenerator {

	private BufferedImage TerrainMap;
	public int mapTiles[][] = new int[200][150];
    public int TerrainTiles[][] = new int[200][150];
    public int TerrainResourceCount[][] = new int[200][150];
    public int TerrainResourceType[][] = new int[200][150];
    public GameTablesClass tables;
    
    public boolean resetTerrain;
    
    public MapGenerator(GameTablesClass tables){
    	this.tables = tables;
    	setupMap();
    	generateTerrainImage();
        resetTerrain = false;
    }

    
    public void generateTerrainImage(){
        TerrainMap = new BufferedImage(2000, 1000, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < 100; y ++){
            for (int x = 0; x < 200; x ++){
            	TerrainMap.getGraphics().drawImage(tables.getTileImage(mapTiles[x][y]).getScaledInstance(15, 15, 0), (x * 15) , (y * 15) ,null);
            	
            }
        }
        System.out.println("Map Image Generated");
        
    }
    public void setupMap(){
        BufferedImage loadMap = null;
        loadMap = tables.getMap(0);
        
        for (int x = 0; x < 200; x ++){
            for (int y = 0; y < 150; y++){
                setTerrainTile(x,y,loadMap.getRGB(x, y));
            }
        }
        
        TerrainResourceCount[80][20] = 20;
        TerrainResourceType[80][20] = 8;
        Random rand = new Random();
        
        for (int y = 0; y < 100; y ++){
            for (int x = 0; x < 200; x ++){
            	if (TerrainTiles[x][y] == 0){	//Grass
            		int n = rand.nextInt(3);
                    mapTiles[x][y] = n;
            	}else if (TerrainTiles[x][y] == 1){//Trees
            		TerrainResourceCount[x][y] = 4;
                    TerrainResourceType[x][y] = 7;
                    mapTiles[x][y] = 17;
            	}else if (TerrainTiles[x][y] == 2){//Water
            		mapTiles[x][y] = 20;
            	}else if (TerrainTiles[x][y] == 3){//Marsh
            		mapTiles[x][y] = 21;
            	}else if (TerrainTiles[x][y] == 4){//Brush
            		TerrainResourceCount[x][y] = 3;
                    TerrainResourceType[x][y] = 1;
                    int n = rand.nextInt(2);
                    mapTiles[x][y] = n+10;
            	}else if (TerrainTiles[x][y] == 5){//Hills
            		TerrainResourceCount[x][y] = 6;
                    TerrainResourceType[x][y] = 8;
                    
                    mapTiles[x][y] = 24;
            	}else if (TerrainTiles[x][y] == 6){//Dirt
            		TerrainResourceCount[x][y] = 5;
                    TerrainResourceType[x][y] = 9;
                    
                    int n = rand.nextInt(2);
                    mapTiles[x][y] = n+5;
            	}else if (TerrainTiles[x][y] == 7){//Plains
            		TerrainResourceCount[x][y] = 1;
                    TerrainResourceType[x][y] = 9;
                    
                    int n = rand.nextInt(2);
                    mapTiles[x][y] = n+14;
            	}
            	
            }
        }
    }
    public void setTerrainTile(int x, int y, int color){
        //System.out.println("Color: " + color);
        int terrainType = 0;
        //-4856291 = grass

        if (color == -14503604) //Green
            terrainType = 1;
        if (color == -16735512) //Blue
            terrainType = 2;
        if (color == -6075996)
            terrainType = 4;
        if (color == -3947581)
            terrainType = 5;
        if (color == -4621737)
            terrainType = 6;
        if (color == -3584)
            terrainType = 7;
        
        TerrainTiles[x][y] = terrainType;
        
    }
    public void harvest (int x, int y, int res){
    	System.out.println("MapTile " + x + "," + y + " Harvested");
    	TerrainResourceCount[x][y] -= 1;
    	
    	if (TerrainResourceCount[x][y] < 1){
    		if (TerrainTiles[x][y] == 1){
    			resetTile(x,y,0);
    		}if (TerrainTiles[x][y] == 4){
    			resetTile(x,y,0);
    		}
    	}
    }
    public void resetTile(int x, int y, int newType){
    	TerrainTiles[x][y] = newType;
    	TerrainMap.getGraphics().drawImage(tables.getTileImage(newType).getScaledInstance(15, 15, 0), (x * 15) , (y * 15) ,null);
        resetTerrain = true;
        System.out.println("Reset Tile " + x + ", " + y  + "     Redraw:" + resetTerrain);
    }
    public int getTerrainResource(){
    	return 0;
    }
    public BufferedImage getTerrainMap(){
    	System.out.println("getTerrainMap()");
    	if (TerrainMap == null)
    		System.out.println("Terrain Map NULL()");
    	return TerrainMap;
    }
    public void setRedraw(boolean set){
        resetTerrain = set;
    }
    public boolean canBuild(int x, int y){
    	if (TerrainTiles[x][y] == 1)
    		return false;
    	if (TerrainTiles[x][y] == 2)
    		return false;
    	return true;
    }
}
