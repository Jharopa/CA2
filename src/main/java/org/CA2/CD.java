package org.CA2;

public class CD extends Asset {
    private String title;
    private String producer;
    private String director;
    private int playtime;
    private boolean availability;

    public CD(String title, String producer, String director, int playtime, boolean availability) {
        this.title = title;
        this.producer = producer;
        this.director = director;
        this.playtime = playtime;
        this.availability = availability;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
