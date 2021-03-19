import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.PieceOperator;
import chess.Turn;
import chess.board.Board;
import chess.command.Command;
import chess.gamestate.Finished;
import chess.gamestate.GameState;
import chess.gamestate.Ready;
import chess.gamestate.Running;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameStateTest {
    private Board board;
    private PieceOperator pieceOperator;

    @BeforeEach
    void setUp() {
        board = new Board();
        pieceOperator = new PieceOperator(board);
        pieceOperator.initialize();
    }

    @Test
    @DisplayName("레디 상태에서 start 입력이 들어오는 경우 상태 변화 테스트")
    void createReadyState() {
        GameState ready = new Ready(pieceOperator);
        ready = ready.operateCommand(Command.START, Collections.emptyList());
        assertThat(ready instanceof Running).isTrue();
    }

    @Test
    @DisplayName("레디 상태에서 start 외의 입력이 들어오는 경우 예외처리 테스트")
    void invalidInputWhenRunningState() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Ready(pieceOperator).operateCommand(Command.END, Collections.emptyList()))
            .withMessage("start 이외의 명령은 입력할 수 없습니다.");

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Ready(pieceOperator).operateCommand(Command.STATUS, Collections.emptyList()))
            .withMessage("start 이외의 명령은 입력할 수 없습니다.");

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Ready(pieceOperator).operateCommand(Command.MOVE, Arrays.asList("b2", "b3")))
            .withMessage("start 이외의 명령은 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("레디 상태에서 start 입력이 들어오는 경우 상태 변화 테스트")
    void createRunningState() {
        GameState running = new Running(pieceOperator, new Turn());

        assertThat(running.operateCommand(Command.MOVE, Arrays.asList("b2", "b3")) instanceof Running).isTrue();
        assertThat(running.operateCommand(Command.END, Collections.emptyList()) instanceof Finished).isTrue();
        assertThat(running.operateCommand(Command.STATUS, Collections.emptyList()) instanceof Running).isTrue();
    }

    @Test
    @DisplayName("레디 상태에서 start 외의 입력이 들어오는 경우 예외처리 테스트")
    void invalidInputWhenReadyState() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Running(pieceOperator, new Turn()).operateCommand(Command.START, Collections.emptyList()))
            .withMessage("올바르지 않은 입력입니다.");
    }

    @Test
    @DisplayName("게임이 끝난 상태에서 입력이 들어오는 경우 예외처리 테스트")
    void operateCommandWhenFinishedState() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Finished().operateCommand(Command.START, Collections.emptyList()))
            .withMessage("올바르지 않은 입력입니다.");
    }
}
