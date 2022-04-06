package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static chess.domain.PieceColor.NONE;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.board.Position;
import chess.domain.game.Score;
import chess.domain.game.Status;

class RunningTest {

    @Test
    @DisplayName("Running 상태를 생성 할 수 있다.")
    void createStateRunning() {
        assertThat(new Running(new ChessBoard(Map.of()), Turn.WHITE)).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("상태가 Running일 때 Start를 하면 예외가 발생한다.")
    void invalidStartToReady() {
        State state = new Running(new ChessBoard(Map.of()), Turn.WHITE);

        assertThatThrownBy(() -> state.start())
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("상태가 Running인 경우 새로 시작 할 수 없습니다.");
    }

    @Test
    @DisplayName("상태가 Running일 때 End를 하면 상태가 변경된다.")
    void validEndToRunning() {
        assertThat(new Running(new ChessBoard(Map.of()), Turn.WHITE).end()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("상태가 Running일 때 move를 하면 Running 상태가 유지된다.")
    void validMoveToRunning() {
        final State state = new Running(new ChessBoard(ChessBoardFactory.initChessBoard()), Turn.WHITE);
        final Position from = Position.valueOf("a2");
        final Position to = Position.valueOf("a3");

        assertThat(state.move(from, to)).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("상태가 Running일 때 보드판을 호출할 수 있습니다.")
    void validateChessBoardToRunning() {
        final State state = new Running(new ChessBoard(ChessBoardFactory.initChessBoard()), Turn.WHITE);

        assertThat(state.getChessBoard()).isInstanceOf(ChessBoard.class);
    }

    @Test
    @DisplayName("상태가 Running일 때 각 팀의 점수를 호출할 수 있습니다.")
    void validGetScoreToRunning() {
        final State state = new Running(new ChessBoard(ChessBoardFactory.initChessBoard()), Turn.WHITE);

        assertThat(state.getStatus()).isEqualTo(new Status(new Score(34.0), new Score(34.0), NONE));
    }

    @Test
    @DisplayName("상태가 Running일 때는 게임의 종료 여부는 false이다.")
    void isFinishedToFalseToRunning() {
        assertThat(new Running(new ChessBoard(Map.of()), Turn.WHITE).isFinished()).isFalse();
    }
}
