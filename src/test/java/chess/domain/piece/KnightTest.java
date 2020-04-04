package chess.domain.piece;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {
    @DisplayName("나이트가 이동 가능한 곳인지 검사")
    @Test
    void movableTest() {
        Piece knight = new Knight(PieceType.KNIGHT, Team.BLACK);
        Position source = Position.of("d4");
        Position movableTarget = Position.of("c6");
        Position nonMovableTarget = Position.of("c7");

        Assertions.assertThat(knight.movable(source, movableTarget)).isTrue();
        Assertions.assertThat(knight.movable(source, nonMovableTarget)).isFalse();
    }
}
