package org.CA2;

import java.util.Date;
import java.util.List;

public class Loan {

    private LibraryUser borrower;
    private List<Asset> borrowedAsset;
    private Date borrowDate;
    private Date returnDate;

    public Loan(LibraryUser borrower, List<Asset> borrowedAsset) {
        this.borrower = borrower;
        this.borrowedAsset = borrowedAsset;
        this.borrowDate = new Date();
        this.returnDate = null;
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

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) throws AssetException {
        if (returnDate.after(new Date())) {
            throw new AssetException("Loan returnDate can not be in the future");
        }

        this.returnDate = returnDate;
    }
}
