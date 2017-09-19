/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agesgame;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author cpemm_000
 */
public class PopulationImpl extends javax.swing.JPanel {
    private GameDataClass game;
    private CityViewImpl upperView;
    
    public PopulationImpl(GameDataClass game, CityViewImpl cityView) {
        this.game = game;
        upperView = cityView;
        initComponents();
        scaleView();
        generateDisplay();
        
    }
    public void generateDisplay(){
        this.setOpaque(true);
    	PopIcon.setIcon(new ImageIcon (game.tables.getPopIcon().getScaledInstance(upperView.Scale(15), upperView.Scale(25), 0)));
        PopIcon.setSize(upperView.Scale(15), upperView.Scale(25));
    	
    	PopCountLabel.setText(game.getPopCount() + "");
    	PopHousingLabel.setText(" / " + game.getHousing() + "  Houses");
    	
    	
    	FoodIcon.setIcon(new ImageIcon(game.tables.getFoodIcon(0).getScaledInstance(upperView.Scale(120), upperView.Scale(120), 0)));
    	for (int i = 0; i < 3; i++){
    		getResourceLabel(i).setText("");
    		getResourceLabel(i).setVisible(false);
    		getResourceLabel(i).setIcon(new ImageIcon (game.tables.getFoodIcon(i+1).getScaledInstance(upperView.Scale(60), upperView.Scale(60), 0)));
        }

        HealthLabel0.setText("+" + 1 + " Base");
        
        
        HealthTotalLabel.setText("+" + game.getHealth());
        //set color by: game.canGrow()
        PopRateLabel.setText("+" + game.getGrowthRate() + " Growth");
        
    }
    
    public void refreshDisplay(){
    	PopCountLabel.setText(game.getPopCount() + "");
    	PopHousingLabel.setText(" / " + game.getHousing() + "  Houses");
    	if (game.getPopCount() >= game.getHousing()){
    		PopHousingLabel.setForeground(new Color(230,28,36));
    	}else{
    		PopHousingLabel.setForeground(new Color(0,0,0));
    	}
    	
    	for (int i = 0; i < 4; i++){
    		if (game.getResource(i) > 0){
    			getResourceLabel(i).setVisible(true);
    		}else{
    			getResourceLabel(i).setVisible(false);
    		}
            
        }
    	if(game.getFoodHealth() > 0){
            HealthLabel1.setText("+" + game.getFoodHealth() + " Food");
            HealthLabel1.setForeground(new Color(51,153,0));
        }else{
            HealthLabel1.setForeground(Color.red);
            HealthLabel1.setText("-" + game.getFoodHealth() + " Food");
        }
    	
    	if (game.getBuildingHealth() > 0){
    		HealthLabel2.setText("+" + game.getBuildingHealth() + " Buildings");
    		HealthLabel2.setVisible(true);
    	}else{
    		HealthLabel2.setVisible(false);
    	}
        //set color by: game.canGrow()
        PopRateLabel.setText("+" + game.getGrowthRate() + "/year");
        PopGrowthCount.setText( ( Math.round(100*game.getPopGrowth())) + "%");
        HealthTotalLabel.setText("+" + game.getHealth() + "  Total");
        
        
    }
    public JLabel getResourceLabel(int i){
        if (i == 0)
            return ResLabel0;
        if (i == 1)
            return ResLabel1;
        if (i == 2)
            return ResLabel2;
        if (i == 3)
            return ResLabel3;
        if (i == 4)
            return ResLabel4;
        return null;
    }
    
