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
public class ProductionImpl extends javax.swing.JPanel {
	private GameDataClass game;
    private CityViewImpl upperView;
    
    private int buildingIndex;
    
    public ProductionImpl(GameDataClass game,CityViewImpl cityView) {
        this.game = game;
        upperView = cityView;
        initComponents();
        scaleView();
    }
    public void generateDisplay(int building){
    	this.buildingIndex = building;
    	int id = game.getBuilding(building).getID();
    	for (int i = 0; i < game.tables.getBuildingProduction(id).split(",").length; i ++){
    		getItemNameLabel(i).setVisible(true);
    		getItemCountLabel(i).setVisible(true);
    		getItemIcon(i).setVisible(true);
    		int item = Integer.parseInt(game.tables.getBuildingProduction(id).split(",")[i]);
    		getItemNameLabel(i).setText(game.tables.getItemName(item));
    		
    		getItemCountLabel(i).setText("x" + game.getItemCount(item));
    		getItemIcon(i).setIcon(new ImageIcon(game.tables.getItemIcon(item).getScaledInstance(upperView.Scale(40), upperView.Scale(40), 0)));
    		
    		getItemTaskLabel(i).setText("" + game.getCraftingCount(item));
    	}
    	for (int i = game.tables.getBuildingProduction(id).split(",").length; i < 3; i ++){
    		getItemNameLabel(i).setVisible(false);
    		getItemCountLabel(i).setVisible(false);
    		getItemIcon(i).setVisible(false);
    	}
    }

    public void itemClick(int i){
    	game.addCraftTask(buildingIndex , i);
    	generateDisplay(buildingIndex);
    }
    public JLabel getItemNameLabel(int i){
    	if (i == 0)
    		return ItemNameLabel;
    	if (i == 1)
    		return ItemNameLabel1;
    	if (i == 2)
    		return ItemNameLabel2;
    	return null;
    }
    public JLabel getItemIcon(int i){
    	if (i == 0)
    		return ItemIconLabel0;
    	if (i == 1)
    		return ItemIconLabel1;
    	if (i == 2)
    		return ItemIconLabel2;
    	return null;
    }
    public JLabel getItemCountLabel(int i){
    	if (i == 0)
    		return ItemCountLabel0;
    	if (i == 1)
    		return ItemCountLabel1;
    	if (i == 2)
    		return ItemCountLabel2;
    	return null;
    }
    public JLabel getItemTaskLabel(int i){
    	if (i == 0)
    		return ItemTaskLabel0;
    	if (i == 1)
    		return ItemTaskLabel1;
    	if (i == 2)
    		return ItemTaskLabel2;
    	return null;
    }
    
    
    public void scaleView(){
    	for (int i = 0; i < 3; i ++){
    		getItemNameLabel(i).setBounds(upperView.Scale(getItemNameLabel(i).getX()), upperView.Scale(getItemNameLabel(i).getY()), upperView.Scale(getItemNameLabel(i).getWidth()), upperView.Scale(getItemNameLabel(i).getHeight()));
    		getItemNameLabel(i).setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(14))); // NOI18N	
    		
