package domain.piece.kind.pawn;

import static domain.piece.attribute.point.File.*;
import static domain.piece.attribute.point.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackPawnTest {


    /*
    ........ 8
    ..P..... 7
    ..#..... 6
    ..#..... 5
    ........ 4
    ........ 3
    ........ 2
    ........ 1
    abcdefgh
    */
    @Test
    @DisplayName("폰은 초기 위치일 경우 최대 두칸 이동이 가능하다")
    void could_move_double_step_when_never_moved() {
        final var sut = Pawn.from(new Point(C, SEVEN), Color.BLACK);

        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut)));
        assertThat(legalMovePoints).containsExactlyInAnyOrder(
                new Point(C, SIX),
                new Point(C, FIVE)
        );
    }


    /*
    ........ 8
    ..P..... 7
    ..E..... 6
    ........ 5
    ........ 4
    ........ 3
    ........ 2
    ........ 1
    abcdefgh
    */
    @Test
    @DisplayName("앞이 막혀 있으면 전진이 불가능하다.")
    void can_not_move_to_forward_when_it_is_block_by_other_pieces() {
        final var sut = Pawn.from(new Point(C, SEVEN), Color.BLACK);
        final var enemy = Pawn.from(new Point(C, SIX), Color.BLACK);
        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut, enemy)));
        assertThat(legalMovePoints).isEmpty();
    }

    /*
    ........ 8
    ..P..... 7
    .E#F.... 6
    ..E..... 5
    ........ 4
    ........ 3
    ........ 2
    ........ 1
    abcdefgh
    */
    @Test
    @DisplayName("대각 1칸 거리에 적이 있으면 해당 위치로 이동이 가능하다.")
    void can_move_diagonal_step_when_exist_enemy() {
        final var sut = Pawn.from(new Point(C, SEVEN), Color.BLACK);
        final var enemy = Pawn.from(new Point(B, SIX), Color.WHITE);
        final var enemy2 = Pawn.from(new Point(C, FIVE), Color.WHITE);
        final var friend = Pawn.from(new Point(D, SIX), Color.BLACK);
        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut, enemy, enemy2, friend)));

        assertThat(legalMovePoints).containsExactlyInAnyOrder(
                new Point(B, SIX),
                new Point(C, SIX)
        );

    }
}
