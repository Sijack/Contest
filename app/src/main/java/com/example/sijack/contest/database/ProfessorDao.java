package com.example.sijack.contest.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Sijack on 19/02/2018.
 */

@Dao
public interface ProfessorDao {

    @Insert
    void insertAll(Professor... professors);

    @Query("SELECT * FROM Professor")
    List<Professor> getAll();

    @Query("SELECT * FROM Professor WHERE Professor.name LIKE :string OR Professor.surname LIKE :string")
    List<Professor> findProfessors(String string);

    @Query("SELECT * FROM Professor WHERE Professor.officeNumber = :officeNumber AND Professor.building = :building")
    List<Professor> getProfessorByOffice(String officeNumber, String building);
}
