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

import Services.Dice;
import Services.Monster;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.Border;


public class BattleSimulator {
    static final int SHORT_WAIT = 100; //used when displaying output to make it readable
    static final int NUMBER_OF_ATTACKS = 3; //number of attack each monster makes per round
    JFrame mainF = new JFrame("Pathfinder 2E: Battle Simulation");
    JTextArea output = new JTextArea(625,300); //display box for battle outputs
    JLabel m1HP, m2HP; //JLabels for Hip Points to dynically update
    JButton battle, select, exit; //buttons that need to be disabled while monsters are fighting
    JPanel battleAnnounce = (JPanel)mainF.getGlassPane();
    JPanel battleAnnounce1 = new JPanel();
    JPanel battleAnnounce2 = new JPanel();
    JPanel battleAnnounce3 = new JPanel();
    JPanel battleBegin = new JPanel();
    JPanel battleResult = new JPanel();
    Font detailFont = new Font("Verdana", Font.PLAIN, 20);
    Font changeFont = new Font("Verdana", Font.ITALIC, 20);
    Font deadFont = new Font("Verdana", Font.BOLD, 20);
    Font endFont = new Font("Verdana", Font.BOLD, 48);
    Border glassBorder = BorderFactory.createLineBorder(new Color(253, 247, 92), 20);

