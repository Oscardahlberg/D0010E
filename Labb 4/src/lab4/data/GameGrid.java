package lab4.data;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Observable;

/**
 * Represents the 2-d game grid
 */

/**
 * 
 * @author Shahriar Chegini och Oscar Dahlberg
 * 
 * här skapar vi själva brädan för spelet och 
 * kollar om någon har vunnit
 *
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
        return horizontalWin(player) || verticalWin(player) || diagonalDownWin(player) || diagonalUpWin(player);
    }

    /**
     *
     *horizontalWin kollar om någon har tillräckligt med kulor i rad för att vinna
     *
     *det for looparna gör är att den tar en index i taget för varje array i den 
     *2 dim. arrayen grid och sedan håller reda på hur många kulor ligger i rad för spelaren.
     *
     *Om den kommer till ett index där det inte ligger något eller till en motståndares kula
     *så resetar inARowCount.
     */
    private boolean horizontalWin(int player){

    	for(int x = 0; x < getSize(); x++){
        	int inARowCount = 0;
            for(int y = 0; y < getSize(); y++){
                if(grid[x][y] == player){
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
    
    /**
    *
    *VerticalWin kollar om någon har tillräckligt med kulor i rad för att vinna
    *
    *det looparna gör är att den kollar en x värde i taget, och går igenom uppifrån  
    *och ner för varje x värde. Och kollar om det är tillräckligt med kulor i rad
    *för att vinna
    */
    private boolean verticalWin(int player){
       
        for(int x = 0; x < getSize(); x++){
        	int inARowCount = 0;
            for(int y = 0; y < getSize(); y++){
                if(grid[y][x] == player){
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
    
    /**
    *
    *kollar om player har vunnit snett ner åt höger(\).
    *
    *det looparna gör är att den tar ett x värde i taget och
    *går igenom för varje y värde och kollar diagonalt
    *om det finns tillräckligt med kulor i rad för att vinna.
    *
    *den går till nästa y värde och resetar inARowCount om den någon gång får
    *ett ArrayIndexOutOfBoundsException Error.
    */
    private boolean diagonalDownWin(int player){
        
        for(int x = 0; x < getSize(); x++){
        	int inARowCount = 0;
            for(int y = 0; y < getSize(); y++){
                for(int n = 0; n < getSize(); n ++){
                    try{
                        if(grid[x + n][y + n] == player){
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
    
    /**
    *
    *kollar om player har vunnit snett upp åt höger(/).
    *
    *det looparna gör är att den tar ett x värde i taget och
    *går igenom för varje y värde och kollar diagonalt uppåt
    *om det finns tillräckligt med kulor i rad för att vinna.
    *
    *den går till nästa y värde och resetar inARowCount om den någon gång får
    *ett ArrayIndexOutOfBoundsException Error.
    */
    private boolean diagonalUpWin(int player){

        for(int y = 0; y < getSize(); y++){
        	int inARowCount = 0;
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