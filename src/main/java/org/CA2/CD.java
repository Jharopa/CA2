package org.CA2;

public class CD extends Asset {
    private String producer;
    private String director;
    private int playtime;

    public CD(String title, String producer, String director, int playtime, boolean availability) throws AssetException {
        super(title, availability);

        this.producer = producer;
        this.director = director;
        this.playtime = playtime;
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
}
