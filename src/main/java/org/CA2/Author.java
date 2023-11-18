package org.CA2;

import java.util.List;

public class Author {

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

    public List<Asset> getAuthoredBooks() {
        return authoredAsset;
    }

    public void setAuthoredBooks(List<Asset> authoredAsset) {
        this.authoredAsset = authoredAsset;
    }
}
