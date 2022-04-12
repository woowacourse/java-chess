package chess.domain.state;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyTest {

    @Test
    @DisplayName("ready -> play")
    void checkChangePlay() {
        Ready ready = new Ready();

        assertThat(ready.start()).isInstanceOf(Play.class);
    }

    @Test
    @DisplayName("end 메서드 실행시 Finish 반환")
    void checkEnd() {
        Ready ready = new Ready();

        assertThat(ready.end()).isInstanceOf(Finish.class);
    }

    @Test
        @DisplayName("Ready 상태에서 move를 하는 경우")
    void checkMoveException() {
        assertThatThrownBy(() -> new Ready()
                .move(Position.of(0, 0), Position.of(0, 1)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Finish 상태 확인")
    void checkFinish() {
        Ready ready = new Ready();

        assertThat(ready.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Ready 상태일 때 점수를 구하려 하는 경우")
    void checkScore() {
        Ready ready = new Ready();

        assertThatThrownBy(() -> ready.computeScore(Color.WHITE))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("현재 턴을 String으로 확인")
    void checkTurnString() {
        Ready ready = new Ready();

        assertThat(ready.turn()).isEqualTo("blank");
    }

    @Test
    @DisplayName("running 중인지 확인")
    void isRunning() {
        Ready ready = new Ready();

        assertThat(ready.isRunning()).isFalse();
    }
}
