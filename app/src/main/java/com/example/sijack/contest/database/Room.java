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
                new Room(185, 77, 195, 172, "Aula", "F1", "F2", 0),
                new Room(380, 75, 232, 89, "Aula", "F2", "F2", 0),
                new Room(414, 165, 197, 91, "Aula", "F3", "F2", 0),
                new Room(437, 256, 174, 145, "Aula", "F4", "F2", 0),
                new Room(477, 401, 134, 215, "Aula", "F5", "F2", 0),
                new Room(372, 439, 105, 176, "Aula", "F6", "F2", 0),
                new Room(247, 439, 124, 175, "Aula", "F7", "F2", 0),
                new Room(73, 404, 174, 212, "Aula", "F8", "F2", 0),
                new Room(361, 105, 222, 220, "Aula", "P1", "F3", -1),
                new Room(362, 394, 223, 215, "Aula", "P2", "F3", -1),
                new Room(18, 114, 241, 226, "Aula", "P3", "F3", 0),
                new Room(327, 116, 196, 234, "Aula", "P4", "F3", 0),
                new Room(327, 353, 196, 128, "Aula", "P5", "F3", 0),
                new Room(327, 482, 196, 128, "Aula", "P6", "F3", 0)

        };
    }
}
