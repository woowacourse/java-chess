//package chess.domain.piece;
//
//import static chess.domain.TeamColor.WHITE;
//
//import chess.domain.Position;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class KnightTest {
//
//    @Test
//    @DisplayName("기물들이 있을 때 나이트의 움직임 테스트")
//    void movablePositions() {
//        List<Position> expectedMovablePositions = Arrays.asList(
//            Position.of(5, 2),
//            Position.of(1, 2),
//            Position.of(1, 4),
//            Position.of(2, 1)
//        );
//
//        Knight knight = new Knight(WHITE, Position.of(3, 3));
//
//        List<Position> existPiecePositions =
//            new ArrayList<>(Arrays.asList(
//                Position.of(4, 1),
//                Position.of(4, 5),
//                Position.of(5, 4)
//            ));
//
//        List<Position> enemiesPositions = Arrays.asList(
//            Position.of(1, 4),
//            Position.of(2, 1)
//        );
//        existPiecePositions.addAll(enemiesPositions);
//
//        knight.updateMovablePositions(existPiecePositions, enemiesPositions);
//
//        Assertions.assertThat(knight.movablePositions())
//            .hasSameElementsAs(expectedMovablePositions);
//    }
//}