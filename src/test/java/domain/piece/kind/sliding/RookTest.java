package domain.piece.kind.sliding;

import static domain.piece.attribute.Color.BLACK;
import static domain.piece.attribute.Color.WHITE;
import static domain.piece.attribute.point.File.A;
import static domain.piece.attribute.point.File.B;
import static domain.piece.attribute.point.File.C;
import static domain.piece.attribute.point.File.D;
import static domain.piece.attribute.point.File.E;
import static domain.piece.attribute.point.File.F;
import static domain.piece.attribute.point.File.G;
import static domain.piece.attribute.point.Rank.EIGHT;
import static domain.piece.attribute.point.Rank.FIVE;
import static domain.piece.attribute.point.Rank.FOUR;
import static domain.piece.attribute.point.Rank.ONE;
import static domain.piece.attribute.point.Rank.SEVEN;
import static domain.piece.attribute.point.Rank.SIX;
import static domain.piece.attribute.point.Rank.THREE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Pieces;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;
import fixture.PieceImpl;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    /*
    #....... 8
    #....... 7
    #....... 6
    #....... 5
    #....... 4
    #....... 3
    #....... 2
    L####### 1
    abcdefgh
     */
    @Test
    @DisplayName("룩은 중간에 장애물이 없을 경우위방향으로 일직선 전진이 가능하다.")
    void can_move_straight_forward_to_up_without_obstacle() {
        final var sut = new Rook(new Point(A, ONE), WHITE);
        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut)));
        Set<Point> expected = new HashSet<>();

        Arrays.stream(Rank.values())
                .filter(rank -> rank != ONE)
                .forEach(rank -> expected.add(new Point(A, rank)));
        Arrays.stream(File.values())
                .filter(file -> file != A)
                .forEach(file -> expected.add(new Point(file, ONE)));

        assertThat(legalMovePoints).isEqualTo(expected);
    }

    /*
    ....#... 8
    ....#... 7
    ....#... 6
    ....#... 5
    E###L#F. 4
    ....F... 3
    ........ 2
    ........ 1
    abcdefgh
     */
    @Test
    @DisplayName("중간에 기물이 가로 막고 있다면 넘어 갈수 없다.")
    void can_not_move_beyond_obstacle() {
        final var sut = new Rook(new Point(E, FOUR), WHITE);
        final var friend1 = new PieceImpl(new Point(E, THREE), WHITE);
        final var friend2 = new PieceImpl(new Point(G, FOUR), WHITE);
        final var enemy = new PieceImpl(new Point(A, FOUR), BLACK);
        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut, friend1, friend2, enemy)));

        assertThat(legalMovePoints).containsExactlyInAnyOrder(
                new Point(A, FOUR),
                new Point(B, FOUR),
                new Point(C, FOUR),
                new Point(D, FOUR),
                new Point(F, FOUR),
                new Point(E, FIVE),
                new Point(E, SIX),
                new Point(E, SEVEN),
                new Point(E, EIGHT)
        );
    }
}
