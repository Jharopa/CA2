package org.CA2;

import java.util.List;

public class Author {

    private String name;
    private List<Book> authoredBooks;

    public Author(String name, List<Book> authoredBooks) {
        this.name = name;
        this.authoredBooks = authoredBooks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getAuthoredBooks() {
        return authoredBooks;
    }

    public void setAuthoredBooks(List<Book> authoredBooks) {
        this.authoredBooks = authoredBooks;
    }
}
