import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.gamestate.Finished;
import chess.domain.gamestate.Running;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class GameStateTest {

    private Board board;
    private ChessGame chessGame;
    private List<String> empty;

    @BeforeEach
    void setUp() {
        board = new Board();
        chessGame = new ChessGame(board);
        empty = new ArrayList<>();
    }

    @Test
    @DisplayName("레디 상태에서 start 입력이 들어오는 경우 상태 변화 테스트")
    void createReadyState() {
        chessGame.start();
        assertThat(chessGame.state() instanceof Running).isTrue();

        Command.STATUS.execute(chessGame, empty);
        assertThat(chessGame.state() instanceof Running).isTrue();

        Command.END.execute(chessGame, empty);
        assertThat(chessGame.state() instanceof Finished).isTrue();

    }

    @Test
    @DisplayName("레디 상태에서 start 외의 입력이 들어오는 경우 예외처리 테스트")
    void invalidInputWhenRunningState() {
        ChessGame chessGame = new ChessGame(board);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(Arrays.asList("move", "a1", "a2")))
                .withMessage("현재 상태에서 유효하지 않은 명령입니다.");

        assertThatIllegalArgumentException()
                .isThrownBy(chessGame::status)
                .withMessage("현재 상태에서 유효하지 않은 명령입니다.");
    }

    @Test
    @DisplayName("게임이 끝난 상태에서 입력이 들어오는 경우 예외처리 테스트")
    void operateCommandWhenFinishedState() {
        chessGame.end();
        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.status())
                .withMessage("현재 상태에서 유효하지 않은 명령입니다.");
    }

    @Test
    @DisplayName("게임이 진행되는 동안은 Running 상태를 반환하는지 테스트")
    void testRunningState() {
        ChessGame chessGame = new ChessGame(board);

        chessGame.start();
        chessGame.move(Arrays.asList("move", "b2", "b4"));
        assertThat(chessGame.state() instanceof Running).isTrue();
    }
}
