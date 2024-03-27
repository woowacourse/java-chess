package domain.piece.strategy;

import domain.direction.Direction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceStrategyTest {

    @DisplayName("존재하는 전략이 없다면 에러를 발생한다.")
    @Test
    void notExistMoveStrategy() {

        PieceStrategy pieceStrategy = new PieceStrategy(Direction.STRAIGHT_DIRECTION);

        Assertions.assertThatThrownBy(() -> pieceStrategy.findDirection(1, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }
}
