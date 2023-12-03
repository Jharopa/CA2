package org.CA2.models;

import org.CA2.interfaces.Printable;
import org.CA2.exceptions.AssetException;

public class AudioBook extends Book implements Printable {
    /**
     * Integer representing the audiobook's duration in minutes
     */
    private int duration;

    /**
     * Class constructor specifying the audiobook object's id, title, author, ISBN, duration, and availability
     * @param id Initializes the audiobook object's id
     * @param title Initializes the audiobook object's title
     * @param author Initializes the audiobook object's author
     * @param ISBN Initializes the audiobook object's ISBN
     * @param duration Initializes the audiobook object's duration
     * @param availability Initializes the audiobook object's availability
     * @throws AssetException When the duration specified is a negative number
     */
    public AudioBook(int id, String title, String author, String ISBN, int duration, boolean availability) throws AssetException{
        super(id, title, author, ISBN, availability);

        if (duration < 0) {
            throw new AssetException("AudioBook duration can not be less than 0");
        }

        this.duration = duration;
    }

    /**
     * Accessor function for the duration attribute
     * @return The object's duration attribute
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Mutator function for the duration attribute
     * @param duration The new value of the asset object's duration attribute
     * @throws AssetException When the duration specified is a negative number
     */
    public void setDuration(int duration) throws AssetException {
        if (duration < 0) {
            throw new AssetException("AudioBook duration can not be less than 0");
        }

        this.duration = duration;
    }

    /**
     * Audiobook classes overridden toString function
     * @return A string representation of the audiobook object
     */
    @Override
    public String toString() {
        return String.format("%-5s%-24s%-20s%-17s%-12s%-12s", id, title, author, ISBN, duration, availability ? "available" : "unavailable");
    }

    /**
     * Implementation of the Printable interfaces abstract function print, used for printing
     * out the string representation of the object
     */
    @Override
    public void print() {
        System.out.printf("%-5s%-24s%-20s%-17s%-12s%-12s\n", "ID", "Title", "Author", "ISBN", "Duration", "Status");
        System.out.println(this);
    }
}
