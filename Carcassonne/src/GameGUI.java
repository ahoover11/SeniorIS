import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.lang.Object;
import java.net.URL;
import java.util.Random;
import java.util.Vector;
import java.util.Iterator;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andy Hoover, The College of Wooster
 */

public class GameGUI extends javax.swing.JFrame {
    private ImageIcon[] images1; //Array to store all tile images rotated zero times
    private ImageIcon[] images2; //Array to store all tile images rotated one time
    private ImageIcon[] images3; //Array to store all tile images rotated two times
    private ImageIcon[] images4; //Array to store all tile images rotated three times
    private ImageIcon startTile; //Image of start tile
    private ImageIcon blankTile; //Image of blank tile
    private ImageIcon backTile; //Image of tile back
    private ImageIcon validPlacement1Tile; //Image to indicate valid tile placement location for player 1
    private ImageIcon validPlacement2Tile; //Image to indicate valid tile placement location for player 2
    private ImageIcon currentTileImage; //Image of current tile to be placed / manipulated
    private ImageIcon originalTileImage; //Image of original tile used for meeple circulation
    private int currentTileRow = 0; //Location of selected tile (row)
    private int currentTileColumn = 0; //Location of selected tile (column)
    private int currentTileNumber = 0; //The tile number of the current tile to be placed
    private int minimumRow = 44; //The minimum row that needs to be visited for particular algorithms
    private int maximumRow = 46; //The maximum row that needs to be visited for particular algorithms
    private int minimumColumn = 44; //The minimum column that needs to be visited for particular algorithms
    private int maximumColumn = 46; //The maximum column that needs to be visited for particular algorithms
    private int randomValue = 0; //Value chosen at random to select a random tile
    private int rotate = 1; //Counter for rotating tiles
    private int circulate1 = 1; //Counter for circulating through meeple placements for player 1
    private int circulate2 = 2; //Counter for circulating through meeple placements for player 2
    private int meepleCirculationValue = 0; //Int used to keep track of tiles when circulating through meeple placements
    private int meeplesRemainingPlayer1 = 5; //Meeples remaining for player 1
    private int meeplesRemainingPlayer2 = 5; //Meeples remaining for player 2
    private int score1 = 0; //Score of player 1
    private int score2 = 0; //Score of player 2
    private int tilesRemaining = 71; //Keeps track of tiles remaining
    private int playerTurn = 1; //Value to indicate whose turn it is player1 = 1, player2 = 2
    private boolean showValidTilePlacementLocations = true; //Boolean to indicate if valid tile placement locations should be shown on board
    private boolean isTilePlaced = false; //Boolean to indicate if drawn tile has been placed
    private int[] numberOfEachTile = {4,2,1,3,1,1,2,3,2,3,2,1,2,2,3,5,3,3,3,3,8,9,4,1}; //Total 71 (71 + start tile = 72 total tiles)
    private int[] totalNumberOfEachTile = {3,5,3,3,3,5,5,3,3,5,5,3,3,5,5,3,5,5,9,5,3,3,7,9}; //Indicates the total number of each tile in images arrays
    private int[] meeplePlacementsPerTile = {2,3,2,2,2,3,3,2,2,3,3,2,2,3,3,2,3,3,5,3,2,2,4,5}; //Number of possible meeple placements per tile
    private int[] originalImages = {0,3,8,11,14,17,22,27,30,33,38,43,46,49,54,59,62,67,72,81,86,89,92,99}; //Location of original images in images arrays
    private int[] shieldImages = {0,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0}; //Indicates which tiles have shields on them
    private int[] randomTiles = {0,0,0,0,1,1,2,3,3,3,4,5,6,6,7,7,7,8,8,9,9,9,10,10,11,12,12,13,13,14,14,14,15,15,15,15,15,
                                 16,16,16,17,17,17,18,18,18,19,19,19,20,20,20,20,20,20,20,20,21,21,21,21,21,21,21,21,21,
                                 22,22,22,22,23}; //Array used to store distribuion of tiles for random selection
    private placedTile[][] board = new placedTile[91][91]; //2d-Array that represents current board state
    private Vector<placedTile> cloistersWithMeeples = new Vector<placedTile>(); //Vector to store all cloisters with meeples placed on them for scoring purposes
    private String player1Name; //Name entered for player 1
    private String player2Name; //Name entered for player 2
    private String player1Type; //Type selected for player 1 (Bot or Human)
    private String player2Type; //Type selected for player 2 (Bot or Human)
    private boolean player1BotAutoMove; //Boolean to indictate if bot should automatically select a move if player 1 is a bot
    private boolean player2BotAutoMove; //Boolean to indictate if bot should automatically select a move if player 2 is a bot
    private int tilePlacementScorePlayer1 = 0; //Plausible score obtained by bot tile placement for player 1 for AI
    private int tilePlacementScorePlayer2 = 0; //Plausible score obtained by bot tile placement for player 2 for AI
    private int road0Size = 0; //Length of a normal road for AI
    private int road1Size = 0; //Length of road coming from top side of tile for AI
    private int road2Size = 0; //Length of road coming from right side of tile for AI
    private int road3Size = 0; //Length of road coming from bottom side of tile for AI
    private int road4Size = 0; //Length of road coming from left side of tile for AI
    private int city0Size = 0; //Size of a normal city for AI
    private int city1Size = 0; //Size of city coming from top side of tile for AI
    private int city2Size = 0; //Size of city coming from right side of tile for AI
    private int city3Size = 0; //Size of city coming from bottom side of tile for AI
    private int city4Size = 0; //Size of city coming from left side of tile for AI
    private int cloisterSize = 0; //Size of cloister ie the number of tiles surrounding the cloister for AI
    private boolean road0Unclaimed = false; //Boolean to indicate if normal road is unclaimed for AI
    private boolean road1Unclaimed = false; //Boolean to indicate if road coming from top side of tile is unclaimed for AI
    private boolean road2Unclaimed = false; //Boolean to indicate if road coming from right side of tile is unclaimed for AI
    private boolean road3Unclaimed = false; //Boolean to indicate if road coming from bottom side of tile is unclaimed for AI
    private boolean road4Unclaimed = false; //Boolean to indicate if road coming from left side of tile is unclaimed for AI
    private boolean city0Unclaimed = false; //Boolean to indicate if normal city is unclaimed for AI
    private boolean city1Unclaimed = false; //Boolean to indicate if city coming from top side of tile is unclaimed for AI
    private boolean city2Unclaimed = false; //Boolean to indicate if city coming from right side of tile is unclaimed for AI
    private boolean city3Unclaimed = false; //Boolean to indicate if city coming from bottom  side of tile is unclaimed for AI
    private boolean city4Unclaimed = false; //Boolean to indicate if city coming from left side of tile is unclaimed for AI
    
    //Image folder 1 tile descriptions
    private String[] images1Values = {"1111-0-0-0-00000","1111-1-1-0-00004","1111-1-2-0-00004","1131-0-0-0-00000","1131-1-1-0-00004","1131-1-2-0-00004",
                                      "1131-2-1-0-00300","1131-2-2-0-00300","2222-0-0-1-00000","2222-1-1-1-22220","2222-1-2-1-22220","2212-0-0-0-00000",
                                      "2212-1-1-0-22020","2212-1-2-0-22020","2212-0-0-1-00000","2212-1-1-1-22020","2212-1-2-1-22020","2232-0-0-0-00000",
                                      "2232-1-1-0-22020","2232-1-2-0-22020","2232-2-1-0-00300","2232-2-2-0-00300","2232-0-0-1-00000","2232-1-1-1-22020",
                                      "2232-1-2-1-22020","2232-2-1-1-00300","2232-2-2-1-00300","2112-0-0-0-00000","2112-1-1-0-20020","2112-1-2-0-20020",
                                      "2112-0-0-1-00000","2112-1-1-1-20020","2112-1-2-1-20020","2332-0-0-0-00000","2332-1-1-0-20020","2332-1-2-0-20020",
                                      "2332-2-1-0-03300","2332-2-2-0-03300","2332-0-0-1-00000","2332-1-1-1-20020","2332-1-2-1-20020","2332-2-1-1-03300",
                                      "2332-2-2-1-03300","1212-0-0-0-00000","1212-1-1-0-02020","1212-1-2-0-02020","1212-0-0-1-00000","1212-1-1-1-02020",
                                      "1212-1-2-1-02020","2112-0-0-0-00000","2112-1-1-0-20000","2112-1-2-0-20000","2112-2-1-0-00020","2112-2-2-0-00020",
                                      "2121-0-0-0-00000","2121-1-1-0-20000","2121-1-2-0-20000","2121-2-1-0-00200","2121-2-2-0-00200","2111-0-0-0-00000",
                                      "2111-1-1-0-20000","2111-1-2-0-20000","2133-0-0-0-00000","2133-1-1-0-20000","2133-1-2-0-20000","2133-2-1-0-00330",
                                      "2133-2-2-0-00330","2331-0-0-0-00000","2331-1-1-0-20000","2331-1-2-0-20000","2331-2-1-0-03300","2331-2-2-0-03300",
                                      "2333-0-0-0-00000","2333-1-1-0-20000","2333-1-2-0-20000","2333-2-1-0-00030","2333-2-2-0-00030","2333-3-1-0-00300",
                                      "2333-3-2-0-00300","2333-4-1-0-03000","2333-4-2-0-03000","2313-0-0-0-00000","2313-1-1-0-20000","2313-1-2-0-20000",
                                      "2313-2-1-0-03030","2313-2-2-0-03030","1313-0-0-0-00000","1313-1-1-0-03030","1313-1-2-0-03030","1133-0-0-0-00000",
                                      "1133-1-1-0-00330","1133-1-2-0-00330","1333-0-0-0-00000","1333-1-1-0-00030","1333-1-2-0-00030","1333-2-1-0-00300",
                                      "1333-2-2-0-00300","1333-3-1-0-03000","1333-3-2-0-03000","3333-0-0-0-00000","3333-1-1-0-00030","3333-1-2-0-00030",
                                      "3333-2-1-0-00300","3333-2-2-0-00300","3333-3-1-0-03000","3333-3-2-0-03000","3333-4-1-0-30000","3333-4-2-0-30000"};
    
    //Image folder 2 tile descriptions
    private String[] images2Values = {"1111-0-0-0-00000","1111-1-1-0-00004","1111-1-2-0-00004","1113-0-0-0-00000","1113-1-1-0-00004","1113-1-2-0-00004",
                                      "1113-2-1-0-00030","1113-2-2-0-00030","2222-0-0-1-00000","2222-1-1-1-22220","2222-1-2-1-22220","2221-0-0-0-00000",
                                      "2221-1-1-0-22200","2221-1-2-0-22200","2221-0-0-1-00000","2221-1-1-1-22200","2221-1-2-1-22200","2223-0-0-0-00000",
                                      "2223-1-1-0-22200","2223-1-2-0-22200","2223-2-1-0-00030","2223-2-2-0-00030","2223-0-0-1-00000","2223-1-1-1-22200",
                                      "2223-1-2-1-22200","2223-2-1-1-00030","2223-2-2-1-00030","2211-0-0-0-00000","2211-1-1-0-22000","2211-1-2-0-22000",
                                      "2211-0-0-1-00000","2211-1-1-1-22000","2211-1-2-1-22000","2233-0-0-0-00000","2233-1-1-0-22000","2233-1-2-0-22000",
                                      "2233-2-1-0-00330","2233-2-2-0-00330","2233-0-0-1-00000","2233-1-1-1-22000","2233-1-2-1-22000","2233-2-1-1-00330",
                                      "2233-2-2-1-00330","2121-0-0-0-00000","2121-1-1-0-20200","2121-1-2-0-20200","2121-0-0-1-00000","2121-1-1-1-20200",
                                      "2121-1-2-1-20200","2211-0-0-0-00000","2211-1-1-0-02000","2211-1-2-0-02000","2211-2-1-0-20000","2211-2-2-0-20000",
                                      "1212-0-0-0-00000","1212-1-1-0-02000","1212-1-2-0-02000","1212-2-1-0-00020","1212-2-2-0-00020","1211-0-0-0-00000",
                                      "1211-1-1-0-02000","1211-1-2-0-02000","3213-0-0-0-00000","3213-1-1-0-02000","3213-1-2-0-02000","3213-2-1-0-30030",
                                      "3213-2-2-0-30030","1233-0-0-0-00000","1233-1-1-0-02000","1233-1-2-0-02000","1233-2-1-0-00330","1233-2-2-0-00330",
                                      "3233-0-0-0-00000","3233-1-1-0-02000","3233-1-2-0-02000","3233-2-1-0-30000","3233-2-2-0-30000","3233-3-1-0-00030",
                                      "3233-3-2-0-00030","3233-4-1-0-00300","3233-4-2-0-00300","3231-0-0-0-00000","3231-1-1-0-02000","3231-1-2-0-02000",
                                      "3231-2-1-0-30300","3231-2-2-0-30300","3131-0-0-0-00000","3131-1-1-0-30300","3131-1-2-0-30300","3113-0-0-0-00000",
                                      "3113-1-1-0-30030","3113-1-2-0-30030","3133-0-0-0-00000","3133-1-1-0-30000","3133-1-2-0-30000","3133-2-1-0-00030",
                                      "3133-2-2-0-00030","3133-3-1-0-00300","3133-3-2-0-00300","3333-0-0-0-00000","3333-1-1-0-30000","3333-1-2-0-30000",
                                      "3333-2-1-0-00030","3333-2-2-0-00030","3333-3-1-0-00300","3333-3-2-0-00300","3333-4-1-0-03000","3333-4-2-0-03000"};
                                      
    //Image folder 3 tile descriptions
    private String[] images3Values = {"1111-0-0-0-00000","1111-1-1-0-00004","1111-1-2-0-00004","3111-0-0-0-00000","3111-1-1-0-00004","3111-1-2-0-00004",
                                      "3111-2-1-0-30000","3111-2-2-0-30000","2222-0-0-1-00000","2222-1-1-1-22220","2222-1-2-1-22220","1222-0-0-0-00000",
                                      "1222-1-1-0-02220","1222-1-2-0-02220","1222-0-0-1-00000","1222-1-1-1-02220","1222-1-2-1-02220","3222-0-0-0-00000",
                                      "3222-1-1-0-02220","3222-1-2-0-02220","3222-2-1-0-30000","3222-2-2-0-30000","3222-0-0-1-00000","3222-1-1-1-02220",
                                      "3222-1-2-1-02220","3222-2-1-1-30000","3222-2-2-1-30000","1221-0-0-0-00000","1221-1-1-0-02200","1221-1-2-0-02200",
                                      "1221-0-0-1-00000","1221-1-1-1-02200","1221-1-2-1-02200","3223-0-0-0-00000","3223-1-1-0-02200","3223-1-2-0-02200",
                                      "3223-2-1-0-30030","3223-2-2-0-30030","3223-0-0-1-00000","3223-1-1-1-02200","3223-1-2-1-02200","3223-2-1-1-30030",
                                      "3223-2-2-1-30030","1212-0-0-0-00000","1212-1-1-0-02020","1212-1-2-0-02020","1212-0-0-1-00000","1212-1-1-1-02020",
                                      "1212-1-2-1-02020","1221-0-0-0-00000","1221-1-1-0-00200","1221-1-2-0-00200","1221-2-1-0-02000","1221-2-2-0-02000",
                                      "2121-0-0-0-00000","2121-1-1-0-00200","2121-1-2-0-00200","2121-2-1-0-20000","2121-2-2-0-20000","1121-0-0-0-00000",
                                      "1121-1-1-0-00200","1121-1-2-0-00200","3321-0-0-0-00000","3321-1-1-0-00200","3321-1-2-0-00200","3321-2-1-0-33000",
                                      "3321-2-2-0-33000","3123-0-0-0-00000","3123-1-1-0-00200","3123-1-2-0-00200","3123-2-1-0-30030","3123-2-2-0-30030",
                                      "3323-0-0-0-00000","3323-1-1-0-00200","3323-1-2-0-00200","3323-2-1-0-03000","3323-2-2-0-03000","3323-3-1-0-30000",
                                      "3323-3-2-0-30000","3323-4-1-0-00030","3323-4-2-0-00030","1323-0-0-0-00000","1323-1-1-0-00200","1323-1-2-0-00200",
                                      "1323-2-1-0-03030","1323-2-2-0-03030","1313-0-0-0-00000","1313-1-1-0-03030","1313-1-2-0-03030","3311-0-0-0-00000",
                                      "3311-1-1-0-33000","3311-1-2-0-33000","3313-0-0-0-00000","3313-1-1-0-03000","3313-1-2-0-03000","3313-2-1-0-30000",
                                      "3313-2-2-0-30000","3313-3-1-0-00030","3313-3-2-0-00030","3333-0-0-0-00000","3333-1-1-0-03000","3333-1-2-0-03000",
                                      "3333-2-1-0-30000","3333-2-2-0-30000","3333-3-1-0-00030","3333-3-2-0-00030","3333-4-1-0-00300","3333-4-2-0-00300"};
    
    //Image folder 4 tile descriptions
    private String[] images4Values = {"1111-0-0-0-00000","1111-1-1-0-00004","1111-1-2-0-00004","1311-0-0-0-00000","1311-1-1-0-00004","1311-1-2-0-00004",
                                      "1311-2-1-0-03000","1311-2-2-0-03000","2222-0-0-1-00000","2222-1-1-1-22220","2222-1-2-1-22220","2122-0-0-0-00000",
                                      "2122-1-1-0-20220","2122-1-2-0-20220","2122-0-0-1-00000","2122-1-1-1-20220","2122-1-2-1-20220","2322-0-0-0-00000",
                                      "2322-1-1-0-20220","2322-1-2-0-20220","2322-2-1-0-03000","2322-2-2-0-03000","2322-0-0-1-00000","2322-1-1-1-20220",
                                      "2322-1-2-1-20220","2322-2-1-1-03000","2322-2-2-1-03000","1122-0-0-0-00000","1122-1-1-0-00220","1122-1-2-0-00220",
                                      "1122-0-0-1-00000","1122-1-1-1-00220","1122-1-2-1-00220","3322-0-0-0-00000","3322-1-1-0-00220","3322-1-2-0-00220",
                                      "3322-2-1-0-33000","3322-2-2-0-33000","3322-0-0-1-00000","3322-1-1-1-00220","3322-1-2-1-00220","3322-2-1-1-33000",
                                      "3322-2-2-1-33000","2121-0-0-0-00000","2121-1-1-0-20200","2121-1-2-0-20200","2121-0-0-1-00000","2121-1-1-1-20200",
                                      "2121-1-2-1-20200","1122-0-0-0-00000","1122-1-1-0-00020","1122-1-2-0-00020","1122-2-1-0-00200","1122-2-2-0-00200",
                                      "1212-0-0-0-00000","1212-1-1-0-00020","1212-1-2-0-00020","1212-2-1-0-02000","1212-2-2-0-02000","1112-0-0-0-00000",
                                      "1112-1-1-0-00020","1112-1-2-0-00020","1332-0-0-0-00000","1332-1-1-0-00020","1332-1-2-0-00020","1332-2-1-0-03300",
                                      "1332-2-2-0-03300","3312-0-0-0-00000","3312-1-1-0-00020","3312-1-2-0-00020","3312-2-1-0-33000","3312-2-2-0-33000",
                                      "3332-0-0-0-00000","3332-1-1-0-00020","3332-1-2-0-00020","3332-2-1-0-00300","3332-2-2-0-00300","3332-3-1-0-03000",
                                      "3332-3-2-0-03000","3332-4-1-0-30000","3332-4-2-0-30000","3132-0-0-0-00000","3132-1-1-0-00020","3132-1-2-0-00020",
                                      "3132-2-1-0-30300","3132-2-2-0-30300","3131-0-0-0-00000","3131-1-1-0-30300","3131-1-2-0-30300","1331-0-0-0-00000",
                                      "1331-1-1-0-03300","1331-1-2-0-03300","3331-0-0-0-00000","3331-1-1-0-00300","3331-1-2-0-00300","3331-2-1-0-03000",
                                      "3331-2-2-0-03000","3331-3-1-0-30000","3331-3-2-0-30000","3333-0-0-0-00000","3333-1-1-0-00300","3333-1-2-0-00300",
                                      "3333-2-1-0-03000","3333-2-2-0-03000","3333-3-1-0-30000","3333-3-2-0-30000","3333-4-1-0-00030","3333-4-2-0-00030"};
    
    /************************************************************************************
     * GameGUI constructor
     ************************************************************************************/
    public GameGUI(String player1Name, String player2Name, String player1Type, String player2Type, boolean player1BotAutoMove, boolean player2BotAutoMove) {
        //Set the players' names and types
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Type = player1Type;
        this.player2Type = player2Type;
        this.player1BotAutoMove = player1BotAutoMove;
        this.player2BotAutoMove = player2BotAutoMove;
        
        //Generate image icons for all tiles
        images1 = generateImageIcons(108,1,totalNumberOfEachTile,images1,images1Values);
        images2 = generateImageIcons(108,2,totalNumberOfEachTile,images2,images2Values);
        images3 = generateImageIcons(108,3,totalNumberOfEachTile,images3,images3Values);
        images4 = generateImageIcons(108,4,totalNumberOfEachTile,images4,images4Values);
        
        //Generate unique tiles and set descriptions
        URL imageBlankTile = this.getClass().getClassLoader().getResource("Images/blank_tile.png");
        blankTile = new ImageIcon(imageBlankTile);
        blankTile.setDescription("0000-0-0-0-00000");
        URL imageStartTile = this.getClass().getClassLoader().getResource("Images/tile_start.png");
        startTile = new ImageIcon(imageStartTile);
        startTile.setDescription("2313-0-0-0-00000");
        URL imageBackTile = this.getClass().getClassLoader().getResource("Images/tile_back.png");
        backTile = new ImageIcon(imageBackTile);
        URL imageValidPlacement1Tile = this.getClass().getClassLoader().getResource("Images/validplacement1_tile.png");
        validPlacement1Tile = new ImageIcon(imageValidPlacement1Tile);
        validPlacement1Tile.setDescription("0000-0-0-0-00000");
        URL imageValidPlacement2Tile = this.getClass().getClassLoader().getResource("Images/validplacement2_tile.png");
        validPlacement2Tile = new ImageIcon(imageValidPlacement2Tile);
        validPlacement2Tile.setDescription("0000-0-0-0-00000");
        
        //Turn off show show valid tile placements if both players are bots and both are set to auto select a move
        if((player1Type.equals("Bot-Beginner") || player1Type.equals("Bot-Advanced")) &&
           (player2Type.equals("Bot-Beginner") || player2Type.equals("Bot-Advanced"))){
            if(player1BotAutoMove && player2BotAutoMove){
                showValidTilePlacementLocations = false;
            }
        }
        
        //Initialize the GUI components
        initComponents();
        
        //Setup
        setup();
        
        //Start the Game
        newGame();
    }
    
    /***************************************************************************
    * Method to generate ImageIcons for all tile images used in game
    ***************************************************************************/
    public ImageIcon[] generateImageIcons(int x, int y, int[] totals, ImageIcon[] images, String[] imageValues){
        images = new ImageIcon[x];
        int count = 0;
        for(int i = 0; i<24; i++){
            for(int j = 0; j <totals[i]; j++){
                URL image = this.getClass().getClassLoader().getResource("Images"+y+"/"+"tile"+i+"-"+imageValues[count]+".png");
                images[count] = new ImageIcon(image);
                images[count].setDescription(imageValues[count]);
                count++;
            }
        }
        return images;
    }
    
    /***************************************************************************
    * Method to start a new game
    ***************************************************************************/
    public void newGame(){
        //Set initial values
        currentTileRow = 0; //Location of selected tile (row)
        currentTileColumn = 0; //Location of selected tile (column)
        currentTileNumber = 0; //The tile number of the current tile to be placed
        minimumRow = 44; //The minimum row that needs to be visited for particular algorithms
        maximumRow = 46; //The maximum row that needs to be visited for particular algorithms
        minimumColumn = 44; //The minimum column that needs to be visited for particular algorithms
        maximumColumn = 46; //The maximum column that needs to be visited for particular algorithms
        randomValue = 0; //Value chosen at random to select a random tile
        rotate = 1; //Counter for rotating tiles
        circulate1 = 1; //Counter for circulating through meeple placements for player 1
        circulate2 = 2; //Counter for circulating through meeple placements for player 2
        meepleCirculationValue = 0; //Int used to keep track of tile when circulating meeple placements
        meeplesRemainingPlayer1 = 5; //Meeples remaining for player 1
        meeplesRemainingPlayer2 = 5; //Meeples remaining for player 2
        score1 = 0; //Score of player 1
        score2 = 0; //Score of player 2
        playerTurn = 1; //Value to indicate whose turn it is player1 = 1, player2 = 2
        tilesRemaining = 71; //Keeps track of tiles remaining
        isTilePlaced = false; //Boolean to indicate if drawn tile has been placed
        randomTiles = new int[]{0,0,0,0,1,1,2,3,3,3,4,5,6,6,7,7,7,8,8,9,9,9,10,10,11,12,12,13,13,14,14,14,15,15,15,15,15,
                                16,16,16,17,17,17,18,18,18,19,19,19,20,20,20,20,20,20,20,20,21,21,21,21,21,21,21,21,21,
                                22,22,22,22,23};
        cloistersWithMeeples.clear();
        
        //Fill board data structure and jTableGrid with blank tiles
        for(int row = 0; row <= 90; row++){
            for(int col = 0; col <= 90; col++){
                board[row][col] = new placedTile(row,col,blankTile,0,0);
                jTableGrid.setValueAt(blankTile, row, col);
            }
        }
        
        //Add start tile to data structures and place on board
        board[45][45] = new placedTile(45,45,startTile,0,0);  
        jTableGrid.setValueAt(startTile,45,45);
        
        //Update fields in GUI
        jLabelCurrentTilePicture.setIcon(currentTileImage);
        jTextFieldTilesRemaining.setText(Integer.toString(tilesRemaining));
        jTextFieldScore1.setText(Integer.toString(score1));
        jTextFieldScore2.setText(Integer.toString(score2));
        jTextFieldMeeples1.setText(Integer.toString(meeplesRemainingPlayer1));
        jTextFieldMeeples2.setText(Integer.toString(meeplesRemainingPlayer2));
        jTextFieldPlayer1Move.setText("");
        jTextFieldPlayer2Move.setText("");
        jLabelPlayer1.setForeground(Color.red);
        jTextFieldPlayer1.setText(player1Name);
        jTextFieldPlayer2.setText(player2Name);
        
        nextTurn();
    }
    
