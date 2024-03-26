package domain.piece.kind.jumping;

import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;
import fixture.PieceImpl;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.piece.attribute.point.File.*;
import static domain.piece.attribute.point.Rank.FOUR;
import static domain.piece.attribute.point.Rank.THREE;
import static domain.piece.attribute.point.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @Test
    @DisplayName("킹은 아래 오른쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_up_right() {
        final var sut = new King(new Point(C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(D, Rank.FOUR));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 아래 왼쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_down_left() {
        final var sut = new King(new Point(C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(B, THREE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 위 오른쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_up_left() {
        final var sut = new King(new Point(C, Rank.TWO), Color.BLACK);

        final var result = sut.canMove(new Point(D, THREE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 위 왼쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_down_right() {
        final var sut = new King(new Point(F, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(E, Rank.FIVE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 오른쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_right() {
        final var sut = new King(new Point(C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(D, Rank.FOUR));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 아래 방향으로 한칸 이동할 수 있다.")
    void can_move_down() {
        final var sut = new King(new Point(C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(C, THREE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 위 방향으로 한칸 이동할 수 있다.")
    void can_move_up() {
        final var sut = new King(new Point(C, Rank.TWO), Color.BLACK);

        final var result = sut.canMove(new Point(C, THREE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 왼쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_left() {
        final var sut = new King(new Point(F, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(E, Rank.FOUR));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 한칸 이상은 이동할 수 없다.")
    void can_not_move_double() {
        final var sut = new King(new Point(F, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(D, Rank.FOUR));

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("킹은 한칸 이상은 이동할 수 없다.")
    void can_not_move_double_case() {
        final var sut = new King(new Point(C, Rank.FOUR), Color.BLACK);

        final var result = sut.canMove(new Point(A, Rank.TWO));

        assertThat(result).isFalse();
    }


    /*
   ........ 8
   ........ 7
   ........ 6
   ........ 5
   ..###... 4
   ..#Q#... 3
   ..###... 2
   ........ 1
   abcdefgh
    */
    @Test
    @DisplayName("킹은 이동위치에 아군이 없을 시 좌우양옆 모든 대각 방향으로 1칸씩 이동할 수 있다.")
    void can_move_one_step_to_any_direction() {
        final var sut = new King(new Point(D, THREE), Color.BLACK);

        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut)));

        assertThat(legalMovePoints).containsExactlyInAnyOrder(
                new Point(C, FOUR), new Point(D, FOUR),
                new Point(E, FOUR), new Point(C, THREE),
                new Point(E, THREE), new Point(C, TWO),
                new Point(D, TWO), new Point(E, TWO)
        );
    }


    /*
   ........ 8
   ........ 7
   ........ 6
   ........ 5
   ..FFE... 4
   ..FQF... 3
   ..EFE... 2
   ........ 1
   abcdefgh
    */
    @Test
    @DisplayName("킹은 이동위치에 아군이 있을 시 그 위치를 제외하고 1칸 이동 가능하다.")
    void can_move_one_step_to_any_direction_except_exist_friend_point() {
        final var sut = new King(new Point(D, THREE), Color.BLACK);
        final var friend1 = new PieceImpl(new Point(D, TWO), Color.BLACK);
        final var friend2 = new PieceImpl(new Point(C, THREE), Color.BLACK);
        final var friend3 = new PieceImpl(new Point(E, THREE), Color.BLACK);
        final var friend4 = new PieceImpl(new Point(C, FOUR), Color.BLACK);
        final var friend5 = new PieceImpl(new Point(D, FOUR), Color.BLACK);
        final var enemy1 = new PieceImpl(new Point(C, TWO), Color.WHITE);
        final var enemy2 = new PieceImpl(new Point(E, TWO), Color.WHITE);
        final var enemy3 = new PieceImpl(new Point(E, FOUR), Color.WHITE);

        Set<Point> legalMovePoints = sut.findLegalMovePoints(
                new Pieces(Set.of(sut, friend1, friend2, friend3, friend4, friend5, enemy1, enemy2, enemy3)));

        assertThat(legalMovePoints).containsExactlyInAnyOrder(
                new Point(C, TWO), new Point(E, FOUR), new Point(E, TWO)
        );
    }
}
