package org.CA2.services;

public class DisplayService {
    public static void printMainMenu() {
        show("\nPlease select one of the following options:");
        show("1. Catalogue information");
        show("2. Add asset to the library catalogue");
        show("3. Add author to catalogue");
        show("4. Register new user");
        show("5. Customer wants to borrow asset");
        show("6. Customer wants to return asset");

        show("\n0. Exit\n");
        System.out.print("\nEnter your choice: ");
    }

    public static void printAddAssetMenu() {
        show("\nWhich type of asset would you like to add?");
        show("1. Book");
        show("2. Audio Book");
        show("3. Thesis");
        show("4. CD");
        show("\n0. Return to main menu");
        System.out.print("\nEnter your choice: ");

    }

    public static void printAvailableAssetsMenu() {

        show("\nPlease select one of the asset types to view: ");
        show("1. List all available assets");
        show("2. List available books");
        show("3. List available audio books");
        show("4. List available thesis");
        show("5. List available CDs");
        show("6. List borrowed assets");

        show("\n0. Return to previous menu\n");

        System.out.print("Enter your selection: ");
    }
    public static void show(String message) {
        System.out.println(message);
    }
}
