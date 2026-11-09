
package co.edu.unillanos.msg.infrastructure.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyFormatTest {

    @Test
    public void testFormatPositive() {
        assertEquals("$ 1160.00", MoneyFormat.format(1160.0));
    }

    @Test
    public void testFormatDecimal() {
        assertEquals("$ 0.50", MoneyFormat.format(0.5));
    }

    @Test
    public void testFormatNegative() {
        assertEquals("$ -750.00", MoneyFormat.format(-750.0));
    }
}