/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.objects;

/**
 *
 * @author Kyle
 */
public abstract class Controller {
    public boolean up, down, right, left, atk, jump;
    
    public abstract void PressRight();
    public abstract void PressLeft();
    public abstract void PressDown();
    public abstract void PressUp();
    public abstract void PressAtk();
    public abstract void PressJump();
}
