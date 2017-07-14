/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agesgame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author cpemm_000
 */
public class TechScreen extends javax.swing.JPanel {
    private GameDataClass game;
    private CityViewImpl upperView;
    private int selection;
    
    public TechScreen(GameDataClass game, CityViewImpl cityView) {
        this.game = game;
        upperView = cityView;
        
        initComponents();
        scaleView();
        generateDisplay();
        selection = -1;
    }
    public void generateDisplay(){
        ResearchIcon.setText("");
        ResearchIcon.setIcon(new ImageIcon(game.tables.getResearchPointIcon().getScaledInstance(upperView.Scale(30), upperView.Scale(30), 0) ) );
        ResearchIcon.setSize(upperView.Scale(30), upperView.Scale(30));
    	TechPoints.setText(game.getResearch()+"");
    	StorageLabel.setText(game.getTechUseage() + "/" + game.getTechStorge());
    	
        SelectedLabel.setVisible(false);
        ResearchLabel.setVisible(false);
        DescLabel.setVisible(false);
        for (int i = 0; i < 13; i++){
            getTechNameLabel(i).setText(game.tables.getTechnologyName(i));
            getTechCostLabel(i).setText(game.tables.getTechnologyCost(i) +"");
            getTechStorageLabel(i).setText(game.tables.getTechnologyStorage(i) +"");
            
            if (game.hasResearched(i))
            	getTechPanel(i).setBackground(new java.awt.Color(34, 177, 76));
        }
    }
    public void selectTech(int i){
        SelectedLabel.setText(game.tables.getTechnologyName(i));
        SelectedLabel.setVisible(true);
        DescLabel.setText(game.tables.getTechDescript(i));
        DescLabel.setVisible(true);
        selection = i;
        if (game.canResearch(i)){
            ResearchLabel.setVisible(true);
        }else{
            ResearchLabel.setVisible(false);
        }
    }
    public void researchClicked(){
    	if (selection > -1){
    		if (game.canResearch(selection)){
    			game.setResearchTech(selection);
    		}
    		selection = -1;
    		generateDisplay();
    	}
    }
    public void close(){
        this.hide();
    }
    public JLabel getTechNameLabel(int i){
        if (i == 0)
            return TechNameLabel0;
        if (i == 1)
            return TechNameLabel1;
        if (i == 2)
            return TechNameLabel2;
        if (i == 3)
            return TechNameLabel3;
        if (i == 4)
            return TechNameLabel4;
        if (i == 5)
            return TechNameLabel5;
        if (i == 6)
            return TechNameLabel6;
        if (i == 7)
            return TechNameLabel7;
        if (i == 8)
            return TechNameLabel8;
        if (i == 9)
            return TechNameLabel9;
        if (i == 10)
            return TechNameLabel10;
        if (i == 11)
            return TechNameLabel11;
        if (i == 12)
            return TechNameLabel12;
        return null;
    }
    public JLabel getTechCostLabel(int i){
        if (i == 0)
            return TechCostLabel0;
        if (i == 1)
            return TechCostLabel1;
        if (i == 2)
            return TechCostLabel2;
        if (i == 3)
            return TechCostLabel3;
        if (i == 4)
            return TechCostLabel4;
        if (i == 5)
            return TechCostLabel5;
        if (i == 6)
            return TechCostLabel5;
        if (i == 7)
            return TechCostLabel7;
        if (i == 8)
            return TechCostLabel8;
        if (i == 9)
            return TechCostLabel9;
        if (i == 10)
            return TechCostLabel10;
        if (i == 11)
            return TechCostLabel11;
        if (i == 12)
            return TechCostLabel12;
        return null;
    }
    public JLabel getTechStorageLabel(int i){
        if (i == 0)
            return TechStorageLabel;
        if (i == 1)
            return TechStorageLabel1;
        if (i == 2)
            return TechStorageLabel2;
        if (i == 3)
            return TechStorageLabel3;
        if (i == 4)
            return TechStorageLabel4;
        if (i == 5)
            return TechStorageLabel5;
        if (i == 6)
            return TechStorageLabel6;
        if (i == 7)
            return TechStorageLabel7;
        if (i == 8)
            return TechStorageLabel8;
        if (i == 9)
            return TechStorageLabel9;
        if (i == 10)
            return TechStorageLabel10;
        if (i == 11)
            return TechStorageLabel11;
        if (i == 12)
            return TechStorageLabel12;
        return null;
    }
    public JPanel getTechPanel(int i){
    	if (i == 0)
    		return TechPanel0;
    	if (i == 1)
    		return TechPanel1;
    	if (i == 2)
    		return TechPanel2;
    	if (i == 3)
    		return TechPanel3;
    	if (i == 4)
    		return TechPanel4;
    	if (i == 5)
    		return TechPanel5;
    	if (i == 6)
    		return TechPanel6;
    	if (i == 7)
    		return TechPanel7;
    	if (i == 8)
    		return TechPanel8;
    	if (i == 9)
    		return TechPanel9;
    	if (i == 10)
    		return TechPanel10;
    	if (i == 11)
    		return TechPanel11;
    	if (i == 12)
    		return TechPanel12;
    	return null;
    }
    public void scaleView(){
    	for (int i = 0; i < 13; i ++){
    		getTechPanel(i).setBounds(upperView.Scale(getTechPanel(i).getX()), upperView.Scale(getTechPanel(i).getY()), upperView.Scale(getTechPanel(i).getWidth()), upperView.Scale(getTechPanel(i).getHeight()));
    		
    		getTechStorageLabel(i).setBounds(upperView.Scale(getTechStorageLabel(i).getX()), upperView.Scale(getTechStorageLabel(i).getY()), upperView.Scale(getTechStorageLabel(i).getWidth()), upperView.Scale(getTechStorageLabel(i).getHeight()));
    		getTechStorageLabel(i).setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(12)));
    		
