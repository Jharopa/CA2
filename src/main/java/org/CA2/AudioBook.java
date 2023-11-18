package org.CA2;

public class AudioBook extends Book {

    private int duration;

    public AudioBook(String title, String author, String ISBN, int duration, boolean availability) throws AssetException{
        super(title, author, ISBN, availability);

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
}
