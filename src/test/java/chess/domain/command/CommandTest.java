package chess.domain.command;

import chess.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    @Test
    void fromSuccess() {
        assertThat(Command.from("move a1 a2")).isInstanceOf(Move.class);
        assertThat(Command.from("end")).isInstanceOf(End.class);
        assertThat(Command.from("status")).isInstanceOf(Status.class);
        assertThat(Command.from("start")).isInstanceOf(Start.class);
    }

    @Test
    void fromFail() {
        assertThatThrownBy(() -> Command.from("abc")).isInstanceOf(InvalidCommandException.class);
    }
}