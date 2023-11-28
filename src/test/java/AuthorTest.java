import org.CA2.*;
import org.junit.Test;
import org.junit.Assert;

import java.util.LinkedList;

public class AuthorTest {
    @Test
    public void testAuthor() throws AssetException {
        Book b1 = new Book("The Lord of the Rings", "JRR. Tolkien", "9780544003415", true);
        AudioBook ab1 = new AudioBook("The Hobbit", "JRR. Tolkien","9781566192637", 340, true);

        Author a = new Author("JRR. Tolkien");

        a.AddAssetToAuthor(b1);
        a.AddAssetToAuthor(ab1);

        Assert.assertEquals(a.getName(), "JRR. Tolkien");
        Assert.assertEquals(a.getAuthoredAssets().get(0), b1);
        Assert.assertEquals(a.getAuthoredAssets().get(1), ab1);

        a.setName("New Name");

        Book b2 = new Book("New Book", "New name", "123456", true);
        AudioBook ab2 = new AudioBook("New Book 2", "New name","654321", 340, true);

        LinkedList<Asset> assets = new LinkedList<>();
        assets.add(b2);
        assets.add(ab2);
        a.setAuthoredAssets(assets);

        Assert.assertEquals(a.getName(), "New Name");
        Assert.assertEquals(a.getAuthoredAssets().get(0), b2);
        Assert.assertEquals(a.getAuthoredAssets().get(1), ab2);
    }
}
