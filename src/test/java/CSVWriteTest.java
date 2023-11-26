import org.CA2.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVWriteTest {
    static LibrarySystem lb;
    static String[] CSVPaths;

    @BeforeClass
    public static void testSetup() throws AssetException, ParseException {
        CSVPaths = new String[] {
                "src/test/resources/write_books.csv",
                "src/test/resources/write_audiobooks.csv",
                "src/test/resources/write_cds.csv",
                "src/test/resources/write_theses.csv",
                "src/test/resources/write_users.csv",
                "src/test/resources/write_authors.csv",
        };

        lb = new LibrarySystem(CSVPaths);

        LinkedList<Asset> authorsAssets = new LinkedList<>();
        LinkedList<Asset> usersBorrowedAssets = new LinkedList<>();

        lb.addBook("The Lord of the Rings", "JRR. Tolkien", "9780544003415");
        lb.addAudioBook("Moby Dick", "Herman Melville","9781566192637", 340);
        lb.addCD("Hunky Dory", "Ken Scott", "David Bowie", 41);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        lb.addThesis("Where are we now", "John Doe", "Philosophy", "This is an abstract", LocalDate.parse("2023-08-08", DateTimeFormatter.ISO_LOCAL_DATE));

        authorsAssets.add(new Book("The Lord of the Rings", "JRR. Tolkien", "9780544003415", true));
        authorsAssets.add(new AudioBook("The Hobbit", "JRR. Tolkien","9781566192637", 340, true));
        lb.addAuthor("JRR. Tolkien", authorsAssets);

        usersBorrowedAssets.add(new CD("Hunky Dory", "Ken Scott", "David Bowie", 41, true));
        usersBorrowedAssets.add(new AudioBook("The Hobbit", "JRR. Tolkien","9781566192637", 340, true));
        lb.addUser(1,"John Doe", usersBorrowedAssets);

        lb.save();
    }

    @Test
    public void testCSVWriteBooks() throws IOException {
        ArrayList<String> contents = getContents("src/test/resources/write_books.csv");

        Assert.assertEquals(contents.get(0), "title,author,ISBN,availability");
        Assert.assertEquals(contents.get(1), "The Lord of the Rings,JRR. Tolkien,9780544003415,true");
    }

    @Test
    public void testCSVWriteAudiobooks() throws IOException {
        ArrayList<String> contents = getContents("src/test/resources/write_audiobooks.csv");

        Assert.assertEquals(contents.get(0), "title,author,ISBN,duration,availability");
        Assert.assertEquals(contents.get(1), "Moby Dick,Herman Melville,9781566192637,340,true");
    }

    @Test
    public void testCSVWriteCDs() throws IOException {
        ArrayList<String> contents = getContents("src/test/resources/write_cds.csv");

        Assert.assertEquals(contents.get(0), "title,producer,director,playtime,availability");
        Assert.assertEquals(contents.get(1), "Hunky Dory,Ken Scott,David Bowie,41,true");
    }

    @Test
    public void testCSVWriteTheses() throws IOException {
        ArrayList<String> contents = getContents("src/test/resources/write_theses.csv");

        Assert.assertEquals(contents.get(0), "title,author,topic,Abstract,datePublished,availability");
        Assert.assertEquals(contents.get(1), "Where are we now,John Doe,Philosophy,This is an abstract,2023-08-08,true");
    }

    @Test
    public void testCSVWriteUsers() throws IOException {
        ArrayList<String> contents = getContents("src/test/resources/write_users.csv");

        Assert.assertEquals(contents.get(0), "id,name,borrowed");
        Assert.assertEquals(contents.get(1), "1,John Doe,Hunky Dory|The Hobbit|");
    }

    @Test
    public void testCSVWriteAuthors() throws IOException {
        ArrayList<String> contents = getContents("src/test/resources/write_authors.csv");

        Assert.assertEquals(contents.get(0), "name,authored");
        Assert.assertEquals(contents.get(1), "JRR. Tolkien,The Lord of the Rings|The Hobbit|");
    }

    private ArrayList<String> getContents(String path) throws IOException {
        ArrayList<String> contents = new ArrayList<String>();

        BufferedReader bf = new BufferedReader(
                new FileReader(path));

        String line = bf.readLine();

        while (line != null) {
            contents.add(line);
            line = bf.readLine();
        }

        return contents;
    }
}
