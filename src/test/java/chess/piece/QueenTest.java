package chess.piece;

import chess.Position;
import chess.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class QueenTest {

    @Test
    @DisplayName("퀸의 진행 방향이 맞는다면 true 반환")
    void correctMove() {
        Queen queen = new Queen(Position.of('a', '1'), Team.WHITE);

        assertAll(
                () -> assertThat(queen.isMovable(Position.of('f', '6'))).isTrue(),
                () -> assertThat(queen.isMovable(Position.of('a', '5'))).isTrue(),
                () -> assertThat(queen.isMovable(Position.of('f', '1'))).isTrue()
        );
    }
}
