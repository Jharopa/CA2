package org.CA2.models;

import org.CA2.interfaces.Printable;
import org.CA2.exceptions.AssetException;

public class CD extends Asset implements Printable {
    /**
     * String representing the CD's producer
     */
    private String producer;
    /**
     * String representing the CD's performer
     */
    private String performer;
    /**
     * Integer representing the CD's playtime
     */
    private int playtime;

    /**
     * Class constructor specifying the CD object's id, title, producer, performer, playtime, and availability
     * @param id Initializes the CD object's id
     * @param title Initializes the CD object's title
     * @param producer Initializes the CD object's producer
     * @param performer Initializes the CD object's performer
     * @param playtime Initializes the CD object's playtime
     * @param availability Initializes the CD object's availability
     * @throws AssetException When the playtime specified is less than 0
     */
    public CD(int id, String title, String producer, String performer, int playtime, boolean availability) throws AssetException {
        super(id, title, availability);

        if (playtime < 0) {
            throw new AssetException("CD playtime can not be less than 0");
        }

        this.producer = producer;
        this.performer = performer;
        this.playtime = playtime;
    }

    /**
     * Accessor function for the producer attribute
     * @return The object's producer attribute
     */
    public String getProducer() {
        return producer;
    }

    /**
     * Mutator function for the producer attribute
     * @param producer The new value of the CD object's producer attribute
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }

    /**
     * Accessor function for the performer attribute
     * @return The object's performer attribute
     */
    public String getPerformer() {
        return performer;
    }

    /**
     * Mutator function for the performer attribute
     * @param performer The new value of the CD object's producer attribute
     */
    public void setPerformer(String performer) {
        this.performer = performer;
    }

    /**
     * Accessor function for the playtime attribute
     * @return The object's playtime attribute
     */
    public int getPlaytime() {
        return playtime;
    }

    /**
     * Mutator function for the playtime attribute
     * @param playtime The new value of the CD object's playtime attribute
     */
    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    /**
     * Audiobook classes overridden toString function
     * @return A string representation of the audiobook object
     */
    @Override
    public String toString() {
        return super.toString() + String.format("%-20s%-20s%-12s%-12s", producer, performer, playtime, availability ? "available" : "unavailable");
    }

    /**
     * Implementation of the Printable interfaces abstract function print, used for printing
     * out the string representation of the object
     */
    @Override
    public void print() {
        System.out.printf("%-5s%-24s%-20s%-20s%-12s%-12s\n", "ID", "Title", "Producer", "Performer", "Playtime", "Status");
        System.out.println(this);
    }
}
