package chess.domain.strategy.rook;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Color;
import chess.domain.piece.Position;
import chess.domain.strategy.PieceStrategy;
import chess.dto.PositionDto;
import chess.dto.request.MoveRequest;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookStrategyTest {

    PieceStrategy rookStrategy = new RookStrategy();

    @Test
    @DisplayName("세로로 움직인다.")
    void moveFile() {
        //given
        MoveRequest request = MoveRequest.from(
                Collections.emptyList(), // 모든 포지션
                Color.WHITE, // 이동할 기물 진영
                new PositionDto(Position.from(1, 'a')), // movablePiecePosition
                new PositionDto(Position.from(3, 'a')) // targetPosition
        );
        // when, then
        assertDoesNotThrow(() -> rookStrategy.validateDirection(request));
    }

    @Test
    @DisplayName("가로로 움직인다.")
    void moveRank() {
        //given
        MoveRequest request = MoveRequest.from(
                List.of(Position.from(0, 'a')),
                Color.WHITE, // 이동할 기물 진영
                new PositionDto(Position.from(0, 'a')), // movablePiecePosition
                new PositionDto(Position.from(0, 'e')) // targetPosition
        );

        // when, then
        assertDoesNotThrow(() -> rookStrategy.validateDirection(request));
    }

    @Test
    @DisplayName("타겟이 대각에 있으면 예외가 발생한다.")
    void throwExceptionWhenTargetIsOnDiagonal() {
        MoveRequest request = MoveRequest.from(
                Collections.emptyList(), // 모든 포지션
                Color.WHITE, // 이동할 기물 진영
                new PositionDto(Position.from(0, 'a')), // movablePiecePosition
                new PositionDto(Position.from(1, 'b')) // targetPosition
        );
        // when, then
        assertThatThrownBy(() -> rookStrategy.validateDirection(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
