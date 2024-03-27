package chess.domain.piece;

import chess.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    @DisplayName("위로 두칸을 이동한 뒤 좌로 한칸 이동할 수 있다.")
    void isMovablePoint1() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovablePoint(
                Point.of('e', 4),
                Point.of('d', 6)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("위로 두칸을 이동한 뒤 우로 한칸 이동할 수 있다.")
    void isMovablePoint2() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovablePoint(
                Point.of('e', 4),
                Point.of('f', 6)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아래로 두칸을 이동한 뒤 좌로 한칸 이동할 수 있다.")
    void isMovablePoint3() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovablePoint(
                Point.of('e', 4),
                Point.of('d', 2)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아래로 두칸을 이동한 뒤 우로 한칸 이동할 수 있다.")
    void isMovablePoint4() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovablePoint(
                Point.of('e', 4),
                Point.of('f', 2)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("우로 두칸을 이동한 뒤 위로 한칸 이동할 수 있다.")
    void isMovablePoint5() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovablePoint(
                Point.of('e', 4),
                Point.of('g', 5)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("우로 두칸을 이동한 뒤 아래로 한칸 이동할 수 있다.")
    void isMovablePoint6() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovablePoint(
                Point.of('e', 4),
                Point.of('g', 3)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("좌로 두칸을 이동한 뒤 위로 한칸 이동할 수 있다.")
    void isMovablePoint7() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovablePoint(
                Point.of('e', 4),
                Point.of('c', 5)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("좌로 두칸을 이동한 뒤 아래로 한칸 이동할 수 있다.")
    void isMovablePoint8() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovablePoint(
                Point.of('e', 4),
                Point.of('g', 3)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("한 방향으로 두 칸 이동 후 다른 방향으로 한 칸이 아니면 이동할 수 없다.")
    void invalidMovablePoint() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovablePoint(
                Point.of('e', 4),
                Point.of('g', 2)
        );

        assertThat(result).isFalse();
    }
}