    public void LaunchSimulator(Monster m1, Monster m2){
        //Frame and panel declaration
        JPanel mainP = new JPanel();
        mainP.setLayout(new BoxLayout(mainP,BoxLayout.PAGE_AXIS));
        JPanel topSplit = new JPanel(new FlowLayout());
        topSplit.setSize(675,525);
        JPanel leftP = new JPanel();
        leftP.setLayout(new BoxLayout(leftP, BoxLayout.PAGE_AXIS));
        leftP.setSize(325, 500);
        JPanel rightP = new JPanel();
        rightP.setLayout(new BoxLayout(rightP, BoxLayout.PAGE_AXIS));
        rightP.setSize(325,500);
        JPanel buttons = new JPanel(new FlowLayout());
        battleAnnounce.setLayout(new FlowLayout());
        battleResult.setLayout(new BoxLayout(battleResult, BoxLayout.PAGE_AXIS));

        

        
        //Setting frame properties
        mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainF.setSize(1300,800);
        mainF.setResizable(false);
        mainF.setLocationRelativeTo(null);
        mainF.setBackground(Color.lightGray);
        mainF.setVisible(true);
        battleAnnounce.setOpaque(false);
        battleAnnounce1.setOpaque(false);
        battleAnnounce2.setOpaque(false);
        battleAnnounce3.setOpaque(false);
        battleBegin.setOpaque(false);
        battleResult.setOpaque(false);
        battleAnnounce.setVisible(false);
        battleAnnounce.setBackground(new Color(86, 8 ,8));
        battleAnnounce1.setVisible(false);
        battleAnnounce2.setVisible(false);
        battleAnnounce3.setVisible(false);
        battleBegin.setVisible(false);
        battleResult.setVisible(false);
        
        //fonts used in the GUI
        Font headerFont = new Font("Verdana", Font.BOLD,40);
        Font midFont = new Font("Verdana", Font.BOLD, 50);

        //GUI components
        JLabel m1Image = new JLabel(new ImageIcon(new ImageIcon(m1.getPicture()).getImage().getScaledInstance(300, 300, 0)));
	JLabel m1Name = new JLabel(m1.getName());
        m1Name.setFont(headerFont);
        JLabel m1Init = new JLabel(("Initiative: " + String.valueOf(m1.getInitiative())));
        m1Init.setFont(detailFont);
        m1HP = new JLabel(("Hit Points: " + String.valueOf(m1.getHitPoints())));
        m1HP.setFont(detailFont);
        JLabel m1Arm = new JLabel(("Armor Rating: " + String.valueOf(m1.getArmorClass())));
        m1Arm.setFont(detailFont);
        JLabel m1Atk = new JLabel(("Attack: " + String.valueOf(m1.getAttack())));
        m1Atk.setFont(detailFont);
        JLabel m1Dmg = new JLabel(("Damage Roll: " + String.valueOf(m1.getDamage().getName())));
        m1Dmg.setFont(detailFont);
        JLabel m1Title = new JLabel(new ImageIcon(new ImageIcon(m1.getTitlePicture()).getImage().getScaledInstance(700, 500, 0)));
        JLabel vs = new JLabel("VS");
        JLabel VS = new JLabel(new ImageIcon(new ImageIcon("VS_Title.png").getImage().getScaledInstance(500,500,0)));
        vs.setFont(midFont);
        JLabel m2Image = new JLabel(new ImageIcon(new ImageIcon(m2.getPicture()).getImage().getScaledInstance(300, 300, 0)));
	JLabel m2Name = new JLabel(m2.getName());
        m2Name.setFont(headerFont);
        JLabel m2Init = new JLabel(("Initiative: " + String.valueOf(m2.getInitiative())));
        m2Init.setFont(detailFont);
        m2HP = new JLabel(("Hit Points: " + String.valueOf(m2.getHitPoints())));
        m2HP.setFont(detailFont);
        JLabel m2Arm = new JLabel(("Armor Rating: " + String.valueOf(m2.getArmorClass())));
        m2Arm.setFont(detailFont);
        JLabel m2Atk = new JLabel(("Attack: " + String.valueOf(m2.getAttack())));
        m2Atk.setFont(detailFont);
        JLabel m2Dmg = new JLabel(("Damage Roll: " + String.valueOf(m2.getDamage().getName())));
        m2Dmg.setFont(detailFont);
        JLabel m2Title = new JLabel(new ImageIcon(new ImageIcon(m2.getTitlePicture()).getImage().getScaledInstance(700, 500, 0)));
        JLabel fight = new JLabel(new ImageIcon(new ImageIcon("Fight.png").getImage().getScaledInstance(700,500,0)));
        output.setEditable(false);
        output.setFont(output.getFont().deriveFont(17f));
        JScrollPane outputPane = new JScrollPane(output);
        outputPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outputPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        battle = new JButton("Battle!");
        select = new JButton("Monster Selector");
        exit = new JButton("Exit");
        
        //Adding components to their panels
        battleAnnounce1.add(m1Title);
        battleAnnounce2.add(VS);
        battleAnnounce3.add(m2Title);
        battleBegin.add(fight);
        battleAnnounce.add(battleAnnounce1);
        battleAnnounce.add(battleAnnounce2);
        battleAnnounce.add(battleAnnounce3);
        battleAnnounce.add(battleBegin);
        battleAnnounce.add(battleResult);
        
        leftP.add(m1Image);
        leftP.add(m1Name);
        leftP.add(m1Init);
        leftP.add(m1HP);
        leftP.add(m1Arm);
        leftP.add(m1Atk);
        leftP.add(m1Dmg);

        rightP.add(m2Image);
        rightP.add(m2Name);
        rightP.add(m2Init);
        rightP.add(m2HP);
        rightP.add(m2Arm);
        rightP.add(m2Atk);
        rightP.add(m2Dmg);
        
        buttons.add(battle);
        buttons.add(select);
        buttons.add(exit);
        
        topSplit.add(leftP);
        topSplit.add(vs);
        topSplit.add(rightP);
        
        mainP.add(topSplit);
        mainP.add(outputPane);
        mainP.add(buttons);
        mainF.getContentPane().add(mainP);
        
        //Setting action listeners        
        class BattleThread implements Runnable {
            //thread that allows JTextArea output to display sequentially
            @Override
            public void run() {
            	//disable buttons until thread is complete
            	battle.setEnabled(false);
            	select.setEnabled(false);
                
            	battleMonsters(m1, m2); //battle the selected monsters
                
                //change monster picture to reflect the loser of the battle
                if(output.getText().contains(m1.getName() + " has fainted!")){
                    m1Image.setIcon(new ImageIcon(new ImageIcon(m1.getLostPicture()).getImage().getScaledInstance(300, 300, 0)));
                    waitTime(1000);
                    result(m2);
                }else if(output.getText().contains(m2.getName() + " has fainted!")){
                    m2Image.setIcon(new ImageIcon(new ImageIcon(m2.getLostPicture()).getImage().getScaledInstance(300, 300, 0)));
                    waitTime(1000);
                    result(m1);
                }
                
            	//reenable buttons after thread completes
            	battle.setEnabled(true);
            	select.setEnabled(true);
            }
        }
        battle.addActionListener(e->{
            output.setText(""); //clear battle output

            //reset monster HP and picture in the GUI
            m1HP.setText("Hit Points: " + String.valueOf(m1.getHitPoints()));
            m1HP.setFont(detailFont);
            m1HP.setForeground(Color.BLACK);
            m2HP.setText("Hit Points: " + String.valueOf(m2.getHitPoints()));
            m2HP.setFont(detailFont);
            m2HP.setForeground(Color.BLACK);
            m1Image.setIcon(new ImageIcon(new ImageIcon(m1.getPicture()).getImage().getScaledInstance(300, 300, 0)));
            m2Image.setIcon(new ImageIcon(new ImageIcon(m2.getPicture()).getImage().getScaledInstance(300, 300, 0)));
            
            //create new thread for battle output
            Runnable runnable = new BattleThread(); 
            Thread thread = new Thread(runnable); 
            thread.start();//execute battle thread separate from the swing action event thread
        });

        //return to monster selection
        select.addActionListener(e->{
            MonsterMenu.ShowMonsterScreen();
            mainF.dispose();
        });
        
        //exit to program
        exit.addActionListener(e->{
        	System.exit(0);
        });
    }

