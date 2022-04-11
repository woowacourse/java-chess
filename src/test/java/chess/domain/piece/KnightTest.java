package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @Test
    @DisplayName("나이트를 생성한다.")
    void construct() {
        final var piece = new Knight(Color.BLACK);

        assertThat(piece).isInstanceOf(Knight.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"b3", "b5", "c2", "c6", "e2", "e6", "f3", "f5"})
    @DisplayName("나이트는 정해진 8개의 위치로 갈 수 있다.")
    void isMovableTrue(final String position) {
        final Piece knight = new Knight(Color.BLACK);
        final boolean actual = knight.isMovable(Position.of("d4"), Position.of(position));

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"b1", "b2", "b4", "b6", "d6", "f6", "f4", "f2"})
    @DisplayName("나이트는 정해진 8개 외의 위치로는 갈 수 없다.")
    void isMovableFalse(final String position) {
        final Piece knight = new Knight(Color.BLACK);
        final boolean actual = knight.isMovable(Position.of("a1"), Position.of(position));

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("나이트는 2.5점이다.")
    void getPoint() {
        final Piece knight = new Knight(Color.BLACK);
        final double point = knight.getPoint();

        assertThat(point).isEqualTo(2.5);
    }
}
