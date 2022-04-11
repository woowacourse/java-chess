package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BishopTest {

    @Test
    @DisplayName("비숍을 생성한다.")
    void construct() {
        final var piece = new Bishop(Color.BLACK);

        assertThat(piece).isInstanceOf(Bishop.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a1", "a7", "g1", "h8", "c3", "c5", "e3", "e5"})
    @DisplayName("비숍은 대각선으로 갈 수 있다.")
    void isMovableTrue(final String position) {
        final Piece bishop = new Bishop(Color.BLACK);
        final boolean actual = bishop.isMovable(Position.of("d4"), Position.of(position));

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"d3", "d5", "c4", "e4", "a8", "h1"})
    @DisplayName("비숍은 대각선이 아닌 방향으로 갈 수 없다.")
    void isMovableFalse(final String position) {
        final Piece bishop = new Bishop(Color.BLACK);
        final boolean actual = bishop.isMovable(Position.of("a1"), Position.of(position));

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("비숍은 3점이다.")
    void getPoint() {
        final Piece bishop = new Bishop(Color.BLACK);
        final double point = bishop.getPoint();

        assertThat(point).isEqualTo(3);
    }
}
