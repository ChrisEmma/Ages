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
public class CivCreationImpl extends javax.swing.JPanel {
	
	private GameDataClass game;
	private CityViewImpl upperView;
	
    public CivCreationImpl(GameDataClass game,CityViewImpl cityView) {
    	this.game = game;
    	upperView = cityView;
        initComponents();
        scaleView();
        generateDisplay();
    }
    public void generateDisplay(){
    	TitleLabel.setText("Stone Age");
    	//TitleLabel.setText("Congratulations, your people have formed a Civilization!");
    	CivNameBox.setText(game.getCivName());
    	
    	GoodsIconLabel.setText("");
    	GoodsIconLabel.setSize(upperView.Scale(15), upperView.Scale(15));
    	GoodsIconLabel.setIcon(new ImageIcon(game.tables.getGoodsIcon().getScaledInstance(upperView.Scale(15),upperView.Scale(15), 0)));
        
        GoodsCountLabel.setText(game.getGoodsCount() + "");
        
        //smart auto select
        GoodsLevel0.setSelected(true);
    }
    public void setCiv(){
    	game.setCivName(CivNameBox.getText());
    	//set goods level
    	
    	if (GoodsLevel0.isSelected())
    		game.setGoodsConsumptionLevel(1);
    	if (GoodsLevel1.isSelected())
    		game.setGoodsConsumptionLevel(2);
    	if (GoodsLevel2.isSelected())
    		game.setGoodsConsumptionLevel(4);
    	game.setFounded(true);
    	this.hide();
    	
    	game.setPause(false);
    }
    
    public void scaleView(){
    	
        for (int i = 0; i < 5; i ++){
        	JLabel setLabel = new javax.swing.JLabel();;
        	if (i == 0)
        		setLabel = TitleLabel;
        	if (i == 1)
        		setLabel = NameCivLabel;
        	if (i == 2)
        		setLabel = GoodsIconLabel;
        	if (i == 3)
        		setLabel = OkLabel;
        	if (i == 4)
        		setLabel = GoodsCountLabel;
        	setLabel.setBounds(upperView.Scale(setLabel.getX()), upperView.Scale(setLabel.getY()), upperView.Scale(setLabel.getWidth()), upperView.Scale(setLabel.getHeight()));
        }
        CivNameBox.setBounds(upperView.Scale(CivNameBox.getX()), upperView.Scale(CivNameBox.getY()), upperView.Scale(CivNameBox.getWidth()), upperView.Scale(CivNameBox.getHeight()));
        
        GoodsLevel0.setBounds(upperView.Scale(GoodsLevel0.getX()), upperView.Scale(GoodsLevel0.getY()), upperView.Scale(GoodsLevel0.getWidth()), upperView.Scale(GoodsLevel0.getHeight()));
        GoodsLevel1.setBounds(upperView.Scale(GoodsLevel1.getX()), upperView.Scale(GoodsLevel1.getY()), upperView.Scale(GoodsLevel1.getWidth()), upperView.Scale(GoodsLevel1.getHeight()));
        GoodsLevel2.setBounds(upperView.Scale(GoodsLevel2.getX()), upperView.Scale(GoodsLevel2.getY()), upperView.Scale(GoodsLevel2.getWidth()), upperView.Scale(GoodsLevel2.getHeight()));
        GoodsLevel0.setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(14))); // NOI18N
        GoodsLevel1.setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(14))); // NOI18N
        GoodsLevel2.setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(14))); // NOI18N
        
        TitleLabel.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(16))); // NOI18N
        CivNameBox.setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(14))); // NOI18N
        NameCivLabel.setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(14))); // NOI18N
        GoodsCountLabel.setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(14))); // NOI18N
        OkLabel.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(18))); // NOI18N
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        TitleLabel = new javax.swing.JLabel();
        NameCivLabel = new javax.swing.JLabel();
        CivNameBox = new javax.swing.JTextField();
        OkLabel = new javax.swing.JLabel();
        GoodsIconLabel = new javax.swing.JLabel();
        GoodsCountLabel = new javax.swing.JLabel();
        GoodsLevel0 = new javax.swing.JRadioButton();
        GoodsLevel1 = new javax.swing.JRadioButton();
        GoodsLevel2 = new javax.swing.JRadioButton();

        setLayout(null);

        TitleLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleLabel.setText("Title");
        add(TitleLabel);
        TitleLabel.setBounds(90, 10, 410, 20);

        NameCivLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NameCivLabel.setText("Civilization Name");
        add(NameCivLabel);
        NameCivLabel.setBounds(10, 50, 110, 17);

        CivNameBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        CivNameBox.setText("jTextField1");
        add(CivNameBox);
        CivNameBox.setBounds(120, 50, 270, 28);

        OkLabel.setBackground(new java.awt.Color(0, 204, 0));
        OkLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        OkLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OkLabel.setText("OK");
        OkLabel.setOpaque(true);
        OkLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OkLabelMouseClicked(evt);
            }
        });
        add(OkLabel);
        OkLabel.setBounds(450, 390, 50, 30);

        GoodsIconLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        GoodsIconLabel.setText("L");
        add(GoodsIconLabel);
        GoodsIconLabel.setBounds(50, 130, 30, 30);

        GoodsCountLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        GoodsCountLabel.setText("000");
        add(GoodsCountLabel);
        GoodsCountLabel.setBounds(80, 130, 40, 17);

        buttonGroup1.add(GoodsLevel0);
        GoodsLevel0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        GoodsLevel0.setText("1");
        add(GoodsLevel0);
        GoodsLevel0.setBounds(30, 190, 70, 20);

        buttonGroup1.add(GoodsLevel1);
        GoodsLevel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        GoodsLevel1.setText("2");
        add(GoodsLevel1);
        GoodsLevel1.setBounds(30, 220, 70, 20);

        buttonGroup1.add(GoodsLevel2);
        GoodsLevel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        GoodsLevel2.setText("4");
        add(GoodsLevel2);
        GoodsLevel2.setBounds(30, 250, 70, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void OkLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OkLabelMouseClicked
    	setCiv();
    }//GEN-LAST:event_OkLabelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CivNameBox;
    private javax.swing.JLabel GoodsCountLabel;
    private javax.swing.JLabel GoodsIconLabel;
    private javax.swing.JRadioButton GoodsLevel0;
    private javax.swing.JRadioButton GoodsLevel1;
    private javax.swing.JRadioButton GoodsLevel2;
    private javax.swing.JLabel NameCivLabel;
    private javax.swing.JLabel OkLabel;
    private javax.swing.JLabel TitleLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    // End of variables declaration//GEN-END:variables
}
