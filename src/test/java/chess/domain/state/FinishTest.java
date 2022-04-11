package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.command.GameCommand;
import chess.domain.piece.generator.NormalPiecesGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinishTest {

    private final ChessBoard chessBoard = new ChessBoard(new NormalPiecesGenerator());

    @Test
    @DisplayName("종료상태에서 start 입력 시 하얀색의 시작상태가 발생한다.")
    void proceedStart() {
        final var state = new Finish();
        final State actual = state.proceed(chessBoard, GameCommand.of("start"));

        assertThat(actual).isInstanceOf(WhiteRunning.class);
    }

    @Test
    @DisplayName("준비 상태에서 end 입력 시 종료 상태가 된다.")
    void proceedEnd() {
        final var state = new Finish();

        assertThatThrownBy(() ->
                state.proceed(chessBoard, GameCommand.of("end")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("종료 상태에서는 해당 명령어를 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("준비 상태에서 move 입력 시 예외가 발생한다.")
    void proceedMove() {
        final var state = new Finish();

        assertThatThrownBy(() ->
                state.proceed(chessBoard, GameCommand.of("move", "b1", "b2")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("종료 상태에서는 해당 명령어를 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("준비 상태에서 status 입력 시 예외가 발생한다.")
    void proceedStatus() {
        final var state = new Finish();

        assertThatThrownBy(() ->
                state.proceed(chessBoard, GameCommand.of("status")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("종료 상태에서는 해당 명령어를 사용할 수 없습니다.");
    }
}
