package org.CA2;

import java.time.LocalDate;

public class Loan implements Comparable<Loan>, Printable {
    /**
     * Integer representing the loan's id
     */
    private final int id;

    /**
     * LibraryUser representing the loan's borrower
     */
    private LibraryUser borrower;

    /**
     * Asset representing the loan's borrowed asset
     */
    private Asset borrowedAsset;

    /**
     * LocalDate representing the date the asset was borrowed
     */
    private LocalDate borrowDate;

    /**
     * LocalDate representing the date the asset should be returned by
     */
    private LocalDate returnDate;

    /**
     * boolean representing whether the borrowed asset has been returned or not
     */
    private boolean returned;

    /**
     * Class constructor specifying the loan object's id, borrower, borrowed asset, borrow date, return date, and returned
     * @param id Initializes the loan object's id
     * @param borrower Initializes the loan object's borrower
     * @param borrowedAsset Initializes the loan object's borrowedAsset
     * @param borrowDate Initializes the loan object's borrowDate
     * @param returnDate Initializes the loan object's returnDate
     * @param returned Initializes the loan object's returned
     */
    public Loan(int id, LibraryUser borrower, Asset borrowedAsset, LocalDate borrowDate, LocalDate returnDate, boolean returned) {
        this.id = id;
        this.borrower = borrower;
        this.borrowedAsset = borrowedAsset;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.returned = returned;
    }

    /**
     * Accessor function for the id attribute
     * @return The object's id attribute
     */
    public int getID() {
        return id;
    }

    /**
     * Accessor function for the borrower attribute
     * @return The object's id borrower
     */
    public LibraryUser getBorrower() {
        return borrower;
    }

    /**
     * Accessor function for the borrowedAsset attribute
     * @return The object's id borrowedAsset
     */
    public Asset getBorrowedAsset() {
        return borrowedAsset;
    }

    /**
     * Accessor function for the borrowDate attribute
     * @return The object's id borrowDate
     */
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    /**
     * Accessor function for the returnDate attribute
     * @return The object's id returnDate
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Accessor function for the returned attribute
     * @return The object's id returned attribute
     */
    public boolean isReturned() {
        return returned;
    }

    /**
     * Mutator function sets the returned attribute to true
     */
    public void returnLoan() {
        returned = true;
    }

    /**
     * Function used to check if an asset is overdue
     * @return true if the asset is overdue otherwise false
     */
    public boolean isOverdue() {
        return (!isReturned() && LocalDate.now().isAfter(returnDate));
    }

    /**
     * Implementation of the Comparable interface's abstract function compareTo
     * @param o the object to be compared.
     * @return -1, 0, or 1 depending on the result of the Integer comparison between object's id attribute
     */
    @Override
    public int compareTo(Loan o) {
        return Integer.compare(this.getID(), o.getID());
    }

    /**
     * Loan class' overridden toString function
     * @return A string representation of the library user object
     */
    @Override
    public String toString() {
        return String.format(
                "%-5s%-12s%-18s%-15s%-15s%-10s",
                id, borrower.getName(), borrowedAsset.getTitle(),
                borrowDate.toString(), returnDate.toString(),
                returned ? "returned" : "unreturned"
        );
    }

    /**
     * Implementation of the Printable interfaces abstract function print, used for printing
     * out the string representation of the object
     */
    @Override
    public void print() {
        System.out.println(this);
    }
}
