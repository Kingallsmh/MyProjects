/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Kyle
 */
public class BoardScreen implements Screen{
    SpriteBatch batch;
    OrthographicCamera cam;
    ShapeRenderer testing;
    BitmapFont font;
    public Vector3 vector;
    
    //Board properties
    Texture board;
    Texture winner;
    Texture loser;
    ArrayList<Integer> code;
    ArrayList<Pegs> btnList;
    ArrayList<Rows> rows;
    Pegs choice;
    int round = 1;
    int numCorrect = 0;
    int numAlmost = 0;
    
    Pegs spot1;
    Pegs spot2;
    Pegs spot3;
    Pegs spot4;
    
    //Piece choices
    Pegs redBtn;
    Pegs blueBtn;
    Pegs greenBtn;
    Pegs yellowBtn;
    Pegs orangeBtn;
    Pegs purpleBtn;
    Pegs brownBtn;
    Pegs grayBtn;
    Rectangle inputBtnBox;
    Rectangle againBtnBox;
    
    //Pieces properties
    GamePieces gp;
    boolean isPlaying = true;
    float s = 16;//size of piece
    int win = 0;
    
    public BoardScreen(MainGame mg){
        this.batch = mg.batch;
        this.cam = mg.cam;
        this.gp = new GamePieces();
        vector = new Vector3();
        code = new ArrayList<Integer>();
        btnList = new ArrayList<Pegs>();
        rows = new ArrayList<Rows>();
        font = new BitmapFont();
        
        //Init the btns ***************
        float x = 16;
        float y = 31;
        redBtn = new Pegs(x, y, gp.redP, 1);
        blueBtn = new Pegs(x + s, y, gp.blueP, 2);
        greenBtn = new Pegs(x + (s*2), y, gp.greenP, 3);
        yellowBtn = new Pegs(x + (s*3), y, gp.yellowP, 4);
        orangeBtn = new Pegs(x, y - s, gp.orangeP, 5);
        purpleBtn = new Pegs(x + s, y - s, gp.purpleP, 6);
        brownBtn = new Pegs(x + (s*2), y - s, gp.brownP, 7);
        grayBtn = new Pegs(x + (s*3), y - s, gp.grayP, 8);
        inputBtnBox = new Rectangle(82, 30, 32, 16);
        againBtnBox = new Rectangle(64-16, 100, 32, 16);
        choice = new Pegs(0, 0, gp, 0);
        
        btnList.add(redBtn);
        btnList.add(blueBtn);
        btnList.add(greenBtn);
        btnList.add(yellowBtn);
        btnList.add(orangeBtn);
        btnList.add(purpleBtn);
        btnList.add(brownBtn);
        btnList.add(grayBtn);
        
        testing = new ShapeRenderer();
        board = new Texture("Board.png");
        winner = new Texture("Winner.png");
        loser = new Texture("Loser.png");
        
        
        ScrambleCode();
        RoundSetup(round);
        
    }
    
    public void RoundSetup(int round){
        if(round > 1){
            Rows row = new Rows(spot1, spot2, spot3, spot4, numCorrect, numAlmost, round - 1, gp);
            rows.add(row);
        }
        LoseGame();
        EndGame();
        numCorrect = 0;
        numAlmost = 0;
        
        float x = 15;
        float y = 47 - 16;
        spot1 = new Pegs(x, y + (s*round), gp, 0);
        spot2 = new Pegs(x + (s), y + (s*round), gp, 0);
        spot3 = new Pegs(x + (s*2), y + (s*round), gp, 0);
        spot4 = new Pegs(x + (s*3), y + (s*round), gp, 0);
        
    }
    
    public void ScrambleCode(){
        Random rd = new Random();
        for(int i = 0; i < 4; i++){
            int randomNum = rd.nextInt((8 - 1) + 1) + 1;
            code.add(randomNum);
        }
//        code.add(5);
//        code.add(7);
//        code.add(5);
//        code.add(7);
    }
    
    public void PickBox(){
        float x = 16;
        float y = 31;
    
        for (Pegs btnList1 : btnList) {
            batch.draw(btnList1.colourChoice, btnList1.spot.x, btnList1.spot.y);
        }
    }
    
    public void drawEmpty(){
        float x = 16;
        float y = 47;
        for(int r = 0; r < 10; r++){
           for(int i = 0; i < 4; i++){
            //batch.draw(gp.noP, x + (s*i), y + (s*r));
            batch.draw(gp.scoreP, x + 65, y + (s*r));
            } 
        }
    }
    
