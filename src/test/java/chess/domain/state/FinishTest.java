package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Command;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishTest {

    @Test
    @DisplayName("Finish 상태인 경우 명령을 실행할 수 없다.")
    void execute() {
        Command command = Command.MOVE;
        List<String> input = List.of("move", "b2", "b4");
        GameState gameState = new Finish(ChessBoard.create());

        assertThatThrownBy(() -> gameState.execute(command, input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
