package chess.view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.view.command.Command;
import chess.view.command.Ready;
import chess.view.command.Running;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RunningTest {

    @ParameterizedTest
    @ValueSource(strings = {"start", "move1", "end1"})
    @DisplayName("현재 상태에서 진행 가능한 커맨드가 아니면 예외발생")
    void runException(String inputLine) {
        Command command = new Running(ChessBoard.createNewChessBoard());
        assertThatThrownBy(() -> command.run(inputLine))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 진행상태에서 불가능한 명령어입니다.");
    }
}
