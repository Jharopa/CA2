package org.CA2;

import java.time.LocalDate;

public class Thesis extends Asset implements Printable {
    /**
     * String representing the thesis' author
     */
    private String author;

    /**
     * String representing the thesis' topic
     */
    private String topic;

    /**
     * String representing the thesis' abstract
     */
    private String Abstract;

    /**
     * LocalDate representing the thesis' publish date
     */
    private LocalDate datePublished;

    /**
     * Class constructor specifying the thesis object's id, title, author, topic, Abstract, datePublished, and availability
     * @param id Initializes the thesis object's id
     * @param title Initializes the thesis object's title
     * @param author Initializes the thesis object's author
     * @param topic Initializes the thesis object's topic
     * @param Abstract Initializes the thesis object's Abstract
     * @param datePublished Initializes the thesis object's datePublished
     * @param availability Initializes the thesis object's availability
     * @throws AssetException When the publishDate specified is in the future
     */
    public Thesis(int id, String title, String author, String topic, String Abstract, LocalDate datePublished, boolean availability) throws AssetException {
        super(id, title, availability);

        if (datePublished.isAfter(LocalDate.now())) {
            throw new AssetException("Theses datePublished can not be in the future");
        }

        this.author = author;
        this.topic = topic;
        this.Abstract = Abstract;
        this.datePublished = datePublished;
    }

    /**
     * Accessor function for the author attribute
     * @return The object's author attribute
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Mutator function for author name attribute
     * @param author The new value of the thesis object's author attribute
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Accessor function for the topic attribute
     * @return The object's topic attribute
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Mutator function for topic name attribute
     * @param topic The new value of the thesis object's topic attribute
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Accessor function for the Abstract attribute
     * @return The object's Abstract attribute
     */
    public String getAbstract() {
        return Abstract;
    }

    /**
     * Mutator function for Abstract name attribute
     * @param anAbstract The new value of the thesis object's Abstract attribute
     */
    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }

    /**
     * Accessor function for the datePublished attribute
     * @return The object's datePublished attribute
     */
    public LocalDate getDatePublished() {
        return datePublished;
    }

    /**
     * Mutator function for datePublished name attribute
     * @param datePublished The new value of the thesis object's datePublished attribute
     * @throws AssetException When the publishDate specified is in the future
     */
    public void setDatePublished(LocalDate datePublished) throws AssetException {
        if (datePublished.isAfter(LocalDate.now())) {
            throw new AssetException("Theses datePublished can not be in the future");
        }

        this.datePublished = datePublished;
    }

    /**
     * Thesis class' overridden toString function
     * @return A string representation of the library user object
     */
    @Override
    public String toString() {
        return super.toString() + String.format("%-20s%-15s%-17s%-12s", author, topic, datePublished.toString(), availability ? "available" : "unavailable");
    }

    /**
     * Implementation of the Printable interfaces abstract function print, used for printing
     * out the string representation of the object
     */
    @Override
    public void print() {
        System.out.printf("%-5s%-24s%-20s%-15s%-17s%-12s\n", "ID", "Title", "Author", "Topic", "Publish date", "Status");
        System.out.println(this);
    }
}
