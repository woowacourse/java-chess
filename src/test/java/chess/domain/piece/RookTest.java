package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RookTest {

    @Test
    @DisplayName("룩을 생성한다.")
    void construct() {
        final var piece = new Rook(Color.BLACK);

        assertThat(piece).isInstanceOf(Rook.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a4", "h4", "d1", "d8"})
    @DisplayName("룩은 직선으로 갈 수 있다.")
    void isMovableTrue(final String position) {
        final Piece rook = new Rook(Color.BLACK);
        final boolean actual = rook.isMovable(Position.of("d4"), Position.of(position));

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"h1", "h8", "e3", "a7"})
    @DisplayName("룩은 직선이 아닌 방향으로 갈 수 없다.")
    void isMovableFalse(final String position) {
        final Piece rook = new Rook(Color.BLACK);
        final boolean actual = rook.isMovable(Position.of("d4"), Position.of(position));

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("룩은 5점이다.")
    void getPoint() {
        final Piece rook = new Rook(Color.BLACK);
        final double point = rook.getPoint();

        assertThat(point).isEqualTo(5);
    }
}
