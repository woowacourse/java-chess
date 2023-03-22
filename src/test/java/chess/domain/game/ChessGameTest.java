package chess.domain.game;

import chess.view.PositionConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.view.PositionConverter.convertToSourcePosition;
import static chess.view.PositionConverter.convertToTargetPosition;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    @Test
    @DisplayName("올바른 턴이 아니면 예외를 던진다.")
    void progress() {
        String command = "c7c5";
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        chessGame.inputGameCommand(GameCommand.START);
        assertThatThrownBy(() -> chessGame.progress(convertToSourcePosition(command), convertToTargetPosition(command)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 지금은 WHITE차례입니다.");
    }

    @Test
    @DisplayName("게임이 끝나고 move를 하면 예외를 던진다.")
    void gameTerminated() {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        chessGame.inputGameCommand(GameCommand.START);
        List<String> commands = List.of("b1c3", "h7h6", "c3b5", "h6h5", "b5c7", "h5h4", "c7e8");
        String commandAfterKingDead = "h4h3";
        for (String command : commands) {
            chessGame.progress(convertToSourcePosition(command), PositionConverter.convertToTargetPosition(command));
        }
        assertThatThrownBy(() ->
                chessGame.progress(convertToSourcePosition(commandAfterKingDead), convertToTargetPosition(commandAfterKingDead)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 게임이 진행중이 아닙니다.");
    }

    @Test
    @DisplayName("게임이 Ready인 상태에서는 시작 이외의 커맨드가 들어왔을 시 예외를 던진다.")
    void gameReadyStatus() {
        ChessGame chessGame = new ChessGame(new Board());
        assertThatThrownBy(() -> chessGame.inputGameCommand(GameCommand.STATUS))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 게임을 먼저 시작해주세요.");
    }

    @Test
    @DisplayName("게임이 Running인 상태에서는 시작 커맨드가 들어왔을 시 예외를 던진다.")
    void gameRunningStatusWithStart() {
        ChessGame chessGame = new ChessGame(new Board());
        chessGame.inputGameCommand(GameCommand.START);
        assertThatThrownBy(() -> chessGame.inputGameCommand(GameCommand.START))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 게임이 이미 실행중입니다.");
    }

    @Test
    @DisplayName("게임이 Running인 상태에서는 상태 커맨드가 들어왔을 시 예외를 던진다.")
    void gameRunningStatusWithStatus() {
        ChessGame chessGame = new ChessGame(new Board());
        chessGame.inputGameCommand(GameCommand.START);
        assertThatThrownBy(() -> chessGame.inputGameCommand(GameCommand.STATUS))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 게임이 실행중 일 때는 결과를 알 수 없습니다.");
    }

    @Test
    @DisplayName("게임이 Terminated인 상태에서는 시작 커맨드가 들어왔을 시 예외를 던진다.")
    void gameTerminatedStatusWithStart() {
        ChessGame chessGame = new ChessGame(new Board());
        chessGame.inputGameCommand(GameCommand.START);
        chessGame.inputGameCommand(GameCommand.END);
        assertThatThrownBy(() -> chessGame.inputGameCommand(GameCommand.START))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 종료된 게임이므로 시작할 수 없습니다.");
    }

    @Test
    @DisplayName("게임이 Terminated인 상태에서는 무브 커맨드가 들어왔을 시 예외를 던진다.")
    void gameTerminatedStatusWithMove() {
        ChessGame chessGame = new ChessGame(new Board());
        chessGame.inputGameCommand(GameCommand.START);
        chessGame.inputGameCommand(GameCommand.END);
        assertThatThrownBy(() -> chessGame.inputGameCommand(GameCommand.MOVE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 종료된 게임이므로 움직일 수 없습니다.");
    }
}
