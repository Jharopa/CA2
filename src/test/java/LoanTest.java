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

        Assert.assertEquals(1, loan.getID());
        Assert.assertEquals("John Doe", loan.getBorrower().getName());
        Assert.assertEquals("The Lord of the Rings", loan.getBorrowedAsset().getTitle());
        Assert.assertEquals(LocalDate.parse("2023-11-30", DateTimeFormatter.ISO_LOCAL_DATE), loan.getBorrowDate());
        Assert.assertEquals(LocalDate.parse("9999-11-30", DateTimeFormatter.ISO_LOCAL_DATE), loan.getReturnDate());
        Assert.assertFalse(loan.isReturned());
        Assert.assertFalse(loan.isOverdue());

        Loan loanOverdue = new Loan(
                2, user, book, LocalDate.parse("2023-01-01", DateTimeFormatter.ISO_LOCAL_DATE),
                LocalDate.parse("2023-01-14", DateTimeFormatter.ISO_LOCAL_DATE), false
        );

        Assert.assertTrue(loanOverdue.isOverdue());
    }
}
