package org.CA2;

public class AudioBook extends Book implements Printable {

    private int duration;

    public AudioBook(int id, String title, String author, String ISBN, int duration, boolean availability) throws AssetException{
        super(id, title, author, ISBN, availability);

        if (duration < 0) {
            throw new AssetException("AudioBook duration can not be less than 0");
        }

        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) throws AssetException {
        if (duration < 0) {
            throw new AssetException("AudioBook duration can not be less than 0");
        }

        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("%-5s%-24s%-20s%-17s%-12s%-12s", id, title, author, ISBN, duration, availability ? "available" : "unavailable");
    }

    @Override
    public void print() {
        System.out.printf("%-5s%-24s%-20s%-17s%-12s%-12s\n", "ID", "Title", "Author", "ISBN", "Duration", "Status");
        System.out.println(this);
    }
}
