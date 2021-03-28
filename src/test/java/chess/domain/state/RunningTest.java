package chess.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RunningTest {
    private State running;

    @BeforeEach
    void setUp() {
        running = new Running();
    }

    @DisplayName("시작 전 상태인지 확인한다.")
    @Test
    void 시작_전_상태인지_확인() {
        boolean isReady = running.isReady();

        assertThat(isReady).isFalse();
    }

    @DisplayName("끝난 상태인지 확인한다.")
    @Test
    void 끝난_상태인지_확인() {
        boolean isFinish = running.isFinish();

        assertThat(isFinish).isFalse();
    }

    @DisplayName("게임을 시작한다")
    @Test
    void 게임시작() {
        assertThatThrownBy(() -> running.start())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("게임을 끝낸다")
    @Test
    void 게임끝() {
        State state = running.end();

        assertThat(state).isInstanceOf(End.class);
    }
}
