package agesgame;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ItemImpl extends javax.swing.JPanel {
    private GameDataClass game;
    private CityViewImpl upperView;
            
    public ItemImpl(GameDataClass game,CityViewImpl cityView) {
        this.game = game;
        upperView = cityView;
        initComponents();
        scaleView();
        generateDisplay();
    }
    public void generateDisplay(){
    	GoodsCountLabel.setText(game.getGoodsCount() + "");
    	GoodIconLabel.setIcon(new ImageIcon (game.tables.getGoodsIcon().getScaledInstance(upperView.Scale(20), upperView.Scale(20), 0 )));
        
    	for (int i = 0; i < 2; i++){
    		getItemNameLabel(0,i).setText(game.tables.getItemName(i));
    		getItemCountLabel(0,i).setText(game.getItemCount(i) + "");
    		
    		getItemIcon(0,i).setText("");
    		getItemIcon(0,i).setIcon(new ImageIcon(game.tables.getItemIcon(i).getScaledInstance(upperView.Scale(35), upperView.Scale(35), 0) ) );
    		
    		if (game.getItemCount(i) > 0){
    			getGoodsAddLabel(i).setText("+" + (game.tables.getItemValue(i) * game.getItemCount(i)));
    		}else{
    			getGoodsAddLabel(i).setText("");
    		}
    	}
    	
    	for (int i = 0; i < 1; i++){
    		getItemNameLabel(1,i).setText(game.tables.getItemName(i+2));
    		getItemCountLabel(1,i).setText(game.getItemCount(i+2) + "");
    	}
    }
    public void useGoodsButton(){
    	game.useAllGoods();
    	generateDisplay();
    }
    public void close(){
        this.hide();
    }

    public JLabel getItemCountLabel(int cat, int i) {
    	if (cat == 0){
	        if (i == 0)
	        	return CountLabel0_0;
	        if (i == 1)
	        	return CountLabel0_1;
    	}
    	if (cat == 1){
	        if (i == 0)
	        	return CountLabel1_0;
	        if (i == 1)
	        	return CountLabel1_1;
    	}
        return null;
    }
    public JLabel getItemIcon(int cat, int i){
    	if (cat == 0){
	        if (i == 0)
	        	return ItemIcon0_0;
	        if (i == 1)
	        	return ItemIcon0_1;
    	}
    	if (cat == 1){
	        if (i == 0)
	        	return ItemIcon1_0;
	        if (i == 1)
	        	return ItemIcon1_1;
    	}
        return null;
    }
    public JLabel getItemNameLabel(int cat, int i){
    	if (cat == 0){
	        if (i == 0)
	        	return ItemName0_0;
	        if (i == 1)
	        	return ItemName0_1;
    	}
    	if (cat == 1){
	        if (i == 0)
	        	return ItemName1_0;
	        if (i == 1)
	        	return ItemName1_1;
    	}
        return null;
    }
    
    public JLabel getCategoryLabel(int c){
    	if (c == 0)
    		return CatLabel0;
    	if (c == 1)
    		return CatLabel1;
    	return null;
    }
    	
    public JLabel getGoodsAddLabel(int c){
    	if (c == 0)
    		return GoodsAddLabel0;
    	if (c == 1)
    		return GoodsAddLabel1;
    	return null;
    }
    	

    public void scaleView(){
    	CloseLabel.setBounds(upperView.Scale(CloseLabel.getX()), upperView.Scale(CloseLabel.getY()), upperView.Scale(CloseLabel.getWidth()), upperView.Scale(CloseLabel.getHeight()));
    	CloseLabel.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(14))); // NOI18N
    	GoodsCountLabel.setBounds(upperView.Scale(GoodsCountLabel.getX()), upperView.Scale(GoodsCountLabel.getY()), upperView.Scale(GoodsCountLabel.getWidth()), upperView.Scale(GoodsCountLabel.getHeight()));
    	GoodsCountLabel.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(14))); // NOI18N
    	GoodIconLabel.setBounds(upperView.Scale(GoodIconLabel.getX()), upperView.Scale(GoodIconLabel.getY()), upperView.Scale(20), upperView.Scale(20));
    	
    	jLabel1.setBounds(upperView.Scale(jLabel1.getX()), upperView.Scale(jLabel1.getY()), upperView.Scale(jLabel1.getWidth()), upperView.Scale(jLabel1.getHeight()));
    	jLabel1.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(14))); // NOI18N

    	for (int c = 0; c < 2; c++){
    		getCategoryLabel(c).setBounds(upperView.Scale(getCategoryLabel(c).getX()), upperView.Scale(getCategoryLabel(c).getY()), upperView.Scale(getCategoryLabel(c).getWidth()), upperView.Scale(getCategoryLabel(c).getHeight()));
    		getCategoryLabel(c).setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(18))); // NOI18N	
    	
	    	for (int i = 0; i < 2; i ++){
	    		getItemIcon(c,i).setBounds(upperView.Scale(getItemIcon(c,i).getX()), upperView.Scale(getItemIcon(c,i).getY()), upperView.Scale(35), upperView.Scale(35));
	    		getItemCountLabel(c,i).setBounds(upperView.Scale(getItemCountLabel(c,i).getX()), upperView.Scale(getItemCountLabel(c,i).getY()), upperView.Scale(getItemCountLabel(c,i).getWidth()), upperView.Scale(getItemCountLabel(c,i).getHeight()));
	    		getItemNameLabel(c,i).setBounds(upperView.Scale(getItemNameLabel(c,i).getX()), upperView.Scale(getItemNameLabel(c,i).getY()), upperView.Scale(getItemNameLabel(c,i).getWidth()), upperView.Scale(getItemNameLabel(c,i).getHeight()));
	    		
	    		getItemCountLabel(c,i).setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(14))); // NOI18N	
	    		//TEMP -- to be split to ItemNameLabel
	    		getItemNameLabel(c,i).setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(12))); // NOI18N	
	        }
    	}
    	
    	for (int i = 0; i < 2; i ++){
    		getGoodsAddLabel(i).setBounds(upperView.Scale(getGoodsAddLabel(i).getX()), upperView.Scale(getGoodsAddLabel(i).getY()), upperView.Scale(getGoodsAddLabel(i).getWidth()), upperView.Scale(getGoodsAddLabel(i).getHeight()));
    		getGoodsAddLabel(i).setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(14))); // NOI18N	
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

        CloseLabel = new javax.swing.JLabel();
        CountLabel0_0 = new javax.swing.JLabel();
        ItemIcon0_0 = new javax.swing.JLabel();
        CatLabel0 = new javax.swing.JLabel();
        CountLabel0_1 = new javax.swing.JLabel();
        ItemIcon0_1 = new javax.swing.JLabel();
        CountLabel1_0 = new javax.swing.JLabel();
        ItemIcon1_0 = new javax.swing.JLabel();
        CatLabel1 = new javax.swing.JLabel();
        CountLabel1_1 = new javax.swing.JLabel();
        ItemIcon1_1 = new javax.swing.JLabel();
        GoodsCountLabel = new javax.swing.JLabel();
        ItemName0_0 = new javax.swing.JLabel();
        ItemName0_1 = new javax.swing.JLabel();
        ItemName1_0 = new javax.swing.JLabel();
        ItemName1_1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        GoodIconLabel = new javax.swing.JLabel();
        GoodsAddLabel0 = new javax.swing.JLabel();
        GoodsAddLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(null);

        CloseLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CloseLabel.setText("X");
        CloseLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseLabelMouseClicked(evt);
            }
        });
        add(CloseLabel);
        CloseLabel.setBounds(560, 0, 12, 14);

        CountLabel0_0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CountLabel0_0.setText("000");
        add(CountLabel0_0);
        CountLabel0_0.setBounds(100, 50, 30, 17);

        ItemIcon0_0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ItemIcon0_0.setText("R2");
        add(ItemIcon0_0);
        ItemIcon0_0.setBounds(20, 47, 40, 30);

        CatLabel0.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        CatLabel0.setText("Goods");
        add(CatLabel0);
        CatLabel0.setBounds(50, 20, 80, 22);

        CountLabel0_1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CountLabel0_1.setText("000");
        add(CountLabel0_1);
        CountLabel0_1.setBounds(100, 110, 30, 17);

        ItemIcon0_1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ItemIcon0_1.setText("R2");
        add(ItemIcon0_1);
        ItemIcon0_1.setBounds(20, 110, 40, 17);

        CountLabel1_0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CountLabel1_0.setText("000");
        add(CountLabel1_0);
        CountLabel1_0.setBounds(260, 40, 30, 17);

        ItemIcon1_0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ItemIcon1_0.setText("R2");
        add(ItemIcon1_0);
        ItemIcon1_0.setBounds(220, 40, 40, 30);

        CatLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CatLabel1.setText("Weapons");
        add(CatLabel1);
        CatLabel1.setBounds(250, 20, 80, 17);

        CountLabel1_1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CountLabel1_1.setText("000");
        add(CountLabel1_1);
        CountLabel1_1.setBounds(260, 90, 30, 17);

        ItemIcon1_1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ItemIcon1_1.setText("R2");
        add(ItemIcon1_1);
        ItemIcon1_1.setBounds(220, 90, 40, 30);

        GoodsCountLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        GoodsCountLabel.setText("00");
        add(GoodsCountLabel);
        GoodsCountLabel.setBounds(150, 20, 40, 17);

        ItemName0_0.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ItemName0_0.setText("R2");
        add(ItemName0_0);
        ItemName0_0.setBounds(30, 80, 80, 15);

        ItemName0_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ItemName0_1.setText("R2");
        add(ItemName0_1);
        ItemName0_1.setBounds(30, 150, 80, 15);

        ItemName1_0.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ItemName1_0.setText("R2");
        add(ItemName1_0);
        ItemName1_0.setBounds(240, 70, 80, 15);

        ItemName1_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ItemName1_1.setText("R2");
        add(ItemName1_1);
        ItemName1_1.setBounds(240, 120, 80, 15);

        jLabel1.setBackground(new java.awt.Color(153, 255, 153));
        jLabel1.setText("USE");
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1);
        jLabel1.setBounds(140, 170, 50, 40);

        GoodIconLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        GoodIconLabel.setText("ICO");
        add(GoodIconLabel);
        GoodIconLabel.setBounds(120, 20, 30, 20);

        GoodsAddLabel0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        GoodsAddLabel0.setText("000");
        add(GoodsAddLabel0);
        GoodsAddLabel0.setBounds(150, 50, 30, 17);

        GoodsAddLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        GoodsAddLabel1.setText("000");
        add(GoodsAddLabel1);
        GoodsAddLabel1.setBounds(150, 110, 30, 17);
    }// </editor-fold>//GEN-END:initComponents

    private void CloseLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseLabelMouseClicked
        close();
    }//GEN-LAST:event_CloseLabelMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
    	useGoodsButton();
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CatLabel0;
    private javax.swing.JLabel CatLabel1;
    private javax.swing.JLabel CloseLabel;
    private javax.swing.JLabel CountLabel0_0;
    private javax.swing.JLabel CountLabel0_1;
    private javax.swing.JLabel CountLabel1_0;
    private javax.swing.JLabel CountLabel1_1;
    private javax.swing.JLabel GoodIconLabel;
    private javax.swing.JLabel GoodsAddLabel0;
    private javax.swing.JLabel GoodsAddLabel1;
    private javax.swing.JLabel GoodsCountLabel;
    private javax.swing.JLabel ItemIcon0_0;
    private javax.swing.JLabel ItemIcon0_1;
    private javax.swing.JLabel ItemIcon1_0;
    private javax.swing.JLabel ItemIcon1_1;
    private javax.swing.JLabel ItemName0_0;
    private javax.swing.JLabel ItemName0_1;
    private javax.swing.JLabel ItemName1_0;
    private javax.swing.JLabel ItemName1_1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
