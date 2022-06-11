/*

Pathfinder 2e Combat Simulator Version 4.0.0.2
Developed for CMSC 495
Team:
    Bryan Robinson � Documentation Lead
    Chris Kaufmann � Programmer Lead
    Jason Jones � Project Lead
    Patrick Walsh � Reviewer Lead
    Thomas Huff � GUI Developer Lead

*/

package Interfaces;

import Services.Dice;
import Services.Monster;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MonsterMenu {
    private static final List<Monster> monsters = Arrays.asList(
          //Monster(name, init, hp, regen, arm, atk, dmg, pic, lostPic)
        new Monster("Astral Deva", 36, 285, 0, 36, 30, new Dice("2d8+22", 2, 8, 22),"AstralDeva.png", "AstralDeva_Lost.png", "AstralDeva_Title.png"),
        new Monster("Blood Ooze", 8, 90, 0, 12, 15, new Dice("1d12+8", 1, 12, 8),"BloodOoze.png", "BloodOoze_Lost.png", "BloodOoze_Title.png"),
        new Monster("Boneship", 32, 415, 0, 42, 35, new Dice("3d10+17", 3, 10, 17),"Boneship.png", "Boneship_Lost.png", "Boneship_Title.png"),
        new Monster("Camel", 4, 20, 0, 15, 7, new Dice("1d6+4", 1, 6, 4),"Camel.png" , "Camel_Lost.png", "Camel_Title.png"),
        new Monster("Crocodile", 7, 30, 0, 18, 10, new Dice("1d10+4", 1, 10, 4),"Crocodile.png", "Crocodile_Lost.png", "Crocodile_Title.png"),
        new Monster("Giant Gecko", 7, 20, 0, 16, 8, new Dice("1d8+2", 1, 8, 2), "GiantGecko.png", "GiantGecko_Lost.png", "GiantGecko_Title.png"),
        new Monster("Giant Fly", 8, 20, 0, 17, 8, new Dice("1d6+3", 1, 6, 3),"GiantFly.png", "GiantFly_Lost.png", "GiantFly_Title.png"),
        new Monster("Gold Dragon", 36, 450, 0, 46, 38, new Dice("4d12+17", 4, 12, 17),"AncientGoldDragon.png", "AncientGoldDragon_Lost.png", "AncientGoldDragon_Title.png"),
        new Monster("Green Man", 42, 525, 0, 51, 46, new Dice("4d10+27", 4, 10, 27),"GreenMan.png", "GreenMan_Lost.png", "GreenMan_Title.png"),
        new Monster("Hydra", 17, 90, 15, 23, 16, new Dice("6d6+7", 6, 6, 7),"Hydra.png", "Hydra_Lost.png", "Hydra_Title.png"),
        new Monster("Red Dragon", 35, 425, 0, 45, 37, new Dice("4d10+17", 4, 10, 17),"RedDragon.png", "RedDragon_Lost.png", "RedDragon_Title.png"),
        new Monster("Rune Giant", 28, 330, 0, 38, 33, new Dice("3d12+17", 3, 12, 17),"RuneGiant.png", "RuneGiant_Lost.png", "RuneGiant_Title.png"),
        new Monster("Tarrasque", 48, 540, 50, 54, 45, new Dice("5d10+20", 5, 10, 20), "Tarrasque.png", "Tarrasque_Lost.png", "Tarrasque_Title.png"),
        new Monster("Tyrannosaurus", 29, 180, 0, 29, 22, new Dice("2d12+12", 2, 12, 12),"Tyrannosaurus.png", "Tyrannosaurus_Lost.png", "Tyrannosaurus_Title.png"),
        new Monster("Treerazer", 46, 550, 50, 54, 47, new Dice("4d12+15", 4, 12, 15),"Treerazor.png", "Treerazor_Lost.png", "Treerazer_Title.png")
        ); 
    static JLabel m1Name, m1Init, m1HP, m1Arm, m1Atk, m1Dmg, m1Image;
    static JLabel m2Name, m2Init, m2HP, m2Arm, m2Atk, m2Dmg, m2Image;
//    static JTextArea m1Name, m2Name;
    
    public static void ShowMonsterScreen(){
        //monster names for the combo box
        String[] monsterNames = new String[monsters.size()];
        for (int i=0; i<monsters.size();i++){
            monsterNames[i] = monsters.get(i).getName();
        }

        //Frame and panel declaration
        JFrame mainF = new JFrame("Pathfinder 2E: Monster Selection");
        JPanel mainP = new JPanel();
        mainP.setLayout(new FlowLayout());
        mainP.setSize(675,525);
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel,BoxLayout.PAGE_AXIS));
        JPanel innerSplit = new JPanel(new FlowLayout());
        innerSplit.setSize(675,525);
        JPanel topSplit = new JPanel(new FlowLayout());
        topSplit.setSize(1000,525);
        JPanel leftP = new JPanel();
        leftP.setLayout(new BoxLayout(leftP, BoxLayout.PAGE_AXIS));
        leftP.setSize(325, 500);
        leftP.setMaximumSize(new Dimension(325,500));
        JPanel centerP = new JPanel();
        centerP.setLayout(new BoxLayout(centerP, BoxLayout.PAGE_AXIS));
        centerP.setSize(325,500);
        JPanel rightP = new JPanel();
        rightP.setLayout(new BoxLayout(rightP, BoxLayout.PAGE_AXIS));
        rightP.setSize(325,500);
        JPanel buttons = new JPanel(new FlowLayout());
        
        //Setting frame properties
        mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainF.setResizable(false);
        mainF.setSize(1050,700);
        mainF.setLocationRelativeTo(null);
        mainF.setBackground(Color.lightGray);
        mainF.setVisible(true);
        
        // Image files
        ImageIcon m1ImageIcon = new ImageIcon(new ImageIcon(monsters.get(0).getPicture()).getImage().getScaledInstance(300, 300, 0));
        ImageIcon m2ImageIcon = new ImageIcon(new ImageIcon(monsters.get(1).getPicture()).getImage().getScaledInstance(300, 300, 0));
        
        //fonts used in the GUI
        Font headerFont = new Font("Verdana", Font.BOLD,40);
        Font detailFont = new Font("Verdana", Font.PLAIN, 20);
        
        m1Image = new JLabel(m1ImageIcon);
	m1Name = new JLabel();
        m1Name.setText(monsters.get(0).getName());
        m1Name.setFont(headerFont);
        m1Init = new JLabel(("Initiative: " + String.valueOf(monsters.get(0).getInitiative())));
        m1Init.setHorizontalAlignment(SwingConstants.CENTER);
        m1Init.setFont(detailFont);
        m1HP = new JLabel(("Hit Points: " + String.valueOf(monsters.get(0).getHitPoints())));
        m1HP.setFont(detailFont);
        m1Arm = new JLabel(("Armor Rating: " + String.valueOf(monsters.get(0).getArmorClass())));
        m1Arm.setFont(detailFont);
        m1Atk = new JLabel(("Attack: " + String.valueOf(monsters.get(0).getAttack())));
        m1Atk.setFont(detailFont);
        m1Dmg = new JLabel(("Damage Roll: " + String.valueOf(monsters.get(0).getDamage().getName())));
        m1Dmg.setFont(detailFont);
        JComboBox m1Selector = new JComboBox(monsterNames);
        m1Selector.setSelectedIndex(0);

        m2Image = new JLabel(m2ImageIcon);
	m2Name = new JLabel();
        m2Name.setText(monsters.get(1).getName());
        m2Name.setFont(headerFont);
        m2Init = new JLabel(("Initiative: " + String.valueOf(monsters.get(1).getInitiative())));
        m2Init.setFont(detailFont);
        m2HP = new JLabel(("Hit Points: " + String.valueOf(monsters.get(1).getHitPoints())));
        m2HP.setFont(detailFont);
        m2Arm = new JLabel(("Armor Rating: " + String.valueOf(monsters.get(1).getArmorClass())));
        m2Arm.setFont(detailFont);
        m2Atk = new JLabel(("Attack: " + String.valueOf(monsters.get(1).getAttack())));
        m2Atk.setFont(detailFont);
        m2Dmg = new JLabel(("Damage Roll: " + String.valueOf(monsters.get(1).getDamage().getName())));
        m2Dmg.setFont(detailFont);
        JComboBox m2Selector = new JComboBox(monsterNames);
        m2Selector.setSelectedIndex(1);

        JButton battle = new JButton("Battle!");
        JButton exit = new JButton("Exit");
        
        //Adding components to panels
        leftP.add(m1Image);
        leftP.add(m1Name);
        leftP.add(m1Init);
        leftP.add(m1HP);
        leftP.add(m1Arm);
        leftP.add(m1Atk);
        leftP.add(m1Dmg);
        leftP.add(m1Selector);
        
        rightP.add(m2Image);
        rightP.add(m2Name);
        rightP.add(m2Init);
        rightP.add(m2HP);
        rightP.add(m2Arm);
        rightP.add(m2Atk);
        rightP.add(m2Dmg);
        rightP.add(m2Selector);
        
        buttons.add(battle);
        buttons.add(exit);
        innerSplit.add(leftP);
        topSplit.add(innerSplit);
        topSplit.add(rightP);
        subPanel.add(topSplit);
        subPanel.add(buttons);
        mainF.getContentPane().add(subPanel);
        
        //Setting action listeners
        exit.addActionListener(e->{
        	System.exit(0);
        });
        
        //open the BattleSimulator interface and send the selected monsters to battle
        battle.addActionListener(e->{
            int m1Index = 0; //holder for the 1st monster selected
            int m2Index = 0; //holder for the 2nd monster selected
            for(int i = 0; i < monsters.size(); i ++){//scan monsters ArrayList for the selected monsters
                if(monsters.get(i).getName().equals(m1Name.getText())){
                    m1Index = i;
                }
                if(monsters.get(i).getName().equals(m2Name.getText())){
                    m2Index = i;
                }
            }
            
            //start a new battle with the selected monsters
            BattleSimulator sim = new BattleSimulator();
            sim.LaunchSimulator(monsters.get(m1Index), monsters.get(m2Index));
            mainF.dispose();
        });
        
        //when the selector values changes update m2Selector to prevent duplicate monsters
        m1Selector.addActionListener(e->{
            List<String> currentItems = new ArrayList<String>();
            //build/update both selectors with monster names
            for(int i = 0; i < m2Selector.getItemCount(); i++){
                currentItems.add(m2Selector.getItemAt(i).toString());
            }
            for(int i = 0; i < monsterNames.length; i ++){
                if(monsterNames[i] != m1Selector.getSelectedItem().toString() && !currentItems.contains(monsterNames[i])){
                    m2Selector.insertItemAt(monsterNames[i], i);
                }
            }
            m2Selector.removeItem(m1Selector.getSelectedItem()); //remove monster 1 from selector 2
            setM1Data(m1Selector.getSelectedItem().toString()); //update GUI with selected monster info
            
        });
        
        //when the selector values changes update m1Selector to prevent duplicate monsters
        m2Selector.addActionListener(e->{
            List<String> currentItems = new ArrayList<String>();
            //build/update both selectors with monster names
            for(int i = 0; i < m1Selector.getItemCount(); i++){
                currentItems.add(m1Selector.getItemAt(i).toString());
            }
            for(int i = 0; i < monsterNames.length; i ++){
                if(monsterNames[i] != m2Selector.getSelectedItem().toString() && !currentItems.contains(monsterNames[i])){
                    m1Selector.insertItemAt(monsterNames[i], i);
                }
            }
            m1Selector.removeItem(m2Selector.getSelectedItem());//remove monster 2 from selector 1
            setM2Data(m2Selector.getSelectedItem().toString());//update GUI with selected monster info
        });
    }
    
    //sets monster1 (left side) data in the GUI
    private static void setM1Data(String selection) {
        int thisIndex = 0; //index of selected monster
        for(int i = 0; i < monsters.size(); i ++){
            if(monsters.get(i).getName().equals(selection)){
                thisIndex = i; //lookup monster information form the monsters ArrayList
            }
        }
        ImageIcon tempIcon = new ImageIcon(new ImageIcon(monsters.get(thisIndex).getPicture()).getImage().getScaledInstance(300, 300, 0));
        m1Image.setIcon(tempIcon);
        m1Name.setText((monsters.get((thisIndex)).getName()));
        m1Init.setText(("Initiative: " + String.valueOf(monsters.get(thisIndex).getInitiative())));
        m1HP.setText(("Hit Points: " + String.valueOf(monsters.get(thisIndex).getHitPoints())));
        m1Arm.setText(("Armor Rating: " + String.valueOf(monsters.get(thisIndex).getArmorClass())));
        m1Atk.setText(("Attack: " + String.valueOf(monsters.get(thisIndex).getAttack())));
        m1Dmg.setText(("Damage Roll: " + String.valueOf(monsters.get(thisIndex).getDamage().getName())));
    }

    //sets monster2 (right side) data in the GUI
    private static void setM2Data(String selection) {
        int thisIndex = 0; //index of selected monster
        for(int i = 0; i < monsters.size(); i ++){
            if(monsters.get(i).getName().equals(selection)){
                thisIndex = i; //lookup monster information form the monsters ArrayList
            }
        }
        ImageIcon tempIcon = new ImageIcon(new ImageIcon(monsters.get(thisIndex).getPicture()).getImage().getScaledInstance(300, 300, 0));
        m2Image.setIcon(tempIcon);
        m2Name.setText((monsters.get((thisIndex)).getName()));
        m2Init.setText(("Initiative: " + String.valueOf(monsters.get(thisIndex).getInitiative())));
        m2HP.setText(("Hit Points: " + String.valueOf(monsters.get(thisIndex).getHitPoints())));
        m2Arm.setText(("Armor Rating: " + String.valueOf(monsters.get(thisIndex).getArmorClass())));
        m2Atk.setText(("Attack: " + String.valueOf(monsters.get(thisIndex).getAttack())));
        m2Dmg.setText(("Damage Roll: " + String.valueOf(monsters.get(thisIndex).getDamage().getName())));
    }
}
