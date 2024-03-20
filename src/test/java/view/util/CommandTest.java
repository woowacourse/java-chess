package view.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @DisplayName("동일한 식별자가 있는지 확인한다.")
    @Test
    void isSameIdentifier() {
        assertTrue(Command.START.isSameIdentifier("start"));
    }
}
