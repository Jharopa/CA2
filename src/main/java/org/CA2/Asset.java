package org.CA2;

public abstract class Asset implements Comparable<Asset>, Printable {
    protected int id;
    protected String title;
    protected boolean availability;

    public Asset(int id, String title, boolean availability) throws AssetException {
        if (title.length() > 200) {
            throw new AssetException("Asset title can not be greater than 200");
        }

        this.id = id;
        this.title = title;
        this.availability = availability;
    }

    public int getID() {
        return id;
    }

    public void getID(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws AssetException {
        if (title.length() > 200) {
            throw new AssetException("Asset title can not be greater than 200");
        }

        this.title = title;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    // Strings are compared lexicographic order https://www.educative.io/answers/what-is-a-lexicographic-order
    public int compareTo(Asset o) {
        return Integer.compare(this.getID(), o.getID());
    }

    @Override
    public String toString() {
        return String.format("%-5s%-24s", getID() ,getTitle());
    }
}
