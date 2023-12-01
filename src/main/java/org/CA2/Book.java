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
        return super.toString() + String.format(" %s, %s, %s", author, ISBN, availability ? "available" : "unavailable");
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
