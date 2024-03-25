package chess.domain.piece;

import chess.domain.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @Test
    @DisplayName("위로 움직일 수 있다")
    void isMovableDirection1() {
        Rook rook = new Rook(Team.WHITE);

        boolean result = rook.isMovableDirection(
                new Point('a', 1),
                new Point('a', 8)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아래로 움직일 수 있다")
    void isMovableDirection2() {
        Rook rook = new Rook(Team.WHITE);

        boolean result = rook.isMovableDirection(
                new Point('a', 8),
                new Point('a', 1)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽으로 움직일 수 있다")
    void isMovableDirection3() {
        Rook rook = new Rook(Team.WHITE);

        boolean result = rook.isMovableDirection(
                new Point('h', 1),
                new Point('a', 1)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽으로 움직일 수 있다")
    void isMovableDirection4() {
        Rook rook = new Rook(Team.WHITE);

        boolean result = rook.isMovableDirection(
                new Point('a', 1),
                new Point('h', 1)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("대각선으로 움질수 없다")
    void invalidMovableDirection() {
        Rook rook = new Rook(Team.WHITE);

        boolean result = rook.isMovableDirection(
                new Point('a', 1),
                new Point('b', 2)
        );

        assertThat(result).isFalse();
    }
}
