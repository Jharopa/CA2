package org.CA2;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import javax.swing.text.DateFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class LibrarySystem {
    private static LinkedList<Asset> assets;
    private static LinkedList<Author> authors;
    private static LinkedList<LibraryUser> users;
    private static LinkedList<Loan> loans;

    private AtomicInteger loanIDCount;

    // Paths to the CSV files that should be read from or written to
    // Books, Audiobooks, CDs, Theses, Users, Authors
    private final String[] CSVPaths;

    public LibrarySystem(String[] CSVPaths) {
        assets = new LinkedList<>();
        authors = new LinkedList<>();
        users = new LinkedList<>();
        loans = new LinkedList<>();

        this.CSVPaths = CSVPaths;
    }

    public static void addBook(String title, String author, String ISBN) {
        Author thisAuthor = getAuthor(author);

        if (thisAuthor == null) {
            System.out.println("No author found, please create this author first.");
            return;
        }

        try {
            Book book = new Book(title, author, ISBN, true);
            thisAuthor.AddAssetToAuthor(book);
            assets.add(book);
        } catch (AssetException e) {
            System.out.printf("Unable to add book. Reason: %s", e);
        }
    }


    public static void addAudioBook(String title, String author, String ISBN, int duration) {
        Author thisAuthor = getAuthor(author);

        if (thisAuthor == null) {
            System.out.println("No author found, please create this author first.");
            return;
        }

        try {
            AudioBook audioBook = new AudioBook(title, author, ISBN, duration, true);
            thisAuthor.AddAssetToAuthor(audioBook);
            assets.add(audioBook);
        } catch (AssetException e) {
            System.out.printf("Unable to add audio book. Reason: %s", e);
        }
    }

    public static void addCD(String title, String producer, String director, int playtime) {
        try {
            assets.add(new CD(title, producer, director, playtime, true));
        } catch (AssetException e) {
            System.out.printf("Unable to add CD. Reason: %s", e);
        }
    }

    // Create the date outside of class and pass it in or pass in formatted string and create it here?
    public static void addThesis(String title, String author, String topic, String Abstract, LocalDate datePublished) {
        Author thisAuthor = getAuthor(author);

        if (thisAuthor == null) {
            System.out.println("No author found, please create this author first.");
            return;
        }

        try {
            Thesis thesis = new Thesis(title, author, topic, Abstract, datePublished, true);
            thisAuthor.AddAssetToAuthor(thesis);
            assets.add(thesis);
        } catch (AssetException e) {
            System.out.printf("Unable to add theses. Reason: %s", e);
        }
    }

    public static void addAuthor(String name) {
        authors.add(new Author(name));
    }

    // Move the search functions out to their own class?
    // Find a way to make search algorithm generic to reduce code reuse?

    public static Author getAuthor(String name) {
        Author[] authorArr = new Author[authors.size()];
        authors.toArray(authorArr);
        HeapSort.sort(authorArr);

        return BinarySearch.authorSearch(authorArr, name);
    }

    public static void addUser(int ID, String name, LinkedList<Asset> borrowedAssets) {
        users.add(new LibraryUser(name, ID, borrowedAssets));
    }


    public static LibraryUser getUser(String name) {
        LibraryUser[] userArr = new LibraryUser[users.size()];
        users.toArray(userArr);
        HeapSort.sort(userArr);

        return BinarySearch.userSearch(userArr, name);
    }

    public Asset getAsset(String title) {
        Asset[] assetsArr = new Asset[assets.size()];
        assets.toArray(assetsArr);
        HeapSort.sort(assetsArr);

        return BinarySearch.assetSearch(assetsArr, title);
    }

    public Loan getLoan(int id) {
        Loan[] loanArr = new Loan[loans.size()];
        loans.toArray(loanArr);
        HeapSort.sort(loanArr);

        return BinarySearch.loanSearch(loanArr, id);
    }

    public void createLoan(String assetTitle, String userName) {
        Asset asset = getAsset(assetTitle);
        LibraryUser user = getUser(userName);

        if (asset == null) {
            System.out.printf("Unable to find provided asset %s\nUnable to create loan", assetTitle);
            return;
        }

        if (user == null) {
            System.out.printf("Unable to find provided user %s\nUnable to create loan", assetTitle);
            return;
        }

        if (asset.isAvailability()) {
            asset.setAvailability(false);
            user.addBorrowedAsset(asset);

            loans.add(new Loan(loanIDCount.incrementAndGet() ,user, asset, LocalDate.now(), LocalDate.now().plusDays(14), false));

            System.out.println("Loan successfully created.");
        } else {
            System.out.println("Asset is not available for loan.");
        }
    }

    public void returnLoan(int id) {
        Loan loan = getLoan(id);

        if (loan != null) {
            loan.getBorrowedAsset().setAvailability(true);
            loan.getBorrower().removeBorrowedAsset(loan.getBorrowedAsset());
            loan.returnLoan();

            System.out.println("Loan successfully returned.");
        } else {
            System.out.printf("Unable to find loan with ID of %d.\n", id);
        }
    }

    public static void listAvailableAssets() {
        for (Asset asset : assets) {
            if (asset.isAvailability()) {
                asset.print();
            }
        }
    }

    public static void listAvailableBooks() {
        for (Asset asset : assets) {
            if (asset.isAvailability() && asset instanceof Book book && !(asset instanceof AudioBook)) {
                book.print();
            }
        }
    }

    public static void listAvailableAudioBooks() {
        for (Asset asset : assets) {
            if (asset.isAvailability() && asset instanceof AudioBook audioBook) {
                audioBook.print();
            }
        }
    }

    public static void listAvailableThesis() {
        for (Asset asset : assets) {
            if (asset.isAvailability() && asset instanceof Thesis thesis) {
                thesis.print();
            }
        }
    }

    public static void listAvailableCds() {
        for (Asset asset : assets) {
            if (asset.isAvailability() && asset instanceof CD cd) {
                cd.print();
            }
        }
    }

    public static void listBorrowedAssets(String userName) {
        LibraryUser libraryUser = getUser(userName);

        for (Asset asset : libraryUser.getBorrowedAssets()) {
            asset.print();
        }
    }

    public void listAuthorsAssets(String authorName) {
        Author author = getAuthor(authorName);

        for (Asset asset : author.getAuthoredAssets()) {
            asset.print();
        }
    }

    public void listLoans() {
        for (Loan loan : loans) {
            loan.print();
        }
    }

    public void listOverdueLoans() {
        for (Loan loan : loans) {
            if (loan.isOverdue()) {
                loan.print();
            }
        }
    }

    public void initializeIDCounters() {
        if (loans.isEmpty()) {
            loanIDCount = new AtomicInteger(0);
        } else {
            loanIDCount = new AtomicInteger(loans.get(loans.size() - 1).getID());
        }
    }

    public void load() {
        loadItems(new String[] {"title", "author", "ISBN", "availability"}, CSVPaths[0]);
        loadItems(new String[] {"title", "author", "ISBN", "duration", "availability"}, CSVPaths[1]);
        loadItems(new String[] {"title", "producer", "director", "playtime", "availability"}, CSVPaths[2]);
        loadItems(new String[] {"title", "author", "topic", "Abstract", "datePublished", "availability"}, CSVPaths[3]);
        loadItems(new String[] {"id", "name", "borrowed"}, CSVPaths[4]);
        loadItems(new String[] {"name", "authored"}, CSVPaths[5]);
        loadItems(new String[] {"id", "user", "borrowed", "borrowDate", "returnDate", "returned"}, CSVPaths[6]);
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
                    if (filePath.equals(CSVPaths[0])) {
                        String title = csvRecord.get("title");
                        String author = csvRecord.get("author");
                        String ISBN = csvRecord.get("ISBN");
                        boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));

                        assets.add(new Book(title, author, ISBN, availability));
                    }
                    else if (filePath.equals(CSVPaths[1])) {
                        String title = csvRecord.get("title");
                        String author = csvRecord.get("author");
                        String ISBN = csvRecord.get("ISBN");
                        int duration = Integer.parseInt(csvRecord.get("duration"));
                        boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));

                        assets.add(new AudioBook(title, author, ISBN, duration, availability));
                    }
                    else if (filePath.equals(CSVPaths[2])) {
                        String title = csvRecord.get("title");
                        String producer = csvRecord.get("producer");
                        String director = csvRecord.get("director");
                        int playtime = Integer.parseInt(csvRecord.get("playtime"));
                        boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));

                        assets.add(new CD(title, producer, director, playtime, availability));
                    }
                    else if (filePath.equals(CSVPaths[3])) {
                        String title = csvRecord.get("title");
                        String author = csvRecord.get("author");
                        String topic = csvRecord.get("topic");
                        String Abstract = csvRecord.get("Abstract");

                        LocalDate datePublished = LocalDate.parse(csvRecord.get("datePublished"), DateTimeFormatter.ISO_LOCAL_DATE);

                        boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));

                        assets.add(new Thesis(title, author, topic, Abstract, datePublished, availability));
                    }
                    else if (filePath.equals(CSVPaths[4])) {
                        int id = Integer.parseInt(csvRecord.get("id"));
                        String name = csvRecord.get("name");
                        String borrowed = csvRecord.get("borrowed");

                        String[] borrowedAssetsTitle = borrowed.split("\\|");
                        LinkedList<Asset> assetsList = new LinkedList<>();

                        // Search for asset by name and add to assetsList
                        Asset[] arr = this.assets.toArray(new Asset[assets.size()]);
                        HeapSort.sort(arr);

                        for (String string: borrowedAssetsTitle) {
                            assetsList.add(BinarySearch.assetSearch(arr, string));
                        }

                        users.add(new LibraryUser(name, id, assetsList));
                    }
                    else if (filePath.equals(CSVPaths[5])) {
                        String name = csvRecord.get("name");
                        String authored = csvRecord.get("authored");


                        String[] authoredAssets = authored.split("\\|");
                        LinkedList<Asset> assetsList = new LinkedList<>();

                        // Search for asset by name and add to assetsList
                      
                        Asset[] arr = this.assets.toArray(new Asset[assets.size()]);
                        HeapSort.sort(arr);

                        Author author = new Author(name);

                        for (String string: authoredAssets) {
                            author.AddAssetToAuthor(BinarySearch.assetSearch(arr, string));
                        }
                        authors.add(author);
                    }
                    else if (filePath.equals(CSVPaths[6])) {
                        // Get loan ID
                        int id = Integer.parseInt(csvRecord.get("id"));

                        // Get library user
                        String userName = csvRecord.get("user");

                        LibraryUser user = getUser(userName);

                        // Get borrowed asset
                        String borrowed = csvRecord.get("borrowed");

                        Asset borrowedAsset = getAsset(borrowed);

                        // Create dateBorrowed
                        LocalDate dateBorrowed = LocalDate.parse(csvRecord.get("borrowDate"), DateTimeFormatter.ISO_LOCAL_DATE);

                        // Create dateReturned
                        LocalDate dateReturned = LocalDate.parse(csvRecord.get("returnDate"), DateTimeFormatter.ISO_LOCAL_DATE);

                        boolean returned = Boolean.parseBoolean(csvRecord.get("returned"));

                        loans.add(new Loan(id, user, borrowedAsset, dateBorrowed, dateReturned, returned));
                    }
            }
        } catch (IOException e) {
            System.out.printf("Failed to load asset. %s", e);
        } catch (AssetException e) {
            System.out.printf("Unable to add loaded asset. %s", e);
        }
    }

    public void save() {
        saveItems(new String[] {"title", "author", "ISBN", "availability"}, CSVPaths[0]);
        saveItems(new String[] {"title", "author", "ISBN", "duration", "availability"}, CSVPaths[1]);
        saveItems(new String[] {"title", "producer", "director", "playtime", "availability"}, CSVPaths[2]);
        saveItems(new String[] {"title", "author", "topic", "Abstract", "datePublished", "availability"}, CSVPaths[3]);
        saveItems(new String[] {"id", "name", "borrowed"}, CSVPaths[4]);
        saveItems(new String[] {"name", "authored"}, CSVPaths[5]);
        saveItems(new String[] {"id", "user", "borrowed", "dateBorrowed", "dateReturned", "returned"}, CSVPaths[6]);
    }

    private void saveItems(String[] headers, String filePath) {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(headers)
                .build();

        try (FileWriter fileWriter = new FileWriter(filePath);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat)) {

            if (filePath.equals(CSVPaths[0])) {
                for (Asset asset : assets) {
                    if (asset instanceof Book book && !(asset instanceof AudioBook)) {
                        csvPrinter.printRecord(
                                book.getTitle(),
                                book.getAuthor(),
                                book.getISBN(),
                                book.isAvailability()
                        );
                    }
                }
            } else if (filePath.equals(CSVPaths[1])) {
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
            } else if (filePath.equals(CSVPaths[2])) {
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
            } else if (filePath.equals(CSVPaths[3])) {
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
            } else if (filePath.equals(CSVPaths[4])) {
                for (LibraryUser user : users) {
                    StringBuilder borrowed = new StringBuilder();
                    for (Asset asset : user.getBorrowedAssets()) {
                        borrowed.append(asset.getTitle()).append("|");
                    }

                    csvPrinter.printRecord(
                            user.getID(),
                            user.getName(),
                            borrowed
                    );
                }
            } else if (filePath.equals(CSVPaths[5])) {
                for (Author author : authors) {
                    StringBuilder authoredAssets = new StringBuilder();
                    for (Asset asset : author.getAuthoredAssets()) {
                        authoredAssets.append(asset.getTitle()).append("|");
                    }

                    csvPrinter.printRecord(
                            author.getName(),
                            authoredAssets
                    );
                }
            } else if (filePath.equals(CSVPaths[6])) {
                for (Loan loan : loans) {
                    csvPrinter.printRecord(
                            loan.getID(),
                            loan.getBorrower().getName(),
                            loan.getBorrowedAsset().getTitle(),
                            loan.getBorrowDate().toString(),
                            loan.getReturnDate().toString(),
                            loan.isReturned()
                    );
                }
            }
        } catch (IOException e) {
            System.out.printf("Failed to save asset. %s", e);
        }
    }
}

