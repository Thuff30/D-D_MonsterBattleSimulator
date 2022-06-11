/*

Pathfinder 2e Combat Simulator Version 4.0.0.2
Developed for CMSC 495
Team:
    Bryan Robinson – Documentation Lead
    Chris Kaufmann – Programmer Lead
    Jason Jones – Project Lead
    Patrick Walsh – Reviewer Lead
    Thomas Huff – GUI Developer Lead

*/
package Interfaces;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    
    public static void LaunchApplication(){
        
        //Frame and panel declaration
        JFrame mainF = new JFrame("Pathfinder 2E: Monster Simulator");
        JPanel mainP = new JPanel();
        mainP.setLayout(new BoxLayout(mainP,BoxLayout.PAGE_AXIS));
        JPanel top = new JPanel(new FlowLayout());
        JPanel buttons = new JPanel(new FlowLayout());
        
        //Component declaration
        JLabel label = new JLabel();
        ImageIcon logo = new ImageIcon("PF2Logo.png");
        JButton cont = new JButton("NEW BATTLE");
        JButton exit = new JButton("EXIT");
        
        //Setting frame properties
        mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainF.setSize(600,400);
        mainF.setResizable(false);
        mainF.setLocationRelativeTo(null);
        mainF.setBackground(Color.LIGHT_GRAY);
        mainF.setVisible(true);
        
        //Adding components to panels
        label.setIcon(logo);
        top.add(label);
        buttons.add(cont);
        buttons.add(exit);
        mainP.add(top);
        mainP.add(buttons);
        mainF.getContentPane().add(mainP);
        
        //Setting action listeners
        cont.addActionListener(e->{
            MonsterMenu.ShowMonsterScreen();
            mainF.dispose();
        });
        exit.addActionListener(e->{
        	System.exit(0);
        });
    }
}