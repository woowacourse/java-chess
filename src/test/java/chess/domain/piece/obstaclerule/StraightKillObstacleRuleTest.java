package chess.domain.piece.obstaclerule;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StraightKillObstacleRuleTest {

    /*
     * ........
     * ........
     * ........
     * ........
     * ........ <- target
     * ..p.....
     * ..p..... <- source
     * ........
     * */
    @ParameterizedTest
    @CsvSource({"BLACK, WHITE"})
    @DisplayName("출발 위치와 도착 위치가 직선 이동이고 사이에 말이 존재할 경우, 팀에 상관없이 그 말은 장애물이다.")
    void findObstacleBetweenSourceAndTarget(final Color color) {
        StraightKillObstacleRule straightKillObstacleRule = new StraightKillObstacleRule();

        Position sourcePosition = Position.of(3, 2);
        Position targetPosition = Position.of(3, 4);
        Position obstaclePosition = Position.of(3, 3);

        List<Position> obstacle = straightKillObstacleRule.findObstacle(sourcePosition, targetPosition,
                Map.of(sourcePosition, new Piece(PieceType.PAWN, Color.BLACK),
                        targetPosition, Piece.getEmptyPiece(),
                        obstaclePosition, new Piece(PieceType.PAWN, color)));

        assertThat(obstacle).containsExactly(obstaclePosition);
    }

    /*
     * ........
     * ........
     * ........
     * ........
     * ..P..... <- target
     * ........
     * ..p..... <- source
     * ........
     * */
    @Test
    @DisplayName("출발 위치와 도착 위치가 직선 이동이고 사이에 말이 존재하지 않을 경우, 도착 위치의 말이 상대편 말이면 공격이 가능하므로 장애물이 아니다.")
    void findObstacleWhenTargetIsOtherSide() {
        StraightKillObstacleRule straightKillObstacleRule = new StraightKillObstacleRule();

        Position sourcePosition = Position.of(3, 2);
        Position targetPosition = Position.of(3, 4);

        List<Position> obstacle = straightKillObstacleRule.findObstacle(sourcePosition, targetPosition,
                Map.of(sourcePosition, new Piece(PieceType.PAWN, Color.BLACK),
                        targetPosition, new Piece(PieceType.PAWN, Color.WHITE)));

        assertThat(obstacle).isEqualTo(List.of());
    }

    /*
     * ........
     * ........
     * ........
     * ........
     * ........ <- target
     * ........
     * ..p..... <- source
     * ........
     * */
    @Test
    @DisplayName("출발 위치와 도착 위치가 직선 이동이고 사이에 말이 존재하지 않을 경우, 도착 위치의 말이 비어있다면 말이면 공격이 가능하므로 장애물이 아니다.")
    void findObstacleWhenTargetIsEmpty() {
        StraightKillObstacleRule straightKillObstacleRule = new StraightKillObstacleRule();

        Position sourcePosition = Position.of(3, 2);
        Position targetPosition = Position.of(3, 4);

        List<Position> obstacle = straightKillObstacleRule.findObstacle(sourcePosition, targetPosition,
                Map.of(sourcePosition, new Piece(PieceType.PAWN, Color.BLACK),
                        targetPosition, Piece.getEmptyPiece()));

        assertThat(obstacle).isEqualTo(List.of());
    }

    /*
     * ........
     * ........
     * ........
     * ........
     * ..p..... <- target
     * ........
     * ..p..... <- source
     * ........
     * */
    @Test
    @DisplayName("출발 위치와 도착 위치가 직선 이동이고 사이에 말이 존재하지 않을 경우, 도착 위치의 말이 우리편 말이면 장애물이다.")
    void findObstacleWhenTargetIsOurSide() {
        StraightKillObstacleRule straightKillObstacleRule = new StraightKillObstacleRule();

        Position sourcePosition = Position.of(3, 2);
        Position targetPosition = Position.of(3, 4);

        List<Position> obstacle = straightKillObstacleRule.findObstacle(sourcePosition, targetPosition,
                Map.of(sourcePosition, new Piece(PieceType.PAWN, Color.BLACK),
                        targetPosition, new Piece(PieceType.PAWN, Color.BLACK)));

        assertThat(obstacle).containsExactly(targetPosition);
    }
}
