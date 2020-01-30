
package lab2.level;

import java.util.Observable;


public class Level extends Observable {
	private Room rooms[];
	private int index = 0;

	public Level(int size) {
		rooms = new Room[size];
	}
	
	public boolean place(Room r, int x, int y)  {

		int rLeft = r.returnX();
		int rRight = rLeft + r.returnWidth();
		int rTop = r.returnY();
		int rBottom = rTop - r.returnHeight();

		for(int i = 0; i < index; i++){
			int iLeft = rooms[i].returnX();
			int iRight = iLeft + rooms[i].returnWidth();
			int iTop = rooms[i].returnY();
			int iBottom = iTop - rooms[i].returnHeight();

			if(!(rLeft < iRight && rRight > iLeft && rTop > iBottom && rBottom < iTop)){
				return false;
			}
		}

		r.setCoords(x, y);

		Room connectedRoom;
		if (r.northRoom != null) {
			r.northRoom.connectSouthTo(r);
		} else if (r.westRoom != null){
			r.westRoom.connectEastTo(r);
		} else if (r.southRoom != null){
			r.southRoom.connectNorthTo(r);
		} else if (r.eastRoom != null){
			r.eastRoom.connectWestTo(r);
		}

		rooms[index] = r;
		index++;
        return true;
	}
	
	public void firstLocation(Room r) {

	}
	
}
