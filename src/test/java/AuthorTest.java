import org.CA2.*;
import org.junit.Test;
import org.junit.Assert;

import java.util.LinkedList;

public class AuthorTest {
    @Test
    public void testAuthor() throws AssetException {
        LinkedList<Asset> authorsAssets = new LinkedList<>();

        Book b1 = new Book("The Lord of the Rings", "JRR. Tolkien", "9780544003415", true);
        AudioBook ab1 = new AudioBook("The Hobbit", "JRR. Tolkien","9781566192637", 340, true);

        authorsAssets.add(b1);
        authorsAssets.add(ab1);

        Author a = new Author("JRR. Tolkien", authorsAssets);

        Assert.assertEquals(a.getName(), "JRR. Tolkien");
        Assert.assertEquals(a.getAuthoredAssets().get(0), b1);
        Assert.assertEquals(a.getAuthoredAssets().get(1), ab1);

        a.setName("New Name");

        Book b2 = new Book("New Book", "New name", "123456", true);
        AudioBook ab2 = new AudioBook("New Book 2", "New name","654321", 340, true);

        authorsAssets.clear();
        authorsAssets.add(b2);
        authorsAssets.add(ab2);

        a.setAuthoredAssets(authorsAssets);

        Assert.assertEquals(a.getName(), "New Name");
        Assert.assertEquals(a.getAuthoredAssets().get(0), b2);
        Assert.assertEquals(a.getAuthoredAssets().get(1), ab2);
    }
}
