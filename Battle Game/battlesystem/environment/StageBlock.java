/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem.environment;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Kyle
 */
public class StageBlock {
    private Rectangle hitbox;
    private TextureRegion img;
    
    public StageBlock(){
        
    }
    
    public void Render(SpriteBatch batch){
        batch.draw(img, hitbox.x, hitbox.y);
    }
    
    public void DebugRender(ShapeRenderer draw){
        draw.setColor(1, 0, 0, 1);
        draw.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    /**
     * @return the hitbox
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * @param hitbox the hitbox to set
     */
    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    /**
     * @return the img
     */
    public TextureRegion getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(TextureRegion img) {
        this.img = img;
    }
}
