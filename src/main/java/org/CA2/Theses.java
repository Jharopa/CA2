package org.CA2;

import java.util.Date;

public class Theses extends Asset {
    private Author author;
    private String topic;
    private String Abstract;
    private Date datePublished;

    public Theses(String title, Author author, String topic, String Abstract, Date datePublished, boolean availability) throws AssetException {
        super(title, availability);

        if (datePublished.after(new Date())) {
            throw new AssetException("Theses datePublished can not be in the future");
        }

        this.author = author;
        this.topic = topic;
        this.Abstract = Abstract;
        this.datePublished = datePublished;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
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
}
