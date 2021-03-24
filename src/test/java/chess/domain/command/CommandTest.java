package chess.domain.command;

import chess.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    @Test
    void fromSuccess() {
        assertThat(Command.from("move a1 a2")).isEqualTo(Command.MOVE);
        assertThat(Command.from("end")).isEqualTo(Command.END);
        assertThat(Command.from("status")).isEqualTo(Command.STATUS);
        assertThat(Command.from("start")).isEqualTo(Command.START);
    }

    @Test
    void fromFail() {
        assertThatThrownBy(() -> Command.from("abc")).isInstanceOf(InvalidCommandException.class);
    }
}