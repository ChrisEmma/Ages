/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agesgame;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.sun.glass.ui.Timer;


/**
 *
 * @author cpemm_000
 */
public class CityViewImpl extends javax.swing.JLayeredPane {

	public float SCALE;
	public int TILE = 15;
        public int POP_W = 14;
        public int POP_L = 20;
    public BuildingMenuImpl buildingMenu;
    public WorkforceImpl workforceMenu;
    public PopulationImpl popMenu;
    public BuildingInfoImpl buildingInfo;
    public PersonInfoImpl personInfo;
    public TechScreen researchMenu;
    public ResourceImpl resourceMenu;
    public ItemImpl itemMenu;
    public ProductionImpl productionMenu;
    public MilitaryImpl militaryMenu;
    
    public CivCreationImpl newCivMenu; 
    

    private GameDataClass game;
    public BufferedImage TerrainMap;
    private boolean buildMode;

    private int viewX;
    private int viewY;
    
    

    public CityViewImpl(GameDataClass game, float SCALE) {
        this.game = game;
        this.SCALE = SCALE;
        buildMode = false;
        initComponents();
        generateDisplay();
        scaleView();
    }

    public void generateDisplay() {
        viewX = 0;
        viewY = 0;
        buildingMenu = new BuildingMenuImpl(game, this);
        this.setComponentZOrder(buildingMenu, 0);
        workforceMenu = new WorkforceImpl(game,this);
        this.setComponentZOrder(workforceMenu, 0);
        popMenu = new PopulationImpl(game,this);
        this.setComponentZOrder(popMenu, 0);
        popMenu.setVisible(false);
        
        researchMenu = new TechScreen(game,this);
        researchMenu.setBounds(Scale(150), Scale(50),Scale(646),Scale(400));
        this.add(researchMenu);
        this.setComponentZOrder(researchMenu, 0);
        researchMenu.setVisible(false);
        
        militaryMenu = new MilitaryImpl(game,this);
        militaryMenu.setBounds(Scale(150), Scale(50),Scale(660),Scale(560));
        this.add(militaryMenu);
        this.setComponentZOrder(militaryMenu, 0);
        militaryMenu.setVisible(false);
        
        resourceMenu = new ResourceImpl(game,this);
        resourceMenu.setBounds(Scale(200), Scale(50),Scale(600),Scale(400));
        this.add(resourceMenu);
        this.setComponentZOrder(resourceMenu, 0);
        resourceMenu.setVisible(false);
        
        itemMenu = new ItemImpl(game,this);
        itemMenu.setBounds(Scale(200), Scale(50),Scale(600),Scale(400));
        this.add(itemMenu);
        this.setComponentZOrder(itemMenu, 0);
        itemMenu.setVisible(false);
        
        buildingInfo = new BuildingInfoImpl(game,this,SCALE);
        buildingInfo.setBounds(0, Scale(730),Scale(600),Scale(80));
        this.add(buildingInfo);
        buildingInfo.setVisible(false);
        
        personInfo = new PersonInfoImpl(game,SCALE);
        personInfo.setBounds(0, Scale(730),Scale(600),Scale(80));
        this.add(personInfo);
        personInfo.setVisible(false);
        
        productionMenu= new ProductionImpl(game,this);
        productionMenu.setBounds(Scale(250), Scale(300), Scale(290), Scale(435));
        this.add(productionMenu);
        this.setComponentZOrder(productionMenu, 0);
        productionMenu.setVisible(false);
        
        TerrainLabel.setIcon(new ImageIcon(game.getMap(viewX, viewY).getScaledInstance(Scale(1600), Scale(710), 0)));
        TerrainLabel.setSize(Scale(1600), Scale(710));
        TerrainLabel.setLocation(0, Scale(20));
        this.setComponentZOrder(TerrainLabel, 5);
        for (int i = 0; i < game.getBuildingCount(); i++){
            getBuildingLabel(i).setSize(Scale(game.getBuilding(i).getL()*TILE), Scale(game.getBuilding(i).getW()*TILE));
            getBuildingLabel(i).setLocation(Scale(game.getBuilding(i).getX()*TILE+viewX), Scale(game.getBuilding(i).getY()*TILE+viewY) + Scale(20));            
            getBuildingLabel(i).setIcon(new ImageIcon(game.getBuilding(i).getImage().getScaledInstance(Scale(game.getBuilding(i).getL()*TILE), Scale(game.getBuilding(i).getW()*TILE), 0)));
            this.setComponentZOrder(getBuildingLabel(i), 3);
        }
        
        
        for (int i = 0; i < 21; i++){
            getPopulationLabel(i).setSize(Scale(POP_W), Scale(POP_L));
            getPopulationLabel(i).setVisible(false);
            this.setComponentZOrder(getPopulationLabel(i), 2);
            
            getPopCarryLabel(i).setSize(Scale(POP_W),Scale(25));
            getPopCarryLabel(i).setVisible(false);
            this.setComponentZOrder(getPopCarryLabel(i), 1);
        }
        
        for (int i = 0; i < game.animalCount(); i ++){
        	getAnimalLabel(i).setSize(10,10);
        	getAnimalLabel(i).setOpaque(true);
        	this.setComponentZOrder(getAnimalLabel(i), 2);
        }
        
        for (int i = 0; i < game.barbarianCount(); i ++){
        	getBarbarianLabel(i).setSize(Scale(POP_W), Scale(POP_L));
        	this.setComponentZOrder(getBarbarianLabel(i), 2);
        } 
        
        PopCountLabel.setText(game.getPopCount() + "");
        //set color by: game.canGrow()
        PopRateLabel.setText("+" + game.getGrowthRate() + "/year");
        
        jLabel1.setText("");
        jLabel1.setIcon(new ImageIcon(game.tables.getCityButtons(0).getScaledInstance(Scale(60), Scale(40), 0)));
        PopOutput.setText("");
        PopOutput.setIcon(new ImageIcon(game.tables.getCityButtons(1).getScaledInstance(Scale(60), Scale(40), 0)));
        MilitaryLabel.setText("");
        MilitaryLabel.setIcon(new ImageIcon(game.tables.getCityButtons(2).getScaledInstance(Scale(60), Scale(40), 0)));
        
        NewObjectLabel = new javax.swing.JLabel();
        NewObjectLabel.setVisible(false);
        NewObjectLabel.setLocation(Scale(3000), Scale(3000));
        NewObjectLabel.setOpaque(true);
        this.add(NewObjectLabel);
        
        PopIcon.setText("");
        PopIcon.setIcon(new ImageIcon(game.tables.getPopIcon().getScaledInstance(Scale(10),Scale(15), 0)));
        GoodsIcon.setText("");
        GoodsIcon.setIcon(new ImageIcon(game.tables.getGoodsIcon().getScaledInstance(Scale(15),Scale(15), 0)));
        ResourceIcon2.setText("");
        
        
        
        ResourceIcon2.setIcon(new ImageIcon(game.tables.getResourceIcon(4).getScaledInstance(Scale(20),Scale(20), 0)));
        //ResourceIcon2.setIcon(new ImageIcon(game.tables.getResourceIcon(4).getScaledInstance(40,40, 0)));
        
        
        ResourceIcon0.setText("");
//        ResourceIcon0.setSize(20, 20);
        ResourceIcon0.setIcon(new ImageIcon(game.tables.getResourceIcon(7).getScaledInstance(Scale(20),Scale(20),0)));
        
        ResourceIcon1.setIcon(new ImageIcon(game.tables.getResourceIcon(8).getScaledInstance(Scale(20),Scale(20), 0)));
        ResourceIcon1.setText("");
        this.setComponentZOrder(TimerLabel, 10);
        this.setComponentZOrder(ResourceIcon0, 10);
        this.setComponentZOrder(ResourceLabel0, 10);
        this.setComponentZOrder(ResourceIcon1, 10);
        this.setComponentZOrder(ResourceLabel1, 10);
        
        
    }
    public void refreshDisplay(){
        
        for (int i = 0; i < game.getBuildingCount(); i++){
            getBuildingLabel(i).setSize(Scale(game.getBuilding(i).getL()*TILE), Scale(game.getBuilding(i).getW()*TILE));
            getBuildingLabel(i).setLocation(Scale(game.getBuilding(i).getX()*TILE+viewX), Scale(game.getBuilding(i).getY()*TILE+viewY)+ Scale(20));
            getBuildingLabel(i).setIcon(new ImageIcon(game.getBuilding(i).getImage().getScaledInstance(Scale(game.getBuilding(i).getL()*TILE), Scale(game.getBuilding(i).getW()*TILE), 0)));
        }
        
        for (int i = 0; i < game.getPopCount(); i++){
            getPopulationLabel(i).setSize(Scale(POP_W), Scale(POP_L));
            getPopulationLabel(i).setIcon(new ImageIcon(game.getPopulation(i).getImage().getScaledInstance(Scale(POP_W), Scale(POP_L), 0)));
            this.setComponentZOrder(getPopulationLabel(i), 2);
            
            
        }
        
        NewObjectLabel.setVisible(false);
        
        PopCountLabel.setText(game.getPopCount() + "");
        //set color by: game.canGrow()
        PopRateLabel.setText("+" + game.getGrowthRate() + "/year");
        GoodsLabel.setText(game.getGoodsCount() + "");
        
        
        
    }
    
