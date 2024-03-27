package chess.domain.piece;

import chess.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @Test
    @DisplayName("오른쪽 대각선 위로 움직일 수 있다.")
    void isMovableDirection1() {
        Bishop bishop = new Bishop(Team.WHITE);
        boolean result = bishop.isMovableDirection(
                Point.of('a', 1),
                Point.of('b', 2)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽 대각선 아래로 움직일 수 있다.")
    void isMovableDirection2() {
        Bishop bishop = new Bishop(Team.WHITE);
        boolean result = bishop.isMovableDirection(
                Point.of('c', 4),
                Point.of('e', 2)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 위로 움직일 수 있다.")
    void isMovableDirection3() {
        Bishop bishop = new Bishop(Team.WHITE);
        boolean result = bishop.isMovableDirection(
                Point.of('h', 1),
                Point.of('a', 8)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 아래로 움직일 수 있다.")
    void isMovableDirection4() {
        Bishop bishop = new Bishop(Team.WHITE);
        boolean result = bishop.isMovableDirection(
                Point.of('h', 8),
                Point.of('b', 2)
        );

        assertThat(result).isTrue();
    }
}
