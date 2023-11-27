import org.CA2.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class LibraryUserTest {
    @Test
    public void testLibraryUser() throws AssetException {
        LinkedList<Asset> userAssets = new LinkedList<>();

        Book b1 = new Book("The Lord of the Rings", "JRR. Tolkien", "9780544003415", true);
        AudioBook ab1 = new AudioBook("The Hobbit", "JRR. Tolkien","9781566192637", 340, true);

        userAssets.add(b1);
        userAssets.add(ab1);

        LibraryUser a = new LibraryUser("John Doe", 1, userAssets);

        Assert.assertEquals(a.getName(), "John Doe");
        Assert.assertEquals(a.getBorrowedAssets().get(0), b1);
        Assert.assertEquals(a.getBorrowedAssets().get(1), ab1);

        a.setName("New Name");

        Book b2 = new Book("New Book", "New name", "123456", true);
        AudioBook ab2 = new AudioBook("New Book 2", "New name","654321", 340, true);

        userAssets.clear();
        userAssets.add(b2);
        userAssets.add(ab2);

        a.setBorrowedAssets(userAssets);

        Assert.assertEquals(a.getName(), "New Name");
        Assert.assertEquals(a.getBorrowedAssets().get(0), b2);
        Assert.assertEquals(a.getBorrowedAssets().get(1), ab2);
    }
}
