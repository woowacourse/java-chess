package chess.domain.piece;

import chess.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @Test
    @DisplayName("위로 한칸 움직일 수 있다")
    void isMovablePoint1() {
        King king = new King(Team.WHITE);

        boolean result = king.isMovablePoint(
                new Point('a', 1),
                new Point('a', 2)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아래로 한칸 움직일 수 있다")
    void isMovablePoint2() {
        King king = new King(Team.WHITE);

        boolean result = king.isMovablePoint(
                new Point('a', 8),
                new Point('a', 7)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽으로 한칸 움직일 수 있다")
    void isMovablePoint3() {
        King king = new King(Team.WHITE);

        boolean result = king.isMovablePoint(
                new Point('b', 1),
                new Point('a', 1)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽으로 한칸 움직일 수 있다")
    void isMovablePoint4() {
        King king = new King(Team.WHITE);

        boolean result = king.isMovablePoint(
                new Point('a', 1),
                new Point('b', 1)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽 대각선 위로 한칸 움직일 수 있다.")
    void isMovablePoint5() {
        King king = new King(Team.WHITE);
        boolean result = king.isMovablePoint(
                new Point('a', 1),
                new Point('b', 2)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽 대각선 아래로 한칸 움직일 수 있다.")
    void isMovablePoint6() {
        King king = new King(Team.WHITE);
        boolean result = king.isMovablePoint(
                new Point('c', 4),
                new Point('d', 3)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 위로 한칸 움직일 수 있다.")
    void isMovablePoint7() {
        King king = new King(Team.WHITE);
        boolean result = king.isMovablePoint(
                new Point('b', 1),
                new Point('a', 2)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 아래로 한칸 움직일 수 있다.")
    void isMovablePoint8() {
        King king = new King(Team.WHITE);
        boolean result = king.isMovablePoint(
                new Point('c', 3),
                new Point('b', 2)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("주위로만 움직일 수 있다")
    void invalidMovablePoint() {
        King king = new King(Team.WHITE);
        boolean result = king.isMovablePoint(
                new Point('c', 3),
                new Point('c', 5)
        );

        assertThat(result).isFalse();
    }
}