    public void timeTick(){
    	if (game.isPaused())
    		return;
        if (game.redrawMap()){
            TerrainLabel.setIcon(new ImageIcon(game.getMap(viewX, viewY).getScaledInstance(Scale(1600), Scale(710), 0)));
            game.clearRedraw();
        }
        
        if (game.getCivEra() < 1){
        	EraLabel.setVisible(false);
        	NameLabel.setVisible(false);
        	GoodsIcon.setVisible(false);
        	GoodsLabel.setVisible(false);
        }else{
        	if (!game.isFounded()){
        		openCivCreation();
        		
        		
        	}
        	
        	EraLabel.setVisible(true);
        	EraLabel.setText("" + game.getCivEra()); //To become icon
        	NameLabel.setVisible(true);
        	NameLabel.setText(game.getCivName());
        	GoodsIcon.setVisible(true);
        	GoodsLabel.setVisible(true);
        }
        
        
    	if (game.getYear() < 0){
    		TimerLabel.setText(Math.abs(game.getYear()) + " BC");
    	}else{
    		TimerLabel.setText(Math.abs(game.getYear()) + " AD");
    	}
        
    	MapPos.setText(viewX + ", " + -viewY);
    	
        int food = game.getResource(0) + game.getResource(1) + game.getResource(2) + game.getResource(3) + game.getResource(4);
        ResourceLabel2.setText(food+"");
        ResourceLabel0.setText(game.getResource(7)+"");
        ResourceLabel1.setText(game.getResource(8)+"");
        GoodsLabel.setText(game.getGoodsCount() + "");
        for (int i = 0; i < game.getBuildingCount(); i++){
        	getBuildingLabel(i).setBounds(Scale(game.getBuilding(i).getX()*TILE-viewX), Scale(game.getBuilding(i).getY()*TILE+viewY), Scale(game.getBuilding(i).getL()*TILE), Scale(game.getBuilding(i).getW()*TILE));
            getBuildingLabel(i).setIcon(new ImageIcon(game.getBuilding(i).getImage().getScaledInstance(Scale(game.getBuilding(i).getL()*TILE), Scale(game.getBuilding(i).getW()*TILE), 0)));
        }
        for (int i = game.getBuildingCount(); i < 16; i++){
        	getBuildingLabel(i).setVisible(false);
        }
        
        for (int i = 0; i < game.getPopCount(); i++){
        	getPopulationLabel(i).setVisible(true);
        	this.setComponentZOrder(getPopulationLabel(i), 2);
        	
            getPopulationLabel(i).setIcon(new ImageIcon(game.getPopulation(i).getImage().getScaledInstance(Scale(POP_W), Scale(POP_L), 0)));
        //if (Scale(game.getPopulation(i).getX()) != getPopulationLabel(i).getX() || Scale(game.getPopulation(i).getY()+20) != getPopulationLabel(i).getY())
            getPopulationLabel(i).setLocation(Scale(game.getPopulation(i).getX()*TILE-viewX), Scale(game.getPopulation(i).getY()*TILE+viewY)+ Scale(20));
            getPopulationLabel(i).repaint(0, 0,Scale(POP_W), Scale(POP_L));
        	if (game.getPopulation(i).dammageFlag){
        		getPopulationLabel(i).setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 28, 36)));
        	}else{
        		getPopulationLabel(i).setBorder(null);
        	}
        	
        	if (game.getPopulation(i).getCarryIcon() != null){
        		getPopCarryLabel(i).setLocation(Scale(game.getPopulation(i).getX()*TILE-viewX), Scale(game.getPopulation(i).getY()*TILE+viewY)+ Scale(23));
            	getPopCarryLabel(i).setVisible(true);
            	getPopCarryLabel(i).setIcon(new ImageIcon(game.getPopulation(i).getCarryIcon().getScaledInstance(Scale(POP_W), Scale(20), 0)));
            	this.setComponentZOrder(getPopCarryLabel(i), 2);
            }else{
            	getPopCarryLabel(i).setVisible(false);
            }
        		
        }
        
        for (int i = 0; i < game.animalCount(); i++){
        	this.setComponentZOrder(getAnimalLabel(i), 2);
        	getAnimalLabel(i).setLocation(Scale(game.getAnimal(i).getX()*TILE-viewX), Scale(game.getAnimal(i).getY()*TILE+viewY)+ Scale(20));
            
            
        	if (game.getAnimal(i).dammageFlag){
        		System.out.println("Animal(" + i + ") Dammage Flag");
        		getAnimalLabel(i).setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 28, 36)));
        		game.getAnimal(i).dammageFlag = false;
        	}else{
        		getAnimalLabel(i).setBorder(null);
        	}
        }
        
        for (int i = 0; i < game.barbarianCount(); i++){
        	this.setComponentZOrder(getBarbarianLabel(i), 2);
        	getBarbarianLabel(i).setLocation(Scale(game.getBarbarian(i).getX()*TILE-viewX), Scale(game.getBarbarian(i).getY()*TILE+viewY)+ Scale(20));
            
        	getBarbarianLabel(i).setIcon(new ImageIcon(game.getBarbarian(i).getImage().getScaledInstance(Scale(POP_W), Scale(POP_L), 0)));
        	
        }
        
        
        PopCountLabel.setText(game.getPopCount() + "");
        //set color by: game.canGrow()
        PopRateLabel.setText("+" + game.getGrowthRate() + "/year");
    	if (game.getGrowthRate() <= 0){
    		PopRateLabel.setForeground(new Color(230,28,36));
    	}else if (game.getPopCount() >= game.getHousing()){
    		PopRateLabel.setForeground(new Color(240,128,39));
    	}else{
    		PopRateLabel.setForeground(new Color(0,0,0));
    	}
    	
    	if (personInfo.isVisible()){
    		personInfo.refreshDisplay();
    	}else if(buildingInfo.isVisible()){
    		buildingInfo.refreshDisplay();
    	}else if(militaryMenu.isVisible()){
    		militaryMenu.generateDisplay();
    	}
        //Check Menus
    	
        if (popMenu.isVisible()){
            popMenu.refreshDisplay();
        }
        
        checkPopups();
        //SelectionBuiltLabel.setText();
    }
    public void checkPopups(){
    	while (!game.popupList.isEmpty()){
    		System.out.println("Popup: " + game.popupList.get(0));
    		BufferedImage icon = null;// = new BufferedImage(40,40,0);
    		if (game.popupList.get(0).contains("RES")){
    			String i = game.popupList.get(0).split("-")[0].substring(3);
    			System.out.println("Fetch Resource Icon:" + i);
    			icon = game.tables.getResourceIcon(Integer.parseInt(i));
    		}
    		if (game.popupList.get(0).contains("TEC")){
    			String i = game.popupList.get(0).split("-")[0].substring(3);
    			icon = game.tables.getResearchIcon(Integer.parseInt(i));
    		}
    		if (game.popupList.get(0).contains("GOODS")){
    			icon = game.tables.getGoodsIcon();
    		}
    		if (game.popupList.get(0).contains("EAT")){
    			icon = game.tables.getEatingIcon();
    		}
    		if (game.popupList.get(0).contains("ATTACK")){
    			icon = game.tables.getCombatIcon(0);
    		}
    		if (game.popupList.get(0).contains("DEFEND")){
    			icon = game.tables.getCombatIcon(1);
    		}
    		
    			
    		PopupText popup = new PopupText("", 20, icon, Scale(TILE*Integer.parseInt(game.popupList.get(0).split("-")[1].split(",")[0])-viewX) , Scale(TILE*Integer.parseInt(game.popupList.get(0).split("-")[1].split(",")[1]))+viewY,SCALE);
            this.add(popup);
            this.setComponentZOrder(popup, 8);
            popup.setVisible(true);
            popup.setSize(Scale(25), Scale(25));
            
            game.popupList.remove(0);
    	}
    }
    public void refreshBuilding(int id){
        System.out.println("Refresh Building:" + id) ;
        getBuildingLabel(id).setVisible(true);
        getBuildingLabel(id).setBounds(Scale(game.getBuilding(id).getX()*TILE-viewX), Scale(game.getBuilding(id).getY()*TILE+viewY), Scale(game.getBuilding(id).getL()*TILE), Scale(game.getBuilding(id).getW()*TILE));
        System.out.println("refreshBuilding - Set Building(" + id + ") Color");
        getBuildingLabel(id).setIcon(new ImageIcon(game.getBuilding(id).getImage().getScaledInstance(Scale(game.getBuilding(id).getL()*TILE), Scale(game.getBuilding(id).getW()*TILE), 0)));
        this.setComponentZOrder(getBuildingLabel(id), 3);
    }

    public void setBuild(int id, int x, int y) {
        double calcPos = (x + Scale(viewX)) / Scale(TILE);
        x = (int) (Math.round(calcPos));
        //calcPos = (y - Scale(20)) / Scale(TILE);
        calcPos = (y - Scale(viewY) ) / Scale(TILE);
        y = (int) (Math.round(calcPos));

        game.setupBuilding(id, x, y,-1);
        refreshBuilding((game.getBuildingCount()-1));
        setBuildMode(0);
    }
    public void buildingClick(int i){
        buildingInfo.setID(i);
        personInfo.setVisible(false);
        buildingInfo.setVisible(true);
    }
    public void personClick(int i){
        personInfo.setID(i);
        buildingInfo.setVisible(false);
        personInfo.setVisible(true);
    }
    public void wildClicked(int i){
    	personInfo.setCreatureID(i);
        buildingInfo.setVisible(false);
        personInfo.setVisible(true);
    }
    public void barbClicked(int i){
    	personInfo.setCreatureID(10 + i);
        buildingInfo.setVisible(false);
        personInfo.setVisible(true);
    }

    public void openBuildMenu() {
        System.out.println("Open Build Menu");
        buildingMenu.setOpaque(true);
        buildingMenu.generateDisplay();
        buildingMenu.setVisible(true);
        buildingMenu.setBounds(Scale(1300), Scale(50), Scale(290), Scale(370));
        this.add(buildingMenu, 0);
    }
    
    public void openProductionMenu(int id) {
        productionMenu.setOpaque(true);
        productionMenu.generateDisplay(id);
        productionMenu.setVisible(true);
    }

    public void setBuildMode(int id) {
        if (id > 0) {
            buildMode = true;
            NewObjectLabel.setVisible(true);
            NewObjectLabel.setText(id + "");
            NewObjectLabel.setSize(Scale(game.tables.getBuildingLength(id)*TILE) , Scale(game.tables.getBuildingWidth(id)*TILE)); 
            this.setComponentZOrder(NewObjectLabel,0);
        } else {
            System.out.println("Close Build Menu");
            buildMode = false;
            buildingMenu.hide();
            //this.remove(buildingMenu);
            //buildingMenu.setVisible(false);
            
            NewObjectLabel.setVisible(false);
        }

    }

    public void closeMenus(){
    	buildingMenu.close();
    	researchMenu.close();
    	workforceMenu.close();
    	researchMenu.close();
        resourceMenu.close();
        itemMenu.close();
        productionMenu.close();
        militaryMenu.close();
        
        buildingMenu.hide();
    }
    
    public void mouseMove(MouseEvent e) {
        if (buildMode) {
            int x = e.getX();
            int y = e.getY();
            setBuildLocation(x, y);
            //System.out.println("BuildObj (" + x + "," + y +")");

            if (canPlaceObject(x, y)) {
                NewObjectLabel.setBackground(new Color(0, 200, 0));
            } else {
                NewObjectLabel.setBackground(new Color(200, 0, 0));
            }
            
        }
    }
    public void mouseMoveBuilding(int i, MouseEvent e) {
    	if (buildMode) {
    		int x = Scale(game.getBuilding(i).getX()*TILE+viewX) + e.getX();
    	    int y = Scale(game.getBuilding(i).getY()*TILE-viewY) + e.getY();
    	    setBuildLocation(x,y);
    	
	    	if (canPlaceObject(x, y)) {
	            NewObjectLabel.setBackground(new Color(0, 200, 0));
	        } else {
	            NewObjectLabel.setBackground(new Color(200, 0, 0));
	        }
    	}
    }

    public void setBuildLocation(int x, int y) {
    	
        double calcPos = x / Scale(TILE);
        x = (int) (Math.round(calcPos) * Scale(TILE));
        calcPos = y / Scale(TILE);
        y = (int) (Math.round(calcPos) * Scale(TILE));
        
        NewObjectLabel.setLocation(x, y);
    }

    public void screenClick(MouseEvent e) {
        System.out.println("CityView: Screen Click");
        if (e.getButton() == 1) {
            if (buildMode) {
                int id = Integer.parseInt(NewObjectLabel.getText());
                if (canPlaceObject(e.getX(), e.getY())) {
                    setBuild(id, e.getX(), e.getY());
                }
            }else{
            	closeMenus();
            }
        } else {
            if (buildMode) {
                setBuildMode(0);
                //closeInfo();
            }
        }
        
    }

    
    public boolean canPlaceObject(int x, int y) {
        int id = Integer.parseInt(NewObjectLabel.getText());
        int L = game.tables.getBuildingLength(id);
        int W = game.tables.getBuildingWidth(id);
        x += Scale(viewX);
        y += Scale(-viewY) -Scale(20);
        
        for (int tY = 0; tY < L; tY++) {
            for (int tX = 0; tX < W; tX++) {
                if (!game.canBuild( (x / Scale(TILE)) + tX, (y / Scale(TILE)) + tY) ) {
                    System.out.println("Building Collision (" + (x / Scale(TILE) + tX) + ", " + (y / Scale(TILE) + tY) + ")");
                    return false;
                }
            }
        }
        
        for (int i = 0; i < game.getBuildingCount(); i ++){
        	int bld_x1 = game.getBuilding(i).getX();
        	int bld_x2 = game.getBuilding(i).getW() + bld_x1;
        	int bld_y1 = game.getBuilding(i).getY();
        	int bld_y2 = game.getBuilding(i).getL() + bld_y1;
        	
        	int CurrentX = x / Scale(TILE);
        	int CurrentY = y / Scale(TILE);
        	
        	if (CurrentX > bld_x1 && (CurrentX + L) < bld_x2 && CurrentY > bld_y1 && (CurrentY + W) < bld_y2)
                return false;
        	
        }

        /*for (int i = 0; i < game.office[0].getObjCount(); i ++){
            
         
         }
         }*/
        //System.out.println("Clear (" + (x / 10) + ", " + (y / 10) + ") to (" + (x / 10 + W) + "," + (y / 10 + L) + ")");
        return true;
    }
    public void showInventoryScreen(){
    	System.out.println("Show Resources Screen");
        resourceMenu.setOpaque(true);
        this.add(resourceMenu, 0);
        resourceMenu.setVisible(true);
        resourceMenu.generateDisplay();
    }
    public void showItemsScreen(){
    	closeMenus();
    	itemMenu.setOpaque(true);
        this.add(itemMenu, 0);
        itemMenu.setVisible(true);
        itemMenu.generateDisplay();
    }
    public void showWorkforce(){
        workforceMenu.setOpaque(true);
        workforceMenu.setVisible(true);
        workforceMenu.generateDisplay();
        workforceMenu.setBounds(Scale(250), Scale(300), Scale(290), Scale(405));
        this.add(workforceMenu, 0);
    }
    public void showResearchScreen(){
        researchMenu.setOpaque(true);
        this.setComponentZOrder(researchMenu, 0);
        researchMenu.setVisible(true);
        researchMenu.generateDisplay();
    }
    public void showMilitaryScreen(){
        militaryMenu.setOpaque(true);
        this.setComponentZOrder(militaryMenu, 0);
        militaryMenu.setVisible(true);
        militaryMenu.generateDisplay();
    }
    public void showPopInfo(){
    	popMenu.setOpaque(true);
    	popMenu.setVisible(true);
        popMenu.setBounds(Scale(100), Scale(40), Scale(300), Scale(405));
        this.add(popMenu, 0);
        popMenu.refreshDisplay();
        for (int i = 0; i < game.getPopCount(); i++){
            System.out.println("Pop" + i + ": " + game.getPopulation(i).task);
        }
    }
    
    public void openCivCreation(){
    	newCivMenu = new CivCreationImpl(game,this);
    	newCivMenu.setBounds(Scale(310), Scale(90),Scale(530),Scale(440));
        this.add(newCivMenu);
        this.setComponentZOrder(newCivMenu, 0);
        game.setPause(true);
    }

    public void YbuttonClick(int y) {
        viewY += y;
        if (viewY > 0)
        	viewY = 0;
        game.setMapRedraw();
        timeTick();
    }
    public void XbuttonClick(int x) {
        viewX += x;
        if (viewX < 0)
        	viewX = 0;
        
        game.setMapRedraw();
        timeTick();
    }
    public void recenterMapClick(){
    	viewX = 0;
    	viewY = 0;
    	game.setMapRedraw();
        timeTick();
    }
    
    public void outputTaskList(){
    	for (int i = 0; i < game.tasks.size(); i++){
    		System.out.println("TASK - " + game.tasks.get(i).getString() + " Bld:" + game.tasks.get(i).getBuilding() + " O:" + game.tasks.get(i).getObject());	
    	}
        
    }

    public void keyPressed(KeyEvent event) {
        System.out.println("CityView - Key: " + event.getKeyChar());
    }
    public JLabel getBuildingLabel(int i){
        if (i == 0){
            return BuildingLabel0;
        }if (i == 1){
            return BuildingLabel1;
        }if (i == 2){
            return BuildingLabel2;
        }if (i == 3){
            return BuildingLabel3;
        }if (i == 4){
            return BuildingLabel4;
        }if (i == 5){
            return BuildingLabel5;
        }if (i == 6){
            return BuildingLabel6;
        }if (i == 7){
            return BuildingLabel7;
        }if (i == 8){
            return BuildingLabel8;
        }if (i == 9){
            return BuildingLabel9;
        }if (i == 10){
            return BuildingLabel10;
        }if (i == 11){
            return BuildingLabel11;
        }if (i == 12){
            return BuildingLabel12;
        }if (i == 13){
            return BuildingLabel13;
        }if (i == 14){
            return BuildingLabel14;
        }if (i == 15){
            return BuildingLabel15;
        }
        return  null;
    }
    public JLabel getPopulationLabel(int i){
        if (i == 0){
            return PopLabel0;
        }if (i == 1){
            return PopLabel1;
        }if (i == 2){
            return PopLabel2;
        }if (i == 3){
            return PopLabel3;
        }if (i == 4){
            return PopLabel4;
        }if (i == 5){
            return PopLabel5;
        }if (i == 6){
            return PopLabel6;
        }if (i == 7){
            return PopLabel7;
        }if (i == 8){
            return PopLabel8;
        }if (i == 9){
            return PopLabel9;
        }if (i == 10){
            return PopLabel10;
        }if (i == 11){
            return PopLabel11;
        }if (i == 12){
            return PopLabel12;
        }if (i == 13){
            return PopLabel13;
        }if (i == 14){
            return PopLabel14;
        }if (i == 15){
            return PopLabel15;
        }if (i == 16){
            return PopLabel16;
        }if (i == 17){
            return PopLabel17;
        }if (i == 18){
            return PopLabel18;
        }if (i == 19){
            return PopLabel19;
        }if (i == 20){
            return PopLabel20;
        }
        
        return  null;
    }
    public JLabel getPopCarryLabel(int i){
        if (i == 0){
            return PopCarry0;
        }if (i == 1){
            return PopCarry1;
        }if (i == 2){
            return PopCarry2;
        }if (i == 3){
            return PopCarry3;
        }if (i == 4){
            return PopCarry4;
        }if (i == 5){
            return PopCarry5;
        }if (i == 6){
            return PopCarry6;
        }if (i == 7){
            return PopCarry7;
        }if (i == 8){
            return PopCarry8;
        }if (i == 9){
            return PopCarry9;
        }if (i == 10){
            return PopCarry10;
        }if (i == 11){
            return PopCarry11;
        }if (i == 12){
            return PopCarry12;
        }if (i == 13){
            return PopCarry13;
        }if (i == 14){
            return PopCarry14;
        }if (i == 15){
            return PopCarry15;
        }if (i == 16){
            return PopCarry16;
        }if (i == 17){
            return PopCarry17;
        }if (i == 18){
            return PopCarry18;
        }if (i == 19){
            return PopCarry19;
        }if (i == 20){
            return PopCarry20;
        }if (i == 21){
            return PopCarry21;
        }if (i == 22){
            return PopCarry22;
        }
        return null;
    }
    
    public JLabel getAnimalLabel(int i){
        if (i == 0)
            return AnimalLabel0;
        if (i == 1)
        	return AnimalLabel1;
        if (i == 2)
        	return AnimalLabel2;
        if (i == 3)
        	return WildLabel3;
        if (i == 4)
        	return WildLabel4;
        return null;
    }
    public JLabel getBarbarianLabel(int i){
        if (i == 0)
            return BarbLabel0;
        if (i == 1)
        	return BarbLabel1;
        if (i == 2)
        	return BarbLabel2;
        if (i == 3)
        	return BarbLabel3;
        if (i == 4)
        	return BarbLabel4;
        if (i == 5)
        	return BarbLabel5;
        if (i == 6)
        	return BarbLabel6;
        if (i == 7)
        	return BarbLabel7;
        if (i == 8)
        	return BarbLabel8;
        if (i == 9)
        	return BarbLabel9;
        return null;
    }
    
    
    public void scaleView(){
    	
        for (int i = 0; i < 22; i ++){
        	JLabel setLabel = new javax.swing.JLabel();;
        	if (i == 0)
        		setLabel = jLabel1;
        	if (i == 1)
        		setLabel = TasksLbl;
        	if (i == 2)
        		setLabel = TimerLabel;
        	if (i == 3)
        		setLabel = PopOutput;
        	if (i == 4)
        		setLabel = ResourceIcon0;
        	if (i == 5)
        		setLabel = ResourceLabel0;
        	if (i == 6)
        		setLabel = WorkforceLabel;
        	if (i == 7)
        		setLabel = PopIcon;
        	if (i == 8)
        		setLabel = PopCountLabel;
        	if (i == 9)
        		setLabel = ResourceIcon1;
        	if (i == 10)
        		setLabel = ResourceLabel1;
        	if (i == 11)
        		setLabel = PopRateLabel;
        	if (i == 12)
        		setLabel = ResourceIcon2;
        	if (i == 13)
        		setLabel = ResourceLabel2;
        	if (i == 14)
        		setLabel = TechLabel;
        	if (i == 15)
        		setLabel = ItemsLabel;
        	if (i == 16)
        		setLabel = NameLabel;
        	if (i == 17)
        		setLabel = EraLabel;
        	if (i == 18)
        		setLabel = GoodsLabel;
        	if (i == 19)
        		setLabel = GoodsIcon;
        	if (i == 20)
        		setLabel = MilitaryLabel;
        	if (i == 21)
        		setLabel = MapPos;
        	
        	
        	
        	setLabel.setBounds(Scale(setLabel.getX()), Scale(setLabel.getY()), Scale(setLabel.getWidth()), Scale(setLabel.getHeight()));
        	
        }
        
        UpButton.setBounds(Scale(UpButton.getX()), Scale(UpButton.getY()), Scale(UpButton.getWidth()), Scale(UpButton.getHeight()));
        DownButton.setBounds(Scale(DownButton.getX()), Scale(DownButton.getY()), Scale(DownButton.getWidth()), Scale(DownButton.getHeight()));
        RightButton.setBounds(Scale(RightButton.getX()), Scale(RightButton.getY()), Scale(RightButton.getWidth()), Scale(RightButton.getHeight()));
        LeftButton.setBounds(Scale(LeftButton.getX()), Scale(LeftButton.getY()), Scale(LeftButton.getWidth()), Scale(LeftButton.getHeight()));
        ZeroButton.setBounds(Scale(ZeroButton.getX()), Scale(ZeroButton.getY()), Scale(ZeroButton.getWidth()), Scale(ZeroButton.getHeight()));
        MapPos.setFont(new java.awt.Font("Tahoma", 0, Scale(12))); // NOI18N
        
        TimerLabel.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
        PopOutput.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
        
        ResourceIcon0.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
        ResourceLabel0.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
        PopCountLabel.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
        ResourceIcon1.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
        ResourceLabel1.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
        PopRateLabel.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
        ResourceIcon2.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
        ResourceLabel2.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
        GoodsLabel.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N

        EraLabel.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
        NameLabel.setFont(new java.awt.Font("Tahoma", 1, Scale(16))); // NOI18N
    }
    public int Scale(int in){
    	return Math.round(in * SCALE);
    }
    public int Scale(float in){
    	return Math.round(in * SCALE);
    }
    public int Scale(double in){
    	return Math.round(Math.round(in * SCALE)); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TerrainLabel = new javax.swing.JLabel();
        DownButton = new javax.swing.JButton();
        UpButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BuildingLabel0 = new javax.swing.JLabel();
        BuildingLabel1 = new javax.swing.JLabel();
        BuildingLabel2 = new javax.swing.JLabel();
        TasksLbl = new javax.swing.JLabel();
        TimerLabel = new javax.swing.JLabel();
        PopOutput = new javax.swing.JLabel();
        PopLabel0 = new javax.swing.JLabel();
        PopLabel1 = new javax.swing.JLabel();
        ResourceIcon0 = new javax.swing.JLabel();
        ResourceLabel0 = new javax.swing.JLabel();
        WorkforceLabel = new javax.swing.JLabel();
        PopLabel2 = new javax.swing.JLabel();
        BuildingLabel3 = new javax.swing.JLabel();
        BuildingLabel4 = new javax.swing.JLabel();
        PopIcon = new javax.swing.JLabel();
        PopCountLabel = new javax.swing.JLabel();
        ResourceIcon1 = new javax.swing.JLabel();
        ResourceLabel1 = new javax.swing.JLabel();
        PopRateLabel = new javax.swing.JLabel();
        BuildingLabel5 = new javax.swing.JLabel();
        BuildingLabel6 = new javax.swing.JLabel();
        BuildingLabel7 = new javax.swing.JLabel();
        BuildingLabel8 = new javax.swing.JLabel();
        BuildingLabel9 = new javax.swing.JLabel();
        PopLabel3 = new javax.swing.JLabel();
        PopLabel4 = new javax.swing.JLabel();
        PopLabel5 = new javax.swing.JLabel();
        ResourceIcon2 = new javax.swing.JLabel();
        ResourceLabel2 = new javax.swing.JLabel();
        TechLabel = new javax.swing.JLabel();
        PopLabel6 = new javax.swing.JLabel();
        PopLabel7 = new javax.swing.JLabel();
        PopLabel8 = new javax.swing.JLabel();
        PopLabel9 = new javax.swing.JLabel();
        PopLabel10 = new javax.swing.JLabel();
        PopLabel11 = new javax.swing.JLabel();
        BuildingLabel10 = new javax.swing.JLabel();
        BuildingLabel11 = new javax.swing.JLabel();
        BuildingLabel12 = new javax.swing.JLabel();
        BuildingLabel13 = new javax.swing.JLabel();
        BuildingLabel14 = new javax.swing.JLabel();
        BuildingLabel15 = new javax.swing.JLabel();
        PopLabel12 = new javax.swing.JLabel();
        PopLabel13 = new javax.swing.JLabel();
        PopLabel14 = new javax.swing.JLabel();
        PopLabel15 = new javax.swing.JLabel();
        PopLabel16 = new javax.swing.JLabel();
        PopLabel17 = new javax.swing.JLabel();
        PopLabel18 = new javax.swing.JLabel();
        PopLabel19 = new javax.swing.JLabel();
        PopLabel20 = new javax.swing.JLabel();
        PopLabel21 = new javax.swing.JLabel();
        PopLabel22 = new javax.swing.JLabel();
        PopLabel23 = new javax.swing.JLabel();
        ItemsLabel = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        EraLabel = new javax.swing.JLabel();
        GoodsIcon = new javax.swing.JLabel();
        GoodsLabel = new javax.swing.JLabel();
        MilitaryLabel = new javax.swing.JLabel();
        AnimalLabel0 = new javax.swing.JLabel();
        AnimalLabel1 = new javax.swing.JLabel();
        AnimalLabel2 = new javax.swing.JLabel();
        WildLabel3 = new javax.swing.JLabel();
        WildLabel4 = new javax.swing.JLabel();
        WildLabel5 = new javax.swing.JLabel();
        WildLabel6 = new javax.swing.JLabel();
        WildLabel7 = new javax.swing.JLabel();
        WildLabel8 = new javax.swing.JLabel();
        WildLabel9 = new javax.swing.JLabel();
        MapPos = new javax.swing.JLabel();
        LeftButton = new javax.swing.JButton();
        RightButton = new javax.swing.JButton();
        ZeroButton = new javax.swing.JButton();
        PopCarry0 = new javax.swing.JLabel();
        PopCarry1 = new javax.swing.JLabel();
        PopCarry2 = new javax.swing.JLabel();
        PopCarry3 = new javax.swing.JLabel();
        PopCarry4 = new javax.swing.JLabel();
        PopCarry5 = new javax.swing.JLabel();
        PopCarry6 = new javax.swing.JLabel();
        PopCarry7 = new javax.swing.JLabel();
        PopCarry8 = new javax.swing.JLabel();
        PopCarry9 = new javax.swing.JLabel();
        PopCarry10 = new javax.swing.JLabel();
        PopCarry11 = new javax.swing.JLabel();
        PopCarry12 = new javax.swing.JLabel();
        PopCarry13 = new javax.swing.JLabel();
        PopCarry14 = new javax.swing.JLabel();
        PopCarry15 = new javax.swing.JLabel();
        PopCarry16 = new javax.swing.JLabel();
        PopCarry17 = new javax.swing.JLabel();
        PopCarry18 = new javax.swing.JLabel();
        PopCarry19 = new javax.swing.JLabel();
        PopCarry20 = new javax.swing.JLabel();
        PopCarry21 = new javax.swing.JLabel();
        PopCarry22 = new javax.swing.JLabel();
        PopCarry23 = new javax.swing.JLabel();
        PopCarry24 = new javax.swing.JLabel();
        BarbLabel0 = new javax.swing.JLabel();
        BarbLabel1 = new javax.swing.JLabel();
        BarbLabel2 = new javax.swing.JLabel();
        BarbLabel3 = new javax.swing.JLabel();
        BarbLabel4 = new javax.swing.JLabel();
        BarbLabel5 = new javax.swing.JLabel();
        BarbLabel6 = new javax.swing.JLabel();
        BarbLabel7 = new javax.swing.JLabel();
        BarbLabel8 = new javax.swing.JLabel();
        BarbLabel9 = new javax.swing.JLabel();

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        TerrainLabel.setBackground(new java.awt.Color(255, 255, 255));
        TerrainLabel.setIconTextGap(0);
        TerrainLabel.setOpaque(true);
        TerrainLabel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TerrainLabelMouseMoved(evt);
            }
        });
        TerrainLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TerrainLabelMouseClicked(evt);
            }
        });
        add(TerrainLabel);
        TerrainLabel.setBounds(0, 20, 20, 20);

        DownButton.setText("D");
        DownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DownButtonActionPerformed(evt);
            }
        });
        add(DownButton);
        DownButton.setBounds(730, 780, 40, 20);

        UpButton.setText("U");
        UpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpButtonActionPerformed(evt);
            }
        });
        add(UpButton);
        UpButton.setBounds(730, 740, 40, 20);

        jLabel1.setBackground(new java.awt.Color(255, 153, 51));
        jLabel1.setText("BUILD");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1);
        jLabel1.setBounds(1350, 750, 62, 42);

        BuildingLabel0.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel0.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel0MouseMoved(evt);
            }
        });
        BuildingLabel0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel0MouseClicked(evt);
            }
        });
        add(BuildingLabel0);
        BuildingLabel0.setBounds(1510, 30, 20, 20);

        BuildingLabel1.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel1MouseMoved(evt);
            }
        });
        BuildingLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel1MouseClicked(evt);
            }
        });
        add(BuildingLabel1);
        BuildingLabel1.setBounds(1470, 30, 30, 20);

        BuildingLabel2.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel2MouseMoved(evt);
            }
        });
        BuildingLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel2MouseClicked(evt);
            }
        });
        add(BuildingLabel2);
        BuildingLabel2.setBounds(1420, 30, 20, 20);

        TasksLbl.setBackground(new java.awt.Color(255, 153, 153));
        TasksLbl.setText("TASKS");
        TasksLbl.setOpaque(true);
        TasksLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TasksLblMouseClicked(evt);
            }
        });
        add(TasksLbl);
        TasksLbl.setBounds(1520, 750, 60, 40);

        TimerLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TimerLabel.setText("TIME");
        add(TimerLabel);
        TimerLabel.setBounds(1490, 0, 80, 20);

        PopOutput.setBackground(new java.awt.Color(0, 255, 0));
        PopOutput.setText("Pop Data");
        PopOutput.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        PopOutput.setOpaque(true);
        PopOutput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopOutputMouseClicked(evt);
            }
        });
        add(PopOutput);
        PopOutput.setBounds(1420, 750, 62, 42);

        PopLabel0.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel0MouseClicked(evt);
            }
        });
        add(PopLabel0);
        PopLabel0.setBounds(1500, 190, 5, 5);

        PopLabel1.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel1MouseClicked(evt);
            }
        });
        add(PopLabel1);
        PopLabel1.setBounds(1490, 160, 5, 5);

        ResourceIcon0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResourceIcon0.setText("W:");
        ResourceIcon0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResourceIcon0MouseClicked(evt);
            }
        });
        add(ResourceIcon0);
        ResourceIcon0.setBounds(1270, 0, 20, 17);

        ResourceLabel0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResourceLabel0.setText("0000");
        ResourceLabel0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResourceLabel0MouseClicked(evt);
            }
        });
        add(ResourceLabel0);
        ResourceLabel0.setBounds(1300, 0, 40, 17);

        WorkforceLabel.setBackground(new java.awt.Color(255, 255, 0));
        WorkforceLabel.setText("WORKFORCE");
        WorkforceLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        WorkforceLabel.setOpaque(true);
        WorkforceLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WorkforceLabelMouseClicked(evt);
            }
        });
        add(WorkforceLabel);
        WorkforceLabel.setBounds(1140, 750, 62, 42);

        PopLabel2.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel2MouseClicked(evt);
            }
        });
        add(PopLabel2);
        PopLabel2.setBounds(1520, 160, 5, 5);

        BuildingLabel3.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel3MouseMoved(evt);
            }
        });
        BuildingLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel3MouseClicked(evt);
            }
        });
        add(BuildingLabel3);
        BuildingLabel3.setBounds(1500, 70, 34, 14);

        BuildingLabel4.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel4MouseMoved(evt);
            }
        });
        BuildingLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel4MouseClicked(evt);
            }
        });
        add(BuildingLabel4);
        BuildingLabel4.setBounds(1450, 70, 34, 14);

        PopIcon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PopIcon.setText("P");
        PopIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PopIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopIconMouseClicked(evt);
            }
        });
        add(PopIcon);
        PopIcon.setBounds(70, 0, 10, 19);

        PopCountLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PopCountLabel.setText("0000");
        PopCountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCountLabelMouseClicked(evt);
            }
        });
        add(PopCountLabel);
        PopCountLabel.setBounds(90, 0, 40, 17);

        ResourceIcon1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResourceIcon1.setText("S:");
        ResourceIcon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResourceIcon1MouseClicked(evt);
            }
        });
        add(ResourceIcon1);
        ResourceIcon1.setBounds(1350, 0, 20, 17);

        ResourceLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResourceLabel1.setText("0000");
        ResourceLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResourceLabel1MouseClicked(evt);
            }
        });
        add(ResourceLabel1);
        ResourceLabel1.setBounds(1380, 0, 40, 17);

        PopRateLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PopRateLabel.setText("0000");
        PopRateLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopRateLabelMouseClicked(evt);
            }
        });
        add(PopRateLabel);
        PopRateLabel.setBounds(140, 0, 100, 17);

        BuildingLabel5.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel5MouseMoved(evt);
            }
        });
        BuildingLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel5MouseClicked(evt);
            }
        });
        add(BuildingLabel5);
        BuildingLabel5.setBounds(1510, 30, 20, 20);

        BuildingLabel6.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel6MouseMoved(evt);
            }
        });
        BuildingLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel6MouseClicked(evt);
            }
        });
        add(BuildingLabel6);
        BuildingLabel6.setBounds(1470, 30, 30, 20);

        BuildingLabel7.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel7MouseMoved(evt);
            }
        });
        BuildingLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel7MouseClicked(evt);
            }
        });
        add(BuildingLabel7);
        BuildingLabel7.setBounds(1420, 30, 20, 20);

        BuildingLabel8.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel8MouseMoved(evt);
            }
        });
        BuildingLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel8MouseClicked(evt);
            }
        });
        add(BuildingLabel8);
        BuildingLabel8.setBounds(1500, 70, 34, 14);

        BuildingLabel9.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel9MouseMoved(evt);
            }
        });
        BuildingLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel9MouseClicked(evt);
            }
        });
        add(BuildingLabel9);
        BuildingLabel9.setBounds(1450, 70, 34, 14);

        PopLabel3.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel3MouseClicked(evt);
            }
        });
        add(PopLabel3);
        PopLabel3.setBounds(1440, 220, 5, 5);

        PopLabel4.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel4MouseClicked(evt);
            }
        });
        add(PopLabel4);
        PopLabel4.setBounds(1430, 190, 5, 5);

        PopLabel5.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel5MouseClicked(evt);
            }
        });
        add(PopLabel5);
        PopLabel5.setBounds(1590, 250, 5, 5);

        ResourceIcon2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResourceIcon2.setText("F:");
        ResourceIcon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResourceIcon2MouseClicked(evt);
            }
        });
        add(ResourceIcon2);
        ResourceIcon2.setBounds(1140, 0, 20, 17);

        ResourceLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResourceLabel2.setText("0000");
        ResourceLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResourceLabel2MouseClicked(evt);
            }
        });
        add(ResourceLabel2);
        ResourceLabel2.setBounds(1170, 0, 40, 17);

        TechLabel.setBackground(new java.awt.Color(51, 102, 255));
        TechLabel.setText("TECH");
        TechLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        TechLabel.setOpaque(true);
        TechLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechLabelMouseClicked(evt);
            }
        });
        add(TechLabel);
        TechLabel.setBounds(1280, 750, 62, 42);

        PopLabel6.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel6MouseClicked(evt);
            }
        });
        add(PopLabel6);
        PopLabel6.setBounds(1520, 230, 5, 5);

        PopLabel7.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel7MouseClicked(evt);
            }
        });
        add(PopLabel7);
        PopLabel7.setBounds(1510, 200, 5, 5);

        PopLabel8.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel8MouseClicked(evt);
            }
        });
        add(PopLabel8);
        PopLabel8.setBounds(1540, 200, 5, 5);

        PopLabel9.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel9MouseClicked(evt);
            }
        });
        add(PopLabel9);
        PopLabel9.setBounds(1460, 260, 5, 5);

        PopLabel10.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel10MouseClicked(evt);
            }
        });
        add(PopLabel10);
        PopLabel10.setBounds(1450, 230, 5, 5);

        PopLabel11.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel11MouseClicked(evt);
            }
        });
        add(PopLabel11);
        PopLabel11.setBounds(1480, 230, 5, 5);

        BuildingLabel10.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel10MouseMoved(evt);
            }
        });
        BuildingLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel10MouseClicked(evt);
            }
        });
        add(BuildingLabel10);
        BuildingLabel10.setBounds(1430, 30, 20, 20);

        BuildingLabel11.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel11MouseMoved(evt);
            }
        });
        BuildingLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel11MouseClicked(evt);
            }
        });
        add(BuildingLabel11);
        BuildingLabel11.setBounds(1390, 30, 30, 20);

        BuildingLabel12.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel12MouseMoved(evt);
            }
        });
        BuildingLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel12MouseClicked(evt);
            }
        });
        add(BuildingLabel12);
        BuildingLabel12.setBounds(1340, 30, 20, 20);

        BuildingLabel13.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel13MouseMoved(evt);
            }
        });
        BuildingLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel13MouseClicked(evt);
            }
        });
        add(BuildingLabel13);
        BuildingLabel13.setBounds(1430, 30, 20, 20);

        BuildingLabel14.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel14MouseMoved(evt);
            }
        });
        BuildingLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel14MouseClicked(evt);
            }
        });
        add(BuildingLabel14);
        BuildingLabel14.setBounds(1390, 30, 30, 20);

        BuildingLabel15.setBackground(new java.awt.Color(153, 153, 153));
        BuildingLabel15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuildingLabel15MouseMoved(evt);
            }
        });
        BuildingLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuildingLabel15MouseClicked(evt);
            }
        });
        add(BuildingLabel15);
        BuildingLabel15.setBounds(1340, 30, 20, 20);

        PopLabel12.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel12MouseClicked(evt);
            }
        });
        add(PopLabel12);
        PopLabel12.setBounds(1500, 190, 5, 5);

        PopLabel13.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel13MouseClicked(evt);
            }
        });
        add(PopLabel13);
        PopLabel13.setBounds(1490, 160, 5, 5);

        PopLabel14.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel14MouseClicked(evt);
            }
        });
        add(PopLabel14);
        PopLabel14.setBounds(1520, 160, 5, 5);

        PopLabel15.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel15MouseClicked(evt);
            }
        });
        add(PopLabel15);
        PopLabel15.setBounds(1440, 220, 5, 5);

        PopLabel16.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel16MouseClicked(evt);
            }
        });
        add(PopLabel16);
        PopLabel16.setBounds(1430, 190, 5, 5);

        PopLabel17.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel17MouseClicked(evt);
            }
        });
        add(PopLabel17);
        PopLabel17.setBounds(1460, 190, 5, 5);

        PopLabel18.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel18MouseClicked(evt);
            }
        });
        add(PopLabel18);
        PopLabel18.setBounds(1520, 230, 5, 5);

        PopLabel19.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel19MouseClicked(evt);
            }
        });
        add(PopLabel19);
        PopLabel19.setBounds(1510, 200, 5, 5);

        PopLabel20.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel20MouseClicked(evt);
            }
        });
        add(PopLabel20);
        PopLabel20.setBounds(1540, 200, 5, 5);

        PopLabel21.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel21MouseClicked(evt);
            }
        });
        add(PopLabel21);
        PopLabel21.setBounds(1460, 260, 5, 5);

        PopLabel22.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel22MouseClicked(evt);
            }
        });
        add(PopLabel22);
        PopLabel22.setBounds(1450, 230, 5, 5);

        PopLabel23.setBackground(new java.awt.Color(0, 0, 0));
        PopLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopLabel23MouseClicked(evt);
            }
        });
        add(PopLabel23);
        PopLabel23.setBounds(1480, 230, 5, 5);

        ItemsLabel.setBackground(new java.awt.Color(255, 0, 255));
        ItemsLabel.setText("ITEMS");
        ItemsLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        ItemsLabel.setOpaque(true);
        ItemsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ItemsLabelMouseClicked(evt);
            }
        });
        add(ItemsLabel);
        ItemsLabel.setBounds(1070, 750, 62, 42);

        NameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NameLabel.setText("CIV NAME");
        add(NameLabel);
        NameLabel.setBounds(560, 0, 120, 20);

        EraLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        EraLabel.setText("ERA");
        add(EraLabel);
        EraLabel.setBounds(400, 0, 40, 20);

        GoodsIcon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GoodsIcon.setText("G:");
        GoodsIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GoodsIconMouseClicked(evt);
            }
        });
        add(GoodsIcon);
        GoodsIcon.setBounds(290, 0, 20, 17);

        GoodsLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GoodsLabel.setText("0000");
        GoodsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GoodsLabelMouseClicked(evt);
            }
        });
        add(GoodsLabel);
        GoodsLabel.setBounds(320, 0, 40, 17);

        MilitaryLabel.setBackground(new java.awt.Color(255, 0, 51));
        MilitaryLabel.setText("MILITARY");
        MilitaryLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        MilitaryLabel.setOpaque(true);
        MilitaryLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MilitaryLabelMouseClicked(evt);
            }
        });
        add(MilitaryLabel);
        MilitaryLabel.setBounds(1210, 750, 62, 42);

        AnimalLabel0.setBackground(new java.awt.Color(0, 0, 0));
        AnimalLabel0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AnimalLabel0MouseClicked(evt);
            }
        });
        add(AnimalLabel0);
        AnimalLabel0.setBounds(1520, 230, 5, 5);

        AnimalLabel1.setBackground(new java.awt.Color(0, 0, 0));
        AnimalLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AnimalLabel1MouseClicked(evt);
            }
        });
        add(AnimalLabel1);
        AnimalLabel1.setBounds(1520, 230, 5, 5);

        AnimalLabel2.setBackground(new java.awt.Color(0, 0, 0));
        AnimalLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AnimalLabel2MouseClicked(evt);
            }
        });
        add(AnimalLabel2);
        AnimalLabel2.setBounds(1520, 230, 5, 5);

        WildLabel3.setBackground(new java.awt.Color(0, 0, 0));
        WildLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WildLabel3MouseClicked(evt);
            }
        });
        add(WildLabel3);
        WildLabel3.setBounds(1520, 230, 5, 5);

        WildLabel4.setBackground(new java.awt.Color(0, 0, 0));
        WildLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WildLabel4MouseClicked(evt);
            }
        });
        add(WildLabel4);
        WildLabel4.setBounds(1520, 230, 5, 5);

        WildLabel5.setBackground(new java.awt.Color(0, 0, 0));
        WildLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WildLabel5MouseClicked(evt);
            }
        });
        add(WildLabel5);
        WildLabel5.setBounds(1520, 230, 5, 5);

        WildLabel6.setBackground(new java.awt.Color(0, 0, 0));
        WildLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WildLabel6MouseClicked(evt);
            }
        });
        add(WildLabel6);
        WildLabel6.setBounds(1520, 230, 5, 5);

        WildLabel7.setBackground(new java.awt.Color(0, 0, 0));
        WildLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WildLabel7MouseClicked(evt);
            }
        });
        add(WildLabel7);
        WildLabel7.setBounds(1520, 230, 5, 5);

        WildLabel8.setBackground(new java.awt.Color(0, 0, 0));
        WildLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WildLabel8MouseClicked(evt);
            }
        });
        add(WildLabel8);
        WildLabel8.setBounds(1520, 230, 5, 5);

        WildLabel9.setBackground(new java.awt.Color(0, 0, 0));
        WildLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WildLabel9MouseClicked(evt);
            }
        });
        add(WildLabel9);
        WildLabel9.setBounds(1520, 230, 5, 5);

        MapPos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        MapPos.setText("LOCATION");
        MapPos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MapPosMouseClicked(evt);
            }
        });
        add(MapPos);
        MapPos.setBounds(970, 0, 100, 17);

        LeftButton.setText("L");
        LeftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeftButtonActionPerformed(evt);
            }
        });
        add(LeftButton);
        LeftButton.setBounds(690, 760, 40, 20);

        RightButton.setText("R");
        RightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RightButtonActionPerformed(evt);
            }
        });
        add(RightButton);
        RightButton.setBounds(770, 760, 40, 20);

        ZeroButton.setText("0");
        ZeroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZeroButtonActionPerformed(evt);
            }
        });
        add(ZeroButton);
        ZeroButton.setBounds(730, 760, 40, 20);

        PopCarry0.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry0MouseClicked(evt);
            }
        });
        add(PopCarry0);
        PopCarry0.setBounds(980, 190, 10, 10);

        PopCarry1.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry1MouseClicked(evt);
            }
        });
        add(PopCarry1);
        PopCarry1.setBounds(1035, 190, 10, 10);

        PopCarry2.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry2MouseClicked(evt);
            }
        });
        add(PopCarry2);
        PopCarry2.setBounds(1035, 190, 10, 10);

        PopCarry3.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry3MouseClicked(evt);
            }
        });
        add(PopCarry3);
        PopCarry3.setBounds(1035, 190, 10, 10);

        PopCarry4.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry4MouseClicked(evt);
            }
        });
        add(PopCarry4);
        PopCarry4.setBounds(1035, 190, 10, 10);

        PopCarry5.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry5MouseClicked(evt);
            }
        });
        add(PopCarry5);
        PopCarry5.setBounds(1035, 190, 10, 10);

        PopCarry6.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry6MouseClicked(evt);
            }
        });
        add(PopCarry6);
        PopCarry6.setBounds(1035, 190, 10, 10);

        PopCarry7.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry7MouseClicked(evt);
            }
        });
        add(PopCarry7);
        PopCarry7.setBounds(1035, 190, 10, 10);

        PopCarry8.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry8MouseClicked(evt);
            }
        });
        add(PopCarry8);
        PopCarry8.setBounds(1035, 190, 10, 10);

        PopCarry9.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry9MouseClicked(evt);
            }
        });
        add(PopCarry9);
        PopCarry9.setBounds(1035, 190, 10, 10);

        PopCarry10.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry10MouseClicked(evt);
            }
        });
        add(PopCarry10);
        PopCarry10.setBounds(1035, 190, 10, 10);

        PopCarry11.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry11MouseClicked(evt);
            }
        });
        add(PopCarry11);
        PopCarry11.setBounds(1035, 190, 10, 10);

        PopCarry12.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry12MouseClicked(evt);
            }
        });
        add(PopCarry12);
        PopCarry12.setBounds(1035, 190, 10, 10);

        PopCarry13.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry13MouseClicked(evt);
            }
        });
        add(PopCarry13);
        PopCarry13.setBounds(1035, 190, 10, 10);

        PopCarry14.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry14MouseClicked(evt);
            }
        });
        add(PopCarry14);
        PopCarry14.setBounds(1035, 190, 10, 10);

        PopCarry15.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry15MouseClicked(evt);
            }
        });
        add(PopCarry15);
        PopCarry15.setBounds(1035, 190, 10, 10);

        PopCarry16.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry16MouseClicked(evt);
            }
        });
        add(PopCarry16);
        PopCarry16.setBounds(1035, 190, 10, 10);

        PopCarry17.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry17MouseClicked(evt);
            }
        });
        add(PopCarry17);
        PopCarry17.setBounds(1035, 190, 10, 10);

        PopCarry18.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry18MouseClicked(evt);
            }
        });
        add(PopCarry18);
        PopCarry18.setBounds(1035, 190, 10, 10);

        PopCarry19.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry19MouseClicked(evt);
            }
        });
        add(PopCarry19);
        PopCarry19.setBounds(1035, 190, 10, 10);

        PopCarry20.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry20MouseClicked(evt);
            }
        });
        add(PopCarry20);
        PopCarry20.setBounds(1035, 190, 10, 10);

        PopCarry21.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry21MouseClicked(evt);
            }
        });
        add(PopCarry21);
        PopCarry21.setBounds(1035, 190, 10, 10);

        PopCarry22.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry22MouseClicked(evt);
            }
        });
        add(PopCarry22);
        PopCarry22.setBounds(1035, 190, 10, 10);

        PopCarry23.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry23MouseClicked(evt);
            }
        });
        add(PopCarry23);
        PopCarry23.setBounds(1035, 190, 10, 10);

        PopCarry24.setBackground(new java.awt.Color(0, 0, 0));
        PopCarry24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopCarry24MouseClicked(evt);
            }
        });
        add(PopCarry24);
        PopCarry24.setBounds(980, 190, 10, 10);

        BarbLabel0.setBackground(new java.awt.Color(0, 0, 0));
        BarbLabel0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BarbLabel0MouseClicked(evt);
            }
        });
        add(BarbLabel0);
        BarbLabel0.setBounds(820, 230, 5, 5);

        BarbLabel1.setBackground(new java.awt.Color(0, 0, 0));
        BarbLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BarbLabel1MouseClicked(evt);
            }
        });
        add(BarbLabel1);
        BarbLabel1.setBounds(820, 230, 5, 5);

        BarbLabel2.setBackground(new java.awt.Color(0, 0, 0));
        BarbLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BarbLabel2MouseClicked(evt);
            }
        });
        add(BarbLabel2);
        BarbLabel2.setBounds(820, 230, 5, 5);

        BarbLabel3.setBackground(new java.awt.Color(0, 0, 0));
        BarbLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BarbLabel3MouseClicked(evt);
            }
        });
        add(BarbLabel3);
        BarbLabel3.setBounds(820, 230, 5, 5);

        BarbLabel4.setBackground(new java.awt.Color(0, 0, 0));
        BarbLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BarbLabel4MouseClicked(evt);
            }
        });
        add(BarbLabel4);
        BarbLabel4.setBounds(820, 230, 5, 5);

        BarbLabel5.setBackground(new java.awt.Color(0, 0, 0));
        BarbLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BarbLabel5MouseClicked(evt);
            }
        });
        add(BarbLabel5);
        BarbLabel5.setBounds(820, 230, 5, 5);

        BarbLabel6.setBackground(new java.awt.Color(0, 0, 0));
        BarbLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BarbLabel6MouseClicked(evt);
            }
        });
        add(BarbLabel6);
        BarbLabel6.setBounds(820, 230, 5, 5);

        BarbLabel7.setBackground(new java.awt.Color(0, 0, 0));
        BarbLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BarbLabel7MouseClicked(evt);
            }
        });
        add(BarbLabel7);
        BarbLabel7.setBounds(820, 230, 5, 5);

        BarbLabel8.setBackground(new java.awt.Color(0, 0, 0));
        BarbLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BarbLabel8MouseClicked(evt);
            }
        });
        add(BarbLabel8);
        BarbLabel8.setBounds(820, 230, 5, 5);

        BarbLabel9.setBackground(new java.awt.Color(0, 0, 0));
        BarbLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BarbLabel9MouseClicked(evt);
            }
        });
        add(BarbLabel9);
        BarbLabel9.setBounds(820, 230, 5, 5);
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        keyPressed(evt);
    }//GEN-LAST:event_formKeyPressed

    private void UpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpButtonActionPerformed
    	YbuttonClick(5);
    }//GEN-LAST:event_UpButtonActionPerformed

    private void DownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DownButtonActionPerformed
    	YbuttonClick(-5);
    }//GEN-LAST:event_DownButtonActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        mouseMove(evt);
    }//GEN-LAST:event_formMouseMoved

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        openBuildMenu();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void TerrainLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TerrainLabelMouseClicked
        screenClick(evt);
    }//GEN-LAST:event_TerrainLabelMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void TerrainLabelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TerrainLabelMouseMoved
        mouseMove(evt);
    }//GEN-LAST:event_TerrainLabelMouseMoved

    private void BuildingLabel0MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel0MouseMoved
    	mouseMoveBuilding(0,evt);
    }//GEN-LAST:event_BuildingLabel0MouseMoved

    private void BuildingLabel0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel0MouseClicked
        buildingClick(0);
    }//GEN-LAST:event_BuildingLabel0MouseClicked

    private void BuildingLabel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel1MouseMoved
    	mouseMoveBuilding(1,evt);
    }//GEN-LAST:event_BuildingLabel1MouseMoved

    private void BuildingLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel1MouseClicked
        buildingClick(1);
    }//GEN-LAST:event_BuildingLabel1MouseClicked

    private void BuildingLabel2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel2MouseMoved
    	mouseMoveBuilding(2,evt);
    }//GEN-LAST:event_BuildingLabel2MouseMoved

    private void BuildingLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel2MouseClicked
        buildingClick(2);
    }//GEN-LAST:event_BuildingLabel2MouseClicked

    private void TasksLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TasksLblMouseClicked
        outputTaskList();
    }//GEN-LAST:event_TasksLblMouseClicked

    private void PopOutputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopOutputMouseClicked
    	showPopInfo();
    }//GEN-LAST:event_PopOutputMouseClicked

    private void WorkforceLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WorkforceLabelMouseClicked
        showWorkforce();
    }//GEN-LAST:event_WorkforceLabelMouseClicked

    private void BuildingLabel3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel3MouseMoved
    	mouseMoveBuilding(3,evt);
    }//GEN-LAST:event_BuildingLabel3MouseMoved

    private void BuildingLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel3MouseClicked
    	buildingClick(3);
    }//GEN-LAST:event_BuildingLabel3MouseClicked

    private void BuildingLabel4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel4MouseMoved
    	mouseMoveBuilding(4,evt);
    }//GEN-LAST:event_BuildingLabel4MouseMoved

    private void BuildingLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel4MouseClicked
    	buildingClick(4);
    }//GEN-LAST:event_BuildingLabel4MouseClicked

    private void BuildingLabel5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel5MouseMoved
    	mouseMoveBuilding(5,evt);
    }//GEN-LAST:event_BuildingLabel5MouseMoved

    private void BuildingLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel5MouseClicked
        buildingClick(5);
    }//GEN-LAST:event_BuildingLabel5MouseClicked

    private void BuildingLabel6MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel6MouseMoved
    	mouseMoveBuilding(6,evt);
    }//GEN-LAST:event_BuildingLabel6MouseMoved

    private void BuildingLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel6MouseClicked
        buildingClick(6);
    }//GEN-LAST:event_BuildingLabel6MouseClicked

    private void BuildingLabel7MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel7MouseMoved
    	mouseMoveBuilding(7,evt);
    }//GEN-LAST:event_BuildingLabel7MouseMoved

    private void BuildingLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel7MouseClicked
        buildingClick(7);
    }//GEN-LAST:event_BuildingLabel7MouseClicked

    private void BuildingLabel8MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel8MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_BuildingLabel8MouseMoved

    private void BuildingLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel8MouseClicked
        buildingClick(8);
    }//GEN-LAST:event_BuildingLabel8MouseClicked

    private void BuildingLabel9MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel9MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_BuildingLabel9MouseMoved

    private void BuildingLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel9MouseClicked
        buildingClick(9);
    }//GEN-LAST:event_BuildingLabel9MouseClicked

    private void TechLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TechLabelMouseClicked
    	showResearchScreen();
    }//GEN-LAST:event_TechLabelMouseClicked

    private void ResourceLabel0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResourceLabel0MouseClicked
        showInventoryScreen();
    }//GEN-LAST:event_ResourceLabel0MouseClicked

    private void ResourceIcon0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResourceIcon0MouseClicked
        showInventoryScreen();
    }//GEN-LAST:event_ResourceIcon0MouseClicked

    private void ResourceIcon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResourceIcon1MouseClicked
        showInventoryScreen();
    }//GEN-LAST:event_ResourceIcon1MouseClicked

    private void ResourceLabel1MouseClicked(java.awt.event.MouseEvent evt) {                                            
        showInventoryScreen();
    }                                           
    
    private void PopRateLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResourceLabel1MouseClicked
    	showPopInfo();
    }//GEN-LAST:event_ResourceLabel1MouseClicked

    private void PopLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel1MouseClicked
    	personClick(1);
    }//GEN-LAST:event_PopLabel1MouseClicked

    private void PopLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel2MouseClicked
    	personClick(2);
    }//GEN-LAST:event_PopLabel2MouseClicked

    private void PopLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel4MouseClicked
    	personClick(4);
    }//GEN-LAST:event_PopLabel4MouseClicked

    private void PopLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel5MouseClicked
    	personClick(5);
    }//GEN-LAST:event_PopLabel5MouseClicked

    private void PopLabel0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel0MouseClicked
    	personClick(0);
    }//GEN-LAST:event_PopLabel0MouseClicked

    private void PopLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel7MouseClicked
    	personClick(7);
    }//GEN-LAST:event_PopLabel7MouseClicked

    private void PopLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel8MouseClicked
    	personClick(8);
    }//GEN-LAST:event_PopLabel8MouseClicked

    private void PopLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel3MouseClicked
    	personClick(3);
    }//GEN-LAST:event_PopLabel3MouseClicked

    private void PopLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel10MouseClicked
    	personClick(10);
    }//GEN-LAST:event_PopLabel10MouseClicked

    private void PopLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel11MouseClicked
    	personClick(11);
    }//GEN-LAST:event_PopLabel11MouseClicked

    private void PopLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel6MouseClicked
    	personClick(6);
    }//GEN-LAST:event_PopLabel6MouseClicked

    private void PopLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel9MouseClicked
    	personClick(9);
    }//GEN-LAST:event_PopLabel9MouseClicked

    private void BuildingLabel10MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel10MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_BuildingLabel10MouseMoved

    private void BuildingLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel10MouseClicked
    	buildingClick(10);
    }//GEN-LAST:event_BuildingLabel10MouseClicked

    private void BuildingLabel11MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel11MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_BuildingLabel11MouseMoved

    private void BuildingLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel11MouseClicked
    	buildingClick(11);
    }//GEN-LAST:event_BuildingLabel11MouseClicked

    private void BuildingLabel12MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel12MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_BuildingLabel12MouseMoved

    private void BuildingLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel12MouseClicked
    	buildingClick(12);
    }//GEN-LAST:event_BuildingLabel12MouseClicked

    private void BuildingLabel13MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel13MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_BuildingLabel13MouseMoved

    private void BuildingLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel13MouseClicked
    	buildingClick(13);
    }//GEN-LAST:event_BuildingLabel13MouseClicked

    private void BuildingLabel14MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel14MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_BuildingLabel14MouseMoved

    private void BuildingLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel14MouseClicked
    	buildingClick(14);
    }//GEN-LAST:event_BuildingLabel14MouseClicked

    private void BuildingLabel15MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel15MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_BuildingLabel15MouseMoved

    private void BuildingLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuildingLabel15MouseClicked
    	buildingClick(15);
    }//GEN-LAST:event_BuildingLabel15MouseClicked

    private void PopLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopLabel12MouseClicked

    private void PopLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopLabel13MouseClicked

    private void PopLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopLabel14MouseClicked

    private void PopLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopLabel15MouseClicked

    private void PopLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopLabel16MouseClicked

    private void PopLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopLabel17MouseClicked

    private void PopLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopLabel18MouseClicked

    private void PopLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopLabel19MouseClicked

    private void PopLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopLabel20MouseClicked

    private void PopLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopLabel21MouseClicked

    private void PopLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel22MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopLabel22MouseClicked

    private void PopLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopLabel23MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopLabel23MouseClicked

    private void ItemsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ItemsLabelMouseClicked
    	showItemsScreen();
    }//GEN-LAST:event_ItemsLabelMouseClicked

    private void ResourceLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResourceLabel2MouseClicked
    	showInventoryScreen();
    }//GEN-LAST:event_ResourceLabel2MouseClicked

    private void GoodsIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GoodsIconMouseClicked
    	showItemsScreen();
    }//GEN-LAST:event_GoodsIconMouseClicked

    private void GoodsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GoodsLabelMouseClicked
    	showItemsScreen();
    }//GEN-LAST:event_GoodsLabelMouseClicked

    private void MilitaryLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MilitaryLabelMouseClicked
    	showMilitaryScreen();
    }//GEN-LAST:event_MilitaryLabelMouseClicked

    private void AnimalLabel0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnimalLabel0MouseClicked
    	wildClicked(0);
    }//GEN-LAST:event_AnimalLabel0MouseClicked

    private void AnimalLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnimalLabel1MouseClicked
    	wildClicked(1);
    }//GEN-LAST:event_AnimalLabel1MouseClicked

    private void AnimalLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnimalLabel2MouseClicked
    	wildClicked(2);
    }//GEN-LAST:event_AnimalLabel2MouseClicked

    private void WildLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WildLabel3MouseClicked
    	wildClicked(3);
    }//GEN-LAST:event_WildLabel3MouseClicked

    private void WildLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WildLabel4MouseClicked
    	wildClicked(4);
    }//GEN-LAST:event_WildLabel4MouseClicked

    private void WildLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WildLabel5MouseClicked
    	wildClicked(5);
    }//GEN-LAST:event_WildLabel5MouseClicked

    private void WildLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WildLabel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_WildLabel6MouseClicked

    private void WildLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WildLabel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_WildLabel7MouseClicked

    private void WildLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WildLabel8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_WildLabel8MouseClicked

    private void WildLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WildLabel9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_WildLabel9MouseClicked

    private void MapPosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MapPosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_MapPosMouseClicked

    private void LeftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftButtonActionPerformed
    	XbuttonClick(-5);
    }//GEN-LAST:event_LeftButtonActionPerformed

    private void RightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightButtonActionPerformed
    	XbuttonClick(5);
    }//GEN-LAST:event_RightButtonActionPerformed

    private void ZeroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZeroButtonActionPerformed
    	recenterMapClick();
    }//GEN-LAST:event_ZeroButtonActionPerformed

    private void ResourceIcon2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResourceIcon2MouseClicked
    	showInventoryScreen();
    }//GEN-LAST:event_ResourceIcon2MouseClicked

    private void PopIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopIconMouseClicked
    	showPopInfo();
    }//GEN-LAST:event_PopIconMouseClicked

    private void PopCountLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCountLabelMouseClicked
    	showPopInfo();
    }//GEN-LAST:event_PopCountLabelMouseClicked

    private void PopCarry0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry0MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry0MouseClicked

    private void PopCarry1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry1MouseClicked

    private void PopCarry2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry2MouseClicked

    private void PopCarry3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry3MouseClicked

    private void PopCarry4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry4MouseClicked

    private void PopCarry5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry5MouseClicked

    private void PopCarry6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry6MouseClicked

    private void PopCarry7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry7MouseClicked

    private void PopCarry8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry8MouseClicked

    private void PopCarry9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry9MouseClicked

    private void PopCarry10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry10MouseClicked

    private void PopCarry11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry11MouseClicked

    private void PopCarry12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry12MouseClicked

    private void PopCarry13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry13MouseClicked

    private void PopCarry14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry14MouseClicked

    private void PopCarry15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry15MouseClicked

    private void PopCarry16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry16MouseClicked

    private void PopCarry17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry17MouseClicked

    private void PopCarry18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry18MouseClicked

    private void PopCarry19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry19MouseClicked

    private void PopCarry20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry20MouseClicked

    private void PopCarry21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry21MouseClicked

    private void PopCarry22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry22MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry22MouseClicked

    private void PopCarry23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry23MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry23MouseClicked

    private void PopCarry24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopCarry24MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PopCarry24MouseClicked

    private void BarbLabel0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarbLabel0MouseClicked
    	barbClicked(0);
    }//GEN-LAST:event_BarbLabel0MouseClicked

    private void BarbLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarbLabel1MouseClicked
    	barbClicked(1);
    }//GEN-LAST:event_BarbLabel1MouseClicked

    private void BarbLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarbLabel2MouseClicked
    	barbClicked(2);
    }//GEN-LAST:event_BarbLabel2MouseClicked

    private void BarbLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarbLabel3MouseClicked
    	barbClicked(3);
    }//GEN-LAST:event_BarbLabel3MouseClicked

    private void BarbLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarbLabel4MouseClicked
    	barbClicked(4);
    }//GEN-LAST:event_BarbLabel4MouseClicked

    private void BarbLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarbLabel5MouseClicked
    	barbClicked(5);
    }//GEN-LAST:event_BarbLabel5MouseClicked

    private void BarbLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarbLabel6MouseClicked
    	barbClicked(6);
    }//GEN-LAST:event_BarbLabel6MouseClicked

    private void BarbLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarbLabel7MouseClicked
    	barbClicked(7);
    }//GEN-LAST:event_BarbLabel7MouseClicked

    private void BarbLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarbLabel8MouseClicked
    	barbClicked(8);
    }//GEN-LAST:event_BarbLabel8MouseClicked

    private void BarbLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarbLabel9MouseClicked
    	barbClicked(9);
    }//GEN-LAST:event_BarbLabel9MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AnimalLabel0;
    private javax.swing.JLabel AnimalLabel1;
    private javax.swing.JLabel AnimalLabel2;
    private javax.swing.JLabel BarbLabel0;
    private javax.swing.JLabel BarbLabel1;
    private javax.swing.JLabel BarbLabel2;
    private javax.swing.JLabel BarbLabel3;
    private javax.swing.JLabel BarbLabel4;
    private javax.swing.JLabel BarbLabel5;
    private javax.swing.JLabel BarbLabel6;
    private javax.swing.JLabel BarbLabel7;
    private javax.swing.JLabel BarbLabel8;
    private javax.swing.JLabel BarbLabel9;
    private javax.swing.JLabel BuildingLabel0;
    private javax.swing.JLabel BuildingLabel1;
    private javax.swing.JLabel BuildingLabel10;
    private javax.swing.JLabel BuildingLabel11;
    private javax.swing.JLabel BuildingLabel12;
    private javax.swing.JLabel BuildingLabel13;
    private javax.swing.JLabel BuildingLabel14;
    private javax.swing.JLabel BuildingLabel15;
    private javax.swing.JLabel BuildingLabel2;
    private javax.swing.JLabel BuildingLabel3;
    private javax.swing.JLabel BuildingLabel4;
    private javax.swing.JLabel BuildingLabel5;
    private javax.swing.JLabel BuildingLabel6;
    private javax.swing.JLabel BuildingLabel7;
    private javax.swing.JLabel BuildingLabel8;
    private javax.swing.JLabel BuildingLabel9;
    private javax.swing.JButton DownButton;
    private javax.swing.JLabel EraLabel;
    private javax.swing.JLabel GoodsIcon;
    private javax.swing.JLabel GoodsLabel;
    private javax.swing.JLabel ItemsLabel;
    private javax.swing.JButton LeftButton;
    private javax.swing.JLabel MapPos;
    private javax.swing.JLabel MilitaryLabel;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JLabel PopCarry0;
    private javax.swing.JLabel PopCarry1;
    private javax.swing.JLabel PopCarry10;
    private javax.swing.JLabel PopCarry11;
    private javax.swing.JLabel PopCarry12;
    private javax.swing.JLabel PopCarry13;
    private javax.swing.JLabel PopCarry14;
    private javax.swing.JLabel PopCarry15;
    private javax.swing.JLabel PopCarry16;
    private javax.swing.JLabel PopCarry17;
    private javax.swing.JLabel PopCarry18;
    private javax.swing.JLabel PopCarry19;
    private javax.swing.JLabel PopCarry2;
    private javax.swing.JLabel PopCarry20;
    private javax.swing.JLabel PopCarry21;
    private javax.swing.JLabel PopCarry22;
    private javax.swing.JLabel PopCarry23;
    private javax.swing.JLabel PopCarry24;
    private javax.swing.JLabel PopCarry3;
    private javax.swing.JLabel PopCarry4;
    private javax.swing.JLabel PopCarry5;
    private javax.swing.JLabel PopCarry6;
    private javax.swing.JLabel PopCarry7;
    private javax.swing.JLabel PopCarry8;
    private javax.swing.JLabel PopCarry9;
    private javax.swing.JLabel PopCountLabel;
    private javax.swing.JLabel PopIcon;
    private javax.swing.JLabel PopLabel0;
    private javax.swing.JLabel PopLabel1;
    private javax.swing.JLabel PopLabel10;
    private javax.swing.JLabel PopLabel11;
    private javax.swing.JLabel PopLabel12;
    private javax.swing.JLabel PopLabel13;
    private javax.swing.JLabel PopLabel14;
    private javax.swing.JLabel PopLabel15;
    private javax.swing.JLabel PopLabel16;
    private javax.swing.JLabel PopLabel17;
    private javax.swing.JLabel PopLabel18;
    private javax.swing.JLabel PopLabel19;
    private javax.swing.JLabel PopLabel2;
    private javax.swing.JLabel PopLabel20;
    private javax.swing.JLabel PopLabel21;
    private javax.swing.JLabel PopLabel22;
    private javax.swing.JLabel PopLabel23;
    private javax.swing.JLabel PopLabel3;
    private javax.swing.JLabel PopLabel4;
    private javax.swing.JLabel PopLabel5;
    private javax.swing.JLabel PopLabel6;
    private javax.swing.JLabel PopLabel7;
    private javax.swing.JLabel PopLabel8;
    private javax.swing.JLabel PopLabel9;
    private javax.swing.JLabel PopOutput;
    private javax.swing.JLabel PopRateLabel;
    private javax.swing.JLabel ResourceIcon0;
    private javax.swing.JLabel ResourceIcon1;
    private javax.swing.JLabel ResourceIcon2;
    private javax.swing.JLabel ResourceLabel0;
    private javax.swing.JLabel ResourceLabel1;
    private javax.swing.JLabel ResourceLabel2;
    private javax.swing.JButton RightButton;
    private javax.swing.JLabel TasksLbl;
    private javax.swing.JLabel TechLabel;
    private javax.swing.JLabel TerrainLabel;
    private javax.swing.JLabel TimerLabel;
    private javax.swing.JButton UpButton;
    private javax.swing.JLabel WildLabel3;
    private javax.swing.JLabel WildLabel4;
    private javax.swing.JLabel WildLabel5;
    private javax.swing.JLabel WildLabel6;
    private javax.swing.JLabel WildLabel7;
    private javax.swing.JLabel WildLabel8;
    private javax.swing.JLabel WildLabel9;
    private javax.swing.JLabel WorkforceLabel;
    private javax.swing.JButton ZeroButton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JLabel BuildingLabel;
    private javax.swing.JLabel NewObjectLabel;
}
