package org.CA2;

import java.util.List;

public class LibraryUser {

    private String name;
    private int ID;
    private List<Book> borrowedAssets;

    public LibraryUser(String name, int ID, List<Book> borrowedAssets) {
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

    public List<Book> getBorrowedAssets() {
        return borrowedAssets;
    }

    public void setBorrowedAssets(List<Book> borrowedAssets) {
        this.borrowedAssets = borrowedAssets;
    }
}
