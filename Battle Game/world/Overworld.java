/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mb.battlesystem.BattleStage;
import com.mb.battlesystem.attacks.ActionLibrary;
import com.mb.battlesystem.attacks.ActionLibrary.MeleeName;
import com.mb.battlesystem.attacks.ActionLibrary.ShotName;
import com.mb.battlesystem.attacks.ActionLibrary.WeaponName;
import com.mb.battlesystem.environment.StageLayout;
import com.mb.battlesystem.environment.StageLayout.StageType;
import com.mb.entities.battle.Beast;
import com.mb.entities.battle.BeastLibrary;
import com.mb.entities.battle.BeastLibrary.BeastName;
import com.mb.entities.overworld.WorldCharLibrary;
import com.mb.entities.overworld.WorldCharLibrary.WorldCharacters;
import com.mb.player.GamePlayer;
import com.mb.player.Player2Control;
import com.mb.player.PlayerControl;

/**
 *
 * @author Kyle
 */
public class Overworld {
    GamePlayer p1, p2;
    BattleStage battle;
    World world;
    GameState gameState = GameState.Battle;
    
    public enum GameState{
        Overworld, Menu, Battle
    }
    
    public Overworld(){
        WorldCharLibrary charLib = new WorldCharLibrary();
        p1 = new GamePlayer("Kyle");
        p1.setControls(new PlayerControl());
        p1.setCharacter(charLib.CreateWorldChar(WorldCharacters.Main));
        p1.getCharacter().setController(p1.getControls());
//        WorldSetup();
        SetupTest1();
        p2 = new GamePlayer("April");
        p2.setControls(new Player2Control());
        SetupTest2();
        SetupBattle(p1, p2, StageType.Forest, 1);
    }
    
    public void WorldSetup(){
        world = new World(p1);
    }
    
    //Test
    public void SetupTest1(){
        //Test
        ActionLibrary actionLib = new ActionLibrary();
        BeastLibrary beastLib = new BeastLibrary();
        
        Beast b = beastLib.CreateBeast(BeastName.Fishar, 0, 0, p1);
        b.setName("Wish Fish");
        b.AddAction(actionLib.CreateShotAction(ShotName.MechaBird), 0);
        b.AddAction(actionLib.CreateShotAction(ShotName.Snowball), 1);
        b.AddAction(actionLib.CreateMeleeAction(MeleeName.VoltageRelease), 2);
        b.AddAction(actionLib.CreateWeaponAction(WeaponName.FrozenAxe), 3);
        p1.AddBeastToParty(b);
        
        Beast b2 = beastLib.CreateBeast(BeastName.BattleBot, 0, 0, p1);
        b2.setName("E1T4");
        b2.AddAction(actionLib.CreateShotAction(ShotName.EvilGrip), 0);
        b2.AddAction(actionLib.CreateShotAction(ShotName.MagnetRetract), 1);
        b2.AddAction(actionLib.CreateMeleeAction(MeleeName.NightmareSurprise), 2);
        b2.AddAction(actionLib.CreateWeaponAction(WeaponName.BrandishedFlower), 3);
        p1.AddBeastToParty(b2);
        
        Beast b3 = beastLib.CreateBeast(BeastName.Eyevil, 0, 0, p1);
        b3.setName("Unaliver");
        b3.AddAction(actionLib.CreateShotAction(ShotName.ToxicBalloon), 0);
        b3.AddAction(actionLib.CreateShotAction(ShotName.PoisonedThorn), 1);
        b3.AddAction(actionLib.CreateMeleeAction(MeleeName.VoltageRelease), 2);
        b3.AddAction(actionLib.CreateWeaponAction(WeaponName.SerratedKnife), 3);
        p1.AddBeastToParty(b3);
        
        Beast b4 = beastLib.CreateBeast(BeastName.Hathare, 0, 0, p1);
        b4.setName("Greg");
        b4.AddAction(actionLib.CreateShotAction(ShotName.AirDevil), 0);
        b4.AddAction(actionLib.CreateShotAction(ShotName.PulsingLight), 1);
        b4.AddAction(actionLib.CreateMeleeAction(MeleeName.PiercingWall), 2);
        b4.AddAction(actionLib.CreateWeaponAction(WeaponName.BladedEnd), 3);
        p1.AddBeastToParty(b4);
        
        Beast b5 = beastLib.CreateBeast(BeastName.Gnomelit, 0, 0, p1);
        b5.setName("Grump");
        b5.AddAction(actionLib.CreateShotAction(ShotName.PebbleCloud), 0);
        b5.AddAction(actionLib.CreateShotAction(ShotName.LavaSpit), 1);
        b5.AddAction(actionLib.CreateMeleeAction(MeleeName.RisingBlades), 2);
        b5.AddAction(actionLib.CreateWeaponAction(WeaponName.DragonsTongue), 3);
        p1.AddBeastToParty(b5);
    }
    
