package chess.domain.state;

import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {
    private State ready;

    @BeforeEach
    void setUp() {
        ready = new Ready(new Pieces(PieceFactory.initialPieces()));
    }

    @DisplayName("시작 전 상태인지 확인한다.")
    @Test
    void 시작_전_상태인지_확인() {
        boolean isReady = ready.isReady();

        assertThat(isReady).isTrue();
    }

    @DisplayName("끝난 상태인지 확인한다.")
    @Test
    void 끝난_상태인지_확인() {
        boolean isFinish = ready.isFinish();

        assertThat(isFinish).isFalse();
    }

    @DisplayName("게임을 시작한다")
    @Test
    void 게임시작() {
        State state = ready.start();

        assertThat(state).isInstanceOf(Running.class);
    }

    @DisplayName("게임을 끝낸다")
    @Test
    void 게임끝() {
        State state = ready.end();

        assertThat(state).isInstanceOf(End.class);
    }
}
