package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("위로 두칸을 이동한 뒤 좌로 한칸 이동할 수 있다.")
    void isMovable1() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovable(
                new Point('e', 4),
                new Point('d', 6)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("위로 두칸을 이동한 뒤 우로 한칸 이동할 수 있다.")
    void isMovable2() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovable(
                new Point('e', 4),
                new Point('f', 6)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아래로 두칸을 이동한 뒤 좌로 한칸 이동할 수 있다.")
    void isMovable3() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovable(
                new Point('e', 4),
                new Point('d', 2)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아래로 두칸을 이동한 뒤 우로 한칸 이동할 수 있다.")
    void isMovable4() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovable(
                new Point('e', 4),
                new Point('f', 2)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("우로 두칸을 이동한 뒤 위로 한칸 이동할 수 있다.")
    void isMovable5() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovable(
                new Point('e', 4),
                new Point('g', 5)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("우로 두칸을 이동한 뒤 아래로 한칸 이동할 수 있다.")
    void isMovable6() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovable(
                new Point('e', 4),
                new Point('g', 3)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("좌로 두칸을 이동한 뒤 위로 한칸 이동할 수 있다.")
    void isMovable7() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovable(
                new Point('e', 4),
                new Point('c', 5)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("좌로 두칸을 이동한 뒤 아래로 한칸 이동할 수 있다.")
    void isMovable8() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovable(
                new Point('e', 4),
                new Point('g', 3)
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("한 방향으로 두 칸 이동 후 다른 방향으로 한 칸이 아니면 이동할 수 없다.")
    void invalidIsMovable() {
        Knight knight = new Knight(Team.WHITE);

        boolean result = knight.isMovable(
                new Point('e', 4),
                new Point('g', 2)
        );

        assertThat(result).isFalse();
    }
}