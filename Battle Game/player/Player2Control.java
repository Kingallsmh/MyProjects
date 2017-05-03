/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;

/**
 *
 * @author Kyle
 */
public class Player2Control extends BaseControl{
     boolean hasControllers = true;
    Controller controller;
    boolean northEast = false;
    boolean northWest = false;
    boolean southEast = false;
    boolean southWest = false;
    
    public Player2Control(){        
        if(Controllers.getControllers().size <= 1 || Controllers.getControllers().get(1) == null)
        {
            hasControllers = false;
        }
        else{
            controller = Controllers.getControllers().get(1);
        }
    }
    
    @Override
    public void ListenForInput() {
        if(!hasControllers){
            up = Gdx.input.isKeyPressed(Keys.UP);
            down = Gdx.input.isKeyPressed(Keys.DOWN);
            left = Gdx.input.isKeyPressed(Keys.LEFT);
            right = Gdx.input.isKeyPressed(Keys.RIGHT);
            button1 = Gdx.input.isKeyPressed(Keys.NUMPAD_0);
            button2 = Gdx.input.isKeyPressed(Keys.NUMPAD_1);
            button3 = Gdx.input.isKeyPressed(Keys.NUMPAD_2);
            button4 = Gdx.input.isKeyPressed(Keys.NUMPAD_3); 
            buttonStart = Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT);  
        }
        else{
            up = controller.getPov(0) == PovDirection.north;
            northEast = controller.getPov(0) == PovDirection.northEast;
            northWest = controller.getPov(0) == PovDirection.northWest;
            down = controller.getPov(0) == PovDirection.south;
            southEast = controller.getPov(0) == PovDirection.southEast;
            southWest = controller.getPov(0) == PovDirection.southWest;
            left = controller.getPov(0) == PovDirection.west;
            right = controller.getPov(0) == PovDirection.east;
            
            if(northEast == true){
                up = true;
                right = true;
            }
            else if(northWest == true){
                up = true;
                left = true;
            }
            else if(southEast == true){
                down = true;
                right = true;
            }
            else if(southWest == true){
                down = true;
                left = true;
            }
            
            button1 = controller.getButton(0);
            button2 = controller.getButton(1);
            button3 = controller.getButton(2);
            button4 = controller.getButton(3);
            buttonStart = controller.getButton(9);
        }
    }
    
}
