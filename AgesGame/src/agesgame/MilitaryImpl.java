package agesgame;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MilitaryImpl extends javax.swing.JPanel {
    private GameDataClass game;
    private CityViewImpl upperView;
            
    public MilitaryImpl(GameDataClass game,CityViewImpl cityView) {
        this.game = game;
        upperView = cityView;
        initComponents();
        scaleView();
        generateDisplay();
    }
    public void generateDisplay(){
    	AvailableLabel.setText("Available Pop: " +  getIdleWorkerCount());
    	
    	for (int i = 0; i < game.military[0].size(); i ++){
    		getTeamMemberLabel(0,i).setVisible(true);
    		getTeamMemberLabel(0,i).setText("");
    		getTeamMemberLabel(0,i).setIcon(new ImageIcon(game.getPopulation(game.military[0].get(i)).getImage().getScaledInstance(upperView.Scale(2*upperView.POP_W), upperView.Scale(2*upperView.POP_L), 0)));
    		
    		getMemberWepLabel(0,i).setVisible(true);
    		if (game.getPopulation(game.military[0].get(i)).getWeapon() < 1){
    			getMemberWepLabel(0,i).setText("Unarmed");
    		}else{
    			getMemberWepLabel(0,i).setText(game.tables.getItemName(game.getPopulation(game.military[0].get(i)).getWeapon()));
    		}
    	}
    	
    	if (game.military[0].size() < 3){
	    	getTeamMemberLabel(0,game.military[0].size()).setVisible(true);
	    	getTeamMemberLabel(0,game.military[0].size()).setIcon(new ImageIcon(game.tables.getUpIcon().getScaledInstance(upperView.Scale(2*upperView.POP_W), upperView.Scale(2*upperView.POP_L), 0)));
	    	getMemberWepLabel(0,game.military[0].size()).setVisible(false);
	    	
	    	for (int i = game.military[0].size() + 1; i < 3; i ++){
	    		getTeamMemberLabel(0,i).setVisible(false);
	    		getMemberWepLabel(0,i).setVisible(false);
	    	}
    	}
    }
    
    public void close(){
        this.hide();
    }
    public void setHunt(int team){
    	for (int i = 0; i < game.military[0].size(); i ++){
    		if (game.getPopulation(game.military[0].get(i)).getWeapon() > 0){
    			game.getPopulation(game.military[0].get(i)).jobsAllowd = "HUNT";
    		}
    	}
    }
    public int getIdleWorkerCount(){
        int count = 0;	
        for (int i = 0; i < game.getPopCount(); i++){
            if (game.getPopulation(i).jobsAllowd == "PICKUP" || game.getPopulation(i).jobsAllowd == null || game.getPopulation(i).jobsAllowd == ""){
                count ++;
            }
        }
        return count;
    }
    public void addMember(int team){
            for (int i = 0; i < game.getPopCount(); i++){
                if (game.getPopulation(i).jobsAllowd == "PICKUP" || game.getPopulation(i).jobsAllowd == null || game.getPopulation(i).jobsAllowd == ""){
                    game.getPopulation(i).jobsAllowd = "MILITARY" + team;
                    game.military[team].add(i);
                    break;
                }
            }
            
        
            generateDisplay();
    }
    public void removeMember(int team, int box){
    	int i = game.military[team].get(box);
    	game.getPopulation(i).jobsAllowd = "PICKUP";
    	game.military[team].remove(box);
    }
    public void equiptWep(int team){
    	System.out.println("Equipt Team " + team);
    	for (int i = 0; i < game.military[team].size(); i ++){
    		game.getPopulation(game.military[team].get(i)).checkWeapon();
    	}
    }
    
    public JLabel getTeamMemberLabel(int team, int i){
    	if (team == 0){
	    	if (i == 0)
	    		return TeamMember0_0;
	    	if (i == 1)
	    		return TeamMember0_1;
	    	if (i == 2)
	    		return TeamMember0_2;
    	}
    	return null;
    }
    public JLabel getMemberWepLabel(int team, int i){
    	if (team == 0){
	    	if (i == 0)
	    		return MemberWep0_0;
	    	if (i == 1)
	    		return MemberWep0_1;
	    	if (i == 2)
	    		return MemberWep0_2;
    	}
    	return null;
    }
    	

    public void scaleView(){
    	CloseLabel.setBounds(upperView.Scale(CloseLabel.getX()), upperView.Scale(CloseLabel.getY()), upperView.Scale(CloseLabel.getWidth()), upperView.Scale(CloseLabel.getHeight()));
    	CloseLabel.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(14))); // NOI18N
    	
    	AvailableLabel.setBounds(upperView.Scale(AvailableLabel.getX()), upperView.Scale(AvailableLabel.getY()), upperView.Scale(AvailableLabel.getWidth()), upperView.Scale(AvailableLabel.getHeight()));
    	AvailableLabel.setFont(new java.awt.Font("Tahoma", 0, upperView.Scale(14))); // NOI18N
    	
    	Title0.setBounds(upperView.Scale(Title0.getX()), upperView.Scale(Title0.getY()), upperView.Scale(Title0.getWidth()), upperView.Scale(Title0.getHeight()));
    	Title0.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(14))); // NOI18N
    	
    	TeamHunt0.setBounds(upperView.Scale(TeamHunt0.getX()), upperView.Scale(TeamHunt0.getY()), upperView.Scale(TeamHunt0.getWidth()), upperView.Scale(TeamHunt0.getHeight()));
    	TeamHunt0.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(14))); // NOI18N
    	TeamTrain0.setBounds(upperView.Scale(TeamTrain0.getX()), upperView.Scale(TeamTrain0.getY()), upperView.Scale(TeamTrain0.getWidth()), upperView.Scale(TeamTrain0.getHeight()));
    	TeamTrain0.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(14))); // NOI18N
    	TeamWeapon0.setBounds(upperView.Scale(TeamWeapon0.getX()), upperView.Scale(TeamWeapon0.getY()), upperView.Scale(TeamWeapon0.getWidth()), upperView.Scale(TeamWeapon0.getHeight()));
    	TeamWeapon0.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(14))); // NOI18N
    	
    	TeamLabel0.setBounds(upperView.Scale(TeamLabel0.getX()), upperView.Scale(TeamLabel0.getY()), upperView.Scale(TeamLabel0.getWidth()), upperView.Scale(TeamLabel0.getHeight()));
    	for (int c = 0; c < 1; c++){
    		//getTeamLabel(c).setBounds(upperView.Scale(getCategoryLabel(c).getX()), upperView.Scale(getCategoryLabel(c).getY()), upperView.Scale(getCategoryLabel(c).getWidth()), upperView.Scale(getCategoryLabel(c).getHeight()));
    		//getCategoryLabel(c).setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(18))); // NOI18N	
    	
	    	for (int i = 0; i < 3; i ++){
	    		getTeamMemberLabel(c,i).setBounds(upperView.Scale(getTeamMemberLabel(c,i).getX()), upperView.Scale(getTeamMemberLabel(c,i).getY()), upperView.Scale(2*upperView.POP_W), upperView.Scale(2*upperView.POP_L));
	    		
	    		getMemberWepLabel(c,i).setBounds(upperView.Scale(getMemberWepLabel(c,i).getX()), upperView.Scale(getMemberWepLabel(c,i).getY()), upperView.Scale(getMemberWepLabel(c,i).getWidth()), upperView.Scale(getMemberWepLabel(c,i).getHeight()));
	    		
	    		
	    		getMemberWepLabel(c,i).setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(14))); // NOI18N	
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

        CloseLabel = new javax.swing.JLabel();
        TeamHunt0 = new javax.swing.JLabel();
        Title0 = new javax.swing.JLabel();
        TeamLabel0 = new javax.swing.JLabel();
        TeamMember0_0 = new javax.swing.JLabel();
        TeamMember0_1 = new javax.swing.JLabel();
        TeamMember0_2 = new javax.swing.JLabel();
        TeamWeapon0 = new javax.swing.JLabel();
        MemberWep0_0 = new javax.swing.JLabel();
        MemberWep0_1 = new javax.swing.JLabel();
        MemberWep0_2 = new javax.swing.JLabel();
        AvailableLabel = new javax.swing.JLabel();
        TeamTrain0 = new javax.swing.JLabel();

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

        TeamHunt0.setBackground(new java.awt.Color(255, 51, 51));
        TeamHunt0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TeamHunt0.setText("HUNT");
        TeamHunt0.setOpaque(true);
        TeamHunt0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TeamHunt0MouseClicked(evt);
            }
        });
        add(TeamHunt0);
        TeamHunt0.setBounds(480, 70, 40, 30);

        Title0.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Title0.setText("Team");
        add(Title0);
        Title0.setBounds(10, 20, 80, 22);

        TeamLabel0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TeamLabel0.setText("Team 1");
        add(TeamLabel0);
        TeamLabel0.setBounds(10, 70, 80, 17);

        TeamMember0_0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TeamMember0_0.setText("X");
        TeamMember0_0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TeamMember0_0MouseClicked(evt);
            }
        });
        add(TeamMember0_0);
        TeamMember0_0.setBounds(110, 50, 20, 40);

        TeamMember0_1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TeamMember0_1.setText("X");
        TeamMember0_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TeamMember0_1MouseClicked(evt);
            }
        });
        add(TeamMember0_1);
        TeamMember0_1.setBounds(170, 50, 20, 40);

        TeamMember0_2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TeamMember0_2.setText("X");
        TeamMember0_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TeamMember0_2MouseClicked(evt);
            }
        });
        add(TeamMember0_2);
        TeamMember0_2.setBounds(240, 50, 20, 40);

        TeamWeapon0.setBackground(new java.awt.Color(255, 204, 102));
        TeamWeapon0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TeamWeapon0.setText("WEP");
        TeamWeapon0.setOpaque(true);
        TeamWeapon0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TeamWeapon0MouseClicked(evt);
            }
        });
        add(TeamWeapon0);
        TeamWeapon0.setBounds(410, 70, 40, 30);

        MemberWep0_0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MemberWep0_0.setText("Spear: 5");
        add(MemberWep0_0);
        MemberWep0_0.setBounds(90, 90, 60, 17);

        MemberWep0_1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MemberWep0_1.setText("Spear: 5");
        add(MemberWep0_1);
        MemberWep0_1.setBounds(160, 90, 60, 17);

        MemberWep0_2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MemberWep0_2.setText("Spear: 5");
        add(MemberWep0_2);
        MemberWep0_2.setBounds(230, 90, 60, 17);

        AvailableLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AvailableLabel.setText("Available Pop: 0");
        add(AvailableLabel);
        AvailableLabel.setBounds(230, 20, 130, 17);

        TeamTrain0.setBackground(new java.awt.Color(51, 255, 0));
        TeamTrain0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TeamTrain0.setText("TRAIN");
        TeamTrain0.setOpaque(true);
        TeamTrain0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TeamTrain0MouseClicked(evt);
            }
        });
        add(TeamTrain0);
        TeamTrain0.setBounds(530, 70, 40, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void CloseLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseLabelMouseClicked
        close();
    }//GEN-LAST:event_CloseLabelMouseClicked

    private void TeamMember0_0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TeamMember0_0MouseClicked
    	 addMember(0);
    }//GEN-LAST:event_TeamMember0_0MouseClicked

    private void TeamMember0_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TeamMember0_1MouseClicked
    	addMember(0);
    }//GEN-LAST:event_TeamMember0_1MouseClicked

    private void TeamMember0_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TeamMember0_2MouseClicked
    	addMember(0);
    }//GEN-LAST:event_TeamMember0_2MouseClicked

    private void TeamWeapon0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TeamWeapon0MouseClicked
        equiptWep(0); 
    }//GEN-LAST:event_TeamWeapon0MouseClicked

    private void TeamHunt0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TeamHunt0MouseClicked
    	setHunt(0);
    }//GEN-LAST:event_TeamHunt0MouseClicked

    private void TeamTrain0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TeamTrain0MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TeamTrain0MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AvailableLabel;
    private javax.swing.JLabel CloseLabel;
    private javax.swing.JLabel MemberWep0_0;
    private javax.swing.JLabel MemberWep0_1;
    private javax.swing.JLabel MemberWep0_2;
    private javax.swing.JLabel TeamHunt0;
    private javax.swing.JLabel TeamLabel0;
    private javax.swing.JLabel TeamMember0_0;
    private javax.swing.JLabel TeamMember0_1;
    private javax.swing.JLabel TeamMember0_2;
    private javax.swing.JLabel TeamTrain0;
    private javax.swing.JLabel TeamWeapon0;
    private javax.swing.JLabel Title0;
    // End of variables declaration//GEN-END:variables
}
