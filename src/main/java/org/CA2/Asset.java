package org.CA2;

public abstract class Asset {

    private String title;
    private boolean availability;

    public Asset(String title, boolean availability) {
        this.title = title;
        this.availability = availability;
    }

    public Asset() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
