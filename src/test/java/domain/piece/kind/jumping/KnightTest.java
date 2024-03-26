package domain.piece.kind.jumping;

import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;
import fixture.PieceImpl;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.piece.attribute.point.File.*;
import static domain.piece.attribute.point.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    /*
   ........ 8
   ........ 7
   ........ 6
   ..#.#... 5
   .#...#.. 4
   ...Q.... 3
   .#...#.. 2
   ..#.#... 1
   abcdefgh
    */
    @Test
    @DisplayName("나이트는 이동경로 사이에 장애물이 있어도 해당 위치에 같은 팀 기물만 없으면 이동이 가능하다.")
    void can_move_although_has_obstacle_on_the_move_path() {
        final var sut = new Knight(new Point(D, THREE), Color.WHITE);

        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut)));
        assertThat(legalMovePoints)
                .containsExactlyInAnyOrder(
                        new Point(B, FOUR),
                        new Point(F, FOUR),
                        new Point(C, FIVE),
                        new Point(E, FIVE),
                        new Point(F, TWO),
                        new Point(B, TWO),
                        new Point(E, ONE),
                        new Point(C, ONE)
                );
    }


    /*
   ........ 8
   ........ 7
   ........ 6
   ..#.#... 5
   .F...#.. 4
   ...Q.... 3
   .#...F.. 2
   ..#.E... 1
   abcdefgh
    */
    @Test
    @DisplayName("나이트는 이동경로 사이에 장애물이 있어도 해당 위치에 같은 팀 기물만 없으면 이동이 가능하다.")
    void can_move_although_has_obstacle_on_the_move_path_has_same_team_case() {
        final var sut = new Knight(new Point(D, THREE), Color.WHITE);
        final var friend1 = new PieceImpl(new Point(B, FOUR), Color.WHITE);
        final var friend2 = new PieceImpl(new Point(F, TWO), Color.WHITE);
        final var enemy = new PieceImpl(new Point(E, ONE), Color.BLACK);

        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut, friend1, friend2, enemy)));
        assertThat(legalMovePoints)
                .containsExactlyInAnyOrder(
                        new Point(F, FOUR),
                        new Point(C, FIVE),
                        new Point(E, FIVE),
                        new Point(B, TWO),
                        new Point(E, ONE),
                        new Point(C, ONE)
                );
    }
}
