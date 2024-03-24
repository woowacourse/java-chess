package chess.util;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.position.Movement;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RouteCalculatorTest {

//    @DisplayName("수직으로 사이에 있는 위치들을 반환한다.")
//    @Test
//    void getVerticalMiddlePositions() {
//        // given
//        final Movement movement = new Movement(new Position(File.F, Rank.EIGHT), new Position(File.F, Rank.ONE));
//
//        // when
//        final Set<Position> positions = RouteCalculator.getVerticalPositions(movement);
//
//        // then
//        assertThat(positions).containsExactlyInAnyOrder(
//                new Position(File.F, Rank.SEVEN),
//                new Position(File.F, Rank.SIX),
//                new Position(File.F, Rank.FIVE),
//                new Position(File.F, Rank.FOUR),
//                new Position(File.F, Rank.THREE),
//                new Position(File.F, Rank.TWO)
//        );
//    }
//
//    @DisplayName("수평으로 사이에 있는 위치들을 반환한다.")
//    @Test
//    void getHorizontalMiddlePositions() {
//        // given
//        final Movement movement = new Movement(new Position(File.H, Rank.FOUR), new Position(File.D, Rank.FOUR));
//
//        // when
//        final Set<Position> positions = RouteCalculator.getHorizontalPositions(movement);
//
//        // then
//        assertThat(positions).containsExactlyInAnyOrder(
//                new Position(File.G, Rank.FOUR),
//                new Position(File.F, Rank.FOUR),
//                new Position(File.E, Rank.FOUR)
//        );
//    }
//
//    @DisplayName("오른쪽으로 사이에 있는 위치들을 반환한다.")
//    @Test
//    void getRightDiagonalMiddlePositions() {
//        // given
//        final Movement movement = new Movement(new Position(File.C, Rank.THREE), new Position(File.F, Rank.SIX));
//
//        // when
//        final Set<Position> positions = RouteCalculator.getMiddlePositions(movement);
//
//        // then
//        assertThat(positions).containsExactlyInAnyOrder(
//                new Position(File.D, Rank.FOUR),
//                new Position(File.E, Rank.FIVE)
//        );
//    }
//
//    @DisplayName("왼쪽으로 사이에 있는 위치들을 반환한다.")
//    @Test
//    void getLeftDiagonalMiddlePositions() {
//        // given
//        final Movement movement = new Movement(new Position(File.B, Rank.SIX), new Position(File.E, Rank.THREE));
//
//        // when
//        final Set<Position> positions = RouteCalculator.getMiddlePositions(movement);
//
//        // then
//        assertThat(positions).containsExactlyInAnyOrder(
//                new Position(File.C, Rank.FIVE),
//                new Position(File.D, Rank.FOUR)
//        );
//    }
}
