package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    @DisplayName("게임이 실행 된 이후에 다시 start를 호출하면 예외가 발생한다.")
    @Test
    void cannot_Start() {
        ChessGame chessGame = new ChessGame(new Board(new CreateCompleteBoardStrategy()));
        chessGame.start();

        assertThatThrownBy(chessGame::start)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("게임이 실행되지 않았는데 move를 호출하면 예외가 발생한다.")
    @Test
    void cannot_Move() {
        ChessGame chessGame = new ChessGame(new Board(new CreateCompleteBoardStrategy()));

        assertThatThrownBy(() -> chessGame.move(CachedPosition.a1, CachedPosition.a2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("end를 호출하면 게임이 종료된다.")
    @Test
    void end() {
        ChessGame chessGame = new ChessGame(new Board(new CreateCompleteBoardStrategy()));

        chessGame.end();

        assertThat(chessGame.isFinished()).isTrue();
    }
}
