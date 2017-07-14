/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agesgame;

import agesgame.GameDataClass;
import agesgame.GameTablesClass;
import agesgame.MainImpl;
import agesgame.TimerClass;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author cpemm_000
 */
public class AgesGame extends JApplet {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }

                JFrame frame = new JFrame("JavaFX 2 in Swing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JApplet applet = new AgesGame();
                applet.init();

                frame.setContentPane(applet.getContentPane());

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                applet.start();
                
            }
        });
        
    }

    @Override
    public void init() {
        JFXPanel fxContainer = new JFXPanel();

        GameTablesClass tables = new GameTablesClass();
        tables.MockLoad();
        
        GameDataClass game = new GameDataClass(tables);
        
        int UI_X = 1600;
        int UI_Y = 800;
        float Scale = 1;
        String version = "Alpha 1";
        
        if(Toolkit.getDefaultToolkit().getScreenSize().getWidth()> 2000){
        	Scale = 2;
        }
        
        
        System.out.println("Scale " + Scale);
        
        MainImpl UI = new MainImpl(game, tables, Scale, version);
        UI_X = Math.round(Scale*UI_X);
        UI_Y = Math.round(Scale*UI_Y);
        UI.setSize(UI_X, UI_Y);
        add(UI);
        System.out.println("UI.openTitle");
        UI.openTitle(version);

        //TimerClass gameTimer = new TimerClass(UI, game);

        
        

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                UI.setVisible(true);

            }
        });

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keyPressed(evt);
            }
        });
    }
    
    public void keyPressed(KeyEvent event) {
        System.out.println("Ages - Key: " + event.getKeyChar());
    }
    
}
