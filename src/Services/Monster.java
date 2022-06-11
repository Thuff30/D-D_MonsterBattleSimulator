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
package Services;

import java.util.ArrayList;
import java.util.List;

public class Monster {
    private String name;
    private int init;
    private int hp;
    private int regen;
    private int arm;
    private int atk;
    private Dice dmg;
    private String pic;
    private String lostPic;
    private String titePic;
    private int baseHP;
    
    public Monster (String name, int init, int hp, int regen, int arm, int atk, Dice dmg, String pic, String lostPic, String titlePic){
    	setName(name);
    	setInitiative(init);
    	setHitPoints(hp);
        setRegen(regen);
    	setArmorClass(arm);
    	setAttack(atk);
    	setDamage(dmg);
    	setPicture(pic);
        setLostPicture(lostPic);
        setTitlePicture(titlePic);
        setBaseHP(hp);
    }

    private void setName(String name){this.name = name;}
    private void setInitiative(int init){this.init = init;}
    public void setHitPoints(int hp){this.hp = hp;}
    private void setRegen(int regen){this.regen = regen;}
    private void setArmorClass(int arm){this.arm = arm;}
    private void setAttack(int atk){this.atk = atk;}
    private void setDamage(Dice dmg){this.dmg = dmg;}
    private void setPicture(String pic) {this.pic = pic;}
    private void setLostPicture(String lostPic) {this.lostPic = lostPic;}
    private void setTitlePicture(String titlePic) {this.titePic = titlePic;}
    private void setBaseHP(int hp){this.baseHP = hp;}
    
    public String getName(){return this.name;}
    public int getInitiative(){return this.init;}
    public int getHitPoints(){return this.hp;}
    public int getRegen(){return this.regen;}
    public int getArmorClass(){return this.arm;}
    public int getAttack(){return this.atk;}
    public Dice getDamage(){return this.dmg;}
    public String getPicture() {return this.pic;}
    public String getLostPicture() {return this.lostPic;}
    public String getTitlePicture() {return this.titePic;}
    public int getBaseHP(){return this.baseHP;}
    
    //execute monster attack
    //atkDiminish reduces attack efficiency with each subsiquent attack
    public ArrayList<Integer> attack(Monster defM, int atkDiminish) {
        Dice atkCheck = new Dice("1d20+0",1, 20, 0);  //1d20 used to perfrom an attack roll
        ArrayList<Integer> atkDMG = new ArrayList<>(2); //results of the attack (isCrit? and damage value)
        
        int dmgIndex = 0; //index counter for dmg ArrayList
        
        int roll = atkCheck.roll(this.getAttack() - atkDiminish); //attack roll with diminished attack value

        if (this.getHitPoints() >= 0) {  //make sure monster is not dead
            if (roll >= defM.getArmorClass()) { //check that attack roll >= defending monsters armor value
                if (roll > defM.getArmorClass()+10) { //check for crit hit if attack role is > Armor +10
                    atkDMG.add(dmgIndex, 1); //crit hit = 1
                    dmgIndex++; //move to next index
                    atkDMG.add(dmgIndex, this.getDamage().roll(this.getDamage().getModifier())*2); //no damage
                    defM.setHitPoints(defM.getHitPoints() - atkDMG.get(dmgIndex)); //update defending monster HP
                    dmgIndex++; //move to next index
                } else { //normal hit
                    atkDMG.add(dmgIndex, 0); //not a crit hit = 0
                    dmgIndex++; //move to next index
                    atkDMG.add(dmgIndex, this.getDamage().roll(this.getDamage().getModifier())); //no damage
                    defM.setHitPoints(defM.getHitPoints() - atkDMG.get(dmgIndex)); //update defending monster HP
                    dmgIndex++; //move to next index
                }
            } else { //attack misses, no damage
                atkDMG.add(dmgIndex, 0); //not a crit hit = 0
                dmgIndex++; //move to next index
                atkDMG.add(dmgIndex, 0); //no damage
                dmgIndex++; //move to next index
            }
        }

        return atkDMG; //atkDMG[0]-> 1=Crit 0= normal atkDMG[1]-> damage value
    }
    
    //calculate regen value
    public int hpRegen(int maxHP) {
        int rslt = 0; //default regen amount 
        
        if (this.regen > 0 && this.hp < maxHP) { //monster has a regen value and is not at max hp   
            if (this.hp+this.regen > maxHP) {
                this.hp = maxHP; //doesnt let a monster heal past max HP
                rslt = this.hp+this.regen - maxHP; //actual ammount healed
            } else {
                this.hp = this.hp + this.regen; //regen HP 
                rslt = this.regen; //actual ammount healed
            }
        }
        
        return rslt; //actual ammount healed
    }
}