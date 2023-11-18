package org.CA2;

public class Book extends Asset {
    private Author author;
    private String ISBN;

    public Book(String title, Author author, String ISBN, boolean availability) throws AssetException {
        super(title, availability);
        this.author = author;
        this.ISBN = ISBN;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
