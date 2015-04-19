
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
public class placedTile {
    
    private int xcoord;
    private int ycoord;
    private ImageIcon image;
    private int tileNumber;
    private int rotateValue;
    
    public placedTile(int x, int y, ImageIcon img, int tileNum, int r){
        xcoord = x;
        ycoord = y;
        image = img;
        tileNumber = tileNum;
        rotateValue = r;
    }
    
    public int getXCoord(){
        return xcoord;
    }
    
    public void setXCoord(int x){
        xcoord = x;
    }
    
    public int getYCoord(){
        return ycoord;
    }
    
    public void setYCoord(int y){
        ycoord = y;
    }
    
    public ImageIcon getImage(){
        return image;
    }
    
    public void setImage(ImageIcon img){
        image = img;
    }
    
    public int getTileNumber(){
        return tileNumber;
    }
    
    public void setTileNumber(int tileNum){
        tileNumber = tileNum;
    }
    
    public int getRotateValue(){
        return rotateValue;
    }
    
    public void setRotateValue(int r){
        rotateValue = r;
    }
    
}
