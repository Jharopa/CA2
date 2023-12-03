import org.CA2.exceptions.AssetException;
import org.junit.Test;
import org.junit.Assert;
import org.CA2.models.AudioBook;

public class AudioBookTest {
    @Test
    public void testAudioBook() throws AssetException {
        AudioBook ab = new AudioBook(1, "The Lord of the Rings", "JRR. Tolkien", "9780544003415", 360,true);

        Assert.assertEquals("The Lord of the Rings", ab.getTitle());
        Assert.assertEquals("JRR. Tolkien", ab.getAuthor());
        Assert.assertEquals("9780544003415", ab.getISBN());
        Assert.assertEquals(360, ab.getDuration());
        Assert.assertTrue(ab.isAvailability());

        Assert.assertEquals("1    The Lord of the Rings   JRR. Tolkien        9780544003415    360         available   ", ab.toString());

        ab.setTitle("New title");
        ab.setAuthor("John Doe");
        ab.setISBN("9780544004321");
        ab.setDuration(180);
        ab.setAvailability(false);

        Assert.assertEquals("New title", ab.getTitle());
        Assert.assertEquals("John Doe", ab.getAuthor());
        Assert.assertEquals("9780544004321", ab.getISBN());
        Assert.assertEquals(180, ab.getDuration());
        Assert.assertFalse(ab.isAvailability());
    }
}
