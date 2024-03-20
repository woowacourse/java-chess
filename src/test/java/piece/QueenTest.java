package piece;

import domain.Queen;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    @Test
    @DisplayName("퀸은 아래 오른쪽 방향으로 이동할 수 있다.")
    void can_move_up_right() {
        final var sut = new Queen(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.D, Rank.THREE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 아래 왼쪽 방향으로 이동할 수 있다.")
    void can_move_down_left() {
        final var sut = new Queen(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.B, Rank.THREE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 위 오른쪽 방향으로 이동할 수 있다.")
    void can_move_up_left() {
        final var sut = new Queen(new Point(File.C, Rank.TWO), Color.BLACK);

        final var result = sut.canMove(new Point(File.D, Rank.THREE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 위 왼쪽 방향으로 이동할 수 있다.")
    void can_move_down_right() {
        final var sut = new Queen(new Point(File.F, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.E, Rank.FIVE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 오른쪽 방향으로 이동할 수 있다.")
    void can_move_right() {
        final var sut = new Queen(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.E, Rank.FOUR));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 아래 방향으로 이동할 수 있다.")
    void can_move_down() {
        final var sut = new Queen(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.C, Rank.ONE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 위 방향으로 이동할 수 있다.")
    void can_move_up() {
        final var sut = new Queen(new Point(File.C, Rank.TWO), Color.BLACK);

        final var result = sut.canMove(new Point(File.C, Rank.EIGHT));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 왼쪽 방향으로 이동할 수 있다.")
    void can_move_left() {
        final var sut = new Queen(new Point(File.F, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.A, Rank.FOUR));

        assertThat(result).isTrue();
    }
}
