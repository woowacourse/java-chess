package domain.piece.kind;

import domain.piece.attribute.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("검은색 폰은 아래로 한칸 이동할 수 있다.")
    void black_pawn_can_move_down() {
        final var sut = new Pawn(C4, Color.BLACK);

        final var result = sut.canMove(C3);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("검은색 폰은 위로 한칸 이동할 수 없다.")
    void black_pawn_can_not_move_down() {
        final var sut = new Pawn(C4, Color.BLACK);

        final var result = sut.canMove(C5);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("검은색 폰은 아래 오른쪽 방향으로 대각선 이동할 수 있다.")
    void black_pawn_can_move_down_right() {
        final var sut = new Pawn(C4, Color.BLACK);

        final var result = sut.canMove(D3);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("검은색 폰은 아래 왼쪽 방향으로 대각선 이동할 수 있다.")
    void black_pawn_can_move_down_left() {
        final var sut = new Pawn(C4, Color.BLACK);

        final var result = sut.canMove(B3);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("하얀색 폰은 위로 한칸 이동할 수 있다.")
    void white_pawn_can_move_up() {
        final var sut = new Pawn(C4, Color.WHITE);

        final var result = sut.canMove(C5);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("하얀색 폰은 아래로 한칸 이동할 수 없다.")
    void white_pawn_can_move_down() {
        final var sut = new Pawn(C4, Color.WHITE);

        final var result = sut.canMove(C3);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("하얀색 폰은 위 방향으로 대각선 이동할 수 있다.")
    void white_pawn_can_move_up_left() {
        final var sut = new Pawn(C4, Color.WHITE);

        final var result = sut.canMove(B5);
        assertThat(result).isTrue();
    }
}
