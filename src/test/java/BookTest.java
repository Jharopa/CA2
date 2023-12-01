import org.CA2.AssetException;
import org.junit.Test;
import org.junit.Assert;
import org.CA2.Book;

public class BookTest {
    @Test
    public void testBook() throws AssetException {
        Book b = new Book(1, "The Lord of the Rings", "JRR. Tolkien", "9780544003415", true);

        Assert.assertEquals(b.getTitle(), "The Lord of the Rings");
        Assert.assertEquals(b.getAuthor(), "JRR. Tolkien");
        Assert.assertEquals(b.getISBN(), "9780544003415");
        Assert.assertTrue(b.isAvailability());

        Assert.assertEquals(b.toString(), "1, The Lord of the Rings, JRR. Tolkien, 9780544003415, available");

        b.setTitle("New title");
        b.setAuthor("John Doe");
        b.setISBN("1234");
        b.setAvailability(false);

        Assert.assertEquals(b.getTitle(), "New title");
        Assert.assertEquals(b.getAuthor(), "John Doe");
        Assert.assertEquals(b.getISBN(), "1234");
        Assert.assertFalse(b.isAvailability());
    }
}
