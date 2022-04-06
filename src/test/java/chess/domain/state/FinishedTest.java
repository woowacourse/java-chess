package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static chess.domain.PieceColor.BLACK;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.game.Score;
import chess.domain.game.Status;

class FinishedTest {

    @Test
    @DisplayName("Finished를 생성할 수 있다.")
    void createStateReady() {
        assertThat(new Finished(new ChessBoard(Map.of()))).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("상태가 Finished일 때 Start를 실행하면 예외가 발생한다.")
    void changeStateRunningToFinished() {
        State state = new Finished(new ChessBoard(Map.of()));

        assertThatThrownBy(() -> state.start())
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("새로 시작 할 수 없습니다.");
    }

    @Test
    @DisplayName("상태가 Finished일 때 End를 실행할 수 있다.")
    void changeStateEndToFinished() {
        State state = new Finished(new ChessBoard(Map.of()));

        assertThat(state.end()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("상태가 Finished일 때 체스판을 볼 수 있습니다.")
    void validGetChessBoardToFinshed() {
        State state = new Finished(new ChessBoard(Map.of()));

        assertThat(state.chessBoard()).isInstanceOf(ChessBoard.class);
    }

    @Test
    @DisplayName("상태가 Finished일 때 점수를 확인할 수 있다.")
    void validGetScoreToFinished() {
        State state = new Finished(new ChessBoard(ChessBoardFactory.initChessBoard()));

        assertThat(state.status()).isEqualTo(new Status(new Score(34.0), new Score(34.0), BLACK));
    }

    @Test
    @DisplayName("상태가 Ready일 때는 게임의 종료 여부는 true이다.")
    void isFinishedToTrueToFinished() {
        State state = new Finished(new ChessBoard(ChessBoardFactory.initChessBoard()));

        assertThat(state.isFinished()).isTrue();
    }
}
