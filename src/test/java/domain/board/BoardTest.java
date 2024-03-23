package domain.board;

import static domain.PositionFixture.*;

import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    @DisplayName("현재 차례가 아니라면 예외가 발생한다")
    void move2() {
        final Board board = new Board(BoardInitiator.init());
        board.move(B_TWO, B_THREE);

        Assertions.assertThatThrownBy(() -> board.move(B_THREE, B_FOUR))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 차례가 아닙니다.");
    }
}
