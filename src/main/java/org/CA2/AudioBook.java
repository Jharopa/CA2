package org.CA2;

public class AudioBook extends Book {

    private int duration;

    public AudioBook(String title, String author, String ISBN, boolean availability, int duration) {
        super(title, author, ISBN, availability);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
