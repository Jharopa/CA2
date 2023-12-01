import org.CA2.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoanTest {
    @Test
    public void testLoan() throws AssetException {
        LibraryUser user = new LibraryUser( 1, "John Doe");
        Asset book = new Book(1, "The Lord of the Rings", "JRR. Tolkien", "1234", false);
        Loan loan = new Loan(
                1, user, book, LocalDate.parse("2023-11-30", DateTimeFormatter.ISO_LOCAL_DATE),
                LocalDate.parse("9999-11-30", DateTimeFormatter.ISO_LOCAL_DATE), false
        );

        Assert.assertEquals(loan.getID(), 1);
        Assert.assertEquals(loan.getBorrower().getName(), "John Doe");
        Assert.assertEquals(loan.getBorrowedAsset().getTitle(), "The Lord of the Rings");
        Assert.assertEquals(loan.getBorrowDate(), LocalDate.parse("2023-11-30", DateTimeFormatter.ISO_LOCAL_DATE));
        Assert.assertEquals(loan.getReturnDate(), LocalDate.parse("9999-11-30", DateTimeFormatter.ISO_LOCAL_DATE));
        Assert.assertFalse(loan.isReturned());
        Assert.assertFalse(loan.isOverdue());

        Loan loanOverdue = new Loan(
                2, user, book, LocalDate.parse("2023-01-01", DateTimeFormatter.ISO_LOCAL_DATE),
                LocalDate.parse("2023-01-14", DateTimeFormatter.ISO_LOCAL_DATE), false
        );

        Assert.assertTrue(loanOverdue.isOverdue());
    }
}
