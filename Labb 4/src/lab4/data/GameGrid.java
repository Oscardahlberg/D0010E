package lab4.data;

import javax.xml.bind.annotation.XmlType;
import java.util.Observable;

/**
 * Represents the 2-d game grid
 */


public class GameGrid extends Observable{

    public static final int EMPTY = 0;
    public static final int ME = 1;
    public static final int OTHER = 2;
    public static final int INROW = 5;

    private int[][] grid;

    /**
     * Constructor
     *
     * @param size The width/height of the game grid
     */
    public GameGrid(int size){
        this.grid = new int[size][size];
    }

    /**
     * Reads a location of the grid
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return the value of the specified location
     */
    public int getLocation(int x, int y){}

    /**
     * Returns the size of the grid
     *
     * @return the grid size
     */
    public int getSize(){
        return grid.length;
    }

    /**
     * Enters a move in the game grid
     *
     * @param x the x position
     * @param y the y position
     * @param player
     * @return true if the insertion worked, false otherwise
     */
    public boolean move(int x, int y, int player){
        if(grid[y][x] == EMPTY){
            grid[y][x] = player;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Clears the grid of pieces
     */
    public void clearGrid(){
        this.grid = new int[getSize()][getSize()];
    }

    /**
     * Check if a player has 5 in row
     *
     * @param player the player to check for
     * @return true if player has 5 in row, false otherwise
     */
    public boolean isWinner(int player){

    }

    private boolean horizontalWin(int player){
        int inARowCount = 0;

        for(int[] i : grid){
            for(int y : i){

                if(i[y] == player){
                    inARowCount += 1;
                }
                else {
                    inARowCount = 0;
                }
                if(inARowCount == INROW){
                    return true;
                }
            }

        }
        return false;
    }
    private boolean verticalWin(int player){
        int inARowCount = 0;

        for(int i = 0; i < getSize(); i++){
            for(int y = 0; y < getSize(); y++){
                if(grid[y][i] == player){
                    inARowCount += 1;
                }
                else {
                    inARowCount = 0;
                }
                if(inARowCount == INROW){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean diagonalDownWin(int player){
        int inARowCount = 0;

        for(int i = 0; i < getSize(); i++){
            for(int y = 0; y < getSize(); y++){
                for(int n = 0; n < getSize(); n ++){
                    if(grid[y][n] == player){
                        inARowCount += 1;
                    }
                }
            }
        }

        return false;
    }
    private boolean diagonalUpWin(int player){

    }

}