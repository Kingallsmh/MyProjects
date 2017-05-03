/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.player;

/**
 *
 * @author Kyle
 */
public abstract class BaseControl {
    boolean up;
    boolean left;
    boolean right;
    boolean down;
    boolean button1;
    boolean button2;
    boolean button3;
    boolean button4;
    boolean buttonStart;
    boolean button6;
    
    public abstract void ListenForInput();

    /**
     * @return the up
     */
    public boolean isUp() {
        return up;
    }

    /**
     * @return the left
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public boolean isRight() {
        return right;
    }

    /**
     * @return the down
     */
    public boolean isDown() {
        return down;
    }

    /**
     * @return the button1
     */
    public boolean isButton1() {
        return button1;
    }

    /**
     * @return the button2
     */
    public boolean isButton2() {
        return button2;
    }

    /**
     * @return the button3
     */
    public boolean isButton3() {
        return button3;
    }

    /**
     * @param button3 the button3 to set
     */
    public void setButton3(boolean button3) {
        this.button3 = button3;
    }

    /**
     * @return the button4
     */
    public boolean isButton4() {
        return button4;
    }

    /**
     * @param button4 the button4 to set
     */
    public void setButton4(boolean button4) {
        this.button4 = button4;
    }

    /**
     * @return the buttonStart
     */
    public boolean isButtonStart() {
        return buttonStart;
    }

    /**
     * @param button5 the buttonStart to set
     */
    public void setButtonStart(boolean button5) {
        this.buttonStart = button5;
    }

    /**
     * @return the button6
     */
    public boolean isButton6() {
        return button6;
    }

    /**
     * @param button6 the button6 to set
     */
    public void setButton6(boolean button6) {
        this.button6 = button6;
    }
}
