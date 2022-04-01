package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;
import chess.domain.State.Finish;
import chess.domain.State.Ready;
import chess.domain.State.Start;
import chess.domain.piece.generator.NormalPiecesGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadyTest {

    private final ChessBoard chessBoard = new ChessBoard(new NormalPiecesGenerator());

    @Test
    @DisplayName("준비 상태에서 start 입력 시 시작 상태가 된다.")
    void proceedStart() {
        final var state = new Ready();
        final var actual = state.proceed(chessBoard, new GameCommand("start"));

        assertThat(actual).isInstanceOf(Start.class);
    }

    @Test
    @DisplayName("준비 상태에서 end 입력 시 종료 상태가 된다.")
    void proceedEnd() {
        final var state = new Ready();
        final var actual = state.proceed(chessBoard, new GameCommand("end"));

        assertThat(actual).isInstanceOf(Finish.class);
    }

    @Test
    @DisplayName("준비 상태에서 move 입력 시 예외가 발생한다.")
    void proceedMove() {
        final var state = new Ready();

        assertThatThrownBy(() ->
                state.proceed(chessBoard, new GameCommand("move", "b1", "b2")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("준비 상태에서는 해당 명령어를 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("준비 상태에서 status 입력 시 예외가 발생한다.")
    void proceedStatus() {
        final var state = new Ready();

        assertThatThrownBy(() ->
                state.proceed(chessBoard, new GameCommand("status")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("준비 상태에서는 해당 명령어를 사용할 수 없습니다.");
    }
}
