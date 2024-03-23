package domain.board;

import static domain.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    @DisplayName("현재 차례가 아니라면 예외가 발생한다")
    void turn() {
        final Board board = new Board(BoardInitiator.init());
        board.move(B_TWO, B_THREE);

        assertThatThrownBy(() -> board.move(B_THREE, B_FOUR))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 차례가 아닙니다.");
    }

    @Test
    @DisplayName("이동 경로에 다른 말이 있다면 예외가 발생한다")
    void path() {
        final Board board = new Board(BoardInitiator.init());

        assertThatThrownBy(() -> board.move(A_ONE, A_THREE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 말이 놓져 있습니다");
    }

    @Test
    @DisplayName("해당 말로 이동할 수 없는 위치가 입력되면 예외가 발생한다")
    void reachability() {
        final Board board = new Board(BoardInitiator.init());

        assertThatThrownBy(() -> board.move(B_TWO, H_TWO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말로 해당 위치를 갈 수 없습니다");

    }
}
