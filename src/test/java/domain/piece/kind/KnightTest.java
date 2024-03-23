package domain.piece.kind;


import domain.piece.attribute.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    @DisplayName("나이트는 위 - 위 오른쪽 으로 이동 가능하다.")
    void can_move_up_and_up_right() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(E6);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 위 - 위 왼쪽 으로 이동 가능하다.")
    void can_move_up_and_up_left() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(C6);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 왼쪽 - 위 왼쪽 으로 이동 가능하다.")
    void can_move_left_and_up_left() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(B5);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 왼쪽 - 아래 왼쪽 으로 이동 가능하다.")
    void can_move_left_and_down_left() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(B3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 아래 - 아래 왼쪽 으로 이동 가능하다.")
    void can_move_down_and_down_left() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(C2);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 아래 - 아래 오른쪽 으로 이동 가능하다.")
    void can_move_down_and_down_right() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(E2);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 오른쪽 - 위 오른쪽 으로 이동 가능하다.")
    void can_move_right_and_up_right() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(F5);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 오른쪽 - 아래 오른쪽 으로 이동 가능하다.")
    void can_move_right_and_down_right() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(F3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 기존 기물들(비숍,퀸,룩) 과 같이 이동 불가능하다.")
    void can_not_move_like_other_piece_case1() {
        final var sut = new Knight(F4, Color.BLACK);

        final var result = sut.canMove(E5);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("나이트는 기존 기물들(비숍,퀸,룩) 과 같이 이동 불가능하다.")
    void can_not_move_like_other_piece_case2() {
        final var sut = new Knight(F4, Color.BLACK);

        final var result = sut.canMove(A4);

        assertThat(result).isFalse();
    }
}
