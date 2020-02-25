package lab4.data;

//import javax.xml.bind.annotation.XmlType;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Observable;

/**
 * Represents the 2-d game grid
 */


public class GameGrid extends Observable{

    public static final int EMPTY = 0;
    public static final int ME = 1;
    public static final int OTHER = 2;
    public static final int INROW = 5;

    public int[][] grid;

    /**
     * Constructor
     *
     * @param //size The width/height of the game grid
     */

    public static void main(String[] args) {
        GameGrid g1 = new GameGrid(10);
        
        //snett ner
//        g1.move(0, 0, ME);
//        g1.move(1, 1, ME);
//        g1.move(2, 2, ME);
//        g1.move(3, 3, ME);
//        g1.move(4, 4, ME);

        //raktNer
        
//        g1.move(0, 0, ME);
//        g1.move(0, 1, ME);
//        g1.move(0, 2, OTHER);
//        g1.move(0, 3, ME);
//        g1.move(0, 4, ME);
//        g1.move(0, 5, ME);
//        g1.move(0, 6, ME);
//        g1.move(0, 7, ME);

        
//        g1.move(5, 3, ME);
//        g1.move(5, 4, OTHER);
//        g1.move(5, 5, ME);
//        g1.move(5, 6, ME);
//        g1.move(5, 7, ME);

        for(int i[] : g1.grid){
            System.out.println(Arrays.toString(i));
        }
        
        System.out.println(g1.isWinner(ME));

    }

    GameGrid(int size){
        this.grid = new int[size][size];
    }

    /**
     * Reads a location of the grid
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return the value of the specified location
     */
    public int getLocation(int x, int y){
        return grid[y][x];
    }

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
    	System.out.println(x + ":x, y:" + y);
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
        if(horizontalWin(player) || verticalWin(player) || diagonalDownWin(player) || diagonalUpWin(player)){
            return true;
        }
        return false;
    }

    private boolean horizontalWin(int player){
        int inARowCount = 0;

        for(int[] i : grid){
            for(int y : i){

                if(y == player){
                	
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
                    try{
                        if(grid[i + n][y + n] == player){
                            inARowCount += 1;
                        }
                        else {
                            inARowCount = 0;
                        }
                        if(inARowCount == INROW){
                            return true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e){
                        inARowCount = 0;
                        break;
                    }

                }
            }
        }

        return false;
    }
    private boolean diagonalUpWin(int player){
        int inARowCount = 0;

        for(int y = 0; y < getSize(); y++){
            for(int x = 0; x < getSize(); x++){
                for(int n = 0; n < getSize(); n ++){
                    try{
                        if(grid[y - n][x + n] == player){
                            inARowCount += 1;
                        }
                        else {
                            inARowCount = 0;
                        }
                        if(inARowCount == INROW){
                            return true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e){
                        inARowCount = 0;
                        break;
                    }
                }
            }
        }
        return false;
    }
}