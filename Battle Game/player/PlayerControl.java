/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author Kyle
 */
public class PlayerControl extends BaseControl{
    Controller controller;
    boolean northEast = false;
    boolean northWest = false;
    boolean southEast = false;
    boolean southWest = false;
    
    //Should find a way to reconnect or disconnect
    public PlayerControl(){        
        if(Controllers.getControllers().size == 0)
        {
            
        }
        else{
            controller = Controllers.getControllers().first();
        }
    }
    
    @Override
    public void ListenForInput(){
        if(controller == null){
            up = Gdx.input.isKeyPressed(Keys.W);
            down = Gdx.input.isKeyPressed(Keys.S);
            left = Gdx.input.isKeyPressed(Keys.A);
            right = Gdx.input.isKeyPressed(Keys.D);
            button1 = Gdx.input.isKeyPressed(Keys.V);
            button2 = Gdx.input.isKeyPressed(Keys.B);
            button3 = Gdx.input.isKeyPressed(Keys.N);
            button4 = Gdx.input.isKeyPressed(Keys.M);
            buttonStart = Gdx.input.isKeyPressed(Keys.SPACE);
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
