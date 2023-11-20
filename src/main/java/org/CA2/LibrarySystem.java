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
import java.util.Objects;

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

    public void addBook(String title, String author, String ISBN) {
        try {
            assets.add(new Book(title, author, ISBN, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add book. Reason: %s", e);
        }
    }

    public void addAudioBook(String title, String author, String ISBN, int duration) {
        try {
            assets.add(new AudioBook(title, author, ISBN, duration, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add audio book. Reason: %s", e);
        }
    }

    public void addCD(String title, String producer, String director, int playtime) {
        try {
            assets.add(new CD(title, producer, director, playtime, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add CD. Reason: %s", e);
        }
    }

    // Create the date outside of class and pass it in or pass in formatted string and create it here?
    public void addTheses(String title, String author, String topic, String Abstract, Date datePublished, boolean availability) {
        try {
            assets.add(new Thesis(title, author, topic, Abstract, datePublished, true));
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
        loadItems(new String[] {"title", "author", "ISBN", "availability"}, "books.csv");
        loadItems(new String[] {"title", "author", "ISBN", "duration", "availability"}, "audiobooks.csv");
        loadItems(new String[] {"title", "producer", "director", "playtime", "availability"}, "cds.csv");
        loadItems(new String[] {"title", "author", "topic", "Abstract", "datePublished", "availability"}, "theses.csv");
        loadItems(new String[] {"id", "name", "borrowed"}, "users.csv");
        loadItems(new String[] {"name", "authored"}, "authors.csv");
    }

    private void loadItems(String[] headers, String filePath) {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(headers)
                .setSkipHeaderRecord(true)
                .build();

        try (FileReader fileReader = new FileReader(filePath);
             CSVParser csvParser = csvFormat
                     .parse(fileReader)) {

            for (CSVRecord csvRecord : csvParser) {
                switch (filePath) {
                    case "books.csv" -> {
                        String title = csvRecord.get("title");
                        String author = csvRecord.get("author");
                        String ISBN = csvRecord.get("ISBN");
                        boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));

                        assets.add(new Book(title, author, ISBN, availability));
                    }
                    case "audiobooks.csv" -> {
                        String title = csvRecord.get("title");
                        String author = csvRecord.get("author");
                        String ISBN = csvRecord.get("ISBN");
                        int duration = Integer.parseInt(csvRecord.get("duration"));
                        boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));

                        assets.add(new AudioBook(title, author, ISBN, duration, availability));
                    }
                    case "cds.csv" -> {
                        String title = csvRecord.get("title");
                        String producer = csvRecord.get("producer");
                        String director = csvRecord.get("director");
                        int playtime = Integer.parseInt(csvRecord.get("playtime"));
                        boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));

                        assets.add(new CD(title, producer, director, playtime, availability));
                    }
                    case "thesis.csv" -> {
                        String title = csvRecord.get("title");
                        String author = csvRecord.get("author");
                        String topic = csvRecord.get("topic");
                        String Abstract = csvRecord.get("Abstract");
                        Date datePublished = new Date(Date.parse(csvRecord.get("datePublished")));
                        boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));

                        assets.add(new Thesis(title, author, topic, Abstract, datePublished, availability));
                    }

                    case "users.csv" -> {
                        int id = Integer.parseInt(csvRecord.get("id"));
                        String name = csvRecord.get("name");
                        String borrowed = csvRecord.get("borrowed");

                        String[] borrowedAssetsTitle = borrowed.split(" ");
                        LinkedList<Asset> assetsList = new LinkedList<>();
                        Asset[] arr = this.assets.toArray(new Asset[assets.size()]);
                        HeapSort.sort(arr);

                        for (String string: borrowedAssetsTitle) {
                            assetsList.add(BinarySearch.assetSearch(arr, string));
                        }

                        users.add(new LibraryUser(name, id, assetsList));
                    }

                    case "authors.csv" -> {
                        String name = csvRecord.get("name");
                        String authored = csvRecord.get("authored");

                        String[] authoredAssets = authored.split(" ");
                        LinkedList<Asset> assetsList = new LinkedList<>();
                        Asset[] arr = this.assets.toArray(new Asset[assets.size()]);
                        HeapSort.sort(arr);

                        for (String string: authoredAssets) {
                            assetsList.add(BinarySearch.assetSearch(arr, string));
                        }

                        authors.add(new Author(name, assetsList));
                    }
                }
            }
        } catch (IOException e) {
            System.out.printf("Failed to load asset. %s", e);
        } catch (AssetException e) {
            System.out.printf("Unable to add loaded asset. %s", e);
        }
    }

    public void save() {
        saveItems(new String[] {"title", "author", "ISBN", "availability"}, "books.csv");
        saveItems(new String[] {"title", "author", "ISBN", "duration", "availability"}, "audiobooks.csv");
        saveItems(new String[] {"title", "producer", "director", "playtime", "availability"}, "cds.csv");
        saveItems(new String[] {"title", "author", "topic", "Abstract", "datePublished", "availability"}, "theses.csv");
        saveItems(new String[] {"id", "name", "borrowed"}, "users.csv");
        saveItems(new String[] {"name", "authored"}, "authors.csv");
    }

    private void saveItems(String[] headers, String filePath) {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(headers)
                .build();

        try (FileWriter fileWriter = new FileWriter(filePath);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat)) {
            switch (filePath) {
                case "books.csv" -> {
                    for (Asset asset : assets) {
                        if (asset instanceof Book book) {
                            csvPrinter.printRecord(
                                    book.getTitle(),
                                    book.getAuthor(),
                                    book.getISBN(),
                                    book.isAvailability()
                            );
                        }
                    }
                }
                case "audiobooks.csv" -> {
                    for (Asset asset : assets) {
                        if (asset instanceof AudioBook audioBook) {
                            csvPrinter.printRecord(
                                    audioBook.getTitle(),
                                    audioBook.getAuthor(),
                                    audioBook.getISBN(),
                                    audioBook.getDuration(),
                                    audioBook.isAvailability()
                            );
                        }
                    }
                }
                case "cds.csv" -> {
                    for (Asset asset : assets) {
                        if (asset instanceof CD cd) {
                            csvPrinter.printRecord(
                                    cd.getTitle(),
                                    cd.getProducer(),
                                    cd.getDirector(),
                                    cd.getPlaytime(),
                                    cd.isAvailability()
                            );
                        }
                    }
                }
                case "thesis.csv" -> {
                    for (Asset asset : assets) {
                        if (asset instanceof Thesis thesis) {
                            csvPrinter.printRecord(
                                    thesis.getTitle(),
                                    thesis.getAuthor(),
                                    thesis.getTopic(),
                                    thesis.getAbstract(),
                                    thesis.getDatePublished(),
                                    thesis.isAvailability()
                            );
                        }
                    }
                }
                case "users.csv" -> {
                    for (LibraryUser user : users) {
                        StringBuilder borrowed = new StringBuilder();
                        for (Asset asset: user.getBorrowedAssets()) {
                            borrowed.append(asset.getTitle()).append(" ");
                        }

                        csvPrinter.printRecord(
                                user.getID(),
                                user.getName(),
                                borrowed
                        );
                    }
                }
                case "authors.csv" -> {
                    for (Author author : authors) {
                        StringBuilder authoredAssets = new StringBuilder();
                        for (Asset asset: author.getAuthoredAssets()) {
                            authoredAssets.append(asset.getTitle()).append(" ");
                        }

                        csvPrinter.printRecord(
                                author.getName(),
                                authoredAssets
                        );
                    }
                }
            }
        } catch (IOException e) {
            System.out.printf("Failed to save asset. %s", e);
        }
    }
}
