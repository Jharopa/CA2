package org.CA2;

public class BinarySearch {
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
