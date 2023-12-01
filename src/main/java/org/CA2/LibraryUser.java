package org.CA2;

import java.util.LinkedList;

public class LibraryUser implements Comparable<LibraryUser>, Printable{
    private int ID;
    private String name;
    private LinkedList<Asset> borrowedAssets;

    public LibraryUser(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.borrowedAssets = new LinkedList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Asset> getBorrowedAssets() {
        return borrowedAssets;
    }

    public void setBorrowedAssets(LinkedList<Asset> borrowedAssets) {
        this.borrowedAssets = borrowedAssets;
    }

    @Override
    public int compareTo(LibraryUser o) {
        return Integer.compare(this.getID(), o.getID());
    }

    @Override
    public String toString() {
        return String.format("%-5s%-24s", ID, name);
    }

    @Override
    public void print() {
        System.out.println(this.toString());
    }

    public void addBorrowedAsset(Asset asset) {
        borrowedAssets.add(asset);
    }

    public void removeBorrowedAsset(Asset asset) {
        borrowedAssets.remove(asset);
    }
}
