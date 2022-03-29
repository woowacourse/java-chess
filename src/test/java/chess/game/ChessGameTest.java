package chess.game;

import static org.assertj.core.api.Assertions.*;

import chess.ChessBoard;
import chess.command.Command;
import chess.state.Ready;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new Ready(ChessBoard.createChessBoard()));
        chessGame.start();
    }

    @Test
    @DisplayName("End 명령어를 입력해서 끝나는지 확인")
    void executeEndCommand() {
        chessGame.execute(Command.from("end"));

        assertThat(chessGame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Move 명령어를 입력해서 정상적으로 실행되는지 확인")
    void executeMoveCommand() {
        assertThatCode(() -> chessGame.execute(Command.from("move b2 b3")))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("잘못된 명령어 입력하면 예외 발생")
    void throwExceptionExecute() {
        assertThatThrownBy(() -> chessGame.execute(Command.from("helpMe")))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("isGameEnd가 정상적으로 실행되는지 확인")
    void isGameEnd() {
        assertThat(chessGame.isGameEnd()).isFalse();
    }
}
