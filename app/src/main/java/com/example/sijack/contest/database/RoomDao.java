package com.example.sijack.contest.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Sijack on 25/01/2018.
 */

@Dao
public interface RoomDao {

    @Insert
    void insertAll(Room... rooms);

    @Query("SELECT * FROM Room")
    List<Room> getAll();

    @Query("SELECT * FROM Room WHERE Room.building = :building AND Room.floor = :floor")
    List <Room> getRoomsByBuildingFloor(String building, int floor);

    @Query("SELECT * FROM Room WHERE Room.number LIKE :string OR Room.type LIKE :string")
    List<Room> getRoomsByString(String string);

    @Query("SELECT * FROM Room WHERE Room.id = :id")
    Room getRoomById(int id);
}
