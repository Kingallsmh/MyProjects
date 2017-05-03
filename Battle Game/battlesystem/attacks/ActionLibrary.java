/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem.attacks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mb.battlesystem.Rules.ElementalType;
import com.mb.battlesystem.attacks.MeleeAction.MeleeType;
import com.mb.battlesystem.attacks.ShotAction.ShotType;
import com.mb.battlesystem.attacks.WeaponAction.AttackType;
import com.mb.entities.battle.Beast;

/**
 *
 * @author Kyle
 */
public class ActionLibrary {
    int s = 64;
    Texture meleeSheet = new Texture("MeleeAtks.png");
    Texture shotSheet = new Texture("ShotAtks.png");
    Texture weaponSheet = new Texture("WeaponAtks.png");
    
    public enum MeleeName{
        Airblade, ImpactFist, ThornyVine, PiercingWall, BubbleBurst, FlamePunch, VoltageRelease,
        StoneBlockade, NightmareSurprise, SonicScream, GoldenHawk, RisingBlades, EarthenExplosion,
        FrozenPikes, JudgementSwords
    }
        
    public Action CreateMeleeAction(MeleeName name){
        Action action = new Action();
        MeleeAction a = null;
        switch(name){
            case Airblade: a = new MeleeAction(action, MeleeType.Float);
                a.setImgList(GetImgList(0, 0, 1, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 14, 16));
                a.setXSpeed(110);
                a.setMaxTime(0.25f);
                action.setType(ElementalType.WIND);
                action.setEPUsage(4);
                action.setSATK(40);
                action.setPATK(0);
                action.setBATK(0);
                action.setName("Air Blade");
                break;
            case ImpactFist: a = new MeleeAction(action, MeleeType.Standard);
                a.setImgList(GetImgList(1, 0, 1, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 24, 24));
                a.setXSpeed(20);
                a.setMaxTime(0.25f);
                a.setPushXAmount(100);
                action.setType(ElementalType.NEUTRAL);
                action.setEPUsage(4);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(50);
                action.setName("Impact Fist");
                break;
            case ThornyVine: a = new MeleeAction(action, MeleeType.Float);
                a.setImgList(GetImgList(2, 0, 2, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 32, 16));
                a.setXSpeed(0);
                a.setMaxTime(0.7f);
                action.setType(ElementalType.NATURE);
                action.setEPUsage(4);
                action.setSATK(0);
                action.setPATK(40);
                action.setBATK(0);
                action.setName("Thorny Vine");
                break;
            case PiercingWall: a = new MeleeAction(action, MeleeType.Standard);
                a.setImgList(GetImgList(3, 0, 1, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 16, 32));
                a.setXSpeed(0);
                a.setMaxTime(2f);
                a.setPushXAmount(10);
                a.setySpeed(10);
                action.setType(ElementalType.NEUTRAL);
                action.setEPUsage(4);
                action.setSATK(0);
                action.setPATK(40);
                action.setBATK(20);
                action.setName("Piercing Wall");
                break;
            case BubbleBurst: a = new MeleeAction(action, MeleeType.Float);
                a.setImgList(GetImgList(4, 0, 2, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 20, 20));
                a.setXSpeed(10);
                a.setySpeed(10);
                a.setMaxTime(1f);
                a.setPushXAmount(50);
                a.setPushYAmount(50);
                action.setType(ElementalType.WATER);
                action.setEPUsage(4);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(45);
                action.setName("Bubble Burst");
                break;
            case FlamePunch: a = new MeleeAction(action, MeleeType.Float);
                a.setImgList(GetImgList(0, 2, 1, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 24, 16));
                a.setXSpeed(110);
                a.setMaxTime(0.4f);
                a.setPushXAmount(20);
                action.setType(ElementalType.FIRE);
                action.setEPUsage(4);
                action.setSATK(20);
                action.setPATK(20);
                action.setBATK(20);
                action.setName("Flame Punch");
                break;
            case VoltageRelease: a = new MeleeAction(action, MeleeType.Standard);
                a.setImgList(GetImgList(1,2, 1, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 48, 16));
                a.setXSpeed(0);
                a.setMaxTime(0.2f);
                action.setType(ElementalType.ELECTRICITY);
                action.setEPUsage(4);
                action.setSATK(30);
                action.setPATK(0);
                action.setBATK(30);
                action.setName("Voltage Release");
                break;
            case StoneBlockade: a = new MeleeAction(action, MeleeType.Float);
                a.setImgList(GetImgList(2, 2, 2, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 31, 31));
                a.setXSpeed(0);
                a.setMaxTime(2f);
                action.setType(ElementalType.EARTH);
                action.setEPUsage(4);
                action.setSATK(20);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Stone Blockade");
                break;
            case NightmareSurprise: a = new MeleeAction(action, MeleeType.Standard);
                a.setImgList(GetImgList(3, 2, 2, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 48, 32));
                a.setXSpeed(0);
                a.setMaxTime(1f);
                a.setPushXAmount(120);
                action.setType(ElementalType.DARK);
                action.setEPUsage(4);
                action.setSATK(20);
                action.setPATK(20);
                action.setBATK(0);
                action.setName("Nightmare Surprise");
                break;
            case SonicScream: a = new MeleeAction(action, MeleeType.Float);
                a.setImgList(GetImgList(4, 2, 1, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 16, 32));
                a.setXSpeed(160);
                a.setMaxTime(0.8f);
                a.setPushXAmount(80);
                action.setType(ElementalType.WIND);
                action.setEPUsage(4);
                action.setSATK(0);
                action.setPATK(20);
                action.setBATK(20);
                action.setName("Sonic Scream");
                break;
            case GoldenHawk: a = new MeleeAction(action, MeleeType.Standard);
                a.setImgList(GetImgList(0, 4, 1, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 10, 37));
                a.setXSpeed(80);
                a.setMaxTime(0.5f);
                a.setPushXAmount(50);
                action.setType(ElementalType.LIGHT);
                action.setEPUsage(4);
                action.setSATK(50);
                action.setPATK(10);
                action.setBATK(0);
                action.setName("Golden Hawk");
                break;
            case RisingBlades: a = new MeleeAction(action, MeleeType.Wave);
                a.setImgList(GetImgList(1, 4, 2, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 16, 40));
                a.setXSpeed(8);
                a.setMaxTime(0.5f);
                a.setPushYAmount(50);
                a.setMaxWaves(3);
                action.setType(ElementalType.NEUTRAL);
                action.setEPUsage(4);
                action.setSATK(50);
                action.setPATK(0);
                action.setBATK(0);
                action.setName("Rising Blades");
                break;
            case EarthenExplosion: a = new MeleeAction(action, MeleeType.Wave);
                a.setImgList(GetImgList(2, 4, 2, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 32, 32));
                a.setXSpeed(0);
                a.setMaxTime(0.4f);
                a.setPushYAmount(100);
                a.setMaxWaves(4);
                action.setType(ElementalType.EARTH);
                action.setEPUsage(4);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(60);
                action.setName("Earthen Explosion");
                break;
            case FrozenPikes: a = new MeleeAction(action, MeleeType.Wave);
                a.setImgList(GetImgList(3, 4, 2, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 48, 32));
                a.setXSpeed(0);
                a.setMaxTime(0.4f);
                a.setPushYAmount(0);
                a.setMaxWaves(3);
                action.setType(ElementalType.WATER);
                action.setEPUsage(4);
                action.setSATK(20);
                action.setPATK(40);
                action.setBATK(0);
                action.setName("Frozen Pikes");
                break;
            case JudgementSwords: a = new MeleeAction(action, MeleeType.Wave);
                a.setImgList(GetImgList(4, 4, 2, meleeSheet, s));
                a.setHitbox(new Rectangle(0, 0, 16, 48));
                a.setXSpeed(32);
                a.setMaxTime(0.8f);
                a.setPushYAmount(-180);
                a.setMaxWaves(4);
                action.setType(ElementalType.LIGHT);
                action.setEPUsage(4);
                action.setSATK(60);
                action.setPATK(0);
                action.setBATK(0);
                action.setName("Judgement Swords");
                break; 
        }
        action.setMelee(a);
        return action;
    }
    
