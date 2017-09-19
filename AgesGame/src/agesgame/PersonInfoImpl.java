/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agesgame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author cpemm_000
 */
public class PersonInfoImpl extends javax.swing.JPanel {
    private GameDataClass game;
    public int id;
    public float SCALE;
    public boolean population;
    public PersonInfoImpl(GameDataClass game, float SCALE) {
    	this.SCALE = SCALE;
        this.game = game;
        
        initComponents();
        scaleView();
    }
    public void generateDisplay(){
       if (population){
    	   BuildingValueLabel.setVisible(true);
    	   ImageLabel.setIcon(new ImageIcon(game.getPopulation(id).getImage().getScaledInstance(Scale(35), Scale(59), 0)));
    	   FoodLabel.setVisible(true);
    	   HappyLabel.setVisible(true);
    	   HuntLabel.setVisible(false);
    	   ItemLabel.setVisible(true);
       }else{
    	   BuildingValueLabel.setVisible(false);
    	   GoodsLabel.setVisible(false);
    	   FoodLabel.setVisible(false);
    	   HappyLabel.setVisible(false);
    	   HuntLabel.setVisible(true);
    	   ItemLabel.setVisible(false);
    	   
    	   if (id > 9){
    		   int i = id - 10;
    		   ImageLabel.setIcon(new ImageIcon(game.getBarbarian(i).getImage().getScaledInstance(Scale(35), Scale(59), 0)));
    		   HuntLabel.setText("FIGHT");
    	   }else{
    		   HuntLabel.setText("HUNT");
    		   
    	   }
       }
        
        
        
        refreshDisplay();
    }
    public void refreshDisplay(){
    	if (population){
	    	if (game.getPopulation(id).task != null){
	    		BuildingTypeLabel.setText(game.getPopulation(id).task.getString());
	    		BuildingValueLabel.setText(Math.round(game.getPopulation(id).workCount) + "");
	    	}else{
	    		BuildingTypeLabel.setText("Idle");
	    	}
	        
	    	UpgradeCostLabel.setText(game.getPopulation(id).getX() + ", " + game.getPopulation(id).getY());
	    	FoodLabel.setText("F:" + Math.round(game.getPopulation(id).food));
	    	
	    	if (game.goodsConsumption()){
	    		GoodsLabel.setVisible(true);
	    		GoodsLabel.setText("G: " +game.getPopulation(id).getGoodsLevel() + " ("+ Math.round(game.getPopulation(id).getGoodsValue()) + ")");
	    	}else{
	    		GoodsLabel.setVisible(false);
	    	}
	    	
	    	
	    	HappyLabel.setSize(Scale(25), Scale(25));
	    	HappyLabel.setIcon(new ImageIcon(game.tables.getHappyIcon(game.getPopulation(id).getHappyness()).getScaledInstance(Scale(25), Scale(25), 0)));
	    	
	    	if (game.getPopulation(id).getWeapon() > 0){
	    		ItemLabel.setText(game.tables.getItemName(game.getPopulation(id).getWeapon()));
	    	}else{
	    		ItemLabel.setText("");
	    	}
    	}else{
    		if(id < 10){
    			BuildingTypeLabel.setText(game.getAnimal(id).getName());
    			UpgradeCostLabel.setText(game.getPopulation(id).getX() + ", " + game.getPopulation(id).getY());
    			
    			if (game.getAnimal(id).getHunted()){
    				HuntLabel.setBackground(new java.awt.Color(177, 122, 116));   
    	 		   }else{
    	 			HuntLabel.setBackground(new java.awt.Color(237, 28, 36));   
    	 		   }
    			
    		}else{
    			int i = id - 10;
    			UpgradeCostLabel.setText(game.getBarbarian(i).getX() + ", " + game.getBarbarian(i).getY());
    			BuildingTypeLabel.setText("Barbarian");
    		}
    		
    		
    	}
    }
    public void setID(int id){
    	population = true;
        this.id = id;
        generateDisplay();
    }
    public void setCreatureID(int id){
    	population = false;
    	this.id = id;
    	generateDisplay();
    }
    public void huntButton(){
    	game.getAnimal(id).setHunt(id);
    }
    public void scaleView(){
    	for (int i = 0; i < 9; i ++){
        	JLabel setLabel = new javax.swing.JLabel();;
        	if (i == 0)
        		setLabel = ImageLabel;
        	if (i == 1)
        		setLabel = BuildingTypeLabel;
        	if (i == 2)
        		setLabel = BuildingValueLabel;
        	if (i == 3)
        		setLabel = UpgradeCostLabel;
        	if (i == 4)
        		setLabel = FoodLabel;
        	if (i == 5)
        		setLabel = HappyLabel;
        	if (i == 6)
        		setLabel = GoodsLabel;
        	if (i == 7)
        		setLabel = ItemLabel;
        	if (i == 8)
        		setLabel = HuntLabel;
        	
        	
        	
        	setLabel.setBounds(Scale(setLabel.getX()), Scale(setLabel.getY()), Scale(setLabel.getWidth()), Scale(setLabel.getHeight()));
        }
    	

    	BuildingTypeLabel.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
    	
    	BuildingValueLabel.setFont(new java.awt.Font("Tahoma", 0, Scale(14))); // NOI18N
    	FoodLabel.setFont(new java.awt.Font("Tahoma", 0, Scale(14))); // NOI18N
    	GoodsLabel.setFont(new java.awt.Font("Tahoma", 0, Scale(14)));
    	ItemLabel.setFont(new java.awt.Font("Tahoma", 0, Scale(14)));
    	UpgradeCostLabel.setFont(new java.awt.Font("Tahoma", 0, Scale(12))); // NOI18N
    	HuntLabel.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
    }
    public int Scale(int in){
    	return Math.round(in * SCALE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageLabel = new javax.swing.JLabel();
        UpgradeCostLabel = new javax.swing.JLabel();
        BuildingTypeLabel = new javax.swing.JLabel();
        BuildingValueLabel = new javax.swing.JLabel();
        HappyLabel = new javax.swing.JLabel();
        GoodsLabel = new javax.swing.JLabel();
        FoodLabel = new javax.swing.JLabel();
        ItemLabel = new javax.swing.JLabel();
        HuntLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(null);

        ImageLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        add(ImageLabel);
        ImageLabel.setBounds(10, 10, 30, 60);

        UpgradeCostLabel.setText("COST");
        add(UpgradeCostLabel);
        UpgradeCostLabel.setBounds(410, 50, 80, 33);

        BuildingTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BuildingTypeLabel.setText("TYPE");
        add(BuildingTypeLabel);
        BuildingTypeLabel.setBounds(110, 10, 100, 20);

        BuildingValueLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BuildingValueLabel.setText("(100%)");
        add(BuildingValueLabel);
        BuildingValueLabel.setBounds(120, 30, 70, 20);

        HappyLabel.setText("HAP");
        add(HappyLabel);
        HappyLabel.setBounds(60, 10, 20, 33);

        GoodsLabel.setText("Goods");
        add(GoodsLabel);
        GoodsLabel.setBounds(370, 30, 34, 33);

        FoodLabel.setText("Food");
        add(FoodLabel);
        FoodLabel.setBounds(370, 10, 59, 33);

        ItemLabel.setText("ITEM");
        add(ItemLabel);
        ItemLabel.setBounds(220, 10, 90, 33);

        HuntLabel.setBackground(new java.awt.Color(255, 102, 102));
        HuntLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HuntLabel.setText("HUNT");
        HuntLabel.setOpaque(true);
        HuntLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HuntLabelMouseClicked(evt);
            }
        });
        add(HuntLabel);
        HuntLabel.setBounds(220, 33, 100, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void HuntLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HuntLabelMouseClicked
    	huntButton();
    }//GEN-LAST:event_HuntLabelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BuildingTypeLabel;
    private javax.swing.JLabel BuildingValueLabel;
    private javax.swing.JLabel FoodLabel;
    private javax.swing.JLabel GoodsLabel;
    private javax.swing.JLabel HappyLabel;
    private javax.swing.JLabel HuntLabel;
    private javax.swing.JLabel ImageLabel;
    private javax.swing.JLabel ItemLabel;
    private javax.swing.JLabel UpgradeCostLabel;
    // End of variables declaration//GEN-END:variables
}
