package chess.domain.state;

import chess.domain.board.BoardCache;
import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinishTest {

    @Test
    @DisplayName("Finish 상태 확인")
    void checkFinish() {
        Finish finish = new Finish(new Chessboard(BoardCache.create()));

        assertThat(finish.isFinished()).isTrue();
    }

    @Test
    @DisplayName("최종 점수 확인")
    void checkScore() {
        Finish finish = new Finish(new Chessboard(BoardCache.create()));

        assertThat(finish.computeScore(Color.WHITE).getScore()).isEqualTo(38);
    }

    @Test
    @DisplayName("Finish 상태에서 start를 하는 경우")
    void checkStartException() {
        Finish finish = new Finish(new Chessboard(BoardCache.create()));

        assertThat(finish.start()).isInstanceOf(Play.class);
    }

    @Test
    @DisplayName("Finish 상태에서 move를 하는 경우")
    void checkMoveException() {
        assertThatThrownBy(() -> new Finish(new Chessboard(BoardCache.create()))
                .move(Position.of(0, 0), Position.of(0, 1)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Finish 상태에서 end를 하는 경우")
    void checkFinishException() {
        assertThatThrownBy(() -> new Finish(new Chessboard(BoardCache.create())).end())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("현재 턴을 String으로 확인")
    void checkTurnString() {
        Finish finish = new Finish(Chessboard.create());

        assertThat(finish.turn()).isEqualTo("blank");
    }

    @Test
    @DisplayName("running 중인지 확인")
    void isRunning() {
        Finish finish = new Finish(Chessboard.create());

        assertThat(finish.isRunning()).isFalse();
    }
}
