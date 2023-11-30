import org.CA2.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVWriteTest {
    static LibrarySystem lb;
    static String[] CSVPaths;

    @BeforeClass
    public static void testSetup() throws AssetException {
        CSVPaths = new String[] {
                "src/test/resources/write_books.csv",
                "src/test/resources/write_audiobooks.csv",
                "src/test/resources/write_cds.csv",
                "src/test/resources/write_theses.csv",
                "src/test/resources/write_users.csv",
                "src/test/resources/write_authors.csv",
                "src/test/resources/write_loans.csv",
        };

        lb = new LibrarySystem(CSVPaths);

        lb.initializeIDCounters();

        LibrarySystem.addAuthor("JRR. Tolkien");
        LibrarySystem.addAuthor("Herman Melville");
        LibrarySystem.addAuthor("John Doe");

        LibrarySystem.addBook("The Lord of the Rings", "JRR. Tolkien", "9780544003415");
        LibrarySystem.addBook("The Hobbit", "JRR. Tolkien","9781566192637");
        LibrarySystem.addAudioBook("Moby Dick", "Herman Melville","9781566192637", 340);
        LibrarySystem.addCD("Hunky Dory", "Ken Scott", "David Bowie", 41);
        LibrarySystem.addThesis("Where are we now", "John Doe", "Philosophy", "This is an abstract", LocalDate.parse("2023-08-08", DateTimeFormatter.ISO_LOCAL_DATE));

        LibrarySystem.addUser("John Doe");

        LibraryUser user = LibrarySystem.getUser("John Doe");

        user.addBorrowedAsset(new CD("Hunky Dory", "Ken Scott", "David Bowie", 41, true));

        LibrarySystem.createLoan("The Hobbit", "John Doe");

        lb.save();
    }

    @Test
    public void testCSVWriteBooks() throws IOException {
        ArrayList<String> contents = getContents("src/test/resources/write_books.csv");

        Assert.assertEquals(contents.get(0), "title,author,ISBN,availability");
        Assert.assertEquals(contents.get(1), "The Hobbit,JRR. Tolkien,9781566192637,false");
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
        Assert.assertEquals(contents.get(1), "1,John Doe,Hunky Dory|The Hobbit");
    }

    @Test
    public void testCSVWriteAuthors() throws IOException {
        ArrayList<String> contents = getContents("src/test/resources/write_authors.csv");

        Assert.assertEquals(contents.get(0), "name,authored");
        Assert.assertEquals(contents.get(1), "Herman Melville,Moby Dick");
        Assert.assertEquals(contents.get(2), "JRR. Tolkien,The Lord of the Rings|The Hobbit");
        Assert.assertEquals(contents.get(3), "John Doe,Where are we now");
    }

    @Test
    public void testCSVWriteLoans() throws IOException {
        ArrayList<String> loanContents = getContents("src/test/resources/write_loans.csv");

        Assert.assertEquals(loanContents.get(0), "id,user,borrowed,dateBorrowed,dateReturned,returned");
        Assert.assertEquals(loanContents.get(1), "1,John Doe,The Hobbit,2023-11-30,2023-12-14,false");

        ArrayList<String> bookContents = getContents("src/test/resources/write_books.csv");
        Assert.assertTrue(bookContents.get(1).contains("false"));
    }

    private ArrayList<String> getContents(String path) throws IOException {
        ArrayList<String> contents = new ArrayList<>();

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
