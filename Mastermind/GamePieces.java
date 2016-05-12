/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Kyle
 */
public class GamePieces {
    
    int w = 16;
    public Texture pieces;
    //First row
    public TextureRegion redP, blueP, greenP, yellowP, orangeP, purpleP, brownP, grayP; 
    //Second Row
    public TextureRegion noP, scoreP, correctP, almostP, inputBtn, againBtn;
    public GamePieces(){
        pieces = new Texture("Pieces.png");
        //First row
        redP = new TextureRegion(pieces, 0, 0, w, w);
        blueP = new TextureRegion(pieces, w, 0, w, w); 
        greenP = new TextureRegion(pieces, w*2, 0, w, w);
        yellowP = new TextureRegion(pieces, w*3, 0, w, w);
        orangeP = new TextureRegion(pieces, w*4, 0, w, w);
        purpleP = new TextureRegion(pieces, w*5, 0, w, w); 
        brownP = new TextureRegion(pieces, w*6, 0, w, w);
        grayP = new TextureRegion(pieces, w*7, 0, w, w);
        
        //Second Row
        noP = new TextureRegion(pieces, 0, w, w, w);
        scoreP = new TextureRegion(pieces, w, w, w, w);
        correctP = new TextureRegion(pieces, w*2, w, w, w);
        almostP = new TextureRegion(pieces, w*3, w, w, w);
        inputBtn = new TextureRegion(pieces, w*4, w, w*2, w);
        againBtn = new TextureRegion(pieces, w*6, w, w*2, w);
    }
    
}
