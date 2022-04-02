package chess.view;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoveCommandTest {

    @Test
    @DisplayName("source 위치 반환 확인")
    void moveSourceFormat() {
        MoveCommand moveCommand = new MoveCommand("move a1 a2");
        assertThat(moveCommand.getSource()).isEqualTo(Position.of("1", "a"));
    }

    @Test
    @DisplayName("target 위치 반환 확인")
    void moveTargetFormat() {
        MoveCommand moveCommand = new MoveCommand("move a1 a2");
        assertThat(moveCommand.getTarget()).isEqualTo(Position.of("2", "a"));
    }
}
