package org.CA2;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Loan {

    private LibraryUser borrower;
    private List<Asset> borrowedAsset;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Loan(LibraryUser borrower, List<Asset> borrowedAsset, LocalDate borrowDate, LocalDate returnDate) {
        this.borrower = borrower;
        this.borrowedAsset = borrowedAsset;
        this.borrowDate = returnDate;
        this.returnDate = borrowDate;
    }

    public LibraryUser getBorrower() {
        return borrower;
    }

    public void setBorrower(LibraryUser borrower) {
        this.borrower = borrower;
    }

    public List<Asset> getBorrowedAsset() {
        return borrowedAsset;
    }

    public void setBorrowedAsset(List<Asset> borrowedAsset) {
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
}
