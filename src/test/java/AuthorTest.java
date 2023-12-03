import org.CA2.exceptions.AssetException;
import org.CA2.models.Asset;
import org.CA2.models.AudioBook;
import org.CA2.models.Author;
import org.CA2.models.Book;
import org.junit.Test;
import org.junit.Assert;

import java.util.LinkedList;

public class AuthorTest {
    @Test
    public void testAuthor() throws AssetException {
        Book b1 = new Book(1, "The Lord of the Rings", "JRR. Tolkien", "9780544003415", true);
        AudioBook ab1 = new AudioBook(2, "The Hobbit", "JRR. Tolkien","9781566192637", 340, true);

        Author a = new Author(1, "JRR. Tolkien");

        a.addAuthoredAsset(b1);
        a.addAuthoredAsset(ab1);

        Assert.assertEquals("JRR. Tolkien", a.getName());
        Assert.assertEquals(b1, a.getAuthoredAssets().get(0));
        Assert.assertEquals(ab1, a.getAuthoredAssets().get(1));

        a.setName("New Name");

        Book b2 = new Book(1, "New Book", "New name", "9780544001234", true);
        AudioBook ab2 = new AudioBook(2, "New Book 2", "New name","9780544004321", 340, true);

        LinkedList<Asset> assets = new LinkedList<>();
        assets.add(b2);
        assets.add(ab2);
        a.setAuthoredAssets(assets);

        Assert.assertEquals("New Name", a.getName());
        Assert.assertEquals(b2, a.getAuthoredAssets().get(0));
        Assert.assertEquals(ab2, a.getAuthoredAssets().get(1));
    }
}
