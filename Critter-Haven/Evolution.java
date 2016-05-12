/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.monsters;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Kyle
 */
public class Evolution {
    PlayerMonster mon;
    int currentMonNum;
    int currentLvl;
    int monStr, monCon, monAgl;
    int EvoMon;
    
    
    public Evolution(PlayerMonster mon){
        this.mon = mon;
        this.currentMonNum = mon.monNumber;
        this.monStr = mon.strLvl;
        this.monCon = mon.conLvl;
        this.monAgl = mon.aglLvl;
    }
    
    public void EvoPlayerMon(){
        checkEvoLineLvl0();
        EvolveMonList(EvoMon);
        mon.monNumber = EvoMon;
    }
    
    void EvolveMonList(int EvoMon){
        //Level 1 monsters**************
        if(EvoMon == 1){
            mon.mPic = new Texture("BlueGumdropSheet.png");
        }
        if(EvoMon == 2){
            mon.mPic = new Texture("AngetongueSheet.png");
        }
        if(EvoMon == 3){
            mon.mPic = new Texture("Rocket-ESheet.png");
        }
        //Level 2 monsters**************
        if(EvoMon == 4){
            //chillgent sheet
        }
        if(EvoMon == 5){
            mon.mPic = new Texture("DarklingSheet.png");
        }
        if(EvoMon == 6){
            mon.mPic = new Texture("TwinFlampSheet.png");
        }
        if(EvoMon == 7){
            mon.mPic = new Texture("MagnebotSheet.png");
        }
        //Level 3 monsters**************
        if(EvoMon == 8){
            mon.mPic = new Texture("EvilFaceSheet.png");
        }
        if(EvoMon == 9){
            //mon.mPic = new Texture("MirrorDemonSheet.png");
        }
        if(EvoMon == 10){
            //mon.mPic = new Texture("BagloomSheet.png");
        }
        if(EvoMon == 11){
            mon.mPic = new Texture("FlamesterSheet.png");
        }
        if(EvoMon == 12){
            //mon.mPic = new Texture("GunMouseSheet.png");
        }
        //Level 4 monsters**************
        if(EvoMon == 13){
            //mon.mPic = new Texture("BlackheartSheet.png");
        }
        if(EvoMon == 14){
            //mon.mPic = new Texture("DrownedAssassinSheet.png");
        }
        if(EvoMon == 15){
            //mon.mPic = new Texture("HarshQueenSheet.png");
        }
        if(EvoMon == 16){
            //mon.mPic = new Texture("WereDoberSheet.png");
        }
        if(EvoMon == 17){
            //mon.mPic = new Texture("DustkSheet.png");
        }
        if(EvoMon == 18){
            mon.mPic = new Texture("DualithSheet.png");
        }
    }
    
    void checkEvoLineLvl0(){
        if(currentMonNum == 0){
            int bigNum = 0;
            
            //Check highest stat
            if(monStr > bigNum){
                bigNum = monStr;
                EvoMon = 1;
            }
            if(monCon > bigNum){
                bigNum = monCon;
                EvoMon = 2;
            }
            if(monAgl > bigNum){
                bigNum = monAgl;
                EvoMon = 3;
            }
            //End of checking stats
        }
    }
}
