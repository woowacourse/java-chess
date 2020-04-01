package chess.domain.piece;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {
    @DisplayName("퀸이 이동 가능한 곳인지 검사")
    @Test
    void movableTest() {
        Piece queen = new Queen(PieceType.QUEEN, Team.BLACK);
        Position source = Position.of("d4");
        Position diagonalTarget = Position.of("g7");
        Position directTarget = Position.of("d8");
        Position nonMovableTarget = Position.of("c7");

        Assertions.assertThat(queen.movable(source, diagonalTarget)).isTrue();
        Assertions.assertThat(queen.movable(source, directTarget)).isTrue();
        Assertions.assertThat(queen.movable(source, nonMovableTarget)).isFalse();
    }
}
