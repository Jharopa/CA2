package org.CA2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class UserInterface {
    private final Scanner sc;

    private final LibrarySystem library;

    //-------------------//
    // Listing functions //
    //-------------------//

    UserInterface() {
        sc = new Scanner(System.in);

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
    }

    private void catalogueList() {
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

    private void listAssets() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nPlease select one of the asset types to view: ");
            System.out.println("1. List all available assets");
            System.out.println("2. List available books");
            System.out.println("3. List available audio books");
            System.out.println("4. List available thesis");
            System.out.println("5. List available CDs");
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

                    System.out.print("\nPress enter to continue... ");
                    sc.nextLine();
                    break;
                case 2:
                    System.out.println("\nAvailable books: ");
                    library.listAvailableBooks();

                    System.out.print("\nPress enter to continue... ");
                    sc.nextLine();
                    break;
                case 3:
                    System.out.println("\nAvailable audio books: ");
                    library.listAvailableAudioBooks();

                    System.out.print("\nPress enter to continue... ");
                    sc.nextLine();
                    break;
                case 4:
                    System.out.println("\nAvailable theses: ");
                    library.listAvailableThesis();

                    System.out.print("\nPress enter to continue... ");
                    sc.nextLine();
                    break;
                case 5:
                    System.out.println("\nAvailable CDs: ");
                    library.listAvailableCds();

                    System.out.print("\nPress enter to continue... ");
                    sc.nextLine();
                    break;
                case 6:
                    System.out.println("\nBorrowed Assets: ");
                    library.listBorrowedAssets();

                    System.out.print("\nPress enter to continue...");
                    sc.nextLine();
                    break;
                case 0:
                    System.out.println("\nReturning previous menu... ");
                    break;
                default:
                    System.out.println("Invalid selection, please try again.\n");
            }
        }
    }

    private void listUsers() {
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
                    System.out.println("\nLibrary users: ");

                    library.listUsers();

                    System.out.print("\nPress enter to continue...");
                    sc.nextLine();
                    break;
                case 2:
                    int userID;

                    System.out.print("Please enter desired user's ID: ");

                    try {
                        userID = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Failed to find user. ID value provided was not a number!");
                        continue;
                    }

                    library.listUserAssets(userID);

                    System.out.print("\nPress enter to continue...");
                    sc.nextLine();
                    break;
                case 0:
                    System.out.println("\nReturning previous menu... ");
                    break;
                default:
                    System.out.println("Invalid selection, please try again.\n");
            }
        }
    }

    private void listAuthors() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nPlease select from one of the following options: ");
            System.out.println("1. List all authors");
            System.out.println("2. List authors assets");

            System.out.println("\n0. Return to previous menu\n");

            System.out.print("\nEnter your selection: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice, value is not a number. Please provide a value from the list of options");
            }

            switch (choice) {
                case 1:
                    System.out.println("\nAuthors: ");

                    library.listAuthors();

                    System.out.print("\nPress enter to continue...");
                    sc.nextLine();
                    break;
                case 2:
                    int authorID = 0;

                    System.out.print("\nPlease enter desired author's ID: ");

                    try {
                        authorID = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Failed to find author. ID value provided was not a number!");
                        continue;
                    }

                    library.listAuthorsAssets(authorID);

                    System.out.print("\nPress enter to continue...");
                    sc.nextLine();
                    break;
                case 0:
                    System.out.println("\nReturning previous menu... ");
                    break;
                default:
                    System.out.println("Invalid selection, please try again.\n");
            }
        }
    }

    private void listLoans() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nPlease select from one of the following options: ");
            System.out.println("1. List all loans");
            System.out.println("2. List overdue loans");

            System.out.println("\n0. Return to previous menu\n");

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

    //--------------------------//
    // End of listing functions //
    //--------------------------//

    //---------------------------------//
    // Library item creation functions //
    //---------------------------------//

    private void addLibraryItem() {
        int choice = -1;

        do {
            System.out.println("\nWhich type of asset would you like to add?");
            System.out.println("1. Book");
            System.out.println("2. Audio Book");
            System.out.println("3. Thesis");
            System.out.println("4. CD");
            System.out.println("\n0. Return to main menu");
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
                    System.out.println("\nReturning to main menu...");
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
                    System.out.println("Invalid selection, please try again.");
            }
        } while (choice != 0);
    }

    private void addBook() {
        String title, isbn;
        int authorID;

        try {
            System.out.print("Please enter the title of the book: ");
            title = sc.nextLine();

            System.out.print("Please enter the book's ISBN: ");
            isbn = sc.nextLine();

            System.out.print("Please enter the ID of the book's author: ");
            authorID = Integer.parseInt(sc.nextLine());

            System.out.println("Adding this book to our catalog");
            library.addBook(title, authorID, isbn);
        } catch (NumberFormatException e) {
            System.out.println("Failed to create book. Author ID value was non-number");
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Failed to create book. Please try again.");
        }

    }

    private void addCD() {
        String title, producer, director;
        int playtime;

        try {
            System.out.println("Please enter the title of the CD:\n");
            title = sc.nextLine();

            System.out.println("Please enter the producer of the cd:\n");
            producer = sc.nextLine();

            System.out.println("Please enter the director of the cd:\n");
            director = sc.nextLine();

            System.out.println("Please enter the length of the CD in minutes:\n");
            playtime = Integer.parseInt(sc.nextLine());

            System.out.println("Adding this cd to our catalog...");
            library.addCD(title, producer, director, playtime);
        } catch (NumberFormatException e) {
            System.out.println("Failed to create CD. Playtime value was non-number");
        } catch (NoSuchElementException|IllegalStateException e) {
            System.out.println("Failed to create CD due to unexpected error. Please try again.");
        }
    }

    private void addAudioBook() {
        try {
            System.out.println("Please enter the title of the audio book:\n");
            String title = sc.nextLine();

            System.out.println("Please enter the ID of the audio books author:\n");
            int authorID = Integer.parseInt(sc.nextLine());

            System.out.println("Please enter the duration of the audio book:\n");
            int duration = Integer.parseInt(sc.nextLine());

            System.out.println("Please enter th ISBN of this audio book...");
            String isbn = sc.nextLine();

            System.out.println("Adding the audio book to our catalog...");
            library.addAudioBook(title, authorID, isbn, duration);
        }  catch (NumberFormatException e) {
            System.out.println("Failed to create CD. Author ID or duration value provided was non-number");
        } catch (NoSuchElementException|IllegalStateException e) {
            System.out.println("Failed to create audio book due to unexpected error. Please try again.");
        }
    }

    private void addTheses() {
        try {
            System.out.println("Please enter the title of the thesis:\n");
            String title = sc.nextLine();

            System.out.println("Please enter the author of the thesis:\n");
            int authorID = Integer.parseInt(sc.nextLine());

            System.out.println("Please enter the topic of the thesis:\n");
            String topic = sc.nextLine();

            System.out.println("Please enter the abstract of the thesis:\n");
            String thesisAbstract = sc.nextLine();

            LocalDate date = null;
            String input = null;
            boolean validDate = false;

            System.out.println("Please enter the publish date for the thesis in the format yyyy-mm-dd:");

            while (!validDate) {
                input = sc.nextLine();
                validDate = validateDate(input);
            }

            date = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);

            System.out.println("Adding thesis to our catalog...");
            library.addThesis(title, authorID, topic, thesisAbstract, date);
        }  catch (NumberFormatException e) {
            System.out.println("Failed to create thesis. Author ID value provided was non-number");
        } catch (NoSuchElementException|IllegalStateException e) {
            System.out.println("Failed to create thesis due to unexpected error. Please try again.");
        }
    }

    private void addAuthor() {
        try {
            System.out.print("Please enter the name of the author: ");
            String name = sc.nextLine();

            library.addAuthor(name);
            System.out.println("Added " + name + " to list of authors in library system.");
        } catch (NoSuchElementException|IllegalStateException e) {
            System.out.println("Failed to create author due to unexpected error. Please try again.");
        }
    }

    private void addUser() {
        try {
            System.out.println("Please enter the user's full name:\n");
            String name = sc.nextLine();

            library.addUser(name);
            System.out.println("Added " + name + " to list of users in library system.");
        } catch (NoSuchElementException|IllegalStateException e) {
            System.out.println("Failed to create user due to unexpected error. Please try again.");
        }
    }

    //----------------------------------------//
    // End of library item creation functions //
    //----------------------------------------//

    //------------------------------------//
    // Loan creation and return functions //
    //------------------------------------//

    private void borrowAsset() {
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

    private void returnAsset() {
        System.out.print("Please enter the loan ID: ");

        try {
            int loanID = Integer.parseInt(sc.nextLine());
            library.returnLoan(loanID);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Loan ID must be a number!");
        }
    }

    //-------------------------------------------//
    // End of loan creation and return functions //
    //-------------------------------------------//

    //------------------//
    // Helper functions //
    //------------------//

    private boolean validateDate(String input) {
        try {
            LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            System.out.print("Invalid date format. Please enter the date as yyyy-MM-dd: ");
            return false;
        }
    }

    public String generateISBN() {
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

    //-------------------------//
    // End of helper functions //
    //-------------------------//

    public void run() {
        // Main Loop/Menu
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

            System.out.println("\n0. Exit\n");

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
}
