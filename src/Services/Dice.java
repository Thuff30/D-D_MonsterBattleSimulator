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


public class Dice {
    private String name; //name of dice (i.e. 1d20)
    private int rolls; //number of times a dice is rolled
    private int sides;  //# of sides of the dice
    private int mod;    //modifier to dice roll result
    
    //Constructor for dice with a given amount of sides and NO modifier
    Dice(String name, int sides){
    	setName(name);
        setRolls(1);
        setSides(sides);
        setModifier(0);
    }
    
    //Constructor for dice with a given amount of sides and a modifier
    public Dice(String name, int sides, int mod) {
        setName(name);
        setRolls(1);
        setSides(sides);
        setModifier(mod);
    }
    
    public Dice(String name, int rolls, int sides, int mod) {
        setName(name);
        setRolls(rolls);
        setSides(sides);
        setModifier(mod);
    }
    
    //set # of dice sides
    private void setName(String name) {this.name = name;}
    private void setRolls(int rolls) {this.rolls = rolls;}
    private void setSides(int sides) {this.sides = sides;}
    private void setModifier(int mod) {this.mod = mod;}
    
    //get # of dice sides
    public String getName() {return this.name;}
    public int getRolls() {return this.rolls;}
    public int getSides() {return this.sides;}
    public int getModifier() {return this.mod;}
    
    //roll the dice a given number of times
    public int roll(int mod) {
        int[] multiRoll = new int[this.rolls];
        int rslt = 0;
        
        for (int i = 0; i <this.rolls;i++) {
        	multiRoll[i] = ((int)(Math.random()*this.sides+1));
            if (multiRoll[i]< 1) {
            	multiRoll[i]=0;
            } else {
            	rslt+=multiRoll[i];
            }
        }
        rslt+= mod; //add modifier to the final roll total
        return rslt;
    }
}