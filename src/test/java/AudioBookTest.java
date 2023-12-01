import org.CA2.AssetException;
import org.junit.Test;
import org.junit.Assert;
import org.CA2.AudioBook;

public class AudioBookTest {
    @Test
    public void testAudioBook() throws AssetException {
        AudioBook ab = new AudioBook("The Lord of the Rings", "JRR. Tolkien", "9780544003415", 360,true);

        Assert.assertEquals(ab.getTitle(), "The Lord of the Rings");
        Assert.assertEquals(ab.getAuthor(), "JRR. Tolkien");
        Assert.assertEquals(ab.getISBN(), "9780544003415");
        Assert.assertEquals(ab.getDuration(), 360);
        Assert.assertTrue(ab.isAvailability());

        Assert.assertEquals(ab.toString(), "The Lord of the Rings, JRR. Tolkien, 9780544003415, 360, available");

        ab.setTitle("New title");
        ab.setAuthor("John Doe");
        ab.setISBN("1234");
        ab.setDuration(180);
        ab.setAvailability(false);

        Assert.assertEquals(ab.getTitle(), "New title");
        Assert.assertEquals(ab.getAuthor(), "John Doe");
        Assert.assertEquals(ab.getISBN(), "1234");
        Assert.assertEquals(ab.getDuration(), 180);
        Assert.assertFalse(ab.isAvailability());
    }
}
