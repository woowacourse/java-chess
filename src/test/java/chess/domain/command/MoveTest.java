package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.exception.InvalidCommandException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoveTest {

    @ParameterizedTest(name = "형식에 맞지 않는 move 명령")
    @ValueSource(strings = {"", "move", "move a1", "move a1 a2 a3"})
    void validateLength(String command) {
        assertThatThrownBy(() -> new Move(command)).isInstanceOf(InvalidCommandException.class);
    }
}