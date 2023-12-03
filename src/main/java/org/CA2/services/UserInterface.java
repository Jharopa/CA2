package org.CA2.services;

import org.CA2.util.Helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInterface {
    /**
     * UserInterface class' Scanner object
     */
    private final Scanner sc;

    /**
     * UserInterface class' LibrarySystem object
     */
    private final LibrarySystem library;

    //-------------------//
    // Listing functions //
    //-------------------//

    /**
     * Class constructor, initializes class' scanner and library system by providing CSV paths,
     * and calling the library system's constructor, load (loads library items state from CSV files),
     * and initializeIDCounters functions
     */
    public UserInterface() {
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

    /**
     * Function used to select the desired library items information to be listed, calling the appropriate user interface functions
     * associated with listing assets, users, authors, or loans
     */
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

            choice = Helpers.validateInteger(choice);

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
                    choice = -1;
            }
        }
    }

    /**
     * Function used to select the desired assets information to be listed calling the appropriate library system functions
     * associated with listing all available assets, available books, available audiobooks, available theses,
     * available CDs, or borrowed assets
     */
    private void listAssets() {
        int choice = -1;

        while (choice != 0) {
            DisplayService.printAvailableAssetsMenu();

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice, value is not a number. Please provide a value from the list of options");
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable assets: ");
                    library.listAvailableAssets();

                    Helpers.pause();
                    break;
                case 2:
                    System.out.println("\nAvailable books: ");
                    library.listAvailableBooks();


                    Helpers.pause();
                    break;
                case 3:
                    System.out.println("\nAvailable audio books: ");
                    library.listAvailableAudioBooks();

                    Helpers.pause();
                    break;
                case 4:
                    System.out.println("\nAvailable theses: ");
                    library.listAvailableThesis();

                    Helpers.pause();
                    break;
                case 5:
                    System.out.println("\nAvailable CDs: ");
                    library.listAvailableCds();

                    Helpers.pause();
                    break;
                case 6:
                    System.out.println("\nBorrowed Assets: ");
                    library.listBorrowedAssets();

                    Helpers.pause();
                    break;
                case 0:
                    System.out.println("\nReturning previous menu... ");
                    break;
                default:
                    System.out.println("Invalid selection, please try again.\n");
                    choice = -1;
            }
        }
    }

    /**
     * Function used to select the desired users information to be listed calling the appropriate library system
     * functions associated with listing all users, or the borrowed assets of a user selected via id
     */
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
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\nLibrary users: ");

                    library.listUsers();

                    Helpers.pause();
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

                    Helpers.pause();
                    break;
                case 0:
                    System.out.println("\nReturning previous menu... ");
                    break;
                default:
                    System.out.println("Invalid selection, please try again.\n");
                    choice = -1;
            }
        }
    }

    /**
     * Function used to select the desired author information to be listed calling the appropriate library system
     * functions associated with listing all authors, or the authored assets of an author selected via id
     */
    private void listAuthors() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nPlease select from one of the following options: ");
            System.out.println("1. List all authors");
            System.out.println("2. List authors assets");

            System.out.println("\n0. Return to previous menu\n");

            System.out.print("Enter your selection: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice, value is not a number. Please provide a value from the list of options");
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\nLibrary authors: ");

                    library.listAuthors();

                    Helpers.pause();
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

                    Helpers.pause();
                    break;
                case 0:
                    System.out.println("\nReturning previous menu... ");
                    break;
                default:
                    System.out.println("Invalid selection, please try again.\n");
                    choice = -1;
            }
        }
    }

    /**
     * Function used to select the desired loans information to be listed calling the appropriate library system
     * functions associated with listing all loans, or all overdue loans via id
     */
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
                    System.out.println("\nLibrary loans: ");
                    library.listLoans();

                    Helpers.pause();
                    break;
                case 2:
                    System.out.println("\nOverdue Library loans: ");
                    library.listOverdueLoans();

                    Helpers.pause();
                    break;
                case 0:
                    System.out.println("Returning previous menu... ");
                    break;
                default:
                    System.out.println("Invalid selection, please try again.\n");
                    choice = -1;
            }
        }
    }

    //--------------------------//
    // End of listing functions //
    //--------------------------//

    //---------------------------------//
    // Library item creation functions //
    //---------------------------------//

    /**
     * Function used to select the desired library item to be added to the library system by calling the
     * appropriate user interface functions associated with adding a book, audiobook, thesis, or CD.
     */
    private void addLibraryItem() {
        int choice = -1;

        do {
            DisplayService.printAddAssetMenu();

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
                    System.out.println("\nYou have chosen to add a book...");
                    addBook();
                    break;
                case 2:
                    System.out.println("\nYou have chosen to add an audio book...");
                    addAudioBook();
                    break;
                case 3:
                    System.out.println("\nYou have chosen to add a thesis...");
                    addTheses();
                    break;
                case 4:
                    System.out.println("\nYou have chosen to add a CD...");
                    addCD();
                    break;
                default:
                    System.out.println("Invalid selection, please try again.");
                    choice = -1;
            }
        } while (choice != 0);
    }

    /**
     * Function used to add a book to the library system
     */
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

    /**
     * Function used to add a CD to the library system
     */
    private void addCD() {
        String title, producer, director;
        int playtime;

        try {
            System.out.print("Please enter the title of the CD: ");
            title = sc.nextLine();

            System.out.print("Please enter the producer of the cd: ");
            producer = sc.nextLine();

            System.out.print("Please enter the director of the cd: ");
            director = sc.nextLine();

            System.out.print("Please enter the length of the CD in minutes: ");
            playtime = Integer.parseInt(sc.nextLine());

            System.out.println("Adding this cd to our catalog...");
            library.addCD(title, producer, director, playtime);
        } catch (NumberFormatException e) {
            System.out.println("Failed to create CD. Playtime value was non-number");
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Failed to create CD due to unexpected error. Please try again.");
        }
    }

    /**
     * Function used to add an audiobook to the library system
     */
    private void addAudioBook() {
        try {
            System.out.print("Please enter the title of the audio book: ");
            String title = sc.nextLine();

            System.out.print("Please enter the ID of the audio books author: ");
            int authorID = Integer.parseInt(sc.nextLine());

            System.out.print("Please enter the duration of the audio book: ");
            int duration = Integer.parseInt(sc.nextLine());

            System.out.print("Please enter th ISBN of this audio book: ");
            String isbn = sc.nextLine();

            System.out.println("Adding the audio book to our catalog...");
            library.addAudioBook(title, authorID, isbn, duration);
        } catch (NumberFormatException e) {
            System.out.println("Failed to create CD. Author ID or duration value provided was non-number");
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Failed to create audio book due to unexpected error. Please try again.");
        }
    }

    /**
     * Function used to add a thesis to the library system
     */
    private void addTheses() {
        try {
            System.out.print("Please enter the title of the thesis: ");
            String title = sc.nextLine();

            System.out.print("Please enter the author of the thesis: ");
            int authorID = Integer.parseInt(sc.nextLine());

            System.out.print("Please enter the topic of the thesis: ");
            String topic = sc.nextLine();

            System.out.print("Please enter the abstract of the thesis: ");
            String thesisAbstract = sc.nextLine();

            LocalDate date = null;
            String input = null;
            boolean validDate = false;

            System.out.println("Please enter the publish date for the thesis in the format yyyy-mm-dd:");

            while (!validDate) {
                input = sc.nextLine();
                validDate = Helpers.validateDate(input);
            }

            date = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);

            System.out.println("Adding thesis to our catalog...");
            library.addThesis(title, authorID, topic, thesisAbstract, date);
        } catch (NumberFormatException e) {
            System.out.println("Failed to create thesis. Author ID value provided was non-number");
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Failed to create thesis due to unexpected error. Please try again.");
        }
    }

    /**
     * Function used to add an author to the library system
     */
    private void addAuthor() {
        try {
            System.out.print("Please enter the name of the author: ");
            String name = sc.nextLine();

            library.addAuthor(name);
            System.out.println("Added " + name + " to list of authors in library system.");
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Failed to create author due to unexpected error. Please try again.");
        }
    }

    /**
     * Function used to add a user to the library system
     */
    private void addUser() {
        try {
            System.out.print("Please enter the user's full name: ");
            String name = sc.nextLine();

            library.addUser(name);
            System.out.println("Added " + name + " to list of users in library system.");
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Failed to create user due to unexpected error. Please try again.");
        }
    }

    //----------------------------------------//
    // End of library item creation functions //
    //----------------------------------------//

    //------------------------------------//
    // Loan creation and return functions //
    //------------------------------------//

    /**
     * Function used to create a library system loan
     */
    private void borrowAsset() {
        try {
            System.out.print("Please enter the user's ID: ");
            int userID = Integer.parseInt(sc.nextLine());

            System.out.print("Please enter the asset's ID: ");
            int assetID = Integer.parseInt(sc.nextLine());

            library.createLoan(assetID, userID);
        } catch (NumberFormatException e) {
            System.out.println("Failed to create loan. User's ID must be a number!");
        }
    }

    /**
     * Function used to return a library system loan
     */
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



    /**
     * Main menu function that runs the main menu loop, allowing the user to select desired options from the list and calling
     * the appropriate user interface function for Catalogue information (library item lists), add asset to library system,
     * add author to library system, add user to library system, create a loan on library system and return loan on library system.
     * <p>
     * Also calls the library systems save function for saving library items state out to CSV file.
     */
    public void run() {
        // Main Loop/Menu
        System.out.println("\nWelcome to the Library Management System.");

        int choice = -1;

        while (choice != 0) {
            DisplayService.printMainMenu();
            choice = Helpers.validateInteger(choice);


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
                    choice = -1;
            }
        }

        library.save();
    }
}