    public void DetectBtn(){
        for (Pegs btnList1 : btnList) {
            if(btnList1.spot.contains(vector.x, vector.y)){
                testBtns(btnList1.spot);
                choice = btnList1;
            }
        }
    }
    
    public void DetectInput(){
        if(inputBtnBox.contains(vector.x, vector.y)){
            if(spot1.choiceNum > 0 && spot2.choiceNum > 0 && spot3.choiceNum > 0 && spot4.choiceNum > 0){
                //isPressed = true;
                boolean spot1B = false;
                boolean spot2B = false;
                boolean spot3B = false;
                boolean spot4B = false;
                ArrayList<Integer> pos = new ArrayList<Integer>(code);
                //if it is in the right spot as well
                if(spot1.choiceNum == pos.get(0)){
                    numCorrect += 1;
                    pos.set(0, 0);
                    spot1B = true;
                }
                if(spot2.choiceNum == pos.get(1)){
                    numCorrect += 1;
                    pos.set(1, 0);
                    spot2B = true;
                }
                if(spot3.choiceNum == pos.get(2)){
                    numCorrect += 1;
                    pos.set(2, 0);
                    spot3B = true;
                }
                if(spot4.choiceNum == pos.get(3)){
                    numCorrect += 1;
                    pos.set(3, 0);
                    spot4B = true;
                }
                
                //If the first is the right number but wrong spot
                if(!spot1B){
                    int num = 0;
                    boolean next = false;
                    for(int i = 0;i < pos.size();i++){
                        if(spot1.choiceNum == pos.get(i) && !next){
                            pos.set(i, 0);
                            num = 1;
                            next = true;
                        }
                    }
                    numAlmost += num;
                }
                //If the second is the right number but wrong spot
                if(!spot2B){
                    int num = 0;
                    boolean next = false;
                    for(int i = 0;i < pos.size();i++){
                        if(spot2.choiceNum == pos.get(i) && !next){
                            pos.set(i, 0);
                            num = 1;
                            next = true;
                        }
                    }
                    numAlmost += num;
                }
                //If the third is the right number but wrong spot
                if(!spot3B){
                    int num = 0;
                    boolean next = false;
                    for(int i = 0;i < pos.size();i++){
                        if(spot3.choiceNum == pos.get(i) && !next){
                            pos.set(i, 0);
                            num = 1;
                            next = true;
                        }
                    }
                    numAlmost += num;
                }
                //If the fourth is the right number but wrong spot
                if(!spot4B){
                    int num = 0;
                    boolean next = false;
                    for(int i = 0;i < pos.size();i++){
                        if(spot4.choiceNum == pos.get(i) && !next){
                            pos.set(i, 0);
                            num = 1;
                            next = true;
                        }
                    }
                    numAlmost += num;
                }
                //*** End of Checking almost ***
                
                vector.x = 0;
                vector.y = 0;
                round += 1;
                
                RoundSetup(round);
            }
        }
    }//End of DetectInput()
    
    public void EndGame(){
        if(numCorrect == 4){
            isPlaying = false;
            win = 1;
        }
    }
    
    public void LoseGame(){
        if(round > 10){
            isPlaying = false;
            win = 2;
        }
    }
    
    public void WinChicken(){
        if(win == 1){
            batch.draw(winner, 0, 100);
        }
        else if(win == 2){
            batch.draw(loser, 0, 100);
        }
        batch.draw(gp.againBtn, againBtnBox.x, againBtnBox.y);
        //Display the secret code
        for(int i = 0;i < code.size();i++){
            int x = 32;
            int y = 160;
            if(code.get(i) == 1){
                batch.draw(gp.redP, x + (s*i), y);
            }
            if(code.get(i) == 2){
                batch.draw(gp.blueP, x + (s*i), y);
            }
            if(code.get(i) == 3){
                batch.draw(gp.greenP, x + (s*i), y);
            }
            if(code.get(i) == 4){
                batch.draw(gp.yellowP, x + (s*i), y);
            }
            if(code.get(i) == 5){
                batch.draw(gp.orangeP, x + (s*i), y);
            }
            if(code.get(i) == 6){
                batch.draw(gp.purpleP, x + (s*i), y);
            }
            if(code.get(i) == 7){
                batch.draw(gp.brownP, x + (s*i), y);
            }
            if(code.get(i) == 8){
                batch.draw(gp.grayP, x + (s*i), y);
            }
            
        }
        
        
        
        //If the button is pressed
        if(againBtnBox.contains(vector.x, vector.y)){
            Reset();
        }
    }
    
