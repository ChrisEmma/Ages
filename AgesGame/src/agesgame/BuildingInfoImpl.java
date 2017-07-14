/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agesgame;

import javax.swing.JLabel;

/**
 *
 * @author cpemm_000
 */
public class BuildingInfoImpl extends javax.swing.JPanel {
    private GameDataClass game;
    private CityViewImpl upperView;
    public int index;
    public float SCALE;
    public BuildingInfoImpl(GameDataClass game, CityViewImpl cityView, float SCALE) {
    	this.SCALE = SCALE;
    	upperView = cityView;
        this.game = game;
        initComponents();
        scaleView();
    }
    public void generateDisplay(){
        SelectionNameLabel.setText(game.getBuilding(index).getName());
        if (!(game.getBuilding(index).getType() == "" || game.getBuilding(index).getType() == null) && game.getBuilding(index).getConstructed()){
            BuildingTypeLabel.setText(game.getBuilding(index).getType());
            BuildingValueLabel.setText(buildingValue());    
            
        }else{
            BuildingTypeLabel.setText("");
            BuildingValueLabel.setText("");   
        }
        
        AdditionalInfoLabel.setText("Pos:" + game.getBuilding(index).getX() + "," + game.getBuilding(index).getY());
        refreshDisplay();
    }
    public void refreshDisplay(){
        if (game.getBuilding(index).getConstructed()){
            SelectionBuiltLabel.setVisible(false);
            CancelLabel.setVisible(false);
        }else{
            SelectionBuiltLabel.setVisible(true);
            SelectionBuiltLabel.setText("(" + game.getBuilding(index).showBuildPercent() + ")");
            
            CancelLabel.setVisible(true);
        }
        if (game.getBuilding(index).canUpgrade()){
        	int up = game.tables.getBuidlingUpgrade(game.getBuilding(index).getID());
            UpgradeLabel.setVisible(true);
            UpgradeCostLabel.setVisible(true);
            
            
            UpgradeCostLabel.setText(game.tables.getBuildingCostText(up));
        }else{
            UpgradeLabel.setVisible(false);
            UpgradeCostLabel.setVisible(false);
        }
        
        if (game.getBuilding(index).getProduction() != "" && game.getBuilding(index).getConstructed()){
        	jLabel1.setVisible(true);
        }else{
        	jLabel1.setVisible(false);
        }
    }
    public String buildingValue(){
    	String value = "";
    	if (game.getBuilding(index).getType() == "HOUSE"){
    		value = game.getBuilding(index).getValue() + " pop";
    	}else if (game.getBuilding(index).getType() != "CRAFT"){
    		value = game.getWorkTime(  game.getBuilding(index).getType(),index) + " sec";
    	}
    	
    	return value;
    }
    public void setID(int id){
        this.index = id;
        generateDisplay();
    }
    public void upgrade(){
        game.upgradeBuilding(index);
        refreshDisplay();
    }
    public void openProdMenu(){
    	System.out.println("Open Prod Menu  Building: " + index);
    	upperView.openProductionMenu(index);
    }
    public void cancelBuild(){
    	//game.deleteBuilding(index);
    	this.setVisible(false);
    }
    public void scaleView(){
    	for (int i = 0; i < 9; i ++){
        	JLabel setLabel = new javax.swing.JLabel();;
        	if (i == 0)
        		setLabel = SelectionNameLabel;
        	if (i == 1)
        		setLabel = SelectionBuiltLabel;
        	if (i == 2)
        		setLabel = AdditionalInfoLabel;
        	if (i == 3)
        		setLabel = UpgradeLabel;
        	if (i == 4)
        		setLabel = UpgradeCostLabel;
        	if (i == 5)
        		setLabel = BuildingTypeLabel;
        	if (i == 6)
        		setLabel = BuildingValueLabel;
        	if (i == 7)
        		setLabel = jLabel1;
        	if (i == 8)
        		setLabel = CancelLabel;
        	
        	
        	setLabel.setBounds(Scale(setLabel.getX()), Scale(setLabel.getY()), Scale(setLabel.getWidth()), Scale(setLabel.getHeight()));
        }
    	SelectionNameLabel.setFont(new java.awt.Font("Tahoma", 1, Scale(18))); // NOI18N
    	SelectionBuiltLabel.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
    	UpgradeLabel.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
    	BuildingTypeLabel.setFont(new java.awt.Font("Tahoma", 1, Scale(14))); // NOI18N
    	UpgradeCostLabel.setFont(new java.awt.Font("Tahoma", 0, Scale(12))); // NOI18N
    	BuildingValueLabel.setFont(new java.awt.Font("Tahoma", 0, Scale(14))); // NOI18N
    	CancelLabel.setFont(new java.awt.Font("Tahoma", 0, Scale(14))); // NOI18N
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

        SelectionNameLabel = new javax.swing.JLabel();
        SelectionBuiltLabel = new javax.swing.JLabel();
        AdditionalInfoLabel = new javax.swing.JLabel();
        UpgradeLabel = new javax.swing.JLabel();
        UpgradeCostLabel = new javax.swing.JLabel();
        BuildingTypeLabel = new javax.swing.JLabel();
        BuildingValueLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        CancelLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(null);

        SelectionNameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        SelectionNameLabel.setText("NAME");
        add(SelectionNameLabel);
        SelectionNameLabel.setBounds(10, 0, 180, 30);

        SelectionBuiltLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SelectionBuiltLabel.setText("(100%)");
        add(SelectionBuiltLabel);
        SelectionBuiltLabel.setBounds(20, 30, 70, 20);

        AdditionalInfoLabel.setText("0000000");
        add(AdditionalInfoLabel);
        AdditionalInfoLabel.setBounds(40, 50, 120, 33);

        UpgradeLabel.setBackground(new java.awt.Color(51, 204, 255));
        UpgradeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpgradeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UpgradeLabel.setText("UPGRADE");
        UpgradeLabel.setOpaque(true);
        UpgradeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpgradeLabelMouseClicked(evt);
            }
        });
        add(UpgradeLabel);
        UpgradeLabel.setBounds(420, 10, 80, 30);

        UpgradeCostLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        UpgradeCostLabel.setText("COST");
        add(UpgradeCostLabel);
        UpgradeCostLabel.setBounds(410, 50, 120, 20);

        BuildingTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BuildingTypeLabel.setText("TYPE");
        add(BuildingTypeLabel);
        BuildingTypeLabel.setBounds(200, 10, 100, 20);

        BuildingValueLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BuildingValueLabel.setText("(100%)");
        add(BuildingValueLabel);
        BuildingValueLabel.setBounds(210, 30, 70, 20);

        jLabel1.setBackground(new java.awt.Color(255, 51, 204));
        jLabel1.setText("PRODUCTION");
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1);
        jLabel1.setBounds(330, 10, 70, 30);

        CancelLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CancelLabel.setForeground(new java.awt.Color(204, 0, 0));
        CancelLabel.setText("CANCEL");
        CancelLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CancelLabelMouseClicked(evt);
            }
        });
        add(CancelLabel);
        CancelLabel.setBounds(160, 30, 100, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void UpgradeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpgradeLabelMouseClicked
        upgrade();
    }//GEN-LAST:event_UpgradeLabelMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
    	openProdMenu();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void CancelLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelLabelMouseClicked
    	cancelBuild();
    }//GEN-LAST:event_CancelLabelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AdditionalInfoLabel;
    private javax.swing.JLabel BuildingTypeLabel;
    private javax.swing.JLabel BuildingValueLabel;
    private javax.swing.JLabel CancelLabel;
    private javax.swing.JLabel SelectionBuiltLabel;
    private javax.swing.JLabel SelectionNameLabel;
    private javax.swing.JLabel UpgradeCostLabel;
    private javax.swing.JLabel UpgradeLabel;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}