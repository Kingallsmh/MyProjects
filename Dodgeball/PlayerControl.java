/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Kyle
 */
public class PlayerControl implements KeyListener{
    
    private boolean[] keys;
    public boolean up, down, left, right, z, x, esc;
    
    public PlayerControl(){
        keys = new boolean[256];
    }
    
    public void KeyTick(){
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        z = keys[KeyEvent.VK_Z];
        x = keys[KeyEvent.VK_X];
        esc = keys[KeyEvent.VK_ESCAPE];
    }

    @Override
    //Unused
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        //System.out.println("Button Pressed: " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        //System.out.println("Button Released: " + e.getKeyCode());
    }
    
}
