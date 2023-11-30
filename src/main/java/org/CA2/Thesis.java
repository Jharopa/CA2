package org.CA2;

import java.time.LocalDate;

public class Thesis extends Asset implements Printable {
    private String author;
    private String topic;
    private String Abstract;
    private LocalDate datePublished;

    public Thesis(String title, String author, String topic, String Abstract, LocalDate datePublished, boolean availability) throws AssetException {
        super(title, availability);

        if (datePublished.isAfter(LocalDate.now())) {
            throw new AssetException("Theses datePublished can not be in the future");
        }

        this.author = author;
        this.topic = topic;
        this.Abstract = Abstract;
        this.datePublished = datePublished;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAbstract() {
        return Abstract;
    }

    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %s %s %s", author, topic, datePublished.toString());
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
