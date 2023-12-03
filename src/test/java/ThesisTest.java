import org.CA2.exceptions.AssetException;
import org.junit.Test;
import org.junit.Assert;
import org.CA2.models.Thesis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ThesisTest {
    @Test
    public void testThesis() throws AssetException {
        Thesis t = new Thesis(1, "Where are we now", "John Doe", "Philosophy", "This is an abstract", LocalDate.parse("2023-08-08", DateTimeFormatter.ISO_LOCAL_DATE), true);

        Assert.assertEquals(t.getTitle(), "Where are we now");
        Assert.assertEquals(t.getAuthor(), "John Doe");
        Assert.assertEquals(t.getTopic(), "Philosophy");
        Assert.assertEquals(t.getAbstract(), "This is an abstract");
        Assert.assertEquals(t.getDatePublished(), LocalDate.parse("2023-08-08", DateTimeFormatter.ISO_LOCAL_DATE));
        Assert.assertTrue(t.isAvailability());

        Assert.assertEquals(t.toString(), "1    Where are we now        John Doe            Philosophy     2023-08-08       available   ");

        t.setTitle("New title");
        t.setAuthor("Jane Doe");
        t.setTopic("Chemistry");
        t.setAbstract("This is a new abstract");
        t.setDatePublished(LocalDate.parse("2023-09-09", DateTimeFormatter.ISO_LOCAL_DATE));
        t.setAvailability(false);

        Assert.assertEquals(t.getTitle(), "New title");
        Assert.assertEquals(t.getAuthor(), "Jane Doe");
        Assert.assertEquals(t.getTopic(), "Chemistry");
        Assert.assertEquals(t.getAbstract(), "This is a new abstract");
        Assert.assertEquals(t.getDatePublished(), LocalDate.parse("2023-09-09", DateTimeFormatter.ISO_LOCAL_DATE));
        Assert.assertFalse(t.isAvailability());
    }
}
