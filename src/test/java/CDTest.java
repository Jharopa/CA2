import org.CA2.AssetException;
import org.junit.Test;
import org.junit.Assert;
import org.CA2.CD;

public class CDTest {
    @Test
    public void testBook() throws AssetException {
        CD c = new CD("Hunky Dory", "Ken Scott", "David Bowie", 41, true);

        Assert.assertEquals(c.getTitle(), "Hunky Dory");
        Assert.assertEquals(c.getProducer(), "Ken Scott");
        Assert.assertEquals(c.getDirector(), "David Bowie");
        Assert.assertEquals(c.getPlaytime(), 41);
        Assert.assertTrue(c.isAvailability());

        Assert.assertEquals(c.toString(), "Hunky Dory, Ken Scott, David Bowie, 41, available");

        c.setTitle("New title");
        c.setProducer("John Doe");
        c.setDirector("Jane Doe");
        c.setPlaytime(21);
        c.setAvailability(false);

        Assert.assertEquals(c.getTitle(), "New title");
        Assert.assertEquals(c.getProducer(), "John Doe");
        Assert.assertEquals(c.getDirector(), "Jane Doe");
        Assert.assertEquals(c.getPlaytime(), 21);
        Assert.assertFalse(c.isAvailability());
    }
}
