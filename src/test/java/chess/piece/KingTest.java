package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("킹은 상하좌우 및 대각선 방향으로 한 칸 이동할 수 있다.")
    void kingMoveTest() {
        // given
        King king = new King(Color.WHITE);
        Position source = Position.of("d", 4);
        // when, then
        assertAll(
                () -> assertThat(king.isMovable(source, Position.of("d", 5))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of("e", 5))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of("e", 4))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of("e", 3))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of("d", 3))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of("c", 3))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of("c", 4))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of("c", 5))).isTrue()
        );
    }

    @Test
    @DisplayName("킹은 한 번에 여러 칸 이동할 수 없다.")
    void kingMaxUnitTest() {
        // given
        King king = new King(Color.WHITE);
        Position source = Position.of("d", 4);
        // when, then
        assertThat(king.isMovable(source, Position.of("d", 6))).isFalse();
    }
}
