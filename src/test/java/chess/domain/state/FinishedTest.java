package chess.domain.state;

import chess.domain.CachedPosition;
import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {

    @DisplayName("start 명령을 실행하면 예외를 반환한다.")
    @Test
    void cannot_Start() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState finished = new Finished(board);

        Assertions.assertThatThrownBy(finished::start)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("start 명령을 실행하면 예외를 반환한다.")
    @Test
    void cannot_Move() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState finished = new Finished(board);

        Assertions.assertThatThrownBy(() -> finished.move(CachedPosition.a1, CachedPosition.a2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("end 명령을 실행하면 예외를 반환한다.")
    @Test
    void cannot_End() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState finished = new Finished(board);

        Assertions.assertThatThrownBy(finished::end)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }
}
