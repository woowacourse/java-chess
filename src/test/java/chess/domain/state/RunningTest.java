package chess.domain.state;

import static chess.domain.position.File.B;
import static chess.domain.position.File.E;
import static chess.domain.position.Rank.FIVE;
import static chess.domain.position.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.ChessBoard;
import chess.domain.game.Score;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {

    private Running running;

    @BeforeEach
    void setUp() {
        running = new Running(ChessBoard.createChessBoard(), Color.WHITE);
    }

    @Test
    @DisplayName("finished 메서드 호출 후 Finished로 변경")
    void callFinishedAfterFinishedState() {
        assertThat(running.finished()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("기물을 움직여도 Running 상태인지 확인")
    void movePieceAfterRunningState() {
        assertThat(running.move(Position.from("d2"), Position.from("d4")))
            .isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("왕을 잡을 경우 GameEnd상태로 변화")
    void killOfKingAfterGameEndState() {
        Running running = new Running(
            new ChessBoard(List.of(new King(Color.BLACK, Position.from("e8")),
                new Rook(Color.WHITE, Position.from("e1")),
                new King(Color.WHITE, Position.from("f1")))), Color.WHITE);

        assertThat(running.move(Position.from("e1"), Position.from("e8"))).isInstanceOf(
            GameEnd.class);
    }

    @Test
    @DisplayName("같은 플레이어가 연속해서 기물을 움직일 경우 예외 발생")
    void throwExceptionWhenMoveSameColorPieceSubsequently() {
        Running running = new Running(new ChessBoard(
            List.of(new King(Color.WHITE, new Position(E, FIVE)),
                new King(Color.BLACK, new Position(B, FIVE)))), Color.WHITE);

        State finalRunning = running.move(new Position(E, FIVE), new Position(E, SIX));

        assertAll(() -> {
            assertThatThrownBy(() -> finalRunning.move(new Position(E, SIX), new Position(E, FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(running.chessBoard().getPieces()).containsExactlyInAnyOrder(
                new King(Color.WHITE, new Position(E, SIX)),
                new King(Color.BLACK, new Position(B, FIVE)));
        });
    }

    @Test
    @DisplayName("점수 계산이 정확한지 확인")
    void score() {
        Running running = new Running(new ChessBoard(
            List.of(new Rook(Color.WHITE, Position.from("e5")),
                new Knight(Color.WHITE, Position.from("e6")),
                new Bishop(Color.BLACK, Position.from("a7")))), Color.WHITE);

        Score score = running.score();

        assertAll(()-> {
            assertThat(score.getBlackScore()).isEqualTo(new BigDecimal("3.0"));
            assertThat(score.getWhiteScore()).isEqualTo(new BigDecimal("7.5"));
        });
    }

    @Test
    @DisplayName("start메서드를 실행하면 예외 발생")
    void throwExceptionCallStart() {
        assertThatThrownBy(() -> running.start())
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("isFinished 호출 시 항상 false를 반환")
    void isFinished() {
        assertThat(running.isFinished()).isFalse();
    }

    @Test
    @DisplayName("isGameEnd 호출 시 항상 false를 반환")
    void isGameEnd() {
        assertThat(running.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("winner 메서드를 호출 시 예외 발생")
    void throwExceptionCallWinner() {
        assertThatThrownBy(() -> running.winner())
            .isInstanceOf(UnsupportedOperationException.class);
    }
}
