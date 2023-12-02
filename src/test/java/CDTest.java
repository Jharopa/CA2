import org.CA2.AssetException;
import org.junit.Test;
import org.junit.Assert;
import org.CA2.CD;

public class CDTest {
    @Test
    public void testBook() throws AssetException {
        CD c = new CD(1, "Hunky Dory", "Ken Scott", "David Bowie", 41, true);

        Assert.assertEquals("Hunky Dory", c.getTitle());
        Assert.assertEquals("Ken Scott", c.getProducer());
        Assert.assertEquals("David Bowie", c.getPerformer());
        Assert.assertEquals(41, c.getPlaytime());
        Assert.assertTrue(c.isAvailability());

        Assert.assertEquals("1    Hunky Dory              Ken Scott           David Bowie         41          available   ", c.toString());

        c.setTitle("New title");
        c.setProducer("John Doe");
        c.setPerformer("Jane Doe");
        c.setPlaytime(21);
        c.setAvailability(false);

        Assert.assertEquals("New title", c.getTitle());
        Assert.assertEquals("John Doe", c.getProducer());
        Assert.assertEquals("Jane Doe", c.getPerformer());
        Assert.assertEquals(21, c.getPlaytime());
        Assert.assertFalse(c.isAvailability());
    }
}
