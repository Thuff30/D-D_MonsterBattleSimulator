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

package Services;

import Interfaces.MainMenu;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Pathfinder2e {
    public static void main(String[] args) {
        JFrame errFrame = new JFrame();
        String error = "An error occured while launching the Monster Combat Simulator application. If this issue persists, please contact the your administrator.";
        try {
            MainMenu.LaunchApplication();
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(errFrame, error, "Application Launch Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
 
}
