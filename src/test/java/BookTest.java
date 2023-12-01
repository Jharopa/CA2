import org.CA2.AssetException;
import org.junit.Test;
import org.junit.Assert;
import org.CA2.Book;

public class BookTest {
    @Test
    public void testBook() throws AssetException {
        Book b = new Book(1, "The Lord of the Rings", "JRR. Tolkien", "9780544003415", true);

        Assert.assertEquals("The Lord of the Rings", b.getTitle());
        Assert.assertEquals("JRR. Tolkien", b.getAuthor());
        Assert.assertEquals("9780544003415", b.getISBN());
        Assert.assertTrue(b.isAvailability());

        Assert.assertEquals("1    The Lord of the Rings   JRR. Tolkien        9780544003415    available   ", b.toString());

        b.setTitle("New title");
        b.setAuthor("John Doe");
        b.setISBN("1234");
        b.setAvailability(false);

        Assert.assertEquals("New title", b.getTitle());
        Assert.assertEquals("John Doe", b.getAuthor());
        Assert.assertEquals("1234", b.getISBN());
        Assert.assertFalse(b.isAvailability());
    }
}
