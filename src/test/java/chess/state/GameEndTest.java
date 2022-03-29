package chess.state;

import static org.assertj.core.api.Assertions.*;

import chess.ChessBoard;
import chess.piece.Color;
import chess.piece.King;
import chess.position.Position;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameEndTest {

    private GameEnd gameEnd;

    @BeforeEach
    void setUp() {
        gameEnd = new GameEnd(ChessBoard.createChessBoard(), Color.WHITE);
    }

    @Test
    @DisplayName("finished 호출 후 finished 상태로 변한다.")
    void callFinishedAfterFinishedState() {
        assertThat(gameEnd.finished()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("move 메서드 호출 시 예외 발생")
    void throwExceptionCallMove() {
        assertThatThrownBy(() -> gameEnd.move(Position.from("e5"), Position.from("e6")))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("isFinished 메서드 호출 시 false를 반환")
    void isFinished() {
        assertThat(gameEnd.isFinished()).isFalse();
    }

    @Test
    @DisplayName("isGameEnd 메서드 호출 시 true를 반환")
    void isGameEnd() {
        assertThat(gameEnd.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("score 메서드를 호출하면 예외 발생")
    void throwExceptionCallScore() {
        assertThatThrownBy(() -> gameEnd.score())
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("start 메서드 호출 시 예외 발생")
    void throwExceptionCallStart() {
        assertThatThrownBy(() -> gameEnd.start())
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("winner 호출 시 정상적으로 우승자 반환")
    void winner() {
        GameEnd gameEnd = new GameEnd(new ChessBoard(
            List.of(new King(Color.WHITE, Position.from("a1")))), Color.WHITE);

        assertThat(gameEnd.winner()).isEqualTo(Color.WHITE);
    }
}
