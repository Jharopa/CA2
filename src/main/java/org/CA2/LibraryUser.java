package org.CA2;

import java.util.LinkedList;

public class LibraryUser implements Comparable<LibraryUser>, Printable {
    /**
     * Integer representing the library user's id
     */
    private final int ID;
    /**
     * String representing the library user's name
     */
    private String name;
    /**
     * String representing the library user's borrowed assets
     */
    private final LinkedList<Asset> borrowedAssets;

    /**
     * Class constructor specifying the library user object's id, and name
     * @param ID Initializes the library user object's id
     * @param name Initializes the library user object's name
     */
    public LibraryUser(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.borrowedAssets = new LinkedList<>();
    }

    /**
     * Accessor function for the id attribute
     * @return The object's id attribute
     */
    public int getID() {
        return ID;
    }

    /**
     * Accessor function for the name attribute
     * @return The object's name attribute
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator function for the name attribute
     * @param name The new value of the library user object's name attribute
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor function for the borrowedAssets attribute
     * @return The object's borrowedAssets attribute
     */
    public LinkedList<Asset> getBorrowedAssets() {
        return borrowedAssets;
    }

    /**
     * Implementation of the Comparable interface's abstract function compareTo
     * @param o the object to be compared.
     * @return -1, 0, or 1 depending on the result of the Integer comparison between object's id attribute
     */
    @Override
    public int compareTo(LibraryUser o) {
        return Integer.compare(this.getID(), o.getID());
    }

    /**
     * LibraryUser class' overridden toString function
     * @return A string representation of the library user object
     */
    @Override
    public String toString() {
        return String.format("%-5s%-24s", ID, name);
    }

    /**
     * Implementation of the Printable interfaces abstract function print, used for printing
     * out the string representation of the object
     */
    @Override
    public void print() {
        System.out.println(this.toString());
    }

    /**
     * Mutator function that adds an asset to the object's borrowedAssets LinkedList
     * @param asset The asset object to be added to borrowedAssets attribute
     */
    public void addBorrowedAsset(Asset asset) {
        borrowedAssets.add(asset);
    }

    /**
     * Mutator function that removes an asset to the object's borrowedAssets LinkedList
     * @param asset The asset object to be added to borrowedAssets attribute
     */
    public void removeBorrowedAsset(Asset asset) {
        borrowedAssets.remove(asset);
    }
}
