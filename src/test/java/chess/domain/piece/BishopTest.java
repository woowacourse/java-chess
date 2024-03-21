package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("오른쪽 대각선 위로 움직일 수 있다.")
    void isMovable1() {
        Bishop bishop = new Bishop(Team.WHITE);
        boolean result = bishop.isMovable(
                new Point('a', 1),
                new Point('b', 2));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽 대각선 아래로 움직일 수 있다.")
    void isMovable2() {
        Bishop bishop = new Bishop(Team.WHITE);
        boolean result = bishop.isMovable(
                new Point('c', 4),
                new Point('e', 2));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 위로 움직일 수 있다.")
    void isMovable3() {
        Bishop bishop = new Bishop(Team.WHITE);
        boolean result = bishop.isMovable(
                new Point('h', 1),
                new Point('a', 8));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 아래로 움직일 수 있다.")
    void isMovable4() {
        Bishop bishop = new Bishop(Team.WHITE);
        boolean result = bishop.isMovable(
                new Point('h', 8),
                new Point('b', 2));

        assertThat(result).isTrue();
    }
}