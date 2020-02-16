package ee.neotech.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NeotechCustomExceptionTest {

    public static final String TEST_EXCEPTION_MESSAGE = "Test exception";

    @Test
    public void neotechCustomExceptionTest() {
        final NeotechCustomException test_exception = new NeotechCustomException(TEST_EXCEPTION_MESSAGE);

        assertEquals(TEST_EXCEPTION_MESSAGE, test_exception.getLocalizedMessage());
    }

}