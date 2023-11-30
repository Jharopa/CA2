package org.CA2;

import java.util.LinkedList;
import java.util.List;

public class Author implements Comparable<Author>, Printable {

    private String name;
    private LinkedList<Asset> authoredAsset;

    public Author(String name) {
        this.name = name;
        this.authoredAsset = new LinkedList<>();
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

    public List<Asset> getAuthoredAssets() {
        return authoredAsset;
    }

    public void setAuthoredAssets(LinkedList<Asset> authoredAsset) {
        this.authoredAsset = authoredAsset;
    }

    @Override
    public int compareTo(Author o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
