package domain.piece.kind;

import domain.piece.attribute.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @Test
    @DisplayName("룩은 오른쪽 방향으로 이동할 수 있다.")
    void can_move_right() {
        final var sut = new Rook(C4, Color.BLACK);

        final var result = sut.canMove(E4);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("룩은 아래 방향으로 이동할 수 있다.")
    void can_move_down() {
        final var sut = new Rook(C4, Color.BLACK);

        final var result = sut.canMove(C1);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("룩은 위 방향으로 이동할 수 있다.")
    void can_move_up() {
        final var sut = new Rook(C2, Color.BLACK);

        final var result = sut.canMove(C8);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("룩은 왼쪽 방향으로 이동할 수 있다.")
    void can_move_left() {
        final var sut = new Rook(F4, Color.BLACK);

        final var result = sut.canMove(A4);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("룩은 그외 방향으로 이동할 수 없다.")
    void can_not_move_other_direction() {
        final var sut = new Rook(F8, Color.BLACK);

        final var result = sut.canMove(E7);

        assertThat(result).isFalse();
    }
}
