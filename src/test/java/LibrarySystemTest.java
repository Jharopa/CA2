import org.CA2.models.*;
import org.CA2.services.LibrarySystem;
import org.CA2.services.UserInterface;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LibrarySystemTest {
    static LibrarySystem lb;
    static String[] CSVPaths;

    @BeforeClass
    public static void testSetup() {
        CSVPaths = new String[]{
                "src/test/resources/library_system_test_books.csv",
                "src/test/resources/library_system_test_audiobooks.csv",
                "src/test/resources/library_system_test_cds.csv",
                "src/test/resources/library_system_test_theses.csv",
                "src/test/resources/library_system_test_users.csv",
                "src/test/resources/library_system_test_authors.csv",
                "src/test/resources/library_system_test_loans.csv",
        };

        lb = new LibrarySystem(CSVPaths);
        lb.initializeIDCounters();

        lb.addAuthor("Test author");
        lb.addUser("Test user");

        lb.addBook("Test book", 1, "9781566192637");
        lb.addAudioBook("Test audiobook", 1, "9781566192637", 120);
        lb.addThesis("Test thesis", 1, "Mathematics", "This is an abstract", LocalDate.parse("2023-08-08", DateTimeFormatter.ISO_LOCAL_DATE));
        lb.addCD("Test CD", "Test producer", "Test performer", 120);

        lb.addBook("Test book id", 1, "9781566192637");
        lb.addAudioBook("Test audiobook id", 1, "9781566192637", 120);
        lb.addThesis("Test thesis id", 1, "Mathematics", "This is an abstract", LocalDate.parse("2023-08-08", DateTimeFormatter.ISO_LOCAL_DATE));
        lb.addCD("Test CD id", "Test producer", "Test performer", 120);
    }

    @Test
    public void testAddBook() {
        Asset book = lb.getAsset(1);

        Assert.assertNotNull(book);
        Assert.assertEquals(1, book.getID());
        Assert.assertEquals("Test book", book.getTitle());
    }

    @Test
    public void testAddAudiobook() {
        Asset audioBook = lb.getAsset(2);

        Assert.assertNotNull(audioBook);
        Assert.assertEquals(2, audioBook.getID());
        Assert.assertEquals("Test audiobook", audioBook.getTitle());
    }

    @Test
    public void testAddThesis() {
        Asset thesis = lb.getAsset(3);

        Assert.assertNotNull(thesis);
        Assert.assertEquals(3, thesis.getID());
        Assert.assertEquals("Test thesis", thesis.getTitle());
    }

    @Test
    public void testAddCD() {
        Asset cd = lb.getAsset(4);

        Assert.assertEquals(4, cd.getID());
        Assert.assertEquals("Test CD", cd.getTitle());
    }

    @Test
    public void testAddAuthor() {
        lb.addAuthor("John Doe");

        Author author = lb.getAuthor(2);

        Assert.assertEquals(2, author.getID());
        Assert.assertEquals("John Doe", author.getName());
    }

    @Test
    public void testAddUser() {
        lb.addUser("John Doe");

        LibraryUser user = lb.getUser(2);

        Assert.assertEquals(2, user.getID());
        Assert.assertEquals("John Doe", user.getName());
    }

    @Test
    public void testIDGeneration() {
        Book book = (Book) lb.getAsset(5);
        Assert.assertNotNull(book);
        Assert.assertEquals(5, book.getID());
        Assert.assertEquals("Test book id", book.getTitle());

        AudioBook audioBook = (AudioBook) lb.getAsset(6);
        Assert.assertNotNull(audioBook);
        Assert.assertEquals(6, audioBook.getID());
        Assert.assertEquals("Test audiobook id", audioBook.getTitle());

        Thesis thesis = (Thesis) lb.getAsset(7);
        Assert.assertNotNull(thesis);
        Assert.assertEquals(7, thesis.getID());
        Assert.assertEquals("Test thesis id", thesis.getTitle());

        CD cd = (CD) lb.getAsset(8);
        Assert.assertNotNull(thesis);
        Assert.assertEquals(8, cd.getID());
        Assert.assertEquals("Test CD id", cd.getTitle());
    }

    @Test
    public void testCreateLoan() {
        lb.createLoan(1, 1);
        Loan loan = lb.getLoan(1);

        Assert.assertNotNull(loan);
        Assert.assertEquals("Test user", loan.getBorrower().getName());
        Assert.assertEquals("Test book", loan.getBorrowedAsset().getTitle());

        Assert.assertFalse(loan.getBorrowedAsset().isAvailability());
    }
}
