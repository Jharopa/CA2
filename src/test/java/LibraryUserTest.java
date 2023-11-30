import org.CA2.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class LibraryUserTest {
    @Test
    public void testLibraryUser() throws AssetException {
        LibraryUser user = new LibraryUser( 1, "John Doe");

        Book b1 = new Book("The Lord of the Rings", "JRR. Tolkien", "9780544003415", true);
        AudioBook ab1 = new AudioBook("The Hobbit", "JRR. Tolkien","9781566192637", 340, true);

        user.addBorrowedAsset(b1);
        user.addBorrowedAsset(ab1);

        Assert.assertEquals(user.getName(), "John Doe");
        Assert.assertEquals(user.getBorrowedAssets().get(0), b1);
        Assert.assertEquals(user.getBorrowedAssets().get(1), ab1);

        user.setName("New Name");

        Book b2 = new Book("New Book", "New name", "123456", true);
        AudioBook ab2 = new AudioBook("New Book 2", "New name","654321", 340, true);

        user.removeBorrowedAsset(b1);
        user.removeBorrowedAsset(ab1);

        user.addBorrowedAsset(b2);
        user.addBorrowedAsset(ab2);

        Assert.assertEquals(user.getName(), "New Name");
        Assert.assertEquals(user.getBorrowedAssets().get(0), b2);
        Assert.assertEquals(user.getBorrowedAssets().get(1), ab2);
    }
}
