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

import java.util.Random; //necessary java package

/**
 * Class to create mobs for array
 *
 * @author Ryan Castelli
 * @version 1.0
 */
public class Mobs {

    //random number generator
    Random numGenerator = new Random();

    //start of variables
    public boolean fourArms;
    public boolean fourLegs;
    public boolean hasHair;
    public boolean hasWeapon; //weapon

    public int attackPoints; //AP
    public int dmgTotal; //total Damage
    public int hitPoints; //HP
    public int hpTotal; //total HP
    public int level; //Lvl
    public int magicPoints; //MP
    public int mpTotal; //total MP
    public int number; //number in wave
    public int totalDamage;
    public int weaponDamage;
    public int weaponNum = 0; //# of weapons

    public String color;

    /**
     * Basic mob
     * @param h
     * @param l 
     */
    public Mobs(int h, int l) {
        hitPoints = h;
        level = l;
    }

    /**
     * Mob with weapon
     * @param h
     * @param l
     * @param w 
     */
    public Mobs(int h, int l, boolean w) {
        hitPoints = h;
        level = l;
        hasWeapon = w;
    }

    /**
     * Mob with magic
     * @param h
     * @param l
     * @param m 
     */
    public Mobs(int h, int l, int m) {
        magicPoints = m;
        hitPoints = h;
        level = l;
    }

    /**
     * Mixed mobs, randomly selected armaments
     * @param h
     * @param l
     * @param m
     * @param w 
     */
    public Mobs(int h, int l, int m, boolean w) {
        magicPoints = m;
        hitPoints = h;
        level = l;
        hasWeapon = w;
    }

    /**
     * Sets health of mob
     * @param hp 
     */
    public void setHP(int hp) {
        hitPoints = hp;
    }

    /**
     * Sets magic of mob
     * @param m 
     */
    public void setMP(int m) {
        magicPoints = m;
    }

    /**
     * Sets color of mob
     * @param c 
     */
    public void setColorManual(String c) {
        color = c;
    }

    /**
     * Sets attack of mob
     * @param ap 
     */
    public void setAP(int ap) {
        attackPoints = ap;
    }

    /**
     * Sets weapon damage of mob
     * @param w 
     */
    public void setWeaponDamage(int w) {
        weaponDamage = w;
    }

    /**
     * Sets level of mob
     * @param l 
     */
    public void setLvl(int l) {
        level = l;
    }

    /**
     * Changes whether or not a mob has a weapon
     * @param w 
     */
    public void setWeapon(boolean w) {
        hasWeapon = w;
    }

    /**
     * Changes mobs number in wave
     * @param n 
     */
    public void setNumber(int n) {
        number = n;
    }

    /**
     * Changes whether the mob has two legs or four
     * @param l 
     */
    public void setLegs(boolean l) {
        fourLegs = l;
    }

    /**
     * Changes whether the mob has two arms or four
     * @param a 
     */
    public void setArms(boolean a) {
        fourArms = a;
    }

    /**
     * Changes whether or not the mob has hair
     * @param h 
     */
    public void setHair(boolean h) {
        hasHair = h;
    }

    /**
     * 
     * @return attack points of mob
     */
    public int getAP() {
        return attackPoints;
    }

    /**
     * 
     * @return magic points of mob
     */
    public int getMP() {
        return magicPoints;
    }

    /**
     * 
     * @return presence of weapon
     */
    public boolean getWeapon() {
        return hasWeapon;
    }

    /**
     * 
     * @return health of mob
     */
    public int getHP() {
        return hitPoints;
    }

    /**
     * 
     * @return experience level of mob
     */
    public int getLvl() {
        return level;
    }

    /**
     * 
     * @return hex string for color of mob
     */
    public String getColor() {
        return color;
    }

    /**
     * 
     * @return presence of hair
     */
    public boolean getHair() {
        return hasHair;
    }

    /**
     * 
     * @return presence of four arms
     */
    public boolean getArms() {
        return fourArms;
    }

    /**
     * 
     * @return presence of four legs
     */
    public boolean getLegs() {
        return fourLegs;
    }

    /**
     * Determines random hex color for mob
     */
    public void setColorAuto() {
        for (int j = 0; j < 3; j++) {
            String colorToHex = Integer.toHexString(numGenerator.nextInt(255) + 1);
            color = color + colorToHex;
        }
    }

    /**
     * Adds damage across all of a mob's abilities
     * @return total damage
     */
    public int getDamage() {
        totalDamage = weaponDamage + attackPoints + magicPoints;
        return totalDamage;
    }

    /**
     * Increments weapon number on presence of weapon
     * @param w
     * @return number of weapons
     */
    public int numberOfWeapons(boolean w) {
        if (w = true) {
            weaponNum++;
        }
        return weaponNum;
    }
}
