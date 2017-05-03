/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.events;

import com.mb.battlesystem.BattleStage;
import com.mb.battlesystem.environment.StageLayout;
import com.mb.battlesystem.environment.StageLayout.StageType;
import com.mb.player.GamePlayer;

/**
 *
 * @author Kyle
 */
public class BattleStart {
    GamePlayer enemy;
    StageType type;
    int layoutNum;
    
    /*If there is going to be "trainer" battles then they need to be saved data
        so that it stays consistent. Otherwise there should be a randomly generated
        process for random fights.
    */  
    public void SetEnemy(){
        
    }
    
    public BattleStage CreateBattle(GamePlayer p1){
        StageLayout layout = new StageLayout(type, layoutNum);
        BattleStage battle = new BattleStage(layout);
        battle.SetupDuel(p1, enemy);
        return battle;
    }
}
