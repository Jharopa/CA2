package org.CA2.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Helpers {
    private static final Scanner sc = new Scanner(System.in);

    //------------------//
    // Helper functions //
    //------------------//


    /**
     * Helper function used to validate an integer provided by system user is actually an integer
     *
     * @param choice The users inout (should be type int)
     * @return the entered value if it can be parsed to an int, returns -1 to repeat if it fails
     */
    public static int validateInteger(int choice) {
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            choice = -1;
        }
        return choice;
    }



    /**
     * Helper function used to validate a date string provided by system user is in the correct format yyyy-MM-dd
     *
     * @param input The date string
     * @return true if the date string is in the correct format, false otherwise.
     */
    public static boolean validateDate(String input) {
        try {
            LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            System.out.print("Invalid date format. Please enter the date as yyyy-MM-dd: ");
            return false;
        }
    }

    /**
     * Helper function used to create prompt on screen to pause output from continuing until the enter key is pressed.
     */
    public static void pause() {
        System.out.print("\nPress enter to continue...");
        sc.nextLine();
    }

    //-------------------------//
    // End of helper functions //
    //-------------------------//
}
