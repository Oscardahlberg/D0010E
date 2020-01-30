package lab2;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

import java.awt.Color;
import java.lang.Math;
public class Driver {

	public void run() {
        int roomInt = 50;
        Room rooms[] = new Room [roomInt];
        Level level = new Level(roomInt);

        rooms = makeRooms(rooms, roomInt); //Makes the rooms ready to be placed

        level.place(rooms[0], 0, 0); //Places the first room

        placeRooms(rooms, roomInt, level);
	}

	private Room[] makeRooms(Room rooms[], int roomInt){
        for (int i = 0;i < roomInt; i++) {
            rooms[i] = new Room(
                            (int)Math.ceil(Math.random()*10),
                            (int)Math.ceil(Math.random()*10),
                    new Color(
                            (int)Math.ceil(Math.random()*255),
                            (int)Math.ceil(Math.random()*255),
                            (int)Math.ceil(Math.random()*255)));
        }
        return rooms;
    }

    private void placeRooms(Room rooms[], int roomInt, Level level) {
        for (int i = 1; i < roomInt; i++) {
            int ranRoom, x = 0, y = 0;
            do {
                ranRoom = (int) Math.floor(Math.random() * (i - 1));
                rooms[i].resetPointers();
                switch ((int) Math.floor(Math.random() * 4)) {
                    case 0:
                        if (rooms[ranRoom].northRoom != null) {
                            continue;
                        } else {
                            x = rooms[ranRoom].returnX() - rooms[i].returnWidth()
                                    + (int) Math.floor(Math.random() * (rooms[ranRoom].returnWidth()
                                    + rooms[i].returnWidth() - 1)) + 1;
                            y = rooms[ranRoom].returnY() + rooms[i].returnHeight();
                            rooms[i].southRoom = rooms[ranRoom];
                        }
                    case 1:
                        if (rooms[ranRoom].eastRoom != null) {
                            continue;
                        } else {
                            x = rooms[ranRoom].returnX() + rooms[ranRoom].returnWidth();
                            y = rooms[ranRoom].returnY() - rooms[i].returnHeight()
                                    + (int) Math.floor(Math.random() * (rooms[ranRoom].returnHeight()
                                    + rooms[i].returnHeight() - 1)) + 1;
                            rooms[i].westRoom = rooms[ranRoom];
                        }
                    case 2:
                        if (rooms[ranRoom].southRoom != null) {
                            continue;
                        } else {
                            x = rooms[ranRoom].returnX() - rooms[i].returnWidth()
                                    + (int) Math.floor(Math.random() * (rooms[ranRoom].returnWidth()
                                    + rooms[i].returnWidth() - 1)) + 1;
                            y = rooms[ranRoom].returnY() - rooms[ranRoom].returnHeight();
                            rooms[i].northRoom = rooms[ranRoom];
                        }
                    case 3:
                        if (rooms[ranRoom].westRoom != null) {
                            continue;
                        } else {
                            x = rooms[ranRoom].returnX() - rooms[i].returnWidth();
                            y = rooms[ranRoom].returnY() - rooms[i].returnHeight()
                                    + (int) Math.floor(Math.random() * (rooms[ranRoom].returnHeight()
                                    + rooms[i].returnHeight() - 1)) + 1;
                            rooms[i].eastRoom = rooms[ranRoom];
                        }
                }
            } while (!level.place(rooms[i], x, y));
        }
    }

    private void justMeming(Room rooms[], int roomInt){
        for (int i = 0; i < roomInt; i++) {
            rooms[i].connectNorthTo(rooms[(int)Math.floor(Math.random()*roomInt)]);
            rooms[i].connectEastTo(rooms[(int)Math.floor(Math.random()*roomInt)]);
            rooms[i].connectSouthTo(rooms[(int)Math.floor(Math.random()*roomInt)]);
            rooms[i].connectWestTo(rooms[(int)Math.floor(Math.random()*roomInt)]);
        }
    }

}
