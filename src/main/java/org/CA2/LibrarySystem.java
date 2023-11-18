package org.CA2;

import java.util.Date;
import java.util.LinkedList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;

public class LibrarySystem {
    private LinkedList<Book> books;
    private LinkedList<AudioBook> audioBooks;
    private LinkedList<CD> cds;
    private LinkedList<Thesis> theses;
    private LinkedList<Author> authors;
    private LinkedList<LibraryUser> users;
    private LinkedList<Loan> loans;

    LibrarySystem() {
        this.books = new LinkedList<>();
        this.audioBooks = new LinkedList<>();
        this.cds = new LinkedList<>();
        this.theses = new LinkedList<>();
        this.authors = new LinkedList<>();
        this.users = new LinkedList<>();
        this.loans = new LinkedList<>();
    }

    public void addBook(String title, String author, String ISBN) {
        try {
            books.add(new Book(title, author, ISBN, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add book. Reason: %s", e.toString());
        }
    }

    public void addAudioBook(String title, String author, String ISBN, int duration) {
        try {
            audioBooks.add(new AudioBook(title, author, ISBN, duration, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add audio book. Reason: %s", e.toString());
        }
    }

    public void addCD(String title, String producer, String director, int playtime) {
        try {
            cds.add(new CD(title, producer, director, playtime, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add CD. Reason: %s", e.toString());
        }
    }

    // Create the date outside of class and pass it in or pass in formatted string and create it here?
    public void addTheses(String title, String author, String topic, String Abstract, Date datePublished, boolean availability) {
        try {
            theses.add(new Thesis(title, author, topic, Abstract, datePublished, true));
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

    public void load() {
        // Loads data from CSV
    }

    public void save() {
        // Save data to CSV
        saveBooks();
        saveAudioBooks();
        saveCD();
        saveTheses();
    }

    private void saveBooks() {
        String csvFilePath = "books.csv";
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("title", "author", "ISBN", "availability")
                .build();

        try (FileWriter fileWriter = new FileWriter(csvFilePath);
            CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat)) {
            for (Book book : books) {
                csvPrinter.printRecord(
                        book.getTitle(),
                        book.getAuthor(),
                        book.getISBN(),
                        book.isAvailability()
                );
            }
        } catch (IOException e) {
            System.out.printf("Failed to save book. %s", e);
        }
    }

    private void saveAudioBooks() {
        String csvFilePath = "audiobooks.csv";
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("title", "author", "ISBN", "duration", "availability")
                .build();

        try (FileWriter fileWriter = new FileWriter(csvFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat)) {
            for (AudioBook audioBook : audioBooks) {
                csvPrinter.printRecord(
                        audioBook.getTitle(),
                        audioBook.getAuthor(),
                        audioBook.getISBN(),
                        audioBook.getDuration(),
                        audioBook.isAvailability()
                );
            }
        } catch (IOException e) {
            System.out.printf("Failed to save audiobook. %s", e);
        }
    }

    private void saveCD() {
        String csvFilePath = "cds.csv";
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("title", "producer", "director", "playtime", "availability")
                .build();

        try (FileWriter fileWriter = new FileWriter(csvFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat)) {
            for (CD cd : cds) {
                csvPrinter.printRecord(
                        cd.getTitle(),
                        cd.getProducer(),
                        cd.getDirector(),
                        cd.getPlaytime(),
                        cd.isAvailability()
                );
            }
        } catch (IOException e) {
            System.out.printf("Failed to save CD. %s", e);
        }
    }

    private void saveTheses() {
        String csvFilePath = "theses.csv";
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("title", "author", "topic", "abstract", "availability")
                .build();

        try (FileWriter fileWriter = new FileWriter(csvFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat)) {
            for (Thesis thesis : theses) {
                csvPrinter.printRecord(
                        thesis.getTitle(),
                        thesis.getAuthor(),
                        thesis.getTopic(),
                        thesis.getAbstract(),
                        thesis.isAvailability()
                );
            }
        } catch (IOException e) {
            System.out.printf("Failed to save Theses. %s", e);
        }
    }
}