    private void battleMonsters(Monster m1, Monster m2) {
        int m1StartHP = m1.getHitPoints(); //save the origional monster hit point value
        int m2StartHP = m2.getHitPoints(); //save the origional monster hit point value
    	int roundCount = 1; //battle round counter
        
        announcement();
        
    	writeOutput("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    	writeOutput("\t\t\t\t" + m1.getName() +  "   vs   " +  m2.getName() + "\n");
    	writeOutput("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
 
    	waitTime(SHORT_WAIT);
    	writeOutput("\n");
    	writeOutput("Determining first attack:\n");
    	waitTime(SHORT_WAIT);
    	writeOutput("\n");
    	waitTime(SHORT_WAIT);
    	
    	Boolean m1FirstAttack = whoGoesFirst(m1, m2);
        waitTime(SHORT_WAIT);

        writeOutput("\n");
        writeOutput("Ready........FIGHT!\n");
        waitTime(SHORT_WAIT);
        writeOutput("\n");

        if (m1FirstAttack) { //monster 1 attacks first
            do {
                writeOutput("~~~ Round " + roundCount + "~~~\n");
                if (m1.getHitPoints() > 0 && m2.getHitPoints() > 0) {//regen if the monsters are alive
                    checkRegen(m1, m1StartHP, m1HP);
                }
                checkAttack(m1, m2, m2HP); 
                writeOutput("\n"); //space between monster attacks
                if (m1.getHitPoints() > 0 && m2.getHitPoints() > 0) {//regen if the monsters are alive
                    checkRegen(m2, m2StartHP, m2HP);  
                }
                checkAttack(m2, m1, m1HP);
                roundCount++;
                writeOutput("\n");
            } while (m1.getHitPoints() >= 0 && m2.getHitPoints() >= 0); //end the battle when one monster has <0 HP
        } else {
            do {
            	writeOutput("~~~ Round " + roundCount + "~~~\n");
                if (m1.getHitPoints() > 0 && m2.getHitPoints() > 0) {//regen if the monsters are alive
                    checkRegen(m2, m2StartHP, m2HP);  
                }
                checkAttack(m2, m1, m1HP);
                writeOutput("\n"); //space between monster attacks
                if (m1.getHitPoints() > 0 && m2.getHitPoints() > 0) {//regen if the monsters are alive
                    checkRegen(m1, m1StartHP, m1HP);
                }
                checkAttack(m1, m2, m2HP);
                roundCount++;
                writeOutput("\n");
            } while (m1.getHitPoints() >= 0 && m2.getHitPoints() >= 0); //end the battle when one monster has <0 HP
        }
        
        //reset monsters back to their origional value
        m1.setHitPoints(m1StartHP);
        m2.setHitPoints(m2StartHP);
    }
    
    //GUI output processing for monster attacks
    private void checkAttack(Monster atkM, Monster defM, JLabel mHP) {
        ArrayList<Integer> atkDMG = new ArrayList<>(2); //stores damage info (isCrit? and dmg value)   
        boolean isCrit = false; //was the attack a crit?
        
        //forces the monster to attack a set number of times
        for (int atkCount = 1; atkCount <= NUMBER_OF_ATTACKS; atkCount++) { 
            //perform attacks with a diminishing attack attribute by 5 each round
            atkDMG = atkM.attack(defM, (atkCount-1)*5);
            
            for (int i = 0; i < atkDMG.size(); i++) { //read the damage results from the attack
                if (i % 2 == 0) { //check the even damage slots for a crit (Crit  = 1)
                    if (atkDMG.get(i) == 1) {
                        isCrit = true;
                    } else if (atkDMG.get(i) == 0){
                        isCrit = false;
                    }
                } else if (i % 2 == 1) { //check the odd damage slots
                    if (atkDMG.get(i) > 0 & isCrit) { //dagame greater than 0 is a crit
                        writeOutput(atkM.getName() + " scores a CRITICAL HIT! ");
                        writeOutput(atkM.getName() + " rolls a " + atkM.getDamage().getName() + 
                                ". The " + defM.getName() + " takes " + atkDMG.get(i) + " damage!\n");
                    } else if (atkDMG.get(i) > 0 & !isCrit) { //it dmg >= 0 then its a miss
                        writeOutput(atkM.getName() + " rolls a " + atkM.getDamage().getName() + 
                                ". The " + defM.getName() + " takes " + atkDMG.get(i) + " damage!\n");  
                    } else if (atkDMG.get(i) <= 0) { //it dmg >= 0 then its a miss
                        writeOutput(atkM.getName() + "'s attack misses! The " + defM.getName() + " laughs! \n");
                        waitTime(SHORT_WAIT); //wait to prevent the battle output from jumping
                    }

                    //updates the defending monsters HP in the GUI
                    if (atkDMG.get(i) > 0) {
                        mHP.setForeground(Color.RED); //indicate monster damage
                        mHP.setFont(changeFont);
                        mHP.setText("Hit Points: " + String.valueOf((defM.getHitPoints()+atkDMG.get(i)) + " - " + atkDMG.get(i)));
                        waitTime(SHORT_WAIT*3); //longer wait to show GUI update
//                        mHP.setForeground(Color.BLACK); //return to default color
                        updateHPFont(defM, mHP);
                    }
                    if (checkDeath(atkM, defM)) {
                        mHP.setFont(deadFont);
                        atkCount = 999; //forces the for loop to end early
                    }
                }
            } //end crit and damage check for loop
        } //end multiple attack for loop
    }
    
    //GUI output processing for monster regen
    private void checkRegen(Monster m, int maxHP, JLabel mHP) {
        int hpRegen = m.hpRegen(maxHP); //calculate monsters regen value
        
        if (hpRegen > 0) { //regen value is greater than 0
            writeOutput(m.getName() + " recovers " + hpRegen + " hitpoints!\n");
            mHP.setForeground(new Color(0, 210, 53)); //indicate monster regen (dark green)
            mHP.setFont(changeFont);
            mHP.setText("Hit Points: " + String.valueOf((m.getHitPoints()-hpRegen) + " + " + hpRegen));
            waitTime(SHORT_WAIT*3); //longer wait to show GUI update
            mHP.setText("Hit Points: " + String.valueOf(m.getHitPoints()));
            updateHPFont(m, mHP);
        }
    }
    
    private void updateHPFont(Monster m, JLabel mHP){
        float percentHealth = (m.getHitPoints() * 100)/m.getBaseHP();
        mHP.setText("Hit Points: " + String.valueOf(m.getHitPoints()));
        if(percentHealth > 66){
            mHP.setForeground(Color.BLACK);
            mHP.setFont(detailFont);
        }
        else if(percentHealth <= 66 && percentHealth > 33){
            mHP.setForeground(Color.ORANGE);
            mHP.setFont(detailFont);
        }else if(percentHealth <= 33){
            mHP.setForeground(Color.RED);
            mHP.setFont(deadFont);
        }
    }
    
    //calculate which monster attacks first
    private boolean whoGoesFirst(Monster m1, Monster m2) {
        Dice d20 = new Dice("1d20+0",1, 20, 0); // 20 sided dice for initiative calculation
    	Boolean m1FirstAttack = false;
        int m1Roll, m2Roll; //roll results for each monster
        
        do {
            m1Roll = d20.roll(m1.getInitiative()); //1d20 + monster initiative
            m2Roll = d20.roll(m2.getInitiative()); //1d20 + monster initiative

            writeOutput(m1.getName() + " rolls a " + m1Roll + "\n");
            waitTime(SHORT_WAIT);
            writeOutput(m2.getName() + " rolls a " + m2Roll + "\n");
            writeOutput("\n"); //blank line
            waitTime(SHORT_WAIT);
            
            if (m1Roll == m2Roll) { //announce a tied roll
                writeOutput("Its a TIE!!! Roll again!");
                writeOutput("\n"); //blank line
            } else if (m1Roll > m2Roll) { //monster 1 wins
                writeOutput(m1.getName() + " attacks first!\n");
        	m1FirstAttack = true;
            } else { //monster 2 wins
        	writeOutput(m2.getName() + " attacks first!\n");
            }
        } while(m1Roll == m2Roll);

        return m1FirstAttack; //false if monster 2 wins
    }
    
    //checks to see if the defending monster's HP < 0
    private boolean checkDeath(Monster atkM, Monster defM) {
        Boolean isDead = false; //default value
        
    	if (defM.getHitPoints() < 0) { //if the defender is dead announce the winner
            writeOutput("\n");
            writeOutput(defM.getName() + " has fainted!\n");
            waitTime(SHORT_WAIT);
            writeOutput(atkM.getName() + " is victorious!");
            waitTime(SHORT_WAIT);
            
            isDead = true; //defender is dead
    	} 
        
        return isDead; //false if the defender survived the attack
    }
    
    //add messages to the output window
    private void writeOutput(String o) {
    	output.append(o);
    	output.setCaretPosition(output.getText().length() - 1);//forces JTextArea to scroll with text
    }
    
    private void waitTime(int wt) {
    	try {
            Thread.sleep(wt); //delat the output so it is readable
        } catch (InterruptedException e) {
            //do nothing on error
        }
    }
    
    private void announcement(){
        battleAnnounce.setVisible(true);
        battleAnnounce1.setVisible(true);
        waitTime(1200);
        battleAnnounce1.setVisible(false);
        battleAnnounce2.setVisible(true);
        waitTime(1200);
        battleAnnounce2.setVisible(false);
        battleAnnounce3.setVisible(true);
        waitTime(1200);
        battleAnnounce3.setVisible(false);
        battleBegin.setVisible(true);
        waitTime(1000);
        battleBegin.setVisible(false);
        battleAnnounce.setVisible(false);
    }
    
    private void result(Monster winner){
        battleResult.removeAll();
        JLabel winnerImage = new JLabel(new ImageIcon(new ImageIcon(winner.getPicture()).getImage().getScaledInstance(500, 500, 0)));
        JLabel winnerName = new JLabel(winner.getName());
        JLabel resultText = new JLabel("HAS WON!!");
        winnerName.setFont(endFont);
        winnerName.setForeground(new Color(253, 247, 92));
        winnerName.setHorizontalAlignment(JLabel.CENTER);
        winnerName.setHorizontalTextPosition(CENTER);
        resultText.setFont(endFont);
        resultText.setForeground(new Color(253, 247, 92));
        resultText.setHorizontalAlignment(JLabel.CENTER);
        resultText.setHorizontalTextPosition(CENTER);
        battleResult.add(winnerImage);
        battleResult.add(winnerName);
        battleResult.add(resultText);
        battleAnnounce.setBorder(glassBorder);
        battleAnnounce.setVisible(true);
        battleAnnounce.setOpaque(true);
        battleResult.setVisible(true);
        waitTime(2400);
        battleResult.setVisible(false);
        battleAnnounce.setOpaque(false);
        battleAnnounce.setVisible(false);
        battleAnnounce.setBorder(null);
    }
}