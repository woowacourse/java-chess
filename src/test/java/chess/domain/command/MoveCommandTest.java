package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoveCommandTest {

    @Test
    @DisplayName("입력한 출발점과 도착점의 MoveCommand 를 생성하는지")
    void createMoveCommand() {
        MoveCommand moveCommand = MoveCommand.of("move a1 a2");

        assertAll(
                () -> assertThat(moveCommand.from()).isEqualTo(Position.of(Column.A, Row.RANK_1)),
                () -> assertThat(moveCommand.to()).isEqualTo(Position.of(Column.A, Row.RANK_2))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"move a10 a2", "move a1 a22", "move q1 a2", "move a1 w2", "move q1 q2"})
    @DisplayName("잘못된 값으로 MoveCommand 를 생성하려 할 때 예외처리를 하는지")
    void CreateWithInvalidValue(String value) {
        assertThatThrownBy(() -> MoveCommand.of(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 좌표입니다.");
    }
}
