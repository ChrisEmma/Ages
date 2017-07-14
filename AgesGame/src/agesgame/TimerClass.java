/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agesgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;


/**
 *
 * @author cpemm_000
 */
class TimerClass extends JFrame{
    private Timer t;

 

    TimerClass(GameDataClass game, int time){
        t = new Timer(time, new ActionListener(){
              public void actionPerformed(ActionEvent e){
                  game.timerTick();
                t.restart();
                   TimerClass.this.dispose();
              }});
        t.start();
    }
    TimerClass(MainImpl screen, int time){
        t = new Timer(time, new ActionListener(){
              public void actionPerformed(ActionEvent e){
                  screen.timerTick();
                t.restart();
                   TimerClass.this.dispose();
              }});
        t.start();
    }
    TimerClass(PopupText upper){
        t = new Timer(250, new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 upper.timerTick();
               t.restart();
                  TimerClass.this.dispose();
             }});
       t.start();
   }

}

