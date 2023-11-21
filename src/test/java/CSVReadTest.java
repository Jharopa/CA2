import org.CA2.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
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
    public void testCSVWriteBooks() {
        Asset lotr = lb.getAsset("The Lord of the Rings");
        Assert.assertNotNull(lotr);
    }

    @Test
    public void testCSVWriteAudiobooks() {
        Asset md = lb.getAsset("Moby Dick");
        Assert.assertNotNull(md);
    }

    @Test
    public void testCSVWriteCDs() {
        Asset hd = lb.getAsset("Hunky Dory");
        Assert.assertNotNull(hd);
    }

    @Test
    public void testCSVWriteTheses() throws IOException {
        Asset wawn = lb.getAsset("Where are we now");
        Assert.assertNotNull(wawn);
    }

    @Test
    public void testCSVWriteUsers() throws IOException {

    }

    @Test
    public void testCSVWriteAuthors() throws IOException {

    }
}
