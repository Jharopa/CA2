import org.CA2.models.Asset;
import org.CA2.models.Author;
import org.CA2.models.LibraryUser;
import org.CA2.models.Loan;
import org.CA2.services.LibrarySystem;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CSVReadTest {
    static LibrarySystem lb;
    static String[] CSVPaths;

    @BeforeClass
    public static void testSetup() {
        CSVPaths = new String[] {
                "src/test/resources/read_books.csv",
                "src/test/resources/read_audiobooks.csv",
                "src/test/resources/read_cds.csv",
                "src/test/resources/read_theses.csv",
                "src/test/resources/read_users.csv",
                "src/test/resources/read_authors.csv",
                "src/test/resources/read_loans.csv",
        };

        lb = new LibrarySystem(CSVPaths);

        lb.load();
    }

    @Test
    public void testCSVReadBooks() {
        Asset lotr = lb.getAsset(1);
        Assert.assertNotNull(lotr);
    }

    @Test
    public void testCSVReadAudiobooks() {
        Asset md = lb.getAsset(2);
        Assert.assertNotNull(md);
    }

    @Test
    public void testCSVReadCDs() {
        Asset hd = lb.getAsset(3);
        Assert.assertNotNull(hd);
    }

    @Test
    public void testCSVReadTheses() {
        Asset wawn = lb.getAsset(4);
        Assert.assertNotNull(wawn);
    }

    @Test
    public void testCSVReadUsers() {
        LibraryUser lu = lb.getUser(1);

        Assert.assertNotNull(lu);

        Assert.assertNotNull(lu.getBorrowedAssets().get(0));
        Assert.assertNotNull(lu.getBorrowedAssets().get(1));
    }

    @Test
    public void testCSVReadAuthors() {
        Author a = lb.getAuthor(1);

        Assert.assertNotNull(a);

        Assert.assertNotNull(a.getAuthoredAssets().get(0));
    }

    @Test
    public void testCSVReadLoan() {
        Loan loan = lb.getLoan(1);

        Assert.assertEquals(1, loan.getID());
        Assert.assertEquals("John Doe", loan.getBorrower().getName());
        Assert.assertEquals("The Lord of the Rings", loan.getBorrowedAsset().getTitle());
        Assert.assertEquals(LocalDate.parse("2023-11-30", DateTimeFormatter.ISO_LOCAL_DATE), loan.getBorrowDate());
        Assert.assertEquals(LocalDate.parse("2023-12-14", DateTimeFormatter.ISO_LOCAL_DATE), loan.getReturnDate());
        Assert.assertFalse(loan.isReturned());
    }
}
