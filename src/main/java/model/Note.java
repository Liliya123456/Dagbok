package model;

import java.time.LocalDate;
import java.util.Date;

/**
 * Klassen Note beskriver alla detaljer på användarens anteckningen.
 */
public class Note {
    private String titel;
    private String text;
    private String date;

    /**
     * Kontruktör for Note Klassen.
     * @param titel namn på anteckningen.
     * @param text  text av anteckningen.
     * @param date  date på anteckningen.
     */
    public Note(String titel, String text, String date) {
        this.titel = titel;
        this.text = text;
        this.date = date;

    }

    public Note() {

    }


    /**
     * Metoden getTitlel får namn på antekningen.
     * @return namn på titel.
     */
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
