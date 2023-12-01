package org.CA2;

public class CD extends Asset implements Printable{
    private String producer;
    private String director;
    private int playtime;

    public CD(int id, String title, String producer, String director, int playtime, boolean availability) throws AssetException {
        super(id, title, availability);

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

    @Override
    public String toString() {
        return super.toString() + String.format("%-20s%-20s%-12s%-12s", producer, director, playtime, availability ? "available" : "unavailable");
    }

    @Override
    public void print() {
        System.out.printf("%-5s%-24s%-20s%-20s%-12s%-12s\n", "ID", "Title", "Producer", "Performer", "Playtime", "Status");
        System.out.println(this);
    }
}
