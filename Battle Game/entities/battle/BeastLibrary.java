/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.entities.battle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mb.battlesystem.Rules.ElementalType;
import com.mb.player.GamePlayer;

/**
 *
 * @author Kyle
 */
public class BeastLibrary {
    int s = 64;
    Texture sheet1 = new Texture("BeastSheet1.png");
    Texture sheet2 = new Texture("BeastSheet2.png");
    
    public enum BeastName{
        Pincepod, Elykid, Nitenite, Litrod, Nitro, Cryptolem, Grimmith, Fishar, SpikeSlym, 
        BulbSlym, Hathare, Mineefrost, Eyevil, Gnomelit, FrozenGnomelit, AssaultMiny, ShieldCarp,
        MagicMiny, DevoutMiny, BattleBot
    }
    
    public BeastLibrary(){
        
    }
    
    public Beast CreateBeast(BeastName name, float x, float y, GamePlayer gamePlayer){
        Beast b = new Beast(gamePlayer);
        b.setSpecies(name);
        switch(name){
            case Pincepod: b.getAnimations().setWalkAnim(GetImgSet(0, 0, 2, sheet1));
                b.getStats().setType(ElementalType.WATER);
                b.getAnimations().setMeleeAnim(GetImgSet(0, 1, 2, sheet1));
                b.getAnimations().setHurtAnim(GetImg(2, 0, sheet1));
                b.getAnimations().setShootAnim(GetImg(2, 1, sheet1));
                b.setHitbox(new Rectangle(x, y, 14, 20));
                b.setxDistance(4);
                b.setFaceBox(new Rectangle(19, 0, 30, 30));
                break;
            case Elykid: b.getAnimations().setWalkAnim(GetImgSet(3, 0, 2, sheet1));
                b.getStats().setType(ElementalType.NEUTRAL);
                b.getAnimations().setMeleeAnim(GetImgSet(3, 1, 2, sheet1));
                b.getAnimations().setHurtAnim(GetImg(5, 0, sheet1));
                b.getAnimations().setShootAnim(GetImg(5, 1, sheet1));
                b.setHitbox(new Rectangle(x, y, 16, 28));
                b.setxDistance(4);
                b.setFaceBox(new Rectangle(17, 3, 30, 30));
                break;
            case Nitenite: b.getAnimations().setWalkAnim(GetImgSet(0, 2, 2, sheet1));
                b.getStats().setType(ElementalType.DARK);
                b.getAnimations().setMeleeAnim(GetImgSet(0, 3, 2, sheet1));
                b.getAnimations().setHurtAnim(GetImg(2, 2, sheet1));
                b.getAnimations().setShootAnim(GetImg(2, 3, sheet1));
                b.setHitbox(new Rectangle(x, y, 12, 17));
                b.setxDistance(2);
                b.setFaceBox(new Rectangle(17, 0, 30, 30));
                break;
            case Litrod: b.getAnimations().setWalkAnim(GetImgSet(3, 2, 2, sheet1));
                b.getStats().setType(ElementalType.ELECTRICITY);
                b.getAnimations().setMeleeAnim(GetImgSet(3, 3, 2, sheet1));
                b.getAnimations().setHurtAnim(GetImg(5, 2, sheet1));
                b.getAnimations().setShootAnim(GetImg(5, 3, sheet1));
                b.setHitbox(new Rectangle(x, y, 14, 25));
                b.setxDistance(5);
                b.setFaceBox(new Rectangle(17, 0, 30, 30));
                break;
            case Nitro: b.getAnimations().setWalkAnim(GetImgSet(0, 4, 2, sheet1));
                b.getStats().setType(ElementalType.FIRE);
                b.getAnimations().setMeleeAnim(GetImgSet(0, 5, 2, sheet1));
                b.getAnimations().setHurtAnim(GetImg(2, 4, sheet1));
                b.getAnimations().setShootAnim(GetImg(2, 5, sheet1));
                b.setHitbox(new Rectangle(x, y, 26, 26));
                b.setxDistance(4);
                b.setFaceBox(new Rectangle(18, 1, 30, 30));
                break;
            case Cryptolem: b.getAnimations().setWalkAnim(GetImgSet(3, 4, 2, sheet1));
                b.getStats().setType(ElementalType.EARTH);
                b.getAnimations().setMeleeAnim(GetImgSet(3, 5, 2, sheet1));
                b.getAnimations().setHurtAnim(GetImg(5, 4, sheet1));
                b.getAnimations().setShootAnim(GetImg(5, 5, sheet1));
                b.setHitbox(new Rectangle(x, y, 24, 30));
                b.setxDistance(4);
                b.setFaceBox(new Rectangle(20, 2, 30, 30));
                break;
            case Grimmith: b.getAnimations().setWalkAnim(GetImgSet(0, 0, 2, sheet2));
                b.getStats().setType(ElementalType.DARK);
                b.getAnimations().setMeleeAnim(GetImgSet(0, 1, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(2, 0, sheet2));
                b.getAnimations().setShootAnim(GetImg(2, 1, sheet2));
                b.setHitbox(new Rectangle(x, y, 14, 30));
                b.setxDistance(6);
                b.setFaceBox(new Rectangle(19, 6, 30, 30));
                break;
            case Fishar: b.getAnimations().setWalkAnim(GetImgSet(3, 0, 2, sheet2));
                b.getStats().setType(ElementalType.ELECTRICITY);
                b.getAnimations().setMeleeAnim(GetImgSet(3, 1, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(5, 0, sheet2));
                b.getAnimations().setShootAnim(GetImg(5, 1, sheet2));
                b.setHitbox(new Rectangle(x, y, 16, 20));
                b.setxDistance(4);
                b.setFaceBox(new Rectangle(17, 0, 30, 30));
                break;
            case SpikeSlym: b.getAnimations().setWalkAnim(GetImgSet(6, 0, 2, sheet2));
                b.getStats().setType(ElementalType.NEUTRAL);
                b.getAnimations().setMeleeAnim(GetImgSet(6, 1, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(8, 1, sheet2));
                b.getAnimations().setShootAnim(GetImg(8, 1, sheet2));
                b.setHitbox(new Rectangle(x, y, 18, 24));
                b.setxDistance(4);
                b.setFaceBox(new Rectangle(17, 0, 30, 30));
                break;
            case BulbSlym: b.getAnimations().setWalkAnim(GetImgSet(0, 2, 2, sheet2));
                b.getStats().setType(ElementalType.LIGHT);
                b.getAnimations().setMeleeAnim(GetImgSet(0, 3, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(2, 2, sheet2));
                b.getAnimations().setShootAnim(GetImg(2, 3, sheet2));
                b.setHitbox(new Rectangle(x, y, 20, 28));
                b.setxDistance(4);
                b.setFaceBox(new Rectangle(17, 0, 30, 30));
                break;
            case Hathare: b.getAnimations().setWalkAnim(GetImgSet(3, 2, 2, sheet2));
                b.getStats().setType(ElementalType.NATURE);
                b.getAnimations().setMeleeAnim(GetImgSet(3, 3, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(5, 2, sheet2));
                b.getAnimations().setShootAnim(GetImg(5, 3, sheet2));
                b.setHitbox(new Rectangle(x, y, 14, 31));
                b.setxDistance(5);
                b.setFaceBox(new Rectangle(18, 9, 30, 30));
                break;
            case Mineefrost: b.getAnimations().setWalkAnim(GetImgSet(6, 2, 2, sheet2));
                b.getStats().setType(ElementalType.WATER);
                b.getAnimations().setMeleeAnim(GetImgSet(6, 3, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(8, 2, sheet2));
                b.getAnimations().setShootAnim(GetImg(8, 3, sheet2));
                b.setHitbox(new Rectangle(x, y, 10, 26));
                b.setxDistance(6);
                b.setFaceBox(new Rectangle(17, 0, 30, 30));
                break;
            case Eyevil: b.getAnimations().setWalkAnim(GetImgSet(0, 4, 2, sheet2));
                b.getStats().setType(ElementalType.DARK);
                b.getAnimations().setMeleeAnim(GetImgSet(0, 5, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(2, 4, sheet2));
                b.getAnimations().setShootAnim(GetImg(2, 5, sheet2));
                b.setHitbox(new Rectangle(x, y, 18, 20));
                b.setxDistance(6);
                b.setFaceBox(new Rectangle(17, 0, 30, 30));
                break;
            case Gnomelit: b.getAnimations().setWalkAnim(GetImgSet(3, 4, 2, sheet2));
                b.getStats().setType(ElementalType.EARTH);
                b.getAnimations().setMeleeAnim(GetImgSet(3, 5, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(5, 4, sheet2));
                b.getAnimations().setShootAnim(GetImg(5, 5, sheet2));
                b.setHitbox(new Rectangle(x, y, 14, 20));
                b.setxDistance(2);
                b.setFaceBox(new Rectangle(17, 0, 30, 30));
                break;
            case FrozenGnomelit: b.getAnimations().setWalkAnim(GetImgSet(6, 4, 2, sheet2));
                b.getStats().setType(ElementalType.WATER);
                b.getAnimations().setMeleeAnim(GetImgSet(6, 5, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(8, 4, sheet2));
                b.getAnimations().setShootAnim(GetImg(8, 5, sheet2));
                b.setHitbox(new Rectangle(x, y, 14, 20));
                b.setxDistance(2);
                b.setFaceBox(new Rectangle(17, 0, 30, 30));
                break;
            case AssaultMiny: b.getAnimations().setWalkAnim(GetImgSet(0, 6, 2, sheet2));
                b.getStats().setType(ElementalType.NEUTRAL);
                b.getAnimations().setMeleeAnim(GetImgSet(0, 7, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(2, 6, sheet2));
                b.getAnimations().setShootAnim(GetImg(2, 7, sheet2));
                b.setHitbox(new Rectangle(x, y, 16, 17));
                b.setxDistance(2);
                b.setFaceBox(new Rectangle(17, 0, 30, 30));
                break;
            case ShieldCarp: b.getAnimations().setWalkAnim(GetImgSet(3, 6, 2, sheet2));
                b.getStats().setType(ElementalType.WATER);
                b.getAnimations().setMeleeAnim(GetImgSet(3, 7, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(5, 6, sheet2));
                b.getAnimations().setShootAnim(GetImg(5, 7, sheet2));
                b.setHitbox(new Rectangle(x, y, 22, 15));
                b.setxDistance(2);
                b.setFaceBox(new Rectangle(17, 0, 30, 30));
                break;
            case MagicMiny: b.getAnimations().setWalkAnim(GetImgSet(6, 6, 2, sheet2));
                b.getStats().setType(ElementalType.DARK);
                b.getAnimations().setMeleeAnim(GetImgSet(6, 7, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(8, 6, sheet2));
                b.getAnimations().setShootAnim(GetImg(8, 7, sheet2));
                b.setHitbox(new Rectangle(x, y, 16, 17));
                b.setxDistance(2);
                b.setFaceBox(new Rectangle(17, 0, 30, 30));
                break;
            case DevoutMiny: b.getAnimations().setWalkAnim(GetImgSet(0, 8, 2, sheet2));
                b.getStats().setType(ElementalType.LIGHT);
                b.getAnimations().setMeleeAnim(GetImgSet(0, 9, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(2, 8, sheet2));
                b.getAnimations().setShootAnim(GetImg(2, 9, sheet2));
                b.setHitbox(new Rectangle(x, y, 16, 17));
                b.setxDistance(2);
                b.setFaceBox(new Rectangle(17, 0, 30, 30));
                break;
            case BattleBot: b.getAnimations().setWalkAnim(GetImgSet(3, 8, 2, sheet2));
                b.getStats().setType(ElementalType.NEUTRAL);
                b.getAnimations().setMeleeAnim(GetImgSet(3, 9, 2, sheet2));
                b.getAnimations().setHurtAnim(GetImg(5, 8, sheet2));
                b.getAnimations().setShootAnim(GetImg(5, 9, sheet2));
                b.setHitbox(new Rectangle(x, y, 16, 38));
                b.setxDistance(2);
                b.setFaceBox(new Rectangle(17, 11, 30, 30));
                break;
        }
        return b;
    }
    
    public TextureRegion GetImg(int x, int y, Texture img){
        return new TextureRegion(img, s*x, s*y, s, s);
    }
    
    public TextureRegion[] GetImgSet(int x, int y, int num, Texture img){
        TextureRegion[] set = new TextureRegion[2];
        for(int i = 0; i < num; i++){
            set[i] = new TextureRegion(img, s*(x+i), s*y, s, s);
        }
        return set;
    }
}
