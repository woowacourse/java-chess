package domain.piece.kind;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @Test
    @DisplayName("검은색 폰은 아래로 한칸 이동할 수 있다.")
    void black_pawn_can_move_down() {
        final var sut = new Pawn(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.C, Rank.THREE));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("검은색 폰은 위로 한칸 이동할 수 없다.")
    void black_pawn_can_not_move_down() {
        final var sut = new Pawn(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.C, Rank.FIVE));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("검은색 폰은 아래 오른쪽 방향으로 대각선 이동할 수 있다.")
    void black_pawn_can_move_down_right() {
        final var sut = new Pawn(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.D, Rank.THREE));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("검은색 폰은 아래 왼쪽 방향으로 대각선 이동할 수 있다.")
    void black_pawn_can_move_down_left() {
        final var sut = new Pawn(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.B, Rank.THREE));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("하얀색 폰은 위로 한칸 이동할 수 있다.")
    void white_pawn_can_move_up() {
        final var sut = new Pawn(new Point(File.C, Rank.FOUR), Color.WHITE);

        final var result = sut.canMove(new Point(File.C, Rank.FIVE));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("하얀색 폰은 아래로 한칸 이동할 수 없다.")
    void white_pawn_can_move_down() {
        final var sut = new Pawn(new Point(File.C, Rank.FOUR), Color.WHITE);

        final var result = sut.canMove(new Point(File.C, Rank.THREE));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("하얀색 폰은 위 방향으로 대각선 이동할 수 있다.")
    void white_pawn_can_move_up_left() {
        final var sut = new Pawn(new Point(File.C, Rank.FOUR), Color.WHITE);

        final var result = sut.canMove(new Point(File.B, Rank.FIVE));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("검은색 폰이 아직 이동하지 않았다면 아래로 두칸 이동할 수 있다.")
    void black_pawn_can_move_down_double() {
        final var sut = new Pawn(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.C, Rank.TWO));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("대각선으로 두칸 이동할 수 없다.")
    void black_pawn_can_not_move_diagonal_left_double() {
        final var sut = new Pawn(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.A, Rank.TWO));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("대각선으로 두칸 이동할 수 없다.")
    void black_pawn_can_not_move_diagonal_right_double() {
        final var sut = new Pawn(new Point(File.C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(File.E, Rank.TWO));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("흰색 폰이 아직 이동하지 않았다면 위로 두칸 이동할 수 있다")
    void white_pawn_can_move_up_double() {
        final var sut = new Pawn(new Point(File.C, Rank.FOUR), Color.WHITE);

        final var result = sut.canMove(new Point(File.C, Rank.SIX));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("대각선으로 두칸 이동할 수 없다.")
    void white_pawn_can_not_move_diagonal_left_double() {
        final var sut = new Pawn(new Point(File.C, Rank.FOUR), Color.WHITE);

        final var result = sut.canMove(new Point(File.E, Rank.SIX));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("대각선으로 두칸 이동할 수 없다.")
    void white_pawn_can_not_move_diagonal_right_double() {
        final var sut = new Pawn(new Point(File.C, Rank.FOUR), Color.WHITE);

        final var result = sut.canMove(new Point(File.A, Rank.SIX));
        assertThat(result).isFalse();
    }
}
