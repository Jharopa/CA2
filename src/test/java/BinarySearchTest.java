import org.CA2.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BinarySearchTest {
    private static Asset[] arr;

    @BeforeClass
    public static void testSetup() throws AssetException {
        arr = new Asset[] {
                new CD(1, "Hunky Dory", "Ken Scott", "David Bowie", 41, true),
                new AudioBook(2, "Moby Dick", "Herman Melville","9781566192637", 340, true),
                new Book(3, "The Lord of the Rings", "JRR. Tolkien", "9780544003415", true),
                new Thesis(4, "Where are we now", "John Doe", "Philosophy", "This is an abstract", LocalDate.parse("2023-08-08", DateTimeFormatter.ISO_LOCAL_DATE), true)
        };
    }

    @Test
    public void testBinarySearch() {
        Asset hd = BinarySearch.assetSearch(arr, 1);
        Asset mb = BinarySearch.assetSearch(arr, 2);
        Asset lotr = BinarySearch.assetSearch(arr, 3);
        Asset wawn = BinarySearch.assetSearch(arr, 4);

        Assert.assertTrue(hd instanceof CD);
        Assert.assertTrue(mb instanceof AudioBook);
        Assert.assertTrue(lotr instanceof Book);
        Assert.assertTrue(wawn instanceof Thesis);

        Assert.assertEquals(hd.getTitle(),"Hunky Dory");
        Assert.assertEquals(mb.getTitle(),"Moby Dick");
        Assert.assertEquals(lotr.getTitle(),"The Lord of the Rings");
        Assert.assertEquals(wawn.getTitle(),"Where are we now");

        Asset nonExistent = BinarySearch.assetSearch(arr, 9999);

        Assert.assertNull(nonExistent);
    }
}
