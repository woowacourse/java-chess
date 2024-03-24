package domain.piece.kind;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    @DisplayName("나이트는 위 - 위 오른쪽 으로 이동 가능하다.")
    void can_move_up_and_up_right() {
        final var sut = new Knight(new Point(File.D, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.E, Rank.SIX));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 위 - 위 왼쪽 으로 이동 가능하다.")
    void can_move_up_and_up_left() {
        final var sut = new Knight(new Point(File.D, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.C, Rank.SIX));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 왼쪽 - 위 왼쪽 으로 이동 가능하다.")
    void can_move_left_and_up_left() {
        final var sut = new Knight(new Point(File.D, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.B, Rank.FIVE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 왼쪽 - 아래 왼쪽 으로 이동 가능하다.")
    void can_move_left_and_down_left() {
        final var sut = new Knight(new Point(File.D, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.B, Rank.THREE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 아래 - 아래 왼쪽 으로 이동 가능하다.")
    void can_move_down_and_down_left() {
        final var sut = new Knight(new Point(File.D, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.C, Rank.TWO));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 아래 - 아래 오른쪽 으로 이동 가능하다.")
    void can_move_down_and_down_right() {
        final var sut = new Knight(new Point(File.D, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.E, Rank.TWO));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 오른쪽 - 위 오른쪽 으로 이동 가능하다.")
    void can_move_right_and_up_right() {
        final var sut = new Knight(new Point(File.D, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.F, Rank.FIVE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 오른쪽 - 아래 오른쪽 으로 이동 가능하다.")
    void can_move_right_and_down_right() {
        final var sut = new Knight(new Point(File.D, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.F, Rank.THREE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 기존 기물들(비숍,퀸,룩) 과 같이 이동 불가능하다.")
    void can_not_move_like_other_piece_case1() {
        final var sut = new Knight(new Point(File.F, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.E, Rank.FIVE));

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("나이트는 기존 기물들(비숍,퀸,룩) 과 같이 이동 불가능하다.")
    void can_not_move_like_other_piece_case2() {
        final var sut = new Knight(new Point(File.F, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.A, Rank.FOUR));

        assertThat(result).isFalse();
    }
}
