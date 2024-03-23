package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("위로 움직일 수 있다")
    void isMovable1() {
        Rook rook = Rook.from(Team.WHITE);

        boolean result = rook.isMovable(
                new Point('a', 1),
                new Point('a', 8)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아래로 움직일 수 있다")
    void isMovable2() {
        Rook rook = Rook.from(Team.WHITE);

        boolean result = rook.isMovable(
                new Point('a', 8),
                new Point('a', 1)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽으로 움직일 수 있다")
    void isMovable3() {
        Rook rook = Rook.from(Team.WHITE);

        boolean result = rook.isMovable(
                new Point('h', 1),
                new Point('a', 1)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽으로 움직일 수 있다")
    void isMovable4() {
        Rook rook = Rook.from(Team.WHITE);

        boolean result = rook.isMovable(
                new Point('a', 1),
                new Point('h', 1)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("대각선으로 움질수 없다")
    void invalidIsMovable() {
        Rook rook = Rook.from(Team.WHITE);

        boolean result = rook.isMovable(
                new Point('a', 1),
                new Point('b', 2)
        );

        assertThat(result).isFalse();
    }
}
