/*
 * Copyright (C) 2018 Ryan Castelli
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package MobGeneration;

import java.util.Scanner;
import java.util.Random;

/**
 * Class to generate waves of Mobs, meant only for terminal
 *
 * @author Ryan Castelli
 * @version 1.0
 */
public class WaveConstructor {

    /**
     * GUI and array generation
     *
     * @param args
     */
    public static void main(String args[]) {
        System.out.println("Welcome to the Mob Wave Stat Generator!");

        //creates variables for user input in GUIs
        int mainChoice;
        int subChoice;
        int subChoice2;
        int subChoice3;

        //creates number generator
        Random numGenerator = new Random();

        //creates keyboard input reader
        Scanner kbReader = new Scanner(System.in);

        //gets army size
        System.out.print("\nPlease enter wave size, or 0 for a random size less than 10000: ");
        int waveSize = kbReader.nextInt();
        if (waveSize == 0) {
            waveSize = numGenerator.nextInt(10000);
        }

        //wave array generator, creates an array of mobs matching wave size
        Mobs theWave[] = new Mobs[waveSize];

        //establishes while
        do {
            //main gui

            System.out.println("1) Generate wave of default mobs");
            System.out.println("2) Generate wave of armed mobs");
            System.out.println("3) Generate wave of magic mobs");
            System.out.println("4) Generate wave of mixed mobs");
            System.out.println("5) Select new wave size");
            System.out.println("0) Exit");

            //user gui prompt
            System.out.print("\nYour selection?: ");

            //user selection
            mainChoice = kbReader.nextInt();

            //main switch, uses user input from GUI
            switch (mainChoice) {
                case 1:
                    //wave generator for default mobs, no special properties
                    //for loop to navigate array and create mobs
                    for (int basicMob = 0; basicMob < theWave.length; basicMob++) {
                        int hitPoints = 10 + numGenerator.nextInt(21); //random int for HP
                        int level = 1 + numGenerator.nextInt(5); //random int for Lvl
                        theWave[basicMob] = new Mobs(hitPoints, level); //creates mobs
                        theWave[basicMob].setColorAuto(); //creates a random color
                        theWave[basicMob].setAP(1 + numGenerator.nextInt(15)); //sets AP from random number generator
                        theWave[basicMob].setNumber(basicMob + 1); //sets wave number

                        //sets percentage of mobs that will have 4 legs
                        int randomLegs = numGenerator.nextInt(15);
                        if (randomLegs > 10) //uses random number to determine leg number
                        {
                            theWave[basicMob].setLegs(true);
                        }

                        //sets percentage of mobs that will have 4 arms
                        int randomArms = numGenerator.nextInt(25);
                        if (randomArms > 20) //uses random number to determine arm number
                        {
                            theWave[basicMob].setArms(true);
                        }

                        //sets percentage of mobs that will have hair
                        int randomHair = numGenerator.nextInt(10);
                        if (randomHair >= 5) //uses random number to set hair
                        {
                            theWave[basicMob].setHair(true);
                        }
                    }
                    System.out.println("Done!");

                    //sub do while, loops secondary GUI
                    do {
                        //variables needed for totals
                        int totalDmg = 0;
                        int totalHP = 0;

                        //sub gui 1
                        System.out.println("");
                        System.out.println("1) Retrieve All Set Values");
                        System.out.println("2) Set HP for Wave");
                        System.out.println("3) Set Lvl for Wave - Will increase AP and HP accordingly");
                        System.out.println("4) Specify Mob Name");
                        System.out.println("5) Specify Color in Hex Code");
                        System.out.println("6) Set AP for Wave");
                        System.out.println("7) Retrieve Total Damage for Wave");
                        System.out.println("8) Retrieve Total HP for Wave");
                        System.out.println("0) Exit - WARNING: CLEARS CURRENT WAVE");

                        System.out.print("\nYour selection?: "); //user GUI prompt

                        //user selection
                        subChoice = kbReader.nextInt();

                        //sub switch 1, secondary GUI based on basic mobs
                        switch (subChoice) {
                            case 1:
                                //loops to retrieve values
                                for (int basicMob = 0; basicMob < theWave.length; basicMob++) {
                                    System.out.println("");
                                    System.out.println("Wave Number: " + (basicMob + 1)); //adds 1 so range is 0-wavesize
                                    System.out.println("Name: " + theWave[basicMob]); //uses position in array as name
                                    System.out.println("Lvl: " + theWave[basicMob].getLvl()); //gets Lvl set at creation
                                    System.out.println("HP: " + theWave[basicMob].getHP()); //gets HP set at creation
                                    System.out.println("AP: " + theWave[basicMob].getAP()); //gets AP set at creation
                                    System.out.println("Color: " + theWave[basicMob].getColor()); //gets Color set at creation

                                    //prints existance of 4 arms based on random value set at creation
                                    if (theWave[basicMob].getArms() == true) {
                                        System.out.println("Arms: 4");
                                    } else {
                                        System.out.println("Arms: 2");
                                    }

                                    //prints existance of 4 legs based on random value set at creation
                                    if (theWave[basicMob].getLegs() == true) {
                                        System.out.println("Legs: 4");
                                    } else {
                                        System.out.println("Legs: 2");
                                    }

                                    //prints existance of hair based on random value set at creation
                                    System.out.println("Hair: " + theWave[basicMob].getHair());
                                }
                                break;

                            case 2:
                                //sets hp for every individual mob
                                //prompts for hp
                                System.out.print("\nWhat do you want the HP of the wave to be?: ");
                                int newBasicHP = kbReader.nextInt();

                                //loops, applying new hp to every mob
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setHP(newBasicHP);
                                }
                                System.out.println("Done!");
                                break;

                            case 3:
                                //sets lvl for every individual mob
                                //prompts for lvl
                                System.out.print("\nWhat level do you want the wave to be?: ");
                                int newBasicLvl = kbReader.nextInt();

                                //loops, applying new lvl to every mob
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setLvl(newBasicLvl);
                                }
                                System.out.println("Done!");
                                //allows level to affect AP and HP based on ranges
                                for (Mobs theWave1 : theWave) {
                                    //increases hp by 10 and ap by 5 if the level is within 5-20
                                    if (theWave1.getLvl() > 5 && theWave1.getLvl() < 20) {
                                        theWave1.setHP(theWave1.getHP() + 10);
                                        theWave1.setAP(theWave1.getAP() + 5);
                                    } //increases hp by 20 and ap by 10 if the level is within 20-50
                                    else if (theWave1.getLvl() >= 20 && theWave1.getLvl() < 50) {
                                        theWave1.setHP(theWave1.getHP() + 20);
                                        theWave1.setAP(theWave1.getAP() + 10);
                                    } //increases hp by 40 and ap by 20 if level is above 50
                                    else if (theWave1.getLvl() >= 50) {
                                        theWave1.setHP(theWave1.getHP() + 40);
                                        theWave1.setAP(theWave1.getAP() + 20);
                                    }
                                }
                                break;

                            case 4:
                                System.out.print("\nWould you like to specify a range(1) or a specific mob(2)?: ");

                                //sets integer to determine changes to be made
                                int subChoiceRange = kbReader.nextInt();

                                //creates sub-switch for changes to be made
                                switch (subChoiceRange) {
                                    case 1:
                                        System.out.print("What is your range (end not included), enter as: beginning end- "); //explains range mechanics
                                        int rangeBegin = kbReader.nextInt(); //integer for start of range
                                        int rangeEnd = kbReader.nextInt(); //integer for top edge of range
                                        do { //loop for changes to be applied to range selected
                                            System.out.println("1) Get All Values");
                                            System.out.println("2) Set HP of Selected Range");
                                            System.out.println("3) Set Lvl of Selected Range");
                                            System.out.println("4) Set Color of Selected Range");
                                            System.out.println("5) Set AP of Selected Range");
                                            System.out.println("0) Exit");

                                            System.out.print("\nYour selection?: "); //prompt for GUI

                                            subChoice2 = kbReader.nextInt(); //integer for switch for GUI

                                            //third sub switch
                                            switch (subChoice2) {
                                                case 1:
                                                    //establishes range for changes
                                                    for (int basicMob = 0; basicMob < theWave.length; basicMob++) //loops through array values
                                                    {
                                                        if (theWave[basicMob].number >= rangeBegin && theWave[basicMob].number < rangeEnd) //establishes range so only contained items are changed
                                                        {
                                                            System.out.println("");
                                                            System.out.println("Wave Number: " + (basicMob + 1)); //adds 1 so range is 0-wavesize
                                                            System.out.println("Name: " + theWave[basicMob]); //uses array position as name
                                                            System.out.println("Lvl: " + theWave[basicMob].getLvl()); //gets Lvl set at creation
                                                            System.out.println("HP: " + theWave[basicMob].getHP()); //gets HP set at creation
                                                            System.out.println("AP: " + theWave[basicMob].getAP()); //gets AP set at creation
                                                            System.out.println("Color: " + theWave[basicMob].getColor()); //gets color set at creation

                                                            //prints existance of 4 arms
                                                            if (theWave[basicMob].getArms() == true) {
                                                                System.out.println("Arms: 4");
                                                            } else {
                                                                System.out.println("Arms: 2");
                                                            }

                                                            //prints existance of 4 legs
                                                            if (theWave[basicMob].getLegs() == true) {
                                                                System.out.println("Legs: 4");
                                                            } else {
                                                                System.out.println("Legs: 2");
                                                            }

                                                            //prints existance of hair
                                                            System.out.println("Hair: " + theWave[basicMob].getHair());
                                                        }
                                                    }
                                                    break;

                                                case 2:
                                                    //allows setting of hp for range selected

                                                    //acquires new HP
                                                    System.out.print("\nWhat is the new HP?: ");
                                                    int newHP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setHP(newHP);
                                                        }
                                                    }
                                                    break;

                                                case 3:
                                                    //allows setting of lvl for selected range

                                                    //acquires new lvl
                                                    System.out.print("\nWhat is the new lvl?: ");
                                                    int newLvl = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setLvl(newLvl);
                                                        }
                                                    }
                                                    //allows level to affect AP and HP
                                                    for (Mobs theWave1 : theWave) {
                                                        //increases hp by 10 and ap by 5 if the level is within 5-20
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            if (theWave1.getLvl() > 5 && theWave1.getLvl() < 20) {
                                                                theWave1.setHP(theWave1.getHP() + 10);
                                                                theWave1.setAP(theWave1.getAP() + 5);
                                                            } //increases hp by 20 and ap by 10 if the level is within 20-50
                                                            else if (theWave1.getLvl() >= 20 && theWave1.getLvl() < 50) {
                                                                theWave1.setHP(theWave1.getHP() + 20);
                                                                theWave1.setAP(theWave1.getAP() + 10);
                                                            } //increases hp by 40 and ap by 20 if level is above 50
                                                            else if (theWave1.getLvl() >= 50) {
                                                                theWave1.setHP(theWave1.getHP() + 40);
                                                                theWave1.setAP(theWave1.getAP() + 20);
                                                            }
                                                        }
                                                    }
                                                    break;

                                                case 4:
                                                    //allows setting of color

                                                    //acquires new color
                                                    System.out.print("\nWhat is the new color?: ");
                                                    String newColor = kbReader.next();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setColorManual(newColor);
                                                        }
                                                    }
                                                    break;

                                                case 5:
                                                    //allows setting of AP

                                                    //acquires new AP
                                                    System.out.println("\nWhat is the new AP?: ");
                                                    int newAP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setAP(newAP);
                                                        }
                                                    }
                                                    break;

                                                case 6:

                                                    break;
                                            }
                                        } while (subChoice2 != 0);
                                        break;

                                    case 2:

                                        //acquires mob number
                                        System.out.println("What is the number in the range you wish to edit?: ");
                                        int mobNumber = kbReader.nextInt();
                                        do {
                                            System.out.println("1) Get All Values");
                                            System.out.println("2) Set HP of Selected Mob");
                                            System.out.println("3) Set Lvl of Selected Mob");
                                            System.out.println("4) Set Color of Selected Mob");
                                            System.out.println("5) Set AP of Selected Mob");
                                            System.out.println("0) Exit");

                                            System.out.print("\nYour selection?: ");
                                            subChoice3 = kbReader.nextInt();

                                            //third sub switch
                                            switch (subChoice3) {
                                                case 1:

                                                    //establishes range
                                                    for (int basicMob = 0; basicMob < theWave.length; basicMob++) {
                                                        if (theWave[basicMob].number == mobNumber) {
                                                            System.out.println("");
                                                            System.out.println("Wave Number: " + (basicMob + 1)); //adds 1 so range is 0-wavesize
                                                            System.out.println("Name: " + theWave[basicMob]);
                                                            System.out.println("Lvl: " + theWave[basicMob].getLvl());
                                                            System.out.println("HP: " + theWave[basicMob].getHP());
                                                            System.out.println("AP: " + theWave[basicMob].getAP());
                                                            System.out.println("Color: " + theWave[basicMob].getColor());

                                                            //prints existance of 4 arms
                                                            if (theWave[basicMob].getArms() == true) {
                                                                System.out.println("Arms: 4");
                                                            } else {
                                                                System.out.println("Arms: 2");
                                                            }

                                                            //prints existance of 4 legs
                                                            if (theWave[basicMob].getLegs() == true) {
                                                                System.out.println("Legs: 4");
                                                            } else {
                                                                System.out.println("Legs: 2");
                                                            }

                                                            //prints existance of hair
                                                            System.out.println("Hair: " + theWave[basicMob].getHair());
                                                        }
                                                    }
                                                    break;

                                                case 2:
                                                    //allows setting of hp for range selected

                                                    //acquires new HP
                                                    System.out.print("\nWhat is the new HP?: ");
                                                    int newHP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setHP(newHP);
                                                        }
                                                    }
                                                    break;

                                                case 3:
                                                    //allows setting of lvl for selected range

                                                    //acquires new lvl
                                                    System.out.print("\nWhat is the new lvl?: ");
                                                    int newLvl = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setLvl(newLvl);
                                                        }
                                                    }
                                                    //allows level to affect AP and HP
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            if (theWave1.getLvl() > 5 && theWave1.getLvl() < 20) {
                                                                theWave1.setHP(theWave1.getHP() + 10);
                                                                theWave1.setAP(theWave1.getAP() + 5);
                                                            } //increases hp by 20 and ap by 10 if the level is within 20-50
                                                            else if (theWave1.getLvl() >= 20 && theWave1.getLvl() < 50) {
                                                                theWave1.setHP(theWave1.getHP() + 20);
                                                                theWave1.setAP(theWave1.getAP() + 10);
                                                            } //increases hp by 40 and ap by 20 if level is above 50
                                                            else if (theWave1.getLvl() >= 50) {
                                                                theWave1.setHP(theWave1.getHP() + 40);
                                                                theWave1.setAP(theWave1.getAP() + 20);
                                                            }
                                                        }
                                                    }
                                                    break;

                                                case 4:
                                                    //allows setting of color

                                                    //acquires new color
                                                    System.out.print("\nWhat is the new color?: ");
                                                    String newColor = kbReader.next();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setColorManual(newColor);
                                                        }
                                                    }
                                                    break;

                                                case 5:
                                                    //allows setting of AP

                                                    //acquires new AP
                                                    System.out.println("\nWhat is the new AP?: ");
                                                    int newAP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setAP(newAP);
                                                        }
                                                    }
                                                    break;

                                                case 6:

                                                    break;
                                            }
                                        } while (subChoice3 != 0);
                                }
                                break;

                            case 5:
                                //allows setting of color for entire wave

                                //acquires color
                                System.out.print("\nWhat is the new color(in hex)?: ");
                                String newColor = kbReader.next();

                                //sets for entire wave
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setColorManual(newColor);
                                }
                                break;

                            case 6:
                                //allows setting of AP

                                //acquires new AP
                                System.out.println("What is the new AP?: ");
                                int newAP = kbReader.nextInt();

                                //sets for entire wave
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setAP(newAP);
                                }
                                break;

                            case 7:
                                //retrieves total damage
                                System.out.println("");

                                //adds total damage of wave
                                for (Mobs theWave1 : theWave) {
                                    int indivDmg = theWave1.getDamage();
                                    totalDmg += indivDmg;
                                }
                                System.out.println("The wave's capacity for damage is: " + totalDmg);
                                break;

                            case 8:
                                //retrieves total HP
                                System.out.println("");

                                //adds total HP
                                for (Mobs theWave1 : theWave) {
                                    int indivHP = theWave1.getHP();
                                    totalHP += indivHP;
                                }
                                System.out.println("The wave's total health is: " + totalHP);
                                break;
                        }
                    } while (subChoice != 0);
                    break;

                case 2:
                    boolean weapon;
                    //same thing, for armed mobs
                    for (int armedMob = 0; armedMob < theWave.length; armedMob++) {
                        int hitPoints = 10 + numGenerator.nextInt(21);
                        int level = 1 + numGenerator.nextInt(5);
                        int randomWeapon = numGenerator.nextInt(10);
                        int randomWeaponDamage = numGenerator.nextInt(20) + 1;
                        weapon = randomWeapon >= 5;
                        theWave[armedMob] = new Mobs(hitPoints, level, weapon);
                        theWave[armedMob].setColorAuto();
                        theWave[armedMob].setHP(1 + numGenerator.nextInt(10));
                        theWave[armedMob].setAP(1 + numGenerator.nextInt(15));
                        theWave[armedMob].setNumber(armedMob + 1);
                        if (weapon == true) {
                            theWave[armedMob].setWeaponDamage(randomWeaponDamage);
                        }

                        //sets percentage of mobs that will have 4 legs
                        int randomLegs = numGenerator.nextInt(15);
                        if (randomLegs > 10) {
                            theWave[armedMob].setLegs(true);
                        }

                        //sets percentage of mobs that will have 4 arms
                        int randomArms = numGenerator.nextInt(25);
                        if (randomArms > 20) {
                            theWave[armedMob].setArms(true);
                        }

                        //sets percentage of mobs that will have hair
                        int randomHair = numGenerator.nextInt(10);
                        if (randomHair >= 5) {
                            theWave[armedMob].setHair(true);
                        }
                    }

                    System.out.println("Done!");

                    //sub while
                    do {
                        //necessary variables
                        int totalDmg = 0;
                        int totalHP = 0;
                        int totalWeapons = 0;

                        //sub gui 1
                        System.out.println("");
                        System.out.println("1) Retrieve All Set Values");
                        System.out.println("2) Set HP for Wave");
                        System.out.println("3) Set Lvl for Wave - Will increase AP and HP accordingly");
                        System.out.println("4) Specify Mob Name");
                        System.out.println("5) Specify Color in Hex Code");
                        System.out.println("6) Set Weapons for Wave");
                        System.out.println("7) Set AP for Wave");
                        System.out.println("8) Retrieve Total Damage for Wave");
                        System.out.println("9) Retrieve Total HP for Wave");
                        System.out.println("10) Retrieve Total Weapons for Wave");
                        System.out.println("0) Exit - WARNING: CLEARS CURRENT WAVE");

                        System.out.print("\nYour selection?: ");

                        //user selection
                        subChoice = kbReader.nextInt();

                        //sub switch 1
                        switch (subChoice) {
                            case 1:
                                //loops to retrieve values
                                for (int armedMob = 0; armedMob < theWave.length; armedMob++) {
                                    System.out.println("");
                                    System.out.println("Wave Number: " + (armedMob + 1)); //adds 1 so range is 0-wavesize
                                    System.out.println("Name: " + theWave[armedMob]);
                                    System.out.println("Lvl: " + theWave[armedMob].getLvl());
                                    System.out.println("HP: " + theWave[armedMob].getHP());
                                    System.out.println("AP: " + theWave[armedMob].getAP());
                                    System.out.println("Color: " + theWave[armedMob].getColor());
                                    System.out.println("Weapon: " + theWave[armedMob].getWeapon());

                                    //prints existance of 4 arms
                                    if (theWave[armedMob].getArms() == true) {
                                        System.out.println("Arms: 4");
                                    } else {
                                        System.out.println("Arms: 2");
                                    }

                                    //prints existance of 4 legs
                                    if (theWave[armedMob].getLegs() == true) {
                                        System.out.println("Legs: 4");
                                    } else {
                                        System.out.println("Legs: 2");
                                    }

                                    //prints existance of hair
                                    System.out.println("Hair: " + theWave[armedMob].getHair());
                                }
                                break;

                            case 2:
                                //sets hp for every individual mob

                                //prompts for hp
                                System.out.print("\nWhat do you want the HP of the wave to be?: ");
                                int newBasicHP = kbReader.nextInt();

                                //loops, applying new hp to every mob
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setHP(newBasicHP);
                                }
                                System.out.println("Done!");
                                break;

                            case 3:
                                //sets lvl for every individual mob

                                //prompts for lvl
                                System.out.print("\nWhat level do you want the wave to be?: ");
                                int newBasicLvl = kbReader.nextInt();

                                //loops, applying new lvl to every mob
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setLvl(newBasicLvl);
                                }
                                System.out.println("Done!");
                                //allows level to affect AP and HP
                                for (Mobs theWave1 : theWave) {
                                    //increases hp by 10 and ap by 5 if the level is within 5-20
                                    if (theWave1.getLvl() > 5 && theWave1.getLvl() < 20) {
                                        theWave1.setHP(theWave1.getHP() + 10);
                                        theWave1.setAP(theWave1.getAP() + 5);
                                    } //increases hp by 20 and ap by 10 if the level is within 20-50
                                    else if (theWave1.getLvl() >= 20 && theWave1.getLvl() < 50) {
                                        theWave1.setHP(theWave1.getHP() + 20);
                                        theWave1.setAP(theWave1.getAP() + 10);
                                    } //increases hp by 40 and ap by 20 if level is above 50
                                    else if (theWave1.getLvl() >= 50) {
                                        theWave1.setHP(theWave1.getHP() + 40);
                                        theWave1.setAP(theWave1.getAP() + 20);
                                    }
                                }
                                break;

                            case 4:
                                //allows user to specify mob name
                                System.out.print("\nWould you like to specify a range(1) or a specific mob(2)?: ");
                                int subChoiceRange = kbReader.nextInt();
                                //creates sub-switch
                                switch (subChoiceRange) {
                                    case 1:
                                        System.out.print("What is your range (end not included), enter as: beginning end- ");
                                        int rangeBegin = kbReader.nextInt();
                                        int rangeEnd = kbReader.nextInt();
                                        do {
                                            System.out.println("1) Get All Values");
                                            System.out.println("2) Set HP of Selected Range");
                                            System.out.println("3) Set Lvl of Selected Range");
                                            System.out.println("4) Set Color of Selected Range");
                                            System.out.println("5) Set AP of Selected Range");
                                            System.out.println("6) Set Weapon of Selected Range");
                                            System.out.println("0) Exit");

                                            System.out.print("\nYour selection?: ");
                                            subChoice2 = kbReader.nextInt();

                                            //third sub switch
                                            switch (subChoice2) {
                                                case 1:

                                                    //establishes range
                                                    for (int armedMob = 0; armedMob < theWave.length; armedMob++) {
                                                        if (theWave[armedMob].number >= rangeBegin && theWave[armedMob].number < rangeEnd) {
                                                            System.out.println("");
                                                            System.out.println("Wave Number: " + (armedMob + 1)); //adds 1 so range is 0-wavesize
                                                            System.out.println("Name: " + theWave[armedMob]);
                                                            System.out.println("Lvl: " + theWave[armedMob].getLvl());
                                                            System.out.println("HP: " + theWave[armedMob].getHP());
                                                            System.out.println("AP: " + theWave[armedMob].getAP());
                                                            System.out.println("Color: " + theWave[armedMob].getColor());
                                                            System.out.println("Weapon: " + theWave[armedMob].getWeapon());

                                                            //prints existance of 4 arms
                                                            if (theWave[armedMob].getArms() == true) {
                                                                System.out.println("Arms: 4");
                                                            } else {
                                                                System.out.println("Arms: 2");
                                                            }

                                                            //prints existance of 4 legs
                                                            if (theWave[armedMob].getLegs() == true) {
                                                                System.out.println("Legs: 4");
                                                            } else {
                                                                System.out.println("Legs: 2");
                                                            }

                                                            //prints existance of hair
                                                            System.out.println("Hair: " + theWave[armedMob].getHair());
                                                        }
                                                    }
                                                    break;

                                                case 2:
                                                    //allows setting of hp for range selected

                                                    //acquires new HP
                                                    System.out.print("\nWhat is the new HP?: ");
                                                    int newHP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setHP(newHP);
                                                        }
                                                    }
                                                    break;

                                                case 3:
                                                    //allows setting of lvl for selected range

                                                    //acquires new lvl
                                                    System.out.print("\nWhat is the new lvl?: ");
                                                    int newLvl = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setLvl(newLvl);
                                                        }
                                                    }
                                                    //allows level to affect AP and HP
                                                    for (Mobs theWave1 : theWave) {
                                                        //increases hp by 10 and ap by 5 if the level is within 5-20
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            if (theWave1.getLvl() > 5 && theWave1.getLvl() < 20) {
                                                                theWave1.setHP(theWave1.getHP() + 10);
                                                                theWave1.setAP(theWave1.getAP() + 5);
                                                            } //increases hp by 20 and ap by 10 if the level is within 20-50
                                                            else if (theWave1.getLvl() >= 20 && theWave1.getLvl() < 50) {
                                                                theWave1.setHP(theWave1.getHP() + 20);
                                                                theWave1.setAP(theWave1.getAP() + 10);
                                                            } //increases hp by 40 and ap by 20 if level is above 50
                                                            else if (theWave1.getLvl() >= 50) {
                                                                theWave1.setHP(theWave1.getHP() + 40);
                                                                theWave1.setAP(theWave1.getAP() + 20);
                                                            }
                                                        }
                                                    }
                                                    break;

                                                case 4:
                                                    //allows setting of color

                                                    //acquires new color
                                                    System.out.print("\nWhat is the new color?: ");
                                                    String newColor = kbReader.next();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setColorManual(newColor);
                                                        }
                                                    }
                                                    break;

                                                case 5:
                                                    //allows setting of AP

                                                    //acquires new AP
                                                    System.out.print("\nWhat is the new AP?: ");
                                                    int newAP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setAP(newAP);
                                                        }
                                                    }
                                                    break;

                                                case 6:
                                                    //allows setting of weapon
                                                    //necessary boolean
                                                    boolean weaponStat = false;
                                                    System.out.print("\nDoes your mob have weapons(y/n)?: ");

                                                    String newWeapon = kbReader.next();

                                                    //sets boolean
                                                    if (newWeapon.equalsIgnoreCase("y")) {
                                                        weaponStat = true;
                                                    } else if (newWeapon.equalsIgnoreCase("n")) {
                                                        weaponStat = false;
                                                    }

                                                    //applies new value
                                                    for (Mobs theWave1 : theWave) {
                                                        int newWeapDmg = numGenerator.nextInt(20) + 1;
                                                        theWave1.setWeapon(weaponStat);
                                                        if (theWave1.getWeapon() == true) {
                                                            theWave1.setWeaponDamage(newWeapDmg);
                                                        }
                                                    }
                                                    break;
                                            }
                                        } while (subChoice2 != 0);
                                        break;

                                    case 2:
                                        //acquires mob number
                                        System.out.println("What is the number in the range you wish to edit?: ");
                                        int mobNumber = kbReader.nextInt();
                                        do {
                                            System.out.println("1) Get All Values");
                                            System.out.println("2) Set HP of Selected Mob");
                                            System.out.println("3) Set Lvl of Selected Mob");
                                            System.out.println("4) Set Color of Selected Mob");
                                            System.out.println("5) Set AP of Selected Mob");
                                            System.out.println("6) Set Weapon of Selected Mob");
                                            System.out.println("0) Exit");

                                            System.out.print("\nYour selection?: ");
                                            subChoice3 = kbReader.nextInt();

                                            //third sub switch
                                            switch (subChoice3) {
                                                case 1:
                                                    //establishes range
                                                    for (int armedMob = 0; armedMob < theWave.length; armedMob++) {
                                                        if (theWave[armedMob].number == mobNumber) {
                                                            System.out.println("");
                                                            System.out.println("Wave Number: " + (armedMob + 1)); //adds 1 so range is 0-wavesize
                                                            System.out.println("Name: " + theWave[armedMob]);
                                                            System.out.println("Lvl: " + theWave[armedMob].getLvl());
                                                            System.out.println("HP: " + theWave[armedMob].getHP());
                                                            System.out.println("AP: " + theWave[armedMob].getAP());
                                                            System.out.println("Color: " + theWave[armedMob].getColor());
                                                            System.out.println("Weapon: " + theWave[armedMob].getWeapon());

                                                            //prints existance of 4 arms
                                                            if (theWave[armedMob].getArms() == true) {
                                                                System.out.println("Arms: 4");
                                                            } else {
                                                                System.out.println("Arms: 2");
                                                            }

                                                            //prints existance of 4 legs
                                                            if (theWave[armedMob].getLegs() == true) {
                                                                System.out.println("Legs: 4");
                                                            } else {
                                                                System.out.println("Legs: 2");
                                                            }

                                                            //prints existance of hair
                                                            System.out.println("Hair: " + theWave[armedMob].getHair());
                                                        }
                                                    }
                                                    break;

                                                case 2:
                                                    //allows setting of hp for range selected

                                                    //acquires new HP
                                                    System.out.print("\nWhat is the new HP?: ");
                                                    int newHP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setHP(newHP);
                                                        }
                                                    }
                                                    break;

                                                case 3:
                                                    //allows setting of lvl for selected range

                                                    //acquires new lvl
                                                    System.out.print("\nWhat is the new lvl?: ");
                                                    int newLvl = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setLvl(newLvl);
                                                        }
                                                    }
                                                    //allows level to affect AP and HP
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            if (theWave1.getLvl() > 5 && theWave1.getLvl() < 20) {
                                                                theWave1.setHP(theWave1.getHP() + 10);
                                                                theWave1.setAP(theWave1.getAP() + 5);
                                                            } //increases hp by 20 and ap by 10 if the level is within 20-50
                                                            else if (theWave1.getLvl() >= 20 && theWave1.getLvl() < 50) {
                                                                theWave1.setHP(theWave1.getHP() + 20);
                                                                theWave1.setAP(theWave1.getAP() + 10);
                                                            } //increases hp by 40 and ap by 20 if level is above 50
                                                            else if (theWave1.getLvl() >= 50) {
                                                                theWave1.setHP(theWave1.getHP() + 40);
                                                                theWave1.setAP(theWave1.getAP() + 20);
                                                            }
                                                        }
                                                    }
                                                    break;

                                                case 4:
                                                    //allows setting of color

                                                    //acquires new color
                                                    System.out.print("\nWhat is the new color?: ");
                                                    String newColor = kbReader.next();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setColorManual(newColor);
                                                        }
                                                    }
                                                    break;

                                                case 5:
                                                    //allows setting of AP

                                                    //acquires new AP
                                                    System.out.println("\nWhat is the new AP?: ");
                                                    int newAP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setAP(newAP);
                                                        }
                                                    }
                                                    break;

                                                case 6:
                                                    //allows setting of weapon
                                                    //necessary boolean
                                                    boolean weaponStat = false;

                                                    System.out.print("\nDoes your mob have weapons(y/n)?: ");

                                                    String newWeapon = kbReader.next();

                                                    //sets boolean
                                                    if (newWeapon.equalsIgnoreCase("y")) {
                                                        weaponStat = true;
                                                    } else if (newWeapon.equalsIgnoreCase("n")) {
                                                        weaponStat = false;
                                                    }

                                                    //applies new value
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            int newWeapDmg = numGenerator.nextInt(20) + 1;
                                                            theWave1.setWeapon(weaponStat);
                                                            if (theWave1.getWeapon() == true) {
                                                                theWave1.setWeaponDamage(newWeapDmg);
                                                            }
                                                        }
                                                    }
                                                    break;
                                            }
                                        } while (subChoice3 != 0);
                                }
                                break;

                            case 5:
                                //allows setting of color for entire wave

                                //acquires color
                                System.out.print("\nWhat is the new color(in hex)?: ");
                                String newColor = kbReader.next();

                                //sets for entire wave
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setColorManual(newColor);
                                }
                                break;

                            case 6:
                                //allows setting of weapons
                                //necessary boolean
                                boolean weaponStat = false;

                                //acquires weapon status
                                System.out.print("\nDo your mobs have weapons(y/n)?: ");
                                String newWeapon = kbReader.next();

                                //sets boolean
                                if (newWeapon.equalsIgnoreCase("y")) {
                                    weaponStat = true;
                                } else if (newWeapon.equalsIgnoreCase("n")) {
                                    weaponStat = false;
                                }

                                //applies new value
                                for (Mobs theWave1 : theWave) {
                                    int newWeapDmg = numGenerator.nextInt(20) + 1;
                                    theWave1.setWeapon(weaponStat);
                                    if (theWave1.getWeapon() == true) {
                                        theWave1.setWeaponDamage(newWeapDmg);
                                    }
                                }
                                System.out.println("Done!");
                                break;

                            case 7:
                                //allows setting of AP

                                //acquires new AP
                                System.out.println("What is the new AP?: ");
                                int newAP = kbReader.nextInt();

                                //sets for entire wave
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setAP(newAP);
                                }
                                break;

                            case 8:
                                //retrieves total damage of wave
                                System.out.println("");

                                //adds total damage
                                for (Mobs theWave1 : theWave) {
                                    int indivDmg = theWave1.getDamage();
                                    totalDmg += indivDmg;
                                }
                                System.out.println("The wave's capacity for damage is: " + totalDmg);
                                break;

                            case 9:
                                //retrieves total hp
                                System.out.println("");

                                //adds total hp
                                for (Mobs theWave1 : theWave) {
                                    int indivHP = theWave1.getHP();
                                    totalHP += indivHP;
                                }
                                System.out.println("The wave's total HP is: " + totalHP);
                                break;

                            case 10:
                                //adds total weapons
                                System.out.println("");

                                //adder for weapons
                                for (Mobs theWave1 : theWave) {
                                    boolean weaponPresent = theWave1.getWeapon();
                                    if (weaponPresent == true) {
                                        totalWeapons++;
                                    }
                                }
                                System.out.println("The wave has " + totalWeapons + " weapons");
                                break;
                        }
                    } while (subChoice != 0);
                    break;

                case 3:
                    //necessary variables

                    //same thing, for magic mobs
                    for (int magicMob = 0; magicMob < theWave.length; magicMob++) {
                        int hitPoints = 10 + numGenerator.nextInt(21);
                        int level = 1 + numGenerator.nextInt(5);
                        int magicPoints = 20 + numGenerator.nextInt(11);
                        theWave[magicMob] = new Mobs(hitPoints, level, magicPoints);
                        theWave[magicMob].setColorAuto();
                        theWave[magicMob].setHP(1 + numGenerator.nextInt(10));
                        theWave[magicMob].setAP(1 + numGenerator.nextInt(15));
                        theWave[magicMob].setNumber(magicMob + 1);

                        //sets percentage of mobs that will have 4 legs
                        int randomLegs = numGenerator.nextInt(15);
                        if (randomLegs > 10) {
                            theWave[magicMob].setLegs(true);
                        }

                        //sets percentage of mobs that will have 4 arms
                        int randomArms = numGenerator.nextInt(25);
                        if (randomArms > 20) {
                            theWave[magicMob].setArms(true);
                        }

                        //sets percentage of mobs that will have hair
                        int randomHair = numGenerator.nextInt(10);
                        if (randomHair >= 5) {
                            theWave[magicMob].setHair(true);
                        }
                    }

                    System.out.println("Done!");

                    //sub while
                    do {
                        int totalDmg = 0;
                        int totalHP = 0;
                        int totalMP = 0;
                        //sub gui 1
                        System.out.println("");
                        System.out.println("1) Retrieve All Set Values");
                        System.out.println("2) Set HP for Wave");
                        System.out.println("3) Set Lvl for Wave - Will increase AP and HP accordingly");
                        System.out.println("4) Specify Mob Name");
                        System.out.println("5) Specify Color in Hex Code");
                        System.out.println("6) Set AP for Wave");
                        System.out.println("7) Set MP for Wave");
                        System.out.println("8) Retrieve Total Damage for Wave");
                        System.out.println("9) Retrieve Total HP for Wave");
                        System.out.println("10) Retrieve Total MP for Wave");
                        System.out.println("0) Exit - WARNING: CLEARS CURRENT WAVE");

                        System.out.print("\nYour selection?: ");

                        //user selection
                        subChoice = kbReader.nextInt();

                        //sub switch 1
                        switch (subChoice) {
                            case 1:
                                //loops to retrieve values
                                for (int magicMob = 0; magicMob < theWave.length; magicMob++) {
                                    System.out.println("");
                                    System.out.println("Wave Number: " + (magicMob + 1)); //adds 1 so range is 0-wavesize
                                    System.out.println("Name: " + theWave[magicMob]);
                                    System.out.println("Lvl: " + theWave[magicMob].getLvl());
                                    System.out.println("HP: " + theWave[magicMob].getHP());
                                    System.out.println("AP: " + theWave[magicMob].getAP());
                                    System.out.println("Color: " + theWave[magicMob].getColor());
                                    System.out.println("Mana: " + theWave[magicMob].getMP());

                                    //prints existance of 4 arms
                                    if (theWave[magicMob].getArms() == true) {
                                        System.out.println("Arms: 4");
                                    } else {
                                        System.out.println("Arms: 2");
                                    }

                                    //prints existance of 4 legs
                                    if (theWave[magicMob].getLegs() == true) {
                                        System.out.println("Legs: 4");
                                    } else {
                                        System.out.println("Legs: 2");
                                    }

                                    //prints existance of hair
                                    System.out.println("Hair: " + theWave[magicMob].getHair());
                                }
                                break;

                            case 2:
                                //sets hp for every individual mob

                                //prompts for hp
                                System.out.print("\nWhat do you want the HP of the wave to be?: ");
                                int newBasicHP = kbReader.nextInt();

                                //loops, applying new hp to every mob
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setHP(newBasicHP);
                                }
                                System.out.println("Done!");
                                break;

                            case 3:
                                //sets lvl for every individual mob

                                //prompts for lvl
                                System.out.print("\nWhat level do you want the wave to be?: ");
                                int newBasicLvl = kbReader.nextInt();

                                //loops, applying new lvl to every mob
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setLvl(newBasicLvl);
                                }
                                System.out.println("Done!");
                                //allows level to affect AP and HP
                                for (Mobs theWave1 : theWave) {
                                    //increases hp by 10 and ap by 5 if the level is within 5-20
                                    if (theWave1.getLvl() > 5 && theWave1.getLvl() < 20) {
                                        theWave1.setHP(theWave1.getHP() + 10);
                                        theWave1.setAP(theWave1.getAP() + 5);
                                    } //increases hp by 20 and ap by 10 if the level is within 20-50
                                    else if (theWave1.getLvl() >= 20 && theWave1.getLvl() < 50) {
                                        theWave1.setHP(theWave1.getHP() + 20);
                                        theWave1.setAP(theWave1.getAP() + 10);
                                    } //increases hp by 40 and ap by 20 if level is above 50
                                    else if (theWave1.getLvl() >= 50) {
                                        theWave1.setHP(theWave1.getHP() + 40);
                                        theWave1.setAP(theWave1.getAP() + 20);
                                    }
                                }
                                break;

                            case 4:
                                //allows user to specify mob name
                                System.out.print("\nWould you like to specify a range(1) or a specific mob(2)?: ");
                                int subChoiceRange = kbReader.nextInt();
                                //creates sub-switch
                                switch (subChoiceRange) {
                                    case 1:
                                        System.out.print("What is your range (end not included), enter as: beginning end- ");
                                        int rangeBegin = kbReader.nextInt();
                                        int rangeEnd = kbReader.nextInt();
                                        do {
                                            System.out.println("1) Get All Values");
                                            System.out.println("2) Set HP of Selected Range");
                                            System.out.println("3) Set Lvl of Selected Range");
                                            System.out.println("4) Set Color of Selected Range");
                                            System.out.println("5) Set AP of Selected Range");
                                            System.out.println("6) Set MP of Selected Range");
                                            System.out.println("0) Exit");

                                            System.out.print("\nYour selection?: ");
                                            subChoice2 = kbReader.nextInt();

                                            //third sub switch
                                            switch (subChoice2) {
                                                case 1:
                                                    //establishes range
                                                    for (int magicMob = 0; magicMob < theWave.length; magicMob++) {
                                                        if (theWave[magicMob].number >= rangeBegin && theWave[magicMob].number < rangeEnd) {
                                                            System.out.println("");
                                                            System.out.println("Wave Number: " + (magicMob + 1)); //adds 1 so range is 0-wavesize
                                                            System.out.println("Name: " + theWave[magicMob]);
                                                            System.out.println("Lvl: " + theWave[magicMob].getLvl());
                                                            System.out.println("HP: " + theWave[magicMob].getHP());
                                                            System.out.println("AP: " + theWave[magicMob].getAP());
                                                            System.out.println("Color: " + theWave[magicMob].getColor());
                                                            System.out.println("Mana: " + theWave[magicMob].getMP());

                                                            //prints existance of 4 arms
                                                            if (theWave[magicMob].getArms() == true) {
                                                                System.out.println("Arms: 4");
                                                            } else {
                                                                System.out.println("Arms: 2");
                                                            }

                                                            //prints existance of 4 legs
                                                            if (theWave[magicMob].getLegs() == true) {
                                                                System.out.println("Legs: 4");
                                                            } else {
                                                                System.out.println("Legs: 2");
                                                            }

                                                            //prints existance of hair
                                                            System.out.println("Hair: " + theWave[magicMob].getHair());
                                                        }
                                                    }
                                                    break;

                                                case 2:
                                                    //allows setting of hp for range selected

                                                    //acquires new HP
                                                    System.out.print("\nWhat is the new HP?: ");
                                                    int newHP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setHP(newHP);
                                                        }
                                                    }
                                                    break;

                                                case 3:
                                                    //allows setting of lvl for selected range

                                                    //acquires new lvl
                                                    System.out.print("\nWhat is the new lvl?: ");
                                                    int newLvl = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setLvl(newLvl);
                                                        }
                                                    }
                                                    //allows level to affect AP and HP
                                                    for (Mobs theWave1 : theWave) {
                                                        //increases hp by 10 and ap by 5 if the level is within 5-20
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            if (theWave1.getLvl() > 5 && theWave1.getLvl() < 20) {
                                                                theWave1.setHP(theWave1.getHP() + 10);
                                                                theWave1.setAP(theWave1.getAP() + 5);
                                                            } //increases hp by 20 and ap by 10 if the level is within 20-50
                                                            else if (theWave1.getLvl() >= 20 && theWave1.getLvl() < 50) {
                                                                theWave1.setHP(theWave1.getHP() + 20);
                                                                theWave1.setAP(theWave1.getAP() + 10);
                                                            } //increases hp by 40 and ap by 20 if level is above 50
                                                            else if (theWave1.getLvl() >= 50) {
                                                                theWave1.setHP(theWave1.getHP() + 40);
                                                                theWave1.setAP(theWave1.getAP() + 20);
                                                            }
                                                        }
                                                    }
                                                    break;

                                                case 4:
                                                    //allows setting of color

                                                    //acquires new color
                                                    System.out.print("\nWhat is the new color?: ");
                                                    String newColor = kbReader.next();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setColorManual(newColor);
                                                        }
                                                    }
                                                    break;

                                                case 5:
                                                    //allows setting of AP

                                                    //acquires new AP
                                                    System.out.print("\nWhat is the new AP?: ");
                                                    int newAP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setAP(newAP);
                                                        }
                                                    }
                                                    break;

                                                case 6:
                                                    //allows setting of MP

                                                    //acquires new MP
                                                    System.out.print("\nWhat is the new MP?: ");
                                                    int newMP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setMP(newMP);
                                                        }
                                                    }
                                                    break;
                                            }
                                        } while (subChoice2 != 0);
                                        break;

                                    case 2:
                                        //acquires mob number
                                        System.out.println("What is the number in the range you wish to edit?: ");
                                        int mobNumber = kbReader.nextInt();
                                        do {
                                            System.out.println("1) Get All Values");
                                            System.out.println("2) Set HP of Selected Mob");
                                            System.out.println("3) Set Lvl of Selected Mob");
                                            System.out.println("4) Set Color of Selected Mob");
                                            System.out.println("5) Set AP of Selected Mob");
                                            System.out.println("6) Set MP of Selected Mob");
                                            System.out.println("0) Exit");

                                            System.out.print("\nYour selection?: ");
                                            subChoice3 = kbReader.nextInt();

                                            //third sub switch
                                            switch (subChoice3) {
                                                case 1:
                                                    //establishes range
                                                    for (int magicMob = 0; magicMob < theWave.length; magicMob++) {
                                                        if (theWave[magicMob].number == mobNumber) {
                                                            System.out.println("");
                                                            System.out.println("Wave Number: " + (magicMob + 1)); //adds 1 so range is 0-wavesize
                                                            System.out.println("Name: " + theWave[magicMob]);
                                                            System.out.println("Lvl: " + theWave[magicMob].getLvl());
                                                            System.out.println("HP: " + theWave[magicMob].getHP());
                                                            System.out.println("AP: " + theWave[magicMob].getAP());
                                                            System.out.println("Color: " + theWave[magicMob].getColor());
                                                            System.out.println("Mana: " + theWave[magicMob].getMP());

                                                            //prints existance of 4 arms
                                                            if (theWave[magicMob].getArms() == true) {
                                                                System.out.println("Arms: 4");
                                                            } else {
                                                                System.out.println("Arms: 2");
                                                            }

                                                            //prints existance of 4 legs
                                                            if (theWave[magicMob].getLegs() == true) {
                                                                System.out.println("Legs: 4");
                                                            } else {
                                                                System.out.println("Legs: 2");
                                                            }

                                                            //prints existance of hair
                                                            System.out.println("Hair: " + theWave[magicMob].getHair());
                                                        }
                                                    }
                                                    break;

                                                case 2:
                                                    //allows setting of hp for range selected

                                                    //acquires new HP
                                                    System.out.print("\nWhat is the new HP?: ");
                                                    int newHP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setHP(newHP);
                                                        }
                                                    }
                                                    break;

                                                case 3:
                                                    //allows setting of lvl for selected range

                                                    //acquires new lvl
                                                    System.out.print("\nWhat is the new lvl?: ");
                                                    int newLvl = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setLvl(newLvl);
                                                        }
                                                    }
                                                    //allows level to affect AP and HP
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            if (theWave1.getLvl() > 5 && theWave1.getLvl() < 20) {
                                                                theWave1.setHP(theWave1.getHP() + 10);
                                                                theWave1.setAP(theWave1.getAP() + 5);
                                                            } //increases hp by 20 and ap by 10 if the level is within 20-50
                                                            else if (theWave1.getLvl() >= 20 && theWave1.getLvl() < 50) {
                                                                theWave1.setHP(theWave1.getHP() + 20);
                                                                theWave1.setAP(theWave1.getAP() + 10);
                                                            } //increases hp by 40 and ap by 20 if level is above 50
                                                            else if (theWave1.getLvl() >= 50) {
                                                                theWave1.setHP(theWave1.getHP() + 40);
                                                                theWave1.setAP(theWave1.getAP() + 20);
                                                            }
                                                        }
                                                    }
                                                    break;

                                                case 4:
                                                    //allows setting of color

                                                    //acquires new color
                                                    System.out.print("\nWhat is the new color?: ");
                                                    String newColor = kbReader.next();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setColorManual(newColor);
                                                        }
                                                    }
                                                    break;

                                                case 5:
                                                    //allows setting of AP

                                                    //acquires new AP
                                                    System.out.print("\nWhat is the new AP?: ");
                                                    int newAP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setAP(newAP);
                                                        }
                                                    }
                                                    break;

                                                case 6:
                                                    //allows setting of MP

                                                    //acquires new MP
                                                    System.out.print("\nWhat is the new MP?: ");
                                                    int newMP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setMP(newMP);
                                                        }
                                                    }
                                                    break;
                                            }
                                        } while (subChoice3 != 0);
                                }
                                break;

                            case 5:
                                //allows setting of color for entire wave

                                //acquires color
                                System.out.print("\nWhat is the new color(in hex)?: ");
                                String newColor = kbReader.next();

                                //sets for entire wave
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setColorManual(newColor);
                                }
                                break;

                            case 6:
                                //allows setting of AP

                                //acquires new AP
                                System.out.print("\nWhat is the new AP?: ");
                                int newAP = kbReader.nextInt();

                                //sets for entire wave
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setAP(newAP);
                                }
                                break;

                            case 7:
                                //allows setting of MP for entire wave

                                //acquires new MP
                                System.out.print("\nWhat is the new MP?: ");
                                int newMP = kbReader.nextInt();

                                //establishes range
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setMP(newMP);
                                }
                                break;

                            case 8:
                                //retrieves total damage of wave
                                System.out.println("");

                                //adds total damage
                                for (Mobs theWave1 : theWave) {
                                    int indivDmg = theWave1.getDamage();
                                    totalDmg += indivDmg;
                                }
                                System.out.println("The waves capacity for damage is: " + totalDmg);
                                break;

                            case 9:
                                //retrieves total HP of wave
                                System.out.println("");

                                //adds total HP
                                for (Mobs theWave1 : theWave) {
                                    int indivHP = theWave1.getHP();
                                    totalHP += indivHP;
                                }
                                System.out.println("The wave's total health is: " + totalHP);
                                break;

                            case 10:
                                //retrieves total MP of wave
                                System.out.println("");

                                //adds total MP
                                for (Mobs theWave1 : theWave) {
                                    int indivMP = theWave1.getMP();
                                    totalMP += indivMP;
                                }
                                System.out.println("The wave's total mana is: " + totalMP);
                                break;
                        }
                    } while (subChoice != 0);
                    break;

                case 4:

                    //same thing, for mixed mobs
                    for (int mixedMob = 0; mixedMob < theWave.length; mixedMob++) {
                        int hitPoints = 10 + numGenerator.nextInt(21);
                        int level = 1 + numGenerator.nextInt(5);
                        int magicPoints = 20 + numGenerator.nextInt(11);
                        int randomWeapon = numGenerator.nextInt(10);
                        int randomWeaponDamage = numGenerator.nextInt(20) + 1;
                        weapon = randomWeapon >= 5;

                        theWave[mixedMob] = new Mobs(hitPoints, level, magicPoints, weapon);
                        theWave[mixedMob].setColorAuto();
                        theWave[mixedMob].setHP(1 + numGenerator.nextInt(10));
                        theWave[mixedMob].setAP(1 + numGenerator.nextInt(15));
                        theWave[mixedMob].setNumber(mixedMob + 1);
                        if (weapon == true) {
                            theWave[mixedMob].setWeaponDamage(randomWeaponDamage);
                        }

                        //sets percentage of mobs that will have 4 legs
                        int randomLegs = numGenerator.nextInt(15);
                        if (randomLegs > 10) {
                            theWave[mixedMob].setLegs(true);
                        }

                        //sets percentage of mobs that will have 4 arms
                        int randomArms = numGenerator.nextInt(25);
                        if (randomArms > 20) {
                            theWave[mixedMob].setArms(true);
                        }

                        //sets percentage of mobs that will have hair
                        int randomHair = numGenerator.nextInt(10);
                        if (randomHair >= 5) {
                            theWave[mixedMob].setHair(true);
                        }
                    }

                    System.out.println("Done!");

                    //sub while
                    do {
                        //necessary variables
                        int totalDmg = 0;
                        int totalHP = 0;
                        int totalMP = 0;
                        int totalWeapons = 0;
                        //sub gui 1
                        System.out.println("");
                        System.out.println("1) Retrieve All Set Values");
                        System.out.println("2) Set HP for Wave");
                        System.out.println("3) Set Lvl for Wave - Will increase AP and HP accordingly");
                        System.out.println("4) Specify Mob Name");
                        System.out.println("5) Specify Color in Hex Code");
                        System.out.println("6) Set AP for Wave");
                        System.out.println("7) Set MP for Wave");
                        System.out.println("8) Set Weapon for Wave");
                        System.out.println("9) Retrieve Total Damage for Wave");
                        System.out.println("10) Retrieve Total HP for Wave");
                        System.out.println("11) Retrieve Total MP for Wave");
                        System.out.println("12) Retrieve Total Weapons for Wave");
                        System.out.println("0) Exit - WARNING: CLEARS CURRENT WAVE");

                        System.out.print("\nYour selection?: ");

                        //user selection
                        subChoice = kbReader.nextInt();

                        //sub switch 1
                        switch (subChoice) {
                            case 1:
                                //loops to retrieve values
                                for (int mixedMob = 0; mixedMob < theWave.length; mixedMob++) {
                                    System.out.println("");
                                    System.out.println("Wave Number: " + (mixedMob + 1)); //adds 1 so range is 0-wavesize
                                    System.out.println("Name: " + theWave[mixedMob]);
                                    System.out.println("Lvl: " + theWave[mixedMob].getLvl());
                                    System.out.println("HP: " + theWave[mixedMob].getHP());
                                    System.out.println("AP: " + theWave[mixedMob].getAP());
                                    System.out.println("Color: " + theWave[mixedMob].getColor());
                                    System.out.println("Mana: " + theWave[mixedMob].getMP());
                                    System.out.println("Weapon: " + theWave[mixedMob].getWeapon());

                                    //prints existance of 4 arms
                                    if (theWave[mixedMob].getArms() == true) {
                                        System.out.println("Arms: 4");
                                    } else {
                                        System.out.println("Arms: 2");
                                    }

                                    //prints existance of 4 legs
                                    if (theWave[mixedMob].getLegs() == true) {
                                        System.out.println("Legs: 4");
                                    } else {
                                        System.out.println("Legs: 2");
                                    }

                                    //prints existance of hair
                                    System.out.println("Hair: " + theWave[mixedMob].getHair());
                                }
                                break;

                            case 2:
                                //sets hp for every individual mob

                                //prompts for hp
                                System.out.print("\nWhat do you want the HP of the wave to be?: ");
                                int newBasicHP = kbReader.nextInt();

                                //loops, applying new hp to every mob
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setHP(newBasicHP);
                                }
                                System.out.println("Done!");
                                break;

                            case 3:
                                //sets lvl for every individual mob

                                //prompts for lvl
                                System.out.print("\nWhat level do you want the wave to be?: ");
                                int newBasicLvl = kbReader.nextInt();

                                //loops, applying new lvl to every mob
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setLvl(newBasicLvl);
                                }
                                System.out.println("Done!");
                                //allows level to affect AP and HP
                                for (Mobs theWave1 : theWave) {
                                    //increases hp by 10 and ap by 5 if the level is within 5-20
                                    if (theWave1.getLvl() > 5 && theWave1.getLvl() < 20) {
                                        theWave1.setHP(theWave1.getHP() + 10);
                                        theWave1.setAP(theWave1.getAP() + 5);
                                    } //increases hp by 20 and ap by 10 if the level is within 20-50
                                    else if (theWave1.getLvl() >= 20 && theWave1.getLvl() < 50) {
                                        theWave1.setHP(theWave1.getHP() + 20);
                                        theWave1.setAP(theWave1.getAP() + 10);
                                    } //increases hp by 40 and ap by 20 if level is above 50
                                    else if (theWave1.getLvl() >= 50) {
                                        theWave1.setHP(theWave1.getHP() + 40);
                                        theWave1.setAP(theWave1.getAP() + 20);
                                    }
                                }
                                break;

                            case 4:
                                //allows user to specify mob name
                                System.out.print("\nWould you like to specify a range(1) or a specific mob(2)?: ");
                                int subChoiceRange = kbReader.nextInt();
                                //creates sub-switch
                                switch (subChoiceRange) {
                                    case 1:
                                        System.out.print("What is your range (end not included), enter as: beginning end- ");
                                        int rangeBegin = kbReader.nextInt();
                                        int rangeEnd = kbReader.nextInt();
                                        do {
                                            System.out.println("1) Get All Values");
                                            System.out.println("2) Set HP of Selected Range");
                                            System.out.println("3) Set Lvl of Selected Range");
                                            System.out.println("4) Set Color of Selected Range");
                                            System.out.println("5) Set AP of Selected Range");
                                            System.out.println("6) Set MP of Selected Range");
                                            System.out.println("7) Set Weapon of Selected Range");
                                            System.out.println("0) Exit");

                                            System.out.print("\nYour selection?: ");
                                            subChoice2 = kbReader.nextInt();

                                            //third sub switch
                                            switch (subChoice2) {
                                                case 1:

                                                    //establishes range
                                                    for (int mixedMob = 0; mixedMob < theWave.length; mixedMob++) {
                                                        if (theWave[mixedMob].number >= rangeBegin && theWave[mixedMob].number < rangeEnd) {
                                                            System.out.println("");
                                                            System.out.println("Wave Number: " + (mixedMob + 1)); //adds 1 so range is 0-wavesize
                                                            System.out.println("Name: " + theWave[mixedMob]);
                                                            System.out.println("Lvl: " + theWave[mixedMob].getLvl());
                                                            System.out.println("HP: " + theWave[mixedMob].getHP());
                                                            System.out.println("AP: " + theWave[mixedMob].getAP());
                                                            System.out.println("Color: " + theWave[mixedMob].getColor());
                                                            System.out.println("Mana: " + theWave[mixedMob].getMP());
                                                            System.out.println("Weapon: " + theWave[mixedMob].getWeapon());

                                                            //prints existance of 4 arms
                                                            if (theWave[mixedMob].getArms() == true) {
                                                                System.out.println("Arms: 4");
                                                            } else {
                                                                System.out.println("Arms: 2");
                                                            }

                                                            //prints existance of 4 legs
                                                            if (theWave[mixedMob].getLegs() == true) {
                                                                System.out.println("Legs: 4");
                                                            } else {
                                                                System.out.println("Legs: 2");
                                                            }

                                                            //prints existance of hair
                                                            System.out.println("Hair: " + theWave[mixedMob].getHair());
                                                        }
                                                    }
                                                    break;

                                                case 2:
                                                    //allows setting of hp for range selected

                                                    //acquires new HP
                                                    System.out.print("\nWhat is the new HP?: ");
                                                    int newHP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setHP(newHP);
                                                        }
                                                    }
                                                    break;

                                                case 3:
                                                    //allows setting of lvl for selected range

                                                    //acquires new lvl
                                                    System.out.print("\nWhat is the new lvl?: ");
                                                    int newLvl = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setLvl(newLvl);
                                                        }
                                                    }
                                                    //allows level to affect AP and HP
                                                    for (Mobs theWave1 : theWave) {
                                                        //increases hp by 10 and ap by 5 if the level is within 5-20
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            if (theWave1.getLvl() > 5 && theWave1.getLvl() < 20) {
                                                                theWave1.setHP(theWave1.getHP() + 10);
                                                                theWave1.setAP(theWave1.getAP() + 5);
                                                            } //increases hp by 20 and ap by 10 if the level is within 20-50
                                                            else if (theWave1.getLvl() >= 20 && theWave1.getLvl() < 50) {
                                                                theWave1.setHP(theWave1.getHP() + 20);
                                                                theWave1.setAP(theWave1.getAP() + 10);
                                                            } //increases hp by 40 and ap by 20 if level is above 50
                                                            else if (theWave1.getLvl() >= 50) {
                                                                theWave1.setHP(theWave1.getHP() + 40);
                                                                theWave1.setAP(theWave1.getAP() + 20);
                                                            }
                                                        }
                                                    }
                                                    break;

                                                case 4:
                                                    //allows setting of color

                                                    //acquires new color
                                                    System.out.print("\nWhat is the new color?: ");
                                                    String newColor = kbReader.next();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setColorManual(newColor);
                                                        }
                                                    }
                                                    break;

                                                case 5:
                                                    //allows setting of AP

                                                    //acquires new AP
                                                    System.out.println("\nWhat is the new AP?: ");
                                                    int newAP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setAP(newAP);
                                                        }
                                                    }
                                                    break;

                                                case 6:
                                                    //allows setting of MP

                                                    //acquires new MP
                                                    System.out.print("\nWhat is the new MP?: ");
                                                    int newMP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number >= rangeBegin && theWave1.number < rangeEnd) {
                                                            theWave1.setMP(newMP);
                                                        }
                                                    }
                                                    break;

                                                case 7:
                                                    //allows setting of weapon
                                                    //necessary boolean
                                                    boolean weaponStat = false;
                                                    System.out.print("\nDoes your mob have weapons(y/n)?: ");

                                                    String newWeapon = kbReader.next();

                                                    //sets boolean
                                                    if (newWeapon.equalsIgnoreCase("y")) {
                                                        weaponStat = true;
                                                    } else if (newWeapon.equalsIgnoreCase("n")) {
                                                        weaponStat = false;
                                                    }

                                                    //applies new value
                                                    for (Mobs theWave1 : theWave) {
                                                        int newWeapDmg = numGenerator.nextInt(20) + 1;
                                                        theWave1.setWeapon(weaponStat);
                                                        if (theWave1.getWeapon() == true) {
                                                            theWave1.setWeaponDamage(newWeapDmg);
                                                        }
                                                    }
                                                    break;

                                                case 8:

                                                    break;
                                            }
                                        } while (subChoice2 != 0);
                                        break;

                                    case 2:
                                        //acquires mob number
                                        System.out.println("What is the number in the range you wish to edit?: ");
                                        int mobNumber = kbReader.nextInt();
                                        do {
                                            System.out.println("1) Get All Values");
                                            System.out.println("2) Set HP of Selected Mob");
                                            System.out.println("3) Set Lvl of Selected Mob");
                                            System.out.println("4) Set Color of Selected Mob");
                                            System.out.println("5) Set AP of Selected Mob");
                                            System.out.println("6) Set MP of Selected Mob");
                                            System.out.println("7) Set Weapon of Selected Mob");
                                            System.out.println("0) Exit");

                                            System.out.print("\nYour selection?: ");
                                            subChoice3 = kbReader.nextInt();

                                            //third sub switch
                                            switch (subChoice3) {
                                                case 1:

                                                    //establishes range
                                                    for (int mixedMob = 0; mixedMob < theWave.length; mixedMob++) {
                                                        if (theWave[mixedMob].number == mobNumber) {
                                                            System.out.println("");
                                                            System.out.println("Wave Number: " + (mixedMob + 1)); //adds 1 so range is 0-wavesize
                                                            System.out.println("Name: " + theWave[mixedMob]);
                                                            System.out.println("Lvl: " + theWave[mixedMob].getLvl());
                                                            System.out.println("HP: " + theWave[mixedMob].getHP());
                                                            System.out.println("AP: " + theWave[mixedMob].getAP());
                                                            System.out.println("Color: " + theWave[mixedMob].getColor());
                                                            System.out.println("Mana: " + theWave[mixedMob].getMP());
                                                            System.out.println("Weapon: " + theWave[mixedMob].getWeapon());

                                                            //prints existance of 4 arms
                                                            if (theWave[mixedMob].getArms() == true) {
                                                                System.out.println("Arms: 4");
                                                            } else {
                                                                System.out.println("Arms: 2");
                                                            }

                                                            //prints existance of 4 legs
                                                            if (theWave[mixedMob].getLegs() == true) {
                                                                System.out.println("Legs: 4");
                                                            } else {
                                                                System.out.println("Legs: 2");
                                                            }

                                                            //prints existance of hair
                                                            System.out.println("Hair: " + theWave[mixedMob].getHair());
                                                        }
                                                    }
                                                    break;

                                                case 2:
                                                    //allows setting of hp for range selected

                                                    //acquires new HP
                                                    System.out.print("\nWhat is the new HP?: ");
                                                    int newHP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setHP(newHP);
                                                        }
                                                    }
                                                    break;

                                                case 3:
                                                    //allows setting of lvl for selected range

                                                    //acquires new lvl
                                                    System.out.print("\nWhat is the new lvl?: ");
                                                    int newLvl = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setLvl(newLvl);
                                                        }
                                                    }
                                                    //allows level to affect AP and HP
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            if (theWave1.getLvl() > 5 && theWave1.getLvl() < 20) {
                                                                theWave1.setHP(theWave1.getHP() + 10);
                                                                theWave1.setAP(theWave1.getAP() + 5);
                                                            } //increases hp by 20 and ap by 10 if the level is within 20-50
                                                            else if (theWave1.getLvl() >= 20 && theWave1.getLvl() < 50) {
                                                                theWave1.setHP(theWave1.getHP() + 20);
                                                                theWave1.setAP(theWave1.getAP() + 10);
                                                            } //increases hp by 40 and ap by 20 if level is above 50
                                                            else if (theWave1.getLvl() >= 50) {
                                                                theWave1.setHP(theWave1.getHP() + 40);
                                                                theWave1.setAP(theWave1.getAP() + 20);
                                                            }
                                                        }
                                                    }
                                                    break;

                                                case 4:
                                                    //allows setting of color

                                                    //acquires new color
                                                    System.out.print("\nWhat is the new color?: ");
                                                    String newColor = kbReader.next();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setColorManual(newColor);
                                                        }
                                                    }
                                                    break;

                                                case 5:
                                                    //allows setting of AP

                                                    //acquires new AP
                                                    System.out.println("\nWhat is the new AP?: ");
                                                    int newAP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setAP(newAP);
                                                        }
                                                    }
                                                    break;

                                                case 6:
                                                    //allows setting of MP

                                                    //acquires new MP
                                                    System.out.print("\nWhat is the new MP?: ");
                                                    int newMP = kbReader.nextInt();

                                                    //establishes range
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            theWave1.setMP(newMP);
                                                        }
                                                    }
                                                    break;

                                                case 7:
                                                    //allows setting of weapon
                                                    //necessary boolean
                                                    boolean weaponStat = false;

                                                    System.out.print("\nDoes your mob have weapons(y/n)?: ");

                                                    String newWeapon = kbReader.next();

                                                    //sets boolean
                                                    if (newWeapon.equalsIgnoreCase("y")) {
                                                        weaponStat = true;
                                                    } else if (newWeapon.equalsIgnoreCase("n")) {
                                                        weaponStat = false;
                                                    }

                                                    //applies new value
                                                    for (Mobs theWave1 : theWave) {
                                                        if (theWave1.number == mobNumber) {
                                                            int newWeapDmg = numGenerator.nextInt(20) + 1;
                                                            theWave1.setWeapon(weaponStat);
                                                            if (theWave1.getWeapon() == true) {
                                                                theWave1.setWeaponDamage(newWeapDmg);
                                                            }
                                                        }
                                                    }
                                                    break;

                                                case 8:

                                                    break;
                                            }
                                        } while (subChoice3 != 0);
                                }
                                break;

                            case 5:
                                //allows setting of color for entire wave

                                //acquires color
                                System.out.print("\nWhat is the new color(in hex)?: ");
                                String newColor = kbReader.next();

                                //sets for entire wave
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setColorManual(newColor);
                                }
                                break;

                            case 6:
                                //allows setting of AP

                                //acquires new AP
                                System.out.print("\nWhat is the new AP?: ");
                                int newAP = kbReader.nextInt();

                                //sets for entire wave
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setAP(newAP);
                                }
                                break;

                            case 7:
                                //allows setting of MP for entire wave

                                //acquires new MP
                                System.out.print("\nWhat is the new MP?: ");
                                int newMP = kbReader.nextInt();

                                //establishes range
                                for (Mobs theWave1 : theWave) {
                                    theWave1.setMP(newMP);
                                }
                                break;

                            case 8:
                                //allows setting of weapon for entire wave
                                //necessary boolean
                                boolean weaponStat = false;

                                //acquires weapon status
                                System.out.print("\nDo your mobs have weapons(y/n)?: ");
                                String newWeapon = kbReader.next();

                                //sets boolean
                                if (newWeapon.equalsIgnoreCase("y")) {
                                    weaponStat = true;
                                } else if (newWeapon.equalsIgnoreCase("n")) {
                                    weaponStat = false;
                                }

                                //applies new value
                                for (Mobs theWave1 : theWave) {
                                    int newWeapDmg = numGenerator.nextInt(20) + 1;
                                    theWave1.setWeapon(weaponStat);
                                    if (theWave1.getWeapon() == true) {
                                        theWave1.setWeaponDamage(newWeapDmg);
                                    }
                                }
                                System.out.println("Done!");
                                break;

                            case 9:
                                //retrieves total damage of wave
                                System.out.println("");

                                //adds total damage
                                for (Mobs theWave1 : theWave) {
                                    int indivDmg = theWave1.getDamage();
                                    totalDmg += indivDmg;
                                }
                                System.out.println("The waves capacity for damage is: " + totalDmg);
                                break;

                            case 10:
                                //retrieves total HP of wave
                                System.out.println("");

                                //adds total HP
                                for (Mobs theWave1 : theWave) {
                                    int indivHP = theWave1.getHP();
                                    totalHP += indivHP;
                                }
                                System.out.println("The wave's total health is: " + totalHP);
                                break;

                            case 11:
                                //retrieves total MP of wave
                                System.out.println("");

                                //adds total MP
                                for (Mobs theWave1 : theWave) {
                                    int indivMP = theWave1.getMP();
                                    totalMP += indivMP;
                                }
                                System.out.println("The wave's total mana is: " + totalMP);
                                break;

                            case 12:
                                //adds total weapons
                                System.out.println("");

                                //adder for weapons
                                for (Mobs theWave1 : theWave) {
                                    boolean weaponPresent = theWave1.getWeapon();
                                    if (weaponPresent == true) {
                                        totalWeapons++;
                                    }
                                }
                                System.out.println("The wave has " + totalWeapons + " weapons");
                                break;
                        }
                    } while (subChoice != 0);
                    break;

                case 5:
                    //acquires wave size
                    System.out.print("\nWhat is the new wave size?: ");
                    int newWave = kbReader.nextInt();

                    //generates new array
                    theWave = new Mobs[newWave];
                    break;
            }
        } while (mainChoice != 0);
    }
}
