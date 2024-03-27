package chessgame.domain.piece.kind.sliding;

import chessgame.domain.piece.Pieces;
import chessgame.domain.piece.attribute.Color;
import chessgame.domain.piece.attribute.point.Point;
import chessgame.fixture.PieceImpl;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chessgame.domain.piece.attribute.point.File.A;
import static chessgame.domain.piece.attribute.point.File.B;
import static chessgame.domain.piece.attribute.point.File.C;
import static chessgame.domain.piece.attribute.point.File.D;
import static chessgame.domain.piece.attribute.point.File.E;
import static chessgame.domain.piece.attribute.point.File.F;
import static chessgame.domain.piece.attribute.point.File.G;
import static chessgame.domain.piece.attribute.point.File.H;
import static chessgame.domain.piece.attribute.point.Rank.EIGHT;
import static chessgame.domain.piece.attribute.point.Rank.FIVE;
import static chessgame.domain.piece.attribute.point.Rank.FOUR;
import static chessgame.domain.piece.attribute.point.Rank.ONE;
import static chessgame.domain.piece.attribute.point.Rank.SEVEN;
import static chessgame.domain.piece.attribute.point.Rank.SIX;
import static chessgame.domain.piece.attribute.point.Rank.THREE;
import static chessgame.domain.piece.attribute.point.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    /*
   ...#.... 8
   ...#...# 7
   #..#..#. 6
   .#.#.#.. 5
   ..###... 4
   ###Q#### 3
   ..###... 2
   .#.#.#.. 1
   abcdefgh
    */
    @Test
    @DisplayName("퀸은 경로 사이에 방해 기물이 없다면 앞뒤양옆, 모든 대각 방향으로 이동가능하다.")
    void can_move_up_down_left_right_and_all_of_diagonal_side() {
        final var sut = new Queen(new Point(D, THREE), Color.BLACK);

        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut)));

        assertThat(legalMovePoints).containsExactlyInAnyOrder(
                new Point(B, ONE), new Point(D, ONE),
                new Point(F, ONE), new Point(C, TWO),
                new Point(D, TWO), new Point(E, TWO),
                new Point(A, THREE), new Point(B, THREE),
                new Point(C, THREE), new Point(E, THREE),
                new Point(F, THREE), new Point(G, THREE),
                new Point(H, THREE), new Point(C, FOUR),
                new Point(D, FOUR), new Point(E, FOUR),
                new Point(B, FIVE), new Point(D, FIVE),
                new Point(F, FIVE), new Point(D, SEVEN),
                new Point(G, SIX), new Point(D, SIX),
                new Point(A, SIX), new Point(H, SEVEN),
                new Point(D, EIGHT)
        );
    }


    /*
   ........ 8
   ........ 7
   #....... 6
   .#.F.E.. 5
   ..###... 4
   ###Q#E.. 3
   ..F##... 2
   ...#.#.. 1
   abcdefgh
    */
    @Test
    @DisplayName("퀸은 경로 사이에 방해 기물이 없다면 앞뒤양옆, 모든 대각 방향으로 이동가능하다.")
    void should_stop_when_move_up_down_left_right_and_all_of_diagonal_side_when_has_obstacle() {
        final var sut = new Queen(new Point(D, THREE), Color.BLACK);
        final var enemy1 = new PieceImpl(new Point(F, FIVE), Color.WHITE);
        final var enemy2 = new PieceImpl(new Point(F, THREE), Color.WHITE);
        final var friend1 = new PieceImpl(new Point(C ,TWO), Color.BLACK);
        final var friend2 = new PieceImpl(new Point(D, FIVE), Color.BLACK);

        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut, enemy1, enemy2, friend1, friend2)));

        assertThat(legalMovePoints).containsExactlyInAnyOrder(
                new Point(D, ONE), new Point(F, ONE),
                new Point(D, TWO), new Point(E, TWO),
                new Point(A, THREE), new Point(B, THREE),
                new Point(C, THREE), new Point(E, THREE),
                new Point(F, THREE), new Point(C, FOUR),
                new Point(D, FOUR), new Point(E, FOUR),
                new Point(B, FIVE), new Point(F, FIVE),
                new Point(A, SIX)
        );
    }

}
