package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;
import chess.domain.State.BlackTurn;
import chess.domain.State.Finish;
import chess.domain.piece.generator.NormalPiecesGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StartTest {

    private final ChessBoard chessBoard = new ChessBoard(new NormalPiecesGenerator());

    @Test
    @DisplayName("시작 상태에서 start 입력 시 예외가 발생한다.")
    void proceedStart() {
        final var state = new BlackTurn();

        assertThatThrownBy(() ->
                state.proceed(chessBoard, new GameCommand("start")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("시작 상태에서는 다시 시작할 수 없습니다.");
    }

    @Test
    @DisplayName("시작 상태에서 end 입력 시 종료 상태가 된다.")
    void proceedEnd() {
        final var state = new BlackTurn();
        final var actual = state.proceed(chessBoard, new GameCommand("end"));

        assertThat(actual).isInstanceOf(Finish.class);
    }

    @Test
    @DisplayName("검은팀 차례에서 흰색 말을 move 할 시 예외가 발생한다.")
    void proceedMove() {
        final var state = new BlackTurn();

        assertThatThrownBy(() ->
                state.proceed(chessBoard, new GameCommand("move", "b2", "b4")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("흰색 차례가 아닙니다.");
    }

    @Test
    @DisplayName("시작 상태에서 status 입력 시 예외가 발생한다.")
    void proceedStatus() {
        final var state = new BlackTurn();

        assertThat(state.proceed(chessBoard, new GameCommand("status")))
                .isInstanceOf(BlackTurn.class);
    }
}
