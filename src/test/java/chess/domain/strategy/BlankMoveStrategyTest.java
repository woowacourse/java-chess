package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.BoardFactory;
import chess.domain.color.Color;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlankMoveStrategyTest {

    @Test
    @DisplayName("비어있는 칸이 선택되면 이동 전략이 Blank 전략이다.")
    void changeStrategy() {
        BlankMoveStrategy blankStrategy = new BlankMoveStrategy(new BoardFactory().getInitialBoard());

        assertThat(blankStrategy.changeStrategy(new Position(4, 4))).isInstanceOf(BlankMoveStrategy.class);
    }

    @Test
    @DisplayName("체스 판에 말이 없을 때 이동하려고 하면 예외를 던진다.")
    void move() {
        BlankMoveStrategy blankStrategy = new BlankMoveStrategy(new BoardFactory().getInitialBoard());

        assertThatIllegalArgumentException()
                .isThrownBy(() -> blankStrategy.move(Color.WHITE, new Position(1, 1), new Position(1, 2)))
                .withMessage("이동할 수 있는 말이 없습니다.");
    }
}
