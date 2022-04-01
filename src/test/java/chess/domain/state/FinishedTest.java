package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {

    private Finished finished;

    @BeforeEach
    void setUp() {
        finished = new Finished(ChessBoard.createChessBoard(), Color.WHITE);
    }

    @Test
    @DisplayName("start 메서드 호출 시 예외 발생")
    void throwExceptionCallStart() {
        assertThatThrownBy(() -> finished.start())
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("finished 메서드 호출 시 예외 발생")
    void throwExceptionCallFinished() {
        assertThatThrownBy(() -> finished.finished())
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("move 메서드 호출 시 예외 발생")
    void throwExceptionCallMove() {
        assertThatThrownBy(() -> finished.move(Position.from("a1"), Position.from("a2")))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("isFinished 메서드 호출 시 ture 반환")
    void isFinished() {
        assertThat(finished.isFinished()).isTrue();
    }

    @Test
    @DisplayName("isGameEnd 메서드 호출 시 false 반환")
    void isGameEnd() {
        assertThat(finished.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("score 메서드 호출 시 예외 발생")
    void throwExceptionCallScore() {
        assertThatThrownBy(() -> finished.score())
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("winner 메서드 호출 시 예외 발생")
    void throwExceptionCallWinner() {
        assertThatThrownBy(() -> finished.winner())
            .isInstanceOf(UnsupportedOperationException.class);
    }
}
