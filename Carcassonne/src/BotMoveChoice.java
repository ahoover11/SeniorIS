/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andy Hoover, The College of Wooster
 */
public class BotMoveChoice {
    
    private placedTile tile;
    private int score;
    
    public BotMoveChoice(placedTile t, int s){
        tile = t;
        score = s;
    }
    
    public placedTile getTile(){
        return tile;
    }
    
    public void setTile(placedTile t){
        tile = t;
    }
    
    public int getScore(){
        return score;
    }
    
    public void setScore(int s){
        score = s;
    }
    
}
