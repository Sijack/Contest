package com.example.sijack.contest.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Sijack on 19/02/2018.
 */

@Entity
public class Professor {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String surname;
    private String name;
    private String officeNumber;
    private String building;

    public Professor(String surname, String name, String officeNumber, String building) {
        this.surname = surname;
        this.name = name;
        this.officeNumber = officeNumber;
        this.building = building;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public static Professor[] populateData() {
        return new Professor[] {
                new Professor("Scarano", "Vittorio", "1", "F"),
                new Professor("Cattaneo", "Giuseppe", "2", "F"),
                new Professor("Persiano", "Giuseppe", "3", "F"),
                new Professor("Carpentieri", "Bruno", "4", "F"),
                new Professor("Napoli", "Margherita", "10", "F"),
                new Professor("Nota", "Giancarlo", "11", "F"),
                new Professor("De Rosa", "Carmela", "12", "F"),
                new Professor("De Marco", "Gianluca", "13", "F"),
                new Professor("Zizza", "Rosalba", "14", "F"),
                new Professor("Malandrino", "Delfina", "40", "F"),
                new Professor("Masucci", "Barbara", "43", "F"),
                new Professor("De Bonis", "Annalisa", "44", "F"),
                new Professor("D'Arco", "Paolo", "45", "F"),
                new Professor("La Torre", "Salvatore", "47", "F"),
                new Professor("De Santis", "Alfredo", "48", "F"),
                new Professor("De Felice", "Clelia", "49", "F"),
                new Professor("Anselmo", "Marcella", "57", "F"),
                new Professor("De Prisco", "Roberto", "58", "F"),
                new Professor("Rescigno", "Adele Anna", "59", "F"),
                new Professor("De Santis", "Filomena", "60", "F"),
                new Professor("Vaccaro", "Ugo", "61", "F"),
                new Professor("Gargano", "Luisa", "62", "F"),
                new Professor("Negro", "Alberto", "63", "F"),
                new Professor("Annunziato", "Mario", "8", "F2"),
                new Professor("Monsurrò", "Sara", "8", "F2"),
                new Professor("Cardone", "Angelamaria", "22", "F2"),
                new Professor("Conte", "Dajana", "22", "F2"),
                new Professor("De Nicola", "Antonio", "22", "F2"),
                new Professor("Lenzi", "Giacomo", "31", "F2"),
                new Professor("Gerla", "Giangiacomo", "31", "F2"),
                new Professor("Cerulli", "Raffaele", "36", "F2"),
                new Professor("Di Gironimo", "Patrizia", "39", "F2"),
                new Professor("Miranda", "Annamaria", "39", "F2"),
                new Professor("Carrabs", "Francesco", "40", "F2"),
                new Professor("Gentili", "Monica", "40", "F2"),
                new Professor("Risi", "Michele", "43", "F2"),
                new Professor("Gravino", "Carmine", "77", "F2"),
                new Professor("Tucci", "Maurizio", "77", "F2"),
                new Professor("Tortora", "Genoveffa", "81", "F2"),
                new Professor("Polese", "Giuseppe", "82", "F2"),
                new Professor("Deufemia", "Vincenzo", "82", "F2"),
                new Professor("Vitiello", "Giuliana", "83", "F2"),
                new Professor("Sebillo", "Monica", "83", "F2"),
                new Professor("Ferrucci", "Filomena", "84", "F2"),
                new Professor("Fuccella", "Vittorio", "84", "F2"),
                new Professor("De Lucia", "Andrea", "89", "F2"),
                new Professor("Francese", "Rita", "89", "F2"),
                new Professor("Di Crescenzo", "Antonio", "90", "F2"),
                new Professor("Martinucci", "Barbara", "90", "F2"),
                new Professor("Nobile", "Amelia Giuseppina", "91", "F2"),
                new Professor("Giorno", "Virginia", "91", "F2"),
                new Professor("Abate", "Andrea Francesco", "92", "F2"),
                new Professor("Di Stasi", "Mario", "92", "F2"),
                new Professor("Nappi", "Michele", "92", "F2"),

        };
    }

}
