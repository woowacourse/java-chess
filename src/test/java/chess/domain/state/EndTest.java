package chess.domain.state;

import chess.Command;
import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @DisplayName("End 상태에서는 진행할 수 없다.")
    @Test
    void cannot_Execute() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState end = new End(board);

        Assertions.assertThatThrownBy(() -> end.execute(Command.END))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }
}
