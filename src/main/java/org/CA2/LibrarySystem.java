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
    /**
     * Linked list containing the library system's asset objects
     */
    private LinkedList<Asset> assets;
    /**
     * Linked list containing the library system's authors objects
     */
    private LinkedList<Author> authors;
    /**
     * Linked list containing the library system's library user objects
     */
    private LinkedList<LibraryUser> users;
    /**
     * Linked list containing the library system's loans objects
     */
    private LinkedList<Loan> loans;

    /**
     * AtomicInteger used for asset object's ID generation
     */
    private AtomicInteger assetIDCount;
    /**
     * AtomicInteger used for user object's ID generation
     */
    private AtomicInteger userIDCount;
    /**
     * AtomicInteger used for author object's ID generation
     */
    private AtomicInteger authorIDCount;
    /**
     * AtomicInteger used for loan object's ID generation
     */
    private AtomicInteger loanIDCount;

    /**
     * Paths to the CSV files that should be read from or written to
     * In the order of Books, Audiobooks, CDs, Theses, Users, Authors, Loans
     */
    private final String[] CSVPaths;

    /**
     * Class constructor, initializes the library system's linked lists and CSV paths
     * @param CSVPaths Paths of the files library item objects should be read from and written to.
     */
    public LibrarySystem(String[] CSVPaths) {
        assets = new LinkedList<>();
        authors = new LinkedList<>();
        users = new LinkedList<>();
        loans = new LinkedList<>();

        this.CSVPaths = CSVPaths;
    }

    /**
     * Adds a new book to the library system
     * @param title New book's title
     * @param authorID ID of the new book's author
     * @param ISBN New book's ISBN
     */
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

    /**
     * Adds a new audiobook to the library system
     * @param title New audiobook's title
     * @param authorID ID of the new audiobook's author
     * @param ISBN New audiobook's ISBN
     * @param duration New audiobook's duration
     */
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

    /**
     * Adds a new CD to the library system
     * @param title New CDs title
     * @param producer New CDs producer
     * @param performer New CDs performer
     * @param playtime New CDs playtime
     */
    public void addCD(String title, String producer, String performer, int playtime) {
        try {
            assets.add(new CD(assetIDCount.incrementAndGet(), title, producer, performer, playtime, true));
            sortAssets();
        } catch (AssetException e) {
            System.out.printf("Unable to add CD. Reason: %s", e);
        }
    }

    /**
     * Adds a new thesis to the library system
     * @param title New thesis' title
     * @param authorID ID of the new thesis' author
     * @param topic New thesis' topic
     * @param Abstract New thesis' abstract
     * @param datePublished New thesis' publish date
     */
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

    /**
     * Adds a new author to the library system
     * @param name The name of the new author
     */
    public void addAuthor(String name) {
        authors.add(new Author(authorIDCount.incrementAndGet(), name));
        sortAuthors();
    }

    /**
     * Adds a new user to the library system
     * @param name The name of the new user
     */
    public void addUser(String name) {
        users.add(new LibraryUser(userIDCount.incrementAndGet(), name));
        sortUsers();
    }

    /**
     * Gets a library system's asset using the asset's ID
     * @param assetID The ID of the desired asset
     * @return The asset object if found otherwise null
     */
    public Asset getAsset(int assetID) {
        Asset[] assetsArr = new Asset[assets.size()];
        assets.toArray(assetsArr);

        return BinarySearch.assetSearch(assetsArr, assetID);
    }

    /**
     * Gets a library system's author using the author's ID
     * @param authorID The ID of the desired author
     * @return The author object if found otherwise null
     */
    public Author getAuthor(int authorID) {
        Author[] authorArr = new Author[authors.size()];
        authors.toArray(authorArr);

        return BinarySearch.authorSearch(authorArr, authorID);
    }

    /**
     * Gets a library system's user using the user's ID
     * @param userID The ID of the desired user
     * @return The user object if found otherwise null
     */
    public LibraryUser getUser(int userID) {
        LibraryUser[] userArr = new LibraryUser[users.size()];
        users.toArray(userArr);

        return BinarySearch.userSearch(userArr, userID);
    }

    /**
     * Gets a library system's loan using the loan's ID
     * @param id The ID of the desired loan
     * @return The loan object if found otherwise null
     */
    public Loan getLoan(int id) {
        Loan[] loanArr = new Loan[loans.size()];
        loans.toArray(loanArr);

        return BinarySearch.loanSearch(loanArr, id);
    }

    /**
     * Sorts library system's assets linked list
     */
    private void sortAssets() {
        Asset[] assetsArr = new Asset[assets.size()];
        assets.toArray(assetsArr);
        HeapSort.sort(assetsArr);
        assets = new LinkedList<>(Arrays.asList(assetsArr));
    }

    /**
     * Sorts library system's authors linked list
     */
    private void sortAuthors() {
        Author[] authorArr = new Author[authors.size()];
        authors.toArray(authorArr);
        HeapSort.sort(authorArr);
        authors = new LinkedList<>(Arrays.asList(authorArr));
    }

    /**
     * Sorts library system's users linked list
     */
    private void sortUsers() {
        LibraryUser[] userArr = new LibraryUser[users.size()];
        users.toArray(userArr);
        HeapSort.sort(userArr);
        users = new LinkedList<>(Arrays.asList(userArr));
    }

    /**
     * Sorts library system's loans linked list
     */
    private void sortLoans() {
        Loan[] loanArr = new Loan[loans.size()];
        loans.toArray(loanArr);
        HeapSort.sort(loanArr);
        loans = new LinkedList<>(Arrays.asList(loanArr));
    }

    /**
     * User to create a library system loan
     * @param assetID The ID of the asset to be loaned out
     * @param userID The ID of the user loaning the asset
     */
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

    /**
     * Used to return a library system loan
     * @param id The ID of the loan to be returned
     */
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

    /**
     * Lists library system's available assets
     */
    public void listAvailableAssets() {
        listAvailableBooks();
        listAvailableAudioBooks();
        listAvailableThesis();
        listAvailableCds();
    }

    /**
     * Lists library system's available books
     */
    public void listAvailableBooks() {
        for (Asset asset : assets) {
            if (asset.isAvailability() && asset instanceof Book book && !(asset instanceof AudioBook)) {
                book.print();
            }
        }
    }

    /**
     * Lists library system's available audiobooks
     */
    public void listAvailableAudioBooks() {
        assets.forEach( (asset) -> {
            if (asset.isAvailability() && asset instanceof AudioBook audioBook) {
                audioBook.print();
            }
        });
    }

    /**
     * Lists library system's available theses
     */
    public void listAvailableThesis() {
        assets.forEach( (asset) -> {
            if (asset.isAvailability() && asset instanceof Thesis thesis) {
                thesis.print();
            }
        });
    }

    /**
     * Lists library system's available CDs
     */
    public void listAvailableCds() {
        assets.forEach( (asset) -> {
            if (asset.isAvailability() && asset instanceof CD cd) {
                cd.print();
            }
        });
    }

    /**
     * Lists library system's borrowed assets
     */
    public void listBorrowedAssets() {
        assets.forEach( (asset) -> {
            if (!asset.isAvailability()) {
                asset.print();
            }
        });
    }

    /**
     * Lists authored assets of a library system's author selected via user's ID
     * @param authorID ID of desired author
     */
    public void listAuthorsAssets(int authorID) {
        Author author = getAuthor(authorID);

        if (author == null) {
            System.out.printf("\nFailed to find author with ID %d\n", authorID);
            return;
        }

        System.out.printf("\nAssets authored by %s:\n", author.getName());

        author.getAuthoredAssets().forEach(Printable::print);
    }

    /**
     * Lists borrowed assets of a library system's user selected via user's ID
     * @param userID ID of desired user
     */
    public void listUserAssets(int userID) {
        LibraryUser user = getUser(userID);

        if (user == null) {
            System.out.printf("Failed to find user with ID %d\n", userID);
            return;
        }

        System.out.printf("\nAssets borrowed by %s:\n", user.getName());

        user.getBorrowedAssets().forEach(Printable::print);
    }

    /**
     * Lists library system's loans
     */
    public void listLoans() {
        System.out.printf(
                "%-5s%-12s%-18s%-15s%-15s%-10s\n",
                "ID", "Borrower", "Borrowed assets",
                "Borrow date", "Return date", "Status"
        );

        loans.forEach(Printable::print);
    }

    /**
     * Lists library system's overdue loans
     */
    public void listOverdueLoans() {
        System.out.printf(
                "%-5s%-12s%-18s%-15s%-15s%-10s\n",
                "ID", "Borrower", "Borrowed assets",
                "Borrow date", "Return date", "Status"
        );

        loans.forEach( (loan) -> {
            if (loan.isOverdue()) {
                loan.print();
            }
        });
    }

    /**
     * Lists library system's authors
     */
    public void listAuthors() {
        System.out.printf("%-5s%-24s\n", "ID", "Name");

        authors.forEach(Author::print);
    }

    /**
     * Lists library system's users
     */
    public void listUsers() {
        System.out.printf("%-5s%-24s\n", "ID", "Name");

        users.forEach(LibraryUser::print);
    }

    /**
     * Initializes the AtomicInteger counters for assets, users, authors, and loans, used to generate IDs each respective object
     */
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

    /**
     * Calls loadItems function with the appropriate CSV headers and CSV path then sorts asset, author, user, and loan
     * linked lists populated by the loadItems function calls.
     */
    public void load() {
        loadItems(new String[] {"id", "title", "author", "ISBN", "availability"}, CSVPaths[0]);
        loadItems(new String[] {"id", "title", "author", "ISBN", "duration", "availability"}, CSVPaths[1]);
        loadItems(new String[] {"id", "title", "producer", "performer", "playtime", "availability"}, CSVPaths[2]);
        loadItems(new String[] {"id", "title", "author", "topic", "Abstract", "datePublished", "availability"}, CSVPaths[3]);
        loadItems(new String[] {"id", "name", "borrowed"}, CSVPaths[4]);
        loadItems(new String[] {"id", "name", "authored"}, CSVPaths[5]);
        loadItems(new String[] {"id", "user", "borrowed", "borrowDate", "returnDate", "returned"}, CSVPaths[6]);

        sortAssets();
        sortAuthors();
        sortUsers();
        sortLoans();
    }

    /**
     * Reads asset, author, user, and loan information in from CSV files creating objects and adding them to the
     * appropriate linked list in the library system using the CSV file headers and file path provided by the load function
     * to do so.
     *
     * @param headers CSV file headers
     * @param filePath CSV file path
     */
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
                        String director = csvRecord.get("performer");
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

    /**
     * Sorts asset, author, user, and loan linked lists then calls save item functions with the appropriate CSV headers,
     * and CSV path.
     */
    public void save() {
        sortAssets();
        sortAuthors();
        sortUsers();
        sortLoans();

        saveItems(new String[] {"id", "title", "author", "ISBN", "availability"}, CSVPaths[0]); // Book
        saveItems(new String[] {"id", "title", "author", "ISBN", "duration", "availability"}, CSVPaths[1]); // Audiobook
        saveItems(new String[] {"id", "title", "producer", "performer", "playtime", "availability"}, CSVPaths[2]); // CD
        saveItems(new String[] {"id", "title", "author", "topic", "Abstract", "datePublished", "availability"}, CSVPaths[3]); // Thesis
        saveItems(new String[] {"id", "name", "borrowed"}, CSVPaths[4]); // User
        saveItems(new String[] {"id", "name", "authored"}, CSVPaths[5]); // Author
        saveItems(new String[] {"id", "user", "borrowed", "dateBorrowed", "dateReturned", "returned"}, CSVPaths[6]); // Loan
    }

    /**
     * Writes asset, author, user, and loan objects found in the library system's linked lists to CSV files using the appropriate
     * CSV headers and file path.
     * @param headers CSV file headers
     * @param filePath CSV file path
     */
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
                                cd.getPerformer(),
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

