package org.CA2;

import java.util.Date;
import java.util.List;

public class Loan {

    private String borrower;
    private List<Asset> borrowedAsset;
    private Date borrowDate;
    private Date returnDate;

    public Loan(String borrower, List<Asset> borrowedAsset, Date borrowDate, Date returnDate) {
        this.borrower = borrower;
        this.borrowedAsset = borrowedAsset;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
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

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
