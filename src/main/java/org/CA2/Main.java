package org.CA2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static LibrarySystem library;

    private static boolean validateDate(String input) {
        try {
            LocalDate date = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date as yyyy-MM-dd:");
            return false;
        }
    }

    private static void addLibraryItem() {
        int choice = -1;

        do {
            System.out.println("\nWhich type of asset would you like to add?");
            System.out.println("1. Book");
            System.out.println("2. Audio Book");
            System.out.println("3. Thesis");
            System.out.println("4. CD");
            System.out.println("0. Return to main menu");
            System.out.print("\nEnter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice, value is not a number. Please provide a value from the list of options");
                choice = -1;
                continue;
            }


            switch (choice) {
                case 0:
                    System.out.println("\n\rReturning to main menu...\n\r");
                    break;
                case 1:
                    System.out.println("You have chosen to add a book...");
                    addBook();
                    break;
                case 2:
                    System.out.println("You have chosen to add an audio book...");
                    addAudioBook();
                    break;
                case 3:
                    System.out.println("You have chosen to add a thesis...");
                    addTheses();
                    break;
                case 4:
                    System.out.println("You have chosen to add a CD...");
                    addCD();
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid number between 0 and 4.");
            }
        } while (choice != 0);
    }

    // This is a lot of reused code just because I have an author... there needs to be a better way
    private void addAuthorItem(int id) {
        // Enter multiple
        System.out.println("Please select one of the following to create for this author");
        System.out.println("1. Book");
        System.out.println("2. Audio Book");
        System.out.println("3. Thesis");

        int choice = 0;

        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice, value is not a number. Please provide a value from the list of options");
        }

        switch (choice) {
            case 1:
                System.out.println("You have chosen a book. Please enter it's title: ");
                String title = sc.nextLine();
                System.out.println("Generating isbn ");
                String isbn = generateISBN();
                System.out.println("Adding the book titled '" + title + "' to the catalog");
                library.addBook(title, id, isbn);
            case 2:
                System.out.println("You have chosen an audio book. Please enter it's title: ");
                String audioTitle = sc.nextLine();
                System.out.println("Please enter it's duration in minutes: ");
                int audioDuration = sc.nextInt();
                System.out.println("Generating isbn ");
                String isbnAudio = generateISBN();
                System.out.println("Adding the audio book titled '" + audioTitle + "' to the catalog");
                library.addAudioBook(audioTitle, id, isbnAudio, audioDuration);
                break;

            case 3:
                System.out.println("You have chosen a thesis. Please enter it's title: ");
                String thesisTitle = sc.nextLine();
                System.out.println("Generating isbn ");
                String isbnThesis = generateISBN();
                System.out.println("Enter the topic");
                String topic = sc.nextLine();
                System.out.println("Enter the abstract");
                String thesisAbstract = sc.nextLine();
                System.out.println("Enter the publishing date");
                LocalDate date = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
                System.out.println("Adding the thesis titled '" + thesisTitle + "' to the catalog");
                library.addThesis(thesisTitle, id, topic, thesisAbstract, date);
        }
    }

    private static void addBook() {
        String title, isbn;
        int authorID;

        System.out.print("Please enter the title of the book: ");
        title = sc.nextLine();

        System.out.print("Please enter the book's ISBN: ");
        isbn = sc.nextLine();

        System.out.print("Please enter the ID of the book's author: ");
        authorID = Integer.parseInt(sc.nextLine());

        System.out.println("Adding this book to our catalog");
        library.addBook(title, authorID, isbn);
    }

    private static void addCD() {
        System.out.println("Please enter the title of the CD:\n");
        String title = sc.nextLine();
        System.out.println("Debug: Title entered: " + title);

        System.out.println("Please enter the producer of the cd:\n");
        String producer = sc.nextLine();
        System.out.println("Please enter the director of the cd:\n");
        String director = sc.nextLine();
        System.out.println("Please enter the length of the CD in minutes:\n");
        int playtime = sc.nextInt();
        sc.nextLine();

        System.out.println("Adding this cd to our catalog...");
        // Call LibrarySystem addCD
        library.addCD(title, producer, director, playtime);

    }

    private static void addAudioBook() {
        System.out.println("Please enter the title of the audio book:\n");
        String title = sc.nextLine();
        System.out.println("Please enter the ID of the audio books author:\n");
        int authorID = sc.nextInt();
        System.out.println("Please enter the duration of the book:\n");
        int duration = sc.nextInt();
        System.out.println("Generating an ISBN for this audio book...");
        String isbn = generateISBN();
        System.out.println("ISBN for " + title + " is " + isbn);
        System.out.println("Adding the audio book to our catalog...");
        // Call LibrarySystem addAudioBook
        library.addAudioBook(title, authorID, isbn, duration);
    }

    private static void addTheses() {
        System.out.println("Please enter the title of the thesis:\n");
        String title = sc.nextLine();
        System.out.println("Please enter the author of the thesis:\n");
        int authorID = sc.nextInt();
        System.out.println("Please enter the topic of the thesis:\n");
        String topic = sc.nextLine();
        System.out.println("Please enter the abstract of the thesis:\n");
        String thesisAbstract = sc.nextLine();
        System.out.println("Please enter the publish date for the thesis in the format yyyy-mm-dd:");
        LocalDate date = null;
        boolean thisBool = false;
        while (!thisBool) {
            String input = sc.nextLine();
            thisBool = validateDate(input);
            date = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
        }
        System.out.println("Adding thesis to our catalog...");
        // Call LibrarySystem addThesis
        library.addThesis(title, authorID, topic, thesisAbstract, date);
    }

    private static void addAuthor() {
        // Get author data as input
        System.out.print("Please enter the name of the author: ");
        String name = sc.nextLine();
        library.addAuthor(name);
        System.out.println("Added " + name + " to list of authors in library system.");
    }

    private static void addUser() {
        // Get user data as input
        System.out.println("Please enter the user's full name:\n");
        String name = sc.nextLine();

        // enter logic here to allow user to borrow books immediately
        // Call LibrarySystem addUser
        library.addUser(name);
    }

    private static void borrowAsset() {
        try {
            System.out.print("Please enter the user's ID: ");
            int userID = Integer.parseInt(sc.nextLine());

            System.out.print("Please enter the asset's name: ");
            String assetName = sc.nextLine();

            library.createLoan(assetName, userID);
        } catch (NumberFormatException e) {
            System.out.println("Failed to create loan. User's ID must be a number!");
        }
    }

    private static void returnAsset() {
        // Get user data as input
        System.out.println("Please enter the user's ID:\n");
        int id = sc.nextInt();
        // LibrarySystem.getUser(id);
        // Get asset titles

        // Call LibrarySystem createLoan
    }

    private static void catalogueList() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nPlease select one of the following options: ");
            System.out.println("1. List assets");
            System.out.println("2. List users");
            System.out.println("3. List authors");
            System.out.println("4. List loans");

            System.out.println("\n0. Return to main menu");

            System.out.print("\nEnter your selection: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice, value is not a number. Please provide a value from the list of options");
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1:
                    listAssets();
                    break;
                case 2:
                    listUsers();
                    break;
                case 3:
                    listAuthors();
                    break;
                case 4:
                    listLoans();
                    break;
                case 0:
                    System.out.println("\nReturning to main menu... ");
                    break;
                default:
                    System.out.println("\nInvalid selection, please try again.\n");
            }
        }
    }

    private static void listAssets() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nPlease select one of the asset types to view: ");
            System.out.println("1. List all assets");
            System.out.println("2. List books");
            System.out.println("3. List audio books");
            System.out.println("4. List thesis");
            System.out.println("5. List CDs");
            System.out.println("6. List borrowed assets");

            System.out.println("\n0. Return to previous menu\n");

            System.out.print("Enter your selection: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice, value is not a number. Please provide a value from the list of options");
            }

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable assets: ");
                    library.listAvailableAssets();
                    break;
                case 2:
                    System.out.println("\nAvailable books: ");
                    library.listAvailableBooks();
                    break;
                case 3:
                    System.out.println("\nAvailable audio books: ");
                    library.listAvailableAudioBooks();
                    break;
                case 4:
                    System.out.println("\nAvailable theses: ");
                    library.listAvailableThesis();
                    break;
                case 5:
                    System.out.println("\nAvailable CDs: ");
                    library.listAvailableCds();
                    break;
                case 6:
                    System.out.println("\nBorrowed Assets: ");
                    library.listBorrowedAssets();
                    break;
                case 0:
                    System.out.println("\nReturning previous menu... ");
                    break;
                default:
                    System.out.println("Invalid selection, please try again.\n");
            }
        }
    }

    private static void listUsers() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nPlease select one of the asset types to view: ");
            System.out.println("1. All users");
            System.out.println("2. Users' borrowed assets");

            System.out.println("\n0. Return to previous\n");

            System.out.print("Enter your selection: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice, value is not a number. Please provide a value from the list of options");
            }

            switch (choice) {
                case 1:
                    System.out.println("Available assets: ");
                    library.listUsers();
                    break;
                case 2:
                    System.out.println("Please enter required user's ID:");
                    int userID = Integer.parseInt(sc.nextLine());
                    library.listUserAssets(userID);
                    break;
                case 0:
                    System.out.println("\nReturning previous menu... ");
                    break;
                default:
                    System.out.println("Invalid selection, please try again.\n");
            }
        }
    }

    private static void listAuthors() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nPlease select from one of the following options: ");
            System.out.println("1. List all authors");
            System.out.println("2. List authors assets");

            System.out.println("\n0. Return to previous menu");

            System.out.print("Enter your selection: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice, value is not a number. Please provide a value from the list of options");
            }

            switch (choice) {
                case 1:
                    library.listAuthors();
                    break;
                case 2:
                    int authorID = sc.nextInt();
                    library.listAuthorsAssets(authorID);
                    break;
                case 0:
                    System.out.println("\nReturning previous menu... ");
                    break;
                default:
                    System.out.println("Invalid selection, please try again.\n");
            }
        }
    }

    private static void listLoans() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nPlease select from one of the following options: ");
            System.out.println("1. List all loans");
            System.out.println("2. List overdue loans");

            System.out.println("\n0. Return to previous menu");

            System.out.print("Enter your selection: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice, value is not a number. Please provide a value from the list of options");
            }

            switch (choice) {
                case 1:
                    library.listLoans();
                    break;
                case 2:
                    library.listOverdueLoans();
                    break;
                case 0:
                    System.out.println("Returning previous menu... ");
                    break;
                default:
                    System.out.println("Invalid selection, please try again.\n");
            }
        }
    }

    public static void main(String[] args) {
        // Call LibrarySystems load

        // Main Loop/Menu
        String[] csvPaths = new String[]{
                "books.csv",
                "audiobooks.csv",
                "cds.csv",
                "theses.csv",
                "users.csv",
                "authors.csv",
                "loans.csv"
        };

        library = new LibrarySystem(csvPaths);

        library.load();
        library.initializeIDCounters();

        System.out.println("\nWelcome to the Library Management System.");

        int choice = -1;

        while (choice != 0) {
            System.out.println("\nPlease select one of the following options:");
            System.out.println("1. Catalogue information");
            System.out.println("2. Add asset to the library catalogue");
            System.out.println("3. Add author to catalogue");
            System.out.println("4. Register new user");
            System.out.println("5. Customer wants to borrow asset");
            System.out.println("6. Customer wants to return asset");

            System.out.println("\n0. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice, value is not a number. Please provide a value from the list of options");
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1:
                    catalogueList();
                    break;
                case 2:
                    System.out.println("You have chosen to add an asset.");
                    addLibraryItem();
                    break;
                case 3:
                    System.out.println("You have chosen to add an author.");
                    addAuthor();
                    break;
                case 4:
                    System.out.println("You have chosen to register a new member.");
                    addUser();
                    break;
                case 5:
                    System.out.println("You have chosen to lend an asset to a member.");
                    borrowAsset();
                    break;
                case 6:
                    System.out.println("You have chosen a member is returning an asset.");
                    returnAsset();
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select option from 0-6.");
            }
        }

        library.save();
    }

    public static String generateISBN() {
        // Generate random ISBN-13 digits
        Random random = new Random();
        int[] digits = new int[13];
        for (int i = 0; i < 12; i++) {
            digits[i] = random.nextInt(10);
        }

        // Convert list of digits to ISBN-13 string
        StringBuilder isbn = new StringBuilder();
        for (int digit : digits) {
            isbn.append(digit);
        }

        return isbn.toString();
    }

    // Call LibrarySystems save
}
