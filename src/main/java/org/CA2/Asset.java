package org.CA2;

public abstract class Asset implements Comparable<Asset>, Printable {
    /**
     *  Integer representing the asset's ID
     */
    protected int id;
    /**
     *  String representing the asset's name/title
     */
    protected String title;
    /**
     *  Boolean representing the availability status of the asset.
     */
    protected boolean availability;

    /**
     * Class constructor specifying the asset object's ID, title and availability
     * @param id Initializes the asset object's id
     * @param title Initializes the asset object's title
     * @param availability Initializes the asset object's availability
     * @throws AssetException When the title specified is greater than 200
     */
    public Asset(int id, String title, boolean availability) throws AssetException {
        if (title.length() > 200) {
            throw new AssetException("Asset title can not be greater than 200");
        }

        this.id = id;
        this.title = title;
        this.availability = availability;
    }

    /**
     * Accessor function for the id attribute
     * @return The object's id attribute
     */
    public int getID() {
        return id;
    }

    /**
     * Accessor function for the title attribute
     * @return The object's title attribute
     */
    public String getTitle() {
        return title;
    }

    /**
     * Mutator function for the title attribute
     * @param title The new value of the asset object's title attribute
     * @throws AssetException When the title specified is greater than 200
     */
    public void setTitle(String title) throws AssetException {
        if (title.length() > 200) {
            throw new AssetException("Asset title can not be greater than 200");
        }

        this.title = title;
    }

    /**
     * Accessor function for the availability attribute
     * @return The object's availability attribute
     */
    public boolean isAvailability() {
        return availability;
    }

    /**
     * Mutator function for the title attribute
     * @param availability The new value of the asset object's availability attribute
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    /**
     * Implementation of the Comparable interface's abstract function compareTo
     * @param o the object to be compared.
     * @return -1, 0, or 1 depending on the result of the Integer comparison between object's id attribute
     */
    public int compareTo(Asset o) {
        return Integer.compare(this.getID(), o.getID());
    }

    /**
     * Asset classes overridden toString function
     * @return A string representation of the asset object
     */
    @Override
    public String toString() {
        return String.format("%-5s%-24s", getID() ,getTitle());
    }
}
