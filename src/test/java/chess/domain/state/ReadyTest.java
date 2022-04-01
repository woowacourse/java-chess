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
                .move(new Position(0, 0), new Position(0, 1)))
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

        assertThatThrownBy(() -> new Ready().computeScore(Color.WHITE))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}