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
    public PersonInfoImpl(GameDataClass game, float SCALE) {
    	this.SCALE = SCALE;
        this.game = game;
        
        initComponents();
        scaleView();
    }
    public void generateDisplay(){
       
        ImageLabel.setIcon(new ImageIcon(game.getPopulation(id).getImage().getScaledInstance(Scale(35), Scale(59), 0)));
        
        
        refreshDisplay();
    }
    public void refreshDisplay(){
    	BuildingTypeLabel.setText(game.getPopulation(id).task.getString());
    	BuildingValueLabel.setText(Math.round(game.getPopulation(id).workCount) + "");
        
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
    	
    	
    	ItemLabel.setText("Wep: " + game.getPopulation(id).getWeapon());
    }
    public void setID(int id){
        this.id = id;
        generateDisplay();
    }
    public void scaleView(){
    	for (int i = 0; i < 8; i ++){
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
        	
        	
        	setLabel.setBounds(Scale(setLabel.getX()), Scale(setLabel.getY()), Scale(setLabel.getWidth()), Scale(setLabel.getHeight()));
        }
    	

    	BuildingTypeLabel.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
    	
    	BuildingValueLabel.setFont(new java.awt.Font("Tahoma", 0, Scale(14))); // NOI18N
    	FoodLabel.setFont(new java.awt.Font("Tahoma", 0, Scale(14))); // NOI18N
    	GoodsLabel.setFont(new java.awt.Font("Tahoma", 0, Scale(14)));
    	ItemLabel.setFont(new java.awt.Font("Tahoma", 0, Scale(14)));
    	UpgradeCostLabel.setFont(new java.awt.Font("Tahoma", 0, Scale(12))); // NOI18N
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

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(null);

        ImageLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        add(ImageLabel);
        ImageLabel.setBounds(10, 10, 30, 60);

        UpgradeCostLabel.setText("COST");
        add(UpgradeCostLabel);
        UpgradeCostLabel.setBounds(410, 50, 80, 14);

        BuildingTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BuildingTypeLabel.setText("TYPE");
        add(BuildingTypeLabel);
        BuildingTypeLabel.setBounds(160, 10, 100, 20);

        BuildingValueLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BuildingValueLabel.setText("(100%)");
        add(BuildingValueLabel);
        BuildingValueLabel.setBounds(170, 30, 70, 20);

        HappyLabel.setText("HAP");
        add(HappyLabel);
        HappyLabel.setBounds(60, 10, 70, 14);

        GoodsLabel.setText("Goods");
        add(GoodsLabel);
        GoodsLabel.setBounds(370, 30, 80, 14);

        FoodLabel.setText("Food");
        add(FoodLabel);
        FoodLabel.setBounds(370, 10, 50, 14);

        ItemLabel.setText("ITEM");
        add(ItemLabel);
        ItemLabel.setBounds(260, 10, 50, 14);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BuildingTypeLabel;
    private javax.swing.JLabel BuildingValueLabel;
    private javax.swing.JLabel FoodLabel;
    private javax.swing.JLabel GoodsLabel;
    private javax.swing.JLabel HappyLabel;
    private javax.swing.JLabel ImageLabel;
    private javax.swing.JLabel ItemLabel;
    private javax.swing.JLabel UpgradeCostLabel;
    // End of variables declaration//GEN-END:variables
}
