package chess.state;

import static chess.position.File.B;
import static chess.position.File.E;
import static chess.position.Rank.FIVE;
import static chess.position.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.ChessBoard;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Rook;
import chess.position.Position;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {

    @Test
    @DisplayName("finished 메서드 호출 후 Finished로 변경")
    void callFinishedAfterFinishedState() {
        State running = new Running(ChessBoard.createChessBoard(), Color.WHITE);

        assertThat(running.finished()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("기물을 움직여도 Running 상태인지 확인")
    void movePieceAfterRunningState() {
        State running = new Running(ChessBoard.createChessBoard(), Color.WHITE);

        assertThat(running.move(Position.from("d2"), Position.from("d4")))
            .isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("왕을 잡을 경우 GameEnd상태로 변화")
    void killOfKingAfterGameEndState() {
        State running = new Running(
            new ChessBoard(List.of(new King(Color.BLACK, Position.from("e8")),
                new Rook(Color.WHITE, Position.from("e1")),
                new King(Color.WHITE, Position.from("f1")))), Color.WHITE);

        assertThat(running.move(Position.from("e1"), Position.from("e8"))).isInstanceOf(
            GameEnd.class);
    }

    @Test
    @DisplayName("같은 플레이어가 연속해서 기물을 움직일 경우 예외 발생")
    void throwExceptionWhenMoveSameColorPieceSubsequently() {
        State running = new Running(new ChessBoard(
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
}
