package org.CA2.models;

import org.CA2.interfaces.Printable;

import java.util.LinkedList;

public class Author implements Comparable<Author>, Printable {
    /**
     * Integer representing the author's id
     */
    private final int id;
    /**
     * String representing the author's name
     */
    private String name;
    /**
     * LinkedList of Assets representing the author's authored assets
     */
    private LinkedList<Asset> authoredAsset;

    /**
     * Class constructor specifying the author object's id, and name
     * @param id Initializes the author object's id
     * @param name Initializes the author object's name
     */
    public Author(int id, String name) {
        this.id = id;
        this.name = name;
        this.authoredAsset = new LinkedList<>();
    }

    /**
     * Accessor function for the id attribute
     * @return The object's id attribute
     */
    public int getID() {
        return id;
    }

    /**
     * Accessor function for the name attribute
     * @return The object's name attribute
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator function that adds an asset to the object's authoredAsset LinkedList
     * @param asset The asset object to be added to authoredAsset attribute
     */
    public void addAuthoredAsset(Asset asset){
        authoredAsset.add(asset);
    }

    /**
     * Mutator function for the name attribute
     * @param name The new value of the author object's name attribute
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor function for the authoredAsset attribute
     * @return The object's authoredAsset attribute
     */
    public LinkedList<Asset> getAuthoredAssets() {
        return authoredAsset;
    }

    /**
     * Mutator function for the authoredAsset attribute
     * @param authoredAsset The new value for the author object's authoredAsset attribute
     */
    public void setAuthoredAssets(LinkedList<Asset> authoredAsset) {
        this.authoredAsset = authoredAsset;
    }

    /**
     * Implementation of the Comparable interface's abstract function compareTo
     * @param o the object to be compared.
     * @return -1, 0, or 1 depending on the result of the Integer comparison between object's id attribute
     */
    @Override
    public int compareTo(Author o) {
        return Integer.compare(this.getID(), o.getID());
    }

    /**
     * Author classes overridden toString function
     * @return A string representation of the author object
     */
    @Override
    public String toString() {
        return String.format("%-5s%-24s", id, name);
    }

    /**
     * Implementation of the Printable interfaces abstract function print, used for printing
     * out the string representation of the object
     */
    @Override
    public void print() {
        System.out.println(this);
    }
}
