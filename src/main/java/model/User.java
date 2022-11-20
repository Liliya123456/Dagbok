package model;

import java.util.List;

/**
 * Klassen User inehåller Användares namn och list av antekningar som användare skapar.
 */
public class User {
    private String name;
    private List<Note> notes;

    /**
     * Konstrukör för klassen
      * @param name name på användare.
     * @param notes  list av antekningar som skapats.
     */
    public User(String name, List<Note> notes) {
        this.name = name;
        this.notes = notes;
    } public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}