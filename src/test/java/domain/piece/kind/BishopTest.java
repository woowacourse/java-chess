package domain.piece.kind;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @Test
    @DisplayName("비숍은 아래 오른쪽 방향으로 이동할 수 있다.")
    void can_move_up_right() {
        final var sut = new Bishop(C4, Color.BLACK);

        final var result = sut.canMove(D3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 아래 왼쪽 방향으로 이동할 수 있다.")
    void can_move_down_left() {
        final var sut = new Bishop(C4, Color.BLACK);

        final var result = sut.canMove(B3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 위 오른쪽 방향으로 이동할 수 있다.")
    void can_move_up_left() {
        final var sut = new Bishop(C2, Color.BLACK);

        final var result = sut.canMove(D3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 위 왼쪽 방향으로 이동할 수 있다.")
    void can_move_down_right() {
        final var sut = new Bishop(F4, Color.BLACK);

        final var result = sut.canMove(E5);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 그외 방향으로 이동할 수 없다.")
    void can_not_move_other_direction() {
        final var sut = new Bishop(F8, Color.BLACK);

        final var result = sut.canMove(F1);

        assertThat(result).isFalse();
    }
}
