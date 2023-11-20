import org.CA2.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.Date;

public class BinarySearchTest {
    private static Asset[] arr;

    @BeforeClass
    public static void testSetup() throws AssetException {
        arr = new Asset[] {
                new CD("Hunky Dory", "Ken Scott", "David Bowie", 41, true),
                new AudioBook("Moby Dick", "Herman Melville","9781566192637", 340, true),
                new Book("The Lord of the Rings", "JRR. Tolkien", "9780544003415", true),
                new Thesis("Where are we now", "John Doe", "Philosophy", "This is an abstract", new Date(), true)
        };
    }

    @Test
    public void testBinarySearch() {
        Asset hd = BinarySearch.assetSearch(arr, "Hunky Dory");
        Asset mb = BinarySearch.assetSearch(arr, "Moby Dick");
        Asset lotr = BinarySearch.assetSearch(arr, "The Lord of the Rings");
        Asset wawn = BinarySearch.assetSearch(arr, "Where are we now");

        Assert.assertTrue(hd instanceof CD);
        Assert.assertTrue(mb instanceof AudioBook);
        Assert.assertTrue(lotr instanceof Book);
        Assert.assertTrue(wawn instanceof Thesis);

        Assert.assertEquals(hd.getTitle(),"Hunky Dory");
        Assert.assertEquals(mb.getTitle(),"Moby Dick");
        Assert.assertEquals(lotr.getTitle(),"The Lord of the Rings");
        Assert.assertEquals(wawn.getTitle(),"Where are we now");

        Asset nonExistent = BinarySearch.assetSearch(arr, "1234");

        Assert.assertNull(nonExistent);
    }
}
