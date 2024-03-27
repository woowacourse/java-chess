package chessgame.domain.piece.kind.pawn;

import static chessgame.domain.piece.attribute.point.File.B;
import static chessgame.domain.piece.attribute.point.File.C;
import static chessgame.domain.piece.attribute.point.File.D;
import static chessgame.domain.piece.attribute.point.Rank.FIVE;
import static chessgame.domain.piece.attribute.point.Rank.FOUR;
import static chessgame.domain.piece.attribute.point.Rank.THREE;
import static chessgame.domain.piece.attribute.point.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chessgame.domain.piece.Pieces;
import chessgame.domain.piece.attribute.Color;
import chessgame.domain.piece.attribute.point.Point;
import chessgame.domain.piece.kind.pawn.Pawn;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhitePawnTest {



    /*
    ........ 8
    ........ 7
    ........ 6
    ........ 5
    ..#..... 4
    ..#..... 3
    ..P..... 2
    ........ 1
    abcdefgh
    */
    @Test
    @DisplayName("폰은 초기 위치일 경우 최대 두칸 이동이 가능하다")
    void could_move_double_step_when_never_moved() {
        final var sut = Pawn.from(new Point(C, TWO), Color.WHITE);

        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut)));
        assertThat(legalMovePoints).containsExactlyInAnyOrder(
                new Point(C, THREE),
                new Point(C, FOUR)
        );
    }


    /*
    ........ 8
    ........ 7
    ........ 6
    ........ 5
    ........ 4
    ..E..... 3
    ..P..... 2
    ........ 1
    abcdefgh
    */
    @Test
    @DisplayName("앞이 막혀 있으면 전진이 불가능하다.")
    void can_not_move_to_forward_when_it_is_block_by_other_pieces() {
        final var sut = Pawn.from(new Point(C, TWO), Color.WHITE);
        final var enemy = Pawn.from(new Point(C, THREE), Color.BLACK);
        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut, enemy)));
        assertThat(legalMovePoints).isEmpty();
    }

    /*
    ........ 8
    ........ 7
    ........ 6
    ..E..... 5
    ..#..... 4
    .F#E.... 3
    ..P..... 2
    ........ 1
    abcdefgh
    */
    @Test
    @DisplayName("대각 1칸 거리에 적이 있으면 해당 위치로 이동이 가능하다.")
    void can_move_diagonal_step_when_exist_enemy() {
        final var sut = Pawn.from(new Point(C, TWO), Color.WHITE);
        final var enemy = Pawn.from(new Point(D, THREE), Color.BLACK);
        final var enemy2 = Pawn.from(new Point(C, FIVE), Color.BLACK);
        final var friend = Pawn.from(new Point(B, THREE), Color.WHITE);
        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut, enemy, enemy2, friend)));
        assertThat(legalMovePoints).containsExactlyInAnyOrder(
                new Point(C, THREE),
                new Point(C, FOUR),
                new Point(D, THREE)
        );

    }
}
