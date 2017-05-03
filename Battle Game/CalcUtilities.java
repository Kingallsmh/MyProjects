/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb;

import com.badlogic.gdx.Gdx;

/**
 *
 * @author Kyle
 */
public class CalcUtilities {
    
    public static float CorrectTime(){
        return (Math.min(Gdx.graphics.getDeltaTime(), 1/60f));
    }
}
