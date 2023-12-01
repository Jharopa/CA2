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

        lb.addAuthor("JRR. Tolkien");
        lb.addAuthor("Herman Melville");
        lb.addAuthor("John Doe");

        lb.addBook("The Lord of the Rings", 1, "9780544003415");
        lb.addBook("The Hobbit", 1,"9781566192637");
        lb.addAudioBook("Moby Dick", 2,"9781566192637", 340);
        lb.addCD("Hunky Dory", "Ken Scott", "David Bowie", 41);
        lb.addThesis("Where are we now", 3, "Philosophy", "This is an abstract", LocalDate.parse("2023-08-08", DateTimeFormatter.ISO_LOCAL_DATE));

        lb.addUser("John Doe");

        LibraryUser user = lb.getUser(1);

        user.addBorrowedAsset(new CD("Hunky Dory", "Ken Scott", "David Bowie", 41, true));

        lb.createLoan("The Hobbit", 1);

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
        Assert.assertEquals(contents.get(1), "1,John Doe,Hunky Dory|The Hobbit|");
    }

    @Test
    public void testCSVWriteAuthors() throws IOException {
        ArrayList<String> contents = getContents("src/test/resources/write_authors.csv");

        Assert.assertEquals(contents.get(0), "id,name,authored");
        Assert.assertEquals(contents.get(1), "1,JRR. Tolkien,The Lord of the Rings|The Hobbit|");
        Assert.assertEquals(contents.get(2), "2,Herman Melville,Moby Dick|");
        Assert.assertEquals(contents.get(3), "3,John Doe,Where are we now|");
    }

    @Test
    public void testCSVWriteLoans() throws IOException {
        ArrayList<String> loanContents = getContents("src/test/resources/write_loans.csv");

        Assert.assertEquals(loanContents.get(0), "id,user,borrowed,dateBorrowed,dateReturned,returned");
        Assert.assertEquals(loanContents.get(1), String.format("1,1,The Hobbit,%s,%s,false", LocalDate.now(), LocalDate.now().plusDays(14)));

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
