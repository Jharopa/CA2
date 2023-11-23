import org.CA2.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.text.ParseException;

public class CSVReadTest {
    static LibrarySystem lb;
    static String[] CSVPaths;

    @BeforeClass
    public static void testSetup() throws AssetException, ParseException {
        CSVPaths = new String[] {
                "src/test/resources/read_books.csv",
                "src/test/resources/read_audiobooks.csv",
                "src/test/resources/read_cds.csv",
                "src/test/resources/read_theses.csv",
                "src/test/resources/read_users.csv",
                "src/test/resources/read_authors.csv",
        };

        lb = new LibrarySystem(CSVPaths);

        lb.load();
    }

    @Test
    public void testCSVReadBooks() {
        Asset lotr = lb.getAsset("The Lord of the Rings");
        Assert.assertNotNull(lotr);
    }

    @Test
    public void testCSVReadAudiobooks() {
        Asset md = lb.getAsset("Moby Dick");
        Assert.assertNotNull(md);
    }

    @Test
    public void testCSVReadCDs() {
        Asset hd = lb.getAsset("Hunky Dory");
        Assert.assertNotNull(hd);
    }

    @Test
    public void testCSVReadTheses() {
        Asset wawn = lb.getAsset("Where are we now");
        Assert.assertNotNull(wawn);
    }

    @Test
    public void testCSVReadUsers() {
        LibraryUser lu = lb.getUser("John Doe");

        Assert.assertNotNull(lu);

        Assert.assertNotNull(lu.getBorrowedAssets().get(0));
        Assert.assertNotNull(lu.getBorrowedAssets().get(1));
    }

    @Test
    public void testCSVReadAuthors() {
        Author a = lb.getAuthor("JRR. Tolkien");

        Assert.assertNotNull(a);

        Assert.assertNotNull(a.getAuthoredAssets().get(0));
    }
}
