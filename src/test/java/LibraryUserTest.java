import org.CA2.exceptions.AssetException;
import org.CA2.models.AudioBook;
import org.CA2.models.Book;
import org.CA2.models.LibraryUser;
import org.junit.Assert;
import org.junit.Test;

public class LibraryUserTest {
    @Test
    public void testLibraryUser() throws AssetException {
        LibraryUser user = new LibraryUser( 1, "John Doe");

        Book b1 = new Book(1, "The Lord of the Rings", "JRR. Tolkien", "9780544003415", true);
        AudioBook ab1 = new AudioBook(2, "The Hobbit", "JRR. Tolkien","9781566192637", 340, true);

        user.addBorrowedAsset(b1);
        user.addBorrowedAsset(ab1);

        Assert.assertEquals("John Doe", user.getName());
        Assert.assertEquals(b1, user.getBorrowedAssets().get(0));
        Assert.assertEquals( ab1, user.getBorrowedAssets().get(1));

        user.setName("New Name");

        Book b2 = new Book(1, "New Book", "New name", "9781566191234", true);
        AudioBook ab2 = new AudioBook(2, "New Book 2", "New name","9781566194321", 340, true);

        user.removeBorrowedAsset(b1);
        user.removeBorrowedAsset(ab1);

        user.addBorrowedAsset(b2);
        user.addBorrowedAsset(ab2);

        Assert.assertEquals("New Name", user.getName());
        Assert.assertEquals(b2, user.getBorrowedAssets().get(0));
        Assert.assertEquals(ab2, user.getBorrowedAssets().get(1));
    }
}
