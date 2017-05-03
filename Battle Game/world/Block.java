/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mb.CalcUtilities;
import com.mb.world.BlockLibrary.BlockName;
import com.mb.world.BlockLibrary.MiniBlockName;
import com.mb.world.BlockLibrary.ObjectName;
import com.mb.world.BlockLibrary.Section;
import java.io.Serializable;

/**
 *
 * @author Kyle
 */
public class Block implements Serializable, IDraw{
    private boolean passable;
    //Used for reloading imgs
    private BlockName blockName;
    private MiniBlockName miniBlockName;
    private Section section;
    private ObjectName objectName;
    
    transient private TextureRegion[] imgList;
    int frame;
    private Rectangle hitbox;
    
    float time;
    private float maxTime = 0.5f;
    
    public Block(){
        
    }
    
    public Block(int numOfFrames){
        imgList = new TextureRegion[numOfFrames];
    }
    
    public void Animate(){
        if(imgList.length > 1){
            if(time > maxTime){
                if(frame < imgList.length - 1){
                    frame ++;
                }
                else{
                    frame = 0;
                }
                time = 0;
            }
            else{
                time += CalcUtilities.CorrectTime();
            }
        }
    }
    
    @Override
    public void Render(SpriteBatch batch){
        Animate();
        float x = hitbox.x - imgList[frame].getRegionWidth()/2 + hitbox.width/2;
        batch.draw(imgList[frame], x, hitbox.y);
    }
    
    public void DebugRender(ShapeRenderer draw){
        if(!passable){
            draw.setColor(1, 0, 0, 1);
        }
        draw.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        draw.setColor(1, 1, 1, 1);
    }

    /**
     * @return the passable
     */
    public boolean isPassable() {
        return passable;
    }

    /**
     * @param passable the passable to set
     */
    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    /**
     * @return the imgList
     */
    public TextureRegion[] getImgList() {
        return imgList;
    }

    /**
     * @param imgList the imgList to set
     */
    public void setImgList(TextureRegion[] imgList) {
        this.imgList = imgList;
    }

    /**
     * @return the hitbox
     */
    @Override
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
     * @return the maxTime
     */
    public float getMaxTime() {
        return maxTime;
    }

    /**
     * @param maxTime the maxTime to set
     */
    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }

    /**
     * @return the blockName
     */
    public BlockName getBlockName() {
        return blockName;
    }

    /**
     * @param blockName the blockName to set
     */
    public void setBlockName(BlockName blockName) {
        this.blockName = blockName;
    }

    /**
     * @return the miniBlockName
     */
    public MiniBlockName getMiniBlockName() {
        return miniBlockName;
    }

    /**
     * @param miniBlockName the miniBlockName to set
     */
    public void setMiniBlockName(MiniBlockName miniBlockName) {
        this.miniBlockName = miniBlockName;
    }

    /**
     * @return the section
     */
    public Section getSection() {
        return section;
    }

    /**
     * @param section the section to set
     */
    public void setSection(Section section) {
        this.section = section;
    }

    /**
     * @return the objectName
     */
    public ObjectName getObjectName() {
        return objectName;
    }

    /**
     * @param objectName the objectName to set
     */
    public void setObjectName(ObjectName objectName) {
        this.objectName = objectName;
    }
}
