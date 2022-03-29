package chess.command;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MoveCommandTest {

    @Test
    void throwExceptionInvalidCommand() {
        assertThatThrownBy(() -> new MoveCommand("move 123 123123"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
