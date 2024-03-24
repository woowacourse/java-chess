package domain.piece.kind;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @Test
    @DisplayName("룩은 오른쪽 방향으로 이동할 수 있다.")
    void can_move_right() {
        final var sut = new Rook(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.E, Rank.FOUR));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("룩은 아래 방향으로 이동할 수 있다.")
    void can_move_down() {
        final var sut = new Rook(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.C, Rank.ONE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("룩은 위 방향으로 이동할 수 있다.")
    void can_move_up() {
        final var sut = new Rook(new Point(File.C, Rank.TWO), Color.BLACK);

        final var result = sut.canMove(new Point(File.C, Rank.EIGHT));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("룩은 왼쪽 방향으로 이동할 수 있다.")
    void can_move_left() {
        final var sut = new Rook(new Point(File.F, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.A, Rank.FOUR));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("룩은 그외 방향으로 이동할 수 없다.")
    void can_not_move_other_direction() {
        final var sut = new Rook(new Point(File.F, Rank.EIGHT), Color.BLACK);

        final var result = sut.canMove(new Point(File.E, Rank.SEVEN));

        assertThat(result).isFalse();
    }
}
