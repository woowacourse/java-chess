package chess.piece;

import chess.Position;
import chess.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @Test
    @DisplayName("비숍의 진행 방향이 맞는다면 true 반환")
    void correctMove() {
        Bishop bishop = new Bishop(Position.of('a', '1'), Team.WHITE);

        assertThat(bishop.isMovable(Position.of('f', '6'))).isTrue();
    }
}
