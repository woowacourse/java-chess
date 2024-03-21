package domain.piece.kind;

import domain.piece.kind.Bishop;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @Test
    @DisplayName("비숍은 아래 오른쪽 방향으로 이동할 수 있다.")
    void can_move_up_right() {
        final var sut = new Bishop(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.D, Rank.THREE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 아래 왼쪽 방향으로 이동할 수 있다.")
    void can_move_down_left() {
        final var sut = new Bishop(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.B, Rank.THREE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 위 오른쪽 방향으로 이동할 수 있다.")
    void can_move_up_left() {
        final var sut = new Bishop(new Point(File.C, Rank.TWO), Color.BLACK);

        final var result = sut.canMove(new Point(File.D, Rank.THREE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 위 왼쪽 방향으로 이동할 수 있다.")
    void can_move_down_right() {
        final var sut = new Bishop(new Point(File.F, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.E, Rank.FIVE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 그외 방향으로 이동할 수 없다.")
    void can_not_move_other_direction() {
        final var sut = new Bishop(new Point(File.F, Rank.EIGHT), Color.BLACK);

        final var result = sut.canMove(new Point(File.F, Rank.ONE));

        assertThat(result).isFalse();
    }
}
