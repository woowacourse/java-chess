package model.status;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.ChessBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {

    @Test
    @DisplayName("running 상태면 isRunning 이 true이다.")
    void isRunning() {
        final GameStatus gameStatus = Initialization.gameSetting(List.of("start"));

        assertThat(gameStatus.isRunning()).isTrue();
    }

    @Test
    @DisplayName("실행 중에서 move 하면 Running 이다.")
    void running() {
        final GameStatus gameStatus = Initialization.gameSetting(List.of("start"));
        final ChessBoard chessBoard = new ChessBoard();
        chessBoard.setting();

        final List<String> moveCommand = List.of("move", "a2", "a3");
        assertThat(gameStatus.play(moveCommand, chessBoard))
                .isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("실행 중에서 end 하면 End 이다.")
    void ending() {
        final GameStatus gameStatus = Initialization.gameSetting(List.of("start"));
        final ChessBoard chessBoard = new ChessBoard();
        chessBoard.setting();

        final List<String> endCommand = List.of("end");
        assertThat(gameStatus.play(endCommand, chessBoard))
                .isInstanceOf(End.class);
    }
}