    		getTechNameLabel(i).setBounds(upperView.Scale(getTechNameLabel(i).getX()), upperView.Scale(getTechNameLabel(i).getY()), upperView.Scale(getTechNameLabel(i).getWidth()), upperView.Scale(getTechNameLabel(i).getHeight()));
    		getTechNameLabel(i).setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(12)));
    		
    		getTechCostLabel(i).setBounds(upperView.Scale(getTechCostLabel(i).getX()), upperView.Scale(getTechCostLabel(i).getY()), upperView.Scale(getTechCostLabel(i).getWidth()), upperView.Scale(getTechCostLabel(i).getHeight()));
    		getTechCostLabel(i).setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(12)));
    	}
    	
    	for (int i = 0; i < 8; i ++){
        	JLabel setLabel = new javax.swing.JLabel();;
        	if (i == 0)
        		setLabel = jLabel1;
        	if (i == 1)
        		setLabel = StorageLabel;
        	if (i == 2)
        		setLabel = ResearchIcon;
        	if (i == 3)
        		setLabel = jLabel5;
        	if (i == 4)
        		setLabel = TechPoints;
        	if (i == 5)
        		setLabel = SelectedLabel;
        	if (i == 6)
        		setLabel = ResearchLabel;
        	if (i == 7)
        		setLabel = DescLabel;
        	

        	setLabel.setBounds(upperView.Scale(setLabel.getX()), upperView.Scale(setLabel.getY()), upperView.Scale(setLabel.getWidth()), upperView.Scale(setLabel.getHeight()));
        	setLabel.setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(12))); // NOI18N
        }
    	jLabel1.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(18))); // NOI18N
    	

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
        StorageLabel = new javax.swing.JLabel();
        ResearchIcon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TechPoints = new javax.swing.JLabel();
        TechPanel0 = new javax.swing.JPanel();
        TechNameLabel0 = new javax.swing.JLabel();
        TechCostLabel0 = new javax.swing.JLabel();
        TechStorageLabel = new javax.swing.JLabel();
        TechPanel1 = new javax.swing.JPanel();
        TechNameLabel1 = new javax.swing.JLabel();
        TechCostLabel1 = new javax.swing.JLabel();
        TechStorageLabel1 = new javax.swing.JLabel();
        TechPanel2 = new javax.swing.JPanel();
        TechNameLabel2 = new javax.swing.JLabel();
        TechCostLabel2 = new javax.swing.JLabel();
        TechStorageLabel2 = new javax.swing.JLabel();
        TechPanel3 = new javax.swing.JPanel();
        TechNameLabel3 = new javax.swing.JLabel();
        TechCostLabel3 = new javax.swing.JLabel();
        TechStorageLabel3 = new javax.swing.JLabel();
        TechPanel4 = new javax.swing.JPanel();
        TechNameLabel4 = new javax.swing.JLabel();
        TechCostLabel4 = new javax.swing.JLabel();
        TechStorageLabel4 = new javax.swing.JLabel();
        TechPanel5 = new javax.swing.JPanel();
        TechNameLabel5 = new javax.swing.JLabel();
        TechCostLabel5 = new javax.swing.JLabel();
        TechStorageLabel5 = new javax.swing.JLabel();
        TechPanel6 = new javax.swing.JPanel();
        TechNameLabel6 = new javax.swing.JLabel();
        TechCostLabel6 = new javax.swing.JLabel();
        TechStorageLabel6 = new javax.swing.JLabel();
        SelectedLabel = new javax.swing.JLabel();
        ResearchLabel = new javax.swing.JLabel();
        TechPanel7 = new javax.swing.JPanel();
        TechNameLabel7 = new javax.swing.JLabel();
        TechCostLabel7 = new javax.swing.JLabel();
        TechStorageLabel7 = new javax.swing.JLabel();
        TechPanel8 = new javax.swing.JPanel();
        TechNameLabel8 = new javax.swing.JLabel();
        TechCostLabel8 = new javax.swing.JLabel();
        TechStorageLabel8 = new javax.swing.JLabel();
        TechPanel9 = new javax.swing.JPanel();
        TechNameLabel9 = new javax.swing.JLabel();
        TechCostLabel9 = new javax.swing.JLabel();
        TechStorageLabel9 = new javax.swing.JLabel();
        TechPanel10 = new javax.swing.JPanel();
        TechNameLabel10 = new javax.swing.JLabel();
        TechCostLabel10 = new javax.swing.JLabel();
        TechStorageLabel10 = new javax.swing.JLabel();
        TechPanel11 = new javax.swing.JPanel();
        TechNameLabel11 = new javax.swing.JLabel();
        TechCostLabel11 = new javax.swing.JLabel();
        TechStorageLabel11 = new javax.swing.JLabel();
        TechPanel12 = new javax.swing.JPanel();
        TechNameLabel12 = new javax.swing.JLabel();
        TechCostLabel12 = new javax.swing.JLabel();
        TechStorageLabel12 = new javax.swing.JLabel();
        DescLabel = new javax.swing.JLabel();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("TECH");
        add(jLabel1);
        jLabel1.setBounds(148, 0, 70, 20);

        StorageLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        StorageLabel.setText("0/0");
        add(StorageLabel);
        StorageLabel.setBounds(20, 20, 50, 15);

        ResearchIcon.setText("ICON");
        add(ResearchIcon);
        ResearchIcon.setBounds(300, 10, 30, 30);

        jLabel5.setText("Storage");
        add(jLabel5);
        jLabel5.setBounds(30, 40, 50, 14);

        TechPoints.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TechPoints.setText("000");
        add(TechPoints);
        TechPoints.setBounds(340, 20, 50, 15);

        TechPanel0.setBackground(new java.awt.Color(51, 204, 255));
        TechPanel0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechPanel0MouseClicked(evt);
            }
        });
        TechPanel0.setLayout(null);

        TechNameLabel0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TechNameLabel0.setText("TECH");
        TechPanel0.add(TechNameLabel0);
        TechNameLabel0.setBounds(10, 10, 150, 14);

        TechCostLabel0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TechCostLabel0.setText("00");
        TechPanel0.add(TechCostLabel0);
        TechCostLabel0.setBounds(0, 0, 30, 20);

        TechStorageLabel.setText("000");
        TechPanel0.add(TechStorageLabel);
        TechStorageLabel.setBounds(170, 10, 18, 14);

        add(TechPanel0);
        TechPanel0.setBounds(100, 70, 200, 30);

        TechPanel1.setBackground(new java.awt.Color(51, 204, 255));
        TechPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechPanel1MouseClicked(evt);
            }
        });
        TechPanel1.setLayout(null);

        TechNameLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TechNameLabel1.setText("TECH");
        TechPanel1.add(TechNameLabel1);
        TechNameLabel1.setBounds(10, 10, 150, 14);

        TechCostLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TechCostLabel1.setText("00");
        TechPanel1.add(TechCostLabel1);
        TechCostLabel1.setBounds(0, 0, 30, 20);

        TechStorageLabel1.setText("000");
        TechPanel1.add(TechStorageLabel1);
        TechStorageLabel1.setBounds(170, 10, 18, 14);

        add(TechPanel1);
        TechPanel1.setBounds(10, 110, 200, 30);

        TechPanel2.setBackground(new java.awt.Color(51, 204, 255));
        TechPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechPanel2MouseClicked(evt);
            }
        });
        TechPanel2.setLayout(null);

        TechNameLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TechNameLabel2.setText("TECH");
        TechPanel2.add(TechNameLabel2);
        TechNameLabel2.setBounds(10, 10, 150, 14);

        TechCostLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TechCostLabel2.setText("00");
        TechPanel2.add(TechCostLabel2);
        TechCostLabel2.setBounds(0, 0, 30, 20);

        TechStorageLabel2.setText("000");
        TechPanel2.add(TechStorageLabel2);
        TechStorageLabel2.setBounds(170, 10, 18, 14);

        add(TechPanel2);
        TechPanel2.setBounds(230, 110, 200, 30);

        TechPanel3.setBackground(new java.awt.Color(51, 204, 255));
        TechPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechPanel3MouseClicked(evt);
            }
        });
        TechPanel3.setLayout(null);

        TechNameLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TechNameLabel3.setText("TECH");
        TechPanel3.add(TechNameLabel3);
        TechNameLabel3.setBounds(10, 10, 150, 14);

        TechCostLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TechCostLabel3.setText("00");
        TechPanel3.add(TechCostLabel3);
        TechCostLabel3.setBounds(0, 0, 30, 20);

        TechStorageLabel3.setText("000");
        TechPanel3.add(TechStorageLabel3);
        TechStorageLabel3.setBounds(170, 10, 18, 14);

        add(TechPanel3);
        TechPanel3.setBounds(440, 110, 200, 30);

        TechPanel4.setBackground(new java.awt.Color(51, 204, 255));
        TechPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechPanel4MouseClicked(evt);
            }
        });
        TechPanel4.setLayout(null);

        TechNameLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TechNameLabel4.setText("TECH");
        TechPanel4.add(TechNameLabel4);
        TechNameLabel4.setBounds(10, 10, 150, 14);

        TechCostLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TechCostLabel4.setText("00");
        TechPanel4.add(TechCostLabel4);
        TechCostLabel4.setBounds(0, 0, 30, 20);

        TechStorageLabel4.setText("000");
        TechPanel4.add(TechStorageLabel4);
        TechStorageLabel4.setBounds(170, 10, 18, 14);

        add(TechPanel4);
        TechPanel4.setBounds(10, 150, 200, 30);

        TechPanel5.setBackground(new java.awt.Color(51, 204, 255));
        TechPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechPanel5MouseClicked(evt);
            }
        });
        TechPanel5.setLayout(null);

        TechNameLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TechNameLabel5.setText("TECH");
        TechPanel5.add(TechNameLabel5);
        TechNameLabel5.setBounds(10, 10, 150, 14);

        TechCostLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TechCostLabel5.setText("00");
        TechPanel5.add(TechCostLabel5);
        TechCostLabel5.setBounds(0, 0, 30, 20);

        TechStorageLabel5.setText("000");
        TechPanel5.add(TechStorageLabel5);
        TechStorageLabel5.setBounds(170, 10, 18, 14);

        add(TechPanel5);
        TechPanel5.setBounds(230, 150, 200, 30);

        TechPanel6.setBackground(new java.awt.Color(51, 204, 255));
        TechPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechPanel6MouseClicked(evt);
            }
        });
        TechPanel6.setLayout(null);

        TechNameLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TechNameLabel6.setText("TECH");
        TechPanel6.add(TechNameLabel6);
        TechNameLabel6.setBounds(10, 10, 150, 14);

        TechCostLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TechCostLabel6.setText("00");
        TechPanel6.add(TechCostLabel6);
        TechCostLabel6.setBounds(0, 0, 30, 20);

        TechStorageLabel6.setText("000");
        TechPanel6.add(TechStorageLabel6);
        TechStorageLabel6.setBounds(170, 10, 18, 14);

        add(TechPanel6);
        TechPanel6.setBounds(440, 150, 200, 30);

        SelectedLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SelectedLabel.setText("SELECTION");
        add(SelectedLabel);
        SelectedLabel.setBounds(390, 320, 120, 20);

        ResearchLabel.setBackground(new java.awt.Color(204, 204, 0));
        ResearchLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ResearchLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ResearchLabel.setText("RESEARCH");
        ResearchLabel.setOpaque(true);
        ResearchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResearchLabelMouseClicked(evt);
            }
        });
        add(ResearchLabel);
        ResearchLabel.setBounds(520, 330, 90, 20);

        TechPanel7.setBackground(new java.awt.Color(51, 204, 255));
        TechPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechPanel7MouseClicked(evt);
            }
        });
        TechPanel7.setLayout(null);

        TechNameLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TechNameLabel7.setText("TECH");
        TechPanel7.add(TechNameLabel7);
        TechNameLabel7.setBounds(10, 10, 150, 14);

        TechCostLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TechCostLabel7.setText("00");
        TechPanel7.add(TechCostLabel7);
        TechCostLabel7.setBounds(0, 0, 30, 20);

        TechStorageLabel7.setText("000");
        TechPanel7.add(TechStorageLabel7);
        TechStorageLabel7.setBounds(170, 10, 18, 14);

        add(TechPanel7);
        TechPanel7.setBounds(10, 190, 200, 30);

        TechPanel8.setBackground(new java.awt.Color(51, 204, 255));
        TechPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechPanel8MouseClicked(evt);
            }
        });
        TechPanel8.setLayout(null);

        TechNameLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TechNameLabel8.setText("TECH");
        TechPanel8.add(TechNameLabel8);
        TechNameLabel8.setBounds(10, 10, 150, 14);

        TechCostLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TechCostLabel8.setText("00");
        TechPanel8.add(TechCostLabel8);
        TechCostLabel8.setBounds(0, 0, 30, 20);

        TechStorageLabel8.setText("000");
        TechPanel8.add(TechStorageLabel8);
        TechStorageLabel8.setBounds(170, 10, 18, 14);

        add(TechPanel8);
        TechPanel8.setBounds(230, 190, 200, 30);

        TechPanel9.setBackground(new java.awt.Color(51, 204, 255));
        TechPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechPanel9MouseClicked(evt);
            }
        });
        TechPanel9.setLayout(null);

        TechNameLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TechNameLabel9.setText("TECH");
        TechPanel9.add(TechNameLabel9);
        TechNameLabel9.setBounds(10, 10, 150, 14);

        TechCostLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TechCostLabel9.setText("00");
        TechPanel9.add(TechCostLabel9);
        TechCostLabel9.setBounds(0, 0, 30, 20);

        TechStorageLabel9.setText("000");
        TechPanel9.add(TechStorageLabel9);
        TechStorageLabel9.setBounds(170, 10, 18, 14);

        add(TechPanel9);
        TechPanel9.setBounds(440, 190, 200, 30);

        TechPanel10.setBackground(new java.awt.Color(51, 204, 255));
        TechPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechPanel10MouseClicked(evt);
            }
        });
        TechPanel10.setLayout(null);

        TechNameLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TechNameLabel10.setText("TECH");
        TechPanel10.add(TechNameLabel10);
        TechNameLabel10.setBounds(10, 10, 150, 14);

        TechCostLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TechCostLabel10.setText("00");
        TechPanel10.add(TechCostLabel10);
        TechCostLabel10.setBounds(0, 0, 30, 20);

        TechStorageLabel10.setText("000");
        TechPanel10.add(TechStorageLabel10);
        TechStorageLabel10.setBounds(170, 10, 18, 14);

        add(TechPanel10);
        TechPanel10.setBounds(10, 230, 200, 30);

        TechPanel11.setBackground(new java.awt.Color(51, 204, 255));
        TechPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechPanel11MouseClicked(evt);
            }
        });
        TechPanel11.setLayout(null);

        TechNameLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TechNameLabel11.setText("TECH");
        TechPanel11.add(TechNameLabel11);
        TechNameLabel11.setBounds(10, 10, 150, 14);

        TechCostLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TechCostLabel11.setText("00");
        TechPanel11.add(TechCostLabel11);
        TechCostLabel11.setBounds(0, 0, 30, 20);

        TechStorageLabel11.setText("000");
        TechPanel11.add(TechStorageLabel11);
        TechStorageLabel11.setBounds(170, 10, 18, 14);

        add(TechPanel11);
        TechPanel11.setBounds(230, 230, 200, 30);

        TechPanel12.setBackground(new java.awt.Color(51, 204, 255));
        TechPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TechPanel12MouseClicked(evt);
            }
        });
        TechPanel12.setLayout(null);

        TechNameLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TechNameLabel12.setText("TECH");
        TechPanel12.add(TechNameLabel12);
        TechNameLabel12.setBounds(10, 10, 150, 14);

        TechCostLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TechCostLabel12.setText("00");
        TechPanel12.add(TechCostLabel12);
        TechCostLabel12.setBounds(0, 0, 30, 20);

        TechStorageLabel12.setText("000");
        TechPanel12.add(TechStorageLabel12);
        TechStorageLabel12.setBounds(170, 10, 18, 14);

        add(TechPanel12);
        TechPanel12.setBounds(440, 230, 200, 30);

        DescLabel.setText("Desc");
        add(DescLabel);
        DescLabel.setBounds(400, 360, 230, 14);
    }// </editor-fold>//GEN-END:initComponents

    private void TechPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TechPanel1MouseClicked
        selectTech(1);
    }//GEN-LAST:event_TechPanel1MouseClicked

    private void TechPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TechPanel2MouseClicked
        selectTech(2);
    }//GEN-LAST:event_TechPanel2MouseClicked

    private void TechPanel0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TechPanel0MouseClicked
        selectTech(0);
    }//GEN-LAST:event_TechPanel0MouseClicked

    private void TechPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TechPanel3MouseClicked
        selectTech(3);
    }//GEN-LAST:event_TechPanel3MouseClicked

    private void TechPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TechPanel4MouseClicked
        selectTech(4);
    }//GEN-LAST:event_TechPanel4MouseClicked

    private void TechPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TechPanel5MouseClicked
        selectTech(5);
    }//GEN-LAST:event_TechPanel5MouseClicked

    private void TechPanel6MouseClicked(java.awt.event.MouseEvent evt) {                                        
        selectTech(6);
    }                                       

    private void ResearchLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResearchLabelMouseClicked
        researchClicked();
    }//GEN-LAST:event_ResearchLabelMouseClicked

    private void TechPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TechPanel7MouseClicked
    	selectTech(7);
    }//GEN-LAST:event_TechPanel7MouseClicked

    private void TechPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TechPanel8MouseClicked
    	selectTech(8);
    }//GEN-LAST:event_TechPanel8MouseClicked

    private void TechPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TechPanel9MouseClicked
    	selectTech(9);
    }//GEN-LAST:event_TechPanel9MouseClicked

    private void TechPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TechPanel10MouseClicked
    	selectTech(10);
    }//GEN-LAST:event_TechPanel10MouseClicked

    private void TechPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TechPanel11MouseClicked
    	selectTech(11);
    }//GEN-LAST:event_TechPanel11MouseClicked

    private void TechPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TechPanel12MouseClicked
    	selectTech(12);
    }//GEN-LAST:event_TechPanel12MouseClicked
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DescLabel;
    private javax.swing.JLabel ResearchIcon;
    private javax.swing.JLabel ResearchLabel;
    private javax.swing.JLabel SelectedLabel;
    private javax.swing.JLabel StorageLabel;
    private javax.swing.JLabel TechCostLabel0;
    private javax.swing.JLabel TechCostLabel1;
    private javax.swing.JLabel TechCostLabel10;
    private javax.swing.JLabel TechCostLabel11;
    private javax.swing.JLabel TechCostLabel12;
    private javax.swing.JLabel TechCostLabel2;
    private javax.swing.JLabel TechCostLabel3;
    private javax.swing.JLabel TechCostLabel4;
    private javax.swing.JLabel TechCostLabel5;
    private javax.swing.JLabel TechCostLabel6;
    private javax.swing.JLabel TechCostLabel7;
    private javax.swing.JLabel TechCostLabel8;
    private javax.swing.JLabel TechCostLabel9;
    private javax.swing.JLabel TechNameLabel0;
    private javax.swing.JLabel TechNameLabel1;
    private javax.swing.JLabel TechNameLabel10;
    private javax.swing.JLabel TechNameLabel11;
    private javax.swing.JLabel TechNameLabel12;
    private javax.swing.JLabel TechNameLabel2;
    private javax.swing.JLabel TechNameLabel3;
    private javax.swing.JLabel TechNameLabel4;
    private javax.swing.JLabel TechNameLabel5;
    private javax.swing.JLabel TechNameLabel6;
    private javax.swing.JLabel TechNameLabel7;
    private javax.swing.JLabel TechNameLabel8;
    private javax.swing.JLabel TechNameLabel9;
    private javax.swing.JPanel TechPanel0;
    private javax.swing.JPanel TechPanel1;
    private javax.swing.JPanel TechPanel10;
    private javax.swing.JPanel TechPanel11;
    private javax.swing.JPanel TechPanel12;
    private javax.swing.JPanel TechPanel2;
    private javax.swing.JPanel TechPanel3;
    private javax.swing.JPanel TechPanel4;
    private javax.swing.JPanel TechPanel5;
    private javax.swing.JPanel TechPanel6;
    private javax.swing.JPanel TechPanel7;
    private javax.swing.JPanel TechPanel8;
    private javax.swing.JPanel TechPanel9;
    private javax.swing.JLabel TechPoints;
    private javax.swing.JLabel TechStorageLabel;
    private javax.swing.JLabel TechStorageLabel1;
    private javax.swing.JLabel TechStorageLabel10;
    private javax.swing.JLabel TechStorageLabel11;
    private javax.swing.JLabel TechStorageLabel12;
    private javax.swing.JLabel TechStorageLabel2;
    private javax.swing.JLabel TechStorageLabel3;
    private javax.swing.JLabel TechStorageLabel4;
    private javax.swing.JLabel TechStorageLabel5;
    private javax.swing.JLabel TechStorageLabel6;
    private javax.swing.JLabel TechStorageLabel7;
    private javax.swing.JLabel TechStorageLabel8;
    private javax.swing.JLabel TechStorageLabel9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
