package org.CA2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static boolean validateIntegerRange(int choice, int limit) {
        if (choice >= 0 && choice <= limit) {
            return true;
        } else {
            return false;
        }
    }

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
        int choice;
        do {
            System.out.println("\nWhich type of asset would you like to add?");
            System.out.println("1. Book");
            System.out.println("2. Audio Book");
            System.out.println("3. Thesis");
            System.out.println("4. CD");
            System.out.println("0. Return to main menu");
            System.out.print("\r\nEnter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("\n\rReturning to main menu...\n\r");
                    return;
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
        } while (!validateIntegerRange(choice, 4));
    }

    // This is a lot of reused code just because I have an author... there needs to be a better way
    private void addAuthorItem(String name) {
        System.out.println("Please select one of the following to create for the author " + name);
        System.out.println("1. Book");
        System.out.println("2. Audio Book");
        System.out.println("3. Thesis");
        int input = sc.nextInt();

        switch (input) {
            case 1:
                System.out.println("You have chosen a book. Please enter it's title: ");
                String title = sc.nextLine();
                System.out.println("Generating isbn ");
                String isbn = generateISBN();
                System.out.println("Adding the book titled '" + title + "' to the catalog");
                LibrarySystem.addBook(title, name, isbn);
            case 2:
                System.out.println("You have chosen an audio book. Please enter it's title: ");
                String audioTitle = sc.nextLine();
                System.out.println("Please enter it's duration in minutes: ");
                int audioDuration = sc.nextInt();
                System.out.println("Generating isbn ");
                String isbnAudio = generateISBN();
                System.out.println("Adding the audio book titled '" + audioTitle + "' to the catalog");
                LibrarySystem.addAudioBook(audioTitle, name, isbnAudio, audioDuration);
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
                LibrarySystem.addThesis(thesisTitle, name, topic, thesisAbstract, date);
        }
        System.out.println("Enter q to return to the main menu, or hit enter to add another asset");

        while (true) {
            String carryon = sc.nextLine();
            if (carryon.toLowerCase() == "q") break;
            else if (carryon.isEmpty()) addAuthorItem(name);
            else {
                System.out.println("Invalid entry. Please enter 'q' or nothing.");
            }
        }

    }

    private static void addBook() {
        // Get book data as input
        sc.nextLine();
        System.out.println("Please enter the title of the book:\r\n");
        String title = sc.nextLine();
        System.out.println("Generating an ISBN....\r\n");
        String isbn = generateISBN();
        System.out.println("ISBN for " + title + " is " + isbn);
        System.out.println("Please enter the author of the book:\r\n");
        String author = sc.nextLine();

//        Search for author
//        LibrarySystem.getAuthor(author);

        System.out.println("Adding this book to our catalog");
        // Call LibrarySystem addBook
        LibrarySystem.addBook(title, author, isbn);
    }

    private static void addCD() {
        // needed to add an extra nextLine as it's not waiting for cd title input
        sc.nextLine();
        System.out.println("Please enter the title of the CD:\r\n");
        String title = sc.nextLine();
        System.out.println("Debug: Title entered: " + title);

        System.out.println("Please enter the producer of the cd:\r\n");
        String producer = sc.nextLine();
        System.out.println("Please enter the director of the cd:\r\n");
        String director = sc.nextLine();
        System.out.println("Please enter the length of the CD in minutes:\r\n");
        int playtime = sc.nextInt();
        sc.nextLine();

        System.out.println("Adding this cd to our catalog...");
        // Call LibrarySystem addCD
        LibrarySystem.addCD(title, producer, director, playtime);

    }

    private static void addAudioBook() {
        // Get audiobook data as input
        sc.nextLine();
        System.out.println("Please enter the title of the audio book:\r\n");
        String title = sc.nextLine();
        System.out.println("Please enter the author of the book:\r\n");
        String author = sc.nextLine();
        System.out.println("Please enter the duration of the book:\r\n");
        int duration = sc.nextInt();
        System.out.println("Generating an ISBN for this audio book...");
        String isbn = generateISBN();
        System.out.println("ISBN for " + title + " is " + isbn);
        // search for author
//        LibrarySystem.getAuthor(author);
        System.out.println("Adding the audio book to our catalog...");
        // Call LibrarySystem addAudioBook
        LibrarySystem.addAudioBook(title, author, isbn, duration);
    }

    private static void addTheses() {
        sc.nextLine();
        // Get thesis data as input
        System.out.println("Please enter the title of the thesis:\r\n");
        String title = sc.nextLine();
        System.out.println("Please enter the author of the thesis:\r\n");
        String author = sc.nextLine();
        System.out.println("Please enter the topic of the thesis:\r\n");
        String topic = sc.nextLine();
        System.out.println("Please enter the abstract of the thesis:\r\n");
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
        LibrarySystem.addThesis(title, author, topic, thesisAbstract, date);
    }

    private static void addAuthor() {
        // Get author data as input
        System.out.print("Please enter the name of the author: ");
        String name = sc.nextLine();
        LibrarySystem.addAuthor(name);
        System.out.println("Added " + name + " to list of authors in library system.");
//        System.out.println("Would you like to add an asset belonging to this author? (y/n)");
//        String input = sc.nextLine();
//        if (input.toLowerCase() == "n") {
//            System.out.println("Returning to main menu...");
//            return;
//        } else if (input.toLowerCase() == "y") {
//            System.out.println("You have chosen to add assets for this author...");
//            addLibraryItem();
//        }
    }

    private static void addUser() {
        // Get user data as input
        System.out.println("Please enter the user's full name:\r\n");
        String name = sc.nextLine();

        // enter logic here to allow user to borrow books immediately
        // Call LibrarySystem addUser
        LibrarySystem.addUser(name);
    }

    private static void borrowAsset() {
        // Get user data as input
        System.out.println("Please enter the user's ID:\r\n");
        int id = sc.nextInt();
        // LibrarySystem.getUser(id);

        // Get asset titles

        // Call LibrarySystem createLoan
    }

    private static void returnAsset() {
        // Get user data as input
        System.out.println("Please enter the user's ID:\r\n");
        int id = sc.nextInt();
        // LibrarySystem.getUser(id);
        // Get asset titles

        // Call LibrarySystem createLoan
    }

    private static void ListAvailableAssets() {
        System.out.println("Please select one of the asset types to view: ");
        System.out.println("1. All assets");
        System.out.println("2. Book");
        System.out.println("3. Audio Book");
        System.out.println("4. Thesis");
        System.out.println("5. CDs");
        System.out.println("0. Return to main menu");

        System.out.print("Enter your selection: ");
        int input = sc.nextInt();

        switch (input) {
            case 1:
                System.out.println("Listing available assets: ");
                LibrarySystem.listAvailableAssets();
                break;
            case 2:
                System.out.println("Listing available books: ");
                LibrarySystem.listAvailableBooks();
                break;
            case 3:
                System.out.println("Listing available audio books: ");
                LibrarySystem.listAvailableAudioBooks();
                break;
            case 4:
                System.out.println("Listing available theses: ");
                LibrarySystem.listAvailableThesis();
                break;
            case 5:
                System.out.println("Listing available CDs: ");
                LibrarySystem.listAvailableCds();
                break;
            case 0:
                System.out.println("Returning to main menu... ");
                return;
            default:
                System.out.println("Invalid selection, please try again.\n");
        }
    }

    private static void listAvailableBooks() {
        // Call LibrarySystems listAvailableBooks
        LibrarySystem.listAvailableAssets();
    }

    private void listBorrowedBooks() {
        // Call LibrarySystems listBorrowedBooks

    }

    private void listAuthorsBooks() {
        // Call LibrarySystems listAuthorsBooks
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

        LibrarySystem library = new LibrarySystem(csvPaths); // Create library object

        library.load();
        library.initializeIDCounters();

        // library.listAssets();
        // library.listAuthors();

        int choice = -1;

        while (choice != 0) {
            System.out.println("\nWelcome to the Library Management System. \r\nPlease select one of the following options:");
            System.out.println("1. Add asset to the library catalogue");
            System.out.println("2. Add author to catalogue");
            System.out.println("3. Register new user");
            System.out.println("4. View available assets");
            System.out.println("5. Customer wants to borrow asset");
            System.out.println("6. Customer wants to return asset");
            System.out.println();
            System.out.println("0. Exit");


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
                    System.out.println("You have chosen to add an asset.");
                    addLibraryItem();
                    break;
                case 2:
                    System.out.println("You have chosen to add an author.");
                    addAuthor();
                    break;
                case 3:
                    System.out.println("You have chosen to register a new member.");
                    addUser();
                    break;
                case 4:
                    System.out.println("You have chosen to view available assets");
                    ListAvailableAssets();
                    break;
                case 5:
                    System.out.println("You have chosen to lend an asset to a member.");
                    borrowAsset();
                    break;
                case 6:
                    System.out.println("You have chosen a member is returning an asset.");
                    returnAsset();
                    break;
                // Add case statements for other options
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
