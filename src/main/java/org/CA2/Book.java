package org.CA2;

public class Book extends Asset implements Printable{
    protected String author;
    protected String ISBN;

    public Book(int id, String title, String author, String ISBN, boolean availability) throws AssetException {
        super(id, title, availability);
        this.author = author;
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("%-20s%-17s%-12s", author, ISBN, availability ? "available" : "unavailable");
    }

    @Override
    public void print() {
        System.out.printf("%-5s%-24s%-20s%-17s%-12s\n", "ID", "Title", "Author", "ISBN", "Status");
        System.out.println(this);
    }
}