    /***************************************************************************
    * Method to setup the jTableGrid and add mouse and key listeners
    ***************************************************************************/
    public void setup(){
        //Remove the column headers from Grid
        jScrollPaneGrid.getColumnHeader().setVisible(false);
        
        //Center the Scroll Pane
        Rectangle bounds = jScrollPaneGrid.getViewport().getViewRect();
        Dimension size = jScrollPaneGrid.getViewport().getViewSize();
        int x = (size.width - bounds.width) / 2;
        int y = (size.height - bounds.height) / 2;
        jScrollPaneGrid.getViewport().setViewPosition(new Point(x, y));
        
        //Set all table cells to handle images
        for(int col = 0; col <= 90; col++){
            jTableGrid.getColumnModel().getColumn(col).setCellRenderer(jTableGrid.getDefaultRenderer(ImageIcon.class));
        }
        
        //Remove key and mouse listeners
        MouseListener[] listeners = jTableGrid.getMouseListeners();
        for (MouseListener l : listeners)
        {
            jTableGrid.removeMouseListener(l);
        }
        KeyListener[] listeners1 = jTableGrid.getKeyListeners();
        for(KeyListener l : listeners1){
            jTableGrid.removeKeyListener(l);
        }
        MouseMotionListener[] listeners2 = jTableGrid.getMouseMotionListeners();
        for(MouseMotionListener l : listeners2){
            jTableGrid.removeMouseMotionListener(l);
        }
        MouseWheelListener[] listeners3 = jTableGrid.getMouseWheelListeners();
        for(MouseWheelListener l : listeners3){
            jTableGrid.removeMouseWheelListener(l);
        }
        
        //Remove other listeners
        jTableGrid.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");
        jTableGrid.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "none");
        jTableGrid.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "none");
        jTableGrid.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_KP_UP, 0), "none");
        jTableGrid.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_KP_DOWN, 0), "none");
        jTableGrid.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_KP_LEFT, 0), "none");
        jTableGrid.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_KP_RIGHT, 0), "none");
        jTableGrid.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "none");
        jTableGrid.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "none");
        jTableGrid.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "none");
        jTableGrid.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "none");
        
        //Set key and mouse listeners
        jTableGrid.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent me) {
            if(SwingUtilities.isLeftMouseButton(me)){
                //Check if tile has been placed
                if(!isTilePlaced){
                    //Check to see if the player is a human
                    if((playerTurn == 1 && player1Type.equals("Human")) || (playerTurn == 2 && player2Type.equals("Human"))){
                        //Set current tile row and column to values of cell clicked on in grid
                        currentTileRow = jTableGrid.rowAtPoint(me.getPoint());
                        currentTileColumn = jTableGrid.columnAtPoint(me.getPoint());
                        //Check to see if cell clicked on contains a blank tile
                        if(jTableGrid.getValueAt(currentTileRow, currentTileColumn) == blankTile ||
                           jTableGrid.getValueAt(currentTileRow, currentTileColumn) == validPlacement1Tile ||
                           jTableGrid.getValueAt(currentTileRow, currentTileColumn) == validPlacement2Tile)
                        {
                            //Check if location is valid for tile placement
                            if(checkValidTilePlacement(currentTileRow, currentTileColumn)){
                                //Update minimum/maximum row/column if needed
                                if(currentTileRow == minimumRow){
                                    minimumRow--;
                                }else if(currentTileRow == maximumRow){
                                    maximumRow++;
                                }
                                if(currentTileColumn == minimumColumn){
                                    minimumColumn--;
                                }else if(currentTileColumn == maximumColumn){
                                    maximumColumn++;
                                }
                                //Add tiles to data structure for storage
                                board[currentTileRow][currentTileColumn] = new placedTile(currentTileRow,currentTileColumn,currentTileImage,currentTileNumber,rotate);
                                //Set cell to current tile image and draw pile image to backtile
                                jTableGrid.setValueAt(currentTileImage, currentTileRow, currentTileColumn);
                                jLabelCurrentTilePicture.setIcon(backTile);
                                //Remove all valid tile placement images from board if there are any
                                removeValidTilePlacementImages();
                                isTilePlaced = true;
                                //Update the move field in the GUI to reflect location of tile placement
                                String placement = "( " + Integer.toString(currentTileRow) + " , " + Integer.toString(currentTileColumn) + " )";
                                if(playerTurn == 1){                           
                                    jTextFieldPlayer1Move.setText(placement);
                                }else{
                                    jTextFieldPlayer2Move.setText(placement);
                                }
                            }         
                        }
                    }
                }
                else if(isTilePlaced){
                    //Check to see if the player is a human
                    if((playerTurn == 1 && player1Type.equals("Human")) || (playerTurn == 2 && player2Type.equals("Human"))){
                        board[currentTileRow][currentTileColumn] = new placedTile(currentTileRow,currentTileColumn,currentTileImage,currentTileNumber,rotate);
                        addToCloistersWithMeeples();
                        reduceMeepleCount();
                        scoreFeatures(currentTileRow,currentTileColumn,false);
                        nextTurn();
                    }
                }
            }
            if(SwingUtilities.isRightMouseButton(me)){
                if(!isTilePlaced){
                    //Check to see if the player is a human
                    if((playerTurn == 1 && player1Type.equals("Human")) || (playerTurn == 2 && player2Type.equals("Human"))){
                        rotateTile();
                    }
                }
                else if(isTilePlaced){
                    //Check to see if the player is a human
                    if((playerTurn == 1 && player1Type.equals("Human")) || (playerTurn == 2 && player2Type.equals("Human"))){
                        circulateMeeples(currentTileRow,currentTileColumn,false);
                    }
                }
            }
        }
        });
        jTableGrid.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //Press the C button on the keyboard to center scroll pane view
                if(e.getKeyCode()==KeyEvent.VK_C){
                    jScrollPaneGrid.getViewport().setViewPosition(new Point(x, y));
                }
                //Press the R button on the keyboard to rotate the current tile
                else if(e.getKeyCode()==KeyEvent.VK_R){ 
                    if(!isTilePlaced){
                        //Check to see if the player is a human
                        if((playerTurn == 1 && player1Type.equals("Human")) || (playerTurn == 2 && player2Type.equals("Human"))){
                            rotateTile();
                        }
                    }
                }
                //Press the M button on the keyboard to circulate through possible meeple placements
                else if(e.getKeyCode()==KeyEvent.VK_M){
                    if(isTilePlaced){
                        //Check to see if the player is a human
                        if((playerTurn == 1 && player1Type.equals("Human")) || (playerTurn == 2 && player2Type.equals("Human"))){
                            circulateMeeples(currentTileRow,currentTileColumn,false);
                        }
                    }
                }
                //Press the S button on the keyboard to select chosen placed tile (meeple or no meeple)
                else if(e.getKeyCode()==KeyEvent.VK_S){
                    if(isTilePlaced){
                        //Check to see if the player is a human
                        if((playerTurn == 1 && player1Type.equals("Human")) || (playerTurn == 2 && player2Type.equals("Human"))){
                            board[currentTileRow][currentTileColumn] = new placedTile(currentTileRow,currentTileColumn,currentTileImage,currentTileNumber,rotate);
                            addToCloistersWithMeeples();
                            reduceMeepleCount();
                            scoreFeatures(currentTileRow,currentTileColumn,false);
                            nextTurn();
                        }
                    }
                }
                //Press the B button on the keyboard to simulate a move for a bot
                else if(e.getKeyCode()==KeyEvent.VK_B){
                    //Check to see if the player is a bot
                    if((playerTurn == 1 && player1Type.equals("Bot-Beginner") && !player1BotAutoMove) || (playerTurn == 2 && player2Type.equals("Bot-Beginner") && !player2BotAutoMove) ||
                       (playerTurn == 1 && player1Type.equals("Bot-Advanced") && !player1BotAutoMove) || (playerTurn == 2 && player2Type.equals("Bot-Advanced") && !player2BotAutoMove)){
                            removeValidTilePlacementImages();
                            botMove();
                    }
                }
                //Press the V button on the keyboard to show all valid tile placement locations
                else if(e.getKeyCode()==KeyEvent.VK_V){
                   //Check to see if the player is a human
                    if((playerTurn == 1 && player1Type.equals("Human")) || (playerTurn == 2 && player2Type.equals("Human"))){
                        Vector<placedTile> validTilePlacements = getValidTilePlacements();
                        setValidTilePlacements(validTilePlacements);
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //Press the C button on the keyboard to center scroll pane view
                if(e.getKeyCode()==KeyEvent.VK_C){
                    jScrollPaneGrid.getViewport().setViewPosition(new Point(x, y));
                }
                //Press the R button on the keyboard to rotate the current tile
                else if(e.getKeyCode()==KeyEvent.VK_R){
                    if(!isTilePlaced){
                        //Check to see if the player is a human
                        if((playerTurn == 1 && player1Type.equals("Human")) || (playerTurn == 2 && player2Type.equals("Human"))){
                            rotateTile();
                        }
                    }
                }
                //Press the M button on the keyboard to circulate through possible meeple placements
                else if(e.getKeyCode()==KeyEvent.VK_M){
                    if(isTilePlaced){
                        //Check to see if the player is a human
                        if((playerTurn == 1 && player1Type.equals("Human")) || (playerTurn == 2 && player2Type.equals("Human"))){
                            circulateMeeples(currentTileRow,currentTileColumn,false);
                        }
                    }
                }
                //Press the S button on the keyboard to select chosen placed tile (meeple or no meeple)
                else if(e.getKeyCode()==KeyEvent.VK_S){ 
                    if(isTilePlaced){
                        //Check to see if the player is a human
                        if((playerTurn == 1 && player1Type.equals("Human")) || (playerTurn == 2 && player2Type.equals("Human"))){
                            board[currentTileRow][currentTileColumn] = new placedTile(currentTileRow,currentTileColumn,currentTileImage,currentTileNumber,rotate);
                            addToCloistersWithMeeples();
                            reduceMeepleCount();
                            scoreFeatures(currentTileRow,currentTileColumn,false);
                            nextTurn();
                        }
                    }
                }
                //Press the B button on the keyboard to simulate a move for a bot
                else if(e.getKeyCode()==KeyEvent.VK_B){
                    //Check to see if the player is a bot
                    if((playerTurn == 1 && player1Type.equals("Bot-Beginner")) || (playerTurn == 2 && player2Type.equals("Bot-Beginner")) ||
                       (playerTurn == 1 && player1Type.equals("Bot-Advanced")) || (playerTurn == 2 && player2Type.equals("Bot-Advanced"))){
                            removeValidTilePlacementImages();
                            botMove();
                    }
                }
                //Press the V button on the keyboard to show all valid tile placement locations
                else if(e.getKeyCode()==KeyEvent.VK_V){
                   if(!isTilePlaced){
                        Vector<placedTile> validTilePlacements = getValidTilePlacements();
                        setValidTilePlacements(validTilePlacements);
                   }
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                
            } 
        });
    }
    
    /***************************************************************************
    * Method to initiate next turn in the game
    ***************************************************************************/
    public void nextTurn(){
        if(tilesRemaining != 0){
            //Tile no longer placed
            isTilePlaced = false;
            
            //Draw random tile and check if any of that particular tile remains
            randomValue = randomTile();
            while(randomTiles[randomValue] == -1){
                randomValue = randomTile();
            }
            randomTiles[randomValue] = -1;
            
            //Decrement tiles remaining
            tilesRemaining--;
            
            //Determine whose turn it is (player 1 or player 2)
            if(tilesRemaining % 2 == 0){
                playerTurn = 1;
                jLabelPlayer1.setForeground(Color.red);
                jLabelPlayer2.setForeground(Color.black);
            }else{
                playerTurn = 2;
                jLabelPlayer1.setForeground(Color.black);
                jLabelPlayer2.setForeground(Color.blue);
            }
            
            //Reset values for rotation and meeple circulation
            rotate = 1;
            circulate1 = 1;
            circulate2 = 2;
            meepleCirculationValue = 0;
            
            //Update fields in GUI
            jLabelCurrentTilePicture.setIcon(currentTileImage);
            jTextFieldTilesRemaining.setText(Integer.toString(tilesRemaining));
            jTextFieldScore1.setText(Integer.toString(score1));
            jTextFieldScore2.setText(Integer.toString(score2));
            jTextFieldMeeples1.setText(Integer.toString(meeplesRemainingPlayer1));
            jTextFieldMeeples2.setText(Integer.toString(meeplesRemainingPlayer2));
            
            //Determine if all valid tile placement locations should automatically be shown on the board
            if(showValidTilePlacementLocations){
               Vector<placedTile> validTilePlacements = getValidTilePlacements();
               setValidTilePlacements(validTilePlacements); 
            }
            
            //Determine if bot should automatically select a move
            if((playerTurn == 1 && player1Type.equals("Bot-Beginner") && player1BotAutoMove) || (playerTurn == 2 && player2Type.equals("Bot-Beginner") && player2BotAutoMove) ||
               (playerTurn == 1 && player1Type.equals("Bot-Advanced") && player1BotAutoMove) || (playerTurn == 2 && player2Type.equals("Bot-Advanced") && player2BotAutoMove)){
                    removeValidTilePlacementImages();
                    botMove();
            }
            
        }else{
            //Score the incomplete features at the end of the game
            removeValidTilePlacementImages();
            scoreEndGameFeatures();
            launchFinalScoreFrame();
        }
    }
    
    /***************************************************************************
    * Method to generate random integer that will be used to select tile
    ***************************************************************************/
    public int randomTile(){
        //Randomly choose a value between 0-70
        Random rand = new Random();
        int lowerbound = 0; //inclusive
        int upperbound = 71; //exclusive
        int random = rand.nextInt(upperbound - lowerbound) + lowerbound;
        
        //Set currentTileNumber, currentTileImage, and originalTileImage
        currentTileNumber = randomTiles[random];
        if(currentTileNumber != -1){
            currentTileImage = images1[originalImages[currentTileNumber]];
            originalTileImage = currentTileImage;
        }
        
        return random;
    }
    
    /************************************************************************************
    * Method to redraw a tile from the pile when the current tile has no valid placement
    ************************************************************************************/
    public void redrawTile(){
        int totalValidTilePlacements = countValidTilePlacements();
        if(tilesRemaining != 0 && isTilePlaced == false && totalValidTilePlacements == 0){
            tilesRemaining++;
            randomTiles[randomValue] = currentTileNumber;
            nextTurn();
        }
    }
    
    /****************************************************************************************
    * Method to count the total number of valid placements for the current tile on the board
    ****************************************************************************************/
    public int countValidTilePlacements(){
        int totalPlacements = 0; //Integer used to reflect the total number of valid tile placements on board for the given tile
        
        //Loop over the board and check all placements for each valid rotation
        for(int row = minimumRow; row <= maximumRow; row++){
            for(int col = minimumColumn; col <= maximumColumn; col++){
                //Make sure the location of tile placement is blank
                if( jTableGrid.getValueAt(row, col) == blankTile  || jTableGrid.getValueAt(row, col) == validPlacement1Tile ||
                    jTableGrid.getValueAt(row, col) == validPlacement2Tile){
                    //Reset values for rotation
                    rotate = 1;
                    //Loop over all valid rotations of tile
                    for(int r = 1; r <= 4; r++){
                        boolean checkTilePlacement = checkValidTilePlacement(row,col);
                        if(checkTilePlacement){
                            //Add original tile to tile placements storage
                            totalPlacements++;
                        }
                        rotateTile();
                    }
                }
            }
        }
        return totalPlacements;
    }
    
    /****************************************************************************
    * Method to get all of the valid placements for the current tile on the board
    *****************************************************************************/
    public Vector<placedTile> getValidTilePlacements(){
        Vector<placedTile> validTilePlacements = new Vector<placedTile>(); //Vector to store all valid tile placements
        
        //Loop over the board and check all placements for each valid rotation
        for(int row = minimumRow; row <= maximumRow; row++){
            for(int col = minimumColumn; col <= maximumColumn; col++){
                //Make sure the location of tile placement is blank
                if( jTableGrid.getValueAt(row, col) == blankTile ){
                    //Reset values for rotation
                    rotate = 1;
                    //Loop over all valid rotations of tile
                    for(int r = 1; r <= 4; r++){
                        boolean checkTilePlacement = checkValidTilePlacement(row,col);
                        if(checkTilePlacement){
                            //Add original tile to tile placements storage
                            validTilePlacements.add(new placedTile(row,col,currentTileImage,currentTileNumber,rotate));
                        }
                        rotateTile();
                    }
                }
            }
        }
        return validTilePlacements;
    }
    
    /****************************************************************************
    * Method to set all of the valid placements for the current tile on the board
    *****************************************************************************/
    public void setValidTilePlacements(Vector<placedTile> tiles){
        Vector<placedTile> validTilePlacements = tiles;
        
        //Loop over all possible tile placement locations and update blank tile with highlighted tile
        if(!validTilePlacements.isEmpty()){
            Iterator it = validTilePlacements.iterator();
            while(it.hasNext()){
                placedTile tile = (placedTile)it.next();
                int row = tile.getXCoord();
                int col = tile.getYCoord();
                if(playerTurn == 1){
                    jTableGrid.setValueAt(validPlacement1Tile,row,col);
                }else{
                    jTableGrid.setValueAt(validPlacement2Tile,row,col);
                }
            }
        }
    }
    
    /****************************************************************************
    * Method to remove all valid tile placement images after placing a tile
    *****************************************************************************/
    public void removeValidTilePlacementImages(){
        //Loop over the board and replace valid placement tiles with blank tiles
        for(int row = minimumRow; row <= maximumRow; row++){
            for(int col = minimumColumn; col <= maximumColumn; col++){
                //Make sure the location of tile placement is blank
                if( jTableGrid.getValueAt(row, col) == validPlacement1Tile || jTableGrid.getValueAt(row, col) == validPlacement2Tile ){
                    jTableGrid.setValueAt(blankTile,row,col);
                }
            }
        }
    }
    
    /***************************************************************************
    * Method to reduce players meeple count if they placed a meeple
    ***************************************************************************/
    public void reduceMeepleCount(){
        int meeplesRemaining = 0; //Reflects number of meeples current player has remaining
        char description[] = currentTileImage.getDescription().toCharArray(); //Description of currentTileImage
        char tileValue = description[5]; //Tile value that indicates if it has a meeple on it (0 = No Meeple)
        
        //Determine which meeple count to check (Player 1 or Player 2)
        if(playerTurn == 1){
            meeplesRemaining = Integer.parseInt(jTextFieldMeeples1.getText());
        }else{
            meeplesRemaining = Integer.parseInt(jTextFieldMeeples2.getText());
        }
        
        //Reduce the meeple count only if the player has meeples remaining and placed a meeple
        if(meeplesRemaining != 0 && tileValue != '0'){
            if(playerTurn == 1){
                meeplesRemainingPlayer1 = meeplesRemaining - 1;
                jTextFieldMeeples1.setText(Integer.toString(meeplesRemainingPlayer1));
            }else{
                meeplesRemainingPlayer2 = meeplesRemaining - 1;
                jTextFieldMeeples2.setText(Integer.toString(meeplesRemainingPlayer2));
            }
        }            
    }
    
    /****************************************************
    * Method to execute a move for a player who is a bot
    ****************************************************/
    public void botMove(){
        Vector<placedTile> validTilePlacements = new Vector<placedTile>(); //Vector to store all valid tile placements
        Vector<BotMoveChoice> allBotMoveChoices = new Vector<BotMoveChoice>(); //Vector to store all possible move choices for a bot
        Vector<BotMoveChoice> bestBotMoveChoices = new Vector<BotMoveChoice>(); //Vector to store all the best move choices for a bot
        Vector<BotMoveChoice> badBotMoveChoices = new Vector<BotMoveChoice>(); //Vector to store all bad move choices for a bot
        placedTile bestTile = new placedTile(0,0,blankTile,0,0); //The best tile placement based on tile score
        int maxScore = 0; //Maximum tile placement score found
        BotMoveChoice bestChoice = new BotMoveChoice(bestTile, maxScore); //The best move choice overall
        char description[] = currentTileImage.getDescription().toCharArray(); //Description of current tile
        int roadCount = 0; //Number of sides with roads on tile
        int cityCount = 0; //Number of sides with cities on tile
        
        
        //Make sure there is a valid tile placement for the bot and if not redraw a tile
        int count = countValidTilePlacements();
        if(count == 0){
            redrawTile();
        }
        
        //Count the number of sides with roads and cities on current tile
        for (int i = 0; i < 4; i++) {
            if (description[i] == '2') {
                cityCount++;
            }else if(description[i] == '3'){
                roadCount++;
            }
        }
        
        //Make sure tile has not been placed already
        if(!isTilePlaced){
            //Check to see that the current player is a bot
            if((playerTurn == 1 && player1Type.equals("Bot-Beginner")) || (playerTurn == 2 && player2Type.equals("Bot-Beginner")) ||
               (playerTurn == 1 && player1Type.equals("Bot-Advanced")) || (playerTurn == 2 && player2Type.equals("Bot-Advanced"))){
                //Loop over the board and check all placements for each valid rotation
                for(int row = minimumRow; row <= maximumRow; row++){
                    for(int col = minimumColumn; col <= maximumColumn; col++){
                        //Make sure the location of tile placement is blank
                        if( jTableGrid.getValueAt(row, col) == blankTile ){
                            //Reset values for rotation
                            rotate = 1;
                            //Loop over all valid rotations of tile
                            for(int r = 1; r <= 4; r++){
                                boolean checkTilePlacement = checkValidTilePlacement(row,col);
                                if(checkTilePlacement){
                                    //Add original tile to tile placements storage
                                    validTilePlacements.add(new placedTile(row,col,currentTileImage,currentTileNumber,rotate));
                                    //Temporarily place tile on board for meeple placement checking
                                    board[row][col] = new placedTile(row,col,currentTileImage,currentTileNumber,rotate);
                                    ImageIcon original = currentTileImage;
                                    //Reset values for meeple circulation
                                    circulate1 = 1;
                                    circulate2 = 2;
                                    meepleCirculationValue = 0;
                                    //Loop over possible meeple placements
                                    for(int c = 1; c < meeplePlacementsPerTile[currentTileNumber]; c++){
                                        boolean checkMeeplePlacement = circulateMeeples(row,col,true);
                                        if(checkMeeplePlacement){
                                            //Add tile with meeple placement to tile placements storage
                                            validTilePlacements.add(new placedTile(row,col,currentTileImage,currentTileNumber,rotate));
                                        }
                                    }
                                    //Remove tile placement on board after meeple placement checking finished for tile
                                    board[row][col] = new placedTile(row,col,blankTile,0,0);
                                    currentTileImage = original;
                                }
                                rotateTile();
                            }
                        }
                    }
                }
                
                //Select a tile placement for a beginner bot
                if((playerTurn == 1 && player1Type.equals("Bot-Beginner")) || (playerTurn == 2 && player2Type.equals("Bot-Beginner"))){
                    int meepleBonusRandom = 0;
                    Random rand1 = new Random();
                    int lowerbound1 = 0; //inclusive
                    int upperbound1 = 101; //exclusive
                    int random1 = rand1.nextInt(upperbound1 - lowerbound1) + lowerbound1;
                    
                    //Determine if a meeple should be placed based on a probability that is effected by the number of meeples the player has remaining
                    if(playerTurn == 1){
                        if(meeplesRemainingPlayer1 == 5){
                            if(random1 % 2 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer1 == 4){
                            if(random1 % 3 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer1 == 3){
                            if(random1 % 4 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer1 == 2){
                            if(random1 % 4 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer1 == 1){
                            if(random1 % 5 == 0){
                                meepleBonusRandom++;
                            }
                        }
                        if(meepleBonusRandom == 0){
                                meepleBonusRandom--;
                        }
                    }else if(playerTurn == 2){
                        if(meeplesRemainingPlayer2 == 5){
                            if(random1 % 2 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer2 == 4){
                            if(random1 % 3 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer2 == 3){
                            if(random1 % 4 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer2 == 2){
                            if(random1 % 4 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer2 == 1){
                            if(random1 % 5 == 0){
                                meepleBonusRandom++;
                            }
                        }
                        if(meepleBonusRandom == 0){
                                meepleBonusRandom--;
                        }
                    }
                    //Loop over all valid tile placements and assign each a score
                    if(!validTilePlacements.isEmpty()){
                        Iterator it = validTilePlacements.iterator();
                        while(it.hasNext()){
                            int totalTileScore = 0; //The total score given to a tile
                            tilePlacementScorePlayer1 = 0; //Plausible score obtained by bot tile placement for player 1
                            tilePlacementScorePlayer2 = 0; //Plausible score obtained by bot tile placement for player 2
                            placedTile tile = (placedTile)it.next(); //Current tile
                            int row = tile.getXCoord(); //Current tile's row
                            int col = tile.getYCoord(); //Current tile's column
                            int t = tile.getTileNumber(); //Current tile's tile number
                            int r = tile.getRotateValue(); //Current tile's rotation value
                            currentTileImage = tile.getImage();
                            char description1[] = currentTileImage.getDescription().toCharArray();
                            
                            //Temporarily place tile on board for score analysis
                            board[row][col] = new placedTile(row,col,currentTileImage,t,r);
                            scoreFeatures(row,col,true);
                            
                            if(playerTurn == 1){
                                int tilePlacementScoreDifference = tilePlacementScorePlayer1 - tilePlacementScorePlayer2;
                                if(tilePlacementScoreDifference == 0){
                                    tilePlacementScoreDifference = tilePlacementScorePlayer1;
                                }
                                totalTileScore = tilePlacementScoreDifference;
                                if(description1[5] != '0'){
                                    totalTileScore += meepleBonusRandom;
                                }
                                allBotMoveChoices.add(new BotMoveChoice(tile,totalTileScore));
                            }else if(playerTurn == 2){
                                int tilePlacementScoreDifference = tilePlacementScorePlayer2 - tilePlacementScorePlayer1;
                                if(tilePlacementScoreDifference == 0){
                                    tilePlacementScoreDifference = tilePlacementScorePlayer2;
                                }
                                totalTileScore = tilePlacementScoreDifference;
                                if(description1[5] != '0'){
                                    totalTileScore += meepleBonusRandom;
                                }
                                allBotMoveChoices.add(new BotMoveChoice(tile,totalTileScore));
                            }
                            
                            //Remove temporary tile placement from above
                            board[row][col] = new placedTile(row,col,blankTile,0,0);
                        }
                    }
                    
                    //Loop over all possible move choices and sort them
                    if(!allBotMoveChoices.isEmpty()){
                        Iterator it = allBotMoveChoices.iterator();
                        while(it.hasNext()){
                            BotMoveChoice choice = (BotMoveChoice)it.next();
                            int score = choice.getScore();
                            if(score > maxScore){
                                maxScore = score;
                                bestBotMoveChoices.clear();
                                bestBotMoveChoices.add(choice);
                            }else if(score == maxScore){
                                bestBotMoveChoices.add(choice);
                            }else{
                                badBotMoveChoices.add(choice);
                            }
                        }
                    }
                    
                    //Choose best tile if there is only 1 or choose randomly if more than 1
                    if(!bestBotMoveChoices.isEmpty()){
                        if(bestBotMoveChoices.size() == 1){
                            bestTile = bestBotMoveChoices.firstElement().getTile();
                            maxScore = bestBotMoveChoices.firstElement().getScore();
                        }else{
                            rand1 = new Random();
                            lowerbound1 = 0; //inclusive
                            upperbound1 = bestBotMoveChoices.size(); //exclusive
                            random1 = rand1.nextInt(upperbound1 - lowerbound1) + lowerbound1;
                            bestTile = bestBotMoveChoices.get(random1).getTile();
                            maxScore = bestBotMoveChoices.get(random1).getScore();
                        }
                    }else{
                        bestTile = badBotMoveChoices.firstElement().getTile();
                        maxScore = badBotMoveChoices.firstElement().getScore();
                        Iterator its = badBotMoveChoices.iterator();
                        while(its.hasNext()){
                            BotMoveChoice choice = (BotMoveChoice)its.next();
                            int score = choice.getScore();
                            if(score > maxScore){
                                maxScore = score;
                                bestTile = choice.getTile();
                            }
                        }
                    }
                    bestChoice.setTile(bestTile);
                    bestChoice.setScore(maxScore);
                }
                
                //Select a tile placement for an advanced bot
                if((playerTurn == 1 && player1Type.equals("Bot-Advanced")) || (playerTurn == 2 && player2Type.equals("Bot-Advanced"))){
                    int meepleBonusRandom = 0;
                    Random rand = new Random();
                    int lowerbound = 0; //inclusive
                    int upperbound = 101; //exclusive
                    int random = rand.nextInt(upperbound - lowerbound) + lowerbound;
                    //Determine if a meeple should be placed based on a probability that is affected by the number of meeples the player has remaining / tiles remaining
                    if(playerTurn == 1){
                        if(meeplesRemainingPlayer1 == 5){
                            if(random % 2 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer1 == 4){
                            if(random % 3 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer1 == 3){
                            if(random % 4 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer1 == 2){
                            if(random % 4 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer1 == 1){
                            if(random % 5 == 0){
                                meepleBonusRandom++;
                            }
                        }
                        if((tilesRemaining >= 69 || tilesRemaining <= 5) && meepleBonusRandom == 0){
                            meepleBonusRandom++;
                        }  
                        if(meepleBonusRandom == 0){
                                meepleBonusRandom--;
                        }
                    }else if(playerTurn == 2){
                        if(meeplesRemainingPlayer2 == 5){
                            if(random % 2 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer2 == 4){
                            if(random % 3 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer2 == 3){
                            if(random % 4 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer2 == 2){
                            if(random % 4 == 0){
                                meepleBonusRandom++;
                            }
                        }else if(meeplesRemainingPlayer2 == 1){
                            if(random % 5 == 0){
                                meepleBonusRandom++;
                            }
                        }
                        if((tilesRemaining >= 69 || tilesRemaining <= 5) && meepleBonusRandom == 0){
                            meepleBonusRandom++;
                        }
                        if(meepleBonusRandom == 0){
                                meepleBonusRandom--;
                        }
                    }
                    //Loop over all valid tile placements and assign each a score
                    if(!validTilePlacements.isEmpty()){
                        Iterator it = validTilePlacements.iterator();
                        while(it.hasNext()){
                            int meepleBonusFeatureSize = 2; //Bonus value awarded to tiles that build on unclaimed features of a certain size
                            int totalTileScore = 0; //The total score given to a tile
                            tilePlacementScorePlayer1 = 0; //Plausible score obtained by bot tile placement for player 1
                            tilePlacementScorePlayer2 = 0; //Plausible score obtained by bot tile placement for player 2
                            road0Size = 0; //Length of normal road
                            road1Size = 0; //Length of road coming from top side of tile
                            road2Size = 0; //Length of road coming from right side of tile
                            road3Size = 0; //Length of road coming from bottom side of tile
                            road4Size = 0; //Length of road coming from left side of tile
                            city0Size = 0; //Size of normal city
                            city1Size = 0; //Size of city coming from top side of tile
                            city2Size = 0; //Size of city coming from right side of tile
                            city3Size = 0; //Size of city coming from bottom side of tile
                            city4Size = 0; //Size of city coming from left side of tile
                            cloisterSize = 0; //Size of cloister ie the number of tiles surrounding the cloister
                            road0Unclaimed = false; //Boolean to indicate if normal road is unclaimed for AI
                            road1Unclaimed = false; //Boolean to indicate if road coming from top side of tile is unclaimed
                            road2Unclaimed = false; //Boolean to indicate if road coming from right side of tile is unclaimed
                            road3Unclaimed = false; //Boolean to indicate if road coming from bottom side of tile is unclaimed
                            road4Unclaimed = false; //Boolean to indicate if road coming from left side of tile is unclaimed
                            city0Unclaimed = false; //Boolean to indicate if normal city is unclaimed
                            city1Unclaimed = false; //Boolean to indicate if city coming from top side of tile is unclaimed
                            city2Unclaimed = false; //Boolean to indicate if city coming from right side of tile is unclaimed
                            city3Unclaimed = false; //Boolean to indicate if city coming from bottom  side of tile is unclaimed
                            city4Unclaimed = false; //Boolean to indicate if city coming from left side of tile is unclaimed
                            
                            placedTile tile = (placedTile)it.next(); //Current tile
                            int row = tile.getXCoord(); //Current tile's row
                            int col = tile.getYCoord(); //Current tile's column
                            int r = tile.getRotateValue(); //Current tile's rotation value
                            int t = tile.getTileNumber(); //Current tile's tile number
                            currentTileImage = tile.getImage();
                            char description1[] = currentTileImage.getDescription().toCharArray();
                            
                            //Temporarily place tile on board for score analysis
                            board[row][col] = new placedTile(row,col,currentTileImage,t,r);
                            scoreFeatures(row,col,true);
                            
                            //Award a point bonus to tile placements with cloisterSize >= 6
                            if(cloisterSize >= 6 && description1[15] == '4'){
                                totalTileScore += meepleBonusFeatureSize;
                            }
                            
                            //Award a point bonus to tile placements with roadSize >= 4
                            if(roadCount == 1 || roadCount == 2){
                                if(road0Size >= 4 && (description1[11] == '3' || description1[12] == '3' ||
                                   description1[13] == '3' || description1[14] == '3') && road0Unclaimed){
                                    totalTileScore += meepleBonusFeatureSize;
                                }
                                if(meepleBonusRandom == 1 && (description1[11] == '3' || description1[12] == '3' ||
                                   description1[13] == '3' || description1[14] == '3')){
                                    totalTileScore += road0Size;
                                }
                            }else if(roadCount == 3 || roadCount == 4){
                                if(road1Size >= 4 && description1[11] == '3' && road1Unclaimed){
                                    totalTileScore += meepleBonusFeatureSize;
                                }else if(road2Size >= 4 && description1[12] == '3' && road2Unclaimed){
                                    totalTileScore += meepleBonusFeatureSize;
                                }else if(road3Size >= 4 && description1[13] == '3' && road3Unclaimed){
                                    totalTileScore += meepleBonusFeatureSize;
                                }else if(road4Size >= 4 && description1[14] == '3' && road4Unclaimed){
                                    totalTileScore += meepleBonusFeatureSize;
                                }
                                if(meepleBonusRandom == 1 && description1[11] == '3'){
                                    totalTileScore += road1Size;
                                }else if(meepleBonusRandom == 1 && description1[12] == '3'){
                                    totalTileScore += road2Size;
                                }else if(meepleBonusRandom == 1 && description1[13] == '3'){
                                    totalTileScore += road3Size;
                                }else if(meepleBonusRandom == 1 && description1[14] == '3'){
                                    totalTileScore += road4Size;
                                }
                            }
                            
                            //Award a point bonus to tile placements with citySize >= 3
                            if(cityCount > 0 && t != 13 && t != 14){
                                if(city0Size >= 3 && (description1[11] == '2' || description1[12] == '2' ||
                                   description1[13] == '2' || description1[14] == '2') && city0Unclaimed){
                                    totalTileScore += meepleBonusFeatureSize;
                                }
                                if(meepleBonusRandom == 1 && (description1[11] == '2' || description1[12] == '2' ||
                                   description1[13] == '2' || description1[14] == '2')){
                                    totalTileScore += city0Size;
                                }
                            }else if(t == 13 || t == 14){
                                if(city1Size >= 3 && description1[11] == '2' && city1Unclaimed){
                                    totalTileScore += meepleBonusFeatureSize;
                                }else if(city2Size >= 3 && description1[12] == '2' && city2Unclaimed){
                                    totalTileScore += meepleBonusFeatureSize;
                                }else if(city3Size >= 3 && description1[13] == '2' && city3Unclaimed){
                                    totalTileScore += meepleBonusFeatureSize;
                                }else if(city4Size >= 3 && description1[14] == '2' && city4Unclaimed){
                                    totalTileScore += meepleBonusFeatureSize;
                                }
                                if(meepleBonusRandom == 1 && description1[11] == '2'){
                                    totalTileScore += city1Size;
                                }else if(meepleBonusRandom == 1 && description1[12] == '2'){
                                    totalTileScore += city2Size;
                                }else if(meepleBonusRandom == 1 && description1[13] == '2'){
                                    totalTileScore += city3Size;
                                }else if(meepleBonusRandom == 1 && description1[14] == '2'){
                                    totalTileScore += city4Size;
                                }
                            }
                            
                            //Add in points that reflect immediate scoring opportunities
                            if(playerTurn == 1){
                                int tilePlacementScoreDifference = tilePlacementScorePlayer1 - tilePlacementScorePlayer2;
                                if(tilePlacementScoreDifference == 0){
                                   tilePlacementScoreDifference = tilePlacementScorePlayer1; 
                                }
                                totalTileScore += 3*tilePlacementScoreDifference;
                                if(description1[5] != '0'){
                                    totalTileScore += meepleBonusRandom;
                                }
                                totalTileScore += road0Size + road1Size + road2Size + road3Size + road4Size + city0Size + city1Size + city2Size + city3Size + city4Size + cloisterSize;
                                allBotMoveChoices.add(new BotMoveChoice(tile,totalTileScore));
                            }else if(playerTurn == 2){
                                int tilePlacementScoreDifference = tilePlacementScorePlayer2 - tilePlacementScorePlayer1;
                                if(tilePlacementScoreDifference == 0){
                                   tilePlacementScoreDifference = tilePlacementScorePlayer2; 
                                }
                                totalTileScore += 3*tilePlacementScoreDifference;
                                if(description1[5] != '0'){
                                    totalTileScore += meepleBonusRandom;
                                }
                                totalTileScore += road0Size + road1Size + road2Size + road3Size + road4Size + city0Size + city1Size + city2Size + city3Size + city4Size + cloisterSize;
                                allBotMoveChoices.add(new BotMoveChoice(tile,totalTileScore));
                            }
                            
                            //Remove temporary tile placement from above
                            board[row][col] = new placedTile(row,col,blankTile,0,0);
                        }
                    }
                
                    //Loop over all possible move choices and sort them
                    if(!allBotMoveChoices.isEmpty()){
                        Iterator it = allBotMoveChoices.iterator();
                        while(it.hasNext()){
                            BotMoveChoice choice = (BotMoveChoice)it.next();
                            int score = choice.getScore();
                            if(score > maxScore){
                                maxScore = score;
                                bestBotMoveChoices.clear();
                                bestBotMoveChoices.add(choice);
                            }else if(score == maxScore){
                                bestBotMoveChoices.add(choice);
                            }else{
                                badBotMoveChoices.add(choice);
                            }
                        }
                    }
                    
                    //Choose best tile if there is only 1 or choose randomly if more than 1
                    if(!bestBotMoveChoices.isEmpty()){
                        if(bestBotMoveChoices.size() == 1){
                            bestTile = bestBotMoveChoices.firstElement().getTile();
                            maxScore = bestBotMoveChoices.firstElement().getScore();
                        }else{
                            rand = new Random();
                            lowerbound = 0; //inclusive
                            upperbound = bestBotMoveChoices.size(); //exclusive
                            random = rand.nextInt(upperbound - lowerbound) + lowerbound;
                            bestTile = bestBotMoveChoices.get(random).getTile();
                            maxScore = bestBotMoveChoices.get(random).getScore();
                        }
                    }else{
                        bestTile = badBotMoveChoices.firstElement().getTile();
                        maxScore = badBotMoveChoices.firstElement().getScore();
                        Iterator its = badBotMoveChoices.iterator();
                        while(its.hasNext()){
                            BotMoveChoice choice = (BotMoveChoice)its.next();
                            int score = choice.getScore();
                            if(score > maxScore){
                                maxScore = score;
                                bestTile = choice.getTile();
                            }
                        }
                    }
                    bestChoice.setTile(bestTile);
                    bestChoice.setScore(maxScore);
                }
                
                //if(playerTurn == 1){
                    //jTextFieldPlayer1.setText(Integer.toString(bestChoice.getScore()));
                //}else{
                    //jTextFieldPlayer2.setText(Integer.toString(bestChoice.getScore()));
                //}
                
                //Update currentTile fields to the best tile placement attributes
                currentTileRow = bestChoice.getTile().getXCoord();
                currentTileColumn = bestChoice.getTile().getYCoord();
                currentTileImage = bestChoice.getTile().getImage();
                currentTileNumber = bestChoice.getTile().getTileNumber();
                rotate = bestChoice.getTile().getRotateValue();
                
                //Update minimum/maximum row/column if needed
                if (currentTileRow == minimumRow) {
                    minimumRow--;
                }else if (currentTileRow == maximumRow) {
                    maximumRow++;
                }
                if (currentTileColumn == minimumColumn) {
                    minimumColumn--;
                }else if (currentTileColumn == maximumColumn) {
                    maximumColumn++;
                }
                
                //Add tiles to data structure for storage
                board[currentTileRow][currentTileColumn] = bestChoice.getTile();
                //Set cell to current tile image
                jTableGrid.setValueAt(currentTileImage, currentTileRow, currentTileColumn);
                //Update the move field in the GUI to reflect location of tile placement
                String placement = "( " + Integer.toString(currentTileRow) + " , " + Integer.toString(currentTileColumn) + " )";
                
                //Update move field in GUI to reflect tile placement location
                if(playerTurn == 1){                           
                    jTextFieldPlayer1Move.setText(placement);
                }else{
                    jTextFieldPlayer2Move.setText(placement);
                }
                
                //Execute other necessary functions to complete turn
                addToCloistersWithMeeples();
                reduceMeepleCount();
                scoreFeatures(currentTileRow,currentTileColumn,false);
                nextTurn();
            }
        }
    }
    
    
    /***************************************************************************
    * Method to check if valid tile placement
    ***************************************************************************/
    public boolean checkValidTilePlacement(int row, int col){
        boolean checkTop = false; //Boolean topTile check
        boolean checkRight = false; //Boolean rightTile check
        boolean checkBottom = false; //Boolean bottomTile check
        boolean checkLeft = false; //Boolean leftTile check
        int numBlankTiles = 4; //Number of blank tiles surrounding currentTileImage
        char t1,r1,b1,l1; //Surrounding tile values (topTile bottom, rightTile left, bottomTile top, leftTile right)
        char t2,r2,b2,l2; //Current tile values (top, right, bottom, left)
        ImageIcon imageTop = board[row-1][col].getImage(); //Image above currentTileImage
        ImageIcon imageRight = board[row][col+1].getImage(); //Image to the right of currentTileImage
        ImageIcon imageBottom = board[row+1][col].getImage(); //Image below currentTileImage
        ImageIcon imageLeft = board[row][col-1].getImage(); //Image to the left of currentTileImage
       
        //Check topTile, rightTile, bottomTile, leftTile tile to see if blank and if so reduce blank tile count (4 blank tiles indicates illegal placement)
        if(imageTop != blankTile && imageTop != validPlacement1Tile && imageTop != validPlacement2Tile){
            numBlankTiles--;
        }
        if(imageRight != blankTile && imageRight != validPlacement1Tile && imageRight != validPlacement2Tile){
            numBlankTiles--;
        }
        if(imageBottom != blankTile && imageBottom != validPlacement1Tile && imageBottom != validPlacement2Tile){
            numBlankTiles--;
        }
        if(imageLeft != blankTile && imageLeft != validPlacement1Tile && imageLeft != validPlacement2Tile){
            numBlankTiles--;
        }

        //Surrounding tile side values
        t1 = imageTop.getDescription().charAt(2); //Bottom value of topTile
        r1 = imageRight.getDescription().charAt(3); //Left value of rightTile
        b1 = imageBottom.getDescription().charAt(0); //Top value of bottomTile
        l1 = imageLeft.getDescription().charAt(1); //Right value of leftTile
        
        //Current tile side values
        t2 = currentTileImage.getDescription().charAt(0); //CurrentTile top value
        r2 = currentTileImage.getDescription().charAt(1); //CurrentTile right value
        b2 = currentTileImage.getDescription().charAt(2); //CurrentTile left value
        l2 = currentTileImage.getDescription().charAt(3); //CurrentTile bottom value
        
        //Perform checks to see if valid placement
        if(t1 == t2 || t1 == '0'){
            checkTop = true;
        }
        if(r1 == r2 || r1 == '0'){
            checkRight = true;
        }
        if(b1 == b2 || b1 == '0'){
            checkBottom = true;
        }
        if(l1 == l2 || l1 == '0'){
            checkLeft = true;
        }
        
        //Return true if valid and false if not
        if(checkTop == true && checkRight == true && checkBottom == true && checkLeft == true && numBlankTiles <= 3){
            return true;
        }else{
            return false;
        }

    }
    
    /***************************************************************************
    * Method to rotate the currentTileImage before placement
    ***************************************************************************/
    public void rotateTile(){
        //Rotate can only occur before placing tile
        if(!isTilePlaced){
            rotate++;
            
            //Looping structure for rotations
            if(rotate == 5){
                rotate = 1;
            }
            
            //Obtain the description of the currentTileImage
            char description[] = currentTileImage.getDescription().toCharArray();
            char zero = description[0]; //Side description - top
            char one = description[1]; //Side description - right
            char two = description[2]; //Side description - bottom
            char three = description[3]; //Side description - left
            char four = description[4]; //-
            char five = description[5]; //Tile Value
            char six = description[6]; // -
            char seven = description[7]; //Player Number
            char eight = description[8]; //-
            char nine = description[9]; //Shield
            char ten = description[10]; //-
            char eleven = description[11]; //Meeple Location - top
            char twelve = description[12]; //Meeple Location - right
            char thirteen = description[13]; //Meeple Location - bottom
            char fourteen = description[14]; //Meeple Location - left
            char fifteen = description[15]; //Meeple Location - middle
            
            //Modify description to reflect description of rotated tile
            String newDescription = "" + three + zero + one + two + four + five + six + seven + eight + nine + ten + eleven + twelve + thirteen + fourteen + fifteen;
            URL image = this.getClass().getClassLoader().getResource("Images"+rotate+"/"+"tile"+currentTileNumber+"-"+newDescription+".png");
            currentTileImage = new ImageIcon(image);
            currentTileImage.setDescription(newDescription);
            originalTileImage = currentTileImage;
            jLabelCurrentTilePicture.setIcon(currentTileImage);
        }
    }
    
    /***************************************************************************
    * Method to circulate through possible meeple placements
    ***************************************************************************/
    public boolean circulateMeeples(int row, int col, boolean testPlacement){
        int totTiles = totalNumberOfEachTile[currentTileNumber]; //Reflects total tiles needed for circulation for currentTileImage
        boolean check = true; //Boolean used to indicate if the meeple placement is valid
        ImageIcon previousTileImage = currentTileImage; //Store previous image
        
        //Looping structure for meeple circulation (Player 1)
        if(playerTurn == 1){
            if(circulate1 >= totTiles){
                meepleCirculationValue = originalImages[currentTileNumber];
                circulate1 = 1;
            }else{
                meepleCirculationValue = originalImages[currentTileNumber] + circulate1;
                circulate1 = circulate1 + 2;
            }
        }
        
        //Looping structure for meeple circulation (Player 2)
        if(playerTurn == 2){
            if(circulate2 >= totTiles){
                meepleCirculationValue = originalImages[currentTileNumber];
                circulate2 = 2;
            }else{
                meepleCirculationValue = originalImages[currentTileNumber] + circulate2;
                circulate2 = circulate2 + 2;
            }
        }
        
        //Use current rotate value to generate the correct new image during circulation
        if(rotate == 1){
            URL image = this.getClass().getClassLoader().getResource("Images"+rotate+"/"+"tile"+currentTileNumber+"-"+images1Values[meepleCirculationValue]+".png");
            currentTileImage = new ImageIcon(image);
            currentTileImage.setDescription(images1Values[meepleCirculationValue]);
        }
        if(rotate == 2){
            URL image = this.getClass().getClassLoader().getResource("Images"+rotate+"/"+"tile"+currentTileNumber+"-"+images2Values[meepleCirculationValue]+".png");
            currentTileImage = new ImageIcon(image);
            currentTileImage.setDescription(images2Values[meepleCirculationValue]);
        }
        if(rotate == 3){
            URL image = this.getClass().getClassLoader().getResource("Images"+rotate+"/"+"tile"+currentTileNumber+"-"+images3Values[meepleCirculationValue]+".png");
            currentTileImage = new ImageIcon(image);
            currentTileImage.setDescription(images3Values[meepleCirculationValue]);
        }
        if(rotate == 4){
            URL image = this.getClass().getClassLoader().getResource("Images"+rotate+"/"+"tile"+currentTileNumber+"-"+images4Values[meepleCirculationValue]+".png");
            currentTileImage = new ImageIcon(image);
            currentTileImage.setDescription(images4Values[meepleCirculationValue]);
        }
        
        //Obtain description of currentTileImage to determine which isValidMeeple method to call
        char description[] = currentTileImage.getDescription().toCharArray();
        char eleven = description[11]; //Meeple Location - top
        char twelve = description[12]; //Meeple Location - right
        char thirteen = description[13]; //Meeple Location - bottom
        char fourteen = description[14]; //Meeple Location - left
        char fifteen = description[15]; //Meeple Location - middle
        
        //Determine method to call based on description values (2 = city, 3 = road, 4 = cloister)
        if(eleven == '2' || twelve == '2' || thirteen == '2' || fourteen == '2'){
            check = isValidMeeplePlacementCity(row,col);
        }else if(eleven == '3' || twelve == '3' || thirteen == '3' || fourteen == '3'){
            check = isValidMeeplePlacementRoad(row,col);
        }else if(fifteen == '4'){
            check = isValidMeeplePlacementCloister();
        }
        
        //Skip over meeple placement if not a valid placement for the meeple
        if(check == true && testPlacement == false){
            //Update the jTableGrid to reflect current tile
            jTableGrid.setValueAt(currentTileImage, row, col);
        }else if(check == false && testPlacement == false){
            currentTileImage = previousTileImage;
            jTableGrid.setValueAt(currentTileImage, row, col);
        }else if(check == true && testPlacement == true){
            //do nothing
        }else if(check == false && testPlacement == true) {
            //do nothing
        }
        
        return check;
    }
    
    /***************************************************************************
    * Method to check if valid meeple placement on city
    ***************************************************************************/
    public boolean isValidMeeplePlacementCity(int row, int col){
        Queue queue = new LinkedList(); //Data structure to hold city tiles
        boolean hasMeeple = false; //Boolean to keep track if meeple present on direction traveled 
        boolean[][] visited = new boolean[90][90]; //2d-Array to keep track if tile has been visited
        specialTile[][] specialTiles = new specialTile[90][90]; //2d-Array to store special tile's (tile numbers 13 & 14)
        placedTile currentTile = new placedTile(row,col,originalTileImage,currentTileNumber,rotate); //Current tile
        placedTile originalTile = currentTile; //Original tile
        placedTile topTile = board[row-1][col]; //Tile above current tile
        placedTile rightTile = board[row][col+1]; //Tile to right of current tile
        placedTile bottomTile = board[row+1][col]; //Tile below current tile
        placedTile leftTile = board[row][col-1]; //Tile to left of current tile
        int topTileNumber = board[row-1][col].getTileNumber(); //Tile number of topTile
        int rightTileNumber = board[row][col+1].getTileNumber(); //Tile number of rightTile
        int bottomTileNumber = board[row+1][col].getTileNumber(); //Tile number of bottomTile
        int leftTileNumber = board[row][col-1].getTileNumber(); //Tile number of leftTile
        char description[] = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
        char description1[] = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
        char description2[] = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
        char description3[] = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
        char description4[] = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
        char description5[] = currentTileImage.getDescription().toCharArray(); //Description of currentTileImage
        int cityCount1 = 0; //Number of roads on tile above current tile
        int cityCount2 = 0; //Number of roads on tile to right of current tile
        int cityCount3 = 0; //Number of roads on tile below current tile
        int cityCount4 = 0; //Number of roads on tile to left of current tile
        int meeplesRemaining = 0; //Reflects number of meeples current player has remaining
        
        //Check if tile number is 13 or 14 to make sure checking correct tile side
        if(currentTile.getTileNumber() == 13 || currentTile.getTileNumber() == 14){
            description[0] = description5[11];
            description[1] = description5[12];
            description[2] = description5[13];
            description[3] = description5[14];
            
            //Store the special tile indicating which side has already been checked for a meeple
            if(description[0] == '2'){
                specialTiles[row][col] = new specialTile(currentTile.getImage(),currentTile.getTileNumber(),true,false,false,false);
            }else if (description[1] == '2'){
                specialTiles[row][col] = new specialTile(currentTile.getImage(),currentTile.getTileNumber(),false,true,false,false);
            }else if (description[2] == '2'){
                specialTiles[row][col] = new specialTile(currentTile.getImage(),currentTile.getTileNumber(),false,false,true,false);
            }else if (description[3] == '2'){
                specialTiles[row][col] = new specialTile(currentTile.getImage(),currentTile.getTileNumber(),false,false,false,true);
            }
        }
        
        
        //Set current tile to visited
        visited[row][col] = true;
        
        while(!queue.isEmpty() || (currentTile == originalTile)){
            //Check if top of current tile has a city
            if (description[0] == '2' && !visited[row - 1][col]) {
                //Count number of cities on topTile
                for (int i = 0; i < 4; i++) {
                    if (description1[i] == '2') {
                        cityCount1++;
                    }
                }
                
                //If city count is greater than 1, push it on the stack
                if (cityCount1 > 1 && topTile.getTileNumber() != 13 && topTile.getTileNumber() != 14) {
                    queue.add(topTile);
                }
                
                //Check bottom of topTile for meeple
                if (description1[13] == '2') {
                    hasMeeple = true;
                }
                
                //Store the special tile indicating which side has already been checked for a meeple
                if(topTile.getTileNumber() == 13 || topTile.getTileNumber() == 14){
                    if(specialTiles[row - 1][col] == null){
                        specialTiles[row - 1][col] = new specialTile(topTile.getImage(),topTileNumber,false,false,true,false);
                    }else{
                        specialTiles[row - 1][col].setBottom(true);
                    }
                }
                
                //Mark tile as visited
                visited[row-1][col] = true;
            }else if(description[0] == '2' && visited[row - 1][col] && (topTileNumber == 13 || topTileNumber == 14)){
                //Check bottom of topTile for meeple if not already checked
                if(specialTiles[row - 1][col].getBottom() == false){
                    if (description1[13] == '2') {
                        hasMeeple = true;
                    }
                }
            }
            
            //Check if right side of current tile has a city
            if (description[1] == '2' && !visited[row][col + 1]) {
                //Count number of cities on rightTile
                for (int i = 0; i < 4; i++) {
                    if (description2[i] == '2') {
                        cityCount2++;
                    }
                }
                
                //If city count is greater than 1, push it on the stack
                if (cityCount2 > 1 && rightTile.getTileNumber() != 13 && rightTile.getTileNumber() != 14) {
                    queue.add(rightTile);
                }
                
                //Check left side of rightTile for meeple
                if (description2[14] == '2') {
                    hasMeeple = true;
                }
                
                //Store the special tile indicating which side has already been checked for a meeple
                if(rightTile.getTileNumber() == 13 || rightTile.getTileNumber() == 14){
                    if(specialTiles[row][col + 1] == null){
                        specialTiles[row][col + 1] = new specialTile(rightTile.getImage(),rightTileNumber,false,false,false,true);
                    }else{
                        specialTiles[row][col + 1].setLeft(true);
                    }
                }
                
                //Mark tile as visited
                visited[row][col+1] = true;
            }else if(description[1] == '2' && visited[row][col + 1] && (rightTileNumber == 13 || rightTileNumber == 14)){
                //Check left side of rightTile for meeple if not already checked
                if(specialTiles[row][col + 1].getLeft() == false){
                    if (description2[14] == '2') {
                        hasMeeple = true;
                    }
                }
            }
            
            //Check if bottom of current tile has a city
            if (description[2] == '2' && !visited[row + 1][col]) {
                //Count number of cities on bottomTile
                for (int i = 0; i < 4; i++) {
                    if (description3[i] == '2') {
                        cityCount3++;
                    }
                }
                
                //If city count is greater than 1, push it on the stack
                if (cityCount3 > 1 && bottomTile.getTileNumber() != 13 && bottomTile.getTileNumber() != 14) {
                    queue.add(bottomTile);
                }
                
                //Check top of bottomTile for meeple
                if (description3[11] == '2') {
                    hasMeeple = true;
                }
                
                //Store the special tile indicating which side has already been checked for a meeple
                if(bottomTile.getTileNumber() == 13 || bottomTile.getTileNumber() == 14){
                    if(specialTiles[row + 1][col] == null){
                        specialTiles[row + 1][col] = new specialTile(bottomTile.getImage(),bottomTileNumber,true,false,false,false);
                    }else{
                        specialTiles[row + 1][col].setTop(true);
                    }
                }
                
                //Mark tile as visited
                visited[row+1][col] = true;
            }else if(description[2] == '2' && visited[row + 1][col] && (bottomTileNumber == 13 || bottomTileNumber == 14)){
                //Check top of bottomTile for meeple if not already checked
                if(specialTiles[row + 1][col].getTop() == false){
                    if (description3[11] == '2') {
                        hasMeeple = true;
                    }
                }
            }
            
            //Check if left side of current tile has a city
            if (description[3] == '2' && !visited[row][col - 1]) {
                //Count number of cities on leftTile
                for (int i = 0; i < 4; i++) {
                    if (description4[i] == '2') {
                        cityCount4++;
                    }
                }
                
                //If city count is greater than 1, push it on the stack
                if (cityCount4 > 1 && leftTile.getTileNumber() != 13 && leftTile.getTileNumber() != 14) {
                    queue.add(leftTile);
                }
                
                //Check right side of leftTile for meeple
                if (description4[12] == '2') {
                    hasMeeple = true;
                }
                
                //Store the special tile indicating which side has already been checked for a meeple
                if(leftTile.getTileNumber() == 13 || leftTile.getTileNumber() == 14){
                    if(specialTiles[row][col - 1] == null){
                        specialTiles[row][col - 1] = new specialTile(leftTile.getImage(),leftTileNumber,false,true,false,false);
                    }else{
                        specialTiles[row][col - 1].setRight(true);
                    }
                }
                
                //Mark tile as visited
                visited[row][col-1] = true;
            }else if(description[3] == '2' && visited[row][col - 1] && (leftTileNumber == 13 || leftTileNumber == 14)){
                //Check right side of leftTile for meeple if not already checked
                if(specialTiles[row][col - 1].getRight() == false){
                    if (description4[12] == '2') {
                        hasMeeple = true;
                    }
                }
            }
            
            //Remove tile from head of queue
            if(!queue.isEmpty() && (currentTile != originalTile)){
                queue.remove();
            }
            
            //Set currentTile to null
            if(currentTile == originalTile){
                currentTile = null;
            }
            
            //Set new values if queue is not empty
            if(!queue.isEmpty()){
                currentTile = (placedTile)queue.peek(); //Current tile
                row = currentTile.getXCoord(); //Row of current tile
                col = currentTile.getYCoord(); //Collumn of current tile
                topTile = board[row-1][col]; //Tile above current tile
                rightTile = board[row][col+1]; //Tile to right of current tile
                bottomTile = board[row+1][col]; //Tile below current tile
                leftTile = board[row][col-1]; //Tile to left of current tile
                topTileNumber = board[row-1][col].getTileNumber(); //Tile number of topTile
                rightTileNumber = board[row][col+1].getTileNumber(); //Tile number of rightTile
                bottomTileNumber = board[row+1][col].getTileNumber(); //Tile number of bottomTile
                leftTileNumber = board[row][col-1].getTileNumber(); //Tile number of leftTile
                description = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
                description1 = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
                description2 = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
                description3 = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
                description4 = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
                cityCount1 = 0; //Number of roads on tile above current tile
                cityCount2 = 0; //Number of roads on tile to right of current tile
                cityCount3 = 0; //Number of roads on tile below current tile
                cityCount4 = 0; //Number of roads on tile to left of current tile
            }
        }
        
        
        //Determine which meeple count to check (Player 1 or Player 2)
        if(playerTurn == 1){
            meeplesRemaining = Integer.parseInt(jTextFieldMeeples1.getText());
        }else{
            meeplesRemaining = Integer.parseInt(jTextFieldMeeples2.getText());
        }
        
        if(hasMeeple == false){
            if(meeplesRemaining != 0){
               return true; 
            }else{
                return false;
            }      
        }else{
            return false;
        }
        
    }   
    
    /***************************************************************************
    * Method to check if valid meeple placement on road
    ***************************************************************************/
    public boolean isValidMeeplePlacementRoad(int row, int col){
        boolean directionEnd1 = false; //Boolean to keep track of progress on 1st road direction traveled
        boolean directionEnd2 = false; //Boolean to keep track of progress on 2nd road direction traveled
        boolean hasMeeple1 = false; //Boolean to keep track if meeple present on 1st road direction traveled
        boolean hasMeeple2 = false; //Boolean to keep track if meeple present on 2nd road direction traveled
        boolean solve1 = false; //Boolean that reflects presence of meeple on 1st road direction traveled
        boolean solve2 = false; //Boolean that reflects presence of meeple on 2nd road direction traveled
        boolean[][] visited = new boolean[90][90]; //2d-Array to keep track if tile has been visited
        placedTile currentTile = board[row][col]; //Current tile
        placedTile topTile = board[row-1][col]; //Tile above current tile
        placedTile rightTile = board[row][col+1]; //Tile to right of current trrile
        placedTile bottomTile = board[row+1][col]; //Tile below current tile
        placedTile leftTile = board[row][col-1]; //Tile to left of current tile
        char description[] = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
        char description1[] = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
        char description2[] = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
        char description3[] = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
        char description4[] = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
        char description5[] = currentTileImage.getDescription().toCharArray(); //Description of currentTileImage
        int roadCount = 0; //Number of roads when checking if only need to travel in one direction
        int roadCount1 = 0; //Number of roads on tile above current tile
        int roadCount2 = 0; //Number of roads on tile to right of current tile
        int roadCount3 = 0; //Number of roads on tile below current tile
        int roadCount4 = 0; //Number of roads on tile to left of current tile
        int meeplesRemaining = 0; //Reflects number of meeples current player has remaining
        int originalRow = row; //Reflects location of starting row
        int originalColumn = col; //Reflects location of starting column
        
        //Set current tile to visited
        visited[row][col] = true; 
        
        //Count number of roads on original tile
        for (int i = 0; i < 4; i++) {
            if (description[i] == '3') {
                roadCount++;
            }
        }
            
        //If roadCount == 1,3,or 4 then only need to travel one direction
        if(roadCount == 1 || roadCount == 3 || roadCount == 4){
            directionEnd2 = true;
            hasMeeple2 = false;
            //Change description of tile to reflect the correct road that needs traveled (only need for 3 or 4)
            if(roadCount == 3 || roadCount == 4 ){
                description[0] = description5[11];
                description[1] = description5[12];
                description[2] = description5[13];
                description[3] = description5[14];
            }
        }
        
        //Check first direction of road for meeple
        while(directionEnd1 == false) {
            //Check if top of current tile has a road
            if (description[0] == '3' && !visited[row - 1][col]) {
                //Count number of roads on topTile
                for (int i = 0; i < 4; i++) {
                    if (description1[i] == '3') {
                        roadCount1++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount1 != 2) {
                    directionEnd1 = true;
                }
                
                //Check bottom of topTile for meeple
                if (description1[13] == '3') {
                    hasMeeple1 = true;
                }
                row = row - 1;
            }
            
            //Check if right side of current tile has a road
            else if (description[1] == '3' && !visited[row][col + 1]) {
                //Count number of roads on rightTile
                for (int i = 0; i < 4; i++) {
                    if (description2[i] == '3') {
                        roadCount2++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount2 != 2) {
                    directionEnd1 = true;
                }
                
                //Check left side of rightTile for meeple
                if (description2[14] == '3') {
                    hasMeeple1 = true;
                }
                col = col + 1;
            }
            
            //Check if bottom of current tile has a road
            else if (description[2] == '3' && !visited[row + 1][col]) {
                //Count number of roads on bottomTile
                for (int i = 0; i < 4; i++) {
                    if (description3[i] == '3') {
                        roadCount3++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount3 != 2) {
                    directionEnd1 = true;
                }
                
                //Check top of bottomTile for meeple
                if (description3[11] == '3') {
                    hasMeeple1 = true;
                }
                row = row + 1;
            }
            
            //Check if left side of current tile has a road
            else if (description[3] == '3' && !visited[row][col - 1]) {
                //Count number of roads on leftTile
                for (int i = 0; i < 4; i++) {
                    if (description4[i] == '3') {
                        roadCount4++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount4 != 2) {
                    directionEnd1 = true;
                }
                
                //Check right side of leftTile for meeple
                if (description4[12] == '3') {
                    hasMeeple1 = true;
                }
                col = col - 1;
            }else{
                //Break out of a loop
                directionEnd1 = true;
                if (description[0] == '3' && visited[row - 1][col]){
                    //Count number of roads on topTile
                    for (int i = 0; i < 4; i++) {
                        if (description1[i] == '3') {
                        roadCount1++;
                        }
                    }
                    if(roadCount1 == 3 || roadCount1 == 4){
                        //Check bottom of topTile for meeple
                        if (description1[13] == '3') {
                            hasMeeple1 = true;
                        }
                    }
                }else if (description[1] == '3' && visited[row][col + 1]){
                    //Count number of roads on rightTile
                    for (int i = 0; i < 4; i++) {
                        if (description2[i] == '3') {
                            roadCount2++;
                        }
                    }
                    if(roadCount2 == 3 || roadCount2 == 4){
                        //Check left side of rightTile for meeple
                        if (description2[14] == '3') {
                            hasMeeple1 = true;
                        }
                    }
                }else if (description[2] == '3' && visited[row + 1][col]){
                    //Count number of roads on bottomTile
                    for (int i = 0; i < 4; i++) {
                        if (description3[i] == '3') {
                            roadCount3++;
                        }
                    }
                    if(roadCount3 == 3 || roadCount3 == 4){
                        //Check top of bottomTile for meeple
                        if (description3[11] == '3') {
                            hasMeeple1 = true;
                        }
                    }
                }else if (description[3] == '3' && visited[row][col - 1]){
                    //Count number of roads on leftTile
                    for (int i = 0; i < 4; i++) {
                        if (description4[i] == '3') {
                            roadCount4++;
                        }
                    }
                    if(roadCount4 == 3 || roadCount4 == 4){
                        //Check right side of leftTile for meeple
                        if (description4[12] == '3') {
                            hasMeeple1 = true;
                        }
                    }
                }
            }
            visited[row][col] = true;
            currentTile = board[row][col]; //Current tile
            topTile = board[row-1][col]; //Tile above current tile
            rightTile = board[row][col+1]; //Tile to right of current tile
            bottomTile = board[row+1][col]; //Tile below current tile
            leftTile = board[row][col-1]; //Tile to left of current tile
            description = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
            description1 = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
            description2 = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
            description3 = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
            description4 = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
            roadCount = 0; //Number of roads when checking if only need to travel in one direction
            roadCount1 = 0; //Number of roads on tile above current tile
            roadCount2 = 0; //Number of roads on tile to right of current tile
            roadCount3 = 0; //Number of roads on tile below current tile
            roadCount4 = 0; //Number of roads on tile to left of current tile
        }
        
        //Check direction 1 for meeple
        if(directionEnd1 == true){
            if(hasMeeple1 == true){
                solve1 = true;
            }else{
                solve1 = false;
            }
        }
        
        //Return to starting tile
        row = originalRow;
        col = originalColumn;
        currentTile = board[row][col];
        topTile = board[row-1][col];
        rightTile = board[row][col+1];
        bottomTile = board[row+1][col];
        leftTile = board[row][col-1];
        description = currentTile.getImage().getDescription().toCharArray();
        description1 = topTile.getImage().getDescription().toCharArray();
        description2 = rightTile.getImage().getDescription().toCharArray();
        description3 = bottomTile.getImage().getDescription().toCharArray();
        description4 = leftTile.getImage().getDescription().toCharArray();
        roadCount1 = 0;
        roadCount2 = 0;
        roadCount3 = 0;
        roadCount4 = 0;

        
        //Check second direction of road for meeple (if it exists!)
        while(directionEnd2 == false) {
            //Check if top of current tile has a road
            if (description[0] == '3' && !visited[row - 1][col]) {
                //Count number of roads on topTile
                for (int i = 0; i < 4; i++) {
                    if (description1[i] == '3') {
                        roadCount1++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount1 != 2) {
                    directionEnd2 = true;
                }
                
                //Check bottom of topTile for meeple
                if (description1[13] == '3') {
                    hasMeeple2 = true;
                }
                row = row - 1;
            }
            
            //Check if right side of current tile has a road
            else if (description[1] == '3' && !visited[row][col + 1]) {
                //Count number of roads on rightTile
                for (int i = 0; i < 4; i++) {
                    if (description2[i] == '3') {
                        roadCount2++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount2 != 2) {
                    directionEnd2 = true;
                }
                
                //Check left side of rightTile for meeple
                if (description2[14] == '3') {
                    hasMeeple2 = true;
                }
                col = col + 1;
            }
            
            //Check if bottom of current tile has a road
            else if (description[2] == '3' && !visited[row + 1][col]) {
                //Count number of roads on bottomTile
                for (int i = 0; i < 4; i++) {
                    if (description3[i] == '3') {
                        roadCount3++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount3 != 2) {
                    directionEnd2 = true;
                }
                
                //Check top of bottomTile for meeple
                if (description3[11] == '3') {
                    hasMeeple2 = true;
                }
                row = row + 1;
            }
            
            //Check if left side of current tile has a road
            else if (description[3] == '3' && !visited[row][col - 1]) {
                //Count number of roads on leftTile
                for (int i = 0; i < 4; i++) {
                    if (description4[i] == '3') {
                        roadCount4++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount4 != 2) {
                    directionEnd2 = true;
                }
                
                //Check right side of leftTile for meeple
                if (description4[12] == '3') {
                    hasMeeple2= true;
                }
                col = col - 1;
            }else{
                //Break out of a loop
                directionEnd2 = true;
                if (description[0] == '3' && visited[row - 1][col]){
                    //Count number of roads on topTile
                    for (int i = 0; i < 4; i++) {
                        if (description1[i] == '3') {
                        roadCount1++;
                        }
                    }
                    if(roadCount1 == 3 || roadCount1 == 4){
                        //Check bottom of topTile for meeple
                        if (description1[13] == '3') {
                            hasMeeple2 = true;
                        }
                    }
                }else if (description[1] == '3' && visited[row][col + 1]){
                    //Count number of roads on rightTile
                    for (int i = 0; i < 4; i++) {
                        if (description2[i] == '3') {
                            roadCount2++;
                        }
                    }
                    if(roadCount2 == 3 || roadCount2 == 4){
                        //Check left side of rightTile for meeple
                        if (description2[14] == '3') {
                            hasMeeple2 = true;
                        }
                    }
                }else if (description[2] == '3' && visited[row + 1][col]){
                    //Count number of roads on bottomTile
                    for (int i = 0; i < 4; i++) {
                        if (description3[i] == '3') {
                            roadCount3++;
                        }
                    }
                    if(roadCount3 == 3 || roadCount3 == 4){
                        //Check top of bottomTile for meeple
                        if (description3[11] == '3') {
                            hasMeeple2 = true;
                        }
                    }
                }else if (description[3] == '3' && visited[row][col - 1]){
                    //Count number of roads on leftTile
                    for (int i = 0; i < 4; i++) {
                        if (description4[i] == '3') {
                            roadCount4++;
                        }
                    }
                    if(roadCount4 == 3 || roadCount4 == 4){
                        //Check right side of leftTile for meeple
                        if (description4[12] == '3') {
                            hasMeeple2 = true;
                        }
                    }
                }
            }
            visited[row][col] = true;
            currentTile = board[row][col]; //Current tile
            topTile = board[row-1][col]; //Tile above current tile
            rightTile = board[row][col+1]; //Tile to right of current tile
            bottomTile = board[row+1][col]; //Tile below current tile
            leftTile = board[row][col-1]; //Tile to left of current tile
            description = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
            description1 = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
            description2 = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
            description3 = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
            description4 = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
            roadCount = 0; //Number of roads when checking if only need to travel in one direction
            roadCount1 = 0; //Number of roads on tile above current tile
            roadCount2 = 0; //Number of roads on tile to right of current tile
            roadCount3 = 0; //Number of roads on tile below current tile
            roadCount4 = 0; //Number of roads on tile to left of current tile
        }
        
        //Check direction 2 for meeple
        if(directionEnd2 == true){
            if(hasMeeple2 == true){
                solve2 = true;
            }else{
                solve2 = false;
            }
        }
        
        //Determine which meeple count to check (Player 1 or Player 2)
        if(playerTurn == 1){
            meeplesRemaining = Integer.parseInt(jTextFieldMeeples1.getText());
        }else{
            meeplesRemaining = Integer.parseInt(jTextFieldMeeples2.getText());
        }
        
        //If either direction on road contained a meeple (check is false)
        if(solve1 || solve2){
            return false;
        }else{
            if(meeplesRemaining != 0){
                return true;
            }else{
                return false;
            }
        }
    }
    
    /***************************************************************************
    * Method to check if valid meeple placement on cloister
    ***************************************************************************/
    public boolean isValidMeeplePlacementCloister(){
        int meeplesRemaining = 0; //Reflects number of meeples current player has remaining
        
        //Determine which meeple count to check (Player 1 or Player 2)
        if(playerTurn == 1){
            meeplesRemaining = Integer.parseInt(jTextFieldMeeples1.getText());
        }else{
            meeplesRemaining = Integer.parseInt(jTextFieldMeeples2.getText());
        }
        
        //You can place meeple on cloister as long as you have more than 0 meeples remaining
        if(meeplesRemaining != 0){
            return true;
        }else{
            return false;
        }
    }
    
    /***************************************************************************
    * Method to score features of the recently placed tile
    ***************************************************************************/
    public void scoreFeatures(int row, int col, boolean test){
        int roadCount = 0; //Keep track of number of roads on current tile
        int cityCount = 0; //Keep track of the number of cities on tile
        char description[] = currentTileImage.getDescription().toCharArray(); //Description of current tile
        int tileNumber = board[row][col].getTileNumber(); //Tile number of current tile
        
        //Count number of roads on the recently placed tile
        for(int i = 0; i < 4; i++){
            if(description[i] == '3'){
                roadCount++;
            }
            if(description[i] == '2'){
                cityCount++;
            }
        }
        
        //Score tile containing 1 or 2 sides with roads
        if(roadCount == 1 || roadCount == 2){
            road0Size = scoreRoadCount1or2(row,col,false,test);
        }
        
        //Score tile containing 3 or 4 sides with roads
       if(roadCount == 3 || roadCount == 4){
            if(description[0] == '3'){
                road1Size = scoreRoadCount3or4(row,col,0,false,test);
            }
            if(description[1] == '3'){
                road2Size = scoreRoadCount3or4(row,col,1,false,test);
            }
            if(description[2] == '3'){
                road3Size = scoreRoadCount3or4(row,col,2,false,test);
            }
            if(description[3] == '3'){
                road4Size = scoreRoadCount3or4(row,col,3,false,test);
            }
        }
       
       //Score a city if the tile number is not 13 or 14 (normal)
       if(cityCount > 0 && tileNumber != 13 && tileNumber != 14){
           city0Size = scoreCityNormal(row,col,false,test);
       }
       
       //Score a city depending on the tile number (13 or 14 = special)
       if(tileNumber == 13 || tileNumber == 14){
           if(description[0] == '2'){
               city1Size = scoreCitySpecial(row,col,0,false,test);
            }
           if(description[1] == '2'){
               city2Size = scoreCitySpecial(row,col,1,false,test);
           }
           if(description[2] == '2'){
               city3Size = scoreCitySpecial(row,col,2,false,test);
           }
           if(description[3] == '2'){
               city4Size = scoreCitySpecial(row,col,3,false,test);
           }
       }
       
       
        
        //Check all cloisters with meeples on them for completion every turn
        if(test == false){
            if(!cloistersWithMeeples.isEmpty()){
                Iterator it = cloistersWithMeeples.iterator();
                while(it.hasNext()){
                    placedTile tile = (placedTile)it.next();
                    boolean result = scoreCloister(tile,false);
                    if(result == true){
                        it.remove();
                    }
                }
            }
        }else if(test == true && (tileNumber == 0 || tileNumber == 1)){
            cloisterSize = scoreCloisterTest(row,col,currentTileImage);
        }
        
        //Test if bot tile placement would complete a cloister for AI
        if(test == true){
            if(!cloistersWithMeeples.isEmpty()){
                Iterator it = cloistersWithMeeples.iterator();
                while(it.hasNext()){
                    placedTile tile = (placedTile)it.next();
                    int r = tile.getXCoord();
                    int c = tile.getYCoord();
                    scoreCloisterTest(r,c,tile.getImage());
                }
            }
        }
        
    }
    
    /***************************************************************************
    * Method to score incomplete features at the end of the game
    ***************************************************************************/
    public void scoreEndGameFeatures(){
        placedTile tile; //Tile being currently checked for a meeple
        char description[]; //Description of the tile currently being checked for a meeple
        int roadCount = 0; //Keep track of number of roads on tile
        int cityCount = 0; //Keep track of the number of cities on tile
        int tileNumber; //Tile number of current tile
        
        //Loop over board and look for tiles that still have meeples on them
        for(int row = minimumRow; row <= maximumRow; row++){
            for(int col = minimumColumn; col <= maximumColumn; col++){
                tile = board[row][col];
                tileNumber = board[row][col].getTileNumber();
                description = tile.getImage().getDescription().toCharArray();
                roadCount = 0;
                cityCount = 0;
                
                if(description[5] != '0'){
                    //Count number of roads on the recently placed tile
                    for(int i = 0; i < 4; i++){
                        if(description[i] == '3'){
                            roadCount++;
                        }
                        if(description[i] == '2'){
                            cityCount++;
                        }
                    }
                    
                    //Score tile containing 1 or 2 sides with roads
                    if(roadCount == 1 || roadCount == 2){
                        scoreRoadCount1or2(row,col,true,false);
                    }
        
                    //Score tile containing 3 or 4 sides with roads
                    if(roadCount == 3 || roadCount == 4){
                        if(description[0] == '3'){
                            scoreRoadCount3or4(row,col,0,true,false);
                        }
                        if(description[1] == '3'){
                            scoreRoadCount3or4(row,col,1,true,false);
                        }
                        if(description[2] == '3'){
                            scoreRoadCount3or4(row,col,2,true,false);
                        }
                        if(description[3] == '3'){
                            scoreRoadCount3or4(row,col,3,true,false);
                        }
                    }
                    
                    //Score a city depending on the tile number (13 or 14 = special)
                    if(tileNumber == 13 || tileNumber == 14){
                        if(description[0] == '2'){
                            scoreCitySpecial(row,col,0,true,false);
                        }
                        if(description[1] == '2'){
                            scoreCitySpecial(row,col,1,true,false);
                        }
                        if(description[2] == '2'){
                            scoreCitySpecial(row,col,2,true,false);
                        }
                        if(description[3] == '2'){
                            scoreCitySpecial(row,col,3,true,false);
                        }
                    }
                    
                    //Score a city if the tile number is not 13 or 14 (normal)
                    if(cityCount > 0 && tileNumber != 13 && tileNumber != 14){
                        scoreCityNormal(row,col,true,false);
                    }
                    
                    //Score a cloister
                    if(description[15] == '4'){
                        scoreCloister(tile,true);
                    }
                }
            }
        }
        
        //Update fields in GUI
        jTextFieldTilesRemaining.setText(Integer.toString(tilesRemaining));
        jTextFieldScore1.setText(Integer.toString(score1));
        jTextFieldScore2.setText(Integer.toString(score2));
        jTextFieldMeeples1.setText(Integer.toString(meeplesRemainingPlayer1));
        jTextFieldMeeples2.setText(Integer.toString(meeplesRemainingPlayer2));
    }
    
    /**********************************************************************************************
    * Method triggered to score a city when a normal city tile is placed (ie not tile 13 or tile 14)
    ***********************************************************************************************/
    public int scoreCityNormal(int row, int col, boolean endGame, boolean test){
        Queue queue = new LinkedList(); //Data structure to store city pieces
        boolean hasBlankTile = false; //Boolean to keep track if a blank tile is found (ie city not complete)
        int totalCityPieces = 0; //Integer to keep track of the total number of pieces that make up the city
        int totalShields = 0; //Integer to keep track of the total number of shields contained in a city
        int meepleCountPlayer1 = 0; //Integer to keep track of number of meeples player 1 has in the city
        int meepleCountPlayer2 = 0; //Integer to keep track of number of meeples player 2 has in the city
        boolean[][] visited = new boolean[90][90]; //2d-Array to keep track if tile has been visited
        specialTile[][] specialTiles = new specialTile[90][90]; //2d-Array to store special tile's (tile numbers 13 & 14)
        Vector<placedTile> storage = new Vector<placedTile>(); //Vector to store tiles in city being searched that contain meeples
        placedTile currentTile = board[row][col]; //Current tile
        placedTile originalTile = currentTile; //Original tile
        placedTile topTile = board[row-1][col]; //Tile above current tile
        placedTile rightTile = board[row][col+1]; //Tile to right of current tile
        placedTile bottomTile = board[row+1][col]; //Tile below current tile
        placedTile leftTile = board[row][col-1]; //Tile to left of current tile
        int topTileNumber = board[row-1][col].getTileNumber(); //Tile number of topTile
        int rightTileNumber = board[row][col+1].getTileNumber(); //Tile number of rightTile
        int bottomTileNumber = board[row+1][col].getTileNumber(); //Tile number of bottomTile
        int leftTileNumber = board[row][col-1].getTileNumber(); //Tile number of leftTile
        char description[] = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
        char description1[] = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
        char description2[] = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
        char description3[] = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
        char description4[] = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
        int cityCount1 = 0; //Number of cities on tile above current tile
        int cityCount2 = 0; //Number of cities on tile to right of current tile
        int cityCount3 = 0; //Number of cities on tile below current tile
        int cityCount4 = 0; //Number of cities on tile to left of current tile
        
        //Check original tile for meeple
        if(description[11] == '2' || description[12] == '2' || description[13] == '2' || description[14] == '2'){
            //Determine which player's meeple it is
            if(description[7] == '1'){
                meepleCountPlayer1++;
            }else{
                meepleCountPlayer2++;
            }
            
            //Add tile to storage container since it contains a meeple and needs replaced if city completed
            storage.add(currentTile);
        }
        
        //Determine if the city tile contains a shield
        if(description[9] == '1'){
           totalShields++;
        }
        
        //Set current tile to visited
        visited[row][col] = true;
        
        //Add 1 to to totalCityPieces since orginal tile has a city
        totalCityPieces++; 
        
        while(!queue.isEmpty() || (currentTile == originalTile)){
            //Check if top of current tile has a city
            if (description[0] == '2' && !visited[row - 1][col]) {
                //Check if bottom of topTile contains a city
                if(description1[2] == '2'){
                    totalCityPieces++;
                }
                
                //Count number of cities on topTile
                for (int i = 0; i < 4; i++) {
                    if (description1[i] == '2') {
                        cityCount1++;
                    }
                }
                
                //If city count is greater than 1 and its not tile 13 or tile 14, add it to the queue
                if (cityCount1 > 1 && topTile.getTileNumber() != 13 && topTile.getTileNumber() != 14) {
                    queue.add(topTile);
                }
                
                //Check bottom of topTile for meeple
                if (description1[13] == '2') {
                    //Determine which player's meeple it is
                    if(description1[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if city completed
                    storage.add(topTile);
                }
                
                //Determine if the city tile contains a shield
                if(description1[9] == '1'){
                    totalShields++;
                }
                
                //Check if topTile is blank
                if(topTile.getImage() == blankTile){
                    hasBlankTile = true;
                }
                
                //Store the special tile indicating which side has already been checked for a meeple
                if(topTile.getTileNumber() == 13 || topTile.getTileNumber() == 14){
                    if(specialTiles[row - 1][col] == null){
                        specialTiles[row - 1][col] = new specialTile(topTile.getImage(),topTileNumber,false,false,true,false);
                    }else{
                        specialTiles[row - 1][col].setBottom(true);
                    }
                }
                
                //Mark tile as visited
                visited[row-1][col] = true;
            }else if(description[0] == '2' && visited[row - 1][col] && (topTileNumber == 13 || topTileNumber == 14)){
                //Check bottom of topTile for meeple if not already checked
                if(specialTiles[row - 1][col].getBottom() == false){
                    if (description1[13] == '2') {
                        //Determine which player's meeple it is
                        if(description1[7] == '1'){
                            meepleCountPlayer1++;
                        }else{
                            meepleCountPlayer2++;
                        }
                        
                        //Add tile to storage container since it contains a meeple and needs replaced if city completed
                        storage.add(topTile);
                    }
                }
            }
            
            //Check if right side of current tile has a city
            if (description[1] == '2' && !visited[row][col + 1]) {
                //Check if left side of right tile contains a city
                if(description2[3] == '2'){
                    totalCityPieces++;
                }
                
                //Count number of cities on rightTile
                for (int i = 0; i < 4; i++) {
                    if (description2[i] == '2') {
                        cityCount2++;
                    }
                }
                
                //If city count is greater than 1 and its not tile 13 or tile 14, add it to the queue
                if (cityCount2 > 1 && rightTile.getTileNumber() != 13 && rightTile.getTileNumber() != 14) {
                    queue.add(rightTile);
                }
                
                //Check left side of rightTile for meeple
                if (description2[14] == '2') {
                    //Determine which player's meeple it is
                    if(description2[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if city completed
                    storage.add(rightTile);
                }
                
                //Determine if the city tile contains a shield
                if(description2[9] == '1'){
                    totalShields++;
                }
                
                //Check if rightTile is blank
                if(rightTile.getImage() == blankTile){
                    hasBlankTile = true;
                }
                
                //Store the special tile indicating which side has already been checked for a meeple
                if(rightTile.getTileNumber() == 13 || rightTile.getTileNumber() == 14){
                    if(specialTiles[row][col + 1] == null){
                        specialTiles[row][col + 1] = new specialTile(rightTile.getImage(),rightTileNumber,false,false,false,true);
                    }else{
                        specialTiles[row][col + 1].setLeft(true);
                    }
                }
                
                //Mark tile as visited
                visited[row][col+1] = true;
            }else if(description[1] == '2' && visited[row][col + 1] && (rightTileNumber == 13 || rightTileNumber == 14)){
                //Check left side of rightTile for meeple if not already checked
                if(specialTiles[row][col + 1].getLeft() == false){
                    if (description2[14] == '2') {
                        //Determine which player's meeple it is
                        if(description2[7] == '1'){
                            meepleCountPlayer1++;
                        }else{
                            meepleCountPlayer2++;
                        }
                        
                        //Add tile to storage container since it contains a meeple and needs replaced if city completed
                        storage.add(rightTile);
                    }
                }
            }
            
            //Check if bottom of current tile has a city
            if (description[2] == '2' && !visited[row + 1][col]) {
                //Check if top side of bottomTile contains a city
                if(description3[0] == '2'){
                    totalCityPieces++;
                }
                
                //Count number of cities on bottomTile
                for (int i = 0; i < 4; i++) {
                    if (description3[i] == '2') {
                        cityCount3++;
                    }
                }
                
                //If city count is greater than 1 and its not tile 13 or tile 14, add it to the queue
                if (cityCount3 > 1 && bottomTile.getTileNumber() != 13 && bottomTile.getTileNumber() != 14) {
                    queue.add(bottomTile);
                }
                
                //Check top of bottomTile for meeple
                if (description3[11] == '2') {
                    //Determine which player's meeple it is
                    if(description3[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if city completed
                    storage.add(bottomTile);
                }
                
                //Determine if the city tile contains a shield
                if(description3[9] == '1'){
                    totalShields++;
                }
                
                //Check if bottomTile is blank
                if(bottomTile.getImage() == blankTile){
                    hasBlankTile = true;
                }
                
                //Store the special tile indicating which side has already been checked for a meeple
                if(bottomTile.getTileNumber() == 13 || bottomTile.getTileNumber() == 14){
                    if(specialTiles[row + 1][col] == null){
                        specialTiles[row + 1][col] = new specialTile(bottomTile.getImage(),bottomTileNumber,true,false,false,false);
                    }else{
                        specialTiles[row + 1][col].setTop(true);
                    }
                }
                
                //Mark tile as visited
                visited[row+1][col] = true;
            }else if(description[2] == '2' && visited[row + 1][col] && (bottomTileNumber == 13 || bottomTileNumber == 14)){
                //Check top of bottomTile for meeple if not already checked
                if(specialTiles[row + 1][col].getTop() == false){
                    if (description3[11] == '2') {
                        //Determine which player's meeple it is
                        if(description3[7] == '1'){
                            meepleCountPlayer1++;
                        }else{
                            meepleCountPlayer2++;
                        }
                        
                        //Add tile to storage container since it contains a meeple and needs replaced if city completed
                        storage.add(bottomTile);
                    }
                }
            }
            
            //Check if left side of current tile has a city
            if (description[3] == '2' && !visited[row][col - 1]) {
                //Check if right side of leftTile contains a city
                if(description4[1] == '2'){
                    totalCityPieces++;
                }
                
                //Count number of cities on leftTile
                for (int i = 0; i < 4; i++) {
                    if (description4[i] == '2') {
                        cityCount4++;
                    }
                }
                
                //If city count is greater than 1 and its not tile 13 or tile 14, add it to the queue
                if (cityCount4 > 1 && leftTile.getTileNumber() != 13 && leftTile.getTileNumber() != 14) {
                    queue.add(leftTile);
                }
                
                //Check right side of leftTile for meeple
                if (description4[12] == '2') {
                   //Determine which player's meeple it is
                    if(description4[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(leftTile);
                }
                
                //Determine if the city tile contains a shield
                if(description4[9] == '1'){
                    totalShields++;
                }
                
                //Check if leftTile is blank
                if(leftTile.getImage() == blankTile){
                    hasBlankTile = true;
                }
                
                //Store the special tile indicating which side has already been checked for a meeple
                if(leftTile.getTileNumber() == 13 || leftTile.getTileNumber() == 14){
                    if(specialTiles[row][col - 1] == null){
                        specialTiles[row][col - 1] = new specialTile(leftTile.getImage(),leftTileNumber,false,true,false,false);
                    }else{
                        specialTiles[row][col - 1].setRight(true);
                    }
                }
                
                //Mark tile as visited
                visited[row][col-1] = true;
            }else if(description[3] == '2' && visited[row][col - 1] && (leftTileNumber == 13 || leftTileNumber == 14)){
                //Check right side of leftTile for meeple if not already checked
                if(specialTiles[row][col - 1].getRight() == false){
                    if (description4[12] == '2') {
                       //Determine which player's meeple it is
                        if(description4[7] == '1'){
                            meepleCountPlayer1++;
                        }else{
                            meepleCountPlayer2++;
                        }
                        //Add tile to storage container since it contains a meeple and needs replaced if road completed
                        storage.add(leftTile);
                    }
                }
            }
            
            //Remove tile from head of queue
            if(!queue.isEmpty() && (currentTile != originalTile)){
                queue.remove();
            }
            
            //Set currentTile to null
            if(currentTile == originalTile){
                currentTile = null;
            }
            
            //Set new values if queue is not empty
            if(!queue.isEmpty()){
                currentTile = (placedTile)queue.peek(); //Current tile
                row = currentTile.getXCoord(); //Row of current tile
                col = currentTile.getYCoord(); //Collumn of current tile
                topTile = board[row-1][col]; //Tile above current tile
                rightTile = board[row][col+1]; //Tile to right of current tile
                bottomTile = board[row+1][col]; //Tile below current tile
                leftTile = board[row][col-1]; //Tile to left of current tile
                topTileNumber = board[row-1][col].getTileNumber(); //Tile number of topTile
                rightTileNumber = board[row][col+1].getTileNumber(); //Tile number of rightTile
                bottomTileNumber = board[row+1][col].getTileNumber(); //Tile number of bottomTile
                leftTileNumber = board[row][col-1].getTileNumber(); //Tile number of leftTile
                description = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
                description1 = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
                description2 = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
                description3 = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
                description4 = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
                cityCount1 = 0; //Number of roads on tile above current tile
                cityCount2 = 0; //Number of roads on tile to right of current tile
                cityCount3 = 0; //Number of roads on tile below current tile
                cityCount4 = 0; //Number of roads on tile to left of current tile
            }
        }
        
        //Calculate the proper score and allocate it to the correct player if city completed during the game
        if(hasBlankTile == false && endGame == false && test == false){
            if(meepleCountPlayer1 != 0 || meepleCountPlayer2 != 0){
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    score1 = score1 + 2*totalCityPieces + 2*totalShields;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    score2 = score2 + 2*totalCityPieces + 2*totalShields;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    score1 = score1 + 2*totalCityPieces + 2*totalShields;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 < meepleCountPlayer2){
                    score2 = score2 + 2*totalCityPieces + 2*totalShields;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }
                //Loop over storage and replace tiles with meeples on them with original tiles
                for(int i = 0; i < storage.size(); i++){
                    int x = storage.get(i).getXCoord(); //X-coordinate of placed tile
                    int y = storage.get(i).getYCoord(); ////Y-coordinate of placed tile
                    ImageIcon image = storage.get(i).getImage(); //Imageof placed tile
                    description = image.getDescription().toCharArray(); //Description of placed tile
                    int tileNum = storage.get(i).getTileNumber(); //Tile number of placed tile
                    int r = storage.get(i).getRotateValue(); //Rotate value of placed tile
                    
                    
                    String newDescription = ""+description[0]+description[1]+description[2]+description[3]+"-0-0-"+description[9]+"-00000";
                    URL imageNew = this.getClass().getClassLoader().getResource("Images"+r+"/"+"tile"+tileNum+"-"+newDescription+".png");
                    image = new ImageIcon(imageNew);
                    image.setDescription(newDescription);
                    jTableGrid.setValueAt(image, x, y);
                    board[x][y] = new placedTile(x,y,image,tileNum,r);
                }
            }
        }
        
        //Calculate the proper score and allocate it to the correct player for an incomplete city at the end of the game
        if(endGame == true && test == false){
            if(meepleCountPlayer1 != 0 || meepleCountPlayer2 != 0){
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    score1 = score1 + totalCityPieces + totalShields;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    score2 = score2 + totalCityPieces + totalShields;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    score1 = score1 + totalCityPieces + totalShields;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 < meepleCountPlayer2){
                    score2 = score2 + totalCityPieces + totalShields;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }
                //Loop over storage and replace tiles with meeples on them with original tiles
                for(int i = 0; i < storage.size(); i++){
                    int x = storage.get(i).getXCoord(); //X-coordinate of placed tile
                    int y = storage.get(i).getYCoord(); ////Y-coordinate of placed tile
                    ImageIcon image = storage.get(i).getImage(); //Image of placed tile
                    description = image.getDescription().toCharArray(); //Description of placed tile
                    int tileNum = storage.get(i).getTileNumber(); //Tile number of placed tile
                    int r = storage.get(i).getRotateValue(); //Rotate value of placed tile
                    
                    
                    String newDescription = ""+description[0]+description[1]+description[2]+description[3]+"-0-0-"+description[9]+"-00000";
                    URL imageNew = this.getClass().getClassLoader().getResource("Images"+r+"/"+"tile"+tileNum+"-"+newDescription+".png");
                    image = new ImageIcon(imageNew);
                    image.setDescription(newDescription);
                    jTableGrid.setValueAt(image, x, y);
                    board[x][y] = new placedTile(x,y,image,tileNum,r);
                }
            }
        }
        
        //Calculate the possible score for a tile placement
        if(hasBlankTile == false && endGame == false && test == true){
            if(meepleCountPlayer1 != 0 || meepleCountPlayer2 != 0){
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    tilePlacementScorePlayer1 = tilePlacementScorePlayer1 + 2*totalCityPieces + 2*totalShields;
                    tilePlacementScorePlayer2 = tilePlacementScorePlayer2 + 2*totalCityPieces + 2*totalShields;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    tilePlacementScorePlayer1 = tilePlacementScorePlayer1 + 2*totalCityPieces + 2*totalShields;
                }else if(meepleCountPlayer1 < meepleCountPlayer2){
                    tilePlacementScorePlayer2 = tilePlacementScorePlayer2 + 2*totalCityPieces + 2*totalShields;
                }
            }else{
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    tilePlacementScorePlayer1 = tilePlacementScorePlayer1 + totalCityPieces + totalShields - 1;
                    tilePlacementScorePlayer2 = tilePlacementScorePlayer2 + totalCityPieces + totalShields - 1;
                }
            }
        }
        
        //Return a value that reflects the point value of the city to the current player
        if(hasBlankTile == true && endGame == false && test == true){
            description = currentTileImage.getDescription().toCharArray();
            if(playerTurn == 1){
                if(meepleCountPlayer1 == 1 && description[5] != '0' && meeplesRemainingPlayer1 != 0){
                    city0Unclaimed = true;
                }
                
                if(meepleCountPlayer1 > meepleCountPlayer2){
                    return totalCityPieces + totalShields;
                }else if(meepleCountPlayer2 > meepleCountPlayer1){
                    return -1;
                }else if(meepleCountPlayer1 == meepleCountPlayer2){
                    return totalCityPieces + totalShields;
                }
            }else{
                if(meepleCountPlayer2 == 1 && description[5] != '0' && meeplesRemainingPlayer2 != 0){
                    city0Unclaimed = true;
                }
                
                if(meepleCountPlayer2 > meepleCountPlayer1){
                    return totalCityPieces + totalShields;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    return -1;
                }else if(meepleCountPlayer2 == meepleCountPlayer1){
                    return totalCityPieces + totalShields;
                }
            }
        }
        return 0;
    }
    
    /*********************************************************************************************
    * Method triggered to score a city when a special city tile is placed (ie tile 13 or tile 14)
    **********************************************************************************************/
    public int scoreCitySpecial(int row, int col, int value, boolean endGame, boolean test){
        Queue queue = new LinkedList(); //Data structure to store city pieces
        boolean hasBlankTile = false; //Boolean to keep track if a blank tile is found (ie city not complete)
        int totalCityPieces = 0; //Integer to keep track of the total number of pieces that make up the city
        int totalShields = 0; //Integer to keep track of the total number of shields contained in a city
        int meepleCountPlayer1 = 0; //Integer to keep track of number of meeples player 1 has in the city
        int meepleCountPlayer2 = 0; //Integer to keep track of number of meeples player 2 has in the city
        boolean[][] visited = new boolean[90][90]; //2d-Array to keep track if tile has been visited
        specialTile[][] specialTiles = new specialTile[90][90]; //2d-Array to store special tile's (tile numbers 13 & 14)
        Vector<placedTile> storage = new Vector<placedTile>(); //Vector to store tiles in city being searched that contain meeples
        placedTile currentTile = board[row][col]; //Current tile
        placedTile originalTile = currentTile; //Original tile
        placedTile topTile = board[row-1][col]; //Tile above current tile
        placedTile rightTile = board[row][col+1]; //Tile to right of current tile
        placedTile bottomTile = board[row+1][col]; //Tile below current tile
        placedTile leftTile = board[row][col-1]; //Tile to left of current tile
        int topTileNumber = board[row-1][col].getTileNumber(); //Tile number of topTile
        int rightTileNumber = board[row][col+1].getTileNumber(); //Tile number of rightTile
        int bottomTileNumber = board[row+1][col].getTileNumber(); //Tile number of bottomTile
        int leftTileNumber = board[row][col-1].getTileNumber(); //Tile number of leftTile
        char description[] = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
        char description1[] = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
        char description2[] = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
        char description3[] = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
        char description4[] = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
        int cityCount1 = 0; //Number of cities on tile above current tile
        int cityCount2 = 0; //Number of cities on tile to right of current tile
        int cityCount3 = 0; //Number of cities on tile below current tile
        int cityCount4 = 0; //Number of cities on tile to left of current tile
        
        //Use value passed in to determine which city to check
        if(value == 0){
            //Set values other than city to be checked to 0
            description[1] = '0';
            description[2] = '0';
            description[3] = '0';
            
            //Check original tile for meeple
            if(description[11] == '2'){
                //Determine which player's meeple it is
                if(description[7] == '1'){
                    meepleCountPlayer1++;
                }else{
                    meepleCountPlayer2++;
                }
                
                //Add tile to storage container since it contains a meeple and needs replaced if city completed
                storage.add(currentTile);
            }
            
            //Store the special tile indicating which side has already been checked for a meeple
            specialTiles[row][col] = new specialTile(currentTile.getImage(),currentTile.getTileNumber(),true,false,false,false);
            
            //Determine if the city tile contains a shield
            if(description[9] == '1'){
                totalShields++;
            }
        }else if(value == 1){
            //Set values other than city to be checked to 0
            description[0] = '0';
            description[2] = '0';
            description[3] = '0';
            
            //Check original tile for meeple
            if(description[12] == '2'){
                //Determine which player's meeple it is
                if(description[7] == '1'){
                    meepleCountPlayer1++;
                }else{
                    meepleCountPlayer2++;
                }
                
                //Add tile to storage container since it contains a meeple and needs replaced if city completed
                storage.add(currentTile);
            }
            
            //Store the special tile indicating which side has already been checked for a meeple
            specialTiles[row][col] = new specialTile(currentTile.getImage(),currentTile.getTileNumber(),false,true,false,false);
            
            //Determine if the city tile contains a shield
            if(description[9] == '1'){
                totalShields++;
            }
        }else if(value == 2){
            //Set values other than city to be checked to 0
            description[0] = '0';
            description[1] = '0';
            description[3] = '0';
            
            //Check original tile for meeple
            if(description[13] == '2'){
                //Determine which player's meeple it is
                if(description[7] == '1'){
                    meepleCountPlayer1++;
                }else{
                    meepleCountPlayer2++;
                }
                
                //Add tile to storage container since it contains a meeple and needs replaced if city completed
                storage.add(currentTile);
            }
            
            //Store the special tile indicating which side has already been checked for a meeple
            specialTiles[row][col] = new specialTile(currentTile.getImage(),currentTile.getTileNumber(),false,false,true,false);
            
            //Determine if the city tile contains a shield
            if(description[9] == '1'){
                totalShields++;
            }
        }else if(value == 3){
            //Set values other than city to be checked to 0
            description[0] = '0';
            description[1] = '0';
            description[2] = '0';
            
            //Check original tile for meeple
            if(description[14] == '2'){
                //Determine which player's meeple it is
                if(description[7] == '1'){
                    meepleCountPlayer1++;
                }else{
                    meepleCountPlayer2++;
                }
                
                //Add tile to storage container since it contains a meeple and needs replaced if city completed
                storage.add(currentTile);
            }
            
            //Store the special tile indicating which side has already been checked for a meeple
            specialTiles[row][col] = new specialTile(currentTile.getImage(),currentTile.getTileNumber(),false,false,false,true);
            
            
            //Determine if the city tile contains a shield
            if(description[9] == '1'){
                totalShields++;
            }
        }
        
        //Set current tile to visited
        visited[row][col] = true;
        
        //Add 1 to to totalCityPieces since orginal tile has a city
        totalCityPieces++;
        
        while(!queue.isEmpty() || (currentTile == originalTile)){
            //Check if top of current tile has a city
            if (description[0] == '2' && !visited[row - 1][col]) {
                //Check if bottom of topTile contains a city
                if(description1[2] == '2'){
                    totalCityPieces++;
                }
                
                //Count number of cities on topTile
                for (int i = 0; i < 4; i++) {
                    if (description1[i] == '2') {
                        cityCount1++;
                    }
                }
                
                //If city count is greater than 1 and its not tile 13 or tile 14, add it to the queue
                if (cityCount1 > 1 && topTile.getTileNumber() != 13 && topTile.getTileNumber() != 14) {
                    queue.add(topTile);
                }
                
                //Check bottom of topTile for meeple
                if (description1[13] == '2') {
                    //Determine which player's meeple it is
                    if(description1[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if city completed
                    storage.add(topTile);
                }
                
                //Determine if the city tile contains a shield
                if(description1[9] == '1'){
                    totalShields++;
                }
                
                //Check if topTile is blank
                if(topTile.getImage() == blankTile){
                    hasBlankTile = true;
                }
                
                //Store the special tile indicating which side has already been checked for a meeple
                if(topTile.getTileNumber() == 13 || topTile.getTileNumber() == 14){
                    if(specialTiles[row - 1][col] == null){
                        specialTiles[row - 1][col] = new specialTile(topTile.getImage(),topTileNumber,false,false,true,false);
                    }else{
                        specialTiles[row - 1][col].setBottom(true);
                    }
                }
                
                //Mark tile as visited
                visited[row-1][col] = true;
            }else if(description[0] == '2' && visited[row - 1][col] && (topTileNumber == 13 || topTileNumber == 14)){
                //Check bottom of topTile for meeple if not already checked
                if(specialTiles[row - 1][col].getBottom() == false){
                    if (description1[13] == '2') {
                        //Determine which player's meeple it is
                        if(description1[7] == '1'){
                            meepleCountPlayer1++;
                        }else{
                            meepleCountPlayer2++;
                        }
                        
                        //Add tile to storage container since it contains a meeple and needs replaced if city completed
                        storage.add(topTile);
                    }
                }
            }
            
            //Check if right side of current tile has a city
            if (description[1] == '2' && !visited[row][col + 1]) {
                //Check if left side of rightTile contains a city
                if(description2[3] == '2'){
                    totalCityPieces++;
                }
                
                //Count number of cities on rightTile
                for (int i = 0; i < 4; i++) {
                    if (description2[i] == '2') {
                        cityCount2++;
                    }
                }
                
                //If city count is greater than 1 and its not tile 13 or tile 14, add it to the queue
                if (cityCount2 > 1 && rightTile.getTileNumber() != 13 && rightTile.getTileNumber() != 14) {
                    queue.add(rightTile);
                }
                
                //Check left side of rightTile for meeple
                if (description2[14] == '2') {
                    //Determine which player's meeple it is
                    if(description2[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(rightTile);
                }
                
                //Determine if the city tile contains a shield
                if(description2[9] == '1'){
                    totalShields++;
                }
                
                //Check if rightTile is blank
                if(rightTile.getImage() == blankTile){
                    hasBlankTile = true;
                }
                
                //Store the special tile indicating which side has already been checked for a meeple
                if(rightTile.getTileNumber() == 13 || rightTile.getTileNumber() == 14){
                    if(specialTiles[row][col + 1] == null){
                        specialTiles[row][col + 1] = new specialTile(rightTile.getImage(),rightTileNumber,false,false,false,true);
                    }else{
                        specialTiles[row][col + 1].setLeft(true);
                    }
                }
                
                //Mark tile as visited
                visited[row][col+1] = true;
            }else if(description[1] == '2' && visited[row][col + 1] && (rightTileNumber == 13 || rightTileNumber == 14)){
                //Check left side of rightTile for meeple if not already checked
                if(specialTiles[row][col + 1].getLeft() == false){
                    if (description2[14] == '2') {
                        //Determine which player's meeple it is
                        if(description2[7] == '1'){
                            meepleCountPlayer1++;
                        }else{
                            meepleCountPlayer2++;
                        }
                        
                        //Add tile to storage container since it contains a meeple and needs replaced if city completed
                        storage.add(rightTile);
                    }
                }
            }
            
            //Check if bottom of current tile has a city
            if (description[2] == '2' && !visited[row + 1][col]) {
                //Check if top side of bottomTile contains a city
                if(description3[0] == '2'){
                    totalCityPieces++;
                }
                
                //Count number of cities on bottomTile
                for (int i = 0; i < 4; i++) {
                    if (description3[i] == '2') {
                        cityCount3++;
                    }
                }
                
                //If city count is greater than 1 and its not tile 13 or tile 14, add it to the queue
                if (cityCount3 > 1 && bottomTile.getTileNumber() != 13 && bottomTile.getTileNumber() != 14) {
                    queue.add(bottomTile);
                }
                
                //Check top of bottomTile for meeple
                if (description3[11] == '2') {
                    //Determine which player's meeple it is
                    if(description3[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(bottomTile);
                }
                
                //Determine if the city tile contains a shield
                if(description3[9] == '1'){
                    totalShields++;
                }
                
                //Check if bottomTile is blank
                if(bottomTile.getImage() == blankTile){
                    hasBlankTile = true;
                }
                
                //Store the special tile indicating which side has already been checked for a meeple
                if(bottomTile.getTileNumber() == 13 || bottomTile.getTileNumber() == 14){
                    if(specialTiles[row + 1][col] == null){
                        specialTiles[row + 1][col] = new specialTile(bottomTile.getImage(),bottomTileNumber,true,false,false,false);
                    }else{
                        specialTiles[row + 1][col].setTop(true);
                    }
                }
                
                //Mark tile as visited
                visited[row+1][col] = true;
            }else if(description[2] == '2' && visited[row + 1][col] && (bottomTileNumber == 13 || bottomTileNumber == 14)){
                //Check top of bottomTile for meeple if not already checked
                if(specialTiles[row + 1][col].getTop() == false){
                    if (description3[11] == '2') {
                        //Determine which player's meeple it is
                        if(description3[7] == '1'){
                            meepleCountPlayer1++;
                        }else{
                            meepleCountPlayer2++;
                        }
                        
                        //Add tile to storage container since it contains a meeple and needs replaced if city completed
                        storage.add(bottomTile);
                    }
                }
            }
            
            //Check if left side of current tile has a city
            if (description[3] == '2' && !visited[row][col - 1]) {
                //Check if right side of leftTile contains a city
                if(description4[1] == '2'){
                    totalCityPieces++;
                }
                
                //Count number of cities on leftTile
                for (int i = 0; i < 4; i++) {
                    if (description4[i] == '2') {
                        cityCount4++;
                    }
                }
                
                //If City count is greater than 1 and its not tile 13 or tile 14, add it to the queue
                if (cityCount4 > 1 && leftTile.getTileNumber() != 13 && leftTile.getTileNumber() != 14) {
                    queue.add(leftTile);
                }
                
                //Check right side of leftTile for meeple
                if (description4[12] == '2') {
                   //Determine which player's meeple it is
                    if(description4[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(leftTile);
                }
                
                //Determine if the city tile contains a shield
                if(description4[9] == '1'){
                    totalShields++;
                }
                
                //Check if leftTile is blank
                if(leftTile.getImage() == blankTile){
                    hasBlankTile = true;
                }
                
                //Store the special tile indicating which side has already been checked for a meeple
                if(leftTile.getTileNumber() == 13 || leftTile.getTileNumber() == 14){
                    if(specialTiles[row][col - 1] == null){
                        specialTiles[row][col - 1] = new specialTile(leftTile.getImage(),leftTileNumber,false,true,false,false);
                    }else{
                        specialTiles[row][col - 1].setRight(true);
                    }
                }
                
                //Mark tile as visited
                visited[row][col-1] = true;
            }else if(description[3] == '2' && visited[row][col - 1] && (leftTileNumber == 13 || leftTileNumber == 14)){
                //Check right side of leftTile for meeple if not already checked
                if(specialTiles[row][col - 1].getRight() == false){
                    if (description4[12] == '2') {
                       //Determine which player's meeple it is
                        if(description4[7] == '1'){
                            meepleCountPlayer1++;
                        }else{
                            meepleCountPlayer2++;
                        }
                        
                        //Add tile to storage container since it contains a meeple and needs replaced if road completed
                        storage.add(leftTile);
                    }
                }
            }
            
            //Remove tile from head of queue
            if(!queue.isEmpty() && (currentTile != originalTile)){
                queue.remove();
            }
            
            if(currentTile == originalTile){
                currentTile = null;
            }
            
            //Set new values if queue is not empty
            if(!queue.isEmpty()){
                currentTile = (placedTile)queue.peek(); //Current tile
                row = currentTile.getXCoord(); //Row of current tile
                col = currentTile.getYCoord(); //Collumn of current tile
                topTile = board[row-1][col]; //Tile above current tile
                rightTile = board[row][col+1]; //Tile to right of current tile
                bottomTile = board[row+1][col]; //Tile below current tile
                leftTile = board[row][col-1]; //Tile to left of current tile
                topTileNumber = board[row-1][col].getTileNumber(); //Tile number of topTile
                rightTileNumber = board[row][col+1].getTileNumber(); //Tile number of rightTile
                bottomTileNumber = board[row+1][col].getTileNumber(); //Tile number of bottomTile
                leftTileNumber = board[row][col-1].getTileNumber(); //Tile number of leftTile
                description = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
                description1 = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
                description2 = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
                description3 = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
                description4 = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
                cityCount1 = 0; //Number of roads on tile above current tile
                cityCount2 = 0; //Number of roads on tile to right of current tile
                cityCount3 = 0; //Number of roads on tile below current tile
                cityCount4 = 0; //Number of roads on tile to left of current tile
            }
        }
        
        //Calculate the proper score and allocate it to the correct player if city completed
        if(hasBlankTile == false && endGame == false && test == false){
            if(meepleCountPlayer1 != 0 || meepleCountPlayer2 != 0){
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    score1 = score1 + 2*totalCityPieces + 2*totalShields;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    score2 = score2 + 2*totalCityPieces + 2*totalShields;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    score1 = score1 + 2*totalCityPieces + 2*totalShields;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 < meepleCountPlayer2){
                    score2 = score2 + 2*totalCityPieces + 2*totalShields;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }
                //Loop over storage and replace tiles with meeples on them
                for(int i = 0; i < storage.size(); i++){
                    int x = storage.get(i).getXCoord(); //X-coordinate of placed tile
                    int y = storage.get(i).getYCoord(); ////Y-coordinate of placed tile
                    ImageIcon image = storage.get(i).getImage(); //Imageof placed tile
                    description = image.getDescription().toCharArray(); //Description of placed tile
                    int tileNum = storage.get(i).getTileNumber(); //Tile number of placed tile
                    int r = storage.get(i).getRotateValue(); //Rotate value of placed tile
                    
                    
                    String newDescription = ""+description[0]+description[1]+description[2]+description[3]+"-0-0-"+description[9]+"-00000";
                    URL imageNew = this.getClass().getClassLoader().getResource("Images"+r+"/"+"tile"+tileNum+"-"+newDescription+".png");
                    image = new ImageIcon(imageNew);
                    image.setDescription(newDescription);
                    jTableGrid.setValueAt(image, x, y);
                    board[x][y] = new placedTile(x,y,image,tileNum,r);
                }
            }
        }
        
        //Calculate the proper score and allocate it to the correct player for an incomplete city at the end of the game
        if(endGame == true && test == false){
            if(meepleCountPlayer1 != 0 || meepleCountPlayer2 != 0){
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    score1 = score1 + totalCityPieces + totalShields;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    score2 = score2 + totalCityPieces + totalShields;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    score1 = score1 + totalCityPieces + totalShields;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 < meepleCountPlayer2){
                    score2 = score2 + totalCityPieces + totalShields;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }
                //Loop over storage and replace tiles with meeples on them with original tiles
                for(int i = 0; i < storage.size(); i++){
                    int x = storage.get(i).getXCoord(); //X-coordinate of placed tile
                    int y = storage.get(i).getYCoord(); ////Y-coordinate of placed tile
                    ImageIcon image = storage.get(i).getImage(); //Imageof placed tile
                    description = image.getDescription().toCharArray(); //Description of placed tile
                    int tileNum = storage.get(i).getTileNumber(); //Tile number of placed tile
                    int r = storage.get(i).getRotateValue(); //Rotate value of placed tile
                    
                    
                    String newDescription = ""+description[0]+description[1]+description[2]+description[3]+"-0-0-"+description[9]+"-00000";
                    URL imageNew = this.getClass().getClassLoader().getResource("Images"+r+"/"+"tile"+tileNum+"-"+newDescription+".png");
                    image = new ImageIcon(imageNew);
                    image.setDescription(newDescription);
                    jTableGrid.setValueAt(image, x, y);
                    board[x][y] = new placedTile(x,y,image,tileNum,r);
                }
            }
        }
        
        //Calculate the possible score for a tile placement
        if(hasBlankTile == false && endGame == false && test == true){
            if(meepleCountPlayer1 != 0 || meepleCountPlayer2 != 0){
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    tilePlacementScorePlayer1 = tilePlacementScorePlayer1 + 2*totalCityPieces + 2*totalShields;
                    tilePlacementScorePlayer2 = tilePlacementScorePlayer2 + 2*totalCityPieces + 2*totalShields;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    tilePlacementScorePlayer1 = tilePlacementScorePlayer1 + 2*totalCityPieces + 2*totalShields;
                }else if(meepleCountPlayer1 < meepleCountPlayer2){
                    tilePlacementScorePlayer2 = tilePlacementScorePlayer2 + 2*totalCityPieces + 2*totalShields;
                }
            }else{
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    tilePlacementScorePlayer1 = tilePlacementScorePlayer1 + totalCityPieces + totalShields - 1;
                    tilePlacementScorePlayer2 = tilePlacementScorePlayer2 + totalCityPieces + totalShields - 1;
                }
            }
        }
        
        //Return a value that reflects the point value of the city to the current player
        if(hasBlankTile == true && endGame == false && test == true){
            description = currentTileImage.getDescription().toCharArray();
            if(playerTurn == 1){
                if(meepleCountPlayer1 == 1 && description[5] != '0' && meeplesRemainingPlayer1 != 0){
                    if(value == 0){
                        city1Unclaimed = true;
                    }else if(value == 1){
                        city2Unclaimed = true;
                    }else if(value == 2){
                        city3Unclaimed = true;
                    }else if(value == 3){
                        city4Unclaimed = true;
                    }
                }
                
                if(meepleCountPlayer1 > meepleCountPlayer2){
                    return totalCityPieces + totalShields;
                }else if(meepleCountPlayer2 > meepleCountPlayer1){
                    return -1;
                }else if (meepleCountPlayer1 == meepleCountPlayer2){
                    return totalCityPieces + totalShields;
                }
            }else{
                if(meepleCountPlayer2 == 1 && description[5] != '0' && meeplesRemainingPlayer2 != 0){
                    if(value == 0){
                        city1Unclaimed = true;
                    }else if(value == 1){
                        city2Unclaimed = true;
                    }else if(value == 2){
                        city3Unclaimed = true;
                    }else if(value == 3){
                        city4Unclaimed = true;
                    }
                }
                
                if(meepleCountPlayer2 > meepleCountPlayer1){
                    return totalCityPieces + totalShields;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    return -1;
                }else if (meepleCountPlayer2 == meepleCountPlayer1){
                    return totalCityPieces + totalShields;
                }
            }
        }
        return 0;
    }
    
    /***************************************************************************
    * Method to calculate the score of a newly placed tile containing 1 or 2 roads
    ***************************************************************************/
    public int scoreRoadCount1or2(int row, int col, boolean endGame, boolean test){
        boolean directionEnd1 = false; //Boolean to keep track of progress on 1st road direction traveled
        boolean directionEnd2 = false; //Boolean to keep track of progress on 2nd road direction traveled
        boolean directionEnd1Type = false; //Boolean to discern whether the road end is a blank tile or the actual end of the road
        boolean directionEnd2Type = false; //Boolean to discern whether the road end is a blank tile or the actual end of the road
        int totalRoadPieces = 0; //Integer to keep track of the total number of pieces that make up the road
        int meepleCountPlayer1 = 0; //Integer to keep track of number of meeples player 1 has on the road
        int meepleCountPlayer2 = 0; //Integer to keep track of number of meeples player 2 has on the road
        boolean[][] visited = new boolean[90][90]; //2d-Array to keep track if tile has been visited
        Vector<placedTile> storage = new Vector<placedTile>(); //Vector to store tiles on road being searched that contain meeples
        placedTile currentTile = board[row][col]; //Current tile
        placedTile topTile = board[row-1][col]; //Tile above current tile
        placedTile rightTile = board[row][col+1]; //Tile to right of current tile
        placedTile bottomTile = board[row+1][col]; //Tile below current tile
        placedTile leftTile = board[row][col-1]; //Tile to left of current tile
        char description[] = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
        char description1[] = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
        char description2[] = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
        char description3[] = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
        char description4[] = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
        int roadCount = 0; //Number of roads when checking if only need to travel in one direction
        int roadCount1 = 0; //Number of roads on tile above current tile
        int roadCount2 = 0; //Number of roads on tile to right of current tile
        int roadCount3 = 0; //Number of roads on tile below current tile
        int roadCount4 = 0; //Number of roads on tile to left of current tile
        int originalRow = row; //Row value of original tile
        int originalColumn = col; //Column value of original tile
        
        //Set current tile to visited
        visited[row][col] = true;
        
        //Check original tile for meeple
        if(description[11] == '3' || description[12] == '3' || description[13] == '3' || description[14] == '3'){
            //Determine which player's meeple it is
            if(description[7] == '1'){
                meepleCountPlayer1++;
            }else{
                meepleCountPlayer2++;
            }
            //Add tile to storage container since it contains a meeple and needs replaced if road completed
            storage.add(currentTile);
        }
        
        //Count number of roads on original tile
        for (int i = 0; i < 4; i++) {
            if (description[i] == '3') {
                roadCount++;
            }
        }
            
        //If roadCount == 1 then only need to travel one direction
        if(roadCount == 1){
            directionEnd2 = true;
            directionEnd2Type = true;
        }
        
        //Add 1 to to totalRoadPieces since orginal tile has a road
        totalRoadPieces++;
        
        
        //Check first direction of road for meeples and count road pieces / meeples
        while(directionEnd1 == false) {
            //Check if top of current tile has a road
            if (description[0] == '3' && !visited[row - 1][col]) {
                //Check if bottom of topTile contains a road
                if(description1[2] == '3'){
                    totalRoadPieces++;
                }
                
                //Count number of roads on topTile
                for (int i = 0; i < 4; i++) {
                    if (description1[i] == '3') {
                        roadCount1++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount1 != 2) {
                    directionEnd1 = true;
                    //Make sure not a blank tile at end of road
                    if(roadCount1 != 0){
                        directionEnd1Type = true;
                    }
                }
                
                //Check bottom of topTile for meeple
                if (description1[13] == '3') {
                    //Determine which player's meeple it is
                    if(description1[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(topTile);
                }
                row = row - 1;
            }
            
            //Check if right side of current tile has a road
            else if (description[1] == '3' && !visited[row][col + 1]) {
                //Check if left side of rightTile contains a road
                if(description2[3] == '3'){
                    totalRoadPieces++;
                }
                
                //Count number of roads on rightTile
                for (int i = 0; i < 4; i++) {
                    if (description2[i] == '3') {
                        roadCount2++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount2 != 2) {
                    directionEnd1 = true;
                    //Make sure not a blank tile at end of road
                    if(roadCount2 != 0){
                        directionEnd1Type = true;
                    }
                }
                
                //Check left side of rightTile for meeple
                if (description2[14] == '3') {
                    //Determine which player's meeple it is
                    if(description2[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(rightTile);
                }
                col = col + 1;
            }
            
            //Check if bottom of current tile has a road
            else if (description[2] == '3' && !visited[row + 1][col]) {
                //Check if topTile side of bottomTile contains a road
                if(description3[0] == '3'){
                    totalRoadPieces++;
                }
                
                //Count number of roads on bottomTile
                for (int i = 0; i < 4; i++) {
                    if (description3[i] == '3') {
                        roadCount3++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount3 != 2) {
                    directionEnd1 = true;
                    //Make sure not a blank tile at end of road
                    if(roadCount3 != 0){
                        directionEnd1Type = true;
                    }
                }
                
                //Check topTile of bottomTile for meeple
                if (description3[11] == '3') {
                    //Determine which player's meeple it is
                    if(description3[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(bottomTile);
                }
                row = row + 1;
            }
            
            //Check if left side of current tile has a road
            else if (description[3] == '3' && !visited[row][col - 1]) {
                //Check if right side of leftTile contains a road
                if(description4[1] == '3'){
                    totalRoadPieces++;
                }
                
                //Count number of roads on leftTile
                for (int i = 0; i < 4; i++) {
                    if (description4[i] == '3') {
                        roadCount4++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount4 != 2) {
                    directionEnd1 = true;
                    //Make sure not a blank tile at end of road
                    if(roadCount4 != 0){
                        directionEnd1Type = true;
                    }
                }
                
                //Check right side of leftTile for meeple
                if (description4[12] == '3') {
                    //Determine which player's meeple it is
                    if(description4[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(leftTile);
                }
                col = col - 1;
            }else{
                //Break out of a loop
                directionEnd1 = true;
                directionEnd1Type = true;
                if (description[0] == '3' && visited[row - 1][col]){
                    //Count number of roads on topTile
                    for (int i = 0; i < 4; i++) {
                        if (description1[i] == '3') {
                            roadCount1++;
                        }
                    }
                    if(roadCount1 == 3 || roadCount1 == 4){
                        //Check bottom of topTile for meeple
                        if (description1[13] == '3') {
                            //Determine which player's meeple it is
                            if(description1[7] == '1'){
                                meepleCountPlayer1++;
                            }else{
                                meepleCountPlayer2++;
                            }
                            //Add tile to storage container since it contains a meeple and needs replaced if road completed
                            storage.add(topTile);
                        }
                    }
                }else if (description[1] == '3' && visited[row][col + 1]){
                    //Count number of roads on rightTile
                    for (int i = 0; i < 4; i++) {
                        if (description2[i] == '3') {
                            roadCount2++;
                        }
                    }
                    if(roadCount2 == 3 || roadCount2 == 4){
                        //Check left side of rightTile for meeple
                        if (description2[14] == '3') {
                            //Determine which player's meeple it is
                            if(description2[7] == '1'){
                                meepleCountPlayer1++;
                            }else{
                                meepleCountPlayer2++;
                            }
                            //Add tile to storage container since it contains a meeple and needs replaced if road completed
                            storage.add(rightTile);
                        }
                    }
                }else if (description[2] == '3' && visited[row + 1][col]){
                    //Count number of roads on bottomTile
                    for (int i = 0; i < 4; i++) {
                        if (description3[i] == '3') {
                            roadCount3++;
                        }
                    }
                    if(roadCount3 == 3 || roadCount3 == 4){
                        //Check top of bottomTile tile for meeple
                        if (description3[11] == '3') {
                            //Determine which player's meeple it is
                            if(description3[7] == '1'){
                                meepleCountPlayer1++;
                            }else{
                                meepleCountPlayer2++;
                            }
                            //Add tile to storage container since it contains a meeple and needs replaced if road completed
                            storage.add(bottomTile);
                        }
                    }
                }else if (description[3] == '3' && visited[row][col - 1]){
                    //Count number of roads on leftTile
                    for (int i = 0; i < 4; i++) {
                        if (description4[i] == '3') {
                            roadCount4++;
                        }
                    }
                    if(roadCount4 == 3 || roadCount4 == 4){
                        //Check right side of leftTile for meeple
                        if (description4[12] == '3') {
                            //Determine which player's meeple it is
                            if(description4[7] == '1'){
                                meepleCountPlayer1++;
                            }else{
                                meepleCountPlayer2++;
                            }
                            //Add tile to storage container since it contains a meeple and needs replaced if road completed
                            storage.add(leftTile);
                        }
                    }
                }
            }
            visited[row][col] = true;
            currentTile = board[row][col]; //Current tile
            topTile = board[row-1][col]; //Tile above current tile
            rightTile = board[row][col+1]; //Tile to right of current tile
            bottomTile = board[row+1][col]; //Tile below current tile
            leftTile = board[row][col-1]; //Tile to left of current tile
            description = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
            description1 = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
            description2 = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
            description3 = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
            description4 = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
            roadCount = 0; //Number of roads when checking if only need to travel in one direction
            roadCount1 = 0; //Number of roads on tile above current tile
            roadCount2 = 0; //Number of roads on tile to right of current tile
            roadCount3 = 0; //Number of roads on tile below current tile
            roadCount4 = 0; //Number of roads on tile to left of current tile
        }
        
        
        //Return to starting tile
        row = originalRow;
        col = originalColumn;
        currentTile = board[row][col];
        topTile = board[row-1][col];
        rightTile = board[row][col+1];
        bottomTile = board[row+1][col];
        leftTile = board[row][col-1];
        description = currentTile.getImage().getDescription().toCharArray();
        description1 = topTile.getImage().getDescription().toCharArray();
        description2 = rightTile.getImage().getDescription().toCharArray();
        description3 = bottomTile.getImage().getDescription().toCharArray();
        description4 = leftTile.getImage().getDescription().toCharArray();
        roadCount1 = 0;
        roadCount2 = 0;
        roadCount3 = 0;
        roadCount4 = 0;

        
        //Check second direction of road for meeples and count road pieces / meeples (if it exists!)
        while(directionEnd2 == false) {
            //Check if top of current tile has a road
            if (description[0] == '3' && !visited[row - 1][col]) {
                //Check if bottom of topTile contains a road
                if(description1[2] == '3'){
                    totalRoadPieces++;
                }
                
                //Count number of roads on topTile
                for (int i = 0; i < 4; i++) {
                    if (description1[i] == '3') {
                        roadCount1++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount1 != 2) {
                    directionEnd2 = true;
                    //Make sure not a blank tile at end of road
                    if(roadCount1 != 0){
                        directionEnd2Type = true;
                    }
                }
                
                //Check bottom of topTile for meeple
                if (description1[13] == '3') {
                    //Determine which player's meeple it is
                    if(description1[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(topTile);
                }
                row = row - 1;
            }
            
            //Check if right side of current tile has a road
            else if (description[1] == '3' && !visited[row][col + 1]) {
                //Check if left side of right tile contains a road
                if(description2[3] == '3'){
                    totalRoadPieces++;
                }
                
                //Count number of roads on rightTile
                for (int i = 0; i < 4; i++) {
                    if (description2[i] == '3') {
                        roadCount2++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount2 != 2) {
                    directionEnd2 = true;
                    //Make sure not a blank tile at end of road
                    if(roadCount2 != 0){
                        directionEnd2Type = true;
                    }
                }
                
                //Check left side of rightTile for meeple
                if (description2[14] == '3') {
                    //Determine which player's meeple it is
                    if(description2[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(rightTile);
                }
                col = col + 1;
            }
            
            //Check if bottom of current tile has a road
            else if (description[2] == '3' && !visited[row + 1][col]) {
                //Check if top side of bottomTile contains a road
                if(description3[0] == '3'){
                    totalRoadPieces++;
                }
                
                //Count number of roads on bottomTile
                for (int i = 0; i < 4; i++) {
                    if (description3[i] == '3') {
                        roadCount3++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount3 != 2) {
                    directionEnd2 = true;
                    //Make sure not a blank tile at end of road
                    if(roadCount3 != 0){
                        directionEnd2Type = true;
                    }
                }
                
                //Check top of bottomTile for meeple
                if (description3[11] == '3') {
                    //Determine which player's meeple it is
                    if(description3[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(bottomTile);
                }
                row = row + 1;
            }
            
            //Check if left side of current tile has a road
            else if (description[3] == '3' && !visited[row][col - 1]) {
                //Check if right side of leftTile contains a road
                if(description4[1] == '3'){
                    totalRoadPieces++;
                }
                
                //Count number of roads on leftTile
                for (int i = 0; i < 4; i++) {
                    if (description4[i] == '3') {
                        roadCount4++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount4 != 2) {
                    directionEnd2 = true;
                    //Make sure not a blank tile at end of road
                    if(roadCount4 != 0){
                        directionEnd2Type = true;
                    }
                }
                
                //Check right side of leftTile for meeple
                if (description4[12] == '3') {
                    //Determine which player's meeple it is
                    if(description4[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(leftTile);
                }
                col = col - 1;
            }else{
                //Break out of a loop
                directionEnd2 = true;
                directionEnd2Type = true;
                if (description[0] == '3' && visited[row - 1][col]){
                    //Count number of roads on topTile
                    for (int i = 0; i < 4; i++) {
                        if (description1[i] == '3') {
                            roadCount1++;
                        }
                    }
                    if(roadCount1 == 3 || roadCount1 == 4){
                        //Check bottom of topTile tile for meeple
                        if (description1[13] == '3') {
                            //Determine which player's meeple it is
                            if(description1[7] == '1'){
                                meepleCountPlayer1++;
                            }else{
                                meepleCountPlayer2++;
                            }
                            //Add tile to storage container since it contains a meeple and needs replaced if road completed
                            storage.add(topTile);
                        }
                    }
                }else if (description[1] == '3' && visited[row][col + 1]){
                    //Count number of roads on rightTile
                    for (int i = 0; i < 4; i++) {
                        if (description2[i] == '3') {
                            roadCount2++;
                        }
                    }
                    if(roadCount2 == 3 || roadCount2 == 4){
                        //Check left side of rightTile for meeple
                        if (description2[14] == '3') {
                            //Determine which player's meeple it is
                            if(description2[7] == '1'){
                                meepleCountPlayer1++;
                            }else{
                                meepleCountPlayer2++;
                            }
                            //Add tile to storage container since it contains a meeple and needs replaced if road completed
                            storage.add(rightTile);
                        }
                    }
                }else if (description[2] == '3' && visited[row + 1][col]){
                    //Count number of roads on bottomTile
                    for (int i = 0; i < 4; i++) {
                        if (description3[i] == '3') {
                            roadCount3++;
                        }
                    }
                    if(roadCount3 == 3 || roadCount3 == 4){
                        //Check top of bottomTile for meeple
                        if (description3[11] == '3') {
                            //Determine which player's meeple it is
                            if(description3[7] == '1'){
                                meepleCountPlayer1++;
                            }else{
                                meepleCountPlayer2++;
                            }
                            //Add tile to storage container since it contains a meeple and needs replaced if road completed
                            storage.add(bottomTile);
                        }
                    }
                }else if (description[3] == '3' && visited[row][col - 1]){
                    //Count number of roads on leftTile
                    for (int i = 0; i < 4; i++) {
                        if (description4[i] == '3') {
                            roadCount4++;
                        }
                    }
                    if(roadCount4 == 3 || roadCount4 == 4){
                        //Check right side of leftTile for meeple
                        if (description4[12] == '3') {
                            //Determine which player's meeple it is
                            if(description4[7] == '1'){
                                meepleCountPlayer1++;
                            }else{
                                meepleCountPlayer2++;
                            }
                            //Add tile to storage container since it contains a meeple and needs replaced if road completed
                            storage.add(leftTile);
                        }
                    }
                }
            }
            visited[row][col] = true;
            currentTile = board[row][col]; //Current tile
            topTile = board[row-1][col]; //Tile above current tile
            rightTile = board[row][col+1]; //Tile to right of current tile
            bottomTile = board[row+1][col]; //Tile below current tile
            leftTile = board[row][col-1]; //Tile to left of current tile
            description = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
            description1 = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
            description2 = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
            description3 = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
            description4 = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
            roadCount = 0; //Number of roads when checking if only need to travel in one direction
            roadCount1 = 0; //Number of roads on tile above current tile
            roadCount2 = 0; //Number of roads on tile to right of current tile
            roadCount3 = 0; //Number of roads on tile below current tile
            roadCount4 = 0; //Number of roads on tile to left of current tile
        }
        
        //Calculate the proper score and allocate it to the correct player if road completed
        if(directionEnd1Type == true && directionEnd2Type == true && endGame == false && test == false){
            if(meepleCountPlayer1 != 0 || meepleCountPlayer2 != 0){
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    score1 = score1 + totalRoadPieces;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    score2 = score2 + totalRoadPieces;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    score1 = score1 + totalRoadPieces;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 < meepleCountPlayer2){
                    score2 = score2 + totalRoadPieces;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }
                //Loop over storage and replace tiles with meeples on them
                for(int i = 0; i < storage.size(); i++){
                    int x = storage.get(i).getXCoord(); //X-coordinate of placed tile
                    int y = storage.get(i).getYCoord(); ////Y-coordinate of placed tile
                    ImageIcon image = storage.get(i).getImage(); //Imageof placed tile
                    description = image.getDescription().toCharArray(); //Description of placed tile
                    int tileNum = storage.get(i).getTileNumber(); //Tile number of placed tile
                    int r = storage.get(i).getRotateValue(); //Rotate value of placed tile
                    
                    
                    String newDescription = ""+description[0]+description[1]+description[2]+description[3]+"-0-0-"+description[9]+"-00000";
                    URL imageNew = this.getClass().getClassLoader().getResource("Images"+r+"/"+"tile"+tileNum+"-"+newDescription+".png");
                    image = new ImageIcon(imageNew);
                    image.setDescription(newDescription);
                    jTableGrid.setValueAt(image, x, y);
                    board[x][y] = new placedTile(x,y,image,tileNum,r);
                }
            }
        }
        
        //Calculate the proper score and allocate it to the correct player for an incomplete road at the end of the game
         if(endGame == true && test == false){
            if(meepleCountPlayer1 != 0 || meepleCountPlayer2 != 0){
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    score1 = score1 + totalRoadPieces;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    score2 = score2 + totalRoadPieces;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    score1 = score1 + totalRoadPieces;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 < meepleCountPlayer2){
                    score2 = score2 + totalRoadPieces;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }
                //Loop over storage and replace tiles with meeples on them
                for(int i = 0; i < storage.size(); i++){
                    int x = storage.get(i).getXCoord(); //X-coordinate of placed tile
                    int y = storage.get(i).getYCoord(); ////Y-coordinate of placed tile
                    ImageIcon image = storage.get(i).getImage(); //Imageof placed tile
                    description = image.getDescription().toCharArray(); //Description of placed tile
                    int tileNum = storage.get(i).getTileNumber(); //Tile number of placed tile
                    int r = storage.get(i).getRotateValue(); //Rotate value of placed tile
                    
                    
                    String newDescription = ""+description[0]+description[1]+description[2]+description[3]+"-0-0-"+description[9]+"-00000";
                    URL imageNew = this.getClass().getClassLoader().getResource("Images"+r+"/"+"tile"+tileNum+"-"+newDescription+".png");
                    image = new ImageIcon(imageNew);
                    image.setDescription(newDescription);
                    jTableGrid.setValueAt(image, x, y);
                    board[x][y] = new placedTile(x,y,image,tileNum,r);
                }
            }
        }
         
        //Calculate the possible score for a tile placement
        if(directionEnd1Type == true && directionEnd2Type == true && endGame == false && test == true){
            if(meepleCountPlayer1 != 0 || meepleCountPlayer2 != 0){
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    tilePlacementScorePlayer1 = tilePlacementScorePlayer1 + totalRoadPieces;
                    tilePlacementScorePlayer2 = tilePlacementScorePlayer2 + totalRoadPieces;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    tilePlacementScorePlayer1 = tilePlacementScorePlayer1 + totalRoadPieces;
                }else if(meepleCountPlayer1 < meepleCountPlayer2){
                    tilePlacementScorePlayer2 = tilePlacementScorePlayer2 + totalRoadPieces;
                }
            }else{
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    tilePlacementScorePlayer1 = tilePlacementScorePlayer1 + totalRoadPieces - 1;
                    tilePlacementScorePlayer2 = tilePlacementScorePlayer2 + totalRoadPieces - 1;
                }
            }
        }
        
        //Return a value that reflects the point value of the road to the current player
        if((directionEnd1Type == false || directionEnd2Type == false) && endGame == false && test == true){
            description = currentTileImage.getDescription().toCharArray();
            if(playerTurn == 1){
                if(meepleCountPlayer1 == 1 && description[5] != '0' && meeplesRemainingPlayer1 != 0){
                    road0Unclaimed = true;
                }
                
                if(meepleCountPlayer1 > meepleCountPlayer2){
                    return totalRoadPieces;
                }else if(meepleCountPlayer2 > meepleCountPlayer1){
                    return -1;
                }else if(meepleCountPlayer1 == meepleCountPlayer2){
                    return totalRoadPieces;
                }
            }else{
                if(meepleCountPlayer2 == 1 && description[5] != '0' && meeplesRemainingPlayer2 != 0){
                    road0Unclaimed = true;
                }
                
                if(meepleCountPlayer2 > meepleCountPlayer1){
                    return totalRoadPieces;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    return -1;
                }else if(meepleCountPlayer2 == meepleCountPlayer1){
                    return totalRoadPieces;
                }
            }
        }
        return 0;
    }
    
    /***************************************************************************
    * Method to calculate the score of a newly placed tile containing 3 or 4 roads
    ***************************************************************************/
    public int scoreRoadCount3or4(int row, int col, int value, boolean endGame, boolean test){
        boolean directionEnd1 = false; //Boolean to keep track of progress on 1st road direction traveled
        boolean directionEnd1Type = false; //Boolean to discern whether the road end is a blank tile or the actual end of the road
        int totalRoadPieces = 0; //Integer to keep track of the total number of pieces that make up the road
        int meepleCountPlayer1 = 0; //Integer to keep track of number of meeples player 1 has on the road
        int meepleCountPlayer2 = 0; //Integer to keep track of number of meeples player 2 has on the road
        boolean[][] visited = new boolean[90][90]; //2d-Array to keep track if tile has been visited
        Vector<placedTile> storage = new Vector<placedTile>(); //Vector to store tiles on road being searched that contain meeples
        visited[row][col] = true; //Set current tile to visited
        placedTile currentTile = board[row][col]; //Current tile
        placedTile topTile = board[row-1][col]; //Tile above current tile
        placedTile rightTile = board[row][col+1]; //Tile to right of current tile
        placedTile bottomTile = board[row+1][col]; //Tile below current tile
        placedTile leftTile = board[row][col-1]; //Tile to left of current tile
        char description[] = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
        char description1[] = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
        char description2[] = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
        char description3[] = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
        char description4[] = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
        int roadCount1 = 0; //Number of roads on tile above current tile
        int roadCount2 = 0; //Number of roads on tile to right of current tile
        int roadCount3 = 0; //Number of roads on tile below current tile
        int roadCount4 = 0; //Number of roads on tile to left of current tile
        
        //Use value passed in to determine which road to check
        if(value == 0){
            //Set values other than road to be travelled down to 0
            description[1] = '0';
            description[2] = '0';
            description[3] = '0';
            
            //Check original tile for meeple
            if(description[11] == '3'){
                //Determine which player's meeple it is
                if(description[7] == '1'){
                    meepleCountPlayer1++;
                }else{
                    meepleCountPlayer2++;
                }
                //Add tile to storage container since it contains a meeple and needs replaced if road completed
                storage.add(currentTile);
            }
        }else if(value == 1){
            //Set values other than road to be travelled down to 0
            description[0] = '0';
            description[2] = '0';
            description[3] = '0';
            
            //Check original tile for meeple
            if(description[12] == '3'){
                //Determine which player's meeple it is
                if(description[7] == '1'){
                    meepleCountPlayer1++;
                }else{
                    meepleCountPlayer2++;
                }
                //Add tile to storage container since it contains a meeple and needs replaced if road completed
                storage.add(currentTile);
            }
        }else if(value == 2){
            //Set values other than road to be travelled down to 0
            description[0] = '0';
            description[1] = '0';
            description[3] = '0';
            
            //Check original tile for meeple
            if(description[13] == '3'){
                //Determine which player's meeple it is
                if(description[7] == '1'){
                    meepleCountPlayer1++;
                }else{
                    meepleCountPlayer2++;
                }
                //Add tile to storage container since it contains a meeple and needs replaced if road completed
                storage.add(currentTile);
            }
        }else if(value == 3){
            //Set values other than road to be travelled down to 0
            description[0] = '0';
            description[1] = '0';
            description[2] = '0';
            
            //Check original tile for meeple
            if(description[14] == '3'){
                //Determine which player's meeple it is
                if(description[7] == '1'){
                    meepleCountPlayer1++;
                }else{
                    meepleCountPlayer2++;
                }
                //Add tile to storage container since it contains a meeple and needs replaced if road completed
                storage.add(currentTile);
            }
        }
        
        //Add 1 to to totalRoadPieces since orginal tile has a road
        totalRoadPieces++;
        
        //Check first direction of road for meeples and count road pieces / meeples
        while(directionEnd1 == false) {
            //Check if top of current tile has a road
            if (description[0] == '3' && !visited[row - 1][col]) {
                //Check if bottom of topTile contains a road
                if(description1[2] == '3'){
                    totalRoadPieces++;
                }
                
                //Count number of roads on topTile
                for (int i = 0; i < 4; i++) {
                    if (description1[i] == '3') {
                        roadCount1++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount1 != 2) {
                    directionEnd1 = true;
                    //Make sure not a blank tile at end of road
                    if(roadCount1 != 0){
                        directionEnd1Type = true;
                    }
                }
                
                //Check bottom of topTile for meeple
                if (description1[13] == '3') {
                    //Determine which player's meeple it is
                    if(description1[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(topTile);
                }
                row = row - 1;
            }
            
            //Check if right side of current tile has a road
            else if (description[1] == '3' && !visited[row][col + 1]) {
                //Check if left side of rightTile contains a road
                if(description2[3] == '3'){
                    totalRoadPieces++;
                }
                
                //Count number of roads on rightTile
                for (int i = 0; i < 4; i++) {
                    if (description2[i] == '3') {
                        roadCount2++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount2 != 2) {
                    directionEnd1 = true;
                    //Make sure not a blank tile at end of road
                    if(roadCount2 != 0){
                        directionEnd1Type = true;
                    }
                }
                
                //Check left side of rightTile for meeple
                if (description2[14] == '3') {
                    //Determine which player's meeple it is
                    if(description2[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(rightTile);
                }
                col = col + 1;
            }
            
            //Check if bottom of current tile has a road
            else if (description[2] == '3' && !visited[row + 1][col]) {
                //Check if top side of bottomTile contains a road
                if(description3[0] == '3'){
                    totalRoadPieces++;
                }
                
                //Count number of roads on bottomTile
                for (int i = 0; i < 4; i++) {
                    if (description3[i] == '3') {
                        roadCount3++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount3 != 2) {
                    directionEnd1 = true;
                    //Make sure not a blank tile at end of road
                    if(roadCount3 != 0){
                        directionEnd1Type = true;
                    }
                }
                
                //Check top of bottomTile for meeple
                if (description3[11] == '3') {
                    //Determine which player's meeple it is
                    if(description3[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(bottomTile);
                }
                row = row + 1;
            }
            
            //Check if left side of current tile has a road
            else if (description[3] == '3' && !visited[row][col - 1]) {
                //Check if right side of leftTile contains a road
                if(description4[1] == '3'){
                    totalRoadPieces++;
                }
                
                //Count number of roads on leftTile
                for (int i = 0; i < 4; i++) {
                    if (description4[i] == '3') {
                        roadCount4++;
                    }
                }
                
                //If road count is not 2, at end of road
                if (roadCount4 != 2) {
                    directionEnd1 = true;
                    //Make sure not a blank tile at end of road
                    if(roadCount4 != 0){
                        directionEnd1Type = true;
                    }
                }
                
                //Check right side of leftTile for meeple
                if (description4[12] == '3') {
                    //Determine which player's meeple it is
                    if(description4[7] == '1'){
                        meepleCountPlayer1++;
                    }else{
                        meepleCountPlayer2++;
                    }
                    //Add tile to storage container since it contains a meeple and needs replaced if road completed
                    storage.add(leftTile);
                }
                col = col - 1;
            }else{
                //Break out of a loop
                directionEnd1 = true;
                directionEnd1Type = true;
                if (description[0] == '3' && visited[row - 1][col]){
                    //Count number of roads on topTile
                    for (int i = 0; i < 4; i++) {
                        if (description1[i] == '3') {
                            roadCount1++;
                        }
                    }
                    if(roadCount1 == 3 || roadCount1 == 4){
                        //Check bottom of topTile for meeple
                        if (description1[13] == '3') {
                            //Determine which player's meeple it is
                            if(description1[7] == '1'){
                                meepleCountPlayer1++;
                            }else{
                                meepleCountPlayer2++;
                            }
                            //Add tile to storage container since it contains a meeple and needs replaced if road completed
                            storage.add(topTile);
                        }
                    }
                }else if (description[1] == '3' && visited[row][col + 1]){
                    //Count number of roads on rightTile
                    for (int i = 0; i < 4; i++) {
                        if (description2[i] == '3') {
                            roadCount2++;
                        }
                    }
                    if(roadCount2 == 3 || roadCount2 == 4){
                        //Check left side of rightTile for meeple
                        if (description2[14] == '3') {
                            //Determine which player's meeple it is
                            if(description2[7] == '1'){
                                meepleCountPlayer1++;
                            }else{
                                meepleCountPlayer2++;
                            }
                            //Add tile to storage container since it contains a meeple and needs replaced if road completed
                            storage.add(rightTile);
                        }
                    }
                }else if (description[2] == '3' && visited[row + 1][col]){
                    //Count number of roads on bottomTile
                    for (int i = 0; i < 4; i++) {
                        if (description3[i] == '3') {
                            roadCount3++;
                        }
                    }
                    if(roadCount3 == 3 || roadCount3 == 4){
                        //Check top of bottomTile for meeple
                        if (description3[11] == '3') {
                            //Determine which player's meeple it is
                            if(description3[7] == '1'){
                                meepleCountPlayer1++;
                            }else{
                                meepleCountPlayer2++;
                            }
                            //Add tile to storage container since it contains a meeple and needs replaced if road completed
                            storage.add(bottomTile);
                        }
                    }
                }else if (description[3] == '3' && visited[row][col - 1]){
                    //Count number of roads on leftTile
                    for (int i = 0; i < 4; i++) {
                        if (description4[i] == '3') {
                            roadCount4++;
                        }
                    }
                    if(roadCount4 == 3 || roadCount4 == 4){
                        //Check right side of leftTile for meeple
                        if (description4[12] == '3') {
                            //Determine which player's meeple it is
                            if(description4[7] == '1'){
                                meepleCountPlayer1++;
                            }else{
                                meepleCountPlayer2++;
                            }
                            //Add tile to storage container since it contains a meeple and needs replaced if road completed
                            storage.add(leftTile);
                        }
                    }
                }
            }
            visited[row][col] = true;
            currentTile = board[row][col]; //Current tile
            topTile = board[row-1][col]; //Tile above current tile
            rightTile = board[row][col+1]; //Tile to right of current tile
            bottomTile = board[row+1][col]; //Tile below current tile
            leftTile = board[row][col-1]; //Tile to left of current tile
            description = currentTile.getImage().getDescription().toCharArray(); //Description of current tile
            description1 = topTile.getImage().getDescription().toCharArray(); //Description of tile above current tile
            description2 = rightTile.getImage().getDescription().toCharArray(); //Description of tile to right of current tile
            description3 = bottomTile.getImage().getDescription().toCharArray(); //Description of tile below current tile
            description4 = leftTile.getImage().getDescription().toCharArray(); //Description of tile to left of current tile
            roadCount1 = 0; //Number of roads on tile above current tile
            roadCount2 = 0; //Number of roads on tile to right of current tile
            roadCount3 = 0; //Number of roads on tile below current tile
            roadCount4 = 0; //Number of roads on tile to left of current tile
        }
        
        
        //Calculate the proper score and allocate it to the correct player if road completed
        if(directionEnd1Type == true && endGame == false && test == false){
            if(meepleCountPlayer1 != 0 || meepleCountPlayer2 != 0){
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    score1 = score1 + totalRoadPieces;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    score2 = score2 + totalRoadPieces;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    score1 = score1 + totalRoadPieces;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 < meepleCountPlayer2){
                    score2 = score2 + totalRoadPieces;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }
                //Loop over storage and replace tiles with meeples on them
                for(int i = 0; i < storage.size(); i++){
                    int x = storage.get(i).getXCoord(); //X-coordinate of placed tile
                    int y = storage.get(i).getYCoord(); ////Y-coordinate of placed tile
                    ImageIcon image = storage.get(i).getImage(); //Imageof placed tile
                    description = image.getDescription().toCharArray(); //Description of placed tile
                    int tileNum = storage.get(i).getTileNumber(); //Tile number of placed tile
                    int r = storage.get(i).getRotateValue(); //Rotate value of placed tile
                    
                    
                    String newDescription = ""+description[0]+description[1]+description[2]+description[3]+"-0-0-"+description[9]+"-00000";
                    URL imageNew = this.getClass().getClassLoader().getResource("Images"+r+"/"+"tile"+tileNum+"-"+newDescription+".png");
                    image = new ImageIcon(imageNew);
                    image.setDescription(newDescription);
                    jTableGrid.setValueAt(image, x, y);
                    board[x][y] = new placedTile(x,y,image,tileNum,r);
                }
            }
        }
        
        //Calculate the proper score and allocate it to the correct player for an incomplete road at the end of the game
         if(endGame == true && test == false){
            if(meepleCountPlayer1 != 0 || meepleCountPlayer2 != 0){
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    score1 = score1 + totalRoadPieces;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    score2 = score2 + totalRoadPieces;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    score1 = score1 + totalRoadPieces;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }else if(meepleCountPlayer1 < meepleCountPlayer2){
                    score2 = score2 + totalRoadPieces;
                    meeplesRemainingPlayer1 = meeplesRemainingPlayer1 + meepleCountPlayer1;
                    meeplesRemainingPlayer2 = meeplesRemainingPlayer2 + meepleCountPlayer2;
                }
                //Loop over storage and replace tiles with meeples on them
                for(int i = 0; i < storage.size(); i++){
                    int x = storage.get(i).getXCoord(); //X-coordinate of placed tile
                    int y = storage.get(i).getYCoord(); ////Y-coordinate of placed tile
                    ImageIcon image = storage.get(i).getImage(); //Imageof placed tile
                    description = image.getDescription().toCharArray(); //Description of placed tile
                    int tileNum = storage.get(i).getTileNumber(); //Tile number of placed tile
                    int r = storage.get(i).getRotateValue(); //Rotate value of placed tile
                    
                    
                    String newDescription = ""+description[0]+description[1]+description[2]+description[3]+"-0-0-"+description[9]+"-00000";
                    URL imageNew = this.getClass().getClassLoader().getResource("Images"+r+"/"+"tile"+tileNum+"-"+newDescription+".png");
                    image = new ImageIcon(imageNew);
                    image.setDescription(newDescription);
                    jTableGrid.setValueAt(image, x, y);
                    board[x][y] = new placedTile(x,y,image,tileNum,r);
                }
            }
        }
         
        //Calculate the possible score for a tile placement
        if(directionEnd1Type == true && endGame == false && test == true){
            if(meepleCountPlayer1 != 0 || meepleCountPlayer2 != 0){
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    tilePlacementScorePlayer1 = tilePlacementScorePlayer1 + totalRoadPieces;
                    tilePlacementScorePlayer2 = tilePlacementScorePlayer2 + totalRoadPieces;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    tilePlacementScorePlayer1 = tilePlacementScorePlayer1 + totalRoadPieces;
                }else if(meepleCountPlayer1 < meepleCountPlayer2){
                    tilePlacementScorePlayer2 = tilePlacementScorePlayer2 + totalRoadPieces;
                }
            }else{
                if(meepleCountPlayer1 == meepleCountPlayer2){
                    tilePlacementScorePlayer1 = tilePlacementScorePlayer1 + totalRoadPieces - 1;
                    tilePlacementScorePlayer2 = tilePlacementScorePlayer2 + totalRoadPieces - 1;
                }
            }
        }
        
        //Return a value that reflects the point value of the road to the current player
        if(directionEnd1Type == false && endGame == false && test == true){
            description = currentTileImage.getDescription().toCharArray();
            if(playerTurn == 1){
                if(meepleCountPlayer1 == 1 && description[5] != '0' && meeplesRemainingPlayer1 != 0){
                    if(value == 0){
                        road1Unclaimed = true;
                    }else if(value == 1){
                        road2Unclaimed = true;
                    }else if(value == 2){
                        road3Unclaimed = true;
                    }else if(value == 3){
                        road4Unclaimed = true;
                    }
                }
                
                if(meepleCountPlayer1 > meepleCountPlayer2){
                    return totalRoadPieces;
                }else if(meepleCountPlayer2 > meepleCountPlayer1){
                    return -1;
                }else if(meepleCountPlayer1 == meepleCountPlayer2){
                    return totalRoadPieces;
                }
            }else{
                if(meepleCountPlayer2 == 1 && description[5] != '0' && meeplesRemainingPlayer2 != 0){
                    if(value == 0){
                        road1Unclaimed = true;
                    }else if(value == 1){
                        road2Unclaimed = true;
                    }else if(value == 2){
                        road3Unclaimed = true;
                    }else if(value == 3){
                        road4Unclaimed = true;
                    }
                }
                
                if(meepleCountPlayer2 > meepleCountPlayer1){
                    return totalRoadPieces;
                }else if(meepleCountPlayer1 > meepleCountPlayer2){
                    return -1;
                }else if(meepleCountPlayer2 == meepleCountPlayer1){
                    return totalRoadPieces;
                }
            }
        }
        return 0;
    }
    
    /******************************************************************************************
    * Method to add placed tile to vector containing cloisters with meeples on them for scoring
    *******************************************************************************************/
    public void addToCloistersWithMeeples(){
        char description[] = currentTileImage.getDescription().toCharArray(); //Description of current tile
        
        if(description[15] == '4'){
            cloistersWithMeeples.add(new placedTile(currentTileRow,currentTileColumn,currentTileImage,currentTileNumber,rotate));
        }
    }
    
    /***************************************************************************
    * Method to calculate the score of a newly placed tile containing a cloister
    ***************************************************************************/
    public boolean scoreCloister(placedTile image, boolean endGame){
        int row = image.getXCoord();
        int col = image.getYCoord();
        ImageIcon topTile = board[row-1][col].getImage(); //Tile top
        ImageIcon topLeftTile = board[row-1][col-1].getImage(); //Tile top left
        ImageIcon topRightTile = board[row-1][col+1].getImage(); //Tile top right
        ImageIcon rightTile = board[row][col+1].getImage(); //Tile right
        ImageIcon bottomTile = board[row+1][col].getImage(); //Tile bottom
        ImageIcon bottomLeftTile = board[row+1][col-1].getImage(); //Tile bottom left
        ImageIcon bottomRightTile = board[row+1][col+1].getImage(); //Tile bottom right
        ImageIcon leftTile = board[row][col-1].getImage(); //Tile left
        char description[] = image.getImage().getDescription().toCharArray(); //Description of cloister tile
        int numNonBlankTiles = 0; //Integer to keep track of the number of non-blank tiles surrounding the cloister
        
        //Count the number of blank tiles surrounding cloister
        if(topTile != blankTile){
            numNonBlankTiles++;
        }
        if(topLeftTile != blankTile){
           numNonBlankTiles++; 
        }
        if(topRightTile != blankTile){
           numNonBlankTiles++;
        }
        if(rightTile != blankTile){
           numNonBlankTiles++;
        }
        if(bottomTile != blankTile){
           numNonBlankTiles++; 
        }
        if(bottomLeftTile != blankTile){
           numNonBlankTiles++;
        }
        if(bottomRightTile != blankTile){
           numNonBlankTiles++; 
        }
        if(leftTile != blankTile){
           numNonBlankTiles++; 
        }
        
        //Calculate score for a completed cloister during the game
        if(numNonBlankTiles == 8 && endGame == false)
        {
            if(description[7] == '1'){
               score1 = score1 + 9;
               meeplesRemainingPlayer1++;
            }else if(description[7] == '2') {
               score2 = score2 + 9;
               meeplesRemainingPlayer2++;
            }
            
            //Remove meeple from cloister tile on board
            String newDescription = ""+description[0]+description[1]+description[2]+description[3]+"-0-0-0-00000";
            URL imageNew = this.getClass().getClassLoader().getResource("Images"+image.getRotateValue()+"/"+"tile"+image.getTileNumber()+"-"+newDescription+".png");
            ImageIcon image2 = new ImageIcon(imageNew);
            image2.setDescription(newDescription);
            jTableGrid.setValueAt(image2, row, col);
            board[row][col] = new placedTile(row,col,image2,image.getTileNumber(),image.getRotateValue());
            return true;
        }
        
        //Calculate score for an incomplete cloister at the end of the game
        if(endGame == true)
        {
            if(description[7] == '1'){
               score1 = score1 + numNonBlankTiles + 1;
               meeplesRemainingPlayer1++;
            }else if(description[7] == '2'){
               score2 = score2 + numNonBlankTiles + 1;
               meeplesRemainingPlayer2++;
            }
            
            //Remove meeple from cloister tile on board
            String newDescription = ""+description[0]+description[1]+description[2]+description[3]+"-0-0-0-00000";
            URL imageNew = this.getClass().getClassLoader().getResource("Images"+image.getRotateValue()+"/"+"tile"+image.getTileNumber()+"-"+newDescription+".png");
            ImageIcon image2 = new ImageIcon(imageNew);
            image2.setDescription(newDescription);
            jTableGrid.setValueAt(image2, row, col);
            board[row][col] = new placedTile(row,col,image2,image.getTileNumber(),image.getRotateValue());
            return true;
        }
        
        return false;
    }
    
    /************************************************************************************
    * Method to calculate the possible score of a newly placed tile containing a cloister
    *************************************************************************************/
    public int scoreCloisterTest(int r, int c, ImageIcon image){
        int row = r;
        int col = c;
        ImageIcon topTile = board[row-1][col].getImage(); //Tile top
        ImageIcon topLeftTile = board[row-1][col-1].getImage(); //Tile top left
        ImageIcon topRightTile = board[row-1][col+1].getImage(); //Tile top right
        ImageIcon rightTile = board[row][col+1].getImage(); //Tile right
        ImageIcon bottomTile = board[row+1][col].getImage(); //Tile bottom
        ImageIcon bottomLeftTile = board[row+1][col-1].getImage(); //Tile bottom left
        ImageIcon bottomRightTile = board[row+1][col+1].getImage(); //Tile bottom right
        ImageIcon leftTile = board[row][col-1].getImage(); //Tile left
        char description[] = image.getDescription().toCharArray(); //Description of cloister tile
        int numNonBlankTiles = 0; //Integer to keep track of the number of non-blank tiles surrounding the cloister
        
        //Count the number of blank tiles surrounding cloister
        if(topTile != blankTile){
            numNonBlankTiles++;
        }
        if(topLeftTile != blankTile){
           numNonBlankTiles++; 
        }
        if(topRightTile != blankTile){
           numNonBlankTiles++;
        }
        if(rightTile != blankTile){
           numNonBlankTiles++;
        }
        if(bottomTile != blankTile){
           numNonBlankTiles++; 
        }
        if(bottomLeftTile != blankTile){
           numNonBlankTiles++;
        }
        if(bottomRightTile != blankTile){
           numNonBlankTiles++; 
        }
        if(leftTile != blankTile){
           numNonBlankTiles++; 
        }
        
        //Calculate the possible score for completing a cloister
        if(numNonBlankTiles == 8)
        {
            if(description[7] == '1'){
               tilePlacementScorePlayer1 = tilePlacementScorePlayer1 + 9;
            }else if(description[7] == '2'){
               tilePlacementScorePlayer2 = tilePlacementScorePlayer2 + 9;
            }
        }
        
        return numNonBlankTiles;
    }
    
    /*********************************************************************************
    * Method to launch the frame that contains the final scores at the end of the game
    **********************************************************************************/
    public void launchFinalScoreFrame(){
        FinalScoreFrame frame = new FinalScoreFrame("","","","","","","","");
        if(score1 > score2){
          frame = new FinalScoreFrame(player1Name,player2Name,Integer.toString(score1),Integer.toString(score2),"Winner!","Loser!",player1Type,player2Type); 
        }else if(score2 > score1){
          frame = new FinalScoreFrame(player1Name,player2Name,Integer.toString(score1),Integer.toString(score2),"Loser!","Winner!",player1Type,player2Type);
        }else if(score1 == score2){
          frame = new FinalScoreFrame(player1Name,player2Name,Integer.toString(score1),Integer.toString(score2),"Tied!","Tied!",player1Type,player2Type);
        }
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }
    
    /******************************************************************
    * Method to launch the frame that contains the rules for the game
    *******************************************************************/
    public void launchRulesFrame(){
        RulesFrame frame = new RulesFrame();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        jTableGrid.requestFocusInWindow();
    }
    
    /******************************************************************
    * Method to launch the frame that contains the controls for the game
    *******************************************************************/
    public void launchControlsFrame(){
        ControlsFrame frame = new ControlsFrame();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        jTableGrid.requestFocusInWindow();
    }
       

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneGrid = new javax.swing.JScrollPane();
        jTableGrid = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButtonNewGame = new javax.swing.JButton();
        jButtonRules = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBoxAutoShowValidTilePlacement = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabelPlayer1 = new javax.swing.JLabel();
        jLabelPlayer2 = new javax.swing.JLabel();
        jLabelScore1 = new javax.swing.JLabel();
        jLabelScore2 = new javax.swing.JLabel();
        jLabelMeeples1 = new javax.swing.JLabel();
        jLabelMeeples2 = new javax.swing.JLabel();
        jTextFieldPlayer1 = new javax.swing.JTextField();
        jTextFieldPlayer2 = new javax.swing.JTextField();
        jTextFieldScore1 = new javax.swing.JTextField();
        jTextFieldMeeples1 = new javax.swing.JTextField();
        jTextFieldScore2 = new javax.swing.JTextField();
        jTextFieldMeeples2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabelCurrentTilePicture = new javax.swing.JLabel();
        jLabelCurentTile = new javax.swing.JLabel();
        jLabelTilesRemaining = new javax.swing.JLabel();
        jTextFieldTilesRemaining = new javax.swing.JTextField();
        jLabelPlayer1Move = new javax.swing.JLabel();
        jLabelPlayer2Move = new javax.swing.JLabel();
        jTextFieldPlayer2Move = new javax.swing.JTextField();
        jTextFieldPlayer1Move = new javax.swing.JTextField();
        jButtonRedrawTile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Carcassonne");
        setBackground(new java.awt.Color(204, 204, 204));
        setName("frameGameGUI"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1920, 1080));

        jScrollPaneGrid.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPaneGrid.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPaneGrid.setDoubleBuffered(true);

        jTableGrid.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15", "Title 16", "Title 17", "Title 18", "Title 19", "Title 20", "Title 21", "Title 22", "Title 23", "Title 24", "Title 25", "Title 26", "Title 27", "Title 28", "Title 29", "Title 30", "Title 31", "Title 32", "Title 33", "Title 34", "Title 35", "Title 36", "Title 37", "Title 38", "Title 39", "Title 40", "Title 41", "Title 42", "Title 43", "Title 44", "Title 45", "Title 46", "Title 47", "Title 48", "Title 49", "Title 50", "Title 51", "Title 52", "Title 53", "Title 54", "Title 55", "Title 56", "Title 57", "Title 58", "Title 59", "Title 60", "Title 61", "Title 62", "Title 63", "Title 64", "Title 65", "Title 66", "Title 67", "Title 68", "Title 69", "Title 70", "Title 71", "Title 72", "Title 73", "Title 74", "Title 75", "Title 76", "Title 77", "Title 78", "Title 79", "Title 80", "Title 81", "Title 82", "Title 83", "Title 84", "Title 85", "Title 86", "Title 87", "Title 88", "Title 89", "Title 90", "Title 91"
            }
        ));
        jTableGrid.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableGrid.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableGrid.setDoubleBuffered(true);
        jTableGrid.setMinimumSize(new java.awt.Dimension(0, 0));
        jTableGrid.setName(""); // NOI18N
        jTableGrid.setPreferredSize(new java.awt.Dimension(11830, 11830));
        jTableGrid.setRequestFocusEnabled(false);
        jTableGrid.setRowHeight(130);
        jTableGrid.setRowSelectionAllowed(false);
        jTableGrid.setUpdateSelectionOnSort(false);
        jScrollPaneGrid.setViewportView(jTableGrid);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jButtonNewGame.setText("New Game");
        jButtonNewGame.setDoubleBuffered(true);
        jButtonNewGame.setFocusable(false);
        jButtonNewGame.setMinimumSize(new java.awt.Dimension(0, 20));
        jButtonNewGame.setName(""); // NOI18N
        jButtonNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewGameActionPerformed(evt);
            }
        });

        jButtonRules.setText("Rules");
        jButtonRules.setDoubleBuffered(true);
        jButtonRules.setFocusable(false);
        jButtonRules.setMinimumSize(new java.awt.Dimension(0, 20));
        jButtonRules.setName(""); // NOI18N
        jButtonRules.setPreferredSize(new java.awt.Dimension(70, 25));
        jButtonRules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRulesActionPerformed(evt);
            }
        });

        jButton2.setText("Controls");
        jButton2.setDoubleBuffered(true);
        jButton2.setFocusable(false);
        jButton2.setMinimumSize(new java.awt.Dimension(0, 20));
        jButton2.setName(""); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(70, 25));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBoxAutoShowValidTilePlacement.setSelected(true);
        jCheckBoxAutoShowValidTilePlacement.setText("Automatically Show Valid Tile Placements");
        jCheckBoxAutoShowValidTilePlacement.setDoubleBuffered(true);
        jCheckBoxAutoShowValidTilePlacement.setFocusable(false);
        jCheckBoxAutoShowValidTilePlacement.setMinimumSize(new java.awt.Dimension(0, 20));
        jCheckBoxAutoShowValidTilePlacement.setName(""); // NOI18N
        jCheckBoxAutoShowValidTilePlacement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAutoShowValidTilePlacementActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNewGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonRules, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jCheckBoxAutoShowValidTilePlacement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRules, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonNewGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jCheckBoxAutoShowValidTilePlacement, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabelPlayer1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelPlayer1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelPlayer1.setText("Player 1:");
        jLabelPlayer1.setDoubleBuffered(true);
        jLabelPlayer1.setMinimumSize(new java.awt.Dimension(0, 0));
        jLabelPlayer1.setName(""); // NOI18N

        jLabelPlayer2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelPlayer2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelPlayer2.setText("Player 2:");
        jLabelPlayer2.setDoubleBuffered(true);
        jLabelPlayer2.setMinimumSize(new java.awt.Dimension(0, 0));
        jLabelPlayer2.setName(""); // NOI18N

        jLabelScore1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabelScore1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelScore1.setText("Score:");
        jLabelScore1.setDoubleBuffered(true);
        jLabelScore1.setMinimumSize(new java.awt.Dimension(0, 0));
        jLabelScore1.setName(""); // NOI18N

        jLabelScore2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabelScore2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelScore2.setText("Score:");
        jLabelScore2.setDoubleBuffered(true);
        jLabelScore2.setMinimumSize(new java.awt.Dimension(0, 0));
        jLabelScore2.setName(""); // NOI18N

        jLabelMeeples1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabelMeeples1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelMeeples1.setText("Meeples:");
        jLabelMeeples1.setDoubleBuffered(true);
        jLabelMeeples1.setMinimumSize(new java.awt.Dimension(0, 0));

        jLabelMeeples2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabelMeeples2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelMeeples2.setText("Meeples:");
        jLabelMeeples2.setDoubleBuffered(true);
        jLabelMeeples2.setMinimumSize(new java.awt.Dimension(0, 0));
        jLabelMeeples2.setName(""); // NOI18N

        jTextFieldPlayer1.setEditable(false);
        jTextFieldPlayer1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldPlayer1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPlayer1.setToolTipText("");
        jTextFieldPlayer1.setDoubleBuffered(true);
        jTextFieldPlayer1.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextFieldPlayer1.setName(""); // NOI18N

        jTextFieldPlayer2.setEditable(false);
        jTextFieldPlayer2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldPlayer2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPlayer2.setToolTipText("");
        jTextFieldPlayer2.setDoubleBuffered(true);
        jTextFieldPlayer2.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextFieldPlayer2.setName(""); // NOI18N

        jTextFieldScore1.setEditable(false);
        jTextFieldScore1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldScore1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldScore1.setDoubleBuffered(true);
        jTextFieldScore1.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextFieldScore1.setName(""); // NOI18N

        jTextFieldMeeples1.setEditable(false);
        jTextFieldMeeples1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldMeeples1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldMeeples1.setDoubleBuffered(true);
        jTextFieldMeeples1.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextFieldMeeples1.setName(""); // NOI18N

        jTextFieldScore2.setEditable(false);
        jTextFieldScore2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldScore2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldScore2.setDoubleBuffered(true);
        jTextFieldScore2.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextFieldScore2.setName(""); // NOI18N

        jTextFieldMeeples2.setEditable(false);
        jTextFieldMeeples2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldMeeples2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldMeeples2.setDoubleBuffered(true);
        jTextFieldMeeples2.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextFieldMeeples2.setName(""); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), java.awt.Color.gray));
        jPanel3.setPreferredSize(new java.awt.Dimension(225, 200));

        jLabelCurrentTilePicture.setBackground(new java.awt.Color(153, 153, 153));
        jLabelCurrentTilePicture.setDoubleBuffered(true);
        jLabelCurrentTilePicture.setMaximumSize(new java.awt.Dimension(234324, 234324));
        jLabelCurrentTilePicture.setPreferredSize(new java.awt.Dimension(130, 130));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jLabelCurrentTilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabelCurrentTilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabelCurentTile.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelCurentTile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCurentTile.setText("Current Tile");
        jLabelCurentTile.setDoubleBuffered(true);
        jLabelCurentTile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelCurentTile.setMinimumSize(new java.awt.Dimension(0, 0));
        jLabelCurentTile.setName(""); // NOI18N

        jLabelTilesRemaining.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabelTilesRemaining.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTilesRemaining.setText("Tiles Remaining:");
        jLabelTilesRemaining.setToolTipText("");
        jLabelTilesRemaining.setDoubleBuffered(true);
        jLabelTilesRemaining.setMinimumSize(new java.awt.Dimension(0, 0));
        jLabelTilesRemaining.setName(""); // NOI18N

        jTextFieldTilesRemaining.setEditable(false);
        jTextFieldTilesRemaining.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldTilesRemaining.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTilesRemaining.setDoubleBuffered(true);
        jTextFieldTilesRemaining.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextFieldTilesRemaining.setName(""); // NOI18N

        jLabelPlayer1Move.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabelPlayer1Move.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelPlayer1Move.setText("Move:");
        jLabelPlayer1Move.setDoubleBuffered(true);
        jLabelPlayer1Move.setMinimumSize(new java.awt.Dimension(0, 0));
        jLabelPlayer1Move.setName(""); // NOI18N

        jLabelPlayer2Move.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabelPlayer2Move.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelPlayer2Move.setText("Move:");
        jLabelPlayer2Move.setDoubleBuffered(true);
        jLabelPlayer2Move.setMinimumSize(new java.awt.Dimension(0, 0));
        jLabelPlayer2Move.setName(""); // NOI18N

        jTextFieldPlayer2Move.setEditable(false);
        jTextFieldPlayer2Move.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldPlayer2Move.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPlayer2Move.setDoubleBuffered(true);
        jTextFieldPlayer2Move.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextFieldPlayer2Move.setName(""); // NOI18N

        jTextFieldPlayer1Move.setEditable(false);
        jTextFieldPlayer1Move.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldPlayer1Move.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPlayer1Move.setDoubleBuffered(true);
        jTextFieldPlayer1Move.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextFieldPlayer1Move.setName(""); // NOI18N

        jButtonRedrawTile.setText("Redraw Tile");
        jButtonRedrawTile.setToolTipText("");
        jButtonRedrawTile.setDoubleBuffered(true);
        jButtonRedrawTile.setFocusable(false);
        jButtonRedrawTile.setMinimumSize(new java.awt.Dimension(0, 20));
        jButtonRedrawTile.setName(""); // NOI18N
        jButtonRedrawTile.setPreferredSize(new java.awt.Dimension(200, 25));
        jButtonRedrawTile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRedrawTileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelMeeples2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelScore1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabelMeeples1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelPlayer1Move, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelPlayer2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelScore2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelPlayer2Move, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldScore1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldPlayer1Move, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldMeeples1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldPlayer1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldPlayer2Move, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldMeeples2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jTextFieldScore2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelTilesRemaining, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldTilesRemaining, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelCurentTile, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonRedrawTile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldScore1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelScore1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMeeples1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMeeples1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPlayer1Move, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldPlayer1Move, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldScore2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelScore2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMeeples2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMeeples2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPlayer2Move, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldPlayer2Move, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jLabelCurentTile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTilesRemaining, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTilesRemaining, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jButtonRedrawTile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPaneGrid, javax.swing.GroupLayout.DEFAULT_SIZE, 1622, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPaneGrid, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Launch the frame containingt the games rules when the rules button is pressed
    private void jButtonRulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRulesActionPerformed
        launchRulesFrame();
    }//GEN-LAST:event_jButtonRulesActionPerformed

    //Start a new game when the new game button is pressed
    private void jButtonNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewGameActionPerformed
        newGame(); 
    }//GEN-LAST:event_jButtonNewGameActionPerformed

    private void jButtonRedrawTileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRedrawTileActionPerformed
        redrawTile();
    }//GEN-LAST:event_jButtonRedrawTileActionPerformed

    private void jCheckBoxAutoShowValidTilePlacementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAutoShowValidTilePlacementActionPerformed
        if(jCheckBoxAutoShowValidTilePlacement.isSelected()){
           showValidTilePlacementLocations = true;
           if(!isTilePlaced){
              Vector<placedTile> validTilePlacements = getValidTilePlacements();
              setValidTilePlacements(validTilePlacements);
           }
        }else{
           showValidTilePlacementLocations = false;
           if(!isTilePlaced){
              removeValidTilePlacementImages();
           }
        }
    }//GEN-LAST:event_jCheckBoxAutoShowValidTilePlacementActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        launchControlsFrame();
    }//GEN-LAST:event_jButton2ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonNewGame;
    private javax.swing.JButton jButtonRedrawTile;
    private javax.swing.JButton jButtonRules;
    private javax.swing.JCheckBox jCheckBoxAutoShowValidTilePlacement;
    private javax.swing.JLabel jLabelCurentTile;
    private javax.swing.JLabel jLabelCurrentTilePicture;
    private javax.swing.JLabel jLabelMeeples1;
    private javax.swing.JLabel jLabelMeeples2;
    private javax.swing.JLabel jLabelPlayer1;
    private javax.swing.JLabel jLabelPlayer1Move;
    private javax.swing.JLabel jLabelPlayer2;
    private javax.swing.JLabel jLabelPlayer2Move;
    private javax.swing.JLabel jLabelScore1;
    private javax.swing.JLabel jLabelScore2;
    private javax.swing.JLabel jLabelTilesRemaining;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPaneGrid;
    private javax.swing.JTable jTableGrid;
    private javax.swing.JTextField jTextFieldMeeples1;
    private javax.swing.JTextField jTextFieldMeeples2;
    private javax.swing.JTextField jTextFieldPlayer1;
    private javax.swing.JTextField jTextFieldPlayer1Move;
    private javax.swing.JTextField jTextFieldPlayer2;
    private javax.swing.JTextField jTextFieldPlayer2Move;
    private javax.swing.JTextField jTextFieldScore1;
    private javax.swing.JTextField jTextFieldScore2;
    private javax.swing.JTextField jTextFieldTilesRemaining;
    // End of variables declaration//GEN-END:variables
}

