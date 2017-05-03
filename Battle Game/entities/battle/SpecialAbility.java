/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.entities.battle;

/**
 *
 * @author Kyle
 */
public class SpecialAbility {
    
    public void SpeciesAbilityLogic(Beast b){
        switch(b.getSpecies()){
            case Cryptolem: EffecientUser(b);
                break;
            case Nitenite: FloatAbility(b);
                break;
            case Grimmith: FloatAbility(b);
                break;
            case Eyevil: FloatAbility(b);
                break;
            case Fishar: FloatAbility(b);
                break;
        }
    }
    
    //Able to float about the stage
    public void FloatAbility(Beast b){
        b.getInput().setFloating(true);
    }
    
    //Half the EP cost of using skill the same element as user
    public void EffecientUser(Beast b){
        b.getStats().setEfficientUser(true);
    }
}
