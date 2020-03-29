package f11_1.calgo.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static f11_1.calgo.testutil.Assert.assertThrows;

import f11_1.calgo.testutil.Assert;
import org.junit.jupiter.api.Test;

public class ProteinTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Protein(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Protein(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> Protein.isValidProtein(null));

        // invalid addresses
        assertFalse(Protein.isValidProtein("")); // empty string
        assertFalse(Protein.isValidProtein(" ")); // spaces only

        // valid addresses
        assertTrue(Protein.isValidProtein("Blk 456, Den Road, #01-355"));
        assertTrue(Protein.isValidProtein("-")); // one character
        assertTrue(Protein.isValidProtein("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}