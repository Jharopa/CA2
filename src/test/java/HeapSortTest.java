import org.CA2.exceptions.AssetException;
import org.CA2.models.*;
import org.CA2.util.HeapSort;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDate;

public class HeapSortTest {
    private static Asset[] assetArr;
    private static Author[] authorArr;
    private static LibraryUser[] userArr;

    @BeforeClass
    public static void testSetup() throws AssetException {
        assetArr = new Asset[] {
                new Book(2, "The Lord of the Rings", "JRR. Tolkien", "9780544003415", true),
                new AudioBook(4, "Moby Dick", "Herman Melville","9781566192637", 340, true),
                new CD(3, "Hunky Dory", "Ken Scott", "David Bowie", 41, true),
                new Thesis(1, "Where are we now", "John Doe", "Philosophy", "This is an abstract", LocalDate.now(), true)

        };

        authorArr = new Author[] {
                new Author(4, "John Doe"),
                new Author(2, "JRR. Tolkien"),
                new Author(3, "Herman Melville"),
                new Author(1, "Ken Scott"),
        };

        userArr = new LibraryUser[] {
                new LibraryUser(2, "Mary Smith"),
                new LibraryUser(3, "John Doe"),
                new LibraryUser(1, "Jane Doe"),
                new LibraryUser(4, "Alan Connor")
        };
    }

    @Test
    public void testBinarySearch() {
        Assert.assertEquals("The Lord of the Rings", assetArr[0].getTitle());
        Assert.assertEquals("Moby Dick", assetArr[1].getTitle());
        Assert.assertEquals("Hunky Dory", assetArr[2].getTitle());
        Assert.assertEquals("Where are we now", assetArr[3].getTitle());

        HeapSort.sort(assetArr);

        Assert.assertEquals("Where are we now", assetArr[0].getTitle());
        Assert.assertEquals("The Lord of the Rings", assetArr[1].getTitle());
        Assert.assertEquals("Hunky Dory", assetArr[2].getTitle());
        Assert.assertEquals("Moby Dick", assetArr[3].getTitle());

        Assert.assertEquals("John Doe", authorArr[0].getName());
        Assert.assertEquals("JRR. Tolkien", authorArr[1].getName());
        Assert.assertEquals("Herman Melville", authorArr[2].getName());
        Assert.assertEquals("Ken Scott", authorArr[3].getName());

        HeapSort.sort(authorArr);

        Assert.assertEquals("Ken Scott", authorArr[0].getName());
        Assert.assertEquals("JRR. Tolkien", authorArr[1].getName());
        Assert.assertEquals("Herman Melville", authorArr[2].getName());
        Assert.assertEquals("John Doe", authorArr[3].getName());

        Assert.assertEquals(userArr[0].getName(), "Mary Smith");
        Assert.assertEquals("John Doe", userArr[1].getName());
        Assert.assertEquals("Jane Doe", userArr[2].getName());
        Assert.assertEquals("Alan Connor", userArr[3].getName());

        HeapSort.sort(userArr);

        Assert.assertEquals("Jane Doe", userArr[0].getName());
        Assert.assertEquals("Mary Smith", userArr[1].getName());
        Assert.assertEquals("John Doe", userArr[2].getName());
        Assert.assertEquals("Alan Connor", userArr[3].getName());
    }
}
