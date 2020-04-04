package chess.domain.piece;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {
    @DisplayName("비숍이 이동 가능한 곳인지 검사")
    @Test
    void movableTest() {
        Piece bishop = new Bishop(PieceType.BISHOP, Team.WHITE);
        Position source = Position.of("d4");
        Position movableTarget = Position.of("f6");
        Position nonMovableTarget = Position.of("d5");

        Assertions.assertThat(bishop.movable(source, movableTarget)).isTrue();
        Assertions.assertThat(bishop.movable(source, nonMovableTarget)).isFalse();
    }
}
