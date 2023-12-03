package org.CA2.util;

import org.CA2.models.Asset;
import org.CA2.models.Author;
import org.CA2.models.LibraryUser;
import org.CA2.models.Loan;

public class BinarySearch {
    /**
     * Static function that performs a binary search on an array of asset objects by ID
     * @param arr Asset object array to be searched
     * @param id The id to search for the asset object by
     * @return The object that matches the id provided or null if none is found
     */
    public static Asset assetSearch(Asset[] arr, int id) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;

            if (arr[mid].getID() == id){
                return arr[mid];
            }
            else if (arr[mid].getID() < id){
                left = mid + 1;
            }
            else {
                right = mid -1;
            }
        }

        return null;
    }

    /**
     * Static function that performs a binary search on an array of author objects by ID
     * @param arr Author object array to be searched
     * @param id The id to search for the author object by
     * @return The object that matches the id provided or null if none is found
     */
    public static Author authorSearch(Author[] arr, int id) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;

            if (arr[mid].getID() == id){
                return arr[mid];
            }
            else if (arr[mid].getID() < id){
                left = mid + 1;
            }
            else {
                right = mid -1;
            }
        }

        return null;
    }

    /**
     * Static function that performs a binary search on an array of library user objects by ID
     * @param arr LibraryUser object array to be searched
     * @param id The id to search for the library user object by
     * @return The object that matches the id provided or null if none is found
     */
    public static LibraryUser userSearch(LibraryUser[] arr, int id) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;

            if (arr[mid].getID() == id){
                return arr[mid];
            }
            else if (arr[mid].getID() < id){
                left = mid + 1;
            }
            else {
                right = mid -1;
            }
        }

        return null;
    }

    /**
     * Static function that performs a binary search on an array of loan objects by ID
     * @param arr Loan object array to be searched
     * @param id The id to search for the loan object by
     * @return The object that matches the id provided or null if none is found
     */
    public static Loan loanSearch(Loan[] arr, int id) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;

            if (arr[mid].getID() == id){
                return arr[mid];
            }
            else if (arr[mid].getID() < id){
                left = mid + 1;
            }
            else {
                right = mid -1;
            }
        }

        return null;
    }
}
