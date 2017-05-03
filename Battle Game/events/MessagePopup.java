/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.events;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class MessagePopup {
    int currentMessage = 0;
    ArrayList<String> messageList;
    
    public MessagePopup(){
        
    }
    
    public void Render(SpriteBatch batch, BitmapFont font){
        
    }
    
    public void NextMessage(){
        if(currentMessage < messageList.size()){
            currentMessage ++;
        }
    }
    
    public void AddMessageToList(String message){
        messageList.add(message);
    }
    
    public void RemoveMessageFromList(int num){
        messageList.remove(num);
    }
}
