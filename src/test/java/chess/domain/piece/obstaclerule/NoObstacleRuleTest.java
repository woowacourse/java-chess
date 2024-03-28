package chess.domain.piece.obstaclerule;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoObstacleRuleTest {

    @Test
    @DisplayName("장애물 조건이 없는 경우, 장애물 조회 시 빈 리스트를 반환한다.")
    void findObstacle() {
        NoObstacleRule noObstacleRule = new NoObstacleRule();
        Position sourcePosition = Position.of(3, 2);
        Position targetPosition = Position.of(3, 4);
        Position betweenPosition = Position.of(3, 3);

        assertThat(noObstacleRule.findObstacle(sourcePosition, targetPosition,
                Map.of(betweenPosition, new Piece(PieceType.PAWN, Color.BLACK)))).isEmpty();
    }
}
