package org.CA2;

public class Book extends Asset implements Printable {
    /**
     * String representing the name of the author that wrote the book
     */
    protected String author;

    /**
     * String representing the ISBN of hte book
     */
    protected String ISBN;

    /**
     * Class constructor specifying the book object's id, title, author, ISBN, and availability
     * @param id Initializes the book object's id
     * @param title Initializes the book object's title
     * @param author Initializes the book object's author
     * @param ISBN Initializes the book object's ISBN
     * @param availability Initializes the book object's availability
     * @throws AssetException When the isbn specified is not of character length 13
     */
    public Book(int id, String title, String author, String ISBN, boolean availability) throws AssetException {
        super(id, title, availability);

        if (ISBN.length() != 13) {
            throw new AssetException("Books ISBN character length must be equal to 13");
        }

        this.author = author;
        this.ISBN = ISBN;
    }

    /**
     * Accessor function for the author attribute
     * @return The object's author attribute
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Mutator function for the author attribute
     * @param author The new value of the book object's author attribute
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Accessor function for the ISBN attribute
     * @return The object's ISBN attribute
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Mutator function for the ISBN attribute
     * @param ISBN The new value of the book object's ISBN attribute
     * @throws AssetException When the isbn specified is not of character length 13
     */
    public void setISBN(String ISBN) throws AssetException {
        if (ISBN.length() != 13) {
            throw new AssetException("Books ISBN character length must be equal to 13");
        }

        this.ISBN = ISBN;
    }

    /**
     * Book classes overridden toString function
     * @return A string representation of the book object
     */
    @Override
    public String toString() {
        return super.toString() + String.format("%-20s%-17s%-12s", author, ISBN, availability ? "available" : "unavailable");
    }

    /**
     * Implementation of the Printable interfaces abstract function print, used for printing
     * out the string representation of the object
     */
    @Override
    public void print() {
        System.out.printf("%-5s%-24s%-20s%-17s%-12s\n", "ID", "Title", "Author", "ISBN", "Status");
        System.out.println(this);
    }
}
