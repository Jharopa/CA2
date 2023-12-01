package org.CA2;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class LibrarySystem {
    private LinkedList<Asset> assets;
    private LinkedList<Author> authors;
    private LinkedList<LibraryUser> users;
    private LinkedList<Loan> loans;

    private AtomicInteger assetIDCount;
    private AtomicInteger userIDCount;
    private AtomicInteger authorIDCount;
    private AtomicInteger loanIDCount;

    // Paths to the CSV files that should be read from or written to
    // Order of Books, Audiobooks, CDs, Theses, Users, Authors, Loans
    private final String[] CSVPaths;

    public LibrarySystem(String[] CSVPaths) {
        assets = new LinkedList<>();
        authors = new LinkedList<>();
        users = new LinkedList<>();
        loans = new LinkedList<>();

        this.CSVPaths = CSVPaths;
    }

    public void addBook(String title, int authorID, String ISBN) {
        Author thisAuthor = getAuthor(authorID);

        if (thisAuthor == null) {
            System.out.println("No author found, please create this author first.");
            return;
        }

        try {
            Book book = new Book(assetIDCount.incrementAndGet(), title, thisAuthor.getName(), ISBN, true);
            thisAuthor.addAuthoredAsset(book);
            assets.add(book);
            sortAssets();
        } catch (AssetException e) {
            System.out.printf("Unable to add book. Reason: %s", e);
        }
    }


    public void addAudioBook(String title, int authorID, String ISBN, int duration) {
        Author thisAuthor = getAuthor(authorID);

        if (thisAuthor == null) {
            System.out.println("No author found, please create this author first.");
            return;
        }

        try {
            AudioBook audioBook = new AudioBook(assetIDCount.incrementAndGet(), title, thisAuthor.getName(), ISBN, duration, true);
            thisAuthor.addAuthoredAsset(audioBook);
            assets.add(audioBook);
            sortAssets();
        } catch (AssetException e) {
            System.out.printf("Unable to add audio book. Reason: %s", e);
        }
    }

    public void addCD(String title, String producer, String director, int playtime) {
        try {
            assets.add(new CD(assetIDCount.incrementAndGet(), title, producer, director, playtime, true));
            sortAssets();
        } catch (AssetException e) {
            System.out.printf("Unable to add CD. Reason: %s", e);
        }
    }

    // Create the date outside of class and pass it in or pass in formatted string and create it here?
    public void addThesis(String title, int authorID, String topic, String Abstract, LocalDate datePublished) {
        Author thisAuthor = getAuthor(authorID);

        if (thisAuthor == null) {
            System.out.println("No author found, please create this author first.");
            return;
        }

        try {
            Thesis thesis = new Thesis(assetIDCount.incrementAndGet(), title, thisAuthor.getName(), topic, Abstract, datePublished, true);
            thisAuthor.addAuthoredAsset(thesis);
            assets.add(thesis);
            sortAssets();
        } catch (AssetException e) {
            System.out.printf("Unable to add theses. Reason: %s", e);
        }
    }

    public void addAuthor(String name) {
        authors.add(new Author(authorIDCount.incrementAndGet(), name));
        sortAuthors();
    }

    public void addUser(String name) {
        users.add(new LibraryUser(userIDCount.incrementAndGet(), name));
        sortUsers();
    }

    public Asset getAsset(int assetID) {
        Asset[] assetsArr = new Asset[assets.size()];
        assets.toArray(assetsArr);

        return BinarySearch.assetSearch(assetsArr, assetID);
    }

    public Author getAuthor(int authorID) {
        Author[] authorArr = new Author[authors.size()];
        authors.toArray(authorArr);

        return BinarySearch.authorSearch(authorArr, authorID);
    }

    public LibraryUser getUser(int userID) {
        LibraryUser[] userArr = new LibraryUser[users.size()];
        users.toArray(userArr);

        return BinarySearch.userSearch(userArr, userID);
    }

    public Loan getLoan(int id) {
        Loan[] loanArr = new Loan[loans.size()];
        loans.toArray(loanArr);

        return BinarySearch.loanSearch(loanArr, id);
    }

    private void sortAssets() {
        Asset[] assetsArr = new Asset[assets.size()];
        assets.toArray(assetsArr);
        HeapSort.sort(assetsArr);
        assets = new LinkedList<>(Arrays.asList(assetsArr));
    }

    private void sortAuthors() {
        Author[] authorArr = new Author[authors.size()];
        authors.toArray(authorArr);
        HeapSort.sort(authorArr);
        authors = new LinkedList<>(Arrays.asList(authorArr));
    }

    private void sortUsers() {
        LibraryUser[] userArr = new LibraryUser[users.size()];
        users.toArray(userArr);
        HeapSort.sort(userArr);
        users = new LinkedList<>(Arrays.asList(userArr));
    }

    private void sortLoans() {
        Loan[] loanArr = new Loan[loans.size()];
        loans.toArray(loanArr);
        HeapSort.sort(loanArr);
        loans = new LinkedList<>(Arrays.asList(loanArr));
    }

    public void createLoan(int assetID, int userID) {
        Asset asset = getAsset(assetID);
        LibraryUser user = getUser(userID);

        if (asset == null) {
            System.out.printf("Unable to find asset with ID %s\nUnable to create loan", assetID);
            return;
        }

        if (user == null) {
            System.out.printf("Unable to find user with ID %s\nUnable to create loan", userID);
            return;
        }

        if (asset.isAvailability()) {
            asset.setAvailability(false);
            user.addBorrowedAsset(asset);

            loans.add(new Loan(loanIDCount.incrementAndGet() ,user, asset, LocalDate.now(), LocalDate.now().plusDays(14), false));

            sortLoans();

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

    public void listAvailableAssets() {
        listAvailableBooks();
        listAvailableAudioBooks();
        listAvailableThesis();
        listAvailableCds();
    }

    public void listAvailableBooks() {
        for (Asset asset : assets) {
            if (asset.isAvailability() && asset instanceof Book book && !(asset instanceof AudioBook)) {
                book.print();
            }
        }
    }

    public void listAvailableAudioBooks() {
        for (Asset asset : assets) {
            if (asset.isAvailability() && asset instanceof AudioBook audioBook) {
                audioBook.print();
            }
        }
    }

    public void listAvailableThesis() {
        for (Asset asset : assets) {
            if (asset.isAvailability() && asset instanceof Thesis thesis) {
                thesis.print();
            }
        }
    }

    public void listAvailableCds() {
        for (Asset asset : assets) {
            if (asset.isAvailability() && asset instanceof CD cd) {
                cd.print();
            }
        }
    }

    public void listBorrowedAssets() {
        for (Asset asset : assets) {
            if (!asset.isAvailability()) {
                asset.print();
            }
        }
    }

    public void listBorrowedAssets(int userID) {
        LibraryUser libraryUser = getUser(userID);

        for (Asset asset : libraryUser.getBorrowedAssets()) {
            asset.print();
        }
    }

    public void listAuthorsAssets(int authorID) {
        Author author = getAuthor(authorID);

        if (author == null) {
            System.out.printf("\nFailed to find author with ID %d\n", authorID);
            return;
        }

        System.out.printf("\nAssets authored by %s:\n", author.getName());

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

    public void listAuthors() {
        for (Author author : authors) {
            author.print();
        }
    }

    public void listUsers() {
        for (LibraryUser user : users) {
            user.print();
        }
    }

    public void listUserAssets(int userID) {
        LibraryUser user = getUser(userID);

        if (user == null) {
            System.out.printf("Failed to find user with ID %d\n", userID);
            return;
        }

        System.out.printf("\nAssets borrowed by %s:\n", user.getName());

        for (Asset asset : user.getBorrowedAssets()) {
            asset.print();
        }
    }

    public void initializeIDCounters() {
        if (assets.isEmpty()) {
            assetIDCount = new AtomicInteger(0);
        } else {
            assetIDCount = new AtomicInteger(assets.get(assets.size() - 1).getID());
        }

        if (users.isEmpty()) {
            userIDCount = new AtomicInteger(0);
        } else {
            userIDCount = new AtomicInteger(users.get(users.size() - 1).getID());
        }

        if (authors.isEmpty()) {
            authorIDCount = new AtomicInteger(0);
        } else {
            authorIDCount = new AtomicInteger(authors.get(authors.size() - 1).getID());
        }

        if (loans.isEmpty()) {
            loanIDCount = new AtomicInteger(0);
        } else {
            loanIDCount = new AtomicInteger(loans.get(loans.size() - 1).getID());
        }
    }

    public void load() {
        loadItems(new String[] {"id", "title", "author", "ISBN", "availability"}, CSVPaths[0]);
        loadItems(new String[] {"id", "title", "author", "ISBN", "duration", "availability"}, CSVPaths[1]);
        loadItems(new String[] {"id", "title", "producer", "director", "playtime", "availability"}, CSVPaths[2]);
        loadItems(new String[] {"id", "title", "author", "topic", "Abstract", "datePublished", "availability"}, CSVPaths[3]);
        loadItems(new String[] {"id", "name", "borrowed"}, CSVPaths[4]);
        loadItems(new String[] {"id", "name", "authored"}, CSVPaths[5]);
        loadItems(new String[] {"id", "user", "borrowed", "borrowDate", "returnDate", "returned"}, CSVPaths[6]);

        sortAssets();
        sortAuthors();
        sortUsers();
        sortLoans();
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
                        int id = Integer.parseInt(csvRecord.get("id"));
                        String title = csvRecord.get("title");
                        String author = csvRecord.get("author");
                        String ISBN = csvRecord.get("ISBN");
                        boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));

                        assets.add(new Book(id, title, author, ISBN, availability));
                    }
                    else if (filePath.equals(CSVPaths[1])) {
                        int id = Integer.parseInt(csvRecord.get("id"));
                        String title = csvRecord.get("title");
                        String author = csvRecord.get("author");
                        String ISBN = csvRecord.get("ISBN");
                        int duration = Integer.parseInt(csvRecord.get("duration"));
                        boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));

                        assets.add(new AudioBook(id, title, author, ISBN, duration, availability));
                    }
                    else if (filePath.equals(CSVPaths[2])) {
                        int id = Integer.parseInt(csvRecord.get("id"));
                        String title = csvRecord.get("title");
                        String producer = csvRecord.get("producer");
                        String director = csvRecord.get("director");
                        int playtime = Integer.parseInt(csvRecord.get("playtime"));
                        boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));

                        assets.add(new CD(id, title, producer, director, playtime, availability));
                    }
                    else if (filePath.equals(CSVPaths[3])) {
                        int id = Integer.parseInt(csvRecord.get("id"));
                        String title = csvRecord.get("title");
                        String author = csvRecord.get("author");
                        String topic = csvRecord.get("topic");
                        String Abstract = csvRecord.get("Abstract");

                        LocalDate datePublished = LocalDate.parse(csvRecord.get("datePublished"), DateTimeFormatter.ISO_LOCAL_DATE);

                        boolean availability = Boolean.parseBoolean(csvRecord.get("availability"));

                        assets.add(new Thesis(id, title, author, topic, Abstract, datePublished, availability));
                    }
                    else if (filePath.equals(CSVPaths[4])) {
                        int id = Integer.parseInt(csvRecord.get("id"));
                        String name = csvRecord.get("name");
                        String borrowed = csvRecord.get("borrowed");

                        String[] borrowedAssetsIDStrings;
                        int[] borrowedAssetsIDs = null;

                        if (!borrowed.isEmpty()) {
                            borrowedAssetsIDStrings = borrowed.split("\\|");
                            borrowedAssetsIDs = new int[borrowedAssetsIDStrings.length];

                            for (int i = 0; i < borrowedAssetsIDs.length; i++) {
                                borrowedAssetsIDs[i] = Integer.parseInt(borrowedAssetsIDStrings[i]);
                            }
                        }

                        // Search for asset by name and add to assetsList
                        Asset[] arr = assets.toArray(new Asset[0]);
                        HeapSort.sort(arr);

                        LibraryUser user = new LibraryUser(id, name);

                        if (borrowedAssetsIDs != null) {
                            for (int assetID: borrowedAssetsIDs) {
                                user.addBorrowedAsset(BinarySearch.assetSearch(arr, assetID));
                            }
                        }

                        users.add(user);
                    } else if (filePath.equals(CSVPaths[5])) {
                        int id = Integer.parseInt(csvRecord.get("id"));
                        String name = csvRecord.get("name");
                        String authored = csvRecord.get("authored");

                        String[] authoredAssetIDStrings;
                        int[] authoredAssetIDs = null;

                        if (!authored.isEmpty()) {
                            authoredAssetIDStrings = authored.split("\\|");
                            authoredAssetIDs = new int[authoredAssetIDStrings.length];

                            for (int i = 0; i < authoredAssetIDs.length; i++) {
                                authoredAssetIDs[i] = Integer.parseInt(authoredAssetIDStrings[i]);
                            }
                        }

                        // Search for asset by name and add to assetsList
                        Asset[] arr = assets.toArray(new Asset[0]);
                        HeapSort.sort(arr);

                        Author author = new Author(id, name);

                        if (authoredAssetIDs != null) {
                            for (int assetID: authoredAssetIDs) {
                                author.addAuthoredAsset(BinarySearch.assetSearch(arr, assetID));
                            }
                        }

                        authors.add(author);
                    }
                    else if (filePath.equals(CSVPaths[6])) {
                        // Get loan ID
                        int loanID = Integer.parseInt(csvRecord.get("id"));

                        // Get library user
                        int userID = Integer.parseInt(csvRecord.get("user"));

                        LibraryUser user = getUser(userID);

                        // Get borrowed asset
                        int assetID = Integer.parseInt(csvRecord.get("borrowed"));

                        sortAssets();
                        Asset borrowedAsset = getAsset(assetID);

                        // Create dateBorrowed
                        LocalDate dateBorrowed = LocalDate.parse(csvRecord.get("borrowDate"), DateTimeFormatter.ISO_LOCAL_DATE);

                        // Create dateReturned
                        LocalDate dateReturned = LocalDate.parse(csvRecord.get("returnDate"), DateTimeFormatter.ISO_LOCAL_DATE);

                        boolean returned = Boolean.parseBoolean(csvRecord.get("returned"));

                        loans.add(new Loan(loanID, user, borrowedAsset, dateBorrowed, dateReturned, returned));
                    }
            }
        } catch (IOException e) {
            System.out.printf("Failed to load asset. %s", e);
        } catch (AssetException e) {
            System.out.printf("Unable to add loaded asset. %s", e);
        }
    }

    public void save() {
        sortAssets();
        sortAuthors();
        sortUsers();
        sortLoans();

        saveItems(new String[] {"id", "title", "author", "ISBN", "availability"}, CSVPaths[0]); // Book
        saveItems(new String[] {"id", "title", "author", "ISBN", "duration", "availability"}, CSVPaths[1]); // Audiobook
        saveItems(new String[] {"id", "title", "producer", "director", "playtime", "availability"}, CSVPaths[2]); // CD
        saveItems(new String[] {"id", "title", "author", "topic", "Abstract", "datePublished", "availability"}, CSVPaths[3]); // Thesis
        saveItems(new String[] {"id", "name", "borrowed"}, CSVPaths[4]); // User
        saveItems(new String[] {"id", "name", "authored"}, CSVPaths[5]); // Author
        saveItems(new String[] {"id", "user", "borrowed", "dateBorrowed", "dateReturned", "returned"}, CSVPaths[6]); // Loan
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
                                book.getID(),
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
                                audioBook.getID(),
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
                                cd.getID(),
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
                                thesis.getID(),
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
                    StringBuilder borrowedAssets = new StringBuilder();
                    LinkedList<Asset> assets = user.getBorrowedAssets();

                    for (Asset asset : assets) {
                        borrowedAssets.append(asset.getID()).append("|");
                    }

                    csvPrinter.printRecord(
                            user.getID(),
                            user.getName(),
                            borrowedAssets
                    );
                }
            } else if (filePath.equals(CSVPaths[5])) {
                for (Author author : authors) {
                    StringBuilder authoredAssets = new StringBuilder();
                    LinkedList<Asset> assets = author.getAuthoredAssets();

                    for (Asset asset : assets) {
                        authoredAssets.append(asset.getID()).append("|");
                    }

                    csvPrinter.printRecord(
                            author.getID(),
                            author.getName(),
                            authoredAssets
                    );
                }
            } else if (filePath.equals(CSVPaths[6])) {
                for (Loan loan : loans) {
                    csvPrinter.printRecord(
                            loan.getID(),
                            loan.getBorrower().getID(),
                            loan.getBorrowedAsset().getID(),
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

