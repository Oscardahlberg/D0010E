
package lab2.level;

import java.awt.Color;


public class Room {
    public Room northRoom = null;
    public Room eastRoom = null;
    public Room southRoom = null;
    public Room westRoom = null;

    private Color floorColor;
    private int x;
    private int y;

    private int xcoord;
    private int ycoord;

	public Room(int dx, int dy, Color color) {

        floorColor = color;
        x = dx;
		y = dy;
        System.out.println(floorColor);
        System.out.println(x + y);
	}

	public void connectNorthTo(Room r) {
        northRoom = r;
	}

	public void connectEastTo(Room r) {
        eastRoom = r;
	}

	public void connectSouthTo(Room r) {
        southRoom = r;
	}

	public void connectWestTo(Room r) {
        westRoom = r;
	}


    public void resetPointers() {
	    northRoom = null;
        eastRoom = null;
        southRoom = null;
        westRoom = null;
    }

    protected void setCoords(int x, int y) {
        xcoord = x;
        ycoord = y;
    }
    public int returnHeight (){
		return y;

	}
	public int returnWidth (){
		return x;

	}
	public int returnY (){
		return ycoord;

	}
	public int returnX (){
		return xcoord;

	}
}
