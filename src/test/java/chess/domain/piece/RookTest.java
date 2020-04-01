package chess.domain.piece;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {
    @DisplayName("룩이 이동 가능한 곳인지 검사")
    @Test
    void movableTest() {
        Piece rook = new Rook(PieceType.ROOK, Team.BLACK);
        Position source = Position.of("d4");
        Position movableTarget = Position.of("d7");
        Position nonMovableTarget = Position.of("c7");

        Assertions.assertThat(rook.movable(source, movableTarget)).isTrue();
        Assertions.assertThat(rook.movable(source, nonMovableTarget)).isFalse();
    }
}