    public void DetectChoice(){
        if(spot1.spot.contains(vector.x, vector.y)){
            spot1.changeSpot(choice.colourChoice, choice.choiceNum);
        }
        if(spot2.spot.contains(vector.x, vector.y)){
            spot2.changeSpot(choice.colourChoice, choice.choiceNum);
        }
        if(spot3.spot.contains(vector.x, vector.y)){
            spot3.changeSpot(choice.colourChoice, choice.choiceNum);
        }
        if(spot4.spot.contains(vector.x, vector.y)){
            spot4.changeSpot(choice.colourChoice, choice.choiceNum);
        }
    }
    
    public void renderObjects(){
        batch.draw(gp.inputBtn, inputBtnBox.x, inputBtnBox.y);
        batch.draw(spot1.colourChoice, spot1.spot.x, spot1.spot.y);
        batch.draw(spot2.colourChoice, spot2.spot.x, spot2.spot.y);
        batch.draw(spot3.colourChoice, spot3.spot.x, spot3.spot.y);
        batch.draw(spot4.colourChoice, spot4.spot.x, spot4.spot.y);
    }
    
    
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
	batch.begin();
	batch.setProjectionMatrix(cam.combined);
        UpdatePostion();
        //First Layer
        batch.draw(board, 0, 0);
        if(isPlaying){
            drawEmpty();
            DetectInput();
            DetectChoice();
            //For testing purposes
//            TestText();
            //***************
            renderObjects();
            for(int i = 0; i < rows.size(); i++){
                rows.get(i).RenderRowsPegs(batch);
            }
            PickBox();
            EndGame();
        }
        else{
            
            WinChicken();
            
        }
        
        //End of drawing
        batch.end();
        //For testing purposes**********************************************
        testing.begin(ShapeRenderer.ShapeType.Line);
        testing.setProjectionMatrix(cam.combined);
        testing.setColor(Color.WHITE);
        DetectBtn();
        testing.end();
    }
    
    public void Reset(){
        isPlaying = true;
        vector = new Vector3();
        code = new ArrayList<Integer>();
        btnList = new ArrayList<Pegs>();
        rows = new ArrayList<Rows>();
        font = new BitmapFont();
        
        //Init the btns ***************
        float x = 16;
        float y = 31;
        redBtn = new Pegs(x, y, gp.redP, 1);
        blueBtn = new Pegs(x + s, y, gp.blueP, 2);
        greenBtn = new Pegs(x + (s*2), y, gp.greenP, 3);
        yellowBtn = new Pegs(x + (s*3), y, gp.yellowP, 4);
        orangeBtn = new Pegs(x, y - s, gp.orangeP, 5);
        purpleBtn = new Pegs(x + s, y - s, gp.purpleP, 6);
        brownBtn = new Pegs(x + (s*2), y - s, gp.brownP, 7);
        grayBtn = new Pegs(x + (s*3), y - s, gp.grayP, 8);
        inputBtnBox = new Rectangle(82, 30, 32, 16);
        againBtnBox = new Rectangle(64-16, 100, 32, 16);
        choice = new Pegs(0, 0, gp, 0);
        
        btnList.add(redBtn);
        btnList.add(blueBtn);
        btnList.add(greenBtn);
        btnList.add(yellowBtn);
        btnList.add(orangeBtn);
        btnList.add(purpleBtn);
        btnList.add(brownBtn);
        btnList.add(grayBtn);
        
        round = 1;
        numCorrect = 0;
        numAlmost = 0;
        win = 0;
        
        ScrambleCode();
        RoundSetup(round);
    }
    
    public void testBtns(Rectangle r){
        testing.rect(r.x, r.y, r.width, r.height);
    }
    
    public void UpdatePostion(){
        if(Gdx.input.isTouched()){
            vector.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(vector);
        }
    }
    
    public void TestText(){
        font.draw(batch, String.valueOf(code), 10, 10);
        font.draw(batch, String.valueOf(numCorrect), 5, 130);
        font.draw(batch, String.valueOf(numAlmost), 5, 100);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
    
}
