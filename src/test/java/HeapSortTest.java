import org.CA2.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.Date;

public class HeapSortTest {
    private static Asset[] assetArr;
    private static Author[] authorArr;
    private static LibraryUser[] userArr;

    @BeforeClass
    public static void testSetup() throws AssetException {
        assetArr = new Asset[] {
                new Book("The Lord of the Rings", "JRR. Tolkien", "9780544003415", true),
                new AudioBook("Moby Dick", "Herman Melville","9781566192637", 340, true),
                new CD("Hunky Dory", "Ken Scott", "David Bowie", 41, true),
                new Thesis("Where are we now", "John Doe", "Philosophy", "This is an abstract", new Date(), true)
        };

        authorArr = new Author[] {
                new Author("Ken Scott", null),
                new Author("JRR. Tolkien", null),
                new Author("Herman Melville", null),
                new Author("John Doe",null),
        };

        userArr = new LibraryUser[] {
                new LibraryUser("Mary Smith", 1, null),
                new LibraryUser("John Doe", 2, null),
                new LibraryUser("Jane Doe", 3, null),
                new LibraryUser("Alan Connor", 4, null)
        };
    }

    @Test
    public void testBinarySearch() {
        Assert.assertEquals(assetArr[0].getTitle(), "The Lord of the Rings");
        Assert.assertEquals(assetArr[1].getTitle(), "Moby Dick");
        Assert.assertEquals(assetArr[2].getTitle(), "Hunky Dory");
        Assert.assertEquals(assetArr[3].getTitle(), "Where are we now");

        HeapSort.sort(assetArr);

        Assert.assertEquals(assetArr[0].getTitle(), "Hunky Dory");
        Assert.assertEquals(assetArr[1].getTitle(), "Moby Dick");
        Assert.assertEquals(assetArr[2].getTitle(), "The Lord of the Rings");
        Assert.assertEquals(assetArr[3].getTitle(), "Where are we now");

        Assert.assertEquals(authorArr[0].getName(), "Ken Scott");
        Assert.assertEquals(authorArr[1].getName(), "JRR. Tolkien");
        Assert.assertEquals(authorArr[2].getName(), "Herman Melville");
        Assert.assertEquals(authorArr[3].getName(), "John Doe");

        HeapSort.sort(authorArr);

        Assert.assertEquals(authorArr[0].getName(), "Herman Melville");
        Assert.assertEquals(authorArr[1].getName(), "JRR. Tolkien");
        Assert.assertEquals(authorArr[2].getName(), "John Doe");
        Assert.assertEquals(authorArr[3].getName(), "Ken Scott");

        Assert.assertEquals(userArr[0].getName(), "Mary Smith");
        Assert.assertEquals(userArr[1].getName(), "John Doe");
        Assert.assertEquals(userArr[2].getName(), "Jane Doe");
        Assert.assertEquals(userArr[3].getName(), "Alan Connor");

        HeapSort.sort(userArr);

        Assert.assertEquals(userArr[0].getName(), "Alan Connor");
        Assert.assertEquals(userArr[1].getName(), "Jane Doe");
        Assert.assertEquals(userArr[2].getName(), "John Doe");
        Assert.assertEquals(userArr[3].getName(), "Mary Smith");
    }
}
