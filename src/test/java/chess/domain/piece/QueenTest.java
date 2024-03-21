package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    @DisplayName("위로 움직일 수 있다")
    void isMovable1() {
        Queen queen = new Queen(Team.WHITE);

        boolean result = queen.isMovable(new Point('a', 1), new Point('a', 8));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아래로 움직일 수 있다")
    void isMovable2() {
        Queen queen = new Queen(Team.WHITE);

        boolean result = queen.isMovable(new Point('a', 8), new Point('a', 1));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽으로 움직일 수 있다")
    void isMovable3() {
        Queen queen = new Queen(Team.WHITE);

        boolean result = queen.isMovable(new Point('h', 1), new Point('a', 1));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽으로 움직일 수 있다")
    void isMovable4() {
        Queen queen = new Queen(Team.WHITE);

        boolean result = queen.isMovable(new Point('a', 1), new Point('h', 1));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽 대각선 위로 움직일 수 있다.")
    void isMovable5() {
        Queen queen = new Queen(Team.WHITE);
        boolean result = queen.isMovable(
                new Point('a', 1),
                new Point('b', 2));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽 대각선 아래로 움직일 수 있다.")
    void isMovable6() {
        Queen queen = new Queen(Team.WHITE);
        boolean result = queen.isMovable(
                new Point('c', 4),
                new Point('e', 2));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 위로 움직일 수 있다.")
    void isMovable7() {
        Queen queen = new Queen(Team.WHITE);
        boolean result = queen.isMovable(
                new Point('h', 1),
                new Point('a', 8));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 아래로 움직일 수 있다.")
    void isMovable8() {
        Queen queen = new Queen(Team.WHITE);
        boolean result = queen.isMovable(
                new Point('h', 8),
                new Point('b', 2));

        assertThat(result).isTrue();
    }
}