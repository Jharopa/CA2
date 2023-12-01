package org.CA2;

import java.util.LinkedList;

public class Author implements Comparable<Author>, Printable {
    private int id;
    private String name;
    private LinkedList<Asset> authoredAsset;

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
        this.authoredAsset = new LinkedList<>();
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void addAuthoredAsset(Asset asset){
        authoredAsset.add(asset);
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Asset> getAuthoredAssets() {
        return authoredAsset;
    }

    public void setAuthoredAssets(LinkedList<Asset> authoredAsset) {
        this.authoredAsset = authoredAsset;
    }

    @Override
    public int compareTo(Author o) {
        return Integer.compare(this.getID(), o.getID());
    }

    @Override
    public String toString() {
        return String.format("%d, %s", id, name);
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
