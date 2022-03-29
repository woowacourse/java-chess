package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {

    @DisplayName("End 상태에서는 진행할 수 없다.")
    @Test
    void cannot_Execute() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState end = new Finished(board);

        Assertions.assertThatThrownBy(end::end)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }
}