    //Test
    public void SetupTest2(){
        //Test
        ActionLibrary actionLib = new ActionLibrary();
        BeastLibrary beastLib = new BeastLibrary();
        
        Beast b = beastLib.CreateBeast(BeastName.DevoutMiny, 0, 0, p2);
        b.setName("Monky");
        b.AddAction(actionLib.CreateShotAction(ShotName.ElectroSeeker), 0);
        b.AddAction(actionLib.CreateShotAction(ShotName.EnergyDisrupt), 1);
        b.AddAction(actionLib.CreateShotAction(ShotName.IlluminatedChakram), 2);
        b.AddAction(actionLib.CreateWeaponAction(WeaponName.BladedEnd), 3);
        p2.AddBeastToParty(b);
        
        Beast b2 = beastLib.CreateBeast(BeastName.Pincepod, 0, 0, p2);
        b2.setName("Krabby");
        b2.AddAction(actionLib.CreateShotAction(ShotName.RokFeather), 0);
        b2.AddAction(actionLib.CreateShotAction(ShotName.MalevolentSphere), 1);
        b2.AddAction(actionLib.CreateMeleeAction(MeleeName.FrozenPikes), 2);
        b2.AddAction(actionLib.CreateWeaponAction(WeaponName.SerratedKnife), 3);
        p2.AddBeastToParty(b2);
        
        Beast b3 = beastLib.CreateBeast(BeastName.Elykid, 0, 0, p2);
        b3.setName("Dumbo");
        b3.AddAction(actionLib.CreateShotAction(ShotName.AngelWings), 0);
        b3.AddAction(actionLib.CreateShotAction(ShotName.TorchRocket), 1);
        b3.AddAction(actionLib.CreateMeleeAction(MeleeName.ImpactFist), 2);
        b3.AddAction(actionLib.CreateWeaponAction(WeaponName.ArkSpear), 3);
        p2.AddBeastToParty(b3);
        
        Beast b4 = beastLib.CreateBeast(BeastName.Cryptolem, 0, 0, p2);
        b4.setName("Loki");
        b4.AddAction(actionLib.CreateShotAction(ShotName.RockTunneler), 0);
        b4.AddAction(actionLib.CreateShotAction(ShotName.EarthShard), 1);
        b4.AddAction(actionLib.CreateMeleeAction(MeleeName.SonicScream), 2);
        b4.AddAction(actionLib.CreateWeaponAction(WeaponName.CelestialFangs), 3);
        p2.AddBeastToParty(b4);
        
        Beast b5 = beastLib.CreateBeast(BeastName.Grimmith, 0, 0, p2);
        b5.setName("Ghost");
        b5.AddAction(actionLib.CreateShotAction(ShotName.BlazingRing), 0);
        b5.AddAction(actionLib.CreateShotAction(ShotName.MalevolentSphere), 1);
        b5.AddAction(actionLib.CreateMeleeAction(MeleeName.StoneBlockade), 2);
        b5.AddAction(actionLib.CreateWeaponAction(WeaponName.BrandishedFlower), 3);
        p2.AddBeastToParty(b5);
    }
    
    public void Render(SpriteBatch batch){
        switch(gameState){
            case Overworld: world.Render(batch);
                break;
            case Battle: battle.Render(batch);
                break;
        }
    }
    
    public void DebugRender(ShapeRenderer draw){
        switch(gameState){
            case Overworld: 
                world.DebugRender(draw);
                break;
            case Battle: battle.DebugRender(draw);
                break;
        }
    }
    
    public void SetupBattle(GamePlayer p1, GamePlayer p2, StageType type, int layoutNum){
        gameState = GameState.Battle;
        StageLayout layout = new StageLayout(type, layoutNum);
        this.battle = new BattleStage(layout);
        battle.SetupDuel(p1, p2);
    }
}
