package com.example.sijack.contest.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Sijack on 20/01/2018.
 */

@Entity
public class Room {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int x;
    private int y;
    private int width;
    private int height;
    private String profId;

    //Aula, lab., ufficio.
    private String type;

    //Nome aula/lab, numero ufficio
    private String number;

    //Nome edificio
    private String building;

    private int floor;

    public Room(){}

    /*
        Costruttore per aula o laboratorio.
     */
    public Room(int x, int y, int w, int h, String type, String number, String building, int floor) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.type = type;
        this.number = number;
        this.building = building;
        this.floor = floor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getProfId() {
        return profId;
    }

    public void setProfId(String profId) {
        this.profId = profId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public static Room[] populateData() {
        return new Room[] {
                new Room(216, 56, 108, 146, "Laboratorio", "Modelli", "F2", -1),
                new Room(80, 197, 138, 101, "Laboratorio", "SESA", "F2", -1),
                new Room(79, 440, 251, 131, "Laboratorio", "Sistemi", "F2", -1),
                new Room(385, 433, 250, 138, "Laboratorio", "Reti", "F2", -1),
                new Room(185, 77, 195, 172, "Aula", "F1", "F2", 0),
                new Room(380, 75, 232, 89, "Aula", "F2", "F2", 0),
                new Room(414, 165, 197, 91, "Aula", "F3", "F2", 0),
                new Room(437, 256, 174, 145, "Aula", "F4", "F2", 0),
                new Room(477, 401, 134, 215, "Aula", "F5", "F2", 0),
                new Room(372, 439, 105, 176, "Aula", "F6", "F2", 0),
                new Room(247, 439, 124, 175, "Aula", "F7", "F2", 0),
                new Room(73, 404, 174, 212, "Aula", "F8", "F2", 0),
                new Room(458, 21, 67, 84, "Ufficio", "77", "F2", 1),
                new Room(593, 21, 84, 74, "Ufficio", "81", "F2", 1),
                new Room(593, 97, 84, 74, "Ufficio", "82", "F2", 1),
                new Room(593, 173, 84, 68, "Ufficio", "83", "F2", 1),
                new Room(593, 240, 84, 68, "Ufficio", "84", "F2", 1),
                new Room(593, 388, 84, 68, "Ufficio", "89", "F2", 1),
                new Room(593, 456, 84, 68, "Ufficio", "90", "F2", 1),
                new Room(593, 523, 84, 68, "Ufficio", "91", "F2", 1),
                new Room(593, 592, 84, 83, "Ufficio", "92", "F2", 1),
                new Room(492, 591, 58, 82, "Ufficio", "40", "F2", 1),
                new Room(425, 591, 68, 82, "Ufficio", "39", "F2", 1),
                new Room(377, 454, 177, 94, "Ufficio", "43", "F2", 1),
                new Room(323, 452, 54, 96, "Ufficio", "36", "F2", 1),
                new Room(27, 591, 81, 80, "Ufficio", "31", "F2", 1),
                new Room(27, 523, 81, 66, "Ufficio", "22", "F2", 1),
                new Room(27, 457, 81, 66, "Ufficio", "8", "F2", 1),
                new Room(361, 105, 222, 220, "Aula", "P1", "F3", -1),
                new Room(362, 394, 223, 215, "Aula", "P2", "F3", -1),
                new Room(18, 114, 241, 226, "Aula", "P3", "F3", 0),
                new Room(327, 116, 196, 234, "Aula", "P4", "F3", 0),
                new Room(327, 353, 196, 128, "Aula", "P5", "F3", 0),
                new Room(327, 482, 196, 128, "Aula", "P6", "F3", 0),
                new Room(1180, 115, 108, 172, "Laboratorio", "ISIS 2", "F", 2),
                new Room(1060, 115, 120, 172, "Laboratorio", "ISIS 1", "F", 2),
                new Room(760, 333, 230, 182, "Laboratorio", "Turing", "F", 2),
                new Room(1697, 460, 74, 64, "Ufficio", "1", "F", 4),
                new Room(1629, 460, 64, 64, "Ufficio", "2", "F", 4),
                new Room(1553, 460, 74, 64, "Ufficio", "3", "F", 4),
                new Room(1433, 460, 62, 64, "Ufficio", "4", "F", 4),
                new Room(1003, 460, 72, 64, "Ufficio", "10", "F", 4),
                new Room(880, 460, 48, 64, "Ufficio", "11", "F", 4),
                new Room(832, 460, 48, 64, "Ufficio", "12", "F", 4),
                new Room(781, 460, 48, 64, "Ufficio", "13", "F", 4),
                new Room(735, 460, 48, 64, "Ufficio", "14", "F", 4),
                new Room(720, 380, 82, 50, "Ufficio", "40", "F", 4),
                new Room(720, 234, 82, 50, "Ufficio", "43", "F", 4),
                new Room(734, 136, 50, 66, "Ufficio", "44", "F", 4),
                new Room(784, 136, 48, 66, "Ufficio", "45", "F", 4),
                new Room(880, 136, 58, 66, "Ufficio", "47", "F", 4),
                new Room(1004, 136, 72, 66, "Ufficio", "48", "F", 4),
                new Room(1076, 136, 64, 66, "Ufficio", "49", "F", 4),
                new Room(1286, 136, 52, 66, "Ufficio", "57", "F", 4),
                new Room(1336, 136, 52, 66, "Ufficio", "58", "F", 4),
                new Room(1384, 136, 52, 66, "Ufficio", "59", "F", 4),
                new Room(1436, 136, 60, 66, "Ufficio", "60", "F", 4),
                new Room(1552, 136, 78, 66, "Ufficio", "61", "F", 4),
                new Room(1630, 136, 62, 66, "Ufficio", "62", "F", 4),
                new Room(1694, 136, 78, 66, "Ufficio", "63", "F", 4)
        };
    }
}
