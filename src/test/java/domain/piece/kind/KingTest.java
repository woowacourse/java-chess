package domain.piece.kind;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @Test
    @DisplayName("킹은 아래 오른쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_up_right() {
        final var sut = new King(C4, Color.BLACK);

        final var result = sut.canMove(new Point(File.D, Rank.FOUR));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 아래 왼쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_down_left() {
        final var sut = new King(C4, Color.BLACK);

        final var result = sut.canMove(B3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 위 오른쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_up_left() {
        final var sut = new King(C2, Color.BLACK);

        final var result = sut.canMove(D3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 위 왼쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_down_right() {
        final var sut = new King(F4, Color.BLACK);

        final var result = sut.canMove(E5);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 오른쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_right() {
        final var sut = new King(C4, Color.BLACK);

        final var result = sut.canMove(D4);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 아래 방향으로 한칸 이동할 수 있다.")
    void can_move_down() {
        final var sut = new King(C4, Color.BLACK);

        final var result = sut.canMove(C3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 위 방향으로 한칸 이동할 수 있다.")
    void can_move_up() {
        final var sut = new King(C2, Color.BLACK);

        final var result = sut.canMove(C3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 왼쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_left() {
        final var sut = new King(F4, Color.BLACK);

        final var result = sut.canMove(E4);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 한칸 이상은 이동할 수 없다.")
    void can_not_move_double() {
        final var sut = new King(F4, Color.BLACK);

        final var result = sut.canMove(D4);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("킹은 한칸 이상은 이동할 수 없다.")
    void can_not_move_double_case() {
        final var sut = new King(C4, Color.BLACK);

        final var result = sut.canMove(A2);

        assertThat(result).isFalse();
    }
}
