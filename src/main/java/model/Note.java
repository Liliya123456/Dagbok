package model;

import java.time.LocalDate;
import java.util.Date;

public class Note {
    private String titel;
    private String text;
    private String date;


    public Note(String titel, String text, String date) {
        this.titel = titel;
        this.text = text;
        this.date = date;

    }

    public Note() {

    }


    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
