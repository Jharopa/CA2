package org.CA2;

import java.io.FileReader;
import java.util.Date;
import java.util.LinkedList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

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
            System.out.printf("Unable to add book. Reason: %s", e);
        }
    }

    public void addAudioBook(String title, String author, String ISBN, int duration) {
        try {
            audioBooks.add(new AudioBook(title, author, ISBN, duration, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add audio book. Reason: %s", e);
        }
    }

    public void addCD(String title, String producer, String director, int playtime) {
        try {
            cds.add(new CD(title, producer, director, playtime, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add CD. Reason: %s", e);
        }
    }

    // Create the date outside of class and pass it in or pass in formatted string and create it here?
    public void addTheses(String title, String author, String topic, String Abstract, Date datePublished, boolean availability) {
        try {
            theses.add(new Thesis(title, author, topic, Abstract, datePublished, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add theses. Reason: %s", e);
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
        loadBooks();
        loadAudioBooks();
        loadCDs();
        loadThesis();
    }

    private void loadBooks() {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("title", "author", "ISBN", "availability")
                .setSkipHeaderRecord(true)
                .build();

        try (FileReader fileReader = new FileReader("books.csv");
             CSVParser csvParser = csvFormat
                     .parse(fileReader)) {

            for (CSVRecord csvRecord : csvParser) {
                String title = csvRecord.get("title");
                String author = csvRecord.get("author");
                String ISBN = csvRecord.get("ISBN");
                boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));
                books.push(new Book(title, author, ISBN, availability));
            }
        } catch (IOException e) {
            System.out.printf("Failed to load book. %s", e);
        } catch (AssetException e) {
            System.out.printf("Unable to add loaded book. %s", e);
        }
    }

    private void loadAudioBooks() {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("title", "author", "ISBN", "duration", "availability")
                .setSkipHeaderRecord(true)
                .build();

        try (FileReader fileReader = new FileReader("audiobooks.csv");
             CSVParser csvParser = csvFormat
                     .parse(fileReader)) {

            for (CSVRecord csvRecord : csvParser) {
                String title = csvRecord.get("title");
                String author = csvRecord.get("author");
                String ISBN = csvRecord.get("ISBN");
                int duration = Integer.parseInt(csvRecord.get("duration"));
                boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));
                audioBooks.push(new AudioBook(title, author, ISBN, duration, availability));
            }
        } catch (IOException e) {
            System.out.printf("Failed to load audiobook. %s", e);
        } catch (AssetException e) {
            System.out.printf("Unable to add loaded audiobook. %s", e);
        }
    }

    private void loadCDs() {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("title", "producer", "director", "playtime", "availability")
                .setSkipHeaderRecord(true)
                .build();

        try (FileReader fileReader = new FileReader("cds.csv");
             CSVParser csvParser = csvFormat
                     .parse(fileReader)) {

            for (CSVRecord csvRecord : csvParser) {
                String title = csvRecord.get("title");
                String producer = csvRecord.get("producer");
                String director = csvRecord.get("director");
                int playtime = Integer.parseInt(csvRecord.get("playtime"));
                boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));
                cds.push(new CD(title, producer, director, playtime, availability));
            }
        } catch (IOException e) {
            System.out.printf("Failed to load CD. %s", e);
        } catch (AssetException e) {
            System.out.printf("Unable to add loaded CD. %s", e);
        }
    }

    private void loadThesis() {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("title", "author", "topic", "Abstract", "datePublished", "availability")
                .setSkipHeaderRecord(true)
                .build();

        try (FileReader fileReader = new FileReader("cds.csv");
             CSVParser csvParser = csvFormat
                     .parse(fileReader)) {

            for (CSVRecord csvRecord : csvParser) {
                String title = csvRecord.get("title");
                String author = csvRecord.get("author");
                String topic = csvRecord.get("topic");
                String Abstract = csvRecord.get("Abstract");
                Date datePublished = new Date(Date.parse(csvRecord.get("datePublished")));
                boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));
                theses.push(new Thesis(title, author, topic, Abstract,datePublished, availability));
            }
        } catch (IOException e) {
            System.out.printf("Failed to load thesis %s", e);
        } catch (AssetException e) {
            System.out.printf("Unable to add loaded thesis. %s", e);
        }
    }

    public void save() {
        saveBooks();
        saveAudioBooks();
        saveCDs();
        saveTheses();
    }

    private void saveBooks() {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("title", "author", "ISBN", "availability")
                .build();

        try (FileWriter fileWriter = new FileWriter("books.csv");
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
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("title", "author", "ISBN", "duration", "availability")
                .build();

        try (FileWriter fileWriter = new FileWriter("audiobooks.csv");
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

    private void saveCDs() {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("title", "producer", "director", "playtime", "availability")
                .build();

        try (FileWriter fileWriter = new FileWriter("cds.csv");
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
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("title", "author", "topic", "abstract", "datePublished", "availability")
                .build();

        try (FileWriter fileWriter = new FileWriter("theses.csv");
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat)) {
            for (Thesis thesis : theses) {
                csvPrinter.printRecord(
                        thesis.getTitle(),
                        thesis.getAuthor(),
                        thesis.getTopic(),
                        thesis.getAbstract(),
                        thesis.getDatePublished(),
                        thesis.isAvailability()
                );
            }
        } catch (IOException e) {
            System.out.printf("Failed to save Theses. %s", e);
        }
    }
}
