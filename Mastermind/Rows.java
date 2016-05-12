/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class Rows {
    
    ArrayList<Pegs> pegs;
    int correct;
    int almost;
    int round;
    GamePieces gp;
    
    public Rows(Pegs p1, Pegs p2, Pegs p3, Pegs p4, int c, int a, int r, GamePieces gp){
        pegs = new ArrayList<Pegs>();
        pegs.add(p1);
        pegs.add(p2);
        pegs.add(p3);
        pegs.add(p4);
        this.gp = gp;
        
        this.correct = c;
        this.almost = a;
        this.round = r;
    }
    
    public void RenderRowsPegs(SpriteBatch batch){
        for (Pegs peg : pegs) {
            batch.draw(peg.colourChoice, peg.spot.x, peg.spot.y);
        }
        RenderTips(batch);
    }
    
    private void RenderTips(SpriteBatch batch){
        int pos = 1;
        int x1 = 77;
        int y1 = 51 + (16 * (round-1));
        int x2 = 85;
        int y2 = 51  + (16 * (round-1));
        int x3 = 77;
        int y3 = 43 + (16 * (round-1));
        int x4 = 85;
        int y4 = 43 + (16 * (round-1));
        
        for(int i = 0; i < correct; i++){
            if(pos == 1){
                batch.draw(gp.correctP, x1, y1);//Top Left
                pos += 1;
            }
            else if(pos == 2){
                batch.draw(gp.correctP, x2, y2);//Top Right
                pos += 1;
            }
            else if(pos == 3){
                batch.draw(gp.correctP, x3, y3);//Bot Left
                pos += 1;
            }
            else if(pos == 4){
                batch.draw(gp.correctP, x4, y4);//Bot Right
                pos += 1;
            }
        }
        
        for(int i = 0; i < almost; i++){
            if(pos == 1){
                batch.draw(gp.almostP, x1, y1);//Top Left
                pos += 1;
            }
            else if(pos == 2){
                batch.draw(gp.almostP, x2, y2);//Top Right
                pos += 1;
            }
            else if(pos == 3){
                batch.draw(gp.almostP, x3, y3);//Bot Left
                pos += 1;
            }
            else if(pos == 4){
                batch.draw(gp.almostP, x4, y4);//Bot Right
                pos += 1;
            }
        }
    }
}
