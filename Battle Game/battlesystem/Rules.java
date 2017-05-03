/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem;

/**
 *
 * @author Kyle
 */
public class Rules {
    
    public enum StatusEffect{
        NONE, POISONED, PARALYZED, CHILLED, BURNED, DISABLED, ENRAGED, CONFUSED, GRAVITYPULL, GRAVITYPUSH
    }
    
    public enum ElementalType{
        NEUTRAL, FIRE, WATER, NATURE, EARTH, ELECTRICITY, WIND, LIGHT, DARK
    }
    
    public enum Direction{
        NORTH, SOUTH, EAST, WEST
    }
}
