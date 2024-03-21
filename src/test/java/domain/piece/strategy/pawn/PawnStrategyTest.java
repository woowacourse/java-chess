package domain.piece.strategy.pawn;

import domain.piece.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnStrategyTest {

    @DisplayName("존재하는 폰의 전략이 없다면 에러를 발생한다.")
    @Test
    void notExistMoveStrategy() {
        Assertions.assertThatThrownBy(() -> PawnStrategy.getMoveStrategy(3, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }

    @DisplayName("폰은 뒤로 갈 수 없다.")
    @Test
    void pawnCanNotGoBack() {
        Assertions.assertThatThrownBy(() -> PawnStrategy.DOWN.validateMoveBackward(Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 뒤로 갈 수 없습니다.");
    }
}
