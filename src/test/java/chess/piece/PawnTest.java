package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Color;
import chess.Fixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @DisplayName("백팀이라면 위로 한 칸 이동 가능하다.")
    @Test
    void move_up_one_step_white_color() {
        Pawn pawn = new Pawn(Color.WHITE, Fixtures.D2);

        pawn.move(0, 1);

        assertThat(pawn.getPosition()).isEqualTo(Fixtures.D3);
    }

    @DisplayName("흑팀이라면 아래로 한 칸 이동 가능하다.")
    @Test
    void move_down_one_step_black_color() {
        Pawn pawn = new Pawn(Color.BLACK, Fixtures.D7);

        pawn.move(0, -1);

        assertThat(pawn.getPosition()).isEqualTo(Fixtures.D6);
    }

    @DisplayName("백팀이라면 처음 이동할 때, 위로 두 칸 이동 가능하다.")
    @Test
    void move_up_two_step_white_color_first_move() {
        Pawn pawn = new Pawn(Color.WHITE, Fixtures.D2);

        pawn.move(0, 2);

        assertThat(pawn.getPosition()).isEqualTo(Fixtures.D4);
    }

    @DisplayName("흑팀이라면 처음 이동할 때 아래로 두 칸 이동 가능하다.")
    @Test
    void move_down_two_step_black_color_first_move() {
        Pawn pawn = new Pawn(Color.BLACK, Fixtures.D7);

        pawn.move(0, -2);

        assertThat(pawn.getPosition()).isEqualTo(Fixtures.D5);
    }

    @DisplayName("처음 이동 아니라면, 백팀은 위로 두 칸 이동할 수 없다.")
    @Test
    void cant_move_up_two_step_white_color_not_first_move() {
        Pawn pawn = new Pawn(Color.WHITE, Fixtures.D3);

        assertThatThrownBy(()->pawn.move(0, 2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("처음 이동 아니라면, 흑팀은 아래로 두 칸 이동할 수 없다.")
    @Test
    void cnat_move_down_two_step_black_color_not_first_move() {
        Pawn pawn = new Pawn(Color.BLACK, Fixtures.D6);

        assertThatThrownBy(()->pawn.move(0, -2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("백팀이라면 아래로 이동 불가능하다.")
    @Test
    void cant_move_down_white_color() {
        Pawn pawn = new Pawn(Color.WHITE, Fixtures.D2);

        assertThatThrownBy(()->pawn.move(0, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("흑팀이라면 위로 이동 불가능하다.")
    @Test
    void cant_move_up_black_color() {
        Pawn pawn = new Pawn(Color.BLACK, Fixtures.D7);

        assertThatThrownBy(()->pawn.move(0, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("백팀은 위로 두 칸 이상 이동할 수 없다.")
    @Test
    void cant_move_up_over_two_step_white_color() {
        Pawn pawn = new Pawn(Color.WHITE, Fixtures.D3);

        assertThatThrownBy(()->pawn.move(0, 3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("흑팀은 아래로 두 칸 이상 이동할 수 없다.")
    @Test
    void cnat_move_down_over_two_step_black_color() {
        Pawn pawn = new Pawn(Color.BLACK, Fixtures.D6);

        assertThatThrownBy(()->pawn.move(0, -3))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
