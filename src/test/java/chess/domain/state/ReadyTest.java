package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Command;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("Ready 상태에서 start 명령어를 입력하면 WhiteTurn 상태가 된다.")
    void executeStartCommand() {
        GameState gameState = new Ready(ChessBoard.create());
        gameState = gameState.execute(Command.START, List.of("start"));

        assertThat(gameState).isInstanceOf(WhiteTurn.class);
    }

    @Test
    @DisplayName("Ready 상태에서 end 명령어를 입력하면 End 상태가 된다.")
    void executeEndCommand() {
        GameState gameState = new Ready(ChessBoard.create());
        gameState = gameState.execute(Command.END, List.of("end"));

        assertThat(gameState).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("Ready 상태에서 move 명령어를 입력하면 오류가 발생한다.")
    void executeMoveCommand() {
        Command command = Command.MOVE;
        List<String> input = List.of("move", "b2", "b4");
        GameState gameState = new Ready(ChessBoard.create());

        assertThatThrownBy(() -> gameState.execute(command, input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Ready 상태에서 status 명령어를 입력하면 오류가 발생한다.")
    void executeStatusCommand() {
        Command command = Command.STATUS;
        List<String> input = List.of("status");
        GameState gameState = new Ready(ChessBoard.create());

        assertThatThrownBy(() -> gameState.execute(command, input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
