package piece;

import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.point.File;
import domain.piece.point.Point;
import domain.piece.point.Rank;
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
}
