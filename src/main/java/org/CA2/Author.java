package org.CA2;

import java.util.List;

public class Author implements Comparable<Author>, Printable {

    private String name;
    private List<Asset> authoredAsset;

    public Author(String name, List<Asset> authoredAsset) {
        this.name = name;
        this.authoredAsset = authoredAsset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Asset> getAuthoredAssets() {
        return authoredAsset;
    }

    public void setAuthoredAssets(List<Asset> authoredAsset) {
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
