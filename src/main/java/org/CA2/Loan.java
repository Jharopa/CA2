package org.CA2;

import java.time.LocalDate;

public class Loan implements Comparable<Loan>, Printable {
    private final int id;
    private LibraryUser borrower;
    private Asset borrowedAsset;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;

    public Loan(int id, LibraryUser borrower, Asset borrowedAsset, LocalDate borrowDate, LocalDate returnDate, boolean returned) {
        this.id = id;
        this.borrower = borrower;
        this.borrowedAsset = borrowedAsset;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.returned = returned;
    }

    public int getID() {
        return id;
    }

    public LibraryUser getBorrower() {
        return borrower;
    }

    public void setBorrower(LibraryUser borrower) {
        this.borrower = borrower;
    }

    public Asset getBorrowedAsset() {
        return borrowedAsset;
    }

    public void setBorrowedAsset(Asset borrowedAsset) {
        this.borrowedAsset = borrowedAsset;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) throws AssetException {
        if (returnDate.isAfter(LocalDate.now())) {
            throw new AssetException("Loan returnDate can not be in the future");
        }

        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void returnLoan() {
        returned = true;
    }

    public boolean isOverdue() {
        return (!isReturned() && LocalDate.now().isAfter(returnDate));
    }

    @Override
    public int compareTo(Loan o) {
        return Integer.compare(this.getID(), o.getID());
    }

    @Override
    public String toString() {
        return String.format(
                "%-5s%-12s%-18s%-15s%-15s%-10s",
                id, borrower.getName(), borrowedAsset.getTitle(),
                borrowDate.toString(), returnDate.toString(),
                returned ? "returned" : "unreturned"
        );
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
