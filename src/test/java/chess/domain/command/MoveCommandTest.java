package chess.domain.command;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

public class MoveCommandTest {

    @Test
    @DisplayName("입력한 출발점과 도착점의 MoveCommand 를 생성하는지")
    void createMoveCommand() {
        MoveCommand moveCommand = MoveCommand.of("move a1 a2");

        assertAll(
            () -> assertThat(moveCommand.from()).isEqualTo(new Position(PositionX.A, PositionY.RANK_1)),
            () -> assertThat(moveCommand.to()).isEqualTo(new Position(PositionX.A, PositionY.RANK_2))
        );
    }
}
