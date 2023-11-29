package org.CA2;

import java.util.List;

public class LibraryUser implements Comparable<LibraryUser>, Printable{
    private String name;
    private int ID;
    private List<Asset> borrowedAssets;

    public LibraryUser(String name, int ID, List<Asset> borrowedAssets) {
        this.name = name;
        this.ID = ID;
        this.borrowedAssets = borrowedAssets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<Asset> getBorrowedAssets() {
        return borrowedAssets;
    }

    public void setBorrowedAssets(List<Asset> borrowedAssets) {
        this.borrowedAssets = borrowedAssets;
    }

    @Override
    public int compareTo(LibraryUser o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return String.format("%d %s", ID, name);
    }

    @Override
    public void print() {
        System.out.println(this.toString());
    }

    public void addBorrowedAsset(Asset asset) {
    }

    public boolean contains(Asset asset) {
        return true;
    }

    public void remove(Asset asset) {
    }
}
