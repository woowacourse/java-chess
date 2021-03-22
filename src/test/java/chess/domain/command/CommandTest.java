package chess.domain.command;

import chess.exception.InvalidCommandException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    @Test
    @DisplayName("문자열에 따라 올바른 Command 반환")
    void fromSuccess() {
        assertThat(Command.from("move a1 a2")).isInstanceOf(Move.class);
        assertThat(Command.from("end")).isInstanceOf(End.class);
        assertThat(Command.from("status")).isInstanceOf(Status.class);
        assertThat(Command.from("start")).isInstanceOf(Start.class);
    }

    @Test
    @DisplayName("올바르지 않은 문자열 Command 반환 실패")
    void fromFail() {
        assertThatThrownBy(() -> Command.from("abc")).isInstanceOf(InvalidCommandException.class);
    }
}