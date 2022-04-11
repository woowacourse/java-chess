package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class QueenTest {

    @Test
    @DisplayName("퀸을 생성한다.")
    void construct() {
        final var piece = new Queen(Color.BLACK);

        assertThat(piece).isInstanceOf(Queen.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a4", "h4", "d1", "d8", "g1", "h8", "a1", "a7"})
    @DisplayName("퀸은 직선, 대각선으로 갈 수 있다.")
    void isMovableTrue(final String position) {
        final Piece queen = new Queen(Color.BLACK);
        final boolean actual = queen.isMovable(Position.of("d4"), Position.of(position));

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"a2", "e6", "c2", "a8"})
    @DisplayName("퀸은 직선, 대각선이 아닌 방향으로 갈 수 없다.")
    void isMovableFalse(final String position) {
        final Piece queen = new Queen(Color.BLACK);
        final boolean actual = queen.isMovable(Position.of("d4"), Position.of(position));

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("퀸은 9점이다.")
    void getPoint() {
        final Piece queen = new Queen(Color.BLACK);
        final double point = queen.getPoint();

        assertThat(point).isEqualTo(9);
    }
}