    public void close(){
        this.hide();
        //this.setVisible(false);
    }
    public void scaleView(){
    	for (int i = 0; i < 16; i ++){
        	JLabel setLabel = new javax.swing.JLabel();;
        	if (i == 0)
        		setLabel = jLabel1;
        	if (i == 1)
        		setLabel = PopIcon;
        	if (i == 2)
        		setLabel = PopCountLabel;
        	if (i == 3)
        		setLabel = PopHousingLabel;
        	if (i == 4)
        		setLabel = HealthLabel0;
        	if (i == 5)
        		setLabel = PopRateLabel;
        	if (i == 6)
        		setLabel = PopGrowthCount;
        	if (i == 7)
        		setLabel = HealthLabel1;
        	if (i == 8)
        		setLabel = FoodIcon;
        	if (i == 9)
        		setLabel = HealthTotalLabel;
                if (i == 10)
        		setLabel = ResLabel0;
                if (i == 11)
        		setLabel = ResLabel1;
                if (i == 12)
        		setLabel = ResLabel2;
                if (i == 13)
        		setLabel = ResLabel3;
                if (i == 14)
        		setLabel = ResLabel4;
                if (i == 15)
            		setLabel = HealthLabel2;


        	setLabel.setBounds(upperView.Scale(setLabel.getX()), upperView.Scale(setLabel.getY()), upperView.Scale(setLabel.getWidth()), upperView.Scale(setLabel.getHeight()));

        	if (i == 0){
        		setLabel.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(17))); // NOI18N
        	}else if (i == 6){
        		setLabel.setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(14))); // NOI18N
        	}else{
        		setLabel.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(14))); // NOI18N
        	}
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        PopIcon = new javax.swing.JLabel();
        PopCountLabel = new javax.swing.JLabel();
        PopHousingLabel = new javax.swing.JLabel();
        HealthLabel0 = new javax.swing.JLabel();
        PopRateLabel = new javax.swing.JLabel();
        PopGrowthCount = new javax.swing.JLabel();
        HealthLabel1 = new javax.swing.JLabel();
        HealthTotalLabel = new javax.swing.JLabel();
        ResLabel0 = new javax.swing.JLabel();
        ResLabel1 = new javax.swing.JLabel();
        ResLabel2 = new javax.swing.JLabel();
        ResLabel3 = new javax.swing.JLabel();
        ResLabel4 = new javax.swing.JLabel();
        FoodIcon = new javax.swing.JLabel();
        HealthLabel2 = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("TITLE");
        add(jLabel1);
        jLabel1.setBounds(170, 10, 80, 20);

        PopIcon.setText("P");
        add(PopIcon);
        PopIcon.setBounds(20, 60, 20, 33);

        PopCountLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PopCountLabel.setText("jLabel2");
        add(PopCountLabel);
        PopCountLabel.setBounds(50, 60, 50, 15);

        PopHousingLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PopHousingLabel.setText("jLabel2");
        add(PopHousingLabel);
        PopHousingLabel.setBounds(110, 60, 140, 15);

        HealthLabel0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        HealthLabel0.setText("+ 00 BASE");
        add(HealthLabel0);
        HealthLabel0.setBounds(190, 150, 100, 20);

        PopRateLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PopRateLabel.setText("jLabel2");
        add(PopRateLabel);
        PopRateLabel.setBounds(80, 80, 60, 15);

        PopGrowthCount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PopGrowthCount.setText("jLabel2");
        add(PopGrowthCount);
        PopGrowthCount.setBounds(150, 80, 110, 15);

        HealthLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        HealthLabel1.setForeground(new java.awt.Color(51, 153, 0));
        HealthLabel1.setText("+ 00 FOOD");
        add(HealthLabel1);
        HealthLabel1.setBounds(190, 180, 100, 20);

        HealthTotalLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        HealthTotalLabel.setText("000");
        add(HealthTotalLabel);
        HealthTotalLabel.setBounds(190, 270, 100, 20);

        ResLabel0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResLabel0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ResLabel0.setText("000");
        add(ResLabel0);
        ResLabel0.setBounds(90, 130, 60, 60);

        ResLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ResLabel1.setText("000");
        add(ResLabel1);
        ResLabel1.setBounds(30, 130, 60, 60);

        ResLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ResLabel2.setText("000");
        add(ResLabel2);
        ResLabel2.setBounds(30, 190, 60, 60);

        ResLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ResLabel3.setText("000");
        add(ResLabel3);
        ResLabel3.setBounds(90, 190, 60, 60);

        ResLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ResLabel4.setText("000");
        add(ResLabel4);
        ResLabel4.setBounds(30, 250, 30, 20);

        FoodIcon.setBackground(new java.awt.Color(255, 255, 255));
        FoodIcon.setOpaque(true);
        add(FoodIcon);
        FoodIcon.setBounds(30, 130, 120, 120);

        HealthLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        HealthLabel2.setForeground(new java.awt.Color(51, 153, 0));
        HealthLabel2.setText("+ 00 FOOD");
        add(HealthLabel2);
        HealthLabel2.setBounds(190, 210, 100, 20);
    }// </editor-fold>//GEN-END:initComponents
    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    	close();
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FoodIcon;
    private javax.swing.JLabel HealthLabel0;
    private javax.swing.JLabel HealthLabel1;
    private javax.swing.JLabel HealthLabel2;
    private javax.swing.JLabel HealthTotalLabel;
    private javax.swing.JLabel PopCountLabel;
    private javax.swing.JLabel PopGrowthCount;
    private javax.swing.JLabel PopHousingLabel;
    private javax.swing.JLabel PopIcon;
    private javax.swing.JLabel PopRateLabel;
    private javax.swing.JLabel ResLabel0;
    private javax.swing.JLabel ResLabel1;
    private javax.swing.JLabel ResLabel2;
    private javax.swing.JLabel ResLabel3;
    private javax.swing.JLabel ResLabel4;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
