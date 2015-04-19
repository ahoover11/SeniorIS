import javax.swing.ImageIcon;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andy Hoover, The College of Wooster
 */
public class specialTile {
    
    private ImageIcon tile;
    private int tileNumber;
    private boolean top, right, bottom, left;
    
    public specialTile(ImageIcon tile, int tileNumber, boolean top, boolean right, boolean bottom, boolean left){
        this.tile = tile;
        this.tileNumber = tileNumber;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }
    
    public ImageIcon getTile(){
        return tile;
    }
    
    public void setTile(ImageIcon tile){
        this.tile = tile;
    }
    
    public int getTileNumber(){
        return tileNumber;
    }
    
    public void setTileNumber(int tileNumber){
        this.tileNumber = tileNumber;
    }
    
    public boolean getTop(){
        return top;
    }
    
    public void setTop(boolean top){
        this.top = top;
    }
    
    public boolean getRight(){
        return right;
    }
    
    public void setRight(boolean right){
        this.right = right;
    }
    
    public boolean getBottom(){
        return bottom;
    }
    
    public void setBottom(boolean bottom){
        this.bottom = bottom;
    }
    
    public boolean getLeft(){
        return left;
    }
    
    public void setLeft(boolean left){
        this.left = left;
    }
    
}
