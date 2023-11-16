package org.CA2;

import java.util.Date;

public class Theses extends Asset {

    private String title;
    private String author;
    private String topic;
    private String Abstract;
    private Date datePublished;
    private boolean availability;

    public Theses(String title, String author, String topic, String anAbstract, Date datePublished, boolean availability) {
        this.title = title;
        this.author = author;
        this.topic = topic;
        Abstract = anAbstract;
        this.datePublished = datePublished;
        this.availability = availability;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
