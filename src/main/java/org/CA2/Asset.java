package org.CA2;

public abstract class Asset {

    protected String title;
    protected boolean availability;

    public Asset(String title, boolean availability) throws AssetException {
        if (title.length() > 200) {
            throw new AssetException("Asset title can not be greater than 200");
        }

        this.title = title;
        this.availability = availability;
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
}
