/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.world;

import com.mb.entities.overworld.WorldCharacter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class DungeonSaveOperator implements Serializable{
    private ArrayList<Block[][]> blockList;
    private ArrayList<Block> detailList;
    private ArrayList<WorldCharacter> charaList;

    public DungeonSaveOperator(DungeonLayer d){
        this.blockList = d.blockList;
        this.detailList = d.detailList;
        //and so on...
    }
    
    /**
     * @return the blockList
     */
    public ArrayList<Block[][]> getBlockList() {
        return blockList;
    }

    /**
     * @param blockList the blockList to set
     */
    public void setBlockList(ArrayList<Block[][]> blockList) {
        this.blockList = blockList;
    }

    /**
     * @return the detailList
     */
    public ArrayList<Block> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(ArrayList<Block> detailList) {
        this.detailList = detailList;
    }

    /**
     * @return the charaList
     */
    public ArrayList<WorldCharacter> getCharaList() {
        return charaList;
    }

    /**
     * @param charaList the charaList to set
     */
    public void setCharaList(ArrayList<WorldCharacter> charaList) {
        this.charaList = charaList;
    }
}