    public enum ShotName{
        SeedShot, TorchRocket, BubbleBlast, AirDevil, ThrowingBlade, MalevolentSphere, 
        AngelWings, EarthShard, EnergyDisrupt,
        PoisonedThorn, BlazingRing, ToxicBalloon, RokFeather, Heatseeker, EvilGrip, 
        PulsingLight, PebbleCloud, ElectroSeeker,
        ManEatingPlant, LavaSpit, Snowball, MechaBird, KiWaveShot, HarmingFlight, IlluminatedChakram,
        RockTunneler, MagnetRetract
    }
    
    public Action CreateShotAction(ShotName name){
        Action action = new Action();
        ShotAction a = null;
        switch(name){
            case SeedShot: a = new ShotAction(action, ShotType.Straight);
                a.setImgList(GetImgList(0, 0, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 7, 7));
                a.setxSpeed(100);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                a.setPushXAmount(20);
                action.setType(ElementalType.NATURE);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Seed Shot");
                break;
            case TorchRocket: a = new ShotAction(action, ShotType.BuildUp);
                a.setImgList(GetImgList(1, 0, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 18, 8));
                a.setAccelerationRate(200);
                a.setxSpeed(280);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                a.setPushXAmount(70);
                action.setType(ElementalType.FIRE);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Torch Rocket");
                break;
            case BubbleBlast: a = new ShotAction(action, ShotType.SlowDown);
                a.setImgList(GetImgList(2, 0, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 20, 11));
                a.setAccelerationRate(100);
                a.setxSpeed(160);
                a.setMaxTime(0.2f); //To set how long an animation takes to change frames
                a.setPushXAmount(20);
                action.setType(ElementalType.WATER);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Bubble Blast");
                break;
            case AirDevil: a = new ShotAction(action, ShotType.Boomerang);
                a.setImgList(GetImgList(3, 0, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 15, 15));
                a.setAccelerationRate(140);
                a.setxSpeed(200);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                a.setPushXAmount(40);
                a.setPushYAmount(120);
                action.setType(ElementalType.WIND);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Air-devil");
                break;
            case ThrowingBlade: a = new ShotAction(action, ShotType.Straight);
                a.setImgList(GetImgList(4, 0, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 14, 5));
                a.setxSpeed(140);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                a.setPushXAmount(40);
                action.setType(ElementalType.NEUTRAL);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Throwing Blade");
                break;
            case MalevolentSphere: a = new ShotAction(action, ShotType.WavePath);
                a.setImgList(GetImgList(5, 0, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 12, 11));
                a.setPhaseThrough(true);
                a.setAccelerationRate(200);
                a.setySpeed(120);
                a.setxSpeed(60);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.DARK);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Malevolent Sphere");
                break;
            case AngelWings: a = new ShotAction(action, ShotType.Boomerang);
                a.setImgList(GetImgList(6, 0, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 22, 22));
                a.setAccelerationRate(50);
                a.setxSpeed(100);
                a.setMaxTime(0.05f); //To set how long an animation takes to change frames
                action.setType(ElementalType.LIGHT);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Angel Wings");
                break;
            case EarthShard: a = new ShotAction(action, ShotType.Straight);
                a.setImgList(GetImgList(7, 0, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 11, 8));
                a.setxSpeed(120);
                a.setMaxTime(0.05f); //To set how long an animation takes to change frames
                action.setType(ElementalType.EARTH);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Earth Shard");
                break;
            case EnergyDisrupt: a = new ShotAction(action, ShotType.WavePath);
                a.setImgList(GetImgList(8, 0, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 12, 12));
                a.setPhaseThrough(true);
                a.setAccelerationRate(800);
                a.setySpeed(200);
                a.setxSpeed(130);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.ELECTRICITY);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Energy Disrupt");
                break;
            case PoisonedThorn: a = new ShotAction(action, ShotType.SlowDown);
                a.setImgList(GetImgList(0, 2, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 13, 13));
                a.setAccelerationRate(40);
                a.setxSpeed(150);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                a.setPushXAmount(20);
                action.setType(ElementalType.NATURE);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Poisoned Thorn");
                break;
            case BlazingRing: a = new ShotAction(action, ShotType.Boomerang);
                a.setImgList(GetImgList(1, 2, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 22, 22));
                a.setAccelerationRate(160);
                a.setxSpeed(200);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.FIRE);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Blazing Ring");
                break;
            case ToxicBalloon: a = new ShotAction(action, ShotType.WavePath);
                a.setImgList(GetImgList(2, 2, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 12, 14));
                a.setPhaseThrough(false);
                a.setAccelerationRate(12);
                a.setySpeed(18);
                a.setxSpeed(30);
                a.setMaxTime(0.4f); //To set how long an animation takes to change frames
                action.setType(ElementalType.WATER);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Toxic Balloon");
                break;
            case RokFeather: a = new ShotAction(action, ShotType.Homing);
                a.setImgList(GetImgList(3, 2, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 17, 7));
                a.setxSpeed(160);
                a.setAccelerationRate(80);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.WIND);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Rok Feather");
                break;
            case Heatseeker: a = new ShotAction(action, ShotType.Homing);
                a.setImgList(GetImgList(4, 2, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 15, 9));
                a.setxSpeed(100);
                a.setAccelerationRate(80);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.NEUTRAL);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Heatseeker");
                break;
            case EvilGrip: a = new ShotAction(action, ShotType.Straight);
                a.setImgList(GetImgList(5, 2, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 17, 14));
                a.setxSpeed(80);
                a.setPushXAmount(-90);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.DARK);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Evil Grip");
                break;
            case PulsingLight: a = new ShotAction(action, ShotType.BuildUp);
                a.setImgList(GetImgList(6, 2, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 15, 11));
                a.setxSpeed(220);
                a.setAccelerationRate(180);
                a.setPushXAmount(60);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.LIGHT);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Pulsing Light");
                break;
            case PebbleCloud: a = new ShotAction(action, ShotType.Boomerang);
                a.setImgList(GetImgList(7, 2, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 18, 18));
                a.setAccelerationRate(50);
                a.setxSpeed(120);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.EARTH);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Pebble Cloud");
                break;
            case ElectroSeeker: a = new ShotAction(action, ShotType.Homing);
                a.setImgList(GetImgList(8, 2, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 19, 13));
                a.setxSpeed(120);
                a.setAccelerationRate(80);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.NEUTRAL);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Electroseeker");
                break;
            case ManEatingPlant: a = new ShotAction(action, ShotType.Homing);
                a.setImgList(GetImgList(0, 4, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 26, 26));
                a.setxSpeed(60);
                a.setAccelerationRate(80);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.NATURE);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Man-eating Plant");
                break;
            case LavaSpit: a = new ShotAction(action, ShotType.Straight);
                a.setImgList(GetImgList(1, 4, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 20, 11));
                a.setxSpeed(130);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.FIRE);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Lava Spit");
                break;
            case Snowball: a = new ShotAction(action, ShotType.Straight);
                a.setImgList(GetImgList(2, 4, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 12, 12));
                a.setxSpeed(100);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.WATER);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Snowball");
                break;
            case MechaBird: a = new ShotAction(action, ShotType.WavePath);
                a.setImgList(GetImgList(3, 4, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 28, 15));
                a.setPhaseThrough(true);
                a.setAccelerationRate(40);
                a.setySpeed(50);
                a.setxSpeed(50);
                a.setMaxTime(0.4f); //To set how long an animation takes to change frames
                action.setType(ElementalType.WIND);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Mechabird");
                break;
            case KiWaveShot: a = new ShotAction(action, ShotType.WavePath);
                a.setImgList(GetImgList(4, 4, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 15, 11));
                a.setPhaseThrough(true);
                a.setAccelerationRate(600);
                a.setySpeed(180);
                a.setxSpeed(140);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.NEUTRAL);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Ki Wave Shot");
                break;
            case HarmingFlight: a = new ShotAction(action, ShotType.Homing);
                a.setImgList(GetImgList(5, 4, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 11, 12));
                a.setxSpeed(80);
                a.setAccelerationRate(300);
                a.setMaxTime(0.2f); //To set how long an animation takes to change frames
                action.setType(ElementalType.DARK);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(20);
                action.setName("Harming Flight");
                break;
            case IlluminatedChakram: a = new ShotAction(action, ShotType.Boomerang);
                a.setImgList(GetImgList(6, 4, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 25, 25));
                a.setAccelerationRate(80);
                a.setxSpeed(150);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.LIGHT);
                action.setEPUsage(2);
                action.setSATK(20);
                action.setPATK(0);
                action.setBATK(0);
                action.setName("Illuminated Chakram");
                break;
            case RockTunneler: a = new ShotAction(action, ShotType.BuildUp);
                a.setImgList(GetImgList(7, 4, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 19, 11));
                a.setxSpeed(110);
                a.setAccelerationRate(100);
                a.setPushXAmount(60);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.EARTH);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(40);
                action.setBATK(20);
                action.setName("Rock Tunneler");
                break;
            case MagnetRetract: a = new ShotAction(action, ShotType.Boomerang);
                a.setImgList(GetImgList(8, 4, 2, shotSheet, s/2));
                a.setHitbox(new Rectangle(0, 0, 13, 12));
                a.setAccelerationRate(100);
                a.setxSpeed(200);
                a.setPushXAmount(-200);
                a.setMaxTime(0.1f); //To set how long an animation takes to change frames
                action.setType(ElementalType.ELECTRICITY);
                action.setEPUsage(2);
                action.setSATK(0);
                action.setPATK(0);
                action.setBATK(40);
                action.setName("Magnet Retract");
                break;
        }
        action.setShot(a);
        return action;
    }
    
    public enum WeaponName{
        CelestialFangs, ArkSpear, BladedEnd, BrandishedFlower, DragonsTongue ,SerratedKnife,
        FrozenAxe
    }
    
    public Action CreateWeaponAction(WeaponName name){
        Action action = new Action();
        WeaponAction a = null;
        switch(name){
            case CelestialFangs: a = new WeaponAction(action, AttackType.Slice);
                a.setImg(GetImgList(0, 0, 1, weaponSheet, s)[0]);
                a.setHitbox(new Rectangle(0, 0, 11, 56));
                a.setPushXAmount(40);
                a.setPushYAmount(0);
                a.setSwingSpeed(6);
                action.setType(ElementalType.LIGHT);
                action.setSATK(60);
                action.setPATK(0);
                action.setBATK(0);
                action.setName("Celestial Fangs");
                break; 
            case ArkSpear: a = new WeaponAction(action, AttackType.Stab);
                a.setImg(GetImgList(1, 0, 1, weaponSheet, s)[0]);
                a.setHitbox(new Rectangle(0, 0, 8, 56));
                a.setStabSpeed(2);
                a.setPushXAmount(40);
                a.setPushYAmount(0);
                action.setType(ElementalType.LIGHT);
                action.setSATK(60);
                action.setPATK(0);
                action.setBATK(0);
                action.setName("Ark Spear");
                break;
            case BladedEnd: a = new WeaponAction(action, AttackType.Stab);
                a.setImg(GetImgList(2, 0, 1, weaponSheet, s)[0]);
                a.setHitbox(new Rectangle(0, 0, 12, 56));
                a.setStabSpeed(2);
                a.setPushXAmount(40);
                a.setPushYAmount(0);
                action.setType(ElementalType.NEUTRAL);
                action.setSATK(60);
                action.setPATK(0);
                action.setBATK(0);
                action.setName("Bladed End");
                break;
            case BrandishedFlower: a = new WeaponAction(action, AttackType.Slice);
                a.setImg(GetImgList(3, 0, 1, weaponSheet, s)[0]);
                a.setHitbox(new Rectangle(0, 0, 8, 57));
                a.setSwingSpeed(6);
                a.setStabSpeed(3);
                a.setPushXAmount(40);
                a.setPushYAmount(0);
                action.setType(ElementalType.NATURE);
                action.setSATK(40);
                action.setPATK(0);
                action.setBATK(0);
                action.setName("Brandished Flower");
                break;
            case DragonsTongue: a = new WeaponAction(action, AttackType.Slice);
                a.setImg(GetImgList(4, 0, 1, weaponSheet, s)[0]);
                a.setHitbox(new Rectangle(0, 0, 22, 32));
                a.setSwingSpeed(6);
                a.setStabSpeed(3);
                a.setPushXAmount(40);
                a.setPushYAmount(0);
                action.setType(ElementalType.FIRE);
                action.setSATK(40);
                action.setPATK(0);
                action.setBATK(0);
                action.setName("Dragon's Tongue");
                break;
            case FrozenAxe: a = new WeaponAction(action, AttackType.Slice);
                a.setImg(GetImgList(5, 0, 1, weaponSheet, s)[0]);
                a.setHitbox(new Rectangle(0, 0, 27, 39));
                a.setSwingSpeed(8);
                a.setStabSpeed(3);
                a.setPushXAmount(40);
                a.setPushYAmount(0);
                action.setType(ElementalType.WATER);
                action.setSATK(40);
                action.setPATK(0);
                action.setBATK(0);
                action.setName("Frozen Axe");
                break;
            case SerratedKnife: a = new WeaponAction(action, AttackType.Stab);
                a.setImg(GetImgList(0, 1, 1, weaponSheet, s)[0]);
                a.setHitbox(new Rectangle(0, 0, 7, 25));
                a.setSwingSpeed(6);
                a.setStabSpeed(3);
                a.setPushXAmount(40);
                a.setPushYAmount(0);
                action.setType(ElementalType.NEUTRAL);
                action.setSATK(40);
                action.setPATK(0);
                action.setBATK(0);
                action.setName("Serrated Knife");
                break;
        }
        action.setWeapon(a);
        return action;
    }
    
    public TextureRegion[] GetImgList(int x, int y, int num, Texture img, int size){
        TextureRegion[] n = new TextureRegion[num];
        for(int i = 0; i < n.length; i++){
            n[i] = new TextureRegion(img, x*size, (i+y)*size, size, size);
        }
        return n;
    }
}
