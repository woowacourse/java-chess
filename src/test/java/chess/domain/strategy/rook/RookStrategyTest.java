package chess.domain.strategy.rook;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.model.player.Color;
import chess.domain.model.piece.shape.strategy.rook.RookStrategy;
import chess.domain.model.position.Position;
import chess.domain.model.piece.shape.strategy.PieceStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookStrategyTest {

    PieceStrategy rookStrategy = new RookStrategy();

    @Test
    @DisplayName("세로로 움직인다.")
    void moveFile() {
        //given
        // when, then
        assertDoesNotThrow(() -> rookStrategy.validateDirection(
                Position.from(1, 'a'), // movablePiecePosition
                Position.from(3, 'a'), // targetPosition
                Color.WHITE, // 이동할 기물 진영
                false
        ));
    }

    @Test
    @DisplayName("가로로 움직인다.")
    void moveRank() {
        //given
        // when, then
        assertDoesNotThrow(() -> rookStrategy.validateDirection(
                Position.from(0, 'a'), // movablePiecePosition
                Position.from(0, 'e'), // targetPosition,
                Color.WHITE, // 이동할 기물 진영
                false
        ));
    }

    @Test
    @DisplayName("타겟이 대각에 있으면 예외가 발생한다.")
    void throwExceptionWhenTargetIsOnDiagonal() {
        //given, when, then
        assertThatThrownBy(() -> rookStrategy.validateDirection(
                Position.from(0, 'a'), // movablePiecePosition
                Position.from(1, 'b'), // targetPosition
                Color.WHITE, // 이동할 기물 진영
                false
        ))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