    		getItemCountLabel(i).setBounds(upperView.Scale(getItemCountLabel(i).getX()), upperView.Scale(getItemCountLabel(i).getY()), upperView.Scale(getItemCountLabel(i).getWidth()), upperView.Scale(getItemCountLabel(i).getHeight()));
    		getItemCountLabel(i).setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(14))); // NOI18N	

    		getItemTaskLabel(i).setBounds(upperView.Scale(getItemTaskLabel(i).getX()), upperView.Scale(getItemTaskLabel(i).getY()), upperView.Scale(getItemTaskLabel(i).getWidth()), upperView.Scale(getItemTaskLabel(i).getHeight()));
    		getItemTaskLabel(i).setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(14))); // NOI18N	
    		
    		getItemIcon(i).setBounds(upperView.Scale(getItemIcon(i).getX()), upperView.Scale(getItemIcon(i).getY()),upperView.Scale(40), upperView.Scale(40));
    	}
    }
    public void close(){
        this.hide();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ItemNameLabel = new javax.swing.JLabel();
        ItemIconLabel0 = new javax.swing.JLabel();
        ItemCountLabel0 = new javax.swing.JLabel();
        ItemTaskLabel0 = new javax.swing.JLabel();
        ItemNameLabel1 = new javax.swing.JLabel();
        ItemIconLabel1 = new javax.swing.JLabel();
        ItemCountLabel1 = new javax.swing.JLabel();
        ItemTaskLabel1 = new javax.swing.JLabel();
        ItemNameLabel2 = new javax.swing.JLabel();
        ItemIconLabel2 = new javax.swing.JLabel();
        ItemCountLabel2 = new javax.swing.JLabel();
        ItemTaskLabel2 = new javax.swing.JLabel();

        setLayout(null);

        ItemNameLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ItemNameLabel.setText("NAME");
        add(ItemNameLabel);
        ItemNameLabel.setBounds(30, 50, 60, 20);

        ItemIconLabel0.setText("ICON");
        ItemIconLabel0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ItemIconLabel0MouseClicked(evt);
            }
        });
        add(ItemIconLabel0);
        ItemIconLabel0.setBounds(30, 10, 60, 30);

        ItemCountLabel0.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ItemCountLabel0.setText("000");
        add(ItemCountLabel0);
        ItemCountLabel0.setBounds(90, 10, 40, 20);

        ItemTaskLabel0.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ItemTaskLabel0.setText("000");
        add(ItemTaskLabel0);
        ItemTaskLabel0.setBounds(90, 30, 40, 20);

        ItemNameLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ItemNameLabel1.setText("NAME");
        add(ItemNameLabel1);
        ItemNameLabel1.setBounds(150, 50, 60, 20);

        ItemIconLabel1.setText("ICON");
        ItemIconLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ItemIconLabel1MouseClicked(evt);
            }
        });
        add(ItemIconLabel1);
        ItemIconLabel1.setBounds(150, 10, 60, 30);

        ItemCountLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ItemCountLabel1.setText("000");
        add(ItemCountLabel1);
        ItemCountLabel1.setBounds(210, 10, 40, 20);

        ItemTaskLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ItemTaskLabel1.setText("000");
        add(ItemTaskLabel1);
        ItemTaskLabel1.setBounds(210, 30, 40, 20);

        ItemNameLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ItemNameLabel2.setText("NAME");
        add(ItemNameLabel2);
        ItemNameLabel2.setBounds(260, 50, 60, 20);

        ItemIconLabel2.setText("ICON");
        ItemIconLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ItemIconLabel2MouseClicked(evt);
            }
        });
        add(ItemIconLabel2);
        ItemIconLabel2.setBounds(260, 10, 60, 30);

        ItemCountLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ItemCountLabel2.setText("000");
        add(ItemCountLabel2);
        ItemCountLabel2.setBounds(320, 10, 40, 20);

        ItemTaskLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ItemTaskLabel2.setText("000");
        add(ItemTaskLabel2);
        ItemTaskLabel2.setBounds(320, 30, 40, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void ItemIconLabel0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ItemIconLabel0MouseClicked
    	itemClick(0);
    }//GEN-LAST:event_ItemIconLabel0MouseClicked

    private void ItemIconLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ItemIconLabel1MouseClicked
    	itemClick(1);
    }//GEN-LAST:event_ItemIconLabel1MouseClicked

    private void ItemIconLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ItemIconLabel2MouseClicked
    	itemClick(2);
    }//GEN-LAST:event_ItemIconLabel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ItemCountLabel0;
    private javax.swing.JLabel ItemCountLabel1;
    private javax.swing.JLabel ItemCountLabel2;
    private javax.swing.JLabel ItemIconLabel0;
    private javax.swing.JLabel ItemIconLabel1;
    private javax.swing.JLabel ItemIconLabel2;
    private javax.swing.JLabel ItemNameLabel;
    private javax.swing.JLabel ItemNameLabel1;
    private javax.swing.JLabel ItemNameLabel2;
    private javax.swing.JLabel ItemTaskLabel0;
    private javax.swing.JLabel ItemTaskLabel1;
    private javax.swing.JLabel ItemTaskLabel2;
    // End of variables declaration//GEN-END:variables
}
