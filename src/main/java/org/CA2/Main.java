package org.CA2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;


public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private boolean validateIntegerRange(int choice, int limit) {
        if (choice >= 0 && choice <= limit) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateDate(String input) {
        try {
            LocalDate date = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date as yyyy-MM-dd:");
            return false;
        }
    }

    private void addLibraryItem() {
        int choice;
        do {
            System.out.println("\nWhich type of asset would you like to add?");
            System.out.println("1. Book");
            System.out.println("2. Audio Book");
            System.out.println("3. Thesis");
            System.out.println("4. CD");
            System.out.println("");
            System.out.println("0. Exit");

            choice = sc.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("\n\rThank you for using the library system. Goodbye.");
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
        System.out.println("Enter q to return to the main menu, or hit enter to add another assett");

        while (true) {
            String carryon = sc.nextLine();
            if (carryon.toLowerCase() == "q") break;
            else if (carryon.isEmpty()) addAuthorItem(name);
            else {
                System.out.println("Invalid entry. Please enter 'q' or nothing.");
            }
        }

    }

    private void addBook() {
        // Get book data as input
        System.out.println("Please enter the title of the book:\r\n");
        String title = sc.nextLine();
        System.out.println("Generating an ISBN....\r\n");
        String isbn = generateISBN();
        System.out.println("Please enter the author of the book:\r\n");
        String author = sc.nextLine();

        // Search for author
        LibrarySystem.getAuthor(author);

        System.out.println("Adding this book to our catalog");
        // Call LibrarySystem addBook
        LibrarySystem.addBook(title, author, isbn);
    }

    private void addCD() {
        // Get CD data as input
        System.out.println("Please enter the title of the CD:\r\n");
        String title = sc.nextLine();
        System.out.println("Please enter the producer of the book:\r\n");
        String producer = sc.nextLine();
        System.out.println("Please enter the director of the book:\r\n");
        String director = sc.nextLine();
        System.out.println("Please enter the length of the CD in minutes:\r\n");
        int playtime = sc.nextInt();

        System.out.println("Adding this cd to our catalog...");
        // Call LibrarySystem addCD
        LibrarySystem.addCD(title, producer, director, playtime);
    }

    private void addAudioBook() {
        // Get audiobook data as input
        System.out.println("Please enter the title of the audio book:\r\n");
        String title = sc.nextLine();
        System.out.println("Please enter the author of the book:\r\n");
        String author = sc.nextLine();
        System.out.println("Please enter the duration of the book:\r\n");
        int duration = sc.nextInt();
        System.out.println("Generating an ISBN for this audio book...");
        String isbn = generateISBN();

        // search for author
        LibrarySystem.getAuthor(author);
        System.out.println("Adding the audio book to our catalog...");
        // Call LibrarySystem addAudioBook
        LibrarySystem.addAudioBook(title, author, isbn, duration);
    }

    private void addTheses() {
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
        LocalDate date;
        boolean thisBool = false;
        while (!thisBool) {
            String input = sc.nextLine();
            thisBool = validateDate(input);
            date = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
        }


        // search for author
        LibrarySystem.getAuthor(author);

        // Call LibrarySystem addThesis
        LibrarySystem.addThesis(title, author, topic, thesisAbstract, date);
    }

    private void addAuthor() {
        // Get author data as input
        System.out.println("Please enter the name of the author:\r\n");
        String name = sc.nextLine();

        // Get asset titles and add to list
        LinkedList<Asset> authoredBooks;
        System.out.println("Would you like to add an asset belonging to this author? (y/n)");
        String input = sc.nextLine();
        if (input == "0" || input.toLowerCase() == "q") {
            System.out.println("Thank you for using the system. Goodbye.");
            break;
        } else if (input.toLowerCase() == "y") {
            System.out.println("You have chosen to add assets for this author...");
            addAuthorItem(name);
        }


        // Call LibrarySystem addAuthor
        LibrarySystem.addAuthor(name, authoredBooks);
    }

    private void addUser() {
        // Get user data as input
        System.out.println("Please enter the user's full name:\r\n");
        String name = sc.nextLine();
        // create ID generator method
        System.out.println("Please enter the user's ID:\r\n");
        int id = sc.nextInt();

        // enter logic here to allow user to borrow books immediately
        // Call LibrarySystem addUser
        LibrarySystem.addUser(id, name, null);
    }

    private void borrowAsset() {
        // Get user data as input
        System.out.println("Please enter the user's ID:\r\n");
        int id = sc.nextInt();
        LibrarySystem.getUser(id);

        // Get asset titles

        // Call LibrarySystem createLoan
    }

    private void returnAsset() {
        // Get user data as input
        System.out.println("Please enter the user's ID:\r\n");
        int id = sc.nextInt();
        LibrarySystem.getUser(id);
        // Get asset titles

        // Call LibrarySystem createLoan
    }

    private void listAvailableBooks() {
        // Call LibrarySystems listAvailableBooks
        LibrarySystem.listAvailableAssets();
    }

    private void listBorrowedBooks() {
        // Call LibrarySystems listBorrowedBooks

    }

    private void listAuthorsBooks() {
        // Call LibrarySystems listAuthorsBooks
    }

    public void main(String[] args) {
        // Call LibrarySystems load

        // Main Loop/Menu
        String[] csvPaths = new String[]{
                "assets.csv",
                "authors.csv",
                "users.csv",
                "loans.csv"
        };
        LibrarySystem library = new LibrarySystem(csvPaths); // Create library object

        while (true) {
            System.out.println("\nWelcome to the Library Management System. \r\nPlease select one of the following options:");
            System.out.println("1. Add asset to the library catalogue");
            System.out.println("2. Register a new user");
            System.out.println("3. View available assets");
            System.out.println("4. Lend book to customer");
            System.out.println("5. Customer is returning a book");
            System.out.println("6. View user account");
            System.out.println("7. View overdue fines");
            System.out.println();
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

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
                    System.out.println("You have chosen to lend an asset to a member.");
                    borrowAsset();
                    break;
                case 5:
                    System.out.println("You have chosen a member is returning an asset.");
                    returnAsset();
                    break;
                // Add case statements for other options
                case 0:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static String generateISBN() {
        // Generate random ISBN-13 digits
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
