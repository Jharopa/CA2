package org.CA2;

import java.util.Date;
import java.util.LinkedList;

public class LibrarySystem {
    private LinkedList<Asset> assets;
    private LinkedList<Author> authors;
    private LinkedList<LibraryUser> users;
    private LinkedList<Loan> loans;

    LibrarySystem() {
        this.assets = new LinkedList<>();
        this.authors = new LinkedList<>();
        this.users = new LinkedList<>();
        this.loans = new LinkedList<>();
    }

    public void addBook(String title, Author author, String ISBN) {
        try {
            assets.add(new Book(title, author, ISBN, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add book. Reason: %s", e.toString());
        }
    }

    public void addCD(String title, String producer, String director, int playtime) {
        try {
            assets.add(new CD(title, producer, director, playtime, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add CD. Reason: %s", e.toString());
        }
    }

    public void addAudioBook(String title, Author author, String ISBN, int duration) {
        try {
            assets.add(new AudioBook(title, author, ISBN, duration, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add audio book. Reason: %s", e.toString());
        }
    }

    // Create the date outside of class and pass it in or pass in formatted string and create it here?
    public void addTheses(String title, Author author, String topic, String Abstract, Date datePublished, boolean availability) {
        try {
            assets.add(new Theses(title, author, topic, Abstract, datePublished, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add theses. Reason: %s", e.toString());
        }
    }

    public void addAuthor(String name, LinkedList<Asset> authoredBooks) {
        authors.add(new Author(name, authoredBooks));
    }

    // Move the search functions out to their own class?
    // Find a way to make search algorithm generic to reduce code reuse?
    public Author getAuthor(String name) {
        return authorSearch(name);
    }

    private Author authorSearch(String name) {
        return null;
    }

    public void addUser() {

    }

    public LibraryUser getUser(int ID) {
        return userSearch(ID);
    }

    private LibraryUser userSearch(int ID) {
        return null;
    }

    public Asset getAsset(String title) {
        return assetSearch(title);
    }

    private Asset assetSearch(String title) {
        return null;
    }

    public void createLoan(int ID, String title) {
        // find library user by ID
        // find asset by title
        // Create loan class and append to list
    }

    public void returnLoan(int ID) {
        // find library user by ID
        // find books?
        // Search for loans with library user and check books?
    }

    public void listAvailableAssets() {
        // Iterate over assets
        // Print out available assets
    }

    public void listBorrowedBooks(LibraryUser user) {
        // Find the user in list
        // Print out its borrowed books
    }

    public void listAuthorsBooks(Author author) {
        // Find the author in list
        // Print out its books
    }

    private void load() {
        // Loads data from CSV
    }

    public void save() {
        // Save data to CSV
    }
}
