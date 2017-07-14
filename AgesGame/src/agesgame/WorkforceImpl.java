package agesgame;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WorkforceImpl extends javax.swing.JPanel {
    private GameDataClass game;
    private CityViewImpl upperView;
            
    public WorkforceImpl(GameDataClass game,CityViewImpl cityView) {
        this.game = game;
        upperView = cityView;
        initComponents();
        scaleView();
        generateDisplay();
    }
    public void generateDisplay(){
        getJobLabel(0).setText(getIdleWorkerCount()+"");
        getJobLabel(1).setText(getWorkerCount("BUILD") + " of " + game.getJobsAvilable("BUILD"));
        getJobLabel(2).setText(getWorkerCount("FORAGE") + " of " + game.getJobsAvilable("FORAGE"));
        getJobLabel(3).setText(getWorkerCount("WOODCUT") + " of " + game.getJobsAvilable("WOODCUT"));
        getJobLabel(4).setText(getWorkerCount("DIGSTONE") + " of " + game.getJobsAvilable("DIGSTONE"));
        getJobLabel(5).setText(getWorkerCount("FARM") + " of " + game.getJobsAvilable("FARM"));
        getJobLabel(6).setText(getWorkerCount("THINK") + " of " + game.getJobsAvilable("THINK"));
        getJobLabel(7).setText(getWorkerCount("COOK") + " of " + game.getJobsAvilable("COOK"));
        getJobLabel(8).setText(getWorkerCount("CRAFT") + " of " + game.getJobsAvilable("CRAFT"));
        getJobLabel(9).setText(getWorkerCount("DIGCLAY") + " of " + game.getJobsAvilable("DIGCLAY"));

        
        for (int i = 1; i < 10; i++){
            getUpDownLabel(i,true).setIcon(new ImageIcon(game.tables.getUpIcon().getScaledInstance(upperView.Scale(30), upperView.Scale(30), 0)));
            getUpDownLabel(i,true).setText("");
            getUpDownLabel(i,false).setIcon(new ImageIcon(game.tables.getDownIcon().getScaledInstance(upperView.Scale(30), upperView.Scale(30), 0)));
            getUpDownLabel(i,false).setText("");
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
    public int getWorkerCount(String job){
        int count = 0;
        for (int i = 0; i < game.getPopCount(); i++){
            if (game.getPopulation(i).jobsAllowd.contains(job)){
                count ++;
            }
        }
        return count;
    }
    public void close(){
        this.hide();
    }

    public void incrementJob(int job,boolean up){
    	System.out.println("SetJob:" + job + " up:" + up);
        String searchVal = "";
        if (job == 1)
            searchVal = "BUILD";
        if (job == 2)
            searchVal = "FORAGE";
        if (job == 3)
            searchVal = "WOODCUT";
        if (job == 4)
            searchVal = "DIGSTONE";
        if (job == 5)
            searchVal = "FARM";
        if (job == 6)
            searchVal = "THINK";
        if (job == 7)
            searchVal = "COOK";
        if (job == 8)
            searchVal = "CRAFT";
        if (job == 9)
            searchVal = "DIGCLAY";
        if (up){
            addJob(searchVal);
        }else{
            removeJob(searchVal);
        }
        generateDisplay();
    }
    public void removeJob(String job){
        for (int i = 0; i < game.getPopCount(); i++){
            if (game.getPopulation(i).jobsAllowd.contains(job)){
                game.getPopulation(i).jobsAllowd = "PICKUP";
                break;
            }
        }
    }
    public void addJob(String job){
        for (int i = 0; i < game.getPopCount(); i++){
            if (game.getPopulation(i).jobsAllowd == "PICKUP" || game.getPopulation(i).jobsAllowd == null || game.getPopulation(i).jobsAllowd == ""){
                game.getPopulation(i).jobsAllowd = game.getPopulation(i).jobsAllowd + "," + job;
                break;
            }
        }
    }
    public JLabel getJobLabel(int i) {
        if (i == 0) {
            return JobLabel0;
        }if (i == 1) {
            return JobLabel1;
        }if (i == 2) {
            return JobLabel2;
        }if (i == 3) {
            return JobLabel3;
        }if (i == 4) {
            return JobLabel4;
        }if (i == 5) {
            return JobLabel5;
        }if (i == 6) {
            return JobLabel6;
        }if (i == 7) {
            return JobLabel7;
        }if (i == 8) {
            return JobLabel8;
        }if (i == 9) {
            return JobLabel9;
        }
        return null;
    }
    public JLabel getUpDownLabel(int i, boolean up) {
        if (up){
            if (i == 1) {
                return JobUp1;
            }if (i == 2) {
                return JobUp2;
            }if (i == 3) {
                return JobUp3;
            }if (i == 4) {
                return JobUp4;
            }if (i == 5) {
                return JobUp5;
            }if (i == 6) {
                return JobUp6;
            }if (i == 7) {
                return JobUp7;
            }if (i == 8) {
                return JobUp8;
            }if (i == 9) {
                return JobUp9;
            }
        }else{
            if (i == 1) {
                return JobDown1;
            }if (i == 2) {
                return JobDown2;
            }if (i == 3) {
                return JobDown3;
            }if (i == 4) {
                return JobDown4;
            }if (i == 5) {
                return JobDown5;
            }if (i == 6) {
                return JobDown6;
            }if (i == 7) {
                return JobDown7;
            }if (i == 8) {
                return JobDown8;
            }if (i == 9) {
                return JobDown9;
            }
        }
        return null;
    }

    public void scaleView(){
    	for (int i = 0; i < 11; i ++){
        	JLabel setLabel = new javax.swing.JLabel();;
        	if (i == 0)
        		setLabel = jLabel1;
        	if (i == 1)
        		setLabel = jLabel2;
        	if (i == 2)
        		setLabel = CloseLabel;
        	if (i == 3)
        		setLabel = jLabel3;
        	if (i == 4)
        		setLabel = jLabel4;
        	if (i == 5)
        		setLabel = jLabel5;
        	if (i == 6)
        		setLabel = jLabel6;
        	if (i == 7)
        		setLabel = jLabel7;
			if (i == 8)
				setLabel = jLabel8;
			if (i == 9)
				setLabel = jLabel9;
			if (i == 10)
				setLabel = jLabel10;
        	setLabel.setBounds(upperView.Scale(setLabel.getX()), upperView.Scale(setLabel.getY()), upperView.Scale(setLabel.getWidth()), upperView.Scale(setLabel.getHeight()));
        	setLabel.setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(14))); // NOI18N
    	}
    	
        for (int i = 0; i < 10; i++){
        	getJobLabel(i).setBounds(upperView.Scale(getJobLabel(i).getX()), upperView.Scale(getJobLabel(i).getY()), upperView.Scale(getJobLabel(i).getWidth()), upperView.Scale(getJobLabel(i).getHeight()));
        	getJobLabel(i).setFont(new java.awt.Font("Tahoma", 1, upperView.Scale(14))); // NOI18N
        	
        	if (i > 0){
        		getUpDownLabel(i,true).setSize(upperView.Scale(30), upperView.Scale(30));
        		getUpDownLabel(i,true).setBounds(upperView.Scale(getUpDownLabel(i,true).getX()), upperView.Scale(getUpDownLabel(i,true).getY()), upperView.Scale(30), upperView.Scale(30));
        		getUpDownLabel(i,false).setSize(upperView.Scale(30), upperView.Scale(30));
        		getUpDownLabel(i,false).setBounds(upperView.Scale(getUpDownLabel(i,false).getX()), upperView.Scale(getUpDownLabel(i,false).getY()), upperView.Scale(30), upperView.Scale(30));
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
        JobLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JobLabel2 = new javax.swing.JLabel();
        CloseLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JobLabel0 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JobLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JobLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JobLabel5 = new javax.swing.JLabel();
        JobUp1 = new javax.swing.JLabel();
        JobDown1 = new javax.swing.JLabel();
        JobUp2 = new javax.swing.JLabel();
        JobDown2 = new javax.swing.JLabel();
        JobUp3 = new javax.swing.JLabel();
        JobDown3 = new javax.swing.JLabel();
        JobUp4 = new javax.swing.JLabel();
        JobDown4 = new javax.swing.JLabel();
        JobUp5 = new javax.swing.JLabel();
        JobDown5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        JobLabel6 = new javax.swing.JLabel();
        JobUp6 = new javax.swing.JLabel();
        JobDown6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        JobLabel7 = new javax.swing.JLabel();
        JobUp7 = new javax.swing.JLabel();
        JobDown7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        JobLabel8 = new javax.swing.JLabel();
        JobUp8 = new javax.swing.JLabel();
        JobDown8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        JobLabel9 = new javax.swing.JLabel();
        JobUp9 = new javax.swing.JLabel();
        JobDown9 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Builders");
        add(jLabel1);
        jLabel1.setBounds(20, 50, 81, 22);

        JobLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JobLabel1.setText("000");
        add(JobLabel1);
        JobLabel1.setBounds(119, 47, 42, 17);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Foragers");
        add(jLabel2);
        jLabel2.setBounds(20, 80, 81, 22);

        JobLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JobLabel2.setText("000");
        add(JobLabel2);
        JobLabel2.setBounds(120, 80, 42, 17);

        CloseLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CloseLabel.setText("X");
        CloseLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseLabelMouseClicked(evt);
            }
        });
        add(CloseLabel);
        CloseLabel.setBounds(277, 1, 12, 14);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Unassigned");
        add(jLabel3);
        jLabel3.setBounds(20, 20, 81, 22);

        JobLabel0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JobLabel0.setText("000");
        add(JobLabel0);
        JobLabel0.setBounds(119, 20, 42, 17);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Woodcutters");
        add(jLabel4);
        jLabel4.setBounds(20, 110, 99, 22);

        JobLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JobLabel3.setText("000");
        add(JobLabel3);
        JobLabel3.setBounds(120, 110, 42, 17);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Stoneminers");
        add(jLabel5);
        jLabel5.setBounds(20, 140, 81, 22);

        JobLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JobLabel4.setText("000");
        add(JobLabel4);
        JobLabel4.setBounds(120, 140, 42, 17);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Farmers");
        add(jLabel6);
        jLabel6.setBounds(20, 170, 81, 22);

        JobLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JobLabel5.setText("000");
        add(JobLabel5);
        JobLabel5.setBounds(120, 170, 42, 17);

        JobUp1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobUp1.setText("+");
        JobUp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobUp1MouseClicked(evt);
            }
        });
        add(JobUp1);
        JobUp1.setBounds(180, 40, 30, 30);

        JobDown1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobDown1.setText("-");
        JobDown1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobDown1MouseClicked(evt);
            }
        });
        add(JobDown1);
        JobDown1.setBounds(220, 40, 30, 30);

        JobUp2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobUp2.setText("+");
        JobUp2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobUp2MouseClicked(evt);
            }
        });
        add(JobUp2);
        JobUp2.setBounds(180, 70, 30, 30);

        JobDown2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobDown2.setText("-");
        JobDown2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobDown2MouseClicked(evt);
            }
        });
        add(JobDown2);
        JobDown2.setBounds(220, 70, 30, 30);

        JobUp3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobUp3.setText("+");
        JobUp3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobUp3MouseClicked(evt);
            }
        });
        add(JobUp3);
        JobUp3.setBounds(180, 100, 30, 30);

        JobDown3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobDown3.setText("-");
        JobDown3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobDown3MouseClicked(evt);
            }
        });
        add(JobDown3);
        JobDown3.setBounds(220, 100, 30, 30);

        JobUp4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobUp4.setText("+");
        JobUp4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobUp4MouseClicked(evt);
            }
        });
        add(JobUp4);
        JobUp4.setBounds(180, 130, 30, 30);

        JobDown4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobDown4.setText("-");
        JobDown4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobDown4MouseClicked(evt);
            }
        });
        add(JobDown4);
        JobDown4.setBounds(220, 130, 30, 30);

        JobUp5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobUp5.setText("+");
        JobUp5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobUp5MouseClicked(evt);
            }
        });
        add(JobUp5);
        JobUp5.setBounds(180, 160, 30, 30);

        JobDown5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobDown5.setText("-");
        JobDown5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobDown5MouseClicked(evt);
            }
        });
        add(JobDown5);
        JobDown5.setBounds(220, 160, 30, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Thinkers");
        add(jLabel7);
        jLabel7.setBounds(20, 200, 81, 22);

        JobLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JobLabel6.setText("000");
        add(JobLabel6);
        JobLabel6.setBounds(120, 200, 42, 17);

        JobUp6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobUp6.setText("+");
        JobUp6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobUp6MouseClicked(evt);
            }
        });
        add(JobUp6);
        JobUp6.setBounds(180, 190, 30, 30);

        JobDown6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobDown6.setText("-");
        JobDown6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobDown6MouseClicked(evt);
            }
        });
        add(JobDown6);
        JobDown6.setBounds(220, 190, 30, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Cooks");
        add(jLabel8);
        jLabel8.setBounds(20, 230, 81, 22);

        JobLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JobLabel7.setText("000");
        add(JobLabel7);
        JobLabel7.setBounds(120, 230, 42, 17);

        JobUp7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobUp7.setText("+");
        JobUp7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobUp7MouseClicked(evt);
            }
        });
        add(JobUp7);
        JobUp7.setBounds(180, 220, 30, 30);

        JobDown7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobDown7.setText("-");
        JobDown7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobDown7MouseClicked(evt);
            }
        });
        add(JobDown7);
        JobDown7.setBounds(220, 220, 30, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Crafters");
        add(jLabel9);
        jLabel9.setBounds(20, 260, 81, 22);

        JobLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JobLabel8.setText("000");
        add(JobLabel8);
        JobLabel8.setBounds(120, 260, 42, 17);

        JobUp8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobUp8.setText("+");
        JobUp8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobUp8MouseClicked(evt);
            }
        });
        add(JobUp8);
        JobUp8.setBounds(180, 250, 30, 30);

        JobDown8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobDown8.setText("-");
        JobDown8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobDown8MouseClicked(evt);
            }
        });
        add(JobDown8);
        JobDown8.setBounds(220, 250, 30, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Clay Gather");
        add(jLabel10);
        jLabel10.setBounds(20, 290, 81, 22);

        JobLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JobLabel9.setText("000");
        add(JobLabel9);
        JobLabel9.setBounds(120, 290, 42, 17);

        JobUp9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobUp9.setText("+");
        JobUp9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobUp9MouseClicked(evt);
            }
        });
        add(JobUp9);
        JobUp9.setBounds(180, 280, 30, 30);

        JobDown9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JobDown9.setText("-");
        JobDown9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobDown9MouseClicked(evt);
            }
        });
        add(JobDown9);
        JobDown9.setBounds(220, 280, 30, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void CloseLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseLabelMouseClicked
        close();
    }//GEN-LAST:event_CloseLabelMouseClicked

    private void JobUp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobUp1MouseClicked
        incrementJob(1,true);
    }//GEN-LAST:event_JobUp1MouseClicked

    private void JobDown1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobDown1MouseClicked
        incrementJob(1,false);
    }//GEN-LAST:event_JobDown1MouseClicked

    private void JobUp2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobUp2MouseClicked
        incrementJob(2,true);
    }//GEN-LAST:event_JobUp2MouseClicked

    private void JobDown2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobDown2MouseClicked
        incrementJob(2,false);
    }//GEN-LAST:event_JobDown2MouseClicked

    private void JobUp3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobUp3MouseClicked
        incrementJob(3,true);
    }//GEN-LAST:event_JobUp3MouseClicked

    private void JobDown3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobDown3MouseClicked
        incrementJob(3,false);
    }//GEN-LAST:event_JobDown3MouseClicked

    private void JobUp4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobUp4MouseClicked
        incrementJob(4,true);
    }//GEN-LAST:event_JobUp4MouseClicked

    private void JobDown4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobDown4MouseClicked
        incrementJob(4,false);
    }//GEN-LAST:event_JobDown4MouseClicked

    private void JobUp5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobUp5MouseClicked
        incrementJob(5,true);
    }//GEN-LAST:event_JobUp5MouseClicked

    private void JobDown5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobDown5MouseClicked
        incrementJob(5,false);
    }//GEN-LAST:event_JobDown5MouseClicked

    private void JobUp6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobUp6MouseClicked
        incrementJob(6,true);
    }//GEN-LAST:event_JobUp6MouseClicked

    private void JobDown6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobDown6MouseClicked
        incrementJob(6,false);
    }//GEN-LAST:event_JobDown6MouseClicked

    private void JobUp7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobUp7MouseClicked
        incrementJob(7,true);
    }//GEN-LAST:event_JobUp7MouseClicked

    private void JobDown7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobDown7MouseClicked
        incrementJob(7,false);
    }//GEN-LAST:event_JobDown7MouseClicked

    private void JobUp8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobUp8MouseClicked
    	incrementJob(8,true);
    }//GEN-LAST:event_JobUp8MouseClicked

    private void JobDown8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobDown8MouseClicked
    	incrementJob(8,false);
    }//GEN-LAST:event_JobDown8MouseClicked

    private void JobUp9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobUp9MouseClicked
    	incrementJob(9,true);
    }//GEN-LAST:event_JobUp9MouseClicked

    private void JobDown9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobDown9MouseClicked
    	incrementJob(9,false);
    }//GEN-LAST:event_JobDown9MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CloseLabel;
    private javax.swing.JLabel JobDown1;
    private javax.swing.JLabel JobDown2;
    private javax.swing.JLabel JobDown3;
    private javax.swing.JLabel JobDown4;
    private javax.swing.JLabel JobDown5;
    private javax.swing.JLabel JobDown6;
    private javax.swing.JLabel JobDown7;
    private javax.swing.JLabel JobDown8;
    private javax.swing.JLabel JobDown9;
    private javax.swing.JLabel JobLabel0;
    private javax.swing.JLabel JobLabel1;
    private javax.swing.JLabel JobLabel2;
    private javax.swing.JLabel JobLabel3;
    private javax.swing.JLabel JobLabel4;
    private javax.swing.JLabel JobLabel5;
    private javax.swing.JLabel JobLabel6;
    private javax.swing.JLabel JobLabel7;
    private javax.swing.JLabel JobLabel8;
    private javax.swing.JLabel JobLabel9;
    private javax.swing.JLabel JobUp1;
    private javax.swing.JLabel JobUp2;
    private javax.swing.JLabel JobUp3;
    private javax.swing.JLabel JobUp4;
    private javax.swing.JLabel JobUp5;
    private javax.swing.JLabel JobUp6;
    private javax.swing.JLabel JobUp7;
    private javax.swing.JLabel JobUp8;
    private javax.swing.JLabel JobUp9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
