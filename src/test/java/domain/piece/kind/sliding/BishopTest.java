package domain.piece.kind.sliding;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;
import fixture.PieceImpl;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    /*
    .......# 8
    ......#. 7
    .....#.. 6
    ....#... 5
    ...#.... 4
    ..#..... 3
    .#...... 2
    B....... 1
    abcdefgh
     */
    @Test
    @DisplayName("비숍은 이동방향 중간에 기물이 없다면 대각 오른&위 방향으로 원하는 만큼 이동할 수 있다.")
    void can_move_to_right_up_diagonal_without_any_piece_between_the_move_direction() {
        Bishop sut = new Bishop(new Point(File.A, Rank.ONE), Color.BLACK);

        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut)));

        assertThat(legalMovePoints)
                .containsExactlyInAnyOrder(new Point(File.B, Rank.TWO), new Point(File.C, Rank.THREE),
                        new Point(File.D, Rank.FOUR), new Point(File.E, Rank.FIVE),
                        new Point(File.F, Rank.SIX), new Point(File.G, Rank.SEVEN),
                        new Point(File.H, Rank.EIGHT));
    }

    /*
    ........ 8
    ........ 7
    ........ 6
    ........ 5
    ...F.... 4
    ..#..... 3
    .#...... 2
    B....... 1
    abcdefgh
     */
    @Test
    @DisplayName("비숍은 이동방향 중간에 아군 기물이 있다면 그 오른&위 전 위치까지만 이동한다.")
    void can_move_to_right_up_diagonal_but_stop_previous_position_friend_piece() {
        Bishop sut = new Bishop(new Point(File.A, Rank.ONE), Color.BLACK);
        PieceImpl friend = new PieceImpl(new Point(File.D, Rank.FOUR), Color.BLACK);

        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut, friend)));

        assertThat(legalMovePoints)
                .containsExactlyInAnyOrder(new Point(File.B, Rank.TWO), new Point(File.C, Rank.THREE));
    }

    /*
    ........ 8
    ........ 7
    ........ 6
    ........ 5
    ...E.... 4
    ..#..... 3
    .#...... 2
    B....... 1
    abcdefgh
     */
    @Test
    @DisplayName("비숍은 이동방향 중간에 적군 기물이 있다면 그 오른&위 해당 위치까지만 이동한다.")
    void can_move_to_right_up_diagonal_but_stop_enemy_piece() {
        Bishop sut = new Bishop(new Point(File.A, Rank.ONE), Color.BLACK);
        PieceImpl enemy = new PieceImpl(new Point(File.D, Rank.FOUR), Color.WHITE);

        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut, enemy)));

        assertThat(legalMovePoints)
                .containsExactlyInAnyOrder(new Point(File.B, Rank.TWO), new Point(File.C, Rank.THREE),
                        new Point(File.D, Rank.FOUR));
    }

    /*
    .......B 8
    ......#. 7
    .....#.. 6
    ....#... 5
    ...#.... 4
    ..#..... 3
    .#...... 2
    #....... 1
    abcdefgh
    */
    @Test
    @DisplayName("비숍은 이동방향 중간에 기물이 없다면 대각 왼&아래 방향으로 원하는 만큼 이동할 수 있다.")
    void can_move_to_left_down_diagonal_without_any_piece_between_the_move_direction() {
        Bishop sut = new Bishop(new Point(File.H, Rank.EIGHT), Color.BLACK);

        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut)));

        assertThat(legalMovePoints)
                .containsExactlyInAnyOrder(new Point(File.B, Rank.TWO), new Point(File.C, Rank.THREE),
                        new Point(File.D, Rank.FOUR), new Point(File.E, Rank.FIVE),
                        new Point(File.F, Rank.SIX), new Point(File.G, Rank.SEVEN),
                        new Point(File.A, Rank.ONE));
    }

    /*
    .......# 8
    E.....#. 7
    .#...#.. 6
    ..#.#... 5
    ...B.... 4
    ..#.F... 3
    .#...... 2
    #....... 1
    abcdefgh     */
    @Test
    @DisplayName("비숍은 대각 경로 가운데 방향으로 원하는 만큼 이동할 수 있다.")
    void can_move_to_diagonal_without_any_piece_between_the_move_direction() {
        Bishop sut = new Bishop(new Point(File.D, Rank.FOUR), Color.BLACK);
        Piece enemy = new PieceImpl(new Point(File.A, Rank.SEVEN), Color.WHITE);
        Piece friend = new PieceImpl(new Point(File.E, Rank.THREE), Color.BLACK);
        Set<Point> legalMovePoints = sut.findLegalMovePoints(new Pieces(Set.of(sut, enemy, friend)));

        assertThat(legalMovePoints)
                .containsExactlyInAnyOrder(new Point(File.B, Rank.TWO), new Point(File.C, Rank.THREE),
                        new Point(File.A, Rank.ONE), new Point(File.E, Rank.FIVE),
                        new Point(File.F, Rank.SIX), new Point(File.G, Rank.SEVEN),
                        new Point(File.H, Rank.EIGHT), new Point(File.C, Rank.FIVE),
                        new Point(File.B, Rank.SIX), new Point(File.A, Rank.SEVEN));
    }
}
