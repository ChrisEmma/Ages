/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agesgame;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author cpemm_000
 */
public class PopupText extends JLabel {
    private int time;
    private TimerClass timer;
    private int yPos;
    private float SCALE_VALUE;
    
    public PopupText(String text, int time, BufferedImage image, int x, int y, float SCALE_VALUE){
        this.setText(text);
        this.time = time;
        this.SCALE_VALUE = SCALE_VALUE;
        timer = new TimerClass(this);
        this.yPos = y-30;
        this.setLocation(x, yPos);
        
        this.setIcon(new ImageIcon (image.getScaledInstance(Scale(25), Scale(25), 0)));

        System.out.println("Popup: " + text + " created  " + x + ", " + y);
    }
    public void timerTick(){
        time -= 1;
        yPos -= 2;
        this.setLocation(this.getX(), yPos);
        if (time <= 0){
            this.setEnabled(false);
            this.setVisible(false);
            
        }
    }
    public int Scale(float in){
    	return Math.round(in * SCALE_VALUE);
    }
}
