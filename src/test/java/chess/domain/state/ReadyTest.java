package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    private Ready ready;

    @BeforeEach
    void setUp() {
        ready = new Ready(ChessBoard.createChessBoard());
    }

    @Test
    @DisplayName("start 메서드 실행 후 Running으로 변경")
    void callStartAfterRunning() {
        Assertions.assertThat(ready.start()).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("finished 메서드 실행 후 Finished로 변경")
    void callFinishedAfterFinished() {
        Assertions.assertThat(ready.finished()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("move 메서드 호출 시 예외 발생")
    void throwExcetipnCallMove() {
        assertThatThrownBy(() -> ready.move(Position.from("e5"), Position.from("e6")))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("isFinished 호출 시 항상 false를 반환")
    void isFinished() {
        assertThat(ready.isFinished()).isFalse();
    }

    @Test
    @DisplayName("isGameEnd 호출 시 항상 false를 반환")
    void isGameEnd() {
        assertThat(ready.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("score 메서드를 호출 시 예외 발생")
    void throwExceptionCallScore() {
        assertThatThrownBy(() -> ready.score())
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("winner 메서드를 호출 시 예외 발생")
    void throwExceptionCallWinner() {
        assertThatThrownBy(() -> ready.winner())
            .isInstanceOf(UnsupportedOperationException.class);
    }
}
