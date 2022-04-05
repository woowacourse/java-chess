package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KingTest {

    @Test
    @DisplayName("킹을 생성한다.")
    void construct() {
        final var piece = new King(Color.BLACK);

        assertThat(piece).isInstanceOf(King.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"c3", "c4", "c5", "d3", "d5", "e3", "e4", "e5"})
    @DisplayName("킹은 주위 8개의 위치로 갈 수 있다.")
    void isMovableTrue(final String position) {
        final Piece king = new King(Color.BLACK);
        final boolean actual = king.isMovable(Position.of("d4"), Position.of(position));

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"b4", "d6", "d2", "f4"})
    @DisplayName("킹은 주위 8개 외의 윛치로 갈 수 없다.")
    void isMovableFalse() {
        final Piece king = new King(Color.BLACK);
        final boolean actual = king.isMovable(Position.of("a1"), Position.of("b8"));

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("킹은 0점이다.")
    void getPoint() {
        final Piece king = new King(Color.BLACK);
        final double point = king.getPoint();

        assertThat(point).isEqualTo(0);
    }
}
