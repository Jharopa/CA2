import org.CA2.models.*;
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
        Book lotr = (Book) lb.getAsset(1);

        Assert.assertNotNull(lotr);
        Assert.assertEquals(1, lotr.getID());
        Assert.assertEquals("The Lord of the Rings", lotr.getTitle());
        Assert.assertEquals("JRR. Tolkien", lotr.getAuthor());
        Assert.assertEquals("9780544003415", lotr.getISBN());
        Assert.assertFalse(lotr.isAvailability());
    }

    @Test
    public void testCSVReadAudiobooks() {
        AudioBook md = (AudioBook) lb.getAsset(2);

        Assert.assertNotNull(md);
        Assert.assertEquals(2, md.getID());
        Assert.assertEquals("Moby Dick", md.getTitle());
        Assert.assertEquals("Herman Melville", md.getAuthor());
        Assert.assertEquals("9781566192637", md.getISBN());
        Assert.assertEquals(340, md.getDuration());
        Assert.assertTrue(md.isAvailability());
    }

    @Test
    public void testCSVReadCDs() {
        CD hd = (CD) lb.getAsset(3);

        Assert.assertNotNull(hd);
        Assert.assertEquals(3, hd.getID());
        Assert.assertEquals("Hunky Dory", hd.getTitle());
        Assert.assertEquals("Ken Scott", hd.getProducer());
        Assert.assertEquals("David Bowie", hd.getPerformer());
        Assert.assertEquals(41, hd.getPlaytime());
        Assert.assertTrue(hd.isAvailability());
    }

    @Test
    public void testCSVReadTheses() {
        Thesis wawn = (Thesis) lb.getAsset(4);

        Assert.assertNotNull(wawn);
        Assert.assertEquals(4, wawn.getID());
        Assert.assertEquals("Where are we now", wawn.getTitle());
        Assert.assertEquals("John Doe", wawn.getAuthor());
        Assert.assertEquals("Philosophy", wawn.getTopic());
        Assert.assertEquals("This is an abstract", wawn.getAbstract());
        Assert.assertEquals(LocalDate.parse("2023-09-26", DateTimeFormatter.ISO_LOCAL_DATE), wawn.getDatePublished());
    }

    @Test
    public void testCSVReadUsers() {
        LibraryUser lu = lb.getUser(1);

        Assert.assertNotNull(lu);
        Assert.assertEquals(1, lu.getID());
        Assert.assertEquals("John Doe", lu.getName());

        CD hd = (CD) lu.getBorrowedAssets().get(0);
        AudioBook md = (AudioBook) lu.getBorrowedAssets().get(1);

        Assert.assertNotNull(lu.getBorrowedAssets().get(0));
        Assert.assertNotNull(hd);
        Assert.assertEquals(3, hd.getID());
        Assert.assertEquals("Hunky Dory", hd.getTitle());
        Assert.assertEquals("Ken Scott", hd.getProducer());
        Assert.assertEquals("David Bowie", hd.getPerformer());
        Assert.assertEquals(41, hd.getPlaytime());
        Assert.assertTrue(hd.isAvailability());

        Assert.assertNotNull(lu.getBorrowedAssets().get(1));
        Assert.assertNotNull(md);
        Assert.assertEquals(2, md.getID());
        Assert.assertEquals("Moby Dick", md.getTitle());
        Assert.assertEquals("Herman Melville", md.getAuthor());
        Assert.assertEquals("9781566192637", md.getISBN());
        Assert.assertEquals(340, md.getDuration());
        Assert.assertTrue(md.isAvailability());
    }

    @Test
    public void testCSVReadAuthors() {
        Author a = lb.getAuthor(1);

        Assert.assertNotNull(a);
        Assert.assertEquals(1, a.getID());
        Assert.assertEquals("JRR. Tolkien", a.getName());

        Book lotr = (Book) a.getAuthoredAssets().get(0);

        Assert.assertNotNull(lotr);
        Assert.assertEquals(1, lotr.getID());
        Assert.assertEquals("The Lord of the Rings", lotr.getTitle());
        Assert.assertEquals("JRR. Tolkien", lotr.getAuthor());
        Assert.assertEquals("9780544003415", lotr.getISBN());
        Assert.assertFalse(lotr.isAvailability());
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
